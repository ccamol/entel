/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao.util;

import org.apache.log4j.Logger;

import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * Clase utilitaria para obtener las propiedades de los servicios Avisale.<br>
 * Estas propiedades son empleadas para las llamadas a los servicios del OSB
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ServiciosAvisalePropertiesHelper {

    /**
     * Logger para ServiciosHelper
     */
    private static final Logger LOGGER = Logger
            .getLogger(ServiciosAvisalePropertiesHelper.class);

    private static final String PREFIX = "adminServicios.avisale.";

    
    /**
     * Singleton
     */
    private ServiciosAvisalePropertiesHelper() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Entrega el valor de la propiedad "activar" para el servicio
     * indicado en <code>servicio</code>
     * 
     * @return String con valor de la propiedad. <code>null</code> si no puede
     *         ser obtenida
     */
    public static String getActivar() {
        return getServicioProperty("activar");
    }
    
    /**
     * Entrega el valor de la propiedad "desactivar" para el servicio
     * indicado en <code>servicio</code>
     * 
     * @return String con valor de la propiedad. <code>null</code> si no puede
     *         ser obtenida
     */
    public static String getDesactivar() {
        return getServicioProperty("desactivar");
    }

    /**
     * Entrega el valor de la propiedad "tipoListaDesactivar" para el servicio
     * indicado en <code>servicio</code>
     * 
     * @return String con valor de la propiedad. <code>null</code> si no puede
     *         ser obtenida
     */
    public static String getTipoListaDesactivar() {
        return getServicioProperty("tipoListaDesactivar");
    }
    
    /**
     * Entrega el valor de la propiedad "tipoListaDesactivar" para el servicio
     * indicado en <code>servicio</code>
     * 
     * @return String con valor de la propiedad. <code>null</code> si no puede
     *         ser obtenida
     */
    public static String getTipoListaDefault() {
        return getServicioProperty("tipoLista.default");
    }

    /**
     * Entrega el valor de la propiedad "tipoLista.avisarTodos" para el servicio
     * indicado en <code>servicio</code>
     * 
     * @return String con valor de la propiedad. <code>null</code> si no puede
     *         ser obtenida
     */
    public static String getTipoListaAvisarTodos() {
        return getServicioProperty("tipoLista.avisarTodos");
    }
    
    /**
     * Entrega el valor de la propiedad "tipoLista.soloAvisarA" para el servicio
     * indicado en <code>servicio</code>
     * 
     * @return String con valor de la propiedad. <code>null</code> si no puede
     *         ser obtenida
     */
    public static String getTipoListaSoloAvisarA() {
        return getServicioProperty("tipoLista.soloAvisarA");
    }
    
    
    /**
     * Entrega el valor de la propiedad "modificar" para el servicio
     * indicado en <code>servicio</code>
     * 
     * @return String con valor de la propiedad. <code>null</code> si no puede
     *         ser obtenida
     */
    public static String getModificar() {
        return getServicioProperty("modificar");
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
                    + "' no encontrada para servicio Avisale", e);
            return null;
        }
    }
}
