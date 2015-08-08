/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class TraficoVozHorario implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String descripcion;
    private double consumo;
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * @return the consumo
     */
    public double getConsumo() {
        return consumo;
    }
    /**
     * @param consumo the consumo to set
     */
    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    
    
}
