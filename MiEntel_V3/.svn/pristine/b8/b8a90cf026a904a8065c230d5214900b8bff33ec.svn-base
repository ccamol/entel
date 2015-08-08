/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.orden.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.BolsaBean;
import com.epcs.bean.BolsaPPBean;
import com.epcs.bean.BolsasActualesDisponiblesBean;
import com.epcs.bean.ResultadoContratarBolsaBean;
import com.epcs.bean.ResumenPlan;
import com.epcs.bean.TransaccionGTMBean;
import com.epcs.cliente.orden.delegate.InternetMovilDelegate;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
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
public class InternetMovilController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = Logger
            .getLogger(InternetMovilController.class);
    
    private InternetMovilDelegate internetMovilDelegate;
    private BolsasActualesDisponiblesBean bolsasADBean;
    private List<BolsaPPBean> bolsasActualesPP;
    private List<BolsaBean> bolsasDispRegalo;
    private String respuestaJson;
    private double cantMbIlimitado;

    /**
     * @return the internetMovilDelegate
     */
    public InternetMovilDelegate getInternetMovilDelegate() {
        return internetMovilDelegate;
    }

    /**
     * @param internetMovilDelegate
     *            the internetMovilDelegate to set
     */
    public void setInternetMovilDelegate(
            InternetMovilDelegate internetMovilDelegate) {
        this.internetMovilDelegate = internetMovilDelegate;
    }

    /**
     * Busca bolsas actuales y disponibles para mercado ss y cc
     * @param phase
     */
    public void init(final PhaseEvent phase) {
        try {
            if (phase.getPhaseId() == PhaseId.RENDER_RESPONSE) {

                final ProfileWrapper profileWrapper = ProfileWrapperHelper
                        .getProfile(JSFPortletHelper.getRequest());

                final String msisdn = ProfileWrapperHelper.getPropertyAsString(
                        profileWrapper, "numeroPcsSeleccionado");
                //Planes con cant superior a este son ilimitados
                cantMbIlimitado = Double.parseDouble(MiEntelProperties
                .getProperty("parametros.internetmovil.ilimitado"));
                

                bolsasADBean = internetMovilDelegate
                        .consultarBolsasActDisponiblesBAM(msisdn);

            }

        } catch (DAOException ex) {
            LOGGER.error("DAOException al inespeado consultar bolsas", ex);
            JSFMessagesHelper.addServiceErrorMessage("serviceDisabled");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("clienteOrden", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperada consultar bolsas", e);
            JSFMessagesHelper.addServiceErrorMessage("serviceDisabled");
        }

    }

    /**
     * Entrada para el procesamiento de ajax
     * 
     * @param phase
     */
    public void initProcesarAjax(final PhaseEvent phase) {
        final String accion = JsfUtil.getRequestParameter("accion");
        if ("contratar".equals(accion)) {
            this.contratarBolsaSSCC();
        }else if("comprar".equals(accion)){
            this.comprarBolsaPP();
        }

    }

    /**
     * Contratar Bolsas SS y CC
     * 
     * @param phase
     */
    private void contratarBolsaSSCC() {
        try {

            String codigoBolsa = JsfUtil.getRequestParameter("codigoBolsa");
            String valorBolsa = JsfUtil.getRequestParameter("valorBolsa");
            String nombreBolsa = JsfUtil.getRequestParameter("nombreBolsa");

            ResultadoContratarBolsaBean resulContratarBolsaBean;

            final ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            final String msisdn = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcsSeleccionado");

            final String TIPO_ACTIVACION = MiEntelProperties
                    .getProperty("parametros.bolsa.tipoactivacion");
            
            resulContratarBolsaBean = internetMovilDelegate.contratarBolsaSSCC(
                    msisdn, codigoBolsa, Double.parseDouble(valorBolsa),
                    TIPO_ACTIVACION);

            String msj = MiEntelProperties
                    .getServiceMessages()
                    .getRb()
                    .getString(
                            "success.clienteOrden.bolsacontratada"
                                    .concat(resulContratarBolsaBean
                                            .getTipoActivacion().toLowerCase()));
            
            //Carga de datos para el marcado GTM
            TransaccionGTMBean transGTM = new TransaccionGTMBean();
            transGTM.setIdTransaccion(msisdn.substring(msisdn.length() - 4));
            transGTM.setSkuID(codigoBolsa);
            transGTM.setNombre(nombreBolsa);
            transGTM.setNuevoValor(valorBolsa);
            transGTM.setCostoOperacional(0);
            transGTM.setNumeroPlanes(1);
            transGTM.setNumeroOperaciones(1);
            transGTM.setValorTransaccion(Double.parseDouble(valorBolsa) + transGTM.getCostoOperacional());
            
            respuestaJson = JsonHelper.toJsonGTMResponse(transGTM, msj);            

        } catch (DAOException e) {
        	LOGGER.error("DAOException caught", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("nocontratabolsa");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonServiceErrorMessage(
                    "clienteOrden", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener datos del plan", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("nocontratabolsa");
        }
    }
    
    /**
     * 
     * @param phase
     */
    public void initPP(final PhaseEvent phase){
        try{
        if(phase.getPhaseId() == PhaseId.RENDER_RESPONSE){
            
            final ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());

            final String msisdn = ProfileWrapperHelper.getPropertyAsString(
            profileWrapper, "numeroPcsSeleccionado");
  
            final String mercado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "mercado");
            
            String tipoMercado = MiEntelProperties
            .getProperty("parametros.flagmercado.".concat(mercado));
            
            try{
                bolsasActualesPP = internetMovilDelegate.consultarPlanesActualesBAMPP(msisdn); 
             }catch(Exception e){
                LOGGER.error("No se obtuvieron bolsas actuales",e);
             }
            
             bolsasDispRegalo = internetMovilDelegate.consultarBolsasDisponiblesRegaloBAM(msisdn, tipoMercado);  
             
             
        }
        }catch (DAOException e) {
        	LOGGER.error("DAOException caught", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("nocontratabolsa");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonServiceErrorMessage(
                    "clienteOrden", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught: "+e.getMessage(), e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("nocontratabolsa");
        }
         
    }
    
    
    /**
     * Action para comprar una bolsa pp
     * 
     */
    public void comprarBolsaPP() {

        try {

            final ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            final String msisdn = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcsSeleccionado");

            final String descuentoStr = MiEntelProperties
                    .getProperty("parametros.comprarbolsa.descuento");
            String msj;
            
            final String cartaServicio = JsfUtil.getRequestParameter("cartaServicio");
            final String nombreBolsa = JsfUtil.getRequestParameter("nombreBolsa");
            final double valorBolsa = Double.parseDouble(JsfUtil.getRequestParameter("valorBolsa"));
            final double descuento = Double.parseDouble(descuentoStr);
            
            //Obtener informacion de saldo.            
            ResumenPlan resumenPlan = this.obtenerPlanResumen(msisdn);
            
            if (resumenPlan != null) {
                // Se valida el saldo.
                if ((resumenPlan.getSaldo() + descuento) <= valorBolsa) {
                    respuestaJson = JsonHelper.toJsonServiceErrorMessage("saldoinsuficientepp");
                }
                else {

                    internetMovilDelegate.comprarBolsasOneShotPP(msisdn,
                    cartaServicio, valorBolsa);
                    msj = MiEntelProperties.getServiceMessages().getRb()
                            .getString("success.clienteOrden.bolsacomprada");
                    msj = msj.replace("{0}", nombreBolsa);
                    
                    //Carga de datos para el marcado GTM
                    TransaccionGTMBean transGTM = new TransaccionGTMBean();
                    transGTM.setIdTransaccion(msisdn.substring(msisdn.length() - 4));
                    transGTM.setSkuID(cartaServicio);
                    transGTM.setNombre(nombreBolsa);
                    transGTM.setNuevoValor(String.valueOf(valorBolsa));
                    transGTM.setCostoOperacional(0);
                    transGTM.setNumeroPlanes(1);
                    transGTM.setNumeroOperaciones(1);                    
                    
                    respuestaJson = JsonHelper.toJsonGTMResponse(transGTM, msj);
                }
            }else{
                respuestaJson = JsonHelper.toJsonServiceErrorMessage("noinfosaldo");
            }

        } catch (DAOException e) {
            LOGGER.error("DAOException al comprar bolsa pp", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("nocomprabolsa");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("clienteOrden", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al comprar bolsa pp", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("nocomprabolsa");
        }

    }
    
    /**
     * Obtener el saldo del equipo prepago. 
     * @param msisdn
     * @return ResumenPlan,   null si se presenta una excepcion 
     */
    private ResumenPlan obtenerPlanResumen(final String msisdn) {
        ResumenPlan resumenPlan = null;
        try {
            resumenPlan = internetMovilDelegate.obtenerResumenPlanPP(msisdn);
        } catch (Exception e) {
            LOGGER.error("No se pudo obtener resumen plan :"+msisdn,e);
        }
        return resumenPlan;
    }

    /**
     * @return the bolsasADBean
     */
    public BolsasActualesDisponiblesBean getBolsasADBean() {
        return bolsasADBean;
    }

    /**
     * @param bolsasADBean
     *            the bolsasADBean to set
     */
    public void setBolsasADBean(BolsasActualesDisponiblesBean bolsasADBean) {
        this.bolsasADBean = bolsasADBean;
    }

    /**
     * @return the respuestaJson
     */
    public String getRespuestaJson() {
        return respuestaJson;
    }

    /**
     * @param respuestaJson
     *            the respuestaJson to set
     */
    public void setRespuestaJson(String respuestaJson) {
        this.respuestaJson = respuestaJson;
    }

    /**
     * @return the bolsasDispRegalo
     */
    public List<BolsaBean> getBolsasDispRegalo() {
        return bolsasDispRegalo;
    }

    /**
     * @param bolsasDispRegalo the bolsasDispRegalo to set
     */
    public void setBolsasDispRegalo(List<BolsaBean> bolsasDispRegalo) {
        this.bolsasDispRegalo = bolsasDispRegalo;
    }

    /**
     * @return the bolsasActualesPP
     */
    public List<BolsaPPBean> getBolsasActualesPP() {
        return bolsasActualesPP;
    }

    /**
     * @param bolsasActualesPP the bolsasActualesPP to set
     */
    public void setBolsasActualesPP(List<BolsaPPBean> bolsasActualesPP) {
        this.bolsasActualesPP = bolsasActualesPP;
    }
    
    /**
     * Cantidad minima para considerar un plan ilimitado
     * @return cantMbIlimitado
     */
    public double getCantidadMbIlimitado(){
        return cantMbIlimitado;
    }
}