/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.orden.controller;

import java.io.Serializable;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.billing.balance.delegate.FacturacionDelegate;
import com.epcs.cliente.orden.delegate.ReposicionServicioDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ReposicionServicioController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger
            .getLogger(ReposicionServicioController.class);
    private ReposicionServicioDelegate reposicionServicioDelegate;
    private FacturacionDelegate facturacionDelegate;
    private static final String SERVICE_NAME = "clienteOrden";
   
    /**
     * @param facturacionDelegate the facturacionDelegate to set
     */
    public void setFacturacionDelegate(FacturacionDelegate facturacionDelegate) {
        this.facturacionDelegate = facturacionDelegate;
    }

    private String estadoReposicion = "";
    private String estadoFacturacion = "";
    private String facturaPagada = null; 

    /**
     * @return the facturaPagada
     */
    public String getFacturaPagada() {
        if(facturaPagada == null){
            facturaPagada = MiEntelProperties.getProperty("facturasImpagas.pagado");
        }
        return facturaPagada;
    }

    /**
     * @param facturaPagada the facturaPagada to set
     */
    public void setFacturaPagada(String facturaPagada) {
        this.facturaPagada = facturaPagada;
    }

    /**
     * @return the estadoFacturacion
     */
    public String getEstadoFacturacion() {
        return estadoFacturacion;
    }

    /**
     * @param estadoFacturacion the estadoFacturacion to set
     */
    public void setEstadoFacturacion(String estadoFacturacion) {
        this.estadoFacturacion = estadoFacturacion;
    }

    /**
     * @return the estadoReposicion
     */
    public String getEstadoReposicion() {
        return estadoReposicion;
    }

    /**
     * @param estadoReposicion
     *            the estadoReposicion to set
     */
    public void setEstadoReposicion(String estadoReposicion) {
        this.estadoReposicion = estadoReposicion;
    }

    /**
     * @param reposicionServicioDelegate
     *            the reposicionServicioDelegate to set
     */
    public void setReposicionServicioDelegate(
            ReposicionServicioDelegate reposicionServicioDelegate) {
        this.reposicionServicioDelegate = reposicionServicioDelegate;
    }

    public void init(PhaseEvent phase) {
        String numeroPcs = "";
        String estadoReposicion;
        String estadoFacturacion;

        try {
                LOGGER.info("phase " + phase.getPhaseId());
               
                ProfileWrapper profile = ProfileWrapperHelper
                        .getProfile(JSFPortletHelper.getRequest());
                numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,
                        "numeroPcsSeleccionado");
                estadoReposicion = this.reposicionServicioDelegate
                        .consultarEstadoReposicion(numeroPcs);
                this.setEstadoReposicion(estadoReposicion);
                
                estadoFacturacion = this.facturacionDelegate.estadoFacturasImpagas(numeroPcs);
                this.setEstadoFacturacion(estadoFacturacion);
               
                
        } catch (DAOException e) {
            LOGGER.error("DAOException al consultar estado Reposicion", e);
            JSFMessagesHelper.addErrorCodeMessage(SERVICE_NAME, "0001");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al consultar estado Reposicion. msisdn: "+numeroPcs);
            JSFMessagesHelper.addErrorCodeMessage(SERVICE_NAME, e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al consultar estado Reposicion", e);
            JSFMessagesHelper.addErrorCodeMessage(SERVICE_NAME, "0001");
        }

    }
    
    /**
     * Reliza la reposocion del servicio a el Cliente.
     * @return 
     */
    public String reponerServicio() {
    	String numeroPcs = "";
    	try {
            LOGGER.info("reponer Servicio ");
            ProfileWrapper profile = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            
            numeroPcs = ProfileWrapperHelper.getPropertyAsString(
                    profile, "numeroPcsSeleccionado");
            String estadoFacturas = JsfUtil.getRequestParameter("estadoFacturas") != null ? JsfUtil.getRequestParameter("estadoFacturas") : "";

            this.reposicionServicioDelegate.reponerServicio(numeroPcs, estadoFacturas);
            
//            String msg = MiEntelProperties.getServiceMessages().getRb().getString("success.clienteOrden.reponerServicio");
//            JSFMessagesHelper.addSuccessMessage(msg);
            JSFMessagesHelper.addServiceSuccessMessage("clienteOrden", "reponerServicio");
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al inespeado estado Reposicion", e);
            JSFMessagesHelper.addErrorCodeMessage(SERVICE_NAME, "0001");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al consultar estado Reposicion. msisdn: "+numeroPcs);
            JSFMessagesHelper.addErrorCodeMessage(SERVICE_NAME, e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado estado Reposicion", e);
            JSFMessagesHelper.addErrorCodeMessage(SERVICE_NAME, "0001");
        }

        return "result";
    }
    
    
    public String getUrlPagoCuenta() {

        String pageLabel = "";

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String mercado = ProfileWrapperHelper.getPropertyAsString(profile,
                    "mercado");

            if (MiEntelProperties.getProperty(
                    "parametros.plan.suscripcion.sigla").equals(mercado)) {
                pageLabel = "ss_page_pagoEnLinea_iter2";
            }
            else if (MiEntelProperties.getProperty(
                    "parametros.plan.cuentacontrolada.sigla").equals(mercado)) {
                pageLabel = "cc_page_pagoEnLinea_iter2";
            }
        } catch (Exception e) {
        	LOGGER.error("Exception caught: "+e.getMessage(), e);
        }

        return JSFPortletHelper.createUrlPage(pageLabel);
    }
    
    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del mercado como clave asociada al valor.
     * @return
     */
    public String getPageLabelPagoEnLinea(){
    	try{
    	  ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
    		
    	  return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "mercado"), null);
		 
        }catch(Exception e){
        	LOGGER.error("No se ha podido obtener el pageLabel"+ e.getMessage(), e);
        	return "";
        }
    
    }

}
