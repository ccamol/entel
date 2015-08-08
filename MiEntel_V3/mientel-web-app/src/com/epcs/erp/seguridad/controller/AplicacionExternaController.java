/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.erp.seguridad.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bea.netuix.servlets.controls.portlet.PortletPresentationContext;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.RutBean;
import com.epcs.billing.balance.util.ArcFour;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.erp.seguridad.util.AplicacionExternaUtil;
import com.epcs.recursoti.configuracion.MiEntelProperties;
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
public class AplicacionExternaController implements Serializable {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(AplicacionExternaController.class);

    private SeguridadDelegate seguridadDelegate;
    
    private AplicacionExternaUtil aplicacionExternaUtil;
    
    private String indiceApp;
    
    private String url;
    
    String llaveEncriptacion = MiEntelProperties.getProperty("parametros.factura.llaveEncriptacion");
    
    /**
     * 
     */
    public AplicacionExternaController(){
        this.seguridadDelegate = new SeguridadDelegate();
        this.aplicacionExternaUtil = new AplicacionExternaUtil();
    }
    
    /**
     * 
     * @return
     */
    public SeguridadDelegate getSeguridadDelegate(){
    	return seguridadDelegate;
    }
    
    /**
     * 
     * @param aplicacionExternaDelegate
     */
    public void setSeguridadDelegate(
    		SeguridadDelegate seguridadDelegate) {
		this.seguridadDelegate = seguridadDelegate;
	}
    

