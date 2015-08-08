/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.erp.seguridad.dao;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.epcs.bean.UsuarioBean;
import com.epcs.erp.seguridad.ERPGestionDeSeguridadService;
import com.epcs.erp.seguridad.ERPGestionDeSeguridadServicePortType;
import com.epcs.erp.seguridad.types.AutenticarAplicacionType;
import com.epcs.erp.seguridad.types.AutenticarUsuarioOrqType;
import com.epcs.erp.seguridad.types.CambiarClaveType;
import com.epcs.erp.seguridad.types.ConsultarIdpType;
import com.epcs.erp.seguridad.types.ResultadoAutenticarAplicacionType;
import com.epcs.erp.seguridad.types.ResultadoAutenticarUsuarioType;
import com.epcs.erp.seguridad.types.ResultadoCambiarClaveType;
import com.epcs.erp.seguridad.types.ResultadoConsultarIdpType;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.WordNumberHelper;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class SeguridadDAO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(SeguridadDAO.class);

    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");

    /**
     * Cambiar clave de un usuario
     * 
     * @param usuario
     *            {@link UsuarioBean}
     * 
     * @throws ServiceException
     * @throws DAOException
     */
    public void cambiarClave(UsuarioBean usuario) throws ServiceException,
            DAOException {

        ERPGestionDeSeguridadServicePortType port = null;
        LOGGER.info("Instanciando el Port.");
        try {
            port = (ERPGestionDeSeguridadServicePortType) WebServiceLocator
                    .getInstance().getPort(ERPGestionDeSeguridadService.class,
                            ERPGestionDeSeguridadServicePortType.class);

        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ERPGestionDeSeguridadServicePortType.class);
            LOGGER.error( new DAOException(e));
        }

        LOGGER.info("Configurando Datos de la peticion");
        CambiarClaveType autRequest = new CambiarClaveType();
        autRequest.setMsisdn(WordNumberHelper.getPrefijoEntel() + usuario.getNumeroPCS());
        autRequest.setAntiguaClave(usuario.getClaveActual());
        autRequest.setNuevaClave(usuario.getClaveNueva());

        LOGGER.info("Invocando servicio");
        ResultadoCambiarClaveType claveResponse = null;
        try {
            claveResponse = port.cambiarClave(autRequest);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
            		+"cambiarClave", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = claveResponse.getRespuesta().getCodigo();
        String descripcionRespuesta = claveResponse.getRespuesta()
                .getDescripcion();
        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            LOGGER.info("Nueva clave para numeroPcs '" + usuario.getNumeroPCS()
                    + "' actualizada correctamente");
        }
        else {
            LOGGER.error("cambiarClave: Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }

    }
    
    /**
     * Obtiene un IDP asociado al numero indicado en <code>numeroPcs</code>
     * 
     * @param numeroPcs
     *            String numero para quien se solicita IDP
     * @return String con IDP valido
     * @throws DAOException
     *             Si ocurre algun problema para establecer comunicacion con
     *             service Bus en la ejecucion del metodo
     * @throws ServiceException
     *             si el servicio retorna un mensaje de error
     */
    public String consultarIDP(String numeroPcs) throws DAOException,
            ServiceException {

        ERPGestionDeSeguridadServicePortType port = null;
        LOGGER.info("Instanciando el port "
                + ERPGestionDeSeguridadServicePortType.class);
        try {
            port = (ERPGestionDeSeguridadServicePortType) WebServiceLocator
                    .getInstance().getPort(ERPGestionDeSeguridadService.class,
                            ERPGestionDeSeguridadServicePortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ERPGestionDeSeguridadServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }

        LOGGER.info("Configurando Datos de la peticion");
        ConsultarIdpType request = new ConsultarIdpType();
        request.setMsisdn(WordNumberHelper.getPrefijoEntel() + numeroPcs);

        LOGGER.info("Invocando servicio");
        ResultadoConsultarIdpType response = null;
        try {
            response = port.consultarIdp(request);
        } catch (Exception e) {
            LOGGER.error(
                    "Exception caught on Service invocation: consultarIdp", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException("consultarIdp: Servicio no respondio "
                    + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            String idp = response.getIdp();
            LOGGER.info("IDP obtenido para '" + numeroPcs + "' : " + idp);
            return idp;

        }
        else {
            LOGGER.error("consultarIdp: Service error code received: "
                    + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return "";
    }

    /**
     * Obtiene un IDP asociado al numero indicado en <code>numeroPcs</code>
     * 
     * @param numeroPcs
     *            String numero para quien se solicita IDP
     * 
     * @return String con IDP valido
     * @throws DAOException
     *             Si ocurre algun problema para establecer comunicacion con
     *             service Bus en la ejecucion del metodo
     * @throws ServiceException
     *             si el servicio retorna un mensaje de error
     */
    public String consultarIDPAmpliacion(String numeroPcs) throws DAOException,
            ServiceException {

        ERPGestionDeSeguridadServicePortType port = null;
        LOGGER.info("Instanciando el port "
                + ERPGestionDeSeguridadServicePortType.class);
        try {
            port = (ERPGestionDeSeguridadServicePortType) WebServiceLocator
                    .getInstance().getPort(ERPGestionDeSeguridadService.class,
                            ERPGestionDeSeguridadServicePortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ERPGestionDeSeguridadServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }

        LOGGER.info("Configurando Datos de la peticion");
        ConsultarIdpType request = new ConsultarIdpType();
        request.setMsisdn(numeroPcs);

        LOGGER.info("Invocando servicio");
        ResultadoConsultarIdpType response = null;
        try {
            response = port.consultarIdp(request);
        } catch (Exception e) {
            LOGGER.error(
                    "Exception caught on Service invocation: consultarIdp", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException("consultarIdp: Servicio no respondio "
                    + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            String idp = response.getIdp();
            LOGGER.info("IDP obtenido para '" + numeroPcs + "' : " + idp);
            return idp;

        }
        else {
            LOGGER.error("consultarIdp: Service error code received: "
                    + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return "";
    }

    
    /**
     * Autentica los parametros de ingreso de un usuario
     * 
     * @param msisdn
     * @param rut
     * @param clave
     * @throws DAOException
     * @throws ServiceException
     */
    public void autenticarUsuario(String msisdn, String rut, String clave)
            throws DAOException, ServiceException {

        ERPGestionDeSeguridadServicePortType port = null;
        LOGGER.info("Instanciando el port "
                + ERPGestionDeSeguridadServicePortType.class);
        try {
            port = (ERPGestionDeSeguridadServicePortType) WebServiceLocator
                    .getInstance().getPort(ERPGestionDeSeguridadService.class,
                            ERPGestionDeSeguridadServicePortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ERPGestionDeSeguridadServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }

        LOGGER.info("Configurando Datos de la peticion");
        AutenticarUsuarioOrqType request = new AutenticarUsuarioOrqType();
        request.setMsisdn(WordNumberHelper.getPrefijoEntel() + msisdn);
        request.setRut(rut);
        request.setClave(clave);

        LOGGER.info("Invocando servicio");
        ResultadoAutenticarUsuarioType response = null;
        try {
            response = port.autenticarUsuarioOrq(request);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: " +
            		"autenticarUsuarioOrq", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException(
                    "autenticarUsuarioOrq: Servicio no responde "
                            + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            LOGGER.info("Codigo de Respuesta Obtenido '" + codigoRespuesta
                    + "'");
        }
        else {
            LOGGER.error("autenticarUsuario: Service error code received: "
                    + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
    }

    /**
     * Obtiene el msisdn asociado a un <code>idp</code>
     * 
     * @param idp
     * @return
     * @throws DAOException
     *             Si ocurre algun problema para establecer comunicacion con
     *             service Bus en la ejecucion del metodo
     * @throws ServiceException
     *             si el servicio retorna un mensaje de error
     */
    public String autenticarAplicacion(String idp) throws DAOException,
    ServiceException {
    	
    		String msisdn = "";

			ERPGestionDeSeguridadServicePortType port = null;
			LOGGER.info("Instanciando el port "
			        + ERPGestionDeSeguridadServicePortType.class);
			try {
			    port = (ERPGestionDeSeguridadServicePortType) WebServiceLocator
			            .getInstance().getPort(ERPGestionDeSeguridadService.class,
			                    ERPGestionDeSeguridadServicePortType.class);
			} catch (WebServiceLocatorException e) {
			    LOGGER.error("Error al inicializar el Port "
			            + ERPGestionDeSeguridadServicePortType.class, e);
			    LOGGER.error( new DAOException(e));
			}
			
			LOGGER.info("Configurando Datos de la peticion");
			AutenticarAplicacionType request = new AutenticarAplicacionType();
			request.setIdp(idp);
			
			LOGGER.info("Invocando servicio");
			ResultadoAutenticarAplicacionType response = null;
			try {
			    response = port.autenticarAplicacion(request);
			} catch (Exception e) {
			    LOGGER.error(
			            "Exception caught on Service invocation: autenticarAplicacion(", e);
			    LOGGER.error( new DAOException(e));
			}
			
			String codigoRespuesta = response.getRespuesta().getCodigo();
			String descripcionRespuesta = response.getRespuesta().getDescripcion();
			
			LOGGER.info("codigoRespuesta " + codigoRespuesta);
			LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
			
			if (Utils.isEmptyString(codigoRespuesta)) {
			    LOGGER.error( new DAOException("consultarIdp(: Servicio no respondio "
			            + "con codigoRespuesta"));
			}
			
			if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
			
				msisdn = response.getAuthApp().getMsisdn();
			    LOGGER.info("NumeroPCS obtenido para '" + idp + "' : " + msisdn);
			    return msisdn;
			
			}
			else {
			    LOGGER.error("autenticarAplicacion(: Service error code received: "
			            + codigoRespuesta + " - " + descripcionRespuesta);
			    LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
			}
			return "";
    }       

}
