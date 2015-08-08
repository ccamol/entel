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
import com.epcs.erp.seguridad.types.AutenticarUsuarioOrqType;
import com.epcs.erp.seguridad.types.ResultadoAutenticarUsuarioType;
import com.epcs.recursoti.exception.DAOException;
import com.epcs.recursoti.exception.ServiceException;
import com.epcs.seguridad.bean.LoginBean;
import com.epcs.seguridad.bean.LoginRespuestaBean;

/**
 * Esta clase tiene como finalidad ocultar el comportamiento (proxy) de la logica de consumo del servicio de 
 * autenticacion de usuarios para el proyecto MiEntel v3.0.
 * A traves de la biblioteca SMInfoLoginServiceSoapBindingQSService-client valida un usuario por medio de
 * su numero de movil, rut y clave. 
 * @author mmartinez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs).
 */
public class LoginDAO {

    /**
     * LogFactory de weblogic, donde se desplegara la respuesta del servicio.
     */
    private static final Logger LOGGER = Logger.getLogger(LoginDAO.class);

    /**
     * Codigo de respuesta exitosa del servicio de login
     */
    private static final String CODIGO_RESPUESTA_OK = "0000";

    public static final String WSDL_PROPERTY = "wsdl";
    public static final String TARGET_NAMESPACE_PROPERTY = "targetnamespace";
    public static final String SERVICE_NAME_PROPERTY = "servicename";
    public static final String TIME_OUT_PROPERTY = "timeout";
    public static final String AUTH_PROPERTIES_NAME = "epcs_auth.properties";
	public static final String REQUEST_TIMEOUT_PROPERTY = "com.sun.xml.internal.ws.request.timeout";
	public static final String AUTHENTICATION_SERVICE_PROPERTIES_FILENAME = "authentication.service.properties.filename";
	public static final String AUTHENTICATION_SERVICE_PROPERTIES_PATH = "authentication.service.properties.path";

	/**
     * Servicio de autenticacion.
     */
    private ERPGestionDeSeguridadServicePortType service = null;


    /**
     * Constructor del DAO. Se inicializa el acceso al componente que consume el servicio remoto.
     */
    public LoginDAO() {
        try {
        	InputStream is = LoginDAO.class.getClassLoader().getResourceAsStream(AUTH_PROPERTIES_NAME);
        	Properties props = new Properties();
		    props.load(is);
		    LOGGER.info("recurso " + AUTH_PROPERTIES_NAME + " cargado Ok");
		    String propertiesPath = props.getProperty(AUTHENTICATION_SERVICE_PROPERTIES_PATH);
		    String propertiesFilename = props.getProperty(AUTHENTICATION_SERVICE_PROPERTIES_FILENAME);
		    
	    	if(LOGGER.isDebugEnabled()) {
	    		LOGGER.debug(AUTHENTICATION_SERVICE_PROPERTIES_FILENAME + " " + propertiesFilename);
	    		LOGGER.debug(AUTHENTICATION_SERVICE_PROPERTIES_PATH + " " + propertiesPath);
	    	}
		    
            ResourceBundle bundle = ResourceBundle.getBundle(propertiesPath.concat(propertiesFilename),
                    ResourceBundleControl.getControl());
	    	if(LOGGER.isDebugEnabled()) {
	    		LOGGER.debug("bundle loaded from " + propertiesPath + " " + propertiesFilename);
	    	}
            
            ERPGestionDeSeguridadService loginService = new ERPGestionDeSeguridadService(
                    new URL(bundle.getString(WSDL_PROPERTY)),
                    new QName(bundle.getString(TARGET_NAMESPACE_PROPERTY),
                            bundle.getString(SERVICE_NAME_PROPERTY)) 
            );
            service = loginService.getERPGestionDeSeguridadServicePort();
            LOGGER.debug("port inicializado");
            
			Map<String, Object> requestContext = ((BindingProvider) service)
                .getRequestContext();
			requestContext.put(REQUEST_TIMEOUT_PROPERTY, bundle
                .getString(TIME_OUT_PROPERTY));				
			LOGGER.debug("port timeout setted " + bundle.getString(TIME_OUT_PROPERTY));

        } catch (Exception e) {
        	LOGGER.error("Error al iniciar el DAO de autenticacion de usuarios", e);
        }
    }