    /**
     * 
     * @return
     */
    public String getIndiceApp() {
		return indiceApp;
	}
    
    
    /**
     * Metodo que permite obtener un string que indica el codigo del atributo de
     * auto atencion del usuario actual
     * 
     * @return 3,2,1 o 0
     */
    public String getUserCodigoAAA() {

        ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(JSFPortletHelper.getRequest());
        try {
            String userCodigoAAA = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "aaa");
            
            return userCodigoAAA;
        } catch (Exception e) {
            LOGGER.error("No se ha podido obtener el codigo del atributo " +
                    "de auto atencion para el usuario actual");
            return "";
        }
    }
    
	/**
     * 
     * @return
     */
    public String getMensajePermiso(){
    	return  MiEntelProperties
        .getProperty("parametro.aplicacionexterna.permiso");
    }
    
    

    /**
     * Metodo que permite construir y obtener la URL correspondiente para ingresar a las 
     * aplicaciones externas (Gestion de Cuentas y Pago en Linea).
     * @return
     */
	public String getUrlAplicacion(){
			String pathUrl = "";
    	try {
    		/* Obtener la URL de la Aplicacion Externa , mapeando las preferencias del Portlet y obteniendo 
    		*  la referencia de la url correspondiente.
    		*/
    		 FacesContext context = FacesContext.getCurrentInstance();
    		 PortletRequest req = (PortletRequest) context.getExternalContext()
    		 .getRequest();
    		 
    		 pathUrl = req.getPreferences().getValue("url",null);
    		 
    		 // Obtener Rut Titular y Numero PCS del usuario en Sesion
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            
            String rutTitular = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "rutTitular");
            
            String numeroPCS = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcs");
            
            // Obtener el IDP del Usuario
            String id_session = this.seguridadDelegate.consultarIDP(numeroPCS);
            
            // Generar queryString
            String queryString = aplicacionExternaUtil
            .generarURLResponse(RutBean.parseRut(rutTitular), id_session);

            //Firmar Datos y Completar la URL Final
            url = aplicacionExternaUtil
            .completarURLResponse(queryString, pathUrl);
            
            LOGGER.info("IDP del Usuario "+rutTitular+"(RUT) --> "+id_session );
            LOGGER.info("URL aplicacion externa: "+url);

    	}catch (DAOException e) {
            JSFMessagesHelper.addServiceErrorMessage("noDisponible");
        } catch (ServiceException e) {
            LOGGER.error("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("gestionSeguridad", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado en cargar aplicacion", e);
            JSFMessagesHelper.addServiceErrorMessage("noDisponible"); 
        }
        return url;
    }
	
	/**
     * Metodo que permite obtener la URL correspondiente para ingresar a las 
     * aplicaciones externas (Speed Test).
     * @return
     */
	public String getUrlAplicacionBam(){

    	try {
    		/* Obtener la URL de la Aplicacion Externa , mapeando las preferencias del Portlet */
    		
    		 HttpServletRequest request = JSFPortletHelper 
             .getRequest(FacesContext.getCurrentInstance());
        	
    		 PortletPresentationContext portletCtx = (PortletPresentationContext) PortletPresentationContext
    		.getPortletPresentationContext(request);
    		 
    		// Obtener el esquema de la Instancia Ej: esqXXX
    		 String esquema = aplicacionExternaUtil.obtenerEsquema(portletCtx);
    		 
    		 indiceApp = esquema.substring(3,5);

    		 FacesContext context = FacesContext.getCurrentInstance();

    		 PortletRequest req = (PortletRequest) context.getExternalContext()
    		 .getRequest();
    		 
             //URL
             url = aplicacionExternaUtil
             .obtenerURL(req, esquema);

    	}catch (Exception e) {
            LOGGER.error("Exception inesperado en cargar aplicacion", e);
            JSFMessagesHelper.addServiceErrorMessage("noDisponible"); 
        }
    	
        return url;
    }
	
	
	/**
     * Metodo que permite obtener la URL correspondiente para ingresar a las 
     * aplicaciones externas.
     * @return
     */
	public String getUrlPortabilidad(){

    	try {
    		
	    		ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
	   		    String numeroPCS = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
	   		    
	   		    String parametro = "portal=2&movil=" + numeroPCS;
	   		    String var       = MiEntelProperties.getProperty("parametros.portabilidad.nombreVariable");	
	    		
	    		/* Obtener la URL de la Aplicacion Externa , mapeando las preferencias del Portlet */
	    		
	    		 HttpServletRequest request = JSFPortletHelper 
	             .getRequest(FacesContext.getCurrentInstance());
	        	
	    		 PortletPresentationContext portletCtx = (PortletPresentationContext) PortletPresentationContext
	    		.getPortletPresentationContext(request);    		 
	    		 
	    		 
	    		// Obtener el esquema de la Instancia Ej: esqXXX
	    		 String esquema = aplicacionExternaUtil.obtenerEsquema(portletCtx);
	    		 
	    		 indiceApp = esquema.substring(3,5);
	
	    		 FacesContext context = FacesContext.getCurrentInstance();
	
	    		 PortletRequest req = (PortletRequest) context.getExternalContext()
	    		 .getRequest();     	       
    		 
	             //URL
	             url = aplicacionExternaUtil
	             .obtenerURL(req, esquema);
	             
                 ArcFour arcfour = new ArcFour();    	        
    	         String paramEncrip = arcfour.encriptar(parametro, llaveEncriptacion);
    	         
	    	     url = url+"?"+var+"="+ paramEncrip.toLowerCase();

    	}catch (Exception e) {
            LOGGER.error("Exception inesperado en cargar aplicacion", e);
            JSFMessagesHelper.addServiceErrorMessage("noDisponible"); 
        }
    	
        return url;
    }
	
   	
	/**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * @return
     */
    public String getUrlSpeedTestBAM(){
    	try{
    		
    	  return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), "urlSpeedTestBAM", null);
		 
        }catch(Exception e){
        	LOGGER.error("No se ha podido obtener la url Speed Test"+ e.getMessage());
        	return "";
        }
    
    }
	
	/**
	 * 
	 * @return url de la aplicacion externa
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @return indice para aplicacion Gestion de Cuenta
	 */
	public String getIndiceAppGestioncuenta(){
		return AplicacionExternaUtil.getIndiceAppGestioncuenta();
	}
	
	/**
	 * 
	 * @return indice para aplicacion Pago en linea
	 */
	public String getIndiceAppPagoenlinea(){
		return AplicacionExternaUtil.getIndiceAppPagoenlinea();
	}
	
					/**
     * @return the mensajeUsuarioNoAutorizado
     */
    public String getMensajeUsuarioNoAutorizado() {
        String mensaje = "";
        try{
         mensaje = MiEntelProperties
        .getProperty("solo.usuario.titular");
        }catch (Exception e) {
            LOGGER.error("Error al obtener propiedad [solo.usuario.tituar]");
        }
        return mensaje;        
    }
	
}