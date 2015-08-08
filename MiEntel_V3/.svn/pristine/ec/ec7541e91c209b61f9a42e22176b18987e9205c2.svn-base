/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion;

/**
 * Clase utilitaria para resolver operaciones de negocio de uso frecuente
 * en la aplicacion de MiEntel
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class MiEntelBusinessHelper {

    /*
     * Mercados
     */
    
    /**
     * Referencia a sigla mercado cuenta controlada existente en aplicacion.properties
     */
    private static final String MERCADO_CUENTA_CONTROLADA = MiEntelProperties
            .getProperty("parametros.plan.cuentacontrolada.sigla");

    /**
     * Referencia a sigla mercado suscripcion existente en aplicacion.properties
     */
    private static final String MERCADO_SUSCRIPCION = MiEntelProperties
            .getProperty("parametros.plan.suscripcion.sigla");

    /**
     * Referencia a sigla mercado prepago existente en aplicacion.properties
     */
    private static final String MERCADO_PREPAGO = MiEntelProperties
            .getProperty("parametros.plan.prepago.sigla");

    
    /*
     * Atributos auto atencion
     */
    
    /**
     * Valor de AAA: Titular
     */
    private static final String TITULAR = MiEntelProperties
            .getProperty("aaa.titular.code");

    /**
     * Valor de AAA: control total
     */
    private static final String CONTROL_TOTAL = MiEntelProperties
            .getProperty("aaa.controlTotal.code");

    /**
     * Valor de AAA: control parcial
     */
    private static final String CONTROL_PARCIAL = MiEntelProperties
            .getProperty("aaa.controlParcial.code");

    /**
     * Valor de AAA: consulta
     */
    private static final String CONSULTA = MiEntelProperties
            .getProperty("aaa.consulta.code");


    /*
     * BAM
     */
    
    /**
     * Valor 'true' de 'flagBam' 
     */
    private static String FLAG_BAM_TRUE = MiEntelProperties
            .getProperty("flagBam.usuarioBAM");

    /**
     * Valor 'false' de 'flagBam' 
     */
    private static String FLAG_BAM_FALSE = MiEntelProperties
            .getProperty("flagBam.usuarioNoBAM");    
    
    /**
     * Referencia a sigla categoria prepago plus (alto valor) existente en aplicacion.properties
     */
    private static final String CATEGORIA_PREPAGO_ALTO_VALOR = MiEntelProperties
            .getProperty("parametros.categoriausuario.diferenciacioncanalespp.altovalor");
    
    
    
    /**
     * @return String sigla mercado suscipcion
     */
    public static String getSiglaSuscripcion() {
        return MERCADO_SUSCRIPCION;
    }
    
    /**
     * 
     * @param mercado sigla a comparar
     * 
     * @return true si <code>mercado</code> corresponde a sigla mercado
     *         suscripcion. false en caso contrario
     */
    public static boolean isMercadoSuscripcion(String mercado) {
        return !Utils.isEmptyString(mercado)
                && MERCADO_SUSCRIPCION.equalsIgnoreCase(mercado);
    }
    
    /**
     * @return String sigla mercado cuenta controlada
     */
    public static String getSiglaCuentaControlada() {
        return MERCADO_CUENTA_CONTROLADA;
    }
    
    /**
     * 
     * @param mercado sigla a comparar
     * 
     * @return true si <code>mercado</code> corresponde a sigla mercado
     *         cuenta controlada. false en caso contrario
     */
    public static boolean isMercadoCuentaControlada(String mercado) {
        return !Utils.isEmptyString(mercado)
                && MERCADO_CUENTA_CONTROLADA.equalsIgnoreCase(mercado);
    }
    
    /**
     * @return String sigla categoria prepago plus (alto valor)
     */
    public static String getSiglaPrepagoPlus() {
        return CATEGORIA_PREPAGO_ALTO_VALOR;
    }
    
    /**
     * 
     * @param categoriaCliente sigla a comparar
     * 
     * @return true si <code>categoriaCliente</code> corresponde a la sigla categoria prepago plus (alto valor)
     */
    public static boolean isCategoriaPrepagoPlus(String categoriaCliente) {
        return Utils.isNotEmptyString(categoriaCliente)
                && CATEGORIA_PREPAGO_ALTO_VALOR.equalsIgnoreCase(categoriaCliente);
    }


    /**
     * @return String sigla mercado prepago
     */
    public static String getSiglaPrepago() {
        return MERCADO_PREPAGO;
    }

    /**
     * 
     * @param mercado sigla a comparar
     * 
     * @return true si <code>mercado</code> corresponde a sigla mercado
     *         prepago. false en caso contrario
     */
    public static boolean isMercadoPrepago(String mercado) {
        return !Utils.isEmptyString(mercado)
                && MERCADO_PREPAGO.equalsIgnoreCase(mercado);
    }

    /**
     * @return array de String con todas las siglas de mercados
     */
    public static String[] getMercados() {

        return new String[] { MERCADO_SUSCRIPCION, MERCADO_CUENTA_CONTROLADA,
                MERCADO_PREPAGO };
    }

    /**
     * 
     * @return array de String con todos valores de Atributos de Auto atencion
     */
    public static String[] getAAAs() {
        return new String[] {TITULAR, CONTROL_TOTAL, CONTROL_PARCIAL, CONSULTA};
    }
    
    /**
     * 
     * @return valor AAA titular
     */
    public static String getAAATitular() {
        return TITULAR;
    }

    /**
     * 
     * @return valor AAA Control total
     */
    public static String getAAAControlTotal() {
        return CONTROL_TOTAL;
    }

    /**
     * 
     * @return valor AAA control parcial
     */
    public static String getAAAControlParcial() {
        return CONTROL_PARCIAL;
    }

    /**
     * 
     * @return valor AAA consultar
     */
    public static String getAAAConsultar() {
        return CONSULTA;
    }

    /**
     * 
     * @param aaa valor de Atributo Auto atencion a comparar
     * @return true si <code>aaa</code> corresponde al valor de titular
     */
    public static boolean isAAATitular(String aaa) {
        return !Utils.isEmptyString(aaa) && TITULAR.equalsIgnoreCase(aaa);
    }

    /**
     * 
     * @param aaa valor de Atributo Auto atencion a comparar
     * @return true si <code>aaa</code> corresponde al valor de titular
     */
    public static boolean isAAATitular(int aaa) {
        return isAAATitular(String.valueOf(aaa));
    }
    
    /**
     * 
     * @param aaa
     *            valor de Atributo Auto atencion a comparar
     * @return true si <code>aaa</code> corresponde al valor de control total
     */
    public static boolean isAAAControlTotal(String aaa) {
        return !Utils.isEmptyString(aaa) && CONTROL_TOTAL.equalsIgnoreCase(aaa);
    }
    
    /**
     * 
     * @param aaa
     *            valor de Atributo Auto atencion a comparar
     * @return true si <code>aaa</code> corresponde al valor de control total
     */
    public static boolean isAAAControlTotal(int aaa) {
        return isAAAControlTotal(String.valueOf(aaa));
    }

    /**
     * 
     * @param aaa
     *            valor de Atributo Auto atencion a comparar
     * @return true si <code>aaa</code> corresponde al valor de control parcial
     */
    public static boolean isAAAControlParcial(String aaa) {
        return !Utils.isEmptyString(aaa)
                && CONTROL_PARCIAL.equalsIgnoreCase(aaa);
    }
    
    /**
     * 
     * @param aaa
     *            valor de Atributo Auto atencion a comparar
     * @return true si <code>aaa</code> corresponde al valor de control parcial
     */
    public static boolean isAAAControlParcial(int aaa) {
        return isAAAControlParcial(String.valueOf(aaa));
    }

    /**
     * 
     * @param aaa
     *            valor de Atributo Auto atencion a comparar
     * @return true si <code>aaa</code> corresponde al valor de consulta
     */
    public static boolean isAAAConsulta(String aaa) {
        return !Utils.isEmptyString(aaa) && CONSULTA.equalsIgnoreCase(aaa);
    }
    
    /**
     * 
     * @param aaa
     *            valor de Atributo Auto atencion a comparar
     * @return true si <code>aaa</code> corresponde al valor de consulta
     */
    public static boolean isAAAConsulta(int aaa) {
        return isAAAConsulta(String.valueOf(aaa));
    }
    
    /**
     * Indica si el usuario es BAM basado en el atributo 'flagBam' del perfil de
     * usuario
     * 
     * @param flagBam
     *            valor del flag bam del perfil de usuario
     * @return true si <code>flagBam</code> corresponde al valor de usuario Bam
     * @see #isNotUserBam(String)
     */
    public static boolean isUserBam(String flagBam) {
        return Utils.isNotEmptyString(flagBam) && FLAG_BAM_TRUE.equals(flagBam);
    }

    /**
     * Indica si el usuario no es BAM basado en el atributo 'flagBam' del perfil
     * de usuario
     * 
     * @param flagBam
     *            valor del flag bam del perfil de usuario
     * @return true si <code>flagBam</code> NO corresponde al valor de usuario
     *         Bam
     * @see #isUserBam(String)
     */
    public static boolean isNotUserBam(String flagBam) {
        return Utils.isNotEmptyString(flagBam)
                && FLAG_BAM_FALSE.equals(flagBam);
    }

    /**
     * Entrega el valor 'true' para flagBam
     * @return String con valor 'true' para flag Bam
     * @see #getFlagBamFalse()
     */
    public static String getFlagBamTrue() {
        return FLAG_BAM_TRUE;
    }

    /**
     * Entrega el valor 'false' para flagBam
     * @return String con valor 'false' para flag Bam
     * @see #getFlagBamTrue()
     */
    public static String getFlagBamFalse() {
        return FLAG_BAM_FALSE;
    }

    
}
