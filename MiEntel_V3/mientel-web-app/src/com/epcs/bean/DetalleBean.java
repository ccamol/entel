/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.Date;


/**
 * @author User (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class DetalleBean {
    
    private Date fechaActual;
    private Integer totalPuntos;
    private Date fechaVencimiento;
    private Integer vencimientoPuntos;
    
    public DetalleBean(){
        
    }

    /**
     * @return the fechaActual
     */
    public Date getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    /**
     * @return the totalPuntos
     */
    public Integer getTotalPuntos() {
        return totalPuntos;
    }

    /**
     * @param totalPuntos the totalPuntos to set
     */
    public void setTotalPuntos(Integer totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the vencimientoPuntos
     */
    public Integer getVencimientoPuntos() {
        return vencimientoPuntos;
    }

    /**
     * @param vencimientoPuntos the vencimientoPuntos to set
     */
    public void setVencimientoPuntos(Integer vencimientoPuntos) {
        this.vencimientoPuntos = vencimientoPuntos;
    }

}
