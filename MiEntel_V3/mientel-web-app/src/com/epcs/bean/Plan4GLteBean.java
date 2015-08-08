
package com.epcs.bean;
	
/*
 * @author Anthony David Cajamarca Acuna(I2B)
 * @date 18/03/2014
 * 
 */


public class Plan4GLteBean{
	
	private String disponibilidadLte;
	private String nombrePlanBscs;
	
	public Plan4GLteBean() {
	
	}
	
	/**
	 * @param disponibilidadLte
	 * @param nombrePlanBscs
	 */
	
	public Plan4GLteBean(String disponibilidadLte, String nombrePlanBscs){
		super();
		this.disponibilidadLte = disponibilidadLte;
		this.nombrePlanBscs = nombrePlanBscs;
	}

	
	public String getDisponibilidadLte() {
		return disponibilidadLte;
	}

	public void setDisponibilidadLte(String disponibilidadLte) {
		this.disponibilidadLte = disponibilidadLte;
	}

	public String getNombrePlanBscs() {
		return nombrePlanBscs;
	}

	public void setNombrePlanBscs(String nombrePlanBscs) {
		this.nombrePlanBscs = nombrePlanBscs;
	}
	
	
}