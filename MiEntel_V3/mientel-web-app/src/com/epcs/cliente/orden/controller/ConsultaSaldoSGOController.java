package com.epcs.cliente.orden.controller;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.DetalleConsultaSaldoSGOBean;
import com.epcs.cliente.orden.delegate.ConsultaSaldoSGODelegate;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ConsultaSaldoSGOController {

	private static final long serialVersionUID = 1L;
	 	 
	private static final Logger LOGGER = Logger.getLogger(ConsultaSaldoSGOController.class);
	
	private static final String EMP_SGO = MiEntelProperties.getProperty("miEntel.subMercadoSGO");
	
	private static final String SGO = MiEntelProperties.getProperty("consulta.plan.sgo.submercado");
	 
	private ConsultaSaldoSGODelegate consultaSaldoSGODelegate;
	
	private String resumenPlanSGOJson;

	private boolean submercadoEMPSGO;		
	
	public void init(PhaseEvent phase) {
		try{
			ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
			String submercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"subMercado");
			submercadoEMPSGO = submercado.equals(EMP_SGO) || submercado.equals(SGO);
		}catch (Exception e) {
			LOGGER.error("Exception inesperado al obtener submercado", e);
		}
	}
	
	public void consultaPlanSGOAjax(PhaseEvent phase) {

		String numeroPcsSeleccionado = "";
		
		try{
			
			ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
	        numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");	        
	        String submercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"subMercado");
	        
	        DetalleConsultaSaldoSGOBean resp = new DetalleConsultaSaldoSGOBean();
	        
			submercadoEMPSGO = submercado.equals(EMP_SGO) || submercado.equals(SGO);
			
			if(submercadoEMPSGO){
	        resp = consultaSaldoSGODelegate.consultarSGO(numeroPcsSeleccionado);
	        resp.setSubmercadoEMPSGO(true);
			}else{
			    resp.setSubmercadoEMPSGO(false);
			}
	        
	        resumenPlanSGOJson = JsonHelper.toJsonResponse(resp);
	        
		} catch (DAOException e) {
			LOGGER.error("DAOException al obtener datos del plan SGO msisdn :"+numeroPcsSeleccionado, e);
			resumenPlanSGOJson = JsonHelper.toJsonServiceErrorMessage("noDisponible");	           
	       } catch (ServiceException e) {
	           LOGGER.info("ServiceException msisdn :"+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
	                   + " - " + e.getDescripcionRespuesta());
	           resumenPlanSGOJson = JsonHelper.toJsonServiceErrorMessage("clienteOrden", e.getCodigoRespuesta(), new String[]{numeroPcsSeleccionado});	          
	       } catch (Exception e) {
	           LOGGER.error("Exception inesperado al obtener datos del plan SGO msisdn :"+numeroPcsSeleccionado, e);
	           resumenPlanSGOJson = JsonHelper.toJsonServiceErrorMessage("inesperado");
	       }
	}
	
	public ConsultaSaldoSGODelegate getConsultaSaldoSGODelegate() {
		return consultaSaldoSGODelegate;
	}

	public void setConsultaSaldoSGODelegate(
			ConsultaSaldoSGODelegate consultaSaldoSGODelegate) {
		this.consultaSaldoSGODelegate = consultaSaldoSGODelegate;
	}

	public String getResumenPlanSGOJson() {
		return resumenPlanSGOJson;
	}

	public void setResumenPlanSGOJson(String resumenPlanSGOJson) {
		this.resumenPlanSGOJson = resumenPlanSGOJson;
	}

	public boolean isSubmercadoEMPSGO() {
		return submercadoEMPSGO;
	}

	public void setSubmercadoEMPSGO(boolean submercadoEMPSGO) {
		this.submercadoEMPSGO = submercadoEMPSGO;
	}	 	 
	
}
