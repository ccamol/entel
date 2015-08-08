/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ResumenTraficoBAMCCBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String codPlan;
    private Date fechaActivacion;
    private Date fechaDesactivacion;
    private Date fechaExpiracion;
    private double saldoMonto;
    private String saldoNavegacion;
    /**
     * @return the codPlan
     */
    public String getCodPlan() {
        return codPlan;
    }
    /**
     * @param codPlan the codPlan to set
     */
    public void setCodPlan(String codPlan) {
        this.codPlan = codPlan;
    }
    /**
     * @return the fechaActivacion
     */
    public Date getFechaActivacion() {
        return fechaActivacion;
    }
    /**
     * @param fechaActivacion the fechaActivacion to set
     */
    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }
    /**
     * @return the fechaDesactivacion
     */
    public Date getFechaDesactivacion() {
        return fechaDesactivacion;
    }
    /**
     * @param fechaDesactivacion the fechaDesactivacion to set
     */
    public void setFechaDesactivacion(Date fechaDesactivacion) {
        this.fechaDesactivacion = fechaDesactivacion;
    }
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
     * @return the saldoMonto
     */
    public double getSaldoMonto() {
        return saldoMonto;
    }
    /**
     * @param saldoMonto the saldoMonto to set
     */
    public void setSaldoMonto(double saldoMonto) {
        this.saldoMonto = saldoMonto;
    }
    /**
     * @return the saldoNavegacion
     */
    public String getSaldoNavegacion() {
        return saldoNavegacion;
    }
    /**
     * @param saldoNavegacion the saldoNavegacion to set
     */
    public void setSaldoNavegacion(String saldoNavegacion) {
        this.saldoNavegacion = saldoNavegacion;
    }

}
