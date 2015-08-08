package com.epcs.cliente.perfil.controller.util;

import com.bea.content.Node;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.bean.ResumenEquipoBean;
import com.epcs.bean.ResumenLineaEquipoBean;
import com.epcs.cliente.perfil.bean.Oferta;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

public class EquipoControllerHelper {
	
	
	
	private ResumenEquipoBean resumenEquipoBean;
	
	private boolean ofertaMM = false;
	private boolean ofertaEquipo = false;	
	private String EC4G = MiEntelProperties.getProperty("mensaje.4Glte.compatible.equipo");
	private String PC4G = MiEntelProperties.getProperty("mensaje.4Glte.compatible.plan");
	private String SC4G = MiEntelProperties.getProperty("mensaje.4Glte.compatible.simCard");
	private String PNC4G = MiEntelProperties.getProperty("mensaje.4Glte.nocompatible.plan");
	private String SNC4G = MiEntelProperties.getProperty("mensaje.4Glte.nocompatible.simCard");	
	private String LM4G = MiEntelProperties.getProperty("mensaje.4Glte.nocompatible.LinkMinisitios");
	private String LMS4G = MiEntelProperties.getProperty("mensaje.4Glte.nocompatible.LinkMinisitiosSimcard");
	
	
	private String MOSTRAR_COMPATIBILIDAD_OFF = MiEntelProperties.getProperty("mostrar.compatibilidad4GLte");
	private String MOSTRAR_COMPATIBILIDAD_ON = MiEntelProperties.getProperty("mostrar.compatibilidad4GLte.on");
	
	private String urlCargarOferta = "";
	private String idOferta="";
	private static final String URL_CARGAR_OFERTA_DISPONIBLE = MiEntelProperties
	.getProperty("parametros.blindaje.urlCargarOferta");
	private static final Logger LOGGER = Logger
    .getLogger(EquipoControllerHelper.class);
	
	
	public EquipoControllerHelper(ResumenEquipoBean resumenEquipoBean){
		this.resumenEquipoBean=resumenEquipoBean;
	}

	public boolean isOfertaMM() {
		return ofertaMM;
	}
	
	public void setOfertaMM(boolean ofertaMM) {
		this.ofertaMM = ofertaMM;
	}
	public boolean isOfertaEquipo() {
		return ofertaEquipo;
	}
	public void setOfertaEquipo(boolean ofertaEquipo) {
		this.ofertaEquipo = ofertaEquipo;
	}

