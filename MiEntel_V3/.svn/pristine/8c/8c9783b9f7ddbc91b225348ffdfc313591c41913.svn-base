package com.epcs.administracion.suscripciones.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.administracion.suscripciones.AdminSuscripcionService;
import com.epcs.administracion.suscripciones.AdminSuscripcionService_Service;
import com.epcs.administracion.suscripciones.types.ActivarPlanSeguridadType;
import com.epcs.administracion.suscripciones.types.ConsultarCredencialesPlanSeguridadType;
import com.epcs.administracion.suscripciones.types.ConsultarPlanActualSeguridadType;
import com.epcs.administracion.suscripciones.types.ConsultarPlanesDisponiblesSeguridadCCSSType;
import com.epcs.administracion.suscripciones.types.ConsultarPlanesDisponiblesSeguridadPPType;
import com.epcs.administracion.suscripciones.types.CredencialesPlanSeguridadType;
import com.epcs.administracion.suscripciones.types.DesactivarPlanSeguridadType;
import com.epcs.administracion.suscripciones.types.PlanActualSeguridadType;
import com.epcs.administracion.suscripciones.types.PlanesDisponiblesSeguridadCCSSType;
import com.epcs.administracion.suscripciones.types.PlanesDisponiblesSeguridadPPType;
import com.epcs.administracion.suscripciones.types.ResultadoActivarPlanSeguridadType;
import com.epcs.administracion.suscripciones.types.ResultadoConsultarCredencialesPlanSeguridadType;
import com.epcs.administracion.suscripciones.types.ResultadoConsultarPlanActualSeguridadType;
import com.epcs.administracion.suscripciones.types.ResultadoConsultarPlanesDisponiblesSeguridadCCSSType;
import com.epcs.administracion.suscripciones.types.ResultadoConsultarPlanesDisponiblesSeguridadPPType;
import com.epcs.administracion.suscripciones.types.ResultadoDesactivarPlanSeguridadType;
import com.epcs.administracion.suscripciones.types.ResultadoValidarPlanSeguridadType;
import com.epcs.administracion.suscripciones.types.ValidarPlanSeguridadType;
import com.epcs.bean.CredencialesPlanSeguridadBean;
import com.epcs.bean.PlanSeguridadBAMBean;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;



