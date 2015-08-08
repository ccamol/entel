/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao.util;

import org.apache.log4j.Logger;

import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * Clase utilitaria para obtener las propiedades de los diferentes servicios de
 * Buzon de voz.<br>
 * Estas propiedades son empleadas para las llamadas a los servicios del OSB
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ServiciosBuzonPropertiesHelper {

    /**
     * Logger para ServiciosBuzonPropertiesHelper
     */
    private static final Logger LOGGER = Logger
            .getLogger(ServiciosBuzonPropertiesHelper.class);

    private static final String PREFIX = "adminServicios.buzon.";

    /**
     * Singleton
     */
    private ServiciosBuzonPropertiesHelper() {
    }

    /**
     * @return valor de la propiedad 'activar'
     */
    public static String getActivar() {
        return getServicioProperty("activar");
    }

    /**
     * @return valor de la propiedad 'desactivar'
     */
    public static String getDesactivar() {
        return getServicioProperty("desactivar");
    }

    /**
     * Metodo utilitario generico para la obtencion de propiedades de servicios
     * 
     */
    private static String getServicioProperty(String property) {
        try {
            return MiEntelProperties.getProperty(PREFIX + property);
        } catch (Exception e) {
            LOGGER.warn("Propiedad '" + property
                    + "' no encontrada para servicio buzon", e);
            return null;
        }
    }

}
