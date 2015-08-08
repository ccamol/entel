/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.delegate;

import com.epcs.bean.PlanBAMBean;
import com.epcs.bean.ResumenTraficoBAMCCBean;
import com.epcs.billing.registrouso.dao.TraficoBamDAO;
import com.epcs.cliente.perfil.dao.PlanBAMDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class TraficoBamCCDelegate {
    
    private TraficoBamDAO traficoBamDAO;
    private PlanBAMDAO planBAMDAO;
    /**
     * @param planBAMDAO the planBAMDAO to set
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
    /**
     * @param traficoBamDAO the traficoBamDAO to set
     */
    public void setTraficoBamDAO(TraficoBamDAO traficoBamDAO) {
        this.traficoBamDAO = traficoBamDAO;
    }
    /**
     * @return the traficoBamDAO
     */
    public TraficoBamDAO getTraficoBamDAO() {
        return traficoBamDAO;
    }
    
    
    /**
     * 
     * @param numeroPcs
     * @param autoAtencion
     * @param mercado
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ResumenTraficoBAMCCBean consultarTraficoBAMCC(String numeroPcs, boolean logCajaTrafico) throws DAOException, ServiceException {
        return this.traficoBamDAO.consultarTraficoBAMCC(numeroPcs, logCajaTrafico);    
    }
    
    /**
     * Obtiene la informacion acerca del plan actual del usuario SS y CC.
     * 
     * @param numeroPcs
     * @param mercado
     * @param atributoAAA
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public PlanBAMBean obtenerPlanActualBAMSSCC(String numeroPcs, String mercado, String atributoAAA, boolean logCajaFacturacion) throws DAOException,
    ServiceException {
        return this.planBAMDAO.obtenerPlanActualBAMSSCC(numeroPcs, mercado, atributoAAA, logCajaFacturacion);
    }
    
}
