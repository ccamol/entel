/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.delegate.cache;

import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMSSCC;
import com.esa.ponline.appmobile.ws.dao.factory.FactoryWSDAO;
import com.google.common.cache.CacheLoader;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * 09-09-2014
 */

public class CacheAvailableBundleBAMSSCC extends CacheLoader<String, AvailableBundleBAMSSCC> {
	
	@Override
	public AvailableBundleBAMSSCC load(String msisdn) throws Exception {
	    AvailableBundleBAMSSCC temp = null;

	    temp = FactoryWSDAO.getBolsasDAO().getAllAvailableSSCCBundles(msisdn);
	    
	    if (temp==null) 
		throw new AppMobileException();
	    
	    return temp;
	}

}
