/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.converter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Converter para la presentacion de valores de trafico de Datos
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class TraficoDatosConverterBAMDouble implements Converter {

     private DecimalFormat format = new DecimalFormat("#.#",
            new DecimalFormatSymbols(new Locale("es_CL")));
    
    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        try {
            Integer trafico = Integer.valueOf(value);
            return trafico;
        } catch (Exception e) {
            return null;
        }
        
    }

    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent compontent, Object value) {
        StringBuilder cuotaString = new StringBuilder();
        double cuotaNumerica = 0;
        try{
            cuotaNumerica = Double.parseDouble(String.valueOf(value));
            if (cuotaNumerica < 1) { // La unidad son KB
                cuotaString.append(new Double((cuotaNumerica * 1024)).intValue());
                cuotaString.append("KB");
            }
            else if (cuotaNumerica < 1024 && cuotaNumerica >= 1) { // La unidad son
                                                                   // MB
                cuotaString.append(cuotaNumerica);
                cuotaString.append("MB");
            }
            else if (cuotaNumerica >= 1024) { // La unidad son GB
                cuotaString.append(format.format(cuotaNumerica / 1024));
                cuotaString.append("GB");
            }
        }catch (Exception e) {
            cuotaString.append(value);
        }
        return cuotaString.toString();
    }

}
