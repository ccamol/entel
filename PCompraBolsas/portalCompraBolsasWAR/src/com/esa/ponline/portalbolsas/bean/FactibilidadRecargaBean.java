/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.bean;

import java.io.Serializable;

/**
 * Bean de factibilidad de recarga
 * @author ccastro (ccastro) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class FactibilidadRecargaBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String numeroPcs;

    private String codUnicoTransaccion;

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
     * @return the codUnicoTransaccion
     */
    public String getCodUnicoTransaccion() {
        return codUnicoTransaccion;
    }

    /**
     * @param codUnicoTransaccion
     *            the codUnicoTransaccion to set
     */
    public void setCodUnicoTransaccion(String codUnicoTransaccion) {
        this.codUnicoTransaccion = codUnicoTransaccion;
    }

}
