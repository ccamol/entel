/* Propiedad de EntelPcs. Todos los derechos reservados */
package com.epcs.recursoti.configuracion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author evargas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public  class CustomResourceBundle extends ResourceBundle {

    private Properties props;

    CustomResourceBundle(InputStream stream) throws IOException {
        props = new Properties();
        props.load(stream);
    }

    protected Object handleGetObject(String key) {
        return props.getProperty(key);
    }

    public Enumeration<String> getKeys() {
        Set<String> handleKeys = props.stringPropertyNames();
        return Collections.enumeration(handleKeys);
    }

}