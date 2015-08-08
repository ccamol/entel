/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.ishop.agendamiento.controller;

import java.util.LinkedList;
import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResumenContenidoBean;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.ishop.agendamiento.delegate.IShopAgendamientoDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.exception.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class IShopSMSMMSInfoController {
    
    /**
     * Logger para IShopSMSMMSInfoController
     */
    private static final Logger LOGGER = Logger
            .getLogger(IShopSMSMMSInfoController.class);
    private static final String AGENDAMIENTO_SMS = MiEntelProperties
            .getProperty("iShop.agendamiento.tipoServicio.SMS");
    private static final String AGENDAMIENTO_MMS = MiEntelProperties
            .getProperty("iShop.agendamiento.tipoServicio.MMS");
    private IShopAgendamientoDelegate iShopAgendamientoDelegate;
    private SeguridadDelegate seguridadDelegate;
    private List<ResumenContenidoBean> contenidoSMS;
    private List<ResumenContenidoBean> contenidoMMS;
    private String formAction;
    private String linkVerMas;
    
    /**
     * @return the iShopAgendamientoDelegate
     */
    public IShopAgendamientoDelegate getiShopAgendamientoDelegate() {
        return iShopAgendamientoDelegate;
    }

    /**
     * @param iShopAgendamientoDelegate the iShopAgendamientoDelegate to set
     */
    public void setiShopAgendamientoDelegate(
            IShopAgendamientoDelegate iShopAgendamientoDelegate) {
        this.iShopAgendamientoDelegate = iShopAgendamientoDelegate;
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
     * @return the contenidoSMS
     */
    public List<ResumenContenidoBean> getContenidoSMS() {
        return contenidoSMS;
    }
    
    /**
     * @return the contenidoMMS
     */
    public List<ResumenContenidoBean> getContenidoMMS() {
        return contenidoMMS;
    }    
    
    /**
     * @return the formAction
     */
    public String getFormAction() {
        return formAction;
    }

    /**
     * @param formAction the formAction to set
     */
    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    /**
     * @return the linkVerMas
     */
    public String getLinkVerMas() {
        return linkVerMas;
    }

    /**
     * @param linkVerMas the linkVerMas to set
     */
    public void setLinkVerMas(String linkVerMas) {
        this.linkVerMas = linkVerMas;
    }
    
    /**
     * Inicializa los datos para el portlet de SMS-MMS Info
     * @param event
     */
    public void init(PhaseEvent event) {
        try {
            loadInfo();
        } catch (DAOException e) {
            LOGGER.error("Error al obtener datos de SMS-MMS info", e);
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener datos de SMS-MMS info", e);
        }
    }
    
    private void loadInfo() throws DAOException, ServiceException, Exception {
        HttpServletRequest request = JSFPortletHelper.getRequest();
        ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(request);
        String msisdn = ProfileWrapperHelper.getPropertyAsString(
                profileWrapper, "numeroPcs");        
        
        final String url = MiEntelProperties.getProperty("iShop.sms-mms-info.formAction");        
        String idp = this.seguridadDelegate.consultarIDP(msisdn);
        
        formAction = url.replace("{0}", idp);
        linkVerMas = MiEntelProperties.getProperty("iShop.sms-mms-info.linkVerMas");
        
        contenidoSMS = new LinkedList<ResumenContenidoBean>();
        contenidoMMS = new LinkedList<ResumenContenidoBean>();
        contenidoSMS.addAll(this.iShopAgendamientoDelegate.consultarContenido(AGENDAMIENTO_SMS));        
        contenidoMMS.addAll(this.iShopAgendamientoDelegate.consultarContenido(AGENDAMIENTO_MMS));
    }    

}
