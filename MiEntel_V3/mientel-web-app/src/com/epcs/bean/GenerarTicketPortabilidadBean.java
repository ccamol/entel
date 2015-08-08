package com.epcs.bean;

public class GenerarTicketPortabilidadBean extends GenerarTicketBean {

	private static final long serialVersionUID = 1L;
	private String marcaEquipo;
	private String modeloEquipo;

	public String getMarcaEquipo() {
		return marcaEquipo;
	}

	public void setMarcaEquipo(String marcaEquipo) {
		this.marcaEquipo = marcaEquipo;
	}

	public String getModeloEquipo() {
		return modeloEquipo;
	}

	public void setModeloEquipo(String modeloEquipo) {
		this.modeloEquipo = modeloEquipo;
	}
}
