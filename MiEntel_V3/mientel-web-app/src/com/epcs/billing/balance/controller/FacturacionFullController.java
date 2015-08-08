/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.balance.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.activation.URLDataSource;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.bea.portlet.GenericURL;
import com.bea.portlet.PageURL;
import com.epcs.bean.DocumentoFacturaFullBean;
import com.epcs.bean.FacturacionFullBean;
import com.epcs.bean.ResultadoConsultarDetalleCuenta;
import com.epcs.bean.ResumenFacturacionBean;
import com.epcs.billing.balance.delegate.FacturacionDelegate;
import com.epcs.billing.balance.util.ArcFour;
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

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class FacturacionFullController implements Serializable{

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
    .getLogger(FacturacionFullController.class);
    
    private FacturacionFullBean facturacionFullBean;
    
    private ResultadoConsultarDetalleCuenta resutadoConsultarDetalleCuentas;
    
    private FacturacionDelegate facturacionDelegate; 
    
    private String urlImagenFactura;
    
    private String AAA;
    
    private String mercado;
    
    private String subMercado;
    
    private String mensajeUsuarioNoAutorizado;
    
    private ResumenFacturacionBean resumenFacturacionBean;
    
    private String  urlFactura = "";
    
    private String urlFacturaContexto = "";
    
    private String urlDetalleLlammadosJson="";
    
    private  String titular = MiEntelProperties.getProperty("aaa.titular.code");
    
    private String ctxECuentaDetalle = MiEntelProperties.getProperty("ecuenta.detalle.contexto");
    private String ctxECuentaBoleta = MiEntelProperties.getProperty("ecuenta.boleta.contexto");
    
    String numIntervalosGrafico="";
    
    StringBuilder mesFacturas;
    
    StringBuilder montoFacturas;
    
    String mesesFacturas;
    
    String montosFacturas;
    
    /**
     * Parametros del contexto que identifican el detalle de llamados
     */
    private String folioFromContexto;
    private String tipoDocFromContexto;
    
    
    String origenCliente = "";   
    
    /**
     * Metodo de inicializacion de controller
     * 
     * @return
     */
    public void init(PhaseEvent event) {
        
        try {
            loadData();
        } catch (DAOException e) {
            
        } catch (ServiceException e) {
            LOGGER.error("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
           
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener resumen facturacion", e);
        }
    }
    
    private void loadData() throws DAOException, ServiceException, Exception {
    	String numero = "";
        try{
        
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            numero = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
            String rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutTitular");
            String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroCuenta");
            
            //Parametros del contexto
            String tipoDoc = JSFPortletHelper.getRequest().getParameter("tipoDoc") != null 
									? JSFPortletHelper.getRequest().getParameter("tipoDoc") : "";
            folioFromContexto = JSFPortletHelper.getRequest().getParameter("folio") != null 
            						? JSFPortletHelper.getRequest().getParameter("folio") : "";
            tipoDocFromContexto = tipoDoc.equalsIgnoreCase("BS") ? "B" : "D";
            
            String context = JSFPortletHelper.getRequest().getParameter("contexto") != null 
			? JSFPortletHelper.getRequest().getParameter("contexto") : "";
			
			
            

            
            AAA = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
            mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
            subMercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"subMercado");
            
            facturacionFullBean = facturacionDelegate.getFacturacionFull(numero, rutTitular);
            
            ArrayList<DocumentoFacturaFullBean> dfull = new ArrayList<DocumentoFacturaFullBean>(facturacionFullBean.getDocumentosFacturasFull());
            
            StringBuilder telMovil = new StringBuilder();
            StringBuilder servAdicionales = new StringBuilder();
            StringBuilder otros = new StringBuilder();
            StringBuilder meses = new StringBuilder();
            String folio ="";
            String tdoc  ="";    
            
            mesFacturas = new StringBuilder();
            montoFacturas =  new StringBuilder();
                        
            for( DocumentoFacturaFullBean df : dfull ){           
                Date emision = df.getFechaEmision();
                if( emision != null ){
                       meses.append("'").append( 
                               DateHelper.getMonthName(emision).toUpperCase()
                               ).append("'").append(" ");
                }            
                telMovil.append(df.getMontoTelefoniaMovil()).append(" ");
                servAdicionales.append(df.getMontoServAdicional()).append(" ");
                otros.append(df.getMontoCobrosDescuentos()).append(" ");
                mesFacturas.append(df.getMesFactura()).append(" ");
                montoFacturas.append(df.getMontoString()).append(" ");
                
                
                String periodoMes = DateHelper.format(facturacionFullBean.getDetalleFacturaMes().getFechaPeriodoMes(), "MM/yyyy");
                
                if(df.getFechaPeriodo().equals(periodoMes)){
                	folio = df.getFolio();
                	tdoc  = df.getTipo();
                }                
                 
            }
            
            facturacionFullBean.setDataTelefoniaMovil( telMovil.toString().trim().replace(" ", ",") );
            facturacionFullBean.setDataServiciosAdicionales( servAdicionales.toString().trim().replace(" ", ",") );
            facturacionFullBean.setDataOtros( otros.toString().trim().replace(" ", ",") );
            facturacionFullBean.setDataMeses( meses.toString().trim().replace(" ", ",") );
            
            //resumenFacturacionBean = facturacionDelegate.getResumenFacturacion(numero, rutTitular, numeroCuenta);                       
            resumenFacturacionBean = new ResumenFacturacionBean();
            resumenFacturacionBean.setFolio(folio);
            resumenFacturacionBean.setTipoDocumento(tdoc);
            
            
            mesesFacturas  = mesFacturas.toString().trim().replace(" ", ",");
            montosFacturas = montoFacturas.toString().trim().replace(" ", ",");
            
            
            if(folio!=null && tdoc!=null){
                if(context != null && context.equalsIgnoreCase(ctxECuentaDetalle)
                		|| context.equalsIgnoreCase(ctxECuentaBoleta)){
                	this.urlFacturaContexto = this.urlFactura(folio.toString(), tdoc.toString(), context);
                }
                this.urlFactura = this.urlFactura(folio.toString(),tdoc.toString());
            }
            
            
            numIntervalosGrafico = MiEntelProperties.getProperty("facturacion.resumen.grafico.numIntervalos");
                        
        }
        catch (DAOException e) {
        	LOGGER.error("DAOException al obtener resumen facturacion", e);
            facturacionFullBean = null;
            JSFMessagesHelper.addErrorCodeMessage("general", "0001");
        }
        catch (ServiceException e) {
        	LOGGER.info("ServiceException msisdn:" + numero + "\n codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            facturacionFullBean = null;
            JSFMessagesHelper.addErrorMessage(e.getDescripcionRespuesta());
        }
        catch (Exception e) {
        	LOGGER.error("Exception inesperado al obtener resumen facturacion", e);
            facturacionFullBean = null;
            JSFMessagesHelper.addErrorCodeMessage("general", "0001");
        }
        
    }
    
    /**
     * 
     * @param numeroPCS
     * @param folio
     * @param tipo
     * @param periodo
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @throws Exception
     */
    
    public void getDetalleFactura(PhaseEvent event){        
    	
    	String numeroPCS = "";
        try{
            
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            
            numeroPCS = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
            String folio = JsfUtil.getRequestParameter("folio");
            String tipo = JsfUtil.getRequestParameter("tipo");
            String periodo = DateHelper.format(DateHelper.parseDate(JsfUtil.getRequestParameter("periodo"), "MM/yyyy"), "yyyyMM");
            
            urlImagenFactura = facturacionDelegate.getUrlImagenFactura(numeroPCS, folio, tipo, periodo);
        
        }
        catch (DAOException e) {
        	LOGGER.error("DAOException al obtener detalle de la factura", e);
            JSFMessagesHelper.addErrorMessage("0001");
        }
        catch (ServiceException e) {
        	LOGGER.info("ServiceException al obtener resumen facturacion. msisdn: " + numeroPCS);
            facturacionFullBean = null;
            JSFMessagesHelper.addErrorCodeMessage("gestionBalance", e.getCodigoRespuesta());
        }
        catch (Exception e) {
        	LOGGER.error("Exception inesperado al obtener resumen facturacion", e);
            JSFMessagesHelper.addErrorMessage("0001");
        }
    
    }
    
    public String toLightBox(){
        return "success_box";
    }
    
    /**
     * @return the urlImagenFactura
     */
    public String getUrlImagenFactura() {
        return urlImagenFactura;   
    }
    
    /**
     * @return the facturacionFullBean
     */
    public FacturacionFullBean getFacturacionFullBean() {
        return facturacionFullBean;
    }

    /**
     * @param facturacionFullBean the facturacionFullBean to set
     */
    public void setFacturacionFullBean(FacturacionFullBean facturacionFullBean) {
        this.facturacionFullBean = facturacionFullBean;
    }

    /**
     * @return the facturacionDelegate
     */
    public FacturacionDelegate getFacturacionDelegate() {
        return facturacionDelegate;
    }
    
    /**
     * @param facturacionDelegate the facturacionDelegate to set
     */
    public void setFacturacionDelegate(FacturacionDelegate facturacionDelegate) {
        this.facturacionDelegate = facturacionDelegate;
    }

    /**
     * @return the aAA
     */
    public String getAAA() {
        return AAA;
    }

    /**
     * @param aAA the aAA to set
     */
    public void setAAA(String aAA) {
        AAA = aAA;
    }

    /**
     * @return the mensajeUsuarioNoAutorizado
     */
    public String getMensajeUsuarioNoAutorizado() {
        String mensaje = "";
        try{
         mensaje = MiEntelProperties
        .getProperty("solo.usuario.titular");
        }catch (Exception e) {
            LOGGER.error("Erro al obtener propiedad [solo.usuario.tituar]",e);
        }
        return mensaje;  
    }

    /**
     * 
     * @return String
     */
    public String getMensajeUsuarioAutorizado() {
        return this.mensajeUsuarioNoAutorizado;
    }
    
    
    /**
     * @param mensajeUsuarioNoAutorizado the mensajeUsuarioNoAutorizado to set
     */
    public void setMensajeUsuarioNoAutorizado(String mensajeUsuarioNoAutorizado) {
        this.mensajeUsuarioNoAutorizado = mensajeUsuarioNoAutorizado;
    }

    /**
     * @return the mercado
     */
    public String getMercado() {
        return mercado;
    }

    /**
     * @param mercado the mercado to set
     */
    public void setMercado(String mercado) {
        this.mercado = mercado;
    }
	
	/**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del mercado como clave asociada al valor.
     * @return
     */
    public String getPageLabelPagoEnLinea(){
    	try{
    	  ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
    		
    	  return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "mercado"), null);
		 
        }catch(Exception e){
        	LOGGER.error("No se ha podido obtener el pageLabel"+ e.getMessage(), e);
        	return "";
        }
    }

    /**
     * @return the resumenFacturacionBean
     */
    public ResumenFacturacionBean getResumenFacturacionBean() {
        return resumenFacturacionBean;
    }  
    
    public String getUrlFacturaHistorial(){
    	try{    		
    		  String folio     = JsfUtil.getRequestParameter("folio");
			  String tipodoc   = JsfUtil.getRequestParameter("tipoDoc");
    		 this.urlFactura = this.urlFactura(folio,tipodoc);
		    }catch(Exception e){
		    	LOGGER.error("No se ha podido obtener la url de la factura y/o detalle"+ e.getMessage(), e);
		    	return "";
		    }
		    return  this.urlFactura ;
    }
    
    public String urlFactura(String idFolio, String idTipoDoc){
    	return urlFactura(idFolio, idTipoDoc, "");
    }
    
    public String urlFactura(String idFolio , String idTipoDoc, String ctx) {
    	String paramEncrip="";
    	String numeroCuenta="";
		  try {
			  
			   String folio    = idFolio ;
			   String tipodoc  = idTipoDoc;	
			   ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
			   numeroCuenta = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroCuenta");
			   String mercado  = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado");
			   String flag     = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "flagBam");
			   AAA = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
			   if(flag!=null)
			   flag = flag.equals("1")?"bam":"";			   
			  
			   String pageLabel ="";
			   //String prefParam ="";
			   
			   pageLabel  = MiEntelProperties.getExternalAppsProperty("contexto.pagoEnLinea."+flag+mercado.toLowerCase());
			   //prefParam  = MiEntelProperties.getProperty("parametros.factura.prefParam");
			   
			   FacesContext fc = FacesContext.getCurrentInstance();			 
			   HttpServletRequest  request = JSFPortletHelper.getRequest(fc);      
	           HttpServletResponse response = JSFPortletHelper.getResponse(); 
	           
	           PageURL url = PageURL.createPageURL(request, response, pageLabel, false);
	           url.setTemplate("page-label");
	           url.setForcedAmpForm(false);
	           url.addParameter(PageURL.LOADSTATE_PARAM, "false");
	           
	           //urlPagoEnLinea = urlPagoEnLinea + "?_nfpb=true"+prefParam+GenericURL.PAGE_LABEL_PARAM+"="+pageLabel.trim()+prefParam+GenericURL.LOADSTATE_PARAM+"=false";
	           			    
			   resutadoConsultarDetalleCuentas = facturacionDelegate.getDetalleDocumento(numeroCuenta, folio); 
			  
			   String numeroPCS = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
			   
			   boolean detalleLlamado = false;
			   detalleLlamado = this.facturacionDelegate.obtenerBloqueoDetalleLlamado(numeroPCS);			   
			   
			   String llaveEncriptacion = MiEntelProperties.getProperty("parametros.factura.llaveEncriptacion");
							
			   String docguid = resutadoConsultarDetalleCuentas.getDocumentoGUID();
	           String canpag  = (detalleLlamado)?resutadoConsultarDetalleCuentas.getCantidadPaginas():"1"; //(usuarioSesion.isPrivadoLlamados())?"1":Integer.toString(facCuenta.getCantidadPaginas());
	           String origen  = MiEntelProperties.getProperty("parametros.factura.origen."+flag+mercado.toLowerCase());
	           String pagActualDetalle = MiEntelProperties.getProperty("parametros.factura.paginaActual.detalle");
	           String pagActualBoleta = MiEntelProperties.getProperty("parametros.factura.paginaActual.boleta");
	           String ctxPagina = "";
	           
	           canpag = canpag ==null?"1":canpag;
	           String urlPago = url.toString(false);
			   LOGGER.info("URL Pago en Linea -> " + urlPago);
	         
	           String movil = (!AAA.equals(titular) && !AAA.equals("") )? numeroPCS : "";
			   		
	           if(ctx != null & !ctx.equalsIgnoreCase("")){
	        	   if(ctx.equalsIgnoreCase(ctxECuentaDetalle)){
	        		   if(AAA.equalsIgnoreCase(titular)){
	        		       //movil = numeroPCS;
	        		       ctxPagina = "&pagina=" + pagActualDetalle;
	        		   }
	        	   }else{
	        		   ctxPagina = "&pagina=" + pagActualBoleta;
	        	   }
	           }
	           
	           String parametros="documentoGuid=" + docguid + "&canpag=" + canpag + ctxPagina + "&pagada=&origen=" + origen +  "&cuenta=&folio=" + folio + "&urlPago="+ urlPago + "&tipoDoc=" + tipodoc + "&movil="+movil;
	           

	           
			   
				
			   paramEncrip = MiEntelProperties.getProperty("parametros.factura.urlImagenEcuentas");
			   paramEncrip = paramEncrip + Utils.encriptar(parametros, llaveEncriptacion);
			   
	           if(ctx != null & !ctx.equalsIgnoreCase("") ){
	        	   if(ctx.equalsIgnoreCase(ctxECuentaDetalle) || ctx.equalsIgnoreCase(ctxECuentaBoleta)){
	        		   if(AAA.equalsIgnoreCase("0")){
	        		       paramEncrip = "";
	        		   }
	        	   }
	           }
			   
		        
      }catch (DAOException e) {
      	 LOGGER.error("DAOException al obtener detalle de la factura", e);         
	  }
	  catch (ServiceException e) {
    	 LOGGER.info("ServiceException al obtener resumen facturacion. Numero de cuenta: "+numeroCuenta);        
	  } 
	  catch (Exception e) {    	  
         LOGGER.error("No se ha podido obtener la url de la factura y/o detalle", e);        
      }      
      return paramEncrip;
	}  
    
    
    
    public String getUrlFactura() {
		return urlFactura;
	}

	public void setUrlFactura(String urlFactura) {
		this.urlFactura = urlFactura;
	}
	
    public String getUrlFacturaContexto() {
		return urlFacturaContexto;
	}

	public void setUrlFacturaContextp(String urlFacturaContexto) {
		this.urlFacturaContexto = urlFacturaContexto;
	}

    /**
	 * @return the folioFromContexto
	 */
	public String getFolioFromContexto() {
		return folioFromContexto;
	}

	/**
	 * @return the tipoDocFromContexto
	 */
	public String getTipoDocFromContexto() {
		return tipoDocFromContexto;
	}

	/**
     * @return the subMercado
     */
    public String getSubMercado() {
        return subMercado;
    }
    
    public String getUrlDetalleLlammadosJson() {
		try {
			String folio = JsfUtil.getRequestParameter("folio");
			String tipodoc = JsfUtil.getRequestParameter("tipoDoc");
			this.urlDetalleLlammadosJson = this.urlFactura(folio, tipodoc);
		} catch (Exception e) {
			LOGGER.error(
					"No se ha podido obtener la url de la factura y/o detalle"
							+ e.getMessage(), e);
			return "";
		}
		urlDetalleLlammadosJson = JsonHelper.toJsonResponse(urlDetalleLlammadosJson);
		return urlDetalleLlammadosJson;
	}

	public void setUrlDetalleLlammadosJson(String urlDetalleLlammadosJson) {
		this.urlDetalleLlammadosJson = urlDetalleLlammadosJson;
	}

	/**
	 * @return the numIntervalosGrafico
	 */
	public String getNumIntervalosGrafico() {
		return numIntervalosGrafico;
	}

	/**
	 * @param numIntervalosGrafico the numIntervalosGrafico to set
	 */
	public void setNumIntervalosGrafico(String numIntervalosGrafico) {
		this.numIntervalosGrafico = numIntervalosGrafico;
	}

	/**
	 * @return the mesFacturas
	 */
	public StringBuilder getMesFacturas() {
		return mesFacturas;
	}

	/**
	 * @param mesFacturas the mesFacturas to set
	 */
	public void setMesFacturas(StringBuilder mesFacturas) {
		this.mesFacturas = mesFacturas;
	}

	/**
	 * @return the montoFacturas
	 */
	public StringBuilder getMontoFacturas() {
		return montoFacturas;
	}

	/**
	 * @param montoFacturas the montoFacturas to set
	 */
	public void setMontoFacturas(StringBuilder montoFacturas) {
		this.montoFacturas = montoFacturas;
	}

	/**
	 * @return the mesesFacturas
	 */
	public String getMesesFacturas() {
		return mesesFacturas;
	}

	/**
	 * @param mesesFacturas the mesesFacturas to set
	 */
	public void setMesesFacturas(String mesesFacturas) {
		this.mesesFacturas = mesesFacturas;
	}

	/**
	 * @return the montosFacturas
	 */
	public String getMontosFacturas() {
		return montosFacturas;
	}

	/**
	 * @param montosFacturas the montosFacturas to set
	 */
	public void setMontosFacturas(String montosFacturas) {
		this.montosFacturas = montosFacturas;
	}

		
	
	
}
