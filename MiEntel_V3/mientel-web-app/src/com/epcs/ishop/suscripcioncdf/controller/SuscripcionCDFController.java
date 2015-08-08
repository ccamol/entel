/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.ishop.suscripcioncdf.controller;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.RespuestaBean;
import com.epcs.bean.ResultadoDesuscribirCDFBean;
import com.epcs.bean.ResultadoObtenerSuscripcionCDFOrqBean;
import com.epcs.bean.ResultadoSuscribirCDFBean;
import com.epcs.bean.SuscripcionCDFOrqBean;
import com.epcs.ishop.suscripcioncdf.delegate.SuscripcionCDFDelegate;
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
public class SuscripcionCDFController {
    

    /**
     * Logger para SuscripcionCDFController
     */
    private static final Logger LOGGER = Logger
            .getLogger(SuscripcionCDFController.class);
    
    private static final long serialVersionUID = 10L;
    
    private SuscripcionCDFDelegate suscripcionCDFDelegate;
    private boolean respuestaOk;
    private boolean sin3G;
    private boolean sin3GRoaming;
    private boolean roaming;
    private boolean errorActivarInactivar;
    private boolean errorObtenerSuscripciones;
    private String respuestaActivarInactivar;
    private String respuestaObtenerSuscripciones;
    private String msisdn;
    private int cantContratadas;
    private List<SuscripcionCDFOrqBean> tablaSuscripciones;
    
        
    
    /**
	 * @return the suscripcionCDFDelegate
	 */
	public SuscripcionCDFDelegate getSuscripcionCDFDelegate() {
		return suscripcionCDFDelegate;
	}

	/**
	 * @param suscripcionCDFDelegate the suscripcionCDFDelegate to set
	 */
	public void setSuscripcionCDFDelegate(
			SuscripcionCDFDelegate suscripcionCDFDelegate) {
		this.suscripcionCDFDelegate = suscripcionCDFDelegate;
	}

	/**
	 * @return the respuestaOk
	 */
	public boolean isRespuestaOk() {
		return respuestaOk;
	}

	/**
	 * @param respuestaOk the respuestaOk to set
	 */
	public void setRespuestaOk(boolean respuestaOk) {
		this.respuestaOk = respuestaOk;
	}

	/**
	 * @return the sin3G
	 */
	public boolean isSin3G() {
		return sin3G;
	}

	/**
	 * @param sin3g the sin3G to set
	 */
	public void setSin3G(boolean sin3g) {
		sin3G = sin3g;
	}

	/**
	 * @return the sin3GRoaming
	 */
	public boolean isSin3GRoaming() {
		return sin3GRoaming;
	}

	/**
	 * @param sin3gRoaming the sin3GRoaming to set
	 */
	public void setSin3GRoaming(boolean sin3gRoaming) {
		sin3GRoaming = sin3gRoaming;
	}

	/**
	 * @return the roaming
	 */
	public boolean isRoaming() {
		return roaming;
	}

	/**
	 * @param roaming the roaming to set
	 */
	public void setRoaming(boolean roaming) {
		this.roaming = roaming;
	}

	/**
	 * @return the errorActivarInactivar
	 */
	public boolean isErrorActivarInactivar() {
		return errorActivarInactivar;
	}

	/**
	 * @param errorActivarInactivar the errorActivarInactivar to set
	 */
	public void setErrorActivarInactivar(boolean errorActivarInactivar) {
		this.errorActivarInactivar = errorActivarInactivar;
	}

	/**
	 * @return the errorObtenerSuscripciones
	 */
	public boolean isErrorObtenerSuscripciones() {
		return errorObtenerSuscripciones;
	}

	/**
	 * @param errorObtenerSuscripciones the errorObtenerSuscripciones to set
	 */
	public void setErrorObtenerSuscripciones(boolean errorObtenerSuscripciones) {
		this.errorObtenerSuscripciones = errorObtenerSuscripciones;
	}

	/**
	 * @return the respuestaActivarInactivar
	 */
	public String getRespuestaActivarInactivar() {
		return respuestaActivarInactivar;
	}

	/**
	 * @param respuestaActivarInactivar the respuestaActivarInactivar to set
	 */
	public void setRespuestaActivarInactivar(String respuestaActivarInactivar) {
		this.respuestaActivarInactivar = respuestaActivarInactivar;
	}

	/**
	 * @return the respuestaObtenerSuscripciones
	 */
	public String getRespuestaObtenerSuscripciones() {
		return respuestaObtenerSuscripciones;
	}

