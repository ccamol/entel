/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.delegate;

import java.util.List;

import com.epcs.bean.AlertaCuotaTraficoBean;
import com.epcs.bean.DetalleMiTraficoSSBean;
import com.epcs.bean.PlanBean;
import com.epcs.bean.ResumenTraficoBean;
import com.epcs.bean.TraficoEnLineaBean;
import com.epcs.bean.TraficoEnLineaPPCCBean;
import com.epcs.billing.balance.dao.FacturacionDAO;
import com.epcs.billing.registrouso.dao.TraficoDAO;
import com.epcs.cliente.perfil.dao.PlanDAO;
import com.epcs.dashboard.mensajes.dao.ConsultarDetalleLlamadosDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoDelegate {

//    private static final Logger LOGGER = Logger
//            .getLogger(TraficoDelegate.class);

	
    private TraficoDAO traficoDAO;
    
    private PlanDAO planDAO;


   public TraficoDelegate() {
	   traficoDAO = new TraficoDAO();
    }

    /**
     * @return the traficoDAO
     */
    public TraficoDAO getTraficoDAO() {
        return traficoDAO;
    }

    /**
     * @param traficoDAO
     *            the traficoDAO to set
     */
    public void setTraficoDAO(TraficoDAO traficoDAO) {
        this.traficoDAO = traficoDAO;
    }
    
    public PlanDAO getPlanDAO() {
		return planDAO;
	}

	public void setPlanDAO(PlanDAO planDAO) {
		this.planDAO = planDAO;
	}

	public ResumenTraficoBean getResumenTrafico(String numeroPcs,
            String autoAtencion) throws Exception {
        return this.traficoDAO.getResumenTrafico(numeroPcs, autoAtencion);
    }

    /**
     * Devuelve el listado de los 5 ultimos movimientos del usuario
     * 
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<TraficoEnLineaBean> getTraficoEnLinea(String numeroPcs)
            throws DAOException, ServiceException {
        return this.traficoDAO.getTraficoEnLinea(numeroPcs);
    }
    

    /**
     * Devuelve el listado de los movimientos del usuario
     * 
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<TraficoEnLineaPPCCBean> getTraficoEnLineaPPCC(String numeroPcs , String pagina)
            throws DAOException, ServiceException {
        return this.traficoDAO.getTraficoEnLineaPPCC(numeroPcs, pagina);
    }

    
    /**
     * 
     * @param numeroPcs 
     * @param mercado 
     * @param atributoAAA 
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
	public PlanBean obtenerPlan(String numeroPcs, String mercado,
			String atributoAAA) throws DAOException, ServiceException {
		return this.planDAO.obtenerPlanActualSSCC(numeroPcs, mercado,
				atributoAAA);
	}
    
    
    /**
     * 
     * @param numeroPcs
     * @param autoAtencion
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
	public List<DetalleMiTraficoSSBean> obtenerMiTrafico(String numeroPcs,
			String periodo, String mercado, boolean logCajaTrafico) throws DAOException,
			ServiceException {
		return this.traficoDAO.obtenerMiTrafico(numeroPcs, periodo, mercado, logCajaTrafico);
	}
	
	 /**
	 * Devuelve historia de alertas de cuota de trafico
     * @author Wilson Brochero Munoz
     * @param  event    
     * @return List<AlertaCuotaTraficoBean>
     * @throws Exception
     * @throws ServiceException
     * @throws DAOException
     */
    public List<AlertaCuotaTraficoBean> getHistoricoAlertaCuotaTrafico(String numeroPcs )
            throws DAOException, ServiceException {
        return this.traficoDAO.getHistoricoAlertaCuotaTrafico(numeroPcs);
    
    }

}
