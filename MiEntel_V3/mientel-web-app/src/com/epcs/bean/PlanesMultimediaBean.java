/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */

public class PlanesMultimediaBean {

    private String fechaDiaMesFormat;
    
    private String fechaDiaMesHoraFormat;
    
    private String velocidadPlan;
    
    private String cuotaTraficoUtil;
    
    private String valorTotalTrafico;
    
    private String valorMBExcedido;
    
    private String traficoExcedido;

    private String totalTrafico;
    
    private String clasificacionPlan;
    
    private String codigoPlanBSCS;
    
    private String nombrePlanBSCS;
    
    private String descPlanBSCS;
    
    private String promConsumo;
    
    private String promConsumoSinDec;
    
    private boolean tieneValorExcedido=true;
    
    
    private String condProrrateo;
  
    
    public PlanesMultimediaBean(){
        
    }

	/**
	 * @param fechaDiaMesFormat
	 * @param fechaDiaMesHoraFormat
	 * @param velocidadPlan
	 * @param cuotaTraficoUtil
	 * @param valorTotalTrafico
	 * @param valorMBExcedido
	 * @param traficoExcedido
	 * @param totalTrafico
	 * @param clasificacionPlan
	 * @param codigoPlanBSCS
	 * @param nombrePlanBSCS
	 * @param descPlanBSCS
	 * @param promConsumo
	 * @param promConsumoSinDec
	 * @param condProrrateo
	 */
	public PlanesMultimediaBean(String fechaDiaMesFormat,
			String fechaDiaMesHoraFormat, String velocidadPlan,
			String cuotaTraficoUtil, String valorTotalTrafico,
			String valorMBExcedido, String traficoExcedido,
			String totalTrafico, String clasificacionPlan,
			String codigoPlanBSCS, String nombrePlanBSCS, String descPlanBSCS,
			String promConsumo, String promConsumoSinDec, String condProrrateo) {
		super();
		this.fechaDiaMesFormat = fechaDiaMesFormat;
		this.fechaDiaMesHoraFormat = fechaDiaMesHoraFormat;
		this.velocidadPlan = velocidadPlan;
		this.cuotaTraficoUtil = cuotaTraficoUtil;
		this.valorTotalTrafico = valorTotalTrafico;
		this.valorMBExcedido = valorMBExcedido;
		this.traficoExcedido = traficoExcedido;
		this.totalTrafico = totalTrafico;
		this.clasificacionPlan = clasificacionPlan;
		this.codigoPlanBSCS = codigoPlanBSCS;
		this.nombrePlanBSCS = nombrePlanBSCS;
		this.descPlanBSCS = descPlanBSCS;
		this.promConsumo = promConsumo;
		this.promConsumoSinDec = promConsumoSinDec;
		this.condProrrateo = condProrrateo;
	}


	/**
	 * @return the fechaDiaMesFormat
	 */
	public String getFechaDiaMesFormat() {
		return fechaDiaMesFormat;
	}



	/**
	 * @param fechaDiaMesFormat the fechaDiaMesFormat to set
	 */
	public void setFechaDiaMesFormat(String fechaDiaMesFormat) {
		this.fechaDiaMesFormat = fechaDiaMesFormat;
	}



	/**
	 * @return the fechaDiaMesHoraFormat
	 */
	public String getFechaDiaMesHoraFormat() {
		return fechaDiaMesHoraFormat;
	}



	/**
	 * @param fechaDiaMesHoraFormat the fechaDiaMesHoraFormat to set
	 */
	public void setFechaDiaMesHoraFormat(String fechaDiaMesHoraFormat) {
		this.fechaDiaMesHoraFormat = fechaDiaMesHoraFormat;
	}



	/**
	 * @return the velocidadPlan
	 */
	public String getVelocidadPlan() {
		return velocidadPlan;
	}



	/**
	 * @param velocidadPlan the velocidadPlan to set
	 */
	public void setVelocidadPlan(String velocidadPlan) {
		this.velocidadPlan = velocidadPlan;
	}



	/**
	 * @return the cuotaTraficoUtil
	 */
	public String getCuotaTraficoUtil() {
		return cuotaTraficoUtil;
	}



