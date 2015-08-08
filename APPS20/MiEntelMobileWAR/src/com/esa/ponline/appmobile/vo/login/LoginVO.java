/* Propiedad de Entel S.A. Todos los derechos reservados */
package com.esa.ponline.appmobile.vo.login;

import java.io.Serializable;

import com.esa.ponline.appmobile.util.Formato;
import com.esa.ponline.appmobile.vo.RutVO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class LoginVO implements Serializable {

	private static final long serialVersionUID = -2304704060270645772L;

	private String rut;
    	private String rutTitular;
	private String msisdn;
	private String mercado;
	private String email;
	private String nombre;
	private String nombreTitular;
	private String resultNav;
	private Boolean isWeb;
	private int aaa;
	private Boolean esVoz;
	private String cuenta;

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public LoginVO() {
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getMercado() {
		return mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}
	
	public Boolean IsWeb() {
		return isWeb;
	}

	public void setIsWeb(Boolean isWeb) {
		this.isWeb = isWeb;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRutTitular() {
		return rutTitular;
	}

	public void setRutTitular(String rutTitular) {
		this.rutTitular = rutTitular;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getResultNav() {
		return resultNav;
	}

	public void setResultNav(String resultNav) {
		this.resultNav = resultNav;
	}

	public int getAaa() {
		return aaa;
	}

	public void setAaa(int aaa) {
		this.aaa = aaa;
	}

	public Boolean getEsVoz() {
		return esVoz;
	}

	public void setEsVoz(Boolean esVoz) {
		this.esVoz = esVoz;
	}
	
	//Utilizados para GoogleTags
	public String getTipoCliente(){
		return esVoz==null ? "" : esVoz ? "VOZ" : "BAM";
	}
	
	public String getMsisdnMD5(){
		return Formato.getMD5(msisdn);
	}
	
	public Boolean getIsWeb(){
	    return isWeb;
	}
}
