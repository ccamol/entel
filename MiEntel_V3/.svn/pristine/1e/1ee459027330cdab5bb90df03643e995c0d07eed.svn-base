/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.Normalizer;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.PlanBAMBean;
import com.epcs.bean.ResumenTraficoBAMCCBean;
import com.epcs.bean.TraficoBamCCBean;
import com.epcs.billing.registrouso.delegate.TraficoBamCCDelegate;
import com.epcs.cliente.perfil.util.PlanHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.error.ServiceMessages;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class MiTraficoBamCCController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(MiTraficoBamCCController.class);
    
    private static final Logger CAJA_TRAFICO_LOGGER = Logger.getLogger("cajaTraficoLog");

    public static final String PLAN_BAM_ILIMITADO = MiEntelProperties
            .getProperty("parametros.planesbam.ilimitado");
    
    public static final String PLAN_BAM_ILIMITADO_LABEL = MiEntelProperties
    .getProperty("parametros.planesbam.ilimitadoLabel");

    private TraficoBamCCDelegate traficoBAMCCDelegate;

    private String respuestaJson;

    private PlanBAMBean planBamBean;

    private ResumenTraficoBAMCCBean resumenTraficoBAMCCBean;

    private TraficoBamCCBean traficoBamCCBean;

    /**
     * Portlet trafico dashboard cc
     * 
     * @param event
     */
    public void init(final PhaseEvent event) {

        String numeroPcsSel = "";
        String autoAtencion = "";
        String mercado = "";
        String rutSeleccionado = "";

        try {

            final ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            numeroPcsSel = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcsSeleccionado");
            rutSeleccionado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "rutUsuarioSeleccionado");
            autoAtencion = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "aaa");
            mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
                    "mercado");

            CAJA_TRAFICO_LOGGER.info("INICIO REGISTRO LOG CAJA TRAFICO BAM");
        	CAJA_TRAFICO_LOGGER.info("****** Clase: com.epcs.billing.registrouso.controller.MiTraficoBamCCController ******");            
            CAJA_TRAFICO_LOGGER.info("Movil: " + numeroPcsSel + " - Rut: " + rutSeleccionado);
            
            //Se indica al metodo que REGISTRE en el log de la caja de Facturacion del Dashboard 
        	boolean logCajaFacturacion = true;
            resumenTraficoBAMCCBean = this.traficoBAMCCDelegate
                    .consultarTraficoBAMCC(numeroPcsSel,logCajaFacturacion);
            planBamBean = this.traficoBAMCCDelegate.obtenerPlanActualBAMSSCC(
                    numeroPcsSel, mercado, autoAtencion, logCajaFacturacion);

            if (resumenTraficoBAMCCBean != null) {
                this.buildTrafico();
            }
            else {
            	ServiceMessages errorMessages = MiEntelProperties.getServiceMessages();
		        CAJA_TRAFICO_LOGGER.warn(errorMessages.getErrorMessage("noinfotraficobam"));
		        
                respuestaJson = JsonHelper
                        .toJsonServiceErrorMessage("noinfotraficobam");
            }
            
            CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO BAM");

        } catch (DAOException e) {
        	CAJA_TRAFICO_LOGGER.error("DAOException al buscar otros datos de Usuario", e);
        	CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO BAM");
            JsonHelper.toJsonServiceErrorMessage("noDisponible");
        } catch (ServiceException e) {
        	CAJA_TRAFICO_LOGGER.info("ServiceException al buscar otros datos de Usuario. msisdn: "+numeroPcsSel);
        	CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO BAM");
            JsonHelper.toJsonServiceErrorMessage("gestionRegistroUso", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
        	CAJA_TRAFICO_LOGGER.error("Exception no esperada al buscar datos de usuario", e);
        	CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO BAM");
            JsonHelper.toJsonServiceErrorMessage("inesperado");
        }
    }

    /**
     * 
     */
    private void buildTrafico() {
        traficoBamCCBean = new TraficoBamCCBean();
        String saldoNavegacion = resumenTraficoBAMCCBean.getSaldoNavegacion();
        if (!"--".equals(saldoNavegacion) && !"".equals(saldoNavegacion)) {
            double saldoNav = Double.valueOf(saldoNavegacion);
            saldoNavegacion = obtenerCuotaRestante(saldoNav);
            traficoBamCCBean.setCuotaRestante(saldoNavegacion);
            String umbralFairUSe = planBamBean.getUmbralFairUseMb();
            try {
            	String umfu = umbralFairUSe;
            	umfu = Normalizer.normalize(umfu, Normalizer.Form.NFD);
            	umfu = umfu.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
                if (PLAN_BAM_ILIMITADO.equalsIgnoreCase(umfu)) {
                    traficoBamCCBean.setIlimitado(true);
                    traficoBamCCBean.setPorcentajeCuotaRestante(100);
                    if (saldoNav == resumenTraficoBAMCCBean.getSaldoMonto()) {
                        traficoBamCCBean.setSaldoMonto(String
                                .valueOf(new Double(saldoNav).intValue()));
                    }
                    else {
                        traficoBamCCBean.setCuotaRestante(PLAN_BAM_ILIMITADO);
                    }
                }
                else {
                    final double umbralFair = Double.parseDouble(umbralFairUSe);
                    this.configurarUmbral(umbralFair);
                    traficoBamCCBean
                            .setPorcentajeCuotaRestante(getPorcentajeUtilizado(
                                    umbralFair, saldoNav));
                }
            } catch (Exception e) {
            	CAJA_TRAFICO_LOGGER.warn("No fue posible calcular el porcentaje restante de navegacion :", e);
            }

            respuestaJson = JsonHelper.toJsonResponse(traficoBamCCBean);

        }
        else {
        	ServiceMessages errorMessages = MiEntelProperties.getServiceMessages();
	        CAJA_TRAFICO_LOGGER.warn(errorMessages.getErrorMessage("noinfotraficobam"));
	        
            respuestaJson = JsonHelper
                    .toJsonServiceErrorMessage("noinfotraficobam");
        }

    }

    public void initTrafico(final PhaseEvent event) {

        try {

            final ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            final String numeroPcsSel = ProfileWrapperHelper
                    .getPropertyAsString(profileWrapper,
                            "numeroPcsSeleccionado");
            final String autoAtencion = ProfileWrapperHelper
                    .getPropertyAsString(profileWrapper, "aaa");
            final String mercado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "mercado");

            //Se indica al metodo que NO REGISTRE en el log de la caja de Facturacion del Dashboard 
        	boolean logCajaFacturacion = false;
            resumenTraficoBAMCCBean = this.traficoBAMCCDelegate
                    .consultarTraficoBAMCC(numeroPcsSel,logCajaFacturacion);
            planBamBean = this.traficoBAMCCDelegate.obtenerPlanActualBAMSSCC(
                    numeroPcsSel, mercado, autoAtencion, logCajaFacturacion);

            if (resumenTraficoBAMCCBean != null) {
                traficoBamCCBean = this.buildMiTraficoBAMCC();
            }
            else {
                JSFMessagesHelper.addServiceErrorMessage("noinfotraficobam");
            }

        } catch (DAOException e) {
            LOGGER.error("DAOException al buscar otros datos de Usuario", e);
            JSFMessagesHelper.addServiceErrorMessage("noDisponible");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar otros datos de Usuario");
            JSFMessagesHelper.addErrorMessage("gestionRegistroUso", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception no esperada al buscar datos de usuario", e);
            JSFMessagesHelper.addServiceErrorMessage("inesperado");
        }
    }

    /**
     * 
     * @return
     */
    private TraficoBamCCBean buildMiTraficoBAMCC() {
        String saldoNavegacion = resumenTraficoBAMCCBean.getSaldoNavegacion();

        if (!"--".equals(saldoNavegacion) && !"".equals(saldoNavegacion)) {
            traficoBamCCBean = new TraficoBamCCBean();
            traficoBamCCBean.setNombrePlan(PlanHelper.extraerNombrePlan(planBamBean.getNombrePlan()));
            traficoBamCCBean.setValorTraficoAdicional(planBamBean
                    .getValorAdicionalMB());
            traficoBamCCBean.setVelocidadMaxPlan(planBamBean
                    .getVelocidadMaxPlan());
            traficoBamCCBean.setVelocidadMaxPlanValor(traficoBamCCBean.getVelocidadMaxPlan().split(" ")[0]);
            traficoBamCCBean.setVelocidadMaxPlanUnidad(traficoBamCCBean.getVelocidadMaxPlan().split(" ")[1]);            

            double saldoNav = Double.valueOf(saldoNavegacion);
            saldoNavegacion = obtenerCuotaRestante(saldoNav);
            traficoBamCCBean.setCuotaRestante(saldoNavegacion.substring(0, saldoNavegacion.length()-2));
            traficoBamCCBean.setCuotaRestanteUnidad(saldoNavegacion.substring(saldoNavegacion.length()-2,saldoNavegacion.length()));
            String umbralFairUSe = planBamBean.getUmbralFairUseMb();
            try {
            	String umfu = umbralFairUSe;
            	umfu = Normalizer.normalize(umfu, Normalizer.Form.NFD);
            	umfu = umfu.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
                if (PLAN_BAM_ILIMITADO.equalsIgnoreCase(umfu)) {
                    traficoBamCCBean.setPorcentajeCuotaRestante(100);
                    traficoBamCCBean.setIlimitado(true);
                    if (saldoNav == resumenTraficoBAMCCBean.getSaldoMonto()) {
                        traficoBamCCBean.setSaldoMonto(String
                                .valueOf(new Double(saldoNav).intValue()));
                    }
                    else {
                        traficoBamCCBean.setCuotaRestante(PLAN_BAM_ILIMITADO);
                    }
                    //traficoBamCCBean.setUmbralFairUse(PLAN_BAM_ILIMITADO);
                    traficoBamCCBean.setUmbralFairUse(PLAN_BAM_ILIMITADO_LABEL);
                }
                else {
                    double umbralFair = Double.parseDouble(umbralFairUSe);
                    this.configurarUmbral(umbralFair);
                    traficoBamCCBean
                            .setPorcentajeCuotaRestante(getPorcentajeUtilizado(
                                    umbralFair, saldoNav));
                }
            } catch (Exception e) {
                LOGGER
                        .warn(
                                "No fue posible calcular el porcentaje restante de navegacion :",
                                e);
            }
        }
        else {
            traficoBamCCBean = null;
            JSFMessagesHelper.addServiceErrorMessage("noinfotraficobam");
        }
        return traficoBamCCBean;
    }
    
    /**
     * Portlet Saldo BAM CC FDT
     * @param event
     */
    public void initSaldoFDT(PhaseEvent event) {
        try {
            final ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            final String numeroPcsSel = ProfileWrapperHelper
                    .getPropertyAsString(profileWrapper,
                            "numeroPcsSeleccionado");
            final String autoAtencion = ProfileWrapperHelper
                    .getPropertyAsString(profileWrapper, "aaa");
            final String mercado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "mercado");

            //Se indica al metodo que NO REGISTRE en el log de la caja de Facturacion del Dashboard 
        	boolean logCajaFacturacion = false;
            resumenTraficoBAMCCBean = this.traficoBAMCCDelegate
                    .consultarTraficoBAMCC(numeroPcsSel,logCajaFacturacion);
            planBamBean = this.traficoBAMCCDelegate.obtenerPlanActualBAMSSCC(
                    numeroPcsSel, mercado, autoAtencion, logCajaFacturacion);

            if (resumenTraficoBAMCCBean != null) {
                traficoBamCCBean = this.buildSaldoFDT();
            }
            else {
                JSFMessagesHelper.addServiceErrorMessage("noinfotraficobam");
            }
        } catch (DAOException e) {
            LOGGER.error("DAOException al buscar otros datos de Usuario", e);
            JSFMessagesHelper.addServiceErrorMessage("noDisponible");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar otros datos de Usuario");
            JSFMessagesHelper.addErrorMessage("gestionRegistroUso", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception no esperada al buscar datos de usuario", e);
            JSFMessagesHelper.addServiceErrorMessage("inesperado");
        }        
    }
    
    /**
     * 
     * @return
     */
    private TraficoBamCCBean buildSaldoFDT() {
        String saldoNavegacion = resumenTraficoBAMCCBean.getSaldoNavegacion();

        if (!"--".equals(saldoNavegacion) && !"".equals(saldoNavegacion)) {
            traficoBamCCBean = new TraficoBamCCBean();
            traficoBamCCBean.setNombrePlan(PlanHelper.extraerNombrePlan(planBamBean.getNombrePlan()));
            traficoBamCCBean.setValorTraficoAdicional(planBamBean
                    .getValorAdicionalMB());
            traficoBamCCBean.setVelocidadMaxPlan(planBamBean
                    .getVelocidadMaxPlan());

            double saldoNav = Double.valueOf(saldoNavegacion);
            saldoNavegacion = obtenerCuotaRestante(saldoNav);
            traficoBamCCBean.setCuotaRestante(saldoNavegacion);
            String umbralFairUSe = planBamBean.getUmbralFairUseMb();
            try {
            	String umfu = umbralFairUSe;
            	umfu = Normalizer.normalize(umfu, Normalizer.Form.NFD);
            	umfu = umfu.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
                if (PLAN_BAM_ILIMITADO.equalsIgnoreCase(umfu)) {
                    traficoBamCCBean.setPorcentajeCuotaRestante(100);
                    traficoBamCCBean.setIlimitado(true);
                    if (saldoNav == resumenTraficoBAMCCBean.getSaldoMonto()) {
                        traficoBamCCBean.setSaldoMonto(String
                                .valueOf(new Double(saldoNav).intValue()));
                    }
                    else {
                        traficoBamCCBean.setCuotaRestante(PLAN_BAM_ILIMITADO);
                    }
                    //traficoBamCCBean.setUmbralFairUse(PLAN_BAM_ILIMITADO);
                    traficoBamCCBean.setUmbralFairUse(PLAN_BAM_ILIMITADO_LABEL);
                }
                else {
                    double umbralFair = Double.parseDouble(umbralFairUSe);
                    this.configurarUmbral(umbralFair);
                    traficoBamCCBean
                            .setPorcentajeCuotaRestante(getPorcentajeUtilizado(
                                    umbralFair, saldoNav));
                }
            } catch (Exception e) {
                LOGGER.warn("No fue posible calcular el porcentaje restante de navegacion :",e);
            }
        }
        else {
            traficoBamCCBean = null;
            JSFMessagesHelper.addServiceErrorMessage("noinfotraficobam");
        }
        return traficoBamCCBean;
    }    

    /**
     * Metodo que retorna la cantidad de cuota de navegacion utilizada en la
     * unidad adecuada
     * 
     * @return - Cuota restante
     */
    private void configurarUmbral(final Double cuotaNumerica) {
        double cuota = 0;
        if (cuotaNumerica < 1) { // La unidad son KB
            cuota = cuotaNumerica * 1024;

            traficoBamCCBean.setUmbralFairUse(String.valueOf(cuota));
            traficoBamCCBean.setUnidadUmbralFairUse("KB");
        }
        else if (cuotaNumerica < 1024 && cuotaNumerica >= 1) { // La unidad son
            // MB
            traficoBamCCBean.setUmbralFairUse(String.valueOf(cuotaNumerica
                    .intValue()));
            traficoBamCCBean.setUnidadUmbralFairUse("MB");
        }
        else if (cuotaNumerica >= 1024) { // La unidad son GB
            cuota = cuotaNumerica / 1024;

            DecimalFormat unDecimal = new DecimalFormat("#.#");
            traficoBamCCBean.setUmbralFairUse(String.valueOf(unDecimal
                    .format(cuota)));
            traficoBamCCBean.setUnidadUmbralFairUse("GB");

        }
    }

    /**
     * Metodo que retorna la cantidad de cuota de navegacion utilizada en la
     * unidad adecuada
     * 
     * @return - Cuota restante
     */
    private String obtenerCuotaRestante(final Double cuotaNumerica) {
        StringBuffer cuotaString = new StringBuffer();
        double cuota = 0;
        if (cuotaNumerica < 1) { // La unidad son KB
            cuota = cuotaNumerica * 1024;

            cuotaString.append(cuota);
            cuotaString.append("KB");
        }
        else if (cuotaNumerica < 1024 && cuotaNumerica >= 1) { // La unidad son
            // MB
            cuotaString.append(cuotaNumerica.intValue());
            cuotaString.append("MB");
        }
        else if (cuotaNumerica >= 1024) { // La unidad son GB
            cuota = cuotaNumerica / 1024;

            DecimalFormat unDecimal = new DecimalFormat("#.#");
            cuotaString.append(unDecimal.format(cuota));
            cuotaString.append("GB");
        }
        return cuotaString.toString();
    }

    /**
     * Retorna el porcentaje utilizado en Mod 20.
     * 
     * @return
     */
    private int getPorcentajeUtilizado(final double umbralFairUse,
            final double cuotaRestante) {
        double cuotaUsada = umbralFairUse - cuotaRestante;
        Double ptjRestante = new Double((cuotaUsada / umbralFairUse) * 100);
        
        int porcUtilizado = 100 - (ptjRestante.intValue() - (ptjRestante.intValue() % 20));
        if (porcUtilizado > 100) {
            return 100;
        } else {
            return porcUtilizado;
        }
    }

    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia
     * del portlet en solicitud dependiendo del mercado como clave asociada al
     * valor.
     * 
     * @return
     */
    public String getPageLabelCambioPlan() {
        String pageLabel;
        try {

            final ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            pageLabel = JSFPortletHelper.getPreference(JSFPortletHelper
                    .getPreferencesObject(), ProfileWrapperHelper
                    .getPropertyAsString(profileWrapper, "mercado"), null);

        } catch (Exception e) {
            LOGGER.error("No se ha podido obtener el pageLabel"
                    + e.getMessage(), e);
            pageLabel = "";
        }
        return pageLabel;
    }

    /**
     * @param traficoBAMCCDelegate
     *            the traficoBAMCCDelegate to set
     */
    public void setTraficoBAMCCDelegate(
            final TraficoBamCCDelegate traficoBAMCCDelegate) {
        this.traficoBAMCCDelegate = traficoBAMCCDelegate;
    }

    /**
     * @return the traficoBAMCCDelegate
     */
    public TraficoBamCCDelegate getTraficoBAMCCDelegate() {
        return traficoBAMCCDelegate;
    }

    /**
     * @param respuestaJson
     *            the respuestaJson to set
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
     * @return the traficoBamCCBean
     */
    public TraficoBamCCBean getTraficoBamCCBean() {
        return traficoBamCCBean;
    }

    /**
     * @param traficoBamCCBean
     *            the traficoBamCCBean to set
     */
    public void setTraficoBamCCBean(final TraficoBamCCBean traficoBamCCBean) {
        this.traficoBamCCBean = traficoBamCCBean;
    }

}
