/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.delegate.cache;

import java.util.ArrayList;

import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.vo.Bundle.PurchasedPPBundle;
import com.esa.ponline.appmobile.ws.dao.factory.FactoryWSDAO;
import com.google.common.cache.CacheLoader;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA) 
 * version 1.0.0 
 * Sep 10, 2014
 * 
 */

public class CacheBundleBalancePP extends CacheLoader<String, ArrayList<PurchasedPPBundle>> {

	@Override
	public ArrayList<PurchasedPPBundle> load(String msisdn) throws Exception {
	    ArrayList<PurchasedPPBundle> temp = null;
	    
	    temp = FactoryWSDAO.getBolsasDAO().obtenerSaldoBolsasPP(msisdn);
		
	    if (temp==null) 
		throw new AppMobileException();
		    
	    return temp;
	}

}