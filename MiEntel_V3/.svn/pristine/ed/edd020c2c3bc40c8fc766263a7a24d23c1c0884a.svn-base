package com.epcs.formulariocontacto.controller;

import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.CodeDescBean;
import com.epcs.bean.GenerarTicketPortabilidadBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.formulariocontacto.delegate.FormularioContactoDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 */
public class FormularioContactoPortabilidadController {
	
	/**
     * Logger para FormularioContactoPortabilidadController
     */
    private static final Logger LOGGER = Logger.getLogger(FormularioContactoPortabilidadController.class);
    private FormularioContactoDelegate formularioContactoDelegate;    
    private CuentaDelegate cuentaDelegate;    
    private GenerarTicketPortabilidadBean ticketBean;    
    private String respuestaJson;    
    private SelectItem[] marcasEquipo;
    private String marcaEquipo;
    private String modeloEquipo;
    private String mensajeExitoCM;    
    private UsuarioBean usuario;
    //RGUZMAN
    private SelectItem[] indicativosTelefono;
    
    public FormularioContactoPortabilidadController() {
    	ticketBean = new GenerarTicketPortabilidadBean();
    }
    
    public void init(PhaseEvent event) {
        try{        	        	
        	ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        	String numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,"numeroPcs");
        	String rut = ProfileWrapperHelper.getPropertyAsString(profile,"rutUsuario");        	
        	usuario = cuentaDelegate.obtenerUsuario(numeroPcs, rut);
        	
			String idContenido = MiEntelProperties.getProperty("parametros.formularioContactoPortabilidad.mensajeExito.idContenido");
			Node mensajeServicio = JSFPortletHelper.getContentNode("idContenido", idContenido);
			if (mensajeServicio == null) {
				mensajeExitoCM = "";
			} else {
				mensajeExitoCM = mensajeServicio.getProperty("html").getValue().getStringValue();
			}
        	        	        	        
        	ticketBean.setPrimerNombre(usuario.getPrimerNombre());
        	ticketBean.setSegundoNombre(usuario.getSegundoNombre());
        	ticketBean.setPrimerApellido(usuario.getApellidoPaterno());
        	ticketBean.setSegundoApellido(usuario.getApellidoMaterno());        	
        	ticketBean.setNumeroPCS(usuario.getNumeroPCS());
        	ticketBean.setRut(usuario.getRut().getStringValue());        	
        } catch (Exception e) {
        	LOGGER.error("Error al inicializar informacion de Formulario Contacto Portabilidad.", e);
            JSFMessagesHelper.addErrorCodeMessage("general", "0001");
        }
    }
    
	public String generarTicketSGA() {
		String response = "";

		try {
			formularioContactoDelegate.generarTicketPortabilidadSGA(ticketBean);
			response = "success";
		} catch (DAOException e) {
			LOGGER.error("Error al inicializar informacion de Formulario Contacto Portabilidad.", e);
			JSFMessagesHelper.addErrorCodeMessage("general", "0001");
		} catch (ServiceException e) {
			LOGGER.error("Error al inicializar informacion de Formulario Contacto Portabilidad.", e);
			JSFMessagesHelper.addErrorCodeMessage("formularioContactoPortabilidad", e.getCodigoRespuesta());
		} catch (Exception e) {
			LOGGER.error("Error al inicializar informacion de Formulario Contacto Portabilidad.", e);
			JSFMessagesHelper.addErrorCodeMessage("general", "0001");
		}

		return response;
	}
	
    public SelectItem[] getMarcasEquipo() {
        if (marcasEquipo == null) {
            List<CodeDescBean> marcasList = ParametrosHelper.getMarcasEquipo();
            marcasEquipo = JsfUtil.getSelectItemsCodeDesc(marcasList, true, "Seleccionar marca");
        }

        return marcasEquipo;
    }
    
	public String getMarcaEquipo() {
		return marcaEquipo;
	}

	public void setMarcaEquipo(String marcaEquipo) {
		this.marcaEquipo = marcaEquipo;
	}

	public String getModeloEquipo() {
		return modeloEquipo;
	}

	public void setModeloEquipo(String modeloEquipo) {
		this.modeloEquipo = modeloEquipo;
	}

	public FormularioContactoDelegate getFormularioContactoDelegate() {
		return formularioContactoDelegate;
	}

	public void setFormularioContactoDelegate(FormularioContactoDelegate formularioContactoDelegate) {
		this.formularioContactoDelegate = formularioContactoDelegate;
	}

	public CuentaDelegate getCuentaDelegate() {
		return cuentaDelegate;
	}

	public void setCuentaDelegate(CuentaDelegate cuentaDelegate) {
		this.cuentaDelegate = cuentaDelegate;
	}

	public GenerarTicketPortabilidadBean getTicketBean() {
		return ticketBean;
	}

	public void setTicketBean(GenerarTicketPortabilidadBean ticketBean) {
		this.ticketBean = ticketBean;
	}

	public String getRespuestaJson() {
		return respuestaJson;
	}

	public void setRespuestaJson(String respuestaJson) {
		this.respuestaJson = respuestaJson;
	}

	public String getMensajeExitoCM() {
		return mensajeExitoCM.replace("{email}", ticketBean.getMail());
	}

	public void setMensajeExitoCM(String mensajeExitoCM) {
		this.mensajeExitoCM = mensajeExitoCM;
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}    

	/**
	 * Metodo que retorna un String para hacer el redireccionamiento
	 */
	public String formularioContactoPortabilidadForm() {
		return "formularioContactoPortabilidad";
	}
	
    /**
     * RGUZMAN 
     * Metodo que devuelve el listado de indicativos telefonicos para ser mostrados en un combo en la pagina
     */
    public SelectItem[] getIndicativosTelefono() {

        if (indicativosTelefono == null) {
            List<CodeDescBean> indTelList = ParametrosHelper.getIndicativoTelefono();
            indicativosTelefono = JsfUtil.getSelectItemsCodeDesc(indTelList, false);
        }

        return indicativosTelefono;
    }
}