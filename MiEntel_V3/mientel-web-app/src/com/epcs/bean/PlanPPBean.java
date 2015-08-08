/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */

public class PlanPPBean implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	 
	 private int idTarifa;
	 
	 private String estado;
	 
	 private Double costoCambioPlan;
	 
	 private boolean flagVisible;
	 
	 private boolean flagFrecuente;
	 
	 private String tipoPlan;
	 
	 private String nombrePlan;
	 
	 private String descripcionPlan;
	 
	 private String breveDescripcion;
	 
	 private ArrayList<String> glosaFormated;

	 private ArrayList<NumeroFrecuenteBean> numerosFrecuentes;
	 
	 private ArrayList<SlotBean> slotBean;
	 

	 /**
	  * 
	  * @return
	  */
	public int getIdTarifa() {
		return idTarifa;
	}

	/**
	 * 
	 * @param idTarifa
	 */
	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}

	/**
	 * 
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * 
	 * @return
	 */
	public Double getCostoCambioPlan() {
		return costoCambioPlan;
	}

	/**
	 * 
	 * @param costoCambioPlan
	 */
	public void setCostoCambioPlan(Double costoCambioPlan) {
		this.costoCambioPlan = costoCambioPlan;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isFlagVisible() {
		return flagVisible;
	}

	/**
	 * 
	 * @param flagVisible
	 */
	public void setFlagVisible(boolean flagVisible) {
		this.flagVisible = flagVisible;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isFlagFrecuente() {
		return flagFrecuente;
	}

	/**
	 * 
	 * @param flagFrecuente
	 */
	public void setFlagFrecuente(boolean flagFrecuente) {
		this.flagFrecuente = flagFrecuente;
	}

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
	public String getDescripcionPlan() {
		return descripcionPlan;
	}

	/**
	 * 
	 * @param descripcionPlan
	 */
	public void setDescripcionPlan(String descripcionPlan) {
		this.descripcionPlan = descripcionPlan;
	}

	/**
	 * 
	 * @return
	 */
	public String getBreveDescripcion() {
		return breveDescripcion;
	}

	/**
	 * 
	 * @param breveDescripcion
	 */
	public void setBreveDescripcion(String breveDescripcion) {
		this.breveDescripcion = breveDescripcion;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getGlosaFormated() {
		return glosaFormated;
	}

	/**
	 * 
	 * @param glosaFormated
	 */
	public void setGlosaFormated(ArrayList<String> glosaFormated) {
		this.glosaFormated = glosaFormated;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<NumeroFrecuenteBean> getNumerosFrecuentes() {
		return numerosFrecuentes;
	}

	/**
	 * 
	 * @param numerosFrecuentes
	 */
	public void setNumerosFrecuentes(
			ArrayList<NumeroFrecuenteBean> numerosFrecuentes) {
		this.numerosFrecuentes = numerosFrecuentes;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<SlotBean> getSlotBean() {
		return slotBean;
	}

	/**
	 * 
	 * @param slotBean
	 */
	public void setSlotBean(ArrayList<SlotBean> slotBean) {
		this.slotBean = slotBean;
	}
	 
	
	 
}
