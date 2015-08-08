/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ResultadoConsultarCatalogoCDFBean {
    
    private List<SuscripcionCDFBean> catalogo;

    /**
     * Constructor de la clase
     */
    public ResultadoConsultarCatalogoCDFBean() {
        
    }

    /**
     * @return the catalogo
     */
    public List<SuscripcionCDFBean> getCatalogo() {
        if (catalogo == null) {
            catalogo = new ArrayList<SuscripcionCDFBean>();
        }
        return this.catalogo;
    }

    /**
     * @param catalogo the catalogo to set
     */
    public void setCatalogo(List<SuscripcionCDFBean> catalogo) {
        this.catalogo = catalogo;
    }

}
