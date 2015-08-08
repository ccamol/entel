/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.vo.menu.cc;

import java.io.Serializable;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class CuentaControladaVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3330091795874952908L;
	private String nombrePlan;
	private String saldo;
	private String fechaExpiracion;

    //CC3 monedero secundario
    private String saldoSecundario;
    private String vigenciaSecundario;

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

	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

    /**
     * @return the saldoSecundario
     */
    public String getSaldoSecundario() {
        return saldoSecundario;
    }

    /**
     * @param saldoSecundario the saldoSecundario to set
     */
    public void setSaldoSecundario(String saldoSecundario) {
        this.saldoSecundario = saldoSecundario;
    }

    /**
     * @return the vigenciaSecundario
     */
    public String getVigenciaSecundario() {
        return vigenciaSecundario;
    }

    /**
     * @param vigenciaSecundario the vigenciaSecundario to set
     */
    public void setVigenciaSecundario(String vigenciaSecundario) {
        this.vigenciaSecundario = vigenciaSecundario;
    }
	
	

}
