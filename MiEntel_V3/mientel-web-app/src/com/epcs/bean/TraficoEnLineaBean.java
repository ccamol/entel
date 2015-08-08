/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoEnLineaBean implements Serializable,Comparable<TraficoEnLineaBean> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String destinatario;

    private String descripcionTipoTrafico;

    private String tipoTrafico;

    private Date fecha;
    
    private String fechaFormated;

    private Double consumoTraficoVoz;
    
    private String consumoFormated;
    
    private int consumoTraficoDato;

    private Double valor;
    
    private String valorFormated;

    /**
     * @return the destinatario
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario
     *            the destinatario to set
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * @return the descripcionTipoTrafico
     */
    public String getDescripcionTipoTrafico() {
        return descripcionTipoTrafico;
    }

    /**
     * @param descripcionTipoTrafico
     *            the descripcionTipoTrafico to set
     */
    public void setDescripcionTipoTrafico(String descripcionTipoTrafico) {
        this.descripcionTipoTrafico = descripcionTipoTrafico;
    }

 
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha
     *            the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the tipoTrafico
     */
    public String getTipoTrafico() {
        return tipoTrafico;
    }

    /**
     * @param tipoTrafico
     *            the tipoTrafico to set
     */
    public void setTipoTrafico(String tipoTrafico) {
        this.tipoTrafico = tipoTrafico;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the consumoTraficoVoz
     */
    public Double getConsumoTraficoVoz() {
        return consumoTraficoVoz;
    }

    /**
     * @param consumoTraficoVoz the consumoTraficoVoz to set
     */
    public void setConsumoTraficoVoz(Double consumoTraficoVoz) {
        this.consumoTraficoVoz = consumoTraficoVoz;
    }

    /**
     * @return the consumoTraficoDato
     */
    public int getConsumoTraficoDato() {
        return consumoTraficoDato;
    }

    /**
     * @param consumoTraficoDato the consumoTraficoDato to set
     */
    public void setConsumoTraficoDato(int consumoTraficoDato) {
        this.consumoTraficoDato = consumoTraficoDato;
    }

   
    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(TraficoEnLineaBean traficoEnLineaBean) {
        return traficoEnLineaBean.getFecha().compareTo(this.getFecha());
    }

    /**
     * @return the fechaFormated
     */
    public String getFechaFormated() {
        return fechaFormated;
    }

    /**
     * @param fechaFormated the fechaFormated to set
     */
    public void setFechaFormated(String fechaFormated) {
        this.fechaFormated = fechaFormated;
    }

    /**
     * @return the consumoFormated
     */
    public String getConsumoFormated() {
        return consumoFormated;
    }

    /**
     * @param consumoFormated the consumoFormated to set
     */
    public void setConsumoFormated(String consumoFormated) {
        this.consumoFormated = consumoFormated;
    }

    /**
     * @return the valorFormated
     */
    public String getValorFormated() {
        return valorFormated;
    }

    /**
     * @param valorFormated the valorFormated to set
     */
    public void setValorFormated(String valorFormated) {
        this.valorFormated = valorFormated;
    }

}
