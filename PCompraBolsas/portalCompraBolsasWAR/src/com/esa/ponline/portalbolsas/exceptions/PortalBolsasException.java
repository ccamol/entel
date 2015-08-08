package com.esa.ponline.portalbolsas.exceptions;

import java.io.Serializable;

/**
 * @author ccastro
 *
 */

public class PortalBolsasException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public PortalBolsasException() {
        super();
    }

    public PortalBolsasException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PortalBolsasException(final String message) {
        super(message);
    
    }

    public PortalBolsasException(final Throwable cause) {
        super(cause);
    }

}

