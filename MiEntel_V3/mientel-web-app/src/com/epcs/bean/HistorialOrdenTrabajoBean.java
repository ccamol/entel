package com.epcs.bean;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class HistorialOrdenTrabajoBean {

    private String codEstado;
    
    private String descEstado;
    
    private String fechaEstado;
    
    public HistorialOrdenTrabajoBean(){
    	
    }

	/**
	 * @return the codEstado
	 */
	public String getCodEstado() {
		return codEstado;
	}

	/**
	 * @param codEstado the codEstado to set
	 */
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	/**
	 * @return the descEstado
	 */
	public String getDescEstado() {
		return descEstado;
	}

	/**
	 * @param descEstado the descEstado to set
	 */
	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	/**
	 * @return the fechaEstado
	 */
	public String getFechaEstado() {
		return fechaEstado;
	}

	/**
	 * @param fechaEstado the fechaEstado to set
	 */
	public void setFechaEstado(String fechaEstado) {
		this.fechaEstado = fechaEstado;
	}
	
}
