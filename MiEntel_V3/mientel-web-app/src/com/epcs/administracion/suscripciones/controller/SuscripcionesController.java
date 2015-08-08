/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.administracion.suscripciones.controller;

import java.io.Serializable;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

import org.apache.log4j.Logger;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.administracion.suscripciones.delegate.SuscripcionesDelegate;
import com.epcs.bam.saldoReservado.delegate.SaldoReservadoDelegate;
import com.epcs.bean.BolsaPPBAMBean;
import com.epcs.bean.BolsaPPBean;
import com.epcs.bean.PlanResumenBAMPPBean;
import com.epcs.bean.ResumenPlanPPBAM;
import com.epcs.bean.SaldoBolsaPPBAMBean;
import com.epcs.bean.SaldoYBolsaDisponiblesCompraBAMBean;
import com.epcs.cliente.orden.dao.BolsaDAO;
import com.epcs.cliente.orden.delegate.BolsaDelegate;
import cl.tecnova.entel.homebampp.saldoreservado.SaldoCongeladoService.types.*;
import com.epcs.cliente.perfil.dao.PlanBAMDAO;
import com.epcs.cliente.perfil.delegate.PlanBAMDelegate;
import com.epcs.provision.suscripcion.datoskpibam.delegate.KPIBAMDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.esa.provision.suscripcion.datoskpibam.types.IngresarDatosKpiBAMResponseType;


