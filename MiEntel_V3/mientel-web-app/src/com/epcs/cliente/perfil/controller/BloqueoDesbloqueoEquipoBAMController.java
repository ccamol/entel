/**
 * 
 */
package com.epcs.cliente.perfil.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.BloqueoDesbloqueoEquipoBean;
import com.epcs.bean.ConsultarBloqueoDesbloqueoBean;
import com.epcs.bean.ConsultarDatosBuicBean;
import com.epcs.bean.MiEntelBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.TraficoEquiposBean;
import com.epcs.cliente.perfil.delegate.EquipoDelegate;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author Juan Carlos
 *
 */
public class BloqueoDesbloqueoEquipoBAMController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(BloqueoDesbloqueoEquipoBAMController.class);
    
    private EquipoDelegate equipoDelegate;
    
    private String respuestaInitBloqueoDesbloqueoJson;
    
    private ConsultarBloqueoDesbloqueoBean consultarBloqueoDesbloqueoBean ;
    
    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties.getProperty("servicios.codigoRespuesta.exito");
    
    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION1 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP1"); // Robo
    
    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION2 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP2"); // Extravio

    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION3 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP3"); // Hurto
    
    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION4 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP4"); // Factura Impaga
    
    public static final String CODIGO_RESPUESTA_PENDIENTE_BLOQUEO = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipopPendienteBloqueo"); // Pendiente Bloqueo
    
    public static final String CODIGO_RESPUESTA_PENDIENTE_DESBLOQUEO = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipopPendienteDesBloqueo"); // Pendiente Des Bloqueo   

    public static final String CODIGO_RESPUESTA_ERROR_DESBLOQUEO = MiEntelProperties.getProperty("servicios.codigoRespuesta.errorDesBloqueo"); // Error proceso DesBloqueo
    
    public static final String CODIGO_RESPUESTA_ERROR_BLOQUEO = MiEntelProperties.getProperty("servicios.codigoRespuesta.errorBloqueo"); // Error proceso Bloqueo
     
    public static final String RAZON_BLOQUEO_ROBOHURTOWS = MiEntelProperties.getProperty("equipo.bloqueo.razonBloqueoRoboHurtoWS");
    
    public static final String RAZON_BLOQUEO_PERDIDAEXTRAVIOWS = MiEntelProperties.getProperty("equipo.bloqueo.razonBloqueoPerdidaExtravioWS");
    
    public static final String RAZON_BLOQUEO_ROBOHURTOMQ = MiEntelProperties.getProperty("equipo.bloqueo.razonBloqueoRoboHurtoMQ");
    
    public static final String RAZON_BLOQUEO_PERDIDAEXTRAVIOMQ = MiEntelProperties.getProperty("equipo.bloqueo.razonBloqueoPerdidaExtravioMQ");

    public static final String CODIGO_RESPUESTA_DATOS_INCOSINTENTES = MiEntelProperties.getProperty("servicios.codigoRespuesta.infoIncosistentes");

    
    private ConsultarDatosBuicBean consultarDatosBuicBean ;
    
    private List<TraficoEquiposBean> listaTraficoEquipos;
    
    private boolean renderedListaTraficoEquipos;
    
    private String respuestaConsultarHistoricoEquiposJson;
    
    private String respuestaBloquearEquipoJson;
    
    private String respuestaValidarClaveDesbloqueoJson;
    
    private String respuestaIngresarDesbloqueoJson;
    
    private String datosBloqueoJson;
    
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
	 * @return the listaTraficoEquipos
	 */
	public List<TraficoEquiposBean> getListaTraficoEquipos() {
		return listaTraficoEquipos;
	}

	/**
	 * @param listaTraficoEquipos the listaTraficoEquipos to set
	 */
	public void setListaTraficoEquipos(List<TraficoEquiposBean> listaTraficoEquipos) {
		this.listaTraficoEquipos = listaTraficoEquipos;
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
    		
    		LOGGER.info("INICIO BLOQUEO DESBLOQUEO");
        	
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());

            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
            String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
            String rutUsuarioSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuarioSeleccionado");
            
            
            loadDataBloqueoDesbloqueoEquipo( mercado , numeroPcsSeleccionado , rutUsuarioSeleccionado );
            
            LOGGER.info("FIN BLOQUEO DESBLOQUEO");
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
    				ConsultarDatosBuicBean consultarDatosBuicBean = null;

		            RutBean rt = new RutBean(rutUsuarioSeleccionado);
		            String rutSinDV = rutUsuarioSeleccionado != null ? rutUsuarioSeleccionado.substring(0, rutUsuarioSeleccionado.length()-1) : "";
		            String rutDV = String.valueOf(rt.getDigito());

    				
    				int imeiDV = loadImeiDV(msisdn);
    				
   
		    		consultarBloqueoDesbloqueoBean = equipoDelegate.getConsultarBloqueoDesbloqueo(msisdn, mercado, String.valueOf(imeiDV));
		    		
		            accionBloqueoDesbloqueo.put("desbloqueado", consultarBloqueoDesbloqueoBean.getCodigoRespuesta() );
		            
		    		if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_OK) || consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION4)){
		    			
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
		    			
		    		}else if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION1 ) || consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION2 ) || consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION3 )){
		    			
		    			if( consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION1) || consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION3 )){
			    				
			    				accionBloqueoDesbloqueo.put("bloqueoRoboHurto", CODIGO_RESPUESTA_BLOQUEO_OPCION1 );
			    			
		    			}else if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION2)){
			    			
			    				accionBloqueoDesbloqueo.put("bloqueoPerdidaExtravio", CODIGO_RESPUESTA_BLOQUEO_OPCION2 );
			        			accionBloqueoDesbloqueo.put("imsi", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getCodImsi());
			        			accionBloqueoDesbloqueo.put("imei", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getCodImei());
			        			accionBloqueoDesbloqueo.put("equipo", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getDescMarcaEquipo() + " " + consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getDescModeloEquipo() );
			        			accionBloqueoDesbloqueo.put("fechaPerdida", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getFechaPerdida());
			        			accionBloqueoDesbloqueo.put("razonDesbloqueo", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getRazonBloqueo());
			        			accionBloqueoDesbloqueo.put("fechaPerdidaFormated", consultarBloqueoDesbloqueoBean.getDatosEquipoRobado().getFechaPerdidaFormated());
			        			accionBloqueoDesbloqueo.put("iccid", consultarBloqueoDesbloqueoBean.getEquipoActual().getIccid());
			        			accionBloqueoDesbloqueo.put("motivoBloqueo","extravio");
			        			
			        			LOGGER.info("consultarBloqueoDesbloqueoBean.getEquipoActual().getIccid() ::::" + consultarBloqueoDesbloqueoBean.getEquipoActual().getIccid() );
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
		    		
		    		
		    		consultarDatosBuicBean =  this.consultarDatosBuic(mercado, rutSinDV, rutDV);		    		
		    		
		    		accionBloqueoDesbloqueo.put("consultarDatosBuicBean", consultarDatosBuicBean);
		    		
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
        int mod10 = 0;
        try {
	        for (int i = 0; i < imei.length(); i++) {
	            sum += Integer.parseInt(imei.substring(i, i + 1));
	        }
	
	        int delta[] = {0, 1, 2, 3, 4, -4, -3, -2, -1, 0};
	        for (int i = imei.length() - 1; i >= 0; i -= 2) {
	            int deltaIndex = Integer.parseInt(imei.substring(i, i + 1));
	            int deltaValue = delta[deltaIndex];
	            sum += deltaValue;
	        }
	
	        mod10 = sum % 10;
	        mod10 = 10 - mod10;
	        if (mod10 == 10) {
	            mod10 = 0;
	        }
        } catch (Exception e) {
            LOGGER.error("Exception al calcular el DV del IMEI.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }
	
        return mod10;
    }
    
    /**
     * Consultar Datos Buic
     * 
     * @param event
     */
    private ConsultarDatosBuicBean consultarDatosBuic(String mercado , String rutSinDV, String rutDV ) {

    	String rutSeleccionado = "";
        
    	try{           
        	
        	LOGGER.info("CONSULTANDO LOS DATOS DEL BUIC....");

        	consultarDatosBuicBean = equipoDelegate.getConsultarDatosBuic(mercado , rutSinDV , rutDV);
            
        } catch (DAOException e) {          
            LOGGER.error("DAOException al consultar los datos del buic ", e);
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al consultar los datos del buic. rutSeleccionado: "+rutSeleccionado);
        } catch (Exception e) {
            LOGGER.error("Exception al consultar los datos del buic", e);
        }
        
        return consultarDatosBuicBean ;
    }


    /**
     * consultar ultimos equipos
     * 
     * @param event
     */
    public void consultarHistoricoEquipos(PhaseEvent event) {
    	String numeroPcs = "";
    	renderedListaTraficoEquipos = false;
    	try{           
        	LOGGER.info("CONSULTANDO EL HISTORICO DE EQUIPOS ");
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());

	    	numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
            
	    	int dvImei = 0;
	    	
	    	listaTraficoEquipos = equipoDelegate.consultarHistoricoEquipos(numeroPcs ,
	    				consultarBloqueoDesbloqueoBean.getEquipoActual().getImei() ,
	    				consultarBloqueoDesbloqueoBean.getEquipoActual().getImsi());
	    	
	    	if( listaTraficoEquipos.size() > 0 ){
	    		renderedListaTraficoEquipos = true;
	    	}
	    	
	    	for( TraficoEquiposBean traficoEquiposBean  : listaTraficoEquipos ){
	    		
	    		dvImei = calcularDigitoIMEI( traficoEquiposBean.getImei() );
	    		traficoEquiposBean.setDescripcionEquipo(equipoDelegate.consultaDescripcionMovilTraficado(traficoEquiposBean.getImei(), String.valueOf(dvImei) ));
	    		
	    	}
	    	
	    	respuestaConsultarHistoricoEquiposJson = JsonHelper.toJsonResponse(listaTraficoEquipos);

        } catch (DAOException e) {          
            LOGGER.error("DAOException al consultar el historial de equipos", e);
            respuestaConsultarHistoricoEquiposJson = JsonHelper.toJsonServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException al consultar el historial de equipos");
        	respuestaConsultarHistoricoEquiposJson = JsonHelper.toJsonServiceErrorMessage("general", e.getCodigoRespuesta());
        } catch (Exception e) {
        	LOGGER.error("Exception al consultar el historial de esquipos", e);
        	respuestaConsultarHistoricoEquiposJson = JsonHelper.toJsonServiceErrorMessage("noinfoequipo");
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
            
            LOGGER.info("numeroDomicilio :::" + numeroDomicilio);
            LOGGER.info("deptoOff ::::"+ deptoOff);
            LOGGER.info("region :::::"+ region);
            LOGGER.info("ciudad ::::" + ciudad);
            LOGGER.info("comuna ::::" + comuna);

            String numeroDomicilioBUIC = JsfUtil.getRequestParameter("numeroDomicilioBUIC");
            String deptoOffBUIC = JsfUtil.getRequestParameter("deptoOffBUIC");
            String regionBUIC = JsfUtil.getRequestParameter("regionBUIC");
            String ciudadBUIC = JsfUtil.getRequestParameter("ciudadBUIC");
            String comunaBUIC = JsfUtil.getRequestParameter("comunaBUIC");
            
            LOGGER.info("numeroDomicilioBUIC :::" + numeroDomicilioBUIC);
            LOGGER.info("deptoOffBUIC ::::"+ deptoOffBUIC);
            LOGGER.info("regionBUIC :::::"+ regionBUIC);
            LOGGER.info("ciudadBUIC ::::" + ciudadBUIC);
            LOGGER.info("comunaBUIC ::::" + comunaBUIC);

            if( numeroDomicilio == null  || "undefined".equals(numeroDomicilio) || "UNDEFINED".equals(numeroDomicilio)){
            	LOGGER.info(" NULO EL numeroDomicilio " + numeroDomicilioBUIC );
            	numeroDomicilio = numeroDomicilioBUIC;
            }
            if( deptoOff == null || "undefined".equals(deptoOff) || "UNDEFINED".equals(deptoOff)){
            	LOGGER.info(" NULO EL deptoOff " + deptoOffBUIC);
            	deptoOff = deptoOffBUIC;
            }
            if( region == null || "undefined".equals(region) || "UNDEFINED".equals(region)){
            	LOGGER.info(" NULO EL region " + regionBUIC );
            	region = regionBUIC;
            }
            if( ciudad == null || "undefined".equals(ciudad) || "UNDEFINED".equals(ciudad)){
            	LOGGER.info(" NULO EL ciudad " + ciudadBUIC );
            	ciudad = ciudadBUIC;
            }
            if( comuna == null || "undefined".equals(comuna) || "UNDEFINED".equals(comuna)){
            	LOGGER.info(" NULO EL comuna " + comunaBUIC );
            	comuna = comunaBUIC;
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
            
            String descMarcaequi = "";
            String descModeloequi = "";
            
            if( claveBloqueo == null || "undefined".equals(claveBloqueo) || "UNDEFINED".equals(claveBloqueo)){
            	claveBloqueo="";
            }
            
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

			/*if(fechaConstancia == null || fechaConstancia.isEmpty()){
				
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
				
			}*/
			
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
		   //===========================================================================
           
		   

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
            
            LOGGER.info("VALIDANDO CLAVE PARA DESBLOQUEO BAM");
            LOGGER.info("mercado  ::::" + mercado);
            LOGGER.info("numereoPcs ::::" + numeroPcs);
            LOGGER.info("clave :::::" + claveBloqueo);
            
            String respuestaValidarClave = null;
            
            respuestaValidarClave = equipoDelegate.validarClaveDesbloqueo(mercado , numeroPcs , claveBloqueo);
            
            if( respuestaValidarClave == null || "".equals(respuestaValidarClave) || respuestaValidarClave.isEmpty()){
            	LOGGER.info("ENTRANDO A LLAMAR NUEVAMENTE EL VALIDAR CLAVE EN EL CONTROLLER BAM....");
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
     * desbloquear equipo
     * 
     * @param event
     */
    public void ingresarDesbloqueoEquipo(PhaseEvent event) {
    	String numeroPcs = "";
        try{           
        	
        	LOGGER.info("REGISTRANDO EL DESBLOQUEO BAM.....");
        	
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
		           
		           if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION1) || consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION2) || consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION3))
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
    
 
   
}

