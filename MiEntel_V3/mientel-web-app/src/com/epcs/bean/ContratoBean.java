/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author dvelez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ContratoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String rut;
	private String numeroDocumento;
	private String nombreDocumento;
	private String fechaDocumento;
	private String estadoFirma;
	private String url;
	private String numeroNegocio;
	private String descripcionFirma;
	private String movil;	
	
	
	/**
	 * @return the movil
	 */
	public String getMovil() {
		return movil;
	}
	/**
	 * @param movil the movil to set
	 */
	public void setMovil(String movil) {
		this.movil = movil;
	}
	/**
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}
	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}
	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return the nombreDocumento
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	/**
	 * @param nombreDocumento the nombreDocumento to set
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	/**
	 * @return the fechaDocumento
	 */
	public String getFechaDocumento() {
		return fechaDocumento;
	}
	/**
	 * @param fechaDocumento the fechaDocumento to set
	 */
	public void setFechaDocumento(String fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
	/**
	 * @return the estadoFirma
	 */
	public String getEstadoFirma() {
		return estadoFirma;
	}
	/**
	 * @param estadoFirma the estadoFirma to set
	 */
	public void setEstadoFirma(String estadoFirma) {
		this.estadoFirma = estadoFirma;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the numeroNegocion
	 */
	public String getNumeroNegocio() {
		return numeroNegocio;
	}
	/**
	 * @param numeroNegocion the numeroNegocion to set
	 */
	public void setNumeroNegocio(String numeroNegocio) {
		this.numeroNegocio = numeroNegocio;
	}
	/**
	 * @return the descripcionFirma
	 */
	public String getDescripcionFirma() {
		return descripcionFirma;
	}
	/**
	 * @param descripcionFirma the descripcionFirma to set
	 */
	public void setDescripcionFirma(String descripcionFirma) {
		this.descripcionFirma = descripcionFirma;
	}
	
}
