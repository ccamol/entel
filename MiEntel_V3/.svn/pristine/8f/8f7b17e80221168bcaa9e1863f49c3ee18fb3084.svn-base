/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */

public  class PlanBean implements Serializable,Comparable<PlanBean> {
	
	 private static final long serialVersionUID = 1L;
	 
	 private String tipoPlan;
	 
	 private String flagTipoDetalle;
	 
	 private String codbscs1;
	 
	 private String codbscs2;
	 
	 private String nombrePlan;
	 
	 private String codigoNombrePlan;
	 
	 private String tipoMercado;
	 
	 private String tipoTasacion;
	 
	 private String totalMinutos;
	 
	 private Long totalMinutosNumerico;
	 
	 private String totalMinutosAdicional;
	 
	 private Double cargoFijoPlan;
	 
	 private String descIMovil;
	 
	 private String limiteIMovil;
	 
	 private List<TasacionBean> listaTasaciones = new ArrayList<TasacionBean>();
	 
	 private boolean tarifaUnicaConSMS;
	 
	 private boolean bberryMMTodoDestino;
	 
	 private boolean MMediaCControlada;
	 
	 private boolean bberryMMediaCControlada;
	 
	 private boolean MMediaRed;
	 
	 private boolean MMediaCuentaControladaTodoDestino;
	 
	 private boolean MMediaRedExcedido;
	 
	 private boolean MMediaRedExcedidoTodoDestino;
	 
	 
	 /**
	  * 
	  * @return
	  */
	public String getTipoPlan() {
		return tipoPlan;
	}

	/**
	 * 
	 * @param tipoPlan
	 */
	public void setTipoPlan(String tipoPlan) {
		this.tipoPlan = tipoPlan;
	}
	

	/**
	 * 
	 * @return
	 */
	public String getFlagTipoDetalle() {
		return flagTipoDetalle;
	}

	/**
	 * 
	 * @param flagTipoDetalle
	 */
	public void setFlagTipoDetalle(String flagTipoDetalle) {
		this.flagTipoDetalle = flagTipoDetalle;
	}

	/**
	 * 
	 * @return
	 */
	public String getCodbscs1() {
		return codbscs1;
	}

	/**
	 * 
	 * @param codbscs1
	 */
	public void setCodbscs1(String codbscs1) {
		this.codbscs1 = codbscs1;
	}

	/**
	 * 
	 * @return
	 */
	public String getCodbscs2() {
		return codbscs2;
	}

	/**
	 * 
	 * @param codbscs2
	 */
	public void setCodbscs2(String codbscs2) {
		this.codbscs2 = codbscs2;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombrePlan() {
		return nombrePlan;
	}

	/**
	 * 
	 * @param nombrePlan
	 */
	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}

	/**
	 * 
	 * @return
	 */
	public String getTipoMercado() {
		return tipoMercado;
	}

	/**
	 * 
	 * @param tipoMercado
	 */
	public void setTipoMercado(String tipoMercado) {
		this.tipoMercado = tipoMercado;
	}

	/**
	 * 
	 * @return
	 */
	public String getTipoTasacion() {
		return tipoTasacion;
	}

	/**
	 * 
	 * @param tipoTasacion
	 */
	public void setTipoTasacion(String tipoTasacion) {
		this.tipoTasacion = tipoTasacion;
	}

	/**
	 * 
	 * @return
	 */
	public String getTotalMinutos() {
		return totalMinutos;
	}

	/**
	 * 
	 * @param totalMinutos
	 */
	public void setTotalMinutosNumerico(Long totalMinutosNumerico) {
		this.totalMinutosNumerico = totalMinutosNumerico;
	}
	
	/**
	 * 
	 * @return
	 */
	public Long getTotalMinutosNumerico() {
		return totalMinutosNumerico;
	}

	/**
	 * 
	 * @param totalMinutos
	 */
	public void setTotalMinutos(String totalMinutos) {
		this.totalMinutos = totalMinutos;
	}	

	/**
	 * 
	 * @return
	 */
	public String getTotalMinutosAdicional() {
		return totalMinutosAdicional;
	}

	/**
	 * 
	 * @param totalMinutosAdicional
	 */
	public void setTotalMinutosAdicional(String totalMinutosAdicional) {
		this.totalMinutosAdicional = totalMinutosAdicional;
	}

	/**
	 * 
	 * @return
	 */
	public Double getCargoFijoPlan() {
		return cargoFijoPlan;
	}

