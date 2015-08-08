/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.ishop.fullmessenger.controller;

import java.math.BigInteger;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.ishop.fullmessenger.delegate.FullMessengerDelegate;
import com.epcs.recursoti.configuracion.CifradoRSA;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 * 
 */
public class FullMessengerController {
    

    /**
     * Logger para FullMessengerController
     */
    private static final Logger LOGGER = Logger
            .getLogger(FullMessengerController.class);
    
    private static final long serialVersionUID = 1L;
    private FullMessengerDelegate fullMessengerDelegate;
    private String numeroPcsEncriptado;
    private String claveEncriptada;
    
    
    /**
     * @return the fullMessengerDelegate
     */
    public FullMessengerDelegate getFullMessengerDelegate() {
        return fullMessengerDelegate;
    }

    /**
     * @param fullMessengerDelegate the fullMessengerDelegate to set
     */
    public void setFullMessengerDelegate(FullMessengerDelegate fullMessengerDelegate) {
        this.fullMessengerDelegate = fullMessengerDelegate;
    }

    /**
     * @return the numeroPcsEncriptado
     */
    public String getNumeroPcsEncriptado() {
        return numeroPcsEncriptado;
    }

    /**
     * @param numeroPcsEncriptado the numeroPcsEncriptado to set
     */
    public void setNumeroPcsEncriptado(String numeroPcsEncriptado) {
        this.numeroPcsEncriptado = numeroPcsEncriptado;
    }

    /**
     * @return the claveEncriptada
     */
    public String getClaveEncriptada() {
        return claveEncriptada;
    }

    /**
     * @param claveEncriptada the claveEncriptada to set
     */
    public void setClaveEncriptada(String claveEncriptada) {
        this.claveEncriptada = claveEncriptada;
    }

    /**
     * Inicializa el numero y la clave de usuario a usar para acceder al servicio de FullMessenger.
     * @param phase
     */
    public void initParamsFullMessenger(PhaseEvent phase) {
        try {
            LOGGER.info("phase " + phase.getPhaseId());
            //Obtenemos datos necesarios para consulta
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            String numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado");
            //Invocamos el metodo de accion que obtiene la clave del usuario a traves del numeroPcs
            String clave = obtenerClaveByNumeroPcs(numeroPcs);
            
            BigInteger[] llavePublicaRSA = {new BigInteger(MiEntelProperties.getProperty("cifradoRSA.params.bothKeys.n"),16),
                                            new BigInteger(MiEntelProperties.getProperty("cifradoRSA.params.llavePublica.e"),16)};
            BigInteger[] llavePrivadaRSA = {new BigInteger(MiEntelProperties.getProperty("cifradoRSA.params.bothKeys.n"),16),
                                            new BigInteger(MiEntelProperties.getProperty("cifradoRSA.params.llavePrivada.d"),16)};
            
            CifradoRSA.setLlavePrivada(llavePrivadaRSA);
            CifradoRSA.setLlavePublica(llavePublicaRSA);
            
            numeroPcsEncriptado = CifradoRSA.encripta(numeroPcs);
            claveEncriptada = CifradoRSA.encripta(clave);
            
            LOGGER.info("movil: "+numeroPcs);
            LOGGER.info("clave: "+clave);

        } catch (Exception e) {
            LOGGER.error("Error inesperado al inicializar parametros para acceder al servicio de FullMessenger", e);
            JSFMessagesHelper.addServiceErrorMessage("initParamsFullMessenger");
        }

    }
    
    /**
     * Action method que obtiene la clave de usuario empleando el numeroPcs
     * @param numeroPcs
     * @return claveUsuario <code>String</code> clave del usuario del movil <code>numeroPcs</code>
     */
    private String obtenerClaveByNumeroPcs(String numeroPcs){
        String clave = "";
        try{
            clave = fullMessengerDelegate.obtenerClaveByNumeroPcs(numeroPcs);
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al obtener clave para el movil: " + numeroPcs, e);
            JSFMessagesHelper.addServiceErrorMessage("obtenerClaveByNumeroPcs");
        } catch (ServiceException e) {
            LOGGER.error("ServiceException al obtener clave para el movil " + numeroPcs + ": " + e.getDescripcionRespuesta(), e);
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al al obtener clave para el movil: " + numeroPcs, e);
            JSFMessagesHelper.addServiceErrorMessage("obtenerClaveByNumeroPcs");
        }
        return clave;
        
    }
    

}
