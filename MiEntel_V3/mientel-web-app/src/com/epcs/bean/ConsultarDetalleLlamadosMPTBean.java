/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ConsultarDetalleLlamadosMPTBean {
    
    private Boolean consultado;
    private String periodo;
    
    public ConsultarDetalleLlamadosMPTBean() {
        this.consultado = Boolean.FALSE;
    }
    
    /**
     * @return the consultado
     */
    public Boolean isConsultado() {
        return consultado;
    }
    /**
     * @param consultado the consultado to set
     */
    public void setConsultado(Boolean consultado) {
        this.consultado = consultado;
    }
    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }
    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
