/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.appmobile.exceptions;

import java.io.Serializable;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class ServiceException extends Exception implements Serializable {

	private static final long serialVersionUID = 7117977745420933935L;

	private String codigoRespuesta;

	private String descripcionRespuesta;

	public ServiceException(String codigoRespuesta, String descripcionRespuesta) {
		super();
		this.codigoRespuesta = codigoRespuesta;
		this.descripcionRespuesta = descripcionRespuesta;
	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getDescripcionRespuesta() {
		return descripcionRespuesta;
	}

	public void setDescripcionRespuesta(String descripcionRespuesta) {
		this.descripcionRespuesta = descripcionRespuesta;
	}

	@Override
	public String getMessage() {
		return codigoRespuesta + " - " + descripcionRespuesta;
	}

}
