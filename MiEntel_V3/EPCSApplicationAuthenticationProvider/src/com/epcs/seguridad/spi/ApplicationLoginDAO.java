/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.seguridad.spi;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import com.epcs.erp.seguridad.ERPGestionDeSeguridadService;
import com.epcs.erp.seguridad.ERPGestionDeSeguridadServicePortType;
import com.epcs.erp.seguridad.types.AutenticarAplicacionType;
import com.epcs.erp.seguridad.types.ResultadoAutenticarAplicacionType;
import com.epcs.seguridad.bean.ApplicationLoginBean;
import com.epcs.seguridad.bean.ApplicationLoginRespuestaBean;

/**
 * Esta clase tiene como finalidad ocultar el comportamiento (proxy) de la logica de consumo del servicio de 
 * autenticacion de aplicaciones externas para el proyecto MiEntel v3.0 a traves del numero identificador idp.
 * A traves de la biblioteca ERP_GestionDeSeguridadService-client valida una aplicacion por medio del idp.
 * @author mmartinez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs).
 */
public class ApplicationLoginDAO {

    /**
     * Servicio de autenticacion.
     */
    private ERPGestionDeSeguridadServicePortType service = null;
    
    private static final String CODIGO_RESPUESTA_OK = "0000";
    
    public static final String WSDL_PROPERTY = "wsdl";
    public static final String TARGET_NAMESPACE_PROPERTY = "targetnamespace";
    public static final String SERVICE_NAME_PROPERTY = "servicename";
    public static final String TIME_OUT_PROPERTY = "timeout";
    public static final String AUTHENTICATION_SERVICE_PROPERTIES_FILENAME = "authentication.service.properties.filename";
	public static final String AUTHENTICATION_SERVICE_PROPERTIES_PATH = "authentication.service.properties.path";
	public static final String AUTH_PROPERTIES_NAME = "epcs_app_auth.properties";
	public static final String REQUEST_TIMEOUT_PROPERTY = "com.sun.xml.internal.ws.request.timeout";
    
    /**
     * Logger de la aplicacion.
     */
    private Logger log = Logger.getLogger(ApplicationLoginDAO.class);

    /**
     * Constructor del DAO. Se inicializa el acceso al componente que consume el servicio remoto.
     */
    public ApplicationLoginDAO() {
    	
        try {
        	InputStream is = ApplicationLoginDAO.class.getClassLoader().getResourceAsStream(AUTH_PROPERTIES_NAME);
        	Properties props = new Properties();
		    props.load(is);
		    log.info("recurso " + AUTH_PROPERTIES_NAME + " cargado Ok");
		    String propertiesPath = props.getProperty(AUTHENTICATION_SERVICE_PROPERTIES_PATH);
		    String propertiesFilename = props.getProperty(AUTHENTICATION_SERVICE_PROPERTIES_FILENAME);
		    
	    	if(log.isDebugEnabled()) {
	    		log.debug(AUTHENTICATION_SERVICE_PROPERTIES_FILENAME + " " + propertiesFilename);
	    		log.debug(AUTHENTICATION_SERVICE_PROPERTIES_PATH + " " + propertiesPath);
	    	}

		    
			ResourceBundle bundle = ResourceBundle.getBundle(propertiesPath
					.concat(propertiesFilename), ResourceBundleControl
					.getControl());
	    	if(log.isDebugEnabled()) {
	    		log.debug("bundle loaded from " + propertiesPath + " " + propertiesFilename);
	    	}
			
			ERPGestionDeSeguridadService loginService = new ERPGestionDeSeguridadService(
					new URL(bundle.getString(WSDL_PROPERTY)), new QName(bundle
							.getString(TARGET_NAMESPACE_PROPERTY), bundle
							.getString(SERVICE_NAME_PROPERTY)));
            service = loginService.getERPGestionDeSeguridadServicePort();
            log.debug("port inicializado");
            
			Map<String, Object> requestContext = ((BindingProvider) service)
                .getRequestContext();
			requestContext.put(REQUEST_TIMEOUT_PROPERTY, bundle
                .getString(TIME_OUT_PROPERTY));
			log.debug("port timeout setted " + bundle.getString(TIME_OUT_PROPERTY));
        } catch (Exception e) {
            log.error("Error al iniciar el DAO de autenticacion de aplicaciones", e);
        }
    }

    /**
     * Metodo que permite validar una aplicacion a partir del IDP encapsulado en un objeto de dominio.
     * @param loginUsuarioBean objeto que encapsula el idp.
     * @return un objeto LoginRespuestaBean que encapsula la respuesta del servicio.
     */
    public ApplicationLoginRespuestaBean login(final ApplicationLoginBean loginUsuarioBean) {   
        
    	ApplicationLoginRespuestaBean result = new ApplicationLoginRespuestaBean();
        AutenticarAplicacionType request = new AutenticarAplicacionType();
        
        log.info("idp for login: " + loginUsuarioBean.getIdp());
        request.setIdp(loginUsuarioBean.getIdp());
        
        ResultadoAutenticarAplicacionType response = null;
        try {
        	log.info("calling service method autenticarAplicacion");
            response = service.autenticarAplicacion(request);
        } catch (Exception e){
            log.error("Exception caught in method call autenticarAplicacion",e);
        }
        
        try {
        
	        if (response!=null && response.getRespuesta()!=null){
	            log.info("Respuesta del servicio [ERP_GestionDeSeguridadService]: codigo [" 
	            		+ response.getRespuesta().getCodigo() 
	            		+ "], descripcion [" 
	            		+ response.getRespuesta().getDescripcion() 
	            		+ "]");

	            //codigo y descripcion
	            result.setCodigoRespuesta(response.getRespuesta().getCodigo());
	            result.setMensajeRespuesta(response.getRespuesta().getDescripcion());

	            if(response.getRespuesta().getCodigo().equals(CODIGO_RESPUESTA_OK)) {
		            result.setNombreSujeto(response.getAuthApp().getMsisdn());
	            	log.info("Respuesta servicio:");
	            	log.info("    msisdn: " + response.getAuthApp().getMsisdn());	            
	            }
	            
	        }
	        log.info("finishing login");
	        return result;
        } catch (Exception e) {
			log.error("Exception caught on autenticarAplicacion response", e);
			return null;
		}
    }
    
    public static void main(String[] args){

        if (args.length==1){
            ApplicationLoginBean bean = new ApplicationLoginBean();
            bean.setIdp(args[0]);
            ApplicationLoginRespuestaBean resp = new ApplicationLoginDAO().login(bean);
            System.out.println("numeroPcs: "+resp.getNombreSujeto());
        }        
    }
}
