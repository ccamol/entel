/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.List;


/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ResultadoObtenerHistorialBean {
    private DetalleBean detalle;
    private List<HistorialDetalleBean> historial;
    
    public ResultadoObtenerHistorialBean(){
        
    }

    /**
     * @return the detalle
     */
    public DetalleBean getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(DetalleBean detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the historial
     */
    public List<HistorialDetalleBean> getHistorial() {
        return historial;
    }

    /**
     * @param historial the historial to set
     */
    public void setHistorial(List<HistorialDetalleBean> historial) {
        this.historial = historial;
    }

}
