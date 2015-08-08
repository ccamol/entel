/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.recarga.dao.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.epcs.recursoti.configuracion.DateHelper;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class MultitiendaHelper {

    /**
     * Genera un numero unico para identificar la transaccion
     * @return
     */    
    public static String generarCodigoUnicoTransaccion(){
        int limite = 9999;
        int valor = (int)(limite*Math.random());
        String total = "" + valor;
        for(int i=0; total.length()<4; ++i){
            total = total.concat("0");
        }
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
       return "01" + total + dateFormat.format(new Date());
    }
    
    public static String getPeriodoValidezRecargaMultitienda(Date fechaActual, int diasValidez){
        Calendar validez = Calendar.getInstance();
        validez.setTime(fechaActual);
        validez.add(Calendar.DATE, diasValidez);
        return DateHelper.format(validez.getTime(), "dd/MM/yyyy hh.mm");
    }

    
}
