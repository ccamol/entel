/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.inteligencianegocio.satisfaccioncliente.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.inteligencianegocio.satisfaccioncliente.delegate.EncuestasPerfiladasDelegate;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class EncuestasPerfiladasController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger
            .getLogger(EncuestasPerfiladasController.class);
    private static final String SESSION_URL_ENCUESTAS = MiEntelProperties
            .getProperty("encuestasPerfiladas.session.urlEncuestas");
    private static final String SESSION_MOSTRAR_ENCUESTAS = MiEntelProperties
            .getProperty("encuestasPerfiladas.session.mostrarEncuestas");       
    
    private static HashMap<String, String> codigosZonas = new HashMap<String, String>() {{
													    	   put(MiEntelBusinessHelper.getSiglaPrepago(), MiEntelProperties
													    			    .getProperty("encuestasPerfiladas.codZona.pp"));
													    	   put(MiEntelBusinessHelper.getSiglaCuentaControlada(), MiEntelProperties
													    			    .getProperty("encuestasPerfiladas.codZona.cc"));
													    	   put(MiEntelBusinessHelper.getSiglaSuscripcion(), MiEntelProperties
													    			    .getProperty("encuestasPerfiladas.codZona.ss"));
													    	}};
    
    private transient EncuestasPerfiladasDelegate encuestasDelegate;
    private Boolean mostrarEncuestas = false;
    private List<String> urls;
    private String urlEncuesta;
    private String numeroPcs;
    private String rutTitular;
    private String ipCliente;
    private String codMercado;
    private String codZona;

    public void init(PhaseEvent event) {
        try {            
            if (!validarParametrosSesion()) {
                this.consultarEncuestasPerfiladas();
            }
        } catch (DAOException de) {
            LOGGER.error("DAOException al buscar encuestas perfiladas", de);
        } catch (ServiceException se) {
            LOGGER.info("ServiceException al buscar encuestas perfiladas");
        } catch (Exception e) {
            LOGGER.error("Exception al buscar encuestas perfiladas", e);
        }
    }
    
    /**
     * @return the encuestasDelegate
     */
    public EncuestasPerfiladasDelegate getEncuestasDelegate() {
        return encuestasDelegate;
    }

    /**
     * @param encuestasDelegate the encuestasDelegate to set
     */
    public void setEncuestasDelegate(EncuestasPerfiladasDelegate encuestasDelegate) {
        this.encuestasDelegate = encuestasDelegate;
    }

    /**
     * @return the mostrarEncuestas
     */
    public boolean isMostrarEncuestas() {
        return mostrarEncuestas;
    }

    /**
     * @return the urlEncuesta
     */
    public String getUrlEncuesta() {
        return urlEncuesta;
    }

    /**
     * @param urlEncuesta the urlEncuesta to set
     */
    public void setUrlEncuesta(String urlEncuesta) {
        this.urlEncuesta = urlEncuesta;
    }
    
    /**
     * Verifica los valores de los parametros de sesion:
     * 
     * com.epcs.inteligencianegocio.satisfaccioncliente.mostrarEncuestas
     * com.epcs.inteligencianegocio.satisfaccioncliente.urls
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean validarParametrosSesion() {
        HttpSession session = JSFPortletHelper.getSession();
        
        if (session.getAttribute(SESSION_MOSTRAR_ENCUESTAS) != null) {
            mostrarEncuestas = (Boolean) session.getAttribute(SESSION_MOSTRAR_ENCUESTAS);
            if (mostrarEncuestas == Boolean.TRUE) {
                urls = (List<String>) session.getAttribute(SESSION_URL_ENCUESTAS);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que recibe la respuesta del metodo consultarEncuestasPerfiladasOrq
     * del servicio y asigna el valor del atributo
     * com.epcs.inteligencianegocio.satisfaccioncliente.urlEncuestas y a las
     * variables urls y urlEncuesta
     * 
     * @throws DAOException
     * @throws ServiceException
     * @throws Exception
     */
    private void consultarEncuestasPerfiladas() throws DAOException,
            ServiceException, Exception {
        HttpServletRequest request = JSFPortletHelper.getRequest();
        HttpSession session = JSFPortletHelper.getSession();

        ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(request);
        numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
                "numeroPcs");
        codMercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
        		"mercado");
        rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
        		MiEntelBusinessHelper.isMercadoPrepago(codMercado)
        			? "rutUsuarioSeleccionado" : "rutTitular");
        ipCliente = request.getRemoteAddr();
        
        codZona = codigosZonas.get(codMercado);
        
        urls = this.encuestasDelegate.consultarEncuestasPerfiladasOrq(
                numeroPcs, rutTitular, ipCliente, codMercado, codZona);        

        if (urls != null && !urls.isEmpty()) {
            session.setAttribute(SESSION_URL_ENCUESTAS, urls);
            session.setAttribute(SESSION_MOSTRAR_ENCUESTAS, true);
            mostrarEncuestas = true;
            urlEncuesta = urls.get(0);
        } else {
            session.setAttribute(SESSION_MOSTRAR_ENCUESTAS, false);
        }
    }
    
    /**
     * Accion ejecutada al momento de hacer click en el link "Responder ahora"
     * del portlet, eliminando de la sesion los atributos: -
     * com.epcs.inteligencianegocio.satisfaccioncliente.mostrarEncuestas -
     * com.epcs.inteligencianegocio.satisfaccioncliente.urls
     */
    public void accionResponderAhora(PhaseEvent event) {
        HttpSession session = JSFPortletHelper.getSession();
        session.removeAttribute(SESSION_MOSTRAR_ENCUESTAS);
        session.removeAttribute(SESSION_URL_ENCUESTAS);
        mostrarEncuestas = false;
        urls = null;
        urlEncuesta = null;
    }    

    /**
     * Accion ejecutada al momento de hacer click en el link "No responder" del
     * portlet
     */
    public void accionNoResponder(PhaseEvent event) {
        ocultarEncuesta();
    }
    
    /**
     * Accion ejecutada al momento de hacer click en el link "No por ahora" del
     * portlet
     */
    public void accionNoPorAhora(PhaseEvent event) {
        ocultarEncuesta();
    }
    
    /**
     * Asigna los siguientes valores a los parametros de sesion:
     * 
     * com.epcs.inteligencianegocio.satisfaccioncliente.mostrarEncuestas - false
     * com.epcs.inteligencianegocio.satisfaccioncliente.urls - null
     * 
     * Esto con el fin de que la encuesta no se despliegue mientras este la
     * sesion iniciada.
     */
    private void ocultarEncuesta() {
        HttpSession session = JSFPortletHelper.getSession();
        session.setAttribute(SESSION_MOSTRAR_ENCUESTAS, false);
        session.setAttribute(SESSION_URL_ENCUESTAS, null);
        mostrarEncuestas = false;
        urls = null;
        urlEncuesta = null;
    }
}