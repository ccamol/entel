/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.erp.seguridad.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.LoginUsuarioBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * @author jlopez (I2B) en nombre Absalon Opazo (Atencion al Cliente, EntelPcs)
 *         Se le agrega manejo de errores de sevicios
 * 
 */
public class SeguridadController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(SeguridadController.class);

    private SeguridadDelegate seguridadDelegate;

    private LoginUsuarioBean loginUsuarioBean;

    private UsuarioBean usuarioBean;

    public SeguridadController() {
        this.loginUsuarioBean = new LoginUsuarioBean();
        this.usuarioBean = new UsuarioBean();
        this.seguridadDelegate = new SeguridadDelegate();
    }

    public LoginUsuarioBean getUsuario() {
        return this.loginUsuarioBean;
    }

    public UsuarioBean getUsuarioBean() {
        return this.usuarioBean;
    }

    /**
     * Realiza el cambio de la clave actual.
     * 
     * @return null postback
     * @throws DAOException
     * @throws ServiceException
     */
    public String cambiarClave() {

        try {
            // Obtener numeroPcs del usuario en sesion
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            String numeroPCS = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcs");

            this.getUsuarioBean().setNumeroPCS(numeroPCS);

            if(getUsuarioBean().getClaveNueva().equals(getUsuarioBean().getClaveNuevaReingreso())) {
                this.seguridadDelegate.cambiarClave(this.getUsuarioBean());
                JSFMessagesHelper.addSuccessMessage("cambioClave", "cambioExitoso");
            } else {
                JSFMessagesHelper.addErrorMessage("cambioClave", "clavesNoCoinciden");
            }
            
        } catch (DAOException e) {
            JSFMessagesHelper.addServiceErrorMessage("noDisponible");
        } catch (ServiceException e) {
            LOGGER.error("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("gestionSeguridad", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado en cambiar clave", e);
            JSFMessagesHelper.addServiceErrorMessage("inesperado"); 
        }
        
        return null;
    }

}
