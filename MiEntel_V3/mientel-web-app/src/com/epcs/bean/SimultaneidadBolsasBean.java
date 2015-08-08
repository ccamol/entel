/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class SimultaneidadBolsasBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private boolean flagDuplicidad;
    private List<BolsaBean> bolsasDuplicadas;
    /**
     * @return the flagDuplicidad
     */
    public boolean isFlagDuplicidad() {
        return flagDuplicidad;
    }
    /**
     * @param flagDuplicidad the flagDuplicidad to set
     */
    public void setFlagDuplicidad(boolean flagDuplicidad) {
        this.flagDuplicidad = flagDuplicidad;
    }
    /**
     * @return the bolsasDuplicadas
     */
    public List<BolsaBean> getBolsasDuplicadas() {
        return bolsasDuplicadas;
    }
    /**
     * @param bolsasDuplicadas the bolsasDuplicadas to set
     */
    public void setBolsasDuplicadas(List<BolsaBean> bolsasDuplicadas) {
        this.bolsasDuplicadas = bolsasDuplicadas;
    }

}
