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

public class CobroRevertidoController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para CobroRevertidoController
     */
    private static final Logger LOGGER = Logger
            .getLogger(CobroRevertidoController.class);
    
    private String msisdnAsociados;
    
    private String msisdnActuales;

    public CobroRevertidoController() {
    	msisdnAsociados = new String();
    	msisdnActuales = new String();
    }
    
    
    /**
     * Action method para activar Cobro revertido
     * 
     * @return null
     */
    public String modificarListaCobroRevertido() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado = new ResultadoServicioBean();
            String numerosActuales [] = msisdnActuales.split(",");
            String numerosSeleccionados [] = msisdnAsociados.split(",");
            
            //Agregar
            for (int i = 0; i < numerosSeleccionados.length; i++) {
                if(!msisdnActuales.contains(numerosSeleccionados[i]) && numerosSeleccionados[i].length() >= 8){
                    //Si no lanza una excepcion se agrega el numero
                    this.validarNumero(numeroPcsSeleccionado, numerosSeleccionados[i]);
                    
                    resultado = this.agregrarNumero(numeroPcsSeleccionado, numerosSeleccionados[i]);
                }
            }
            
            //Eliminar
            for (int i = 0; i < numerosActuales.length; i++) {
                if(!msisdnAsociados.contains(numerosActuales[i]) && numerosActuales[i].length() >= 8){
                    resultado = this.eliminarNumero(numeroPcsSeleccionado, numerosActuales[i]);
                }
            }

           
            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "actualizacionCobroRevertido", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al activar servicio", e);
            JSFMessagesHelper.addServiceErrorMessage("actualizacionCobroRevertido");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addServiceErrorMessage("actualizacionCobroRevertido");
        }

        return null;
    }
    
    
    

    /**
     * Action method para activar cobro revertido
     * 
     * @return null
     */
    public String activarCobroRevertido() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.agregrarNumero(numeroPcsSeleccionado, "");

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "activacionCobroRevertido", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al activar servicio", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionCobroRevertido");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionCobroRevertido");
        }

        return null;
    }

    /**
     * Action method para desactivar Cobro Revertido
     * 
     * @return null
     */
    public String desactivarCobroRevertido() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");
            
            ResultadoServicioBean resultado = new ResultadoServicioBean();

            String [] numerosEliminar = msisdnActuales.split(",");
            for (int i = 0; i < numerosEliminar.length; i++) {
                resultado = this.eliminarNumero(numeroPcsSeleccionado, numerosEliminar[i]);    
            }

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "desactivacionCobroRevertido", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionCobroRevertido");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionCobroRevertido");
        }

        return null;
    }
    
    /**
     * Action method para activar recepcion cobro revertido
     * @return
     */
    public String activarRecepcionCobroRevertido() {
        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");
            
            ResultadoServicioBean resultado = this.activarRecepcionCobroRevertido(numeroPcsSeleccionado);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "activacionCobroRevertido", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addServiceErrorMessage("desactivacionCobroRevertido");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addServiceErrorMessage("desactivacionCobroRevertido");
        }

        return null;
    }

    /**
     * Action method para desactivar recepcion cobro revertido
     * @return
     */
    public String desactivarRecepcionCobroRevertido() {
        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");
            
            ResultadoServicioBean resultado = this.desactivarRecepcionCobroRevertido(numeroPcsSeleccionado);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "desactivacionCobroRevertido", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addServiceErrorMessage("desactivacionCobroRevertido");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addServiceErrorMessage("desactivacionCobroRevertido");
        }

        return null;
    }
    
    /**
     * Action method para activar cobro revertido automatico
     * @return
     */
    public String activarCobroRevertidoAutomatico() {
        return null;
    }

    /**
     * Action method para desactivar cobro revertido automatico
     * @return
     */
    public String desactivarCobroRevertidoAutomatico() {
        return null;
    }

    
    private ResultadoServicioBean agregrarNumero(String numeroPcsSeleccionado, String numeroAgregar)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.agregarNumeroCobroRevertido(numeroPcsSeleccionado, numeroAgregar);
    }

    private ResultadoServicioBean eliminarNumero(String numeroPcsSeleccionado, String numeroEliminar)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.eliminarNumeroCobroRevertido(numeroPcsSeleccionado, numeroEliminar);
    }
    
    private ResultadoServicioBean validarNumero(String numeroPcsSeleccionado,
            String numeroValidar) throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.validarNumeroCobroRevertido(numeroPcsSeleccionado,
                numeroValidar);
    }
    
    private ResultadoServicioBean activarRecepcionCobroRevertido(String numeroPcsSeleccionado
            ) throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.activarRecepcionCobroRevertido(numeroPcsSeleccionado);
    }
    
    
    private ResultadoServicioBean desactivarRecepcionCobroRevertido(
            String numeroPcsSeleccionado) throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate
                .desactivarRecepcionCobroRevertido(numeroPcsSeleccionado);
    }

    /**
     * @param msisdnAsociados the msisdnAsociados to set
     */
    public void setMsisdnAsociados(String msisdnAsociados) {
        this.msisdnAsociados = msisdnAsociados;
    }

    /**
     * @return the msisdnAsociados
     */
    public String getMsisdnAsociados() {
        return msisdnAsociados;
    }

    /**
     * @return the msisdnActuales
     */
    public String getMsisdnActuales() {
        return msisdnActuales;
    }

    /**
     * @param msisdnActuales the msisdnActuales to set
     */
    public void setMsisdnActuales(String msisdnActuales) {
        this.msisdnActuales = msisdnActuales;
    }

}
