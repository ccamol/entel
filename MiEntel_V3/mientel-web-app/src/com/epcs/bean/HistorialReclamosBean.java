package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class HistorialReclamosBean implements Serializable,Comparable<HistorialReclamosBean> {
	
	private static final long serialVersionUID = 1L;
	
	private Date fechaDeIngreso;
	
	private String fechaDeIngresoTest;
	
	private String motivo;
	
	private String numeroReclamo;
	
	private String estado;
	
	private Date fechaDeRespuesta;
	
	private String  fechaDeRespuestaTest;
	
	public Date getFechaDeIngreso() {
		return fechaDeIngreso;
	}
	
	public void setFechaDeIngreso(Date fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getNumeroReclamo() {
		return numeroReclamo;
	}
	public void setNumeroReclamo(String numeroReclamo) {
		this.numeroReclamo = numeroReclamo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaDeRespuesta() {
		return fechaDeRespuesta;
	}
	public void setFechaDeRespuesta(Date fechaDeRespuesta) {
		this.fechaDeRespuesta = fechaDeRespuesta;
	}

	@Override
	public int  compareTo(HistorialReclamosBean historialReclamosBean) {
		 return historialReclamosBean.getFechaDeIngreso().compareTo(this.getFechaDeIngreso());		
	}

	/**
	 * @return the fechaDeIngresoTest
	 */
	public String getFechaDeIngresoTest() {
		return fechaDeIngresoTest;
	}

	/**
	 * @param fechaDeIngresoTest the fechaDeIngresoTest to set
	 */
	public void setFechaDeIngresoTest(String fechaDeIngresoTest) {
		this.fechaDeIngresoTest = fechaDeIngresoTest;
	}

	/**
	 * @return the fechaDeRespuestaTest
	 */
	public String getFechaDeRespuestaTest() {
		return fechaDeRespuestaTest;
	}

	/**
	 * @param fechaDeRespuestaTest the fechaDeRespuestaTest to set
	 */
	public void setFechaDeRespuestaTest(String fechaDeRespuestaTest) {
		this.fechaDeRespuestaTest = fechaDeRespuestaTest;
	}
	
	
	
}
