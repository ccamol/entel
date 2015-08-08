/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ValidacionCompraBolsaRegaloBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String comando;
    private String msisdnRegalo;
    /**
     * @return the comando
     */
    public String getComando() {
        return comando;
    }
    /**
     * @param comando the comando to set
     */
    public void setComando(String comando) {
        this.comando = comando;
    }
    /**
     * @return the msisdnRegalo
     */
    public String getMsisdnRegalo() {
        return msisdnRegalo;
    }
    /**
     * @param msisdnRegalo the msisdnRegalo to set
     */
    public void setMsisdnRegalo(String msisdnRegalo) {
        this.msisdnRegalo = msisdnRegalo;
    } 
}
