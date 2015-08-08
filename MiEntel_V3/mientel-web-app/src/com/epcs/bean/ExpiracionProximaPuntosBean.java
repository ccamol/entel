package com.epcs.bean;

import java.util.Date;

public class ExpiracionProximaPuntosBean {
	private String periodoVencimiento;
	private int puntos;
	private String puntosStringFormat;	
	
	/**
	 * @return the periodoVencimiento
	 */
	public String getPeriodoVencimiento() {
		return periodoVencimiento;
	}
	/**
	 * @param periodoVencimiento the periodoVencimiento to set
	 */
	public void setPeriodoVencimiento(String periodoVencimiento) {
		this.periodoVencimiento = periodoVencimiento;
	}
	/**
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}
	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	/**
	 * @return the puntosStringFormat
	 */
	public String getPuntosStringFormat() {
		return puntosStringFormat;
	}
	/**
	 * @param puntosStringFormat the puntosStringFormat to set
	 */
	public void setPuntosStringFormat(String puntosStringFormat) {
		this.puntosStringFormat = puntosStringFormat;
	}	
}
