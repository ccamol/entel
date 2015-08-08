/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class BolsaBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String spCodigo;
    private String snCodigo;
    private String nombreBolsa;
    private List<String> descBolsa;
    private String observacion;
    private Double costo;
    private Double cantidad;
    private String flagPromocion;
    private String tipoBolsa;
    private String tipoVigencia;
    private String vigencia;
    private String estado;
    private Date fechaPendiente;
    
    /**
     * @return the spCodigo
     */
    public String getSpCodigo() {
        return spCodigo;
    }
    /**
     * @param spCodigo the spCodigo to set
     */
    public void setSpCodigo(String spCodigo) {
        this.spCodigo = spCodigo;
    }
    /**
     * @return the snCodigo
     */
    public String getSnCodigo() {
        return snCodigo;
    }
    /**
     * @param snCodigo the snCodigo to set
     */
    public void setSnCodigo(String snCodigo) {
        this.snCodigo = snCodigo;
    }
    /**
     * @return the nombreBolsa
     */
    public String getNombreBolsa() {
        return nombreBolsa;
    }
    /**
     * @param nombreBolsa the nombreBolsa to set
     */
    public void setNombreBolsa(String nombreBolsa) {
        this.nombreBolsa = nombreBolsa;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }
    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    /**
     * @return the costo
     */
    public Double getCosto() {
        return costo;
    }
    /**
     * @param costo the costo to set
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }
    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    /**
     * @return the flagPromocion
     */
    public String getFlagPromocion() {
        return flagPromocion;
    }
    /**
     * @param flagPromocion the flagPromocion to set
     */
    public void setFlagPromocion(String flagPromocion) {
        this.flagPromocion = flagPromocion;
    }
    /**
     * @return the tipoBolsa
     */
    public String getTipoBolsa() {
        return tipoBolsa;
    }
    /**
     * @param tipoBolsa the tipoBolsa to set
     */
    public void setTipoBolsa(String tipoBolsa) {
        this.tipoBolsa = tipoBolsa;
    }
    /**
     * @return the tipoVigencia
     */
    public String getTipoVigencia() {
        return tipoVigencia;
    }
    /**
     * @param tipoVigencia the tipoVigencia to set
     */
    public void setTipoVigencia(String tipoVigencia) {
        this.tipoVigencia = tipoVigencia;
    }
    /**
     * @return the vigencia
     */
    public String getVigencia() {
        return vigencia;
    }
    /**
     * @param vigencia the vigencia to set
     */
    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }
    /**
     * @return the descBolsa
     */
    public List<String> getDescBolsa() {
        return descBolsa;
    }
    /**
     * @param descBolsa the descBolsa to set
     */
    public void setDescBolsa(List<String> descBolsa) {
        this.descBolsa = descBolsa;
    }
    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }
    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * @return the fechaPendiente
     */
    public Date getFechaPendiente() {
        return fechaPendiente;
    }
    /**
     * @param fechaPendiente the fechaPendiente to set
     */
    public void setFechaPendiente(Date fechaPendiente) {
        this.fechaPendiente = fechaPendiente;
    }

}
