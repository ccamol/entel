package com.epcs.cliente.perfil.controller.servicios.datos;

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

public class InternetMovilBAMController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para InternetMovilController
     */
    private static final Logger LOGGER = Logger
            .getLogger(InternetMovilController.class);

    public InternetMovilBAMController() {
    }

    /**
     * Action method para activar numero 606
     * 
     * @return null
     */
    public String activarInetMovilBAM() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.activacion(numeroPcsSeleccionado);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "activacionInetMovilBAM", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al activar servicio", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionInetMovilBAM");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionInetMovilBAM");
        }

        return null;
    }

    /**
     * Action method para desactivar numero interarea
     * 
     * @return null
     */
    public String desactivarInetMovilBAM() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.desactivacion(numeroPcsSeleccionado);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "desactivacionInetMovilBAM", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionInetMovilBAM");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionInetMovilBAM");
        }

        return null;
    }

    private ResultadoServicioBean activacion(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.activarInternetMovilBAM(numeroPcsSeleccionado);
    }

    private ResultadoServicioBean desactivacion(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.desactivarInternetMovilBAM(numeroPcsSeleccionado);
    }

}
