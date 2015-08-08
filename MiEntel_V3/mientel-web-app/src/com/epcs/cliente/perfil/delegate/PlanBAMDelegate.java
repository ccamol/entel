/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.delegate;

import java.util.List;

import com.epcs.bean.PlanBAMBean;
import com.epcs.bean.PlanResumenBAMPPBean;
import com.epcs.cliente.perfil.dao.PlanBAMDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class PlanBAMDelegate {

    // private static final Logger LOGGER =
    // Logger.getLogger(PlanDelegate.class);
    private PlanBAMDAO planBAMDAO;

    /**
     * Obtiene la informacion acerca del plan actual del usuario BAM SS y CC.
     * 
     * @param numeroPcs
     * @param mercado
     * @param atributoAAA
     * @return PlanBAMBean
     * @throws DAOException
     * @throws ServiceException
     */
    public PlanBAMBean obtenerPlanActualBAMSSCC(String numeroPcs,
            String mercado, String atributoAAA, boolean logCajaFacturacion) throws DAOException,
            ServiceException {
        return this.planBAMDAO.obtenerPlanActualBAMSSCC(numeroPcs, mercado,
                atributoAAA, logCajaFacturacion);
    }

    /**
     * Obtiene la informacion acerca del plan contratado del usuario BAM PP
     * 
     * @param numeroPcs
     * @return PlanResumenBAMPPBean
     * @throws DAOException
     * @throws ServiceException
     */
    public PlanResumenBAMPPBean obtenerPlanResumenBAMPP(String numeroPcs) throws DAOException,ServiceException {
        return this.planBAMDAO.obtenerPlanResumenBAMPP(numeroPcs);
    }


    /**
     * Obtiene los Planes Disponibles para un usuario SS o CC
     * 
     * @param numeroPcs
     * @param mercado
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<PlanBAMBean> obtenerPlanesDisponiblesBAM(String msisdn) throws DAOException,
            ServiceException {
        return this.planBAMDAO.obtenerPlanesDisponiblesBAM(msisdn);
    }
    
    /**
     * Realiza el cambio de plan BAM SSCC
     * 
     * @param numeroPcsSeleccionado
     * @param codigoNuevoPlan
     * @throws DAOException
     * @throws ServiceException
     */
    public void cambiarPlanBAMSSCC(String numeroPcsSeleccionado, String codigoNuevoPlan) throws DAOException,
    ServiceException {
        this.planBAMDAO.cambiarPlanBAMSSCC(numeroPcsSeleccionado, codigoNuevoPlan);
    }
    
    /**
     * Realiza la validacion de Bloqueo Temporal mercado SS o CC
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public void validacionBloqueoTemporal(String msisdn) throws DAOException,
    ServiceException {
        this.planBAMDAO.validacionBloqueoTemporal(msisdn);
    }
    
    /**
     * Realiza la validacion Comercial mercado SS o CC
     * 
     * @param msisdn
     * @param codbscs2
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public void validacionComercial(String msisdn, String codbscs2) throws DAOException,
    ServiceException {
        this.planBAMDAO.validacionComercial(msisdn, codbscs2);
    }

    /**
     * @param planBAMDAO
     *            the planBAMDAO to set
     */
    public void setPlanBAMDAO(PlanBAMDAO planBAMDAO) {
        this.planBAMDAO = planBAMDAO;
    }

    /**
     * @return the planBAMDAO
     */
    public PlanBAMDAO getPlanBAMDAO() {
        return planBAMDAO;
    }

}
