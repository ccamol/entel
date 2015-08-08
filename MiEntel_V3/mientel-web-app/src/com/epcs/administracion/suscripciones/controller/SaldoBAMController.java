/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.administracion.suscripciones.controller;

import javax.faces.event.PhaseEvent;
import javax.portlet.PortletPreferences;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResumenTraficoBAMCCBean;
import com.epcs.bean.SaldoBAMBean;
import com.epcs.billing.registrouso.delegate.TraficoBamCCDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class SaldoBAMController {

    /**
     * Logger para SaldoBAMController
     */
    private static final Logger LOGGER = Logger
            .getLogger(SaldoBAMController.class);
    private String saldo;
    private String fechaExpiracion;
    private String respuestaJson;
    private TraficoBamCCDelegate miTraficoBAMCCDelegate;
    
    /**
     * @return the saldo
     */
    public String getSaldo() {
        return saldo;
    }
    
    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
    
    /**
     * @return the fechaExpiracion
     */
    public String getFechaExpiracion() {
        return fechaExpiracion;
    }
    
    /**
     * @param fechaExpiracion the fechaExpiracion to set
     */
    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
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

    public void init(PhaseEvent event) {
        try {
            loadData();
        } catch (DAOException e) {
            LOGGER.error("Error al obtener datos de saldo", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al obtener datos de saldo");
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al obtener datos de saldo");
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener datos de saldo", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al obtener datos de saldo");
        }
    }
    
    private void loadData() throws DAOException, ServiceException, Exception {
        ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        
        String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcs");
        
        //Se indica al metodo que NO REGISTRE en el log de la caja de Facturacion del Dashboard 
    	boolean logCajaFacturacion = false;
        ResumenTraficoBAMCCBean trafico = miTraficoBAMCCDelegate.consultarTraficoBAMCC(msisdn,logCajaFacturacion);
        
        SaldoBAMBean saldo = new SaldoBAMBean();
        saldo.setSaldo(Utils.formatMoneyPuntos(trafico.getSaldoMonto()));
        saldo.setFechaExpiracion(DateHelper.format(trafico.getFechaExpiracion(), DateHelper.FORMAT_ddMMyyyy_SLASH));        
        
        respuestaJson = JsonHelper.toJsonResponse(saldo);
    }
}
