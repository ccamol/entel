/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */

public class RecargaHistoricoBean implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	 private String msisdn;
	 
	 private Date fechaInicial;
	 
	 private Date fechaFinal;
	 
	 private Double montoTotal;
	 
	 private int numeroConsultas;
	 
	 private String tiempo;
	 
	 private int mes;
	 
	 private int ano;
	 
	 private Double monto;
	 
	 private ArrayList<DetalleRecargasBean> detalleRecargas;
	 
	 
	 
	 
	/**
	 * 
	 * @return
	 */
	public String getMsisdn() {
		return msisdn;
	}

	/**
	 * 
	 * @param msisdn
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * 
	 * @param fechaInicial
	 */
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * 
	 * @param fechaFinal
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * 
	 * @return
	 */
	public Double getMontoTotal() {
		return montoTotal;
	}

	/**
	 * 
	 * @param montoTotal
	 */
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumeroConsultas() {
		return numeroConsultas;
	}

	/**
	 * 
	 * @param numeroConsultas
	 */
	public void setNumeroConsultas(int numeroConsultas) {
		this.numeroConsultas = numeroConsultas;
	}

	/**
	 * 
	 * @return
	 */
	public String getTiempo() {
		return tiempo;
	}

	/**
	 * 
	 * @param tiempo
	 */
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * 
	 * @return
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * 
	 * @param mes
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * 
	 * @return
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * 
	 * @param ano
	 */
	public void setAno(int ano) {
		this.ano = ano;
	}

	/**
	 * 
	 * @return
	 */
	public Double getMonto() {
		return monto;
	}

	/**
	 * 
	 * @param monto
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<DetalleRecargasBean> getDetalleRecargas() {
		return detalleRecargas;
	}

	/**
	 * 
	 * @param detalleRecargas
	 */
	public void setDetalleRecargas(ArrayList<DetalleRecargasBean> detalleRecargas) {
		this.detalleRecargas = detalleRecargas;
	}

}
