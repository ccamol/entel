/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.portalbolsas.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * Aug 27, 2014
 */

public class BolsaComprada implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String idBolsa;
	private String nombreBolsa;
	private String precioBolsa;
	private String tipoBolsa;
	private String tipoTX;
	private String carta;
	private String fechaExp;
	private String codError;
	private String descripcion;
	private String fechaIni;
	private String saldoTiempo;
	private String saldoUnidad;
	private String mensaje;
	private Date fechaExpDateFormat;
	private Date fechaIniDateFormat;
	
	
	public String getIdBolsa() {
		return idBolsa;
	}
	public void setIdBolsa(String idBolsa) {
		this.idBolsa = idBolsa;
	}
	public String getNombreBolsa() {
		return nombreBolsa;
	}
	public void setNombreBolsa(String nombreBolsa) {
		this.nombreBolsa = nombreBolsa;
	}
	public String getPrecioBolsa() {
		return precioBolsa;
	}
	public void setPrecioBolsa(String precioBolsa) {
		this.precioBolsa = precioBolsa;
	}
	public String getTipoBolsa() {
		return tipoBolsa;
	}
	public void setTipoBolsa(String tipoBolsa) {
		this.tipoBolsa = tipoBolsa;
	}
	public String getTipoTX() {
		return tipoTX;
	}
	public void setTipoTX(String tipoTX) {
		this.tipoTX = tipoTX;
	}
	public String getCarta() {
		return carta;
	}
	public void setCarta(String carta) {
		this.carta = carta;
	}
	public String getFechaExp() {
		return fechaExp;
	}
	public void setFechaExp(String fechaExp) {
		this.fechaExp = fechaExp;
	}
	public String getCodError() {
		return codError;
	}
	public void setCodError(String codError) {
		this.codError = codError;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}
	public String getSaldoTiempo() {
		return saldoTiempo;
	}
	public void setSaldoTiempo(String saldoTiempo) {
		this.saldoTiempo = saldoTiempo;
	}
	public Date getFechaExpDateFormat() {
		return fechaExpDateFormat;
	}
	public void setFechaExpDateFormat(Date fechaExpDateFormat) {
		this.fechaExpDateFormat = fechaExpDateFormat;
	}
	public Date getFechaIniDateFormat() {
		return fechaIniDateFormat;
	}
	public void setFechaIniDateFormat(Date fechaIniDateFormat) {
		this.fechaIniDateFormat = fechaIniDateFormat;
	}
	public String getSaldoUnidad() {
		return saldoUnidad;
	}
	public void setSaldoUnidad(String saldoUnidad) {
		this.saldoUnidad = saldoUnidad;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
