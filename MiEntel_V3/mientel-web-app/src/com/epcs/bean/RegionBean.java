/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * Bean para representar una region de Chile
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class RegionBean extends CodeDescBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param codigo
     * @param descripcion
     */
    public RegionBean(String codigo, String descripcion) {
        super(codigo, descripcion);
    }

    /**
     * Entrega una instancia de {@link RegionBean} en blanco para propositos
     * null-safe en otras clases y metodos
     * 
     * @return {@link RegionBean}
     */
    public static RegionBean emptyBean() {
        return new RegionBean("", "");
    }

}
