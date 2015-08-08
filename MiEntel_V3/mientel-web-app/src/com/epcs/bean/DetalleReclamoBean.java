package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */

public class DetalleReclamoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3020310395516562355L;
	
	private String nombre;
	private String rut;
	private Date fechaIngreso;   
	private String fechaIngresoText; 
	private String numeroDeTicket; 
	private String numeroReclamado;	
	private String productoReclamado;	
	private String companiaReclamado;
	private String reclamado;
	private String tipoDereclamo;
	private String numeroDeDocumento;
	private String notificacion;	
	private String antecedentes;
	private String estado;
	private Date fechaRespuesta;
	private String fechaRespuestaText;
	private String respuesta;
	private String email;
	private String motivo;
	private String direccionReclamada;
	private String monto;
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getNumeroDeTicket() {
		return numeroDeTicket;
	}
	public void setNumeroDeTicket(String numeroDeTicket) {
		this.numeroDeTicket = numeroDeTicket;
	}
	public String getNumeroReclamado() {
		return numeroReclamado;
	}
	public void setNumeroReclamado(String numeroReclamado) {
		this.numeroReclamado = numeroReclamado;
	}
	public String getProductoReclamado() {
		return productoReclamado;
	}
	public void setProductoReclamado(String productoReclamado) {
		this.productoReclamado = productoReclamado;
	}
	public String getCompaniaReclamado() {
		return companiaReclamado;
	}
	public void setCompaniaReclamado(String companiaReclamado) {
		this.companiaReclamado = companiaReclamado;
	}
	public String getReclamado() {
		return reclamado;
	}
	public void setReclamado(String reclamado) {
		this.reclamado = reclamado;
	}
	public String getTipoDereclamo() {
		return tipoDereclamo;
	}
	public void setTipoDereclamo(String tipoDereclamo) {
		this.tipoDereclamo = tipoDereclamo;
	}
	public String getNumeroDeDocumento() {
		return numeroDeDocumento;
	}
	public void setNumeroDeDocumento(String numeroDeDocumento) {
		this.numeroDeDocumento = numeroDeDocumento;
	}
	public String getAntecedentes() {
		return antecedentes;
	}
	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}
	public String getNotificacion() {
		return notificacion;
	}
	public void setNotificacion(String notificacion) {
		this.notificacion = notificacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}
	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return the fechaIngresoText
	 */
	public String getFechaIngresoText() {
		return fechaIngresoText;
	}
	/**
	 * @param fechaIngresoText the fechaIngresoText to set
	 */
	public void setFechaIngresoText(String fechaIngresoText) {
		this.fechaIngresoText = fechaIngresoText;
	}
	/**
	 * @return the fechaRespuestaText
	 */
	public String getFechaRespuestaText() {
		return fechaRespuestaText;
	}
	/**
	 * @param fechaRespuestaText the fechaRespuestaText to set
	 */
	public void setFechaRespuestaText(String fechaRespuestaText) {
		this.fechaRespuestaText = fechaRespuestaText;
	}
	public String getDireccionReclamada() {
		return direccionReclamada;
	}
	public void setDireccionReclamada(String direccionReclamada) {
		this.direccionReclamada = direccionReclamada;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}	
	
	

}
