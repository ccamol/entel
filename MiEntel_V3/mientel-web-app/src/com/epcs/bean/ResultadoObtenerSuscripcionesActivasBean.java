/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ResultadoObtenerSuscripcionesActivasBean {
    
    private List<SuscripcionCDFBean> suscripciones;
    
    public ResultadoObtenerSuscripcionesActivasBean(){
        
    }

    /**
     * @return the suscripciones
     */
    public List<SuscripcionCDFBean> getSuscripciones() {
        if (suscripciones == null) {
            suscripciones = new ArrayList<SuscripcionCDFBean>();
        }
        return this.suscripciones;
    }

    /**
     * @param suscripciones the suscripciones to set
     */
    public void setSuscripciones(List<SuscripcionCDFBean> suscripciones) {
        this.suscripciones = suscripciones;
    }
    
    

}
