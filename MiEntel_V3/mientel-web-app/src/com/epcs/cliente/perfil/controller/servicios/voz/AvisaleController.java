package com.epcs.cliente.perfil.controller.servicios.voz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResultadoServicioBean;
import com.epcs.cliente.perfil.delegate.AdministracionServiciosDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

public class AvisaleController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para AvisaleController
     */
    private static final Logger LOGGER = Logger
            .getLogger(AvisaleController.class);

    
    private static final String MODO_AVISAR_TODOS = MiEntelProperties
            .getProperty("adminServicios.avisale.tipoLista.avisarTodos");

    private static final String MODO_SOLO_AVISAR_A = MiEntelProperties
            .getProperty("adminServicios.avisale.tipoLista.soloAvisarA");    
    
    /**
     * indica modo 'avisar todos' o 'solo avisar a'
     */
    private String modoAvisale = MODO_AVISAR_TODOS;
    
    /**
     * comma-separated con los numeros 
     */
    private String listaNumeros;
    
    private String numero;
    
    public AvisaleController() {
    }

    /**
     * @return the modoAvisale
     */
    public String getModoAvisale() {
        return modoAvisale;
    }

    /**
     * @param modoAvisale the modoAvisale to set
     */
    public void setModoAvisale(String modoAvisale) {
        this.modoAvisale = modoAvisale;
    }

    /**
     * @return the listaNumeros
     */
    public String getListaNumeros() {
        return listaNumeros;
    }

    /**
     * @param listaNumeros the listaNumeros to set
     */
    public void setListaNumeros(String listaNumeros) {
        this.listaNumeros = listaNumeros;
    }
    
    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * 
     * @return valor del campo hidden lista_numero_avisar_todos
     */
    private String getFieldNumerosAvisarTodos() {
        return JsfUtil.getRequestParameter("lista_numero_avisar_todos");
    }
    
    /**
     * 
     * @return valor del campo hidden lista_numero_avisar_a
     */
    private String getFieldNumerosSoloAvisarA() {
        return JsfUtil.getRequestParameter("lista_numero_avisar_a");
    }

    /**
     * Action method para activar avisale en modo 'avisar todos'
     * @return null
     */
    public String activarAvisarTodos() {
        return this.activarAvisale(MODO_AVISAR_TODOS);
    }
    
    /**
     * Action method para activar avisale en modo 'solo avisar a'
     * @return null
     */
    public String activarSoloAvisarA() {
        return this.activarAvisale(MODO_SOLO_AVISAR_A);
    }
    
    /**
     * metodo 'common' para activar Avisale
     * 
     * @return null
     */
    private String activarAvisale(String modoAvisale) {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;
            
            List<String> listaNumeros = null;
            if(MODO_AVISAR_TODOS.equals(modoAvisale)) {
                listaNumeros = getListaNumerosAvisarTodos();
            }
            else if(MODO_SOLO_AVISAR_A.equals(modoAvisale)) {
                listaNumeros = getListaNumerosSoloAvisarA();
                
                if(listaNumeros.isEmpty()) {
                    LOGGER.error("Lista de numeros no puede ser vacia para avisale modo 'solo avisar a'");
                    JSFMessagesHelper.addErrorMessage("adminServicios", "avisaleListaVacia");
                    return null;
                }
            }
            else {
                LOGGER.error( new Exception("modoAvisale no identificado: " + modoAvisale));
            }
            
            resultado = this.activacion(numeroPcsSeleccionado, modoAvisale, listaNumeros);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "activacionAvisale", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al activar servicio", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionAvisale");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionAvisale");
        }

        return null;
    }

    /**
     * Action method para desactivar servicio Avisale
     * 
     * @return null
     */
    public String desactivarAvisale() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.desactivacion(numeroPcsSeleccionado);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "desactivacionAvisale", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionAvisale");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionAvisale");
        }

        return null;
    }
    
    /**
     * Action method para la actualizacion de configuracion de avisale, opcion Avisar Todos
     * @return
     */
    public String guardarConfiguracionAvisarTodos() {
        
        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            List<String> listaNumeros = getListaNumerosAvisarTodos();
            resultado = this.actualizacion(numeroPcsSeleccionado, MODO_AVISAR_TODOS, listaNumeros);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "actualizarAvisale", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "actualizarAvisale");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "actualizarAvisale");
        }

        return null;
    }
    
    /**
     * 
     * @return List con los numeros prsentes en el campo 'lista_numero_avisar_todos'
     */
    private List<String> getListaNumerosAvisarTodos() {
        
        List<String> listaNumeros = new ArrayList<String>();
        String numeros = getFieldNumerosAvisarTodos();

        if (Utils.isNotEmptyString(numeros)) {
            
            StringTokenizer tokenizer = new StringTokenizer(numeros, ",");
            
            while (tokenizer.hasMoreTokens()) {
            
                String token = tokenizer.nextToken();
                
                if (Utils.isNotEmptyString(token)) {
                    listaNumeros.add(token);
                }
            }
        }
        
        return listaNumeros;
        
    }

    /**
     * Action method para la actualizacion de configuracion de avisale, opcion Solo avisar a
     * @return
     */
    public String guardarConfiguracionSoloAvisarA() {
        
        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");

            ResultadoServicioBean resultado;

            List<String> listaNumeros = getListaNumerosSoloAvisarA();
            
            if(listaNumeros.isEmpty()) {
                LOGGER.error("Lista de numeros no puede ser vacia para avisale modo 'solo avisar a'");
                JSFMessagesHelper.addErrorMessage("adminServicios", "avisaleListaVacia");
                return null;
            }
            
            resultado = this.actualizacion(numeroPcsSeleccionado, MODO_SOLO_AVISAR_A, listaNumeros);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "actualizarAvisale", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "actualizarAvisale");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "actualizarAvisale");
        }

        return null;
    }

    /**
     * 
     * @return List con los numeros presentes en el campo 'lista_numero_avisar_a'
     */
    private List<String> getListaNumerosSoloAvisarA() {
        
        List<String> listaNumeros = new ArrayList<String>();
        String numeros = getFieldNumerosSoloAvisarA();

        if (Utils.isNotEmptyString(numeros)) {
            
            StringTokenizer tokenizer = new StringTokenizer(numeros, ",");
            
            while (tokenizer.hasMoreTokens()) {
            
                String token = tokenizer.nextToken();
                
                if (Utils.isNotEmptyString(token)) {
                    listaNumeros.add(token);
                }
            }
        }
        
        return listaNumeros;
        
    }
    
    private ResultadoServicioBean activacion(String numeroPcsSeleccionado,
            String tipoLista, List<String> listaNumeros) throws DAOException,
            ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.activarAvisale(numeroPcsSeleccionado, tipoLista,
                listaNumeros);
    }

    private ResultadoServicioBean desactivacion(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.desactivarAvisale(numeroPcsSeleccionado);
    }

    private ResultadoServicioBean actualizacion(String numeroPcsSeleccionado,
            String tipoLista, List<String> listaNumeros) throws DAOException,
            ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.actualizarAvisale(numeroPcsSeleccionado, tipoLista,
                listaNumeros);
    }
    
    /**
     * modo avisar todos accessor
     * @return
     */
    public String getAvisarTodos() {
        return MODO_AVISAR_TODOS;
    }
    
    /**
     * modo solo avisar a accessor
     * @return
     */
    public String getSoloAvisarA() {
        return MODO_SOLO_AVISAR_A;
    }

}
