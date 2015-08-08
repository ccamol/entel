package com.epcs.bean;


import java.io.Serializable;

/**
 * Bean con los atributos de  Equipo para su presentacion con Imagen
 * 
 * @author wbrochero (I2B)
 * */

public class EquipoFullBean implements Serializable 
{
	
	private static final long serialVersionUID = 6078851483477219858L;

    private String marca;

    private String modelo;

    private String urlImagen;
    
    private String idEquipo;
    
    private String wappushEnabled; // Compatibilidad Internet Mvil
    
    private String bkpContSIM; // Contactos SIM CARD
    
    private String bkpContSyncML; // Contactos SIM CARD + Notas y tareas
    
    private String bkpContSW;   // Contactos SIM CARD + Notas y tareas + Multimedia
    
    
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 * 
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the urlImagen
	 */
	public String getUrlImagen() {
		return urlImagen;
	}

	/**
	 * @param urlImagen the urlImagen to set
	 */
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	/**
	 * @return the idEquipo
	 */
	public String getIdEquipo() {
		return idEquipo;
	}

	/**
	 * @param idEquipo the idEquipo to set
	 */
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	/**
	 * Metodo Compatibilidad Internet Movil
	 * @return the wappushEnabled
	 */
	public String getWappushEnabled() {
		return wappushEnabled;
	}

	/**
	 * Metodo Compatibilidad Internet Mvil
	 * @param wappushEnabled the wappushEnabled to set
	 */
	public void setWappushEnabled(String wappushEnabled) {
		this.wappushEnabled = wappushEnabled;
	}

	/**
	 * Metodo  Contactos SIM CARD
	 * @return the bkpContSIM
	 */
	public String getBkpContSIM() {
		return bkpContSIM;
	}

	/**
	 * Metodo  Contactos SIM CARD
	 * @param bkpContSIM the bkpContSIM to set
	 */
	public void setBkpContSIM(String bkpContSIM) {
		this.bkpContSIM = bkpContSIM;
	}

	/**
	 * Metodo Contactos SIM CARD + Notas y tareas
	 * @return the bkpContSyncML
	 */
	public String getBkpContSyncML() {
		return bkpContSyncML;
	}

	/**
	 * Metodo Contactos SIM CARD + Notas y tareas
	 * @param bkpContSyncML the bkpContSyncML to set
	 */
	public void setBkpContSyncML(String bkpContSyncML) {
		this.bkpContSyncML = bkpContSyncML;
	}

	/**
	 * Metodo Contactos SIM CARD + Notas y tareas + Multimedia
	 * @return the bkpContSW
	 */
	public String getBkpContSW() {
		return bkpContSW;
	}

	/**
	 * Metodo Contactos SIM CARD + Notas y tareas + Multimedia
	 * @param bkpContSW the bkpContSW to set
	 */
	public void setBkpContSW(String bkpContSW) {
		this.bkpContSW = bkpContSW;
	}

}
