/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.delegate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epcs.cliente.perfil.bean.Oferta;
import com.epcs.cliente.perfil.bean.CriteriosBusquedaOferta;
import com.epcs.cliente.perfil.dao.CuentaDAO;
import com.epcs.cliente.perfil.dao.OfertaBlindajeDAO;
import com.epcs.cliente.perfil.dao.HistorialPrepagoPlusDAO;
import com.epcs.cliente.perfil.util.PlanHelper;


/**
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class OfertaBlindajeDelegate implements Serializable {

	private static final long serialVersionUID = 1L;

    private OfertaBlindajeDAO ofertaBlindajeDAO;
    private HistorialPrepagoPlusDAO historialPPPlusDAO;

    
    public OfertaBlindajeDelegate() {
        super();
        ofertaBlindajeDAO = new OfertaBlindajeDAO();
        historialPPPlusDAO = new HistorialPrepagoPlusDAO();
    }
    
    /**
     * @return the OfertaBlindajeDAO
     */
    public OfertaBlindajeDAO getOfertaBlindajeDAO() {
        return ofertaBlindajeDAO;
    }

    /**
     * @param OfertaBlindajeDAO ofertaBlindajeDAO to set
     */
    public void setOfertaBlindajeDAO(
    		OfertaBlindajeDAO ofertaBlindajeDAO) {
        this.ofertaBlindajeDAO = ofertaBlindajeDAO;
    }
	
	/**
     *  Consultar la oferta Blindaje para cargar los datos de esta en un objeto con atributos de la oferta.
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
    public List<Oferta>  ConsultarOfertaBlindaje(String movil) {
    	return ofertaBlindajeDAO.ConsultarOfertaBlindaje(movil);
    }
    
      /**
     *  Consultar la oferta Blindaje para cargar los datos de esta en un objeto con atributos de la oferta.
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
    public List<Oferta>  ConsultarOfertaBlindajeBanner(String movil) {
    	return ofertaBlindajeDAO.ConsultarOfertaBlindajeBanner(movil);
    }
    
	/**
     *  Consultar la oferta Blindaje para cargar los datos de esta en un objeto con atributos de la oferta.
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
    public List<Oferta>  ConsultarOfertaBlindajeCriterios(CriteriosBusquedaOferta criterios) {
    	return ofertaBlindajeDAO.ConsultarOfertaBlindajeCriterios(criterios);
    }
    
      /**
     *  Update la oferta Blindaje marcar las visitas
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
    public void actualizarVisitaOferta(long ofertaId, boolean status) throws Exception {
    	ofertaBlindajeDAO.actualizarVisitaOferta(ofertaId, status);
    }
    /**
     *  Consultar la oferta NBA para cargar los datos de esta en un objeto con atributos de la oferta.
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
	public List<Oferta> ConsultarOfertaNBA(String movil) {
		List<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas = ofertaBlindajeDAO.ConsultarOfertaNBA(movil);
		return PlanHelper.sortOfertasMain(ofertas);
	}
	/**
	 * 
	 * @param codBscsOferta
	 * @return
	 */
	public String[] ConsultarOfertaPlanNBA(String codBscsOferta) {
		return ofertaBlindajeDAO.ConsultarOfertaPlanNBA(codBscsOferta);
	}
	/**
	 * 
	 * @param codBscsOferta
	 * @return
	 */
	public String[] ConsultarOfertaBolsaNBA(String codBscsOferta) {
		return ofertaBlindajeDAO.ConsultarOfertaBolsaNBA(codBscsOferta);
	}

	public int minimizarBanner(String movil) {
		return ofertaBlindajeDAO.minimizarBanner(movil);
	}

	public void actualizarMinimizarBanner(String movil, int rechazo) {
		ofertaBlindajeDAO.actualizarMinimizarBanner(movil,rechazo);		
	}

	public int obtenerMaximoMinimizarBanner(String movil) {
		return ofertaBlindajeDAO.obtenerMaximoMinimizarBanner(movil);	
	}
	
    public void rechazaOfertaBlindajeNBA(long codOferta){
    	ofertaBlindajeDAO.rechazaOfertaBlindaje(codOferta);    	
    }
    
    public boolean tieneOfertasPorMovil(String movil){
    	return ofertaBlindajeDAO.tieneOfertasPorMovil(movil);    	
    }    
    public String obtenerPlanOriginalNumero(String codiOferta, String grupoOferta) {
    	return ofertaBlindajeDAO.obtenerPlanOriginalNumero(codiOferta, grupoOferta);
    }
    /**
     *  Consultar la oferta Full MM y Oferta de equipo para cargar los datos de esta en un objeto con atributos de la oferta.
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
	public List<Oferta> consultarOferta4GLTE(String movil) {
		List<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas = ofertaBlindajeDAO.consultarOferta4GLTE(movil);
		return PlanHelper.sortOfertasMain(ofertas);
	}
	
	/**
	 * Valida el despliegue del lightbox PP Plus para el movil indicado
	 * @param msisdn
	 */
	public boolean validarDespliegueLightboxPPPlus(String msisdn){
    	return historialPPPlusDAO.validarDespliegueLightbox(msisdn);
    }
	
	/**
	 * Registra la decision del cliente PP Plus de no volver a ver el lightbox de bienvenida
	 * @param msisdn
	 */
	public void rechazarDespliegueLightboxPPPlus(String msisdn){
    	historialPPPlusDAO.registrarRechazoDespliegueLightbox(msisdn);
    }
	
	/**
	 * Realiza borrado del historial de rechazo de despliegues del lightbox PP Plus
	 */
	public String[] borrarHistorialDespliegueLightbox(){
    	return historialPPPlusDAO.borrarHistorialDespliegueLightbox();
    }

	/**
	 * @return the historialPPPlusDAO
	 */
	public HistorialPrepagoPlusDAO getHistorialPPPlusDAO() {
		return historialPPPlusDAO;
	}

	/**
	 * @param historialPPPlusDAO the historialPPPlusDAO to set
	 */
	public void setHistorialPPPlusDAO(HistorialPrepagoPlusDAO historialPPPlusDAO) {
		this.historialPPPlusDAO = historialPPPlusDAO;
	}

}
