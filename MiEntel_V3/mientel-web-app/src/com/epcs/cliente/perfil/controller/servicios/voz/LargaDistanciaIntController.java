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

public class LargaDistanciaIntController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para LargaDistanciaIntController
     */
    private static final Logger LOGGER = Logger
            .getLogger(LargaDistanciaIntController.class);

    public LargaDistanciaIntController() {
    }

    /**
     * Action method para activar larga distancia int.
     * 
     * @return null
     */
    public String activarLDI() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.activacion(numeroPcsSeleccionado);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "activacionLDI", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al activar servicio", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionLDI");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionLDI");
        }

        return null;
    }

    /**
     * Action method para desactivar larga distancia int.
     * 
     * @return null
     */
    public String desactivarLDI() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.desactivacion(numeroPcsSeleccionado);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "desactivacionLDI", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionLDI");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionLDI");
        }

        return null;
    }

    private ResultadoServicioBean activacion(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.activarLDI(numeroPcsSeleccionado);
    }

    private ResultadoServicioBean desactivacion(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.desactivarLDI(numeroPcsSeleccionado);
    }

}
