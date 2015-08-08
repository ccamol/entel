/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bea.portlet.PageURL;

/**
 * Clase utilitaria para la autenticacion e ingreso de Aplicaciones Externas
 * hacia MiEntel
 * 
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs). <br>
 *         Version inicial, creacion de clase
 * 
 * @author jlopez (I2B) en nombre Absalon Opazo (Atencion al Cliente, EntelPcs).
 *         Refactoring, renombre a ExternalAppsHelper y modificacion para uso de
 *         API de Bea
 */

public class ExternalAppsHelper  implements Serializable {


	 private static final long serialVersionUID = 1L;

	 private static final Logger LOGGER = Logger
	            .getLogger(ExternalAppsHelper.class);

	 private static final String VOZ_ATTTR_NAME = "";
	 
	 private static final String BAM_ATTTR_NAME = "bam";
	    
    /**
     * Entrega el path relativo al contexto indicado en <code>contexto</code>
     * 
     * @deprecated A partir de 09-06-2011, reemplazado por (@link {@link #getPageURL(HttpServletRequest, HttpServletResponse, String)} })
     * 
     * @param request
     *            HttpServletRequest para obtener perfil de usuario y
     * @param contexto
     *            String parametro de contexto recibido por la aplicacion, con
     *            el cual se identifica el path de contexto a retornar.
     * @return String Path de la pagina a la que esta asociada el contexto entregado
     */
    public static String getContextoRelativePath(final String mercado,
            final String contexto) {

        String name = "";
        String contextoPath = "";

		 try{

      		/* Obtener la cantidad de contextos que hay definidos en el archivo
      		 * properties Ej: misDatos,misUsuarios,cambioClave --> 3 contextos
      		 */
      		int cantCtx = Integer.parseInt(MiEntelProperties
      				.getProperty("parametro.contexto"));
      		
      		
      		for(int i=1; i<=cantCtx;i++){
      			/* Obtener el nombre del contexto definido en el properties.*/
	      		name = MiEntelProperties
	  				.getProperty("parametro.contexto."+i);
	      		
	      		/* 
	      		 * Comparar el contexto recibido por la aplicacion externa con los definidos en el properties y 
	      		 * obtener la URL correspondiente.
	      		 */
	      		if(name.equals(contexto)){
	      			try{
                        contextoPath = MiEntelProperties.getProperty("parametro.pagina"
                                + mercado + "." + i);
                        //Si encuentro el path de contexto, rompo ciclo for
	      			    break;
	      			}catch(Exception e){
	      				contextoPath = "";
	      			}
	      		}
      		}
      		
      		if(contextoPath.isEmpty()){
      			LOGGER.info("contexto Path es empty");
      			contextoPath = MiEntelProperties
  				.getProperty("mientel.default.streaming.url");
      		}

        } catch (Exception e) {
            LOGGER.error("Exception al obtener la URL para direccionar.", e);
        }

        return contextoPath;
    }

    /**
     * Entrega el pagelabel registrado en el archivo de propiedades de
     * aplicaciones externas para el valor dado en <code>contexto</code> y el
     * mercado <code>mercado</code>.<br>
     * Si el contexto no esta definido para el mercado indicado, se busca el
     * contexto generico, y si este no esta este metodo lanza una Exception
     * 
     * @param contexto
     *            String con el nombre del contexto entregado por la app externa
     * 
     * @param mercado
     *            String con el mercado [ss|cc|pp]
     * @param flagBam
     *            String con el flagBam [0|1]
     * @return String con page label asociado a <code>contexto</code>
     * @throws Exception
     *             Si ocurre algun error al recuperar el pagelabel
     */
   /* private static String getContextoPageLabel(String contexto, String mercado)
            throws Exception {
        
        if(Utils.isEmptyString(mercado) || Utils.isEmptyString(contexto)) {
            throw new NullPointerException(
                    "debe indicar un contexto y mercado para obtener el pagelabel");
        }
        
        String key = "contexto." + contexto + "." + mercado.toLowerCase();
        if(!MiEntelProperties.containsExternalAppsKey(key)) {
            key = "contexto." + contexto;
            if(!MiEntelProperties.containsExternalAppsKey(key)) {
                throw new Exception("no hay pagelabel definido para '"
                        + contexto + "' y '" + mercado + "'");
            }
        }
        
        String pageLabel = MiEntelProperties.getExternalAppsProperty(key);
        return pageLabel;
    }*/
    