	/**
	 * 
	 * @param cargoFijoPlan
	 */
	public void setCargoFijoPlan(Double cargoFijoPlan) {
		this.cargoFijoPlan = cargoFijoPlan;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getDescIMovil() {
		return descIMovil;
	}

	/**
	 * 
	 * @param descIMovil
	 */
	public void setDescIMovil(String descIMovil) {
		this.descIMovil = descIMovil;
	}

	/**
	 * 
	 * @return
	 */
	public String getLimiteIMovil() {
		return limiteIMovil;
	}

	/**
	 * 
	 * @param limiteIMovil
	 */
	public void setLimiteIMovil(String limiteIMovil) {
		this.limiteIMovil = limiteIMovil;
	}

	/**
	 * 
	 * @return
	 */
	public List<TasacionBean> getListaTasaciones() {
		return listaTasaciones;
	}

	/**
	 * 
	 * @param listaTasaciones
	 */
	public void setListaTasaciones(List<TasacionBean> listaTasaciones) {
		this.listaTasaciones = listaTasaciones;
	}

	@Override
	public int compareTo(PlanBean planBean) {
		if(Integer.parseInt(this.getCodbscs2()) < Integer.parseInt(planBean.getCodbscs2()))
            return -1;
        else if(Integer.parseInt(this.getCodbscs2()) == Integer.parseInt(planBean.getCodbscs2()))
            return 0;
        else
            return 1;

	}

	/**
	 * @return the tarifaUnicaConSMS
	 */
	public boolean isTarifaUnicaConSMS() {
		return tarifaUnicaConSMS;
	}

	/**
	 * @param tarifaUnicaConSMS the tarifaUnicaConSMS to set
	 */
	public void setTarifaUnicaConSMS(boolean tarifaUnicaConSMS) {
		this.tarifaUnicaConSMS = tarifaUnicaConSMS;
	}

	/**
	 * @return the bberryMMTodoDestino
	 */
	public boolean isBberryMMTodoDestino() {
		return bberryMMTodoDestino;
	}

	/**
	 * @param bberryMMTodoDestino the bberryMMTodoDestino to set
	 */
	public void setBberryMMTodoDestino(boolean bberryMMTodoDestino) {
		this.bberryMMTodoDestino = bberryMMTodoDestino;
	}

	/**
	 * @return the codigoNombrePlan
	 */
	public String getCodigoNombrePlan() {
		return codigoNombrePlan;
	}

	/**
	 * @param codigoNombrePlan the codigoNombrePlan to set
	 */
	public void setCodigoNombrePlan(String codigoNombrePlan) {
		this.codigoNombrePlan = codigoNombrePlan;
	}

	public boolean isMMediaCControlada() {
		return MMediaCControlada;
	}

	public void setMMediaCControlada(boolean mMediaCControlada) {
		MMediaCControlada = mMediaCControlada;
	}

	public boolean isBberryMMediaCControlada() {
		return bberryMMediaCControlada;
	}

	public void setBberryMMediaCControlada(boolean bberryMMediaCControlada) {
		this.bberryMMediaCControlada = bberryMMediaCControlada;
	}

	public boolean isMMediaRed() {
		return MMediaRed;
	}

	public void setMMediaRed(boolean mMediaRed) {
		MMediaRed = mMediaRed;
	}

	public boolean isMMediaCuentaControladaTodoDestino() {
		return MMediaCuentaControladaTodoDestino;
	}

	public void setMMediaCuentaControladaTodoDestino(
			boolean mMediaCuentaControladaTodoDestino) {
		MMediaCuentaControladaTodoDestino = mMediaCuentaControladaTodoDestino;
	}

	/**
	 * @return the mMediaRedExcedido
	 */
	public boolean isMMediaRedExcedido() {
		return MMediaRedExcedido;
	}

	/**
	 * @param mMediaRedExcedido the mMediaRedExcedido to set
	 */
	public void setMMediaRedExcedido(boolean mMediaRedExcedido) {
		MMediaRedExcedido = mMediaRedExcedido;
	}

	/**
	 * @return the mMediaRedExcedidoTodoDestino
	 */
	public boolean isMMediaRedExcedidoTodoDestino() {
		return MMediaRedExcedidoTodoDestino;
	}

	/**
	 * @param mMediaRedExcedidoTodoDestino the mMediaRedExcedidoTodoDestino to set
	 */
	public void setMMediaRedExcedidoTodoDestino(boolean mMediaRedExcedidoTodoDestino) {
		MMediaRedExcedidoTodoDestino = mMediaRedExcedidoTodoDestino;
	}
	
	

}
