/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;


import java.io.Serializable;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class MarcaEstadisticaBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    private String funcionalidad;
    
    private String origen;
    
    private String grupo;
    
    private String servicio;
    
    private String segmento;
    
    private String ip;
    
    private String flagExitoFracasoOperacion;
    
    private String rut;
    
    private String msisdn;
    
    private String atributoCliente;
    
    private String campoOpcional1;
    
    private String campoOpcional2;

    /**
     * @return the funcionalidad
     */
    public String getFuncionalidad() {
        return funcionalidad;
    }

    /**
     * @param funcionalidad the funcionalidad to set
     */
    public void setFuncionalidad(String funcionalidad) {
        this.funcionalidad = funcionalidad;
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the servicio
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the segmento
     */
    public String getSegmento() {
        return segmento;
    }

    /**
     * @param segmento the segmento to set
     */
    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the flagExitoFracasoOperacion
     */
    public String getFlagExitoFracasoOperacion() {
        return flagExitoFracasoOperacion;
    }

    /**
     * @param flagExitoFracasoOperacion the flagExitoFracasoOperacion to set
     */
    public void setFlagExitoFracasoOperacion(String flagExitoFracasoOperacion) {
        this.flagExitoFracasoOperacion = flagExitoFracasoOperacion;
    }

    /**
     * @return the rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the atributoCliente
     */
    public String getAtributoCliente() {
        return atributoCliente;
    }

    /**
     * @param atributoCliente the atributoCliente to set
     */
    public void setAtributoCliente(String atributoCliente) {
        this.atributoCliente = atributoCliente;
    }

    /**
     * @return the campoOpcional1
     */
    public String getCampoOpcional1() {
        return campoOpcional1;
    }

    /**
     * @param campoOpcional1 the campoOpcional1 to set
     */
    public void setCampoOpcional1(String campoOpcional1) {
        this.campoOpcional1 = campoOpcional1;
    }

    /**
     * @return the campoOpcional2
     */
    public String getCampoOpcional2() {
        return campoOpcional2;
    }

    /**
     * @param campoOpcional2 the campoOpcional2 to set
     */
    public void setCampoOpcional2(String campoOpcional2) {
        this.campoOpcional2 = campoOpcional2;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MarcaEstadisticaBean [atributoCliente=" + atributoCliente
                + ", campoOpcional1=" + campoOpcional1 + ", campoOpcional2="
                + campoOpcional2 + ", flagExitoFracasoOperacion=" + flagExitoFracasoOperacion
                + ", funcionalidad=" + funcionalidad + ", grupo=" + grupo
                + ", ip=" + ip + ", msisdn=" + msisdn + ", origen=" + origen
                + ", rut=" + rut + ", segmento=" + segmento + ", servicio="
                + servicio + "]";
    }

    
    
    
}
