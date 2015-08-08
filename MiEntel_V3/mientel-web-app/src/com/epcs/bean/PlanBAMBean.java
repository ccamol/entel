/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */

public  class PlanBAMBean implements Serializable,Comparable<PlanBAMBean> {
	
	 private static final long serialVersionUID = 1L;
	 
	 private String tipoPlan;
	 
	 
	 private String codbscs1;
	 
	 private String codbscs2;
	 
	 private String nombrePlan;
	 
	 private Double cargoFijoPlan;
	 
	 private String umbralFairUseMb;
	 
	 private String umbralFairUseGb;
	 
	 private String tipoMercado;
	 
	 private String valorAdicionalMB;
	 
	 private String velocidadFairUse;
	 
	 /**
     * @return the umbralFairUseGb
     */
    public String getUmbralFairUseGb() {
        return umbralFairUseGb;
    }

    /**
     * @param umbralFairUseGb the umbralFairUseGb to set
     */
    public void setUmbralFairUseGb(String umbralFairUseGb) {
        this.umbralFairUseGb = umbralFairUseGb;
    }

    /**
     * @return the velocidadMaxPlan
     */
    public String getVelocidadMaxPlan() {
        return velocidadMaxPlan;
    }

    /**
     * @param velocidadMaxPlan the velocidadMaxPlan to set
     */
    public void setVelocidadMaxPlan(String velocidadMaxPlan) {
        this.velocidadMaxPlan = velocidadMaxPlan;
    }






    private String velocidadMaxPlan;
	 
	 
	 
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
	
	




	@Override
	public int compareTo(PlanBAMBean planBean) {
		if(Integer.parseInt(this.getCodbscs2()) < Integer.parseInt(planBean.getCodbscs2()))
            return -1;
        else if(Integer.parseInt(this.getCodbscs2()) == Integer.parseInt(planBean.getCodbscs2()))
            return 0;
        else
            return 1;

	}

    /**
     * @param tipoMercado the tipoMercado to set
     */
    public void setTipoMercado(String tipoMercado) {
        this.tipoMercado = tipoMercado;
    }

    /**
     * @return the tipoMercado
     */
    public String getTipoMercado() {
        return tipoMercado;
    }

    /**
     * @param valorAdicionalMB the valorAdicionalMB to set
     */
    public void setValorAdicionalMB(String valorAdicionalMB) {
        this.valorAdicionalMB = valorAdicionalMB;
    }

    /**
     * @return the valorAdicionalMB
     */
    public String getValorAdicionalMB() {
        return valorAdicionalMB;
    }

    /**
     * @param umbralFairUseMb the umbralFairUseMb to set
     */
    public void setUmbralFairUseMb(String umbralFairUseMb) {
        this.umbralFairUseMb = umbralFairUseMb;
    }

    /**
     * @return the umbralFairUseMb
     */
    public String getUmbralFairUseMb() {
        return umbralFairUseMb;
    }

    /**
     * 
     * @return
     */
	public String getVelocidadFairUse() {
		return velocidadFairUse;
	}

	/**
	 * 
	 * @param velocidadFairUse
	 */
	public void setVelocidadFairUse(String velocidadFairUse) {
		this.velocidadFairUse = velocidadFairUse;
	}
    
    
    
}
