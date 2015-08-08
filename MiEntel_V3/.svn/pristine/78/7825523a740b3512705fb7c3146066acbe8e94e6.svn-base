/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.converter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Converter para la presentacion de datos de trafico de Voz en formato
 * <i>mm:ss</i> sin limite de minutos. Ej: 103:34 minutos
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoVozMinSecConverter implements Converter {

    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        Double trafico = Double.valueOf(value);
        return trafico;
    }

    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {

        DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("es"));
        DecimalFormat formatMin = new DecimalFormat("####", dfs);
        DecimalFormat formatSeg = new DecimalFormat("00", dfs);
 
        Double trafico = (Double) value;
        double minutos = Math.floor(trafico / 60);
        double segundos = trafico % 60;

        return String.valueOf(formatMin.format(minutos) + ":"
                + formatSeg.format(segundos));
    }

}
