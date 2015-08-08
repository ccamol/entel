/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bam.migracionpp.dao;


import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ResourceBundleControl;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.bam.migracionpp.ws.*;


public class MisbamDAO {

    private static final Logger LOGGER = Logger.getLogger(MisbamDAO.class);
    
    String misBAM = "";

    WSBaseClienteServiceLocator wsBaseClienteServiceLocator = new WSBaseClienteServiceLocator();
    
    private static final String PATH = MiEntelProperties
    .getProperty("miEntel.webservices.properties.path");    
    
    /**
     * Obtener plan MIS BAM del movil
     * 
     * @return String
     * @throws DAOException
     * @throws ServiceException
     */    

    public String getMisBAM(String rut, String movil) throws DAOException, ServiceException  {
    	 LOGGER.info("Instanciando el port del WS MIS BAM");
    	 WSBaseCliente port = null;		 
		 
		 try {
			 String name = WSBaseClienteService.class.getCanonicalName();
			 ResourceBundle bundle = ResourceBundle.getBundle(PATH + name,
                     ResourceBundleControl.getControl());
			 
			 URL url = new URL(bundle.getString("wsdl"));
			 
			 port  = (WSBaseCliente) wsBaseClienteServiceLocator.getWSBaseClienteSoapPort(url);
		     //port = (WSBaseCliente) WebServiceLocator.getInstance().getPort(, WSBaseCliente.class);
		     
		 } catch (Exception e) {
		     LOGGER.error("Error al inicializar el Port " + WSBaseCliente.class, e);
		     LOGGER.error( new DAOException(e));
		 }
		 
		 String existeClientePrepagoResponse = "";		 
		 
		 try{
			 existeClientePrepagoResponse = port.existeClientePP(rut, movil).getCodplan();
		 }catch(Exception e){
			 LOGGER.error("Error al obtener Cliente Prepago " + WSBaseCliente.class, e);
		 }
		
		 return existeClientePrepagoResponse;
    }
    
    public Integer getMisBAMSolicitud(String movil) throws DAOException, ServiceException  {
   	 LOGGER.info("Instanciando el port del WS MIS BAM");
   	 WSBaseCliente port = null;		 
		 
		 try {	
			 
			 String name = WSBaseClienteService.class.getCanonicalName();
			 ResourceBundle bundle = ResourceBundle.getBundle(PATH + name,
                     ResourceBundleControl.getControl());
    
			 URL url = new URL(bundle.getString("wsdl"));
			 
			 port  = (WSBaseCliente) wsBaseClienteServiceLocator.getWSBaseClienteSoapPort(url);
		     //port = (WSBaseCliente) WebServiceLocator.getInstance().getPort(, WSBaseCliente.class);
		     
		 } catch (Exception e) {
		     LOGGER.error("Error al inicializar el Port " + WSBaseCliente.class, e);
		     LOGGER.error( new DAOException(e));
		 }
    
		 int existeSolicitudPPResponse = 0;
		 
		 try{
			 existeSolicitudPPResponse = port.existeMigracionPrepago(movil).getExistemigracion();
		 }catch(Exception e){
			 LOGGER.error("Error al obtener Solicitud Migracion " + WSBaseCliente.class, e);
		 }
    
		 return existeSolicitudPPResponse;
   }
}
