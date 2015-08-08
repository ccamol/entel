package com.epcs.bean;


/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class DetalleConsultaSaldoSGOBean {
	
    private String nombrePlan;

    private String saldo;

    private String diasExpiracion;
    
    private boolean submercadoEMPSGO;
    
    public DetalleConsultaSaldoSGOBean(){
    	
    }

	public String getNombrePlan() {
		return nombrePlan;
	}

	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getDiasExpiracion() {
		return diasExpiracion;
	}

	public void setDiasExpiracion(String diasExpiracion) {
		this.diasExpiracion = diasExpiracion;
	}

	public boolean isSubmercadoEMPSGO() {
		return submercadoEMPSGO;
	}

	public void setSubmercadoEMPSGO(boolean submercadoEMPSGO) {
		this.submercadoEMPSGO = submercadoEMPSGO;
	}    
	
	
}
