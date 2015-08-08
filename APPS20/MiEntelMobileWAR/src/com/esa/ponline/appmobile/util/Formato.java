/* Propiedad de Entel S.A. Todos los derechos reservados */
package com.esa.ponline.appmobile.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.esa.ponline.appmobile.constants.Constants;

import weblogic.utils.encoders.BASE64Decoder;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class Formato {

	private static final Logger LOGGER = Logger.getLogger(Formato.class);
	
	public static String decode(String textoEntrada) throws Exception {
		try {
			return new String((new BASE64Decoder()).decodeBuffer(textoEntrada), "UTF-8");
		} catch (UnsupportedEncodingException uee) {
			return null;
		} catch (IOException ioe) {
			return null;
		}
	}
	
    public static String formatUnDecimal(Double valor){
    	//BigDecimal bd = new BigDecimal(valor);    	   	
    	DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMAN);
        DecimalFormat myFormatter = new DecimalFormat("#,###.#",symbols);        
        //return myFormatter.format(bd.setScale(1,BigDecimal.ROUND_DOWN).doubleValue());
        return myFormatter.format(valor);
    }
    
    public static String formatSinDecimal(Double valor){
    	DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMAN);
        DecimalFormat myFormatter = new DecimalFormat("###,###",symbols);
        return myFormatter.format(valor);
    }
    
    public static String formatPuntos(String numero, Locale locale) {
    	try {	
	    	NumberFormat nf = NumberFormat.getNumberInstance(locale); 
	    	DecimalFormat df = (DecimalFormat)nf;    	       
	        return df.format(Double.valueOf(numero));
    	} catch (Exception e) {
    		LOGGER.error("Error formateando el punto de los valores numericos: " + e.getMessage());
			return "0";
		}
    }
    
	public static String formatFechaSaldoReservado(String numero, Locale locale) {
		try {
			NumberFormat nf = NumberFormat.getNumberInstance(locale);
			DecimalFormat df = (DecimalFormat) nf;
			return df.format(Double.valueOf(numero));
		} catch (Exception e) {
			LOGGER.error("Error formateando el punto de los valores numericos: " + e.getMessage());
			return "0";
		}
	}

    public static String validaMsisdn(String msisdn){							
		String movilPref="";
        if(msisdn.length() == Integer.valueOf(Constants.LONGITUD_CONPREFIJO_MSISDN_ENTEL.getStringValue())){
        	movilPref = msisdn;
        }else if(msisdn.length() == Integer.valueOf(Constants.LONGITUD_SINPREFIJO_MSISDN_ENTEL.getStringValue())){
        	movilPref = Constants.PREFIJO_ENTEL.getStringValue() + msisdn;
        }else{
        	LOGGER.error("Formato de movil invalido: " + msisdn);
        	try {
				throw new Exception();
			} catch (Exception e) {
				LOGGER.error("Se genera exception en validacion del msisdn con o sin prefijo "+e.getMessage());
			}
        }
        return movilPref;
	}
    
    public static boolean isEmptyString(String str) {
        return str == null || str.trim().equals("");
    }
    

	public static boolean esNumerico(String textoNumerico) throws Exception {
		Pattern p = Pattern.compile("[0-9]{" + textoNumerico.length() + "}");
		Matcher m = p.matcher(textoNumerico);
		if (m.matches())
			return true;
		else
			return false;
	}
	
    public static String formatMoneyPuntos(Double valor) {
        java.text.DecimalFormat myFormatter = new java.text.DecimalFormat(
                "###,###.###");
        
        return myFormatter.format(valor).replace(",", ".");
    }
    
    public static String formatMoney(Double valor) {
        java.text.DecimalFormat myFormatter = new java.text.DecimalFormat(
                "###,###.###");
        return myFormatter.format(valor);
    }

	public static String getMD5(String text){
		String plaintext = text;
		String hashtext = "";
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(plaintext.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Excepcion lanzada debido a: " + e.getMessage());
			e.printStackTrace();
		}
		
		return hashtext;
	}

}
