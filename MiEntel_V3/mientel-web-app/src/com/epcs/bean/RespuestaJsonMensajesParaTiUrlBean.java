/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

/**
 * Clase que representa una respuesta Json para el portlet de mensajes para ti 
 * @author zmanotas (I2B) 
 */
public class RespuestaJsonMensajesParaTiUrlBean {

	private String itemsPorPag;
	private String[] urls;

	public String getItemsPorPag() {
		return itemsPorPag;
	}

	public void setItemsPorPag(String itemsPorPag) {
		this.itemsPorPag = itemsPorPag;
	}

	public String[] getUrls() {
		return urls;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
	}
}
