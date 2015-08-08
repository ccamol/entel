/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.recarga.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.CodeDescBean;
import com.epcs.bean.FactibilidadRecargaEntelTicketBean;
import com.epcs.bean.FactibilidadRecargaMultitiendaBean;
import com.epcs.bean.FactibilidadRecargaWebPayBean;
import com.epcs.bean.MultitiendaBean;
import com.epcs.bean.PagoWebPayBean;
import com.epcs.bean.PromoRecargaWebPayBean;
import com.epcs.bean.RecargaEntelTicketBean;
import com.epcs.bean.RecargaMultitiendaBean;
import com.epcs.bean.RecargaWebPayBean;
import com.epcs.billing.recarga.delegate.RecargaDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.WordNumberHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * Controller para el portlet de Recargas
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class RecargaController implements Serializable {   
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para RecargaController
     */
    private static final Logger LOGGER = Logger
            .getLogger(RecargaController.class);
    
    /**
     * Delegado de negocio
     */
    private RecargaDelegate recargaDelegate;

    private String tipoRecarga;
    
    private SelectItem[] tiposRecarga;
    
    /**
     * numero a quien se realiza la recarga
     */
    private String numeroPcs;

    private String nombreMultitienda;
    
    private FactibilidadRecargaMultitiendaBean recargaMultitiendaBean;
    
    // ---------------------------------- Recargas Entel Ticket
    private String numeroEntelTicket;

    private String confirmacionEntelTicket;    
    
    private FactibilidadRecargaEntelTicketBean facibilidadEntelticketBean;

    // ---------------------------------- Recargas Tarjetas

    //Multitienda Seleccionada
    private String multitienda;
    
    // Multitiendas
    private SelectItem[] multitiendas;

    private String numeroTarjetaMultitienda;

    private String claveTarjetaMultitienda;

    // --------------------------------- Recargas Tarjeta credito
    private PagoWebPayBean pagoWebPay;

    private RecargaWebPayBean recargaWebPay = new RecargaWebPayBean();
    
    private SelectItem[] montosRecargaTarjetaCredito;
    
    // Multitiendas y tarjetas de credito
    private String montoRecarga;

    
    private String cuotas;
    
    private String fechaActual;
    
    // Beans de recarga       
    
    private FactibilidadRecargaMultitiendaBean factibilidadMultitienda;
    
    private RecargaMultitiendaBean factibilidadRecarga;
    
    private String indicativoTelefono;
    
    private String fechaActualFormat;
    
    private String productoRecargar;
    
    private String mercado;
    
    // Elige tu Promo Recarga
    private String idPromoRecarga;
    private String descPromoRecarga;
    private String mensajePromoRecarga;    
    private PromoRecargaWebPayBean promoRecarga;
    private boolean eligeTuPromo;
 
    /**
     * Default constructor
     */
    public RecargaController() {
        super();
    }

    // ----------------------------------- Getters and setters


    /**
     * @return the tipoRecarga
     */
    public String getTipoRecarga() {
        return tipoRecarga;
    }
    
    /**
     * @param tipoRecarga the tipoRecarga to set
     */
    public void setTipoRecarga(String tipoRecarga) {
        this.tipoRecarga = tipoRecarga;
    }
    
    /**
     * @return the tiposRecarga
     */
    public SelectItem[] getTiposRecarga() {

        if (tiposRecarga == null) {
            List<CodeDescBean> tiposRecargaList = ParametrosHelper
                    .getTiposRecargaList();
            tiposRecarga = JsfUtil.getSelectItemsCodeDesc(tiposRecargaList,
                    false);
        }

        return tiposRecarga;
    }

    /**
     * @param tiposRecarga the tiposRecarga to set
     */
    public void setTiposRecarga(SelectItem[] tiposRecarga) {
        this.tiposRecarga = tiposRecarga;
    }
    
    /**
     * @return the recargaDelegate
     */
    public RecargaDelegate getRecargaDelegate() {
        return recargaDelegate;
    }

    /**
     * @param recargaDelegate the recargaDelegate to set
     */
    public void setRecargaDelegate(RecargaDelegate recargaDelegate) {
        this.recargaDelegate = recargaDelegate;
    }
    
    /**
     * @return the numeroPcs
     */
    public String getNumeroPcs() {
        return numeroPcs;
    }

    /**
     * @param numeroPcs
     *            the numeroPcs to set
     */
    public void setNumeroPcs(String numeroPcs) {
        this.numeroPcs = numeroPcs;
    }

    /**
     * @return the numeroEntelTicket
     */
    public String getNumeroEntelTicket() {
        return numeroEntelTicket;
    }

    /**
     * @param numeroEntelTicket
     *            the numeroEntelTicket to set
     */
    public void setNumeroEntelTicket(String numeroEntelTicket) {
        this.numeroEntelTicket = numeroEntelTicket;
    }

    /**
     * @return the confirmacionEntelTicket
     */
    public String getConfirmacionEntelTicket() {
        return confirmacionEntelTicket;
    }

    /**
     * @param confirmacionEntelTicket
     *            the confirmacionEntelTicket to set
     */
    public void setConfirmacionEntelTicket(String confirmacionEntelTicket) {
        this.confirmacionEntelTicket = confirmacionEntelTicket;
    }

    /**
     * @return the multitiendas
     */
    public SelectItem[] getMultitiendas() {
        if (multitiendas == null) {
            multitiendas = JsfUtil.getSelectItemsMultitiendaBean(
                    ParametrosHelper.getMultitiendas(), false);
        }
        return multitiendas;
    }

    /**
     * @param multitiendas
     *            the multitiendas to set
     */
    public void setMultitiendas(SelectItem[] multitiendas) {
        this.multitiendas = multitiendas;
    }

    /**
     * @return the numeroTarjetaMultitienda
     */
    public String getNumeroTarjetaMultitienda() {
        return numeroTarjetaMultitienda;
    }

    /**
     * @param numeroTarjetaMultitienda
     *            the numeroTarjetaMultitienda to set
     */
    public void setNumeroTarjetaMultitienda(String numeroTarjetaMultitienda) {
        this.numeroTarjetaMultitienda = numeroTarjetaMultitienda;
    }

    /**
     * @return the claveTarjetaMultitienda
     */
    public String getClaveTarjetaMultitienda() {
        return claveTarjetaMultitienda;
    }

    /**
     * @param claveTarjetaMultitienda
     *            the claveTarjetaMultitienda to set
     */
    public void setClaveTarjetaMultitienda(String claveTarjetaMultitienda) {
        this.claveTarjetaMultitienda = claveTarjetaMultitienda;
    }

    /**
     * @return the numeroCuotas
     */
    public String getNumeroCuotas() {
        List<CodeDescBean> nroCuotas = ParametrosHelper.getCuotas(JsfUtil
                .getRequestParameter("multitienda"));
        this.cuotas = nroCuotas.get(0).getCodigo();
        return JsonHelper.toJson(nroCuotas);
    }

    /**
     * @return the montoRecarga
     */
    public String getMontoRecarga() {
        return montoRecarga;
    }

    /**
     * @param montoRecarga
     *            the montoRecarga to set
     */
    public void setMontoRecarga(String montoRecarga) {
        this.montoRecarga = montoRecarga;
    }

    /**
     * @return the recargaWebPay
     */
    public RecargaWebPayBean getRecargaWebPay() {
        return recargaWebPay;
    }

    /**
     * @param recargaWebPay the recargaWebPay to set
     */
    public void setRecargaWebPay(RecargaWebPayBean recargaWebPay) {
        this.recargaWebPay = recargaWebPay;
    }
    
    /**
     * @return the pagoWebPay
     */
    public PagoWebPayBean getPagoWebPay() {
        return pagoWebPay;
    }

    /**
     * @param pagoWebPay the pagoWebPay to set
     */
    public void setPagoWebPay(PagoWebPayBean pagoWebPay) {
        this.pagoWebPay = pagoWebPay;
    }
    
    /**
     * @return the montoRecargaTarjetaCredito
     */
    public SelectItem[] getMontosRecargaTarjetaCredito() {
        if (montosRecargaTarjetaCredito == null) {
            montosRecargaTarjetaCredito = JsfUtil
                    .getSelectItemsMontosRecargaTarjetaCredito();
        }
        return montosRecargaTarjetaCredito;
    }

    /**
     * @param montoRecargaTarjetaCredito
     *            the montoRecargaTarjetaCredito to set
     */
    public void setMontosRecargaTarjetaCredito(
            SelectItem[] montosRecargaTarjetaCredito) {
        this.montosRecargaTarjetaCredito = montosRecargaTarjetaCredito;
    }    
    
    // ----------------------------------------- Init method
    
    public void init(PhaseEvent event) {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        
        //Se identifica el tipoRecarga que debe aparecer seleccionado
        //por medio del viewId actual
        if (tipoRecarga == null) {
            String viewId = fc.getViewRoot().getViewId();
            tipoRecarga = solveTipoRecarga(viewId);
        }
        
        //Seteamos el valor de multitienda al primer valor
        List<MultitiendaBean> tiendas = ParametrosHelper.getMultitiendas();
        if(tiendas.get(0) != null){
        	setMultitienda(tiendas.get(0).getNombreProperty());	
        }
        
        try {
        	if(getNumeroPcs() == null || (getNumeroPcs() != null && getNumeroPcs().equalsIgnoreCase(""))){
        		ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
    			numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
    			mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado");
    			LOGGER.info("ETPMovil: " + numeroPcs);
    			LOGGER.debug("ETPMovil: " + numeroPcs);
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("No se pudo establecer el numero seleccinado", e);
		}
        
        
    }
    
    private String solveTipoRecarga(String viewId) {
        
        String ret = null;
        
        SelectItem[] tiposRecarga = this.getTiposRecarga();
        for(int i = 0 ; i < tiposRecarga.length; i++) {
            String tipoRecarga = (String) tiposRecarga[i].getValue();
            if(viewId.toLowerCase().contains(tipoRecarga)) {
                ret = tipoRecarga;
                break;
            }
        }
        
        return ret;
    }
    
    
    // ----------------------------------------- Action Methods

    
    public void seleccionTipoRecarga(ValueChangeEvent changeEvent) {

        tipoRecarga = (String) changeEvent.getNewValue();
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getApplication().getNavigationHandler().handleNavigation(fc, null,
                tipoRecarga);

    }
    
    
    public void seleccionRecargaWebpay(ActionEvent event) {
    	
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String tipoWebpay = (String)context.getRequestParameterMap().get("tipoWebpay");

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getApplication().getNavigationHandler().handleNavigation(fc, null,
        		tipoWebpay);
    }   

    /**
     * Action method para solicitar recarga entel ticket
     * 
     * @return String postback
     */
    public String solicitarRecargaEntelTicket() {

        String postback = null;
        
        try {
            RecargaEntelTicketBean bean = new RecargaEntelTicketBean();
            
            bean.setNumeroPcs(WordNumberHelper.getPrefijoAmpliacion() + Integer.parseInt(indicativoTelefono,10) + numeroPcs);
            bean.setNumeroSecretoEnteTicket(numeroEntelTicket);
            
            this.facibilidadEntelticketBean = recargaDelegate
                    .factibilidadRecargaEntelTicket(bean);

            postback = "confirmar";
            
        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("ingresarRecarga",
                    new String[] { Integer.parseInt(getIndicativoTelefono(),10)+getNumeroPcs() });
        } catch (ServiceException e) {        	        
        	
        	LOGGER.info("ServiceException caught: " + numeroPcs + " - "
        				+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
            
            JSFMessagesHelper.addErrorCodeMessage("recarga", e.getCodigoRespuesta());
            
        } catch (Exception e) {
            LOGGER.error("Exception caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("ingresarRecarga",
                    new String[] { Integer.parseInt(getIndicativoTelefono(),10)+getNumeroPcs() });
        }

        return postback;
    }
    
    /**
     * Action method para la confirmacion a recarga entel ticket
     * 
     * @return String postback
     */
    public String confirmarRecargaEntelTicket() {

        String postback = "cancelar";
        
        try {

            montoRecarga = JsfUtil.getRequestParameter("monto");
            numeroEntelTicket = JsfUtil.getRequestParameter("nroEntelTicket");
            String numeroPcsRecarga = WordNumberHelper.getPrefijoAmpliacion() + Integer.parseInt(getIndicativoTelefono(),10) + getNumeroPcs();
            //numeroPcs = JsfUtil.getRequestParameter("numeroPcs");

            FactibilidadRecargaEntelTicketBean factibilidadEntelticketBean = 
                new FactibilidadRecargaEntelTicketBean();
            
            factibilidadEntelticketBean.setMontoDispTicket(Double
                    .parseDouble(montoRecarga));
            factibilidadEntelticketBean.setNumeroEntelTicket(numeroEntelTicket);
            factibilidadEntelticketBean.setNumeroPcs(numeroPcsRecarga);
            factibilidadEntelticketBean.setFolioTicket(JsfUtil
                    .getRequestParameter("folio"));
            factibilidadEntelticketBean.setAgenciaTicket(JsfUtil
                    .getRequestParameter("agencia"));

            facibilidadEntelticketBean = recargaDelegate
                    .realizarRecargaEntelTicket(factibilidadEntelticketBean);

            postback  = "success";

        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("factibilidadRecarga",
                    new String[] { Integer.parseInt(getIndicativoTelefono(),10)+getNumeroPcs() });
        } catch (ServiceException e) {
        	
        	LOGGER.info("ServiceException caught: " + WordNumberHelper.getPrefijoAmpliacion() + 
        			Integer.parseInt(getIndicativoTelefono(),10) + numeroPcs + " - " 
        				+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
        	            
            JSFMessagesHelper.addErrorCodeMessage("recarga", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("factibilidadRecarga",
                    new String[] { Integer.parseInt(getIndicativoTelefono(),10)+getNumeroPcs() });
        }

        return postback;
    }

    /**
     * Action method para solicitar recarga multitienda
     * 
     * @return String postback
     */
    public String solicitarRecargaMultitienda() {

        String postback = null;
        
        try {

            factibilidadRecarga = new RecargaMultitiendaBean();

            factibilidadRecarga.setMontoRecarga(Integer
                    .parseInt(this.montoRecarga));
            factibilidadRecarga.setNumeroPcs(WordNumberHelper.getPrefijoAmpliacion() + 
            		Integer.parseInt(getIndicativoTelefono(),10) + getNumeroPcs());
            factibilidadRecarga.setNumeroTarjeta(Long
                    .parseLong(this.numeroTarjetaMultitienda));
            factibilidadRecarga.setClaveTarjeta(this.claveTarjetaMultitienda);
            factibilidadRecarga.setCuotas(Integer.parseInt(this.cuotas));            
            
            this.factibilidadMultitienda = recargaDelegate
                    .factibilidadRecargaMultitienda(factibilidadRecarga);

            postback = "confirmar";
            
        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("ingresarRecarga",
                    new String[] { Integer.parseInt(getIndicativoTelefono(),10)+getNumeroPcs() });
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException caught: " + numeroPcs + " - "
        				+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("recarga", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("ingresarRecarga",
                    new String[] { Integer.parseInt(getIndicativoTelefono(),10) + getNumeroPcs() });
        }

        return postback;

    }
    
    /**
     * Action method para la confirmacion a recarga multitienda
     * @return String postback
     */
    public String confirmarRecargaMultitienda() {

        try {

            RecargaMultitiendaBean recarga = new RecargaMultitiendaBean();

            this.montoRecarga = JsfUtil.getRequestParameter("monto");
            //this.numeroPcs = JsfUtil.getRequestParameter("numeroPcs");
            String numeroPcsRecarga = WordNumberHelper.getPrefijoAmpliacion() + 
            		Integer.parseInt(getIndicativoTelefono(),10) + getNumeroPcs();
            this.numeroTarjetaMultitienda = JsfUtil
                    .getRequestParameter("nroTarjeta");
            this.claveTarjetaMultitienda = JsfUtil
                    .getRequestParameter("claveTarjeta");
            this.cuotas = JsfUtil.getRequestParameter("cuotas");
            this.nombreMultitienda = JsfUtil
                    .getRequestParameter("nombreMultitienda");

            recarga.setMontoRecarga(Integer.parseInt(montoRecarga));
            recarga.setNumeroPcs(numeroPcsRecarga);
            recarga.setNumeroTarjeta(Long.parseLong(numeroTarjetaMultitienda));
            recarga.setClaveTarjeta(claveTarjetaMultitienda);
            recarga.setCuotas(Integer.parseInt(cuotas));

            recargaMultitiendaBean = recargaDelegate
                    .realizarRecargaMultitienda(recarga);

            return "success";

        } catch (DAOException ex) {
        	 LOGGER.error("DAOException caught: " + ex.getMessage(), ex);
            //TODO cambiar mensaje a properties
            JSFMessagesHelper.addErrorMessage("No se pudo realizar la recarga");
        } catch (ServiceException ex) {
        	LOGGER.info("ServiceException caught: " + numeroPcs + " - "
    					+ ex.getCodigoRespuesta() + " - " + ex.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("recarga", ex.getCodigoRespuesta());
        } catch (Exception ex) {
        	LOGGER.error("Exception caught: " + ex.getMessage(), ex);
        }

        return "cancelar";

    }

    /**
     * Action method para solicitar recarga tarjeta de credito.<br>
     * Este metodo realiza el ingreso y la factibilidad
     * 
     * @return String postback
     */
    public String solicitarRecargaTarjetaCredito() {
		LOGGER.info("## Inicio solicitud recarga TC-Validando promocion ##");
    	String postback = "confirmar";

        Double montoRecarga = null;
        RecargaWebPayBean recarga = null;

        // Validacion de monto recarga
        try {
            montoRecarga = Double.valueOf(getMontoRecarga());            
        } catch (Exception e) {
            //TODO cambiar a mensaje en properties
            JSFMessagesHelper.addErrorMessage("Monto de recarga no es un numero valido");
            postback = null;
        }
        
        // Validacion de promocion
        try {
        	validarMontoPromocion();
        } catch (Exception e) {
        	LOGGER.error("No se pudo validar la promocion para el monto seleccionado", e);
        }

        // Ingreso recarga
        String codPromo = "";
        String codPromoF = "";
        try {        	
        	if (promoRecarga != null) {
        		if (montoRecarga >= 3500) {
        			codPromo = "-" + promoRecarga.getCodigo();
        			codPromoF = promoRecarga.getCodigo();
        			LOGGER.info("Recarga mayor o igual a 3500, PromoRecargaID: "+promoRecarga.getId()+", ETPMovil: " + numeroPcs+", codPromo: "+codPromo+" , codPromoF: "+codPromoF);
        		} else {
        			LOGGER.info("Recarga menor a 3500, PromoRecargaID: "+promoRecarga.getId()+", ETPMovil: " + numeroPcs+", codPromo: "+codPromo+" , codPromoF: "+codPromoF);
        		}
        	} else {
        		LOGGER.info("ETPMovil: " + numeroPcs+" ,codPromo: "+codPromo+" , codPromoF: "+codPromoF);
        	}
        	
        	LOGGER.info("## Invocando IngresarRecargaWebPay ##");
            recarga = recargaDelegate.ingresarRecargaWebPay(WordNumberHelper.getPrefijoAmpliacion()+
            		Integer.parseInt(getIndicativoTelefono(),10)+getNumeroPcs(), montoRecarga, codPromo);
            
        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("ingresarRecarga",
                    new String[] { Integer.parseInt(getIndicativoTelefono(),10)+getNumeroPcs() });
            postback = null;
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught: "+ numeroPcs + " - " 
            			+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("recarga", e.getCodigoRespuesta());
            postback = null;
        } catch (Exception e) {
            LOGGER.error("Exception caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("ingresarRecarga",
                    new String[] { Integer.parseInt(getIndicativoTelefono(),10)+getNumeroPcs() });
            postback = null;
        }

        // Si el postback es null, indica ocurrio excepcion en el ingreso de
        // recarga
        if (postback == null) {
            return postback;
        }

        // Factibilidad
        try {
        	LOGGER.info("## Invocando servicio de factibilidad ##");
            FactibilidadRecargaWebPayBean factibilidad = recargaDelegate.factibilidadRecargaWebPay(recarga, eligeTuPromo, codPromoF);

            if (factibilidad == null) {
            	LOGGER.info("FactibilidadRecargaWebPayBean es nulo!");
               LOGGER.error( new Exception("no se obtuvo factibilidad"));
            }

        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("factibilidadRecarga",
                    new String[] { recarga.getNumeroPcs() });
            postback = null;
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException caught: "+ numeroPcs + " - " 
        				+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("recarga", e.getCodigoRespuesta());
            postback = null;
        } catch (Exception e) {
            LOGGER.error("Exception caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("factibilidadRecarga",
                    new String[] { recarga.getNumeroPcs() });
            postback = null;
        }

        // Se asigna recarga obtenida, ya sea haya sido bien generada, aprobada
        // de factibilidad o no
        this.setRecargaWebPay(recarga);

        return postback;
    }
    
    /**
     * Action method para la confirmacion a recarga tarjeta de credito
     * @return
     */
    public String confirmarRecargaTarjetaCredito() {

        String postback = null;

        try {
            String ordenCompra = this.recargaWebPay.getOrdenCompra();
            
            LOGGER.info("## Inicio ConfirmarRecargaTC - Invocando consultarRecargaWebPayBean ##");
            this.recargaWebPay = recargaDelegate
                    .consultarRecargaWebPayBean(ordenCompra);
            postback = "recargaWebpay";
        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("consultarRecarga",
                    new String[] { recargaWebPay.getNumeroPcs() });
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException caught: "+ numeroPcs + " - " 
        				+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("recarga", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught: " + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("consultarRecarga",
                    new String[] { recargaWebPay.getNumeroPcs() });
        }

        // PagoWebPayBean: bean con las constantes para el pago mediante webpay
        // El objeto PagoWebPayBean debe recibir la URL inicial del servidor
        // ie: http://<serverName>:<port>
        FacesContext context = FacesContext.getCurrentInstance();
        String contextPath = JSFPortletHelper.getServerFullPathHttps(context);
        pagoWebPay = new PagoWebPayBean(contextPath);

		String urlAction = JSFPortletHelper.getPreference(JSFPortletHelper
				.getPreferencesObject(), "recarga.webpay.pago.action",
				pagoWebPay.getUrlFormAction());
		pagoWebPay.setUrlFormAction(urlAction);

        return postback;
    }

    /**
     * Entrega el valor del parametro TBK_ID_SESION que sera enviado a Tranbank.<br>
     * Lo genera concatenando un prefijo definido en la propiedad
     * <code>recarga.webpay.pago.TBK_ID_SESION.prefix</code> el caracter ":" y
     * el IDP de la recarga Webpay
     * 
     * @return String
     */
    public String getTbkIdSesion() {
        String tbkIdSesion = MiEntelProperties
                .getProperty("recarga.webpay.pago.TBK_ID_SESION.prefix");
        return tbkIdSesion + ":" + recargaWebPay.getIdp();
    }
    
    /**
     * @return the factibilidadMultitienda
     */
    public FactibilidadRecargaMultitiendaBean getFactibilidadMultitienda() {
        return factibilidadMultitienda;
    }

    /**
     * @param factibilidadMultitienda the factibilidadMultitienda to set
     */
    public void setFactibilidadMultitienda(
            FactibilidadRecargaMultitiendaBean factibilidadMultitienda) {
        this.factibilidadMultitienda = factibilidadMultitienda;
    }

    /**
     * @return the montos
     */
    public String getMontos() {
        String multi = JsfUtil.getRequestParameter("multitienda");
        nombreMultitienda = ParametrosHelper.getNombreMultitienda(multi);
        List<CodeDescBean> montos = ParametrosHelper.getMontos(multi);
        montoRecarga = montos.get(0).getCodigo();
        return JsonHelper.toJson(montos);
    }
    
   
	/**
	 * Metodo que devuelve la lista de los prefijos para red fija
	 * 
	 * @return
	 */
	public List<SelectItem> getPrefijosTelefono() {
		return ParametrosHelper.getPrefijoTelefonoParametrosList();
	}
	
	/**
	 * Metodo que devuelve la lista de los productos disponibles para recarga
	 * 
	 * @return
	 */	
	public List<SelectItem> getProductosRecarga() {
		return ParametrosHelper.getProductosRecargaList();
	}	
    
    /**
     * @return the multitienda
     */
    public String getMultitienda() {        
        return multitienda;
    }

    /**
     * @param multitienda the multitienda to set
     */
    public void setMultitienda(String multitienda) {
        this.multitienda = multitienda;
    }

    /**
     * @return the cuotas
     */
    public String getCuotas() {
        return cuotas;
    }

    /**
     * @param cuotas the cuotas to set
     */
    public void setCuotas(String cuotas) {
        this.cuotas = cuotas;
    }

    /**
     * @return the nombreMultitienda
     */
    public String getNombreMultitienda() {
        return nombreMultitienda;
    }

    /**
     * @param nombreMultitienda the nombreMultitienda to set
     */
    public void setNombreMultitienda(String nombreMultitienda) {
        this.nombreMultitienda = nombreMultitienda;
    }

    /**
     * @return the recargaMultitiendaBean
     */
    public FactibilidadRecargaMultitiendaBean getRecargaMultitiendaBean() {
        return recargaMultitiendaBean;
    }

    /**
     * @param recargaMultitiendaBean the recargaMultitiendaBean to set
     */
    public void setRecargaMultitiendaBean(
            FactibilidadRecargaMultitiendaBean recargaMultitiendaBean) {
        this.recargaMultitiendaBean = recargaMultitiendaBean;
    }

    /**
     * @return the fechaActual
     */
    public String getFechaActual() {
        fechaActual = DateHelper.format(new Date(), "dd/MM/yyyy hh.mm");
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    /**
     * @return the facibilidadEntelticketBean
     */
    public FactibilidadRecargaEntelTicketBean getFacibilidadEntelticketBean() {
        return facibilidadEntelticketBean;
    }

    /**
     * @param facibilidadEntelticketBean the facibilidadEntelticketBean to set
     */
    public void setFacibilidadEntelticketBean(
            FactibilidadRecargaEntelTicketBean facibilidadEntelticketBean) {
        this.facibilidadEntelticketBean = facibilidadEntelticketBean;
    }

    /**
     * @return the factibilidadRecarga
     */
    public RecargaMultitiendaBean getFactibilidadRecarga() {
        return factibilidadRecarga;
    }

    /**
     * @param factibilidadRecarga the factibilidadRecarga to set
     */
    public void setFactibilidadRecarga(RecargaMultitiendaBean factibilidadRecarga) {
        this.factibilidadRecarga = factibilidadRecarga;
    }   
    
    public String getIndicativoTelefono() {
		return indicativoTelefono;
	}

	public void setIndicativoTelefono(String indicativoTelefono) {
		this.indicativoTelefono = indicativoTelefono;
	}

	public String getFechaActualFormat() {
		Calendar c = Calendar.getInstance();
		fechaActualFormat = (c.getTimeInMillis() / 1000L)+"";	
		return fechaActualFormat;	
	}

	public void setFechaActualFormat(String fechaActualFormat) {
		this.fechaActualFormat = fechaActualFormat;
	}

	public String getProductoRecargar() {
		return productoRecargar;
	}

	public void setProductoRecargar(String productoRecargar) {
		this.productoRecargar = productoRecargar;
	}

	public String getMercado() {
		return mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}	

	public String getIdPromoRecarga() {
		return idPromoRecarga;
	}

	public void setIdPromoRecarga(String idPromoRecarga) {
		this.idPromoRecarga = idPromoRecarga;
	}

	public String getDescPromoRecarga() {
		return descPromoRecarga;
	}

	public void setDescPromoRecarga(String descPromoRecarga) {
		this.descPromoRecarga = descPromoRecarga;
	}

	public String getMensajePromoRecarga() {
		return mensajePromoRecarga;
	}

	public void setMensajePromoRecarga(String mensajePromoRecarga) {
		this.mensajePromoRecarga = mensajePromoRecarga;
	}

	public PromoRecargaWebPayBean getPromoRecarga() {
		return promoRecarga;
	}

	public void setPromoRecarga(PromoRecargaWebPayBean promoRecarga) {
		this.promoRecarga = promoRecarga;
	}

	public String getPageLabelBolsas() {
		try {
			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			return JSFPortletHelper.getPreference(JSFPortletHelper
					.getPreferencesObject(), ProfileWrapperHelper
					.getPropertyAsString(profileWrapper, "mercado"), null);
		} catch (Exception e) {
			LOGGER.error("No se ha podido obtener el pageLabel"
					+ e.getMessage(), e);
			return "";
		}
	}
	
	/**
	 * Valida el despliegue del mensaje de promocion para recarga WebPay
	 * @throws Exception
	 */
	public void validarMontoPromocion() throws Exception {
		/**
		 * Verifica si Elige tu Promo se encuentra habilitado
		 */
		eligeTuPromo = JSFPortletHelper.getContentNode("idContenido",
				"flagMostrarEligeTuPromo").getProperty("valorFlag")
				.getValue().getBooleanValue();
		
		LOGGER.info("ETPMovil: " + numeroPcs + ", productoRecargar: "+productoRecargar.toString()+", eligeTuPromo: " + eligeTuPromo);
		
		if (productoRecargar.equals("TM") && eligeTuPromo) {
			promoRecarga = new PromoRecargaWebPayBean(idPromoRecarga, montoRecarga);
			promoRecarga.setDescripcion(descPromoRecarga);
			promoRecarga.setMensaje(mensajePromoRecarga);	

			LOGGER.info("ETPMovil: " + numeroPcs + ", promoRecargaCodigo: " + promoRecarga.getCodigo()+", montoRecarga: " + montoRecarga + ", idPromoRecarga: " + idPromoRecarga);
			
			if (Integer.parseInt(montoRecarga) >= 3500) {				
				// Cookie que tiene como valor la descripcion de la promocion seleccionada
				Cookie cookieDescPromo = new Cookie("descPromoRecargaWPDesktopPrivado", promoRecarga.getDescripcion());
				cookieDescPromo.setMaxAge(-1);
				
				// Se agrega la cookie al request
				JSFPortletHelper.getResponse().addCookie(cookieDescPromo);
			}
		} else {
			promoRecarga = null;
			LOGGER.info("ETPMovil: " + numeroPcs + ", productoRecargar no es TM y flag EligeTuPromo es false");
			
		}
	}

	/** 
     * Crea un array de SelectItem a partir de un nodo de contenido
     * @param entities List 
     * @param selectOne boolean que indica si se agreaga una opcion vacia al inicio.
     * @return Array de tipo SelectItem
     * @throws No dispara ninguna excepcion.
     */
    public String getJSONPromocionesRecargaTarjetaCredito() {
    	String promocionesJSON = null;
    	Map<String, List<PromoRecargaWebPayBean>> mapPromoRecarga = new LinkedHashMap<String, List<PromoRecargaWebPayBean>>();
    	
    	try {
    		List<Node> parents = JSFPortletHelper.getContentNodes("idContenido", "eligeTuPromoRecarga");
    		String mensajeConfirmarPromo = JSFPortletHelper.getContentNode(
					"idContenido", "mensajeConfirmarPromo").getProperty("html")
					.getValue().getStringValue();    		
    		
    		for (Node parent : parents) {
    			String montoRecarga = parent.getProperty("idPromo").getValue().getStringValue();
    			if (parent.hasChildren()) {
        			List<Node> children = JSFPortletHelper.getChildrenNodes(parent);
        			List<PromoRecargaWebPayBean> detallePromoRecarga = new ArrayList<PromoRecargaWebPayBean>();
        			
        			for (Node n : children) {
        			    String idPromo = n.getProperty("idPromo").getValue().getStringValue();
        			    String tipoPromo = n.getProperty("tipoPromo").getValue().getStringValue();
        			    String descPromo = n.getProperty("descPromo").getValue().getStringValue();
        			    detallePromoRecarga.add(new PromoRecargaWebPayBean(idPromo, montoRecarga, mensajeConfirmarPromo, tipoPromo, descPromo));
        	        }
        			
        			mapPromoRecarga.put(montoRecarga, detallePromoRecarga);
        		}
			}
    		
    		promocionesJSON = JsonHelper.toJson(mapPromoRecarga);
    	} catch (Exception e) { 
    		promocionesJSON = "";
    		LOGGER.info("ETPMovil: " + numeroPcs + ", e: " + e.getMessage());
    	}
    		
    	return promocionesJSON;
    }	
}
