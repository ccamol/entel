package com.epcs.recursoti.configuracion.jsf.converter;

import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * Converter para la presentacion de valores de Plan o cambio de plan
 * 
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */

public class PlanConverter implements Converter {
	
	//private DecimalFormat format = new DecimalFormat("###,###");
	
	private static final String LANGUAGE = MiEntelProperties.getProperty("locale.language");
	private NumberFormat format = NumberFormat.getInstance(new Locale(LANGUAGE));
	
	/* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return "";
    }

    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
    	
    	String valor = null;
    	try{
    		
    		valor = format.format(Long.parseLong(value.toString()));
    		
    	}catch (Exception e) {
    		valor = value.toString();
		}
    	return valor;
    	
    }
    

}
