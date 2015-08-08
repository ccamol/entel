/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * Bean de Login para aplicaciones
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class LoginAplicacionBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8809599068885653235L;

    private String idp;

    private String numeroPcs;

    /**
     * @return the idp
     */
    public String getIdp() {
        return idp;
    }

    /**
     * @param idp
     *            the idp to set
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


}
