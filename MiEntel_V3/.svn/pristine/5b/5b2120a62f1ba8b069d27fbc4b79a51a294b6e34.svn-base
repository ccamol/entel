package com.epcs.administracion.suscripciones.delegate;

import java.util.List;

import com.epcs.administracion.suscripciones.dao.PlanesSeguridadBAMDAO;
import com.epcs.bean.CredencialesPlanSeguridadBean;
import com.epcs.bean.PlanSeguridadBAMBean;
import com.epcs.bean.ResultadoCanjeDePuntosBean;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class PlanesSeguridadBAMDelegate {

	private PlanesSeguridadBAMDAO planesSeguridadBAMDAO;

	/**
	 * @return the planesSeguridadBAMDAO
	 */
	public PlanesSeguridadBAMDAO getPlanesSeguridadBAMDAO() {
		return planesSeguridadBAMDAO;
	}

	/**
	 * @param planesSeguridadBAMDAO the planesSeguridadBAMDAO to set
	 */
	public void setPlanesSeguridadBAMDAO(PlanesSeguridadBAMDAO planesSeguridadBAMDAO) {
		this.planesSeguridadBAMDAO = planesSeguridadBAMDAO;
	}
	
	/**
	 * Metodo que consulta el plan actual de seguridad
	 * @param msisdn
	 * @return {@link ResultadoCanjeDePuntosBean} plan actual de seguridad
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public PlanSeguridadBAMBean consultarPlanActualSeguridad (String msisdn) 
			throws DAOException, ServiceException {
		return planesSeguridadBAMDAO.consultarPlanActualSeguridad(msisdn);
	}
	
	/**
	 * Metodo que obtiene los planes de seguridad disponibles para el mercado PP
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<PlanSeguridadBAMBean> obtenerPlanesSeguridadDispobiblesPP() 
			throws DAOException, ServiceException {
		return planesSeguridadBAMDAO.obtenerPlanesSeguridadDispobiblesPP();
	}
	
	/**
     * Obtiene los planes de seguridad disponibles para CC y SS
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<PlanSeguridadBAMBean> obtenerPlanesSeguridadDispobiblesCCSS(String msisdn) 
			throws DAOException, ServiceException {
    	return planesSeguridadBAMDAO.obtenerPlanesSeguridadDispobiblesCCSS(msisdn);
    }
	
	/**
     * Metodo que recupera las credenciales del plan de seguridad activo para un usuario
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public CredencialesPlanSeguridadBean consultarCredencialesPlanSeguridad(String msisdn) 
			throws DAOException, ServiceException {
    	return planesSeguridadBAMDAO.consultarCredencialesPlanSeguridad(msisdn);
    }
    
    /**
     * Valida si el usuario ya tiene activado el servicio o si esta intentando 
     * activar el servicio de prueba mas de una vez.
     * @param msisdn
     * @param mail
     * @param codigoPlan
     * @param valorPlan
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public boolean validarPlanSeguridad(
    		String msisdn, String mail, String codigoPlan, String valorPlan)
			throws DAOException, ServiceException {
    	return planesSeguridadBAMDAO.validarPlanSeguridad(msisdn, mail, codigoPlan, valorPlan);
    }
    
    /**
     * Activa el plan seleccionado por el usuario
     * @param msisdn
     * @param tipoMovil
     * @param mail
     * @param codigoPlan
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public boolean activarPlanSeguridad(
    		String msisdn, String tipoMovil, String mail, String codigoPlan)
			throws DAOException, ServiceException {
    	return planesSeguridadBAMDAO.activarPlanSeguridad(msisdn, tipoMovil, mail, codigoPlan);
    }
    
    /**
     * Desactiva el plan seleccionado por el usuario
     * @param msisdn
     * @param tipoMovil
     * @param mail
     * @param codigoPlan
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public boolean desactivarPlanSeguridad(
    		String msisdn, String tipoMovil, String mail, String codigoPlan)
			throws DAOException, ServiceException {
    	return planesSeguridadBAMDAO.desactivarPlanSeguridad(msisdn, tipoMovil, mail, codigoPlan);
    }
	
}
