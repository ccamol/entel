/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.administracion.suscripciones.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.administracion.suscripciones.delegate.BolsasBAMCCPPDelegate;
import com.epcs.administracion.suscripciones.util.BolsasBAMCCPPHelper;
import com.epcs.bean.PlanBAMBean;
import com.epcs.bean.ResumenBolsasActivasBAMCCPP;
import com.epcs.bean.ResumenBolsasDisponiblesBAMCCPP;
import com.epcs.bean.ResumenTraficoBAMCCBean;
import com.epcs.bean.TransaccionGTMBean;
import com.epcs.billing.registrouso.delegate.TraficoBamCCDelegate;
import com.epcs.provision.suscripcion.datoskpibam.delegate.KPIBAMDelegate;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import java.util.concurrent.TimeUnit;
import com.epcs.recursoti.configuracion.DateHelper;
import com.esa.provision.suscripcion.datoskpibam.types.IngresarDatosKpiBAMResponseType;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class BolsasBAMCCPPController {
    
    /**
     * Logger para BolsasBAMCCPPController
     */
    private static final Logger LOGGER = Logger.getLogger(BolsasBAMCCPPController.class);
    private BolsasBAMCCPPDelegate bolsasBAMCCPPDelegate;
    private TraficoBamCCDelegate miTraficoBAMCCDelegate;
    private ResumenBolsasDisponiblesBAMCCPP bolsasDisponibles;
    private ResumenBolsasActivasBAMCCPP bolsasActivas;
    private PlanBAMBean planActual;
    private ResumenTraficoBAMCCBean traficoBAM;
    private String respuestaJson;
    private String esSAPC;
    
    private static final String SUBMERCADO_CCPP = MiEntelProperties.getProperty("miEntel.subMercadoCCPP");
    private static final String SUBMERCADO_FDT = MiEntelProperties.getProperty("miEntel.subMercadoFDT");

	public String getEsSAPC() {	
					
		try {
			
			ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());   
			String subMercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "subMercado");		        

	        if(subMercado.equals(SUBMERCADO_FDT) || subMercado.equals(SUBMERCADO_CCPP)){       	   
	        	return "SAPC";
	        }else{
	        	return "OPSC";
	        }
		
		} catch (Exception e) {
			LOGGER.error(e);
			return "";
		}   
          
	}

	public void setEsSAPC(String esSAPC) {
		this.esSAPC = esSAPC;
	}

	/**
     * @return the bolsasBAMCCPPDelegate
     */
    public BolsasBAMCCPPDelegate getBolsasBAMCCPPDelegate() {
        return bolsasBAMCCPPDelegate;
    }

    /**
     * @param bolsasBAMCCPPDelegate the bolsasBAMCCPPDelegate to set
     */
    public void setBolsasBAMCCPPDelegate(BolsasBAMCCPPDelegate bolsasBAMCCPPDelegate) {
        this.bolsasBAMCCPPDelegate = bolsasBAMCCPPDelegate;
    }

    /**
     * @return the miTraficoBAMCCDelegate
     */
    public TraficoBamCCDelegate getMiTraficoBAMCCDelegate() {
        return miTraficoBAMCCDelegate;
    }

    /**
     * @param miTraficoBAMCCDelegate the miTraficoBAMCCDelegate to set
     */
    public void setMiTraficoBAMCCDelegate(
            TraficoBamCCDelegate miTraficoBAMCCDelegate) {
        this.miTraficoBAMCCDelegate = miTraficoBAMCCDelegate;
    }

    /**
     * @return the bolsasDisponibles
     */
    public ResumenBolsasDisponiblesBAMCCPP getBolsasDisponibles() {
        return bolsasDisponibles;
    }

    /**
     * @return the bolsasActivas
     */
    public ResumenBolsasActivasBAMCCPP getBolsasActivas() {
        return bolsasActivas;
    }

    /**
     * @return the pageLabelRecarga
     */
    public String getPageLabelRecarga() {
        try {
            PortletPreferences prefs = JSFPortletHelper.getPreferencesObject();
            return JSFPortletHelper.getPreference(prefs, "pageRecarga", null);
        } catch (Exception e) {
            LOGGER.error("No se ha podido obtener el pageLabel " + e.getMessage());
            return "";
        }
    }

    /**
     * @return the respuestaJson
     */
    public String getRespuestaJson() {
        return respuestaJson;
    }

    public void init(PhaseEvent event) {
    	
        try {
            loadData();
        } catch (DAOException e) {
            LOGGER.error("Error al obtener datos de bolsas", e);
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener datos de bolsas", e);
        }
        
    }
    
    private void loadData() throws DAOException, ServiceException, Exception {    	
    	
        ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());    
                
        String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcs");
        String aaa = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
        String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");        
                  
        bolsasDisponibles = bolsasBAMCCPPDelegate.listarBolsasDisponibles(msisdn);
        bolsasActivas = bolsasBAMCCPPDelegate.listarBolsasActivas(msisdn);
        
        //Se indica al metodo que NO REGISTRE en el log de la caja de Facturacion del Dashboard 
    	boolean logCajaFacturacion = false;
        planActual = miTraficoBAMCCDelegate.obtenerPlanActualBAMSSCC(msisdn, mercado, aaa, logCajaFacturacion);
        traficoBAM = miTraficoBAMCCDelegate.consultarTraficoBAMCC(msisdn,logCajaFacturacion);
        
        BolsasBAMCCPPHelper.verificaTipoCompra(aaa, bolsasDisponibles, bolsasActivas, planActual, traficoBAM);
        
        
     
    }
    
    /**
     * Evento que realiza la compra de una bolsa
     * @param event
     */
    public void comprarBolsa(PhaseEvent event) {
    	    	 
    	String msisdn = "";    	
    	String mercado = "";
    	int estado = 1;
    	Date fechaInicio = new Date();
    	Date fechaFin = new Date();
        
    	try {
    		HttpServletRequest request = JSFPortletHelper.getRequest();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);               
        	msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcs");
        	mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
            String codBolsa = JsfUtil.getRequestParameter("codBolsa");
            String tipoCobro = JsfUtil.getRequestParameter("tipoCobro");
            String nombreBolsa = JsfUtil.getRequestParameter("nombreBolsa");
            String valorBolsa = JsfUtil.getRequestParameter("valorBolsa");
            
            String TIPOCOBRO_CCPP = MiEntelProperties.getProperty( ("miEntel.tipoCobro.").concat(tipoCobro) );
            
            //timestamp para el inicio de la compra
            fechaInicio = new Date();         
            
            //ejecutamos la compra
            bolsasBAMCCPPDelegate.comprarBolsa(msisdn, codBolsa, TIPOCOBRO_CCPP);      
                    
            TransaccionGTMBean transGTM = new TransaccionGTMBean();
            transGTM.setIdTransaccion(msisdn.substring(msisdn.length() - 4));
            transGTM.setSkuID(codBolsa);
            transGTM.setNombre(nombreBolsa);
            transGTM.setNuevoValor(valorBolsa);
            transGTM.setCostoOperacional(0);
            transGTM.setNumeroPlanes(1);
            transGTM.setNumeroOperaciones(1);
            transGTM.setValorTransaccion(Double.parseDouble(valorBolsa) + transGTM.getCostoOperacional());
            
            respuestaJson = JsonHelper.toJsonGTMResponse(transGTM, "Ok");
        } catch (DAOException e) {
        	estado = 0;
            LOGGER.error("Error al comprar bolsa", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al comprar la bolsa");
        } catch (ServiceException e) {
        	estado = 0;
            LOGGER.info("Error de servicio. Movil: "+msisdn);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al comprar la bolsa");
        } catch (Exception e) {
        	estado = 0;
            LOGGER.error("Exception inesperado al comprar bolsa", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al comprar la bolsa");
        }
        
        //timestamp para el fin de la compra
        fechaFin = new Date(); 
        
        //insertamos el KPI
        try{
        	this.insertarMarcaKPI(fechaInicio, fechaFin, mercado, estado, msisdn);
        }catch(Exception ee){
        	LOGGER.error("Error al insertar KPI", ee);
        }
    }
    
    public void insertarMarcaKPI(Date fechaInicio, Date fechaFin, String mercado, int estado, String msisdn){
    	KPIBAMDelegate delegadoKPI = new KPIBAMDelegate();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	
    	String codArea = MiEntelProperties.getProperty("kpi.codArea");
		String nombreUsuario = MiEntelProperties.getProperty("kpi.nombreUsuario"); 
		String codIndicador = MiEntelProperties.getProperty("kpi."+mercado+".codIndicador"); 	
		String fecha = sdf.format(fechaInicio);
		
		//si la compra esta ok => valor marca = OK, en caso contrario valor marca = NOK
		String valor =  estado == 1 ? MiEntelProperties.getProperty("kpi.valorOk"):MiEntelProperties.getProperty("kpi.valorNoOk");		   	
			
		try {		
			/*
			if(delegadoKPI.esMovilSAPC(msisdn)){	
				IngresarDatosKpiBAMResponseType respuesta = delegadoKPI.insertarKPIBAM(codArea, nombreUsuario, codIndicador, fecha, valor);			
				LOGGER.info("Resultado inserta KPI: [codigo=" + respuesta.getResponse().getCodigo() + "][descripcion=" + respuesta.getResponse().getDescripcion()+"]");
			}else{
				LOGGER.info("Resultado inserta KPI: No se inserta KPI. El movil no es del tipo SAPC");
			}
			*/
			IngresarDatosKpiBAMResponseType respuesta = delegadoKPI.insertarKPIBAM(codArea, nombreUsuario, codIndicador, fecha, valor);			
			LOGGER.info("Resultado inserta KPI: [codigo=" + respuesta.getResponse().getCodigo() + "][descripcion=" + respuesta.getResponse().getDescripcion()+"]");
		} catch (DAOException e) {
			LOGGER.error("Error al insertar KPI", e);
		} catch (ServiceException e) {
			LOGGER.error("Error al insertar KPI", e);
		}
		
		
    	
    }
    
    public void validarSubmercado(){
    	try {
			
			ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());   
			String subMercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "subMercado");		        

	        if(subMercado.equals(SUBMERCADO_FDT)){	        	
	        	
	        	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        	String uri = ec.getRequestContextPath() + "/portlet/bolsabam/comprarBolsasPPBAM.faces";
	        	ec.dispatch(uri);	       	
	        	
	        }
		
		} catch (Exception e) {
			LOGGER.error(e);			
		}   
    }
      
}