	/**
	 * @param cuotaTraficoUtil the cuotaTraficoUtil to set
	 */
	public void setCuotaTraficoUtil(String cuotaTraficoUtil) {
		this.cuotaTraficoUtil = cuotaTraficoUtil;
	}



	/**
	 * @return the valorTotalTrafico
	 */
	public String getValorTotalTrafico() {
		return valorTotalTrafico;
	}



	/**
	 * @param valorTotalTrafico the valorTotalTrafico to set
	 */
	public void setValorTotalTrafico(String valorTotalTrafico) {
		this.valorTotalTrafico = valorTotalTrafico;
	}



	/**
	 * @return the valorMBExcedido
	 */
	public String getValorMBExcedido() {
		return valorMBExcedido;
	}



	/**
	 * @param valorMBExcedido the valorMBExcedido to set
	 */
	public void setValorMBExcedido(String valorMBExcedido) {
		this.valorMBExcedido = valorMBExcedido;
	}



	/**
	 * @return the traficoExcedido
	 */
	public String getTraficoExcedido() {
		return traficoExcedido;
	}



	/**
	 * @param traficoExcedido the traficoExcedido to set
	 */
	public void setTraficoExcedido(String traficoExcedido) {
		this.traficoExcedido = traficoExcedido;
	}



	/**
	 * @return the totalTrafico
	 */
	public String getTotalTrafico() {
		return totalTrafico;
	}



	/**
	 * @param totalTrafico the totalTrafico to set
	 */
	public void setTotalTrafico(String totalTrafico) {
		this.totalTrafico = totalTrafico;
	}



	/**
	 * @return the clasificacionPlan
	 */
	public String getClasificacionPlan() {
		return clasificacionPlan;
	}



	/**
	 * @param clasificacionPlan the clasificacionPlan to set
	 */
	public void setClasificacionPlan(String clasificacionPlan) {
		this.clasificacionPlan = clasificacionPlan;
	}



	/**
	 * @return the codigoPlanBSCS
	 */
	public String getCodigoPlanBSCS() {
		return codigoPlanBSCS;
	}



	/**
	 * @param codigoPlanBSCS the codigoPlanBSCS to set
	 */
	public void setCodigoPlanBSCS(String codigoPlanBSCS) {
		this.codigoPlanBSCS = codigoPlanBSCS;
	}



	/**
	 * @return the nombrePlanBSCS
	 */
	public String getNombrePlanBSCS() {
		return nombrePlanBSCS;
	}



	/**
	 * @param nombrePlanBSCS the nombrePlanBSCS to set
	 */
	public void setNombrePlanBSCS(String nombrePlanBSCS) {
		this.nombrePlanBSCS = nombrePlanBSCS;
	}



	/**
	 * @return the descPlanBSCS
	 */
	public String getDescPlanBSCS() {
		return descPlanBSCS;
	}



	/**
	 * @param descPlanBSCS the descPlanBSCS to set
	 */
	public void setDescPlanBSCS(String descPlanBSCS) {
		this.descPlanBSCS = descPlanBSCS;
	}



	/**
	 * @return the promConsumo
	 */
	public String getPromConsumo() {
		return promConsumo;
	}



	/**
	 * @param promConsumo the promConsumo to set
	 */
	public void setPromConsumo(String promConsumo) {
		this.promConsumo = promConsumo;
	}



	/**
	 * @return the promConsumoSinDec
	 */
	public String getPromConsumoSinDec() {
		return promConsumoSinDec;
	}



	/**
	 * @param promConsumoSinDec the promConsumoSinDec to set
	 */
	public void setPromConsumoSinDec(String promConsumoSinDec) {
		this.promConsumoSinDec = promConsumoSinDec;
	}


	/**
	 * @return the condProrrateo
	 */
	public String getCondProrrateo() {
		return condProrrateo;
	}




	/**
	 * @param condProrrateo the condProrrateo to set
	 */
	public void setCondProrrateo(String condProrrateo) {
		this.condProrrateo = condProrrateo;
	}

	public boolean isTieneValorExcedido() {
		return tieneValorExcedido;
	}

	public void setTieneValorExcedido(boolean tieneValorExcedido) {
		this.tieneValorExcedido = tieneValorExcedido;
	}
    

	
	
    
}
