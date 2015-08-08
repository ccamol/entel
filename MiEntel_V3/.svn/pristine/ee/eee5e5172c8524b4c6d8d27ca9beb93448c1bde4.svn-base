/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.converter;

import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.log4j.Logger;

import com.epcs.bean.RutBean;
import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * JSF Converter para presentacion de Strings que representan un Rut.<br>
 * Esta clase se apoya en {@link RutBean}
 *  
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class RutConverter implements Converter {

    
    private static final Logger LOGGER = Logger.getLogger(RutConverter.class);
    
//    private DecimalFormat format = new DecimalFormat("###,###,###.###",
//            new DecimalFormatSymbols(new Locale("es_CL")));

    private static final String LANGUAGE = MiEntelProperties.getProperty("locale.language");

    private static final String COUNTRY = MiEntelProperties.getProperty("locale.country");

    private NumberFormat format = NumberFormat.getInstance(new Locale(LANGUAGE,
            COUNTRY));
    
    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return RutBean.parseRut(value);
    }

    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        
        RutBean rut = null;
        if(value instanceof RutBean) {
            rut = (RutBean) value;
        }
        else if(value instanceof String) {
            try {
                rut = RutBean.parseRut((String)value);
            } catch (Exception e) {
                LOGGER.warn("String no contiene formato de rut valido", e);
                return "";
            }
        }
        else {
            LOGGER.warn("El objeto " + value + " no es un Rut conocido");
            return "";
        }

        String outValue = format.format(rut.getNumero()) + "-"
                + String.valueOf(rut.getDigito());
        return outValue;
    }
    
    
//    public static void main(String[] args) {
//        
//        RutConverter converter = new RutConverter();
//        System.out.println(converter.getAsString(null, null, new RutBean(15333552, '4')));
//        
//    }

}
