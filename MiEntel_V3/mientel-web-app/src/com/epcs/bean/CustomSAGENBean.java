package com.epcs.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomSAGENBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String diasSusc;
	private String horaInicial;
	private String horaFinal;
	
	private List<CategoriaSAGENBean> categorias = new ArrayList<CategoriaSAGENBean>();
	private List<PackContent> packcontents;
	private List<BundleContent> optionalcontents;
	private List<BundleContent> obligatorycontents;
	private UsuarioSAGENBean usuario;
	//hcastillo
	private String duration;
	private String day;
	private String overriding;
	private String hour;
	private String sendSmsSuccess;
    private String time;

	
	public String getDiasSusc() {
		return diasSusc;
	}
	public void setDiasSusc(String diasSusc) {
		this.diasSusc = diasSusc;
	}	
	public String getHoraInicial() {
		return horaInicial;
	}
	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}
	public String getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}
	public List<CategoriaSAGENBean> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<CategoriaSAGENBean> categorias) {
		this.categorias = categorias;
	}
	public UsuarioSAGENBean getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioSAGENBean usuario) {
		this.usuario = usuario;
	}
	public List<PackContent> getPackcontents() {
		return packcontents;
	}
	public void setPackcontents(List<PackContent> packcontents) {
		this.packcontents = packcontents;
	}
	public List<BundleContent> getOptionalcontents() {
		return optionalcontents;
	}
	public void setOptionalcontents(List<BundleContent> optionalcontents) {
		this.optionalcontents = optionalcontents;
	}
	public List<BundleContent> getObligatorycontents() {
		return obligatorycontents;
	}
	public void setObligatorycontents(List<BundleContent> obligatorycontents) {
		this.obligatorycontents = obligatorycontents;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getOverriding() {
		return overriding;
	}
	public void setOverriding(String overriding) {
		this.overriding = overriding;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getSendSmsSuccess() {
		return sendSmsSuccess;
	}
	public void setSendSmsSuccess(String sendSmsSuccess) {
		this.sendSmsSuccess = sendSmsSuccess;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}	
}
