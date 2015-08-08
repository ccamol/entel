package com.esa.ponline.appmobile.bundle;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.esa.ponline.appmobile.exceptions.CommonResourceException;

/**
 * MessageResourceBundle is a concrete subclass of CommonResourceBundle that
 * manages resources for a locale using a set of strings from a file.
 * 
 * @author esuarez@mzzo.com
 * @version 1.0
 */

public class MessageResourceBundle extends CommonResourceBundle {
	private static final Logger LOGGER = Logger.getLogger(MessageResourceBundle.class);
	private static final String CONFIG_PATH = "./aplsEPCS/mientel/config/globalMessages.properties";
	private Properties properties;

	public MessageResourceBundle(String[] baseName) {
		super(baseName);
		buildProperties();
	}

	public MessageResourceBundle(String baseName)
			throws CommonResourceException {
		super(baseName);
		buildProperties();
	}

	public MessageResourceBundle() throws CommonResourceException {
		this("file.resource.messageResourceBundle");
	}

	@SuppressWarnings("unchecked")
	public Enumeration<String> getKeys() {
		Enumeration<?> enumeration = null;
		if (properties != null) {
			enumeration = properties.propertyNames();
		}
		return (Enumeration<String>) enumeration;
	}

	/**
	 * Gets an object for the given key from this resource bundle and null if
	 * this resource bundle does not contain an object for the given key
	 */
	protected Object handleGetObject(String key) {
		if (properties == null)
			return null;
		return properties.getProperty(key);
	}

	private void buildProperties() {

		try {
			InputStream inStreamApp = new FileInputStream(CONFIG_PATH);

			Properties appProperties = new Properties();
			appProperties.load(inStreamApp);

			inStreamApp.close();

			if (properties == null) {
				properties = new Properties();
			} else {
				properties = new Properties(appProperties);
			}

			LOGGER.info("Archivo de mensajes globalMessages cargado correctamente");
		} catch (Exception e) {
			LOGGER.fatal("Error al cargar datos de mensajes", e);
		}
	}

}