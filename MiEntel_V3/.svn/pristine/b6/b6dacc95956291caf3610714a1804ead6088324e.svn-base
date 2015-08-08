/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.recursoti.configuracion.ArrayUtils;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

/**
 * Phase Listener que implementa mecanismo de seguridad basado en Atributo de
 * Auto Atencion
 * 
 * @author jlopez (I2B) en nombre de <contraparte> (<area>, <cliente>)
 * 
 */
public class AAASecurityPhaseListener implements PhaseListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para MiEntelAAASecurityPhaseListener
     */
    private static final Logger LOGGER = Logger
            .getLogger(AAASecurityPhaseListener.class);
    
    @Override
    public void afterPhase(PhaseEvent arg0) {

        FacesContext fc = FacesContext.getCurrentInstance();
        
        PortletPreferences preferences = JSFPortletHelper
                .getPreferencesObject();

        //nombre portlet preference almacenado en properties
        String preferenceName = MiEntelProperties
                .getProperty("miEntel.aaa.security");        
        
        String tieneAAASeguridad = JSFPortletHelper.getPreference(preferences,
                preferenceName, "false");
        if(tieneAAASeguridad.equals("true")) {
            
            LOGGER.info("'" + preferenceName + "' definido. Validando...");
            
            //nombre portlet preference almacenado en properties
            String reglasPreference = MiEntelProperties
                    .getProperty("miEntel.aaa.security.rules");
            
            String reglas = JSFPortletHelper.getPreference(preferences,
                    reglasPreference, "");
            
            if(Utils.isEmptyString(reglas)) {
                LOGGER.warn("Portlet tiene definida seguridad AAA pero no define las reglas");
                
            } else {
                LOGGER.info("Reglas seguridad AAA : '" + reglas + "'");
                if(checkAAASecurity(fc, reglas)) {
                    LOGGER.info("Acceso permitido");
                } else {
                    LOGGER.warn("Acceso denegado");
                    NavigationHandler nh = fc.getApplication().getNavigationHandler();
                    nh.handleNavigation(fc, null, "aaa-security-access-denied");
                }
            }
        }

    }

    private boolean checkAAASecurity(FacesContext fc, String reglas) {

        HttpServletRequest request = JSFPortletHelper.getRequest(fc);
        ProfileWrapper profile = ProfileWrapperHelper.getProfile(request);

        try {
            String aaa = ProfileWrapperHelper.getPropertyAsString(profile,
                    "aaa");
            LOGGER.info("aaa presente: " + aaa);
            return containsAAA(aaa, parseReglas(reglas));
            
        } catch (Exception e) {
            LOGGER.error("Exception caught al validar AAAsecurity", e);
            return false;
        }

    }

    private boolean containsAAA(String aaa, String[] array) {
        
        return ArrayUtils.contains(array, aaa);
    }
    
    private String[] parseReglas(String reglas) {
        
        String separator = MiEntelProperties
                .getProperty("miEntel.aaa.security.rules.separator");

        return ArrayUtils.parse(reglas, separator); 
    }
    
    @Override
    public void beforePhase(PhaseEvent arg0) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
