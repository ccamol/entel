/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.GrupoPlanBAMBean;
import com.epcs.bean.PlanBAMBean;
import com.epcs.bean.PlanResumenBAMPPBean;
import com.epcs.bean.TransaccionGTMBean;
import com.epcs.cliente.perfil.delegate.PlanBAMDelegate;
import com.epcs.cliente.perfil.util.PlanBAMHelper;
import cl.tecnova.entel.homebampp.saldoreservado.SaldoCongeladoService.types.*;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.bam.saldoReservado.delegate.SaldoReservadoDelegate;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class PlanBAMController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = Logger.getLogger(PlanBAMController.class);

    private PlanBAMDelegate planBAMDelegate;
    
    /**** saldo reservado *****/
    private SaldoReservadoDelegate saldoReservadoDelegate;
    
    public SaldoReservadoDelegate getSaldoReservadoDelegate() {
		return saldoReservadoDelegate;
	}

	public void setSaldoReservadoDelegate(
			SaldoReservadoDelegate saldoReservadoDelegate) {
		this.saldoReservadoDelegate = saldoReservadoDelegate;
	}
	
	private SaldoCongeladoResponseDocumentType saldoReservado;
	

	public SaldoCongeladoResponseDocumentType getSaldoReservado() {
		return saldoReservado;
	}

	public void setSaldoReservado(SaldoCongeladoResponseDocumentType saldoReservado) {
		this.saldoReservado = saldoReservado;
	}
	
	private String saldoReservadoMonto;

	public String getSaldoReservadoMonto() {
		return new String(saldoReservado.getAmount().toByteArray());
	}

	public void setSaldoReservadoMonto(String saldoReservadoMonto) {
		this.saldoReservadoMonto = saldoReservadoMonto;
	}	

	private String saldoReservadoJson;
	
	public String getSaldoReservadoJson() {
		return saldoReservadoJson;
	}

	public void setSaldoReservadoJson(String saldoReservadoJson) {
		this.saldoReservadoJson = saldoReservadoJson;
	}

	/**************************/
	
	private PlanBAMBean planBAMActual;
    
    private PlanResumenBAMPPBean planResumenBAMPP;
    
    private String planResumenBAMPPJson;

    private String mensajeVelocidadTransmisionCM = "";
    
	/**
	 * @return the planResumenBAMPPJson
	 */
	public String getPlanResumenBAMPPJson() {
		return planResumenBAMPPJson;
	}

	/**
	 * @param planResumenBAMPPJson the planResumenBAMPPJson to set
	 */
	public void setPlanResumenBAMPPJson(String planResumenBAMPPJson) {
		this.planResumenBAMPPJson = planResumenBAMPPJson;
	}

	private String descripcionPlan; 
    
    private boolean existenPlanesDisponibles = false;
    
    private List<PlanBAMBean> planesDisponibles;
    
    private List<GrupoPlanBAMBean> grupoPlanesDisponibles;
    
    private String respuestaJson;    
    
    private String submercado;        

    private String zonaExtrema;
    
    
	public String getZonaExtrema() {
		return zonaExtrema;
	}

	public void setZonaExtrema(String zonaExtrema) {
		this.zonaExtrema = zonaExtrema;
	}

	public String getSubmercado() {
		return submercado;
	}

	public void setSubmercado(String submercado) {
		this.submercado = submercado;
	}
    
    
	/**
	 * @return the planResumenBAMPP
	 */
	public PlanResumenBAMPPBean getPlanResumenBAMPP() {
		return planResumenBAMPP;
	}

	/**
	 * @param planResumenBAMPP the planResumenBAMPP to set
	 */
	public void setPlanResumenBAMPP(PlanResumenBAMPPBean planResumenBAMPP) {
		this.planResumenBAMPP = planResumenBAMPP;
	}

   
    /**
     * @return the planDelegate
     */
    public PlanBAMDelegate getPlanBAMDelegate() {
        return planBAMDelegate;
    }

    /**
     * Metodo para la injection del delegate
     * @param planDelegate
     */
    public void setPlanBAMDelegate(PlanBAMDelegate planBAMDelegate) {
        this.planBAMDelegate = planBAMDelegate;
    }
    
    /**
     * Metodo que obtiene la informacion correspondiente al Plan actual del usuario
     * @param event
     */
    public void obtenerPlanBAMActual(PhaseEvent event){
    	
    	String numeroPcsSeleccionado = "";
    	String mercado ="";
    	String atributoAA="";
        
        try {
        	 ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        	
        	 mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
             numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
             atributoAA = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
             
             if(MiEntelBusinessHelper.isMercadoSuscripcion(mercado) || MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)){
            	 // PLAN ACTUAL SS Y CC
            	 //Se indica al metodo que NO REGISTRE en el log de la caja de Facturacion del Dashboard 
             	 boolean logCajaFacturacion = false;
            	 setPlanBAMActual(planBAMDelegate.obtenerPlanActualBAMSSCC(numeroPcsSeleccionado, mercado, atributoAA, logCajaFacturacion));
            	 getPlanBAMActual().setTipoMercado(mercado);
            	 descripcionPlan = PlanBAMHelper.obtenerBreveDescripcionPlan(getPlanBAMActual());
            	 
            	 if(ParametrosHelper.getExisteParametro("planes.bam.ze.cc",getPlanBAMActual().getCodbscs2())){
        			 zonaExtrema = "1";
        		 }
            	 
             }
             
       } catch (DAOException e) {
    	   LOGGER.error("DAOException al obtener datos del plan",  e);
    	   JSFMessagesHelper.addServiceErrorMessage("planActual");
           
       } catch (ServiceException e) {
           LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                   + " - " + e.getDescripcionRespuesta());
           JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", 
        		   e.getCodigoRespuesta(), new String[]{numeroPcsSeleccionado});
           
       } catch (Exception e) {
           LOGGER.error("Exception inesperado al obtener datos del plan", e);
           JSFMessagesHelper.addServiceErrorMessage("planActual");
       }
    }
    
    /**
     * Metodo que obtiene la informacion correspondiente los planes disponibles 
     * a los cuales puede cambiarse SS - CC.
     * @param event
     */
    public void obtenerPlanesDisponibles(PhaseEvent event){
        
        String numeroPcsSeleccionado = "";
        String mercado ="";
        String atributoAA="";
                
        try {
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
             mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
             numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
             atributoAA = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
             setSubmercado(ProfileWrapperHelper.getPropertyAsString(profileWrapper,"subMercado"));
             
             
             if(MiEntelBusinessHelper.isMercadoSuscripcion(mercado) || MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)){
                 //Se obtiene Plan actual
            	 //Se indica al metodo que NO REGISTRE en el log de la caja de Facturacion del Dashboard 
             	 boolean logCajaFacturacion = false;
                 PlanBAMBean planBAMActualBean = planBAMDelegate.obtenerPlanActualBAMSSCC(numeroPcsSeleccionado, mercado, atributoAA, logCajaFacturacion);
                 
                 setPlanesDisponibles(planBAMDelegate.obtenerPlanesDisponiblesBAM(numeroPcsSeleccionado));
                 setExistenPlanesDisponibles((getPlanesDisponibles() != null && !getPlanesDisponibles().isEmpty()));
                 
                 //Mensaje para el caso en en que el servicio regresa una lista vacia
                 if(!existenPlanesDisponibles){
                	 JSFMessagesHelper.addServiceErrorMessage("planesDisponibles");
                 }
                
                 // Agrupar Planes por tipo y Filtro para que no me agregue a la lista de planes, mi plan actual.
                 setGrupoPlanesDisponibles(PlanBAMHelper.buildGruposPlan(this.planesDisponibles, planBAMActualBean.getCodbscs2()));
                 
             }
             
       } catch (DAOException e) {
           LOGGER.error("DAOException al obtener datos de los planes disponibles ", e);
           JSFMessagesHelper.addServiceErrorMessage("planesDisponibles");
           
       } catch (ServiceException e) {
    	   LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                   + " - " + e.getDescripcionRespuesta());
           JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", 
                   e.getCodigoRespuesta(), new String[]{numeroPcsSeleccionado});
           
       } catch (Exception e) {
           LOGGER.error("Exception inesperado al obtener datos de los planes disponibles ", e);
           JSFMessagesHelper.addServiceErrorMessage("planesDisponibles");
       }
    }
    
    
    
    /**
     * Metodo que efectua el cambio de Plan para los mercados SS y CC
     *
     * @param phase
     */
    public void cambiarPlanBAMSSCC(PhaseEvent phase) {
        
    	String codigoPlan = "";
    	String nombreNuevoPlan = "";
    	String valorNuevoPlan = "";
    	String numeroPcsSeleccionado = "";
        try {
			codigoPlan = JsfUtil.getRequestParameter("codigoNuevoPlan");
			nombreNuevoPlan = JsfUtil.getRequestParameter("nombreNuevoPlan");
			valorNuevoPlan = JsfUtil.getRequestParameter("valorNuevoPlan");
            
			ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
                    "numeroPcsSeleccionado");
            
            //Si la validacion no arroja errores se continua con el flujo
            this.planBAMDelegate.validacionBloqueoTemporal(numeroPcsSeleccionado);
            //Si la validacion no arroja errores se continua con el flujo            
            this.planBAMDelegate.validacionComercial(numeroPcsSeleccionado, codigoPlan);
            //Se realiza el cambio de plan
            this.planBAMDelegate.cambiarPlanBAMSSCC(numeroPcsSeleccionado, codigoPlan);
            
            //Carga de datos para el marcado GTM
            TransaccionGTMBean transGTM = new TransaccionGTMBean();
            transGTM.setIdTransaccion(numeroPcsSeleccionado.substring(numeroPcsSeleccionado.length() - 4));
            transGTM.setSkuID(codigoPlan);
            transGTM.setNombre(nombreNuevoPlan);
            transGTM.setNuevoValor(valorNuevoPlan);
            transGTM.setCostoOperacional(0);
            transGTM.setNumeroPlanes(1);
            transGTM.setNumeroOperaciones(1);
            transGTM.setValorTransaccion(Double.parseDouble(valorNuevoPlan) + transGTM.getCostoOperacional());
            
            setRespuestaJson(JsonHelper.toJsonGTMResponse(transGTM));
            
        } catch (DAOException e) {
        	LOGGER.error("DAOException al realizar el cambio de plan.", e);
            setRespuestaJson(JsonHelper
            .toJsonServiceErrorMessage("noDisponible"));

        } catch (ServiceException e) {
        	LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());           
            
            setRespuestaJson(JsonHelper.toJsonServiceErrorMessage(
                    "gestionDePerfiles", e.getCodigoRespuesta(),
                    new String[] { numeroPcsSeleccionado }));

        } catch (Exception e) {
            LOGGER.error("Exception inesperada al realizar el cambio de plan.", e);
            setRespuestaJson(JsonHelper
                .toJsonServiceErrorMessage("inesperado"));
        }
    }

    

    /**
     * Metodo que obtiene la informacion correspondiente al Plan contratado actual del usuario PP
     * @param event
     */
    public void obtenerPlanResumenBAMPP(PhaseEvent event){
    	
    	String numeroPcsSeleccionado = "";    	
        
        try {
        	 ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        	
             numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
             planResumenBAMPP = planBAMDelegate.obtenerPlanResumenBAMPP(numeroPcsSeleccionado);
             
            if( planResumenBAMPP == null ){
            	planResumenBAMPPJson = JsonHelper.toJsonServiceErrorMessage("saldoRecargaBam");
            }else{
            	planResumenBAMPP.setFechaExpiracionFormated(DateHelper.format(planResumenBAMPP.getFechaExpiracion(),DateHelper.FORMAT_ddMMyyyy_SLASH));
            	planResumenBAMPP.setSaldoFormated(Utils.formatMoney(Double.parseDouble(planResumenBAMPP.getSaldo())));
            	//planResumenBAMPPJson = JsonHelper.toJsonResponse(planResumenBAMPP);
            }
            
            //Obtener Saldo Rerservado
            String msisdnFormateado = numeroPcsSeleccionado.length() == 8 ? "569" + numeroPcsSeleccionado : numeroPcsSeleccionado;
            saldoReservado = this.obtenerSaldoReservado(msisdnFormateado);            
            
            if(saldoReservado != null){
            	if(saldoReservado.getAmount() != null){
            		planResumenBAMPP.setSaldoReservadoMonto(Utils.formatMoney(saldoReservado.getAmount().doubleValue()));
            		planResumenBAMPP.setSaldoREservadoFechaExpiracion(saldoReservado.getExpirationdate());
            	}else{
            		//el movil no tiene saldo reservado
            		planResumenBAMPP.setSaldoReservadoMonto("0");
                    planResumenBAMPP.setSaldoREservadoFechaExpiracion("0");
            	}
            }else{
            	//falla en el servicio
            	planResumenBAMPP.setSaldoReservadoMonto("error");
            	planResumenBAMPP.setSaldoREservadoFechaExpiracion("error");
            }
                     
            planResumenBAMPPJson = JsonHelper.toJsonResponse(planResumenBAMPP);

            
       } catch (DAOException e) {
    	   LOGGER.error("Exception inesperado al obtener datos del plan",  e);
    	   JSFMessagesHelper.addServiceErrorMessage("planResumenBam");
           
       } catch (ServiceException e) {
    	   LOGGER.info("ServiceException caught: " + numeroPcsSeleccionado + " - "
   						+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
           JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", 
        		   e.getCodigoRespuesta(), new String[]{numeroPcsSeleccionado});
           
       } catch (Exception e) {
           LOGGER.error("Exception inesperado al obtener datos del plan", e);
           JSFMessagesHelper.addServiceErrorMessage("planContratado");
       }
    }
    
    
    public SaldoCongeladoResponseDocumentType obtenerSaldoReservado(String msisdn){  	
    		
    	try {
    		saldoReservadoDelegate = new SaldoReservadoDelegate();
    		return saldoReservadoDelegate.getSaldoReservado(msisdn);	
		} catch (Exception e) {
			LOGGER.error("Exception inesperado al obtener saldo reservado", e);
		}
		
		return null;
    	
    }
    
	/**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del mercado como clave asociada al valor.
     * @return
     */
    public String getPageLabelSaldoRecargaBam(){
        try{

          ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            
         return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                 profileWrapper, "mercado"), null);
         
        }catch(Exception e){
            LOGGER.error("No se ha podido obtener el pageLabel "+ e.getMessage(), e);
            return "";
        }
    
    }
    

    /**
     * @param planBAMActual the planBAMActual to set
     */
    public void setPlanBAMActual(PlanBAMBean planBAMActual) {
        this.planBAMActual = planBAMActual;
    }

    /**
     * @return the planBAMActual
     */
    public PlanBAMBean getPlanBAMActual() {
        return planBAMActual;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcionPlan(String descripcionPlan) {
        this.descripcionPlan = descripcionPlan;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcionPlan() {
        return descripcionPlan;
    }

    /**
     * @param existenPlanesDisponibles the existenPlanesDisponibles to set
     */
    public void setExistenPlanesDisponibles(boolean existenPlanesDisponibles) {
        this.existenPlanesDisponibles = existenPlanesDisponibles;
    }

    /**
     * @return the existenPlanesDisponibles
     */
    public boolean isExistenPlanesDisponibles() {
        return existenPlanesDisponibles;
    }

    /**
     * @param planesDisponibles the planesDisponibles to set
     */
    public void setPlanesDisponibles(List<PlanBAMBean> planesDisponibles) {
        this.planesDisponibles = planesDisponibles;
    }

    /**
     * @return the planesDisponibles
     */
    public List<PlanBAMBean> getPlanesDisponibles() {
        return planesDisponibles;
    }

    /**
     * @param grupoPlanesDisponibles the grupoPlanesDisponibles to set
     */
    public void setGrupoPlanesDisponibles(List<GrupoPlanBAMBean> grupoPlanesDisponibles) {
        this.grupoPlanesDisponibles = grupoPlanesDisponibles;
    }

    /**
     * @return the grupoPlanesDisponibles
     */
    public List<GrupoPlanBAMBean> getGrupoPlanesDisponibles() {
        return grupoPlanesDisponibles;
    }

    /**
     * @param respuestaJson the respuestaJson to set
     */
    public void setRespuestaJson(String respuestaJson) {
        this.respuestaJson = respuestaJson;
    }

    /**
     * @return the respuestaJson
     */
    public String getRespuestaJson() {
        return respuestaJson;
    }    
    
	/**
	 * 
	 * @return
	 */
	public String getMensajeVelocidadTransmisionCM() {
		
		try{
			
			 ProfileWrapper profileWrapper = ProfileWrapperHelper
	            .getProfile(JSFPortletHelper.getRequest());

			 String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado");
			 if(("1").equals(zonaExtrema)){
				 mercado = mercado+"EXTREMA";
			 }
			 String idContenido = MiEntelProperties.getProperty("plan.infoPlanActualBAM"+mercado.toUpperCase()+".idContenido");
			 
		   	 Node mensaje = JSFPortletHelper.getContentNode("idContenido", idContenido);
		   	 mensajeVelocidadTransmisionCM = mensaje.getProperty("html").getValue().getStringValue();
		   	 
			}catch (Exception e) {
				LOGGER.error("Exception al intentar obtener el mensaje de informacion del plan del CM",e);
			} 
			
		return mensajeVelocidadTransmisionCM.replace("{velocidadMax}",getPlanBAMActual().getVelocidadFairUse());
	}

	/**
	 * 
	 * @param mensajeVelocidadTransmisionCM
	 */
	public void setMensajeVelocidadTransmisionCM(String mensajeVelocidadTransmisionCM) {
		this.mensajeVelocidadTransmisionCM = mensajeVelocidadTransmisionCM;
	}
}