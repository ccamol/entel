package com.epcs.cliente.perfil.controller.servicios.voz;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResultadoServicioBean;
import com.epcs.cliente.perfil.delegate.AdministracionServiciosDelegate;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

public class WapController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para WapController
     */
    private static final Logger LOGGER = Logger
            .getLogger(WapController.class);

    public WapController() {
    }

    /**
     * Action method para activar servicio WAP
     * 
     * @return null
     */
    public String activarWap() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            String mercado = ProfileWrapperHelper.getPropertyAsString(profile,
                    "mercado");

            ResultadoServicioBean resultado;

            // suscripcion y cuenta controlada
            if (MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)
                    || MiEntelBusinessHelper.isMercadoSuscripcion(mercado)) {
                resultado = this.activacion_SSCC(numeroPcsSeleccionado);
            }
            // Prepago
            else {
                resultado = this.activacion_PP(numeroPcsSeleccionado);
            }

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "activacionWap", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al activar servicio", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionWap");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionWap");
        }

        return null;
    }

    /**
     * Action method para desactivar servicio WAP
     * 
     * @return null
     */
    public String desactivarWap() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            String mercado = ProfileWrapperHelper.getPropertyAsString(profile,
                    "mercado");

            ResultadoServicioBean resultado;

            // suscripcion y cuenta controlada
            if (MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)
                    || MiEntelBusinessHelper.isMercadoSuscripcion(mercado)) {
                resultado = this.desactivacion_SSCC(numeroPcsSeleccionado);
            }
            // Prepago
            else {
                resultado = this.desactivacion_PP(numeroPcsSeleccionado);
            }

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "desactivacionWap", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionWap");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionWap");
        }

        return null;
    }

    private ResultadoServicioBean activacion_PP(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.activarWapPP(numeroPcsSeleccionado);
    }

    private ResultadoServicioBean activacion_SSCC(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.activarWap(numeroPcsSeleccionado);
    }

    private ResultadoServicioBean desactivacion_PP(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.desactivarWapPP(numeroPcsSeleccionado);
    }

    private ResultadoServicioBean desactivacion_SSCC(
            String numeroPcsSeleccionado) throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.desactivarWap(numeroPcsSeleccionado);
    }
}
