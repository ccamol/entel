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
public class TraficoDatosConverterDouble implements Converter {

    private static final int KILOBYTE = 1024;
    
    private DecimalFormat format = new DecimalFormat("###,###,###.###",
            new DecimalFormatSymbols(new Locale("es_CL")));
    
//    private static final int MEGABYTE = KILOBYTE*1024;
    
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
    	
        Double trafico = (Double) value;
        long traficoKb = trafico.intValue() / KILOBYTE;
        
        return format.format(traficoKb);
    }

}
