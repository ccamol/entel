package com.epcs.aplicaciones.util;

import java.io.File;
import java.security.MessageDigest;
import java.security.PrivateKey;

import javax.portlet.PortletRequest;

import org.apache.log4j.Logger;

import cl.eftbanca.jsigner.certificados.Base64Coder;
import cl.eftbanca.jsigner.certificados.Firmador;

import com.bea.netuix.servlets.controls.portlet.PortletPresentationContext;
import com.epcs.bean.RutBean;
import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class AplicacionExternaUtil {
	
	
	private static final Logger LOGGER = Logger.getLogger(AplicacionExternaUtil.class);
	
	/**
     * Constante con el valor por defecto para la ruta URL del keystore.
     */
    private static final String MI_ENTEL_KEYSTORE_URL = "parametro.keystore";
    
    
    /**
     * Referencia al indice de la aplicacion Gestion de Cuenta existente en aplicacion.properties
     */
    private static final String INDICE_APP_GESTIONCUENTA = MiEntelProperties
            .getProperty("appExterna.gestionCuenta.indice");
    
    /**
     * Referencia al indice de la aplicacion Pago en Linea existente en aplicacion.properties
     */
    private static final String INDICE_APP_PAGOENLINEA= MiEntelProperties
            .getProperty("appExterna.pagoLinea.indice");
    

    /**
     * 
     * @return indice para aplicacion Gestion de Cuenta
     */
	public static String getIndiceAppGestioncuenta() {
		return INDICE_APP_GESTIONCUENTA;
	}

	/**
	 * 
	 * @return indice para aplicacion Pago en Linea
	 */
	public static String getIndiceAppPagoenlinea() {
		return INDICE_APP_PAGOENLINEA;
	}

	/**
	 * Obtener el Esquema para realizar el mapeo y obtener la URL de la aplicacion externa.
	 * 
	 * @param portletCtx
	 * @return
	 */
	public String obtenerEsquema(PortletPresentationContext portletCtx){
		return ( portletCtx.getInstanceLabel()
		 		.substring( (portletCtx.getInstanceLabel().lastIndexOf("_")) + 1 ) );
	}
	
	/**
	 * Generar la URL
	 * 
	 * @param rb RutBean
	 * @param id_session IDP
	 * @return
	 */
	public String generarURLResponse(RutBean rb, String id_session){
		return new StringBuilder()
        .append("&rut_clt=" + rb.getNumero() + "-" + rb.getDigito())
        .append("&id_session=" + id_session)
        .append("&timestamp=" + System.currentTimeMillis()).toString();
	}
	
	/**
	 * Completar la URL para acceder a la Aplicacion Externa.
	 * 
	 * @param req
	 * @param response
	 * @param esquema
	 * @return
	 */
	public String completarURLResponse(PortletRequest req, String response, String esquema){
		return new StringBuilder()
        .append(req.getPreferences().getValue(esquema,null))
        .append(response.concat("&firma=" + firmarDatos(generarFirma(response))))
        .toString();
	}
	
	/**
	 * Generar claves para el ingreso a la aplicacion externa
	 * 
	 * @param text
	 * @return
	 */
	 public String generarFirma(String text) {
	        MessageDigest md;
	        byte[] sha1hash = new byte[40];
	        try {
	            md = MessageDigest.getInstance("SHA-1");
	            md.update(text.getBytes("iso-8859-1"), 0, text.length());
	            sha1hash = md.digest();
	            return convertToHex(sha1hash);
	        } catch (Exception ex) {
	            return null;
	        }

	    }
	    
	 /**
	  * 
	  * @param data
	  * @return
	  */
	    public String convertToHex(byte[] data) {
	        StringBuffer buf = new StringBuffer();
	        for (int i = 0; i < data.length; i++) {
	            int halfbyte = (data[i] >>> 4) & 0x0F;
	            int two_halfs = 0;
	            do {
	                if ((0 <= halfbyte) && (halfbyte <= 9)) {
	                    buf.append((char) ('0' + halfbyte));
	                } else {
	                    buf.append((char) ('a' + (halfbyte - 10)));
	                }
	                halfbyte = data[i] & 0x0F;
	            } while (two_halfs++ < 1);
	        }
	        return buf.toString();
	    }
	   
	    
	    /**
	     * 
	     * @param datos
	     * @return
	     */
	    public String firmarDatos(String datos) {
	        //Firmamos los datos
	    	
	    	
	    	String rutaKeystore = MiEntelProperties.getProperty(MI_ENTEL_KEYSTORE_URL);
	    	
	    	File fileRepositorio = new File(rutaKeystore);
	    	byte[] firma;
	        try {
	        	PrivateKey privKey = Firmador.getPrivateKeyfromKeyStore(fileRepositorio, "EFT2010", null, null, "llaves", "EFT2010");
	            firma = Firmador.firmar(privKey, null, datos.getBytes()); 
	        } catch (Exception e) {
	        	LOGGER.error("Excepcion al firmar los datos, "+ e.getMessage());
	            return "";
	        }
	        return new String(Base64Coder.encode(firma));
	    }

		/**
		 * URL para acceder a la Aplicacion Externa.
		 * 
		 * @param req
		 * @param esquema
		 * @return
		 */
		public String obtenerURL(PortletRequest req, String esquema){
			return new StringBuilder()
	        .append(req.getPreferences().getValue(esquema,null))
	        .toString();
		}
	
}
