/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.controller;

import javax.faces.event.PhaseEvent;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.dashboard.mensajes.util.MensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesManager;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

/**
 * Controller para el portlet de mensajes para ti. Este controller carga las
 * variables iniciales de mensajes y probee un metodo que retorna como Json los
 * mensajes.
 * 
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * @version 1.0 Version inicial
 * 
 * @author jlopez (I2B) en nombre Absalon Opazo (Atencion al Cliente, EntelPcs)
 * @version 2.0 Refactoring de Mensajes Json
 */
public class MensajesParaTiController {

    private static final Logger LOGGER = Logger
            .getLogger(MensajesParaTiController.class);
    
    /**
     * String con valores separados por coma (',') con id servicios a consultar 
     * para el usuario segun su AAA
     */
    private String idServicios;

    /**
     * URL comun para obtener los mensajes de cada servicio
     */
    private String commonURL;
    
    /**
     * Cantidad de mensajes por paginacion del portlet de MPT
     */
    private String itemsPorPag;
    
    /**
     * Manager de mensajes para ti 
     */
    private MensajesManager mensajesManager;
    
    public void init(PhaseEvent event) {
        try {
            HttpServletRequest request = JSFPortletHelper.getRequest();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);

            String aaa = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "aaa");
            idServicios = this.findIdServiciosPreference(aaa);
            
            if(Utils.isEmptyString(idServicios)) {
                LOGGER.warn("No se encontro Portlet preference de Mensajes para Ti, para AAA " + aaa);
            }
        } catch (Exception e) {
            LOGGER.error("Exception al inicializar MensajesParaTiController", e);
        }
    }

    private String findIdServiciosPreference(String aaa) {
        PortletPreferences prefs = JSFPortletHelper.getPreferencesObject();
        return JSFPortletHelper.getPreference(prefs, "aaa" + aaa, "");
    }
    
    /**
     * @return String URL comun para obtener mensajes de cada servicio
     */
    public String getMensajesCommonURL() {
        if (Utils.isEmptyString(commonURL)) {
            HttpServletRequest request = JSFPortletHelper.getRequest();
            commonURL = request.getContextPath()
                    + MiEntelProperties
                            .getProperty("mensajesParaTi.urlRespuesta");
        }

        return commonURL;
    }

    /**
     * @return String con el valor de cantidad de mensajes que deben mostarse
     *         por pagina del portlet Mensajes para ti
     */
    public String getItemsPorPagina() {
        if (itemsPorPag == null) {
            itemsPorPag = MiEntelProperties
                    .getProperty("mensajesParaTi.itemsPorPag");
        }
        return itemsPorPag;
    }

    public MensajesManager getMensajesManager() {
        return mensajesManager;
    }

    public void setMensajesManager(MensajesManager mensajesManager) {
        this.mensajesManager = mensajesManager;
    }

    /**
     * @return the jsonServicio
     */
    public String getJsonServicio() {
        return this.obtenerMensajeJson();
    }

    /**
     * @return String con los id de servicios, separados por coma (',') a
     *         consultar para el usuario
     */
    public String getIdServicios() {
        return idServicios;
    }

    /**
     * @return Mensaje generico para el caso en que no hay mensajes para el usuario
     */
    public String getNoHayMensajes() {
        return MensajesParaTiHelper.getNoHayMensajes();
    }

    
    /**
     * Metodo que obtiene el Json con el mensaje correspondiente al servicio
     * especificado por el parametro 'id' del request
     */
    private String obtenerMensajeJson() {

        String jsonServicio = "";
        
        try {
            //Perfil de usuario para resolver mensajes
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            
            //Parametro id indica que mensaje se solicita
            String mensajeId = JsfUtil.getRequestParameter("id");
            
            if(Utils.isEmptyString(mensajeId)) {
                LOGGER.error( new Exception("mensajeId no valido: " + mensajeId));
            }
            
            //Handler se obtiene segun id del mensaje
            MensajeHandler handler = mensajesManager.getMensajeHandler(mensajeId);
            if(handler == null) {
                LOGGER.error( new Exception("MensajeHandler no definido para mensajeId " + mensajeId));
            }
            MensajeParaTiBean bean = handler.getMensaje(profile);
            
            //Mensaje en Json
            jsonServicio = JsonHelper.toJson(bean);
            
        } catch (Exception e) {
            LOGGER.error("Exception inesperada al obtener Mensaje Para Ti", e);
            jsonServicio = JsonHelper
                    .toJsonServiceErrorMessage("mensajesParaTi");
        }
        
        return jsonServicio;
    }

}
