/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class RegistroTransbankRecargaBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String ordenCompra;
    
    private String idp;
    
    private String respuestaTransbank;

    /**
     * @return the ordenCompra
     */
    public String getOrdenCompra() {
        return ordenCompra;
    }

    /**
     * @param ordenCompra the ordenCompra to set
     */
    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

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
     * @return the respuestaTransbank
     */
    public String getRespuestaTransbank() {
        return respuestaTransbank;
    }

    /**
     * @param respuestaTransbank the respuestaTransbank to set
     */
    public void setRespuestaTransbank(String respuestaTransbank) {
        this.respuestaTransbank = respuestaTransbank;
    }
    
    
}
