/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

/**
 * Bean para recargas con Entel Ticket
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class RecargaEntelTicketBean extends RecargaBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String numeroSecretoEnteTicket;

    /**
     * @return the numeroSecretoEnteTicket
     */
    public String getNumeroSecretoEnteTicket() {
        return numeroSecretoEnteTicket;
    }

    /**
     * @param numeroSecretoEnteTicket
     *            the numeroSecretoEnteTicket to set
     */
    public void setNumeroSecretoEnteTicket(String numeroSecretoEnteTicket) {
        this.numeroSecretoEnteTicket = numeroSecretoEnteTicket;
    }

}
