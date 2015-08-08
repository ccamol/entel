/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.ArrayList;
import java.util.List;

import com.epcs.recursoti.configuracion.Utils;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ResumenBolsasDisponiblesBAMCCPP {
    
    //Informacion del movil
    private String mercado;
    private String msisdn;
    private String saldoPlan;
    private String saldoPlanFormatted;
    private String fechaExpiracionSaldo;
    private Integer grupo;
    private Double limiteCreditoTotal;
    private Double limiteCreditoAcumulado;
    //Bolsas Disponibles
    private List<BolsaDisponibleBAMCCPP> bolsas = new ArrayList<BolsaDisponibleBAMCCPP>();    
    
    /**
     * @return the mercado
     */
    public String getMercado() {
        return mercado;
    }

    /**
     * @param mercado the mercado to set
     */
    public void setMercado(String mercado) {
        this.mercado = mercado;
    }

    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the saldoPlan
     */
    public String getSaldoPlan() {
        return saldoPlan;
    }

    /**
     * @param saldoPlan the saldoPlan to set
     */
    public void setSaldoPlan(String saldoPlan) {
        this.saldoPlan = saldoPlan;
        this.saldoPlanFormatted = Utils.formatMoneyPuntos(new Double(saldoPlan));
    }

    /**
     * @return the saldoPlanFormatted
     */
    public String getSaldoPlanFormatted() {
        return saldoPlanFormatted;
    }

    /**
     * @return the fechaExpiracionSaldo
     */
    public String getFechaExpiracionSaldo() {
        return fechaExpiracionSaldo;
    }

    /**
     * @param fechaExpiracionSaldo the fechaExpiracionSaldo to set
     */
    public void setFechaExpiracionSaldo(String fechaExpiracionSaldo) {
        this.fechaExpiracionSaldo = fechaExpiracionSaldo;
    }

    /**
     * @return the grupo
     */
    public Integer getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the limiteCreditoTotal
     */
    public Double getLimiteCreditoTotal() {
        return limiteCreditoTotal;
    }

    /**
     * @param limiteCreditoTotal the limiteCreditoTotal to set
     */
    public void setLimiteCreditoTotal(Double limiteCreditoTotal) {
        this.limiteCreditoTotal = limiteCreditoTotal;
    }

    /**
     * @return the limiteCreditoAcumulado
     */
    public Double getLimiteCreditoAcumulado() {
        return limiteCreditoAcumulado;
    }

    /**
     * @param limiteCreditoAcumulado the limiteCreditoAcumulado to set
     */
    public void setLimiteCreditoAcumulado(Double limiteCreditoAcumulado) {
        this.limiteCreditoAcumulado = limiteCreditoAcumulado;
    }

    /**
     * @return the bolsas
     */
    public List<BolsaDisponibleBAMCCPP> getBolsas() {
        return bolsas;
    }

    /**
     * @param bolsas the bolsas to set
     */
    public void setBolsas(List<BolsaDisponibleBAMCCPP> bolsas) {
        this.bolsas = bolsas;
    }
    
    public void addBolsa(BolsaDisponibleBAMCCPP bolsa) {
        this.bolsas.add(bolsa);
    }
}