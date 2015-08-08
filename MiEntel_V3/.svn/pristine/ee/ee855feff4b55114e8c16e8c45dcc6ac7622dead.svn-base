/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.epcs.cliente.perfil.bean.Oferta;
import com.epcs.cliente.perfil.bean.CriteriosBusquedaOferta;

/**
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class OfertaBlindajeDAO implements Serializable {
	 
	 private static final long serialVersionUID = 1L;

	private OfertaBlindajeEntelDAO ofertaBlindajeEntelDAO;

	public OfertaBlindajeDAO() {
		ofertaBlindajeEntelDAO = new OfertaBlindajeEntelDAO();
	}

	/**
	 * @return the getOfertaBlindaEntelDAO
	 */
	public OfertaBlindajeEntelDAO getOfertaBlindaEntelDAO() {
		return ofertaBlindajeEntelDAO;
	}

	/**
	 * @return the ofertaVisaEntelDAO
	 */
	public void setOfertaBlindaEntelDAO(
			OfertaBlindajeEntelDAO ofertaBlindajeEntelDAO) {
		this.ofertaBlindajeEntelDAO = ofertaBlindajeEntelDAO;
	}

	/**
	 * Consultar la oferta Blindaje para cargar los datos de esta en un objeto
	 * con atributos de la oferta.
	 * 
	 * @param codigo
	 * @param rechazado
	 * @throws SQLException
	 */
	public List<Oferta> ConsultarOfertaBlindajeBanner(String movil) {
		return ofertaBlindajeEntelDAO.ConsultarOfertaBlindajeBanner(movil);
	}
	
	/**
	 * Consultar la oferta Blindaje para cargar los datos de esta en un objeto
	 * con atributos de la oferta.
	 * 
	 * @param codigo
	 * @param rechazado
	 * @throws SQLException
	 */
	public List<Oferta> ConsultarOfertaBlindaje(String movil) {
		return ofertaBlindajeEntelDAO.ConsultarOfertaBlindaje(movil);
	}
	
	/**
	 * Consultar la oferta Blindaje segun criterios de la campana promocional del mes, 
	 * para cargar los datos de esta en un objeto con atributos de la oferta.
	 * 
	 * @param criterios objeto que contiene los criterios de busqueda de la campana promocional del mes
	 * @throws SQLException
	 */
	public List<Oferta> ConsultarOfertaBlindajeCriterios(CriteriosBusquedaOferta criterios) {
		return ofertaBlindajeEntelDAO.ConsultarOfertaByCriterios(criterios);
	}

	/**
	 * Update la oferta Blindaje marcar las visitas
	 * 
	 * @param codigo
	 * @param rechazado
	 * @throws SQLException
	 */
	public void actualizarVisitaOferta(long ofertaId, boolean status)
			throws Exception {
		ofertaBlindajeEntelDAO.actualizarVisitaOferta(ofertaId, status);
	}
	/**
	 * Consultar la oferta NBA para cargar los datos de esta en un objeto
	 * con atributos de la oferta.
	 * 
	 * @param movil
	 * @throws SQLException
	 */
	public List<Oferta> ConsultarOfertaNBA(String movil) {
		return ofertaBlindajeEntelDAO.ConsultarOfertaNBA(movil);
	}
	
	/**
	 * Consultar la oferta NBA para cargar los datos de esta en un objeto
	 * con atributos de la oferta.
	 * 
	 * @param movil
	 * @throws SQLException
	 */
public List<Oferta> ConsultarOfertaNBAByGroup(String movil, String grupo) {
	return ofertaBlindajeEntelDAO.ConsultarOfertaNBAByGroup(movil, grupo);
}	

	/**
	 * @param codBscsOferta
	 * @return String[]
	 */
	public String[] ConsultarOfertaPlanNBA(String codBscsOferta) {
		return ofertaBlindajeEntelDAO.ConsultarOfertaPlanNBA(codBscsOferta);
	}

	public String[] ConsultarOfertaBolsaNBA(String codBscsOferta) {
		return ofertaBlindajeEntelDAO.ConsultarOfertaBolsaNBA(codBscsOferta);
	}

	public int minimizarBanner(String movil) {
		return ofertaBlindajeEntelDAO.minimizarBanner(movil);
	}

	public void actualizarMinimizarBanner(String movil, int rechazo) {
		ofertaBlindajeEntelDAO.actualizarMinimizarBanner(movil,rechazo);		
	}

	public int obtenerMaximoMinimizarBanner(String movil) {
		return ofertaBlindajeEntelDAO.obtenerMaximoMinimizarBanner(movil);
	}
	
	public void rechazaOfertaBlindaje(long codOferta){
		ofertaBlindajeEntelDAO.rechazaOfertaBlindaje(codOferta);
	}
	
	public boolean tieneOfertasPorMovil(String movil) {
		return ofertaBlindajeEntelDAO.tieneOfertasPorMovil(movil);		
	}
		
	public String obtenerPlanOriginalNumero(String codiOferta, String grupoOferta) {
    	return ofertaBlindajeEntelDAO.obtenerPlanOriginalNumero(codiOferta, grupoOferta);
    }
	
	public List<Oferta> consultarOferta4GLTE(String movil) {
		return ofertaBlindajeEntelDAO.consultarOferta4GLTE(movil);
	}

}
