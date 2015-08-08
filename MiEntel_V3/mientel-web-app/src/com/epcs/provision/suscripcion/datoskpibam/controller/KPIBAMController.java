package com.epcs.provision.suscripcion.datoskpibam.controller;


import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.provision.suscripcion.datoskpibam.delegate.KPIBAMDelegate;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.esa.provision.suscripcion.datoskpibam.types.IngresarDatosKpiBAMResponseType;

public class KPIBAMController {
	private static final Logger LOGGER = Logger.getLogger(KPIBAMController.class);
	private KPIBAMDelegate kpiBamDelegate;
	IngresarDatosKpiBAMResponseType respuesta = null;
	
	public void init(PhaseEvent event) { 	
		LOGGER.info("Iniciando marca KPI"); 		
    }
	
	private void insertarMarcaKPI(PhaseEvent event){ 
		this.kpiBamDelegate = new KPIBAMDelegate();
		
		String codArea = "";
		String nombreUsuario = ""; 
		String codIndicador = ""; 
		String fecha = ""; 
		String valor = "";
		
		try {
			HttpServletRequest request = JSFPortletHelper.getRequest();
	        ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);           
	    	codArea = JsfUtil.getRequestParameter("codArea");
	        nombreUsuario = JsfUtil.getRequestParameter("nombreUsuario");
	        codIndicador = JsfUtil.getRequestParameter("codIndicador");
	        fecha = JsfUtil.getRequestParameter("fecha");
	        valor = JsfUtil.getRequestParameter("valor");       
							
			this.respuesta = this.kpiBamDelegate.insertarKPIBAM(codArea,nombreUsuario,codIndicador,fecha, valor);		
			LOGGER.info("Insertando marca KPI: [" + this.respuesta.getResponse().getCodigo() + "][" + this.respuesta.getResponse().getDescripcion() + "]");
		} catch (Exception e) {
			LOGGER.error("Error al insertar marca KPI", e);
			LOGGER.error(e);
		}
	
		
	}

	public IngresarDatosKpiBAMResponseType getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(IngresarDatosKpiBAMResponseType respuesta) {
		this.respuesta = respuesta;
	}

	
	
}
