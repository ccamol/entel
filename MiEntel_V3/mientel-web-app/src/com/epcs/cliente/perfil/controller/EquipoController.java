/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.BloqueoEquipoBean;
import com.epcs.bean.ContactoPorRenovacionBean;
import com.epcs.bean.DatosUsuarioBloqueoBean;
import com.epcs.bean.DocumentoAperturaOTBean;
import com.epcs.bean.Equipo4GLteBean;
import com.epcs.bean.GrupoClienteBean;
import com.epcs.bean.InformeTecnicoOTBean;
import com.epcs.bean.OrdenTrabajoVigenteBean;
import com.epcs.bean.PinPukBean;
import com.epcs.bean.Plan4GLteBean;
import com.epcs.bean.PlanBean;
import com.epcs.bean.ResumenEquipoBean;
import com.epcs.bean.ResumenLineaEquipoBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.SimCard4GLteBean;
import com.epcs.bean.SituacionActualEquipoBean;
import com.epcs.bean.SolicitaIphoneBean;
import com.epcs.cliente.perfil.bean.Oferta;
import com.epcs.cliente.perfil.controller.util.EquipoControllerHelper;
import com.epcs.cliente.perfil.dao.EquipoDAO;
import com.epcs.cliente.perfil.dao.PlanDAO;
import com.epcs.cliente.perfil.delegate.EquipoDelegate;
import com.epcs.cliente.perfil.delegate.OfertaBlindajeDelegate;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.PDFCreatorHelper;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.esa.clientes.perfilesclientes.serviciolte.delegate.ConsultaSimCardLteServiceDelegate;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class EquipoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(EquipoController.class);

    private EquipoDelegate equipoDelegate;

    private ResumenEquipoBean resumenEquipoBean = new ResumenEquipoBean();
    
    private String numeroPcs = "";
    
    private SituacionActualEquipoBean situacionActualEquipo;
    
    private ContactoPorRenovacionBean contactoPorRenovacion;
    
    private SolicitaIphoneBean solicitaIphone;
    
    private PinPukBean pinPuk;
    
    private DatosUsuarioBloqueoBean datosUsuarioBloqueo;
    
    private OrdenTrabajoVigenteBean ordenesTrabajoVigentes;        
    
    private int ordenesLength;
    
    private String respuestaJson;
    
    private String imei;
    
    private String fechaActual;
    
    private String atributoAceptacionPrespuesto = MiEntelProperties.getProperty("equipo.presupuesto.accion.aceptar");
    
    private String atributoRechazoPrespuesto = MiEntelProperties.getProperty("equipo.presupuesto.accion.rechazar");
    
    private String nombreFormulario = MiEntelProperties.getProperty("equipo.formulario.renovacion.nombre");
    
    private InformeTecnicoOTBean informeTecnicoOT;
    
    private DocumentoAperturaOTBean documentoAperturaOT;
    
    //private boolean bloqueoExistente;
    
    private boolean enGrupoHabil;
    
    private static String PARAMETER_SOPORTE= MiEntelProperties.getProperty("parametros.equipo.soporte");
    
    private String infoTuEquipoJson;
    
    private String infoTuEquipo4GLTEJson;
    
    private SeguridadDelegate seguridadDelegate;
    
    private String mensajeEquipoBeta;
    
    private boolean grupoControl;
    private boolean grupoPreferencial;       
    
    private ConsultaSimCardLteServiceDelegate simCardLTEDelegate;
    private OfertaBlindajeDelegate ofertaBlindajeDelegate;
    
    private List<Oferta> ofertas4GLTE;
    
    private EquipoControllerHelper equipoControllerHelper;
    
    public static final String CODIGO_RESPUESTA_COMPATIBLE = MiEntelProperties.getProperty("servicios.codigoRespuesta.compatible");
    public static final String PREFIJO_ENTEL = MiEntelProperties.getProperty("prefijo.entel");
    private static final String CODIGO_RESPUESTA_COMPATIBLEOTA = MiEntelProperties.getProperty("servicios.codigoRespuesta.compatibleOTA");
	
	private String marcaEquipo = "";
    
	public EquipoControllerHelper getEquipoControllerHelper() {
		return equipoControllerHelper;
	}
    
    private PlanDelegate planDelegate;
	private static final String CODIGO_RESPUESTA_TIPOMERCADO_CC = MiEntelProperties
			.getProperty("servicio.tipoMercado.cc");
	private static final String CODIGO_RESPUESTA_TIPOMERCADO_SS = MiEntelProperties
			.getProperty("servicio.tipoMercado.ss");
	private static final String CODIGO_RESPUESTA_BEAN_DISPONIBILIDAD4GLTE_OK = MiEntelProperties
			.getProperty("servicios.codigoRespuesta.bean.disponibilidad");
	
	private boolean dasBoard = true;
	
	private ResumenLineaEquipoBean resumenLineaEquipoBean;
	private String infoCuposDisponibles;
	
	public boolean isDasBoard() {
		return dasBoard;
	}

	public void setDasBoard(boolean dasBoard) {
		this.dasBoard = dasBoard;
	}

	public void setEquipoControllerHelper(
			EquipoControllerHelper equipoControllerHelper) {
		this.equipoControllerHelper = equipoControllerHelper;
	}	

	public List<Oferta> getOfertas4GLTE() {
		return ofertas4GLTE;
	}

	public void setOfertas4GLTE(List<Oferta> ofertas4glte) {
		ofertas4GLTE = ofertas4glte;
	}		

	public OfertaBlindajeDelegate getOfertaBlindajeDelegate() {
		return ofertaBlindajeDelegate;
	}

	public void setOfertaBlindajeDelegate(
			OfertaBlindajeDelegate ofertaBlindajeDelegate) {
		this.ofertaBlindajeDelegate = ofertaBlindajeDelegate;
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

	/**
     * @return the equipoDelegate
     */
    public EquipoDelegate getEquipoDelegate() {
        return equipoDelegate;
    }

    /**
     * @param equipoDelegate
     *            the equipoDelegate to set
     */
    public void setEquipoDelegate(EquipoDelegate equipoDelegate) {
        this.equipoDelegate = equipoDelegate;
    }
    
    
    /**
     * 
     * @return
     */
    public String getInfoTuEquipo4GLTEJson() {
		return infoTuEquipo4GLTEJson;
	}

    /**
     * 
     * @param infoTuEquipo4GLTEJson
     */
	public void setInfoTuEquipo4GLTEJson(String infoTuEquipo4GLTEJson) {
		this.infoTuEquipo4GLTEJson = infoTuEquipo4GLTEJson;
	}

	/**
     * Aceptar - rechazar presupuesto.
     * 
     * @param event
     */
    public void aceptarRechazarPresupuestoAjax(PhaseEvent event) {
    	String nroOrden = "";
    	try{            
            nroOrden = JsfUtil.getRequestParameter("nroOrden");
            String accion = JsfUtil.getRequestParameter("accion");          
            equipoDelegate.aceptarRechazarPresupuesto(nroOrden, accion);
            respuestaJson = JsonHelper.toJsonResponse("Ok", "exitoSolicitud");                        
        } catch (DAOException e) {          
            LOGGER.error("DAOException al aceptar/rechazar presupuesto", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al aceptar/rechazar presupuesto. nroOrden: "+nroOrden);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al aceptar/rechazar presupuesto", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo");
        }
    }
    
    /**
     * Bloquear Equipo
     * 
     * @param event
     */
    public void bloquearEquipoAjax(PhaseEvent event) {
    	String numeroPcsSeleccionado = "";
    	try{            
            
            ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            
            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
            profileWrapper, "numeroPcsSeleccionado");
            
            String rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutTitular");
            rutTitular = rutTitular == null ? ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado") : rutTitular;
            
            RutBean rt = new RutBean(rutTitular);
            String rutSinDV = rutTitular != null ? rutTitular.substring(0, rutTitular.length()-1) : "";
            String DV = String.valueOf(rt.getDigito());
            
            String fecha = JsfUtil.getRequestParameter("fecha");
            String hora = JsfUtil.getRequestParameter("hora");
            String clave = JsfUtil.getRequestParameter("clave");
            String mail = JsfUtil.getRequestParameter("mail");
            String tel = JsfUtil.getRequestParameter("tel");
            String bloqueo = JsfUtil.getRequestParameter("bloqueo");
            String nombre = JsfUtil.getRequestParameter("nombre");
            String sentido = JsfUtil.getRequestParameter("sentido");
            String area = JsfUtil.getRequestParameter("area");
            String telefono = JsfUtil.getRequestParameter("telefono");
            
            String fechaRobo = fecha != null ? fecha.replace("/", "").concat(hora).concat("0000") : ""; 
            
            if( !clave.substring(0,1).equals("0") ){
                
                BloqueoEquipoBean bloq = new BloqueoEquipoBean();
                
                bloq.setClaveBloqueo(clave);
                bloq.setDominioMail(mail);
                bloq.setUsuarioMail(mail);
                
                bloq.setDv(DV);
                bloq.setFechaRobo(fechaRobo);
                bloq.setIdSentidoBloqueo(sentido);
                
                bloq.setMotivo(bloqueo);
                bloq.setAreaTelefonoFijo(area);
                bloq.setDestinoBloqueo(telefono);           
    
                bloq.setNombreCompletoUsuario(nombre);
                bloq.setNumeroPCS(numeroPcsSeleccionado);
                bloq.setNumeroPCSMotivoBloqueo(numeroPcsSeleccionado);
                bloq.setRutSinDV(rutSinDV);
                bloq.setTelefonoFijo(tel);
                
                DatosUsuarioBloqueoBean buic = new DatosUsuarioBloqueoBean();
                buic.setAreaTelefono(area);
                buic.setEmail(mail);
                buic.setTelefono(telefono);
                
                equipoDelegate.actualizarDatosUsuarioBloqueo(buic, rutSinDV, DV, numeroPcsSeleccionado);            
                equipoDelegate.bloquearEquipoPorRobo(bloq);         
                
                respuestaJson = JsonHelper.toJsonResponse("Ok");
            }else{
                LOGGER.error("No puede comenzar su clave  con caracter cero (0)");
                respuestaJson = JsonHelper.toJsonServiceErrorMessage("claveEntel");
            }
        } catch (DAOException e) {          
            LOGGER.error("DAOException al bloquear equipo", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("bloquearEquipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al bloquear equipo. msisdn: "+numeroPcsSeleccionado);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("bloquearEquipo", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al bloquear equipo", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("bloquearEquipo");
        }
    }
    
    /**
     * Obtener documento de apertur de la OT
     * 
     * @param event
     */
    public String obtenerAperturaDocumentoOT(){
        
        String postback = "success";
        
        try{                        
            
            String nroOrden = JsfUtil.getRequestParameter("nroOrden");          
            this.documentoAperturaOT = equipoDelegate.obtenerAperturaDocumentoOT(nroOrden);
            
            Map<String, Object> propiedades = new HashMap<String, Object>();
            propiedades.put("documento", documentoAperturaOT);
            propiedades.put("hoy", DateHelper.format(new Date(), DateHelper.FORMAT_ddMMyyyy_HYPHEN));
            
            String templatePath = MiEntelProperties.getProperty("equipo.documentoApertura.plantilla.xml");          
            
            PDFCreatorHelper.createAndDownloadPDF(propiedades, templatePath,
                    "DocuentoAperturaOT.pdf", JSFPortletHelper.getResponse(),
                    JSFPortletHelper.getResponse().getOutputStream());

            FacesContext.getCurrentInstance().responseComplete();
            
        } catch (DAOException e) {          
            LOGGER.error("DAOException al obtener apertura de documento presupuesto", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al obtener apertura de documento");
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (Exception e) {
            LOGGER.error("Exception al obtener apertura de documento", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }
        
        return postback;
        
    }
    
    /**
     * Obtener informe tecnico
     * 
     * @param event
     */
    public void obtenerInformeTecnico(PhaseEvent event){
        try{                        
            String nroOrden = JsfUtil.getRequestParameter("nOrden");
            this.informeTecnicoOT = equipoDelegate.obtenerInformeTecnicoOT(nroOrden);
            fechaActual = DateHelper.format(new Date(), DateHelper.FORMAT_ddMMyyyy_HYPHEN);
        } catch (DAOException e) {   
            informeTecnicoOT = null;
            LOGGER.error("DAOException al obtener informe tecnico", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo");
        } catch (ServiceException e) {
            informeTecnicoOT = null;
            LOGGER.info("ServiceException al obtener informe tecnico");
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo", e.getCodigoRespuesta());
        } catch (Exception e) {
            informeTecnicoOT = null;
            LOGGER.error("Exception inseperada al obtener informe tecnico", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo");
        }
    }
    
    /**
     * Enviar formulario solicita iphone. 
     * 
     * @param event
     */
    public void solicitaIphoneAjax(PhaseEvent event) {
    	String numeroPcs = "";
        try{
        
            ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            
            String nombre1 = JsfUtil.getRequestParameter("n1");
            String nombre2 = JsfUtil.getRequestParameter("n2");
            String apellido1 = JsfUtil.getRequestParameter("ap1");
            String apellido2 = JsfUtil.getRequestParameter("ap2");
            String tel = JsfUtil.getRequestParameter("tel");
            String iphone = JsfUtil.getRequestParameter("iphone");
            
            numeroPcs = ProfileWrapperHelper.getPropertyAsString(
                        profileWrapper, "numeroPcs");
            
            solicitaIphone = new SolicitaIphoneBean();          
            solicitaIphone.setMsisdnContacto(numeroPcs);
            solicitaIphone.setTelefonoAdicional(tel);
            solicitaIphone.setNombreCompleto(nombre1+" "+nombre2+" "+apellido1+" "+apellido2);
            solicitaIphone.setRut(ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutTitular"));
            solicitaIphone.setNombreFormulario(MiEntelProperties.getProperty("equipo.formulario.solicitaiphone."+iphone+".nombre"));
            
            solicitaIphone();
            
            respuestaJson = JsonHelper.toJsonResponse("Ok", "exitoSolicitud");
            
        } catch (DAOException e) {          
            LOGGER.error("DAOException al enviar formulario solicita iphone.", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al enviar formulario solicita iphone. msisdn: "+numeroPcs);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al enviar formulario solicita iphone.", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo");
        }
        
    }
    
    
    /**
     * Enviar contacto por renovacion.
     * 
     * @param event
     */
    public void contactoRenovacionAjax(PhaseEvent event) {
    	String numeroPcs = "";
        try{
        
            ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            
            String nombre1 = JsfUtil.getRequestParameter("n1");
            String nombre2 = JsfUtil.getRequestParameter("n2");
            String apellido1 = JsfUtil.getRequestParameter("ap1");
            String apellido2 = JsfUtil.getRequestParameter("ap2");
            String tel = JsfUtil.getRequestParameter("tel");
            
            numeroPcs = ProfileWrapperHelper.getPropertyAsString(
                        profileWrapper, "numeroPcs");
            
            contactoPorRenovacion = new ContactoPorRenovacionBean();            
            contactoPorRenovacion.setMsisdnContacto(numeroPcs);
            contactoPorRenovacion.setTelefonoAdicional(tel);
            contactoPorRenovacion.setNombreCompleto(nombre1+" "+nombre2+" "+apellido1+" "+apellido2);
            contactoPorRenovacion.setRut(ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutTitular"));
            contactoPorRenovacion.setNombreFormulario(nombreFormulario);
            
            contactoPorRenovacion();
            
            respuestaJson = JsonHelper.toJsonResponse("Ok", "exitoSolicitudRenovacion");
            
        } catch (DAOException e) {          
            LOGGER.error("DAOException al buscar informacion de equipo.", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar informacion de equipo. msisdn: "+numeroPcs);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al buscar informacion de equipo.", e);
            respuestaJson = JsonHelper.toJsonServiceErrorMessage("equipo");
        }
        
    }
    
    /**
     * Inicializa los datos de Equipo (Renovacion - Bloqueo - Servicio Tecnico - Configuracion)
     * 
     * @param event
     */
    public void initEquipos(PhaseEvent event) {
    	
    	String numeroPcsSeleccionado = "";
    	try {
            
    		if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
    		
	            ProfileWrapper profileWrapper = ProfileWrapperHelper
	                    .getProfile(JSFPortletHelper.getRequest());
	            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
	                    profileWrapper, "numeroPcsSeleccionado");
	            
	            String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
	            "mercado");
	            
	            
	            //loadDataEquipo(numeroPcsSeleccionado);
	            
	            this.setNumeroPcs(numeroPcsSeleccionado);
	            String rutTitular = null;
	            
	            if(mercado.equals("PP")){
	            	 rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuario");
	            }else{
	            	rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutTitular");
	            }
	            String nroCuenta = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroCuenta");
	            
	            rutTitular = rutTitular == null ? ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado") : rutTitular;
	            
	            RutBean rt = new RutBean(rutTitular);
	            String rutSinDV = rutTitular != null ? rutTitular.substring(0, rutTitular.length()-1) : "";
	            String DV = String.valueOf(rt.getDigito());
	            
	            try {
	                enGrupoHabil = equipoDelegate.estaEnGrupoHabil(rutTitular);
	            	
	                String idContenido = MiEntelProperties.getProperty("parametros.equipo.mensajeEquipoBeta.idContenido");
	            	Node mensajeServicio = JSFPortletHelper.getContentNode("idContenido", idContenido);
	            	mensajeEquipoBeta = mensajeServicio.getProperty("html").getValue().getStringValue();
	                
	            } catch (Exception e) {
	                LOGGER.error("Error al verificar grupo habil.", e);
	            }                        
	            
				String codigoGrupo = null;
	            try {
					/* Formateamos rut para consultar servicio que obtiene el grupo del cliente. 
					 * Convertimos DV a mayusculas y concatenamos con rutSinDV */
			        
					String rutFormated = rutSinDV.concat(DV.toUpperCase());
					
					GrupoClienteBean grupoCliente = equipoDelegate
							.obtenerGrupoCliente(rutFormated, nroCuenta);
					codigoGrupo = grupoCliente.getCodigoGrupo();

					if (ParametrosHelper.getExisteParametro("equipo.grupoCliente.controlado",codigoGrupo)
							|| ParametrosHelper.getExisteParametro("equipo.grupoCliente.empresa",codigoGrupo)) {
						grupoControl = true;
					} else if(ParametrosHelper.getExisteParametro("equipo.grupoCliente.preferencial",codigoGrupo)){
						grupoPreferencial = true;
					}
				} catch (Exception e) {
					LOGGER.error("Error al verificar el grupo del cliente.", e);
				}
	            
	            loadDataPinPuk(numeroPcsSeleccionado);
	            loadDataOrdenesTrabajo(numeroPcsSeleccionado);
	            loadDataSituacionActualEquipo(rutTitular, nroCuenta, rutSinDV, DV);	            
	            resumenEquipoBean.setNumeroPcs(numeroPcsSeleccionado);
	            setDasBoard(false);
	            compatibilidad4GLte(event);
	            
    			if (MiEntelBusinessHelper.isMercadoSuscripcion(mercado) || MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)) {
    				try {
    					if (codigoGrupo == null || codigoGrupo.isEmpty())
    							LOGGER.error(  new IllegalArgumentException("El grupo del cliente no puede estar vacio"));
    					else {
    						resumenLineaEquipoBean = equipoDelegate.obtenerResumenLineaEquipos(rutTitular, nroCuenta, codigoGrupo);
    						infoCuposDisponibles = EquipoControllerHelper.obtenerMensajeCuposDisponibles(resumenLineaEquipoBean);
    					}
    				} catch (Exception e) {
    					LOGGER.error("Error al obtener resumen de lineas", e);
    				}
    			}
    		}
        }  catch (DAOException e) {
            LOGGER.error("DAOException al buscar informacion de equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar informacion de equipo. msisdn: "+numeroPcsSeleccionado);
            JSFMessagesHelper.addErrorCodeMessage("general", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al buscar informacion de equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }
    }

  

    /**
     * Inicializa los datos de Equipo
     * 
     * @param event
     */
   /* private void loadDataEquipo(String numeroPcsSeleccionado){
        try{
        	ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
   		                 
        	String msisdn = numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
        	
            this.resumenEquipoBean = this.equipoDelegate.getResumenEquipo(numeroPcsSeleccionado);           
        }   catch (DAOException e) {
            LOGGER.error("DAOException al buscar informacion de equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar informacion de equipo. msisdn: "+numeroPcsSeleccionado);
            JSFMessagesHelper.addErrorCodeMessage("general", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al buscar informacion de equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }   
    }*/
    
    /**
     * Inicializa los datos de Pin Puk Equipo
     * 
     * @param event
     */
    private void loadDataPinPuk(String numeroPcsSeleccionado){
        try{
            pinPuk = equipoDelegate.consultarPinPuk(numeroPcsSeleccionado);
        } catch (DAOException e) {
            pinPuk = null;              
            LOGGER.error("DAOException al buscar informacion de Pin Puk equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            pinPuk = null;
            LOGGER.info("ServiceException al buscar informacion de Pin Puk equipo. msisdn: "+numeroPcsSeleccionado);
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
            pinPuk = null;
            LOGGER.error("Exception al buscar informacion de Pin Puk equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }
    }
    
    /**
     * Inicializa los datos de Ordenes de trabajo Equipo
     * 
     * @param event
     */
    private void loadDataOrdenesTrabajo(String numeroPcsSeleccionado){
        try{
            ordenesTrabajoVigentes = equipoDelegate.obtenerOrdenesDeTrabajoVigente(numeroPcsSeleccionado);
            ordenesLength = ordenesTrabajoVigentes != null ? ordenesTrabajoVigentes.getOrdenes() != null ? ordenesTrabajoVigentes.getOrdenes().size() : 0 : 0;
        }catch (DAOException e) {
            ordenesTrabajoVigentes = null;      
            LOGGER.error("DAOException al buscar informacion de ordenes de trabajo equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            ordenesTrabajoVigentes = null;
            LOGGER.info("ServiceException al buscar informacion de ordenes de trabajo equipo. msisdn: "+numeroPcsSeleccionado);
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
            ordenesTrabajoVigentes = null;
            LOGGER.error("Exception al buscar informacion de ordenes de trabajo equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }
    }
    
    /**
     * Inicializa los datos de situacion actual Equipo
     * 
     * @param event
     */
    private void loadDataSituacionActualEquipo(String rutTitular, String nroCuenta, String rutSinDV, String DV){
        try{            
            if (isEnGrupoHabil()) {
                this.situacionActualEquipo = equipoDelegate
                        .obtenerSituacionActualEquipo(numeroPcs, rutTitular,
                                nroCuenta, rutSinDV, DV);
            }
            else {
                this.situacionActualEquipo = new SituacionActualEquipoBean();
            }
        } catch (DAOException e) {
            situacionActualEquipo = null;               
            LOGGER.error("DAOException al buscar informacion de situacion actual equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            situacionActualEquipo = null;   
            LOGGER.info("ServiceException al buscar informacion de situacion actual equipo. msisdn: "+numeroPcs);
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
            situacionActualEquipo = null;   
            LOGGER.error("Exception al buscar informacion de situacion actual equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }
    }
    
    /**
     * Inicializa los datos de Equipo
     * 
     * @param event
     */
    public void init(PhaseEvent event) {
    	String numeroPcsSeleccionado = "";
    	try {

            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            
            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcsSeleccionado");
            
            this.resumenEquipoBean = this.equipoDelegate
                    .getResumenEquipo(numeroPcsSeleccionado);                        
            this.resumenEquipoBean.setUrlImagen("");            
            this.resumenEquipoBean.setNumeroPcs(numeroPcsSeleccionado);            
            //compatibilidad4GLte();            
            infoTuEquipoJson = JsonHelper.toJsonResponse(this.resumenEquipoBean);                                    
                             
        }  catch (DAOException e) {
        	
            LOGGER.error("DAOException al buscar informacion de equipo.", e);
            infoTuEquipoJson = JsonHelper.toJsonServiceErrorMessage("noinfoequipo");
            
        } catch (ServiceException e) {
        	
            LOGGER.info("ServiceException al buscar informacion de equipo. msisdn: "+numeroPcsSeleccionado);
            infoTuEquipoJson = JsonHelper.toJsonServiceErrorMessage(e.getCodigoRespuesta());
            
        } catch (Exception e) {
        	
            LOGGER.error("Exception al buscar informacion de equipo.", e);
            infoTuEquipoJson = JsonHelper.toJsonServiceErrorMessage("noinfoequipo");            
            
        }        
        
    }       
    
    public void solicitaIphone(){
        try{
            equipoDelegate.solicitaIphone(solicitaIphone);
        }  catch (DAOException e) {
            LOGGER.error("Error al enviar formulario solicita iphone.", e);
            JSFMessagesHelper.addServiceErrorMessage("equipo");
        } catch (ServiceException e) {
            LOGGER.info("Error al enviar formulario solicita iphone.");
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Error al enviar formulario solicita iphone.", e);
            JSFMessagesHelper.addServiceErrorMessage("equipo");
        }
    }
    
    public void contactoPorRenovacion(){
        try{
            equipoDelegate.contactoPorRenovacion(contactoPorRenovacion);
        }  catch (DAOException e) {
            LOGGER.error("DAOException al buscar informacion de equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("equipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar informacion de equipo.");
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al buscar informacion de equipo.", e);
            JSFMessagesHelper.addServiceErrorMessage("equipo");
        }
    }
        
    /**
     * Datos del equipo del usuario en sesion
     * 
     * @return ResumenEquipoBean
     * @throws Exception
     */
    public ResumenEquipoBean getResumenEquipo() {
        return this.resumenEquipoBean;
    }

    /**
     * @return numeroPcs
     */
    public String getNumeroPcs() {
        return numeroPcs;
    }

    /**
     * @param numeroPcs numeroPcs to set
     */
    public void setNumeroPcs(String numeroPcs) {
        this.numeroPcs = numeroPcs;
    }

    /**
     * @return situacionActualEquipo
     */
    public SituacionActualEquipoBean getSituacionActualEquipo() {
        return situacionActualEquipo;
    }

    /**
     * @param situacionActualEquipo
     */
    public void setSituacionActualEquipo(
            SituacionActualEquipoBean situacionActualEquipo) {
        this.situacionActualEquipo = situacionActualEquipo;
    }
    
    /**
     * @return pinPuk
     */
    public PinPukBean getPinPuk() {
        return pinPuk;
    }

    /**
     * @param pinPuk the pinPuk to set
     */
    public void setPinPuk(PinPukBean pinPuk) {
        this.pinPuk = pinPuk;
    }

    /**
     * @return contactoPorRenovacion
     */
    public ContactoPorRenovacionBean getContactoPorRenovacion() {
        return contactoPorRenovacion;
    }

    /**
     * @param contactoPorRenovacion
     */
    public void setContactoPorRenovacion(
            ContactoPorRenovacionBean contactoPorRenovacion) {
        this.contactoPorRenovacion = contactoPorRenovacion;
    }

    /**
     * @return respuestaJson
     */
    public String getRespuestaJson() {
        return respuestaJson;
    }

    /**
     * @param respuestaJson
     */
    public void setRespuestaJson(String respuestaJson) {
        this.respuestaJson = respuestaJson;
    }

    /**
     * @return datosUsuario
     */
    public DatosUsuarioBloqueoBean getDatosUsuarioBloqueo() {
        return datosUsuarioBloqueo;
    }

    /**
     * @param datosUsuario
     */
    public void setDatosUsuarioBloqueo(DatosUsuarioBloqueoBean datosUsuarioBloqueo) {
        this.datosUsuarioBloqueo = datosUsuarioBloqueo;
    }

    /**
     * @return ordenesTrabajoVigentes
     */
    public OrdenTrabajoVigenteBean getOrdenesTrabajoVigentes() {
        return ordenesTrabajoVigentes;
    }

    /**
     * @param ordenesTrabajoVigentes
     */
    public void setOrdenesTrabajoVigentes(
            OrdenTrabajoVigenteBean ordenesTrabajoVigentes) {
        this.ordenesTrabajoVigentes = ordenesTrabajoVigentes;
    }

    /**
     * @return ordenesLength
     */
    public int getOrdenesLength() {
        return ordenesLength;
    }

    /**
     * @param ordenesLength
     */
    public void setOrdenesLength(int ordenesLength) {
        this.ordenesLength = ordenesLength;
    }
    
     /**
     * @return imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del mercado como clave asociada al valor.
     * @return
     */
    public String getPageLabelByMercado(){
        try{
          ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            
          return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "mercado"), null);
         
        }catch(Exception e){
            LOGGER.error("No se ha podido obtener el pageLabel "+ e.getMessage(), e);
            return "";
        }
    
    }
    
    /**
     * Metodo que se encarga de obtener el link para ir a soporte entel pcs
     * @return
     */
    public String getLinkSoporteEntelPcs(){
        try{                    
            return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), "soporteEntelPCS", null);         
        }catch(Exception e){
            LOGGER.error("No se ha podido obtener la preferencia soporteEntelPCS "+ e.getMessage(), e);
            return "";
        }    
    }
    
    /**
     * @return atributoAceptacionPrespuesto
     */
    public String getAtributoAceptacionPrespuesto() {
        return atributoAceptacionPrespuesto;
    }

    /**
     * @param atributoAceptacionPrespuesto
     */
    public void setAtributoAceptacionPrespuesto(String atributoAceptacionPrespuesto) {
        this.atributoAceptacionPrespuesto = atributoAceptacionPrespuesto;
    }

    /**
     * @return atributoRechazoPrespuesto
     */
    public String getAtributoRechazoPrespuesto() {
        return atributoRechazoPrespuesto;
    }

    /**
     * @param atributoRechazoPrespuesto
     */
    public void setAtributoRechazoPrespuesto(String atributoRechazoPrespuesto) {
        this.atributoRechazoPrespuesto = atributoRechazoPrespuesto;
    }

    /**
     * @return informeTecnicoOT
     */
    public InformeTecnicoOTBean getInformeTecnicoOT() {
        return informeTecnicoOT;
    }

    /**
     * @param informeTecnicoOT
     */
    public void setInformeTecnicoOT(InformeTecnicoOTBean informeTecnicoOT) {
        this.informeTecnicoOT = informeTecnicoOT;
    }

    /**
     * @return documentoAperturaOT
     */
    public DocumentoAperturaOTBean getDocumentoAperturaOT() {
        return documentoAperturaOT;
    }

    /**
     * @param documentoAperturaOT
     */
    public void setDocumentoAperturaOT(DocumentoAperturaOTBean documentoAperturaOT) {
        this.documentoAperturaOT = documentoAperturaOT;
    }

    /**
     * @return fechaActual
     */
    public String getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual
     */
    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public boolean isEnGrupoHabil() {
        return enGrupoHabil;
    }
/**
     * @return
     */
    public String getUrlPageOficialByMarca(){
        
        String equipo="";
        String url="";
        
        try{    
            if(this.getResumenEquipo()!=null )
                   equipo = this.getResumenEquipo().getMarca();
          
            url = JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), equipo , null);
            return url;
            
        }catch(Exception e){
            LOGGER.error("No se ha podido obtener la url correspondiente a la marca "+equipo+ 
                    ", error: "+ e.getMessage(), e);
            return "";
        }    
    }
    
    /**
     * @return 
     */
    public String getUrlPageSoporteByMarca(){
        
        String equipo="";
        String url="";
        
        try{     
        
            if(this.getResumenEquipo()!=null )
                   equipo = this.getResumenEquipo().getMarca();
            
            url = JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), 
                  PARAMETER_SOPORTE+equipo , null);
            
            return url;
         
        }catch(Exception e){
            LOGGER.error("No se ha podido obtener la url soporte correspondiente a la marca "+equipo+ 
                    ", error: "+ e.getMessage(), e);
            return "";
        }
    
    }

	public String getInfoTuEquipoJson() {
		return infoTuEquipoJson;
	}

	public void setInfoTuEquipoJson(String infoTuEquipoJson) {
		this.infoTuEquipoJson = infoTuEquipoJson;
	}   
    
	/**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * @return
     */
    public String getURLMiEntelV2() {
        String idp = "";
        String url = "";
        this.seguridadDelegate = new SeguridadDelegate();
        try {
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            idp = this.seguridadDelegate.consultarIDP(ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs"));
            url = MiEntelProperties.getProperty("miEntel.contextoV2");
            return (url.concat(idp).concat("&contexto="));
        } catch (DAOException e) {
            LOGGER.error("DAOException al obtener la URL de MiEntelV2", e);
            return "";
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al obtener la URL de MiEntelV2 codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            return "";
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener la URL de MiEntelV2", e);
            return "";
        }
    }

    /**
     * @return the mensajeEquipoBeta
     */
    public String getMensajeEquipoBeta() {
        String mensaje = "";
        try {
            String contexto = MiEntelProperties
                    .getProperty("contexto.v2.bloqueo");
            if (mensajeEquipoBeta != null) {
                mensaje = mensajeEquipoBeta.replace("{url}", this
                        .getURLMiEntelV2()
                        + contexto);
            }
        } catch (Exception e) {
            LOGGER.error("No se pudo obtener mensajeEquipoBeta", e);
        }
        return mensaje;
    }

	/**
	 * @param mensajeEquipoBeta the mensajeEquipoBeta to set
	 */
	public void setMensajeEquipoBeta(String mensajeEquipoBeta) {
		this.mensajeEquipoBeta = mensajeEquipoBeta;
	}
	
	
	 /**
     * Obtiene el digito de verificacion para el imei
     * 
     * @param event
     */
    
    private int loadImeiDV( String msisdn ){
    	int imeiDV = 0;
    	try{
    		LOGGER.info("loadImeiDV");
    		String imeiEquipo = equipoDelegate.obtenerImei(msisdn);
    		imei = imeiEquipo;
    		imeiDV = calcularDigitoIMEI( imeiEquipo );
    		
    	}   catch (DAOException e) {
            LOGGER.error("DAOException al buscar informacion del imei.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException de servicio al buscar informacion de imei. msisdn: "+msisdn);
            JSFMessagesHelper.addErrorCodeMessage("general", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al buscar informacion de imei.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }
		return imeiDV;     	
    }
    
    
    /**
     * Calcula el DV para el imei
     * 
     * @param event
     */    
    public int calcularDigitoIMEI(String imei) {

        int sum = 0;
  
        for (int i = 0; i < imei.length(); i++) {
            sum += Integer.parseInt(imei.substring(i, i + 1));
        }

        int delta[] = {0, 1, 2, 3, 4, -4, -3, -2, -1, 0};
        for (int i = imei.length() - 1; i >= 0; i -= 2) {
            int deltaIndex = Integer.parseInt(imei.substring(i, i + 1));
            int deltaValue = delta[deltaIndex];
            sum += deltaValue;
        }

        int mod10 = sum % 10;
        mod10 = 10 - mod10;
        if (mod10 == 10) {
            mod10 = 0;
        }

        return mod10;
    }
    
    /**
     * Verifica la compatibilidad del equipo del usuario con la tecnologia 4GLTE.
     * @param numeroPcs
     * @return boolean
     */
    public Boolean compatibilidadEquipo4Lte(String numeroPcs){
    	Boolean estado = false;              	
    	try {
    		
    		equipoDelegate = new EquipoDelegate();
    		equipoDelegate.setEquipoDAO(new EquipoDAO());
			Equipo4GLteBean  equipo =  new Equipo4GLteBean();
			equipo =  equipoDelegate.compatibilidad(numeroPcs);
			resumenEquipoBean.setMarca(equipo.getMarca());
			resumenEquipoBean.setModelo(equipo.getModelo());
			
			LOGGER.info("Valor Equipo Compatibilidad 4GLTE: "+ equipo.getF4G2600());
			
			if( MiEntelProperties.getProperty("equipo.compatibilidad4GLte.ok").equals(equipo.getF4G2600())){				
				estado = true;
			}else{				
				estado =false;
			}
			LOGGER.info(" equipo para el msisdn:" + numeroPcs+ "es compatible con 4gLte:" +estado);
			
		} catch (DAOException e) {			
			LOGGER.error("DAOException en compatibilidadEquipo4Lte.", e);
		} catch (ServiceException e) {			
			LOGGER.info("ServiceException de servicio en  compatibilidadEquipo4Lte msisdn: "+numeroPcs);
			JSFMessagesHelper.addErrorCodeMessage("general", e.getCodigoRespuesta());
		} catch (Exception e) {
			LOGGER.error("Exception compatibilidadEquipo4Lte.", e);
		}  
		
		return estado;
    }
	
	public boolean isGrupoControl() {
		return grupoControl;
	}

	public void setGrupoControl(boolean grupoControl) {
		this.grupoControl = grupoControl;
	}

	/**
	 * @param grupoPreferencial the grupoPreferencial to set
	 */
	public void setGrupoPreferencial(boolean grupoPreferencial) {
		this.grupoPreferencial = grupoPreferencial;
	}

	/**
	 * @return the grupoPreferencial
	 */
	public boolean isGrupoPreferencial() {
		return grupoPreferencial;
	}
	
	/**
	 * Verifica si la simCard del usuario es compatible 4GLTE
	 * @param numeroPcs
	 * @return boolean
	 */
	public boolean validarSimCardLTE(String numeroPcs){
				
		boolean respuesta=false;
		             		
		try {						
			
			SimCard4GLteBean bean;
			simCardLTEDelegate = new ConsultaSimCardLteServiceDelegate();			
			bean = simCardLTEDelegate.ConsultaSimCardLteService("",PREFIJO_ENTEL+numeroPcs,"");
			
			String codigo = String.valueOf(bean.getCodigo());
			
			LOGGER.info("Codigo: "+codigo);
			
			LOGGER.info("CODIGO_RESPUESTA_COMPATIBLEOTA: "+CODIGO_RESPUESTA_COMPATIBLEOTA);
			
			if(CODIGO_RESPUESTA_COMPATIBLE.equals(codigo) || CODIGO_RESPUESTA_COMPATIBLEOTA.equalsIgnoreCase(codigo)){
				respuesta=true;				
			}
			LOGGER.info("simCard para el msisdn: " + numeroPcs + " es compatible con 4gLte: " + respuesta);
		} catch (DAOException e) {
			LOGGER.error("DAOException al buscar informacion de consultaSimCardLteService.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
		} catch (ServiceException e) {
			 LOGGER.info("ServiceException al obtener ConsultaSimCardLteService codigo: " + e.getCodigoRespuesta());
		} catch (Exception e) {
			LOGGER.info("Exception Error al obtener contenido: " + e);			
		}		
		return respuesta;
	}

	public ConsultaSimCardLteServiceDelegate getSimCardLTEDelegate() {
		return simCardLTEDelegate;
	}

	public void setSimCardLTEDelegate(
			ConsultaSimCardLteServiceDelegate simCardLTEDelegate) {
		this.simCardLTEDelegate = simCardLTEDelegate;
	}
	
	/**
	 * Metodo que verifica si un usuario tiene compatibilidad 4 GLTE
	 */
	public void compatibilidad4GLte(PhaseEvent event){
		LOGGER.info("Entering compatibilidad4GLte");
		
		boolean plan4GLte;
		boolean equipo4GLte;
		boolean simCard4GLte;
		
		ProfileWrapper profileWrapper = ProfileWrapperHelper
        .getProfile(JSFPortletHelper.getRequest());
		
		ofertaBlindajeDelegate = new OfertaBlindajeDelegate();
		equipoControllerHelper = new EquipoControllerHelper(resumenEquipoBean);
				
		try {
			
			numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
			simCard4GLte = validarSimCardLTE(numeroPcs);
			equipo4GLte = compatibilidadEquipo4Lte(numeroPcs);
			plan4GLte = compatibilidadPlan4GLte(profileWrapper);			
			setOfertas4GLTE(ofertaBlindajeDelegate.consultarOferta4GLTE(numeroPcs));									
			resumenEquipoBean=equipoControllerHelper.validarMensajes4GLTE(equipo4GLte, plan4GLte, simCard4GLte, ofertas4GLTE);
			
			infoTuEquipo4GLTEJson = JsonHelper.toJsonResponse(resumenEquipoBean);
			
		} catch (DAOException e) {
        	
            LOGGER.error("DAOException al buscar informacion de equipo.", e);
            infoTuEquipo4GLTEJson = JsonHelper.toJsonServiceErrorMessage("noinfoequipo");
            
        } catch (ServiceException e) {
        	
            LOGGER.info("ServiceException al buscar informacion de equipo. msisdn: "+ numeroPcs);
            infoTuEquipo4GLTEJson = JsonHelper.toJsonServiceErrorMessage(e.getCodigoRespuesta());
            
        }catch (Exception e) {
        	
            LOGGER.error("Exception al buscar informacion de compatibilidad4GLte de equipo.", e);
            infoTuEquipo4GLTEJson = JsonHelper.toJsonServiceErrorMessage("noinfoequipo");            
            
        }  

	}
	
	
	/**
	 * Metodo que verifica si el plan de  un usuario tiene compatibilidad 4 GLTE
	 */
		
	public boolean compatibilidadPlan4GLte(ProfileWrapper profileWrapper) {

		boolean confirmacion = false;
		
		String numeroPcsSeleccionado = "";
		String mercado = "";
		String atributoAAA = "";

		PlanBean planBean = new PlanBean();
		Plan4GLteBean plan4GLteBean;
		String disponibilidad = "";

		try {

			numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroPcsSeleccionado");

			atributoAAA = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "aaa");
			mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
					"mercado");
			

			if (mercado.equals(CODIGO_RESPUESTA_TIPOMERCADO_SS)) {

				planDelegate = new PlanDelegate();
				planDelegate.setPlanDAO(new PlanDAO());

				planBean = this.planDelegate.getPlanActualSSCC(numeroPcsSeleccionado, mercado, atributoAAA);			
				plan4GLteBean = this.planDelegate.consultarDisponibilidad4GLte(planBean.getCodbscs2());
				disponibilidad = plan4GLteBean.getDisponibilidadLte();

				if (disponibilidad.equals(CODIGO_RESPUESTA_BEAN_DISPONIBILIDAD4GLTE_OK)) {
					confirmacion = true;
				}
				else {
					confirmacion = false;
				}
			} 
			
			LOGGER.info("plan para el msisdn:" + numeroPcs + "es compatible con 4gLte:" + confirmacion);

		} catch (Exception e) {
			LOGGER.error("Exception al buscar informacion sobre disponibilidad de un plan para 4G LTE.",e);
		}

		return confirmacion;
	}

	public ResumenLineaEquipoBean getResumenLineaEquipoBean() {
		return resumenLineaEquipoBean;
	}

	public void setResumenLineaEquipoBean(
			ResumenLineaEquipoBean resumenLineaEquipoBean) {
		this.resumenLineaEquipoBean = resumenLineaEquipoBean;
	}

	public String getInfoCuposDisponibles() {
		return infoCuposDisponibles;
	}

	public void setInfoCuposDisponibles(String infoCuposDisponibles) {
		this.infoCuposDisponibles = infoCuposDisponibles;
	}
	
	public String getMarcaEquipo() throws Exception {
        String numero = "";
        ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        numero = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
        marcaEquipo =this.equipoDelegate.getResumenEquipo(numero).getMarca();
        return marcaEquipo;
    }

    public void setMarcaEquipo(String marcaEquipo) {
		this.marcaEquipo = marcaEquipo;
    }
}
