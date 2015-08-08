/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Bean para manejar la lista de parametros de Transbank
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ParametrosWebPay implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Map<String, String> parametersMap = new HashMap<String, String>();

    private String original;

    /**
     * @param respuestaTransbank
     */
    public ParametrosWebPay(String respuestaTransbank) {
        this.original = respuestaTransbank;

    }

    /**
     * @return the original String de transbank
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @param original
     *            the original to set
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsParameter(String key) {
        return parametersMap.containsKey(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#get(java.lang.Object)
     */
    public String getParameter(Object key) {
        return parametersMap.get(key);
    }

    /**
     * @return
     * @see java.util.Map#keySet()
     */
    public Set<String> keySet() {
        return parametersMap.keySet();
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public String putParameter(String key, String value) {
        return parametersMap.put(key, value);
    }

    /**
     * @return
     * @see java.util.Map#size()
     */
    public int size() {
        return parametersMap.size();
    }

    /**
     * Retorna una String de parametros con la sintaxis que lo entrega Transbank
     * 
     * @return String con la concatenacion de todos los parametros.
     */
    public String buildParameters() {

        StringBuilder stringBuilder = new StringBuilder();

        Set<String> keySet = parametersMap.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();

            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(key + "=" + parametersMap.get(key));
        }

        return stringBuilder.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.buildParameters();
    }

}
