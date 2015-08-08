/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.inteligencianegocio.satisfaccioncliente.delegate;

import java.util.List;

import com.epcs.inteligencianegocio.satisfaccioncliente.dao.EncuestasPerfiladasDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/** 
 * @author zmanotas (I2B) 
 */
public class EncuestasPerfiladasDelegate {
	
	private EncuestasPerfiladasDAO encuestasDAO;
	
	public EncuestasPerfiladasDAO getEncuestasDAO() {
		return encuestasDAO;
	}

	public void setEncuestasDAO(EncuestasPerfiladasDAO encuestasDAO) {
		this.encuestasDAO = encuestasDAO;
	}

	public List<String> consultarEncuestasPerfiladasOrq(String numero, String rut, String ipCliente, String codMercado, String codZona) throws DAOException, ServiceException {
		return this.encuestasDAO.consultarEncuestasPerfiladasOrq(numero, rut, ipCliente, codMercado, codZona);
	}

}
