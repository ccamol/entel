/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.vo.plan;

import java.io.Serializable;

/**
 * @author ccastro(MZZO) en nombre de Absalon Opazo (POnline, EntelSA)
 * Sep 15, 2014
 * version 1.0.0
 */

public class PlanVO implements Serializable {
	
	private static final long serialVersionUID = -2954433387884432537L;
	private String idFamiliaPlan;
    private String codigoPlan;
    private String tipoPlan;
    private String descripcion;
    private String tasacion;
    private String minOn;
    private String minOff;
    private String cargoFijo;
    private String valMinON;
    private String valMinOFF;
    private String valMinOTRO;
    
    
	public String getIdFamiliaPlan() {
		return idFamiliaPlan;
	}
	public void setIdFamiliaPlan(String idFamiliaPlan) {
		this.idFamiliaPlan = idFamiliaPlan;
	}
	public String getCodigoPlan() {
		return codigoPlan;
	}
	public void setCodigoPlan(String codigoPlan) {
		this.codigoPlan = codigoPlan;
	}
	public String getTipoPlan() {
		return tipoPlan;
	}
	public void setTipoPlan(String tipoPlan) {
		this.tipoPlan = tipoPlan;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTasacion() {
		return tasacion;
	}
	public void setTasacion(String tasacion) {
		this.tasacion = tasacion;
	}
	public String getMinOn() {
		return minOn;
	}
	public void setMinOn(String minOn) {
		this.minOn = minOn;
	}
	public String getMinOff() {
		return minOff;
	}
	public void setMinOff(String minOff) {
		this.minOff = minOff;
	}
	public String getValMinON() {
		return valMinON;
	}
	public void setValMinON(String valMinON) {
		this.valMinON = valMinON;
	}
	public String getValMinOFF() {
		return valMinOFF;
	}
	public void setValMinOFF(String valMinOFF) {
		this.valMinOFF = valMinOFF;
	}
	public String getValMinOTRO() {
		return valMinOTRO;
	}
	public void setValMinOTRO(String valMinOTRO) {
		this.valMinOTRO = valMinOTRO;
	}
	public String getCargoFijo() {
		return cargoFijo;
	}
	public void setCargoFijo(String cargoFijo) {
		this.cargoFijo = cargoFijo;
	}

    
    
}
