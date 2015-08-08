/**
 * 
 */
package com.epcs.bean;

import java.io.Serializable;


/**
 * @author jroman (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoEquiposBean  implements Serializable {
	
	private static final long serialVersionUID = 6078851483477219858L;
	
	private String numeroTelefono;
	
	private String imsi;
	
	private String imei;
	
	private String modalidad;
	
	private String fechaPrimerTrafico;
	
	private String fechaUltimoTrafico;
	
	private String celdaPrimerTrafico;
	
	private String nombreCeldaPrimerTrafico;
	
	private String direccionCeldaPrimerTrafico;
	
	private String comunaCeldaPrimerTrafico;
	
	private String regionCeldaPrimerTrafico;
	
	private String celdaUltimoTrafico;
	
	private String nombreCeldaUltimoTrafico;
	
	private String direccionCeldaUltimoTrafico;
	
	private String comunaCeldaUltimoTrafico;
    
	private String regionCeldaUltimoTrafico;
	
	private String codigoEquipo;
	
	private String descripcionEquipo;

	/**
	 * @return the numeroTelefono
	 */
	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	/**
	 * @param numeroTelefono the numeroTelefono to set
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}

	/**
	 * @param imsi the imsi to set
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * @param modalidad the modalidad to set
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	/**
	 * @return the fechaPrimerTrafico
	 */
	public String getFechaPrimerTrafico() {
		return fechaPrimerTrafico;
	}

	/**
	 * @param fechaPrimerTrafico the fechaPrimerTrafico to set
	 */
	public void setFechaPrimerTrafico(String fechaPrimerTrafico) {
		this.fechaPrimerTrafico = fechaPrimerTrafico;
	}

	/**
	 * @return the fechaUltimoTrafico
	 */
	public String getFechaUltimoTrafico() {
		return fechaUltimoTrafico;
	}

	/**
	 * @param fechaUltimoTrafico the fechaUltimoTrafico to set
	 */
	public void setFechaUltimoTrafico(String fechaUltimoTrafico) {
		this.fechaUltimoTrafico = fechaUltimoTrafico;
	}

	/**
	 * @return the celdaPrimerTrafico
	 */
	public String getCeldaPrimerTrafico() {
		return celdaPrimerTrafico;
	}

	/**
	 * @param celdaPrimerTrafico the celdaPrimerTrafico to set
	 */
	public void setCeldaPrimerTrafico(String celdaPrimerTrafico) {
		this.celdaPrimerTrafico = celdaPrimerTrafico;
	}

	/**
	 * @return the nombreCeldaPrimerTrafico
	 */
	public String getNombreCeldaPrimerTrafico() {
		return nombreCeldaPrimerTrafico;
	}

	/**
	 * @param nombreCeldaPrimerTrafico the nombreCeldaPrimerTrafico to set
	 */
	public void setNombreCeldaPrimerTrafico(String nombreCeldaPrimerTrafico) {
		this.nombreCeldaPrimerTrafico = nombreCeldaPrimerTrafico;
	}

	/**
	 * @return the direccionCeldaPrimerTrafico
	 */
	public String getDireccionCeldaPrimerTrafico() {
		return direccionCeldaPrimerTrafico;
	}

	/**
	 * @param direccionCeldaPrimerTrafico the direccionCeldaPrimerTrafico to set
	 */
	public void setDireccionCeldaPrimerTrafico(String direccionCeldaPrimerTrafico) {
		this.direccionCeldaPrimerTrafico = direccionCeldaPrimerTrafico;
	}

	/**
	 * @return the comunaCeldaPrimerTrafico
	 */
	public String getComunaCeldaPrimerTrafico() {
		return comunaCeldaPrimerTrafico;
	}

	/**
	 * @param comunaCeldaPrimerTrafico the comunaCeldaPrimerTrafico to set
	 */
	public void setComunaCeldaPrimerTrafico(String comunaCeldaPrimerTrafico) {
		this.comunaCeldaPrimerTrafico = comunaCeldaPrimerTrafico;
	}

	/**
	 * @return the regionCeldaPrimerTrafico
	 */
	public String getRegionCeldaPrimerTrafico() {
		return regionCeldaPrimerTrafico;
	}

	/**
	 * @param regionCeldaPrimerTrafico the regionCeldaPrimerTrafico to set
	 */
	public void setRegionCeldaPrimerTrafico(String regionCeldaPrimerTrafico) {
		this.regionCeldaPrimerTrafico = regionCeldaPrimerTrafico;
	}

	/**
	 * @return the celdaUltimoTrafico
	 */
	public String getCeldaUltimoTrafico() {
		return celdaUltimoTrafico;
	}

	/**
	 * @param celdaUltimoTrafico the celdaUltimoTrafico to set
	 */
	public void setCeldaUltimoTrafico(String celdaUltimoTrafico) {
		this.celdaUltimoTrafico = celdaUltimoTrafico;
	}

	/**
	 * @return the nombreCeldaUltimoTrafico
	 */
	public String getNombreCeldaUltimoTrafico() {
		return nombreCeldaUltimoTrafico;
	}

	/**
	 * @param nombreCeldaUltimoTrafico the nombreCeldaUltimoTrafico to set
	 */
	public void setNombreCeldaUltimoTrafico(String nombreCeldaUltimoTrafico) {
		this.nombreCeldaUltimoTrafico = nombreCeldaUltimoTrafico;
	}

	/**
	 * @return the direccionCeldaUltimoTrafico
	 */
	public String getDireccionCeldaUltimoTrafico() {
		return direccionCeldaUltimoTrafico;
	}

	/**
	 * @param direccionCeldaUltimoTrafico the direccionCeldaUltimoTrafico to set
	 */
	public void setDireccionCeldaUltimoTrafico(String direccionCeldaUltimoTrafico) {
		this.direccionCeldaUltimoTrafico = direccionCeldaUltimoTrafico;
	}

	/**
	 * @return the comunaCeldaUltimoTrafico
	 */
	public String getComunaCeldaUltimoTrafico() {
		return comunaCeldaUltimoTrafico;
	}

	/**
	 * @param comunaCeldaUltimoTrafico the comunaCeldaUltimoTrafico to set
	 */
	public void setComunaCeldaUltimoTrafico(String comunaCeldaUltimoTrafico) {
		this.comunaCeldaUltimoTrafico = comunaCeldaUltimoTrafico;
	}

	/**
	 * @return the regionCeldaUltimoTrafico
	 */
	public String getRegionCeldaUltimoTrafico() {
		return regionCeldaUltimoTrafico;
	}

	/**
	 * @param regionCeldaUltimoTrafico the regionCeldaUltimoTrafico to set
	 */
	public void setRegionCeldaUltimoTrafico(String regionCeldaUltimoTrafico) {
		this.regionCeldaUltimoTrafico = regionCeldaUltimoTrafico;
	}

	/**
	 * @return the codigoEquipo
	 */
	public String getCodigoEquipo() {
		return codigoEquipo;
	}

	/**
	 * @param codigoEquipo the codigoEquipo to set
	 */
	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	/**
	 * @return the descripcionEquipo
	 */
	public String getDescripcionEquipo() {
		return descripcionEquipo;
	}

	/**
	 * @param descripcionEquipo the descripcionEquipo to set
	 */
	public void setDescripcionEquipo(String descripcionEquipo) {
		this.descripcionEquipo = descripcionEquipo;
	}
	
	
	

}
