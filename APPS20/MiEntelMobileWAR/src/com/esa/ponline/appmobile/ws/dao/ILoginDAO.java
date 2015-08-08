/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao;

import com.esa.ponline.appmobile.exceptions.AppMobileException;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public interface ILoginDAO {
	
	public boolean validaLogin(String key) throws AppMobileException;

}
