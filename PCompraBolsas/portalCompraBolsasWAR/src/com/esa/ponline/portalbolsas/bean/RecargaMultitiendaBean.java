/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.bean;

/**
 * Bean de recarga para tarjetas de multitiendas
 * 
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class RecargaMultitiendaBean extends RecargaBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    //private MultitiendaBean multitienda;

    private Long numeroTarjeta;

    private String claveTarjeta;

    private Integer montoRecarga;

    private int cuotas;

    /**
     * @return the numeroTarjeta
     */
    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * @param numeroTarjeta
     *            the numeroTarjeta to set
     */
    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * @return the claveTarjeta
     */
    public String getClaveTarjeta() {
        return claveTarjeta;
    }

    /**
     * @param claveTarjeta
     *            the claveTarjeta to set
     */
    public void setClaveTarjeta(String claveTarjeta) {
        this.claveTarjeta = claveTarjeta;
    }

    /**
     * @return the montoRecarga
     */
    public Integer getMontoRecarga() {
        return montoRecarga;
    }

    /**
     * @param montoRecarga
     *            the montoRecarga to set
     */
    public void setMontoRecarga(Integer montoRecarga) {
        this.montoRecarga = montoRecarga;
    }

    /**
     * @return the cuotas
     */
    public int getCuotas() {
        return cuotas;
    }

    /**
     * @param cuotas
     *            the cuotas to set
     */
    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

}
