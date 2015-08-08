/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao.util;

import org.apache.log4j.Logger;

import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * Clase utilitaria para obtener las propiedades de los diferentes servicios
 * de IVR.<br>
 * Estas propiedades son empleadas para las llamadas a los servicios del OSB
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ServiciosIVRPropertiesHelper {

    /**
     * Logger para ServiciosHelper
     */
    private static final Logger LOGGER = Logger
            .getLogger(ServiciosIVRPropertiesHelper.class);

    private static final String PREFIX = "adminServicios.ivr.";

    /**
     * Singleton
     */
    private ServiciosIVRPropertiesHelper() {
    }

    /**
     * Entrega el valor de la propiedad "idTipoServicio" para el servicio
     * indicado en <code>servicio</code>
     * 
     * @param servicio
     *            Servicio de quien se requiere la propiedad
     * @return String con valor de la propiedad. <code>null</code> si no puede
     *         ser obtenida
     */
    public static String getIdTipoServicio(String servicio) {
        return getServicioProperty(servicio, "idTipoServicio");
    }

    /**
     * Entrega el valor de la propiedad "activar" para el servicio indicado en
     * <code>servicio</code>
     * 
     * @param servicio
     *            Servicio de quien se requiere la propiedad
     * @return String con valor de la propiedad. <code>null</code> si no puede
     *         ser obtenida
     */
    public static String getActivar(String servicio) {
        return getServicioProperty(servicio, "activar");
    }

    /**
     * Entrega el valor de la propiedad "desactivar" para el servicio indicado
     * en <code>servicio</code>
     * 
     * @param servicio
     *            Servicio de quien se requiere la propiedad
     * @return String con valor de la propiedad. <code>null</code> si no puede
     *         ser obtenida
     */
    public static String getDesactivar(String servicio) {
        return getServicioProperty(servicio, "desactivar");
    }

    /**
     * Metodo utilitario generico para la obtencion de propiedades de servicios
     * 
     */
    private static String getServicioProperty(String servicio, String property) {
        try {
            return MiEntelProperties.getProperty(PREFIX + servicio + "."
                    + property);
        } catch (Exception e) {
            LOGGER.warn("Propiedad '" + property
                    + "' no encontrada para servicio " + servicio, e);
            return null;
        }
    }

}
