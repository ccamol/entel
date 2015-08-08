/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.recarga.dao.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.epcs.bean.ParametrosWebPay;
import com.epcs.billing.recarga.dao.RecargaDAO;
import com.epcs.recursoti.configuracion.DateHelper;

/**
 * Clase con metodos utilitarios para las transacciones de webpay
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class WebPayHelper {
	private static final Logger LOGGER = Logger.getLogger(WebPayHelper.class);
    /**
     * Algoritmo generador de valores de Orden de compra para transacciones de
     * WebPay
     * 
     * @return String con valor de orden de compra
     */
    public static String generarIdOrdenDeCompra() {

        /*
         * Siguiendo logica de MiEntelV2, la orden de compra corresponde a un
         * timestamp del Date actual
         */
        long milis = Calendar.getInstance().getTimeInMillis();
        return String.valueOf(milis);
    }

    /**
     * Genera un numero unico estilo hash para identificar la transaccion.<br>
     * El algoritmo, respeta la misma logica de MiEntel V2
     * 
     * @return String con codigo unico transaccion
     */
    public static String generarCodigoUnicoTransaccion() {

        /*
         * Logica de codigo transaccion MiEntel v2
         */
        int limite = 9999;
        int valor = (int) (limite * Math.random());
        String total = "" + valor;
        for (int i = 0; total.length() < 4; ++i) {
            total = total.concat("0");
        }
        return "01"
                + total
                + DateHelper.format(new Date(),
                        DateHelper.FORMAT_yyyyMMddhhmmss);
    }

    private static final String REGEX_PARAM_NAME = "[a-zA-Z_][a-zA-Z0-9_]*";

    private static final String REGEX_PARAM_VALUE = "[a-zA-Z0-9_]+";

    /**
     * Construye una instancia de {@link ParametrosWebPay} basada en la lista de
     * parametros de transbank indicada en <code>respuestaTransbank</code>.<br>
     * El bean obtenido ayuda en la obtencion del valor de parametros
     * especificos de Transbank, ocultando la logica de parsing del mismo.
     * 
     * @param respuestaTransbank
     * @return
     * @throws Exception
     */
    public static ParametrosWebPay parseRespuestaTransbank(
            String respuestaTransbank) throws Exception {

        ParametrosWebPay parametrosWebPay = new ParametrosWebPay(
                respuestaTransbank);

        Matcher matcher = Pattern.compile(
                REGEX_PARAM_NAME + "(=)" + REGEX_PARAM_VALUE + "(&)?").matcher(
                respuestaTransbank);

        while (matcher.find()) {
            String match = matcher.group();
            String[] split = match.replaceAll("&", "").split("=");

            if (split == null || split.length != 2) {
                LOGGER.error( new Exception("Parametro no contiene formato valido: "
                        + match));
            }

            parametrosWebPay.putParameter(split[0], split[1]);
        }

        return parametrosWebPay;

    }

    /**
     * Extrae el parametro indicado en <code>parametro</code> desde un String de
     * respuesta obtenido desde Transbank
     * 
     * @param respuestaTransbank
     * @param parametro
     * @return String con valor de parametro. null si el parametro no es
     *         encontrado, o <code>respuestaTransbank</code> no tiene el formato
     *         esperado
     */
    public static String extraerParametroTransbank(String respuestaTransbank,
            String parametro) {

        String value = null;

        Matcher matcher = Pattern.compile(
                parametro + "(=)" + REGEX_PARAM_VALUE + "(&)?").matcher(
                respuestaTransbank);

        if (matcher.find()) {
            String match = matcher.group();
            String[] split = match.replaceAll("&", "").split("=");

            if (split != null && split.length == 2) {
                value = split[1];
            }

        }

        return value;
    }
    
//    public static void main(String[] args) {
//
//        String respuestaTransbank = "TBK_TIPO_TRANSACCION=TR_MALL&TBK_RESPUESTA=0&TBK_MONTO=200000&TBK_ORDEN_COMPRA=654321&TBK_FINAL_NUMERO_TARJETA=6623&TBK_FECHA_CONTABLE=0318&TBK_FECHA_TRANSACCION=0318&TBK_HORA_TRANSACCION=173102&TBK_ID_SESION=4051885600446623&TBK_ID_TRANSACCION=0477136406&TBK_COD_AUT_M001=281172&TBK_TASA_INTERES_MAX=0250&TBK_TIPO_PAGO_M001=VN&TBK_NUMERO_CUOTAS_M001=0&TBK_COD_RESP_M001=0&TBK_MONTO_TIENDA_M001=200000&TBK_ORDEN_TIENDA_M001=654321&TBK_MAC=xLYX3vLagyTzDK7t5WIPXNnD2a3nA9XwCyKo6h4KfBPXM7fv1TcYLCj4UpiCylwSyHB3gmSeeqpMRHJKsn586PhJu5rEB4fdfnCzYQ7ZY9YZELlL6sgLUIktksygqoIW3L6ug8gZ09CpxkvEHy8tnYeOC1fkS7IFw";
//
//        try {
//            ParametrosWebPay parametros = parseRespuestaTransbank(respuestaTransbank);
//            System.out.println(parametros);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
