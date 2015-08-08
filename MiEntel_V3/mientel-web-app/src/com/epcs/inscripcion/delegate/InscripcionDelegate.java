/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.inscripcion.delegate;

import java.util.Date;

import com.epcs.bean.DatosUsuarioTitularBean;
import com.epcs.bean.MercadoBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.cliente.perfil.dao.CuentaDAO;
import com.epcs.inscripcion.dao.InscripcionDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class InscripcionDelegate {

	private InscripcionDAO inscripcionDAO;
	private CuentaDAO cuentaDAO;
	

	/**
	 * 
	 */
    public InscripcionDelegate() {
		super();
		inscripcionDAO = new InscripcionDAO();
		cuentaDAO = new CuentaDAO();
	}


    /**
     * 
     * @return
     */
	public InscripcionDAO getInscripcionDAO() {
		return inscripcionDAO;
	}


	/**
	 * 
	 * @param inscripcionDAO
	 */
	public void setInscripcionDAO(InscripcionDAO inscripcionDAO) {
		this.inscripcionDAO = inscripcionDAO;
	}


	/**
     * 
     * @param numeroPcs
     * @throws DAOException
     * @throws ServiceException
     */
    public String obtenerMercadoMovil(String numeroPcs) throws DAOException, ServiceException {
        return this.cuentaDAO.obtenerMercadoMovil(numeroPcs);
    }
    
	/**
	 * 
	 * @param numeroPcs
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
    public String obtenerRutTitular(String numeroPcs) throws DAOException, ServiceException {
        return this.cuentaDAO.obtenerRutTitular(numeroPcs);
    }
    
    /**
	 * 
	 * @param numeroPcs
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
    public DatosUsuarioTitularBean obtenerDatosTitular(String numeroPcs) throws DAOException, ServiceException {
        return this.cuentaDAO.obtenerDatosTitular(numeroPcs);
    }
    
    

    /**
     * 
     * @param numeroPcs
     * @throws DAOException
     * @throws ServiceException
     */
    public void validarMovilBuic(String numeroPcs) throws DAOException, ServiceException {
    	this.inscripcionDAO.validarMovilBuic(numeroPcs);
    }

    /**
     * 
     * @param usuarioBean
     * @throws DAOException
     * @throws ServiceException
     */
    public void actualizarDatos(UsuarioBean usuarioBean) throws DAOException,
    ServiceException {
    	this.inscripcionDAO.actualizarDatos(usuarioBean);
    }
    

    /**
     * 
     * @param numeroPcs
     * @param rut
     * @throws DAOException
     * @throws ServiceException
     */
    public Date validarAsociacionMovilRutBuic(String numeroPcs, String rut) throws DAOException, ServiceException {
    	return this.inscripcionDAO.validarAsociacionMovilRutBuic(numeroPcs, rut);
    }
    
    /**
     * 
     * @param numeroPcs
     * @throws DAOException
     * @throws ServiceException
     */
    public MercadoBean obtenerMercado(String numeroPcs) throws DAOException, ServiceException {
    	return this.inscripcionDAO.obtenerMercado(numeroPcs);
    }

}
