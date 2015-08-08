/**
 * 
 */
package com.epcs.cliente.perfil.delegate;

import java.io.Serializable;
import java.util.ArrayList;

import com.epcs.bean.ConsultaSintomaBean;
import com.epcs.bean.EquiposSoporteBean;
import com.epcs.bean.ObtenerBodegasVigentesBean;
import com.epcs.bean.ObtenerEquiposSoporteBean;
import com.epcs.bean.PreIngresoOTBean;
import com.epcs.bean.SintomaBean;
import com.epcs.bean.ValidaPreOTBean;
import com.epcs.cliente.perfil.dao.PreIngresoOTDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author I2B, haltamiranda
 *
 */
public class PreIngresoOTDelegate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PreIngresoOTDAO preIngresoOTDAO;	

    /**
	 * @return the preIngresoOTDAO
	 */
	public PreIngresoOTDAO getPreIngresoOTDAO() {
		return preIngresoOTDAO;
	}

	/**
	 * @param preIngresoOTDAO the preIngresoOTDAO to set
	 */
	public void setPreIngresoOTDAO(PreIngresoOTDAO preIngresoOTDAO) {
		this.preIngresoOTDAO = preIngresoOTDAO;
	}
	
	public ConsultaSintomaBean consultarSintomas()
	    throws DAOException, ServiceException {
		return this.preIngresoOTDAO.consultarSintomas();
	}
	
	public ValidaPreOTBean validaPreOT(String movil, String serie)
	    throws DAOException, ServiceException {
		return this.preIngresoOTDAO.validaPreOT(movil,serie);
	}
	
	public PreIngresoOTBean preIngresoOT(String movil,String serie,String fonoContacto,String mailContacto,String comentario,ArrayList<SintomaBean> sintoma)
	    throws DAOException, ServiceException {
		return this.preIngresoOTDAO.preIngresoOT(movil,serie,fonoContacto,mailContacto,comentario,sintoma);
	}
	
	public ObtenerBodegasVigentesBean obtenerBodegasVigentesBean(String rutAgente)
	    throws DAOException, ServiceException {
		return this.preIngresoOTDAO.obtenerBodegasVigentes(rutAgente);
	}
	
	public ObtenerEquiposSoporteBean obtenerEquiposSoporte(String Dv,String Email,String Msisdn,String RutSinDv)
	    throws DAOException, ServiceException {
		return this.preIngresoOTDAO.obtenerEquiposSoporte(Dv,Email,Msisdn,RutSinDv);
	}	
	
	public EquiposSoporteBean consultaDescripcionMovil(String imei)
    throws DAOException, ServiceException {
	return this.preIngresoOTDAO.consultaDescripcionMovil(imei);
}
	
    /**
     * 
     * @param from
     * @param to
     * @param message
     * @param subject
     * @throws DAOException
     * @throws ServiceException
     */
	public void enviarMail(String from, String to, String message, String subject) throws DAOException,
			ServiceException {
		this.preIngresoOTDAO.enviarMail(from, to, message, subject);
	}
}
