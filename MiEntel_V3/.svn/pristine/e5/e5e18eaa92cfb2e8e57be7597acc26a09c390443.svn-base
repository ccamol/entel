/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.recarga.dao.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.epcs.bean.MontoRecargaBean;
import com.epcs.recursoti.configuracion.ParametrosHelper;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class EntelTicketHelper {

    /**
     * Genera un numero unico para identificar la transaccion
     * @return
     */ 
    public static String generarCodigoUnicoTransaccion(){
        int limite = 999999;        
        int valor = (int)(limite*Math.random());
        String total = "" + valor;
        for(int i=0; total.length()<6; ++i){
        total = total.concat("0");
        }
        SimpleDateFormat dateFormat = new
                SimpleDateFormat("yyyyMMddHHmmss");
        return "" + total + dateFormat.format(new Date());
    }

    /**
     * Devuelve la duracion en dias la validez de la recarga
     * @deprecated A partir de 02-06-2011, reemplazado por (@link {@link #getVigenciaMontoEntelTicket(String)}) 
     * @return
     */ 
    public static String getPeridoValidezRecargaEntelTicket(double montoCarga) {

        if(montoCarga == 2000)
            return "30";
        if(montoCarga == 3500)
            return "30";
        if(montoCarga == 5000)
            return "60";
        if(montoCarga == 10000)
            return "90";
        if(montoCarga == 15000)
            return "90";
        
        return null;
        
    }

    /**
     * Entrega la vigencia de un monto de recarga EntelTicket, segun el monto de
     * la recarga.<br>
     * Este metodo se apoya en los parametros definidos en
     * <code>parametros.montoRecargaEntelTicket</code>
     * 
     * @param monto String con el valor del monto 
     * @return String con la vigencia del monto
     */
    public static String getVigenciaMontoEntelTicket(String monto) {
        
        MontoRecargaBean montoBean = ParametrosHelper
                .getMontoRecargaEntelTicket(monto);
        if (montoBean == null) {
            return null;
        }
        else {
            return montoBean.getVigencia();
        }
        
    }

    
    
}
