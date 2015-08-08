/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.locator;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ResourceBundleControl;
import com.epcs.recursoti.configuracion.locator.handler.CustomHandlelResolver;

/**
 * ServiceLocator para Servicios de MiEntel
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public final class WebServiceLocator {

    // static final Logger LOGGER = Logger.getLogger(ServiceLocator.class
    // .getName());
    
    public static final String REQUEST_TIMEOUT_PROPERTY = "com.sun.xml.internal.ws.request.timeout";

    public static final String WSDL_PROPERTY = "wsdl";
    public static final String TARGET_NAMESPACE_PROPERTY = "targetnamespace";
    public static final String SERVICE_NAME_PROPERTY = "servicename";
    public static final String TIME_OUT_PROPERTY = "timeout";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String IS_AUTH = "isauth";

    private static WebServiceLocator servLocator = new WebServiceLocator();

    /**
     * Path de ubicacion de archivos .properties de los clientes WS
     */
    private static final String PATH = MiEntelProperties
            .getProperty("miEntel.webservices.properties.path");

    // Cache de Servicios
    private Map<String, Object> servicesCache = new HashMap<String, Object>();

    /**
     * @return the servicesCache
     */
    public Map<String, Object> getServicesCache() {
        return servicesCache;
    }

    /**
     * @param servicesCache
     *            the servicesCache to set
     */
    public void setServicesCache(Map<String, Object> servicesCache) {
        this.servicesCache = servicesCache;
    }

    /**
     * 
     * 
     * @throws WebServiceLocatorException
     */
    private WebServiceLocator() {

    }

    /**
     * @return
     * @throws WebServiceLocatorException
     */
    public static WebServiceLocator getInstance() {
        // if (servLocator == null) {
        // servLocator = new ServiceLocator();
        // }
        return servLocator;
    }

    public Object getService(Class<?> className, boolean fromCache)
            throws WebServiceLocatorException {
        return getServiceFromCache(className, fromCache);
    }

    private Object getServiceFromCache(Class<?> className, boolean fromCache)
            throws WebServiceLocatorException {

        String name = className.getCanonicalName();
        Object obj = null;

        if (!fromCache) {
            obj = servicesCache.get(name);
        }

        if (obj == null) {

            Object[] serviceArgs = null;
            Constructor<?> serviceConstructor = null;
            Class<?>[] serviceArgsClass = new Class<?>[] { URL.class,
                    QName.class };

            try {

                ResourceBundle bundle = ResourceBundle.getBundle(PATH + name,
                        ResourceBundleControl.getControl());

                serviceArgs = new Object[] {
                        new URL(bundle.getString(WSDL_PROPERTY)),
                        new QName(bundle.getString(TARGET_NAMESPACE_PROPERTY),
                                bundle.getString(SERVICE_NAME_PROPERTY)) };

                serviceConstructor = className.getConstructor(serviceArgsClass);
                obj = serviceConstructor.newInstance(serviceArgs);

                if (bundle.getString(IS_AUTH).equals("true")) {
                    ((Service) obj)
                            .setHandlerResolver(new CustomHandlelResolver(
                                    bundle.getString(USERNAME),
                                    bundle.getString(PASSWORD),
                                    bundle.getString(TARGET_NAMESPACE_PROPERTY),
                                    "AuthHeaderDocument"));
                }

                servicesCache.put(name, obj);
            } catch (Exception e) {
                throw new WebServiceLocatorException(e);
            }

        }

        return obj;

    }

    public Object getPort(Class<?> className, Class<?> port)
            throws WebServiceLocatorException {

        try {
            Object obj = ((Service) getService(className, false)).getPort(port);

            updateRequestTimeOut(className, obj);
            return obj;

        } catch (Exception e) {
            throw new WebServiceLocatorException(e.getMessage(), e.getCause());
        }

    }

    public void updateRequestTimeOut(Class<?> className, Object port) {
        String name = className.getCanonicalName();// .replace(".", "_");
        ResourceBundle bundle = ResourceBundle.getBundle(PATH + name,
                ResourceBundleControl.getControl());
        Map<String, Object> requestContext = ((BindingProvider) port)
                .getRequestContext();

        requestContext.put(REQUEST_TIMEOUT_PROPERTY, bundle
                .getString(TIME_OUT_PROPERTY));

        // requestContext.put(BindingProvider.USERNAME_PROPERTY, "weblogic");
        // requestContext.put(BindingProvider.PASSWORD_PROPERTY, "qpalwosk10");

    }

}
