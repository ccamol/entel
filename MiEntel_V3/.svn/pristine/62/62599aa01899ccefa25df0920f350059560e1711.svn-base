/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * Bean que contiene la informacion de una promocion de recarga WebPay
 * 
 * @author zmanotas
 * 
 */
public class PromoRecargaWebPayBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String tipo;
	private String descripcion;
	private String monto;
	private String mensaje;
	private String codigo;

	public PromoRecargaWebPayBean() { }

	public PromoRecargaWebPayBean(String id, String monto) {
		this.id = id;
		this.monto = monto;
		this.codigo = monto + id;
	}

	public PromoRecargaWebPayBean(String id, String monto, String mensaje, String tipo, String descripcion) {
		this.id = id;
		this.monto = monto;
		this.tipo = tipo;		
		this.descripcion = descripcion;
		this.mensaje = mensaje + " " + tipo + ": " + descripcion;
		this.codigo = monto + id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}	
}