	/**
	 * Valida que mensaje se va a mostrar y retorna el objeto ResumenEquipoBean con los respectivos mensajes.
	 * @param equipo
	 * @param plan
	 * @param simcard
	 * @param oferta
	 * @return ResumenEquipoBean
	 */
	public ResumenEquipoBean validarMensajes4GLTE(boolean equipo, boolean plan,boolean simcard,List<Oferta> oferta){

		validarOfertas(oferta);
		
		if(equipo && !plan && !simcard && ofertaMM){
			//1
			obtenerMensaje(EC4G,PNC4G,SNC4G);
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_ON);
			LOGGER.info("Mensaje 1");
		}else if(equipo && !plan && !simcard && !ofertaMM){
			//2
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_OFF);
			LOGGER.info("Mensaje 2");
		}else if(equipo && !plan && simcard && ofertaMM){
			//3 generarlink Odertas
			obtenerMensaje(EC4G,"",SC4G);
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_ON);
			String mensajeOferta = urlBlindaje(idOferta, resumenEquipoBean.getNumeroPcs());
			resumenEquipoBean.setUrl4GLte(mensajeOferta);
			resumenEquipoBean.setNumMensaje("3");
			LOGGER.info("Mensaje 3");
		}else if(equipo && !plan && simcard && !ofertaMM){
			//4
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_OFF);
			LOGGER.info("Mensaje 4");
		}else if(equipo && plan && !simcard){
			//5
			LOGGER.info("Mensaje 5");
			obtenerMensaje(EC4G,PC4G,SNC4G);
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_ON);
		}else if(equipo && plan && simcard){
			//6
			LOGGER.info("Mensaje 6");
			obtenerMensaje(EC4G,PC4G,SC4G);
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_ON);
		}else if(!equipo && !plan && !simcard && ofertaEquipo){
			//7
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_OFF);
			LOGGER.info("Mensaje 7");
		}else if(!equipo && !plan && !simcard && !ofertaEquipo){
			//8
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_OFF);
			LOGGER.info("Mensaje 8");
		}else if(!equipo && !plan && simcard && ofertaMM && ofertaEquipo){
			//9
			LOGGER.info("Mensaje 9");
			obtenerMensaje(LM4G,LM4G,SC4G);
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_ON);
		}else if(!equipo && !plan && simcard && !ofertaMM && ofertaEquipo){
			//10
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_OFF);
			LOGGER.info("Mensaje 10");
		}else if(!equipo && !plan && simcard && !ofertaEquipo){
			//11
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_OFF);
			LOGGER.info("Mensaje 11");
		}else if(!equipo && plan && simcard && ofertaEquipo){
			//12
			LOGGER.info("Mensaje 12");
			obtenerMensaje("",PC4G,LMS4G);
			String mensajeOferta = urlBlindaje(idOferta, resumenEquipoBean.getNumeroPcs());
			resumenEquipoBean.setUrl4GLte(mensajeOferta);
			resumenEquipoBean.setNumMensaje("12");
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_ON);
		}else if(!equipo && plan && simcard && !ofertaEquipo){
			//13			
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_OFF);
			LOGGER.info("Mensaje 13");
		}else if(!equipo && plan && !simcard && ofertaEquipo){
			//14
			LOGGER.info("Mensaje 14");
			obtenerMensaje("",PC4G,LM4G);
			String mensajeOferta = urlBlindaje(idOferta, resumenEquipoBean.getNumeroPcs());
			resumenEquipoBean.setUrl4GLte(mensajeOferta);
			resumenEquipoBean.setNumMensaje("14");
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_ON);
		}else if(!equipo && plan && !simcard && !ofertaEquipo){
			//15
			resumenEquipoBean.setMostrar4Glte(MOSTRAR_COMPATIBILIDAD_OFF);
			LOGGER.info("Mensaje 15");
		}						
		return resumenEquipoBean;
	}
	
	/**
	 * Obtiene el mensaje del repositorio y los setea en el objeto resumenEquipoBean
	 * @param equipo
	 * @param plan
	 * @param simCard
	 */
	private void obtenerMensaje(String equipo, String plan , String simCard ) {			
        
		Node mensajeServicio;
		
		try {
			if(!"".equals(equipo)){				
				mensajeServicio = JSFPortletHelper.getContentNode("idContenido",equipo);				
				resumenEquipoBean.setMsjEquipo4GLte(mensajeServicio.getProperty("html").getValue().getStringValue());
				LOGGER.info("Mensaje Equipo");
				LOGGER.info(mensajeServicio.getProperty("html").getValue().getStringValue());
				
	        }		
			if(!"".equals(plan)){
				mensajeServicio = JSFPortletHelper.getContentNode("idContenido",plan);
				resumenEquipoBean.setMsjPlan4GLte(mensajeServicio.getProperty("html").getValue().getStringValue());
				LOGGER.info("Mensaje Plan");
			}
			if(!"".equals(simCard)){
				mensajeServicio = JSFPortletHelper.getContentNode("idContenido",simCard);
				resumenEquipoBean.setMsjSimCard4GLte(mensajeServicio.getProperty("html").getValue().getStringValue());
				LOGGER.info("Mensaje SimCard");
			}
		} catch (Exception e) {			
			LOGGER.error("Exception al obtener mensajes del repositorio.",e);
		}
		
	}

	/**
	 * Metodo que valida si el usuario tiene oferta Full MM u Oferta Equipo
	 * @param oferta
	 */
	public void validarOfertas(List<Oferta> oferta){
		LOGGER.info("OFERTAS: "+oferta.size());
		ofertaMM = false;
		ofertaEquipo = false;
		for(int i = 0; i < oferta.size(); i++){
			LOGGER.info("OFERTAS Grupo: "+oferta.get(i).getGrupoOferta());
			if(oferta.get(i).getGrupoOferta().equalsIgnoreCase("g4")){
				LOGGER.info("ofert Full:" + oferta.get(i).getEstado());
				ofertaMM = true;
				resumenEquipoBean.setTipoOferta(oferta.get(i).getGrupoOferta());
				idOferta=oferta.get(i).getOfertaId();
			}if(oferta.get(i).getGrupoOferta().equalsIgnoreCase("g3")){
				LOGGER.info("ofert Equipo:" + oferta.get(i).getGrupoOferta());
				ofertaEquipo = true;
				idOferta=oferta.get(i).getOfertaId();
				resumenEquipoBean.setTipoOferta("g3");
				resumenEquipoBean.setIdOferta(idOferta);
			}
		}		
	}
	
	/**
	 * Genera el link de la Oferta
	 * @param idOferta
	 * @param numeroPcsSeleccionado
	 * @return String
	 */
	
	public String urlBlindaje(String idOferta,String numeroPcsSeleccionado) {
		String url = "";
		try {

			LOGGER.info("ARMAR URL DE LA OFERTA");
						
			String idp = "";
			
			SeguridadDelegate seguridadDelegate = new SeguridadDelegate();

			idp = seguridadDelegate.consultarIDP(numeroPcsSeleccionado);
			
            LOGGER.info("numeroPcsSeleccionado :::: " + numeroPcsSeleccionado );
            LOGGER.info("idp :::: " + idp );
          
            url = URL_CARGAR_OFERTA_DISPONIBLE + idp + "&idOferta="+idOferta;
            
            resumenEquipoBean.setIdOferta(idOferta);
			
			LOGGER.info("URL :::: " + urlCargarOferta );
			
			
		}  catch (DAOException e) {
            LOGGER.error("DAOException al buscar informacion de la oferta Blidaje.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");
        }catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar informacion de la oferta Blidaje. msisdn: "+numeroPcsSeleccionado);
            JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");
        }catch (Exception e) {
            LOGGER.error("Exception al buscar oferta blidaje disponible.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");
        }
        
		return url;
	}
	
	public static String obtenerMensajeCuposDisponibles(ResumenLineaEquipoBean resumenLineaEquipoBean) {		
		String idContenido = "infoCuposDisponibles";
		try {			
			if (resumenLineaEquipoBean.getCantLineaSimOnly() > 0) {
				if (resumenLineaEquipoBean.getCantLineaNormal() == 0 && resumenLineaEquipoBean.getCantEquipoMultimedia() == 0) // SIM Only
					idContenido = "infoCuposDisponiblesSIMOnly";				
				if (resumenLineaEquipoBean.getCantLineaNormal() > 0 || resumenLineaEquipoBean.getCantEquipoMultimedia() > 0) // SIM Only + ACOC
					idContenido = "infoCuposDisponiblesSIMOnlyACOC";				
			} else {
				if (resumenLineaEquipoBean.getCantLineaNormal() > 0 || resumenLineaEquipoBean.getCantEquipoMultimedia() > 0) // ACOC
					idContenido = "infoCuposDisponiblesACOC";
			}
			return JSFPortletHelper.getNodePropertyAsString("idContenido", idContenido, "html");
		} catch (Exception e) {
			return "";
		}
	}

}
