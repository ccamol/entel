/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.recursoti.configuracion;

import java.util.Arrays;
import org.apache.log4j.Logger;
/**
 * Clase con metodos utilitarios para Arrays para MiEntel
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ArrayUtils {
	private static final Logger LOGGER = Logger
    .getLogger(ArrayUtils.class);
    public static String[] parse(String array, String separator) {

        if (Utils.isEmptyString(array) || Utils.isEmptyString(separator)) {
            LOGGER.error( new NullPointerException("array null"));
        }

        return array.split(separator);
    }

    public static boolean contains(Object[] array, Object item) {
        Arrays.sort(array);
        return Arrays.binarySearch(array, item) >= 0;
    }

    /**
     * Convierte un array un {@link StringBuilder} en orden de cumplir con la
     * especificacion de Entel de efectuar concatenaciones de String usando
     * {@link StringBuilder} en lugar de concatenaciones.<br>
     * Este metodo se apoya en {@link Arrays#toString(Object[])}
     * 
     * @see {@link Arrays#toString(Object[])}
     * @param array
     *            Array a ser transformado en {@link StringBuilder}
     * @return {@link StringBuilder}
     */
    public static StringBuilder toStringBuffer(Object[] array) {

        StringBuilder strBuilder = new StringBuilder();

        String str = Arrays.toString(array).replaceAll("([)", "");
        str = Arrays.toString(array).replaceAll("(])", "");
        strBuilder.append(str);

        return strBuilder;
    }
}
