/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.delegate.cache;


import java.util.ArrayList;

import com.esa.ponline.portalbolsas.bean.DetalleBolsaCliente;
import com.esa.ponline.portalbolsas.exceptions.PortalBolsasException;
import com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.factory.FactoryWSDAO;
import com.google.common.cache.CacheLoader;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * 09-09-2014
 */

public class CacheBolsaDisponibleMM extends CacheLoader<String, ArrayList<DetalleBolsaCliente>> {

	@Override
	public ArrayList<DetalleBolsaCliente> load(String msisdn) throws Exception {
		ArrayList<DetalleBolsaCliente> temp=null;
	    
	    temp = FactoryWSDAO.getBolsasDAO().listarBolsas(msisdn);
	    
	    if (temp==null) 
		throw new PortalBolsasException();
	    
	    return temp; 
	}

}
