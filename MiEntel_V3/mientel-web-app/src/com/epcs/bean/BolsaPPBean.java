/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class BolsaPPBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fechaExpiracion;
    private Double saldo;
    private String instanciaBolsa;
    private String codBolsa;
    private String nombreBolsa;
    private List<String> descBolsa;
    private String tipoBolsa;
    private String cantidadBolsa;
    private Double valorBolsa;
    private String unidad;
    private String descSaldo;
    private String caracteristicas;
    private String descComercial;
    private String orden;
    private String saldoString;
    private String tipoBolsaPlus;

    /**
     * @return the fechaExpiracion
     */
    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }
    /**
     * @param fechaExpiracion the fechaExpiracion to set
     */
    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    /**
     * @return the saldo
     */
    public Double getSaldo() {
        return saldo;
    }
    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    /**
     * @return the instanciaBolsa
     */
    public String getInstanciaBolsa() {
        return instanciaBolsa;
    }
    /**
     * @param instanciaBolsa the instanciaBolsa to set
     */
    public void setInstanciaBolsa(String instanciaBolsa) {
        this.instanciaBolsa = instanciaBolsa;
    }
    /**
     * @return the codBolsa
     */
    public String getCodBolsa() {
        return codBolsa;
    }
    /**
     * @param codBolsa the codBolsa to set
     */
    public void setCodBolsa(String codBolsa) {
        this.codBolsa = codBolsa;
    }
    /**
     * @return the nombreBolsa
     */
    public String getNombreBolsa() {
        return nombreBolsa;
    }
    /**
     * @param nombreBolsa the nombreBolsa to set
     */
    public void setNombreBolsa(String nombreBolsa) {
        this.nombreBolsa = nombreBolsa;
    }
    /**
     * @return the descBolsa
     */
    public List<String> getDescBolsa() {
        return descBolsa;
    }
    /**
     * @param descBolsa the descBolsa to set
     */
    public void setDescBolsa(List<String> descBolsa) {
        this.descBolsa = descBolsa;
    }
    /**
     * @return the tipoBolsa
     */
    public String getTipoBolsa() {
        return tipoBolsa;
    }
    /**
     * @param tipoBolsa the tipoBolsa to set
     */
    public void setTipoBolsa(String tipoBolsa) {
        this.tipoBolsa = tipoBolsa;
    }
    /**
     * @return the cantidadBolsa
     */
    public String getCantidadBolsa() {
        return cantidadBolsa;
    }
    /**
     * @param cantidadBolsa the cantidadBolsa to set
     */
    public void setCantidadBolsa(String cantidadBolsa) {
        this.cantidadBolsa = cantidadBolsa;
    }
    /**
     * @return the valorBolsa
     */
    public Double getValorBolsa() {
        return valorBolsa;
    }
    /**
     * @param valorBolsa the valorBolsa to set
     */
    public void setValorBolsa(Double valorBolsa) {
        this.valorBolsa = valorBolsa;
    }
    /**
     * @return the unidad
     */
    public String getUnidad() {
        return unidad;
    }
    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
	public String getDescSaldo() {
		return descSaldo;
	}
	public void setDescSaldo(String descSaldo) {
		this.descSaldo = descSaldo;
	}	
	public String getDescComercial() {
		return descComercial;
	}
	public void setDescComercial(String descComercial) {
		this.descComercial = descComercial;
	}
	/**
	 * @return the caracteristicas
	 */
	public String getCaracteristicas() {
		return caracteristicas;
	}
	/**
	 * @param caracteristicas the caracteristicas to set
	 */
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	/**
	 * @return the saldoString
	 */
	public String getSaldoString() {
		return saldoString;
	}
	/**
	 * @param saldoString the saldoString to set
	 */
	public void setSaldoString(String saldoString) {
		this.saldoString = saldoString;
	}
	   /**
     * @param bolsaPlus
     */
    public void setTipoBolsaPlus(String tipoBolsaPlus) {
        this.tipoBolsaPlus = tipoBolsaPlus;
    }
    /**
     * @return  bolsaPlus
     */
    public String getTipoBolsaPlus() {
        return this.tipoBolsaPlus;
    }
   
	

}
