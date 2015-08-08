/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.delegate.cache;

import com.google.common.cache.CacheLoader;

/**
 * @author ccastro(MZZO) en nombre de Absalon Opazo (POnline, EntelSA)
 * Sep 15, 2014
 * version 1.0.0
 */

public class CacheCompraBolsa  extends CacheLoader<String, Boolean> {
    @Override
	public Boolean load(String bolsa) throws Exception {	    
	    return false;		
	}
}
