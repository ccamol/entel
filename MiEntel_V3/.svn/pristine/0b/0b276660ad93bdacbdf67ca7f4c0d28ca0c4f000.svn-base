/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.DetalleMiTraficoSSBean;
import com.epcs.bean.GrupoClienteBean;
import com.epcs.bean.PlanesFullBean;
import com.epcs.bean.PlanesMultimediaBean;
import com.epcs.bean.ResumenTraficoBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.TasacionBean;
import com.epcs.billing.registrouso.delegate.TraficoDelegate;
import com.epcs.cliente.perfil.delegate.EquipoDelegate;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.error.ServiceMessages;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(TraficoController.class);
    
    private static final Logger CAJA_TRAFICO_LOGGER = Logger.getLogger("cajaTraficoLog");
    public static final String TIPO_PLAN_EXCEDIDO = MiEntelProperties.getProperty("servicios.codigoRespuesta.tipoPlan.excedido");
    
    private TraficoDelegate traficoDelegate;
    
    private PlanDelegate planDelegate;

    private ResumenTraficoBean resumenTrafico;

    private String resumenTraficoJson;

    private String mercado;
    
    private SeguridadDelegate seguridadDelegate;
    
    private static final int BYTESAMEGAYTES = 1024;  
    
	private static final String LANGUAGE = MiEntelProperties.getProperty("locale.language");
	private NumberFormat format = NumberFormat.getInstance(new Locale(LANGUAGE));
    
    
    
    private EquipoDelegate equipoDelegate;
    
    /*NBuevoRodrigo*/
    private String cargarCargoFijo = "0";
    /*Fin nuevo Rodrigo*/
   

	/**
     * @return the traficoDelegate
     */
    public TraficoDelegate getTraficoDelegate() {
        return traficoDelegate;
    }

    /**
     * @param traficoDelegate
     *            the traficoDelegate to set
     */
    public void setTraficoDelegate(TraficoDelegate traficoDelegate) {
        this.traficoDelegate = traficoDelegate;
    }

    /**
     * @return the resumenTrafico
     */
    public ResumenTraficoBean getResumenTrafico() {
        return resumenTrafico;
    }

    /**
     * @param resumenTrafico
     *            the resumenTrafico to set
     */
    public void setResumenTrafico(ResumenTraficoBean resumenTrafico) {
        this.resumenTrafico = resumenTrafico;
    }

    /**
     * @return the resumenTraficoJson
     */
    public String getResumenTraficoJson() {
        return resumenTraficoJson;
    }

    /**
     * @param resumenTraficoJson
     *            the resumenTraficoJson to set
     */
    public void setResumenTraficoJson(String resumenTraficoJson) {
        this.resumenTraficoJson = resumenTraficoJson;
    }   
    

    /**
	 * @return the planDelegate
	 */
	public PlanDelegate getPlanDelegate() {
		return planDelegate;
	}

	/**
	 * @param planDelegate the planDelegate to set
	 */
	public void setPlanDelegate(PlanDelegate planDelegate) {
		this.planDelegate = planDelegate;
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

	public void init(PhaseEvent event) {

        String numeroPcsSeleccionado = "";
        String autoAtencion = "";
        String mercado = "";
        String rutSeleccionado = "";
        try {
            
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcsSeleccionado");
            rutSeleccionado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "rutUsuarioSeleccionado");
            autoAtencion = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "aaa");
            mercado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "mercado");
            
            /*Nuevo ROdrigo Diaz */
            String nroCuenta = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroCuenta");
            /*FIN Nuevo*/
            
            

        	CAJA_TRAFICO_LOGGER.info("INICIO REGISTRO LOG CAJA TRAFICO");
        	CAJA_TRAFICO_LOGGER.info("****** Clase: com.epcs.billing.registrouso.controller.TraficoController ******");
            CAJA_TRAFICO_LOGGER.info("Movil: " + numeroPcsSeleccionado + " - Rut: " + rutSeleccionado);
            
            resumenTrafico = this.traficoDelegate.getResumenTrafico(
                    numeroPcsSeleccionado, autoAtencion);

            if( resumenTrafico == null ){
            	ServiceMessages errorMessages = MiEntelProperties.getServiceMessages();
		        CAJA_TRAFICO_LOGGER.warn(errorMessages.getErrorMessage("trafico"));
		                   	
                resumenTraficoJson = JsonHelper
                .toJsonServiceErrorMessage("trafico");
            }
            else{
	            	if(MiEntelBusinessHelper.isMercadoSuscripcion(mercado)){
	            		
	            		PlanesFullBean planesFullBean;
						try {
							planesFullBean = planDelegate.consultaXmlPlanesFull(
									resumenTrafico.getCodbscs2(), "");
							if (planesFullBean != null) {
								if (planesFullBean.getPlanExcedido().equals(
										TIPO_PLAN_EXCEDIDO)) {
									resumenTrafico
											.setPlanesMultimediaBean(consultarPlanMultimediaExcedido(numeroPcsSeleccionado,planesFullBean.getValorMBExcedido()));
									resumenTrafico.setExcedido(true);
								}
	
							}
						} catch (DAOException e) {
				        	CAJA_TRAFICO_LOGGER.info("No tiene plan multimedia excedido");
				        } catch (ServiceException e) {
				        	CAJA_TRAFICO_LOGGER.info("No tiene plan multimedia excedido");				           
				        } catch (Exception e) {
				        	CAJA_TRAFICO_LOGGER.info("No tiene plan multimedia excedido");	
				        }
	            		        		
	            	}
	            	
	            	/*INICIO OBTENCION DE DATOS PARA OBTENER SI A GRUPO EMPRESA SE LE MUESTRA EXCEDIDO EN DASHBOARD TRAFICO */
	            	String rutTitular = null;
		            
		            if(mercado.equals("PP")){
		            	 rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuario");
		            }else{
		            	rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutTitular");
		            }
		            
		            rutTitular = rutTitular == null ? ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado") : rutTitular;
		            
		            RutBean rt = new RutBean(rutTitular);
		            String rutSinDV = rutTitular != null ? rutTitular.substring(0, rutTitular.length()-1) : "";
		            String DV = String.valueOf(rt.getDigito());
	            	String codigoGrupo = null;
	            	String rutFormated = rutSinDV.concat(DV.toUpperCase());
	            	 try {
	            		 //EquipoDelegate delegateEquipo = new EquipoDelegate();
	            		 CAJA_TRAFICO_LOGGER.info("rutFormated"+rutFormated+ " nroCuenta"+nroCuenta);
	            	//GrupoClienteBean grupoCliente = delegateEquipo
					//.obtenerGrupoCliente(rutFormated, nroCuenta);
	            	
	            	GrupoClienteBean grupoCliente = this.equipoDelegate
	     			.obtenerGrupoCliente(rutFormated, nroCuenta);
	            	
	            	
	            	CAJA_TRAFICO_LOGGER.info("Grupo"+grupoCliente.getCodigoGrupo());
	            	//codigoGrupo = "13"; //BORRAR TERMINADA LAS PRUEBAS
	            	codigoGrupo = grupoCliente.getCodigoGrupo();
	            	System.out.println("TraficoController CodigoPlan:" +codigoGrupo);	
	            	//FIN BORRAR
	            	CAJA_TRAFICO_LOGGER.info(ParametrosHelper.getExisteParametro("equipo.grupoCliente.empresa",codigoGrupo));	
	            	CAJA_TRAFICO_LOGGER.info(ParametrosHelper.getExisteParametro("grupoCliente.excentoTrafico.empresa",codigoGrupo));	
	            	if (ParametrosHelper.getExisteParametro("equipo.grupoCliente.empresa",codigoGrupo)
	            			&& ParametrosHelper.getExisteParametro("grupoCliente.excentoTrafico.empresa",codigoGrupo)) {
	            		System.out.println("TraficoController - Existe");	
	            		resumenTrafico.setMostrarSeccionExcedidoDash("1");
					}else if(ParametrosHelper.getExisteParametro("equipo.grupoCliente.empresa",codigoGrupo) ){
		 				System.out.println("TraficoController - No Existe");
		 				//resumenTrafico.setMostrarSeccionExcedidoDash("0");
		 				resumenTrafico.setMostrarSeccionExcedidoDash("0");
		 			}else{
		 				System.out.println("TraficoController - Else");
		 				resumenTrafico.setMostrarSeccionExcedidoDash("1");
		 			}
	            	
	            	 } catch (Exception e) {
	 					LOGGER.error("Error al verificar el grupo del cliente.", e);
	 				}
	            	
	            	 /*fin OBTENCION DE DATOS PARA OBTENER SI A GRUPO EMPRESA SE LE MUESTRA EXCEDIDO EN DASHBOARD TRAFICO */
	            	 
	            	 
		        	//Setea los campos formateados
	                 setFormattedValues();
	                /*
	                 * Verificamos que el usuario tenga informacion de trafico generada 
	                 * para el mes actual. Si tiene, no se hace nada y se sigue el flujo 
	                 * normal; en caso contrario se debe mostrar el trafico del mes anterior. 
	                 * El metodo que realiza la validacion (tieneTraficoActual) tambien 
	                 * registra en log de la cajaTrafico en Dashboard. 
	                 * */
	                
	                if(!tieneTraficoActual(numeroPcsSeleccionado, mercado)){
	                	resumenTrafico.setTieneTraficoActual(false);
	                }else{
	                	resumenTrafico.setTieneTraficoActual(true);
	                }
		      
            	resumenTraficoJson = JsonHelper.toJsonResponse(resumenTrafico);
                CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO");
            }

        } catch (DAOException e) {
        	CAJA_TRAFICO_LOGGER.error("DAOException caught: "+e.getMessage(), e);
        	CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO");
            //mensaje de "No disponible"
            resumenTraficoJson = JsonHelper
                    .toJsonServiceErrorMessage("obtenerResumenTrafico");

        } catch (ServiceException e) {
        	CAJA_TRAFICO_LOGGER.info("ServiceException caught msisdn: "+numeroPcsSeleccionado+" - "+ e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
        	CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO");
            resumenTraficoJson = JsonHelper.toJsonServiceErrorMessage(
                    "gestionRegistroUso", e.getCodigoRespuesta());

        } catch (Exception e) {
        	CAJA_TRAFICO_LOGGER.error("Exception caught: "+e.getMessage(), e);
        	CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO");
            resumenTraficoJson = JsonHelper
                    .toJsonServiceErrorMessage("obtenerResumenTrafico");

        }
    }
    
    /**
     * Metodo privado utilitario para verificar si el msisdn tiene trafico actual generado.
     * Este metodo esta disenado para registrar en el log de la cajaTrafico en Dashboard e
     * indicar al metodo correspondiente en la clase DAO que tambien lo haga
     */
    private boolean tieneTraficoActual(String msisdn, String mercado){
    	boolean tieneTraficoActual = true;
    	/*
    	 * Indica al metodo en DAO que registre en log de la cajaTrafico en Dashboard
    	 */
    	boolean logCajaTrafico = true;
    	List<DetalleMiTraficoSSBean> listDetalleMiTraficoSSBean;
    	
    	try{
	        listDetalleMiTraficoSSBean = this.traficoDelegate.obtenerMiTrafico(
	        		msisdn, "0", mercado, logCajaTrafico);
	        
	        if(listDetalleMiTraficoSSBean == null || listDetalleMiTraficoSSBean.isEmpty())
	        	tieneTraficoActual = false;

	    } catch (DAOException e) {
	    	CAJA_TRAFICO_LOGGER.error("DAOException caught: "+e.getMessage(), e);
	
	    } catch (ServiceException e) {
	    	CAJA_TRAFICO_LOGGER.info("ServiceException caught msisdn: "+msisdn+" - "+ e.getCodigoRespuesta()
	                + " - " + e.getDescripcionRespuesta());
	        
	        tieneTraficoActual = false;
	
	    } catch (Exception e) {
	    	CAJA_TRAFICO_LOGGER.error("Exception caught: "+e.getMessage(), e);
	
	    }
	    
	    return tieneTraficoActual;
    }
    
    /**
     * Metodo privado utilitario para setear los atributos formateados
     */
    private void setFormattedValues() {
        
        resumenTrafico.setTraficoFechaActualizacionFormated(getTraficoDateFormatted(resumenTrafico.getTraficoVozFechaActualizacion(),
                        "MMMMM yyyy"));

        resumenTrafico.setTraficoVozFechaActualizacionFormated(getTraficoDateFormatted(resumenTrafico.getTraficoVozFechaActualizacion(),
                        "'al' dd MMMMM"));

        resumenTrafico.setTraficoVozFormated(JsfUtil.getAsConvertedString(
                resumenTrafico.getTraficoVoz(), "traficoVozMinSecConverter"));

        resumenTrafico
                .setTraficoMensajesFechaActualizacionFormated(getTraficoDateFormatted(resumenTrafico
                                .getTraficoMensajesFechaActualizacion(),
                                "'al' dd MMMMM"));

        //resumenTrafico.setTraficoInternetMovilFormated(JsfUtil.getAsConvertedString(resumenTrafico.getTraficoInternetMovil(), "traficoDatosConverter"));
        resumenTrafico.setTraficoInternetMovilFormated(JsfUtil.getAsConvertedString(resumenTrafico.getTraficoInternetMovil(), "traficoDatosConverterBTMB"));

        resumenTrafico
                .setTraficoInternetMovilFechaActualizacionFormated(getTraficoDateFormatted(
                                resumenTrafico
                                        .getTraficoInternetMovilFechaActualizacion(),
                                "'al' dd MMMMM"));

        //resumenTrafico.setTraficoBandaAnchaMovilFormated(JsfUtil.getAsConvertedString(resumenTrafico.getTraficoBandaAnchaMovil(),"traficoDatosConverter"));
        resumenTrafico.setTraficoBandaAnchaMovilFormated(JsfUtil.getAsConvertedString(resumenTrafico.getTraficoBandaAnchaMovil(),"traficoDatosConverterBTMB"));
       
        resumenTrafico
                .setTraficoBandaAnchaMovilFechaActualizacionFormated(getTraficoDateFormatted(
                                resumenTrafico
                                        .getTraficoBandaAnchaMovilFechaActualizacion(),
                                "'al' dd MMMMM"));

       /*resumenTrafico.setTraficoBlackberryFormated(JsfUtil
                .getAsConvertedString(
                        resumenTrafico.getTraficoBlackberry(),
                        "traficoDatosConverter"));*/
        
        resumenTrafico.setTraficoBlackberryFormated(JsfUtil
                .getAsConvertedString(
                        resumenTrafico.getTraficoBlackberry(),
                        "traficoDatosConverterBTMB"));

        resumenTrafico
                .setTraficoBlackberryFechaActualizacionFormated(getTraficoDateFormatted(resumenTrafico
                                .getTraficoBlackberryFechaActualizacion(),
                                "'al' dd MMMMM"));
        
		Long sumaTraficos = null;
		
		sumaTraficos = resumenTrafico.getTraficoInternetMovil()
				+ resumenTrafico.getTraficoBandaAnchaMovil()
				+ resumenTrafico.getTraficoBlackberry();

		resumenTrafico.setSumaTraficoDatosFormated(convertidorDatosTrafico(sumaTraficos));
        
    }
    
    private String getTraficoDateFormatted(Date date, String format) {
        
        if(date == null) {
            return "Sin datos de fecha";
        } else {
            return DateHelper.format(date, format);
        }
    }
    
    /**
     * @return the mercado
     */
    public String getMercado() {
        try{
            if( mercado == null ){
                ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(JSFPortletHelper.getRequest());
                mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");            
            }
        }catch (Exception e) {
        	LOGGER.error("Exception caught: "+e.getMessage(), e);
        }
        return mercado;
    }

    /**
     * @param mercado the mercado to set
     */
    public void setMercado(String mercado) {
        this.mercado = mercado;
    }     
    
    /**
     * @return the seguridadDelegate
     */
    public SeguridadDelegate getSeguridadDelegate() {
        return seguridadDelegate;
    }


    /**
     * @param seguridadDelegate the seguridadDelegate to set
     */
    public void setSeguridadDelegate(SeguridadDelegate seguridadDelegate) {
        this.seguridadDelegate = seguridadDelegate;
    }
    
    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del mercado como clave asociada al valor.
     * @return
     */
    public String getPageLabelTrafico(){
        try{
          ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            
         return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                 profileWrapper, "mercado"), null);
         
        }catch(Exception e){
        	CAJA_TRAFICO_LOGGER.error("No se ha podido obtener el pageLabel"+ e.getMessage(), e);
            return "";
        }
    
    }

    
    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * @return
     */
    public String getURLMiEntelV2() {
        String idp = "";
        String url = "";
        
        try {
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            idp = seguridadDelegate.consultarIDP(ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs"));
            url = MiEntelProperties.getProperty("miEntel.contextoV2");
            return (url.concat(idp).concat("&contexto="));
        } catch (DAOException e) {
            LOGGER.error("Error al obtener el IDP", e);
            return "";
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            return "";
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener la URL de MiEntelV2", e);
            return "";
        }
    } 
    
    
    /**
	 * Metodo consulta la informacion del un plan multimedia excedido
     * @author Wilson Brochero Munoz
     * @param  msisdn  
     * @param  tipoPlan  
     * @return PlanesMultimediaBean
     * @throws Exception
     * @throws ServiceException
     * @throws DAOException
     */
    private PlanesMultimediaBean consultarPlanMultimediaExcedido(String msisdn,String valorMBExcedido){    	
    	
		PlanesMultimediaBean planesMultimediaBean;
		planesMultimediaBean = new PlanesMultimediaBean();
		try {

			planesMultimediaBean = planDelegate
					.consultarPlanMultimediaExcedido(msisdn);
			
			if(planesMultimediaBean!=null){
								
				String velocidadPlan = planesMultimediaBean.getVelocidadPlan();
			    Double cuotaTraficoUtil = Double.valueOf(Utils.isEmptyString(planesMultimediaBean.getTotalTrafico() ) ? "0.0" : planesMultimediaBean.getTotalTrafico())/1024;			
			    Double valorTotalTrafico = Double.valueOf(Utils.isEmptyString(planesMultimediaBean.getCuotaTraficoUtil()) ? "0.0" : planesMultimediaBean.getCuotaTraficoUtil())/1024;
			    //Double valorMBExcedido = Double.valueOf(Utils.isEmptyString(planesMultimediaBean.getValorMBExcedido()) ? "0" : planesMultimediaBean.getValorMBExcedido());		
			    Double totalTrafico = Double.valueOf(Utils.isEmptyString(planesMultimediaBean.getTotalTrafico()) ? "0.0" : planesMultimediaBean.getTotalTrafico())/1024;					
			    Double valorTraficoExcedido = Double.valueOf(Utils.isEmptyString(planesMultimediaBean.getTraficoExcedido()) ? "0.0" : planesMultimediaBean.getTraficoExcedido())/1024;
			    
				Double promedioConsumo =0.0;
				Double promedioUtilImg =0.0;	
				
				if(valorTotalTrafico>=cuotaTraficoUtil){					
					promedioConsumo = Double.valueOf(cuotaTraficoUtil);
					promedioConsumo = (promedioConsumo / Double.valueOf(valorTotalTrafico)) * 100;
					promedioUtilImg = promedioConsumo - (promedioConsumo % 10);
				}else {
					promedioUtilImg=promedioConsumo=100.0;
					cuotaTraficoUtil = valorTotalTrafico;
				}
				
				if(valorTraficoExcedido==0){
					planesMultimediaBean.setTieneValorExcedido(false);
				 }

				try {
					Date fechaReferencia;
					fechaReferencia = DateHelper.parseDate(planesMultimediaBean
							.getFechaDiaMesFormat(),
							DateHelper.FORMAT_yyyyMMddhhmmss24HORAS);
					planesMultimediaBean
							.setFechaDiaMesFormat(DateHelper.format(
									fechaReferencia, DateHelper.FORMAT_DD_MMMM));
					planesMultimediaBean.setFechaDiaMesHoraFormat(DateHelper
							.format(fechaReferencia,
									DateHelper.FORMAT_DD_MMMM_HHmm));
				} catch (NumberFormatException nfe) {
					LOGGER.warn("Error formateando fecha Referencia :"
							+ planesMultimediaBean.getFechaDiaMesFormat()
							+ nfe.getMessage());
				}
				
				planesMultimediaBean.setVelocidadPlan(velocidadPlan);
				planesMultimediaBean.setCuotaTraficoUtil(Utils.formatUnDecimal(cuotaTraficoUtil));//Valor consumido en MB			
				planesMultimediaBean.setValorTotalTrafico(Utils.formatPuntos(String.valueOf(valorTotalTrafico), Locale.GERMAN));			
				planesMultimediaBean.setTraficoExcedido(Utils.formatUnDecimal(valorTraficoExcedido));
				planesMultimediaBean.setValorMBExcedido(Utils.formatPuntos(String.valueOf(valorMBExcedido), Locale.GERMAN));		
				planesMultimediaBean.setTotalTrafico(Utils.formatUnDecimal(totalTrafico));							
				planesMultimediaBean.setPromConsumo(Utils.formatUnDecimal(promedioConsumo));
				planesMultimediaBean.setPromConsumoSinDec(Utils.formatPuntos(String.valueOf(promedioUtilImg), Locale.GERMAN));
				
			}

		} catch (DAOException e) {
			CAJA_TRAFICO_LOGGER.error("DAOException caught: " + e.getMessage(),
					e);
			planesMultimediaBean = null;
		} catch (ServiceException e) {
			CAJA_TRAFICO_LOGGER.info("ServiceException caught msisdn: "
					+ msisdn + " - " + e.getCodigoRespuesta() + " - "
					+ e.getDescripcionRespuesta());
			planesMultimediaBean = null;

		} catch (Exception e) {
			CAJA_TRAFICO_LOGGER.error("Exception caught: " + e.getMessage(), e);

		}

		return planesMultimediaBean;
    }
    
    /**
     * Metodo privado utilitario para convertir datos de traficos  
     */
    private String convertidorDatosTrafico(Object value){  	

	    String unidad = null;   
    	Float trafico;
        String numeroFormato="";    
        try{         	
        	trafico = Float.parseFloat(value.toString());
            trafico  = trafico / BYTESAMEGAYTES;// pasar de byte a kb
            unidad = "MB";
            if(trafico >= 1024){
            	trafico  = trafico / BYTESAMEGAYTES;// pasar de kb a mb
            	unidad = "MB";
            	if(trafico >= 1024){
            		trafico  = trafico / BYTESAMEGAYTES;// pasar de mg a gb
            		unidad = "GB";
            	}
            }
			numeroFormato = format.format(trafico); 
        	if(numeroFormato!=null && numeroFormato!=""){
        		String numero[] = numeroFormato.split(",");        		
        		if(numero.length>1){
        			if(Integer.parseInt(numero[1].substring(1)) > 5){
        				if(Integer.parseInt(numero[1].substring(0,1)) != 9){
        					numeroFormato = numero[0]+","+(Integer.parseInt(numero[1].substring(0,1))+1);
        				}else{
        					numeroFormato = numero[0]+","+numero[1].substring(0,1);
        				}
        				
        			}else{
        				numeroFormato = numero[0]+","+numero[1].substring(0,1);
        			}
        		}else{
        			numeroFormato=numeroFormato+",0";
        		}
			}
        }catch (Exception e) {
            return "";
        }
        numeroFormato = numeroFormato + " " + unidad;
        
        return numeroFormato ; 
    }
    
}
