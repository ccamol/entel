/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.exceptions;

import java.io.Serializable;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class CommonResourceException extends Exception implements Serializable {

	private static final long serialVersionUID = -8154466641421983789L;
	
	public CommonResourceException() {
		super();
	}

	public CommonResourceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public CommonResourceException(final String message) {
		super(message);
	
	}

	public CommonResourceException(final Throwable cause) {
		super(cause);
	}

}
