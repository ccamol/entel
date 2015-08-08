/* Propiedad de EntelPcs. Todos los derechos reservados */
package com.epcs.recursoti.configuracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

/**
 * @author evargas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 */
public class ResourceBundleControl extends Control {

    private static ResourceBundleControl wsr = new ResourceBundleControl();

    private ResourceBundleControl() {

    }

    public static ResourceBundleControl getControl() {
        return wsr;
    }

    public ResourceBundle newBundle(String baseName, Locale locale,
            String format, ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException {
        baseName = !baseName.endsWith(".properties") ? baseName + ".properties"
                : baseName;
        File f = new File(baseName);
        FileInputStream fin = null;
        ResourceBundle rs = null;
        try {
            fin = new FileInputStream(f);
            rs = new CustomResourceBundle(fin);
        } catch (IOException e) {
        } finally {
            fin.close();
        }
        return rs;
    }
}
