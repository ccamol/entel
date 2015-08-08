/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.erp.seguridad.delegate;

import java.io.Serializable;

import com.epcs.bean.UsuarioBean;
import com.epcs.erp.seguridad.dao.SeguridadDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class SeguridadDelegate implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    private SeguridadDAO seguridadDAO;

   /**
    *  
    */
    public SeguridadDelegate() {
        this.seguridadDAO = new SeguridadDAO();
    }
    
    
	/**
	 * 
	 * @return
	 */
    public SeguridadDAO getSeguridadDAO() {
		return seguridadDAO;
	}


	 /**
	  * 
	  * @param seguridadDAO
	  */
	public void setSeguridadDAO(SeguridadDAO seguridadDAO) {
		this.seguridadDAO = seguridadDAO;
	}



	/**
     * Cambiar clave de un usuario
     * 
     * @param usuario
     * @throws ServiceException
     * @throws DAOException
     */
    public void cambiarClave(UsuarioBean usuario) throws ServiceException,
            DAOException {
        this.seguridadDAO.cambiarClave(usuario);
    }
    
    /**
     *  Consultar IDP de un usuario segun el numero Pcs
     * @param numeroPcs
     * @return
     * @throws ServiceException
     * @throws DAOException
     */
    public String consultarIDP(String numeroPcs) throws ServiceException,
    DAOException {
    	return this.seguridadDAO.consultarIDP(numeroPcs);
    }
    

    /**
     * Autenticar al usuario segun su msisdn, rut y clave
     * 
     * @param msisdn
     * @param rut
     * @param clave
     * @return
     * @throws ServiceException
     * @throws DAOException
     */
    public void autenticarUsuario(String msisdn, String rut, String clave) throws ServiceException,
    DAOException {
    	this.seguridadDAO.autenticarUsuario(msisdn,rut,clave);
    }
    /**
     * Autenticar aplicacion segun su idp
     * 
     * @param idp
     * @return
     * @throws ServiceException
     * @throws DAOException
     */
    public String autenticarAplicacion(String idp) throws ServiceException,
    DAOException {
    	return this.seguridadDAO.autenticarAplicacion(idp);
    }    
}
