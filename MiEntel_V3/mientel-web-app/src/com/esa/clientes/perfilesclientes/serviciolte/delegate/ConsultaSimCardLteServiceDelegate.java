package com.esa.clientes.perfilesclientes.serviciolte.delegate;

import com.epcs.bean.SimCard4GLteBean;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.esa.clientes.perfilesclientes.serviciolte.dao.ConsultaSimCardLteServiceDao;

public class ConsultaSimCardLteServiceDelegate {
	
	private ConsultaSimCardLteServiceDao simCardDao;
	
	

	public ConsultaSimCardLteServiceDelegate() {
		simCardDao = new ConsultaSimCardLteServiceDao();
	}
	
	public ConsultaSimCardLteServiceDao getSimCardDao() {
		return simCardDao;
	}

	public void setSimCardDao(ConsultaSimCardLteServiceDao simCardDao) {
		this.simCardDao = simCardDao;
	}

	/**
	 * Obtiene el resultado de la tarjeta Sim si esta esta habilitada para LTE. 
	 * @param msisdns
	 * @param imsi
	 * @param iccid
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public SimCard4GLteBean ConsultaSimCardLteService(
			String imsi , String msisdns, String iccid) throws DAOException,
			ServiceException {
		
		SimCard4GLteBean bean = simCardDao.ConsultaSimCardLteService(imsi,msisdns, iccid); 
		
		return bean;
	}
	
	

}
