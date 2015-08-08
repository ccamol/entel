/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.bean;

import java.io.Serializable;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class MontoRecargaBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Double doubleMonto;

    private String vigencia;
    
    private String descripcion;

    /**
     * @param doubleMonto
     * @param vigencia
     */
    public MontoRecargaBean(Double doubleMonto, String vigencia, String descripcion) {
        super();
        this.doubleMonto = doubleMonto;
        this.vigencia = vigencia;
        this.descripcion = descripcion;
    }
    
    public MontoRecargaBean() {
    }

    /**
     * @return the monto
     */
    public Double getDoubleMonto() {
        return doubleMonto;
    }

    /**
     * @param monto
     *            the monto to set
     */
    public void setDoubleMonto(Double doubleMonto) {
        this.doubleMonto = doubleMonto;
    }

    /**
     * @return the vigencia
     */
    public String getVigencia() {
        return vigencia;
    }

    /**
     * @param vigencia
     *            the vigencia to set
     */
    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

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

}
