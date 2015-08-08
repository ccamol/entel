/**
 * 
 */
package com.esa.ponline.appmobile.vo.account;

import java.io.Serializable;


/**
 * @author tano
 *
 */

public class AccountVO implements Serializable {
	
	private static final long serialVersionUID = -9205724140906413840L;
	
	private String totalActualMes;
    private String saldoAnteriorMes;
    private String totalPagoMes;
    private String fechaPeriodoMes;
    private String fechaEmisionMes;
    private String fechaVencimientoMes;
    private String estadoMes;
    private String urlImagenFactura;
    
    
	public String getTotalActualMes() {
		return totalActualMes;
	}
	public void setTotalActualMes(String totalActualMes) {
		this.totalActualMes = totalActualMes;
	}
	public String getSaldoAnteriorMes() {
		return saldoAnteriorMes;
	}
	public void setSaldoAnteriorMes(String saldoAnteriorMes) {
		this.saldoAnteriorMes = saldoAnteriorMes;
	}
	public String getTotalPagoMes() {
		return totalPagoMes;
	}
	public void setTotalPagoMes(String totalPagoMes) {
		this.totalPagoMes = totalPagoMes;
	}
	public String getFechaPeriodoMes() {
		return fechaPeriodoMes;
	}
	public void setFechaPeriodoMes(String fechaPeriodoMes) {
		this.fechaPeriodoMes = fechaPeriodoMes;
	}
	public String getFechaEmisionMes() {
		return fechaEmisionMes;
	}
	public void setFechaEmisionMes(String fechaEmisionMes) {
		this.fechaEmisionMes = fechaEmisionMes;
	}
	public String getFechaVencimientoMes() {
		return fechaVencimientoMes;
	}
	public void setFechaVencimientoMes(String fechaVencimientoMes) {
		this.fechaVencimientoMes = fechaVencimientoMes;
	}
	public String getEstadoMes() {
		return estadoMes;
	}
	public void setEstadoMes(String estadoMes) {
		this.estadoMes = estadoMes;
	}
	public String getUrlImagenFactura() {
		return urlImagenFactura;
	}
	public void setUrlImagenFactura(String urlImagenFactura) {
		this.urlImagenFactura = urlImagenFactura;
	}

    
    
}
