/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.prodfactura.util;

import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * Clase utilitaria para las operaciones de estados de Factura Electronica
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class FacturaElectronicaHelper {

    public static final String ESTADOFE_INSCRITO = MiEntelProperties
            .getProperty("facturacionElectronica.estadoFE.inscrito");

    public static final String ESTADOFE_INSCRITO_MODIFICADO = MiEntelProperties
            .getProperty("facturacionElectronica.estadoFE.inscritomodificado");

    public static final String ESTADOFE_PRE_INSCRITO = MiEntelProperties
            .getProperty("facturacionElectronica.estadoFE.preinscrito");

    protected FacturaElectronicaHelper() {
        super();
    }
    
    /**
     * Indica si el estado de Factura Electronica es Inscrito
     * @param estadoFE
     * @return true si factura electronica esta inscrita
     */
    public static boolean isInscrita(String estadoFE) {
        return ESTADOFE_INSCRITO.equals(estadoFE);
    }
    
    /**
     * Indica si el estado de Factura Electronica es Inscrito Modifcado
     * @param estadoFE
     * @return true si factura electronica esta inscrita modificada
     */
    public static boolean isInscritaModificada(String estadoFE) {
        return ESTADOFE_INSCRITO_MODIFICADO.equals(estadoFE);
    }

    /**
     * Indica si el estado de Factura Electronica es Pre Inscrito
     * @param estadoFE
     * @return true si factura electronica esta pre inscrita
     */
    public static boolean isPreInscrita(String estadoFE) {
        return ESTADOFE_PRE_INSCRITO.equals(estadoFE);
    }    
}
