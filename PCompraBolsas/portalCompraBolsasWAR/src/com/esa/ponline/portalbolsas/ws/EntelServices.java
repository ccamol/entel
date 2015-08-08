/* Propiedad de Entel S.A. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.ws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import com.epcs.billing.recarga.BillingRecargaService;
import com.epcs.erp.seguridad.ERPGestionDeSeguridadService;
import com.epcs.provision.suscripcion.bolsaspp.SCOBPortTypeSOAPBindingQSService;
import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;
import com.esa.billing.orderingprod.t.validarplanmmautogestion.BILTValidarPlanMMAutogestionBindingQSService;
import com.esa.ponline.portalbolsas.exceptions.ws.EntelServicesLocatorException;

/**
 * @author ccastro
 * @version 1.0
 */

public class EntelServices {

    private static final Logger LOGGER = Logger.getLogger(EntelServices.class);
    
    public static final String QA_ENVIRONMENT = LoadProperty.getProperty("ambienteQA");
    
    public static final String ENTEL_ENVIRONMENT = LoadProperty.getProperty("ambienteENTEL");

    public static SCOBPortTypeSOAPBindingQSService getScobBolsasPPPSPort() throws EntelServicesLocatorException {
        SCOBPortTypeSOAPBindingQSService service = null;

        LOGGER.info("QA_ENVIRONMENT: " + QA_ENVIRONMENT);
        
        LOGGER.info("Instanciando servicio: " + LoadProperty.getProperty("scobPortTypeSOAPBindingQSServiceEndPoint"));
        try {
        	if(Boolean.parseBoolean(QA_ENVIRONMENT)){
                service =
                        new SCOBPortTypeSOAPBindingQSService(
//                                new URL("http://esbtest.entel.cl/provisionyentrega/administracionsuscripcion/bolsasppmientel/scobbolsasppps"),
//                        		new URL(LoadProperty.getProperty("scobPortTypeSOAPBindingQSServiceEndPoint")),
                        		new URL("file:/Users/ccastro/Documents/Desarrollo/Servers/Oracle/Middleware/user_projects/domains/weblogic/aplsEPCS/planesMMAutogestion/wsdl/scobbolsasppps.xml"),
                                new QName("http://www.epcs.com/Provision/Suscripcion/bolsaspp", "SCOBPortTypeSOAPBindingQSService"));  
        	} 
//        	else {
//                service =
//                        new SCOBPortTypeSOAPBindingQSService(
////                                new URL("http://esbtest.entel.cl/provisionyentrega/administracionsuscripcion/bolsasppmientel/scobbolsasppps"),
////                        		new URL(LoadProperty.getProperty("scobPortTypeSOAPBindingQSServiceEndPoint")),
//                        		new URL("file:/home/ccastro/Oracle/Domains/weblogic/aplsEPCS/planesMMAutogestion/wsdl/scobbolsasppps.xml"),
//                                new QName("http://www.epcs.com/Provision/Suscripcion/bolsaspp", "SCOBPortTypeSOAPBindingQSService"));                
//        	}
        	
        	if(Boolean.parseBoolean(ENTEL_ENVIRONMENT)){
                service =
                        new SCOBPortTypeSOAPBindingQSService(
                                new URL("http://esb.entel.cl/provisionyentrega/administracionsuscripcion/bolsasppmientel/scobbolsasppps"),
                                new QName("http://www.epcs.com/Provision/Suscripcion/bolsaspp", "SCOBPortTypeSOAPBindingQSService"));  
        	}
        } catch (MalformedURLException e) {
            LOGGER.error("No es posible crear la llamada al servicio SCOBPortTypeSOAPBindingQSService: "
                    + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Servicio " + SCOBPortTypeSOAPBindingQSService.class, e);
            try {
                throw new EntelServicesLocatorException(e.getMessage());
            } catch (EntelServicesLocatorException e1) {
                LOGGER.error("No es posible crear la llamada al servicio SCOBPortTypeSOAPBindingQSService: "
                        + e.getMessage());
                e1.printStackTrace();
            }
        }
        return service;
    }

    /**
     * Realiza coneccion al servicio
     * 
     * @return {@link BillingRecargaService} servicio BillingRecarga
     * @throws EntelServicesLocatorException
     * @throws MalformedURLException
     * @see BillingRecargaService
     */
    public static BillingRecargaService getBillingRecargaServicePort() throws EntelServicesLocatorException {
        BillingRecargaService service = null;

        LOGGER.info("QA_ENVIRONMENT: " + QA_ENVIRONMENT);
        
        LOGGER.info("Instanciando servicio: " + LoadProperty.getProperty("billingRecargaServiceEndPoint"));
        try {
        	if(Boolean.parseBoolean(QA_ENVIRONMENT)){
            	service = 
//        		new BillingRecargaService(new URL("http://esb.entel.cl/BILLING/Recarga/Proxies/BillingRecargaService"), 
//            	new BillingRecargaService(new URL(LoadProperty.getProperty("billingRecargaServiceEndPoint")),
            	new BillingRecargaService(new URL("file:/Users/ccastro/Documents/Desarrollo/Servers/Oracle/Middleware/user_projects/domains/weblogic/aplsEPCS/planesMMAutogestion/wsdl/BillingRecargaService.xml"),		
                            new QName("http://www.epcs.com/billing/recarga", "BillingRecargaService"));     		
        	} 
//        	else {
//            	service =
////            	new BillingRecargaService(new URL("http://esbtest.entel.cl/BILLING/Recarga/Proxies/BillingRecargaService"), 
////            	new BillingRecargaService(new URL(LoadProperty.getProperty("billingRecargaServiceEndPoint")),
//            	new BillingRecargaService(new URL("file:/home/ccastro/Oracle/Domains/weblogic/aplsEPCS/planesMMAutogestion/wsdl/BillingRecargaService.xml"),		
//                            new QName("http://www.epcs.com/billing/recarga", "BillingRecargaService"));        		
//        	}
        	
        	if(Boolean.parseBoolean(ENTEL_ENVIRONMENT)){
                service =
                        new BillingRecargaService(
                                new URL("http://esb.entel.cl/BILLING/Recarga/Proxies/BillingRecargaService"),
                                new QName("http://www.epcs.com/billing/recarga", "BillingRecargaService"));     
        	}        	
        } catch (MalformedURLException e) {
            LOGGER.error("No es posible crear la llamada al servicio BillingRecargaService: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Servicio " + BillingRecargaService.class, e);
            try {
                throw new EntelServicesLocatorException(e.getMessage());
            } catch (EntelServicesLocatorException e1) {
                LOGGER.error("No es posible crear la llamada al servicio BillingRecargaService: " + e.getMessage());
                e1.printStackTrace();
            }
        }
        return service;
    }
    
    
    public static ERPGestionDeSeguridadService getERPGestionDeSeguridadService() throws EntelServicesLocatorException {
        ERPGestionDeSeguridadService service = null;
        
        LOGGER.info("QA_ENVIRONMENT: " + QA_ENVIRONMENT);
        
        LOGGER.info("Instanciando servicio: " + LoadProperty.getProperty("erpGestionDeSeguridadServiceEndPoint"));
        try {
        	if(Boolean.parseBoolean(QA_ENVIRONMENT)){
            	service = 
//                		new ERPGestionDeSeguridadService(new URL("http://esbtest.entel.cl/ERP/GestionDeSeguridad/Proxies/ERPSeguridadService"), 
//                		new ERPGestionDeSeguridadService(new URL(LoadProperty.getProperty("erpGestionDeSeguridadServiceEndPoint")),
                		new ERPGestionDeSeguridadService(new URL("file:/Users/ccastro/Documents/Desarrollo/Servers/Oracle/Middleware/user_projects/domains/weblogic/aplsEPCS/planesMMAutogestion/wsdl/ERPSeguridadService.xml"),
                		new QName("http://www.epcs.com/erp/seguridad", "ERPGestionDeSeguridadService"));        		
        	} 
//        	else {
//            	service = 
////                		new ERPGestionDeSeguridadService(new URL("http://esbtest.entel.cl/ERP/GestionDeSeguridad/Proxies/ERPSeguridadService"), 
////                		new ERPGestionDeSeguridadService(new URL(LoadProperty.getProperty("erpGestionDeSeguridadServiceEndPoint")),
//                		new ERPGestionDeSeguridadService(new URL("file:/home/ccastro/Oracle/Domains/weblogic/aplsEPCS/planesMMAutogestion/wsdl/ERPSeguridadService.xml"),
//                		new QName("http://www.epcs.com/erp/seguridad", "ERPGestionDeSeguridadService"));
//        	}
        	
        	if(Boolean.parseBoolean(ENTEL_ENVIRONMENT)){
                service =
                        new ERPGestionDeSeguridadService(
                                new URL("http://esb.entel.cl/ERP/GestionDeSeguridad/Proxies/ERPSeguridadService"),
                        		new QName("http://www.epcs.com/erp/seguridad", "ERPGestionDeSeguridadService"));  
        	}        	
        } catch (MalformedURLException e) {
            LOGGER.error("No es posible crear la llamada al servicio ERPGestionDeSeguridadService: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Servicio " + ERPGestionDeSeguridadService.class, e);
            try {
                throw new EntelServicesLocatorException(e.getMessage());
            } catch (EntelServicesLocatorException e1) {
                LOGGER.error("No es posible crear la llamada al servicio ERPGestionDeSeguridadService: "
                        + e.getMessage());
                e1.printStackTrace();
            }
        }
        return service;
    }
    
    
    /**
     * Realiza coneccion al servicio
     * 
     * @return {@link BILTValidarPlanMMAutogestionBindingQSService} servicio MMAutoGestion
     * @throws EntelServicesLocatorException
     * @throws MalformedURLException
     * @see BILTValidarPlanMMAutogestionBindingQSService
     */
    public static BILTValidarPlanMMAutogestionBindingQSService getMMAutoGestionServicePort() throws EntelServicesLocatorException {
        BILTValidarPlanMMAutogestionBindingQSService service = null;

        LOGGER.info("QA_ENVIRONMENT: " + QA_ENVIRONMENT);
        
        LOGGER.info("Instanciando servicio: " + LoadProperty.getProperty("biltValidarPlanMMAutogestionBindingQSEndpoint"));
        try {
        	if(Boolean.parseBoolean(QA_ENVIRONMENT)){
                service = 
//                    	new BILTValidarPlanMMAutogestionBindingQSService(new URL("http://esbdesa.entel.cl/billing/orderingprod/gestprodmmautogestion/bil_t_px_validarplanmmautogestionps"),
//                    		new BILTValidarPlanMMAutogestionBindingQSService(new URL(LoadProperty.getProperty("biltValidarPlanMMAutogestionBindingQSEndpoint")), 
                    		new BILTValidarPlanMMAutogestionBindingQSService(new URL("file:/Users/ccastro/Documents/Desarrollo/Servers/Oracle/Middleware/user_projects/domains/weblogic/aplsEPCS/planesMMAutogestion/wsdl/bil_t_px_validarplanmmautogestionps.xml"),            		
                                    new QName("http://www.esa.com/Billing/OrderingProd/T/ValidarPlanMMAutogestion", "BIL_T_validarPlanMMAutogestionBindingQSService"));        		
        	} 
//        	else {
//                service = 
////                    	new BILTValidarPlanMMAutogestionBindingQSService(new URL("http://esbdesa.entel.cl/billing/orderingprod/gestprodmmautogestion/bil_t_px_validarplanmmautogestionps"),
////                    		new BILTValidarPlanMMAutogestionBindingQSService(new URL(LoadProperty.getProperty("biltValidarPlanMMAutogestionBindingQSEndpoint")), 
//                    		new BILTValidarPlanMMAutogestionBindingQSService(new URL("file:/home/ccastro/Oracle/Domains/weblogic/aplsEPCS/planesMMAutogestion/wsdl/bil_t_px_validarplanmmautogestionps.xml"),            		
//                                    new QName("http://www.esa.com/Billing/OrderingProd/T/ValidarPlanMMAutogestion", "BIL_T_validarPlanMMAutogestionBindingQSService"));        		
//        		
//        	}

        	if(Boolean.parseBoolean(ENTEL_ENVIRONMENT)){
                service =
                        new BILTValidarPlanMMAutogestionBindingQSService(
                                new URL("http://esb.entel.cl/billing/orderingprod/gestprodmmautogestion/bil_t_px_validarplanmmautogestionps"),
                                new QName("http://www.esa.com/Billing/OrderingProd/T/ValidarPlanMMAutogestion", "BIL_T_validarPlanMMAutogestionBindingQSService"));
        	}
        } catch (MalformedURLException e) {
            LOGGER.error("No es posible crear la llamada al servicio BILTValidarPlanMMAutogestionBindingQSService: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Servicio " + BILTValidarPlanMMAutogestionBindingQSService.class, e);
            try {
                throw new EntelServicesLocatorException(e.getMessage());
            } catch (EntelServicesLocatorException e1) {
                LOGGER.error("No es posible crear la llamada al servicio BILTValidarPlanMMAutogestionBindingQSService: " + e.getMessage());
                e1.printStackTrace();
            }
        }
        return service;
    }
    
//	public static ValidaMsisdnService validaCompania() throws EntelServicesLocatorException {
//		ValidaMsisdnService locator = null;
//		try {
//			/**
//			 * linea que aplica
//			 */
//			// locator = new ValidaMsisdnService(new
//			// URL("http://sbepcs.entel.cl:80/pxValidaMsisdnSubtelSoap"), new
//			// QName(
//			// "http://roble.unix/", "validaMsisdnService"));
//			locator = new ValidaMsisdnService(new URL("http://sbepcs.entel.cl:80/pxValidaMsisdnSubtelSoap"), new QName(
//					"http://roble.unix/", "validaMsisdnService"));
//			// ambiente test (con problemas)
//			// port = new ValidaMsisdnService(new
//			// URL("http://sbepcs3t.entel.cl:80/pxValidaMsisdnSubtelSoap"), new
//			// QName(
//			// "http://roble.unix/", "validaMsisdnService"));
//		} catch (MalformedURLException e) {
//			LOGGER.error("No es posible crear la llamada al servicio ValidaMsisdnService: " + e.getMessage());
//			e.printStackTrace();
//		} catch (Exception e) {
//			LOGGER.error("Error al inicializar el Servicio " + ValidaMsisdnService.class, e);
//			try {
//				throw new EntelServicesLocatorException(e.getMessage());
//			} catch (EntelServicesLocatorException e1) {
//				LOGGER.error("No es posible crear la llamada al servicio ValidaMsisdnService: " + e.getMessage());
//				e1.printStackTrace();
//			}
//		}
//		return locator;
//	}
}