    private static String getContextoPageLabel(String contexto, String mercado, String flagBam)
    throws Exception {

    String pageLabel = "";
    	
	if(Utils.isEmptyString(mercado) || Utils.isEmptyString(contexto) || Utils.isEmptyString(flagBam) ) {

	    LOGGER.error( new NullPointerException(
	            "debe indicar un contexto, mercado y flagBam para obtener el pagelabel"));
	}
	String key = "contexto." + contexto + "."+ getSubMercado(flagBam)+mercado.toLowerCase();
	
	if(!MiEntelProperties.containsExternalAppsKey(key)) {
	    key = "contexto." + contexto;
	}  
	
	if(!MiEntelProperties.containsExternalAppsKey(key)){		    	    	
		key = "contexto.dashBoard."+mercado.toLowerCase();
	}
	
	pageLabel = MiEntelProperties.getExternalAppsProperty(key);
	
	return pageLabel;
	}    

    /**
     * Metodo utilitario que compara el flagBam asociada al usuario y retorna
     * el submercado correspondiente al mismo.
     * 
     * @param flagBam 0 Voz,  1 Bam
     * @return String con el submercado, en caso de tener el flagBam 0, retorna vacio.
     * De lo contrario de ser 1 el flagBam retorna el submercado bam.
     */
    private static String getSubMercado(String flagBam){
    	return (flagBam.equals("1")) ? BAM_ATTTR_NAME : VOZ_ATTTR_NAME;
    }

    /**
     * Entrega el {@link PageURL} de la pagina de MiEntel que este asociado
     * contexto de aplicacion externa <code>contexto</code> y al mercado <code>mercado</code>
     * 
     * @param httpRequest
     *            {@link HttpServletRequest} de solicitud de ingreso a MiEntel
     * @param httpResponse
     *            {@link HttpServletResponse} de solicitud de ingreso a MiEntel
     * @param contexto
     *            String valor de parametro "contexto" que entegan las
     *            aplicaciones externas que desean ingesar a MiEntel
     * @return {@link PageURL} de la pagina de MiEntel asociada a
     *         <code>contexto</code>. null si no existe un page Label para el
     *         valor de contexto entregado
     */
    /*public static PageURL getPageURL(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse, String contexto, String mercado) {

        try {

            String pageLabel = getContextoPageLabel(contexto, mercado);
            PageURL url = PageURL.createPageURL(httpRequest, httpResponse, pageLabel, false);
            //Indica a la url que los caracteres '&' no los transforme a '&amp;'
            url.setForcedAmpForm(false);

            return url;

        } catch (Exception e) {
            LOGGER.error("Exception caught finding pagelabel for contexto '"
                    + contexto + "'", e);
            return null;
        }
    }*/
    
    public static PageURL getPageURL(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse, String contexto, String mercado, String flagBam) {

        try {
            String pageLabel = getContextoPageLabel(contexto, mercado, flagBam);

            PageURL url = PageURL.createPageURL(httpRequest, httpResponse, pageLabel, false);

            //Indica a la url que los caracteres '&' no los transforme a '&amp;'
            url.setForcedAmpForm(false);

            return url;

        } catch (Exception e) {
        	LOGGER.info("Exception caught finding pagelabel for contexto '"
                    + contexto + "'"+ e);
            LOGGER.error("Exception caught finding pagelabel for contexto '"
                    + contexto + "'", e);
            return null;
        }
    }    
}