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
public class ConsultarBloqueoDesbloqueoBean implements Serializable {
	
	private static final long serialVersionUID = 6078851483477219858L;
	
	private String codigoRespuesta;
	
	private String descripcionRespuesta;
	
	private EquipoActualBean equipoActual;
	
	private DatosEquipoRobadoBean datosEquipoRobado;

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
	 * @return the descripcionRespuesta
	 */
	public String getDescripcionRespuesta() {
		return descripcionRespuesta;
	}

	/**
	 * @param descripcionRespuesta the descripcionRespuesta to set
	 */
	public void setDescripcionRespuesta(String descripcionRespuesta) {
		this.descripcionRespuesta = descripcionRespuesta;
	}


	/**
	 * @return the equipoActual
	 */
	public EquipoActualBean getEquipoActual() {
		return equipoActual;
	}

	/**
	 * @param equipoActual the equipoActual to set
	 */
	public void setEquipoActual(EquipoActualBean equipoActual) {
		this.equipoActual = equipoActual;
	}

	/**
	 * @return the datosEquipoRobado
	 */
	public DatosEquipoRobadoBean getDatosEquipoRobado() {
		return datosEquipoRobado;
	}

	/**
	 * @param datosEquipoRobado the datosEquipoRobado to set
	 */
	public void setDatosEquipoRobado(DatosEquipoRobadoBean datosEquipoRobado) {
		this.datosEquipoRobado = datosEquipoRobado;
	}
	
	

}
