/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class EstadoPromoPagoAutomaticoBean implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String estadoPromocion;
    private String numeroCuenta;
    private String rutConGuion;
    /**
     * @return the estadoPromocion
     */
    public String getEstadoPromocion() {
        return estadoPromocion;
    }
    /**
     * @param estadoPromocion the estadoPromocion to set
     */
    public void setEstadoPromocion(String estadoPromocion) {
        this.estadoPromocion = estadoPromocion;
    }
    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    /**
     * @return the rutConGuion
     */
    public String getRutConGuion() {
        return rutConGuion;
    }
    /**
     * @param rutConGuion the rutConGuion to set
     */
    public void setRutConGuion(String rutConGuion) {
        this.rutConGuion = rutConGuion;
    }
    
    
    

}
