/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.delegate;

import java.io.Serializable;
import java.sql.SQLException;

import com.epcs.cliente.perfil.bean.Oferta;
import com.epcs.cliente.perfil.dao.OfertaBlindajeDAO;
import com.epcs.cliente.perfil.dao.OfertaVisaDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class OfertaVisaDelegate implements Serializable {

	private static final long serialVersionUID = 1L;

	private OfertaVisaDAO ofertaVisaDAO;
	public static final int TIPO_OFERTA_VISAENTEL = 1;

	public OfertaVisaDelegate() {
		super();
		ofertaVisaDAO = new OfertaVisaDAO();
	}

	/**
	 * @return the OfertaBlindajeDAO
	 */
	public OfertaVisaDAO getOfertaVisaDAO() {
		return ofertaVisaDAO;
	}

	/**
	 * @param OfertaBlindajeDAO
	 *            ofertaBlindajeDAO to set
	 */
	public void setOfertaVisaDAO(OfertaVisaDAO ofertaVisaDAO) {
		this.ofertaVisaDAO = ofertaVisaDAO;
	}

	/**
	 * Marca la oferta entel visa, para no volver a mostrarla al cliente
	 * 
	 * @param codigo
	 * @param rechazado
	 * @throws SQLException
	 */
	public void marcarOfertaVisa(long codigo, boolean rechazado)
			throws Exception {
		this.ofertaVisaDAO.marcarOfertaVisa(codigo, rechazado);
	}

	/**
	 * Marca la oferta entel visa con la fecha en la que fue visitada
	 * 
	 * @param codigo
	 * @throws SQLException
	 */
	public void actualizarVisitaVisaEntel(long codigo) throws Exception {
		ofertaVisaDAO.actualizarVisitaVisaEntel(codigo);
	}

	/**
	 * Consultar la oferta visa para cargar los datos de esta en un objeto con
	 * atributos de la oferta.
	 * 
	 * @param movil
	 * @param rut
	 * @param estado
	 * @throws SQLException
	 */
	public Oferta getVisaEntelByMovilRutEstado(String movil, String rut,
			int estado) throws Exception {
		Oferta oferta = new Oferta();

		oferta = ofertaVisaDAO.getVisaEntelByMovilRutEstado(movil, rut, estado);

		if (oferta == null) {
			oferta = new Oferta();
			oferta.setTipo(TIPO_OFERTA_VISAENTEL);
			oferta.setVisitada(true);
			oferta.setRut(rut);
			oferta.setMovil(movil);
			oferta.setEstado(estado + "");
			oferta.setRechazada(false);
		} else {
			oferta.setTipo(TIPO_OFERTA_VISAENTEL);
			oferta.setVisitada(true);
		}

		return oferta;
	}

	/**
	 * Insertar una oferta visa.
	 * 
	 * @param movil
	 * @param rut
	 * @param estado
	 * @throws SQLException
	 */
	public void insertarVisitaVisaEntel(String movil, String rut, int estado)
			throws Exception {
		ofertaVisaDAO.insertarVisitaVisaEntel(movil, rut, estado);
	}

	public int consultarAlternancia(String rut, String movil, String ofertas)
			throws Exception {
		return ofertaVisaDAO.consultarAlternancia(rut, movil, ofertas);
	}

	/**
	 * Obtiene estado oferta visa
	 * 
	 * @param msisdn
	 * @param rut
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public String obtenerEstadoVisa(int msisdn, String rut)
			throws DAOException, ServiceException {
		return ofertaVisaDAO.obtenerEstadoVisa(msisdn, rut);
	}

	/**
	 * Metodo de tipo boolean que determina si un cliente es preferencial.
	 * 
	 * @throws DAOException
	 * @throws ServiceException
	 * 
	 */
	public boolean isClientePreferencial(String rut, String numeroCuenta)
			throws DAOException, ServiceException {
		return ofertaVisaDAO.isClientePreferencial(rut, numeroCuenta);
	}

}
