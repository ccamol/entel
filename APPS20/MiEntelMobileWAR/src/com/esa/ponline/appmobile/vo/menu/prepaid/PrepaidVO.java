/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.vo.menu.prepaid;

import java.io.Serializable;
import java.util.List;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class PrepaidVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1663310490116227743L;
	private List<String> descripcionPlan;
	private String nombrePlan;
	private String tipoPlan;
	private String saldo;
	private String fechaExpiracion;
	private String saldoReservado;
	private String fechaExpSaldoReservado;
	
	//CC3 monedero secundario
	private String saldoSecundario;
	private String vigenciaSecundario;
	
	
	
	//TODO: NO deberia estar aca
//	public String getDescripcionPlan() {
//		String temp="<li>" + descripcionPlan.replace(".", ".</li><li>").trim();		
//		return temp.substring(0, temp.length()-4);
//	}
    public List<String> getDescripcionPlan() {
        return descripcionPlan;
    }
    public void setDescripcionPlan(List<String> descripcionPlan) {
        this.descripcionPlan = descripcionPlan;
    }
	
	public String getNombrePlan() {
		return nombrePlan;
	}
	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}
	public String getTipoPlan() {
		return tipoPlan;
	}
	public void setTipoPlan(String tipoPlan) {
		this.tipoPlan = tipoPlan;
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
	public String getSaldoReservado() {
		return saldoReservado;
	}
	public void setSaldoReservado(String saldoReservado) {
		this.saldoReservado = saldoReservado;
	}
	public String getFechaExpSaldoReservado() {
		return fechaExpSaldoReservado;
	}
	public void setFechaExpSaldoReservado(String fechaExpSaldoReservado) {
		this.fechaExpSaldoReservado = fechaExpSaldoReservado;
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
