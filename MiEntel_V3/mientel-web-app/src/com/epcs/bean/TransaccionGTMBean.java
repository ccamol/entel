/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.Date;

import com.epcs.recursoti.configuracion.DateHelper;

/**
 * Bean que encapsula los datos necesarios para realizar una marca transaccional
 * con Google Tag Manager
 * 
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 */
public class TransaccionGTMBean {

	// Atributos de la transaccion
	private String idTransaccion;
	private double valorTransaccion;
	// Atributos de la operacion
	private String skuID;
	private String nombre;
	private String nuevoValor;
	private double costoOperacional;
	private int numeroPlanes;
	private int numeroOperaciones;
	// Atributos del producto
	private String tipoProducto;

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		String timestamp = DateHelper.format(new Date(),
				DateHelper.FORMAT_yyyyMMddhhmmss);
		this.idTransaccion = idTransaccion + "_" + timestamp;
	}

	public double getValorTransaccion() {
		return valorTransaccion;
	}

	public void setValorTransaccion(double valorTransaccion) {
		this.valorTransaccion = valorTransaccion;
	}

	public String getSkuID() {
		return skuID;
	}

	public void setSkuID(String skuID) {
		this.skuID = skuID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNuevoValor() {
		return nuevoValor;
	}

	public void setNuevoValor(String nuevoValor) {
		this.nuevoValor = nuevoValor;
	}

	public double getCostoOperacional() {
		return costoOperacional;
	}

	public void setCostoOperacional(int costoOperacional) {
		this.costoOperacional = costoOperacional;
	}

	public int getNumeroPlanes() {
		return numeroPlanes;
	}

	public void setNumeroPlanes(int numeroPlanes) {
		this.numeroPlanes = numeroPlanes;
	}

	public int getNumeroOperaciones() {
		return numeroOperaciones;
	}

	public void setNumeroOperaciones(int numeroOperaciones) {
		this.numeroOperaciones = numeroOperaciones;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
}
