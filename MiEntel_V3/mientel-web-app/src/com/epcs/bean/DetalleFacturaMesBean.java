/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class DetalleFacturaMesBean implements Serializable{
    
    private static final long serialVersionUID = 1585325587372163225L;

    private Double totalActualMes;
    
    private Double saldoAnteriorMes;
    
    private Double totalPagoMes;
    
    private Date fechaPeriodoMes;
    
    private Date fechaEmisionMes;
    
    private Date fechaVencimientoMes;
    
    private String estadoMes;
    
    private String codEstadoMes;
    
    private String urlImagenFactura;
    
    public DetalleFacturaMesBean(){
        
    }

    /**
     * @return the totalActualMes
     */
    public Double getTotalActualMes() {
        return totalActualMes;
    }

    /**
     * @param totalActualMes the totalActualMes to set
     */
    public void setTotalActualMes(Double totalActualMes) {
        this.totalActualMes = totalActualMes;
    }

    /**
     * @return the saldoAnteriorMes
     */
    public Double getSaldoAnteriorMes() {
        return saldoAnteriorMes;
    }

    /**
     * @param saldoAnteriorMes the saldoAnteriorMes to set
     */
    public void setSaldoAnteriorMes(Double saldoAnteriorMes) {
        this.saldoAnteriorMes = saldoAnteriorMes;
    }

    /**
     * @return the totalPagoMes
     */
    public Double getTotalPagoMes() {
        return totalPagoMes;
    }

    /**
     * @param totalPagoMes the totalPagoMes to set
     */
    public void setTotalPagoMes(Double totalPagoMes) {
        this.totalPagoMes = totalPagoMes;
    }

    /**
     * @return the fechaPeriodoMes
     */
    public Date getFechaPeriodoMes() {
        return fechaPeriodoMes;
    }

    /**
     * @param fechaPeriodoMes the fechaPeriodoMes to set
     */
    public void setFechaPeriodoMes(Date fechaPeriodoMes) {
        this.fechaPeriodoMes = fechaPeriodoMes;
    }

    /**
     * @return the fechaEmisionMes
     */
    public Date getFechaEmisionMes() {
        return fechaEmisionMes;
    }

    /**
     * @param fechaEmisionMes the fechaEmisionMes to set
     */
    public void setFechaEmisionMes(Date fechaEmisionMes) {
        this.fechaEmisionMes = fechaEmisionMes;
    }

    /**
     * @return the fechaVencimientoMes
     */
    public Date getFechaVencimientoMes() {
        return fechaVencimientoMes;
    }

    /**
     * @param fechaVencimientoMes the fechaVencimientoMes to set
     */
    public void setFechaVencimientoMes(Date fechaVencimientoMes) {
        this.fechaVencimientoMes = fechaVencimientoMes;
    }

    /**
     * @return the estadoMes
     */
    public String getEstadoMes() {
        return estadoMes;
    }

    /**
     * @param estadoMes the estadoMes to set
     */
    public void setEstadoMes(String estadoMes) {
        this.estadoMes = estadoMes;
    }

    /**
     * @return the urlImagenFactura
     */
    public String getUrlImagenFactura() {
        return urlImagenFactura;
    }

    /**
     * @param urlImagenFactura the urlImagenFactura to set
     */
    public void setUrlImagenFactura(String urlImagenFactura) {
        this.urlImagenFactura = urlImagenFactura;
    }

	public String getCodEstadoMes() {
		return codEstadoMes;
	}

	public void setCodEstadoMes(String codEstadoMes) {
		this.codEstadoMes = codEstadoMes;
	}
    
}
