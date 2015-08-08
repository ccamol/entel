package com.esa.ponline.portalbolsas.util;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Metodos utilitarios generales para MiEntel.<br>
 * Todo metodo de esta clase debe estar debidamente documentado con el fin de
 * entender su proposito y justificarlo.<br>
 * 
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class PCBUtils {

    /**
     * Constante con el valor por defecto para el separador de URL.
     */
    @SuppressWarnings("unused")
    private static final String DEFAULT_PATH_SEPARATOR = "/";
    /**
     * Constante con el nombre del url base de la aplicacion -> "app"
     */
    @SuppressWarnings("unused")
    private static final String MI_ENTEL_DESKTOP_URL = "miEntel.desktop.url";
    @SuppressWarnings("unused")
    private static final String MI_ENTEL_STREAMING_URL = "mientel.default.streaming.url";

    /**
     * Responde <code>true</code> si la siguiente condicion es verdadera:<br>
     * 
     * <pre>
     * (str == null || str.trim().equals(&quot;&quot;))
     * </pre>
     * 
     * @see #isNotEmptyString(String)
     * 
     * @param str
     *            String a evaluar
     * @return <code>true</code> si el String esta vacio. <code>false</code> en
     *         caso contrario
     */
    public static boolean isEmptyString(String str) {
        return str == null || str.trim().equals("");
    }
    
    /**
     * Formatea el valor dado deacuerod a el patron ###,###.###
     * 
     * @param valor
     *            a formatear
     * @return String con el valos formateado
     */
    public static String formatMoney(Double valor) {

        java.text.DecimalFormat myFormatter =
                (DecimalFormat) java.text.DecimalFormat.getInstance(new Locale("es", "cl"));
        myFormatter.applyPattern("###,###.###");
        // java.text.DecimalFormat myFormatter = new
        // java.text.DecimalFormat("###,###.###", );
        return myFormatter.format(valor);
    }
    
    public static String formatMoneyPuntos(Double valor) {
        java.text.DecimalFormat myFormatter = new java.text.DecimalFormat(
                "###,###.###");
        
        return myFormatter.format(valor).replace(",", ".");
    }
}
