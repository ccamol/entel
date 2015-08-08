/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.recarga.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.RecargaHistoricoBean;
import com.epcs.billing.recarga.delegate.RecargaDelegate;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * Controller para el portlet de Historico de Recargas
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class RecargaHistoricoController implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para RecargaHistoricoController
     */
    private static final Logger LOGGER = Logger
            .getLogger(RecargaHistoricoController.class);

    /**
     * Delegado de negocio
     */
    private RecargaDelegate recargaDelegate;

    private RecargaHistoricoBean recargaHistoricoBean;

    private boolean existeHistoricoRecargas = false;
    
    private String msisdn;

    public RecargaDelegate getRecargaDelegate() {
        return recargaDelegate;
    }

    public void setRecargaDelegate(RecargaDelegate recargaDelegate) {
        this.recargaDelegate = recargaDelegate;
    }

    /**
     * @return the recargaHistoricoBean
     */
    public RecargaHistoricoBean getRecargaHistoricoBean() {
        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            msisdn = ProfileWrapperHelper.getPropertyAsString(profile,
                    "numeroPcsSeleccionado");
            recargaHistoricoBean = recargaDelegate.getHistoricoRecargas(msisdn);

            existeHistoricoRecargas = (recargaHistoricoBean != null && !recargaHistoricoBean
                    .getDetalleRecargas().isEmpty());
        } catch (DAOException e) {
        	LOGGER.error("DAOException  al cargar historico de recargas :"
                    + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("historicoRecargas");
        } catch (ServiceException e) {        	           
            
            LOGGER.info("ServiceException caught: " + msisdn + " - "
            			+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
            
            JSFMessagesHelper.addErrorCodeMessage("recarga", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado en cargar historico de recargas :"
                            + e.getMessage(), e);
            JSFMessagesHelper.addServiceErrorMessage("historicoRecargas");
        }

        return recargaHistoricoBean;

    }

    /**
     * @param recargaHistoricoBean
     *            the recargaHistoricoBean to set
     */
    public void setRecargaHistoricoBean(
            RecargaHistoricoBean recargaHistoricoBean) {
        this.recargaHistoricoBean = recargaHistoricoBean;
    }

    /**
     * 
     * @return
     */
    public boolean isExisteHistoricoRecargas() {
        return existeHistoricoRecargas;
    }

    /**
     * 
     * @param existeHistoricoRecargas
     */
    public void setExisteHistoricoRecargas(boolean existeHistoricoRecargas) {
        this.existeHistoricoRecargas = existeHistoricoRecargas;
    }

}
