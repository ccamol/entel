/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.clientes.perfilesclientes.ejecutivo.delegate;

import com.epcs.bean.EjecutivoVIPBean;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.esa.clientes.perfilesclientes.ejecutivo.dao.EjecutivoVIPDAO;

/**
 * @author zmanotas (I2B)
 */
public class EjecutivoVIPDelegate {

	private EjecutivoVIPDAO ejecutivoVIPDAO;
	
	/**
	 * 
	 * @param msisdn
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
    public EjecutivoVIPBean obtenerDatosEjecutivo(String msisdn)
			throws DAOException, ServiceException {
    	return ejecutivoVIPDAO.obtenerDatosEjecutivo(msisdn);
    }

	public EjecutivoVIPDAO getEjecutivoVIPDAO() {
		return ejecutivoVIPDAO;
	}

	public void setEjecutivoVIPDAO(EjecutivoVIPDAO ejecutivoVIPDAO) {
		this.ejecutivoVIPDAO = ejecutivoVIPDAO;
	}

}
