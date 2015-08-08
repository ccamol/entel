/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.seguridad.spi;

/**
 * Clase utilitaria para el WordNumber en numeros de Entel
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class WordNumberHelper {

    /**
     * Prefijo de WordNumber
     */
    public static final String PREFIJO_ENTEL = "569";
    
    protected WordNumberHelper() {
        super();
    }
    
    /**
     * Normaliza un numero entel, agregando el WordNumber
     * @return String
     */
    public static String normalizarMsisdnEntel(String msisdn){
        if(msisdn != null && msisdn.length() > 0) {
            return PREFIJO_ENTEL + msisdn;
        }
        return null;
    }
}
