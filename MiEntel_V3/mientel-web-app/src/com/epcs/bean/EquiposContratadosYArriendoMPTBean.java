/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class EquiposContratadosYArriendoMPTBean {

    private Integer cantidadLineasContratadas;
    private Integer equiposContratoVigente;

    /**
     * @return the cantidadLineasContratadas
     */
    public Integer getCantidadLineasContratadas() {
        return cantidadLineasContratadas;
    }

    /**
     * @param cantidadLineasContratadas
     *            the cantidadLineasContratadas to set
     */
    public void setCantidadLineasContratadas(Integer cantidadLineasContratadas) {
        this.cantidadLineasContratadas = cantidadLineasContratadas;
    }

    /**
     * @return the equiposContratoVigente
     */
    public Integer getEquiposContratoVigente() {
        return equiposContratoVigente;
    }

    /**
     * @param equiposContratoVigente
     *            the equiposContratoVigente to set
     */
    public void setEquiposContratoVigente(Integer equiposContratoVigente) {
        this.equiposContratoVigente = equiposContratoVigente;
    }
}
