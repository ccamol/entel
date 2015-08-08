/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ServicioCobroRevertidoAutomaticoBean extends ServicioBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String parametro5;
    private String msisdn;
        
    
    public ServicioCobroRevertidoAutomaticoBean(){
        super();
    }


    /**
     * @return the parametro5
     */
    public String getParametro5() {
        return parametro5;
    }


    /**
     * @param parametro5 the parametro5 to set
     */
    public void setParametro5(String parametro5) {
        this.parametro5 = parametro5;
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
    
    

    
}
