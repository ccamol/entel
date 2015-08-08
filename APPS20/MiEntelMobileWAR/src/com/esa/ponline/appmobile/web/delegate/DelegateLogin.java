/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.delegate;

import com.esa.ponline.appmobile.ws.dao.factory.FactoryWSDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class DelegateLogin {
		
	public boolean validaLogin(String key) throws Exception{
		return FactoryWSDAO.getLoginDAO().validaLogin(key);
	}

}
