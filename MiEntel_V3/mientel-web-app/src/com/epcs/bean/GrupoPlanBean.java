/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class GrupoPlanBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int tipoPlan;
    private String mercadoPlan;
    private String nombrePlan;
    private String nombrePlanSinAcentos;
    private String descPlan;
    private List<String> tiposTasacionPlan = new ArrayList<String>();
    private List<PlanBean> planesDisponibles = new ArrayList<PlanBean>();
    private List<VelocidadesPlanBean> tablaVelocidades = new ArrayList<VelocidadesPlanBean>();
	
    /**
     * 
     * @return
     */
    public int getTipoPlan() {
		return tipoPlan;
	}
    
    /**
     * 
     * @param tipoPlan
     */
	public void setTipoPlan(int tipoPlan) {
		this.tipoPlan = tipoPlan;
	}

	/**
	 * 
	 * @return
	 */
	public String getMercadoPlan() {
		return mercadoPlan;
	}

	/**
	 * 
	 * @param mercadoPlan
	 */
	public void setMercadoPlan(String mercadoPlan) {
		this.mercadoPlan = mercadoPlan;
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
	public String getDescPlan() {
		return descPlan;
	}

	/**
	 * 
	 * @param descPlan
	 */
	public void setDescPlan(String descPlan) {
		this.descPlan = descPlan;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getTiposTasacionPlan() {
		return tiposTasacionPlan;
	}

	/**
	 * 
	 * @param tipoTasacionPlan
	 */
	public void setTiposTasacionPlan(List<String> tiposTasacionPlan) {
		this.tiposTasacionPlan = tiposTasacionPlan;
	}

	/**
	 * 
	 * @return
	 */
	public List<PlanBean> getPlanesDisponibles() {
		return planesDisponibles;
	}
	
	/**
	 * 
	 * @param planesDisponibles
	 */
	public void setPlanesDisponibles(List<PlanBean> planesDisponibles) {
		this.planesDisponibles = planesDisponibles;
	}
   
	
	/**
	 * 
	 * @param nombreTasacion
	 */
	public void add(String nombreTasacion){
		tiposTasacionPlan.add(nombreTasacion);
	}

	/**
	 * @return the tieneTablaVelocidades
	 */
	public boolean isTieneTablaVelocidades() {
		return tablaVelocidades != null && !tablaVelocidades.isEmpty();
	}

	/**
	 * @return the tablaVelocidades
	 */
	public List<VelocidadesPlanBean> getTablaVelocidades() {
		return tablaVelocidades;
	}
	
	/**
	 * @param tablaVelocidades the tablaVelocidades to set
	 */
	public void setTablaVelocidades(List<VelocidadesPlanBean> tablaVelocidades) {
		this.tablaVelocidades = tablaVelocidades;
	}

	public String getNombrePlanSinAcentos() {
		return nombrePlanSinAcentos;
	}

	public void setNombrePlanSinAcentos(String nombrePlanSinAcentos) {
		this.nombrePlanSinAcentos = nombrePlanSinAcentos;
	}
	
	

}
