package com.epcs.bean;

import java.util.HashMap;

public class ItemMercadoCanjeBean {
	private String id;   
    private HashMap<String, ItemProductoCanjeBean> hashProductos;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the hashProductos
	 */
	public HashMap<String, ItemProductoCanjeBean> getHashProductos() {
		return hashProductos;
	}
	/**
	 * @param hashProductos the hashProductos to set
	 */
	public void setHashProductos(HashMap<String, ItemProductoCanjeBean> hashProductos) {
		this.hashProductos = hashProductos;
	}
	   
}
