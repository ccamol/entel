package com.esa.ponline.appmobile.vo;

import java.io.Serializable;

public class ExcedidoVO implements Serializable {

	private static final long serialVersionUID = 1794688419105504331L;

	private String fechaReferencia;
	private String velocidadPlan;
	private String cuotaTraficoUtil;
	private String valorTotalTrafico;
	private String traficoExcedido;
	private String valorMBExcedido;
	private String totalTrafico;
	private String clasificacionPlan;
	private String codigoPlanBSCS;
	private String nombrePlanBSCS;
	private String descPlanBSCS;
	
	public String getFechaReferencia() {
		return fechaReferencia;
	}
	public void setFechaReferencia(String fechaReferencia) {
		this.fechaReferencia = fechaReferencia;
	}
	public String getVelocidadPlan() {
		return velocidadPlan;
	}
	public void setVelocidadPlan(String velocidadPlan) {
		this.velocidadPlan = velocidadPlan;
	}
	public String getCuotaTraficoUtil() {
		return cuotaTraficoUtil;
	}
	public void setCuotaTraficoUtil(String cuotaTraficoUtil) {
		this.cuotaTraficoUtil = cuotaTraficoUtil;
	}
	public String getValorTotalTrafico() {
		return valorTotalTrafico;
	}
	public void setValorTotalTrafico(String valorTotalTrafico) {
		this.valorTotalTrafico = valorTotalTrafico;
	}
	public String getTraficoExcedido() {
		return traficoExcedido;
	}
	public void setTraficoExcedido(String traficoExcedido) {
		this.traficoExcedido = traficoExcedido;
	}
	public String getValorMBExcedido() {
		return valorMBExcedido;
	}
	public void setValorMBExcedido(String valorMBExcedido) {
		this.valorMBExcedido = valorMBExcedido;
	}
	public String getTotalTrafico() {
		return totalTrafico;
	}
	public void setTotalTrafico(String totalTrafico) {
		this.totalTrafico = totalTrafico;
	}
	public String getClasificacionPlan() {
		return clasificacionPlan;
	}
	public void setClasificacionPlan(String clasificacionPlan) {
		this.clasificacionPlan = clasificacionPlan;
	}
	public String getCodigoPlanBSCS() {
		return codigoPlanBSCS;
	}
	public void setCodigoPlanBSCS(String codigoPlanBSCS) {
		this.codigoPlanBSCS = codigoPlanBSCS;
	}
	public String getNombrePlanBSCS() {
		return nombrePlanBSCS;
	}
	public void setNombrePlanBSCS(String nombrePlanBSCS) {
		this.nombrePlanBSCS = nombrePlanBSCS;
	}
	public String getDescPlanBSCS() {
		return descPlanBSCS;
	}
	public void setDescPlanBSCS(String descPlanBSCS) {
		this.descPlanBSCS = descPlanBSCS;
	}
	
	
}
