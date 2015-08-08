/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;


/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ConsultarPuntosBean {
    
    private String rutSinDV;
    
    private String transaccion;
    
    private String identificacion;

    public ConsultarPuntosBean(){
        
    }

    /**
     * @return the rutSinDV
     */
    public String getRutSinDV() {
        return rutSinDV;
    }

    /**
     * @param rutSinDV the rutSinDV to set
     */
    public void setRutSinDV(String rutSinDV) {
        this.rutSinDV = rutSinDV;
    }

    /**
     * @return the transaccion
     */
    public String getTransaccion() {
        return transaccion;
    }

    /**
     * @param transaccion the transaccion to set
     */
    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    
}
