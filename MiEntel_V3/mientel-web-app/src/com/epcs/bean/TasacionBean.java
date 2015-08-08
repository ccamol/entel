/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */

public class TasacionBean implements Serializable {
	
	 private static final long serialVersionUID = 1L;

	 private String tipo;
	 
	 private String nombre;
	 
	 private String valor;
	 
	 private Long valorNumerico;
	 
	 private String unidad;
	 
	 

	 /**
	  * 
	  * @return
	  */
	public String getTipo() {
		return tipo;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * 
	 * @param valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	/**
	 * 
	 * @return
	 */
	public Long getValorNumerico() {
		return valorNumerico;
	}

	/**
	 * 
	 * @param valorNumerico
	 */
	public void setValorNumerico(Long valorNumerico) {
		this.valorNumerico = valorNumerico;
	}	

	/**
	 * 
	 * @return
	 */
	public String getUnidad() {
		return unidad;
	}

	/**
	 * 
	 * @param unidad
	 */
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
}
