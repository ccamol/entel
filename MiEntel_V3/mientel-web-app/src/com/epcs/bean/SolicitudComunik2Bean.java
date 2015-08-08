/**
 * 
 */
package com.epcs.bean;

import java.util.Date;

/**
 * Bean para representar solicitude del plan comunik2
 * 
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 */
public class SolicitudComunik2Bean {

	String  descripcion = "";
	Date    fechaSolicitud;
	String  msisdn = "";
	String  msisdnEnvia="";
	String  msisdnRecibe ="";
	boolean SolicitudRecibida = false;

  public SolicitudComunik2Bean(){}

/**
 * @return the descripcion
 */
public String getDescripcion() {
	return descripcion;
}

/**
 * @param descripcion the descripcion to set
 */
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

/**
 * @return the fechaSolicitud
 */
public Date getFechaSolicitud() {
	return fechaSolicitud;
}

/**
 * @param fechaSolicitud the fechaSolicitud to set
 */
public void setFechaSolicitud(Date fechaSolicitud) {
	this.fechaSolicitud = fechaSolicitud;
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
 * @return the msisdnEnvia
 */
public String getMsisdnEnvia() {
	return msisdnEnvia;
}

/**
 * @param msisdnEnvia the msisdnEnvia to set
 */
public void setMsisdnEnvia(String msisdnEnvia) {
	this.msisdnEnvia = msisdnEnvia;
}

/**
 * @return the msisdnRecibe
 */
public String getMsisdnRecibe() {
	return msisdnRecibe;
}

/**
 * @param msisdnRecibe the msisdnRecibe to set
 */
public void setMsisdnRecibe(String msisdnRecibe) {
	this.msisdnRecibe = msisdnRecibe;
}

/**
 * @return the solicitudRecibida
 */
public boolean isSolicitudRecibida() {
	return SolicitudRecibida;
}

/**
 * @param solicitudRecibida the solicitudRecibida to set
 */
public void setSolicitudRecibida(boolean solicitudRecibida) {
	SolicitudRecibida = solicitudRecibida;
}



}
