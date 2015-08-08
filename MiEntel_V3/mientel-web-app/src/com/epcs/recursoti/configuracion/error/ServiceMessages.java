/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.error;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.epcs.recursoti.configuracion.ResourceBundleControl;
import com.epcs.recursoti.configuracion.Utils;

/**
 * Clase utilitaria para la obtenci&oacute;n de mensajes espec&iacute;ficos de
 * los Servicios de MiEntel.<br>
 * Los mensajes deben ser proporcionados en un archivo de properties.
 * 
 * <p>
 * Los mensajes de error pueden tener el siguiente formato:<br>
 * 
 * <pre>
 * error.<i>serviceName</i>.<i>serviceCode</i> = message
 * </pre>
 * 
 * o tambien:<br>
 * 
 * <pre>
 * error.<i>serviceName</i>.<i>messageName</i> = message
 * </pre>
 * 
 * 
 * Donde:
 * <ul>
 * <li><i>serviceName</i> : en un nombre l&oacute;gico para asociar los mensajes
 * de un servicio en particular.
 * <li><i>serviceCode</i> : es el c&oacute;digo de error del servicio al cual
 * est&aacute; asociado el mensaje de error.
 * <li><i>messageName</i> : nombre del mensaje
 * </ul>
 * Adicionalmente, los mensajes de error pueden contener par&aacute;metros. Para
 * eso los mensajes de error deben contener tokens de la forma <i>{n}</i>, con
 * <i>n</i> siendo un n&uacute;mero que va desde 0 (cero) para el primer
 * par&aacute;metro, 1 (uno) para el segundo, y as&iacute; sucesivamente.<br>
 * Ejemplo:
 * 
 * <pre>
 * error.<i>serviceName</i>.<i>serviceCode</i> = message {0} more message {1} ... {2} ...
 * </pre>
 *
 * <pre>
 * error.<i>serviceName</i>.<i>messageName</i> = message {0} more message {1} ... {2} ...
 * </pre>

 * </p>
 * 
 * 
 * <p>
 * Los mensajes de exito pueden tener el siguiente formato:<br>
 * 
 * <pre>
 * success.<i>serviceName</i>.<i>messageName</i> = message
 * </pre>
 * 
 * Donde:
 * <ul>
 * <li><i>serviceName</i> : en un nombre l&oacute;gico para asociar los mensajes
 * de un servicio en particular.
 * <li><i>messageName</i> : nombre con que se identifica al mensaje
 * </ul>
 * Los mensajes de exito pueden contener par&aacute;metros de la misma que los
 * mensaje de error
 * </p>
 * 
 * 
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ServiceMessages {

    private static final Logger LOGGER = Logger
            .getLogger(ServiceMessages.class);

    private static final String ERROR_PROPERTIES_PREFIX = "error";

    private static final String SUCCESS_PROPERTIES_PREFIX = "success";

    private ResourceBundle rb;

    /**
     * Constructor default
     */
    public ServiceMessages() {
        super();
    }

    /**
     * Entrega una instancia de {@link ServiceMessages} con el recurso
     * indicado en <code>errorMessagesResourcePath</code> cargado
     * 
     * @param errorMessagesResourcePath
     *            Path del recurso con los mensajes de error
     * @throws Exception
     *             Si el recurso no puede ser cargado correctamente
     */
    public ServiceMessages(String errorMessagesResourcePath)
            throws Exception {
        super();
        loadErrors(errorMessagesResourcePath);
    }

    /**
     * @return the rb
     */
    public ResourceBundle getRb() {
        return rb;
    }

    /**
     * @param rb
     *            the rb to set
     */
    public void setRb(ResourceBundle rb) {
        this.rb = rb;
    }

    private void loadErrors(String errorMessagesResourcePath) throws Exception {
        this.rb = ResourceBundle.getBundle(errorMessagesResourcePath,
                ResourceBundleControl.getControl());
    }

    /**
     * Entrega el mensaje de error del servicio <code>serviceName</code> para el
     * codigo de error de <code>serviceCode</code>
     * 
     * @param serviceCode
     *            c&oacute;digo de error del servicio
     * @param serviceName
     *            nombre del servicio
     * @return String con el mensaje de error. String vac&iacute;o si el mensaje no
     *         existe.
     */
    public String getErrorCodeMessage(String serviceCode, String serviceName) {

        String key = ERROR_PROPERTIES_PREFIX + "." + serviceName + "."
                + serviceCode;

        String errorMessage = "";
        try {
            errorMessage = rb.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.warn("Mensaje de error para el servicio-codigo: "
                    + serviceName + "-" + serviceCode + " no fue encontrado");
            errorMessage = getErrorMessage(serviceCode);
        } catch (Exception e) {
            LOGGER.error(
                    "Exception al intentar obtener mensaje error del servicio-codigo: "
                            + serviceName + "-" + serviceCode, e);
        }
        return errorMessage;

    }

    /**
     * Entrega el mensaje de error del servicio <code>serviceName</code> para el
     * codigo de error de <code>serviceCode</code>, con los par&aacute;metros del
     * mensaje que se indiquen en <code>args</code>
     * 
     * @param serviceCode
     *            c&oacute;digo de error del servicio
     * @param serviceName
     *            nombre del servicio
     * @param args
     *            array de Strings con los par&aacute;metros del mensaje a reemplazar
     * @return String con el mensaje de error. String vac&iacute;o si el mensaje no
     *         existe.
     */
    public String getErrorCodeMessage(String serviceCode, String serviceName,
            String[] args) {
    
        // Recupera mensaje original
        String errorMessage = getErrorCodeMessage(serviceCode, serviceName);
    
        // reemplaza ocurrencias de '{x}' en el mensaje por los argumentos
        // en el mismo &iacute;ndice
        errorMessage = replaceArgsTokens(errorMessage, args);
    
        // retorna mensaje de error
        return errorMessage;
    }

    /**
     * Entrega un mensaje de error general, asociado al nombre
     * <code>messageName</code>.<br>
     * Estos mensajes deben estar en el archivo de mensajes de error con la
     * forma:<br>
     * <i>error.general.{messageName}</i>
     * 
     * @param messageName
     *            String nombre del mensaje
     * @return String con el mensaje
     */
    public String getErrorMessage(String messageName) {
        
        String key = ERROR_PROPERTIES_PREFIX + ".general." + messageName;

        String errorMessage = "";
        try {
            errorMessage = rb.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.warn("Mensaje de error '" + key + "' no fue encontrado");
        } catch (Exception e) {
            LOGGER.error(
                    "Exception al intentar obtener mensaje error general: '"
                            + messageName + "'", e);
        }
        return errorMessage;

    }
    
    /**
     * Entrega un mensaje de error general, asociado al nombre
     * <code>messageName</code>.<br>
     * Estos mensajes deben estar en el archivo de mensajes de error con la
     * forma:<br>
     * <i>error.general.{messageName}</i>
     * 
     * @param messageName
     *            String nombre del mensaje
     * @return String con el mensaje
     */
    public String getErrorMessage(String messageName, String[] args) {
        
        //Recupera mensaje
        String errorMessage = getErrorMessage(messageName);
        
        //reemplaza tokens por valores en args
        errorMessage = replaceArgsTokens(errorMessage, args);
        
        //retorna mensaje
        return errorMessage;
    }

    /**
     * Entrega un mensaje de error del servicio identificado por
     * <code>serviceName</code>, asociado al nombre <code>messageName</code>.<br>
     * Estos mensajes deben estar en el archivo de mensajes de error con la
     * forma:<br>
     * <i>error.{serviceName}.{messageName}</i>
     * 
     * @param serviceName
     *            String nombre del servicio
     * @param messageName
     *            String nombre del mensaje
     * @return String con el mensaje
     */
    public String getErrorMessage(String serviceName, String messageName) {
        
        String key = ERROR_PROPERTIES_PREFIX + "." + serviceName + "." + messageName;

        String errorMessage = "";
        try {
            errorMessage = rb.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.warn("Mensaje de error '" + key + "' no fue encontrado");
        } catch (Exception e) {
            LOGGER.error(
                    "Exception al intentar obtener mensaje error general: '"
                            + messageName + "'", e);
        }
        return errorMessage;

    }

    /**
     * Entrega un mensaje del servicio identificado por <code>serviceName</code>
     * , asociado al nombre <code>messageName</code>.<br>
     * Estos mensajes deben estar en el archivo de mensajes de error con la
     * forma:<br>
     * <i>error.{serviceName}.{messageName}</i>
     * 
     * @param serviceName
     *            String nombre del servicio
     * @param messageName
     *            String nombre del mensaje
     * @param args
     *            array con los argumentos a incorporar en el mensaje
     * @return String con el mensaje
     */
    public String getErrorMessage(String serviceName, String messageName,
            String[] args) {
    
        //Recupera mensaje
        String errorMessage = getErrorMessage(messageName);
        
        //reemplaza tokens por valores en args
        errorMessage = replaceArgsTokens(errorMessage, args);
        
        //retorna mensaje
        return errorMessage;
    }

    /**
     * Entrega un mensaje de exito general, asociado al nombre
     * <code>messageName</code>.<br>
     * Estos mensajes deben estar en el archivo de mensajes de error con la
     * forma:<br>
     * <i>success.general.{messageName}</i>
     * 
     * @param messageName
     *            String nombre del mensaje
     * @return String con el mensaje
     */
    public String getSuccessMessage(String messageName) {
        
        String key = SUCCESS_PROPERTIES_PREFIX + ".general." + messageName;

        String message = "";
        try {
            message = rb.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.warn("Mensaje de exito '" + key + "' no fue encontrado");
        } catch (Exception e) {
            LOGGER.error(
                    "Exception al intentar obtener mensaje exito general: '"
                            + messageName + "'", e);
        }
        return message;

    }
    
    /**
     * Entrega un mensaje de exito general, asociado al nombre
     * <code>messageName</code>.<br>
     * Estos mensajes deben estar en el archivo de mensajes con la
     * forma:<br>
     * <i>success.general.{messageName}</i>
     * 
     * @param messageName
     *            String nombre del mensaje
     * @return String con el mensaje
     */
    public String getSuccessMessage(String messageName, String[] args) {
        
        //Recupera mensaje
        String message = getSuccessMessage(messageName);
        
        //reemplaza tokens por valores en args
        message = replaceArgsTokens(message, args);
        
        //retorna mensaje
        return message;
    }

    /**
     * Entrega el mensaje de exito del servicio <code>serviceName</code> con el
     * nombre <code>messageName</code>
     * 
     * @param messageName
     *            nomrbe del mensaje
     * @param serviceName
     *            nombre del servicio
     * @return String con el mensaje de exito. String vac&iacute;o si el mensaje no
     *         existe.
     */
    public String getSuccessMessage(String messageName, String serviceName) {
    
        String key = SUCCESS_PROPERTIES_PREFIX + "." + serviceName + "."
                + messageName;
    
        String errorMessage = "";
        try {
            errorMessage = rb.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.warn("Mensaje de error para el servicio-codigo: "
                    + serviceName + "-" + messageName + " no fue encontrado");
            errorMessage = getErrorMessage(messageName);
        } catch (Exception e) {
            LOGGER.error(
                    "Exception al intentar obtener mensaje error del servicio-codigo: "
                            + serviceName + "-" + messageName, e);
        }
        return errorMessage;
    
    }

    /**
     * Entrega el mensaje de error del servicio <code>serviceName</code> para el
     * codigo de error de <code>serviceCode</code>, con los par&aacute;metros del
     * mensaje que se indiquen en <code>args</code>
     * 
     * @param serviceCode
     *            c&oacute;digo de error del servicio
     * @param serviceName
     *            nombre del servicio
     * @param args
     *            array de Strings con los par&aacute;metros del mensaje a reemplazar
     * @return String con el mensaje de error. String vac&iacute;o si el mensaje no
     *         existe.
     */
    public String getSuccessMessage(String messageName, String serviceName,
            String[] args) {
    
        // Recupera mensaje original
        String message = getSuccessMessage(messageName, serviceName);
    
        // reemplaza ocurrencias de '{x}' en el mensaje por los argumentos
        // en el mismo &iacute;ndice
        message = replaceArgsTokens(message, args);
    
        // retorna mensaje de error
        return message;
    }

    /**
     * Reemplaza los tokens de <code>message</code> por los valores de
     * <code>args</code>
     * 
     * @param message
     * @param args
     * @return
     */
    private String replaceArgsTokens(String message, String[] args) {

        if (Utils.isEmptyString(message) || args == null
                || args.length == 0) {
            return message;
        }

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if(arg != null) {
                message = message.replaceAll("[{]" + i + "[}]", arg);
            }
        }

        return message;
    }

    
