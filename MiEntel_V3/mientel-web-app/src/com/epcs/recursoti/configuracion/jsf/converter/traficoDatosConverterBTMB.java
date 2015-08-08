/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.converter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
public class traficoDatosConverterBTMB implements Converter {

    private static final int BYTESAMEGAYTES = 1024;  
    
	private static final String LANGUAGE = MiEntelProperties.getProperty("locale.language");
	private NumberFormat format = NumberFormat.getInstance(new Locale(LANGUAGE));
    
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
    	Float trafico;
        String numeroFormato="";    
        try{         	
        	trafico = Float.parseFloat(value.toString());
            trafico  = trafico / BYTESAMEGAYTES;// pasar de byte a kb
            trafico  = trafico / BYTESAMEGAYTES;// pasar de kb a mb
        
        }catch (Exception e) {
            return "";
        }
        try{            
        	numeroFormato = format.format(trafico); 
        	if(numeroFormato!=null && numeroFormato!=""){
        		String numero[] = numeroFormato.split(",");        		
        		if(numero.length>1){
        			if(Integer.parseInt(numero[1].substring(1)) > 5){
        				if(Integer.parseInt(numero[1].substring(0,1)) != 9){
        					numeroFormato = numero[0]+","+(Integer.parseInt(numero[1].substring(0,1))+1);
        				}else{
        					numeroFormato = numero[0]+","+numero[1].substring(0,1);
        				}
        				
        			}else{
        				numeroFormato = numero[0]+","+numero[1].substring(0,1);
        			}
        		}else{
        			numeroFormato=numeroFormato+",0";
        		}
        	}        	
        	
        }catch (Exception e) {
            return trafico+"";
        }
        return numeroFormato; 
    }

}
