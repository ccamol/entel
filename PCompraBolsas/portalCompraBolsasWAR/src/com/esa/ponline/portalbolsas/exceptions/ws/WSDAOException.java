/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.exceptions.ws;

import java.io.Serializable;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class WSDAOException extends Exception implements Serializable {

    private static final long serialVersionUID = 8085229508480152421L;

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