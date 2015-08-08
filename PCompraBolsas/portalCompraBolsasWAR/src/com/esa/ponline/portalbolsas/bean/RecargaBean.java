/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Bean base para Recargas
 * 
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class RecargaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ordenCompra;

    private String numeroPcs;

    private Date fechaSolicitud;

    /**
     * @return the ordenCompra
     */
    public String getOrdenCompra() {
        return ordenCompra;
    }

    /**
     * @param ordenCompra
     *            the ordenCompra to set
     */
    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
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
     * @return the fechaSolicitud
     */
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * @param fechaSolicitud
     *            the fechaSolicitud to set
     */
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

}
