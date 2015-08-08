/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author jvasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class VelocidadesPlanBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombrePlan;
	private String velMaxDescarga;
	private String velMaxSubida;
	private String velPromNacDescarga;
	private String velPromNacSubida;
	private String velPromInterDescarga;
	private String velPromInterSubida;
	private String Codbscs2;
	
	/**
	 * @return the nombrePlan
	 */
	public String getNombrePlan() {
		return nombrePlan;
	}
	/**
	 * @param nombrePlan the nombrePlan to set
	 */
	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}
	/**
	 * @return the velMaxDescarga
	 */
	public String getVelMaxDescarga() {
		return velMaxDescarga;
	}
	/**
	 * @param velMaxDescarga the velMaxDescarga to set
	 */
	public void setVelMaxDescarga(String velMaxDescarga) {
		this.velMaxDescarga = velMaxDescarga;
	}
	/**
	 * @return the velMaxSubida
	 */
	public String getVelMaxSubida() {
		return velMaxSubida;
	}
	/**
	 * @param velMaxSubida the velMaxSubida to set
	 */
	public void setVelMaxSubida(String velMaxSubida) {
		this.velMaxSubida = velMaxSubida;
	}
	/**
	 * @return the velPromNacDescarga
	 */
	public String getVelPromNacDescarga() {
		return velPromNacDescarga;
	}
	/**
	 * @param velPromNacDescarga the velPromNacDescarga to set
	 */
	public void setVelPromNacDescarga(String velPromNacDescarga) {
		this.velPromNacDescarga = velPromNacDescarga;
	}
	/**
	 * @return the velPromNacSubida
	 */
	public String getVelPromNacSubida() {
		return velPromNacSubida;
	}
	/**
	 * @param velPromNacSubida the velPromNacSubida to set
	 */
	public void setVelPromNacSubida(String velPromNacSubida) {
		this.velPromNacSubida = velPromNacSubida;
	}
	/**
	 * @return the velPromInterDescarga
	 */
	public String getVelPromInterDescarga() {
		return velPromInterDescarga;
	}
	/**
	 * @param velPromInterDescarga the velPromInterDescarga to set
	 */
	public void setVelPromInterDescarga(String velPromInterDescarga) {
		this.velPromInterDescarga = velPromInterDescarga;
	}
	/**
	 * @return the velPromInterSubida
	 */
	public String getVelPromInterSubida() {
		return velPromInterSubida;
	}
	/**
	 * @param velPromInterSubida the velPromInterSubida to set
	 */
	public void setVelPromInterSubida(String velPromInterSubida) {
		this.velPromInterSubida = velPromInterSubida;
	}
	public String getCodbscs2() {
		return Codbscs2;
	}
	public void setCodbscs2(String codbscs2) {
		Codbscs2 = codbscs2;
	}
	
	
}
