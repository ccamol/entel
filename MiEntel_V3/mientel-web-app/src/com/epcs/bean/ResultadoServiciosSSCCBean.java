/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.bean;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ResultadoServiciosSSCCBean extends ResultadoServicioBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private boolean cambioPendiente;

    /**
     * 
     */
    public ResultadoServiciosSSCCBean() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the cambioPendiente
     */
    public boolean isCambioPendiente() {
        return cambioPendiente;
    }

    /**
     * @param cambioPendiente
     *            the cambioPendiente to set
     */
    public void setCambioPendiente(boolean cambioPendiente) {
        this.cambioPendiente = cambioPendiente;
    }

}