/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class PlanesSeguridadBAMDAO {
	
	private static final Logger LOGGER = Logger.getLogger(PlanesSeguridadBAMDAO.class);

    private static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");
    
    
    /**
     * Obtiene el plan actual de seguridad
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public PlanSeguridadBAMBean consultarPlanActualSeguridad (String msisdn) 
    	throws DAOException, ServiceException {
    	
    	PlanSeguridadBAMBean resultado = null;
    	
    	AdminSuscripcionService port = null;
        LOGGER.info("Instanciando el port " + AdminSuscripcionService.class);
        try {
            port = (AdminSuscripcionService) WebServiceLocator.getInstance().getPort(
            		AdminSuscripcionService_Service.class, AdminSuscripcionService.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + AdminSuscripcionService.class, e);
            LOGGER.error( new DAOException(e));
        }
        
        ConsultarPlanActualSeguridadType request = new ConsultarPlanActualSeguridadType();
        ResultadoConsultarPlanActualSeguridadType response = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setMsisdn(msisdn);
            
            LOGGER.info("Invocando servicio");
            response = port.consultarPlanActualSeguridad(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: consultarPlanActualSeguridad",
                    e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException("consultarPlanActualSeguridad: Servicio no responde "
                    + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {
            	PlanActualSeguridadType planActualType = response.getPlanActualSeguridad();
            	
            	if(planActualType == null){
            		LOGGER.info("Plan actual de seguridad invalido");
            	}else{
            		//Se inicializa el objeto a devolver por este metodo.
                	resultado = new PlanSeguridadBAMBean();
                	//Extrayendo datos de la respuesta
                	resultado.setCodigoPlan(planActualType.getCodigoPlan());
                	resultado.setEstadoPlan(planActualType.getEstadoPlan());
                	resultado.setFechaActivacionPlan(DateHelper.parseDate(
            				planActualType.getFechaActivacionPlan(), DateHelper.FORMAT_ddMMyyyy_SLASH));
                	resultado.setFechaVencimientoPlan(DateHelper.parseDate(
                			planActualType.getFechaVencimientoPlan(), DateHelper.FORMAT_ddMMyyyy_SLASH));
                	resultado.setNombrePlan(planActualType.getNombrePlan());
                    resultado.setValorPlan(planActualType.getValorPlan());
            	}
                
            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "consultarPlanActualSeguridad", e);
                 LOGGER.error( new DAOException(e));
            }

        }
        else {
            LOGGER.error("consultarPlanActualSeguridad: Service error code received: "
                    + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        
        return resultado;
        
    }
    
    /**
     * Obtiene los planes de seguridad disponibles para PP
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<PlanSeguridadBAMBean> obtenerPlanesSeguridadDispobiblesPP() 
		throws DAOException, ServiceException {
		
    	List<PlanSeguridadBAMBean> resultado = null;
    	
		AdminSuscripcionService port = null;
	    LOGGER.info("Instanciando el port " + AdminSuscripcionService.class);
	    try {
	        port = (AdminSuscripcionService) WebServiceLocator.getInstance().getPort(
	        		AdminSuscripcionService_Service.class, AdminSuscripcionService.class);
	    } catch (WebServiceLocatorException e) {
	        LOGGER.error("Error al inicializar el Port " + AdminSuscripcionService.class, e);
	        LOGGER.error( new DAOException(e));
	    }
	    
	    ConsultarPlanesDisponiblesSeguridadPPType request = new ConsultarPlanesDisponiblesSeguridadPPType();
	    ResultadoConsultarPlanesDisponiblesSeguridadPPType response = null;
	
	    try {
	        
	        LOGGER.info("Invocando servicio");
	        response = port.consultarPlanesDisponiblesSeguridadPP(request);
	
	    } catch (Exception e) {
	        LOGGER.error("Exception caught on Service invocation: consultarPlanesDisponiblesSeguridadPP",
	                e);
	        LOGGER.error( new DAOException(e));
	    }
	
	    String codigoRespuesta = response.getRespuesta().getCodigo();
	    String descripcionRespuesta = response.getRespuesta().getDescripcion();
	
	    LOGGER.info("codigoRespuesta " + codigoRespuesta);
	    LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
	
	    if (Utils.isEmptyString(codigoRespuesta)) {
	        LOGGER.error( new DAOException("consultarPlanesDisponiblesSeguridadPP: Servicio no responde "
	                + "con codigoRespuesta"));
	    }
	
	    if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
	
	        try {
	        	List<PlanesDisponiblesSeguridadPPType> planesDisponiblesPP = 
	        		response.getPlanesDisponiblesSeguridadPP();
	            
	        	if(planesDisponiblesPP == null || planesDisponiblesPP.isEmpty()){
	        		LOGGER.info("No hay planes de seguridad disponibles para PP");
	        	}else{
	        		//Se inicializa el objeto a devolver por este metodo.
	        	    resultado = new LinkedList<PlanSeguridadBAMBean>();
	        	    
	        		//Extrayendo datos de la respuesta
		        	for(PlanesDisponiblesSeguridadPPType planType : planesDisponiblesPP){
		        		PlanSeguridadBAMBean plan = new PlanSeguridadBAMBean();
		        		plan.setCodigoPlan(planType.getCodigoPlan());
		        		plan.setDetallePlan(planType.getDetallePlan());
		        		plan.setNombrePlan(planType.getNombrePlan());
		        		plan.setValorPlan(planType.getValorPlan());
		        		resultado.add(plan);
		        	}
	        	}
	            
	        } catch (Exception e) {
	            LOGGER.error("Exception caught on Service response: "
	                    + "consultarPlanesDisponiblesSeguridadPP", e);
	            LOGGER.error( new DAOException(e));
	        }
	
	    }
	    else {
	        LOGGER.error("consultarPlanesDisponiblesSeguridadPP: Service error code received: "
	                + codigoRespuesta + " - " + descripcionRespuesta);
	        LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
	    }
	    
	    return resultado;
	    
	}
    
    
    /**
     * Obtiene los planes de seguridad disponibles para CC y SS
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<PlanSeguridadBAMBean> obtenerPlanesSeguridadDispobiblesCCSS(String msisdn) 
		throws DAOException, ServiceException {
		
    	List<PlanSeguridadBAMBean> resultado = null;
    	
		AdminSuscripcionService port = null;
	    LOGGER.info("Instanciando el port " + AdminSuscripcionService.class);
	    try {
	        port = (AdminSuscripcionService) WebServiceLocator.getInstance().getPort(
	        		AdminSuscripcionService_Service.class, AdminSuscripcionService.class);
	    } catch (WebServiceLocatorException e) {
	        LOGGER.error("Error al inicializar el Port " + AdminSuscripcionService.class, e);
	        LOGGER.error( new DAOException(e));
	    }
	    
	    ConsultarPlanesDisponiblesSeguridadCCSSType request = new ConsultarPlanesDisponiblesSeguridadCCSSType();
	    ResultadoConsultarPlanesDisponiblesSeguridadCCSSType response = null;
	
	    try {
	    	
	    	LOGGER.info("Configurando Datos de la peticion");
            request.setMsisdn(msisdn);
	        
	        LOGGER.info("Invocando servicio");
	        response = port.consultarPlanesDisponiblesSeguridadCCSS(request);
	
	    } catch (Exception e) {
	        LOGGER.error("Exception caught on Service invocation: consultarPlanesDisponiblesSeguridadCCSS",
	                e);
	        LOGGER.error( new DAOException(e));
	    }
	
	    String codigoRespuesta = response.getRespuesta().getCodigo();
	    String descripcionRespuesta = response.getRespuesta().getDescripcion();
	
	    LOGGER.info("codigoRespuesta " + codigoRespuesta);
	    LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
	
	    if (Utils.isEmptyString(codigoRespuesta)) {
	        LOGGER.error( new DAOException("consultarPlanesDisponiblesSeguridadCCSS: Servicio no responde "
	                + "con codigoRespuesta"));
	    }
	
	    if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
	
	        try {
	        	List<PlanesDisponiblesSeguridadCCSSType> planesDisponiblesCCSS = 
	        		response.getConsultarPlanesDisponiblesSeguridadCCSS();
	            
	        	if(planesDisponiblesCCSS == null || planesDisponiblesCCSS.isEmpty()){
	        		LOGGER.info("No hay planes de seguridad disponibles para CC y SS");
	        	}else{
	        		//Se inicializa el objeto a devolver por este metodo.
	        	    resultado = new LinkedList<PlanSeguridadBAMBean>();
	        	    
	        		//Extrayendo datos de la respuesta
		        	for(PlanesDisponiblesSeguridadCCSSType planType : planesDisponiblesCCSS){
		        		PlanSeguridadBAMBean plan = new PlanSeguridadBAMBean();
		        		plan.setCodigoPlan(planType.getCodigoPlan());
		        		plan.setDetallePlan(planType.getDetallePlan());
		        		plan.setNombrePlan(planType.getNombrePlan());
		        		plan.setValorPlan(planType.getValorPlan());
		        		resultado.add(plan);
		        	}
	        	}
	            
	        } catch (Exception e) {
	            LOGGER.error("Exception caught on Service response: "
	                    + "consultarPlanesDisponiblesSeguridadCCSS", e);
	            LOGGER.error( new DAOException(e));
	        }
	
	    }
	    else {
	        LOGGER.error("consultarPlanesDisponiblesSeguridadCCSS: Service error code received: "
	                + codigoRespuesta + " - " + descripcionRespuesta);
	        LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
	    }
	    
	    return resultado;
	    
	}
    
    
    /**
     * Metodo que recupera las credenciales del plan de seguridad activo para un usuario
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public CredencialesPlanSeguridadBean consultarCredencialesPlanSeguridad(String msisdn) 
		throws DAOException, ServiceException {
		
    	CredencialesPlanSeguridadBean resultado = null;
    	
		AdminSuscripcionService port = null;
	    LOGGER.info("Instanciando el port " + AdminSuscripcionService.class);
	    try {
	        port = (AdminSuscripcionService) WebServiceLocator.getInstance().getPort(
	        		AdminSuscripcionService_Service.class, AdminSuscripcionService.class);
	    } catch (WebServiceLocatorException e) {
	        LOGGER.error("Error al inicializar el Port " + AdminSuscripcionService.class, e);
	        LOGGER.error( new DAOException(e));
	    }
	    
	    ConsultarCredencialesPlanSeguridadType request = new ConsultarCredencialesPlanSeguridadType();
	    ResultadoConsultarCredencialesPlanSeguridadType response = null;
	
	    try {
	    	
	    	LOGGER.info("Configurando Datos de la peticion");
            request.setMsisdn(msisdn);
	        
	        LOGGER.info("Invocando servicio");
	        response = port.consultarCredencialesPlanSeguridad(request);
	
	    } catch (Exception e) {
	        LOGGER.error("Exception caught on Service invocation: consultarCredencialesPlanSeguridad",
	                e);
	        LOGGER.error( new DAOException(e));
	    }
	
	    String codigoRespuesta = response.getRespuesta().getCodigo();
	    String descripcionRespuesta = response.getRespuesta().getDescripcion();
	
	    LOGGER.info("codigoRespuesta " + codigoRespuesta);
	    LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
	
	    if (Utils.isEmptyString(codigoRespuesta)) {
	        LOGGER.error( new DAOException("consultarCredencialesPlanSeguridad: Servicio no responde "
	                + "con codigoRespuesta"));
	    }
	
	    if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
	
	        try {
	        	CredencialesPlanSeguridadType credencialesPlan = 
	        		response.getCredencialesPlanSeguridad();
	        	
	        	if(credencialesPlan == null){
	        		LOGGER.info("Valores invalidos para credenciales");
	        	}else{
	        		//Se inicializa el objeto a devolver por este metodo.
	        	    resultado = new CredencialesPlanSeguridadBean();
	        	    
	        	    //Extrayendo datos de la respuesta
	        		resultado.setEmail(credencialesPlan.getEmail());
	        		resultado.setImsi(credencialesPlan.getImsi());
	        		resultado.setPassword(credencialesPlan.getPassword());
	        	}
	            
	        } catch (Exception e) {
	            LOGGER.error("Exception caught on Service response: "
	                    + "consultarCredencialesPlanSeguridad", e);
	            LOGGER.error( new DAOException(e));
	        }
	
	    }
	    else {
	        LOGGER.error("consultarCredencialesPlanSeguridad: Service error code received: "
	                + codigoRespuesta + " - " + descripcionRespuesta);
	        LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
	    }
	    
	    return resultado;
	    
	}    
    
    /**
     * Valida si el usuario ya tiene activado el servicio o si esta intentando 
     * activar el servicio de prueba mas de una vez.
     * @param msisdn
     * @param mail
     * @param codigoPlan
     * @param valorPlan
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public boolean validarPlanSeguridad(
    		String msisdn, String mail, String codigoPlan, String valorPlan)
			throws DAOException, ServiceException {
		
		boolean resultado = false;
		
		AdminSuscripcionService port = null;
		LOGGER.info("Instanciando el port " + AdminSuscripcionService.class);
		try {
		    port = (AdminSuscripcionService) WebServiceLocator.getInstance().getPort(
		    		AdminSuscripcionService_Service.class, AdminSuscripcionService.class);
		} catch (WebServiceLocatorException e) {
		    LOGGER.error("Error al inicializar el Port " + AdminSuscripcionService.class, e);
		    LOGGER.error( new DAOException(e));
		}
		
		ValidarPlanSeguridadType request = new ValidarPlanSeguridadType();
		ResultadoValidarPlanSeguridadType response = null;
		
		try {
			
			LOGGER.info("Configurando Datos de la peticion");
		    request.setMsisdn(msisdn);
		    request.setMail(mail);
		    request.setCodigoPlan(codigoPlan);
		    request.setValorPlan(valorPlan);
		    
		    LOGGER.info("Invocando servicio");
		    response = port.validarPlanSeguridad(request);
		
		} catch (Exception e) {
		    LOGGER.error("Exception caught on Service invocation: validarPlanSeguridad",
		            e);
		    LOGGER.error( new DAOException(e));
		}
		
		String codigoRespuesta = response.getRespuesta().getCodigo();
		String descripcionRespuesta = response.getRespuesta().getDescripcion();
		
		LOGGER.info("codigoRespuesta " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
		
		if (Utils.isEmptyString(codigoRespuesta)) {
		    LOGGER.error( new DAOException("validarPlanSeguridad: Servicio no responde "
		            + "con codigoRespuesta"));
		}
		
		if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
		    resultado = true;
		}
		else {
		    LOGGER.error("validarPlanSeguridad: Service error code received: "
		            + codigoRespuesta + " - " + descripcionRespuesta);
		    LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
		}
		
		return resultado;
	}
    
    
    /**
     * Activa el plan seleccionado por el usuario
     * @param msisdn
     * @param tipoMovil
     * @param mail
     * @param codigoPlan
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public boolean activarPlanSeguridad(
    		String msisdn, String tipoMovil, String mail, String codigoPlan)
			throws DAOException, ServiceException {
		
		boolean resultado = false;
		
		AdminSuscripcionService port = null;
		LOGGER.info("Instanciando el port " + AdminSuscripcionService.class);
		try {
		    port = (AdminSuscripcionService) WebServiceLocator.getInstance().getPort(
		    		AdminSuscripcionService_Service.class, AdminSuscripcionService.class);
		} catch (WebServiceLocatorException e) {
		    LOGGER.error("Error al inicializar el Port " + AdminSuscripcionService.class, e);
		    LOGGER.error( new DAOException(e));
		}
		
		ActivarPlanSeguridadType request = new ActivarPlanSeguridadType();
		ResultadoActivarPlanSeguridadType response = null;
		
		try {
			
			LOGGER.info("Configurando Datos de la peticion");
			request.setMsisdn(msisdn);
		    request.setTipoMovil(tipoMovil);
		    request.setMail(mail);
		    request.setCodigoPlan(codigoPlan);
		    
		    LOGGER.info("Invocando servicio");
		    response = port.activarPlanSeguridad(request);
		
		} catch (Exception e) {
		    LOGGER.error("Exception caught on Service invocation: activarPlanSeguridad",
		            e);
		    LOGGER.error( new DAOException(e));
		}
		
		String codigoRespuesta = response.getRespuesta().getCodigo();
		String descripcionRespuesta = response.getRespuesta().getDescripcion();
		
		LOGGER.info("codigoRespuesta " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
		
		if (Utils.isEmptyString(codigoRespuesta)) {
		    LOGGER.error( new DAOException("activarPlanSeguridad: Servicio no responde "
		            + "con codigoRespuesta"));
		}
		
		if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
		    resultado = true;
		}
		else {
		    LOGGER.error("activarPlanSeguridad: Service error code received: "
		            + codigoRespuesta + " - " + descripcionRespuesta);
		    LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
		}
		
		return resultado;
	}
    
    /**
     * Desactiva el plan seleccionado por el usuario
     * @param msisdn
     * @param tipoMovil
     * @param mail
     * @param codigoPlan
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public boolean desactivarPlanSeguridad(
    		String msisdn, String tipoMovil, String mail, String codigoPlan)
			throws DAOException, ServiceException {
		
		boolean resultado = false;
		
		AdminSuscripcionService port = null;
		LOGGER.info("Instanciando el port " + AdminSuscripcionService.class);
		try {
		    port = (AdminSuscripcionService) WebServiceLocator.getInstance().getPort(
		    		AdminSuscripcionService_Service.class, AdminSuscripcionService.class);
		} catch (WebServiceLocatorException e) {
		    LOGGER.error("Error al inicializar el Port " + AdminSuscripcionService.class, e);
		    LOGGER.error( new DAOException(e));
		}
		
		DesactivarPlanSeguridadType request = new DesactivarPlanSeguridadType();
		ResultadoDesactivarPlanSeguridadType response = null;
		
		try {
			
			LOGGER.info("Configurando Datos de la peticion");
			request.setMsisdn(msisdn);
			request.setTipoMovil(tipoMovil);
		    request.setMail(mail);
		    request.setCodigoPlan(codigoPlan);
		    
		    LOGGER.info("Invocando servicio");
		    response = port.desactivarPlanSeguridad(request);
		
		} catch (Exception e) {
		    LOGGER.error("Exception caught on Service invocation: desactivarPlanSeguridad",
		            e);
		    LOGGER.error( new DAOException(e));
		}
		
		String codigoRespuesta = response.getRespuesta().getCodigo();
		String descripcionRespuesta = response.getRespuesta().getDescripcion();
		
		LOGGER.info("codigoRespuesta " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
		
		if (Utils.isEmptyString(codigoRespuesta)) {
		    LOGGER.error( new DAOException("desactivarPlanSeguridad: Servicio no responde "
		            + "con codigoRespuesta"));
		}
		
		if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
		    resultado = true;
		}
		else {
		    LOGGER.error("desactivarPlanSeguridad: Service error code received: "
		            + codigoRespuesta + " - " + descripcionRespuesta);
		    LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
		}
		
		return resultado;
	}
    

}
