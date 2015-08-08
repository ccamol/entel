package com.epcs.bean;

import java.io.Serializable;

public class CombinacionVas implements Serializable {
	  /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    private String search;
    
    private String[] listadosearch;
    
    private String htmlFamilias;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String[] getListadosearch() {
		return listadosearch;
	}

	public void setListadosearch(String[] listadosearch) {
		this.listadosearch = listadosearch;
	}

	public String getHtmlFamilias() {
		return htmlFamilias;
	}

	public void setHtmlFamilias(String htmlFamilias) {
		this.htmlFamilias = htmlFamilias;
	}
	
	
    
    
    
}
