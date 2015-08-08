/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean para obtener el catalogo del servicio SAGEN
 * 
 * @author abaldovino (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */

public class CatalogoSAGENBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String vasID;
	private String providerID;
	private String suscriptionID;
	private String description;
	
	private List<CategoriaSAGENBean> categoria;

	public String getVasID() {
		return vasID;
	}

	public void setVasID(String vasID) {
		this.vasID = vasID;
	}

	public String getProviderID() {
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public String getSuscriptionID() {
		return suscriptionID;
	}

	public void setSuscriptionID(String suscriptionID) {
		this.suscriptionID = suscriptionID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CategoriaSAGENBean> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<CategoriaSAGENBean> categoria) {
		this.categoria = categoria;
	}
}
