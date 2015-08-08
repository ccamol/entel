package com.epcs.administracion.suscripciones.controller;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.administracion.suscripciones.delegate.PlanesSeguridadBAMDelegate;
import com.epcs.administracion.suscripciones.util.BannerSeguridadHelper;
import com.epcs.bean.PlanSeguridadBAMBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class BannerSeguridadController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(BannerSeguridadController.class);
	private PlanesSeguridadBAMDelegate planesSeguridadBAMDelegate;
	private CuentaDelegate cuentaDelegate;
	private PlanSeguridadBAMBean planActual;
	private String respuestaClickBannerJson;
	private boolean mostrarBannerSeguridad;
	private boolean clickBanner;

	/**
	 * @return the clickBanner
	 */
	public boolean isClickBanner() {
		return clickBanner;
	}

	/**
	 * @param clickBanner the clickBanner to set
	 */
	public void setClickBanner(boolean clickBanner) {
		this.clickBanner = clickBanner;
	}
	
	
	/**
	 * @return the planesSeguridadBAMDelegate
	 */
	public PlanesSeguridadBAMDelegate getPlanesSeguridadBAMDelegate() {
		return planesSeguridadBAMDelegate;
	}

	/**
	 * @param planesSeguridadBAMDelegate the planesSeguridadBAMDelegate to set
	 */
	public void setPlanesSeguridadBAMDelegate(
			PlanesSeguridadBAMDelegate planesSeguridadBAMDelegate) {
		this.planesSeguridadBAMDelegate = planesSeguridadBAMDelegate;
	}

	/**
	 * @return the cuentaDelegate
	 */
	public CuentaDelegate getCuentaDelegate() {
		return cuentaDelegate;
	}

	/**
	 * @param cuentaDelegate the cuentaDelegate to set
	 */
	public void setCuentaDelegate(CuentaDelegate cuentaDelegate) {
		this.cuentaDelegate = cuentaDelegate;
	}
	
	/**
	 * @return the planActual
	 */
	public PlanSeguridadBAMBean getPlanActual() {
		return planActual;
	}

	/**
	 * @param planActual the planActual to set
	 */
	public void setPlanActual(PlanSeguridadBAMBean planActual) {
		this.planActual = planActual;
	}
	
	/**
	 * @return the respuestaClickBannerJson
	 */
	public String getRespuestaClickBannerJson() {
		return respuestaClickBannerJson;
	}

	/**
	 * @param respuestaClickBannerJson the respuestaClickBannerJson to set
	 */
	public void setRespuestaClickBannerJson(String respuestaClickBannerJson) {
		this.respuestaClickBannerJson = respuestaClickBannerJson;
	}

	/**
	 * @return the mostrarBannerSeguridad
	 */
	public boolean isMostrarBannerSeguridad() {
		return mostrarBannerSeguridad;
	}

	/**
	 * @param mostrarBannerSeguridad the mostrarBannerSeguridad to set
	 */
	public void setMostrarBannerSeguridad(boolean mostrarBannerSeguridad) {
		this.mostrarBannerSeguridad = mostrarBannerSeguridad;
	}
	
	public void initBannerSeguridad(PhaseEvent phase){
		try{
			LOGGER.info("phase " + phase.getPhaseId());
	        //Obtenemos datos necesarios para consulta
	        ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
	        String aaa = ProfileWrapperHelper.getPropertyAsString(profile, "aaa");
	        String mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
	        
	        if(MiEntelBusinessHelper.isMercadoCuentaControlada(mercado) ||
	        		MiEntelBusinessHelper.isMercadoSuscripcion(mercado)){
	        	
	        	String msisdn = ProfileWrapperHelper.getPropertyAsString
	        		(profile, "numeroPcsSeleccionado");
	        	
		        if(Integer.parseInt(aaa) >= 2){
	            	//Consultamos plan actual de seguridad
	                consultarPlanActualSeguridad(msisdn);
	                boolean planActualActivo = this.planActual != null && 
	                	planActual.getEstadoPlan().equalsIgnoreCase("ACTIVO");
	                
	                if(!planActualActivo){
	                	String rut = ProfileWrapperHelper.getPropertyAsString
	                		(profile, "rutUsuarioSeleccionado");
	                	String idContenido = MiEntelProperties.getProperty("parametros.bannerSeguridad.infoPlan.idContenido");
	                	Node contenido = JSFPortletHelper.getContentNode("idContenido", idContenido);
	                	/*
	                	 * La propiedad 'html' del contenido recuperado, contiene una cadena con el siguiente formato:
	                	 * <CODIGO_PLAN>|<NOMBRE_PLAN>|<PRECIO_PLAN>. Por esta razon hacemos un 'split' de la cadena
	                	 * obtenida e inicializamos un objeto de tipo 'PlanSeguridadBAMBean' que representa al plan 
	                	 * asociado a la promocion de seguridad BAM.
	                	 */
	                	String [] infoPlan = contenido.getProperty("html").getValue().getStringValue().split("\\|");
	                	String codigoPlan = infoPlan[0];
	                	String email = obtenerMailUsuario(msisdn, rut);
	                	
	                	//Validamos que se pueda contratar el plan
	                    boolean validarPlan = validarPlanSeguridad(
	                    		msisdn, email, codigoPlan, "0");
	                    if(validarPlan){
	                    	mostrarBannerSeguridad = true;
	                    }
	                }
	            }
	        }
            
		} catch (Exception e) {
            LOGGER.error("Error al inicializar banner promo de seguridad", e);
            JSFMessagesHelper.addServiceErrorMessage("iniciarBannerSeguridad");
        }
		
	}
	
	/**
     * Action method para obtener el plan actual de seguridad de un usuario PP 
     * @param event evento disparado al confirmar el canje de una bolsa o recarga
     */
    public void consultarPlanActualSeguridad(String msisdn){
        try{
            //Invocamos el metodo de accion que consulta el plan actual de seguridad
            this.planActual = this.planesSeguridadBAMDelegate.consultarPlanActualSeguridad(msisdn);
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al consultar plan actual de seguridad para el movil: " + msisdn, e);
            JSFMessagesHelper.addServiceErrorMessage("consultarPlanActualSeguridad");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al consultar plan actual de seguridad para el movil: " + msisdn);
            JSFMessagesHelper.addErrorCodeMessage("planesDeSeguridad.mcAfee",e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al consultar plan actual de seguridad para el movil: " + msisdn, e);
            JSFMessagesHelper.addServiceErrorMessage("consultarPlanActualSeguridad");
        }
        
    }
    
    /**
     * Action method para consultar el mail de un usuario 
     * @param event evento disparado al confirmar el canje de una bolsa o recarga
     */
    public String obtenerMailUsuario(String msisdn, String rut){
    	
    	String mailUsuario = null;
    	
        try{
            //Obtenemos los datos del usuario para luego retornar el mail
        	UsuarioBean usuario = this.cuentaDelegate.obtenerUsuario(msisdn, rut);
        	mailUsuario = usuario.getEmail();
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al consultar mail del usuario: " + msisdn, e);
            JSFMessagesHelper.addServiceErrorMessage("obtenerMailUsuario");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al consultar mail del usuario: "+msisdn);
            JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles",e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al consultar mail del usuario: " + msisdn, e);
            JSFMessagesHelper.addServiceErrorMessage("obtenerMailUsuario");
        }
        
        return mailUsuario;
    }
    
    /**
     * Action method para validar el estado de un plan de seguridad antes de activarlo
     * @param msisdn
     * @param mailUsuario
     * @param codigoPlan
     * @param valorPlan
     * @return
     */
    public boolean validarPlanSeguridad(
    	String msisdn, String mailUsuario, String codigoPlan, String valorPlan)
    		throws Exception{
    	
    	boolean respuesta = false;
        try{
            respuesta = planesSeguridadBAMDelegate.validarPlanSeguridad(
            		msisdn, mailUsuario, codigoPlan, valorPlan);
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al validar plan de seguridad para el usuario: " + msisdn + "\n", e);
            JSFMessagesHelper.addServiceErrorMessage("validarPlan");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al validar plan de seguridad para el usuario: " + msisdn);
            JSFMessagesHelper.addErrorCodeMessage("planesDeSeguridad.mcAfee",e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al validar plan de seguridad para el usuario: " + msisdn + "\n", e);
            JSFMessagesHelper.addServiceErrorMessage("validarPlan");
        }
        
        return respuesta;
    }
    
    /**
     * Action method encargado de direccionar el flujo al listado de planes de seguridad BAM
     */
    public void direccionarToPlanesSeguridad(ActionEvent event){
    	try{
    		BannerSeguridadHelper.redirectToPlanesSeguridad();
    	}catch(Exception e){
    		LOGGER.error("Exception al direccionar a planes de seguridad BAM. \n", e);
            JSFMessagesHelper.addServiceErrorMessage("redirectToPlanesSeguridad");
    	}
    	
    }
    

}
