/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.utils;

import java.io.Serializable;
import java.security.Principal;

import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import weblogic.servlet.security.ServletAuthentication;

import com.bea.netuix.servlets.controls.portlet.PortletPresentationContext;
import com.bea.netuix.servlets.controls.portlet.backing.PortletBackingContext;
import com.bea.netuix.servlets.manager.AppContext;
import com.bea.portlet.container.ActionRequestImpl;
import com.bea.portlet.container.RenderRequestImpl;

/**
 * Clase utilitaria con metodo para Portlets JSF. Estos metodos se espera sean
 * llamados desde los managed beans de JSF principalmente, pero algunos pueden
 * ser llamados en un portlet JSF con baking file.
 * 
 * @author jplopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class JSFPortletHelper {

    // STANDARD CONTEXT
    /**
     * Obtiene el {@link HttpServletRequest} desde FacesContext.<br>
     * <b>Solo puede ser llamado desde un managed bean JSF</b>
     * 
     * @return HttpServletRequest que es realmente un objeto FacesRequest
     */
    static public HttpServletRequest getRequest() {
        return getRequest(FacesContext.getCurrentInstance());
    }
    
    /**
    /**
     * Obtiene el {@link HttpServletRequest} desde la instancia FacesContext <code>context</code>.<br>
     * 
     * @param context instancia FacesContext
     * 
     * @return HttpServletRequest que es realmente un objeto FacesRequest
     */
    static public HttpServletRequest getRequest(FacesContext context) {

        HttpServletRequest request = null;
        Object objReq = context.getExternalContext()
                .getRequest();
        Object objRes = context.getExternalContext()
                .getResponse();
        if (objReq instanceof HttpServletRequest
                && objRes instanceof HttpServletResponse) {
            request = (HttpServletRequest) objReq;
        }
        else if (objReq instanceof RenderRequestImpl) {
            RenderRequestImpl areq = (RenderRequestImpl) objReq;
            request = (HttpServletRequest) areq
                    .getAttribute("javax.servlet.request");
        }
        else if (objReq instanceof ActionRequestImpl) {
            ActionRequestImpl areq = (ActionRequestImpl) objReq;
            request = (HttpServletRequest) areq
                    .getAttribute("javax.servlet.request");

        }
        return request;
    }

    

    /**
     * Obtiene el {@link HttpSession} desde FacesContext.<br>
     * <b>Solo puede ser llamado desde un managed bean JSF</b>
     * 
     * @return a HttpSession implementation
     */
    static public HttpSession getSession() {
        HttpServletRequest request = getRequest();
        return request.getSession();
    }

    /**
     * Obtiene el {@link HttpServletRequest} desde FacesContext.<br>
     * <b>Solo puede ser llamado desde un managed bean JSF</b> *
     * 
     * @return HttpServletResponse implementation, el que es realmente un objeto
     *         FacesResponse
     */
    static public HttpServletResponse getResponse() {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Object objReq = FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        Object objRes = FacesContext.getCurrentInstance().getExternalContext()
                .getResponse();
        System.out.println(objRes.toString());
        if (objReq instanceof HttpServletRequest
                && objRes instanceof HttpServletResponse) {
            request = (HttpServletRequest) objReq;
            response = (HttpServletResponse) objRes;
        }
        else if (objReq instanceof RenderRequestImpl) {
            RenderRequestImpl areq = (RenderRequestImpl) objReq;
            request = (HttpServletRequest) areq
                    .getAttribute("javax.servlet.request");
            response = (HttpServletResponse) areq
                    .getAttribute("javax.servlet.response");
        }
        else if (objReq instanceof ActionRequestImpl) {
            ActionRequestImpl areq = (ActionRequestImpl) objReq;
            request = (HttpServletRequest) areq
                    .getAttribute("javax.servlet.request");
            response = (HttpServletResponse) areq
                    .getAttribute("javax.servlet.response");

        }

        return response;
    }

    // PORTAL ENVIRONMENT
    /**
     * Gets the PortletBackingContext object. This method will return null if
     * called during the RENDER_RESPONSE JSF lifecycle. Must only be called from
     * a JSF managed bean.
     * 
     * @return the active PortletBackingContext, or null
     */
    static public PortletBackingContext getPortletBackingContext() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc
                .getExternalContext().getRequest();
        return PortletBackingContext.getPortletBackingContext(request);
    }

    /**
     * Gets the PortletPresentationContext object. This method will return null
     * if NOT called during the RENDER_RESPONSE JSF lifecycle. Must only be
     * called from a JSF managed bean.
     * 
     * @return the active PortletPresentationContext, or null
     */
    static public PortletPresentationContext getPortletPresentationContext() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc
                .getExternalContext().getRequest();
        return PortletPresentationContext
                .getPortletPresentationContext(request);
    }

    /**
     * Returns true if the user can make customizations (preferences,
     * add/move/remove portlets, add pages) to the portal. This is based on
     * factors such as: is the user authenticated, is it a streaming portal (not
     * a .portal file), and customization is enabled in netuix-config.xml. Can
     * be called from any web application class.
     * 
     * @return a boolean, true if it is possible for the user to make
     *         customizations
     */
    static public boolean isCustomizable() {
        return AppContext.isCustomizationAllowed(getRequest());
    }

    // AUTHENTICATION
    /**
     * Is the current user authenticated? Must only be called from a JSF managed
     * bean.
     * 
     * Developing JSF Portlets with WebLogic Portal 141
     * 
     * @return true if the user is authenticated, false if not
     */
    static public boolean isAuthenticated() {
        Principal principal = FacesContext.getCurrentInstance()
                .getExternalContext().getUserPrincipal();
        return principal != null;
    }

    /**
     * Get the current user's username from the container. Must only be called
     * from a JSF managed bean.
     * 
     * @return the user name, null if the user is not authenticated
     */
    static public String getUsername() {
        String username = null;
        Principal principal = FacesContext.getCurrentInstance()
                .getExternalContext().getUserPrincipal();
        if (principal != null) {
            username = principal.getName();
        }
        return username;
    }

    /**
     * Get the current user's username for display. If the user is not
     * authenticated, it will return the name passed as the anonymousUsername
     * parameter. DO NOT use this method for anything other than display (e.g.
     * access control, auditing, business logic), as the passed anonymous name
     * may conflict with an actual username in the system. Must only be called
     * from a JSF managed bean.
     * 
     * @param anonymousUsername
     *            a String localized name to use for an anonymous user, like
     *            "Guest"
     * @return the user name
     */
    static public String getUsernameForDisplay(String anonymousUsername) {
        String username = anonymousUsername;

        Principal principal = FacesContext.getCurrentInstance()
                .getExternalContext().getUserPrincipal();
        if (principal != null) {
            username = principal.getName();
        }
        return username;
    }

    // USER AUTHENTICATION ROUTINES
    /**
     * Authenticate the user with WebLogic Server Can be called from any web
     * application class.
     * 
     * @param username
     *            the String username
     * @param password
     *            the String password as provided by the user
     * @return true if the login was successful, false if not
     */
    static public boolean authenticate(String username, String password) {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        int result = ServletAuthentication.weak(username, password, request,
                response);
        return result != ServletAuthentication.FAILED_AUTHENTICATION;
    }

    // NAMESPACES AND LABELS
    /**
     * Gets the current portlet's instance label. Must only be called from a JSF
     * managed bean.
     * 
     * @return the String instance label
     */
    static public String getInstanceLabel() {
        return getInstanceLabel(getRequest());
    }

    /**
     * Gets the current portlet's instance label. Can be called from any web
     * application class.
     * 
     * @param the
     *            HttpServletRequest object
     * @return the String instance label
     */
    static public String getInstanceLabel(HttpServletRequest request) {
        PortletPresentationContext portletCtx = (PortletPresentationContext) PortletPresentationContext
                .getPortletPresentationContext(request);
        String portletId = portletCtx.getInstanceLabel();

        return portletId;
    }

    /**
     * Gets the current portlet's definition label. Must only be called from a
     * JSF managed bean.
     * 
     * @return the String definition label
     */
    static public String getDefinitionLabel() {
        return getDefinitionLabel(getRequest());
    }

    /**
     * Gets the current portlet's definition label. Can be called from any web
     * application class.
     * 
     * Developing JSF Portlets with WebLogic Portal 144
     * 
     * @param the
     *            HttpServletRequest object
     * @return the String definition label
     */
    static public String getDefinitionLabel(HttpServletRequest request) {
        String label = "_global";
        PortletBackingContext pbc = PortletBackingContext
                .getPortletBackingContext(request);
        if (pbc != null) {
            label = pbc.getDefinitionLabel();
        }
        else {
            PortletPresentationContext ppc = PortletPresentationContext
                    .getPortletPresentationContext(request);
            if (ppc != null) {
                label = ppc.getDefinitionLabel();
            }
        }
        return label;
    }

    /**
     * Finds a namespace embedded in a portlet instance or definition label.
     * This namespace is intended to be used as a prefix for attributes set into
     * the HttpSession as a way to share state between portlet instances. See
     * the State Sharing chapter of the JSF whitepaper.
     * <p>
     * This method expects to find the namespace at the end of the label,
     * following the passed delimiter.
     * <p>
     * For example:
     * <ul>
     * <li>label = "myportlet_group1"
     * <li>delimiter = "_"
     * <li>return = "group1"
     * </ul>
     * 
     * @param label
     *            the String instance or definition label
     * @param delimiter
     *            the
     * @return the String namespace Developing JSF Portlets with WebLogic Portal
     *         145
     */
    static public String splitNamespaceFromLabel(String label, String delimiter) {
        String namespace = label;
        int lastIndex = label.lastIndexOf(delimiter);
        if (lastIndex > -1) {
            // namespaced
            namespace = label.substring(lastIndex);
        }
        else {
            // not namespaced, noop
        }
        return namespace;
    }

    // PORTAL EVENTS
    /**
     * Fires a custom portal event with a payload. Must only be called from a
     * JSF managed bean.
     * 
     * @param eventName
     *            the String name of the event
     * @param payload
     *            the Serializable payload
     * @return true if the event was fired, false if it could not be
     */
    static public boolean fireCustomEvent(String eventName, Serializable payload) {
        boolean fired = false;
        PortletBackingContext pbc = getPortletBackingContext();
        if (pbc != null) {
            pbc.fireCustomEvent(eventName, payload);
            fired = true;
        }
        return fired;
    }

    // WLP PORTLET PREFERENCE OPERATIONS
    /**
     * Gets an instantiated preferences object for the portlet; it must be
     * obtained once per request. Must only be called from a JSF managed bean.
     * 
     * @return the PortletPreferences object for the request
     */
    static public PortletPreferences getPreferencesObject() {
        PortletPreferences prefs = null;
        PortletBackingContext pbc = getPortletBackingContext();
        HttpServletRequest request = getRequest();
        if (pbc != null) {
            prefs = pbc.getPortletPreferences(request);
        }
        else {
            PortletPresentationContext ppc = PortletPresentationContext
                    .getPortletPresentationContext(request);
            if (ppc != null) {
                prefs = ppc.getPortletPreferences(request);
            }
        }
        return prefs;
    }

    /**
     * Gets the single value preference.
     * 
     * @param name
     *            the String name of the preference
     * @param value
     *            the String default value to use if the preference isn't set
     * @return the String value
     */
    static public String getPreference(PortletPreferences prefs, String name,
            String value) {
        if (prefs != null) {
            value = prefs.getValue(name, value);
        }
        return value;
    }

    /**
     * Sets a single value preference into the preferences object.
     * storePreferences() must be called subsequently to persist the change.
     * 
     * @param name
     *            the String name of the preference
     * @param value
     *            the String value of the preference
     */
    static public void setPreference(PortletPreferences prefs, String name,
            String value) {
        if (prefs != null) {
            try {
                prefs.setValue(name, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * After setting updated values into the preferences object, call this
     * method so they can be stored in the persistent store in a single atomic
     * operation.
     * 
     * @param prefs
     *            the PortletPreferences to be persisted
     * @return a boolean, true if the store succeeded
     */
    static public boolean storePreferences(PortletPreferences prefs) {
        if (!isCustomizable()) {
            return false;
        }
        try {
            prefs.store();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
