/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.pago.controller;

import java.io.Serializable;
import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.EstadoPagoAutomaticoBean;
import com.epcs.bean.EstadoPromoPagoAutomaticoBean;
import com.epcs.bean.PagoAutomaticoBean;
import com.epcs.billing.pago.delegate.PagoAutomaticoDelegate;

import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
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
public class PagoAutomaticoController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger
            .getLogger(PagoAutomaticoController.class);
    private static final String SERVICE_NAME = "pagoAutomatico";
    private String fechaEstadoPromo;
    private PagoAutomaticoBean pagoAutomaticoBean = new PagoAutomaticoBean();
    private HtmlPanelGroup panelInscrito = new HtmlPanelGroup();
    private HtmlPanelGroup panelNoInscrito = new HtmlPanelGroup();
    private HtmlPanelGroup panelProceso = new HtmlPanelGroup();
    private HtmlPanelGroup panelResultado = new HtmlPanelGroup();
    private java.util.Date fechaActual = new java.util.Date();
    private boolean aceptaTerminos = false;

    /**
     * @return the panelNoInscrito
     */
    public HtmlPanelGroup getPanelNoInscrito() {
        return panelNoInscrito;
    }

    /**
     * @return the panelProceso
     */
    public HtmlPanelGroup getPanelProceso() {
        return panelProceso;
    }

    /**
     * @return the panelInscrito
     */
    public HtmlPanelGroup getPanelInscrito() {
        return panelInscrito;
    }

    private EstadoPagoAutomaticoBean estadoPAT;

    /**
     * @return the estadoPAT
     */
    public EstadoPagoAutomaticoBean getEstadoPAT() {
        return estadoPAT;
    }

    /**
     * @param estadoPAT
     *            the estadoPAT to set
     */
    public void setEstadoPAT(EstadoPagoAutomaticoBean estadoPAT) {
        this.estadoPAT = estadoPAT;
    }

    private PagoAutomaticoDelegate pagoAutomaticoDelegate;

    /**
     * @param pagoAutomaticoDelegate
     *            the pagoAutomaticoDelegate to set
     */
    public void setPagoAutomaticoDelegate(
            PagoAutomaticoDelegate pagoAutomaticoDelegate) {
        this.pagoAutomaticoDelegate = pagoAutomaticoDelegate;
    }

    /**
     * @return the fechaEstadoPromo
     */
    public String getFechaEstadoPromo() {
        return fechaEstadoPromo;
    }

    /**
     * @param fechaEstadoPromo
     *            the fechaEstadoPromo to set
     */
    public void setFechaEstadoPromo(String fechaEstadoPromo) {
        this.fechaEstadoPromo = fechaEstadoPromo;
    }
    
    
    public SelectItem[] getTiposTarjetas(){
        return JsfUtil.getSelectItemsCodeDesc(ParametrosHelper.getListTiposTarjeta(), false);
    }
    

    public void init(PhaseEvent phase) {
    	String msisdn = "";
    	try {
            
            if(phase.getPhaseId() == PhaseId.RENDER_RESPONSE){

            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            msisdn = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcs");
            String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroCuenta");

            String rut = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "rutTitular");

            panelInscrito.setRendered(false);
            panelProceso.setRendered(false);
            panelNoInscrito.setRendered(false);

            EstadoPagoAutomaticoBean estadoPAT = this.pagoAutomaticoDelegate
                    .consultarEstadoPAT(msisdn, numeroCuenta);

            if (estadoPAT.getEstado().equals("APROBADO")) {
                panelInscrito.setRendered(true);
            }
            else if (estadoPAT.getEstado().equals("EN PROCESO")) {
                panelProceso.setRendered(true);
            }
            else if (estadoPAT.getEstado().equals("RECHAZADO")) {
                //TODO cambiar a mensaje en properties
                JSFMessagesHelper.addErrorMessage("Rechazado");
            }
            else if (estadoPAT.getEstado().equals("PAT NO INSCRITO")) {

                EstadoPromoPagoAutomaticoBean estadoPromo = this.pagoAutomaticoDelegate
                        .consultarEstadoPromoPAT(rut, numeroCuenta);
                if(estadoPromo.getEstadoPromocion().equals("PAT INSCRITO")){ 
                this.setFechaEstadoPromo(MiEntelProperties
                        .getProperty("pagoAutomatico.fechaEstadoPromo"));

                panelInscrito.setRendered(true);
               }else if(estadoPromo.getEstadoPromocion().equals("PAT NO INSCRITO")){
                   panelNoInscrito.setRendered(true);   
               }
            }

            this.setEstadoPAT(estadoPAT);
            }else{
                
            }
        } catch (DAOException ex) {
            LOGGER.error("Error al inespeado consultar PAT", ex);
            JSFMessagesHelper.addErrorCodeMessage(SERVICE_NAME, "0001");
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio. msisdn: "+msisdn+"\n codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper
                    .addErrorCodeMessage(SERVICE_NAME, e
                            .getCodigoRespuesta());

        } catch (Exception e) {
            LOGGER.error("Error al inesperado  consultar PAT", e);
            JSFMessagesHelper.addErrorCodeMessage(SERVICE_NAME, "0001");
        }

    }
    
    
    /**
     * Navegar a la pagina de Confirmacion
     * @return navigation rule "patconfirmar"
     */
    public String confirmarPAT(){
        return "patconfirmar";
    }
    
    /**
     * Realiza la sol de inscripcion
     * @return navigation rule "resultadoinscribirpat"
     */
    public String inscribirPAT(){
    	String msisdn= "";
        try{
            //Si ocurre una exception el panel quedara oculto
            panelResultado.setRendered(false);
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            msisdn = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcs");
            String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroCuenta");
            
            this.pagoAutomaticoDelegate.inscribirPAT(getPagoAutomaticoBean(), msisdn, numeroCuenta);
            panelResultado.setRendered(true);
            
        }catch (DAOException ex) {
            LOGGER.error("Error al inespeado consultar PAT", ex);
            JSFMessagesHelper.addErrorCodeMessage(SERVICE_NAME, "0001");
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio. msisdn: "+msisdn+"\n codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper
                    .addErrorCodeMessage(SERVICE_NAME, e
                            .getCodigoRespuesta());

        } catch (Exception e) {
            LOGGER.error("Error al inesperado  consultar PAT", e);
            JSFMessagesHelper.addErrorCodeMessage(SERVICE_NAME, "0001");
        }
        
        return "resultadoinscribirpat";
    }
    
    

    /**
     * @return the pagoAutomaticoBean
     */
    public PagoAutomaticoBean getPagoAutomaticoBean() {
        return pagoAutomaticoBean;
    }

    /**
     * @param pagoAutomaticoBean the pagoAutomaticoBean to set
     */
    public void setPagoAutomaticoBean(PagoAutomaticoBean pagoAutomaticoBean) {
        this.pagoAutomaticoBean = pagoAutomaticoBean;
    }

    /**
     * @return the aceptaTerminos
     */
    public boolean isAceptaTerminos() {
        return aceptaTerminos;
    }

    /**
     * @param aceptaTerminos the aceptaTerminos to set
     */
    public void setAceptaTerminos(boolean aceptaTerminos) {
        this.aceptaTerminos = aceptaTerminos;
    }

    /**
     * @param panelInscrito the panelInscrito to set
     */
    public void setPanelInscrito(HtmlPanelGroup panelInscrito) {
        this.panelInscrito = panelInscrito;
    }

    /**
     * @param panelNoInscrito the panelNoInscrito to set
     */
    public void setPanelNoInscrito(HtmlPanelGroup panelNoInscrito) {
        this.panelNoInscrito = panelNoInscrito;
    }

    /**
     * @param panelProceso the panelProceso to set
     */
    public void setPanelProceso(HtmlPanelGroup panelProceso) {
        this.panelProceso = panelProceso;
    }

    /**
     * @return the fechaActual
     */
    public java.util.Date getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(java.util.Date fechaActual) {
        this.fechaActual = fechaActual;
    }
    
    public void validateTerminos(FacesContext context, UIComponent component,Object o) {   
        HtmlSelectBooleanCheckbox checkBox = (HtmlSelectBooleanCheckbox) component;   
        if( checkBox.getSubmittedValue().equals("false") ) {   
           LOGGER.error( new javax.faces.validator.ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "No haz Aceptado los terminos", "No haz Aceptado los terminos")));
        }   
       }

    /**
     * @return the panelResultado
     */
    public HtmlPanelGroup getPanelResultado() {
        return panelResultado;
    }

    /**
     * @param panelResultado the panelResultado to set
     */
    public void setPanelResultado(HtmlPanelGroup panelResultado) {
        this.panelResultado = panelResultado;
    }
    
    
    public String getNumeroTarjetaOculto(){
        StringBuilder numero = new StringBuilder();
        if(getPagoAutomaticoBean().getNumeroTarjeta() != null){
            int nx = getPagoAutomaticoBean().getNumeroTarjeta().length() - 3;
            char[] fill = new char[nx];
            Arrays.fill(fill, 'X');
            numero.append(fill);
            numero.append("-");
            numero.append(getPagoAutomaticoBean().getNumeroTarjeta().substring(getPagoAutomaticoBean().getNumeroTarjeta().length() - 3, getPagoAutomaticoBean().getNumeroTarjeta().length()));
        }
        return numero.toString();
    }
}