//    public static void main(String[] args) {
//        
//        
//        try {
//            ServiceErrorMessages errorMessages = new ServiceErrorMessages(
//                    "com/epcs/recursoti/configuracion/errorMessages");
//            
//            System.out.println(errorMessages.getErrorMessage("0000", "general"));
//            System.out.println(errorMessages.getErrorMessage("0003", "gestionSeguridad"));
//
//            
//            System.out.println(errorMessages.getErrorMessage("1234", "general"));
//            System.out.println(errorMessages.getErrorMessage("", ""));
//            System.out.println(errorMessages.getErrorMessage("", null));
//            System.out.println(errorMessages.getErrorMessage(null, ""));
//            System.out.println(errorMessages.getErrorMessage(null, null));
//
//            System.out.println(errorMessages.getErrorMessage("0002", "general", new String[]{"mis pantalones", "aspen"}));
//            System.out.println(errorMessages.getErrorMessage("0002", "general", new String[]{"mis pantalones"}));
//            System.out.println(errorMessages.getErrorMessage("0002", "general", new String[]{}));
//            System.out.println(errorMessages.getErrorMessage("0002", "general", null));
//            System.out.println(errorMessages.getErrorMessage("0002", "general", new String[]{null, ""}));
//
//            
//        } catch (Exception e) {
//           
//            LOGGER.error(e);
//        }
//        
//    }
    
}
