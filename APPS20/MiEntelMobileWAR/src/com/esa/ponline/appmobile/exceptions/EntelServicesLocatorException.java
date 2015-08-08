/* Propiedad de EntelPcs. Todos los derechos reservados */
package com.esa.ponline.appmobile.exceptions;

import java.io.Serializable;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class EntelServicesLocatorException extends Exception implements Serializable {

	private static final long serialVersionUID = -8809135159893133475L;

	public EntelServicesLocatorException() {
        super();
    }

    public EntelServicesLocatorException(String message) {
        super(message);
    }

    public EntelServicesLocatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntelServicesLocatorException(Throwable cause) {
        super(cause);
    }

}