/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class SuscripcionesController implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(SuscripcionesController.class);
    private static final Logger LOGGER_SCOB = Logger.getLogger("logSCOB");
    
    private static final String PARAMETER_FDT = "FDT";
    private String respuestaJson;
    private SuscripcionesDelegate suscripcionesDelegate;
    private BolsaDelegate bolsaDelegate;
    private String submercado ="";
    
    private String saldoRestante="Sin restricci&oacute;n";    
    private String saldoRestanteUnidad="";    
	private String saldoBolsa="";
	private String nombreBolsa="Sin bolsa activa";
	private String fechaExpiracion="";
    private String traficoGrafico="0";
    private String diasExpiracion="0";
    private String horasExpiracion="0";
    private String minutosExpiracion="0";
    private Boolean esBolsaConTrafico=false;
    private Boolean tieneBolsaActiva=false;
    private String vigenciaActivas="";
    
    private int count = 0;
    
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
	private String saldoReservadoFechaExpiracion;
	
	public String getSaldoReservadoMonto() {
		return saldoReservadoMonto;
	}

	public void setSaldoReservadoMonto(String saldoReservadoMonto) {
		this.saldoReservadoMonto = saldoReservadoMonto;
	}

	public String getSaldoReservadoFechaExpiracion() {
		return saldoReservadoFechaExpiracion;
	}

	public void setSaldoReservadoFechaExpiracion(
			String saldoReservadoFechaExpiracion) {
		this.saldoReservadoFechaExpiracion = saldoReservadoFechaExpiracion;
	}
	/**************************/
    
    
    public String getVigenciaActivas() {
		return vigenciaActivas;
	}


	public void setVigenciaActivas(String vigenciaActivas) {
		this.vigenciaActivas = vigenciaActivas;
	}


	public String getSaldoRestanteUnidad() {
		return saldoRestanteUnidad;
	}


	public void setSaldoRestanteUnidad(String saldoRestanteUnidad) {
		this.saldoRestanteUnidad = saldoRestanteUnidad;
	}

    public Boolean getEsBolsaConTrafico() {
		return esBolsaConTrafico;
	}


	public void setEsBolsaConTrafico(Boolean esBolsaConTrafico) {
		this.esBolsaConTrafico = esBolsaConTrafico;
	}
    
    
    public String getSaldoRestante() {
		return saldoRestante;
	}


	public void setSaldoRestante(String saldoRestante) {
		this.saldoRestante = saldoRestante;
	}


	public String getSaldoBolsa() {
		return saldoBolsa;
	}


	public void setSaldoBolsa(String saldoBolsa) {
		this.saldoBolsa = saldoBolsa;
	}


	public String getNombreBolsa() {
		return nombreBolsa;
	}


	public void setNombreBolsa(String nombreBolsa) {
		this.nombreBolsa = nombreBolsa;
	}


	public String getFechaExpiracion() {
		return fechaExpiracion;
	}


	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}


	public String getDiasExpiracion() {
		return diasExpiracion;
	}


	public void setDiasExpiracion(String diasExpiracion) {
		this.diasExpiracion = diasExpiracion;
	}


	public String getHorasExpiracion() {
		return horasExpiracion;
	}


	public void setHorasExpiracion(String horasExpiracion) {
		this.horasExpiracion = horasExpiracion;
	}


	public String getMinutosExpiracion() {
		return minutosExpiracion;
	}


	public void setMinutosExpiracion(String minutosExpiracion) {
		this.minutosExpiracion = minutosExpiracion;
	}

    private List<BolsaPPBean> bolsasActualesBAMPP;
    
	public String getTraficoGrafico() {
		return traficoGrafico;
	}


	public void setTraficoGrafico(String traficoGrafico) {
		this.traficoGrafico = traficoGrafico;
	}

    /**
     * @return the bolsaDelegate
     */
    public BolsaDelegate getBolsaDelegate() {
        return bolsaDelegate;
    }


    /**
     * @param bolsaDelegate the bolsaDelegate to set
     */
    public void setBolsaDelegate(final BolsaDelegate bolsaDelegate) {
        this.bolsaDelegate = bolsaDelegate;
    }


    private SaldoBolsaPPBAMBean saldoBolsaPPBAM;
    private SaldoYBolsaDisponiblesCompraBAMBean saldoYBolsaDisponiblesCompraBAMBean;
    private boolean saldoSuficiente;
    private List<BolsaPPBean> bolsasActivasBAMPP;    
    private boolean existenBolsasContratadas = false;
    
    /**
	 * @return the saldoSuficiente
	 */
	public boolean isSaldoSuficiente() {
		return saldoSuficiente;
	}


	/**
	 * @param saldoSuficiente the saldoSuficiente to set
	 */
	public void setSaldoSuficiente(boolean saldoSuficiente) {
		this.saldoSuficiente = saldoSuficiente;
	}


	/**
     * @return the suscripcionesDelegate
     */
    public SuscripcionesDelegate getSuscripcionesDelegate() {
        return suscripcionesDelegate;
    }


    /**
     * @param suscripcionesDelegate the suscripcionesDelegate to set
     */
    public void setSuscripcionesDelegate(final SuscripcionesDelegate suscripcionesDelegate) {
        this.suscripcionesDelegate = suscripcionesDelegate;
    }


    /**
     * 
     * @param event
     */
    public void initSaldoBolsaBAM(final PhaseEvent event){
        try{
            
            ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            final String numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
            profileWrapper, "numeroPcsSeleccionado");
            
            bolsaDelegate=new BolsaDelegate();
			bolsaDelegate.setBolsaDAO(new BolsaDAO());
			Long inicio = System.currentTimeMillis();
			bolsasActualesBAMPP = bolsaDelegate.obtenerBolsasActivasBAMScob(numeroPcsSeleccionado);			
			String txtLog="InstanciaServidor:"+InetAddress.getLocalHost().getHostAddress()+", "+"MOVIL:"+numeroPcsSeleccionado+", "+
            	"CANAL: WEBPPBAM, PAGINA: DASHBOARD, SERVICIO:listarBolsasActivasBAM";
			Long fin = System.currentTimeMillis();
	        txtLog=txtLog+", TIEMPO:"+(fin-inicio);
			LOGGER_SCOB.info(txtLog);
			List<BolsaPPBean> tempActivas=new ArrayList<BolsaPPBean>();
			
			for (int x=0;x<bolsasActualesBAMPP.size();x++){
				if (bolsasActualesBAMPP.get(x).getTipoBolsa().length()>4 &&  bolsasActualesBAMPP.get(x).getTipoBolsa().substring(0,5).equalsIgnoreCase("PPBAM")){
					if (!bolsasActualesBAMPP.get(x).getTipoBolsa().equalsIgnoreCase("PPBAM_FDT")){
            			tempActivas.add(bolsasActualesBAMPP.get(x));            			
            		}
				}
            }
			bolsasActualesBAMPP=tempActivas;			
            
			//Asumiremos la ultima bolsa como la bolsa activa...			
			if (bolsasActualesBAMPP.size()>0){		
				tieneBolsaActiva=true;
				//bolsasActualesBAMPP.get(0).setDescSaldo("ilimitada");
				if (bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getDescSaldo().compareToIgnoreCase("ilimitada")!=0){
					esBolsaConTrafico=true;
					saldoRestante=bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getDescSaldo()+"|2097152";
					saldoBolsa=saldoRestante.split("\\|")[1].split("KB")[0];
					saldoRestante=saldoRestante.split("\\|")[0].split("KB")[0];
					try{
						Long salBolsa=Long.parseLong(saldoBolsa.trim());
						Long resBolsa=Long.parseLong(saldoRestante.trim());
						Long porcentaje=resBolsa*100/salBolsa;
						Long trafico=porcentaje/20;
						trafico=trafico*20;
						traficoGrafico=trafico.toString();
						saldoRestante=trasformaUnidadCorrecta(resBolsa);						
						saldoRestanteUnidad=saldoRestante.split(" ")[1];
						saldoRestante=saldoRestante.split(" ")[0];						
						saldoBolsa=trasformaUnidadCorrecta(salBolsa);
					}catch(Exception e){
						traficoGrafico="100";
					}
				}else{
					traficoGrafico="100";					
					saldoRestante="&nbsp;";
					saldoRestanteUnidad="Sin restricci&oacute;n";			
				}
            
				nombreBolsa=bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getNombreBolsa();
				fechaExpiracion=DateHelper.format(bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getFechaExpiracion(), DateHelper.FORMAT_ddMMyyyy_SLASH);
				Long saldoTiempo= bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getFechaExpiracion().getTime() - (new java.util.Date()).getTime();
				saldoTiempo=saldoTiempo/1000;//Segundos
				Long dias=saldoTiempo/(24*60*60);//Dias
				diasExpiracion=dias.toString();
				saldoTiempo=saldoTiempo-(dias*(24*60*60));
				Long horas=saldoTiempo/(60*60);//Horas
				horasExpiracion=horas.toString();
				saldoTiempo=saldoTiempo-(horas*(60*60));
				Long minutos= saldoTiempo / 60;
				minutosExpiracion=minutos.toString();
			}
			saldoBolsaPPBAM=new SaldoBolsaPPBAMBean();
			saldoBolsaPPBAM.setNombreBolsa(nombreBolsa);
			saldoBolsaPPBAM.setDias(diasExpiracion);
			saldoBolsaPPBAM.setHoras(horasExpiracion);
			saldoBolsaPPBAM.setMinutos(minutosExpiracion);
			saldoBolsaPPBAM.setTraficoIncluido(saldoBolsa);
			saldoBolsaPPBAM.setTraficoRestante(saldoRestante);
			saldoBolsaPPBAM.setTraficoGrafico(traficoGrafico);
			saldoBolsaPPBAM.setTieneSaldo(esBolsaConTrafico.booleanValue());
			saldoBolsaPPBAM.setTieneBolsa(tieneBolsaActiva);
			saldoBolsaPPBAM.setTraficoRestanteUnidad(saldoRestanteUnidad);
			//saldoBolsaPPBAM.setVelocidadMaxima(velocidadMaxima);		
			
            
            setRespuestaJson(JsonHelper.toJsonResponse(saldoBolsaPPBAM));
            
        } catch (DAOException e) {
        	LOGGER.info("DAOException initSaldoBolsaBAM",e);
            setRespuestaJson(JsonHelper.toJsonErrorMessage("El servicio no est&aacute; disponible de momento." 
            + " Por favor intente m&aacute;s tarde"));
            
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            setRespuestaJson(JsonHelper.toJsonServiceErrorMessage("gestionBalance", e.getCodigoRespuesta()));
           
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener resumen facturacion", e);
            setRespuestaJson(JsonHelper.toJsonErrorMessage("Ha ocurrido un error inesperado. " 
                    + " Disculpe las molestias"));
        }
        
    }
    
    /**
     * 
     * @param event
     */
    public void initSaldoBolsasDisponiblesBAM(final PhaseEvent event){
        try{
            ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            final String numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
            profileWrapper, "numeroPcsSeleccionado");
            
            submercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "subMercado");
            Date vigencia=new Date();
            
            //Parametro cuando se entra por contexto.
            String fdt = JSFPortletHelper.getRequest().getParameter(PARAMETER_FDT);
            
            if(fdt != null && "1".equals(fdt)){
                JSFPortletHelper.getRequest().getSession().setAttribute(PARAMETER_FDT, fdt);
            }else{
                fdt = JSFPortletHelper.getRequest().getSession().getAttribute(PARAMETER_FDT) != null ? (String)JSFPortletHelper.getRequest().getSession().getAttribute(PARAMETER_FDT) : "0"; 
            }
            Long inicio = System.currentTimeMillis();
            saldoYBolsaDisponiblesCompraBAMBean = suscripcionesDelegate.consultarSaldoYBolsaDisponiblesCompraPPBAM(numeroPcsSeleccionado,fdt);
            String txtLog="InstanciaServidor:"+InetAddress.getLocalHost().getHostAddress()+", "+"MOVIL:"+numeroPcsSeleccionado+", "+
        	"CANAL: WEBPPBAM, PAGINA: BOLSAS, SERVICIO:listarBolsasDisponiblesBAM,listarBolsasActivasBAM";
			Long fin = System.currentTimeMillis();
	        txtLog=txtLog+", TIEMPO:"+(fin-inicio);
			LOGGER_SCOB.info(txtLog);
            
            List<BolsaPPBean> lista= saldoYBolsaDisponiblesCompraBAMBean.getBolsasDisponibles();
            List<BolsaPPBean> listaTemp= new ArrayList<BolsaPPBean>();
            
            for (int x=0;x<lista.size();x++){
            	if (!lista.get(x).getTipoBolsa().equalsIgnoreCase("PPBAM_FDT")){
            		listaTemp.add(lista.get(x));
            	}else{
            		//Existe parametro FDT
            		if(fdt != null && "1".equals(fdt)){
            			listaTemp.add(lista.get(x));
            		}
            	}
            }
            	
            //lista.clear();
        	saldoYBolsaDisponiblesCompraBAMBean.setBolsasDisponibles(listaTemp);
            //listaTemp.clear();            
            
            verificarSaldoSuficiente();
            
			bolsasActualesBAMPP = saldoYBolsaDisponiblesCompraBAMBean.getBolsasActivas();
			List<BolsaPPBean> tempActivas=new ArrayList<BolsaPPBean>();
            
			for (int x=0;x<bolsasActualesBAMPP.size();x++){
				if (bolsasActualesBAMPP.get(x).getTipoBolsa().length()>4 && bolsasActualesBAMPP.get(x).getTipoBolsa().substring(0,5).equalsIgnoreCase("PPBAM")){
					if (!bolsasActualesBAMPP.get(x).getTipoBolsa().equalsIgnoreCase("PPBAM_FDT")){
            			tempActivas.add(bolsasActualesBAMPP.get(x));
            			if (bolsasActualesBAMPP.get(x).getFechaExpiracion().after(vigencia))
            				vigencia=bolsasActualesBAMPP.get(x).getFechaExpiracion();
					}
				}
			}
			bolsasActualesBAMPP=tempActivas;
			
			//Asumiremos la primera bolsa como la bolsa activa...			
			if (bolsasActualesBAMPP.size()>0){		
				tieneBolsaActiva=true;
				if (bolsasActualesBAMPP.size()>1){
					java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy' a las 'HH:mm:ss");
					this.setVigenciaActivas(sdf.format(vigencia));
				}
				//bolsasActualesBAMPP.get(0).setDescSaldo("ilimitada");
				if (bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getDescSaldo().compareToIgnoreCase("ilimitada")!=0){
					esBolsaConTrafico=true;
					saldoRestante=bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getDescSaldo()+"|2097152";
					saldoBolsa=saldoRestante.split("\\|")[1].split("KB")[0];
					saldoRestante=saldoRestante.split("\\|")[0].split("KB")[0];
					try{
						Long salBolsa=Long.parseLong(saldoBolsa.trim());
						Long resBolsa=Long.parseLong(saldoRestante.trim());
						Long porcentaje=resBolsa*100/salBolsa;
						Long trafico=porcentaje/20;
						trafico=trafico*20;
						traficoGrafico=trafico.toString();
						saldoRestante=trasformaUnidadCorrecta(resBolsa);
						saldoRestanteUnidad=saldoRestante.split(" ")[1];
						saldoRestante=saldoRestante.split(" ")[0];		
						saldoBolsa=trasformaUnidadCorrecta(salBolsa);
					}catch(Exception e){
						traficoGrafico="100";
					}
				}else{
					traficoGrafico="100";
					saldoRestante="&nbsp;";
					saldoRestanteUnidad="Sin restricci&oacute;n";					
				}
								
				nombreBolsa=bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getNombreBolsa();
				fechaExpiracion=DateHelper.format(bolsasActualesBAMPP.get(0).getFechaExpiracion(), DateHelper.FORMAT_ddMMyyyy_SLASH);
				Long saldoTiempo= bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getFechaExpiracion().getTime() - (new java.util.Date()).getTime();
				saldoTiempo=saldoTiempo/1000;//Segundos
				Long dias=saldoTiempo/(24*60*60);//Dias
				diasExpiracion=dias.toString();
				saldoTiempo=saldoTiempo-(dias*(24*60*60));
				Long horas=saldoTiempo/(60*60);//Horas
				horasExpiracion=horas.toString();
				saldoTiempo=saldoTiempo-(horas*(60*60));
				Long minutos= saldoTiempo / 60;
				minutosExpiracion=minutos.toString();
			}
			
        }  catch (DAOException e) {
            LOGGER.error("Exception inesperado al comprar bolsa bampp", e);
            JSFMessagesHelper.addServiceErrorMessage("nocomprabolsa");
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("adminSuscripciones", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al comprar bolsa bampp", e);
            JSFMessagesHelper.addServiceErrorMessage("nocomprabolsa");
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
     * 
     * @param event
     */
    
    public void initSaldoBolsasBAMPP(final PhaseEvent event)
    {
    	LOGGER_SCOB.info("Before phase: " + event.getPhaseId());
    	
    	//LOGGER_SCOB.info("count: " + count);
    	
    	if((PhaseId.RENDER_RESPONSE == event.getPhaseId() || PhaseId.APPLY_REQUEST_VALUES == event.getPhaseId()) && count <= 1)
    	{
    		count++;
    		initSaldoBolsasBAMPP();	
    	}
    	
    	//initSaldoBolsasBAMPP();
    	
    }
     
    public void initSaldoBolsasBAMPP(){    	  	
    		
    		LOGGER_SCOB.info("Cargando pagina");
    		
	        try{
	        	ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
	            final String numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
	            
	            //TODO:Flag
	            //setInitFlag();
	            
	            
	            //obtener saldo reservado
	            try{
	            	String msisdnFormateado = numeroPcsSeleccionado.length() == 8 ? "569" + numeroPcsSeleccionado : numeroPcsSeleccionado;
	                saldoReservado = this.obtenerSaldoReservado(msisdnFormateado);          
	                                           
	                if(saldoReservado != null){
	                	if(saldoReservado.getAmount() != null){
	                		saldoReservadoMonto = Utils.formatMoney(saldoReservado.getAmount().doubleValue());
	                    	saldoReservadoFechaExpiracion = saldoReservado.getExpirationdate();
	                	}else{
	                		//el movil no tiene saldo reservado
	                		saldoReservadoMonto = "0";
	                    	saldoReservadoFechaExpiracion = "0";
	                	}
	                }else{
	                	//falla en el servicio
	                	saldoReservadoMonto = "error";
	                	saldoReservadoFechaExpiracion = "error";
	                }
	                
	                
	            }catch(Exception exc){
	            	LOGGER.error("Exception inesperado al obtener saldo reservado", exc);
	            }          
	                    
	            
	            submercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "subMercado");
	            Date vigencia=new Date();
	            
	            //Parametro cuando se entra por contexto.
	            String fdt = JSFPortletHelper.getRequest().getParameter(PARAMETER_FDT);
	            
	            if(fdt != null && "1".equals(fdt)){
	                JSFPortletHelper.getRequest().getSession().setAttribute(PARAMETER_FDT, fdt);
	            }else{
	                fdt = JSFPortletHelper.getRequest().getSession().getAttribute(PARAMETER_FDT) != null ? (String)JSFPortletHelper.getRequest().getSession().getAttribute(PARAMETER_FDT) : "0"; 
	            }
	            
	            
	            Long inicio = System.currentTimeMillis();
	           	           
            	saldoYBolsaDisponiblesCompraBAMBean = suscripcionesDelegate.consultarSaldoYBolsaDisponiblesCompraPPBAM(numeroPcsSeleccionado,fdt);
            
	            String txtLog="InstanciaServidor:"+InetAddress.getLocalHost().getHostAddress()+", "+"MOVIL:"+numeroPcsSeleccionado+", "+
	        	"CANAL: WEBPPBAM, PAGINA: BOLSAS, SERVICIO:listarBolsasDisponiblesBAM,listarBolsasActivasBAM";
				Long fin = System.currentTimeMillis();
		        txtLog=txtLog+", TIEMPO:"+(fin-inicio);
				LOGGER_SCOB.info(txtLog);	           	
	            
	            List<BolsaPPBean> lista= saldoYBolsaDisponiblesCompraBAMBean.getBolsasDisponibles();
	            List<BolsaPPBean> listaTemp= new ArrayList<BolsaPPBean>();
	            
	            for (int x=0;x<lista.size();x++){
	            	if (!lista.get(x).getTipoBolsa().equalsIgnoreCase("PPBAM_FDT")){
	            		listaTemp.add(lista.get(x));
	            	}else{
	            		//Existe parametro FDT
	            		if(fdt != null && "1".equals(fdt)){
	            			listaTemp.add(lista.get(x));
	            		}
	            	}
	            }


            	
            //lista.clear();
        	saldoYBolsaDisponiblesCompraBAMBean.setBolsasDisponibles(listaTemp);
            //listaTemp.clear();            
            
            verificarSaldoSuficiente();
            
			bolsasActualesBAMPP = saldoYBolsaDisponiblesCompraBAMBean.getBolsasActivas();
			
			String[] misbolsasbampp;
			
			String bolsasbampp = MiEntelProperties.getProperty("parametro.tipodebolsa");
			
			misbolsasbampp=bolsasbampp.split(",");
			
			HashSet<String> tipoBolsas = new HashSet<String>();
			
			for (int x=0;x<misbolsasbampp.length;x++){
				tipoBolsas.add(misbolsasbampp[x]);				
			}
									
			List<BolsaPPBean> tempBolsasActivas = new ArrayList<BolsaPPBean>();
			BolsaPPBean tempBolsa; 
			
			
			for (int x=0;x<bolsasActualesBAMPP.size();x++){
				//if (bolsasActualesBAMPP.get(x).getTipoBolsa().equals("PPBAM_SIMULADA")){
				if(bolsasActualesBAMPP.get(x).getTipoBolsa()!= null){
//					if(!bolsasActualesBAMPP.get(x).getTipoBolsa().isEmpty()){
					if(tipoBolsas.contains(bolsasActualesBAMPP.get(x).getTipoBolsa())){
						tempBolsa = new  BolsaPPBean();
						tempBolsa.setCantidadBolsa(bolsasActualesBAMPP.get(x).getCantidadBolsa());
						tempBolsa.setCaracteristicas(bolsasActualesBAMPP.get(x).getCaracteristicas());
						tempBolsa.setCodBolsa(bolsasActualesBAMPP.get(x).getCodBolsa());
						tempBolsa.setDescBolsa(bolsasActualesBAMPP.get(x).getDescBolsa());
						tempBolsa.setDescComercial(bolsasActualesBAMPP.get(x).getDescComercial());
						tempBolsa.setDescSaldo(bolsasActualesBAMPP.get(x).getDescSaldo());
						tempBolsa.setFechaExpiracion(bolsasActualesBAMPP.get(x).getFechaExpiracion());
						tempBolsa.setInstanciaBolsa(bolsasActualesBAMPP.get(x).getInstanciaBolsa());
						tempBolsa.setNombreBolsa(bolsasActualesBAMPP.get(x).getNombreBolsa());
						tempBolsa.setOrden(bolsasActualesBAMPP.get(x).getOrden());
						tempBolsa.setSaldo(bolsasActualesBAMPP.get(x).getSaldo());
						tempBolsa.setSaldoString(bolsasActualesBAMPP.get(x).getSaldoString());
						tempBolsa.setTipoBolsa(bolsasActualesBAMPP.get(x).getTipoBolsa());
						tempBolsa.setUnidad(bolsasActualesBAMPP.get(x).getUnidad());
						tempBolsa.setValorBolsa(bolsasActualesBAMPP.get(x).getValorBolsa());
						tempBolsasActivas.add(tempBolsa);
					}
				}
			}
			
			//bolsasActualesBAMPP.get(0).setDescSaldo("ilimitada");
			for (int x=0;x<tempBolsasActivas.size();x++){
				String saldoRestanteBolsa = "";
				if (tempBolsasActivas.get(x).getDescSaldo().compareToIgnoreCase("ilimitada")!=0){
					saldoRestanteBolsa=tempBolsasActivas.get(x).getDescSaldo();
					saldoRestanteBolsa=saldoRestanteBolsa.split("\\|")[0];
					saldoRestanteBolsa=saldoRestanteBolsa.split(" ")[0]; 
					Long resBolsa=Long.parseLong(saldoRestanteBolsa.trim());																					
					saldoRestanteBolsa=trasformaUnidadCorrecta(resBolsa);
					tempBolsasActivas.get(x).setDescSaldo(saldoRestanteBolsa);
				}else{
					tempBolsasActivas.get(x).setDescSaldo("Sin restricci\u00F3n");									
				}
			}
			
			Collections.reverse(tempBolsasActivas);
			bolsasActivasBAMPP = tempBolsasActivas;			
			existenBolsasContratadas = (bolsasActivasBAMPP != null && !bolsasActivasBAMPP
                    .isEmpty());
			
			List<BolsaPPBean> tempActivas=new ArrayList<BolsaPPBean>();
            
			for (int x=0;x<bolsasActualesBAMPP.size();x++){
				if (bolsasActualesBAMPP.get(x).getTipoBolsa().length()>4 && bolsasActualesBAMPP.get(x).getTipoBolsa().substring(0,5).equalsIgnoreCase("PPBAM")){
					if (!bolsasActualesBAMPP.get(x).getTipoBolsa().equalsIgnoreCase("PPBAM_FDT")){
            			tempActivas.add(bolsasActualesBAMPP.get(x));
            			if (bolsasActualesBAMPP.get(x).getFechaExpiracion().after(vigencia))
            				vigencia=bolsasActualesBAMPP.get(x).getFechaExpiracion();
					}
				}
			}
			bolsasActualesBAMPP=tempActivas;
			
			//Asumiremos la primera bolsa como la bolsa activa...			
			if (bolsasActualesBAMPP.size()>0){		
				tieneBolsaActiva=true;
				if (bolsasActualesBAMPP.size()>1){
					java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy' a las 'HH:mm:ss");
					this.setVigenciaActivas(sdf.format(vigencia));
				}
				//bolsasActualesBAMPP.get(0).setDescSaldo("ilimitada");
				if (bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getDescSaldo().compareToIgnoreCase("ilimitada")!=0){
					esBolsaConTrafico=true;
					saldoRestante=bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getDescSaldo()+"|2097152";
					saldoBolsa=saldoRestante.split("\\|")[1].split("KB")[0];
					saldoRestante=saldoRestante.split("\\|")[0].split("KB")[0];
					try{
						Long salBolsa=Long.parseLong(saldoBolsa.trim());
						Long resBolsa=Long.parseLong(saldoRestante.trim());
						Long porcentaje=resBolsa*100/salBolsa;
						Long trafico=porcentaje/20;
						trafico=trafico*20;
						traficoGrafico=trafico.toString();
						saldoRestante=trasformaUnidadCorrecta(resBolsa);
						saldoRestanteUnidad=saldoRestante.split(" ")[1];
						saldoRestante=saldoRestante.split(" ")[0];		
						saldoBolsa=trasformaUnidadCorrecta(salBolsa);
					}catch(Exception e){
						traficoGrafico="100";
					}
				}else{
					traficoGrafico="100";
					saldoRestante="&nbsp;";
					saldoRestanteUnidad="Sin restricci&oacute;n";					
				}
								
				nombreBolsa=bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getNombreBolsa();
				fechaExpiracion=DateHelper.format(bolsasActualesBAMPP.get(0).getFechaExpiracion(), DateHelper.FORMAT_ddMMyyyy_SLASH);
				Long saldoTiempo= bolsasActualesBAMPP.get(bolsasActualesBAMPP.size()-1).getFechaExpiracion().getTime() - (new java.util.Date()).getTime();
				saldoTiempo=saldoTiempo/1000;//Segundos
				Long dias=saldoTiempo/(24*60*60);//Dias
				diasExpiracion=dias.toString();
				saldoTiempo=saldoTiempo-(dias*(24*60*60));
				Long horas=saldoTiempo/(60*60);//Horas
				horasExpiracion=horas.toString();
				saldoTiempo=saldoTiempo-(horas*(60*60));
				Long minutos= saldoTiempo / 60;
				minutosExpiracion=minutos.toString();
			}
			
        }  catch (DAOException e) {
            LOGGER.error("Exception inesperado al comprar bolsa bampp", e);
            JSFMessagesHelper.addServiceErrorMessage("nocomprabolsa");
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("adminSuscripciones", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al comprar bolsa bampp", e);
            JSFMessagesHelper.addServiceErrorMessage("nocomprabolsa");
        }        
     
    }	
    
    public Boolean getTieneBolsaActiva() {
		return tieneBolsaActiva;
	}


	public void setTieneBolsaActiva(Boolean tieneBolsaActiva) {
		this.tieneBolsaActiva = tieneBolsaActiva;
	}


    /***
     * Function que cambia recibe KB y tranforma a formato con unidad     *  
     */
    private String trasformaUnidadCorrecta(Long cantidad){
    	String resultado="";
    	Double datos;
    	datos=cantidad.doubleValue();
    	DecimalFormat formateador = new DecimalFormat("###.#");
    	DecimalFormatSymbols dfs=new DecimalFormatSymbols(Locale.FRENCH);    	
    	formateador.setDecimalFormatSymbols(dfs);
    	if (datos<1000)
    		resultado=formateador.format(datos)+" KB";
    	datos=datos/1000;
    	if (datos>=1 &&datos<1000)
    		resultado=formateador.format(datos)+" MB";
    	datos=datos/1000;
    	if (datos>=1 &&datos<1000)
    		resultado=formateador.format(datos)+" GB";
    	return resultado;
    }
    
    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del mercado como clave asociada al valor.
     * @return
     */
    public String getPageLabelCompraBolsa(){
        try{
          ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
          
         String pageLabel = JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                 profileWrapper, "mercado"), null);
        
         return pageLabel;
         
        }catch(Exception e){
            LOGGER.error("No se ha podido obtener el pageLabel"+ e.getMessage());
            return "";
        }
    
    }
    
    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del mercado como clave asociada al valor.
     * @return
     */
    public String getPageLabelRecargaEnLinea(){
        try{
          ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            
          return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                 profileWrapper, "mercado"), null);
         
        }catch(Exception e){
            LOGGER.error("No se ha podido obtener el pageLabel "+ e.getMessage());
            return "";
        }
    }
    
    /**
     * Action para comprar una bolsa pp
     * @return "" postback
     */
    public String comprarBolsaPPBAM() {
    	int estado = 1;
    	Date fechaInicio = new Date();
    	Date fechaFin = new Date();
    	String mercado = "";    	

    	LOGGER_SCOB.info("Iniciado compra de bolsa BAM PP...");
    	
    	
        try {
            final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            final String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
            final String descuentoStr = MiEntelProperties.getProperty("parametros.comprarbolsa.descuento");
            mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado");
            final String cartaServicio = JsfUtil.getRequestParameter("cartaServicio");
            final String nombreBolsa = JsfUtil.getRequestParameter("nombreBolsa");
            final double valorBolsa = Double.parseDouble(JsfUtil.getRequestParameter("valorBolsa"));
            final double descuento = Double.parseDouble(descuentoStr);
            
            //Obtener informacion de saldo.            
            //ResumenPlanPPBAM resumenPlan = suscripcionesDelegate.consultarResumenPlanPPBAM(msisdn);         
        	
            /*
            if (resumenPlan != null) {
                // Se valida el saldo.
                if ((resumenPlan.getSaldoRecargas() + descuento) <= valorBolsa) {
                    JSFMessagesHelper.addServiceErrorMessage("saldoinsuficientepp");
                }
                else {
                	//timestamp para el inicio de la compra
                	fechaInicio = new Date();
                	
                	LOGGER.info("Llamada a Compra Bolsa BAMPP - Mercado:"+mercado+":msisdn:"+msisdn+":carta:"+cartaServicio);
                	Long inicio = System.currentTimeMillis();
                    bolsaDelegate.comprarBolsasScobBAMPP(msisdn, cartaServicio);
                    String txtLog="InstanciaServidor:"+InetAddress.getLocalHost().getHostAddress()+", "+"MOVIL:"+msisdn+", "+
                	"CANAL: WEBPPBAM, CARTA:"+cartaServicio+", PAGINA: BOLSAS, SERVICIO:ComprarBolsaBAMPP";
                    Long fin = System.currentTimeMillis();
        	        txtLog=txtLog+", TIEMPO:"+(fin-inicio);
        			LOGGER_SCOB.info(txtLog);                
                    Thread.sleep(500);
                    String[] args = {nombreBolsa};
                    
                    saldoYBolsaDisponiblesCompraBAMBean = suscripcionesDelegate.consultarSaldoYBolsaDisponiblesCompraPPBAM(msisdn,"0");
                    List<BolsaPPBean> lista= saldoYBolsaDisponiblesCompraBAMBean.getBolsasDisponibles();
                    boolean esCuota=false;
                    
                    for (BolsaPPBean bolsaPPBean : lista) {
						if (bolsaPPBean.getCodBolsa().equalsIgnoreCase(cartaServicio)){
							if (bolsaPPBean.getTipoBolsa().equalsIgnoreCase("PPBAM_CUOTA")){
								esCuota=true;
							}
						}                    	
					}
                    
                    if (!esCuota){
                    JSFMessagesHelper.addSuccessMessage("clienteOrden", "bolsacomprada", args);
                    }else{
                    	String msg="bolsaCuota:"+nombreBolsa;
                    	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
                    	FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
                    }
                }
            }else{
                JSFMessagesHelper.addServiceErrorMessage("noinfosaldo");
            }
            */
            
            //timestamp para el inicio de la compra
        	fechaInicio = new Date();
        	
        	LOGGER.info("Llamada a Compra Bolsa BAMPP - Mercado:"+mercado+":msisdn:"+msisdn+":carta:"+cartaServicio);
        	Long inicio = System.currentTimeMillis();
            int response = bolsaDelegate.comprarBolsasScobBAMPP(msisdn, cartaServicio);
        	            
            if(response==-1){
            	JSFMessagesHelper.addServiceErrorMessage("saldoinsuficientepp");
            }else{
            	String txtLog="InstanciaServidor:"+InetAddress.getLocalHost().getHostAddress()+", "+"MOVIL:"+msisdn+", "+
            	"CANAL: WEBPPBAM, CARTA:"+cartaServicio+", PAGINA: BOLSAS, SERVICIO:ComprarBolsaBAMPP";
            	Long fin = System.currentTimeMillis();
            	txtLog=txtLog+", TIEMPO:"+(fin-inicio);
            	LOGGER_SCOB.info(txtLog);                
            	Thread.sleep(500);
            	String[] args = {nombreBolsa};
            	
            	/*
            	saldoYBolsaDisponiblesCompraBAMBean = suscripcionesDelegate.consultarSaldoYBolsaDisponiblesCompraPPBAM(msisdn,"0");
            	List<BolsaPPBean> lista= saldoYBolsaDisponiblesCompraBAMBean.getBolsasDisponibles();
            	boolean esCuota=false;
            
            	for (BolsaPPBean bolsaPPBean : lista) {
            		if (bolsaPPBean.getCodBolsa().equalsIgnoreCase(cartaServicio)){
            			if (bolsaPPBean.getTipoBolsa().equalsIgnoreCase("PPBAM_CUOTA")){
            				esCuota=true;
            			}
            		}                    	
            	}
            	
            	
            	
            	if (!esCuota){
            		JSFMessagesHelper.addSuccessMessage("clienteOrden", "bolsacomprada", args);
            	}else{
            		String msg="bolsaCuota:"+nombreBolsa;
            		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
            		FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
            	}
            	*/
            	
            	JSFMessagesHelper.addSuccessMessage("clienteOrden", "bolsacomprada", args);
            }
            
        } catch (DAOException e) {
        	estado = 0;
            LOGGER.error("Exception inesperado al comprar bolsa bampp", e);
            JSFMessagesHelper.addServiceErrorMessage("nocomprabolsa");
        } catch (ServiceException e) {
        	estado = 0;
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("clienteOrden", e.getCodigoRespuesta());
        } catch (Exception e) {
        	estado = 0;
            LOGGER.error("Exception inesperado al comprar bolsa bampp", e);
            JSFMessagesHelper.addServiceErrorMessage("nocomprabolsa");
        }
        
        //timestamp para el fin de la compra
        fechaFin = new Date(); 
        
        //insertamos el KPI
        try{
        	this.insertarMarcaKPI(fechaInicio, fechaFin, mercado, estado);
        }catch(Exception ee){
        	LOGGER.error("Error al insertar KPI", ee);
        }
                
        return "";
    }
    
    public void insertarMarcaKPI(Date fechaInicio, Date fechaFin, String mercado, int estado){
    	KPIBAMDelegate delegadoKPI = new KPIBAMDelegate();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	//long tiempoTrasncurrido = estado == 1 ? TimeUnit.MILLISECONDS.toSeconds(fechaFin.getTime() - fechaInicio.getTime()) : 2000;
    	
    	String codArea = MiEntelProperties.getProperty("kpi.codArea");
		String nombreUsuario = MiEntelProperties.getProperty("kpi.nombreUsuario"); 
		String codIndicador = MiEntelProperties.getProperty("kpi."+mercado+".codIndicador"); 	
		String fecha = sdf.format(fechaInicio);
		
		//si la compra se demora max. 120 seg => Ok, en caso contrario NOK
		//String valor =  tiempoTrasncurrido <= 120 ? MiEntelProperties.getProperty("kpi.valorOk"):MiEntelProperties.getProperty("kpi.valorNoOk");
		String valor =  estado == 1 ? MiEntelProperties.getProperty("kpi.valorOk"):MiEntelProperties.getProperty("kpi.valorNoOk");
		   	
		try {
			IngresarDatosKpiBAMResponseType respuesta = delegadoKPI.insertarKPIBAM(codArea, nombreUsuario, codIndicador, fecha, valor);			
			LOGGER.info("Resultado inserta KPI: [codigo=" + respuesta.getResponse().getCodigo() + "][descripcion=" + respuesta.getResponse().getDescripcion()+"]");
		} catch (DAOException e) {
			LOGGER.error("Error al insertar KPI", e);
		} catch (ServiceException e) {
			LOGGER.error("Error al insertar KPI", e);
		}
    	
    }
    
    public void verificarSaldoSuficiente(){
    	List<BolsaPPBean> bolsas = saldoYBolsaDisponiblesCompraBAMBean.getBolsasDisponibles();
    	double saldoRecargas = saldoYBolsaDisponiblesCompraBAMBean.getSaldoRecargas();
    	try{
    		for(BolsaPPBean bolsaBAM : bolsas){
        		if(bolsaBAM.getValorBolsa() < saldoRecargas){
        			saldoSuficiente = true;
        			break;
        		}
        	}
    	}catch(NullPointerException npe){
    		LOGGER.error("Precio de bolsa invalido");
    	}
    	
    }
    
    public void doTeardown(final PhaseEvent event){
    	try {
    		final ProfileWrapper profileWrapper = ProfileWrapperHelper
        	.getProfile(JSFPortletHelper.getRequest());    	
			final String msisdn = ProfileWrapperHelper.getPropertyAsString(
			        profileWrapper, "numeroPcsSeleccionado");
			
			bolsaDelegate.ejectuarTeardown(msisdn);
			
		} catch (Exception e) {
			LOGGER.error("Problemas con el teardown.");
		}
    }
    

    /**
     * @param respuestaJson the respuestaJson to set
     */
    public void setRespuestaJson(final String respuestaJson) {
        this.respuestaJson = respuestaJson;
    }


    /**
     * @return the respuestaJson
     */
    public String getRespuestaJson() {
        return respuestaJson;
    }


    /**
     * @param saldoBolsaPPBAM the saldoBolsaPPBAM to set
     */
    public void setSaldoBolsaPPBAM(SaldoBolsaPPBAMBean saldoBolsaPPBAM) {
        this.saldoBolsaPPBAM = saldoBolsaPPBAM;
    }


    /**
     * @return the saldoBolsaPPBAM
     */
    public SaldoBolsaPPBAMBean getSaldoBolsaPPBAM() {
        return saldoBolsaPPBAM;
    }


    /**
     * @param saldoYBolsaDisponiblesCompraBAMBean the saldoYBolsaDisponiblesCompraBAMBean to set
     */
    public void setSaldoYBolsaDisponiblesCompraBAMBean(
            SaldoYBolsaDisponiblesCompraBAMBean saldoYBolsaDisponiblesCompraBAMBean) {
        this.saldoYBolsaDisponiblesCompraBAMBean = saldoYBolsaDisponiblesCompraBAMBean;
    }


    /**
     * @return the saldoYBolsaDisponiblesCompraBAMBean
     */
    public SaldoYBolsaDisponiblesCompraBAMBean getSaldoYBolsaDisponiblesCompraBAMBean() {
        return saldoYBolsaDisponiblesCompraBAMBean;
    }


	public String getSubmercado() {
		return submercado;
	}


	public void setSubmercado(String submercado) {
		this.submercado = submercado;
	}

	/**
     * @param existenBolsasContratadas the existenBolsasContratadas to set
     */
    public void setExistenBolsasContratadas(final boolean existenBolsasContratadas) {
        this.existenBolsasContratadas = existenBolsasContratadas;
    }
	
    public boolean isExistenBolsasContratadas() {
        return existenBolsasContratadas;
    }


	public List<BolsaPPBean> getBolsasActivasBAMPP() {
		return bolsasActivasBAMPP;
	}


	public void setBolsasActivasBAMPP(List<BolsaPPBean> bolsasActivasBAMPP) {
		this.bolsasActivasBAMPP = bolsasActivasBAMPP;
	}
    
    
    
}
