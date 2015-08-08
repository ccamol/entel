package com.epcs.cliente.orden.delegate;

import com.epcs.bean.DetalleConsultaSaldoSGOBean;
import com.epcs.cliente.orden.dao.ConsultaSaldoSGODAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ConsultaSaldoSGODelegate {
	
	private ConsultaSaldoSGODAO consultaSaldoSGODAO;
	
	public DetalleConsultaSaldoSGOBean consultarSGO(String numeroPcs)throws DAOException, ServiceException{
		return consultaSaldoSGODAO.consultarSGO(numeroPcs);
	}

	public ConsultaSaldoSGODAO getConsultaSaldoSGODAO() {
		return consultaSaldoSGODAO;
	}

	public void setConsultaSaldoSGODAO(ConsultaSaldoSGODAO consultaSaldoSGODAO) {
		this.consultaSaldoSGODAO = consultaSaldoSGODAO;
	}
	
}
