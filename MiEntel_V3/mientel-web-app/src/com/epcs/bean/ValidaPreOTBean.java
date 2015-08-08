/**
 * 
 */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author I2B, haltamiranda.
 *
 */
public class ValidaPreOTBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String codigoRespuesta;
	String mensajeRespuesta;
	/**
	 * @return the codigoRespuesta
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	/**
	 * @param codigoRespuesta the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	/**
	 * @return the mensajeRespuesta
	 */
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	/**
	 * @param mensajeRespuesta the mensajeRespuesta to set
	 */
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
	
}
