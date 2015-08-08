/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.List;


/**
 * @author jaraujo (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class PaginaHistorialCanjeBean {
    
	private List<HistorialDetalleBean> listaDetalle;	
	private String totalPuntos;
	
	/**
	 * @return the listaDetalle
	 */
	public List<HistorialDetalleBean> getListaDetalle() {
		return listaDetalle;
	}
	/**
	 * @param listaDetalle the listaDetalle to set
	 */
	public void setListaDetalle(List<HistorialDetalleBean> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}
	/**
	 * @return the totalPuntos
	 */
	public String getTotalPuntos() {
		return totalPuntos;
	}
	/**
	 * @param totalPuntos the totalPuntos to set
	 */
	public void setTotalPuntos(String totalPuntos) {
		this.totalPuntos = totalPuntos;
	}
	
}
