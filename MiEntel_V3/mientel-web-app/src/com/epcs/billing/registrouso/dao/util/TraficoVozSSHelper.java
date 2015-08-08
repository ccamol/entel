/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.dao.util;

import java.lang.reflect.Method;

import com.epcs.billing.registrouso.types.ProductoType;
import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * 
 * Clase utilitaria para obtener el trafico total desde un objeto
 * {@link ProductoType} cuando este corresponde a un Producto Voz y se tiene
 * conocimiento de su tipo de plan.<br>
 * <p>
 * Los tipos de planes deben estar indicados en el recurso de propiedades de
 * MiEntel que maneja la clase {@link MiEntelProperties}, bajo la propiedad
 * <code>producto.voz.tipoPlan</code>
 * </p>
 * <p>
 * Por cada tipo de plan se deben definir 2 propiedades: id y keys:<br>
 * <b>id</b>: corresponde al codigo interno de entel con el que se conoce el
 * plan.<br>
 * <b>keys</b>: debido a que cada tipo de plan, entrega sus valores de trafico
 * en distintas propiedades del objeto {@link ProductoType}, esta propiedad
 * indica los indices de los metodos <code>getKey</code> de donde obtener los
 * valores de trafico.<br>
 * Ejemplo:<br>
 * Para las siguientes propiedades:
 * <pre>
 *    producto.voz.tipoPlan.normal.id=1
 *    producto.voz.tipoPlan.normal.keys=1,3
 * </pre>
 * 
 * El tipo de plan "normal" tiene como id el valor "1" y los keys con sus
 * traficos son: 1 y 3, por tanto se invocaran los metodos
 * <code>getKey1()</code> y <code>getKey3()</code> 
 * 
 * 
 * Si para un plan la propiedad keys tiene el valor <i>1,3,5</i>, entonces los
 * metodos <code>getKey1()</code>, <code>getKey3()</code> y
 * <code>getKey5()</code> tienen los valores de trafico para ese plan.
 * </p>
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoVozSSHelper {

    /**
     * Entrega el valor total del trafico de Voz para el <code>producto</code>
     * de acuerdo al tipo de plan indicado en <code>tipoPlan</code>.<br>
     * Este metodo asume que <code>producto</code> es un Producto Voz y que
     * <code>tipoPlan</code> corresponde al nombre del tipo de plan indicado en
     * {@link MiEntelProperties}.<br>
     * Si un tipo de plan esta indicado en {@link MiEntelProperties} de la
     * forma:<br>
     * 
     * <pre>
     * producto.voz.tipoPlan.miTipoPlan
     * </pre>
     * 
     * <code>tipoPlan</code> debe tener el valor <i>miTipoPlan</i> y en
     * {@link MiEntelProperties} debe existir el key
     * <code>producto.voz.tipoPlan.miTipoPlan.keys</code> con los indices de los
     * metodos <code>getKey</code> que entregan el trafico para este tipo de
     * plan.<br>
     * 
     * 
     * @param producto
     *            {@link ProductoType} de donde se obtendra el trafico voz
     * @param tipoPlan
     *            String nombre del tipo de plan al que corresponde
     *            <code>producto</code>
     * 
     * @return Double con el trafico total que indique <code>producto</code>
     * @throws Exception
     *             Si algun key del tipo de plan no es valido, o no se puede
     *             obtener el trafico desde <code>producto</code>
     */
    public static Double getTraficoVoz(ProductoType producto, String tipoPlan)
            throws Exception {

        // Recupera los keys de producto que tienen informacion de trafico
        String[] keys = MiEntelProperties.getProperty(
                "producto.voz.tipoPlan." + tipoPlan.trim() + ".keys").split(",");

        /*
         * Por cada key invoca al metodo getKey respectivo de producto y acumula
         * el valor en traficoTotal
         */
        double traficoTotal = 0d;
        for (int i = 0; i < keys.length; i++) {
            String trafico = getTraficoVozFromKey(producto, Integer
                    .parseInt(keys[i]));
            traficoTotal += calculaSegundos(Double.parseDouble(trafico));
        }

        return new Double(traficoTotal);

    }
    
    /**
     * Devuelve el total de segundos
     * @param minutos
     * @return
     */
    public static int calculaSegundos(double minutos) {
        String calculoParteEnteraString = String
                .valueOf(Math.floor(minutos) * 60);
        int posicionPuntoDecimal = calculoParteEnteraString.indexOf(".");
        calculoParteEnteraString = calculoParteEnteraString.substring(0,
                posicionPuntoDecimal);

        String calculoParteDecimalString = String.valueOf(minutos);
        posicionPuntoDecimal = calculoParteDecimalString.indexOf(".");
        calculoParteDecimalString = calculoParteDecimalString.substring(
                posicionPuntoDecimal + 1, calculoParteDecimalString.length());
        
        return Integer.parseInt(calculoParteEnteraString)
                + Integer.parseInt(calculoParteDecimalString);
    }

    /**
     * Obtiene el valor del metodo "getKeyX" de <code>producto</code> segun el
     * valor que indique <code>key</code>
     * 
     * @param producto
     *            {@link ProductoType} de donde se obtiene el trafico
     * @param key
     *            int indice del metodo "getKey" que debe ser invocado
     * @return String con el valor del trafico para el metodo que indique
     *         <code>key</code>. Este metodo entrega <code>null</code> si el key
     *         no es valido o alguna Exception es lanzada al recuperar el
     *         trafico
     * @throws Exception
     *             Si ocurre una Exception al invocar el metodo getKey de
     *             <code>producto</code>
     */
    private static String getTraficoVozFromKey(ProductoType producto, int key)
            throws Exception {

        Class<?> argumentClass[] = {};
        Object argumentParams[] = {};

        Method method = producto.getClass().getMethod(
                "getKey" + String.valueOf(key), argumentClass);

        String trafico = (String) method.invoke(producto, argumentParams);
        
        return trafico;

    }
}