	/**
	 * Metodo que permite validar un usuario a partir de sus credenciales
	 * encapsuladas en un objeto de dominio.
	 * 
	 * @param loginUsuarioBean
	 *            objeto que encapsula las credenciales de autenticacion: movil,
	 *            rut y clave.
	 * @return un objeto LoginRespuestaBean que encapsula la respuesta del
	 *         servicio.
	 * @throws DAOException
	 * @throws ServiceException
	 * 
	 */
	public LoginRespuestaBean login(final LoginBean loginUsuarioBean) throws DAOException, ServiceException {
		
		if(service == null) {
			throw new DAOException("No hay cliente de servicios disponible");
		}

        LOGGER.info("User credentials:");
        LOGGER.info("numeroPcs " + loginUsuarioBean.getNumeroPcs());
        LOGGER.info("rut " + loginUsuarioBean.getRut());
        LOGGER.info("clave " + loginUsuarioBean.getClave());

		AutenticarUsuarioOrqType request = new AutenticarUsuarioOrqType();
		ResultadoAutenticarUsuarioType response;

		try {

			LOGGER.info("Configurando Datos de la peticion");
            String nroNormalizado = WordNumberHelper
                    .normalizarMsisdnEntel(loginUsuarioBean.getNumeroPcs());
            if (isEmptyString(nroNormalizado)) {
                throw new DAOException("numeroPcs invalido: '"
                        + loginUsuarioBean.getNumeroPcs() + "'");
            }
            
			request.setMsisdn(nroNormalizado);
			request.setRut(loginUsuarioBean.getRut().toString());
			request.setClave(loginUsuarioBean.getClave());

			LOGGER.info("Invocando servicio");
            LOGGER.info("msisdn " + request.getMsisdn());
            LOGGER.info("rut " + request.getRut());
            LOGGER.info("clave " + request.getClave());

			response = service.autenticarUsuarioOrq(request);

		} catch (Exception e) {
			LOGGER.error("Exception caught on Service invocation: "
					+ "autenticarUsuarioOrq", e);
			throw new DAOException(e);
		}
		
		if(response == null) {
			throw new DAOException("autenticarUsuarioOrq: Servicio no entrega " +
					"resultado");
		}

		String codigoRespuesta = response.getRespuesta().getCodigo();
		String descripcionRespuesta = response.getRespuesta().getDescripcion();

		LOGGER.info("codigoRespuesta " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

		if (isEmptyString(codigoRespuesta)) {
			throw new DAOException("autenticarUsuarioOrq: Servicio no responde "
					+ "con codigoRespuesta");
		}

		if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

			try {

				LoginRespuestaBean result = new LoginRespuestaBean();
				
				result.setCodigoRespuesta(response.getRespuesta().getCodigo());
				result.setMensajeRespuesta(response.getRespuesta()
								.getDescripcion());
				return result;

			} catch (Exception e) {
				LOGGER.error("Exception caught on Service response: "
						+ "autenticarUsuarioOrq", e);
				throw new DAOException(e);
			}

		} else {
			LOGGER.error("autenticarUsuarioOrq: Service error code received: "
					+ codigoRespuesta + " - " + descripcionRespuesta);
			throw new ServiceException(codigoRespuesta, descripcionRespuesta);
		}

	}
    
    private boolean isEmptyString(String str) {
		return str == null || str.trim().equals("");
	}

	public static void main(String[] args){

        if (args.length==3){
            LoginBean bean = new LoginBean();
            bean.setNumeroPcs(args[0]);
            bean.setRut(com.epcs.seguridad.bean.RutBean.parseRut(args[1]));
            bean.setClave(args[2]);
            
            LoginRespuestaBean respuesta;
			try {
				respuesta = new LoginDAO().login(bean);
	            System.out.println("codigo respuesta: "+respuesta.getCodigoRespuesta());
	            System.out.println("descripcion respuesta: "+respuesta.getMensajeRespuesta());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }        
    }
}
