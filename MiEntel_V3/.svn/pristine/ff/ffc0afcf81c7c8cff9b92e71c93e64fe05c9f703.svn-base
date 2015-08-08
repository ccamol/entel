package com.epcs.administracion.suscripciones.util;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.bea.portlet.PageURL;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;


/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class BannerSeguridadHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(BannerSeguridadHelper.class);
	private static final String CLICK_BANNER_ATTTR_NAME = MiEntelProperties
			.getProperty("parametros.bannerSeguridad.click.attr.name");
	private static final String PAGE_LABEL_PLANES_SEGURIDAD_CC = MiEntelProperties
			.getProperty("parametros.planesSeguridadBAM.pageLabel.cc");
	private static final String PAGE_LABEL_PLANES_SEGURIDAD_SS = MiEntelProperties
			.getProperty("parametros.planesSeguridadBAM.pageLabel.ss");
	
	
	/**
	 * Metodo utilitario para la obtencion de la URL y redireccionamiento al
	 * listado de planes de seguridad BAM desde el banner de seguridad.
	 * 
	 */
	public static void redirectToPlanesSeguridad() {
		try {
			HttpServletRequest httpRequest = JSFPortletHelper
					.getRequest(FacesContext.getCurrentInstance());

			HttpServletResponse httpResponse = JSFPortletHelper.getResponse();

			String pageLabelPlanes = "";
			ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
			String mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
			
        	if(MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)){
        		pageLabelPlanes = JSFPortletHelper.getPreference(
    					JSFPortletHelper.getPreferencesObject(),
    					PAGE_LABEL_PLANES_SEGURIDAD_CC, null);
        	}else if(MiEntelBusinessHelper.isMercadoSuscripcion(mercado)){
        		pageLabelPlanes = JSFPortletHelper.getPreference(
    					JSFPortletHelper.getPreferencesObject(),
    					PAGE_LABEL_PLANES_SEGURIDAD_SS, null);
        	}
        	
			PageURL pageURL = getPageURLPlanesSeguridad(httpRequest,
					httpResponse, pageLabelPlanes);
			
			redirectURL(httpResponse, pageURL);
		} catch (Exception e) {
			LOGGER.error("Exception al intentar obtener y redireccionar a la URL del Formulario de Inscripcion",e);
		}
	}
	
	/**
	 * Metodo utilitario que crea un PageURL, agregandole un parametro
	 * @param httpRequest
	 * @param httpResponse
	 * @param pageLabel
	 * @param acceso
	 * @return
	 */
	public static PageURL getPageURLPlanesSeguridad(
			HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			String pageLabel) {

		try {
			PageURL url = PageURL.createPageURL(httpRequest, httpResponse,
					pageLabel, false);
			// Agrega a la url el parametro especificado
			url.addParameter(CLICK_BANNER_ATTTR_NAME, "true");
			// Indica a la url que los caracteres '&' no los transforme a
			// '&amp;'
			url.setForcedAmpForm(false);

			return url;

		} catch (Exception e) {
			LOGGER.error("Exception no se pudo encontrar el pageLabel", e);
			return null;
		}

	}

	/**
	 * Redirecciona al {@link PageURL} de la pagina de planes de seguridad BAM.
	 * 
	 * @param httpResponse
	 *            {@link HttpServletResponse} de solicitud de ingreso a MiEntel
	 * @param pageURL
	 *            Elemento PageURL que contiene la url a redireccionar
	 * @return {@link PageURL} de la pagina del Formulario de Inscripcion
	 */
	public static void redirectURL(HttpServletResponse httpResponse,
			PageURL pageURL) {

		String url = pageURL.toString(false);
		try {
			httpResponse.sendRedirect(url);
			
		} catch (IOException e) {
			LOGGER.error("Exception al intentar redireccionar a la URL:" + url, e);
		}
	}

}
