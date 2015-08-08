/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.locator;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import com.epcs.recursoti.configuracion.PropertiesLoader;
import com.epcs.recursoti.configuracion.ResourceBundleControl;
import com.epcs.recursoti.configuracion.locator.handler.CustomHandlerResolver;

/**
 * Implementacion de pattern Locator para obtener instancias de Port para los
 * clientes de servicios<br>
 * <br>
 * Esta clase esta basada en WebServiceLocator de mientel-web-app, con algunas
 * modificaciones para determinar el path en que se encuentran los archivos de
 * propiedades de los servicios, segun las caracteristicas de mientel-uup-ejb<br>
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class WebServiceLocator {

    /**
     * Nombre de archivo de propiedades local
     */
    public static final String AUTH_PROPERTIES_NAME = "epcs_uup.properties";

    /**
     * Nombre propiedad local con patg de .properties de cliente de servicios
     */
    public static final String AUTHENTICATION_SERVICE_PROPERTIES_PATH = "perfilamiento.service.properties.path";

    // ------------------------------------ WS properties


    /**
     * Nombre de la propiedad con la URL del WSDL
     */
    public static final String WSDL_PROPERTY = "wsdl";
    
    /**
     * Nombre de propiedad con valor del namespace de los servicios
     */
    public static final String TARGET_NAMESPACE_PROPERTY = "targetnamespace";
    
    /**
     * Nombre de propiedad con el nombre del servicio en el Service Bus
     */
    public static final String SERVICE_NAME_PROPERTY = "servicename";

    /**
     * Nombre de propiedad con valor de timeout para los llamados a servicios
     */
    public static final String TIME_OUT_PROPERTY = "timeout";

    /**
     * Nombre propiedad con nombre de usuario para autenticacion en Service Bus
     */
    public static final String USERNAME = "username";
    
    /**
     * Nombre propiedad con password de autenticacion en Service Bus
     */
    public static final String PASSWORD = "password";
    
    /**
     * Nombre propiedad con flag que indica si debe autenticarse para consumir Servicio 
     */
    public static final String IS_AUTH = "isauth";

    

    /**
     * Nombre propiedad de WS que indica el timeout de consulta de un servicio
     */
    public static final String REQUEST_TIMEOUT_PROPERTY = "com.sun.xml.internal.ws.request.timeout";

    // -------------------------------- class attributes

    /**
     * Singleton instance
     */
    private static WebServiceLocator instance = new WebServiceLocator();


    // ------------------------------- instance attributes

    // Path de archivos de propiedades de servicios
    private String propertiesPath;

    // Cache de Servicios
    private Map<String, Object> servicesCache = new HashMap<String, Object>();

    // singleton
    protected WebServiceLocator() {
    }

    public static WebServiceLocator getInstance() {
        return instance;
    }

    /**
     * @return the propertiesPath
     */
    public String getPropertiesPath() {
        return propertiesPath;
    }

    /**
     * @param propertiesPath
     *            the propertiesPath to set
     */
    public void setPropertiesPath(String propertiesPath) {
        this.propertiesPath = propertiesPath;
    }

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
     * Inicializacion
     * 
     * @throws Exception
     */
    public void init() throws Exception {

        if (propertiesPath == null) {
            Properties props = PropertiesLoader.loadProperties(this.getClass(),
                    AUTH_PROPERTIES_NAME);
            propertiesPath = props
                    .getProperty(AUTHENTICATION_SERVICE_PROPERTIES_PATH);
        }
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

                ResourceBundle bundle = ResourceBundle.getBundle(getPropertiesPath() + name,
                        ResourceBundleControl.getControl());

                serviceArgs = new Object[] {
                        new URL(bundle.getString(WSDL_PROPERTY)),
                        new QName(bundle.getString(TARGET_NAMESPACE_PROPERTY),
                                bundle.getString(SERVICE_NAME_PROPERTY)) };

                serviceConstructor = className.getConstructor(serviceArgsClass);
                obj = serviceConstructor.newInstance(serviceArgs);

                if (bundle.getString(IS_AUTH).equals("true")) {
                    ((Service) obj)
                            .setHandlerResolver(new CustomHandlerResolver(
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
            // inicializacion
            init();

            Object obj = ((Service) getService(className, false)).getPort(port);

            updateRequestTimeOut(className, obj);
            return obj;

        } catch (Exception e) {
            throw new WebServiceLocatorException(e.getMessage(), e.getCause());
        }

    }

    public void updateRequestTimeOut(Class<?> className, Object port) {
        String name = className.getCanonicalName();// .replace(".", "_");

        ResourceBundle bundle = ResourceBundle.getBundle(getPropertiesPath()
                + name, ResourceBundleControl.getControl());
        Map<String, Object> requestContext = ((BindingProvider) port)
                .getRequestContext();

        requestContext.put(REQUEST_TIMEOUT_PROPERTY, bundle
                .getString(TIME_OUT_PROPERTY));


    }

}
