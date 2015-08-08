/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.vo.traffic;

import java.io.Serializable;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class TrafficVO implements Serializable {

	private static final long serialVersionUID = 8538689278250237269L;
	
	private String minutosUtilizados;
	private String plan;
	private String smsMMS;
	private String otros;
	private String datosTotales;
	private String datosConsumidos;
	private String bam;
	private String fechaVoz;
	private String fechaMensajeria;
	private String fechaDatos;
	private String tipoTrafico;
	private String traficoExcedido;
	private String tieneExcedido;
	private String bbry;
	private String ishop;
	private String periodo;
	private String trafico;
	private String multimediaTrafico;
	private String multimediaTraficoConsumido;
	private String porcentaje;
	private String horaReferencia;
	private String suma;
	private String valorAdicional;
	
	public String getHoraReferencia() {
		return horaReferencia;
	}
	public void setHoraReferencia(String horaReferencia) {
		this.horaReferencia = horaReferencia;
	}	
	public String getMinutosUtilizados() {
		return minutosUtilizados;
	}
	public void setMinutosUtilizados(String minutosUtilizados) {
		this.minutosUtilizados = minutosUtilizados;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getSmsMMS() {
		return smsMMS;
	}
	public void setSmsMMS(String smsMMS) {
		this.smsMMS = smsMMS;
	}
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
	}
	public String getDatosTotales() {
		return datosTotales;
	}
	public void setDatosTotales(String datosTotales) {
		this.datosTotales = datosTotales;
	}
	public String getDatosConsumidos() {
		return datosConsumidos;
	}
	public void setDatosConsumidos(String datosConsumidos) {
		this.datosConsumidos = datosConsumidos;
	}
	public String getBam() {
		return bam;
	}
	public void setBam(String bam) {
		this.bam = bam;
	}
	public String getFechaVoz() {
		return fechaVoz;
	}
	public void setFechaVoz(String fechaVoz) {
		this.fechaVoz = fechaVoz;
	}
	public String getFechaMensajeria() {
		return fechaMensajeria;
	}
	public void setFechaMensajeria(String fechaMensajeria) {
		this.fechaMensajeria = fechaMensajeria;
	}
	public String getFechaDatos() {
		return fechaDatos;
	}
	public void setFechaDatos(String fechaDatos) {
		this.fechaDatos = fechaDatos;
	}
	public String getTipoTrafico() {
		return tipoTrafico;
	}
	public void setTipoTrafico(String tipoTrafico) {
		this.tipoTrafico = tipoTrafico;
	}
	public String getBbry() {
		return bbry;
	}
	public void setBbry(String bbry) {
		this.bbry = bbry;
	}
	public String getIshop() {
		return ishop;
	}
	public void setIshop(String ishop) {
		this.ishop = ishop;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getTrafico() {
		return trafico;
	}
	public void setTrafico(String trafico) {
		this.trafico = trafico;
	}
	public String getMultimediaTrafico() {
		return multimediaTrafico;
	}
	public void setMultimediaTrafico(String multimediaTrafico) {
		this.multimediaTrafico = multimediaTrafico;
	}
	public String getMultimediaTraficoConsumido() {
		return multimediaTraficoConsumido;
	}
	public void setMultimediaTraficoConsumido(String multimediaTraficoConsumido) {
		this.multimediaTraficoConsumido = multimediaTraficoConsumido;
	}
	public String getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getSuma() {
		return suma;
	}
	public void setSuma(String suma) {
		this.suma = suma;
	}
	public String getValorAdicional() {
		return valorAdicional;
	}
	public void setValorAdicional(String valorAdicional) {
		this.valorAdicional = valorAdicional;
	}
	
	public String getPorcentajePunto() {
		return porcentaje.replace(",", ".");
	}
	public String getTraficoExcedido() {
		return traficoExcedido;
	}
	public void setTraficoExcedido(String traficoExcedido) {
		this.traficoExcedido = traficoExcedido;
	}
	public String getTieneExcedido() {
		return tieneExcedido;
	}
	public void setTieneExcedido(String tieneExcedido) {
		this.tieneExcedido = tieneExcedido;
	}
}
