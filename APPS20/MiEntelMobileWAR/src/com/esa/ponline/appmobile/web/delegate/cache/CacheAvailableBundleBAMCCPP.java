/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.delegate.cache;

import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMCCPP;
import com.esa.ponline.appmobile.ws.dao.factory.FactoryWSDAO;
import com.google.common.cache.CacheLoader;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * 09-09-2014
 */

public class CacheAvailableBundleBAMCCPP extends CacheLoader<String, AvailableBundleBAMCCPP> {

	@Override
	public AvailableBundleBAMCCPP load(String msisdn) throws Exception {
	    AvailableBundleBAMCCPP temp=null;
	    
	    temp=FactoryWSDAO.getBolsasDAO().getAllAvailablePPBundles(msisdn);
	    
	    if (temp==null) 
		throw new AppMobileException();
	    
	    return temp; 
	}

}
