/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.erp.seguridad.util;

import java.util.Calendar;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bea.netuix.servlets.controls.content.backing.AbstractJspBacking;
import com.bea.p13n.security.Authentication;
import com.bea.p13n.usermgmt.SessionHelper;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.bea.portlet.GenericURL;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.backingfile.LoginBacking;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class AccesoNormal extends AbstractJspBacking {
	
	
	private static final String DEFAULT_PLAN_TYPE_ATTRIBUTE = MiEntelProperties.getProperty("miEntel.userProfile.mercado.property");

	private static final String DEFAULT_MIENTEL_PROPERTIES = MiEntelProperties.getProperty("miEntel.userProfile");

	private static final long serialVersionUID = 1L;

    private static final String SEPARATOR_CHAR = MiEntelProperties
            .getProperty("miEntel.separador.movil_rut");
    
    private static final String URL_LINEA_HOGAR_VOZ = MiEntelProperties
            .getProperty("miEntel.lineaHogar.urlVoz");

    private static final String URL_LINEA_HOGAR_DATOS = MiEntelProperties
            .getProperty("miEntel.lineaHogar.urlDatos");

    private static final String SUBMERCADO_LINEA_HOGAR_VOZ = MiEntelProperties
            .getProperty("miEntel.lineaHogar.subMercadoVoz");

    private static final String SUBMERCADO_LINEA_HOGAR_DATOS = MiEntelProperties
            .getProperty("miEntel.lineaHogar.subMercadoDatos");    
	
    private static final Logger LOGGER = Logger.getLogger(LoginBacking.class);

	public boolean acceder(HttpServletRequest request,
            HttpServletResponse response, String username, String rut,
            String password) {

        if (isRequestTargeted(request)) {
            if (request.getParameter(GenericURL.STATE_PARAM) == null) {

                try {
                    String token = String.valueOf(Calendar.getInstance().getTimeInMillis());
                    
                    String loginName = username + SEPARATOR_CHAR + rut
                            + SEPARATOR_CHAR + token;
                    Authentication.login(loginName, password, request, response);
                    ProfileWrapper profile = SessionHelper.getProfile(request);
                    if (profile == null
                            || profile.getProperty(DEFAULT_MIENTEL_PROPERTIES,
                                    DEFAULT_PLAN_TYPE_ATTRIBUTE) == null) {

                        JSFMessagesHelper.addErrorCodeMessage("autenticacion",
                                "perfilamiento");
                    }

                    try {
                        String subMercado = ProfileWrapperHelper
                                .getPropertyAsString(profile, "subMercado");
                        String urlFinal = "";
                        
                        /**
                         * Redireccionamiento para subMercados de lineaHogar
                         */
                        if (subMercado.equals(SUBMERCADO_LINEA_HOGAR_VOZ) || subMercado.equals(SUBMERCADO_LINEA_HOGAR_DATOS)) {
                            String msisdn = ProfileWrapperHelper
                                    .getPropertyAsString(profile, "numeroPcs");
                            SeguridadDelegate seguridadDelegate = new SeguridadDelegate();
                            String idp = seguridadDelegate.consultarIDP(msisdn);
                            if (subMercado.equals(SUBMERCADO_LINEA_HOGAR_VOZ)) {
                                urlFinal = URL_LINEA_HOGAR_VOZ.replace("{IDP}", idp);
                            } else if (subMercado.equals(SUBMERCADO_LINEA_HOGAR_DATOS)) {
                                urlFinal = URL_LINEA_HOGAR_DATOS.replace("{IDP}", idp);                
                            }
                        } else {
                            GenericURL url = GenericURL.createGenericURL(request,
                                    response);
                            url.setTemplate("login-https");
                            urlFinal = url.toString(true);
                        }

                        response.sendRedirect(urlFinal);
                    } catch (Exception ie) {
                        LOGGER
                                .error(
                                        "No ha sido posible redireccionar despues de hacer autenticacion.",
                                        ie);
                    }
                } catch (LoginException le) {

                    JSFMessagesHelper.addErrorCodeMessage("autenticacion",
                            "noautentica");
                } catch (Exception e) {
                    LOGGER
                            .error(
                                    "No fue posible obtener las propiedades del perfil del usuario",
                                    e);

                    JSFMessagesHelper.addErrorCodeMessage("autenticacion",
                            "general");
                }
            }
        }
        return true;
    }
	

}
