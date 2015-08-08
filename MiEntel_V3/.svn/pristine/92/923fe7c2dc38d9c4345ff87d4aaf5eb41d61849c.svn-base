/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao.util;

import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class SelectorCuentaHelper {

    private static final String DEFAULT_VOICE_PLAN_DESCRIPTION = MiEntelProperties
            .getProperty("parametros.plan.telefonia.desc");

    private static final String DEFAULT_BAM_PLAN_DESCRIPTION = MiEntelProperties
            .getProperty("parametros.plan.bam.desc");

    private static final String DEFAULT_SUSCRIPTION_PLAN_CONVENTION = MiEntelProperties
            .getProperty("parametros.plan.suscripcion.sigla");

    private static final String DEFAULT_SUSCRIPTION_PLAN_DESCRIPTION = MiEntelProperties
            .getProperty("parametros.plan.suscripcion.desc");

    private static final String DEFAULT_CUENTA_CONTROLADA_PLAN_CONVENTION = MiEntelProperties
            .getProperty("parametros.plan.cuentacontrolada.sigla");

    private static final String DEFAULT_CUENTA_CONTROLADA_PLAN_DESCRIPTION = MiEntelProperties
            .getProperty("parametros.plan.cuentacontrolada.desc");

    private static final String DEFAULT_PREPAGO_PLAN_CONVENTION = MiEntelProperties
            .getProperty("parametros.plan.prepago.sigla");

    private static final String DEFAULT_PREPAGO_PLAN_DESCRIPTION = MiEntelProperties
            .getProperty("parametros.plan.prepago.desc");

    private static final String DEFAULT_NOT_REGISTERED_PLAN_CONVENTION = MiEntelProperties
            .getProperty("parametros.plan.noregistrado.sigla");

    private static final String DEFAULT_NOT_REGISTERED_PLAN_DESCRIPTION = MiEntelProperties
            .getProperty("parametros.plan.noregistrado.desc");

    private static final String DEFAULT_NUMBER_PLAN_SEPARATOR_CHAR = MiEntelProperties
            .getProperty("parametros.separador.numero.descripcion");

    /**
     * Este metodo permite obtener una cadena descriptiva del tipo
     * "98876622 Telefonia - Suscripcion" para ser desplegada en cada seleccion
     * de la lista desplegable del selector de cuentas.
     * 
     * @param flagBam
     * @param msisdn
     * @return
     */
    public static String formatearDescripcion(int flagBam, String msisdn,
            String mercado) {

        StringBuffer sb = new StringBuffer();
        sb.append(msisdn);
        sb.append(" ");
        if (0 == flagBam) {
            sb.append(DEFAULT_VOICE_PLAN_DESCRIPTION);
        }
        else {
            sb.append(DEFAULT_BAM_PLAN_DESCRIPTION);
        }
        sb.append(DEFAULT_NUMBER_PLAN_SEPARATOR_CHAR);
        if (DEFAULT_SUSCRIPTION_PLAN_CONVENTION.equalsIgnoreCase(mercado)) {
            sb.append(DEFAULT_SUSCRIPTION_PLAN_DESCRIPTION);
        }
        else if (DEFAULT_CUENTA_CONTROLADA_PLAN_CONVENTION
                .equalsIgnoreCase(mercado)) {
            sb.append(DEFAULT_CUENTA_CONTROLADA_PLAN_DESCRIPTION);
        }
        else if (DEFAULT_PREPAGO_PLAN_CONVENTION.equalsIgnoreCase(mercado)) {
            sb.append(DEFAULT_PREPAGO_PLAN_DESCRIPTION);
        }
        else if (DEFAULT_NOT_REGISTERED_PLAN_CONVENTION
                .equalsIgnoreCase(mercado)) {
            sb.append(DEFAULT_NOT_REGISTERED_PLAN_DESCRIPTION);
        }
        return sb.toString();
    }

}
