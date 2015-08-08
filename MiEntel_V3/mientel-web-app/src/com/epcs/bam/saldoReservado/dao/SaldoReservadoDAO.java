/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bam.saldoReservado.dao;


import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ResourceBundleControl;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import cl.tecnova.entel.homebampp.saldoreservado.SaldoCongeladoService.types.*;
import cl.tecnova.entel.homebampp.saldoreservado.SaldoCongeladoService.*;


public class SaldoReservadoDAO {

    private static final Logger LOGGER = Logger.getLogger(SaldoReservadoDAO.class);    
    private static final String PATH = MiEntelProperties.getProperty("miEntel.webservices.properties.path");    
    
    /**
     * Obtener saldo reservadoPP
     * 
     * @return String
     * @throws DAOException
     * @throws ServiceException
     */    

    public SaldoCongeladoResponseDocumentType getSaldoReservado(String msisdn) throws DAOException, ServiceException  {
    	    	
    	LOGGER.info("Consultando saldo reservado: " + msisdn);    	    	 

 		try{
 			String name = "com.epcs.cliente.perfilsaldocongelado.saldocongeladoservice.SaldoCongeladoService.properties";
			ResourceBundle bundle = ResourceBundle.getBundle(PATH + name,ResourceBundleControl.getControl());		
 			String endpointSaldoReservado = bundle.getString("wsdl");
 			
 			SaldoCongeladoServicePortTypeProxy servicio = new SaldoCongeladoServicePortTypeProxy();			
 			servicio.setEndpoint(endpointSaldoReservado);
 			SaldoCongeladoRequestDocumentType consulta = new SaldoCongeladoRequestDocumentType();
 			consulta.setMSISDN(msisdn);
 			return servicio.saldoCongeladoServicePortType(consulta);
 			

 		} catch (Exception e){
 			LOGGER.error("Error al consultar saldo reservado: " + msisdn, e);			
 		}	
  		
 		
 		return null;
		 
		 
		 
    	 
		 
    }    

}
