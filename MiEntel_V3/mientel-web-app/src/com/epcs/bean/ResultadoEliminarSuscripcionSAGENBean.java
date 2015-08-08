package com.epcs.bean;

import java.io.Serializable;

public class ResultadoEliminarSuscripcionSAGENBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String info;
	private String txID;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTxID() {
		return txID;
	}
	public void setTxID(String txID) {
		this.txID = txID;
	}
}
