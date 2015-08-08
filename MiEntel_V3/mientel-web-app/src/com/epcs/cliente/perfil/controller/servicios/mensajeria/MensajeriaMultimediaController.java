package com.epcs.cliente.perfil.controller.servicios.mensajeria;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResultadoServicioBean;
import com.epcs.bean.ResumenPlan;
import com.epcs.cliente.perfil.delegate.AdministracionServiciosDelegate;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

public class MensajeriaMultimediaController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para MensajeriaMultimediaController
     */
    private static final Logger LOGGER = Logger
            .getLogger(MensajeriaMultimediaController.class);
    
    private PlanDelegate planDelegate;

    public MensajeriaMultimediaController() {
    }

    /**
     * Action method para activar MMS
     * 
     * @return null
     */
    public String activarMMS() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");
            
            String mercado = ProfileWrapperHelper
    				.getPropertyAsString(profile, "mercado");
            

            String aaa = ProfileWrapperHelper
    				.getPropertyAsString(profile, "aaa");

            ResultadoServicioBean resultado = null;

            if (MiEntelBusinessHelper.isMercadoPrepago(mercado) || MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)) {
            	ResumenPlan resumenPlan = new ResumenPlan();
            	
            	try{
            		if(MiEntelBusinessHelper.isMercadoPrepago(mercado)){
            			resumenPlan = planDelegate.getPlanResumenPP(numeroPcsSeleccionado);
                	}else {
                		resumenPlan = planDelegate.getPlanResumenCC(numeroPcsSeleccionado,aaa);
                	}
            	}catch (Exception e) {}
            	
            	if(resumenPlan.getSaldo() < 7){
            		LOGGER.error( new ServiceException("0099", ""));
            	}else {
            		if(MiEntelBusinessHelper.isMercadoPrepago(mercado)){
                		resultado = this.activacion(numeroPcsSeleccionado);
                	}else {
                		resultado = this.activacionInetMovilBAM(numeroPcsSeleccionado);
                	}
            	}
            } else {
            	resultado = this.activacionMMSBAM(numeroPcsSeleccionado);
            	
            }

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "activacionMMS", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al activar servicio", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionMMS");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionMMS");
        }

        return null;
    }

    /**
     * Action method para desactivar MMS
     * 
     * @return null
     */
    public String desactivarMMS() {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");
            
            String mercado = ProfileWrapperHelper
            		.getPropertyAsString(profile, "mercado");

            ResultadoServicioBean resultado;
            
            if (MiEntelBusinessHelper.isMercadoPrepago(mercado)) {
            	resultado = this.desactivacion(numeroPcsSeleccionado);
            	
            } else if(MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)) {
            	resultado = this.desactivacionInetMovilBAM(numeroPcsSeleccionado);
            	
            } else {
            	resultado = this.desactivacionMMSBAM(numeroPcsSeleccionado);
            	
            }
            
            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "desactivacionMMS", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionMMS");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionMMS");
        }

        return null;
    }

    //TODO
    private ResultadoServicioBean activacion(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.activarMMSGPRS(numeroPcsSeleccionado);
    }
    
    private ResultadoServicioBean activacionInetMovilBAM(String numeroPcsSeleccionado)
		    throws DAOException, ServiceException {
		AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
		return delegate.activarInternetMovilBAM(numeroPcsSeleccionado);
	}
    
    private ResultadoServicioBean activacionMMSBAM(String numeroPcsSeleccionado)
		    throws DAOException, ServiceException {
		AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
		return delegate.activarMMSBAM(numeroPcsSeleccionado);
	}

    //TODO
    private ResultadoServicioBean desactivacion(String numeroPcsSeleccionado)
            throws DAOException, ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        return delegate.desactivarMMSGPRS(numeroPcsSeleccionado);
    }
    
    private ResultadoServicioBean desactivacionInetMovilBAM(String numeroPcsSeleccionado)
		    throws DAOException, ServiceException {
		AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
		return delegate.desactivarInternetMovilBAM(numeroPcsSeleccionado);
	}
    
    private ResultadoServicioBean desactivacionMMSBAM(String numeroPcsSeleccionado)
		    throws DAOException, ServiceException {
		AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
		return delegate.desactivarMMSBAM(numeroPcsSeleccionado);
	}

	/**
	 * @return the planDelegate
	 */
	public PlanDelegate getPlanDelegate() {
		return planDelegate;
	}

	/**
	 * @param planDelegate the planDelegate to set
	 */
	public void setPlanDelegate(PlanDelegate planDelegate) {
		this.planDelegate = planDelegate;
	}

}
