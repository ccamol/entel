package com.epcs.bean;

import java.io.Serializable;

public class ResultadoConsultarSAGENBean extends ServicioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String vasID;
	private String serviceID;
	private String suscriptionID;
	private String descriptionID;
	private String providerID;
	private CustomSAGENBean custom;
	private String la;
	
	public ResultadoConsultarSAGENBean(){
		super();
	}

		
	public String getProviderID() {
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public String getVasID() {
		return vasID;
	}

	public void setVasID(String vasID) {
		this.vasID = vasID;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getSuscriptionID() {
		return suscriptionID;
	}

	public void setSuscriptionID(String suscriptionID) {
		this.suscriptionID = suscriptionID;
	}

	public String getDescriptionID() {
		return descriptionID;
	}

	public void setDescriptionID(String descriptionID) {
		this.descriptionID = descriptionID;
	}

	public CustomSAGENBean getCustom() {
		return custom;
	}

	public void setCustom(CustomSAGENBean custom) {
		this.custom = custom;
	}


	public String getLa() {
		return la;
	}


	public void setLa(String la) {
		this.la = la;
	}	
	
}