	/**
	 * @param respuestaObtenerSuscripciones the respuestaObtenerSuscripciones to set
	 */
	public void setRespuestaObtenerSuscripciones(
			String respuestaObtenerSuscripciones) {
		this.respuestaObtenerSuscripciones = respuestaObtenerSuscripciones;
	}

	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}

	/**
	 * @param msisdn the msisdn to set
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	/**
	 * @return the cantContratadas
	 */
	public int getCantContratadas() {
		return cantContratadas;
	}

	/**
	 * @param cantContratadas the cantContratadas to set
	 */
	public void setCantContratadas(int cantContratadas) {
		this.cantContratadas = cantContratadas;
	}

	/**
	 * @return the tablaSuscripciones
	 */
	public List<SuscripcionCDFOrqBean> getTablaSuscripciones() {
		return tablaSuscripciones;
	}

	/**
	 * @param tablaSuscripciones the tablaSuscripciones to set
	 */
	public void setTablaSuscripciones(List<SuscripcionCDFOrqBean> tablaSuscripciones) {
		this.tablaSuscripciones = tablaSuscripciones;
	}

	/**
     * Invoca el action Method que inicia la orquestacion de suscripciones CDF a mostrar al usuario.
     * @param event
     */
    public void initObtenerSuscripcionesCDF(PhaseEvent phase) {
        try {
            LOGGER.info("phase " + phase.getPhaseId());
            //Obtenemos datos necesarios para consulta
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            this.msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado");
            
            //Inicia orquestacion de suscripciones CDF
            ResultadoObtenerSuscripcionCDFOrqBean resultado = 
                suscripcionCDFDelegate.orquestarSuscripcionesCDF(msisdn);
            //Inicializa lista de suscripciones a mostrar
            tablaSuscripciones = resultado.getSuscripciones();
            contarSuscripcionesActivas();
            RespuestaBean respuesta = resultado.getRespuesta();
            
            solveTipoRespuesta(respuesta);

        } catch (DAOException e) {
        	errorObtenerSuscripciones = true;
        	respuestaObtenerSuscripciones = MiEntelProperties.getServiceMessages().
        		getErrorMessage("ishop.suscripcioncdf.orquestacion");
            LOGGER.error("DAOException durante la orquestacion de suscripciones CDF", e);
            JSFMessagesHelper.addServiceErrorMessage("ishop.suscripcioncdf.orquestacion");
        } catch (ServiceException e) {
        	errorObtenerSuscripciones = true;
        	respuestaObtenerSuscripciones = e.getDescripcionRespuesta();
            LOGGER.info("ServiceException durante la orquestacion de suscripciones CDF");
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
        	errorObtenerSuscripciones = true;
        	respuestaObtenerSuscripciones = MiEntelProperties.getServiceMessages().
    			getErrorMessage("ishop.suscripcioncdf.orquestacion");
            LOGGER.error("Error inesperado durante la orquestacion de suscripciones CDF", e);
            JSFMessagesHelper.addServiceErrorMessage("ishop.suscripcioncdf.orquestacion");
        }

    }
    
    /**
     * Action method que activa una suscripcion CDF para un usuario.
     * @param event evento activar suscripcion
     */
    public void activarSuscripcionesCDF(ActionEvent event) {
        try {
            //Obtenemos datos necesarios para consulta
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String idSuscripcion = (String)context.getRequestParameterMap().get("idSuscripcion");
            
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            this.msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado");
            
            ResultadoSuscribirCDFBean resultado = 
                suscripcionCDFDelegate.suscribirCDF(idSuscripcion, msisdn);
            respuestaActivarInactivar = resultado.getRespuesta().getDescripcion();
            errorActivarInactivar = false;
            
            //Actualiza orquestacion de suscripciones CDF
            ResultadoObtenerSuscripcionCDFOrqBean resultadoOrq = 
                suscripcionCDFDelegate.orquestarSuscripcionesCDF(msisdn);
            //Actualiza lista de suscripciones a mostrar
            tablaSuscripciones = resultadoOrq.getSuscripciones();
            contarSuscripcionesActivas();

        } catch (DAOException e) {
        	errorActivarInactivar = true;
        	respuestaActivarInactivar = MiEntelProperties.getServiceMessages().
    			getErrorMessage("ishop.suscripcioncdf.activacion");
            LOGGER.error("DAOException durante activacion de suscripciones CDF", e);
            JSFMessagesHelper.addServiceErrorMessage("ishop.suscripcioncdf.activacion");
        } catch (ServiceException e) {
        	errorActivarInactivar = true;
        	respuestaActivarInactivar = e.getDescripcionRespuesta();
            LOGGER.info("ServiceException durante activacion de suscripciones CDF");
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
        	errorActivarInactivar = true;
        	respuestaActivarInactivar = MiEntelProperties.getServiceMessages().
    			getErrorMessage("ishop.suscripcioncdf.activacion");
            LOGGER.error("Error inesperado durante activacion de suscripciones CDF", e);
            JSFMessagesHelper.addServiceErrorMessage("ishop.suscripcioncdf.activacion");
        }
    }
    
    /**
     * Action method que inactiva una suscripcion CDF para un usuario.
     * @param event evento desactivar suscripcion
     */
    public void inactivarSuscripcionesCDF(ActionEvent event) {
        try {
            //Obtenemos datos necesarios para consulta
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String idSuscripcion = (String)context.getRequestParameterMap().get("idSuscripcion");
            
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            this.msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado");
            
            ResultadoDesuscribirCDFBean resultado = 
                suscripcionCDFDelegate.deSuscribirCDF(idSuscripcion, msisdn);
            respuestaActivarInactivar = resultado.getRespuesta().getDescripcion();
            errorActivarInactivar = false;
            
            //Actualiza orquestacion de suscripciones CDF
            ResultadoObtenerSuscripcionCDFOrqBean resultadoOrq = 
                suscripcionCDFDelegate.orquestarSuscripcionesCDF(msisdn);
            //Actualiza lista de suscripciones a mostrar
            tablaSuscripciones = resultadoOrq.getSuscripciones();
            contarSuscripcionesActivas();

        } catch (DAOException e) {
        	errorActivarInactivar = true;
        	respuestaActivarInactivar = MiEntelProperties.getServiceMessages().
    			getErrorMessage("ishop.suscripcioncdf.desactivacion");
            LOGGER.error("DAOException durante inactivacion de desuscripciones CDF", e);
            JSFMessagesHelper.addServiceErrorMessage("ishop.suscripcioncdf.desactivacion");
        } catch (ServiceException e) {
        	errorActivarInactivar = true;
        	respuestaActivarInactivar = e.getDescripcionRespuesta();
            LOGGER.info("ServiceException durante inactivacion de suscripciones CDF");
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
        	errorActivarInactivar = true;
        	respuestaActivarInactivar = MiEntelProperties.getServiceMessages().
    			getErrorMessage("ishop.suscripcioncdf.desactivacion");
            LOGGER.error("Error inesperado durante inactivacion de suscripciones CDF", e);
            JSFMessagesHelper.addServiceErrorMessage("ishop.suscripcioncdf.desactivacion");
        }
    }
    
    /**
     * Metodo utilitario para saber cuantas suscripciones tiene contratadas el usuario
     */
    public void contarSuscripcionesActivas(){
    	for(SuscripcionCDFOrqBean susc : tablaSuscripciones){
    		if(susc.isActiva())
    			cantContratadas++;
    	}
    }
    
    /**
     * Metodo utilitario que interpreta la info de Roaming y compatibilidad 3G del usuario
     * @param respuesta
     */
    public void solveTipoRespuesta(RespuestaBean respuesta){
    	
    	setTipoRespuestaFalse();
    	
    	if(respuesta.getCodigo().equals(MiEntelProperties.getServiceMessages()
        		.getErrorMessage("suscripcionesCDFService","sin3G"))){
        	sin3G = true;
    	}
        
        if(respuesta.getCodigo().equals(MiEntelProperties.getServiceMessages()
        		.getErrorMessage("suscripcionesCDFService","sin3GRoaming"))){
        	sin3GRoaming = true;
        }
        
        if(respuesta.getCodigo().equals(MiEntelProperties.getServiceMessages()
        		.getErrorMessage("suscripcionesCDFService","roaming"))){
        	roaming = true;
        }
        if(respuesta.getCodigo().equals(MiEntelProperties.
        		getProperty("servicios.codigoRespuesta.exito"))){
        	respuestaOk = true;
        }
    }
    
    /**
     * Metodo utilitario para setear a false todos los flags de respuesta 
     * con la informacion de roaming y compatibilidad 3G del usuario
     */
    private void setTipoRespuestaFalse(){
    	sin3G = false; 
    	sin3GRoaming = false; 
    	roaming = false; 
    	respuestaOk = false;
    }

}
