/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.appmobile.exceptions;

import java.io.Serializable;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class WSDAOException extends Exception implements Serializable {

	private static final long serialVersionUID = 3603982441940698503L;

	public WSDAOException() {
        super();
    }

    public WSDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public WSDAOException(String message) {
        super(message);
    }

    public WSDAOException(Throwable cause) {
        super(cause);
    }

}
