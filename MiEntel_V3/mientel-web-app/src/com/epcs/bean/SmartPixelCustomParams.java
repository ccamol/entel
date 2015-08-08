/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class SmartPixelCustomParams {
	
	/*
	 * Fecha de renovacion de equipo, con formato MM/YYYY
	 */
	private String fechaRenovacion;
	
	/*
	 * Posibles valores: 'Titular' o 'No titular'
	 */
	private String atributo;
	
	/*
	 * Indica el segmento del usuario.
	 * Posibles valores: 'Persona', 'Empresa', 'Corporaciones'
	 */
	private String segmento;
	
	private String mercado;
	private int cupoLineas;
	
	/*
	 * Indica si el usuario tiene o no el sevicio de facturacion electronica.
	 * Posibles valores: 'Si' o 'No'
	 */
	private String facturaElectronica;
	
	/*
	 * Indica si el usuario tiene o no el sevicio de internet movil.
	 * Posibles valores: 'Si' o 'No'
	 */
	private String internetMovil;
	
	/*
	 * Lista bolsas contratadas por el usuario.
	 */ 
	private String bolsasContratadas;

	/**
	 * @return the fechaRenovacion
	 */
	public String getFechaRenovacion() {
		return fechaRenovacion;
	}

	/**
	 * @param fechaRenovacion the fechaRenovacion to set
	 */
	public void setFechaRenovacion(String fechaRenovacion) {
		this.fechaRenovacion = fechaRenovacion;
	}

	/**
	 * @return the atributo
	 */
	public String getAtributo() {
		return atributo;
	}

	/**
	 * @param atributo the atributo to set
	 */
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	/**
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * @return the mercado
	 */
	public String getMercado() {
		return mercado;
	}

	/**
	 * @param mercado the mercado to set
	 */
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	/**
	 * @return the cupoLineas
	 */
	public int getCupoLineas() {
		return cupoLineas;
	}

	/**
	 * @param cupoLineas the cupoLineas to set
	 */
	public void setCupoLineas(int cupoLineas) {
		this.cupoLineas = cupoLineas;
	}

	/**
	 * @return the facturaElectronica
	 */
	public String getFacturaElectronica() {
		return facturaElectronica;
	}

	/**
	 * @param facturaElectronica the facturaElectronica to set
	 */
	public void setFacturaElectronica(String facturaElectronica) {
		this.facturaElectronica = facturaElectronica;
	}

	/**
	 * @return the internetMovil
	 */
	public String getInternetMovil() {
		return internetMovil;
	}

	/**
	 * @param internetMovil the internetMovil to set
	 */
	public void setInternetMovil(String internetMovil) {
		this.internetMovil = internetMovil;
	}

	/**
	 * @return the bolsasContratadas
	 */
	public String getBolsasContratadas() {
		return bolsasContratadas;
	}

	/**
	 * @param bolsasContratadas the bolsasContratadas to set
	 */
	public void setBolsasContratadas(String bolsasContratadas) {
		this.bolsasContratadas = bolsasContratadas;
	}

}