/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.converter;

import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * Converter para la presentacion de valores de trafico de Datos
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class TraficoDatosConverter implements Converter {

    private static final int KILOBYTE = 1024;
    
    /*private DecimalFormat format = new DecimalFormat("###,###,###.###",
            new DecimalFormatSymbols(new Locale("es_CL")));*/
    
    private static final String LANGUAGE = MiEntelProperties.getProperty("locale.language");
	private NumberFormat format = NumberFormat.getInstance(new Locale(LANGUAGE));
    
//    private static final int MEGABYTE = KILOBYTE*1024;
    
    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        try {
            Long trafico = Long.valueOf(value);
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
        long traficoKb;

        try{
            Long trafico = Long.valueOf(value.toString());            
            traficoKb = trafico / KILOBYTE;
        
        }catch (Exception e) {
            return "";
        }
        return format.format(traficoKb);
    }

}
