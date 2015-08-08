package com.epcs.recursoti.configuracion.backingfile;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bea.netuix.servlets.controls.content.backing.AbstractJspBacking;
import com.bea.netuix.servlets.controls.portlet.backing.PortletBackingContext;
import com.bea.p13n.security.Authentication;
import com.bea.p13n.usermgmt.SessionHelper;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.bea.portlet.GenericURL;
import com.epcs.recursoti.configuracion.MiEntelProperties;

public class LoginBacking extends AbstractJspBacking {
	private static final String DEFAULT_PLAN_TYPE_ATTRIBUTE = MiEntelProperties.getProperty("miEntel.userProfile.mercado.property");

	private static final String DEFAULT_MIENTEL_PROPERTIES = MiEntelProperties.getProperty("miEntel.userProfile");

	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_USERNAME_CREDENTIAL = "miEntel.movil.tag";
	private static final String DEFAULT_RUT_CREDENTIAL = "miEntel.rut.tag";
	private static final String DEFAULT_PASSWORD_CREDENTIAL = "miEntel.clave.tag";
	private static final String DEFAULT_MOVIL_RUT_SEPARATOR_CHAR = "miEntel.separador.movil_rut";
	
    private static final Logger LOGGER = Logger.getLogger(LoginBacking.class);

	public boolean handlePostbackData(HttpServletRequest request,
            HttpServletResponse response) {
        if (isRequestTargeted(request)) {
            if (request.getParameter(GenericURL.STATE_PARAM) == null) {
                
                String username = request.getParameter(MiEntelProperties
                        .getProperty(DEFAULT_USERNAME_CREDENTIAL));
                String password = request.getParameter(MiEntelProperties
                        .getProperty(DEFAULT_PASSWORD_CREDENTIAL));
                String rut = request.getParameter(MiEntelProperties
                        .getProperty(DEFAULT_RUT_CREDENTIAL));

                if (username != null && password != null) {
                    try {
                        Authentication
                                .login(
                                        username
                                                + MiEntelProperties
                                                        .getProperty(DEFAULT_MOVIL_RUT_SEPARATOR_CHAR)
                                                + rut, password, request,
                                        response);
                        ProfileWrapper profile = SessionHelper
                                .getProfile(request);
                        if (profile==null || profile.getProperty(DEFAULT_MIENTEL_PROPERTIES, DEFAULT_PLAN_TYPE_ATTRIBUTE)==null){
                            SessionHelper.getSession(request).setAttribute(
                                    "no_profile", "true");
                        }
                        PortletBackingContext pbc = PortletBackingContext
                                .getPortletBackingContext(request);
                        
                        try {
                            GenericURL url = GenericURL.createGenericURL(request, response);
                            url.setTemplate("login-https");
//                            pbc.sendRedirect(Utils.getMiEntelBaseURL(request));
                            pbc.sendRedirect(url.toString(true));
                        } catch (Exception ie) {
                            LOGGER.error(
                            		"No ha sido posible redireccionar despues de hacer autenticacion.", ie);
                        }
                    } catch (LoginException le) {
                        SessionHelper.getSession(request).setAttribute(
                                "no_autentica", "true");
                    } catch (Exception e) {
                        LOGGER.error(
                        		"No fue posible obtener las propiedades del perfil del usuario", e);
                        SessionHelper.getSession(request).setAttribute(
                                "error_aplicacion", "true");
                    }
                }
                else if (request.getParameter("logout") != null) {
                    Authentication.logout(request);
                }
            }
        }
        return true;
    }
}