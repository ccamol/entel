/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ResumenProductosContratadosBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5662447405519595194L;

    private String nombrePlanVoz;

    private String nombrePlanInternet;

    private List<String> nombreBolsas;

    /**
     * @return the nombrePlanVoz
     */
    public String getNombrePlanVoz() {
        return nombrePlanVoz;
    }

    /**
     * @param nombrePlanVoz
     *            the nombrePlanVoz to set
     */
    public void setNombrePlanVoz(String nombrePlanVoz) {
        this.nombrePlanVoz = nombrePlanVoz;
    }

    /**
     * @return the nombrePlanInternet
     */
    public String getNombrePlanInternet() {
        return nombrePlanInternet;
    }

    /**
     * @param nombrePlanInternet
     *            the nombrePlanInternet to set
     */
    public void setNombrePlanInternet(String nombrePlanInternet) {
        this.nombrePlanInternet = nombrePlanInternet;
    }

    /**
     * @return the nombreBolsas
     */
    public List<String> getNombreBolsas() {
        return nombreBolsas;
    }

    /**
     * @param nombreBolsas
     *            the nombreBolsas to set
     */
    public void setNombreBolsas(List<String> nombreBolsas) {
        this.nombreBolsas = nombreBolsas;
    }

    /**
     * 
     * @param index
     *            indice del nombreBolsa a retornar
     * @return el nombreBolsa de la posicion especificada de la lista
     *         nombreBolsas
     * 
     * @throws IndexOutOfBoundsException
     *             - Si index esta fuera del rango (index < 0 || index >=
     *             size())
     */
    public String getNombreBolsa(int index) {
        if (this.nombreBolsas != null) {
            return this.nombreBolsas.get(index);
        }
        return null;
    }
    
}
