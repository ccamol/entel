/* Propiedad de Entel S.A. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.configuration.properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * ServiceLocator para Servicios de PCB
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
public class LoadProperty implements Serializable {
	
	private static final Logger LOGGER = Logger.getLogger(LoadProperty.class);

	private static final long serialVersionUID = 1L;
    
	private static final String CONFIG_PATH = "./aplsEPCS/planesMMAutogestion/config/portalBolsas.properties";
	
//	private static final String CONFIG_PATH_II = "./aplsEPCS/planesMMAutogestion/config/xxx.properties";
	
	private static Map<String, String> configApp;
	
//	private static Map<String, String> configMoviles;

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
	
//	static {
//		try {
//			InputStream inStreamMoviles = new FileInputStream(CONFIG_PATH_II);
//    
//			Properties movilProperties = new Properties();
//			movilProperties.load(inStreamMoviles);
//			
//			inStreamMoviles.close();
//
//			configMoviles = new HashMap(movilProperties);
//			
//			LOGGER.info("Archivo de configuracion moviles-properties cargado correctamente");
//		} catch (Exception e) {
//			LOGGER.fatal("Error al cargar datos de configuracion",e);
//		}
//	}
	
	public static String getProperty(String key) {
		String result = configApp.get(key);
		
		if (result==null){
			LOGGER.error("Key:"+key+" no encontrada");
	}

		return result;
	}
	
//	public static String getMovilProperty(String key) {
//		String result = configMoviles.get(key);
//		
//		if (result==null){
//			LOGGER.error("Key:"+key+" no encontrada");
//		}
//		
//		return result;
//	}
	

	
	public LoadProperty(){
	}

}
