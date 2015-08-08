/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class BolsasActualesDisponiblesBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private List<BolsaBean> bolsasActuales;
    private List<BolsaBean> bolsasDisponibles;
    /**
     * @return the bolsasActuales
     */
    public List<BolsaBean> getBolsasActuales() {
        return bolsasActuales;
    }
    /**
     * @param bolsasActuales the bolsasActuales to set
     */
    public void setBolsasActuales(List<BolsaBean> bolsasActuales) {
        this.bolsasActuales = bolsasActuales;
    }
    /**
     * @return the bolsasDisponibles
     */
    public List<BolsaBean> getBolsasDisponibles() {
        return bolsasDisponibles;
    }
    /**
     * @param bolsasDisponibles the bolsasDisponibles to set
     */
    public void setBolsasDisponibles(List<BolsaBean> bolsasDisponibles) {
        this.bolsasDisponibles = bolsasDisponibles;
    }

}
