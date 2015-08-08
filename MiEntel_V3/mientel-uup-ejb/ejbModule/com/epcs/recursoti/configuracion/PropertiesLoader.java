/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase utilitaria para leer archivo de propiedades
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class PropertiesLoader {

    /**
     * Metodo utilitario para la lectura de un archivo de propiedades como
     * recurso.<br>
     * Este metodo utiliza el ClassLoader de la clase indicada en
     * <code>clazz</code>
     * 
     * @param clazz
     * @param propertyFile
     * @return {@link Properties}
     * @throws Exception
     */
    public static Properties loadProperties(Class<?> clazz, String propertyFile)
            throws Exception {

        try {
            InputStream is = clazz.getClassLoader().getResourceAsStream(
                    propertyFile);
            if (is == null) {
                throw new Exception("'" + propertyFile + "' no fue encontrado");
            }

            Properties props = new Properties();
            props.load(is);

            return props;

        } catch (IOException e) {
            throw new Exception(propertyFile + " no puede ser leido", e);
        } catch (IllegalStateException e) {
            throw new Exception(propertyFile + " esta corrupto", e);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

}
