/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class MsisdnCobroRevertidoBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String msisdn;
    private java.util.Date fechaInscripcion;
    
    public MsisdnCobroRevertidoBean(String msisdn, java.util.Date fechaInscripcion){
        this.msisdn = msisdn;
        this.fechaInscripcion = fechaInscripcion;
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
     * @return the fechaInscripcion
     */
    public java.util.Date getFechaInscripcion() {
        return fechaInscripcion;
    }
    /**
     * @param fechaInscripcion the fechaInscripcion to set
     */
    public void setFechaInscripcion(java.util.Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
    

}
