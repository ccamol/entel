package com.epcs.bean;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class SolicitaIphoneBean {

	private String rut;
	
	private String telefonoAdicional;
	
	private String nombreCompleto;
	
	private String msisdnContacto;
	
	private String nombreFormulario;
	
	public SolicitaIphoneBean(){
		
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
	 * @return the telefonoAdicional
	 */
	public String getTelefonoAdicional() {
		return telefonoAdicional;
	}

	/**
	 * @param telefonoAdicional the telefonoAdicional to set
	 */
	public void setTelefonoAdicional(String telefonoAdicional) {
		this.telefonoAdicional = telefonoAdicional;
	}

	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return the msisdnContacto
	 */
	public String getMsisdnContacto() {
		return msisdnContacto;
	}

	/**
	 * @param msisdnContacto the msisdnContacto to set
	 */
	public void setMsisdnContacto(String msisdnContacto) {
		this.msisdnContacto = msisdnContacto;
	}

	/**
	 * @return the nombreFormulario
	 */
	public String getNombreFormulario() {
		return nombreFormulario;
	}

	/**
	 * @param nombreFormulario the nombreFormulario to set
	 */
	public void setNombreFormulario(String nombreFormulario) {
		this.nombreFormulario = nombreFormulario;
	}
	
}
