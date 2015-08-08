package com.esa.ponline.appmobile.util.security;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

public class PostOnDemmand {
	     
		 private static final Logger LOGGER = Logger.getLogger(PostOnDemmand.class);

	   	 /** 
	 * En caso de salir a Internet a tav�s de un Proxy, se deber�n modificar los
	 * siguientes par�metros
	      */  
	     //public static final boolean byProxy      = false;  
	     //public static final String  ProxyAddress = "N/A";  
	     //public static final int     ProxyPort    = 0;  
	       
	     /** 
	      * Direcciones que usamos para enviar peticiones  
	      */  
	// public static final String TargetURLSSL =
	// "http://mipcs.entelpcs.com/mipcs2/enviarClaveSitioMovil.do";
	// public static final String TargetURL =
	// "http://mipcs.entelpcs.com/mipcs2/enviarClaveSitioMovil.do";

	     /** 
	 * Por defecto haremos una petici�n HTTP GET. Cambiar a false para
	 * peticiones HTTP POST
	      */  
	     //public static final boolean ByGet = true;  
	     /** 
	 * Si queremos hacer conectarnos a un servidor seguro via SSL poner a true
	 * la siguiente variable
	      */  
	     //public static final boolean BySSL = false;  
	     /** 
	 * En caso de necesitar autentificacion con el servidor modificar los
	 * siguientes par�metros
	     */  
	     //public static final boolean Authenticate = false;  
	     //public static final String  UsernameCredentials = "TuUsuario";  
	     //public static final String  PasswordCredentials = "TuPassword";  
	     
	     public static String post(String host,String msisdn) {
		HttpClient httpClient = null; // Objeto a trav�s del cual realizamos las
										// peticiones
		HttpMethodBase request = null; // Objeto para realizar las peticiines
										// HTTP GET o POST
		          int             status = 0;         // C�digo de la respuesta HTTP  
		BufferedReader reader = null; // Se usa para leer la respuesta a la
										// petici�n
		String responseRspta = "NOK"; // Se usa para leer cada una de las lineas
										// de texto de la respuesta
		            
		          // Instanciamos el objeto  
		          httpClient = new HttpClient();  
		    
		/*
		 * if (ByGet){ // Invocamos por GET LOGGER.debug("Hago GET a :"+host);
		 * // � Nos conectamos a un servidor seguro ? // Observe que si est�n
		 * bien instalado JSSE (Java Secure Socket Extension), //el c�digo es
		 * exactamente igual if (BySSL) { request = new GetMethod(host); } else
		 * { request = new GetMethod(host); }
		          */      
		// Le indicamos que realize autom�ticamente el seguimiento de las
		// redirecciones
		                              // en caso de que existan.  
		          	request = new GetMethod(host);   
		              request.setFollowRedirects(true);  
		                
		              // A�adimos los par�metros que deseemos a la petici�n   
		                              // GET: http://dominio/pagina?nombre=Carlos+Garc%C3%ADa  
		              NameValuePair params[] = {new NameValuePair("movil", msisdn)};   
		              request.setQueryString(params);
		              
		
		// codigo comentado ESUAREZ
		         // } else {  
		              // Invocamos por POST  
		                
		//LOGGER.debug("Se realiza POST a :" + host);
		//request = new PostMethod(host);
		    
		// A�adimos los par�metros que deseemos a la petici�n
		    
		//((PostMethod) request).addParameter("movil", msisdn);
		// }
		//**
		            
		          try { 
		        	  
			// Leemos el c�digo de la respuesta HTTP que nos devuelve el
			// servidor
		              status = httpClient.executeMethod(request);
		              
		              LOGGER.debug("status:"+status);
		              
		              LOGGER.debug("request.getResponseBodyAsString():"+request.getResponseBodyAsString());
		      
		              // Vemos si la petici�n se ha realizado satisfactoriamente  
		              if (status!= 200) {  
				LOGGER.error("Error\t" + request.getStatusCode() + "\t" + request.getStatusText() + "\t" + request.getStatusLine());
		              } else {  
				// Leemos el contenido de la respuesta y realizamos el
				// tratamiento de la misma.
				// En nuestro caso, simplemente mostramos el resultado por la
				// salida est�ndar
				reader = new BufferedReader(new InputStreamReader(request.getResponseBodyAsStream(), request.getResponseCharSet()));
		                  responseRspta   = reader.readLine();  
		              }  
		          } catch (Exception ex){  
		             LOGGER.error("Error\t: " , ex);  
		                
		              ex.printStackTrace();  
		          } finally {  
		              // Liberamos la conexi�n. (Tambi�n libera los stream asociados)  
		              request.releaseConnection();  
		          }  
		          return responseRspta;
		    }  
	    
}
