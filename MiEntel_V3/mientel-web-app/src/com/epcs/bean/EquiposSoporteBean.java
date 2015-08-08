/**
 * 
 */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author Harold
 *
 */
public class EquiposSoporteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String imei;
	String fechaNegocio;
	String marca;
	String codigoGeneraOT; 
	String mensajeGeneraOT;
	String mesAntiguedad;
	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * @return the mesAntiguedad
	 */
	public String getMesAntiguedad() {
		return mesAntiguedad;
	}
	/**
	 * @param mesAntiguedad the mesAntiguedad to set
	 */
	public void setMesAntiguedad(String mesAntiguedad) {
		this.mesAntiguedad = mesAntiguedad;
	}
	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**
	 * @return the fechaNegocio
	 */
	public String getFechaNegocio() {
		return fechaNegocio;
	}
	/**
	 * @param fechaNegocio the fechaNegocio to set
	 */
	public void setFechaNegocio(String fechaNegocio) {
		this.fechaNegocio = fechaNegocio;
	}
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return the codigoGeneraOT
	 */
	public String getCodigoGeneraOT() {
		return codigoGeneraOT;
	}
	/**
	 * @param codigoGeneraOT the codigoGeneraOT to set
	 */
	public void setCodigoGeneraOT(String codigoGeneraOT) {
		this.codigoGeneraOT = codigoGeneraOT;
	}
	/**
	 * @return the mensajeGeneraOT
	 */
	public String getMensajeGeneraOT() {
		return mensajeGeneraOT;
	}
	/**
	 * @param mensajeGeneraOT the mensajeGeneraOT to set
	 */
	public void setMensajeGeneraOT(String mensajeGeneraOT) {
		this.mensajeGeneraOT = mensajeGeneraOT;
	}
}
