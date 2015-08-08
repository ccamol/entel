/* Propiedad de Entel S.A. Todos los derechos reservados */
package com.esa.ponline.appmobile.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import com.esa.ponline.appmobile.web.delegate.cache.CacheRestriccionZona;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Config implements Serializable {
	
	private static final Logger LOGGER = Logger.getLogger(Config.class);

	private static final long serialVersionUID = 1L;
    
	private static final String CONFIG_PATH = "./aplsEPCS/mientel/config/app-mientel.properties";
	
	private static final String CONFIG_PATH_II = "./aplsEPCS/mientel/config/moviles-mientel.properties";
	
	private static Map<String, String> configApp;
	
	private static Map<String, String> configMoviles;

	public static LoadingCache<String, Boolean> cacheRestriccionZona;
	
	static {
		try {
			InputStream inStreamApp = new FileInputStream(CONFIG_PATH);
			
			Properties appProperties = new Properties();
			appProperties.load(inStreamApp);
			
			inStreamApp.close();

			configApp = new HashMap(appProperties);
			
			LOGGER.info("Archivo de configuracion app-properties cargado correctamente");
		} catch (Exception e) {
			LOGGER.fatal("Error al cargar datos de configuracion",e);
		}
	}
	
	static {
		try {
			InputStream inStreamMoviles = new FileInputStream(CONFIG_PATH_II);
    
			Properties movilProperties = new Properties();
			movilProperties.load(inStreamMoviles);
			
			inStreamMoviles.close();

			configMoviles = new HashMap(movilProperties);
			
			LOGGER.info("Archivo de configuracion moviles-properties cargado correctamente");
		} catch (Exception e) {
			LOGGER.fatal("Error al cargar datos de configuracion",e);
		}
		
		cacheRestriccionZona = CacheBuilder.newBuilder().
			maximumSize(100).expireAfterWrite(1, TimeUnit.MINUTES).build(new CacheRestriccionZona());
	}
	
	public static String getAppProperty(String key) {
		String result = configApp.get(key);
		
		if (result==null){
			LOGGER.error("Key:"+key+" no encontrada");
	}

		return result;
	}
	
	public static String getMovilProperty(String key) {
		String result = configMoviles.get(key);
		
		if (result==null){
			LOGGER.error("Key:"+key+" no encontrada");
		}
		
		return result;
	}
	
	public Config(){
	}
}
