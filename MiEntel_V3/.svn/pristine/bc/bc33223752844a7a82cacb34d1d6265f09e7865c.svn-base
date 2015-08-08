/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.uup;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bea.p13n.usermgmt.SessionHelper;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.billing.balance.util.ArcFour;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;

/**
 * Clase utilitaria para el manejo del {@link ProfileWrapper} dentro de MiEntel
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ProfileWrapperHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String USER_PROFILE = MiEntelProperties
            .getProperty("miEntel.userProfile");
    
    public static ProfileWrapper getProfile(HttpServletRequest request) {
        return SessionHelper.getProfile(request);
    }

    public static void removeProfile(HttpServletRequest request) {
        SessionHelper.removeProfile(request);
    }
    
//    public static ProfileWrapper getProfile(HttpServletRequest request) {
//        return SessionHelper.getProfile(SessionHelper.getSession(request));
//    }
//    

    public static ProfileWrapper getProfile(HttpSession session) {
        return SessionHelper.getProfile(session);
    }

    public static Object getProperty(ProfileWrapper profileWrapper,
            String property) throws Exception {
        return profileWrapper.getProperty(USER_PROFILE, property);
    }

    public static String getPropertyAsString(ProfileWrapper profileWrapper,
            String property) throws Exception {
    	
    	if (property.equals("numeroPcsEncriptado")) {
    		String numeroPcs = getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
    		String llaveEncriptacion = MiEntelProperties.getProperty("miEntel.userProfile.llaveEncriptacion");
    		return Utils.encriptar(numeroPcs, llaveEncriptacion);    		
    	}
    	
        return profileWrapper.getPropertyAsString(USER_PROFILE, property);
    }

    public static int getPropertyAsInt(ProfileWrapper profileWrapper,
            String property) throws Exception, NumberFormatException {
        String value = getPropertyAsString(profileWrapper, property);
        return Integer.parseInt(value);
    }

    public static void setProperty(ProfileWrapper profileWrapper,
            String property, Object value) throws Exception {
        profileWrapper.setProperty(USER_PROFILE, property, value);
    }

    public static void setProperty(ProfileWrapper profileWrapper,
            String property, String value) throws Exception {
        profileWrapper.setProperty(USER_PROFILE, property, value);
    }

    public static void setProperty(ProfileWrapper profileWrapper,
            String property, int value) throws Exception {
        profileWrapper.setProperty(USER_PROFILE, property, Integer.valueOf(value));
    }

}
