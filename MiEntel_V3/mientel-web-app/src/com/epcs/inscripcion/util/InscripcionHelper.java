/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.inscripcion.util;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bea.p13n.security.Authentication;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.bea.portlet.PageURL;
import com.epcs.bean.RutBean;
import com.epcs.cliente.perfil.controller.HeaderController;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.inscripcion.delegate.InscripcionDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.WordNumberHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class InscripcionHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger
			.getLogger(InscripcionHelper.class);

	/**
	 * Constante con el valor por defecto para el separador de URL.
	 */
	private static final String DEFAULT_PATH_SEPARATOR = "/";

	private static final String START_PARAMETER_URL = "?";

	private static final String DEFAULT_PARAMETER_EQUAL = "=";

	private static final String DEFAULT_PARAMETER_SEPARATOR = "&";

	private static final String DEFAULT_IDP_ATTTR_NAME = MiEntelProperties
			.getProperty("miEntel.login.idp.attr");
	
	private static final String DEFAULT_CONTEXTO_ATTR_NAME = MiEntelProperties
			.getProperty("miEntel.login.contexto.attr");

	private static final String DEFAULT_SERVLET_ATTTR_NAME = MiEntelProperties
			.getProperty("miEntel.login.servlet.attr");

	private static final String ACCESO_LOGIN = MiEntelProperties
			.getProperty("inscripcion.acceso.login");

	private static final String ACCESO_SELECTOR_CUENTA = MiEntelProperties
			.getProperty("inscripcion.acceso.selectorCuenta");
	
	private static final String ACCESO_FORM_MISDATOS = MiEntelProperties
	.getProperty("inscripcion.acceso.formMisDatos");

	private static final String INSCRIPCION_ATTTR_NAME = MiEntelProperties
			.getProperty("inscripcion.attr.name");

	private static final String TIPO_INSCRIPCION_ATTTR_NAME = MiEntelProperties
			.getProperty("inscripcion.tipo.attr.name");

	private static final String INSCRIPCION_TIPO_MOVIL_NOBUIC = MiEntelProperties
			.getProperty("inscripcion.tipo.movilNoBuic.attr");

	private static final String INSCRIPCION_TIPO_MOVILRUT_NOASOCIADO = MiEntelProperties
			.getProperty("inscripcion.tipo.movilRutNoAsociado.attr");
	
	private static final String INSCRIPCION_ATTTR_ACCESO = MiEntelProperties
	.getProperty("inscripcion.acceso.attr");
	private static final int LONGITUD_MINIMA_RUT = 8;
	
    /**
     * Valor de exito para la respuesta a un servicio
     */
    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");
	
	
	

	/**
	 * Metodo que obtiene el nombre del acceso via Login
	 * @return
	 */
	public String getAccesoLogin() {
		return ACCESO_LOGIN;
	}

	/**
	 * Metodo que obtiene el nombre del acceso via Selector Cuenta
	 * @return
	 */
	public String getAccesoSelectorCuenta() {
		return ACCESO_SELECTOR_CUENTA;
	}
	
	
	/**
	 * Metodo que obtiene el nombre del acceso via Datos Personales
	 * @return
	 */
	public String getAccesoFormMisDatos() {
		return ACCESO_FORM_MISDATOS;
	}
	

	/**
	 * Metodo utilitario que obtiene el mercado correspondiente al movil y setea
	 * en el UUP la propiedad numeroPcsSeleccionado por el seleccionado en el
	 * selector de cuenta.
	 * 
	 */
	public void startInscripcion(ProfileWrapper profileWrapper,
			String msisdn) {
		try {
			ProfileWrapperHelper.setProperty(
					profileWrapper,
					MiEntelProperties.getProperty("miEntel.userProfile.numeroPcsSeleccionado.property"),
					msisdn);

			redirectFormIngresoRut(getAccesoSelectorCuenta(),"");

		} catch (Exception a) {
			LOGGER.error("Exception al intentar obtener y redireccionar a la URL del Formulario de Inscripcion");
		}
	}

	/**
	 * Metodo utilitario para la obtencion de la URL y redireccionamiento al
	 * Formulario de Ingreso Registro Usuario desde el selector de cuenta o formulario mis datos.
	 * 
	 */
	public void redirectFormIngresoRut(String acceso, String numeroPCS) {
		try {
			HttpServletRequest httpRequest = JSFPortletHelper
					.getRequest(FacesContext.getCurrentInstance());

			HttpServletResponse httpResponse = JSFPortletHelper.getResponse();

			String pageLabelIngresoRegistro = JSFPortletHelper.getPreference(
					JSFPortletHelper.getPreferencesObject(),
					INSCRIPCION_ATTTR_NAME, null);

			PageURL pageURL = getPageURLIngresoRut(httpRequest,
					httpResponse, pageLabelIngresoRegistro,
					acceso);
			
			if(acceso.equals(InscripcionHelper.ACCESO_FORM_MISDATOS)){
				pageURL.addParameter("numeroPcs", numeroPCS);
			}
			
			redirectURL(httpResponse, pageURL);
		} catch (Exception e) {
			LOGGER.error("Exception al intentar obtener y redireccionar a la URL "
					+ "del Formulario de Inscripcion desde el selector de cuenta "
					+ "o formulario mis datos", e);
		}
	}

	/**
	 * Metodo utilitario para la obtencion de la URL y redireccionamiento al
	 * Formulario de Ingreso Registro Usuario desde el login.
	 * 
	 */
	public static void redirectInscripcionByLogin(String msisdn, String rut,
			String clave, String tipoInscripcion) {
		try {
			HttpServletRequest httpRequest = JSFPortletHelper
					.getRequest(FacesContext.getCurrentInstance());

			HttpServletResponse httpResponse = JSFPortletHelper.getResponse();

			String pageLabel = JSFPortletHelper.getPreference(JSFPortletHelper
					.getPreferencesObject(), INSCRIPCION_ATTTR_NAME, null);

			PageURL pageURL = getPageURLLogin(httpRequest, httpResponse,
					pageLabel, ACCESO_LOGIN, msisdn, rut, clave,
					tipoInscripcion);

			redirectURL(httpResponse, pageURL);
			
		} catch (Exception e) {
			LOGGER.error("Exception al intentar obtener y redireccionar a la URL "
					+ "del Formulario de Inscripcion desde el login", e);
		}
	}

	/**
	 * Entrega el {@link PageURL} de la pagina del Formulario de Inscripcion y
	 * agrega al mismo el origen de acceso.
	 * 
	 * @param httpRequest
	 *            {@link HttpServletRequest} de solicitud de ingreso a MiEntel
	 * @param httpResponse
	 *            {@link HttpServletResponse} de solicitud de ingreso a MiEntel
	 * @param acceso
	 *            String valor de parametro "acceso" que entrega el origen de
	 *            acceso
	 * @return {@link PageURL} de la pagina del Formulario de Inscripcion
	 */
	public static PageURL getPageURLLogin(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, String pageLabel, String acceso,
			String username, String rut, String clave, String tipo_inscripcion) {

		try {
			PageURL url = PageURL.createPageURL(httpRequest, httpResponse,
					pageLabel, false);
			// Agrega a la url el parametro especificado
			url.addParameter("acceso", acceso);
			url.addParameter("numeroPcs", username);
			url.addParameter("rut", rut);
			url.addParameter(TIPO_INSCRIPCION_ATTTR_NAME, tipo_inscripcion);
			url.addParameter("logout", "1");

			// Indica a la url que los caracteres '&' no los transforme a
			// '&amp;'
			url.setForcedAmpForm(false);

			return url;

		} catch (Exception e) {
			LOGGER.error("Exception no se pudo encontrar el pageLabel", e);
			return null;
		}

	}

	/**
	 * 
	 * 
	 * @param httpRequest
	 * @param httpResponse
	 * @param pageLabel
	 * @param acceso
	 * @return
	 */
	public static PageURL getPageURLIngresoRut(
			HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			String pageLabel, String acceso) {

		try {
			PageURL url = PageURL.createPageURL(httpRequest, httpResponse,
					pageLabel, false);
			// Agrega a la url el parametro especificado
			url.addParameter("acceso", acceso);
			// Indica a la url que los caracteres '&' no los transforme a
			// '&amp;'
			url.setForcedAmpForm(false);

			return url;

		} catch (Exception e) {
			LOGGER.error("Exception no se pudo encontrar el pageLabel", e);
			return null;
		}

	}

	/**
	 * Redirecciona al {@link PageURL} de la pagina del Formulario de
	 * Inscripcion.
	 * 
	 * @param httpResponse
	 *            {@link HttpServletResponse} de solicitud de ingreso a MiEntel
	 * @param pageURL
	 *            Elemento PageURL que contiene la url a redireccionar
	 * @return {@link PageURL} de la pagina del Formulario de Inscripcion
	 */
	public static void redirectURL(HttpServletResponse httpResponse,
			PageURL pageURL) {

		String url = pageURL.toString(false);
		try {
			httpResponse.sendRedirect(url);
			
		} catch (IOException e) {
			LOGGER.error("Exception al intentar redireccionar a la URL:" + url, e);
		}
	}
	
	/**
	 * 
	 * @param httpRequest
	 * @param httpResponse
	 * @param IDP
	 */
	public static void redirectApp(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, String tipo_inscripcion,
			String IDP) {

		try {

			StringBuffer url = new StringBuffer();

			url.append(FacesContext.getCurrentInstance().getExternalContext()
					.getRequestContextPath());
			url.append(DEFAULT_PATH_SEPARATOR);
			url.append(DEFAULT_SERVLET_ATTTR_NAME);
			url.append(START_PARAMETER_URL);
			url.append(DEFAULT_IDP_ATTTR_NAME);
			url.append(DEFAULT_PARAMETER_EQUAL);
			url.append(IDP);
			url.append(DEFAULT_PARAMETER_SEPARATOR);
			url.append("inscripcion");
			url.append(DEFAULT_PARAMETER_EQUAL);
			url.append("OK");
			url.append(DEFAULT_PARAMETER_SEPARATOR);
			url.append(TIPO_INSCRIPCION_ATTTR_NAME);
			url.append(DEFAULT_PARAMETER_EQUAL);
			url.append(tipo_inscripcion);

			httpResponse.sendRedirect(url.toString());

		} catch (Exception e) {
			LOGGER.error("Exception al redireccionar a la aplicacion, ingreso por IDP", e);
		}

	}

	/**
	 * 
	 * @param httpRequest
	 * @param httpResponse
	 * @param IDP
	 */
	public static void redirectAppContext(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, String tipo_inscripcion,
			String IDP, String context) {

		try {

			StringBuffer url = new StringBuffer();

			url.append(FacesContext.getCurrentInstance().getExternalContext()
					.getRequestContextPath());
			url.append(DEFAULT_PATH_SEPARATOR);
			url.append(DEFAULT_SERVLET_ATTTR_NAME);
			url.append(START_PARAMETER_URL);
			url.append(DEFAULT_IDP_ATTTR_NAME);
			url.append(DEFAULT_PARAMETER_EQUAL);
			url.append(IDP);
			url.append(DEFAULT_PARAMETER_SEPARATOR);
			url.append(DEFAULT_CONTEXTO_ATTR_NAME);
			url.append(DEFAULT_PARAMETER_EQUAL);
			url.append(context);

			httpResponse.sendRedirect(url.toString());

		} catch (Exception e) {
			LOGGER.error("Exception al redireccionar a la aplicacion, ingreso por IDP", e);
		}

	}

	/**
	 * Metodo utilitario que retorna un valor booleano, valida si en la url se
	 * encuentra el parametro de inscripcion para luego mostrar el mensaje
	 * correspondiente.
	 * 
	 * @return true o false
	 */
	public static boolean getParametroUrlInscripcion() {
		boolean resp = false;

		try {
			resp = (JSFPortletHelper.getRequest().getParameter(
					INSCRIPCION_ATTTR_NAME) != null) ? true : false;
		} catch (Exception e) {
			LOGGER.error("Exception al intentar obtener el parametro de resultado de inscripcion");
		}

		return resp;
	}
	
	
	/**
	 * Metodo utilitario que retorna un valor booleano, valida si en la url se
	 * encuentra el parametro de acceso para luego mostrar el mensaje
	 * correspondiente.
	 * 
	 * @return valor entero
	 */
	public String getMessageByParametroAcceso() {
		try {
			if (JSFPortletHelper.getRequest().getParameter(
					INSCRIPCION_ATTTR_ACCESO) != null) {

				if (JSFPortletHelper.getRequest().getParameter(
						INSCRIPCION_ATTTR_ACCESO).equals(
						ACCESO_SELECTOR_CUENTA)) {
					
					JSFMessagesHelper.addErrorMessage(
							MiEntelProperties.getProperty("inscripcion.msg.nroNoRegistrado"));
					
				}else if (JSFPortletHelper.getRequest().getParameter(
						INSCRIPCION_ATTTR_ACCESO).equals(
							ACCESO_FORM_MISDATOS)) {
						
					JSFMessagesHelper.addErrorMessage(
							MiEntelProperties.getProperty("inscripcion.msg.formMisDatos"));		
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception al intentar obtener el parametro acceso",e);
		}

		return "";
	}

	/**
	 * Metodo utilitario que retorna un valor booleano, valida si en la url se
	 * encuentra el parametro de tipo_inscripcion para luego mostrar el mensaje
	 * correspondiente.
	 * 
	 * @return valor entero
	 */
	public String getMessageByParametroTipoInscripcion() {
		try {
			if (JSFPortletHelper.getRequest().getParameter(
					TIPO_INSCRIPCION_ATTTR_NAME) != null) {

		
				if (JSFPortletHelper.getRequest().getParameter(
						TIPO_INSCRIPCION_ATTTR_NAME).equals(
						INSCRIPCION_TIPO_MOVIL_NOBUIC)) {
					
					JSFMessagesHelper.addErrorMessage(
							MiEntelProperties.getProperty("inscripcion.msg.movilNoBuic"));
					
				} else if (JSFPortletHelper.getRequest().getParameter(
						TIPO_INSCRIPCION_ATTTR_NAME).equals(
						INSCRIPCION_TIPO_MOVILRUT_NOASOCIADO)) {
					
					JSFMessagesHelper.addErrorMessage(
							MiEntelProperties.getProperty("inscripcion.msg.movilRutNoAsociado"));
				}

			}

		} catch (Exception e) {
			LOGGER.error("Exception al intentar obtener el parametro tipo_inscripcion",e);
		}

		return "";
	}

	/**
	 * Este metodo tiene dos funciones: hacer un logout de la aplicacion,
	 * invalidando la sesion y redireccionando a la pagina ddel Formulario de
	 * Inscripcion despues de haber forzado el borrado de la cache del response.
	 * 
	 * @return un objeto nulo, no es necesaria una respuesta.
	 */
	public static String redirectInscripcionByIngresoRut(
			String numeroPcs, String rutUsuario, String acceso) {
		
		FacesContext faces = FacesContext.getCurrentInstance();
		
		try {
			HttpServletRequest request = JSFPortletHelper.getRequest(faces);      
            HttpServletResponse response = JSFPortletHelper.getResponse();
            
            Principal userPrincipal = request.getUserPrincipal();
            String msisdnToken = (userPrincipal != null ? userPrincipal.getName().trim()
                    : null);
            
         // Limpiar cache
            HeaderController header = new HeaderController();
            header.cleanupCache(msisdnToken);
            
         // Logout
            Authentication.logout(request, true);
       
            Utils.prepareHeadersForLogout(request, response);
            request.getSession().invalidate();
            ProfileWrapperHelper.removeProfile(request);

			redirectInscripcionByLogin(numeroPcs, rutUsuario, "", acceso);

		} catch (Exception e) {
			LOGGER.error(
					"No ha sido posible redireccionar al Formulario de Inscripcion"
							+ "despues de hacer logout.", e);
		}
		return null;
	}
	
	
	
	/**
	 * Metodo utilitario que verifica y devuelve si el usuario correspondiente al numeroPcs y rutUsuario puede realizar inscripcion,
	 * esto es valido siempre y cuando no tenga restriccion de los 90 dias.
	 * 
	 * @param numeroPcs
	 * @param rutUsuario
	 * @return
	 */
	public static boolean verificarRestriccionDias(String numeroPcs, String rutUsuario){
		boolean response = false;
		Date fechaIngresoMasDiasRestriccion;
		try{
			
		 Date fechaActual = (DateHelper.parseDate(DateHelper.format(new java.util.Date(),
                DateHelper.FORMAT_ddMMyyyy),DateHelper.FORMAT_ddMMyyyy));
		
		 fechaIngresoMasDiasRestriccion = validarAsociacionMovilRutBuic(numeroPcs, rutUsuario);
		
		 int result = fechaActual.compareTo(fechaIngresoMasDiasRestriccion);
			
			if(result > 0){
				response = true;
			}else{
				JSFMessagesHelper.addErrorMessage(MiEntelProperties
						.getProperty("inscripcion.msg.restriccion") +" "+ 
						DateHelper.format(fechaIngresoMasDiasRestriccion, DateHelper.FORMAT_ddMMyyyy_SLASH)+".");
			}
			
		}catch (Exception e) {
			LOGGER.error("No ha sido posible verificar la restriccion de numero de dias "
					+ "para el ingreso del Formulario de Inscripcion", e);
			
		}
		
		return response;
		
	}
	
	
	
	/**
	 * Metodo utilitario que valida la asociacion correspondiente a Movil Rut en el BUIC.
	 * 
	 * @param numeroPcs
	 * @param rutUsuario
	 * @return
	 */
	public static Date validarAsociacionMovilRutBuic(String numeroPcs, String rutUsuario) {
		Date fechaIngresoMasDiasRestriccion = null;
		InscripcionDelegate inscripcionDelegate;
		try{
			
            inscripcionDelegate = new InscripcionDelegate();
            
            fechaIngresoMasDiasRestriccion = inscripcionDelegate
            .validarAsociacionMovilRutBuic(numeroPcs,rutUsuario);

		} catch (DAOException e) {
			LOGGER.error("DAOException caught: " + e.getMessage());
			JSFMessagesHelper.addServiceErrorMessage("validarAsociacionMovilRutBuic");
		} catch (ServiceException e) {

			LOGGER.info("ServiceException caught: "
					+ e.getCodigoRespuesta() + " - "
					+ e.getDescripcionRespuesta());
			
			if(CODIGO_RESPUESTA_OK.equals(e.getCodigoRespuesta())){
				JSFMessagesHelper.addErrorMessage(
						MiEntelProperties.getProperty("inscripcion.msg.registrado"));
			}else{
				JSFMessagesHelper
					.addErrorCodeMessage("gestionDePerfiles",e.getCodigoRespuesta());
			}
			

		} catch (Exception e) {
			LOGGER.error("Exception validarAsociacionMovilRutBuic caught: " + e);
			JSFMessagesHelper.addServiceErrorMessage("validarAsociacionMovilRutBuic");
		}

		return fechaIngresoMasDiasRestriccion;
	}
	/**
	 * 
	 * Metodo utilitario que retorna un valor booleano, es utilizado para
	 * verificar los parametros de acceso al formulario de registro.
	 * @param numeroPcs
	 * @param rut
	 * @return
	 */
	public static boolean verificarParametros(String idp,String acceso, String rut, String tipo_inscripcion){
		try{
			
			if( (validateDataIDP(idp)) &&
					(validateDataAccesoLogin(acceso)) && 
					(validateDataRUT(rut)) &&
					(validateDataTipoInscripcion(tipo_inscripcion)) ){
				return true;
			}else{
				return false;
			}
		
		}catch (Exception e) {
			LOGGER.error("Exception verificarParametros - formulario de inscripcion: " + e);
			return false;
		}
	}
	
	
	/**
	 * Metodo utilitario que retorna un valor booleano, es utilizado para
	 * validar en parametro de acceso.
	 * @param data
	 * @return true o false
	 */
	public static boolean validateDataAccesoLogin(String acceso){
		try{
			
			if ( ( Utils.isNotEmptyString(acceso)) && (  acceso.equals(ACCESO_LOGIN) ) ){
				return true;
			}else{
				return false;
			}
		
		}catch (Exception e){
			LOGGER.error("Exception validateDataAccesoLogin - formulario de inscripcion: " + e);
			return false;
		}
	}
	
	
	
	/**
	 * Metodo utilitario que retorna un valor booleano, es utilizado para
	 * validar si un dato no es nulo y es numerico. 
	 * @param data
	 * @return true o false
	 */
	public static boolean validateDataIsNumeric(String data){
		try{
			
			if ( (Utils.isNotEmptyString(data)) && (data.matches("[0-9]*")) && (data.length() >= LONGITUD_MINIMA_RUT) ){
				return true;
			}else{
				return false;
			}
		
		}catch (NumberFormatException nfe){
			LOGGER.error("NumberFormatException validateDataIsNumeric - formulario de inscripcion: " + nfe);
			return false;
		}
	}
	
	/**
	 * Valida que el rut sea valido.
	 * 
	 * @param data
	 * @return
	 */
	public static boolean validateDataRUT(String data){
		try{	
			RutBean.parseRut(data);
			return true;

		}catch (Exception nfe){
			LOGGER.error("Exception al intentar validar el RUT validateDataRUT - formulario de inscripcion: " + nfe);
			return false;
		}
	}
	
	
	
	/**
	 * Metodo utilitario que retorna un valor booleano, es utilizado para
	 * validar en parametro de tipo_inscripcion.
	 * @param data
	 * @return true o false
	 */
	public static boolean validateDataTipoInscripcion(String tipo_inscripcion){
		try{
			
			if ( ( Utils.isNotEmptyString(tipo_inscripcion)) && (  
					tipo_inscripcion.equals(INSCRIPCION_TIPO_MOVIL_NOBUIC) 
					|| tipo_inscripcion.equals(INSCRIPCION_TIPO_MOVILRUT_NOASOCIADO) 
					|| tipo_inscripcion.equals(ACCESO_SELECTOR_CUENTA)
					|| tipo_inscripcion.equals(ACCESO_FORM_MISDATOS)) ){
				
				return true;
			}else{
				return false;
			}
		
		}catch (Exception e){
			LOGGER.error("Exception validateDataTipoInscripcion - formulario de inscripcion: " + e);
			return false;
		}
	}
	
	
	/**
	 * Metodo utilitario para obtener el IDP asociado a un numeroPCS.
	 * 
	 * @param numeroPCS
	 * @return
	 */
	public static String obtenerIDP(String numeroPCS){
		
		String id_session = "";
		try{
		
		// Obtener el IDP del Usuario
		SeguridadDelegate seguridadDelegate = new SeguridadDelegate();
		id_session = seguridadDelegate.consultarIDP(numeroPCS);
		
		} catch (DAOException e) {
			LOGGER
			.error("Exception al intentar obtener el IDP correspondiente al movil "
					+ numeroPCS);
			JSFMessagesHelper.addServiceErrorMessage("noDisponible");
		} catch (ServiceException e) {
			LOGGER
					.error("Error de servicio al intentar obtener el IDP correspondiente al movil "
							+ numeroPCS);
			JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
		} catch (Exception e) {
			LOGGER
					.error("Exception al intentar obtener el IDP correspondiente al movil "
							+ numeroPCS);
			JSFMessagesHelper.addServiceErrorMessage("noDisponible");
		}
		
		return id_session;
	}
	
	
	/**
	 * Metodo utilitario para obtener el Msisdn asociado a un idp.
	 * 
	 * @param idp
	 * @return
	 */
	public static String obtenerMsisdnByIDP(String idp){
		
		String msisdn = "";
		try{
		
		// Obtener msisdn asociado a un idp
		SeguridadDelegate seguridadDelegate = new SeguridadDelegate();
		msisdn = seguridadDelegate.autenticarAplicacion(idp);
		
		} catch (DAOException e) {
			LOGGER
			.error("Exception al intentar obtener el msisdn correspondiente al idp "
					+ idp);
			JSFMessagesHelper.addServiceErrorMessage("noDisponible");
		} catch (ServiceException e) {
			LOGGER
					.error("Error de servicio al intentar obtener el msisdn correspondiente al idp "
							+ idp);
			JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
		} catch (Exception e) {
			LOGGER
					.error("Exception al intentar obtener el msisdn correspondiente al idp "
							+ idp);
			JSFMessagesHelper.addServiceErrorMessage("noDisponible");
		}
		
		return msisdn;
	}
	
	
		/**
		 * Valida que el idp se encuentre retornando un msisdn.
		 * 
		 * @param idp
		 * @return
		 */
		public static boolean validateDataIDP(String idp){
			String msisdn = "";
			try{
				msisdn = obtenerMsisdnByIDP(idp);			
				if(Utils.isNotEmptyString(msisdn)){
					return true;
				}else{
					return false;
				}
			}catch (Exception e) {
				LOGGER.error("Exception validateDataIDP - formulario de inscripcion: " + e);
				return false;
			}
		}
		
	/**
	 * 
	 * @return
	 */
	public static String getUrlHomeEntel(){
        return MiEntelProperties.getProperty("miEntel.home.url");
    }
	
	
	/**
	 * Metodo utilitario que elimina el prefijo 569 del numeroPcs
	 * 
	 * @param numeroPcs
	 * @return
	 */
	public static String deletePrefijoEntel(String numeroPcs){
	
		String numeroSinPrefijo = "";
		String prefijoEntel = WordNumberHelper.getPrefijoEntel();
		
		if( numeroPcs.length() == 11){
			
			if( (numeroPcs.substring(0,3)).equals(prefijoEntel)){
				numeroSinPrefijo = numeroPcs.substring(3);
			}
			
		}else{
			numeroSinPrefijo = numeroPcs;
		}
		return numeroSinPrefijo;
	}	

}
