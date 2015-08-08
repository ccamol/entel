/* Propiedad de Entel S.A. Todos los derechos reservados */
package com.esa.ponline.appmobile.util.security;

import java.io.Serializable;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class AccesosSolicitudClave implements Serializable{

	private static final long serialVersionUID = 1L;
	private String fechaAcceso1;
	private String fechaAcceso2;
	private String fechaAcceso3;
	private int cantidadAccesos;
	
	public AccesosSolicitudClave() {
	}
	
	public AccesosSolicitudClave(int cantidadAccesos, String fechaAcceso1, String fechaAcceso2, 
			String fechaAcceso3) {
		this.fechaAcceso1 = fechaAcceso1;
		this.fechaAcceso2 = fechaAcceso2;
		this.fechaAcceso3 = fechaAcceso3;
		this.cantidadAccesos = cantidadAccesos;
	}
	
	public int getCantidadAccesos() {
		return cantidadAccesos;
	}
	public void setCantidadAccesos(int cantidadAccesos) {
		this.cantidadAccesos = cantidadAccesos;
	}
	public String getFechaAcceso1() {
		return fechaAcceso1;
	}
	public void setFechaAcceso1(String fechaAcceso1) {
		this.fechaAcceso1 = fechaAcceso1;
	}
	public String getFechaAcceso2() {
		return fechaAcceso2;
	}
	public void setFechaAcceso2(String fechaAcceso2) {
		this.fechaAcceso2 = fechaAcceso2;
	}
	public String getFechaAcceso3() {
		return fechaAcceso3;
	}
	public void setFechaAcceso3(String fechaAcceso3) {
		this.fechaAcceso3 = fechaAcceso3;
	}
	
	
	
	

}
