package com.epcs.cliente.perfil.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.portlet.PortletPreferences;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.SessionHelper;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;

import com.epcs.administracion.suscripciones.dao.SuscripcionesBolsaDAO;
import com.epcs.administracion.suscripciones.delegate.SuscripcionesDelegate;
import com.epcs.bean.BolsaBean;
import com.epcs.bean.BolsaPPBean;
import com.epcs.bean.BolsasActualesDisponiblesBean;
import com.epcs.bean.ItemProductoCanjeBean;
import com.epcs.bean.MsisdnAsociadoBean;
import com.epcs.billing.prodfactura.controller.FacturacionElectronicaController;
import com.epcs.billing.prodfactura.dao.FacturacionElectronicaDAO;
import com.epcs.billing.prodfactura.delegate.FacturacionElectronicaDelegate;
import com.epcs.cliente.orden.dao.BolsaDAO;
import com.epcs.cliente.orden.delegate.BolsaDelegate;
import com.epcs.cliente.perfil.bean.Oferta;
import com.epcs.cliente.perfil.bean.CriteriosBusquedaOferta;
import com.epcs.cliente.perfil.dao.BolsaBlindajeDAO;
import com.epcs.cliente.perfil.dao.PlanDAO;
import com.epcs.cliente.perfil.delegate.OfertaBlindajeDelegate;
import com.epcs.cliente.perfil.delegate.OfertaVisaDelegate;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.cliente.perfil.util.PlanHelper;
import com.epcs.nba.NBAUtils;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

