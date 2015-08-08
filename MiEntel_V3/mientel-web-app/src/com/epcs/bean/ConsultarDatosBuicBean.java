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
public class ConsultarDatosBuicBean implements Serializable {
	
	private static final long serialVersionUID = 6078851483477219858L;
	
	private String emailUsuario;
	
    private String emailDominio;
    
    private String areaFono;
    
    private String numeroFono;
    
    private String direccion;
    
    private String numeroDomicilio;
    
    private String deptoOff;
    
    private String region;
    
    private String ciudad;
    
    private String comuna;
    
    private DireccionBean direccionContacto;
    
    
    

	/**
	 * @return the direccionContacto
	 */
	public DireccionBean getDireccionContacto() {
		return direccionContacto;
	}

	/**
	 * @param direccionContacto the direccionContacto to set
	 */
	public void setDireccionContacto(DireccionBean direccionContacto) {
		this.direccionContacto = direccionContacto;
	}

	/**
	 * @return the emailUsuario
	 */
	public String getEmailUsuario() {
		return emailUsuario;
	}

	/**
	 * @param emailUsuario the emailUsuario to set
	 */
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	/**
	 * @return the emailDominio
	 */
	public String getEmailDominio() {
		return emailDominio;
	}

	/**
	 * @param emailDominio the emailDominio to set
	 */
	public void setEmailDominio(String emailDominio) {
		this.emailDominio = emailDominio;
	}

	/**
	 * @return the areaFono
	 */
	public String getAreaFono() {
		return areaFono;
	}

	/**
	 * @param areaFono the areaFono to set
	 */
	public void setAreaFono(String areaFono) {
		this.areaFono = areaFono;
	}

	/**
	 * @return the numeroFono
	 */
	public String getNumeroFono() {
		return numeroFono;
	}

	/**
	 * @param numeroFono the numeroFono to set
	 */
	public void setNumeroFono(String numeroFono) {
		this.numeroFono = numeroFono;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the numeroDomicilio
	 */
	public String getNumeroDomicilio() {
		return numeroDomicilio;
	}

	/**
	 * @param numeroDomicilio the numeroDomicilio to set
	 */
	public void setNumeroDomicilio(String numeroDomicilio) {
		this.numeroDomicilio = numeroDomicilio;
	}

	/**
	 * @return the deptoOff
	 */
	public String getDeptoOff() {
		return deptoOff;
	}

	/**
	 * @param deptoOff the deptoOff to set
	 */
	public void setDeptoOff(String deptoOff) {
		this.deptoOff = deptoOff;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the comuna
	 */
	public String getComuna() {
		return comuna;
	}

	/**
	 * @param comuna the comuna to set
	 */
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
    
    

}
