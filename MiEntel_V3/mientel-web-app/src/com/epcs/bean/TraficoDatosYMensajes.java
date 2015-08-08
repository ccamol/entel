/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class TraficoDatosYMensajes implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String tipo;
    
    private Date fechaInicial;
    
    private Date fechaFinal;
    
    private double total;
    
    private String totalFormated;
    
    
    
    /**
	 * @return the totalFormated
	 */
	public String getTotalFormated() {
		return totalFormated;
	}
	/**
	 * @param totalFormated the totalFormated to set
	 */
	public void setTotalFormated(String totalFormated) {
		this.totalFormated = totalFormated;
	}
	/**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }
    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }
    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }
    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }
    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }


}
