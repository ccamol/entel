package com.epcs.historial.bolsas.delegate;

import java.util.List;

import com.epcs.bean.DetalleMiTraficoSSBean;
import com.epcs.bean.PlanBean;
import com.epcs.bean.ResumenTraficoBean;
import com.epcs.bean.TraficoEnLineaBean;
import com.epcs.bean.TraficoEnLineaPPCCBean;
import com.epcs.billing.balance.dao.FacturacionDAO;
import com.epcs.billing.registrouso.dao.TraficoDAO;
import com.epcs.cliente.perfil.dao.PlanDAO;
import com.epcs.dashboard.mensajes.dao.ConsultarDetalleLlamadosDAO;
import com.epcs.historial.bolsas.dao.HistorialDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.esa.billing.recargas.registrobolsas.types.BolsaSMSType;

/**
 * @author jlopez (Tecnova)
 * 
 */

public class HistorialDelegate {
	

	private HistorialDAO historialDAO;
	
	
	 public HistorialDelegate() {
		   historialDAO = new HistorialDAO();
	    }
	
	public List<TraficoEnLineaPPCCBean> getTraficoEnLineaPPCC(String numeroPcs , String pagina)
    throws DAOException, ServiceException {
    return this.historialDAO.getTraficoEnLineaPPCC(numeroPcs, pagina);
}
	public List<BolsaSMSType> getHistorialBolsas(String numeroPcs, String mercado)
    throws DAOException, ServiceException {
    return this.historialDAO.getHistorialBolsas(numeroPcs, mercado);
}


	public HistorialDAO getHistorialDAO() {
		return historialDAO;
	}


	public void setHistorialDAO(HistorialDAO historialDAO) {
		this.historialDAO = historialDAO;
	}

}
