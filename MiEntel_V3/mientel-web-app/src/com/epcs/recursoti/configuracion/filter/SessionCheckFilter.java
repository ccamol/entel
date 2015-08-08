/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bea.portlet.GenericURL;
import com.bea.portlet.PostbackURL;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.error.ServiceMessages;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

/**
 * Filtro para chequear la existencia de una sesion autenticada
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class SessionCheckFilter implements Filter {

    private static final String MI_ENTEL_DESKTOP_URL = "miEntel.desktop.url";
    private static boolean redirectToEntel = true;
    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {

    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //Obtengo la sesion
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpSession session = httpServletRequest.getSession(false);
        
        Principal userPrincipal = httpServletRequest.getUserPrincipal();
        String user = (userPrincipal != null ? userPrincipal.getName().trim()
                : null);

        //Si esta presente el parametro 'logout' el filtro no chequear sesion
        String logout = httpServletRequest.getParameter("logout");
        if(Utils.isEmptyString(logout)) {
            
            // Si la sesion es nula y la URL que esta filtrando no es el HOME
            // (url base) de MiEntel
            if ((user == null && !isHomeUrl(httpServletRequest) && !isLoginPage(httpServletRequest) && !isInscripcionPage(httpServletRequest)) 
            	|| (user == null && redirectToEntel)) {

                ProfileWrapperHelper.removeProfile(httpServletRequest);

                // Luego de realizar todas las acciones de logout, se agrega a la
                // nueva sesion (sin usuario autenticado), el mensaje de logout de
                // seguridad
                session = httpServletRequest.getSession();
                session.setAttribute("securityLogoutMessage",
                        getSecurityMessage());

                /*GenericURL url = GenericURL.createGenericURL(
                        httpServletRequest, httpServletResponse);
                url.setTemplate("logout-http");
                */
                
                PostbackURL url = PostbackURL.createPostbackURL(httpServletRequest, httpServletResponse);
                url.setTemplate("default");
                url.addParameter(GenericURL.PAGE_LABEL_PARAM, "mainlogin_page");
                url.addParameter(GenericURL.LOADSTATE_PARAM, "false");
                url.addParameter("logout", "1");
                
                //url.setPath(Utils.getMiEntelBaseURL(httpServletRequest));

                String urlRedirect = isHomeUrl(httpServletRequest) ? this.getUrlHomeEntel() :url.toString(true);
                httpServletResponse.sendRedirect(redirectToEntel ? urlRedirect : url.toString(true));
                
            }
        }
        
        
        // Forces caches to obtain a new copy of the page from the
        // origin server
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        // Directs caches not to store the page under any circumstance
        httpServletResponse.setHeader("Cache-Control", "no-store");
        // Causes the proxy cache to see the page as "stale"
        httpServletResponse.setDateHeader("Expires", 0);
        // HTTP 1.0 backward compatibility
        httpServletResponse.setHeader("Pragma", "no-cache");

        httpServletRequest.setAttribute("_cache_refresh", "true");
        
        chain.doFilter(request, response);
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        redirectToEntel = Boolean.valueOf(arg0.getServletContext().getInitParameter("REDIRECTTOENTEL"));
    }
    
    /**
     * Indica si la URL del request corresponde a la URL de home de Mientel. En
     * tal caso, este metodo retorna true, caso contrario falsa
     * 
     * @param request
     *            {@link HttpServletRequest} de quien se pregunta por al URL del
     *            home
     * @return true si la URL es el home. false en caso contrario
     */
    private boolean isHomeUrl(HttpServletRequest request) {

        String filteredUrl = getFilteredUrl(request);
        String baseUrl = Utils.getMiEntelBaseURL(request);

        return filteredUrl.equals(baseUrl);

    }
    
    /**
     * Obtiene la url filtrada completa, es decir, incluyendo el contexto de la
     * aplicacion y el prefijo de Portal (app)
     * 
     * @param request
     * @param pathInfo
     * @return
     */
    private String getFilteredUrl(HttpServletRequest request) {
        
        String pathInfo = request.getPathInfo();
        
        String url = request.getContextPath();
        String queryString = request.getQueryString();
        url += "/" + MiEntelProperties.getProperty(MI_ENTEL_DESKTOP_URL) + pathInfo;
        if(queryString != null) {
            url += "?" + queryString;
        }
        return url;
    }

    private String getSecurityMessage() {

        ServiceMessages errorMessages = MiEntelProperties
                .getServiceMessages();
        return errorMessages.getErrorMessage("securityLogout");

    }
    
    /**
     * Indica si la URL del request corresponde a la URL de home de Mientel. En
     * tal caso, este metodo retorna true, caso contrario falsa
     * 
     * @param request
     *            {@link HttpServletRequest} de quien se pregunta por al URL del
     *            home
     * @return true si la URL es el home. false en caso contrario
     */
    private boolean isLoginPage(HttpServletRequest request) {

        String filteredUrl = getFilteredUrl(request);
        String loginPage = "mainlogin_page";

        return filteredUrl.contains(loginPage);

    }
    
    private boolean isInscripcionPage(HttpServletRequest request) {

        String filteredUrl = getFilteredUrl(request);
        String inscripcionPage = "inscripcionUsuario_page";

        return filteredUrl.contains(inscripcionPage);

    }
    
    private String getUrlLoginEntel(){
        return MiEntelProperties.getProperty("miEntel.logout.url");
    } 

    private String getUrlHomeEntel(){
        return MiEntelProperties.getProperty("miEntel.home.url");
    }
    
}
