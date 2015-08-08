/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.esa.ponline.portalbolsas.util.Formato;

//import com.esa.ponline.appmobile.util.Formato;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * 
 * 
 */

public class BolsaDisponibleMM implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String mercado;
    private String msisdn;
    private String saldoPlan;
    private String saldoPlanFormatted;
    private String fechaExpiracionSaldo;
    private Integer grupo;
    private Double limiteCreditoTotal;
    private Double limiteCreditoAcumulado;
    private List<BolsaComprar> bolsas = new ArrayList<BolsaComprar>();    
    
    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        this.mercado = mercado;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSaldoPlan() {
        return saldoPlan;
    }

    public void setSaldoPlan(String saldoPlan) {
        this.saldoPlan = saldoPlan;
        this.saldoPlanFormatted = Formato.formatMoneyPuntos(new Double(saldoPlan));
    }

    public String getSaldoPlanFormatted() {
        return saldoPlanFormatted;
    }

    public String getFechaExpiracionSaldo() {
        return fechaExpiracionSaldo;
    }

    public void setFechaExpiracionSaldo(String fechaExpiracionSaldo) {
        this.fechaExpiracionSaldo = fechaExpiracionSaldo;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Double getLimiteCreditoTotal() {
        return limiteCreditoTotal;
    }

    public void setLimiteCreditoTotal(Double limiteCreditoTotal) {
        this.limiteCreditoTotal = limiteCreditoTotal;
    }

    public Double getLimiteCreditoAcumulado() {
        return limiteCreditoAcumulado;
    }

    public void setLimiteCreditoAcumulado(Double limiteCreditoAcumulado) {
        this.limiteCreditoAcumulado = limiteCreditoAcumulado;
    }

    public List<BolsaComprar> getBolsas() {
        return bolsas;
    }

    public void setBolsas(List<BolsaComprar> bolsas) {
        this.bolsas = bolsas;
    }
    
    public void addBolsa(BolsaComprar bolsa) {
        this.bolsas.add(bolsa);
    }
}