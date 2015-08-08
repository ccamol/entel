/* Propiedad de Entel S.A. Todos los derechos reservados */
package com.esa.ponline.appmobile.vo.Bundle;

import java.io.Serializable;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * Aug 29, 2014
 */


public class BundleDetailBalance implements Serializable {

	private static final long serialVersionUID = -833186589626720768L;
	
	private String idBolsa;
	private String nombreBolsa;
	private String introMsj;
	private String mensaje;
	private String expiracion;

	public String getExpiracion() {
		return expiracion;
	}

	public void setExpiracion(String expiracion) {
		this.expiracion = expiracion;
	}

	public String getIdBolsa() {
		return idBolsa;
	}

	public void setIdBolsa(String idBolsa) {
		this.idBolsa = idBolsa;
	}

	public String getIntroMsj() {
		return introMsj;
	}

	public void setIntroMsj(String introMsj) {
		this.introMsj = introMsj;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNombreBolsa() {
		return nombreBolsa;
	}

	public void setNombreBolsa(String nombreBolsa) {
		this.nombreBolsa = nombreBolsa;
	}
}