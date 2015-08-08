/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.ArrayList;
import java.util.List;

import com.epcs.recursoti.configuracion.Utils;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ResumenBolsasActivasBAMCCPP {
    
    //Informacion del movil
    private String mercado;
    private String msisdn;
    private Integer tariffIdOPSC;
    private String saldoPlan;
    private String saldoPlanFormatted;
    private String fechaExpiracionSaldo;
    private String cuotaUtilizadaPlan;    
    //Bolsas Activas
    private List<BolsaActivaBAMCCPP> bolsas = new ArrayList<BolsaActivaBAMCCPP>();
    
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
     * @return the tariffIdOPSC
     */
    public Integer getTariffIdOPSC() {
        return tariffIdOPSC;
    }

    /**
     * @param tariffIdOPSC the tariffIdOPSC to set
     */
    public void setTariffIdOPSC(Integer tariffIdOPSC) {
        this.tariffIdOPSC = tariffIdOPSC;
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
     * @return the cuotaUtilizadaPlan
     */
    public String getCuotaUtilizadaPlan() {
        return cuotaUtilizadaPlan;
    }

    /**
     * @param cuotaUtilizadaPlan the cuotaUtilizadaPlan to set
     */
    public void setCuotaUtilizadaPlan(String cuotaUtilizadaPlan) {
        this.cuotaUtilizadaPlan = cuotaUtilizadaPlan;
    }

    /**
     * @return the bolsas
     */
    public List<BolsaActivaBAMCCPP> getBolsas() {
        return bolsas;
    }

    /**
     * @param bolsas the bolsas to set
     */
    public void setBolsas(List<BolsaActivaBAMCCPP> bolsas) {
        this.bolsas = bolsas;
    }

    public void addBolsa(BolsaActivaBAMCCPP bolsa) {
        this.bolsas.add(bolsa);
    }
}
