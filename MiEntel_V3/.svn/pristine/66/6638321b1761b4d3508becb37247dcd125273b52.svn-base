/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bea.netuix.servlets.manager.AppContext;
import com.bea.p13n.cache.Cache;
import com.bea.p13n.cache.CacheFactory;
import com.bea.portlet.GenericURL;
import com.epcs.billing.balance.util.ArcFour;

/**
 * Metodos utilitarios generales para MiEntel.<br>
 * Todo metodo de esta clase debe estar debidamente documentado con el fin de
 * entender su proposito y justificarlo.<br>
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class Utils {

    /**
     * Constante con el valor por defecto para el separador de URL.
     */
    private static final String DEFAULT_PATH_SEPARATOR = "/";
    /**
     * Constante con el nombre del url base de la aplicacion -> "app"
     */
    private static final String MI_ENTEL_DESKTOP_URL = "miEntel.desktop.url";
    private static final String MI_ENTEL_STREAMING_URL = "mientel.default.streaming.url";

    /**
     * Responde <code>true</code> si la siguiente condicion es verdadera:<br>
     * 
     * <pre>
     * (str == null || str.trim().equals(&quot;&quot;))
     * </pre>
     * 
     * @see #isNotEmptyString(String)
     * 
     * @param str
     *            String a evaluar
     * @return <code>true</code> si el String esta vacio. <code>false</code> en
     *         caso contrario
     */
    public static boolean isEmptyString(String str) {
        return str == null || str.trim().equals("");
    }
    
    /**
     * Responde <code>true</code> si la siguiente condicion es falsa:<br>
     * 
     * <pre>
     * (str == null || str.trim().equals(&quot;&quot;))
     * </pre>
     * 
     * @see #isEmptyString(String)
     * 
     * @param str
     *            String a evaluar
     * @return <code>true</code> si el String no esta vacio. <code>false</code> en
     *         caso contrario
     */
    public static boolean isNotEmptyString(String str) {
        return !isEmptyString(str);
    }

    /**
     * Entrega un String con su 1era letra en mayusculas (capitalizado)
     * 
     * @param str
     * @return String con 1era capitalizado, Si el string es largo 1, entrega el
     *         mismo String afectado por toUpperCase(). Si el String es null o
     *         vacio, este metodo no hace nada y retorna el mismo valor entrante
     */
    public static String capitalize(String str) {

        if (isEmptyString(str)) {
            return str;
        }

        if (str.length() == 1) {
            return str.toUpperCase();
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    

    /**
     * Formatea el valor dado deacuerod a el patron ###,###.### 
     * 
     * @param valor a formatear
     * @return String con el valos formateado
     */
    public static String formatMoney(Double valor) {
    	
    	java.text.DecimalFormat myFormatter =  (DecimalFormat) java.text.DecimalFormat.getInstance(new Locale("es","cl"));
    	myFormatter.applyPattern("###,###.###");
    	//java.text.DecimalFormat myFormatter = new java.text.DecimalFormat("###,###.###", );
        return myFormatter.format(valor);
    }
    
    public static String formatMoneyPuntos(Double valor) {
        java.text.DecimalFormat myFormatter = new java.text.DecimalFormat(
                "###,###.###");
        
        return myFormatter.format(valor).replace(",", ".");
    }
    
    /**
     * Formatea el valor dado deacuerdo a el patron ###.###.###,## 
     * 
     * @param valor a formatear (double o int)
     * @return String con el valos formateado
     */
    public static String formatPuntos(Object numero, Locale locale) {
    	NumberFormat nf = NumberFormat.getNumberInstance(locale);
    	DecimalFormat df = (DecimalFormat)nf;    	      
        return df.format(numero);
    }
       

    public static String getMiEntelPortalPath(final HttpServletRequest request) {

        AppContext appContext = AppContext.getAppContext(request);

        StringBuffer url = new StringBuffer();

        // File based
        if (appContext.isDotPortal()) {
            url.append(appContext.getDesktopPathFileBased());
        }
        // Streaming
        else {
            String prefix = MiEntelProperties.getProperty(MI_ENTEL_DESKTOP_URL);
            String desktopPath = appContext.getDesktopPath();
            String portalPath = appContext.getPortalPath();

            url.append(DEFAULT_PATH_SEPARATOR);
            url.append(prefix);

            if (desktopPath != null && portalPath != null) {
                url.append(DEFAULT_PATH_SEPARATOR);
                url.append(portalPath);
                url.append(DEFAULT_PATH_SEPARATOR);
                url.append(desktopPath);
            }
            else {
                String streamingUrl = MiEntelProperties
                        .getProperty(MI_ENTEL_STREAMING_URL);
                url.append(streamingUrl);
            }
        }
        
        return url.toString();
    }
    
    /**
     * Metodo utilitario que permite obtener la url base de la aplicacion de
     * mientel. Cabe anotar que como el contenido de mientel es de tipo
     * streaming, la url contiene el path tanto del desktop como del portal
     * definidos.
     * 
     * @param request
     *            Un objeto HttpServletRequest para poder obtener el contexto de
     *            la aplicacion web.
     * @return una cadena con la url base de la aplicacion.
     */
    public static String getMiEntelBaseURL(final HttpServletRequest request) {
        
        StringBuffer url = new StringBuffer();
        url.append(request.getContextPath());
        url.append(getMiEntelPortalPath(request));

        return url.toString();
    }
    
    /**
     * Metodo de utilidad para parsear numeros asegurando que siempre se devuelva un {@link Number}.
     * Esta pensado para ser usado en la extracion de datos desde objetos que encapsulan informacion 
     * de respuesta de servicios web. Generalmente, dichos objetos usan el tipo String para todos 
     * sus atributos, incluso para aquellos que debieran ser de otro tipo: numeros, fechas, etc.
     * Especificamente, este metodo se usa para evitar que el flujo normal de la aplicacion se vea
     * interrumpido por una excepcion de tipo {@link NumberFormatException}, en caso que el valor a 
     * parsear sea una cadena vacia ("").<br>
     * En caso que el valor de <code>n</code> no corresponda a un numero, se hace un parse a cero.<br>
     * El objeto devuelto se le debe hacer un cast al tipo adecuado. Dicho tipo debe ser conocido por 
     * quien invoca este metodo. 
     * @param <code>n</code> cadena que representa un numero
     * @param <code>type</code> tipo de numero a parsear: 1. {@link Interger} <br>2. {@link Float}
     * <br>3. {@link Double}
     * @return {@link Number} listo para hacerle un cast
     */
    public static Number parseNumber(String n, int type){
        Number nFormated = null;
        
        switch(type){
        case 1:
            try{
                nFormated = Integer.parseInt(n);
            } catch(NumberFormatException e){
                nFormated = Integer.parseInt("0");
            }
            break;
        case 2:
            try{
                nFormated = Float.parseFloat(n);
            } catch(NumberFormatException e){
                nFormated = Float.parseFloat("0.0");
            }
            break;
        case 3:
            try{
                nFormated = Double.parseDouble(n);
            } catch(NumberFormatException e){
                nFormated = Double.parseDouble("0.0");
            }
            break;
        }
        return nFormated;
    }
    
    /**Metodo para cambiar la codificacion de caracteres a una cadena*/
    public static String encode(String text, String encode) {
        try {
            return new String(text.getBytes(), encode);
        } catch (Exception e) {
            return text;
        }
    }
    
    /**Metodo para cambiar la codificacion de caracteres a una cadena
     * utilizando UTF-8*/
    public static String encodeUTF8(String text) {
        return encode(text, "UTF-8");
    }
    
    public static final String clearString(String text) throws Exception {

        char chars[] = { '\u00E1', '\u00E9', '\u00ED', '\u00F3', '\u00FA',
                '\u00C1', '\u00C9', '\u00CD', '\u00D3', '\u00DA', '\u00F1',
                '\u00D1' };

        char arr[] = text.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (arr[i] == chars[j]) {
                    return text;
                }
            }
        }

        return encodeUTF8(text);

    }
    
    /**
     * Obtiene el parametro presente en <code>request</code> con el nombre
     * <code>attributeName</code>.<br>
     * Este metodo busca el parametro para los nombres
     * <code>parameterName.toLowerCase()</code> y
     * <code>parameterName.toUpperCase()</code>
     * 
     * @param parameterName
     * @param request
     * @return
     */
    public static String getNonSensitiveParameterValue(String parameterName,
            ServletRequest request) {

        String paramValue = null;

        paramValue = (String) request.getParameter(parameterName.toLowerCase());
        if (Utils.isEmptyString(paramValue)) {
            paramValue = (String) request.getParameter(parameterName
                    .toUpperCase());
        }
        return paramValue;
    }

    /**
     * Elimina un item de una cache de Weblogic Portal
     * @param cacheName Nombre de cache
     * @param itemKey key del item en cache a elminar
     */
    public static void removeCacheItem(String cacheName, Object itemKey) {
        
        Cache cache = CacheFactory.getCache(cacheName);
        if (cache != null) {
            cache.remove(itemKey);
        }
        
    }
    
    /**
     * Establece los valores de headers en request y response para la accion de
     * logout
     * 
     * @param request 
     * @param response
     */
    public static void prepareHeadersForLogout(
            final HttpServletRequest request, final HttpServletResponse response) {

        // Forces caches to obtain a new copy of the page from the
        // origin server
        response.setHeader("Cache-Control", "no-cache");
        // Directs caches not to store the page under any circumstance
        response.setHeader("Cache-Control", "no-store");
        // Causes the proxy cache to see the page as "stale"
        response.setDateHeader("Expires", 0);
        // HTTP 1.0 backward compatibility
        response.setHeader("Pragma", "no-cache");

        request.setAttribute("_cache_refresh", "true");

    }
    
    /**
     * Entrega la url de logout, segun la property definia en
     * <code>miEntel.logout.url</code>. Si no esta definida, este metodo entrega
     * la url base de la aplicacion
     * 
     * @param request
     * @param response
     * @return String con url de logout de la app
     */
    public static String getLogoutURL(HttpServletRequest request,
            HttpServletResponse response) {
        String url = MiEntelProperties.getProperty("miEntel.logout.url");
        
        if (Utils.isEmptyString(url)) {
            GenericURL genericURL = GenericURL.createGenericURL(request,
                    response);
            genericURL.setTemplate("logout-http");
            url = genericURL.toString(true);
        }
        
        return url;
    }
    
    /**
     * Reemplaza en una cadena todos los saltos de linea 
     * por espacios en blanco 
     * @param str Cadena a reemplazar
     * @return Cadena reempalzada
     */
    public static String removeLineBreak(String str) {
        if (str != null && !str.trim().isEmpty()) {
            str = str.replaceAll("\\n", " ").replaceAll("\\r", " ");
            return str;
        } else {
            return str;
        }        
    }
    
    /**
     * Metodo utilizado para formatear un numero, dependiendo del Locale.
     * @param numero
     * @param locale
     * @return String
     */
    public static String formatPuntos(String numero, Locale locale) {
    	try {	
	    	NumberFormat nf = NumberFormat.getNumberInstance(locale); 
	    	DecimalFormat df = (DecimalFormat)nf;    	       
	        return df.format(Double.valueOf(numero));
    	} catch (Exception e) {    		
			return "0";
		}
    }
    
    /**
     * Metodo utilizado para formatear un numero #,##0.0.
     * @param Double     
     * @return String
     */
    public static String formatUnDecimal(Double valor){
    	DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMAN);
        DecimalFormat myFormatter = new DecimalFormat("#,###.#",symbols);
        myFormatter.setRoundingMode(RoundingMode.DOWN);
        return myFormatter.format(valor);
    }
    
    /**
     * Metodo utilizado para formatear un numero ###.
     * @param Double   
     * @return String
     */
    public static String formatSinDecimal(Double valor){
        DecimalFormat myFormatter = new DecimalFormat("###");           
        return myFormatter.format(valor);
    } 
    
    /**
     * Metodo que usa la clase ArcFour para encriptar un texto
     * @param texto Texto a encriptar
     * @param llaveEncriptacion Llave de encriptacion
     * @return Texto encriptado
     */
    public static String encriptar(String texto, String llaveEncriptacion) {
    	ArcFour arcfour = new ArcFour();
    	return arcfour.encriptar(texto, llaveEncriptacion);
    }
    
    /**
     * Rellena una cadena con un caracter especifico hasta alcanzar una longitud
     * determinada
     * @param str Cadena
     * @param padChar Caracter de relleno
     * @param maxLength
     * @return
     */
    public static String leftPad(String str, char padChar, int maxLength) {
		return String.format("%" + maxLength + "s", str).replace(' ', padChar);
    }
    
    /**
     * Funcion que elimina acentos y caracteres especiales de una cadena de texto.
     * @param input
     * @return cadena de texto limpia de acentos y caracteres especiales.
     */
    public static String removerAcentos(String input) {
       
        String original = MiEntelProperties.getProperty("parametro.acentos");    
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i=0; i<original.length(); i++) {         
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }
        return output;
    }
    
	/**
	 * Retorna el valor para una cookie guardada en el request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		String cookieValue = "";
		try {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if (c.getName().equals(cookieName)) {
					cookieValue = c.getValue();
				}
			}
		} catch (Exception e) {
			cookieValue = "";
		}
		return cookieValue;
	}

	/**
	 * Retorna una lista de parametros delimitados por comillas simples y separados por coma
	 * @param lista lista separada solo por comas
	 * @return
	 */
	public static String buildListaComillasComas(String listaComas){
		StringBuffer sb = new StringBuffer(200);
		String aux = "";
		String [] arrayParams = listaComas.split("[,]");
		
		for(String param : arrayParams){
			sb.append("'").append(param).append("',");
		}
		
		aux = sb.toString();
		
		return aux.substring(0, aux.length()-1);
		
	}
    
}
