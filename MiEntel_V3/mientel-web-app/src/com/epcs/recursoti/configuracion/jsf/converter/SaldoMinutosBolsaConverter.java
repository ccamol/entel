package com.epcs.recursoti.configuracion.jsf.converter;

import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.log4j.Logger;

import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * Converter para la presentacion de saldos de bolsas
 * 
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */

public class SaldoMinutosBolsaConverter implements Converter {
	
    private static final Logger LOGGER = Logger.getLogger(RutConverter.class);

    private static final String LANGUAGE = MiEntelProperties.getProperty("locale.language");

    private static final String COUNTRY = MiEntelProperties.getProperty("locale.country");

    private NumberFormat format = NumberFormat.getInstance(new Locale(LANGUAGE, COUNTRY));
	
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
    	
    	String outValue = null;
    	int saldoMinutos = 0;
    	
    	if(value instanceof Double) {
            try {
                Double saldoSegundos = (Double) value;
                saldoMinutos = saldoSegundos.intValue() / 60;
                
            } catch (Exception e) {
                LOGGER.warn("El objeto value no es un numero valido", e);
                return "";
            }
        }
        else if(value instanceof String) {
        	try {
                String saldoSegundos = (String) value;
                saldoMinutos = Integer.parseInt(saldoSegundos) / 60;
                
            } catch (Exception e) {
                LOGGER.warn("El objeto value no es un numero valido", e);
                return "";
            }
        }

        outValue = format.format(saldoMinutos);
        return outValue;
    	
    }
    

}
