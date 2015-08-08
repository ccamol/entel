package com.epcs.formulariocontacto.controller;

import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.CodeDescBean;
import com.epcs.bean.GenerarTicketBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.formulariocontacto.delegate.FormularioContactoDelegate;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class FormularioContactoController {

	private static final long serialVersionUID = 1L;
	
	/**
     * Logger para FormularioContactoController
     */
    private static final Logger LOGGER = Logger
            .getLogger(FormularioContactoController.class);

    private FormularioContactoDelegate formularioContactoDelegate;
    
    private CuentaDelegate cuentaDelegate;
    
    private GenerarTicketBean ticketBean;
    
    private String respuestaJson;
    
    private SelectItem[] motivosFormularioContacto;
    
    private SelectItem[] tiposFormularioContacto;
    
    private SelectItem[] indicativosTelefono;
    
    private String mensajeExitoCM;
    
    private UsuarioBean usuario;
    
    private boolean mostrarLinkFormContacto;
    
    public FormularioContactoController(){
    	formularioContactoDelegate = new FormularioContactoDelegate();
    	cuentaDelegate = new CuentaDelegate();
    	ticketBean = new GenerarTicketBean();
    }          
    
    /**
     * Metodo que inicializa los datos de usuario para ser mostrados en el formulario
     */
    public void init(PhaseEvent event) {               
        try{        	        	
        	ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        	String numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,"numeroPcs");
        	String rut = ProfileWrapperHelper.getPropertyAsString(profile,"rutUsuario");        	
        	usuario = cuentaDelegate.obtenerUsuario(numeroPcs, rut);
        	
        	String idContenido = MiEntelProperties.getProperty("parametros.formularioContacto.mensajeExito.idContenido");
        	Node mensajeServicio = JSFPortletHelper.getContentNode("idContenido", idContenido);
        	mensajeExitoCM = mensajeServicio.getProperty("html").getValue().getStringValue();
        	        	        	        
        	ticketBean.setPrimerNombre(usuario.getPrimerNombre());
        	ticketBean.setSegundoNombre(usuario.getSegundoNombre());
        	ticketBean.setPrimerApellido(usuario.getApellidoPaterno());
        	ticketBean.setSegundoApellido(usuario.getApellidoMaterno());        	
        	ticketBean.setNumeroPCS(usuario.getNumeroPCS());
        	ticketBean.setRut(usuario.getRut().getStringValue());       
        	
        }
        catch (Exception e) {
        	LOGGER.error("Error al inicializar informacion de formulario contacto.", e);
            JSFMessagesHelper.addErrorCodeMessage("general", "0001");
        }
    }
    
    public void verificaLinkFormContacto(PhaseEvent event) {
        try {
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            String mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");

            if (MiEntelBusinessHelper.isMercadoPrepago(mercado)) {
                mostrarLinkFormContacto = false;
            } else {
                mostrarLinkFormContacto = true;
            }

        } catch (Exception e) {
            LOGGER.error("Error al inicializar informacion de formulario de medios contacto.", e);
            JSFMessagesHelper.addErrorCodeMessage("general", "0001");
        }
    }  
    
    /**
     * Metodo que retorna un string para hacer el redireccionamiento
     */
    public String formularioContactoForm(){
    	return "formularioContacto";
    }
    
    /**
     * Metodo que genera el ticket GSA
     */
    public String generarTicketSGA(){
    	
    	String response = "";
    	
    	try {    		
    		formularioContactoDelegate.generarTicketSGA(ticketBean);
    		response = "success";
        }  catch (DAOException e) {
            LOGGER.error("Error al inicializar informacion de formulario contacto.", e);
            JSFMessagesHelper.addErrorCodeMessage("general", "0001");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al inicializar informacion de formulario contacto.");
            JSFMessagesHelper.addErrorCodeMessage("formulariocontacto", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Error al inicializar informacion de formulario contacto.", e);
            JSFMessagesHelper.addErrorCodeMessage("general", "0001");
        }
        
        return response;
    }

    /**
     * Metodo que devuelve el listado de indicativos telefonicos para ser mostrados en un combo en la pagina
     */
    public SelectItem[] getIndicativosTelefono() {

        if (indicativosTelefono == null) {
            List<CodeDescBean> indTelList = ParametrosHelper.getIndicativoTelefono();
            indicativosTelefono = JsfUtil.getSelectItemsCodeDesc(indTelList, false);
        }

        return indicativosTelefono;
    }
    
    /**
     * Metodo que devuelve el listado de motivos de contacto para ser mostrados en un combo en la pagina
     */
    public SelectItem[] getMotivosFormularioContacto() {

        if (motivosFormularioContacto == null) {
            List<CodeDescBean> motivosList = ParametrosHelper.getMotivosFormularioContacto();
            motivosFormularioContacto = JsfUtil.getSelectItemsCodeDesc(motivosList, true, "Selecciona");
        }

        return motivosFormularioContacto;
    }
    
    /**
     * Metodo que devuelve el listado de tipos de contacto para ser mostrados en un combo en la pagina
     */
    public SelectItem[] getTiposFormularioContacto() {

        if (tiposFormularioContacto == null) {
            List<CodeDescBean> tiposList = ParametrosHelper.getTiposFormularioContacto();
            tiposFormularioContacto = JsfUtil.getSelectItemsCodeDesc(tiposList, true, "Selecciona");
        }

        return tiposFormularioContacto;
    }
    
	public FormularioContactoDelegate getFormularioContactoDelegate() {
		return formularioContactoDelegate;
	}

	public void setFormularioContactoDelegate(
			FormularioContactoDelegate formularioContactoDelegate) {
		this.formularioContactoDelegate = formularioContactoDelegate;
	}

	public GenerarTicketBean getTicketBean() {
		return ticketBean;
	}

	public void setTicketBean(GenerarTicketBean ticketBean) {
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
     * @return the mostrarFormContacto
     */
    public boolean isMostrarLinkFormContacto() {  
        return mostrarLinkFormContacto;
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
