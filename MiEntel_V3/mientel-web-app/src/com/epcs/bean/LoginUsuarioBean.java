/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class LoginUsuarioBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4751085120266247272L;

    private String numeroPcs;

    private String rut;

    private String clave;
    
    private String idp;

    /**
     * @return the idp
     */
    public String getIdp() {
        return idp;
    }

    /**
     * @param idp the idp to set
     */
    public void setIdp(String idp) {
        this.idp = idp;
    }

    /**
     * @return the numeroPcs
     */
    public String getNumeroPcs() {
        return numeroPcs;
    }

    /**
     * @param numeroPcs
     *            the numeroPcs to set
     */
    public void setNumeroPcs(String numeroPcs) {
        this.numeroPcs = numeroPcs;
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
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave
     *            the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    

}
