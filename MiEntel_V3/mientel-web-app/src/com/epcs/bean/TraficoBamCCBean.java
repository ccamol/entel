/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author jroman (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class TraficoBamCCBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String getVelocidadMaxPlanValor() {
		return velocidadMaxPlanValor;
	}

	public void setVelocidadMaxPlanValor(String velocidadMaxPlanValor) {
		this.velocidadMaxPlanValor = velocidadMaxPlanValor;
	}

	public String getVelocidadMaxPlanUnidad() {
		return velocidadMaxPlanUnidad;
	}

	public void setVelocidadMaxPlanUnidad(String velocidadMaxPlanUnidad) {
		this.velocidadMaxPlanUnidad = velocidadMaxPlanUnidad;
	}

	public String getCuotaRestanteUnidad() {
		return cuotaRestanteUnidad;
	}

	public void setCuotaRestanteUnidad(String cuotaRestanteUnidad) {
		this.cuotaRestanteUnidad = cuotaRestanteUnidad;
	}

    private String nombrePlan;
	private String umbralFairUse;
	private String unidadUmbralFairUse;
	private int porcentajeCuotaRestante;
	private String saldoMonto = ""; 
	private String cuotaRestante;
	private String cuotaRestanteUnidad;
	
	
	private String valorTraficoAdicional;
	private String velocidadMaxPlan;
	private String velocidadMaxPlanValor;
	private String velocidadMaxPlanUnidad;
	
	private boolean ilimitado = false;

    /**
  
	
  

	/**
	 * @return the cuotaRestante
	 */
	public String getCuotaRestante() {
		return cuotaRestante;
	}

	/**
	 * @param cuotaRestante the cuotaRestante to set
	 */
	public void setCuotaRestante(String cuotaRestante) {
		this.cuotaRestante = cuotaRestante;
	}

    /**
     * @param nombrePlan the nombrePlan to set
     */
    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    /**
     * @return the nombrePlan
     */
    public String getNombrePlan() {
        return nombrePlan;
    }

    /**
     * @param porcentajeCuotaRestante the porcentajeCuotaRestante to set
     */
    public void setPorcentajeCuotaRestante(int porcentajeCuotaRestante) {
        this.porcentajeCuotaRestante = porcentajeCuotaRestante;
    }

    /**
     * @return the porcentajeCuotaRestante
     */
    public int getPorcentajeCuotaRestante() {
        return porcentajeCuotaRestante;
    }

    /**
     * @param valorTraficoAdicional the valorTraficoAdicional to set
     */
    public void setValorTraficoAdicional(String valorTraficoAdicional) {
        this.valorTraficoAdicional = valorTraficoAdicional;
    }

    /**
     * @return the valorTraficoAdicional
     */
    public String getValorTraficoAdicional() {
        return valorTraficoAdicional;
    }

    /**
     * @param umbralFairUse the umbralFairUse to set
     */
    public void setUmbralFairUse(String umbralFairUse) {
        this.umbralFairUse = umbralFairUse;
    }

    /**
     * @return the umbralFairUse
     */
    public String getUmbralFairUse() {
        return umbralFairUse;
    }

    /**
     * @param velocidadMaxPlan the velocidadMaxPlan to set
     */
    public void setVelocidadMaxPlan(String velocidadMaxPlan) {
        this.velocidadMaxPlan = velocidadMaxPlan;
    }

    /**
     * @return the velocidadMaxPlan
     */
    public String getVelocidadMaxPlan() {
        return velocidadMaxPlan;
    }

    /**
     * @param unidadUmbralFairUse the unidadUmbralFairUse to set
     */
    public void setUnidadUmbralFairUse(String unidadUmbralFairUse) {
        this.unidadUmbralFairUse = unidadUmbralFairUse;
    }

    /**
     * @return the unidadUmbralFairUse
     */
    public String getUnidadUmbralFairUse() {
        return unidadUmbralFairUse;
    }

    /**
     * @param ilimitado the ilimitado to set
     */
    public void setIlimitado(boolean ilimitado) {
        this.ilimitado = ilimitado;
    }

    /**
     * @return the ilimitado
     */
    public boolean isIlimitado() {
        return ilimitado;
    }

    /**
     * @param saldoMonto the saldoMonto to set
     */
    public void setSaldoMonto(String saldoMonto) {
        this.saldoMonto = saldoMonto;
    }

    /**
     * @return the saldoMonto
     */
    public String getSaldoMonto() {
        return saldoMonto;
    }

}
