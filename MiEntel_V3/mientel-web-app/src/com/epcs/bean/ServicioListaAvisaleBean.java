/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ServicioListaAvisaleBean extends ServicioBean implements
        Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String descMetodo;
    private String metodo;
    private String msisdn;
    private String parametro9;
    private String perfil;
    private String tipoLista;
    private String user;
    private String fechaHora;
    private List<String> numerosAvisale;
    /**
     * 
     */
    public ServicioListaAvisaleBean() {
        super();
    }

    /**
     * @return the descMetodo
     */
    public String getDescMetodo() {
        return descMetodo;
    }

    /**
     * @param descMetodo the descMetodo to set
     */
    public void setDescMetodo(String descMetodo) {
        this.descMetodo = descMetodo;
    }

    /**
     * @return the metodo
     */
    public String getMetodo() {
        return metodo;
    }

    /**
     * @param metodo the metodo to set
     */
    public void setMetodo(String metodo) {
        this.metodo = metodo;
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
     * @return the parametro9
     */
    public String getParametro9() {
        return parametro9;
    }

    /**
     * @param parametro9 the parametro9 to set
     */
    public void setParametro9(String parametro9) {
        this.parametro9 = parametro9;
    }

    /**
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the tipoLista
     */
    public String getTipoLista() {
        return tipoLista;
    }

    /**
     * @param tipoLista the tipoLista to set
     */
    public void setTipoLista(String tipoLista) {
        this.tipoLista = tipoLista;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the fechaHora
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * @return the numerosAvisale
     */
    public List<String> getNumerosAvisale() {
        return numerosAvisale;
    }

    /**
     * @param numerosAvisale the numerosAvisale to set
     */
    public void setNumerosAvisale(List<String> numerosAvisale) {
        this.numerosAvisale = numerosAvisale;
    }
    
    /**
     * 
     * @return String con numeros de lista separados por coma
     */
    public String getNumerosCommaSeparated() {
        
        String retorno = "";
        if(numerosAvisale == null || numerosAvisale.isEmpty()) {
            return retorno;
        }
        
        for(String numero : numerosAvisale) {
            retorno += numero + ",";
        }
        
        return retorno;
        
    }

}
