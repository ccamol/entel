/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * Bean para representar una comuna de Chile
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ComunaBean extends CodeDescBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 9133902258416589217L;

    /**
     * @param codigo
     * @param descripcion
     */
    public ComunaBean(String codigo, String descripcion) {
        super(codigo, descripcion);
    }

    /**
     * Entrega una instancia de {@link ComunaBean} en blanco para propositos
     * null-safe en otras clases y metodos
     * 
     * @return {@link ComunaBean}
     */
    public static ComunaBean emptyBean() {
        return new ComunaBean("", "");
    }
}
