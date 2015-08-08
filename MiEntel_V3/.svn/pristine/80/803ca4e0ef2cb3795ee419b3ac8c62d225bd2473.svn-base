/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.ishop.agendamiento.controller;

import java.util.LinkedList;
import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResumenAgendamientoBean;
import com.epcs.ishop.agendamiento.delegate.IShopAgendamientoDelegate;
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
public class IShopAgendamientoController {

    /**
     * Logger para IShopAgendamientoController
     */
    private static final Logger LOGGER = Logger
            .getLogger(IShopAgendamientoController.class);
    private static final String AGENDAMIENTO_SMS = MiEntelProperties
            .getProperty("iShop.agendamiento.tipoServicio.SMS");
    private static final String AGENDAMIENTO_MMS = MiEntelProperties
            .getProperty("iShop.agendamiento.tipoServicio.MMS");
    private IShopAgendamientoDelegate iShopAgendamientoDelegate;    
    private List<ResumenAgendamientoBean> agendamiento; 
    private String respuestaJson;
    private boolean tieneActivos;

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
     * @return the agendamiento
     */
    public List<ResumenAgendamientoBean> getAgendamiento() {
        return agendamiento;
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

    /**
     * @return the tieneActivos
     */
    public boolean isTieneActivos() {
        return tieneActivos;
    }

    /**
     * Inicializa los datos para el portlet de agendamiento
     * @param event
     */
    public void init(PhaseEvent event) {
        try {
            loadData();
        } catch (DAOException e) {
            LOGGER.error("Error al obtener datos de agendamiento", e);
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener datos de agendamiento", e);
        }
    }
    
   /**
    * 
    * @throws DAOException
    * @throws ServiceException
    * @throws Exception
    */
    private void loadData() throws DAOException, ServiceException, Exception {
        HttpServletRequest request = JSFPortletHelper.getRequest();
        ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
        String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");
        
        agendamiento = new LinkedList<ResumenAgendamientoBean>();
        
        List<ResumenAgendamientoBean> agendamientoSMS = this.iShopAgendamientoDelegate.consultarAgendamiento(AGENDAMIENTO_SMS, msisdn);
        List<ResumenAgendamientoBean> agendamientoMMS = this.iShopAgendamientoDelegate.consultarAgendamiento(AGENDAMIENTO_MMS, msisdn);
        
        for (ResumenAgendamientoBean resumenSMS : agendamientoSMS) {
            if (resumenSMS.getStatus().equals("A")) {
                tieneActivos = true;
                break;
            }
        }
        
        if (!tieneActivos) {
            for (ResumenAgendamientoBean resumenMMS : agendamientoMMS) {
                if (resumenMMS.getStatus().equals("false")) {
                    tieneActivos = true;
                    break;
                }
            }
        }
        
        agendamiento.addAll(agendamientoSMS);
        agendamiento.addAll(agendamientoMMS);        
    }
    
    /**
     * Accion ejecutada para pausar un agendamiento (SMS o MMS)
     * @param event
     */
    public void pausarAgendamiento(PhaseEvent event) {
        try {
            HttpServletRequest request = JSFPortletHelper.getRequest();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
            
            String tipoMensajeria = JsfUtil.getRequestParameter("tipoMensajeria");
            String tipo = JsfUtil.getRequestParameter("tipo");            
            String idPedido = JsfUtil.getRequestParameter("idPedido");
            String hora = JsfUtil.getRequestParameter("hora");
            
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");            
            
            this.iShopAgendamientoDelegate.pausarAgendamiento(tipoMensajeria, tipo, idPedido, hora, msisdn);
            respuestaJson = JsonHelper.toJsonResponse("Ok");
        } catch (DAOException e) {
            LOGGER.error("Error al pausar agendamiento", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al pausar agendamiento");            
        } catch (ServiceException e) {
            LOGGER.info("Error al pausar agendamiento: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al pausar agendamiento");
        } catch (Exception e) {
            LOGGER.error("Error al pausar agendamiento", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al pausar agendamiento");
        }
    }
    
    /**
     * Accion ejecutada para reanudar un agendamiento (SMS o MMS)
     * @param event
     */    
    public void despausarAgendamiento(PhaseEvent event) {
        try {
            HttpServletRequest request = JSFPortletHelper.getRequest();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
            
            String tipoMensajeria = JsfUtil.getRequestParameter("tipoMensajeria");
            String tipo = JsfUtil.getRequestParameter("tipo");            
            String idPedido = JsfUtil.getRequestParameter("idPedido");
            String hora = JsfUtil.getRequestParameter("hora");
            
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");            
            
            this.iShopAgendamientoDelegate.despausarAgendamiento(tipoMensajeria, tipo, idPedido, hora, msisdn);
            respuestaJson = JsonHelper.toJsonResponse("Ok");
        } catch (DAOException e) {
            LOGGER.error("Error al despausar agendamiento", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al despausar agendamiento");            
        } catch (ServiceException e) {
            LOGGER.info("Error al despausar agendamiento: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al despausar agendamiento");
        } catch (Exception e) {
            LOGGER.error("Error al despausar agendamiento", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al despausar agendamiento");
        }
    }    
    
    public void eliminarAgendamiento(PhaseEvent event) {
        try {
            HttpServletRequest request = JSFPortletHelper.getRequest();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
            
            String tipoMensajeria = JsfUtil.getRequestParameter("tipoMensajeria");
            String tipo = JsfUtil.getRequestParameter("tipo");            
            String idPedido = JsfUtil.getRequestParameter("idPedido");
            String hora = JsfUtil.getRequestParameter("hora");
            
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");            
            
            this.iShopAgendamientoDelegate.eliminarAgendamiento(tipoMensajeria, tipo, idPedido, hora, msisdn);
            respuestaJson = JsonHelper.toJsonResponse("Ok");
        } catch (DAOException e) {
            LOGGER.error("Error al eliminar agendamiento", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al eliminar agendamiento");            
        } catch (ServiceException e) {
            LOGGER.info("Error al eliminar agendamiento: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al eliminar agendamiento");
        } catch (Exception e) {
            LOGGER.error("Error al eliminar agendamiento", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al eliminar agendamiento");
        }
    }
    
    public void pausarTodosAgendamiento(PhaseEvent event) {
        try {
            HttpServletRequest request = JSFPortletHelper.getRequest();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
            
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");            
            
            this.iShopAgendamientoDelegate.pausarTodosAgendamiento(AGENDAMIENTO_SMS, msisdn);
            this.iShopAgendamientoDelegate.pausarTodosAgendamiento(AGENDAMIENTO_MMS, msisdn);
            respuestaJson = JsonHelper.toJsonResponse("Ok");
        } catch (DAOException e) {
            LOGGER.error("Error al pausar todos los agendamientos", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al pausar todos los agendamientos");            
        } catch (ServiceException e) {
            LOGGER.info("Error al pausar todos los agendamientos: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al pausar todos los agendamientos");
        } catch (Exception e) {
            LOGGER.error("Error al pausar todos los agendamientos", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al pausar todos los agendamientos");
        }
    }
    
    public void despausarTodosAgendamiento(PhaseEvent event) {
        try {
            HttpServletRequest request = JSFPortletHelper.getRequest();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
            
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");            
            
            this.iShopAgendamientoDelegate.despausarTodosAgendamiento(AGENDAMIENTO_SMS, msisdn);
            this.iShopAgendamientoDelegate.despausarTodosAgendamiento(AGENDAMIENTO_MMS, msisdn);
            respuestaJson = JsonHelper.toJsonResponse("Ok");
        } catch (DAOException e) {
            LOGGER.error("Error al despausar todos los agendamientos", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al despausar todos los agendamientos");            
        } catch (ServiceException e) {
            LOGGER.info("Error al despausar todos los agendamientos: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al despausar todos los agendamientos");
        } catch (Exception e) {
            LOGGER.error("Error al despausar todos los agendamientos", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error al despausar todos los agendamientos");
        }
    }    
}
