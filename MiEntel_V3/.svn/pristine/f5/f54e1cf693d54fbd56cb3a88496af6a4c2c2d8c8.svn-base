/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.filter;

import java.io.IOException;
import java.util.Calendar;

import javax.security.auth.login.LoginException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bea.p13n.security.Authentication;
import com.bea.p13n.usermgmt.SessionHelper;
import com.bea.p13n.usermgmt.profile.ProfileNotFoundException;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.bea.portlet.GenericURL;
import com.bea.portlet.PageURL;
import com.epcs.recursoti.configuracion.ExternalAppsHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

/**
 * Servlet Filter implementation class NotificacionesSesion
 * 
 * @author jroman (I2B) en nombre Absalon Opazo (Atencion al Cliente, EntelPcs)
 * 
 */

public class NotificacionesSesion implements Filter {

	private static final Logger LOGGER = Logger.getLogger(NotificacionesSesion.class);

    private static final String DEFAULT_IDP_ATTTR_NAME = MiEntelProperties
    .getProperty("miEntel.login.idp.attr");

	private static final String CONTEXTO_ATTTR_NAME = MiEntelProperties
	.getProperty("miEntel.login.contexto.attr");

    private static final String SEPARATOR_CHAR = MiEntelProperties
    .getProperty("miEntel.separador.movil_rut");

    private static final String URL_LINEA_HOGAR_VOZ = MiEntelProperties
    .getProperty("miEntel.lineaHogar.urlVoz");

	private static final String URL_LINEA_HOGAR_DATOS = MiEntelProperties
	    .getProperty("miEntel.lineaHogar.urlDatos");

	private static final String URL_REGISTROBAM = MiEntelProperties
    .getProperty("miEntel.registroBAM.urlIngresoRUT");

	private static final String SUBMERCADO_LINEA_HOGAR_VOZ = MiEntelProperties
	    .getProperty("miEntel.lineaHogar.subMercadoVoz");

	private static final String SUBMERCADO_LINEA_HOGAR_DATOS = MiEntelProperties
	    .getProperty("miEntel.lineaHogar.subMercadoDatos");

	private static final String SUBMERCADO_FDT = MiEntelProperties
    .getProperty("miEntel.subMercadoFDT");
	
	private static final String START_PARAMETER_URL = "?";

    /**
     * Default constructor. 
     */
    public NotificacionesSesion() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

	    
        LOGGER.info("Request de autenticacion externa recibido: ");
        LOGGER.info("    Host: " + request.getRemoteHost());
        LOGGER.info("    Addr: " + request.getRemoteAddr());

	    //Parametros recibidos en query String del request
        String idp = this.getNonSensitiveParameterValue(DEFAULT_IDP_ATTTR_NAME, request);
        String contexto = this.getNonSensitiveParameterValue(CONTEXTO_ATTTR_NAME, request);

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        
        //Path portal de la aplicacion
        String portalPath = Utils.getMiEntelPortalPath(servletRequest);

        //URL por defecto: Login de Mientel, en protocolo http
        GenericURL defaultURL = GenericURL.createGenericURL(
                servletRequest, servletResponse);
        defaultURL.setTemplate("logout-http");
        defaultURL.setContextualPath(portalPath);

