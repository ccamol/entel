/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.configuration.properties;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.esa.ponline.portalbolsas.configuration.error.ServiceMessages;
/**
 * Clase auxiliar para la obtencion de propiedades del sistema.<br>
 * Esta clase es un Wrapper para un Properties que es cargado al momento del
 * STARTUP de la aplicacion MiEntel.<br>
 * Su intenci&oacute;n es unificar el acceso a las propiedades de configuraci&oacute;n de
 * MiEntel evitando la carga de &eacute;ste en cada clase que se le necesite.
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class PCBProperties {
	
	private static final Logger LOGGER = Logger.getLogger(PCBProperties.class);
	
    private static ResourceBundle rb;
    
    private static ServiceMessages errorMessages;
    
    private static ResourceBundle externalAppsRb;

    /**
     * Carga el properties de MiEntel.<br>
     * Este m&eacute;todo se ejecuta en el STARTUP de la aplicaci&oacute;n, por tanto, al
     * interior de clases de MiEntel, no es necesario su uso.
     * 
     * @param propsFile
     *            String con el nombre del archivo de propiedades
     * 
     * @throws Exception
     *             Si <code>propsFile</code> no puede ser cargado o no es
     *             encontrado
     */
//    public static void load(String propsFile) throws Exception {
//
//        try {
//            rb = ResourceBundle.getBundle(propsFile, ResourceBundleControl
//                    .getControl());
//        } catch (Exception e) {
//            LOGGER.error( new Exception("Propiedades de MiEntel no pudieron " +
//            		"ser cargadas desde '" + propsFile + "'", e));
//        }
//        
//        String errorMessagesResourcePath = getProperty("miEntel.errorMessages.path");
//        if(Utils.isEmptyString(errorMessagesResourcePath)) {
//            LOGGER.error( new Exception("No existe la propiedad 'miEntel.errorMessages.path' necesaria" +
//            		" para obtener los mensajes de error"));
//        }
//        try {
//            errorMessages = new ServiceMessages(errorMessagesResourcePath);
//        } catch (Exception e) {
//            LOGGER.error( new Exception("No fue posible cargar Mensajes de errores de servicios " 
//                    + " con recurso '" + errorMessagesResourcePath + "'", e));
//        }
//
//        String externalAppsResourcePath = getProperty("miEntel.externalApps.path");
//        if (Utils.isEmptyString(externalAppsResourcePath)) {
//            LOGGER.error( new Exception(
//                    "No existe la propiedad 'miEntel.externalApps.path' necesaria"
//                            + " para el acceso de aplicaciones externas"));
//        }
//        try {
//            externalAppsRb = ResourceBundle.getBundle(externalAppsResourcePath,
//                    ResourceBundleControl.getControl());
//        } catch (Exception e) {
//            LOGGER.error( new Exception(
//                    "No fue posible cargar Mensajes de errores de servicios "
//                            + " con recurso '" + errorMessagesResourcePath
//                            + "'", e));
//        }
//
//        
//    }

    /**
     * Entrega el valor de la propiedad <code>key</code> como String.<br>
     * Wrapper para el metodo getProperty() del ResourceBundle de MiEntel
     * 
     * @param key
     *            String nombre propiedad
     * @return String con valor de la propiedad <code>key</code>.
     *         <code>null</code> si la propiedad no existe, o el Properties no
     *         ha sido inicializado
     */
    public static String getProperty(String key) {
        if (rb == null) {
            LOGGER.error("Key: "+key+" es nulo");
            return null;
        }
        return rb.getString(key);
    }
    
    /**
     * Entrega la instancia de {@link ServiceMessages} de MiEntel.<br>
     * Este recurso es cargado al momento de invocar {@link #load(String)} y se 
     * basa en la propiedad <code>miEntel.errorMessages</code>
     * @return {@link ServiceMessages} mensajes de errores de servicios
     */
    public static ServiceMessages getServiceMessages() {
    	if(null != errorMessages){
    		return errorMessages;
    	}
    	return new ServiceMessages();
    	
    }

    /**
     * Entrega propiedades registradas en el archivo de acceso de aplicaciones
     * externas, que haya sido definido en la propiedad
     * <i>miEntel.externalApps.path</i> de MiEntelProperties
     * 
     * @param key
     *            String con el nombre de la propiedad
     * @return String con el valor de la propiedad, null si la ruta del archivo
     *         de aplicaciones externas no fue definido
     */
    public static String getExternalAppsProperty(String key) {
        if(externalAppsRb == null) {
            return null;
        }
        return externalAppsRb.getString(key);
    }

    /**
     * Determina si el key entregado esta contenido en las propiedades de
     * aplicaciones externas
     * 
     * @param key el key a consultar
     * @return true si el key esta contenido, false en caso contrario
     * @throws Exception
     *             si key es null
     */
    public static boolean containsExternalAppsKey(String key) {
        if (externalAppsRb == null) {
            return false;
        }
        return externalAppsRb.containsKey(key);

    }
}
