/**
 * 
 */
package com.epcs.cliente.perfil.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;

import com.epcs.bean.BloqueoDesbloqueoEquipoBean;
import com.epcs.bean.CiudadBean;
import com.epcs.bean.ConsultarBloqueoDesbloqueoBean;
import com.epcs.bean.ConsultarDatosBuicBean;
import com.epcs.bean.MiEntelBean;
import com.epcs.bean.TraficoEquiposBean;
import com.epcs.bean.RutBean;
import com.epcs.billing.balance.delegate.FacturacionDelegate;
import com.epcs.cliente.perfil.delegate.EquipoDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.recursoti.parametros.delegate.ParametrosDelegate;

import java.text.ParseException;

/**
 * @author jroman (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class BloqueoDesbloqueoEquipoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(BloqueoDesbloqueoEquipoController.class);
    
    private EquipoDelegate equipoDelegate;
    
    //private boolean renderedBloqueo;
    
    //private boolean renderedDesbloqueoRoboHurto;
    
    //private boolean renderedDesbloqueoPerdidaExtravio;
    
    private ConsultarBloqueoDesbloqueoBean consultarBloqueoDesbloqueoBean ;
    
    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties.getProperty("servicios.codigoRespuesta.exito");

    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION1 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP1"); // Robo
    
    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION2 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP2"); // Extravio

    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION3 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP3"); // Hurto
    
    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION4 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP4"); // Factura Impaga
    
    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION5 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP5");  
    
    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION6 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP6");
    
    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION7 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP7");
    
    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION8 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP8"); 
    
    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION9 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP9"); 
    
    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION10 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP10");
    
    public static final String CODIGO_RESPUESTA_PENDIENTE_BLOQUEO = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipopPendienteBloqueo"); // Pendiente Bloqueo
    
    public static final String CODIGO_RESPUESTA_PENDIENTE_DESBLOQUEO = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipopPendienteDesBloqueo"); // Pendiente Des Bloqueo   
    
    public static final String CODIGO_RESPUESTA_ERROR_DESBLOQUEO = MiEntelProperties.getProperty("servicios.codigoRespuesta.errorDesBloqueo"); // Error proceso DesBloqueo
    
    public static final String CODIGO_RESPUESTA_ERROR_BLOQUEO = MiEntelProperties.getProperty("servicios.codigoRespuesta.errorBloqueo"); // Error proceso Bloqueo
   
    public static final String RAZON_BLOQUEO_ROBOHURTOWS = MiEntelProperties.getProperty("equipo.bloqueo.razonBloqueoRoboHurtoWS");
    
    public static final String RAZON_BLOQUEO_PERDIDAEXTRAVIOWS = MiEntelProperties.getProperty("equipo.bloqueo.razonBloqueoPerdidaExtravioWS");
    
    public static final String RAZON_BLOQUEO_ROBOHURTOMQ = MiEntelProperties.getProperty("equipo.bloqueo.razonBloqueoRoboHurtoMQ");
    
    public static final String RAZON_BLOQUEO_PERDIDAEXTRAVIOMQ = MiEntelProperties.getProperty("equipo.bloqueo.razonBloqueoPerdidaExtravioMQ");
    
    public static final String CODIGO_RESPUESTA_DATOS_INCOSINTENTES = MiEntelProperties.getProperty("servicios.codigoRespuesta.infoIncosistentes");
    
    private String respuestaConsultarDatosBuicJson;
    
    private ConsultarDatosBuicBean consultarDatosBuicBean ;
    
    private String respuestaInitBloqueoDesbloqueoJson;
    
    private String respuestaValidarClaveDesbloqueoJson;
    
    private List<TraficoEquiposBean> listaTraficoEquipos;
    
    private String respuestaConsultarHistoricoEquiposJson;
    
    private String respuestaBloquearEquipoJson;
    
    private String respuestaIngresarDesbloqueoJson;
    
    private boolean renderedListaTraficoEquipos;
    
    private SelectItem [] regionesList;
    
    private List<CiudadBean> itemsCiudades = null;

    private ParametrosDelegate parametrosDelegate;
    
    private boolean flagBloqueoDesbloqueo;
    
    private String datosBloqueoJson;     
    
	/**
	 * @return the flagBloqueoDesbloqueo
	 */
	public boolean isFlagBloqueoDesbloqueo() {
		return flagBloqueoDesbloqueo;
	}

	/**
	 * @param flagBloqueoDesbloqueo the flagBloqueoDesbloqueo to set
	 */
	public void setFlagBloqueoDesbloqueo(boolean flagBloqueoDesbloqueo) {
		this.flagBloqueoDesbloqueo = flagBloqueoDesbloqueo;
	}

	/**
	 * @return the parametrosDelegate
	 */
	public ParametrosDelegate getParametrosDelegate() {
		return parametrosDelegate;
	}

	/**
	 * @param parametrosDelegate the parametrosDelegate to set
	 */
	public void setParametrosDelegate(ParametrosDelegate parametrosDelegate) {
		this.parametrosDelegate = parametrosDelegate;
	}

	/**
	 * @return the renderedListaTraficoEquipos
	 */
	public boolean isRenderedListaTraficoEquipos() {
		return renderedListaTraficoEquipos;
	}

	/**
	 * @param renderedListaTraficoEquipos the renderedListaTraficoEquipos to set
	 */
	public void setRenderedListaTraficoEquipos(boolean renderedListaTraficoEquipos) {
		this.renderedListaTraficoEquipos = renderedListaTraficoEquipos;
	}

	/**
	 * @return the respuestaIngresarDesbloqueoJson
	 */
	public String getRespuestaIngresarDesbloqueoJson() {
		return respuestaIngresarDesbloqueoJson;
	}

	/**
	 * @param respuestaIngresarDesbloqueoJson the respuestaIngresarDesbloqueoJson to set
	 */
	public void setRespuestaIngresarDesbloqueoJson(
			String respuestaIngresarDesbloqueoJson) {
		this.respuestaIngresarDesbloqueoJson = respuestaIngresarDesbloqueoJson;
	}

	/**
	 * @return the respuestaBloquearEquipoJson
	 */
	public String getRespuestaBloquearEquipoJson() {
		return respuestaBloquearEquipoJson;
	}

	/**
	 * @param respuestaBloquearEquipoJson the respuestaBloquearEquipoJson to set
	 */
	public void setRespuestaBloquearEquipoJson(String respuestaBloquearEquipoJson) {
		this.respuestaBloquearEquipoJson = respuestaBloquearEquipoJson;
	}

	/**
	 * @return the respuestaConsultarHistoricoEquiposJson
	 */
	public String getRespuestaConsultarHistoricoEquiposJson() {
		return respuestaConsultarHistoricoEquiposJson;
	}

	/**
	 * @param respuestaConsultarHistoricoEquiposJson the respuestaConsultarHistoricoEquiposJson to set
	 */
	public void setRespuestaConsultarHistoricoEquiposJson(
			String respuestaConsultarHistoricoEquiposJson) {
		this.respuestaConsultarHistoricoEquiposJson = respuestaConsultarHistoricoEquiposJson;
	}

	/**
	 * @return the listaTraficoEquipos
	 */
	public List<TraficoEquiposBean> getListaTraficoEquipos() {
		return listaTraficoEquipos;
	}

	/**
	 * @param listaTraficoEquipos the listaTraficoEquipos to set
	 */
	public void setListaTraficoEquipos(
			List<TraficoEquiposBean> listaTraficoEquipos) {
		this.listaTraficoEquipos = listaTraficoEquipos;
	}

	/**
	 * @return the respuestaValidarClaveDesbloqueoJson
	 */
	public String getRespuestaValidarClaveDesbloqueoJson() {
		return respuestaValidarClaveDesbloqueoJson;
	}

	/**
	 * @param respuestaValidarClaveDesbloqueoJson the respuestaValidarClaveDesbloqueoJson to set
	 */
	public void setRespuestaValidarClaveDesbloqueoJson(
			String respuestaValidarClaveDesbloqueoJson) {
		this.respuestaValidarClaveDesbloqueoJson = respuestaValidarClaveDesbloqueoJson;
	}

	/**
	 * @return the respuestaInitBloqueoDesbloqueoJson
	 */
	public String getRespuestaInitBloqueoDesbloqueoJson() {
		return respuestaInitBloqueoDesbloqueoJson;
	}

	/**
	 * @param respuestaInitBloqueoDesbloqueoJson the respuestaInitBloqueoDesbloqueoJson to set
	 */
	public void setRespuestaInitBloqueoDesbloqueoJson(
			String respuestaInitBloqueoDesbloqueoJson) {
		this.respuestaInitBloqueoDesbloqueoJson = respuestaInitBloqueoDesbloqueoJson;
	}

	/**
	 * @return the consultarBloqueoDesbloqueoBean
	 */
	public ConsultarBloqueoDesbloqueoBean getConsultarBloqueoDesbloqueoBean() {
		return consultarBloqueoDesbloqueoBean;
	}

	/**
	 * @param consultarBloqueoDesbloqueoBean the consultarBloqueoDesbloqueoBean to set
	 */
	public void setConsultarBloqueoDesbloqueoBean(
			ConsultarBloqueoDesbloqueoBean consultarBloqueoDesbloqueoBean) {
		this.consultarBloqueoDesbloqueoBean = consultarBloqueoDesbloqueoBean;
	}

	/**
	 * @return the respuestaConsultarDatosBuicJson
	 */
	public String getRespuestaConsultarDatosBuicJson() {
		return respuestaConsultarDatosBuicJson;
	}

	/**
	 * @param respuestaConsultarDatosBuicJson the respuestaConsultarDatosBuicJson to set
	 */
	public void setRespuestaConsultarDatosBuicJson(
			String respuestaConsultarDatosBuicJson) {
		this.respuestaConsultarDatosBuicJson = respuestaConsultarDatosBuicJson;
	}

	/**
	 * @return the consultarDatosBuicBean
	 */
	public ConsultarDatosBuicBean getConsultarDatosBuicBean() {
		return consultarDatosBuicBean;
	}

	/**
	 * @param consultarDatosBuicBean the consultarDatosBuicBean to set
	 */
	public void setConsultarDatosBuicBean(
			ConsultarDatosBuicBean consultarDatosBuicBean) {
		this.consultarDatosBuicBean = consultarDatosBuicBean;
	}

	/**
	 * @return the codigoRespuestaOk
	 */
	public static String getCodigoRespuestaOk() {
		return CODIGO_RESPUESTA_OK;
	}

	/**
	 * @return the codigoRespuestaBloqueoOpcion1
	 */
	public static String getCodigoRespuestaBloqueoOpcion1() {
		return CODIGO_RESPUESTA_BLOQUEO_OPCION1;
	}

	/**
	 * @return the codigoRespuestaBloqueoOpcion2
	 */
	public static String getCodigoRespuestaBloqueoOpcion2() {
		return CODIGO_RESPUESTA_BLOQUEO_OPCION2;
	}

	/**
	 * @return the razonBloqueoRobohurtows
	 */
	public static String getRazonBloqueoRobohurtows() {
		return RAZON_BLOQUEO_ROBOHURTOWS;
	}

	/**
	 * @return the razonBloqueoPerdidaextraviows
	 */
	public static String getRazonBloqueoPerdidaextraviows() {
		return RAZON_BLOQUEO_PERDIDAEXTRAVIOWS;
	}

	/**
	 * @return the razonBloqueoRobohurtomq
	 */
	public static String getRazonBloqueoRobohurtomq() {
		return RAZON_BLOQUEO_ROBOHURTOMQ;
	}

	/**
	 * @return the razonBloqueoPerdidaextraviomq
	 */
	public static String getRazonBloqueoPerdidaextraviomq() {
		return RAZON_BLOQUEO_PERDIDAEXTRAVIOMQ;
	}

	/**
	 * @return the equipoDelegate
	 */
	public EquipoDelegate getEquipoDelegate() {
		return equipoDelegate;
	}

	/**
	 * @param equipoDelegate the equipoDelegate to set
	 */
	public void setEquipoDelegate(EquipoDelegate equipoDelegate) {
		this.equipoDelegate = equipoDelegate;
	}
    
	
	
	/**
     * Inicializa los datos de Equipo (Bloqueo - Desbloqueo)
     * 
     * @param event
     */
    public void initBloqueoDesbloqueo(PhaseEvent event) {
    	String numeroPcsSeleccionado = "";
    	try {
        	
        	//CuentaController cuentaController = new CuentaController();
        	
        	//cuentaController.loadData();
        	
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());

            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
            String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
            String rutUsuarioSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuarioSeleccionado");
            
            
            LOGGER.info("mercado :::: " + mercado );
            LOGGER.info("numeroPcsSeleccionado :::: " + numeroPcsSeleccionado );
            LOGGER.info("rutUsuarioSeleccionado :::: " + rutUsuarioSeleccionado );
            
            loadDataBloqueoDesbloqueoEquipo( mercado , numeroPcsSeleccionado , rutUsuarioSeleccionado );
            

        }  catch (DAOException e) {
            LOGGER.error("DAOException al buscar informacion de equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar informacion de equipo. msisdn: "+numeroPcsSeleccionado);
            JSFMessagesHelper.addErrorCodeMessage("general", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al buscar informacion de equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }
    }	
    
    
    /**
     * Inicializa los datos para el bloqueo o desbloqueo del equipo
     * 
     * @param event
     */
    private void loadDataBloqueoDesbloqueoEquipo(String mercado , String msisdn , String rutUsuarioSeleccionado){
    	try{
    		
    		Map<String, Object> accionBloqueoDesbloqueo = new HashMap<String, Object>();
    		int imeiDV = loadImeiDV(msisdn);
   
		    		consultarBloqueoDesbloqueoBean = equipoDelegate.getConsultarBloqueoDesbloqueo(msisdn, mercado, String.valueOf(imeiDV));
		    				    		
		            RutBean rt = new RutBean(rutUsuarioSeleccionado);
		            String rutSinDV = rutUsuarioSeleccionado != null ? rutUsuarioSeleccionado.substring(0, rutUsuarioSeleccionado.length()-1) : "";
		            String rutDV = String.valueOf(rt.getDigito());
		            
		            LOGGER.info("consultarBloqueoDesbloqueoBean.getCodigoRespuesta()::::::" + consultarBloqueoDesbloqueoBean.getCodigoRespuesta() );
		            
		            accionBloqueoDesbloqueo.put("desbloqueado", consultarBloqueoDesbloqueoBean.getCodigoRespuesta() );
		            
		            String codigoRespuesta=consultarBloqueoDesbloqueoBean.getCodigoRespuesta();
		        	codigoRespuesta= (codigoRespuesta==null || codigoRespuesta=="")?"0000":codigoRespuesta;
		            
		    	if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_OK)){
		    			
		    			accionBloqueoDesbloqueo.put("desbloqueado", CODIGO_RESPUESTA_OK );
		    			accionBloqueoDesbloqueo.put("descripcionEquipo", consultarBloqueoDesbloqueoBean.getEquipoActual().getDescripcionEquipo());
		    			accionBloqueoDesbloqueo.put("imsi", consultarBloqueoDesbloqueoBean.getEquipoActual().getImsi());
		    			accionBloqueoDesbloqueo.put("imei", consultarBloqueoDesbloqueoBean.getEquipoActual().getImei());
		    			accionBloqueoDesbloqueo.put("iccid", consultarBloqueoDesbloqueoBean.getEquipoActual().getIccid());
		    			accionBloqueoDesbloqueo.put("msisdn",msisdn);
		    			accionBloqueoDesbloqueo.put("rutSinDV",rutSinDV);
		    			accionBloqueoDesbloqueo.put("rutDV",rutDV);
		    			accionBloqueoDesbloqueo.put("mercado",mercado);
		    			accionBloqueoDesbloqueo.put("estadoMovil","Activo");
		    			
		    		}else if((Integer.parseInt(codigoRespuesta)>=Integer.parseInt(CODIGO_RESPUESTA_BLOQUEO_OPCION1) && Integer.parseInt(codigoRespuesta)<=Integer.parseInt(CODIGO_RESPUESTA_BLOQUEO_OPCION10))){
		    			
		    			if( consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION1) || consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION3)  || 
		    			    consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION5) || consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION6)  || 
		    			    consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION8) || consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION9) ){
		    				
		    				accionBloqueoDesbloqueo.put("bloqueoRoboHurto", consultarBloqueoDesbloqueoBean.getCodigoRespuesta() );
		    			
		    			}else if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION2)|| consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION7) || 
		    					 consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION10)){
		    			
		    				accionBloqueoDesbloqueo.put("bloqueoPerdidaExtravio", CODIGO_RESPUESTA_BLOQUEO_OPCION2 );
		        			accionBloqueoDesbloqueo.put("imsi", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getCodImsi());
		        			accionBloqueoDesbloqueo.put("imei", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getCodImei());
		        			accionBloqueoDesbloqueo.put("equipo", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getDescMarcaEquipo() + " " + consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getDescModeloEquipo() );
		        			accionBloqueoDesbloqueo.put("razonDesbloqueo", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getRazonBloqueo());
		        			accionBloqueoDesbloqueo.put("fechaPerdida", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getFechaPerdida());
		        			accionBloqueoDesbloqueo.put("fechaPerdidaFormated", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getFechaPerdidaFormated());
		        			accionBloqueoDesbloqueo.put("iccid", consultarBloqueoDesbloqueoBean.getEquipoActual().getIccid());
		        			accionBloqueoDesbloqueo.put("motivoBloqueo","extravio");
		    			}
		    			
		    			accionBloqueoDesbloqueo.put("estadoMovil","Suspendido");		    			
		    			
		    			if (consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION1)) {
		    				accionBloqueoDesbloqueo.put("motivoBloqueo","robo");
		    			} else if (consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION2)) {
		    				accionBloqueoDesbloqueo.put("motivoBloqueo","extrav&iacute;o");
		    			} else if (consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION3)) {
		    				accionBloqueoDesbloqueo.put("motivoBloqueo","hurto");
		    			} 	    			
		    			
		    			
		    		}else if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_DATOS_INCOSINTENTES)){
		    			accionBloqueoDesbloqueo.put("error", consultarBloqueoDesbloqueoBean.getCodigoRespuesta());
					}else if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_PENDIENTE_BLOQUEO)){
		    			accionBloqueoDesbloqueo.put("error", consultarBloqueoDesbloqueoBean.getCodigoRespuesta());
					}else if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_PENDIENTE_DESBLOQUEO)){
		    			accionBloqueoDesbloqueo.put("error", consultarBloqueoDesbloqueoBean.getCodigoRespuesta());
					}else if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_ERROR_DESBLOQUEO)){
		    			accionBloqueoDesbloqueo.put("error", consultarBloqueoDesbloqueoBean.getCodigoRespuesta());
					}else if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_ERROR_BLOQUEO)){
		    			accionBloqueoDesbloqueo.put("error", consultarBloqueoDesbloqueoBean.getCodigoRespuesta());
					}
		    		else{
						accionBloqueoDesbloqueo.put("bloqueoRoboHurto", "9999" );
						accionBloqueoDesbloqueo.put("error", consultarBloqueoDesbloqueoBean.getCodigoRespuesta());
					}
		    		//String fechaActual = DateHelper.format(new Date(), "dd-MM-yyyy");
		    		//accionBloqueoDesbloqueo.put("fechaActual", fechaActual );
		    		
            
            respuestaInitBloqueoDesbloqueoJson = JsonHelper.toJsonResponse(accionBloqueoDesbloqueo);
            
    		
    	}  catch (DAOException e) {          
            LOGGER.error("DAOException Error al buscar informacion de equipo ", e);
            respuestaInitBloqueoDesbloqueoJson = JsonHelper.toJsonServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException Error al buscar informacion de equipo. msisdn: "+msisdn);
            respuestaInitBloqueoDesbloqueoJson = JsonHelper.toJsonServiceErrorMessage("general", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception Error al buscar informacion de equipo", e);
            respuestaInitBloqueoDesbloqueoJson = JsonHelper.toJsonServiceErrorMessage("noinfoequipo");
        }
    	
    }
    
    /**
     * Obtiene el digito de verificacion para el imei 
     * 
     * @param event
     */
    
    private int loadImeiDV( String msisdn ){
    	int imeiDV = 0;
    	try{
    		LOGGER.info("loadImeiDV");
    		String imeiEquipo = equipoDelegate.obtenerImei(msisdn);
    		imeiDV = calcularDigitoIMEI( imeiEquipo );
    		
    	}   catch (DAOException e) {
            LOGGER.error("DAOException al buscar informacion del imei.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException de servicio al buscar informacion de imei. msisdn: "+msisdn);
            JSFMessagesHelper.addErrorCodeMessage("general", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al buscar informacion de imei.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }
		return imeiDV;     	
    } 
    
    /**
     * Calcula el DV para el imei
     * 
     * @param event
     */    
    public int calcularDigitoIMEI(String imei) {

        int sum = 0;
  
        for (int i = 0; i < imei.length(); i++) {
            sum += Integer.parseInt(imei.substring(i, i + 1));
        }

        int delta[] = {0, 1, 2, 3, 4, -4, -3, -2, -1, 0};
        for (int i = imei.length() - 1; i >= 0; i -= 2) {
            int deltaIndex = Integer.parseInt(imei.substring(i, i + 1));
            int deltaValue = delta[deltaIndex];
            sum += deltaValue;
        }

        int mod10 = sum % 10;
        mod10 = 10 - mod10;
        if (mod10 == 10) {
            mod10 = 0;
        }

        return mod10;
    }
    
	 /**
     * Consultar Datos Buic
     * 
     * @param event
     */
    public void consultarDatosBuic(PhaseEvent event) {
    	String rutSeleccionado = "";
        try{           
        	
        	LOGGER.info("CONSULTANDO LOS DATOS DEL BUIC....");
        	
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());

            rutSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuarioSeleccionado");
            String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
            
            
            RutBean rt = new RutBean(rutSeleccionado);
            String rutSinDV = rutSeleccionado != null ? rutSeleccionado.substring(0, rutSeleccionado.length()-1) : "";
            String rutDV = String.valueOf(rt.getDigito());
            
            consultarDatosBuicBean = equipoDelegate.getConsultarDatosBuic(mercado , rutSinDV , rutDV);
            
            
            respuestaConsultarDatosBuicJson = JsonHelper.toJsonResponse(consultarDatosBuicBean);
   
        } catch (DAOException e) {          
            LOGGER.error("DAOException al consultar los datos del buic ", e);
            respuestaConsultarDatosBuicJson = JsonHelper.toJsonServiceErrorMessage("consultarDatosBuic");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al consultar los datos del buic. rutSeleccionado: "+rutSeleccionado);
            respuestaConsultarDatosBuicJson = JsonHelper.toJsonServiceErrorMessage("consultarDatosBuic", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al consultar los datos del buic", e);
            respuestaConsultarDatosBuicJson = JsonHelper.toJsonServiceErrorMessage("consultarDatosBuic");
        }
    }
    
    /**
     * valida clave para el desbloqueo por perdida o extravio
     * 
     * @param event
     */
    public void validarClaveDesbloqueo(PhaseEvent event) {
    	String numeroPcs = "";
    	Map<String, Object> respuestaValidarClaveDesbloqueo = new HashMap<String, Object>();
    	String validarClaveDesbloqueo = "";
    	try{           
        	
        	validarClaveDesbloqueo = MiEntelProperties.getServiceMessages().getErrorMessage("validarClaveDesbloqueo");
    	    
        	MiEntelBean miEntelBean = new MiEntelBean();
            
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());

	    	numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
            String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");

            String claveBloqueo = JsfUtil.getRequestParameter("claveBloqueo");
            
            if( mercado.equals(miEntelBean.getSiglaCuentaControlada()) || mercado.equals(miEntelBean.getSiglaSuscripcion())){
            	mercado = "1";
            }else if(mercado.equals(miEntelBean.getSiglaPrepago())){
            	mercado = "2";
            }
            
            LOGGER.info("VALIDANDO CLAVE PARA DESBLOQUEO");
            LOGGER.info("mercado  ::::" + mercado);
            LOGGER.info("numereoPcs ::::" + numeroPcs);
            LOGGER.info("clave :::::" + claveBloqueo);
            
            String respuestaValidarClave = null;
            
            respuestaValidarClave = equipoDelegate.validarClaveDesbloqueo(mercado , numeroPcs , claveBloqueo);
            
            if( respuestaValidarClave == null || "".equals(respuestaValidarClave) || respuestaValidarClave.isEmpty()){
            	LOGGER.info("ENTRANDO A LLAMAR NUEVAMENTE EL VALIDAR CLAVE EN EL CONTROLLER....");
            	respuestaValidarClave = equipoDelegate.validarClaveDesbloqueo(mercado , numeroPcs , claveBloqueo);	
            }
            
            LOGGER.info("RESPUESTA VALIDACION :::::" + respuestaValidarClave);
            
            respuestaValidarClaveDesbloqueo.put("respuestaValidacion", respuestaValidarClave);
            
            respuestaValidarClaveDesbloqueoJson = JsonHelper.toJsonResponse(respuestaValidarClaveDesbloqueo);
            
        } catch (DAOException e) {          
            LOGGER.error("DAOException al validar la clave para el desbloqueo", e);
            respuestaValidarClaveDesbloqueo.put("respuestaValidacion", validarClaveDesbloqueo);
            respuestaValidarClaveDesbloqueoJson = JsonHelper.toJsonErrorMessage("validarClaveDesbloqueo");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException al validar la clave para el desbloqueo. msisdn: "+numeroPcs);
        	respuestaValidarClaveDesbloqueo.put("respuestaValidacion", validarClaveDesbloqueo);
        	respuestaValidarClaveDesbloqueoJson = JsonHelper.toJsonErrorMessage("validarClaveDesbloqueo");
        } catch (Exception e) {
        	LOGGER.error("Exception al validar la clave para el desbloqueo", e);
        	respuestaValidarClaveDesbloqueo.put("respuestaValidacion", validarClaveDesbloqueo);
        	respuestaValidarClaveDesbloqueoJson = JsonHelper.toJsonErrorMessage("validarClaveDesbloqueo");
        }
    }

    /**
     * consultar ultimos equipos
     * 
     * @param event
     */
    public void consultarHistoricoEquipos(PhaseEvent event) {
    	String numeroPcs = "";
    	try{           
        	
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());

	    	numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
            
	    	int dvImei = 0;
	    	
	    	listaTraficoEquipos = equipoDelegate.consultarHistoricoEquipos(numeroPcs , consultarBloqueoDesbloqueoBean.getEquipoActual().getImei() ,
	    			consultarBloqueoDesbloqueoBean.getEquipoActual().getImsi());
	    	
	    	if( listaTraficoEquipos.size() > 0 ){
	    		renderedListaTraficoEquipos = true;
	    	}else{
	    		renderedListaTraficoEquipos = false;
	    		
	    	}
	    	
	    	for( TraficoEquiposBean traficoEquiposBean  : listaTraficoEquipos ){
	    		
	    		dvImei = calcularDigitoIMEI( traficoEquiposBean.getImei() );
	    		traficoEquiposBean.setDescripcionEquipo(equipoDelegate.consultaDescripcionMovilTraficado(traficoEquiposBean.getImei(), String.valueOf(dvImei) ));
	    		
	    	}
	    	
	    	
	    	respuestaConsultarHistoricoEquiposJson = JsonHelper.toJsonResponse(listaTraficoEquipos);

        } catch (DAOException e) {          
            LOGGER.error("DAOException al validar la clave para el desbloqueo", e);
            respuestaConsultarDatosBuicJson = JsonHelper.toJsonServiceErrorMessage("validarClaveDesbloqueo");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException al validar la clave para el desbloqueo. msisdn: "+numeroPcs);
            respuestaConsultarDatosBuicJson = JsonHelper.toJsonServiceErrorMessage("validarClaveDesbloqueo", e.getCodigoRespuesta());
        } catch (Exception e) {
        	LOGGER.error("Exception al validar la clave para el desbloqueo", e);
            respuestaConsultarDatosBuicJson = JsonHelper.toJsonServiceErrorMessage("validarClaveDesbloqueo");
        }
    }
    
    
    /**
     * bloquear equipo
     * 
     * @param event
     */
    public void registrarBloqueoEquipo(PhaseEvent event) {
    	String numeroPcs = "";
    	try{           
        	
        	MiEntelBean miEntelBean = new MiEntelBean();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            
            numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
            String rutSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuarioSeleccionado");
            String mercadoProfileWrapper = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
            String nombreUsuario = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"nombreUsuario");
            String flagBam = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"flagBam");
            
            
            
            RutBean rt = new RutBean(rutSeleccionado);
            String rutSinDV = rutSeleccionado != null ? rutSeleccionado.substring(0, rutSeleccionado.length()-1) : "";
            String rutDV = String.valueOf(rt.getDigito());
            
            //String movil = JsfUtil.getRequestParameter("movil");
            String movil = numeroPcs;
            String email = JsfUtil.getRequestParameter("email");
            String dominioEmail = JsfUtil.getRequestParameter("emailDominio");
            String areaFonoContacto = JsfUtil.getRequestParameter("areaFonoContacto");
            String fonoContacto = JsfUtil.getRequestParameter("fonoContacto");
            String direccion = JsfUtil.getRequestParameter("direccion");
            
            String numeroDomicilio = JsfUtil.getRequestParameter("numeroDomicilio");
            String deptoOff = JsfUtil.getRequestParameter("deptoOff");
            String region = JsfUtil.getRequestParameter("region");
            String ciudad = JsfUtil.getRequestParameter("ciudad");
            String comuna = JsfUtil.getRequestParameter("comuna");
            

            if( numeroDomicilio == null  || "undefined".equals(numeroDomicilio) || "UNDEFINED".equals(numeroDomicilio)){
            	LOGGER.info(" NULO EL numeroDomicilio ");
            	numeroDomicilio = this.consultarDatosBuicBean.getNumeroDomicilio();
            }
            if( deptoOff == null || "undefined".equals(deptoOff) || "UNDEFINED".equals(deptoOff)){
            	LOGGER.info(" NULO EL deptoOff ");
            	deptoOff = this.consultarDatosBuicBean.getDeptoOff();
            }
            if( region == null || "undefined".equals(region) || "UNDEFINED".equals(region) ){
            	LOGGER.info(" NULO EL region ");
            	region = this.consultarDatosBuicBean.getRegion();
            }
            if( ciudad == null || "undefined".equals(ciudad) || "UNDEFINED".equals(ciudad) ){
            	LOGGER.info(" NULO EL ciudad ");
            	ciudad = this.consultarDatosBuicBean.getCiudad();
            }
            if( comuna == null || "undefined".equals(comuna) || "UNDEFINED".equals(comuna)){
            	LOGGER.info(" NULO EL comuna ");
            	comuna = this.consultarDatosBuicBean.getComuna();
            }
            if( direccion == null || "undefined".equals(direccion) || "UNDEFINED".equals(direccion) ){
            	LOGGER.info(" NULO EL comuna ");
            	direccion = this.consultarDatosBuicBean.getDireccion();
            }
            
            String mercado = "";

            if(mercadoProfileWrapper.equals(miEntelBean.getSiglaSuscripcion()) || mercadoProfileWrapper.equals(miEntelBean.getSiglaCuentaControlada()) ){
            	mercado = "1";
            }else if( mercadoProfileWrapper.equals(miEntelBean.getSiglaPrepago()) ){
            	mercado = "2";
            }
            String sentidoBloqueo = "2";
            String destinoBloqueo = "1";
            String motivoBloqueo = JsfUtil.getRequestParameter("motivoBloqueo");
            String claveBloqueo = JsfUtil.getRequestParameter("claveBloqueo");
            String fechaRoboExtravio = JsfUtil.getRequestParameter("fechaRoboExtravio");
            String plataforma = "Web";
            String imei = JsfUtil.getRequestParameter("imei");
            String marcaModelo = JsfUtil.getRequestParameter("marcaModelo");
            String operacionComercial = JsfUtil.getRequestParameter("operacionComercial");
            String fechaOperacion = JsfUtil.getRequestParameter("fechaOperacion");
            String fechaConstancia = JsfUtil.getRequestParameter("fechaConstancia");
            String horaConstancia = JsfUtil.getRequestParameter("horaConstancia");
            String unidad = JsfUtil.getRequestParameter("unidad");
            String numeroConstancia = JsfUtil.getRequestParameter("nroConstancia");
            String codigoOperacion = JsfUtil.getRequestParameter("codigoOperacion");
            
            LOGGER.info("Codigo operacion ::::" + codigoOperacion );
            
            if( claveBloqueo == null || "undefined".equals(claveBloqueo) || "UNDEFINED".equals(claveBloqueo)){
            	claveBloqueo="";
            }
            
            String descMarcaequi = "";
            String descModeloequi = "";
            
         	 if( marcaModelo != null && marcaModelo.contains(" ") ){
        		String marcaModeloArray[] = marcaModelo.split(" ");
        		descMarcaequi =  marcaModeloArray[0];
        		descModeloequi = marcaModeloArray[1];
 	    	 }

         	String codImsi = JsfUtil.getRequestParameter("imsi");
         	String codTerminal = "";
         	
         	if( flagBam.equals("1")){
         		codTerminal = "BAM";
         	}else if( flagBam.equals("0")){
         		codTerminal = "MOV";
         	}
            
            
         	String fechaRoboExtravioFormated = "";
            //String fechaConstanciaFormated = "";
            String fechaConstanciaFormatedWS = "";
            Date fechaActual = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    	SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMdd");

			SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy");
			fechaOperacion = formatoFecha.format(fechaActual);
			
			String fechaOperacionWS = "";
			SimpleDateFormat formatoFechaWS = new SimpleDateFormat("yyyyMMddhhmmss");
			fechaOperacionWS = formatoFechaWS.format(fechaActual);
			fechaConstanciaFormatedWS = formatoFechaWS.format(fechaActual);

         /*
			if(fechaConstancia == null || fechaConstancia.isEmpty()){
				
				fechaConstanciaFormated = fechaOperacion;
				fechaConstanciaFormatedWS = fechaOperacionWS;
				
				
			}else{
				
				Date fechaConstanciaDate = sdf.parse(fechaConstancia);
				fechaConstanciaFormated = formatoFecha.format(fechaConstanciaDate);
				//fechaConstanciaFormatedWS = fechaConstanciaFormated.concat("000000");
				
				fechaConstanciaFormatedWS = formatoFechaWS.format(fechaConstanciaDate);
				
				LOGGER.info("fechaConstanciaFormated :::" + fechaConstanciaFormated );
				LOGGER.info("fechaConstanciaFormatedWS :::" + fechaConstanciaFormatedWS);
				
				//fechaConstanciaFormated = fechaOperacion;
				//fechaConstanciaFormatedWS = fechaOperacionWS;
				
				//fechaConstanciaFormated = formatoFecha.format(fechaConstancia);
				//fechaConstanciaFormatedWS = formatoFechaWS.format(fechaConstancia);
				
			}
          */
			
	    	String fechaRoboExtravioF = "";
			Date fechaRoboExtravioDate = sdf.parse(fechaRoboExtravio);
			fechaRoboExtravioF = sdff.format(fechaRoboExtravioDate);
			
			
			//fechaRoboExtravioFormated = fechaRoboExtravio != null ? fechaRoboExtravio.replace("-", "").concat("000000") : fechaOperacion.concat("000000");
			fechaRoboExtravioFormated = fechaRoboExtravioF.concat("000000");
			
		   //Fecha robo Extravio formato para MQ =====================================
				String fechaRoboExtravioMQFormat = "";
				SimpleDateFormat formatoFechaExtravioMQ = new SimpleDateFormat("ddMMyyyy");
				Date fechaRoboExtravioDateMQ = sdf.parse(fechaRoboExtravio);
				fechaRoboExtravioMQFormat = formatoFechaExtravioMQ.format(fechaRoboExtravioDateMQ);			
				fechaRoboExtravioMQFormat = fechaRoboExtravioMQFormat.concat("000000");
			//=========================================================================

            int dvImei = this.calcularDigitoIMEI(imei);
            String codImei = imei + dvImei; 

        	BloqueoDesbloqueoEquipoBean bloq = new BloqueoDesbloqueoEquipoBean();
        	
        	bloq.setMsisdn(movil);
        	bloq.setRut(rutSinDV);
        	bloq.setDv(rutDV);
        	bloq.setNombre(nombreUsuario);
        	bloq.setMercado(mercado);
        	bloq.setSentidoBloqueo(sentidoBloqueo);
        	bloq.setDestinoBloqueo(destinoBloqueo);
        	bloq.setMotivoBloqueo(motivoBloqueo);
        	bloq.setClaveBloqueo(claveBloqueo);
        	
        	bloq.setFechaRoboExtravio(fechaRoboExtravioMQFormat);

        	bloq.setEmailUsuario(email);
        	bloq.setEmailDominio(dominioEmail);
        	bloq.setAreaFono(areaFonoContacto);
        	bloq.setFonoContacto(fonoContacto);
        	bloq.setPlataforma(plataforma);
        	bloq.setImei(codImei);
        	bloq.setMarcaModelo(marcaModelo);
        	bloq.setOperacionComercial(operacionComercial);
        	

        	bloq.setFechaOperacion(fechaOperacion);
        	//bloq.setFechaConstancia(fechaConstanciaFormated);
        	
        	bloq.setHoraConstancia(horaConstancia);
        	bloq.setUnidad(unidad);
        	bloq.setNumeroConstancia(numeroConstancia);

        	
        	bloq.setDireccion(direccion);
        	bloq.setNumeroDomicilio(numeroDomicilio);
        	bloq.setDeptoOff(deptoOff);
        	bloq.setRegion(region);
        	bloq.setCiudad(ciudad);
        	bloq.setComuna(comuna);
        	
        	bloq.setCodOperacion(codigoOperacion);
        	bloq.setCodCompEnvio("220");
        	bloq.setCodImei(codImei);

        	bloq.setFechaPerdida(fechaRoboExtravioFormated);
        	bloq.setFechIoper(fechaOperacionWS);
        	bloq.setFechaDenuncia(fechaConstanciaFormatedWS);
        	
        	bloq.setDescMarcaequi(descMarcaequi);
        	bloq.setDescModeloequi(descModeloequi);
        	bloq.setDescTecnologia("OTI");
        	bloq.setIndcModalidad("1");
        	bloq.setNmroTelefono(movil);
        	bloq.setRazonBloqueo(motivoBloqueo);
        	bloq.setCodImsi(codImsi);
        	bloq.setCodTerminal(codTerminal);
        	
        	
        	equipoDelegate.ingresarBloqueoEquipo(bloq);

        	respuestaBloquearEquipoJson = JsonHelper.toJsonResponse("OK");

        } catch (DAOException e) {          
            LOGGER.error("DAOException al bloquear el equipo", e);
            respuestaBloquearEquipoJson = JsonHelper.toJsonServiceErrorMessage("bloquearEquipo");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException al bloquear el equipo. msisdn: "+numeroPcs);
        	respuestaBloquearEquipoJson = JsonHelper.toJsonServiceErrorMessage("bloquearEquipo", e.getCodigoRespuesta());
        } catch (Exception e) {
        	LOGGER.error("Exception al bloquear el equipo", e);
        	respuestaBloquearEquipoJson = JsonHelper.toJsonServiceErrorMessage("bloquearEquipo");
        }
    }
    
    public String formatearFecha(String fecha, String caracter) {
        String nuevaFecha = fecha.replaceAll(caracter, "");

        return nuevaFecha;
    }

    
    /**
     * desbloquear equipo
     * 
     * @param event
     */
    public void ingresarDesbloqueoEquipo(PhaseEvent event) {
    	String numeroPcs = "";
        try{           
        	
        	LOGGER.info("REGISTRANDO EL DESBLOQUEO.....");
        	
        	MiEntelBean miEntelBean = new MiEntelBean();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            
            numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
            String rutSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuarioSeleccionado");
            String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
            
            RutBean rt = new RutBean(rutSeleccionado);
            String rutSinDV = rutSeleccionado != null ? rutSeleccionado.substring(0, rutSeleccionado.length()-1) : "";
            String rutDV = String.valueOf(rt.getDigito());
            
            String mercadoN = "1";
            
            if(mercado.equals(miEntelBean.getSiglaSuscripcion()) || mercado.equals(miEntelBean.getSiglaCuentaControlada()) ){
            	mercadoN = "1";
            }else if( mercado.equals(miEntelBean.getSiglaPrepago()) ){
            	mercadoN = "2";
            }

            String claveBloqueo = JsfUtil.getRequestParameter("claveBloqueo");
            
            String motivoBloqueo = consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getRazonBloqueo();            
     
            
            BloqueoDesbloqueoEquipoBean desbloq = new BloqueoDesbloqueoEquipoBean();
            
            desbloq.setMsisdn(numeroPcs);
            desbloq.setRut(rutSinDV);
            desbloq.setDv(rutDV);
            desbloq.setMercado(mercadoN);
            desbloq.setClaveBloqueo(claveBloqueo);
            desbloq.setPlataforma("Web");
            desbloq.setSentidoBloqueo("2");
            desbloq.setDestinoBloqueo("1");
            desbloq.setMotivoBloqueo(motivoBloqueo);
            desbloq.setIdTransaccion("");
            desbloq.setFechaConstancia("");
            desbloq.setHoraConstancia("");
            desbloq.setUnidad("");
            desbloq.setNroConstancia("");
            desbloq.setCodOperacion("0");
            desbloq.setCodCompEnvio("220");
            desbloq.setCodImei(consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getCodImei());
            desbloq.setFechaPerdida(consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getFechaPerdida());
            desbloq.setFechIoper(consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getFechaIoper());
            desbloq.setFechaDenuncia(consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getFechaDenuncia());
            desbloq.setDescMarcaequi(consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getDescMarcaEquipo());
            desbloq.setDescModeloequi(consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getDescModeloEquipo());
            desbloq.setDescTecnologia(consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getDescTecnologia());
            desbloq.setIndcModalidad("1");
            desbloq.setNmroTelefono(consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getNmroTelefono());
            desbloq.setRazonBloqueo(consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getRazonBloqueo());
            desbloq.setCodImsi(consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getCodImsi());
            desbloq.setCodTerminal(consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getCodTerminal());
            
            equipoDelegate.ingresarDesbloqueoEquipo(desbloq);
            
            respuestaIngresarDesbloqueoJson = JsonHelper.toJsonResponse("OK");

        } catch (DAOException e) {          
            LOGGER.error("DAOException Error al desbloquear el equipo", e);
            respuestaIngresarDesbloqueoJson = JsonHelper.toJsonServiceErrorMessage("desbloquearEquipo");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException Error al desbloquear el equipo. msisdn: "+numeroPcs);
        	respuestaIngresarDesbloqueoJson = JsonHelper.toJsonServiceErrorMessage("desbloquearEquipo", e.getCodigoRespuesta());
        } catch (Exception e) {
        	LOGGER.error("Exception Error al desbloquear el equipo", e);
        	respuestaIngresarDesbloqueoJson = JsonHelper.toJsonServiceErrorMessage("desbloquearEquipo");
        }
    }
    
    /**
     * Inicializa los datos de Equipo (Bloqueo - Desbloqueo)
     * 
     * @param event
     */
    public void initDatosBloqueo(PhaseEvent event) {
    	String numeroPcsSeleccionado = "";
    	try {
        	
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());

            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
            String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
            String rutUsuarioSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuarioSeleccionado");
            
            
            LOGGER.info("mercado :::: " + mercado );
            LOGGER.info("numeroPcsSeleccionado :::: " + numeroPcsSeleccionado );
            LOGGER.info("rutUsuarioSeleccionado :::: " + rutUsuarioSeleccionado );
            
            loadDataBloqueo( mercado , numeroPcsSeleccionado , rutUsuarioSeleccionado );

        }  catch (DAOException e) {
            LOGGER.error("DAOException al buscar informacion de equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar informacion de equipo. msisdn: "+numeroPcsSeleccionado);
            JSFMessagesHelper.addErrorCodeMessage("general", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al buscar informacion de equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }
    }	
    
    
    /**
     * Inicializa los datos para el bloqueo o desbloqueo del equipo
     * 
     * @param event
     */
    private void loadDataBloqueo(String mercado , String msisdn , String rutUsuarioSeleccionado){
    	try{
    		
    		Map<String, Object> datosBloqueo = new HashMap<String, Object>();
    		int imeiDV = loadImeiDV(msisdn);
   
		    	   consultarBloqueoDesbloqueoBean = equipoDelegate.getConsultarBloqueoDesbloqueo(msisdn, mercado, String.valueOf(imeiDV));		    				            
		           LOGGER.info("consultarBloqueoDesbloqueoBean.getCodigoRespuesta()::::::" + consultarBloqueoDesbloqueoBean.getCodigoRespuesta() );		            		      
		           
		           String codigoRespuesta = consultarBloqueoDesbloqueoBean.getCodigoRespuesta();
		           codigoRespuesta= (codigoRespuesta==null || codigoRespuesta=="")?"0000":codigoRespuesta;		
		   		
		 		  if ((Integer.parseInt(codigoRespuesta)>=Integer.parseInt(CODIGO_RESPUESTA_BLOQUEO_OPCION1) && Integer.parseInt(codigoRespuesta)<=Integer.parseInt(CODIGO_RESPUESTA_BLOQUEO_OPCION10)))		           
		    			datosBloqueo.put("tipoBloqueo", consultarBloqueoDesbloqueoBean.getCodigoRespuesta()); 
		    		else
		    			datosBloqueo.put("tipoBloqueo", ""); 
			
		    		if(!mercado.equals("PP"))     		    			
		    			if(equipoDelegate.tieneBloqueoPorFacturaImpaga(msisdn))
		    				  datosBloqueo.put("tieneBloqueoFactura", "1"); //1:Tiene Bloqueo Factura Impaga
		    			else
		    				  datosBloqueo.put("tieneBloqueoFactura", "0"); //0:NO Tiene Bloqueo Factura Impaga
		    		
		    		datosBloqueoJson = JsonHelper.toJsonResponse(datosBloqueo);
            
    		
    	}  catch (DAOException e) {          
            LOGGER.error("DAOException Error al buscar informacion de equipo ", e);
            datosBloqueoJson = JsonHelper.toJsonServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException Error al buscar informacion de equipo. msisdn: "+msisdn);
            datosBloqueoJson = JsonHelper.toJsonServiceErrorMessage("general", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception Error al buscar informacion de equipo", e);
            datosBloqueoJson = JsonHelper.toJsonServiceErrorMessage("noinfoequipo");
        }
    	
    } 
    
    public String getPageLabelPagoEnLinea() {
		try {
			
			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());

			return JSFPortletHelper.getPreference(JSFPortletHelper
					.getPreferencesObject(), ProfileWrapperHelper
					.getPropertyAsString(profileWrapper, "mercado"), null);
		} catch (Exception e) {
			LOGGER.error("No se ha podido obtener el pageLabel" + e.getMessage(), e);
			return "";
		}
	}
    
    public String getDatosBloqueoJson() {
		return datosBloqueoJson;
	}

	public void setDatosBloqueoJson(String datosBloqueoJson) {
		this.datosBloqueoJson = datosBloqueoJson;
	}
	
	public String getPageLabelBloqueoDesbloqueo() {
		try {
			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());

			return JSFPortletHelper.getPreference(JSFPortletHelper
					.getPreferencesObject(), "pageBlo_"+ProfileWrapperHelper
					.getPropertyAsString(profileWrapper, "mercado"), null);
		} catch (Exception e) {
			LOGGER.error("No se ha podido obtener el pageLabel Bloqueo Desbloqueo" + e.getMessage(), e);
			return "";
		}
	}
	

}
