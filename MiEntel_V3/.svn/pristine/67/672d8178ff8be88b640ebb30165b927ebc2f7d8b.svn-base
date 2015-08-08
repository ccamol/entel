package com.epcs.formularioreclamo.delegate;

import java.util.List;

import com.epcs.bean.CodeDescBean;
import com.epcs.bean.DetalleReclamoBean;
import com.epcs.bean.PaginaHistorialReclamosBean;
import com.epcs.bean.ReclamoBean;
import com.epcs.billing.balance.types.DocumentoFacturaFullType;
import com.epcs.formularioreclamo.dao.FormularioReclamoDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class FormularioReclamoDelegate {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The formulario reclamo dao. */
	private FormularioReclamoDAO formularioReclamoDAO;

	/**
	 * Sets the formulario reclamo dao.
	 * 
	 * @param formularioReclamoDAO
	 *            the new formulario reclamo dao
	 */
	public void setFormularioReclamoDAO(
			FormularioReclamoDAO formularioReclamoDAO) {
		this.formularioReclamoDAO = formularioReclamoDAO;
	}

	/**
	 * Gets the formulario reclamo dao.
	 * 
	 * @return the formulario reclamo dao
	 */
	public FormularioReclamoDAO getFormularioReclamoDAO() {
		return formularioReclamoDAO;
	}

	/**
	 * Instantiates a new formulario reclamo delegate.
	 */
	public FormularioReclamoDelegate() {
		formularioReclamoDAO = new FormularioReclamoDAO();
	}

	/**
	 * Ingresa un reclamo.
	 *
	 * @param reclamo the reclamo
	 * @return the string
	 * @throws ServiceException the service exception
	 * @throws DAOException the DAO exception
	 */
	public String ingresarReclamo(ReclamoBean reclamo) throws ServiceException,
			DAOException {
		return formularioReclamoDAO.ingresarReclamo(reclamo);
	}

	/**
	 * Metodo para obtener el historial de reclamos de un cliente.
	 * 
	 * @param rutCliente
	 *            the rut cliente
	 * @return the historial reclamos
	 * @throws ServiceException
	 *             the service exception
	 * @throws DAOException
	 *             the dAO exception
	 */
	public List<PaginaHistorialReclamosBean> getHistorialReclamos(
			String rutCliente) throws ServiceException, DAOException {
		return formularioReclamoDAO.getHistorialReclamos(rutCliente);
	}

	/**
	 * Metodo que permite la consulta del destalle de un reclamo.
	 * 
	 * @param nroReclamo
	 *            the nro reclamo
	 * @return the detalle reclamo bean
	 * @throws ServiceException
	 *             the service exception
	 * @throws DAOException
	 *             the dAO exception
	 */
	public DetalleReclamoBean consultarDetalleReclamo(String nroReclamo)
			throws ServiceException, DAOException {
		return formularioReclamoDAO.consultarDetalleReclamo(nroReclamo);
	}
	

	/**
	 * Metodo que permite la consulta del monto cobrado de un documento
	 * (factura) indicado
	 * 
	 * @param numeroReclamado
	 * @return List<DocumentoFacturaFullType>
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<DocumentoFacturaFullType> consultarDocumentoReclamado(
			String msisdn, String rutTitular) throws DAOException,
			ServiceException {
		return formularioReclamoDAO.consultarDocumentoReclamado(msisdn,
				rutTitular);
	}

}