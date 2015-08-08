/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MiEntelBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.inscripcion.util.InscripcionHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class MisUsuariosController implements Serializable {

    private static final Logger LOGGER = Logger
            .getLogger(MisUsuariosController.class);

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private CuentaDelegate cuentaDelegate;

    private List<UsuarioBean> misUsuarioList;
    
    private String rutUsuarioSeleccionado;
    
    private String aaaUsuarioSesion;
    
    private String nuevoAaa;
    
    private boolean isUserAdmin;
    
    private boolean cuentaMultilinea;
    
    private static final String COD_INGRESO_FORMRUT = MiEntelProperties
            .getProperty("inscripcion.formPreRegistro.codigo.ingresar");    

    /**
     * @return the cuentaDelegate
     */
    public CuentaDelegate getCuentaDelegate() {
        return cuentaDelegate;
    }

    /**
     * @param cuentaDelegate
     *            the cuentaDelegate to set
     */
    public void setCuentaDelegate(CuentaDelegate cuentaDelegate) {
        this.cuentaDelegate = cuentaDelegate;
    }
    
    
    public boolean getUserAdmin(){
        try{
            
         HttpServletRequest request = JSFPortletHelper.getRequest(FacesContext
                    .getCurrentInstance());
            
        ProfileWrapper profileWrapper = ProfileWrapperHelper
        .getProfile(request);

        String codigoAAATitular = MiEntelProperties
                .getProperty("aaa.titular.code");
        String userCodigoAAA = ProfileWrapperHelper.getPropertyAsString(
                profileWrapper, "aaa");
        isUserAdmin = codigoAAATitular.equals(userCodigoAAA);
        LOGGER.info("User Admin :"+isUserAdmin);
        
        } catch (Exception e) {
            LOGGER.error("Exception inesperado listar mis usuarios", e);
            JSFMessagesHelper.addServiceErrorMessage("inesperado");
        }
        
        return isUserAdmin;
        
    }
    
    

    /**
     * Usuarios asociados a un titular
     * @return List<UsuarioBean>
     */

    public List<UsuarioBean> getUsuarios() {
        try {

            if (this.misUsuarioList == null) {
                loadData();
            }
        }catch (DAOException e) {
          LOGGER.error("DAOException al obtener usuarios:"+e.getMessage(), e);
        }catch (ServiceException e) {
          LOGGER.info("ServiceException al obtener usuarios");
        }
        catch (Exception e) {
          LOGGER.error("Exception inesperado al obtener usuarios:"+e.getMessage(), e);
        }

        return this.misUsuarioList;
    }

    /**
     * Inicializa la informacion de usuarios
     * 
     * @throws Exception
     */
    public void loadData() throws Exception {
        if (this.misUsuarioList == null) {
            // Obtener datos del Usuario en SEsion
            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            String rut = ProfileWrapperHelper.getPropertyAsString(profile,
                    "numeroCuenta");
            LOGGER.info("Numero de Cuenta :" + rut);
            this.misUsuarioList = this.cuentaDelegate.getUsuarios(rut);
            
        }

    }
    
    
    /**
     * Actualiza el AAA de un usuario
     * Recibe nroPcs y rut por request
     * @return null postback
     * @throws ServiceException
     * @throws DAOException
     */
    public String actualizarAAA() {
    	String nroPcs = "";
        try {
            // Datos para obtener info del Usuarios
            nroPcs = JsfUtil.getRequestParameter("nroPcs");
            String rut = JsfUtil.getRequestParameter("rut");
            String aaa = JsfUtil.getRequestParameter("idp");

            LOGGER.info("numeroPcs :" + nroPcs + " rut :" + rut + " aut" + aaa
                    + " " + this.getNuevoAaa());
            this.cuentaDelegate.actualizarAAA(nroPcs, rut, aaa, this
                    .getNuevoAaa());
            this.misUsuarioList = null;
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al actualizar aaa", e);
            JSFMessagesHelper.addServiceErrorMessage("serviceDisabled");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al actualizar aaa. msisdn: "+nroPcs);
            JSFMessagesHelper.addServiceErrorMessage("serviceDisabled");
        } catch (Exception e) {
            LOGGER.error("Exception al actualizar aaa", e);
            JSFMessagesHelper.addServiceErrorMessage("serviceDisabled");
        }
        
        return "tablaMisUsuarios";
    }
    
    
    /**
     * Obtener el aaa del usuario en sesion
     * @return aaa del usuario en sesion
     */
    public String getAaaUsuarioSesion(){
        if(this.aaaUsuarioSesion == null){
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            try {
                this.aaaUsuarioSesion  = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
            } catch (Exception e) {
                LOGGER.error("No se pudo obtener del perfil de usuario la propiedad [aaa].!",e);
            }
            
        }
        return this.aaaUsuarioSesion;
    }
    
    /**
     * Obtener el aaa RutUsuarioSeleccionadon
     * @return RutUsuarioSeleccionado del usuario en sesion
     */
    public String getRutUsuarioSeleccionado() {
        if (this.rutUsuarioSeleccionado == null) {
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            try {
                this.rutUsuarioSeleccionado = ProfileWrapperHelper
                        .getPropertyAsString(profileWrapper,
                                "rutUsuarioSeleccionado");
            } catch (Exception e) {
                LOGGER.error("No se pudo obtener del perfil de " +
                        "usuario la propiedad " +
                        "[rutUsuarioSeleccionado].!", e);
            }
        }
        return this.rutUsuarioSeleccionado;
    }
    
    
    
    
    /**
     * Mensaje de usuario no autorizado
     * @return
     */
    public String getMensajeUsuarioNoAutorizado(){
        String mensaje = "";
        try{
         mensaje = MiEntelProperties
        .getProperty("solo.usuario.titular");
        }catch (Exception e) {
            LOGGER.error("Erro al obtener propiedad [solo.usuario.tituar]",e);
        }
        return mensaje;
    }
    
    /**
     * Metodo utilitario para la obtencion de la URL y redireccionamiento al
     * Formulario de Ingreso Registro Usuario desde la tabla de Mis Usuarios.
     * 
     */
    public String redirectFormIngresoRut() {
        HttpServletRequest request = JSFPortletHelper.getRequest(FacesContext
                .getCurrentInstance());
        ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(request);
        String msisdn = JsfUtil.getRequestParameter("nroPCS");

        try {
            cuentaDelegate.obtenerPerfilUsuario(msisdn);
        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage());
            JSFMessagesHelper.addServiceErrorMessage("obtenerPerfil",
                    new String[] { msisdn });
        } catch (ServiceException e) {        	
            if (e.getCodigoRespuesta().equals(COD_INGRESO_FORMRUT)) {
                // Se inicia el ciclo para la inscripcion de usuario , flujo
                // selector de cuenta.
                InscripcionHelper insHelper = new InscripcionHelper();
                insHelper.startInscripcion(profileWrapper, msisdn);
            } else {
                LOGGER.info("ServiceException caught: msisdn: "+msisdn+" - "
                        + e.getCodigoRespuesta() + " - "
                        + e.getDescripcionRespuesta());
                JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", e
                        .getCodigoRespuesta());
            }
        } catch (Exception e) {
            LOGGER.error("Exception al intentar obtener y redireccionar a la URL del Formulario de Inscripcion" + e.getMessage(), e);
        }
        return "";
    }
    
    /**
     * Metodo que indica si una cuenta es multilinea (Tiene varios moviles asociados)
     * @return
     */
	public boolean isCuentaMultilinea() {
		cuentaMultilinea = false;
		try {
			ProfileWrapper profile = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(
					profile, "numeroCuenta");
			String aaa = ProfileWrapperHelper.getPropertyAsString(profile,
					"aaa");
			if (this.cuentaDelegate.getUsuarios(numeroCuenta).size() > 1
					&& aaa.equals(MiEntelBusinessHelper.getAAATitular())) {
				cuentaMultilinea = true;
			}
		} catch (DAOException e) {
			LOGGER.error("DAOException al obtener usuarios: " + e.getMessage(), e);
		} catch (ServiceException e) {
			LOGGER.info("ServiceException al obtener usuarios");
		} catch (Exception e) {
			LOGGER.info("Exception al obtener usuarios");
		}

		return cuentaMultilinea;
	}
    
    public void setCuentaMultilinea(boolean cuentaMultilinea) {
		this.cuentaMultilinea = cuentaMultilinea;
	}

	/**
     * @return the nuevoAaa
     */
    public String getNuevoAaa() {
        return nuevoAaa;
    }

    /**
     * @param aaa
     *            the nuevoAaa to set
     */
    public void setNuevoAaa(String nuevoAaa) {
        this.nuevoAaa = nuevoAaa;
    }

}
