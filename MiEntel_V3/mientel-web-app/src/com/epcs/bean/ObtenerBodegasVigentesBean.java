/**
 * 
 */
package com.epcs.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author I2B, haltamiranda.
 *
 */
public class ObtenerBodegasVigentesBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<DetalleBodegaVigenteBean> detalleBodegaVigente = new ArrayList<DetalleBodegaVigenteBean>();
    String mensajeRespuesta;
    String codigoRespuesta;
	/**
	 * @return the detalleBodegaVigente
	 */
	public ArrayList<DetalleBodegaVigenteBean> getDetalleBodegaVigente() {
		return detalleBodegaVigente;
	}
	/**
	 * @param detalleBodegaVigente the detalleBodegaVigente to set
	 */
	public void setDetalleBodegaVigente(
			ArrayList<DetalleBodegaVigenteBean> detalleBodegaVigente) {
		this.detalleBodegaVigente = detalleBodegaVigente;
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
}
