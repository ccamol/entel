/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ServicioCobroRevertidoBean extends ServicioBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String msisdn;
    private String estadoSponsor;
    private Double valorMinutoPrepago;
    private Double maximoMensual;
    private Double saldoMes;
    private String saldoMesMinutos;
    private String msisdnAsociadosToken = null;
    
    private List<MsisdnCobroRevertidoBean> msisdnAsociados = new ArrayList<MsisdnCobroRevertidoBean>(); 
        
    
    public ServicioCobroRevertidoBean(){
        super();
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
     * @param msisdnAsociados the msisdnAsociados to set
     */
    public void setMsisdnAsociados(List<MsisdnCobroRevertidoBean> msisdnAsociados) {
        this.msisdnAsociados = msisdnAsociados;
    }


    /**
     * @return the msisdnAsociados
     */
    public List<MsisdnCobroRevertidoBean> getMsisdnAsociados() {
        return msisdnAsociados;
    }
    
    /**
     * adicionar un nuevo msisdn
     */
    public void addMsisdn(MsisdnCobroRevertidoBean bean){
        this.msisdnAsociados.add(bean);
    }


    /**
     * @param estadoSponsor the estadoSponsor to set
     */
    public void setEstadoSponsor(String estadoSponsor) {
        this.estadoSponsor = estadoSponsor;
    }


    /**
     * @return the estadoSponsor
     */
    public String getEstadoSponsor() {
        return estadoSponsor;
    }


    /**
     * @return the valorMinuto
     */
    public Double getValorMinutoPrepago() {
        return valorMinutoPrepago;
    }


    /**
     * @param valorMinuto the valorMinuto to set
     */
    public void setValorMinutoPrepago(Double valorMinutoPrepago) {
        this.valorMinutoPrepago = valorMinutoPrepago;
    }


    /**
     * @return the maximoMensual
     */
    public Double getMaximoMensual() {
        return maximoMensual;
    }


    /**
     * @param maximoMensual the maximoMensual to set
     */
    public void setMaximoMensual(Double maximoMensual) {
        this.maximoMensual = maximoMensual;
    }


    /**
     * @return the saldoMes
     */
    public Double getSaldoMes() {
        return saldoMes;
    }


    /**
     * @param saldoMes the saldoMes to set
     */
    public void setSaldoMes(Double saldoMes) {
        this.saldoMes = saldoMes;
    }





    /**
     * @param msisdnAsociadosToken the msisdnAsociadosToken to set
     */
    public void setMsisdnAsociadosToken(String msisdnAsociadosToken) {
        this.msisdnAsociadosToken = msisdnAsociadosToken;
    }

    /**
     * @return the msisdnAsociadosToken
     */
    public String getMsisdnAsociadosToken() {
        if(msisdnAsociadosToken == null){
            msisdnAsociadosToken = ""; 
        for (MsisdnCobroRevertidoBean bean : msisdnAsociados) {
            msisdnAsociadosToken = msisdnAsociadosToken + (bean.getMsisdn() + ",");
        }
        }
        return msisdnAsociadosToken;
    }





    /**
     * @param saldoMesMinutos the saldoMesMinutos to set
     */
    public void setSaldoMesMinutos(String saldoMesMinutos) {
        this.saldoMesMinutos = saldoMesMinutos;
    }





    /**
     * @return the saldoMesMinutos
     */
    public String getSaldoMesMinutos() {
        return saldoMesMinutos;
    }
    
    

    
}
