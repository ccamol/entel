package com.epcs.formularioreclamo.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;


import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.SessionHelper;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.CodeDescBean;
import com.epcs.bean.DetalleReclamoBean;
import com.epcs.bean.MsisdnAsociadoBean;
import com.epcs.bean.PaginaHistorialReclamosBean;
import com.epcs.bean.ReclamoBean;
import com.epcs.billing.balance.types.DocumentoFacturaFullType;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.cliente.perfil.delegate.PreIngresoOTDelegate;
import com.epcs.formularioreclamo.delegate.FormularioReclamoDelegate;
import com.epcs.recursoti.configuracion.HTMLEntities;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.error.ServiceMessages;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;



/**
 * The Class FormularioReclamoController.
 *
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 * EntelPcs)
 */
public class FormularioReclamoController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Logger para FormularioReclamoController. */
	private static final Logger LOGGER = Logger
			.getLogger(FormularioReclamoController.class);
	
	
	/** The Constant BOLETA_Y_FACTURA. */
	public String BOLETA_FACTURA = MiEntelProperties
			.getProperty("parametros.reclamo.boletaFactura");
	
	/** The Constant DIFICULTAD_COMERCIAL. */
	public String DIFICULTAD_COMERCIAL = MiEntelProperties
			.getProperty("parametros.reclamo.dificultadComercial");	
	
	/** The Constant DIFICULTAD_TECNICA. */
	public String DIFICULTAD_TECNICA = MiEntelProperties
			.getProperty("parametros.reclamo.dificultadTecnica");	
	
	/** The Constant SOLICITUD. */
	public static final String SOLICITUD = MiEntelProperties
			.getProperty("parametros.reclamo.servicio.solicitud");
	
	/** The Constant PLATAFORMA. */
	public static final String PLATAFORMA = MiEntelProperties
			.getProperty("parametros.reclamo.servicio.plataforma");
	
	
	/** The Constant CONTENT_DOCUMENTO_MONTO. */
	public static final String CONTENT_DOCUMENTO_MONTO = MiEntelProperties
			.getProperty("parametros.reclamo.numeroMonto.idContenido");
	
	/** The Constant CONTENT_DOCUMENTO_LOCALIZACION_PROBLEMA. */
	public static final String CONTENT_DOCUMENTO_LOCALIZACION_PROBLEMA = MiEntelProperties
	.getProperty("parametros.reclamo.localizacionproblema.idContenido");
	
	/** The Constant MUNDO. */
	public static final String MUNDO = MiEntelProperties
			.getProperty("parametros.reclamo.servicio.mundo");
	
	
	/** The Constant MUNDO_COMERCIAL. */
	public static final String MUNDO_COMERCIAL = MiEntelProperties
			.getProperty("parametros.reclamo.servicio.mundoComercial");
	
	
	

	private FormularioReclamoDelegate formularioReclamoDelegate;
	
	/** The pre ingreso ot delegate. */
	private PreIngresoOTDelegate preIngresoOTDelegate;
	
	/** The cuenta delegate. */
	private CuentaDelegate cuentaDelegate;

	/** The nombre usuario. */
	private String nombreUsuario;

	/** The rut usuario. */
	private String rutUsuario;

	/** The numero pcs. */
	private String numeroPcs;

	/** The email usuario. */
	private String emailUsuario;

	/** The ppemp. */
	private boolean PPEMP = false;

	/** The nombre vacio. */
	private boolean nombreVacio;

	/** The tipos reclamo. */
	private List<CodeDescBean> tiposReclamo;

	/** The tipos reclamo comercial. */
	private List<CodeDescBean> tiposReclamoComercial;

	/** The tipos reclamo tecnico. */
	private List<CodeDescBean> tiposReclamoTecnico;

	/** The tipos reclamo comercial ppemp. */
	private List<CodeDescBean> tiposReclamoComercialPPEMP;

	/** The numeros asociados. */
	private List<MsisdnAsociadoBean> numerosAsociados;

	/** The respuesta json. */
	private String respuestaJSON;

	/** The error historico reclamos. */
	public boolean errorHistoricoReclamos;

	/** The error message. */
	public String errorMessage;
	
	public String respuestaRegistrarReclamoJson;

	/**
	 * Inits the.
	 *
	 * @param event the event
	 */
	public void init(PhaseEvent event) {
		try {

			ProfileWrapper profile = SessionHelper.getProfile(JSFPortletHelper
					.getRequest());

			String aaa = ProfileWrapperHelper.getPropertyAsString(profile,
					"aaa");

			if (MiEntelBusinessHelper.isAAATitular(aaa)) {
				nombreUsuario = ProfileWrapperHelper.getPropertyAsString(
						profile, "nombreTitular");
				if (nombreUsuario == null) {
					nombreUsuario = ProfileWrapperHelper.getPropertyAsString(
							profile, "nombreUsuario");
				}

			} else {				
				if (nombreUsuario == null) {
					nombreUsuario = ProfileWrapperHelper.getPropertyAsString(
							profile, "nombreUsuario");
				}
			}

			   try {
					if (nombreUsuario != null) {
						if (nombreUsuario.equals(" ") || nombreUsuario.equals("")) {
							nombreUsuario = ProfileWrapperHelper
									.getPropertyAsString(profile, "nombreTitular");
							if (nombreUsuario.equals(" ")
									|| nombreUsuario.equals("")) {
								nombreVacio = true;
							}
						}
					}
				} catch (Exception e) {
					LOGGER.error("Exception cargardo el nombre del usuario.", e);
				}

				rutUsuario = ProfileWrapperHelper.getPropertyAsString(profile,
						"rutUsuarioSeleccionado");

				if (rutUsuario != null) {
					if (rutUsuario.equals("") || rutUsuario.equals(" ")) {
						rutUsuario = ProfileWrapperHelper.getPropertyAsString(
								profile, "rutUsuario");
					}
				}
			
		   
			numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,
					"numeroPcs");
			String mercado = ProfileWrapperHelper.getPropertyAsString(profile,
					"mercado");
			String subMercado = ProfileWrapperHelper.getPropertyAsString(
					profile, "subMercado");

			if (tiposReclamo == null) {
				tiposReclamo = JsfUtil.getSelectItemsTipoReclamos("tipoReclamos");
			}

			if (tiposReclamoComercial == null) {
				tiposReclamoComercial = JsfUtil
				.getSelectItemsTipoReclamos("tipoComercial");
			}

			if (tiposReclamoComercialPPEMP == null) {
				tiposReclamoComercialPPEMP = JsfUtil
						.getSelectItemsTipoReclamos("tipoComercialPPEMP");
			}

			if (tiposReclamoTecnico == null) {
				tiposReclamoTecnico = JsfUtil
						.getSelectItemsTipoReclamos("tipoTecnico");
			}

			if (MiEntelBusinessHelper.isMercadoPrepago(mercado)
					|| subMercado.equals(MiEntelProperties
							.getProperty("miEntel.subMercadoEMP"))
					|| subMercado.equals(MiEntelProperties
							.getProperty("miEntel.subMercadoSGO"))) {
				PPEMP = true;
			}

			String flagBam = ProfileWrapperHelper.getPropertyAsString(profile,
					"flagBam");
			if (MiEntelBusinessHelper.getAAATitular().equals(aaa)) {
				CuentaDelegate cuentaDelegate = new CuentaDelegate();
				numerosAsociados = cuentaDelegate
						.getListaMsisdnAsociadosReclamos(numeroPcs, subMercado,
								flagBam);
			}

		} catch (Exception e) {
			LOGGER.error("Exception en el metodo init.", e);
			JSFMessagesHelper.addServiceErrorMessage("infoIngresoReclamo");
		}
	}

	/**
	 * Registrar reclamo.
	 *
	 * @param event the event
	 * @author Wilson Brochero Metodo permite registrar los reclamos en el
	 * servicio dispuesto para esto.
	 */
	public void registrarReclamo(PhaseEvent event) {
		try {

			String movil = JsfUtil.getRequestParameter("movil");
			String suboperacion = JsfUtil.getRequestParameter("suboperacion");
			String motivo = JsfUtil.getRequestParameter("motivo");
			
			String suboperacionText = JsfUtil.getRequestParameter("suboperacionText");
			String motivoText = JsfUtil.getRequestParameter("motivoText");
			
			String email = JsfUtil.getRequestParameter("email");
			//String mensaje = JsfUtil.getRequestParameter("mensaje");
			String nombre = JsfUtil.getRequestParameter("nombre");
			String antecedentes = JsfUtil.getRequestParameter("antecedentes");
			String medio = JsfUtil.getRequestParameter("medio");
			String documento = JsfUtil.getRequestParameter("documento");			
			String localizacion = JsfUtil.getRequestParameter("localizacion");		
			
			String cuenta = JsfUtil.getRequestParameter("cuenta");
			String reclamoTipoDocumento = JsfUtil.getRequestParameter("reclamoTipoDocumento");				
	  
			localizacion = localizacion == null || "undefined".equals(localizacion) || "UNDEFINED".equals(localizacion) ? "" : localizacion;

	        String montoCobrado = JsfUtil.getRequestParameter("montoCobrado");
	        montoCobrado = montoCobrado == null || "undefined".equals(montoCobrado) || "UNDEFINED".equals(montoCobrado) ? "" : montoCobrado;
	       
	        String monto = JsfUtil.getRequestParameter("monto");
	        monto = monto == null || "undefined".equals(monto) || "UNDEFINED".equals(monto) ? "" : monto;

	        String checkEmail = JsfUtil.getRequestParameter("checkEmail") == null ? "" : JsfUtil.getRequestParameter("checkEmail");
	        String checkDireccion = JsfUtil.getRequestParameter("checkEmail") == null ? "" : JsfUtil.getRequestParameter("checkDireccion");

	        String region = checkDireccion.equals("0") ? JsfUtil.getRequestParameter("region") : "";
	        String ciudad = checkDireccion.equals("0") ? JsfUtil.getRequestParameter("ciudad") : "";
	        String comuna = checkDireccion.equals("0") ? JsfUtil.getRequestParameter("comuna") : "";
	        String calle = checkDireccion.equals("0") ? JsfUtil.getRequestParameter("calle") : "";
	        String numero = checkDireccion.equals("0") ? JsfUtil.getRequestParameter("numero") : "";
	        String dpto = checkDireccion.equals("0") ? JsfUtil.getRequestParameter("dpto") : "";			

	        //String medio,String antecedentes
 		    String reclamoTexto ="";
 		    String tipoRecamoTexto="";
			

			ReclamoBean reclamo = new ReclamoBean();
			ProfileWrapper profile = SessionHelper.getProfile(JSFPortletHelper
					.getRequest());
			rutUsuario = ProfileWrapperHelper.getPropertyAsString(profile,
					"rutUsuarioSeleccionado");
			
			if(suboperacion.equals("1")){
 			   reclamoTexto = this.obtenerRelaclamoEquivalente(suboperacion);
 			   tipoRecamoTexto = this.obtenerTipoRelaclamoEquivalente("comercial",motivo);
 			  reclamo.setMundo(MUNDO_COMERCIAL);
 		    }else if(suboperacion.equals("2")){
 		    	reclamoTexto = this.obtenerRelaclamoEquivalente(suboperacion);
 		    	tipoRecamoTexto = this.obtenerTipoRelaclamoEquivalente("tecnico",motivo);
 		    	reclamo.setMundo(MUNDO);
 		    }
			
            if(!"undefined".equals(cuenta) && !"UNDEFINED".equals(cuenta) && cuenta!=null){
            	cuenta = cuenta.replaceAll("\\.", "");
			}else{
				cuenta="";
			}			
			
			reclamo.setNroReclamado(movil);
			reclamo.setRutCliente(rutUsuario);			
			reclamo.setTipoReclamo(reclamoTexto);
			reclamo.setMotivoReclamo(tipoRecamoTexto);
			reclamo.setMensaje(antecedentes);
			reclamo.setDireccionReclamada(localizacion);
			reclamo.setFonoFijo("");
			reclamo.setEmailContacto(email);
			reclamo.setCheckEmail(checkEmail);
			reclamo.setCuenta(cuenta);
			reclamo.setTipoDocumento(reclamoTipoDocumento);
				
					
			
			if(email !=null && !email.equals("")){
	            try{	              
		              reclamo.setEmailCuenta(email.split("@")[0]);
		              reclamo.setEmailDominio(email.split("@")[1]);
	            }catch(Exception e){
	               LOGGER.error("Error al procesar la informacion del mail de contacto :"+email+ "Error: "+e);
	            }
	             
	        }  
			
			reclamo.setCheckDireccion(checkDireccion);
			reclamo.setDireccion(calle);
			reclamo.setDirNumero(numero);			
			reclamo.setDirAdicional(dpto);
			reclamo.setDirRegion(region);
			reclamo.setDirComuna(comuna);
			reclamo.setDirCiudad(ciudad);
			reclamo.setDirCodPostal("");
			reclamo.setNroDocumento(documento);
			reclamo.setMontoImpugnado(montoCobrado);
			reclamo.setValorItem(monto);	
			reclamo.setDetalleNotificacion(antecedentes);			

			String codreclamo = formularioReclamoDelegate.ingresarReclamo(reclamo);

			this.enviarMail(email, nombre, reclamo, suboperacion, motivo,suboperacionText, motivoText
					,antecedentes, medio, movil, documento, monto, codreclamo, localizacion);

			respuestaRegistrarReclamoJson = JsonHelper.toJsonResponse(codreclamo);

		} catch (DAOException e) {
			LOGGER.error("DAOException al ingresar el reclamo", e);
			respuestaRegistrarReclamoJson = JsonHelper
					.toJsonServiceErrorMessage("ingresarReclamo");
		} catch (ServiceException e) {
			LOGGER.info("ServiceException al ingresar el reclamo. msisdn: "
					+ numeroPcs);
			respuestaRegistrarReclamoJson = JsonHelper.toJsonServiceErrorMessage(
					"ingresarReclamo", e.getCodigoRespuesta());
		} catch (Exception e) {
			LOGGER.error("Exception al ingresar el reclamo", e);
			respuestaRegistrarReclamoJson = JsonHelper
					.toJsonServiceErrorMessage("ingresarReclamo");
		}
	}

	/**
	 * Gets the formulario reclamo delegate.
	 *
	 * @return the formularioReclamoDelegate
	 */
	public FormularioReclamoDelegate getFormularioReclamoDelegate() {
		return formularioReclamoDelegate;
	}

	/**
	 * Sets the formulario reclamo delegate.
	 *
	 * @param formularioReclamoDelegate the formularioReclamoDelegate to set
	 */
	public void setFormularioReclamoDelegate(
			FormularioReclamoDelegate formularioReclamoDelegate) {
		this.formularioReclamoDelegate = formularioReclamoDelegate;
	}

	/**
	 * Gets the nombre usuario.
	 *
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Sets the nombre usuario.
	 *
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * Gets the rut usuario.
	 *
	 * @return the rutUsuario
	 */
	public String getRutUsuario() {
		return rutUsuario;
	}

	/**
	 * Gets the numero pcs.
	 *
	 * @return the numeroPcs
	 */
	public String getNumeroPcs() {
		return numeroPcs;
	}

	/**
	 * Sets the numero pcs.
	 *
	 * @param numeroPcs the numeroPcs to set
	 */
	public void setNumeroPcs(String numeroPcs) {
		this.numeroPcs = numeroPcs;
	}

	/**
	 * Sets the rut usuario.
	 *
	 * @param rutUsuario the rutUsuario to set
	 */
	public void setRutUsuario(String rutUsuario) {
		this.rutUsuario = rutUsuario;
	}

	/**
	 * Enviar mail.
	 *
	 * @param mailTo the mail to
	 * @param nombre the nombre
	 * @param reclamo the reclamo
	 * @param suboperacion the suboperacion
	 * @param motivo the motivo
	 * @param antecedentes the antecedentes
	 * @param medio the medio
	 * @param numeroReclamo the numero reclamo
	 * @param documento the documento
	 * @param monto the monto
	 * @param localizacion the localizacion
	 */
	private void enviarMail(String mailTo, String nombre, ReclamoBean reclamo,
			String suboperacion, String motivo,String suboperaciontText, String motivoText, String antecedentes,
			String medio, String numeroReclamo, String documento, String monto, String codreclamo, String localizacion) {
		String idContenido = "templateMailReclamo";
		String from = "";
		String mailContent = "";
		String mailSubject = "";
		InputStream in = null;

		try {

			mailSubject = JSFPortletHelper.getNodePropertyAsString("id",
					idContenido, "subject");
			from = JSFPortletHelper.getNodePropertyAsString("id", idContenido,
					"from");

			in = JSFPortletHelper.getNodePropertyBinaryValue("id", idContenido,
					"attachment");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String read = null;
			StringBuffer sb = new StringBuffer();
			while ((read = br.readLine()) != null) {
				sb.append(read);
			}

			mailContent = sb.toString();

			String nombreCorto = this.getNombreUsuarioCorto(nombre);
			mailSubject = mailSubject.replaceAll("#NOMBRE#", nombreCorto);

		} catch (Exception e) {
			LOGGER
					.error("No se pudo obtener contenido: idContenido : templateMailReclamo");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e2) {
				}
			}
		}

		if (mailContent != null && !mailContent.equals("")) {
			
			mailContent = mailContent.replace("#CODRECLAMO#", HTMLEntities
					.htmlentities(codreclamo));			
			mailContent = mailContent.replace("#NOMBRECOMPLETO#", HTMLEntities
					.htmlentities(nombre));
			mailContent = mailContent.replace("#RUT#", HTMLEntities
					.htmlentities(rutUsuario));
			mailContent = mailContent.replace("#NUMERORECLAMO#", HTMLEntities
					.htmlentities(numeroReclamo));
			if (suboperacion != null) {
				if (suboperacion.equals(DIFICULTAD_COMERCIAL)) {
					mailContent = mailContent.replace("#RECLAMO#", HTMLEntities					
							.htmlentities(this.obtenerRelaclamo("comercial")));
				} else {
					mailContent = mailContent.replace("#RECLAMO#", HTMLEntities					
							.htmlentities(this.obtenerRelaclamo("tecnico")));

				}
			}

			if (suboperacion != null && motivo != null) {
				if (suboperacion.equals("1")) {					
					mailContent = mailContent.replace("#TIPORECLAMO#", HTMLEntities
							.htmlentities(this.obtenerTipoRelaclamo("tipoComercial",
									motivo)));
					mailContent = mailContent.replace("#LOCALIZACIONPROBLEMA#", "");
				} else {
					mailContent = mailContent.replace("#TIPORECLAMO#", HTMLEntities
							.htmlentities(this.obtenerTipoRelaclamo("tipoTecnico",
									motivo)));
					try {
						
						Node texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_DOCUMENTO_LOCALIZACION_PROBLEMA);					
						String contenido = texto.getProperty("html").getValue().getStringValue().replace("#LOCALIZACION#", localizacion);
						mailContent = mailContent.replace("#LOCALIZACIONPROBLEMA#", contenido);
						
					} catch (Exception e) {
						LOGGER.error("Exception inesperado imposible cargar contenido de la localizacion del problema", e);
					}					
				}
			}

			mailContent = mailContent.replace("#ANTECEDENTES#", HTMLEntities
					.htmlentities(antecedentes));
			mailContent = mailContent.replace("#MEDIORESPUESTA#", HTMLEntities
					.htmlentities(medio));

			if (suboperacion != null) {
				if (suboperacion.equals(DIFICULTAD_COMERCIAL)) {
					if (motivo.equals(BOLETA_FACTURA)) {
						Node texto;
						String contenido = "";
						try {
							texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_DOCUMENTO_MONTO);							
							
							contenido = texto.getProperty("html").getValue().getStringValue();
							contenido = contenido.replace("#DOCUMENTO#", documento);
							contenido = contenido.replace("#MONTO#", monto);
							
							mailContent = mailContent.replace("#BLOQUEDOCUMENTOMONTO#", contenido);							

						} catch (Exception e) {
							LOGGER.error("Exception inesperado imposible cargar contenido de numero y monto ", e);
						}

					} else {
						mailContent = mailContent.replace("#BLOQUEDOCUMENTOMONTO#", "");
					}
				} else {
					mailContent = mailContent.replace("#BLOQUEDOCUMENTOMONTO#", "");
				}
			} else {
				mailContent = mailContent.replace("#BLOQUEDOCUMENTOMONTO#",  "");
			}

		}

		try {
			this.preIngresoOTDelegate.enviarMail(from, mailTo, mailContent,	mailSubject);
		} catch (Exception e) {
			LOGGER.warn("No fue posible enviar el mail de confirmacion creacion de reclamo", e);
		}
	}

	/**
	 * Gets the pre ingreso ot delegate.
	 *
	 * @return the pre ingreso ot delegate
	 */
	public PreIngresoOTDelegate getPreIngresoOTDelegate() {
		return preIngresoOTDelegate;
	}

	/**
	 * Sets the pre ingreso ot delegate.
	 *
	 * @param preIngresoOTDelegate the new pre ingreso ot delegate
	 */
	public void setPreIngresoOTDelegate(
			PreIngresoOTDelegate preIngresoOTDelegate) {
		this.preIngresoOTDelegate = preIngresoOTDelegate;
	}

	/**
	 * Gets the cuenta delegate.
	 *
	 * @return the cuenta delegate
	 */
	public CuentaDelegate getCuentaDelegate() {
		return cuentaDelegate;
	}

	/**
	 * Sets the cuenta delegate.
	 *
	 * @param cuentaDelegate the new cuenta delegate
	 */
	public void setCuentaDelegate(CuentaDelegate cuentaDelegate) {
		this.cuentaDelegate = cuentaDelegate;
	}

	/**
	 * Sets the email usuario.
	 *
	 * @param emailUsuario the new email usuario
	 */
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	/**
	 * Gets the email usuario.
	 *
	 * @return the email usuario
	 */
	public String getEmailUsuario() {
		return emailUsuario;
	}

	/**
	 * Gets the tipos reclamo.
	 *
	 * @return the tiposReclamo
	 */
	public List<CodeDescBean> getTiposReclamo() {
		return tiposReclamo;
	}

	/**
	 * Sets the tipos reclamo.
	 *
	 * @param tiposReclamo the tiposReclamo to set
	 */
	public void setTiposReclamo(List<CodeDescBean> tiposReclamo) {
		this.tiposReclamo = tiposReclamo;
	}

	/**
	 * Gets the tipos reclamo comercial.
	 *
	 * @return the tiposReclamoComercial
	 */
	public List<CodeDescBean> getTiposReclamoComercial() {
		return tiposReclamoComercial;
	}

	/**
	 * Sets the tipos reclamo comercial.
	 *
	 * @param tiposReclamoComercial the tiposReclamoComercial to set
	 */
	public void setTiposReclamoComercial(
			List<CodeDescBean> tiposReclamoComercial) {
		this.tiposReclamoComercial = tiposReclamoComercial;
	}


	/**
	 * Gets the tipos reclamo tecnico.
	 *
	 * @return the tiposReclamoTecnico
	 */
	public List<CodeDescBean> getTiposReclamoTecnico() {
		return tiposReclamoTecnico;
	}

	/**
	 * Sets the tipos reclamo tecnico.
	 *
	 * @param tiposReclamoTecnico the tiposReclamoTecnico to set
	 */
	public void setTiposReclamoTecnico(List<CodeDescBean> tiposReclamoTecnico) {
		this.tiposReclamoTecnico = tiposReclamoTecnico;
	}


	/**
	 * @return the respuestaRegistrarReclamoJson
	 */
	public String getRespuestaRegistrarReclamoJson() {
		return respuestaRegistrarReclamoJson;
	}


	/**
	 * @param respuestaRegistrarReclamoJson the respuestaRegistrarReclamoJson to set
	 */
	public void setRespuestaRegistrarReclamoJson(
			String respuestaRegistrarReclamoJson) {
		this.respuestaRegistrarReclamoJson = respuestaRegistrarReclamoJson;
	}


	/**
	 * Gets the tipos reclamo comercial ppemp.
	 *
	 * @return the tiposReclamoComercialPPEMP
	 */
	public List<CodeDescBean> getTiposReclamoComercialPPEMP() {
		return tiposReclamoComercialPPEMP;
	}

	/**
	 * Sets the tipos reclamo comercial ppemp.
	 *
	 * @param tiposReclamoComercialPPEMP the tiposReclamoComercialPPEMP to set
	 */
	public void setTiposReclamoComercialPPEMP(
			List<CodeDescBean> tiposReclamoComercialPPEMP) {
		this.tiposReclamoComercialPPEMP = tiposReclamoComercialPPEMP;
	}

	/**
	 * Checks if is ppemp.
	 *
	 * @return the pPEMP
	 */
	public boolean isPPEMP() {
		return PPEMP;
	}

	/**
	 * Sets the ppemp.
	 *
	 * @param pPEMP the pPEMP to set
	 */
	public void setPPEMP(boolean pPEMP) {
		PPEMP = pPEMP;
	}

	/**
	 * Gets the numeros asociados.
	 *
	 * @return the numerosAsociados
	 */
	public List<MsisdnAsociadoBean> getNumerosAsociados() {
		return numerosAsociados;
	}

	/**
	 * Sets the numeros asociados.
	 *
	 * @param numerosAsociados the numerosAsociados to set
	 */
	public void setNumerosAsociados(List<MsisdnAsociadoBean> numerosAsociados) {
		this.numerosAsociados = numerosAsociados;
	}

	/**
	 * Gets the msisdn asociado list.
	 *
	 * @return the msisdn asociado list
	 */
	public SelectItem[] getMsisdnAsociadoList() {
		List<MsisdnAsociadoBean> listaAuxiliar = new LinkedList<MsisdnAsociadoBean>();
		try {
			numerosAsociados = (numerosAsociados != null) ? numerosAsociados
					: Collections.<MsisdnAsociadoBean> emptyList();

			for (MsisdnAsociadoBean numeroAsociado : numerosAsociados) {
				if (numeroAsociado != null) {
					if (numeroAsociado.getMercado() != null
							&& numeroAsociado.getSubMercado() != null) {
						listaAuxiliar.add(numeroAsociado);
					}
				}

			}

		} catch (Exception e) {
			LOGGER.warn("No se obtuvo el listado de numeros asociados.", e);
		}
		return JsfUtil.getSelectItemsMsisdnAsociadosReclamos(listaAuxiliar,
				false);
	}

	/**
	 * Gets the nombre usuario corto.
	 *
	 * @param nombre the nombre
	 * @return the nombreUsuario con el nombre del usuarios logeado formato
	 * Aaaaa, primera en Mayusculas y el resto en minusculas
	 * @author Wilson Brochero
	 */
	public String getNombreUsuarioCorto(String nombre) {
		if (nombre != null && nombre != "") {
			try {
				if (nombre != null) {
					nombre = nombre.split(" ")[0];
				}

				if (nombre != null) {
					nombre = nombre.toLowerCase();
					nombreUsuario = Utils.capitalize(nombre);
				}

			} catch (Exception e) {
				LOGGER.warn("No se obtuvo el nombre de Usuario autenticado", e);
			}
		}
		return nombreUsuario;
	}

	/**
	 * Obtener tipo relaclamo equivalente.
	 *
	 * @param tipo the tipo
	 * @param id the id
	 * @return the equivalencia
	 * @author Wilson Brochero Metodo para obtener equivalencia de los tipos de
	 * reclamos.
	 */
	public String obtenerTipoRelaclamoEquivalente(String tipo, String id) {
		String equivalencia = "";

		try {
			equivalencia = MiEntelProperties
					.getProperty("parametros.tipoReclamo.equivalente." + tipo
							+ "." + id);

		} catch (Exception e) {
			LOGGER.warn("No se obtuvo nombre de la equivalencia", e);
		}

		return equivalencia;
	}


	/**
	 * Inits the historico reclamos.
	 *
	 * @param phase the phase
	 * @author Wilson Brochero Munoz
	 */
	public void initHistoricoReclamos(PhaseEvent phase) {		
		List<PaginaHistorialReclamosBean> listHitoricoReclamos;
		ServiceMessages serviceMessages = MiEntelProperties
				.getServiceMessages();
		try {

			ProfileWrapper profile = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			rutUsuario = ProfileWrapperHelper.getPropertyAsString(profile,
					"rutUsuarioSeleccionado");		
			
				if(rutUsuario!=null){
			    	if(rutUsuario.length()<9){
			    		for(int i=rutUsuario.length();i<9;i++){
			    			rutUsuario="0"+rutUsuario;
			    		}
			    	}
			    }
			
			    listHitoricoReclamos = formularioReclamoDelegate.getHistorialReclamos(rutUsuario.toUpperCase());
			    
			  //listHitoricoReclamos = formularioReclamoDelegate.getHistorialReclamos("103853184");

			if (listHitoricoReclamos != null && !listHitoricoReclamos.isEmpty()) {
				respuestaJSON = JsonHelper.toJsonResponse(listHitoricoReclamos);
			} else {
				respuestaJSON = JsonHelper
						.toJsonErrorMessage(serviceMessages.getErrorCodeMessage("noTieneHistoricoReclamos","detalleHistoticoReclamos"));
				this.errorHistoricoReclamos = true;
			}

		} catch (DAOException e) {
			LOGGER.error("DAOException caught: " + e.getMessage(), e);
			this.errorHistoricoReclamos = true;
			this.errorMessage = serviceMessages.getErrorCodeMessage(
					"noDisponible", "general");
			respuestaJSON = JsonHelper.toJsonErrorMessage(errorMessage);

		} catch (ServiceException e) {
			LOGGER.info("ServiceException caught rut: " + rutUsuario + " - "
					+ e.getCodigoRespuesta() + " - "
					+ e.getDescripcionRespuesta());
			this.errorHistoricoReclamos = true;
			this.errorMessage = serviceMessages.getErrorCodeMessage(
					"noTieneHistoricoReclamos", "detalleHistoticoReclamos");
			
			respuestaJSON = JsonHelper.toJsonErrorMessage(errorMessage);

		} catch (Exception e) {
			LOGGER.error("Exception caught: " + e.getMessage(), e);
			this.errorHistoricoReclamos = true;
			this.errorMessage = "Ha ocurrido un error inesperado. "
					+ " Disculpe las molestias";
			respuestaJSON = JsonHelper.toJsonErrorMessage(errorMessage);
		}

	}

	/**
	 * Consultar detalle reclamo.
	 *
	 * @param event the event
	 * @author Wilson Brochero Metodo que permite la consulta del destalle de un
	 * reclamo..
	 */
	public void consultarDetalleReclamo(PhaseEvent event) {
		try {

			DetalleReclamoBean detalleReclamoBean = new DetalleReclamoBean();
			String codReclamo = JsfUtil.getRequestParameter("codReclamo");
			
			detalleReclamoBean = formularioReclamoDelegate
					.consultarDetalleReclamo(codReclamo);
			respuestaJSON = JsonHelper.toJsonResponse(detalleReclamoBean);

		} catch (DAOException e) {
			LOGGER.error("DAOException al consultar detalle del reclamoo", e);
			respuestaJSON = JsonHelper
					.toJsonServiceErrorMessage("detalleReclamo");
		} catch (ServiceException e) {
			LOGGER
					.info("ServiceException al consultar detalle del reclamo. msisdn: "
							+ numeroPcs);
			respuestaJSON = JsonHelper.toJsonServiceErrorMessage(
					"detalleReclamo", e.getCodigoRespuesta());
		} catch (Exception e) {
			LOGGER.error("Exception al al consultar detalle del reclamo", e);
			respuestaJSON = JsonHelper
					.toJsonServiceErrorMessage("detalleReclamo");
		}
	}

	/**
	 * Consultar documento reclamado.
	 *
	 * @param event the event
	 * @author Wilson Brochero Metodo que permite la consulta del monto cobrado
	 * de un documento (factura) indicado
	 */
	public void consultarDocumentoReclamado(PhaseEvent event) {
		try {
			double monto = 0.0;
			String documentoBuscado = JsfUtil
					.getRequestParameter("documentoBuscado");
			List<DocumentoFacturaFullType> documentosFactFullList = new ArrayList<DocumentoFacturaFullType>();			
			DocumentoFacturaFullType documentoFactura = new DocumentoFacturaFullType();

			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			String msisdn = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroPcsSeleccionado");
			String rutTitular = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "rutTitular");

			documentosFactFullList = formularioReclamoDelegate
					.consultarDocumentoReclamado(msisdn, rutTitular);

			Iterator<DocumentoFacturaFullType> facturasIt = documentosFactFullList
					.iterator();
			boolean sw = true;
			while (facturasIt.hasNext() && sw) {
				DocumentoFacturaFullType documento = facturasIt.next();
				if (documentoBuscado != null && documento.getFolio() != null) {
					if (documento.getFolio().equals(documentoBuscado)) {
						monto = Double.parseDouble(documento.getMontoTotal());
						
						documentoFactura.setMontoTotal(Utils.formatMoneyPuntos(monto));
						documentoFactura.setMontoTelefoniaMovil(documento.getMontoTotal());
						documentoFactura.setNumeroCuenta(documento.getNumeroCuenta());
						documentoFactura.setTipo(documento.getTipo());
						
						sw = false;
					}
				}
			}

			respuestaJSON = JsonHelper.toJsonErrorMessage("");

			if (!sw)
				respuestaJSON = JsonHelper.toJsonResponse(documentoFactura);

		} catch (DAOException e) {
			LOGGER.error(
					"DAOException al consultar monto del documento reclamado",
					e);
			respuestaJSON = JsonHelper
					.toJsonServiceErrorMessage("detalleReclamo");
		} catch (ServiceException e) {
			LOGGER
					.info("ServiceException al consultar monto del documento reclamado msisdn: "
							+ numeroPcs);
			respuestaJSON = JsonHelper.toJsonServiceErrorMessage(
					"detalleReclamo", e.getCodigoRespuesta());
		} catch (Exception e) {
			LOGGER
					.error(
							"Exception al consultar monto del documento reclamado msisdn",
							e);
			respuestaJSON = JsonHelper
					.toJsonServiceErrorMessage("detalleReclamo");
		}
	}

	/**
	 * Metodo para obtener los submotivos.
	 *
	 * @param event the event
	 */
	/* Se comentario porque no se utilizara
	public void obtenerSubMotivos(PhaseEvent event) {
		try {
			
			String motivo = JsfUtil.getRequestParameter("motivo");
			tiposReclamoComercial = formularioReclamoDelegate.consultarMotivoReclamo(motivo);
			respuestaJSON = JsonHelper.toJsonResponse(tiposReclamoComercial);

		} catch (DAOException e) {
			LOGGER.error("DAOException al obtener los submotivos", e);
			respuestaJSON = JsonHelper.toJsonServiceErrorMessage("obtenerSubMotivos");
		} catch (ServiceException e) {
			LOGGER.error("ServiceException al obtener los submotivos", e);
			respuestaJSON = JsonHelper.toJsonServiceErrorMessage("obtenerSubMotivos", e.getCodigoRespuesta());
		} catch (Exception e) {
			LOGGER.error("Exception al obtener los submotivos", e);
			respuestaJSON = JsonHelper.toJsonServiceErrorMessage("obtenerSubMotivos");
		}
	}*/
	
	/**
	 * Metodo que retorna un string para hacer el redireccionamiento.
	 *
	 * @return the string
	 */
	public String formularioReclamo() {
		return "formularioReclamo";
	}

	/**
	 * Checks if is nombre vacio.
	 *
	 * @return the nombreVacio
	 */
	public boolean isNombreVacio() {
		return nombreVacio;
	}

	/**
	 * Sets the nombre vacio.
	 *
	 * @param nombreVacio the nombreVacio to set
	 */
	public void setNombreVacio(boolean nombreVacio) {
		this.nombreVacio = nombreVacio;
	}

	/**
	 * Gets the respuesta json.
	 *
	 * @return the respuestaJSON
	 */
	public String getRespuestaJSON() {
		return respuestaJSON;
	}

	/**
	 * Sets the respuesta json.
	 *
	 * @param respuestaJSON the respuestaJSON to set
	 */
	public void setRespuestaJSON(String respuestaJSON) {
		this.respuestaJSON = respuestaJSON;
	}

	/**
	 * Checks if is error historico reclamos.
	 *
	 * @return true, if is error historico reclamos
	 */
	public boolean isErrorHistoricoReclamos() {
		return errorHistoricoReclamos;
	}

	/**
	 * Sets the error historico reclamos.
	 *
	 * @param errorHistoricoReclamos the new error historico reclamos
	 */
	public void setErrorHistoricoReclamos(boolean errorHistoricoReclamos) {
		this.errorHistoricoReclamos = errorHistoricoReclamos;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the dificultadComercial
	 */
	public  String getDificultadComercial() {
		return DIFICULTAD_COMERCIAL;
	}

	/**
	 * @param dificultadComercial the dificultadComercial to set
	 */
	public  void setDificultadComercial(String dificultadComercial) {
		DIFICULTAD_COMERCIAL = dificultadComercial;
	}

	/**
	 * @return the dificultadTecnica
	 */
	public  String getDificultadTecnica() {
		return DIFICULTAD_TECNICA;
	}

	/**
	 * @param dificultadTecnica the dificultadTecnica to set
	 */
	public  void setDificultadTecnica(String dificultadTecnica) {
		DIFICULTAD_TECNICA = dificultadTecnica;
	}

	/**
	 * @return the bOLETA_FACTURA
	 */
	public String getBoletaFactura() {
		return BOLETA_FACTURA;
	}

	/**
	 * @param bOLETAFACTURA the bOLETA_FACTURA to set
	 */
	public void setBoletaFactura(String boletaFactura) {
		BOLETA_FACTURA = boletaFactura;
	}

	
	
	/**
	 * @author Wilson Brochero Metodo para obtener equivalencia de un reclamos
	 * @param String
	 *            tipo
	 * @param String
	 *            id
	 * @return the equivalencia
	 */
	public String obtenerRelaclamoEquivalente(String id) {
		String equivalencia = "";

		try {
			equivalencia = MiEntelProperties
					.getProperty("parametros.reclamo.equivalente." + id);

		} catch (Exception e) {
			LOGGER.warn("No se obtuvo nombre de la equivalencia", e);
		}

		return equivalencia;
	}
	
	
	/**
	 * @author Wilson Brochero 
	 *         Metodo para obtener el texto descriptivo del tipo reclamo.
	 * @param  String tipo
	 * @param  String id       
	 * @return the equivalencia
	 */
	public String obtenerTipoRelaclamo(String tipo, String id) {
		String tipoReclamo = "";

		try {
			
			tipoReclamo = MiEntelProperties
					.getProperty("parametros."+tipo+"." + id+".descripcion");			

		} catch (Exception e) {
			LOGGER.warn("No se obtuvo nombre de la equivalencia", e);
		}

		return tipoReclamo;
	}
	
	
	/**
	 * @author Wilson Brochero 
	 * Metodo para obtener el texto descriptivo del reclamo
	 * @param String
	 *            tipo
	 * @param String
	 *            id
	 * @return the reclamo
	 */
	public String obtenerRelaclamo(String id) {
		String reclamo = "";

		try {
			reclamo = MiEntelProperties
					.getProperty("parametros.tipoReclamos."+id+".descripcion");

		} catch (Exception e) {
			LOGGER.warn("No se obtuvo nombre de la equivalencia", e);
		}

		return reclamo;
	}
	
	

	
	
}
