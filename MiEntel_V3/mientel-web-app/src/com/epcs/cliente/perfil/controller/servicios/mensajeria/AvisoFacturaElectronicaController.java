package com.epcs.cliente.perfil.controller.servicios.mensajeria;

import java.io.Serializable;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResultadoServicioBean;
import com.epcs.cliente.perfil.delegate.AdministracionServiciosDelegate;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

public class AvisoFacturaElectronicaController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para AvisoFacturaElectronicaController
     */
    private static final Logger LOGGER = Logger
            .getLogger(AvisoFacturaElectronicaController.class);

    private SelectItem[] diasAnticipacionItems;
    
    private String numeroDias;
    
    public AvisoFacturaElectronicaController() {
    }

    /**
     * @return the diasAnticipacionItems
     */
    public SelectItem[] getDiasAnticipacionItems() {
        if (diasAnticipacionItems == null) {
            diasAnticipacionItems = JsfUtil
                    .getSelectItemsDiasAnticipacion();
        }
        return diasAnticipacionItems;
    }

    /**
     * @param diasAnticipacionItems the diasAnticipacionItems to set
     */
    public void setDiasAnticipacionItems(SelectItem[] diasAnticipacionItems) {
        this.diasAnticipacionItems = diasAnticipacionItems;
    }


    /**
     * Action method para activar FacturaElectronica
     * 
     * @return null
     */
    public String activarFacturaElectronica() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            
            resultado = this.activacion(numeroPcsSeleccionado, numeroDias);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "activacionFacturaElectronica", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al activar servicio", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionFacturaElectronica");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionFacturaElectronica");
        }

        return null;
    }

    /**
     * Action method para desactivar FacturaElectronica
     * 
     * @return null
     */
    public String desactivarFacturaElectronica() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.desactivacion(numeroPcsSeleccionado);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "desactivacionFacturaElectronica", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionFacturaElectronica");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionFacturaElectronica");
        }

        return null;
    }
    
    public String guardarConfiguracion() {
        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.actualizacion(numeroPcsSeleccionado, numeroDias);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "actualizarFacturaElectronica", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "actualizarFacturaElectronica");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "actualizarFacturaElectronica");
        }

        return null;
    }


    private ResultadoServicioBean activacion(String numeroPcsSeleccionado,
            String numeroDias) throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.activarFacturaElectronica(numeroPcsSeleccionado, numeroDias);
    }


    private ResultadoServicioBean desactivacion(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.desactivarFacturaElectronica(numeroPcsSeleccionado);
    }


    private ResultadoServicioBean actualizacion(String numeroPcsSeleccionado,
            String numeroDias) throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.activarFacturaElectronica(numeroPcsSeleccionado, numeroDias);
    }

    /**
     * @return the numeroDias
     */
    public String getNumeroDias() {
        return numeroDias;
    }

    /**
     * @param numeroDias the numeroDias to set
     */
    public void setNumeroDias(String numeroDias) {
        this.numeroDias = numeroDias;
    }

}
