/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.jsf.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.error.ServiceMessages;

/**
 * Esta clase ofrece metodos utilitarios para Controller JSF que desean agregar
 * mensajes de exito y error basados en lo que ofrece {@link ServiceMessages}.<br>
 * Considera los mensajes de error especificos de los servicios,
 * indentificados con <i>codigos de servicio</i> Esta clase es un apoyo para los
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class JSFMessagesHelper {

    /**
     * Singleton 
     */
    private JSFMessagesHelper() {
    }

    /**
     * Agregar un mensaje de ERROR al contexto 
     * @deprecated A partir de 28-07-2011, reemplazado por {@link #addErrorCodeMessage(String, String)}
     * @param msg
     */
    public static void addErrorMessage(String msg) {
        addFacesErrorMessage(msg);
    }

    /**
     * Agrega un mensaje de error del servicio <code>serviceName</code>
     * identificado con el nombre <code>messageName</code>
     * 
     * @param serviceName
     *            String nombre del servicio
     * @param messageName
     *            String nombre del mensaje
     */
    public static void addErrorMessage(String serviceName, String messageName) {
        ServiceMessages messages = MiEntelProperties.getServiceMessages();
        addFacesErrorMessage(messages.getErrorMessage(serviceName, messageName));
    }
    
    /**
     * Agrega un mensaje de error del servicio <code>serviceName</code>
     * identificado con el nombre <code>messageName</code>
     * 
     * @param serviceName
     *            String nombre del servicio
     * @param messageName
     *            String nombre del mensaje
     *            @param args array con los parametros del mensaje
     */
    public static void addErrorMessage(String serviceName, String messageName, String[] args) {
        ServiceMessages messages = MiEntelProperties.getServiceMessages();
        addFacesErrorMessage(messages.getErrorMessage(serviceName, messageName, args));
    }
    
    /**
     * Agrega un mensaje de error que este registrado bajo el nombre de
     * <code>messageName</code>
     * 
     * @param messageName
     *            String con el nombre del mensaje a rescatar
     */
    public static void addServiceErrorMessage(String messageName) {
        ServiceMessages messages = MiEntelProperties
                .getServiceMessages();
        addFacesErrorMessage(messages.getErrorMessage(messageName));
    }

    /**
     * Agrega un mensaje de error que este registrado bajo el nombre de
     * <code>messageName</code> y le aplica los argumentos indicados en
     * <code>args</code>
     * 
     * @param messageName
     *            String con el nombre del mensaje a rescatar
     * @param args
     *            array de String con parametros para el mensaje
     */
    public static void addServiceErrorMessage(String messageName, String[] args) {
        ServiceMessages errorMessages = MiEntelProperties
                .getServiceMessages();
        addFacesErrorMessage(errorMessages.getErrorMessage(messageName, args));
    }

    /**
     * Agrega un Mensaje de error espec&iacute;fico de servicio al contexto
     * 
     * @param serviceName
     *            String nombre del servicio
     * @param serviceCode
     *            String c&oacute;digo de error del servicio
     */
    public static void addErrorCodeMessage(String serviceName,
            String serviceCode) {
        ServiceMessages errorMessages = MiEntelProperties
                .getServiceMessages();
        addFacesErrorMessage(errorMessages.getErrorCodeMessage(serviceCode, serviceName));
    }

    /**
     * Agrega un Mensaje de error espec&iacute;fico de servicio al contexto, con los
     * par&aacute;metros indicados
     * 
     * @param serviceName
     *            String nombre del servicio
     * @param serviceCode
     *            String c&oacute;digo de error del servicio
     * @param args
     *            String array con los par&aacute;metros para el mensaje del servicio
     */
    public static void addErrorCodeMessage(String serviceName,
            String serviceCode, String[] args) {
        ServiceMessages errorMessages = MiEntelProperties
                .getServiceMessages();
        addFacesErrorMessage(errorMessages.getErrorCodeMessage(serviceCode, serviceName,
                args));
    }

    /**
     * Agregar un mensaje de INFO al contexto
     * @deprecated A partir de 28-07-2011, reemplazado por {@link #addSuccessMessage(String, String)}
     * @param msg
     */
    public static void addSuccessMessage(String msg) {
        addFacesInfoMessage(msg);
    }

    /**
     * Agrega un mensaje de exito del servicio <code>serviceName</code>
     * identificado con el nombre <code>messageName</code>
     * 
     * @param serviceName
     *            String nombre del servicio
     * @param messageName
     *            String nombre del mensaje
     */
    public static void addSuccessMessage(String serviceName, String messageName) {
        ServiceMessages messages = MiEntelProperties.getServiceMessages();
        addFacesInfoMessage(messages.getSuccessMessage(messageName, serviceName));
    }
    
    /**
     * Agrega un mensaje de exito del servicio <code>serviceName</code>
     * identificado con el nombre <code>messageName</code>
     * 
     * @param serviceName
     *            String nombre del servicio
     * @param messageName
     *            String nombre del mensaje
     * @param args
     *            array con los parametros del mensaje
     */
    public static void addSuccessMessage(String serviceName, String messageName, String[] args) {
        ServiceMessages messages = MiEntelProperties.getServiceMessages();
        addFacesInfoMessage(messages.getSuccessMessage(messageName, serviceName, args));
    }
    
    /**
     * Agrega un mensaje de exito que este registrado bajo el nombre de
     * <code>messageName</code>
     * 
     * @param messageName
     *            String con el nombre del mensaje a rescatar
     */
    public static void addServiceSuccessMessage(String messageName) {
        ServiceMessages errorMessages = MiEntelProperties
                .getServiceMessages();
        addFacesInfoMessage(errorMessages.getSuccessMessage(messageName));
    }

    /**
     * Agrega un mensaje de error que este registrado bajo el nombre de
     * <code>messageName</code> y le aplica los argumentos indicados en
     * <code>args</code>
     * 
     * @param messageName
     *            String con el nombre del mensaje a rescatar
     * @param args
     *            array de String con parametros para el mensaje
     */
    public static void addServiceSuccessMessage(String messageName, String[] args) {
        ServiceMessages errorMessages = MiEntelProperties
                .getServiceMessages();
        addFacesInfoMessage(errorMessages.getSuccessMessage(messageName, args));
    }

    /**
     * Agrega un Mensaje de error espec&iacute;fico de servicio al contexto
     * 
     * @param serviceName
     *            String nombre del servicio
     * @param messageName
     *            String nombre del mensaje
     */
    public static void addServiceSuccessMessage(String serviceName,
            String messageName) {
        ServiceMessages messages = MiEntelProperties
                .getServiceMessages();
        addFacesInfoMessage(messages.getSuccessMessage(messageName, serviceName));
    }

    /**
     * Agrega un Mensaje de exito espec&iacute;fico de servicio al contexto, con los
     * par&aacute;metros indicados
     * 
     * @param serviceName
     *            String nombre del servicio
     * @param messageName
     *            String nombre del mensaje
     * @param args
     *            String array con los par&aacute;metros para el mensaje del servicio
     */
    public static void addServiceSuccessMessage(String serviceName,
            String messageName, String[] args) {
        ServiceMessages messages = MiEntelProperties
                .getServiceMessages();
        addFacesInfoMessage(messages.getSuccessMessage(messageName, serviceName,
                args));
    }
    
    /**
     * Agregar un mensaje de ERROR al contexto 
     * @param msg
     */
    private static void addFacesErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    /**
     * Agregar un mensaje de INFO al contexto
     * @param msg
     */
    private static void addFacesInfoMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

}
