package com.epcs.cliente.perfil.controller.servicios.voz;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResultadoServicioBean;
import com.epcs.cliente.perfil.delegate.AdministracionServiciosDelegate;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

public class Numero700Controller implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para Numero700Controller
     */
    private static final Logger LOGGER = Logger
            .getLogger(Numero700Controller.class);

    public Numero700Controller() {
    }

    /**
     * Action method para activar numero 700
     * 
     * @return null
     */
    public String activarNum700() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.activacion(numeroPcsSeleccionado);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "activacionNum700", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al activar servicio", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionNum700");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionNum700");
        }

        return null;
    }

    /**
     * Action method para desactivar numero interarea
     * 
     * @return null
     */
    public String desactivarNum700() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.desactivacion(numeroPcsSeleccionado);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "desactivacionNum700", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionNum700");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionNum700");
        }

        return null;
    }

    private ResultadoServicioBean activacion(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.activarNumero700(numeroPcsSeleccionado);
    }

    private ResultadoServicioBean desactivacion(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.desactivarNumero700(numeroPcsSeleccionado);
    }

}
