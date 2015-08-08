/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jroman (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoBamProductoBean implements Serializable,Comparable<TraficoBamProductoBean> {
	
	private static final long serialVersionUID = 1L;
	
	 private String msisdn;
	
	 private String tipoPlan;
	 
	 private String codbscs1;
	 
	 private String codbscs2;
	 
	 private String nombrePlan;
	 
	 private String tipoTasacion;
	 
	 private Date fechaRegistro;
	 
	 private String idProducto;
	 
	 private String key1;
	 
	 private String key2;
	 
	 private String key3;	
	 
	 private String key4;
	 
	 private String key5;
	 
	 private String key6;
	 
	 private String key7;

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
	 * @return the tipoPlan
	 */
	public String getTipoPlan() {
		return tipoPlan;
	}

	/**
	 * @param tipoPlan the tipoPlan to set
	 */
	public void setTipoPlan(String tipoPlan) {
		this.tipoPlan = tipoPlan;
	}

	/**
	 * @return the codbscs1
	 */
	public String getCodbscs1() {
		return codbscs1;
	}

	/**
	 * @param codbscs1 the codbscs1 to set
	 */
	public void setCodbscs1(String codbscs1) {
		this.codbscs1 = codbscs1;
	}

	/**
	 * @return the codbscs2
	 */
	public String getCodbscs2() {
		return codbscs2;
	}

	/**
	 * @param codbscs2 the codbscs2 to set
	 */
	public void setCodbscs2(String codbscs2) {
		this.codbscs2 = codbscs2;
	}

	/**
	 * @return the nombrePlan
	 */
	public String getNombrePlan() {
		return nombrePlan;
	}

	/**
	 * @param nombrePlan the nombrePlan to set
	 */
	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}

	/**
	 * @return the tipoTasacion
	 */
	public String getTipoTasacion() {
		return tipoTasacion;
	}

	/**
	 * @param tipoTasacion the tipoTasacion to set
	 */
	public void setTipoTasacion(String tipoTasacion) {
		this.tipoTasacion = tipoTasacion;
	}

	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return the idProducto
	 */
	public String getIdProducto() {
		return idProducto;
	}

	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	

	/**
	 * @return the key1
	 */
	public String getKey1() {
		return key1;
	}

	/**
	 * @param key1 the key1 to set
	 */
	public void setKey1(String key1) {
		this.key1 = key1;
	}

	/**
	 * @return the key2
	 */
	public String getKey2() {
		return key2;
	}

	/**
	 * @param key2 the key2 to set
	 */
	public void setKey2(String key2) {
		this.key2 = key2;
	}

	/**
	 * @return the key3
	 */
	public String getKey3() {
		return key3;
	}

	/**
	 * @param key3 the key3 to set
	 */
	public void setKey3(String key3) {
		this.key3 = key3;
	}

	/**
	 * @return the key4
	 */
	public String getKey4() {
		return key4;
	}

	/**
	 * @param key4 the key4 to set
	 */
	public void setKey4(String key4) {
		this.key4 = key4;
	}

	/**
	 * @return the key5
	 */
	public String getKey5() {
		return key5;
	}

	/**
	 * @param key5 the key5 to set
	 */
	public void setKey5(String key5) {
		this.key5 = key5;
	}

	/**
	 * @return the key6
	 */
	public String getKey6() {
		return key6;
	}

	/**
	 * @param key6 the key6 to set
	 */
	public void setKey6(String key6) {
		this.key6 = key6;
	}

	/**
	 * @return the key7
	 */
	public String getKey7() {
		return key7;
	}

	/**
	 * @param key7 the key7 to set
	 */
	public void setKey7(String key7) {
		this.key7 = key7;
	}

	@Override
	public int compareTo(TraficoBamProductoBean traficoBamProductoBean) {

		if(Integer.parseInt(this.getCodbscs2()) < Integer.parseInt(traficoBamProductoBean.getCodbscs2()))
            return -1;
        else if(Integer.parseInt(this.getCodbscs2()) == Integer.parseInt(traficoBamProductoBean.getCodbscs2()))
            return 0;
        else
            return 1;
	}	
}
