/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;


/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ObtenerHistorialBean {
    
    private String rutSinDv;
    
    private String mes;
    
    public ObtenerHistorialBean(){
        
    }

    /**
     * @return the rutSinDv
     */
    public String getRutSinDv() {
        return rutSinDv;
    }

    /**
     * @param rutSinDv the rutSinDv to set
     */
    public void setRutSinDv(String rutSinDv) {
        this.rutSinDv = rutSinDv;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

}
