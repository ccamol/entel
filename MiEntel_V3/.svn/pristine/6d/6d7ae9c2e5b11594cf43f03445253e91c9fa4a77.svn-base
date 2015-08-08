/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.controller;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.security.Authentication;
import com.bea.p13n.usermgmt.SessionHelper;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * Controller para el Portlet del Header
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class HeaderController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(HeaderController.class);

    private String nombreUsuario;

    private String nombreTitular;
    
    private String oldVersionText;
    
    private String oldVersionLink;    
    
    /**
	 * @return the oldVersionText
	 */
	public String getOldVersionText() {

		try {
	    	String idContenido 	 = MiEntelProperties.getProperty("parametros.header.oldVersionLink.idContenido");
	    	Node mensajeServicio = JSFPortletHelper.getContentNode("idContenido", idContenido);
			oldVersionText 	 = mensajeServicio.getProperty("html").getValue().getStringValue();
		}
        catch (Exception e) {
        	LOGGER.error("Error al obtener la propiedad html del contenido oldVersionLink de Header.porlet", e);
            JSFMessagesHelper.addErrorCodeMessage("general", "0001");
        }
    	
		return oldVersionText;
	}
	
	
	/**
	 * @param oldVersionText the oldVersionText to set
	 */
	public void setOldVersionText(String oldVersionText) {
		this.oldVersionText = oldVersionText;
	}


	/**
	 * @return the oldVersionLink
	 */
	public String getOldVersionLink() {

		try {
	    	String idContenido 	 = MiEntelProperties.getProperty("parametros.header.oldVersionLink.idContenido");
	    	Node mensajeServicio = JSFPortletHelper.getContentNode("idContenido", idContenido);
			oldVersionLink 	 = mensajeServicio.getProperty("titulo").getValue().getStringValue();
		}
        catch (Exception e) {
        	LOGGER.error("Error al obtener la propiedad titulo del contenido oldVersionLink de Header.porlet", e);
            JSFMessagesHelper.addErrorCodeMessage("general", "0001");
        }
    	
		return oldVersionLink;
	}


	/**
	 * @param oldVersionLink the oldVersionLink to set
	 */
	public void setOldVersionLink(String oldVersionLink) {
		this.oldVersionLink = oldVersionLink;
	}
	

	/**
     * @return the seguridadDelegate
     */
    public SeguridadDelegate getSeguridadDelegate() {
        return seguridadDelegate;
    }


    /**
     * @param seguridadDelegate the seguridadDelegate to set
     */
    public void setSeguridadDelegate(SeguridadDelegate seguridadDelegate) {
        this.seguridadDelegate = seguridadDelegate;
    }

    private SeguridadDelegate seguridadDelegate;

    
    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * @return
     */
    public String getURLMiEntelV2() {
        String idp = "";
        String url = "";
        
        try {
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            idp = seguridadDelegate.consultarIDP(ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs"));
            url = MiEntelProperties.getProperty("miEntel.contextoV2");
            return (url.concat(idp).concat("&contexto="));
        } catch (DAOException e) {
            LOGGER.error("DAOException al obtener el IDP", e);
            return "";
        } catch (ServiceException e) {
            LOGGER.info("ServiceException codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            return "";
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener la URL de MiEntelV2", e);
            return "";
        }
    }
    
    /**
     * 
     */
    public void redirectToMiEntelV2(ActionEvent event){
    	HttpServletResponse httpResponse = JSFPortletHelper.getResponse();
    	
    	try{
    		httpResponse.sendRedirect(getURLMiEntelV2());
    	} catch (IOException e) {
			LOGGER.error("Exception redireccionando a MiEntelV2", e);
		}
    }
    
    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        if(nombreUsuario == null) {
            ProfileWrapper profile = SessionHelper.getProfile(JSFPortletHelper.getRequest());
            
            try {
                nombreUsuario = ProfileWrapperHelper.getPropertyAsString(
                        profile, "nombreUsuario");

            } catch (Exception e) {
                LOGGER.warn("No se obtuvo el nombre de Usuario autenticado", e);
            }
        }
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario
     *            the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the nombreTitular
     */
    public String getNombreTitular() {
        if(nombreTitular == null) {
            ProfileWrapper profile = SessionHelper.getProfile(JSFPortletHelper.getRequest());
            
            try {
                nombreTitular = ProfileWrapperHelper.getPropertyAsString(
                        profile, "nombreTitular");

            } catch (Exception e) {
                LOGGER.warn("No se obtuvo el nombre de titular autenticado");
            }
        }
        return nombreTitular;
    }

    /**
     * @param nombreTitular
     *            the nombreTitular to set
     */
    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public boolean isUserAutenticathed() {

        try {
            return !Authentication.isAnonymous(Authentication
                    .getCurrentSubject());
        } catch (Exception e) {
            LOGGER.error("Ocurri&oacute; una Exception al validar "
                    + "si usuario est&aacute; autenticado. "
                    + "Se retornar&aacute; 'false' como respuesta en "
                    + "isUserAutenticathed", e);
            return false;
        }

    }
    
    /**
     * Este metodo tiene dos funciones: hacer un logout de la aplicacion, invalidando la sesion y 
     * redireccionando a la pagina de login despues de haber forzado el borrado de la cache del response.
     * @return un objeto nulo, no es necesaria una respuesta.
     */
    public String logout() {

        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            
            HttpServletRequest request = JSFPortletHelper.getRequest(fc);      
            HttpServletResponse response = JSFPortletHelper.getResponse();
            
            // Obtengo msisdn + token para limpiar cache
            Principal userPrincipal = request.getUserPrincipal();
            String msisdnToken = (userPrincipal != null ? userPrincipal.getName().trim()
                    : null);
            this.cleanupCache(msisdnToken);
            
            // Logout
            Authentication.logout(request, true);
            
            Utils.prepareHeadersForLogout(request, response);
            
            request.getSession().invalidate();

            ProfileWrapperHelper.removeProfile(request);
            
            String urlRedirect =  this.getUrlHomeEntel();
            response.sendRedirect(urlRedirect);
            
            fc.renderResponse();

        } catch (Exception e) {
            LOGGER.error("No ha sido posible redireccionar " +
                    "despues de hacer logout.", e);
        }
        
        return null;        
    }
    
    /**
     * Este metodo tiene dos funciones: hacer un logout de la aplicacion, invalidando la sesion y 
     * redireccionando a la pagina de login despues de haber forzado el borrado de la cache del response.
     * @return 
     */
    public void logout2(ActionEvent actionEvent) {                

        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            
            HttpServletRequest request = JSFPortletHelper.getRequest(fc);      
            HttpServletResponse response = JSFPortletHelper.getResponse();
            
            // Obtengo msisdn + token para limpiar cache
            Principal userPrincipal = request.getUserPrincipal();
            String msisdnToken = (userPrincipal != null ? userPrincipal.getName().trim()
                    : null);
            this.cleanupCache(msisdnToken);
            
            // Logout
            Authentication.logout(request, true);
            
            Utils.prepareHeadersForLogout(request, response);
            
            request.getSession().invalidate();

            ProfileWrapperHelper.removeProfile(request);
            
            String urlRedirect =  this.getUrlHomeEntel();
            fc.getExternalContext().redirect(urlRedirect);
            

        } catch (Exception e) {
            LOGGER.error("No ha sido posible redireccionar " +
                    "despues de hacer logout.", e);
        }
       
    }
    
    /**
     * Remueve de la cache de UUP el perfil del usuario asociado al numero indicado
     * @param numeroPcs
     */
    public void cleanupCache(final String numeroPcs) {

        String cacheName = MiEntelProperties.getProperty("miEntel.userProfile.cacheName");
        Utils.removeCacheItem(cacheName, numeroPcs);
        LOGGER.info("User entity removed from cache");
    }

    public String getPageLabelFormSatisfaccion() {
        try {            
          ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());

          String mercado = ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "mercado");
          String flagBam = ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "flagBam");
          String url = JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), mercado + flagBam, null);
          return url;
        }catch(Exception e){
            LOGGER.error("No se ha podido obtener el pageLabel "+ e.getMessage(), e);
            return "";
        }
    }    
    
    public String getPageLabelContacto() {
        try {            
          ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());

          String mercado = ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "mercado");
          String flagBam = ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "flagBam");
          String flag = flagBam.equals("1")?"BAM":"VOZ";
          String url = JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), mercado +"_"+ flag, null);
          return url;
        }catch(Exception e){
            LOGGER.error("No se ha podido obtener el pageLabel "+ e.getMessage(), e);
            return "";
        }
    }    
    
    
    private String getUrlHomeEntel(){
        return MiEntelProperties.getProperty("miEntel.home.url");
    }
    
    
    public String getPageLabelReclamos() {
        try {            
          ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());

          String prefPageLabel 	 = MiEntelProperties.getProperty("parametros.header.reclamos.prefPageLabel");
           
          String mercado = ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "mercado");
          String flagBam = ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "flagBam");
          String flag = flagBam.equals("1")?"BAM":"VOZ";
          String url = JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), prefPageLabel+"_"+mercado +"_"+ flag, null);
          return url;
        }catch(Exception e){
            LOGGER.error("No se ha podido obtener el pageLabel del page de reclamos " +
            		""+ e.getMessage(), e);
            return "";
        }
    } 

}
