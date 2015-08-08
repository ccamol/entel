/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.ishop.suscripciones.controller;

import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResumenSuscripcionesOrqBean;
import com.epcs.ishop.suscripciones.delegate.IShopSuscripcionesDelegate;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.exception.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class IShopSuscripcionesController {

    /**
     * Logger para IShopSuscripcionesController
     */
    private static final Logger LOGGER = Logger
            .getLogger(IShopSuscripcionesController.class);
    private IShopSuscripcionesDelegate iShopSuscripcionesDelegate;
    private List<ResumenSuscripcionesOrqBean> suscripciones;
    private String iShopLink;
    private String respuestaJson;

    /**
     * @return the iShopSuscripcionesDelegate
     */
    public IShopSuscripcionesDelegate getiShopSuscripcionesDelegate() {
        return iShopSuscripcionesDelegate;
    }

    /**
     * @param iShopSuscripcionesDelegate the iShopSuscripcionesDelegate to set
     */
    public void setiShopSuscripcionesDelegate(
            IShopSuscripcionesDelegate iShopSuscripcionesDelegate) {
        this.iShopSuscripcionesDelegate = iShopSuscripcionesDelegate;
    }
    
    /**
     * @return the suscripciones
     */
    public List<ResumenSuscripcionesOrqBean> getSuscripciones() {
        return suscripciones;
    }

    /**
     * @return the iShopLink
     */
    public String getiShopLink() {
        return iShopLink;
    }

    /**
     * @param iShopLink the iShopLink to set
     */
    public void setiShopLink(String iShopLink) {
        this.iShopLink = iShopLink;
    }

    /**
     * @return the respuestaJson
     */
    public String getRespuestaJson() {
        return respuestaJson;
    }

    /**
     * @param respuestaJson the respuestaJson to set
     */
    public void setRespuestaJson(String respuestaJson) {
        this.respuestaJson = respuestaJson;
    }

    public void init(PhaseEvent event) {
        try {
            loadData();
        } catch (DAOException e) {
            LOGGER.error("Error al obtener datos de suscripciones", e);
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener datos de suscripciones", e);
        }
    }
    
    private void loadData() throws DAOException, ServiceException, Exception {
        HttpServletRequest request = JSFPortletHelper.getRequest();
        ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
        
        String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");
        
        iShopLink = MiEntelProperties.getProperty("iShop.suscripciones.iShopLink");        
        suscripciones = this.iShopSuscripcionesDelegate.obtenerSuscripcionesUsuarioOrq(msisdn);
    }
    
    public void activarSuscripcion(PhaseEvent event) {
        try {
            HttpServletRequest request = JSFPortletHelper.getRequest();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
            
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");
            String idSuscripcion = JsfUtil.getRequestParameter("idSuscripcion");
            
            this.iShopSuscripcionesDelegate.suscribir(msisdn, idSuscripcion);
            respuestaJson = JsonHelper.toJsonResponse("Ok");
        } catch (DAOException e) {
            LOGGER.error("Error al activar suscripcion", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al activar suscripcion");            
        } catch (ServiceException e) {
            LOGGER.info("Error al activar suscripcion: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al activar suscripcion");
        } catch (Exception e) {
            LOGGER.error("Error al activar suscripcion", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al activar suscripcion");
        }        
    }
    
    public void desactivarSuscripcion(PhaseEvent event) {
        try {
            HttpServletRequest request = JSFPortletHelper.getRequest();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
            
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");
            String idSuscripcion = JsfUtil.getRequestParameter("idSuscripcion");
            
            this.iShopSuscripcionesDelegate.desuscribir(msisdn, idSuscripcion);
            respuestaJson = JsonHelper.toJsonResponse("Ok");
        } catch (DAOException e) {
            LOGGER.error("Error al desactivar suscripcion", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al activar suscripcion");            
        } catch (ServiceException e) {
            LOGGER.info("Error al desactivar suscripcion: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al activar suscripcion");
        } catch (Exception e) {
            LOGGER.error("Error al desactivar suscripcion", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al activar suscripcion");
        }        
    }    
}
