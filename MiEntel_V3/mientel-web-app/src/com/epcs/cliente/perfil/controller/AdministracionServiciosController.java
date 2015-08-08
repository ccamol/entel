package com.epcs.cliente.perfil.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.AdministracionServiciosBean;
import com.epcs.bean.BundleContent;
import com.epcs.bean.CatalogoServiciosBean;
import com.epcs.bean.CategoriaSAGENBean;
import com.epcs.bean.CiudadBean;
import com.epcs.bean.CodeDescBean;
import com.epcs.bean.ComunaBean;
import com.epcs.bean.CustomSAGENBean;
import com.epcs.bean.DireccionBean;
import com.epcs.bean.FamiliaSuscripcionBean;
import com.epcs.bean.PaginaServiciosSagenBean;
import com.epcs.bean.RegionBean;
import com.epcs.bean.ResultadoCrearSuscripcionSAGENBean;
import com.epcs.bean.ResultadoEliminarSuscripcionSAGENBean;
import com.epcs.bean.ResultadoFamiliaSegmentate;
import com.epcs.bean.SuscripcionSAGENBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.bean.UsuarioSAGENBean;
import com.epcs.cliente.perfil.delegate.AdministracionServiciosDelegate;
import com.epcs.provision.suscripcion.wssagenservice.crearsuscripcionservice.BundleContentsType;
import com.epcs.provision.suscripcion.wssagenservice.crearsuscripcionservice.ContentBundleType;
import com.epcs.provision.suscripcion.wssagenservice.crearsuscripcionservice.OptionalContentsType;
import com.epcs.provision.suscripcion.wssagenservice.crearsuscripcionservice.PackContentsType;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.error.ServiceMessages;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

import com.epcs.cliente.perfil.delegate.CuentaDelegate;

import com.epcs.bean.ResultadoConsultarSAGENBean;
import com.epcs.bean.CatalogoSAGENBean;

