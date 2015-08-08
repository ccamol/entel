package com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.delegate;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.epcs.provision.suscripcion.bolsaspp.types.ComprarResponseType;
import com.esa.ponline.portalbolsas.bean.BolsaComprada;
import com.esa.ponline.portalbolsas.bean.BolsaDisponibleMM;
import com.esa.ponline.portalbolsas.bean.DetalleBolsaCliente;
import com.esa.ponline.portalbolsas.bean.Movil;
import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;
import com.esa.ponline.portalbolsas.exceptions.ws.ServiceException;
import com.esa.ponline.portalbolsas.exceptions.ws.WSDAOException;
import com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.delegate.cache.CacheBolsaDisponibleMM;
import com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.factory.FactoryWSDAO;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

/**
 * @author ccastro
 *
 */

public class DelegateBolsa {
	
	
	public static LoadingCache<String, ArrayList<DetalleBolsaCliente>> cacheBolsaDisponibleMM = 
		CacheBuilder.newBuilder().
			maximumSize(1000).expireAfterWrite(
					Long.valueOf(LoadProperty.getProperty("cacheBolsaDisponibleMM")), TimeUnit.SECONDS).build(new CacheBolsaDisponibleMM());

	//TODO Mejorar error cache para canal WEBSGOPP
//	public ArrayList<DetalleBolsaCliente> listarBolsas(String msisdn) throws Exception, ServiceException, WSDAOException  {
//		return cacheBolsaDisponibleMM.get(msisdn);		
//	}

    public BolsaDisponibleMM getBolsaDisponibleInfoMM(String msisdn) throws Exception, ServiceException, WSDAOException  {
        return FactoryWSDAO.getBolsasDAO().getBolsaSeleccionDetalleMM(msisdn);     
    }
    
    public ComprarResponseType.Mensaje comprarBolsa(String msisdn, String codigo, String cobro) throws Exception{
        return FactoryWSDAO.getBolsasDAO().comprarBolsa(msisdn, codigo, cobro);
    }
    
    public ArrayList<BolsaComprada> listarBolsasActivas(String msisdn) throws Exception{
        return FactoryWSDAO.getBolsasDAO().listarBolsasActivas(msisdn);
    }
    
    public ArrayList<DetalleBolsaCliente> listarBolsas(String msisdn) throws Exception{
        return FactoryWSDAO.getBolsasDAO().listarBolsas(msisdn);
    }

    public Movil desplegarInfoCliente(String msisdn) throws Exception{
        return FactoryWSDAO.getBolsasDAO().desplegarInfoCliente(msisdn); 
    }
    
}
