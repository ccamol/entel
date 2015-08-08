/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.listener;

import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * ServletContextListener de la aplicacion MiEntel.<br>
 * Inicializa el log de la aplicacion.
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 */
public class MiEntelServletContextListener implements ServletContextListener {

    private final static Logger LOGGER = Logger
            .getLogger(MiEntelServletContextListener.class);

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        
        loadLog4J(servletContextEvent);
        loadResources(servletContextEvent);        

        parametersLookUp(servletContextEvent);
        
        LOGGER.info("*** MiEntel STARTED ***");
    }
    
    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        LOGGER.info("*** MiEntel ENDED ***");
    }
    
    /**
     * Realiza la carga de propiedades de configuracion de la aplicacion.<br>
     * Depende del init-parameter <code>miEntel.resources.baseName</code>
     * definido en web.xml, el cual debe indiar la ruta de un archivo
     * .properties que debe estar localizado en WEB-INF/classes.<br>
     * Este archivo debe contener el key <code>miEntel.properties.path</code>
     * con el path absoluto del archivo de propiedades de MiEntel.<br>
     * Esta configuracion atiende el requerimiento de Entel de tener los
     * archivos de configuracion fuera de la estructura de directorios de la
     * aplicacion.
     * 
     * @param servletContextEvent
     */
    private void loadResources(ServletContextEvent servletContextEvent) {
        
        String resourcesBaseName = servletContextEvent.getServletContext()
                .getInitParameter("miEntel.resources.baseName");
        String miEntelFile = null;
        
        if(resourcesBaseName == null) {
            
            LOGGER.error("*** No se encontro context-param para " +
            		"'miEntel.resources.baseName'");
            LOGGER.error("*** Parametros de configuracion de MiEntel " +
            		"no pudieron ser cargados");
        }
        else {
            try {
                ResourceBundle bundle = ResourceBundle.getBundle(resourcesBaseName);   
                miEntelFile = bundle.getString("miEntel.properties.path");
                MiEntelProperties.load(miEntelFile);
                LOGGER.info("*** MiEntelProperties cargado desde '" 
                        + miEntelFile + "'");                
            } catch (Exception e) {
                LOGGER.error("*** MiEntelProperties no pudo ser cargado " +
                		"con los valores de '" + miEntelFile + "'", e);
            }
        }
    }

    /**
     * inicializa el LOGGER de la aplicacion.<br>
     * Depende del init-parameter <code>log4j.propertiesFile</code> definido en
     * web.xml, el cual debe indiar la ruta de un archivo .properties que debe
     * estar localizado en WEB-INF/classes.<br>
     * Este archivo debe contener el key <code>mientel.logPath.properties</code>
     * con el path absoluto del archivo de log4j de MiEntel.<br>
     * Esta configuracion atiende el requerimiento de Entel de tener los
     * archivos de configuracion fuera de la estructura de directorios de la
     * aplicacion.
     * 
     * @param servletContextEvent
     */
    private void loadLog4J(ServletContextEvent servletContextEvent) {
        /*
         * Log4J initialization
         */
        String log4jPropertiesFile = servletContextEvent.getServletContext()
                .getInitParameter("log4j.propertiesFile");
        String log4jFile = null;

        try {
            ResourceBundle bundle = ResourceBundle
                    .getBundle(log4jPropertiesFile);
            
            log4jFile = bundle.getString("mientel.logPath.properties");
            
            PropertyConfigurator.configure(log4jFile);
            LOGGER.info("*** Log4j inicializado desde archivo " + log4jFile);
            
        } catch (Exception e) {
            LOGGER.error("*** Log4j no fue inicializado desde archivo "
                    + log4jFile, e);
        }
    }
    
    /**
     * @param servletContextEvent
     */
    private void parametersLookUp(ServletContextEvent servletContextEvent) {
        
        LOGGER.info("*** MiEntel V3 Parameters:");
        LOGGER.info("*** Configuration :");
        lookUpParameter("miEntel.desktop.url");
        lookUpParameter("miEntel.logout.url");
        lookUpParameter("mientel.default.streaming.url");
        lookUpParameter("miEntel.webservices.properties.path");
        lookUpParameter("miEntel.errorMessages.path");
        lookUpParameter("miEntel.externalApps.path");
        LOGGER.info("");
        LOGGER.info("*** Parameters :");
        lookUpParameter("producto.voz");
        lookUpParameter("producto.voz.tipoPlan");
        lookUpParameter("parametros.sexo");
        lookUpParameter("parametros.hijo");
        lookUpParameter("parametros.estadoCivil");
        lookUpParameter("parametros.actividad");
        lookUpParameter("parametros.areaLaboral");
        lookUpParameter("parametros.relacionTitular");
        lookUpParameter("parametros.tipoRecarga");
        lookUpParameter("parametros.multitienda");
        LOGGER.info("");
    }

    private void lookUpParameter(final String parameterKey) {

        try {
            final String parameterValue = MiEntelProperties.getProperty(parameterKey);
            LOGGER.info("\t " + parameterKey + " : " + parameterValue);
        } catch (Exception e) {
            LOGGER.error("Parametro '" + parameterKey + "' no fue obtenido", e);
        }

    }
    
}
