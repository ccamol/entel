/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.delegate;

import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

import com.epcs.cliente.orden.types.ResultadoContrataBolsaSSCCType;
import com.esa.ponline.appmobile.exceptions.ServiceException;
import com.esa.ponline.appmobile.exceptions.WSDAOException;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMCCPP;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMSSCC;
import com.esa.ponline.appmobile.vo.Bundle.PurchasedPPBundle;
import com.esa.ponline.appmobile.web.delegate.cache.CacheAvailableBundleBAMCCPP;
import com.esa.ponline.appmobile.web.delegate.cache.CacheAvailableBundleBAMSSCC;
import com.esa.ponline.appmobile.web.delegate.cache.CacheBundleBAMSSCC;
import com.esa.ponline.appmobile.web.delegate.cache.CacheBundleBalancePP;
import com.esa.ponline.appmobile.ws.dao.factory.FactoryWSDAO;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online,
 *         EntelSA) version 1.0.0 date 12-09-2014
 */


public class DelegateBundle {

	//TODO Config properties cache time
	public static LoadingCache<String, AvailableBundleBAMCCPP> cacheAvailableBundleBAMCCPP = CacheBuilder.newBuilder().maximumSize(1000)
		.expireAfterWrite(1, TimeUnit.HOURS).build(new CacheAvailableBundleBAMCCPP());
	
	public static LoadingCache<String, AvailableBundleBAMSSCC> cacheAvailableBundleBAMSSCC = CacheBuilder.newBuilder().maximumSize(1000)
		.expireAfterWrite(1, TimeUnit.HOURS).build(new CacheAvailableBundleBAMSSCC());
	
	public static LoadingCache<String, AvailableBundleBAMSSCC> cacheBundleBAMSSCC = CacheBuilder.newBuilder().maximumSize(1000)
		.expireAfterWrite(1, TimeUnit.HOURS).build(new CacheBundleBAMSSCC());
	
	public static LoadingCache<String, ArrayList<PurchasedPPBundle>> cacheBundleBalancePP = CacheBuilder.newBuilder().maximumSize(1000)
			.expireAfterWrite(5, TimeUnit.MINUTES).build(new CacheBundleBalancePP());
	
	public ArrayList<PurchasedPPBundle> obtenerSaldoBolsasPP(String msisdn) throws Exception, ServiceException, WSDAOException{
		return cacheBundleBalancePP.get(msisdn);
	}
	
	public AvailableBundleBAMCCPP getAllAvailablePPBundles(String msisdn) throws Exception, ServiceException, WSDAOException  {
		return cacheAvailableBundleBAMCCPP.get(msisdn);		
	}
	
	public AvailableBundleBAMSSCC getAllAvailableSSCCBundles(String msisdn) throws Exception, ServiceException, WSDAOException{
    	return cacheAvailableBundleBAMSSCC.get(msisdn);
	}
	
	public AvailableBundleBAMSSCC getSSCCBundles(String msisdn) throws Exception, ServiceException, WSDAOException{
    	return cacheBundleBAMSSCC.get(msisdn);
	}
	
	public AvailableBundleBAMSSCC getSSCCBundlesSinCache(String msisdn) throws Exception, ServiceException, WSDAOException{
		return FactoryWSDAO.getBolsasDAO().getAllAvailableSSCCBundles(msisdn);
	}
	
//    public List<PurchasedPPBundle> consultarBolsasCompradasPPBAM(String msisdn) throws Exception, ServiceException, WSDAOException{
//    	return FactoryWSDAO.getBolsasDAO().consultarBolsasCompradasPPBAM(msisdn);
//    }
    
//	public AvailableBundleBAMSSCC getAllAvailableSSCCBundles(String msisdn) throws Exception, ServiceException, WSDAOException{
//    	return FactoryWSDAO.getBolsasDAO().getAllAvailableSSCCBundles(msisdn);
//	}
	
	public AvailableBundleBAMSSCC consultarBolsasActualesDisponibles(String msisdn) throws Exception, ServiceException, WSDAOException{
		return FactoryWSDAO.getBolsasDAO().getActualBundleAvailableSSCC(msisdn);
	}
	
    public com.epcs.provision.suscripcion.bolsasppmovil.types.ComprarResponseType.Mensaje comprarBolsa(String msisdn, String codBolsa, String tipoCobro) throws WSDAOException, ServiceException{
    	return FactoryWSDAO.getBolsasDAO().comprarBolsa(msisdn, codBolsa, tipoCobro);
    }
    
	public ResultadoContrataBolsaSSCCType contratarBolsaSSCC(String msisdn, String codBolsa, double valorBolsa, String opcionActivacion) throws WSDAOException, ServiceException{
		return FactoryWSDAO.getBolsasDAO().contratarBolsaSSCC(msisdn, codBolsa, valorBolsa, opcionActivacion);
	}
}