        //Url final hacia donde se enviara el request entrante
        String url = defaultURL.toString(true);

		
		try {
            /*
             * Si no hay IDP indicado se redirige al login de MiEntel con protocolo http
             */
            if (idp == null) {
                // La url se inicializa con la url base de la aplicacion
                url = defaultURL.toString(true);
                LOGGER.warn("IDP no encontrado, se redirecciona hacia Login de MiEntel");
            }else {
                //Autenticacion usuario
                if (!Authentication.isAnonymous(Authentication.getCurrentSubject())) {
                    Authentication.logout(servletRequest, true);
                }

                LOGGER.info("Autenticando usuario con IDP " + idp);
                String token = String.valueOf(Calendar.getInstance().getTimeInMillis());
                String username = idp + SEPARATOR_CHAR + token;
                Authentication.login(username, "", servletRequest,(HttpServletResponse) response);
                
                ProfileWrapper profile = SessionHelper.getProfile(servletRequest);

                LOGGER.info("Usuario autenticado.");
                LOGGER.info("numeroPcs : "
                        + ProfileWrapperHelper.getPropertyAsString(profile,
                                "numeroPcs"));
                LOGGER.info("nombreUsuario : "
                        + ProfileWrapperHelper.getPropertyAsString(profile,
                                "nombreUsuario"));
                LOGGER.info("subMercado : "
                        + ProfileWrapperHelper.getPropertyAsString(profile,
                                "subMercado"));                 

                
                String subMercado = ProfileWrapperHelper.getPropertyAsString(profile, "subMercado");

                String flagBam = ProfileWrapperHelper.getPropertyAsString(profile,"flagBam");

                
                /**
                 * Redireccionamiento para subMercado FDT
                 */
                if ( flagBam.equals("1") &&  subMercado.equals(SUBMERCADO_FDT)) {
	                	String numeroContexto = "";
	                    if(contexto.equals("comprarBolsa"))
	                        numeroContexto= "2";
	                    else if(contexto.equals("pagoEnLinea"))
	                        numeroContexto= "3";
	
	                    url = URL_REGISTROBAM.replace("{IDP}", idp).replace("{numeroContexto}", numeroContexto);
                }
                
                /**
                 * Redireccionamiento para subMercados de lineaHogar
                 */
                
            	else if (subMercado.equals(SUBMERCADO_LINEA_HOGAR_VOZ)) {
                    url = URL_LINEA_HOGAR_VOZ.replace("{IDP}", idp);
                } else if (subMercado.equals(SUBMERCADO_LINEA_HOGAR_DATOS)) {
                    url = URL_LINEA_HOGAR_DATOS.replace("{IDP}", idp);                
                }
                
                // Si se indica parametro 'contexto' se obtiene su path 
                // por medio del Helper de Aplicaciones Externas
                else if (Utils.isEmptyString(contexto)) {
                	// La url se inicializa con la url base de la aplicacion
                    GenericURL genericURL = GenericURL.createGenericURL(
                            servletRequest, servletResponse);
                    genericURL.setTemplate("login-https");
                    genericURL.setContextualPath(portalPath);

                    url = genericURL.toString(true);
                    url = url.concat(START_PARAMETER_URL+servletRequest.getQueryString());
                } else {

                    LOGGER.info("contexto: '" + contexto + "'");

                    String mercado = ProfileWrapperHelper.getPropertyAsString(
                            profile, "mercado");


                    PageURL pageURL = ExternalAppsHelper.getPageURL(
                    		servletRequest, servletResponse, contexto, mercado, flagBam);
                    		
                    if(pageURL == null) {

                        LOGGER.warn("No se encontro URL para el contexto '"
                                + contexto + "' y mercado '" + contexto + "'");
                        LOGGER.warn("redirect hacia Home de MiEntel");

                    } else {

                        pageURL.setTemplate("external-app-https");
                        pageURL.setContextualPath(portalPath);

                        url = pageURL.toString(false);
                        LOGGER.info("URL para contexto: '" + contexto
                                + "' y mercado '" + mercado + "' obtenida");

                    }
                }
            }

        } catch (LoginException e) {
            LOGGER.error(e.getMessage(), e);
            url = defaultURL.toString(true);            
            
        } catch (ProfileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            url = defaultURL.toString(true);
            
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            url = defaultURL.toString(true);
            
        } finally {
            
            // realiza un refresco. en caso de que se haya autenticado y
            // obtenido el perfil, se autorizaran
            // los recursos portal a los que tenga derecho.
            LOGGER.info("redireccionando usuario hacia: '" + url + "'");
            servletResponse.sendRedirect(url);
//            chain.doFilter(request, response);

        }		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * Obtiene el parametro presente en <code>request</code> con el nombre
	 * <code>attributeName</code>.<br>
	 * Este metodo busca el parametro para los nombres
	 * <code>parameterName.toLowerCase()</code> y
	 * <code>parameterName.toUpperCase()</code>
	 * 
	 * @param parameterName
	 * @param request
	 * @return
	 */
	private String getNonSensitiveParameterValue(String parameterName,
			ServletRequest request) {

		String paramValue = null;

		paramValue = (String) request.getParameter(parameterName.toLowerCase());
		if (Utils.isEmptyString(paramValue)) {
			paramValue = (String) request.getParameter(parameterName
					.toUpperCase());
		}
		return paramValue;
	}
}
