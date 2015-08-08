/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import weblogic.servlet.security.ServletAuthentication;

import com.bea.content.ContentContext;
import com.bea.content.Node;
import com.bea.content.expression.Search;
import com.bea.content.federated.ContentManagerFactory;
import com.bea.content.federated.INodeManager;
import com.bea.content.federated.ISearchManager;
import com.bea.content.paging.ISortableFilterablePagedList;
import com.bea.netuix.servlets.controls.page.BookPresentationContext;
import com.bea.netuix.servlets.controls.portlet.PortletPresentationContext;
import com.bea.netuix.servlets.controls.portlet.backing.PortletBackingContext;
import com.bea.netuix.servlets.manager.AppContext;
import com.bea.portlet.GenericURL;
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
     * Logger de la aplicacion.
     */
    private static final Logger LOGGER = Logger.getLogger(JSFPortletHelper.class);
    
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
        else if(objReq instanceof PortletRequest) {
            PortletRequest portletReq = (PortletRequest) objReq;
            request = (HttpServletRequest) portletReq
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
//        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Object objReq = FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        Object objRes = FacesContext.getCurrentInstance().getExternalContext()
                .getResponse();        
        if (objReq instanceof HttpServletRequest
                && objRes instanceof HttpServletResponse) {
//            request = (HttpServletRequest) objReq;
            response = (HttpServletResponse) objRes;
        }
        else if (objReq instanceof RenderRequestImpl) {
            RenderRequestImpl areq = (RenderRequestImpl) objReq;
//            request = (HttpServletRequest) areq
//                    .getAttribute("javax.servlet.request");
            response = (HttpServletResponse) areq
                    .getAttribute("javax.servlet.response");
        }
        else if (objReq instanceof ActionRequestImpl) {
            ActionRequestImpl areq = (ActionRequestImpl) objReq;
//            request = (HttpServletRequest) areq
//                    .getAttribute("javax.servlet.request");
            response = (HttpServletResponse) areq
                    .getAttribute("javax.servlet.response");
        }
        else if(objReq instanceof PortletRequest) {
            PortletRequest portletReq = (PortletRequest) objReq;
            response = (HttpServletResponse) portletReq
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
        HttpServletRequest request = getRequest(fc);
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
        HttpServletRequest request = getRequest(fc);
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
            	LOGGER.error("Error seteando una preferencia del portlet. Preferencia: "+name+ " Valor: "+ value);
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
        	LOGGER.error("Error almacenando preferencias de portlets." + e.getMessage());
            return false;
        }
        return true;
    }
    
    public static String getServerFullPath(FacesContext fc) {
        
        HttpServletRequest request = getRequest(fc);
        HttpServletResponse response = JSFPortletHelper.getResponse();
        
        GenericURL url = GenericURL.createGenericURL(request, response);        
        String protocol = url.getScheme();       
        
        if(request.isSecure()) {
            protocol = "https";
        }
        String serverFullPath = protocol + "://" + request.getServerName();
        serverFullPath += request.getContextPath();
        
        return serverFullPath;
        
    }

    public static String getServerFullPathHttps(FacesContext fc) {
        
        HttpServletRequest request = getRequest(fc);
        
        String serverFullPath = "https://" + request.getServerName();
        serverFullPath += request.getContextPath();
        
        return serverFullPath;
        
    }    
    
    /**
     * Devuelve una url que direcciona a la pagina especificada en el pageLabel   
     * puede ser usada en el value de un outputlink 
     * @param pageLabel
     * @return url to page
     */
    public static String createUrlPage(String pageLabel){
        HttpServletRequest request = JSFPortletHelper.getRequest();
        HttpServletResponse response = JSFPortletHelper.getResponse();
        
        com.bea.portlet.PageURL page = com.bea.portlet.PageURL.createPageURL(request, response, pageLabel); 
        page.setForcedAmpForm(false);
        
        return  page.toString();
   }
    
    /**
     * Consulta en el Content Manager un nodo que cumpla con la propiedad y
     * el valor especificados
     * 
     * @param property Nombre de la propiedad del nodo
     * @param value Valor de la propiedad
     * @return Nodo con el resultado de la consulta
     */    
    public static Node getContentNode(String property, String value) throws Exception {
        Node n = null;
        ContentContext context = new ContentContext();
        ISearchManager searchManager = ContentManagerFactory.getSearchManager();

        String query = property + " == " + "'" + value + "'";
        Search search = new Search(query);

        ISortableFilterablePagedList<Node> result = searchManager.search(context, search);
        Iterator<Node> it = result.iterator();

        while (it.hasNext()) {
            n = it.next();
        }
        
        return n;
    }
    
    /**
     * Devuelve un nodo hijo que tenga la propiedad y el valor especificado
     * @param parent Nodo padre
     * @param property
     * @param value
     * @return
     * @throws Exception
     */
    public static Node getContentChildNode(Node parent, String property, String value) throws Exception {
        Node found = null;
        Node child = null;
        ContentContext context = new ContentContext();
        INodeManager nodeManager = ContentManagerFactory.getNodeManager();
        ISortableFilterablePagedList<Node> result = nodeManager.getNodes(context, parent.getId());
        Iterator<Node> it = result.iterator();
        
        while (it.hasNext()) {
            child = it.next();
            String v = child.getProperty(property).getValue().getStringValue();
            if (v.equals(value)) {
            	found = child;
                break;
            }
        }        
        
        return found;
    }
    
    /**
     * Retorna todos los nodos de contenido que tengan el valor de la propiedad especificada
     * @param property
     * @param value
     * @return
     * @throws Exception
     */
    public static List<Node> getContentNodes(String property, String value) throws Exception {
        List<Node> nodes = new ArrayList<Node>();
        ContentContext context = new ContentContext();
        ISearchManager searchManager = ContentManagerFactory.getSearchManager();

        String query = property + " == " + "'" + value + "'";
        Search search = new Search(query);

        ISortableFilterablePagedList<Node> result = searchManager.search(context, search);
        Iterator<Node> it = result.iterator();

        while (it.hasNext()) {
            nodes.add(it.next());
        }
        
        return nodes;
    }    
    
    /**
     * Devuelve los nodos hijos en una lista
     * @param parent
     * @return
     * @throws Exception
     */
	public static List<Node> getChildrenNodes(Node parent) throws Exception {
		List<Node> children = new ArrayList<Node>();
		ContentContext context = new ContentContext();
		INodeManager nodeManager = ContentManagerFactory.getNodeManager();
		ISortableFilterablePagedList<Node> result = nodeManager.getNodes(
				context, parent.getId());
		Iterator<Node> it = result.iterator();

		while (it.hasNext()) {
			children.add(it.next());
		}

		return children;
	}    
    
    /**
     * Consulta en el Content Manager un nodo que cumpla con la propiedad y
     * el valor especificados y retorna de este nodo una de sus propiedades como String.
     * 
     * @param property Nombre de la propiedad del nodo para la busqueda
     * @param value Valor de la propiedad del nodo para la busqueda
     * @param returnedProperty Nombre de la propiedad del nodo que va a ser devuelta como String
     * @return Valor de la propiedad del nodo con el nombre <code>returnedProperty</code>
     */    
    public static String getNodePropertyAsString(String property, String value, String returnedProperty) throws Exception {
    	Node n = getContentNode(property, value);
        if(n != null){
        	com.bea.content.Property p = n.getProperty(returnedProperty);
        	if(p != null){
        		return p.getValue().getStringValue();
        	}
        	else{
        		LOGGER.error( new Exception("No se encontro la propiedad '" + returnedProperty + "' del contenido"));
        	}
        }
        else{
        	LOGGER.error( new Exception("No se encontro el contenido con la propiedad " + property + " = '" + value +"'"));
        }
        return "";
    }
	
	/**
     * Consulta en el Content Manager un nodo que cumpla con la propiedad y
     * el valor especificados y retorna de este nodo una de sus propiedades como InputStream.
     * 
     * @param property Nombre de la propiedad del nodo para la busqueda
     * @param value Valor de la propiedad del nodo para la busqueda
     * @param returnedProperty Nombre de la propiedad del nodo que va a ser devuelta como InputStream
     * @return Valor de la propiedad del nodo con el nombre <code>returnedProperty</code>
     */    
    public static InputStream getNodePropertyBinaryValue(String property, String value, String returnedProperty) throws Exception {
    	ContentContext context = new ContentContext();
    	InputStream in = null;
    	Node n = getContentNode(property, value);
        if(n != null){
        	INodeManager nodeManager = ContentManagerFactory.getNodeManager();
        	in = nodeManager.getStream(context, n.getId(), returnedProperty); 
        }
        else{
        	LOGGER.error( new Exception("No se encontro el contenido con la propiedad " + property + " = '" + value +"'"));
        } 
        return in;
    }
    
	/**
     * Consulta en el Content Manager un nodo que cumpla con la propiedad y
     * el valor especificados y retorna de este nodo una de sus propiedades como String.
     * 
     * @param property Nombre de la propiedad del nodo para la busqueda
     * @param value Valor de la propiedad del nodo para la busqueda
     * @param returnedProperty Nombre de la propiedad del nodo que va a ser devuelta como String
     * @return Valor de la propiedad del nodo con el nombre <code>returnedProperty</code>
     */
    public static String getNodePropertyBinaryValueAsString(String property, String value, String returnedProperty) {
		StringBuilder txtFile = new StringBuilder();
		InputStream is = null;

		try {
			is = JSFPortletHelper.getNodePropertyBinaryValue(property, value, returnedProperty);
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = buf.readLine()) != null) {
				txtFile.append(line.trim());
			}
			is.close();
		} catch (IOException e) {
			return "";
		} catch (Exception e) { 
			return "";
		}
		
		return txtFile.toString();
	}
    
    /**
     * Obtiene el pageLabel de la pagina actual
     * @return Valor del pageLabel actual
     */
    public static String getCurrentPageLabel() {
    	BookPresentationContext bookContext = BookPresentationContext.getBookPresentationContext(getRequest());
    	return bookContext.getActivePage();
    }
    
	/**
	 * Retorna una instancia de un bean especificado en el archivo
	 * faces-beans.xml
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getFacesBeanInstance(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();

		Object facesBean = context.getApplication().getELResolver().getValue(
				context.getELContext(), null, beanName);

		return facesBean;
	}
	
	/**
	 * Retorna una instancia de un bean JSF en sesion
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getFacesBeanInstanceFromSession(String beanName) {
		HttpSession session = getSession();
		Object found = null;
		
		for (Enumeration e = session.getAttributeNames(); e.hasMoreElements();) {
			String attribName = (String) e.nextElement();
			if (attribName.contains(beanName)) {
				found = session.getAttribute(attribName);
				break;
			}	
		}
		
		return found;
	}
	
	/**
	 * Asigna un nuevo valor a la instancia de un bean JSF en sesion
	 * @param beanName
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public static void setFacesBeanInstanceToSession(String beanName, Object beanValue) {
		HttpSession session = getSession();
		
		for (Enumeration e = session.getAttributeNames(); e.hasMoreElements();) {
			String attribName = (String) e.nextElement();
			if (attribName.contains(beanName)) {
				session.setAttribute(attribName, beanValue);
				break;
			}	
		}
	}	
}
