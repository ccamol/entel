/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.vo.plan;

import java.io.Serializable;

/**
 * @author ccastro(MZZO) en nombre de Absalon Opazo (POnline, EntelSA)
 * Sep 15, 2014
 * version 1.0.0
 */

public class InfoPlanMMExcededVO implements Serializable {

	private static final long serialVersionUID = -4077103175579818314L;
	private String totalMinutos;
	private String totalMinutosAdicional;
	private String descIMovil;
	private String smsMMSNetNombreTasacion;
	private String smsMMSNetvalorTasacion;
	private String MTTDNombreTasacion;
	private String MTTDvalorTasacion;
	private String MBADINombreTasacion;
	private String MBADIvalorTasacion;
	private String SMSADINombreTasacion;
	private String SMSADIvalorTasacion;
	private String SMSTdNombreTasacion;
	private String SMSTdValorTasacion;	
	private String MMSADINombreTasacion;
	private String MMSADIvalorTasacion;
	private String tipoPlan;
	private String condicionComercial;
	private String tituloCondComercial;
	private Boolean esIlimitado;
	private String lineasDisponiblesAdicionales;
	
	
	public String getLineasDisponiblesAdicionales() {
		return lineasDisponiblesAdicionales;
	}
	public void setLineasDisponiblesAdicionales(String lineasDisponiblesAdicionales) {
		this.lineasDisponiblesAdicionales = lineasDisponiblesAdicionales;
	}
	public String getTotalMinutos() {
		return totalMinutos;
	}
	public void setTotalMinutos(String totalMinutos) {
		this.totalMinutos = totalMinutos;
	}
	public String getTotalMinutosAdicional() {
		return totalMinutosAdicional;
	}
	public void setTotalMinutosAdicional(String totalMinutosAdicional) {
		this.totalMinutosAdicional = totalMinutosAdicional;
	}
	public String getDescIMovil() {
		return descIMovil;
	}
	public void setDescIMovil(String descIMovil) {
		this.descIMovil = descIMovil;
	}
	public String getSmsMMSNetNombreTasacion() {
		return smsMMSNetNombreTasacion;
	}
	public void setSmsMMSNetNombreTasacion(String smsMMSNetNombreTasacion) {
		this.smsMMSNetNombreTasacion = smsMMSNetNombreTasacion;
	}
	public String getSmsMMSNetvalorTasacion() {
		return smsMMSNetvalorTasacion;
	}
	public void setSmsMMSNetvalorTasacion(String smsMMSNetvalorTasacion) {
		this.smsMMSNetvalorTasacion = smsMMSNetvalorTasacion;
	}
	public String getMTTDNombreTasacion() {
		return MTTDNombreTasacion;
	}
	public void setMTTDNombreTasacion(String mTTDNombreTasacion) {
		MTTDNombreTasacion = mTTDNombreTasacion;
	}
	public String getMTTDvalorTasacion() {
		return MTTDvalorTasacion;
	}
	public void setMTTDvalorTasacion(String mTTDvalorTasacion) {
		MTTDvalorTasacion = mTTDvalorTasacion;
	}
	public String getMBADINombreTasacion() {
		return MBADINombreTasacion;
	}
	public void setMBADINombreTasacion(String mBADINombreTasacion) {
		MBADINombreTasacion = mBADINombreTasacion;
	}
	public String getMBADIvalorTasacion() {
		return MBADIvalorTasacion;
	}
	public void setMBADIvalorTasacion(String mBADIvalorTasacion) {
		MBADIvalorTasacion = mBADIvalorTasacion;
	}
	public String getSMSADINombreTasacion() {
		return SMSADINombreTasacion;
	}
	public void setSMSADINombreTasacion(String sMSADINombreTasacion) {
		SMSADINombreTasacion = sMSADINombreTasacion;
	}
	public String getSMSADIvalorTasacion() {
		return SMSADIvalorTasacion;
	}
	public void setSMSADIvalorTasacion(String sMSADIvalorTasacion) {
		SMSADIvalorTasacion = sMSADIvalorTasacion;
	}
	public String getMMSADINombreTasacion() {
		return MMSADINombreTasacion;
	}
	public void setMMSADINombreTasacion(String mMSADINombreTasacion) {
		MMSADINombreTasacion = mMSADINombreTasacion;
	}
	public String getMMSADIvalorTasacion() {
		return MMSADIvalorTasacion;
	}
	public void setMMSADIvalorTasacion(String mMSADIvalorTasacion) {
		MMSADIvalorTasacion = mMSADIvalorTasacion;
	}
	public String getTipoPlan() {
		return tipoPlan;
	}
	public void setTipoPlan(String tipoPlan) {
		this.tipoPlan = tipoPlan;
	}
	public String getCondicionComercial() {
		return condicionComercial;
	}
	public void setCondicionComercial(String condicionComercial) {
		this.condicionComercial = condicionComercial;
	}
	public String getTituloCondComercial() {
		return tituloCondComercial;
	}
	public void setTituloCondComercial(String tituloCondComercial) {
		this.tituloCondComercial = tituloCondComercial;
	}	
	public String getSMSTdNombreTasacion() {
	    return SMSTdNombreTasacion;
	}
	public void setSMSTdNombreTasacion(String sMSTdNombreTasacion) {
	    SMSTdNombreTasacion = sMSTdNombreTasacion;
	}
	public String getSMSTdValorTasacion() {
	    return SMSTdValorTasacion;
	}
	public void setSMSTdValorTasacion(String sMSTdValorTasacion) {
	    SMSTdValorTasacion = sMSTdValorTasacion;
	}	
	public void setEsIlimitado(Boolean esIlimitado) {
	    this.esIlimitado = esIlimitado;
	}
	public Boolean getEsIlimitado() {
	    return esIlimitado;
	}	
	
}
