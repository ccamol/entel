/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.bean;

import java.io.Serializable;
import java.util.Date;


/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */

public class DetalleRecargasBean implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private String regionRecarga;
	
	private String comunaRecarga;
	
	private String distribuidorRecarga;
	
	private Date fechaRecarga;
	
	private String msisdnRecarga;
	
	private String terminalRecarga;
	
	private String plataformaRecarga;
	
	private String tipoRecarga;
	
	private String comercioRecarga;
	
	private String localRecarga;
	
	private String codigoPriRecarga;
	
	private String subTipoRecarga;
	
	private Double montoRecarga;
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getRegionRecarga() {
		return regionRecarga;
	}
	
	/**
	 * 
	 * @param regionRecarga
	 */
	public void setRegionRecarga(String regionRecarga) {
		this.regionRecarga = regionRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public String getComunaRecarga() {
		return comunaRecarga;
	}

	/**
	 * 
	 * @param comunaRecarga
	 */
	public void setComunaRecarga(String comunaRecarga) {
		this.comunaRecarga = comunaRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public String getDistribuidorRecarga() {
		return distribuidorRecarga;
	}

	/**
	 * 
	 * @param distribuidorRecarga
	 */
	public void setDistribuidorRecarga(String distribuidorRecarga) {
		this.distribuidorRecarga = distribuidorRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaRecarga() {
		return fechaRecarga;
	}

	/**
	 * 
	 * @param fechaRecarga
	 */
	public void setFechaRecarga(Date fechaRecarga) {
		this.fechaRecarga = fechaRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public String getMsisdnRecarga() {
		return msisdnRecarga;
	}

	/**
	 * 
	 * @param msisdnRecarga
	 */
	public void setMsisdnRecarga(String msisdnRecarga) {
		this.msisdnRecarga = msisdnRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public String getTerminalRecarga() {
		return terminalRecarga;
	}

	/**
	 * 
	 * @param terminalRecarga
	 */
	public void setTerminalRecarga(String terminalRecarga) {
		this.terminalRecarga = terminalRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public String getPlataformaRecarga() {
		return plataformaRecarga;
	}

	/**
	 * 
	 * @param plataformaRecarga
	 */
	public void setPlataformaRecarga(String plataformaRecarga) {
		this.plataformaRecarga = plataformaRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public String getTipoRecarga() {
		return tipoRecarga;
	}

	/**
	 * 
	 * @param tipoRecarga
	 */
	public void setTipoRecarga(String tipoRecarga) {
		this.tipoRecarga = tipoRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public String getComercioRecarga() {
		return comercioRecarga;
	}

	/**
	 * 
	 * @param comercioRecarga
	 */
	public void setComercioRecarga(String comercioRecarga) {
		this.comercioRecarga = comercioRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public String getLocalRecarga() {
		return localRecarga;
	}

	/**
	 * 
	 * @param localRecarga
	 */
	public void setLocalRecarga(String localRecarga) {
		this.localRecarga = localRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public String getCodigoPriRecarga() {
		return codigoPriRecarga;
	}

	/**
	 * 
	 * @param codigoPriRecarga
	 */
	public void setCodigoPriRecarga(String codigoPriRecarga) {
		this.codigoPriRecarga = codigoPriRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public String getSubTipoRecarga() {
		return subTipoRecarga;
	}

	/**
	 * 
	 * @param subTipoRecarga
	 */
	public void setSubTipoRecarga(String subTipoRecarga) {
		this.subTipoRecarga = subTipoRecarga;
	}

	/**
	 * 
	 * @return
	 */
	public Double getMontoRecarga() {
		return montoRecarga;
	}

	/**
	 * 
	 * @param montoRecarga
	 */
	public void setMontoRecarga(Double montoRecarga) {
		this.montoRecarga = montoRecarga;
	}
	

}
