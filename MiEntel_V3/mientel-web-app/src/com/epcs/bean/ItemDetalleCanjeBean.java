package com.epcs.bean;

import java.util.HashMap;

public class ItemDetalleCanjeBean {
		 
    private HashMap<String, ItemMercadoCanjeBean> hashMercados;
    	
	/**
	 * @return the hashMercados
	 */
	public HashMap<String, ItemMercadoCanjeBean> getHashMercados() {
		return hashMercados;
	}
	/**
	 * @param hashMercados the hashMercados to set
	 */
	public void setHashMercados(HashMap<String, ItemMercadoCanjeBean> hashMercados) {
		this.hashMercados = hashMercados;
	}
	   
}