/**
 * Controller para portlet de Administracion de servicios.<br>
 * Proporciona la obtencion de servicios disponibles para un usuario, y comportamiento
 * comun de todos los servicios
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class AdministracionServiciosController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para AdministracionServiciosController
     */
    private static final Logger LOGGER = Logger
            .getLogger(AdministracionServiciosController.class);
    
    private AdministracionServiciosBean administracionServiciosBean;
    
    private CuentaDelegate cuentaDelegate;
    
    private UsuarioBean datosBuic;
    
    private ResultadoConsultarSAGENBean consultaSAGEN;
    
    private CatalogoSAGENBean catalogoSAGEN;
    
    private ResultadoCrearSuscripcionSAGENBean resultadoSuscripcionSAGENBean;
    
    private String respuestaJson;

	private String errorMessage;
	
	//hcastillo
	private SelectItem[] estados;
	//hcastillo	
	private String paginastotal; 
	//hcastillo
	private String habilitados;
	//hcastillo
	private String deshabilitados;
	//hcastillo
    private String bloqueados;

  
	private static final String PREFIJO_ENTEL = MiEntelProperties
	.getProperty("prefijo.entel");
    
	

    
    public AdministracionServiciosController() {
        super();
    }
    
    public void init(PhaseEvent event) {

        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {

            String numeroPcsSeleccionado = null;
            String mercado = null;
            try {

                final ProfileWrapper profileWrapper = ProfileWrapperHelper
                        .getProfile(JSFPortletHelper.getRequest());
                numeroPcsSeleccionado = ProfileWrapperHelper
                        .getPropertyAsString(profileWrapper,
                                "numeroPcsSeleccionado");
                mercado = ProfileWrapperHelper.getPropertyAsString(
                        profileWrapper, "mercado");

                AdministracionServiciosDelegate asDelegate = new AdministracionServiciosDelegate();
                administracionServiciosBean = asDelegate
                        .consultarEstadoServiciosDisponibles(
                                numeroPcsSeleccionado, mercado);
                
                if (MiEntelBusinessHelper.isMercadoSuscripcion(mercado)) {
                    try {
                        administracionServiciosBean
                                .setServicioCobroRevertidoBean(asDelegate
                                        .consultarListaComunik2(numeroPcsSeleccionado));
                    } catch (Exception e) {
                        LOGGER
                                .warn("No se pudo cargar el serivico comunik2",
                                        e);
                    }
                }
                
                //Datos BUIC
                String rutUsuario = null;
                rutUsuario = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuario");
                
                rutUsuario = rutUsuario == null ? ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado") : rutUsuario;
                datosBuic = consultarDatosBuic(numeroPcsSeleccionado,rutUsuario);
                consultaSAGEN = asDelegate.consultarSuscripcionSAGEN(numeroPcsSeleccionado);
                
            } catch (DAOException e) {
                LOGGER.error(
                        "DAOException al buscar informacion de Servicios Disponibles.",
                        e);
                JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");
            } catch (ServiceException e) {
                LOGGER.info("ServiceException al buscar Servicios Disponibles. msisdn: "+numeroPcsSeleccionado);
                JSFMessagesHelper.addServiceErrorMessage("inesperado");
            } catch (Exception e) {
                LOGGER.error(
                        "Exception al buscar informacion de Servicios Disponibles.",
                        e);
                JSFMessagesHelper.addServiceErrorMessage("inesperado");
            }
        }
    }
    
    public void initActualizarBUIC (PhaseEvent event){   	
    	
    	if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
	    	String numeroPcsSeleccionado = null;
	        try{			
	        	final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
	    		  
	    		numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
    		
	    		String rutUsuario = null;
	    		rutUsuario = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuario");
	            
	    		rutUsuario = rutUsuario == null ? ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado") : rutUsuario;
	            datosBuic = consultarDatosBuic(numeroPcsSeleccionado,rutUsuario);
	    		
	    		String region = JsfUtil.getRequestParameter("region");
	    		String ciudad = JsfUtil.getRequestParameter("ciudad");
	    		String comuna = JsfUtil.getRequestParameter("comuna");
	    		String calle = JsfUtil.getRequestParameter("calle");
	    		String nmro = JsfUtil.getRequestParameter("nmro");
	    		String dpto = JsfUtil.getRequestParameter("dpto");
	    		String email = JsfUtil.getRequestParameter("email");
	    		String sexo = JsfUtil.getRequestParameter("sexo");
	    		
	    		DireccionBean direccionContacto = new DireccionBean();
	    		direccionContacto.setRegion(new RegionBean(region, null));
	    		direccionContacto.setCiudad(new CiudadBean(ciudad, null));
	    		direccionContacto.setComuna(new ComunaBean(comuna, null));
	    		direccionContacto.setCalle(calle);
	    		direccionContacto.setNumero(nmro);
	    		direccionContacto.setDepartamento(dpto);
	    		
	    		datosBuic.setDireccionContacto(direccionContacto);	    		
	    		datosBuic.setEmail(email);

	    		datosBuic.setSexo(sexo);
	    		
	    		this.cuentaDelegate.actualizarDatos(datosBuic);
	    		
	    		respuestaJson = JsonHelper.toJsonResponse("Ok");	    		
	    	}
	    	catch (DAOException e) {
	    		LOGGER.error("DAOException al actualizar informacion del BUIC.", e);
	    		JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");
	    	}
	    	catch (ServiceException e) {
	    		LOGGER.info("ServiceException al actualizar informacion del BUIC.");
	    		JSFMessagesHelper.addServiceErrorMessage("general");
	    	}
	    	catch(Exception e){
	    		LOGGER.error("Excepcion desconocida al tratar de actualizar informacion del BUIC.", e);
	    		JSFMessagesHelper.addServiceErrorMessage("inesperado");
	    	}
    	}	    	
    }
    
    public void initCrearSuscripcionSAGEN (PhaseEvent event){  
    	
    	if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
    		  String numeroPcsSeleccionado = null;
              
	        try{		
	        	 final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
	    		  
	    		 numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");

	    		 String rutUsuario = null;
	        	 rutUsuario = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuario");

		            
	        	 rutUsuario = rutUsuario == null ? ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado") : rutUsuario;
		         datosBuic = consultarDatosBuic(numeroPcsSeleccionado,rutUsuario);
	        	
	    		 AdministracionServiciosDelegate asDelegate = new AdministracionServiciosDelegate();
	    		  
	    		 SuscripcionSAGENBean suscripcionSAGENBean = new SuscripcionSAGENBean();	    		  
	    		 suscripcionSAGENBean.setMsisdn(numeroPcsSeleccionado);
	    		  
	    		 CustomSAGENBean customSAGENBean = new CustomSAGENBean();	    		  
	    		 customSAGENBean.setDiasSusc(JsfUtil.getRequestParameter("diasSusc"));
	    		 customSAGENBean.setHoraInicial(JsfUtil.getRequestParameter("horaInicial"));
	    		 customSAGENBean.setHoraFinal(JsfUtil.getRequestParameter("horaFinal"));
	    		  
	    		 List<CategoriaSAGENBean> categorias = obtenerCategorias(JsfUtil.getRequestParameter("categorias"));
	    		  
	    		 UsuarioSAGENBean usuarioSAGENBean = new UsuarioSAGENBean();
	    		  
	    		 usuarioSAGENBean.setNombre(datosBuic.getPrimerNombre() + " " + datosBuic.getApellidoPaterno());
	    		 usuarioSAGENBean.setInfo("");
	    		 usuarioSAGENBean.setEmail(datosBuic.getEmail());
	    		 usuarioSAGENBean.setSexo(datosBuic.getSexoDesc());	    		  
	    		 usuarioSAGENBean.setDireccion(datosBuic.getDireccionContacto());
	    		 
	    		 if (datosBuic.getFechaNacimiento()!=null){
		    		 SimpleDateFormat formatoOUT = new SimpleDateFormat("dd/MM/yyyy");
		    		 usuarioSAGENBean.setFechaNacimiento(formatoOUT.format(datosBuic.getFechaNacimiento()));
	    		 }
	    		 else{
	    			 usuarioSAGENBean.setFechaNacimiento("");
	    		 }
   		  	  
	    		 customSAGENBean.setCategorias(categorias);
	    		 customSAGENBean.setUsuario(usuarioSAGENBean);
	    		 suscripcionSAGENBean.setCustom(customSAGENBean);    		  
	    		  
	    		 resultadoSuscripcionSAGENBean= asDelegate.crearSuscripcionSAGEN(suscripcionSAGENBean);
	    		 respuestaJson = JsonHelper.toJsonResponse("Ok");
	        }
	    	catch (DAOException e) {
	    		LOGGER.error("DAOException al crear suscripcion SAGEN.", e);
	    		JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");
	    	}
	    	catch (ServiceException e) {
	    		 LOGGER.info("ServiceException al crear suscripcion SAGEN.");
	    		 JSFMessagesHelper.addServiceErrorMessage("general");
	    	}
	    	catch(Exception e){
	    		LOGGER.error("Excepcion desconocida al crear suscripcion SAGEN.", e);
	    		JSFMessagesHelper.addServiceErrorMessage("inesperado");
	    	}
    	}	 	
    } 
    
    public void initActualizarSuscripcionSAGEN (PhaseEvent event){  
    	
    	if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
    		  String numeroPcsSeleccionado = null;
            
	        try{	
	        	 final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());	    		  
	    		 numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");

	    		 AdministracionServiciosDelegate asDelegate = new AdministracionServiciosDelegate();
	    		 consultaSAGEN = asDelegate.consultarSuscripcionSAGEN(numeroPcsSeleccionado);
	    		 
	    		 List<CategoriaSAGENBean> categorias = obtenerCategorias(JsfUtil.getRequestParameter("categorias"));	    		
	    		 
	    		 if (consultaSAGEN!=null){	    			 
	    			 SuscripcionSAGENBean suscripcionSAGENBean = new SuscripcionSAGENBean();
		    		 suscripcionSAGENBean.setMsisdn(numeroPcsSeleccionado);
		    		  
		    		 CustomSAGENBean customSAGENBean = new CustomSAGENBean();	    		  
		    		 customSAGENBean.setDiasSusc(consultaSAGEN.getCustom().getDiasSusc());
		    		 customSAGENBean.setHoraInicial(consultaSAGEN.getCustom().getHoraInicial());
		    		 customSAGENBean.setHoraFinal(consultaSAGEN.getCustom().getHoraFinal());		    		 
		    		 customSAGENBean.setUsuario(consultaSAGEN.getCustom().getUsuario());
		    		 customSAGENBean.setCategorias(categorias);
		    		 
		    		 suscripcionSAGENBean.setCustom(customSAGENBean);    		  
		    		  
		    		 resultadoSuscripcionSAGENBean= asDelegate.crearSuscripcionSAGEN(suscripcionSAGENBean);		    		 
		    		 respuestaJson = JsonHelper.toJsonResponse("Ok");
	    		 }
	    		 else{
	    			 respuestaJson = JsonHelper.toJsonResponse("Error", "Error al obtener informacion del servicio.");
	    		 }
	        }
	    	catch (DAOException e) {
	    		LOGGER.error("DAOException al crear suscripcion SAGEN.", e);
	    		JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");
	    	}
	    	catch (ServiceException e) {
	    		 LOGGER.info("ServiceException al crear suscripcion SAGEN.");
	    		 JSFMessagesHelper.addServiceErrorMessage("general");
	    	}
	    	catch(Exception e){
	    		LOGGER.error("Excepcion desconocida al crear suscripcion SAGEN.", e); 
	    		JSFMessagesHelper.addServiceErrorMessage("inesperado");
	    	}
    	}	 	
    }   
    
    public void initEliminarSuscripcionSAGEN (PhaseEvent event){  
    	
    	if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
    		  String numeroPcsSeleccionado = null;
              
	        try{		
	        	 final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());	    	 
	    		 numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");	    	 
		       
	    		 AdministracionServiciosDelegate asDelegate = new AdministracionServiciosDelegate();
	    		 
	    		 ResultadoEliminarSuscripcionSAGENBean result = new ResultadoEliminarSuscripcionSAGENBean();
	    		 result = asDelegate.eliminarSuscripcionSAGEN(numeroPcsSeleccionado);
	    		 
	    		 respuestaJson = JsonHelper.toJsonResponse("Ok");
	        }
	    	catch (DAOException e) {
	    		LOGGER.error("DAOException al crear suscripcion SAGEN.", e);
	    		JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");
	    	}
	    	catch (ServiceException e) {
	    		 LOGGER.info("ServiceException al crear suscripcion SAGEN.");
	    		 JSFMessagesHelper.addServiceErrorMessage("general");
	    	}
	    	catch(Exception e){
	    		LOGGER.error("Excepcion desconocida al crear suscripcion SAGEN.", e); 
	    		JSFMessagesHelper.addServiceErrorMessage("inesperado");
	    	}
    	}	 	
    }    
    
    public List<CategoriaSAGENBean> obtenerCategorias(String categorias){    	
    	String idCategoria;
    	String nombreCategoria;
    	
    	if (categorias!=null && !categorias.equals("")){
    		List<CategoriaSAGENBean> categoriasSAGEN = new ArrayList<CategoriaSAGENBean>();
    		
    		String prueba[] = categorias.split("\\|");
    		
    		for (String item: prueba){
    			String valores[] = item.split("\\-");
    			
    			idCategoria = valores[0];
    			nombreCategoria = valores[1];
    			
    			CategoriaSAGENBean categoriaSAGENBean = new CategoriaSAGENBean();
    			categoriaSAGENBean.setIdCategoria(idCategoria);
    			categoriaSAGENBean.setNombreCategoria(nombreCategoria);
    			
    			categoriasSAGEN.add(categoriaSAGENBean);    			
    		}  
    		
    		return categoriasSAGEN;
    	}    	
    	return null;
    }
  
    public void initCatalogoSAGEN(PhaseEvent event){
    	
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {    	
	    	try{     		  
	  		  
	    	  AdministracionServiciosDelegate asDelegate = new AdministracionServiciosDelegate();
	  		  this.catalogoSAGEN = asDelegate.obtenerCatalogoSAGEN();	  		  
			}
			catch (DAOException e) {
				LOGGER.error("DAOException al consultar catalogo SAGEN.", e);
				JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");
		  	}
		  	catch (ServiceException e) {
		  		LOGGER.info("ServiceException al consultar catalogo SAGEN.");
		  		JSFMessagesHelper.addServiceErrorMessage("general");
		  	}
		  	catch(Exception e){
		  		LOGGER.error("Excepcion desconocida al consultar catalogo SAGEN.", e);
		  		JSFMessagesHelper.addServiceErrorMessage("inesperado");
		  	}    
        }
	  	
	  	LOGGER.info("Saliendo del metodo obtenerCatalogoSAGEN... ");	
    }
    

    
	/**
	 * Inits suscripcion de servicios.
	 *
	 * @param phase the phase
	 * @author Hugo Castillo
	 */
	public void initPageData(PhaseEvent event) {
		//if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {   
			String numeroPcs = "";
			String estado="";
			CatalogoServiciosBean catalogoservices=null;
			ServiceMessages serviceMessages = MiEntelProperties
			.getServiceMessages();
			String numeroPcsSeleccionado=null;
			try {

				ProfileWrapper profile = ProfileWrapperHelper
				.getProfile(JSFPortletHelper.getRequest());
				numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado");
				AdministracionServiciosDelegate asDelegate = new AdministracionServiciosDelegate();
				
				List<FamiliaSuscripcionBean> suscripcion=null;
				suscripcion=ParametrosHelper.getSuscripcionFamilia("familias");
				for (int i=0;i<suscripcion.size();i++){
					FamiliaSuscripcionBean fsb=suscripcion.get(i);
					estado=asDelegate.getEstadoFamiliaService(numeroPcsSeleccionado, fsb.getFamilia());
					fsb.setEstado(estado);
				}
				
				catalogoservices = asDelegate.obtenerCatalogoActualServicios(numeroPcsSeleccionado,suscripcion);

				if (catalogoservices != null) {
					respuestaJson = JsonHelper.toJsonResponse(catalogoservices);
				} else {
					respuestaJson = JsonHelper.toJsonResponse("Error", "Error al obtener informacion del servicio.");
				}

			} catch (DAOException e) {
				LOGGER.error("DAOException al consultar catalogo SAGEN.", e);
				JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");

			} catch (ServiceException e) {
				LOGGER.info("ServiceException al consultar catalogo SAGEN.");
				JSFMessagesHelper.addServiceErrorMessage("general");

			} catch (Exception e) {
				LOGGER.error("Excepcion desconocida al consultar catalogo SAGEN.", e);
				JSFMessagesHelper.addServiceErrorMessage("inesperado");
			}
		//}

	}
	
	
	/**
	 * @param event
	 */
	public void habilitarService(PhaseEvent event) {
		if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {    
			String numeroPcsSeleccionado;
			ResultadoCrearSuscripcionSAGENBean resultCrearSuscripcionSAGENBean =null;
			try {
				final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
				numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
		 	    String overriding = JsfUtil.getRequestParameter("overriding")!=null?JsfUtil.getRequestParameter("overriding"):"";
		 	    String horaSms= JsfUtil.getRequestParameter("horasms")!=null?JsfUtil.getRequestParameter("horasms"):"";
	    		String sendSmsSuccess = JsfUtil.getRequestParameter("smsagenda")!=null?JsfUtil.getRequestParameter("smsagenda"):"";
	    		String horaMms = JsfUtil.getRequestParameter("horamms")!=null?JsfUtil.getRequestParameter("horamms"):"";
                String duration= JsfUtil.getRequestParameter("diasusc")!=null?JsfUtil.getRequestParameter("diasusc"):"";
                String diasSusc=JsfUtil.getRequestParameter("selectdias")!=null?JsfUtil.getRequestParameter("selectdias"):"";
                String vasid=JsfUtil.getRequestParameter("vasid")!=null?JsfUtil.getRequestParameter("vasid"):"";
                String providerid=JsfUtil.getRequestParameter("providerid")!=null?JsfUtil.getRequestParameter("providerid"):"";
                String la=JsfUtil.getRequestParameter("la")!=null?JsfUtil.getRequestParameter("la"):"";
                String canal=JsfUtil.getRequestParameter("canal")!=null?JsfUtil.getRequestParameter("canal"):"";
                String suscriptionid=JsfUtil.getRequestParameter("suscriptionid")!=null?JsfUtil.getRequestParameter("suscriptionid"):"";
                String serviceid=JsfUtil.getRequestParameter("serviceid")!=null?JsfUtil.getRequestParameter("serviceid"):"";
                String optionalcontent=JsfUtil.getRequestParameter("packcontent")!=null?JsfUtil.getRequestParameter("packcontent"):"";
				SuscripcionSAGENBean suscripcionSAGENBean = new SuscripcionSAGENBean();	    		  
				suscripcionSAGENBean.setMsisdn(PREFIJO_ENTEL+numeroPcsSeleccionado);
				suscripcionSAGENBean.setVasID(vasid);
				suscripcionSAGENBean.setProviderID(providerid);
				suscripcionSAGENBean.setSuscriptionID(suscriptionid);
				suscripcionSAGENBean.setCanal(canal);
				suscripcionSAGENBean.setLa(la);
				suscripcionSAGENBean.setServiceID(serviceid);
				CustomSAGENBean customSAGENBean = new CustomSAGENBean();
				customSAGENBean.setOverriding(overriding);
				customSAGENBean.setDuration(duration);				
				customSAGENBean.setSendSmsSuccess(sendSmsSuccess);
				customSAGENBean.setDay(diasSusc);
				customSAGENBean.setHour(horaMms);
				customSAGENBean.setTime(horaSms);
				if(!optionalcontent.equals("")){
					BundleContent cd= new BundleContent();
					cd.setId(optionalcontent);
					ArrayList<BundleContent> bundles= new ArrayList<BundleContent>();
					bundles.add(cd);
					cd=null;
					customSAGENBean.setOptionalcontents(bundles);
                    bundles=null;
				}
				
				suscripcionSAGENBean.setCustom(customSAGENBean);  
				AdministracionServiciosDelegate asDelegate = new AdministracionServiciosDelegate();
				resultCrearSuscripcionSAGENBean=asDelegate.ResultadoCrearSuscriptionServices(suscripcionSAGENBean);
				if(resultCrearSuscripcionSAGENBean!=null){
					if(resultCrearSuscripcionSAGENBean.getTxID().equals("Ok")){
						respuestaJson = JsonHelper.toJsonResponse("Ok");
					}else{
						respuestaJson = JsonHelper.toJsonErrorMessage(resultCrearSuscripcionSAGENBean.getTxID());
					}
				}else{
	    			 respuestaJson = JsonHelper.toJsonErrorMessage("");
	    		 }
				
			}
			catch (DAOException e) {
				LOGGER.error("DAOException al habilitar suscripcion SAGEN.", e);
				JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");
			}
			catch (ServiceException e) {
				LOGGER.info("ServiceException al habilitar suscripcion SAGEN.");
				JSFMessagesHelper.addServiceErrorMessage("general");
			}
			catch(Exception e){
				LOGGER.error("Excepcion desconocida al habilitar suscripcion SAGEN.", e);
				JSFMessagesHelper.addServiceErrorMessage("inesperado");
			}
		}
	}

	
	
	/**
	 * @param event
	 */
	public void desHabilitarService(PhaseEvent event) {
		if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) { 
	   		  String numeroPcsSeleccionado = null;
              
		        try{		
		        	 final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());	    	 
		    		 numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");	    	 
			       
		    		 AdministracionServiciosDelegate asDelegate = new AdministracionServiciosDelegate();
		    		 String serviceId = JsfUtil.getRequestParameter("service")!=null?JsfUtil.getRequestParameter("service"):"";
		    		 String canal = JsfUtil.getRequestParameter("canal")!=null?JsfUtil.getRequestParameter("canal"):"";
		    		 String vasId=  JsfUtil.getRequestParameter("vasId")!=null?JsfUtil.getRequestParameter("vasId"):"";
		    		 String providerId=JsfUtil.getRequestParameter("providerId")!=null?JsfUtil.getRequestParameter("providerId"):"";
		    		 String suscriptionId=JsfUtil.getRequestParameter("suscriptionId")!=null?JsfUtil.getRequestParameter("suscriptionId"):"";
		    		 String la=JsfUtil.getRequestParameter("la")!=null?JsfUtil.getRequestParameter("la"):"";
		    		 ResultadoEliminarSuscripcionSAGENBean result = new ResultadoEliminarSuscripcionSAGENBean();
		    		 
		    		 SuscripcionSAGENBean suscripcionSAGENBean = new SuscripcionSAGENBean();
					 suscripcionSAGENBean.setMsisdn(PREFIJO_ENTEL+numeroPcsSeleccionado);
		    		 suscripcionSAGENBean.setCanal(canal);	
		    		 suscripcionSAGENBean.setVasID(vasId);
		    		 suscripcionSAGENBean.setProviderID(providerId);
		    		 suscripcionSAGENBean.setServiceID(serviceId);
		    		 suscripcionSAGENBean.setSuscriptionID(suscriptionId);
		    		 suscripcionSAGENBean.setLa(la);
		    		 result = asDelegate.eliminarSuscripcionServiciosSagen(suscripcionSAGENBean);
		    		 if(result!=null){
		    			 if(result.getTxID().equals("Ok")){
		    				 respuestaJson = JsonHelper.toJsonResponse("Ok");
		    			 }else{
		    				 respuestaJson = JsonHelper.toJsonErrorMessage(result.getTxID());
		    			 }
		    		 }else{
		    			 respuestaJson = JsonHelper.toJsonErrorMessage("");
		    		 }
		        }
		    	catch (DAOException e) {
		    		LOGGER.error("DAOException al deshabilitar una suscripcion SAGEN.", e);
		    		JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");
		    	}
		    	catch (ServiceException e) {
		    		 LOGGER.info("ServiceException al deshabilitar una suscripcion SAGEN.");
		    		 JSFMessagesHelper.addServiceErrorMessage("general");
		    	}
		    	catch(Exception e){
		    		LOGGER.error("Excepcion desconocida al deshabilitar una suscripcion SAGEN.", e); 
		    		JSFMessagesHelper.addServiceErrorMessage("inesperado");
		    	}			
			
		}
	}	
	
	
	/**
	 * @param event
	 */
	public void bloquearFamiliaService(PhaseEvent event) {
		if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) { 
	   		  String numeroPcsSeleccionado = null;
	   		   ResultadoFamiliaSegmentate resultadoFamiliaSegmentate=null;
              
		        try{		
		        	 final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());	
		        	 String habilitados=this.getHabilitados();
		    		 numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");	    	 
		    		 ResultadoEliminarSuscripcionSAGENBean result = new ResultadoEliminarSuscripcionSAGENBean();
		    		 AdministracionServiciosDelegate asDelegate = new AdministracionServiciosDelegate();
		    		 String familia = JsfUtil.getRequestParameter("family")!=null?JsfUtil.getRequestParameter("family"):"";
		    		 String serviceId = JsfUtil.getRequestParameter("service")!=null?JsfUtil.getRequestParameter("service"):"";
		    		 String canal = JsfUtil.getRequestParameter("canal")!=null?JsfUtil.getRequestParameter("canal"):"";
		    		 String vasId=  JsfUtil.getRequestParameter("vasId")!=null?JsfUtil.getRequestParameter("vasId"):"";
		    		 String providerId=JsfUtil.getRequestParameter("providerId")!=null?JsfUtil.getRequestParameter("providerId"):"";
		    		 String suscriptionId=JsfUtil.getRequestParameter("suscriptionId")!=null?JsfUtil.getRequestParameter("suscriptionId"):"";
		    		 String la=JsfUtil.getRequestParameter("la")!=null?JsfUtil.getRequestParameter("la"):"";
		    		 String estado=JsfUtil.getRequestParameter("estado")!=null?JsfUtil.getRequestParameter("estado"):"";
		    		 SuscripcionSAGENBean suscripcionSAGENBean = new SuscripcionSAGENBean();	    		
		    		 suscripcionSAGENBean.setMsisdn(PREFIJO_ENTEL+numeroPcsSeleccionado);
		    		 suscripcionSAGENBean.setFamilia(familia);
		    		 suscripcionSAGENBean.setServiceID(serviceId);
		    		 suscripcionSAGENBean.setCanal(canal);
		    		 suscripcionSAGENBean.setSuscriptionID(suscriptionId);
		    		 suscripcionSAGENBean.setLa(la);
		    		 suscripcionSAGENBean.setProviderID(providerId);
		    		 suscripcionSAGENBean.setVasID(vasId);
		    		 
		    		 if(estado.equals(habilitados)){
		    			 //si el estado es habilitado realiza la deshabilitacion del suscripcion junto con el bloqueo
		    			 result = asDelegate.eliminarSuscripcionServiciosSagen(suscripcionSAGENBean);
		    		 }
		    		 
		    		 
		    		 
		    		 resultadoFamiliaSegmentate=asDelegate.bloquearFamiliaSegmentate(suscripcionSAGENBean);
		    		 if(resultadoFamiliaSegmentate!=null){
		    				 respuestaJson = JsonHelper.toJsonResponse("Ok");
		    		 }else{
		    			 respuestaJson = JsonHelper.toJsonErrorMessage("");
		    		 }

		        }
		    	catch (DAOException e) {
		    		LOGGER.error("DAOException al bloquear una familia SEGMENTATE.", e);
		    		JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");
		    	}
		    	catch (ServiceException e) {
		    		 LOGGER.info("ServiceException al bloquear una familia SEGMENTATE.");
		    		 JSFMessagesHelper.addServiceErrorMessage("general");
		    	}
		    	catch(Exception e){
		    		LOGGER.error("Excepcion desconocida al bloquear una familia SEGMENTATE.", e); 
		    		JSFMessagesHelper.addServiceErrorMessage("inesperado");
		    	}			
			
		}
	}
	
	
	/**
	 * @param event
	 */
	public void desbloquearFamiliaService(PhaseEvent event) {
		if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) { 
			String numeroPcsSeleccionado = null;
			ResultadoFamiliaSegmentate resultadoFamiliaSegmentate=null;
			try{		
				final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());	    	 
				numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");	    	 
				AdministracionServiciosDelegate asDelegate = new AdministracionServiciosDelegate();
				String family = JsfUtil.getRequestParameter("family")!=null?JsfUtil.getRequestParameter("family"):"";
				SuscripcionSAGENBean suscripcionSAGENBean = new SuscripcionSAGENBean();	    		
				suscripcionSAGENBean.setMsisdn(PREFIJO_ENTEL+numeroPcsSeleccionado);
				resultadoFamiliaSegmentate=asDelegate.desbloquearFamiliaSegmentate(numeroPcsSeleccionado,family);
	    		 if(resultadoFamiliaSegmentate!=null){
	    				 respuestaJson = JsonHelper.toJsonResponse("Ok");
	    		 }else{
	    			 respuestaJson = JsonHelper.toJsonErrorMessage("");
	    		 }
			}
			catch (DAOException e) {
				LOGGER.error("DAOException al al desbloquear una familia SEGMENTATE.", e);
				JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");
			}
			catch (ServiceException e) {
				LOGGER.info("ServiceException al desbloquear una familia SEGMENTATE.");
				JSFMessagesHelper.addServiceErrorMessage("general");
			}
			catch(Exception e){
				LOGGER.error("Excepcion desconocida al al desbloquear una familia SEGMENTATE.", e); 
				JSFMessagesHelper.addServiceErrorMessage("inesperado");
			}			

		}
	}
	
    /**
     * Metodo que devuelve el listado de indicativos telefonicos para ser mostrados en un combo en la pagina
     */
    public SelectItem[] getEstados() {

        if (estados == null) {
            List<CodeDescBean> indEstList = ParametrosHelper.getEstados();
            estados = JsfUtil.getSelectItemsCodeDesc(indEstList, true, "Selecciona");
        }

        return estados;
    }
    
    /**
     * @param event
     */
    public void initCatalogoActualSAGEN(PhaseEvent event){
    	
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) { 
        	String numeroPcsSeleccionado = null;
	    	
            try{     
              final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
	    	  numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
	  		  
	    	  AdministracionServiciosDelegate asDelegate = new AdministracionServiciosDelegate();
	  		  this.catalogoSAGEN = asDelegate.obtenerCatalogoActualSAGEN(numeroPcsSeleccionado);
			}
			catch (DAOException e) {
				LOGGER.error("DAOException al consultar catalogo SAGEN.", e);
				JSFMessagesHelper.addServiceErrorMessage("noinfoservicios");
		  	}
		  	catch (ServiceException e) {
		  		LOGGER.info("ServiceException al consultar catalogo SAGEN.");
		  		JSFMessagesHelper.addServiceErrorMessage("general");
		  	}
		  	catch(Exception e){
		  		LOGGER.error("Excepcion desconocida al consultar catalogo SAGEN.", e);
		  		JSFMessagesHelper.addServiceErrorMessage("inesperado");
		  	}    
        }
	  	
	  	LOGGER.info("Saliendo del metodo obtenerCatalogoSAGEN... ");	
    }
    

    /**
     * @return the administracionServiciosBean
     */
    public AdministracionServiciosBean getAdministracionServiciosBean() {
        return administracionServiciosBean;
    }

    /**
     * @param administracionServiciosBean
     *            the administracionServiciosBean to set
     */
    public void setAdministracionServiciosBean(
            AdministracionServiciosBean administracionServiciosBean) {
        this.administracionServiciosBean = administracionServiciosBean;
    }
    
    public UsuarioBean consultarDatosBuic(String numeroPcs , String  rut) throws DAOException, ServiceException {
        return this.cuentaDelegate.obtenerUsuario(numeroPcs, rut);
    }

	public CuentaDelegate getCuentaDelegate() {
		return cuentaDelegate;
	}

	public void setCuentaDelegate(CuentaDelegate cuentaDelegate) {
		this.cuentaDelegate = cuentaDelegate;
	}

	public UsuarioBean getDatosBuic() {
		return datosBuic;
	}

	public void setDatosBuic(UsuarioBean datosBuic) {
		this.datosBuic = datosBuic;
	}	

	public CatalogoSAGENBean getCatalogoSAGEN() {
		return catalogoSAGEN;
	}

	public void setCatalogoSAGEN(CatalogoSAGENBean catalogoSAGEN) {
		this.catalogoSAGEN = catalogoSAGEN;
	}

	public ResultadoCrearSuscripcionSAGENBean getResultadoSuscripcionSAGENBean() {
		return resultadoSuscripcionSAGENBean;
	}

	public void setResultadoSuscripcionSAGENBean(
			ResultadoCrearSuscripcionSAGENBean resultadoSuscripcionSAGENBean) {
		this.resultadoSuscripcionSAGENBean = resultadoSuscripcionSAGENBean;
	}

	public ResultadoConsultarSAGENBean getConsultaSAGEN() {
		return consultaSAGEN;
	}

	public void setConsultaSAGEN(ResultadoConsultarSAGENBean consultaSAGEN) {
		this.consultaSAGEN = consultaSAGEN;
	}

	public String getRespuestaJson() {
		return respuestaJson;
	}

	public void setRespuestaJson(String respuestaJson) {
		this.respuestaJson = respuestaJson;
	}

	public String getPaginastotal() {
		paginastotal=MiEntelProperties
		.getProperty("parametros.adminServicios.topeRegistrosPagina");
		return paginastotal;
	}


	
	public String getHabilitados(){
		habilitados=MiEntelProperties
		.getProperty("parametros.adminServicios.estado.habilitado");
		return habilitados;
	}
	
	public String getDeshabilitados(){
		deshabilitados=MiEntelProperties
		.getProperty("parametros.adminServicios.estado.deshabilitado");
		return deshabilitados;
	}
	
	public String getBloqueados(){
		bloqueados=MiEntelProperties
		.getProperty("parametros.adminServicios.estado.bloqueado");
		return bloqueados;
	}
	
	public String getHtmlBodyHabilitado() {
		return JSFPortletHelper.getNodePropertyBinaryValueAsString("idContenido", "htmlBodyHabilitado", "html");
	}
	
	public String getHtmlBodyDeshabilitado() {
		return JSFPortletHelper.getNodePropertyBinaryValueAsString("idContenido", "htmlBodyDeshabilitado", "html");
	}
	
}