public class OfertaBlindajeController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String URL_CONSULTAR_OFERTA_DISPONIBLE = MiEntelProperties
			.getProperty("parametros.blindaje.urlOfertaDisponible");

	private static final String URL_CARGAR_OFERTA_DISPONIBLE = MiEntelProperties
			.getProperty("parametros.blindaje.urlCargarOferta");

	private static final String CODIGO_SIN_OFERTA = MiEntelProperties
			.getProperty("parametros.ofertas.codigo.sinOferta");

	private static final String CODIGO_BLINDAJE = MiEntelProperties
			.getProperty("parametros.ofertas.codigo.blindaje");

	private static final String CODIGO_ENTEL_VISA = MiEntelProperties
			.getProperty("parametros.ofertas.codigo.entelVisa");

	private static final String PAGE_APROBADA = MiEntelProperties
			.getProperty("parametros.ofertas.pageAprobado");

	private static final String PAGE_PREEMITADA = MiEntelProperties
			.getProperty("parametros.ofertas.pagePreemitido");	
	
	private static final String PAGE_PREAPOBADA = MiEntelProperties
			.getProperty("parametros.ofertas.pagePreaprobado");

	private static final int TIPO_OFERTA_VISAENTEL = 1;	
	private static final int TIPO_OFERTA_BLINDAJE = 0;
	
	//Grupos de oferta validos para v3
	private static final String G3 = MiEntelProperties
			.getProperty("parametros.ofertas.grupoOferta.3");
	private static final String G4 = MiEntelProperties
			.getProperty("parametros.ofertas.grupoOferta.4");
	private static final String G5 = MiEntelProperties
			.getProperty("parametros.ofertas.grupoOferta.5");
	private static final String G6 = MiEntelProperties
			.getProperty("parametros.ofertas.grupoOferta.6");
	private static final String G7 = MiEntelProperties
			.getProperty("parametros.ofertas.grupoOferta.7");
	
	private static final String G8 = MiEntelProperties
	.getProperty("parametros.ofertas.grupoOferta.8");
	private static final String G10 = MiEntelProperties
	.getProperty("parametros.ofertas.grupoOferta.10");
	
	private Boolean ofertaBlindajeG6 = false;
	
	private Boolean ofertaVisitada = false;
	private String estadoOfertaVisa = "";
	private String idOferta = "";
	private String nombreUsuario = "";
	private String mostrarOferta = "";
	private String nombreEstadoOferta = "";

	private OfertaVisaDelegate ofertaVisaDelegate;
	private OfertaBlindajeDelegate ofertaBlindajeDelegate;
	private BolsaDelegate bolsaBlindajeDelegate;	
	private List<Oferta> listaOferta;

	private static final Logger LOGGER = Logger
			.getLogger(OfertaBlindajeController.class);

	private String urlCargarOferta = "";
	
	private String grupoOferta = "";
	
	private String grupoOfertaPromoMes = "";
	
	private boolean isOfertaBAM = false;

	public void initBlindaje(PhaseEvent event) {
	    String numeroPcsSeleccionado = "";
		try {

			LOGGER.info("ARMAR URL DE LA OFERTA");
						
			String idp = "";
			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			        numeroPcsSeleccionado = ProfileWrapperHelper
					.getPropertyAsString(profileWrapper,
							"numeroPcsSeleccionado");

			SeguridadDelegate seguridadDelegate = new SeguridadDelegate();

			idp = seguridadDelegate.consultarIDP(numeroPcsSeleccionado);
			
            LOGGER.info("numeroPcsSeleccionado :::: " + numeroPcsSeleccionado );
            LOGGER.info("idp :::: " + idp );
            
            String idOferta = (String) JSFPortletHelper.getSession().getAttribute("idOferta");

			urlCargarOferta = URL_CARGAR_OFERTA_DISPONIBLE + idp + "&idOferta="+idOferta;
			
			if(isOfertaBAM)
				urlCargarOferta += "&ofertaBAM="+isOfertaBAM;
			
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
	}

	private String respuestaJson;

	public void controlOfertas(PhaseEvent event) {
	   String movil = "";
		try {

			LOGGER.info("CONTROL OFERTAS");
			
			Map<String, String> resp = new HashMap<String, String>();

			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			      movil = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroPcsSeleccionado");
			String rut = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "rutUsuarioSeleccionado");			
			
		    grupoOferta = JSFPortletHelper.getRequest().getParameter("grupoOferta") != null 
					? JSFPortletHelper.getRequest().getParameter("grupoOferta") : "";
			
			String isBanner = JSFPortletHelper.getRequest().getParameter("isBanner") != null 
					? JSFPortletHelper.getRequest().getParameter("isBanner") : "";
			
			String aaa =  ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
			Oferta ofertaDisponible = new Oferta();

			respuestaJson = "";

			if (!ofertaVisitada || !("").equals(isBanner))
			{
			Map<Integer, Oferta> ofertasList = consultarOfertas(isBanner);
			int codOferta = -1;
			int tamano = (ofertasList == null) ? -1 : ofertasList.size();

			/*if (tamano > 1) {
				Object[] ids = ofertasList.keySet().toArray();
				StringBuffer ofertasSB = new StringBuffer(100);
				for (int i = 0; i < tamano; i++) {
					Integer entero = (Integer) ids[i];
					String tipo = entero.toString();
					ofertasSB.append(tipo);
					if (i != (tamano - 1)) {
						ofertasSB.append(",");
					}
				}

				try {
					
					LOGGER.info("CONSULTAR ALTERNANCIA");
					LOGGER.info("rut:: "+rut);
					LOGGER.info("movil:: "+movil);
					LOGGER.info("ofertasSB:: "+ofertasSB.toString());
					
					codOferta = ofertaVisaDelegate.consultarAlternancia(rut,
							movil, ofertasSB.toString());
					
					LOGGER.info("ofertaVisaDelegate.consultarAlternancia():: "+codOferta);
					
				} catch (Exception ex) {
					LOGGER
							.error("Error consultado alternancia activa del usuario: Error : "
									+ ex.getMessage());
				}
                
				ofertaDisponible = (codOferta != -1) ? (Oferta) ofertasList
						.get(codOferta) : null;

			} else*/ if (tamano == 1) {
				ofertaDisponible = (Oferta) ofertasList
						.get((Integer) ofertasList.keySet().toArray()[0]);
				codOferta = ofertaDisponible.getTipo();
			} else {
				ofertaDisponible = null;
			}
			
			if (ofertaDisponible != null) {		
				
				
				switch(codOferta){

                case TIPO_OFERTA_BLINDAJE:
                	PlanHelper planHelper = new PlanHelper();
                	boolean levantarBanner = false;
    				String mercado;
    				NBAUtils nbaUtils=new NBAUtils();
    				List<MsisdnAsociadoBean> numerosAsociados=nbaUtils.getMovilesAsociadosSinAAA();
    				boolean isActivo = false;

    				//Fix G8
    				if(!ofertaDisponible.getGrupoOferta().equalsIgnoreCase("G8")){
    					//determinar el estado del movil si esta Activo o no
        				for(int i=0;i<numerosAsociados.size();i++){
        					String movilAsociado=((MsisdnAsociadoBean)numerosAsociados.get(i)).getNumeroPcs();
        					if(movilAsociado.equals(movil) && ("A").equals(numerosAsociados.get(i).getFlagEstadoMsisdn())){
        						isActivo = true;
        						break;
        					}
        				}
    				}
    				
                	if(!ofertaDisponible.getGrupoOferta().equalsIgnoreCase("G8") && !isActivo){
                				LOGGER.info("OFERTA RECHAZADA");
        						resp.put("codigo",CODIGO_SIN_OFERTA);
                	}else{
                		LOGGER.info("TIENE OFERTA BLINDAJE");
    					LOGGER.info("ofertaDisponible.getOfertaId():: "+ofertaDisponible.getOfertaId());
    					LOGGER.info("ofertaDisponible.getGrupoOferta():: "+ofertaDisponible.getGrupoOferta());
    					
    					//validaciones comerciales G4 Y G6 Agregado 04 sep 2014
    					try {
    						mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
    						if(ofertaDisponible.getGrupoOferta().equalsIgnoreCase(G6)){
    							ofertaBlindajeG6=true;
    						}
    						levantarBanner= planHelper.validacionesComerciales(ofertaDisponible,movil, mercado, aaa);
    					} catch (Exception e) {
    						 LOGGER.error(e);
    					}
    					if(ofertaDisponible.getGrupoOferta().equalsIgnoreCase(G7)){						
    						FacturacionElectronicaController fec = new FacturacionElectronicaController();
    						FacturacionElectronicaDelegate fed=new FacturacionElectronicaDelegate();
    						fed.setFacturacionElectronicaDAO(new FacturacionElectronicaDAO());
    						fec.setFacturacionElectronicaDelegate(fed);
    						fec.init(event);
    						
    						if(fec.getFacturacionElectronicaBean() != null && fec.isEstadoInscrito()){
    							
    							
    							//MARCAR FE POR OTROS MEDIOS
    							bolsaBlindajeDelegate= new BolsaDelegate();
    							bolsaBlindajeDelegate.setBolsaBlindajeDAO(new BolsaBlindajeDAO());
    							bolsaBlindajeDelegate.marcarOtroCanalBlindaje(Long.parseLong(ofertaDisponible.getOfertaId()));
    							
    							//NO LEVANTAR LIGHTBOX						
    							 LOGGER.info("NO TIENE OFERTAS");
    							 resp.put("codigo",CODIGO_SIN_OFERTA);
    					    	 respuestaJson = JsonHelper.toJsonResponse(resp);	
    					    	 break;
    						}
    					}
    					if(levantarBanner || ofertaDisponible.getGrupoOferta().equalsIgnoreCase("G8")){
    						//se levanta el banner
    	                    resp.put("codigo",CODIGO_BLINDAJE);
    	                    resp.put("codOferta",ofertaDisponible.getOfertaId());
    	                    resp.put("grupo",ofertaDisponible.getGrupoOferta());
    	                    resp.put("aaa",aaa);
    	                    }else{
    	                    	LOGGER.info("NO TIENE OFERTAS");
    							resp.put("codigo",CODIGO_SIN_OFERTA);
    	                    }
                		
                	}
                	respuestaJson = JsonHelper.toJsonResponse(resp);
                    break;

                case TIPO_OFERTA_VISAENTEL:
                    try{
	                       if(ofertaDisponible.getOfertaId() == null){
	                    	   
	                    	   if(ofertaDisponible.getEstado()!=null)
	                    	   this.ofertaVisaDelegate.insertarVisitaVisaEntel(ofertaDisponible.getMovil(), 
											                    			   ofertaDisponible.getRut(), 
											                    			   new Integer(ofertaDisponible.getEstado()));
	                    	   
	                    	    if(ofertaDisponible.getEstado()!=null)
	 	                        ofertaDisponible = this.ofertaVisaDelegate.getVisaEntelByMovilRutEstado(ofertaDisponible.getMovil(), 
	 	                        		                                                                ofertaDisponible.getRut(), 
																	                    			    new Integer(ofertaDisponible.getEstado()));
	                    	    
	 	                    }
	 	                    else if(ofertaDisponible.isVisitada() && !ofertaDisponible.isRechazada())
	 	                    	this.ofertaVisaDelegate.actualizarVisitaVisaEntel(Long.parseLong(ofertaDisponible.getOfertaId()));
	 	                    else
	 	                        ofertaDisponible = null;
	                       
						  if(ofertaDisponible!=null)
						   {
							    LOGGER.info("TIENE OFERTA ENTEL VISA");
								LOGGER.info("ofertaDisponible.getOfertaId():: "+ofertaDisponible.getOfertaId());
								LOGGER.info("ofertaDisponible.getEstado():: "+ofertaDisponible.getEstado());
							    
								resp.put("codigo",CODIGO_ENTEL_VISA);				  
								resp.put("aaa",aaa);
								resp.put("idOferta",ofertaDisponible.getOfertaId());	
								this.cargarNombreEstadoOferta(ofertaDisponible.getEstado());
								resp.put("estado",(String)JSFPortletHelper.getSession().getAttribute("nombreEstadoOferta"));
								JSFPortletHelper.getSession().setAttribute("idOfertaVisa", ofertaDisponible.getOfertaId());
								JSFPortletHelper.getSession().setAttribute("mostrarOferta", "OK");
								JSFPortletHelper.getSession().setAttribute("estadoOfertaVisa", ofertaDisponible.getEstado());	
													
						        respuestaJson = JsonHelper.toJsonResponse(resp);
                            }
						     else
	                       {
						    	 resp.put("codigo",CODIGO_SIN_OFERTA);
						    	 respuestaJson = JsonHelper.toJsonResponse(resp);
	                       }
                    }catch (Exception ex) {
                    	LOGGER.error("Error actualizando Oferta Visa Entel activa del usuario: Error : "+ex.getMessage());
                    }
                 break;
            }		
				        					
			}else{
				 LOGGER.info("NO TIENE OFERTAS");
				 resp.put("codigo",CODIGO_SIN_OFERTA);
		    	 respuestaJson = JsonHelper.toJsonResponse(resp);				
			}			

		}
			this.ofertaVisitada = true;

		 }  catch (DAOException e) {
            LOGGER.error("DAOException al consultar oferta blidaje disponible ", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");     
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al consultar oferta blidaje disponible. msisdn: "+movil);
            JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");
        }catch (Exception e) {
            LOGGER.error("Exception al buscar oferta blidaje disponible.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");
        }
	}

	public Map<Integer, Oferta> consultarOfertas(String isBanner) {

		Map<Integer, Oferta> resultado = new HashMap<Integer, Oferta>();
		Oferta ofertaBlindaje = new Oferta();
		Oferta ofertaVisa = new Oferta();
		CriteriosBusquedaOferta criterios = null;

		try {
						
			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			String movil = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroPcsSeleccionado");
			String rut = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "rutUsuarioSeleccionado");
			
			//valores usados para validaciones comerciales
			String mercado = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "mercado");

			String rutTitular = "";
			if(!MiEntelBusinessHelper.isMercadoPrepago(mercado))
				rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutTitular");
			else
				rutTitular = rut;
			
			String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroCuenta");
			String flagBam = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "flagBam");
			String aaa = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "aaa");
	
			
			String idCriteriosGrupo = JSFPortletHelper.getRequest().getParameter("idCriteriosGrupo") != null 
					? JSFPortletHelper.getRequest().getParameter("idCriteriosGrupo") : "";
					
			String gruposMultiOferta = JSFPortletHelper.getRequest().getParameter("gruposMultiOferta") != null 
					? JSFPortletHelper.getRequest().getParameter("gruposMultiOferta") : "";
			
            LOGGER.info("CONSULTAR OFERTA BLINDAJE");
		    LOGGER.info("movil ::: "+movil);	
		    
		    
		    if(Utils.isNotEmptyString(idCriteriosGrupo)){
		    	
		    	Node contentCriterios = JSFPortletHelper.getContentNode("idContenido", idCriteriosGrupo);
		    	
	            String grupo = contentCriterios.getProperty("grupoCampana").getValue().getStringValue();
	            String ordenCF = contentCriterios.getProperty("ordenCF").getValue().getStringValue();
	            String tipoPlanBolsa = contentCriterios.getProperty("tipoPlanBolsa").getValue() != null
	            	? contentCriterios.getProperty("tipoPlanBolsa").getValue().getStringValue(): "";
	            String plantillaOferta = contentCriterios.getProperty("plantillaOferta").getValue() != null
            		? contentCriterios.getProperty("plantillaOferta").getValue().getStringValue(): "";
            	
            	criterios = new CriteriosBusquedaOferta();
            	
            	if(Utils.isNotEmptyString(tipoPlanBolsa)){
            		criterios.setTipoPlanBolsa(Utils.buildListaComillasComas(tipoPlanBolsa));
            		criterios.setValidarTipoPlanBolsa(true);
            	}
            	
            	if(Utils.isNotEmptyString(plantillaOferta)){
            		criterios.setPlantillaOferta(Utils.buildListaComillasComas(plantillaOferta));
            		criterios.setValidarPlantillaOferta(true);
            	}
            	
            	if(!ordenCF.equals("NONE")){
            		criterios.setValidarCargoFijo(true);
            	}
	            
		    	criterios.setGrupoCampana(grupo);
		    	criterios.setMovil(movil);
		    	criterios.setOrdenCargoFijo(ordenCF);
		    	
		    	if(gruposMultiOferta.contains(criterios.getGrupoCampana()))
		    		criterios.setMultiOferta(true);
		    	
		    	//AAA >= 2. Banner NBA se oculta cuando AAA < 2
		    	if(MiEntelBusinessHelper.isAAAControlTotal(aaa) || MiEntelBusinessHelper.isAAATitular(aaa)){
		    		NBAUtils nbaUtils=new NBAUtils();
			    	List<MsisdnAsociadoBean> numerosAsociados=nbaUtils.getMovilesAsociados();
					//Consultamos los numeros asociados en busca de hallar el estado del movil logueado (numeroPcsSeleccionado)
					if(numerosAsociados != null){
						for(int i=0;i<numerosAsociados.size();i++){
							//Se obtiene el (i) numero asociado para procesarlo
							String movilAsociado=((MsisdnAsociadoBean)numerosAsociados.get(i)).getNumeroPcs();
							String mercadoFor=((MsisdnAsociadoBean)numerosAsociados.get(i)).getMercado();
							String flagEstadoMovil =((MsisdnAsociadoBean)numerosAsociados.get(i)).getFlagEstadoMsisdn();
							/* Si el movil asociado es igual al logueado, entonces validamos 
							 * que se encuentre activo y se recuperan todas las ofertas para dicho movil */
							if(movilAsociado.equals(movil) && ("A").equals(flagEstadoMovil)){
						    	listaOferta = ofertaBlindajeDelegate.ConsultarOfertaBlindajeCriterios(criterios);
						    	PlanHelper ph = new PlanHelper();
							    Iterator<Oferta> itOf = listaOferta.iterator();
							    while(itOf.hasNext()){
							    	Oferta o = (Oferta) itOf.next();
							    	if(!ph.validacionesComerciales(o, mercado, flagBam, aaa, rutTitular, numeroCuenta))
							    		itOf.remove();
							    }
							}
						}
					}else{
						listaOferta = ofertaBlindajeDelegate.ConsultarOfertaBlindajeCriterios(criterios);
						PlanHelper ph = new PlanHelper();
					    Iterator<Oferta> itOf = listaOferta.iterator();
					    while(itOf.hasNext()){
					    	Oferta o = (Oferta) itOf.next();
					    	if(!ph.validacionesComerciales(o, mercado, flagBam, aaa, rutTitular, numeroCuenta))
					    		itOf.remove();
					    }
					}
		    	}
		    	
		    }else if(("SI").equals(isBanner)){
		    	listaOferta = ofertaBlindajeDelegate.ConsultarOfertaBlindajeBanner(movil);
		    
		    }else{
		    	listaOferta = ofertaBlindajeDelegate.ConsultarOfertaBlindaje(movil);	
		    	
		    }
		    
	    	ofertaBlindaje = definirOfertaBlindaje(listaOferta, criterios);

			if (ofertaBlindaje != null) {
				
				LOGGER.info("RESPUESTA CONSULTAR OFERTA BLINDAJE");
				LOGGER.info("ofertaBlindaje.getOfertaId() ::: "+ofertaBlindaje.getOfertaId());	
				LOGGER.info("ofertaBlindaje.getGrupoOferta() :::"+ofertaBlindaje.getGrupoOferta());	
				
				JSFPortletHelper.getSession().setAttribute("idOferta", ofertaBlindaje.getOfertaId());
				
				boolean clientePreferencia =false;				
				
				try {
					clientePreferencia = this.ofertaVisaDelegate.isClientePreferencial(rut, numeroCuenta);					
	            } catch (Exception ex) {
	                
	            }	            	            
	            
				if (!ParametrosHelper.getExisteParametro("ofertas.grupoOfertaNoPermitido",ofertaBlindaje.getGrupoOferta()) && (!clientePreferencia 
						|| ofertaBlindaje.getGrupoOferta().equals(G5) || ofertaBlindaje.getGrupoOferta().equals(G8) || ofertaBlindaje.getGrupoOferta().equals(G10))) {
					resultado.put(ofertaBlindaje.getTipo(),
							ofertaBlindaje);
				}
				
			} else {
				
			ofertaVisa.setMovil(movil);
			ofertaVisa.setRut(rut);	
			
			LOGGER.info("OBTENER ESTADO OFERTA VISA");
			LOGGER.info("movil ::: "+movil);
			LOGGER.info("rut ::: "+rut);			
			
			try 
			{
				if(ofertaVisa.getMovil()!=null)
				ofertaVisa.setEstado(this.ofertaVisaDelegate.obtenerEstadoVisa(new Integer(ofertaVisa.getMovil()), ofertaVisa.getRut()));
            } catch (Exception ex) {
                
            }
            
            LOGGER.info("RESPUESTA OBTENER ESTADO VISA");
      	    LOGGER.info("ofertaVisa.getEstado()::: "+ofertaVisa.getEstado());
      	  
          if(ofertaVisa.getEstado()!=null)
          {
			if (!ParametrosHelper.getExisteParametro("ofertas.noPermitido",ofertaVisa.getEstado()) && !ofertaVisa.getEstado().equals("")) 
			{
				if(ParametrosHelper.getExisteParametro("ofertas.numeros",ofertaVisa.getEstado()))
				{
					    LOGGER.info("CONSULTAR OFERTA VISA");
					    LOGGER.info("movil ::: "+movil);
						LOGGER.info("rut ::: "+rut);
						LOGGER.info("estado ::: "+ofertaVisa.getEstado());		
					    
						ofertaVisa = this.ofertaVisaDelegate
								.getVisaEntelByMovilRutEstado(ofertaVisa.getMovil(),
										ofertaVisa.getRut(), new Integer(ofertaVisa
												.getEstado()));
						
						if (!ofertaVisa.isRechazada())
							resultado.put(ofertaVisa.getTipo(), ofertaVisa);
						
						LOGGER.info("RESPUESTA CONSULTAR OFERTA VISA");
						LOGGER.info("ofertaVisa.getOfertaId()::: "+ofertaVisa.getOfertaId() );
						LOGGER.info("ofertaVisa.getEstado()::: "+ofertaVisa.getEstado() );
						LOGGER.info("ofertaVisa.isRechazada()::: "+ofertaVisa.isRechazada());
				}
			 }
          }
	            LOGGER.info("CONSULTAR OFERTA BLINDAJE");
			    LOGGER.info("movil ::: "+movil);				
		
			    if(Utils.isNotEmptyString(idCriteriosGrupo)){
			    	
			    	Node contentCriterios = JSFPortletHelper.getContentNode("idContenido", idCriteriosGrupo);
			    	
		            String grupo = contentCriterios.getProperty("grupoCampana").getValue().getStringValue();
		            String ordenCF = contentCriterios.getProperty("ordenCF").getValue().getStringValue();
		            String tipoPlanBolsa = contentCriterios.getProperty("tipoPlanBolsa").getValue() != null
		            	? contentCriterios.getProperty("tipoPlanBolsa").getValue().getStringValue(): "";
		            String plantillaOferta = contentCriterios.getProperty("plantillaOferta").getValue() != null
	            		? contentCriterios.getProperty("plantillaOferta").getValue().getStringValue(): "";
	            	
	            	criterios = new CriteriosBusquedaOferta();
	            	
	            	if(Utils.isNotEmptyString(tipoPlanBolsa)){
	            		criterios.setTipoPlanBolsa(Utils.buildListaComillasComas(tipoPlanBolsa));
	            		criterios.setValidarTipoPlanBolsa(true);
	            	}
	            	
	            	if(Utils.isNotEmptyString(plantillaOferta)){
	            		criterios.setPlantillaOferta(Utils.buildListaComillasComas(plantillaOferta));
	            		criterios.setValidarPlantillaOferta(true);
	            	}
	            	
	            	if(!ordenCF.equals("NONE")){
	            		criterios.setValidarCargoFijo(true);
	            	}
		            
			    	criterios.setGrupoCampana(grupo);
			    	criterios.setMovil(movil);
			    	criterios.setOrdenCargoFijo(ordenCF);
			    	
			    	if(gruposMultiOferta.contains(criterios.getGrupoCampana()))
			    		criterios.setMultiOferta(true);
			    	
			    	//AAA >= 2. Banner NBA se oculta cuando AAA < 2
			    	if(MiEntelBusinessHelper.isAAAControlTotal(aaa) || MiEntelBusinessHelper.isAAATitular(aaa)){
			    		NBAUtils nbaUtils=new NBAUtils();
				    	List<MsisdnAsociadoBean> numerosAsociados=nbaUtils.getMovilesAsociados();
						//Consultamos los numeros asociados en busca de hallar el estado del movil logueado (numeroPcsSeleccionado)
						if(numerosAsociados != null){
							for(int i=0;i<numerosAsociados.size();i++){
								//Se obtiene el (i) numero asociado para procesarlo
								String movilAsociado=((MsisdnAsociadoBean)numerosAsociados.get(i)).getNumeroPcs();
								String mercadoFor=((MsisdnAsociadoBean)numerosAsociados.get(i)).getMercado();
								String flagEstadoMovil =((MsisdnAsociadoBean)numerosAsociados.get(i)).getFlagEstadoMsisdn();
								/* Si el movil asociado es igual al logueado, entonces validamos 
								 * que se encuentre activo y se recuperan todas las ofertas para dicho movil */
								if(movilAsociado.equals(movil) && ("A").equals(flagEstadoMovil)){
							    	listaOferta = ofertaBlindajeDelegate.ConsultarOfertaBlindajeCriterios(criterios);
							    	PlanHelper ph = new PlanHelper();
								    Iterator<Oferta> itOf = listaOferta.iterator();
								    while(itOf.hasNext()){
								    	Oferta o = (Oferta) itOf.next();
								    	if(!ph.validacionesComerciales(o, mercado, flagBam, aaa, rutTitular, numeroCuenta))
								    		itOf.remove();
								    }
								}
							}
						}else{
							listaOferta = ofertaBlindajeDelegate.ConsultarOfertaBlindajeCriterios(criterios);
							PlanHelper ph = new PlanHelper();
						    Iterator<Oferta> itOf = listaOferta.iterator();
						    while(itOf.hasNext()){
						    	Oferta o = (Oferta) itOf.next();
						    	if(!ph.validacionesComerciales(o, mercado, flagBam, aaa, rutTitular, numeroCuenta))
						    		itOf.remove();
						    }
						}
			    	}
			    	
			    }else if(("SI").equals(isBanner)){
			    	listaOferta = ofertaBlindajeDelegate.ConsultarOfertaBlindajeBanner(movil);
					    
				}else{
					listaOferta = ofertaBlindajeDelegate.ConsultarOfertaBlindaje(movil);			

				}			
				
				ofertaBlindaje = definirOfertaBlindaje(listaOferta,criterios);
	
				if (ofertaBlindaje != null) {
					
					LOGGER.info("RESPUESTA CONSULTAR OFERTA BLINDAJE");
					LOGGER.info("ofertaBlindaje.getOfertaId() ::: "+ofertaBlindaje.getOfertaId());	
					LOGGER.info("ofertaBlindaje.getGrupoOferta() :::"+ofertaBlindaje.getGrupoOferta());	
					
					JSFPortletHelper.getSession().setAttribute("idOferta", ofertaBlindaje.getOfertaId());
					
					
					boolean clientePreferencia =false;				
					
					try {
						clientePreferencia = this.ofertaVisaDelegate.isClientePreferencial(rut, numeroCuenta);					
		            } catch (Exception ex) {
		                
		            }	            	            
		            
					if (!ParametrosHelper.getExisteParametro("ofertas.grupoOfertaNoPermitido",ofertaBlindaje.getGrupoOferta()) && !clientePreferencia) {
						resultado.put(ofertaBlindaje.getTipo(),
								ofertaBlindaje);
					}
				}
			}
		} catch (DAOException e) {
			LOGGER.error("Error al buscar informacion de la oferta visa.", e);
			JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");
		} catch (Exception e) {
			LOGGER.error("Error al buscar oferta blidaje visa.", e);
			JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");
		}
		return resultado;
	}

	public void cargarTipoOfertaVisa(ActionEvent event) {

		String estadoOferta = (String) JSFPortletHelper.getSession()
				.getAttribute("estadoOfertaVisa");
		String contextOferta = "";
		
		if (estadoOferta != null) {
			if (ParametrosHelper.getExisteParametro("ofertas.aprobado", estadoOferta)) {
				contextOferta = PAGE_APROBADA;
			} else if (ParametrosHelper.getExisteParametro("ofertas.preemitido", estadoOferta)) {
				contextOferta = PAGE_PREEMITADA;

			} else if (ParametrosHelper.getExisteParametro("ofertas.preaprobado", estadoOferta)) {
				contextOferta = PAGE_PREAPOBADA;
			}
		}

		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getApplication().getNavigationHandler().handleNavigation(fc, null,
				contextOferta);
	}

	public void marcarOfertaVisa(PhaseEvent event) {

		this.idOferta = (String) JSFPortletHelper.getSession().getAttribute(
				"idOfertaVisa");
		Map<String, String> resp = new HashMap<String, String>();
		respuestaJson = "";

		try {
			LOGGER.info("MARCAR NO MOSTRAR OFERTA VISA");
			LOGGER.info("idOferta:::: "+idOferta);
			
            if(idOferta!=null)
			this.ofertaVisaDelegate.marcarOfertaVisa(new Long(idOferta)
					.longValue(), true);

			resp.put("estado", "0000");
			JSFPortletHelper.getSession().setAttribute("estadoOfertaVisa", "");
			JSFPortletHelper.getSession().setAttribute("idOfertaVisa", "");
			JSFPortletHelper.getSession().setAttribute("mostrarOferta", "");

		
		}  catch (DAOException e) {
	            LOGGER.error("Error al registrar actualizar la oferta ", e);
	            JSFMessagesHelper.addServiceErrorMessage("noinfoOfertas");     
	        
		} catch (SQLException e) {
			LOGGER.error("Error al registrar actualizar la oferta.", e);
			JSFMessagesHelper.addErrorCodeMessage("visa", "updateOferta");
			resp.put("estado", "0001");

		} catch (Exception e) {
			LOGGER.error("Error al registrar actualizar la oferta", e);
			JSFMessagesHelper.addErrorCodeMessage("visa", "updateOferta");
			resp.put("estado", "0001");
		}

		respuestaJson = JsonHelper.toJsonResponse(resp);
	}

	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		if (nombreUsuario == null || nombreUsuario == "") {
			ProfileWrapper profile = SessionHelper.getProfile(JSFPortletHelper
					.getRequest());

			try {
				nombreUsuario = ProfileWrapperHelper.getPropertyAsString(
						profile, "nombreUsuario");

				if (nombreUsuario != null) {
					nombreUsuario = nombreUsuario.split(" ")[0];
				}

			} catch (Exception e) {
				LOGGER.warn("No se obtuvo el nombre de Usuario autenticado", e);
			}
		}
		return nombreUsuario;
	}

	public void cargarNombreEstadoOferta(String idEstado) {
		try {
			JSFPortletHelper.getSession().setAttribute(
					"nombreEstadoOferta",
					MiEntelProperties.getProperty("parametros.ofertas.estado."
							+ idEstado));

		} catch (Exception e) {
			LOGGER.warn("No se obtuvo el nombre del estado de la oferta", e);
		}
	}

	/**
	 * @param nombreUsuario
	 *            the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getRespuestaJson() {
		return respuestaJson;
	}

	public void setRespuestaJson(String respuestaJson) {
		this.respuestaJson = respuestaJson;
	}

	public String getUrlCargarOferta() {
		return urlCargarOferta;
	}

	public void setUrlCargarOferta(String urlCargarOferta) {
		this.urlCargarOferta = urlCargarOferta;
	}

	public boolean isOfertaVisitada() {
		return ofertaVisitada;
	}

	public void setOfertaVisitada(boolean ofertaVisitada) {
		this.ofertaVisitada = ofertaVisitada;
	}

	public void marcarOfertaVisitada() {
		this.ofertaVisitada = true;
	}

	public String getEstadoOfertaVisa() {
		if (estadoOfertaVisa == null || estadoOfertaVisa == "") {
			estadoOfertaVisa = (String) JSFPortletHelper.getSession()
					.getAttribute("estadoOfertaVisa");
		}
		return estadoOfertaVisa;
	}

	public void setEstadoOfertaVisa(String estadoOfertaVisa) {
		this.estadoOfertaVisa = estadoOfertaVisa;
	}

	public String getIdOferta() {
		if (idOferta == null || idOferta == "") {
			idOferta = (String) JSFPortletHelper.getSession().getAttribute(
					"idOfertaVisa");
		}
		return idOferta;
	}

	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}

	public String getMostrarOferta() {
		mostrarOferta = (String) JSFPortletHelper.getSession().getAttribute(
				"mostrarOferta");
		return mostrarOferta;
	}

	public OfertaBlindajeDelegate getOfertaBlindajeDelegate() {
		return ofertaBlindajeDelegate;
	}

	public void setOfertaBlindajeDelegate(
			OfertaBlindajeDelegate ofertaBlindajeDelegate) {
		this.ofertaBlindajeDelegate = ofertaBlindajeDelegate;
	}

	public void setMostrarOferta(String mostrarOferta) {
		this.mostrarOferta = mostrarOferta;
	}
	
	public String getNombreEstadoOferta() {
		nombreEstadoOferta = (String) JSFPortletHelper.getSession()
				.getAttribute("nombreEstadoOferta");
		return nombreEstadoOferta;
	}

	public void setNombreEstadoOferta(String nombreEstadoOferta) {
		this.nombreEstadoOferta = nombreEstadoOferta;
	}

	public OfertaVisaDelegate getOfertaVisaDelegate() {
		return ofertaVisaDelegate;
	}

	public void setOfertaVisaDelegate(OfertaVisaDelegate ofertaVisaDelegate) {
		this.ofertaVisaDelegate = ofertaVisaDelegate;
	}

	public void setOfertaBlindajeG6(Boolean ofertaBlindajeG6) {
		this.ofertaBlindajeG6 = ofertaBlindajeG6;
	}

	public Boolean getOfertaBlindajeG6() {
		return ofertaBlindajeG6;
	}	
	
	public Oferta definirOfertaBlindaje(List<Oferta> ofertas, CriteriosBusquedaOferta criterios) {
		Oferta ofertaSeleccionada = null;
		ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
		String aaa = "";
		String flagBam = "";
		String mercado = "";
		String msisdn = "";
		String plan = "";
		boolean sw = true;
		List<BolsaBean> bolsasActuales;

		try {
			mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
					"mercado");
			msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
					"numeroPcsSeleccionado");
			aaa = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
					"aaa");
			flagBam = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
					"flagBam");
			
			try{
				PlanDelegate planDelegate = new PlanDelegate();
				planDelegate.setPlanDAO(new PlanDAO());
				
				plan = planDelegate.getPlanActualSSCC(msisdn, mercado, aaa).getCodbscs2();
			}catch (Exception e) {
				plan = "";
			}
			
			try{
				SuscripcionesDelegate suscripcionDelegate = new SuscripcionesDelegate();
				suscripcionDelegate.setSuscripcionesBolsaDAO(new SuscripcionesBolsaDAO());
				
				BolsasActualesDisponiblesBean bolsasActualesDisponiblesBean;
				bolsasActuales = new ArrayList<BolsaBean>();
				
                bolsasActualesDisponiblesBean = suscripcionDelegate.consultarBolsasActualesDisponibles(msisdn);
                bolsasActuales = bolsasActualesDisponiblesBean.getBolsasActuales();

			}catch (Exception e) {
				bolsasActuales = null;
			}

			if (ofertas != null) {
				if (ofertas.size() >= 1) {
					if(criterios != null){
						
						if(criterios.isMultiOferta()){
							if(criterios.getGrupoCampana().equalsIgnoreCase(G4)){
								ofertaSeleccionada = ofertas.get(0);
							}else if(criterios.getGrupoCampana().equalsIgnoreCase(G6)){
								List<Oferta> finalListOferta = new ArrayList<Oferta>();
								String prioridadTipos = criterios.getTipoPlanBolsa().replaceAll("[']", "");
								finalListOferta = PlanHelper.seleccionarOfertasPorPrioridadTipo(finalListOferta,ofertas,prioridadTipos);
								Collections.sort(finalListOferta);
								ofertaSeleccionada = finalListOferta.get(0);
							}
							
						}else{
							ofertaSeleccionada = ofertas.get(0);
						}
						
					}else{
						StringTokenizer prioridades = new StringTokenizer(grupoOferta, ","); //SS: G4,G10,G6,G5,G3,G7 - PP: G8
						
						while (sw && prioridades.hasMoreTokens()) {
							String p = prioridades.nextToken();
							
							for (Oferta o : ofertas) {
								if (o.getGrupoOferta().equals(p)) {
									if(p.equals(G4)){
										if(!(MiEntelBusinessHelper.isAAAControlTotal(aaa) ||
												MiEntelBusinessHelper.isAAATitular(aaa)) 
											|| o.getCodBscsOferta().equalsIgnoreCase(plan)){
											
											continue;
											
										}else{
											sw = false;
											ofertaSeleccionada = o;
											break;
										}
									}else if(p.equals(G6)){
										if(!(MiEntelBusinessHelper.isAAAControlTotal(aaa) ||
												MiEntelBusinessHelper.isAAATitular(aaa))){
											continue;
										}else{
			                				if(ocultarOfertaBolsa(p, o.getCodBscsOferta(), bolsasActuales)){
			                					continue;
			                				}else{
			                					sw = false;
												ofertaSeleccionada = o;
												break;
			                				}
		                				}
										
									}else if(p.equals(G3) || p.equals(G5)){
										sw = false;
										ofertaSeleccionada = o;
										break;
									}else if(p.equals(G7)){
										if(!MiEntelBusinessHelper.isAAATitular(aaa)){
											continue;
										}else{
											sw = false;
											ofertaSeleccionada = o;
											break;
										}
									}else if(p.equals(G8) && !MiEntelBusinessHelper.isUserBam(flagBam)){
										sw = false;
										ofertaSeleccionada = o;
										break;
									}else if(p.equals(G10)){
										sw = false;
										ofertaSeleccionada = o;
										NBAUtils util = new NBAUtils();
										isOfertaBAM = util.isOfertaBAM(o.getCodPlantilla());
										break;
									}else{
										LOGGER.warn("Grupo de oferta desconocido");
										continue;
									}
								}
							}						
						}
						
						if (sw) {
							LOGGER.warn("No se pudo definir oferta de blindaje por a alguno de los siguientes motivos: " +
									"\nNinguna de las ofertas cumplio con las validaciones por grupo." +
									"\nNo hay ofertas para mostrar con grupo de oferta conocido." +
									"\nNo se han definido todos grupos de oferta en el preference de prioridades.");
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.warn("No se pudo definir una oferta para el msisdn : "
					+ msisdn, e);
		}
		
		return ofertaSeleccionada;
	}
	
	private boolean ocultarOfertaBolsa(String grupoOferta, String codBolsaOfertada, List<BolsaBean> bolsasActuales){
		
		if(bolsasActuales == null || bolsasActuales.isEmpty())
			return false;
		
		String bolsasOfertaValidar = MiEntelProperties
				.getProperty("parametros.bolsas.validar.ofertas."+grupoOferta+".codigosBscs");
		
		if(bolsasOfertaValidar.contains("|"+codBolsaOfertada+"|")){
			String bolsasActualesValidar = MiEntelProperties
					.getProperty("parametros.bolsas.validar.actualesCliente."+codBolsaOfertada);
			
			for (BolsaBean bolsaActual : bolsasActuales){
				if(bolsasActualesValidar.contains("|"+bolsaActual.getSnCodigo().trim()+"|")){
					return true;
				}else{
					continue;
				}
			}
			return false;
			
		}else{
			return false;
		}
	}

	/**
	 * @return the grupoOferta
	 */
	public String getGrupoOferta() {
		return grupoOferta;
	}

	/**
	 * @param grupoOferta the grupoOferta to set
	 */
	public void setGrupoOferta(String grupoOferta) {
		this.grupoOferta = grupoOferta;
	}

	/**
	 * @param isOfertaBAM the isOfertaBAM to set
	 */
	public void setOfertaBAM(boolean isOfertaBAM) {
		this.isOfertaBAM = isOfertaBAM;
	}

	/**
	 * @return the isOfertaBAM
	 */
	public boolean isOfertaBAM() {
		return isOfertaBAM;
	}
	
	
	
	
}
