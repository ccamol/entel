/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.appmobile.vo.Bundle;

import java.util.ArrayList;
import java.util.List;

import com.esa.ponline.appmobile.util.Formato;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * Aug 20, 2014
 */

public class AvailableBundleBAMCCPP {
    
    private String mercado;
    private String msisdn;
    private String saldoPlan;
    private String saldoPlanFormatted;
    private String fechaExpiracionSaldo;
    private Integer grupo;
    private Double limiteCreditoTotal;
    private Double limiteCreditoAcumulado;
    private List<BundleCCPPVO> bolsas = new ArrayList<BundleCCPPVO>();    
    
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

    public List<BundleCCPPVO> getBolsas() {
        return bolsas;
    }

    public void setBolsas(List<BundleCCPPVO> bolsas) {
        this.bolsas = bolsas;
    }
    
    public void addBolsa(BundleCCPPVO bolsa) {
        this.bolsas.add(bolsa);
    }
}