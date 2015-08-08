package com.epcs.cliente.canal.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;

import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;

import com.epcs.cliente.perfil.delegate.OfertaBlindajeDelegate;


public class HistorialPPAltoValor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(HistorialPPAltoValor.class);
	
	private static final String CATEGORIA_CLIENTE_PPAV = MiEntelProperties
			.getProperty("parametros.categoriausuario.diferenciacioncanalespp.altovalor");

	private OfertaBlindajeDelegate ofertaBlindajeDelegate;
	
	private boolean limpiezaRealizada;
	
	private String respuestaJson;
	
	
	public void initLimpieza(PhaseEvent event){
		
		try{
			ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
			String categoriaUser = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "categoriaCliente");
			String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado");
			String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
			
			PortletPreferences preferences = JSFPortletHelper.getPreferencesObject();
			String prefFechaLimpieza = JSFPortletHelper.getPreference(preferences,"fechaLimpiezaHistorial", "");
			
			Date fechaActual = new Date();
			Date fechaLimpieza = DateHelper.parseDate(prefFechaLimpieza, DateHelper.FORMAT_ddMMyyyy_SLASH);
			
			if(Utils.isNotEmptyString(mercado) && MiEntelBusinessHelper.isMercadoPrepago(mercado)){
				if (Utils.isNotEmptyString(categoriaUser) && categoriaUser.equals(CATEGORIA_CLIENTE_PPAV)){
					if(Utils.isNotEmptyString(prefFechaLimpieza)){
						if (DateHelper.isSameDay(fechaActual, fechaLimpieza)){
							if(!limpiezaRealizada){
								String [] resultadoLimpieza = ofertaBlindajeDelegate.borrarHistorialDespliegueLightbox();
								if(resultadoLimpieza[0].equals("0")){
									limpiezaRealizada = true;
									LOGGER.info("PROCESO DE LIMPIEZA DE HISTORIAL LIGHTBOX PP PLUS EXITOSO\n");
								}else{
									LOGGER.info("PROCESO DE LIMPIEZA DE HISTORIAL LIGHTBOX PP PLUS FALLIDO: "+resultadoLimpieza[1]+" - "+resultadoLimpieza[2]);
								}
							}else{
								LOGGER.info("YA SE REALIZO LIMPIEZA EL DIA DE HOY."+"\n");
							}
						}else{
							limpiezaRealizada = false;
							LOGGER.info("FECHA ACTUAL NO COINCIDE CON FECHA PROGRAMADA DE LIMPIEZA ("+prefFechaLimpieza+")\n");
						}
					}else{
						LOGGER.info("NO HAY DEFINIDA FECHA PARA INICIAR PROCESO DE LIMPIEZA."+"\n");
					}
				}else{
					LOGGER.info("MOVIL "+msisdn+" NO ES PREPAGO PLUS.\n");
				}
			}else{
				LOGGER.info("MOVIL "+msisdn+" NO ES PREPAGO.\n");
			}
			
		} catch (DAOException e) {
			LOGGER.error("Error al hacer TRUNCATE de la tabla PCD_NEG_DISABLELIGHTBOXPPAV.", e);
		} catch (Exception e) {
			LOGGER.error("Error realizando limpieza de la tabla PCD_NEG_DISABLELIGHTBOXPPAV.", e);
		}
	}
	
	public void validarDespliegueLightbox(PhaseEvent event){
		
		String msjLog = "";
		String msisdn = "";
		String mercado = "";
		String categoriaUser = "";
		try{
			ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
			categoriaUser = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "categoriaCliente");
			msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
			mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado");
			
			respuestaJson = "";
			if(Utils.isNotEmptyString(mercado) && MiEntelBusinessHelper.isMercadoPrepago(mercado)){
				if (Utils.isNotEmptyString(categoriaUser) && categoriaUser.equalsIgnoreCase(CATEGORIA_CLIENTE_PPAV)){
					if(ofertaBlindajeDelegate.validarDespliegueLightboxPPPlus(msisdn)){
						respuestaJson = JsonHelper.toJsonResponse("desplegar");
					}else{
						msjLog = "MOVIL "+msisdn+" NO QUIERE VER LIGHTBOX PREPAGO PLUS\n";
						LOGGER.info(msjLog);
						respuestaJson = JsonHelper.toJsonErrorMessage(msjLog);
					}
				}else{
					msjLog = "MOVIL "+msisdn+" NO ES PREPAGO PLUS.\n";
					LOGGER.info(msjLog);
					respuestaJson = JsonHelper.toJsonErrorMessage(msjLog);
				}
			}else{
				msjLog = "MOVIL "+msisdn+" NO ES PREPAGO.\n";
				LOGGER.info(msjLog);
				respuestaJson = JsonHelper.toJsonErrorMessage(msjLog);
			}
			
		} catch (DAOException e) {
			LOGGER.error("Error al consultar estado de despliegue del lightbox PP Plus para movil "+msisdn, e);
		} catch (Exception e) {
			LOGGER.error("Error al consultar estado de despliegue del lightbox PP Plus para movil "+msisdn, e);
		}
	}
	
	public void rechazarDespliegueLightbox(PhaseEvent event){
		
		String msisdn = "";
		try{
			ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
			msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
			ofertaBlindajeDelegate.rechazarDespliegueLightboxPPPlus(msisdn);
			respuestaJson = JsonHelper.toJsonResponse("");
			
		} catch (Exception e) {
			LOGGER.error("Exception inesperado al rechazar despliegue de lightbox prepago plus para movil "+msisdn, e);
			respuestaJson = JsonHelper.toJsonErrorMessage("");
		}
	}
	
	private String getPreference(String prefName){		               	
    	FacesContext context = FacesContext.getCurrentInstance();		 
    	PortletRequest req = (PortletRequest) context.getExternalContext().getRequest();		 
    	String fechaLimpieza = req.getPreferences().getValue(prefName,"");
    			
		return fechaLimpieza;    
    }
	
	public String getRespuestaJson() {
		return respuestaJson;
	}

	public void setRespuestaJson(String respuestaJson) {
		this.respuestaJson = respuestaJson;
	}

	/**
	 * @return the ofertaBlindajeDelegate
	 */
	public OfertaBlindajeDelegate getOfertaBlindajeDelegate() {
		return ofertaBlindajeDelegate;
	}

	/**
	 * @param ofertaBlindajeDelegate the ofertaBlindajeDelegate to set
	 */
	public void setOfertaBlindajeDelegate(
			OfertaBlindajeDelegate ofertaBlindajeDelegate) {
		this.ofertaBlindajeDelegate = ofertaBlindajeDelegate;
	}

}
