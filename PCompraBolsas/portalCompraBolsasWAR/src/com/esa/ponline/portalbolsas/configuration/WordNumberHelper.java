/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.configuration;

import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class WordNumberHelper {
    
    /**
     * 
     * @return String
     */
    public static String getPrefijoEntel(){
        final String prefijo = LoadProperty.getProperty("prefijo.entel");
        return prefijo;
    }
    
    /**
     * 
     * @return String
     */
    public static String getPrefijoAmpliacion(){
        final String prefijo = LoadProperty.getProperty("prefijo.ampliacion");
        return prefijo;
    }

}
