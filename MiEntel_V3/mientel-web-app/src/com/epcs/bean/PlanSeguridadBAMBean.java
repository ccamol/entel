package com.epcs.bean;

import java.util.Date;

public class PlanSeguridadBAMBean {
	
	private String estadoPlan;
    private String codigoPlan;
    private String nombrePlan;
    private String valorPlan;
    private boolean saldoSuficiente;
    private boolean eqToPlanesPromo;
    private String detallePlan;
    private Date fechaActivacionPlan;
    private Date fechaVencimientoPlan;
    private String fechaActivacionPlanFormatted;
    private String fechaVencimientoPlanFormatted;
    private String mail;
    private String imsi;
    private String password;
    private String textoBoton;
    private String urlDescarga;
    private TransaccionGTMBean transaccionGTM;
    
	/**
	 * @return the estadoPlan
	 */
	public String getEstadoPlan() {
		return estadoPlan;
	}
	/**
	 * @param estadoPlan the estadoPlan to set
	 */
	public void setEstadoPlan(String estadoPlan) {
		this.estadoPlan = estadoPlan;
	}
	/**
	 * @return the codigoPlan
	 */
	public String getCodigoPlan() {
		return codigoPlan;
	}
	/**
	 * @param codigoPlan the codigoPlan to set
	 */
	public void setCodigoPlan(String codigoPlan) {
		this.codigoPlan = codigoPlan;
	}
	/**
	 * @return the nombrePlan
	 */
	public String getNombrePlan() {
		return nombrePlan;
	}
	/**
	 * @param nombrePlan the nombrePlan to set
	 */
	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}
	/**
	 * @return the valorPlan
	 */
	public String getValorPlan() {
		return valorPlan;
	}
	/**
	 * @param valorPlan the valorPlan to set
	 */
	public void setValorPlan(String valorPlan) {
		this.valorPlan = valorPlan;
	}
	/**
	 * @return the saldoSuficiente
	 */
	public boolean isSaldoSuficiente() {
		return saldoSuficiente;
	}
	/**
	 * @param saldoSuficiente the saldoSuficiente to set
	 */
	public void setSaldoSuficiente(boolean saldoSuficiente) {
		this.saldoSuficiente = saldoSuficiente;
	}
	/**
	 * @return the eqToPlanesPromo
	 */
	public boolean isEqToPlanesPromo() {
		return eqToPlanesPromo;
	}
	/**
	 * @param eqToPlanesPromo the eqToPlanesPromo to set
	 */
	public void setEqToPlanesPromo(boolean eqToPlanesPromo) {
		this.eqToPlanesPromo = eqToPlanesPromo;
	}
	/**
	 * @return the detallePlan
	 */
	public String getDetallePlan() {
		return detallePlan;
	}
	/**
	 * @param detallePlan the detallePlan to set
	 */
	public void setDetallePlan(String detallePlan) {
		this.detallePlan = detallePlan;
	}
	/**
	 * @return the fechaActivacionPlan
	 */
	public Date getFechaActivacionPlan() {
		return fechaActivacionPlan;
	}
	/**
	 * @param fechaActivacionPlan the fechaActivacionPlan to set
	 */
	public void setFechaActivacionPlan(Date fechaActivacionPlan) {
		this.fechaActivacionPlan = fechaActivacionPlan;
	}
	/**
	 * @return the fechaVencimientoPlan
	 */
	public Date getFechaVencimientoPlan() {
		return fechaVencimientoPlan;
	}
	/**
	 * @param fechaVencimientoPlan the fechaVencimientoPlan to set
	 */
	public void setFechaVencimientoPlan(Date fechaVencimientoPlan) {
		this.fechaVencimientoPlan = fechaVencimientoPlan;
	}
	/**
	 * @return the fechaActivacionPlanFormatted
	 */
	public String getFechaActivacionPlanFormatted() {
		return fechaActivacionPlanFormatted;
	}
	/**
	 * @param fechaActivacionPlanFormatted the fechaActivacionPlanFormatted to set
	 */
	public void setFechaActivacionPlanFormatted(String fechaActivacionPlanFormatted) {
		this.fechaActivacionPlanFormatted = fechaActivacionPlanFormatted;
	}
	/**
	 * @return the fechaVencimientoPlanFormatted
	 */
	public String getFechaVencimientoPlanFormatted() {
		return fechaVencimientoPlanFormatted;
	}
	/**
	 * @param fechaVencimientoPlanFormatted the fechaVencimientoPlanFormatted to set
	 */
	public void setFechaVencimientoPlanFormatted(
			String fechaVencimientoPlanFormatted) {
		this.fechaVencimientoPlanFormatted = fechaVencimientoPlanFormatted;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}
	/**
	 * @param imsi the imsi to set
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the textoBoton
	 */
	public String getTextoBoton() {
		return textoBoton;
	}
	/**
	 * @param textoBoton the textoBoton to set
	 */
	public void setTextoBoton(String textoBoton) {
		this.textoBoton = textoBoton;
	}
	/**
	 * @return the urlDescarga
	 */
	public String getUrlDescarga() {
		return urlDescarga;
	}
	/**
	 * @param urlDescarga the urlDescarga to set
	 */
	public void setUrlDescarga(String urlDescarga) {
		this.urlDescarga = urlDescarga;
	}

	public TransaccionGTMBean getTransaccionGTM() {
		return transaccionGTM;
	}

	public void setTransaccionGTM(TransaccionGTMBean transaccionGTM) {
		this.transaccionGTM = transaccionGTM;
	}    
}
