/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * Bean de factibilidad de recarga Entelticket
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class FactibilidadRecargaEntelTicketBean extends FactibilidadRecargaBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;    
    
    private String plataforma;
    
    private String tipoOperacion;
    
    private String numeroEntelTicket;
    
    private Double montoDispTicket;
    
    private String folioTicket;
    
    private String agenciaTicket;
    
    private String montoInicialTicket;
    
    private String codEstadoRespuesta;
    
    private String codRespuestaExitoAdicional;
    
    private String validezRecarga;
    
    public FactibilidadRecargaEntelTicketBean(){
        
    }

    /**
     * @param plataforma
     * @param tipoOperacion
     * @param numeroEntelTicket
     * @param montoDispTicket
     * @param folioTicket
     * @param agenciaTicket
     * @param montoInicialTicket
     * @param codEstadoRespuesta
     * @param codRespuestaExitoAdicional
     * @param validezRecarga
     */
    public FactibilidadRecargaEntelTicketBean(String plataforma,
            String tipoOperacion, String numeroEntelTicket,
            Double montoDispTicket, String folioTicket, String agenciaTicket,
            String montoInicialTicket, String codEstadoRespuesta,
            String codRespuestaExitoAdicional, String validezRecarga) {
        super();
        this.plataforma = plataforma;
        this.tipoOperacion = tipoOperacion;
        this.numeroEntelTicket = numeroEntelTicket;
        this.montoDispTicket = montoDispTicket;
        this.folioTicket = folioTicket;
        this.agenciaTicket = agenciaTicket;
        this.montoInicialTicket = montoInicialTicket;
        this.codEstadoRespuesta = codEstadoRespuesta;
        this.codRespuestaExitoAdicional = codRespuestaExitoAdicional;
        this.validezRecarga = validezRecarga;
    }


    /**
     * @return the plataforma
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * @param plataforma the plataforma to set
     */
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * @return the tipoOperacion
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * @param tipoOperacion the tipoOperacion to set
     */
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * @return the numeroEntelTicket
     */
    public String getNumeroEntelTicket() {
        return numeroEntelTicket;
    }

    /**
     * @param numeroEntelTicket the numeroEntelTicket to set
     */
    public void setNumeroEntelTicket(String numeroEntelTicket) {
        this.numeroEntelTicket = numeroEntelTicket;
    }

    /**
     * @return the montoDispTicket
     */
    public Double getMontoDispTicket() {
        return montoDispTicket;
    }

    /**
     * @param montoDispTicket the montoDispTicket to set
     */
    public void setMontoDispTicket(Double montoDispTicket) {
        this.montoDispTicket = montoDispTicket;
    }

    /**
     * @return the folioTicket
     */
    public String getFolioTicket() {
        return folioTicket;
    }

    /**
     * @param folioTicket the folioTicket to set
     */
    public void setFolioTicket(String folioTicket) {
        this.folioTicket = folioTicket;
    }

    /**
     * @return the agenciaTicket
     */
    public String getAgenciaTicket() {
        return agenciaTicket;
    }

    /**
     * @param agenciaTicket the agenciaTicket to set
     */
    public void setAgenciaTicket(String agenciaTicket) {
        this.agenciaTicket = agenciaTicket;
    }

    /**
     * @return the montoInicialTicket
     */
    public String getMontoInicialTicket() {
        return montoInicialTicket;
    }

    /**
     * @param montoInicialTicket the montoInicialTicket to set
     */
    public void setMontoInicialTicket(String montoInicialTicket) {
        this.montoInicialTicket = montoInicialTicket;
    }

    /**
     * @return the codEstadoRespuesta
     */
    public String getCodEstadoRespuesta() {
        return codEstadoRespuesta;
    }

    /**
     * @param codEstadoRespuesta the codEstadoRespuesta to set
     */
    public void setCodEstadoRespuesta(String codEstadoRespuesta) {
        this.codEstadoRespuesta = codEstadoRespuesta;
    }

    /**
     * @return the codRespuestaExitoAdicional
     */
    public String getCodRespuestaExitoAdicional() {
        return codRespuestaExitoAdicional;
    }

    /**
     * @param codRespuestaExitoAdicional the codRespuestaExitoAdicional to set
     */
    public void setCodRespuestaExitoAdicional(String codRespuestaExitoAdicional) {
        this.codRespuestaExitoAdicional = codRespuestaExitoAdicional;
    }

    /**
     * @return the validezRecarga
     */
    public String getValidezRecarga() {
        return validezRecarga;
    }

    /**
     * @param validezRecarga the validezRecarga to set
     */
    public void setValidezRecarga(String validezRecarga) {
        this.validezRecarga = validezRecarga;
    }
    
}
