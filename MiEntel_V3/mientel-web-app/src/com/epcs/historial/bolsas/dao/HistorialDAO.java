package com.epcs.historial.bolsas.dao;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.epcs.bean.TraficoEnLineaPPCCBean;
import com.epcs.billing.gestregistrouso.consultatraficoppycc.Respuestadto;
import com.epcs.billing.gestregistrouso.consultatraficoppycc.Serviciotraficodto;
import com.epcs.billing.gestregistrouso.consultatraficoppycc.Serviciotraficoppcc;
import com.epcs.billing.gestregistrouso.consultatraficoppycc.ServiciotraficoppccService;
import com.epcs.billing.registrouso.BillingRegistroUsoService;
import com.epcs.billing.registrouso.BillingRegistroUsoServicePortType;
import com.epcs.cliente.perfil.dao.CuentaDAO;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.esa.billing.recargas.registrobolsas.*;
import com.esa.billing.recargas.registrobolsas.types.*;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;

public class HistorialDAO {

	private static final Logger LOGGER = Logger.getLogger(HistorialDAO.class);
	/**
     * Devuelve el listado del historial del usuario
     * @param msisdn del Usuario en sesion
     * @throws DAOException
     * @return List<''>
     */
    public List<TraficoEnLineaPPCCBean> getTraficoEnLineaPPCC(String msisdn, String pagina) throws DAOException, ServiceException {

    	List<TraficoEnLineaPPCCBean> listTraficoEnLineaPPCC = new ArrayList<TraficoEnLineaPPCCBean>();
    	
    	
    	for(int i=0; i<110;i++){
    		
    		TraficoEnLineaPPCCBean trafico = new TraficoEnLineaPPCCBean();
    		
    		trafico.setCosto("costo"+(i+1));
    		trafico.setSaldo("saldo"+(i+1));
    		trafico.setDuracion("duracion"+(i+1));
    		listTraficoEnLineaPPCC.add(trafico);
    		
    	}
    	
    	return listTraficoEnLineaPPCC;
    	
    }
    
    public List<BolsaSMSType> getHistorialBolsas(String msisdn, String mercado) throws DAOException, ServiceException {

 
    	CompraBolsaServicesPortType port = null;
    	try {
			port = (CompraBolsaServicesPortType) WebServiceLocator
			.getInstance().getPort(CompraBolsaServices.class,
					CompraBolsaServicesPortType.class);
		} catch (WebServiceLocatorException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
		
		
		ConsultaComprasBolsasType consultaHistoricaBolsas = new ConsultaComprasBolsasType();
		
		//consultaHistoricaBolsas.setMovil("56994997128");   //en duro test
		
		consultaHistoricaBolsas.setMovil(msisdn); 
		
		consultaHistoricaBolsas.setIndicador(mercado);
		
		ResultadoConsultaComprasBolsasType resultado = port.consultaComprasBolsas(consultaHistoricaBolsas);
		           	
		List<BolsaSMSType> listaBolsas = resultado.getBolsaSMS();
		
		if(mercado.endsWith("PPBAM")){
			for (int x=0;x<listaBolsas.size();x++){				
				if (listaBolsas.get(x).getCanalContratacion().equals("WEBPPBAM")){
					listaBolsas.get(x).setCanalContratacion("Portal Mi Entel");
				}
			}
		}		
		
		
		return listaBolsas;
    	
    	
    	
    }
	
	
}
