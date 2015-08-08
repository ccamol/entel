/* Propiedad de Entel S.A. Todos los derechos reservados */
package com.esa.ponline.appmobile.ws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import unix.roble.ValidaMsisdnService;
import cl.i2b.ws22.AutoAtencionService;
import cl.i2b.ws22.BolsasService;
import cl.i2b.ws22.SolicitarContrasenaService;
import cl.i2b.ws22.TraficoService;
import cl.i2b.ws22.WsPlanesMovilService;

import com.epcs.administracion.suscripciones.AdminSuscripcionService_Service;
import com.epcs.billing.balance.BillingBalanceService_Service;
import com.epcs.billing.registrouso.BillingRegistroUsoService;
import com.epcs.cliente.orden.ClienteOrdenService_Service;
import com.epcs.cliente.perfil.ClientePerfilService;
import com.epcs.erp.seguridad.ERPGestionDeSeguridadService;
import com.epcs.provision.suscripcion.bolsasppmovil.SCOBPortTypeSOAPBindingQSService;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.DatosExcedidosService;
import com.esa.billing.billingcons.t.conssaldobonoextra.ConsSaldoBonoExtraServiceBindingQSService;
import com.esa.billing.orderingprod.consultarfamiliamultidispositivo.BILTPXConsultarFamiliaMultiDispositivoService;
import com.esa.billing.orderingprod.t.validarplanmmautogestion.BILTValidarPlanMMAutogestionBindingQSService;
import com.esa.mediation.mediation.n.obtenertraficoindividualag.ObtenerTraficoIndividualAG;
import com.esa.marketsales.salesportal.t.consultarcupodisponiblemdonlinesga.ConsultarCupoDisponible;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.util.Config;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class EntelServices {

	private static final Logger LOGGER = Logger.getLogger(EntelServices.class);
	
    public static ObtenerTraficoIndividualAG getObtenerTraficoIndividualAGService() throws EntelServicesLocatorException {
        ObtenerTraficoIndividualAG locator = null;
        try {
            locator = new ObtenerTraficoIndividualAG(
                    new URL("http://esb.entel.cl:80/mediation/mediation/consultatrafico/med_n_px_obtenertraficoindividualagps"), 
                    new QName("http://www.esa.com/Mediation/Mediation/N/ObtenerTraficoIndividualAG", "ObtenerTraficoIndividualAG"));
        } catch (MalformedURLException e) {
            LOGGER.error("No es posible crear la llamada al servicio ObtenerTraficoIndividualAG: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Servicio " + ObtenerTraficoIndividualAG.class, e);
            try {
                throw new EntelServicesLocatorException(e.getMessage());
            } catch (EntelServicesLocatorException e1) {
                LOGGER.error("No es posible crear la llamada al servicio ObtenerTraficoIndividualAG: " + e.getMessage());
                e1.printStackTrace();
            }
        }

        return locator;
    }	
    
//    public static ConsSaldoBonoExtraServiceBindingQSService getConsSaldoBonoExtraServiceBindingQSService() throws 
//        EntelServicesLocatorException {
//        ConsSaldoBonoExtraServiceBindingQSService locator = null;
//        try {
//            locator = new ConsSaldoBonoExtraServiceBindingQSService(
//                    new URL("http://esb.entel.cl:80/billing/billingcons/consultafacturas/bil_t_px_conssaldobonoextraps"), 
//                    new QName("http://www.esa.com/Billing/BillingCons/T/ConsSaldoBonoExtra", "consSaldoBonoExtraServiceBindingQSService"));
//        } catch (MalformedURLException e) {
//            LOGGER.error("No es posible crear la llamada al servicio ConsSaldoBonoExtraServiceBindingQSService: " + e.getMessage());
//            e.printStackTrace();
//        } catch (Exception e) {
//            LOGGER.error("Error al inicializar el Servicio " + ConsSaldoBonoExtraServiceBindingQSService.class, e);
//            try {
//                throw new EntelServicesLocatorException(e.getMessage());
//            } catch (EntelServicesLocatorException e1) {
//                LOGGER.error("No es posible crear la llamada al servicio ConsSaldoBonoExtraServiceBindingQSService: " + e.getMessage());
//                e1.printStackTrace();
//            }
//        }
//
//        return locator;
//    }

	public static TraficoService getTraficoLocatorInstanceService() throws EntelServicesLocatorException {
		TraficoService locator = null;
		try {
			// locator = new TraficoService(new
			// URL("http://sbepcs.entel.cl:80/EntelPCSMobile/Trafico/px/pxTraficoSOAP"),
			// new QName("http://ws22.i2b.cl/", "TraficoService"));
			locator = new TraficoService(new URL("http://sbepcs.entel.cl:80/EntelPCSMobile/Trafico/px/pxTraficoSOAP"), new QName(
					"http://ws22.i2b.cl/", "TraficoService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio TraficoService: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + TraficoService.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio TraficoService: " + e.getMessage());
				e1.printStackTrace();
			}
		}
		/**
		 * forTest
		 */
		// return null;

		return locator;
	}

	/*
	public static DatosExcedidosService getExcedidosInstanceService() throws EntelServicesLocatorException {
		DatosExcedidosService locator = null;
		try {
			// locator = new DatosExcedidosService(new
			// URL("http://esb.entel.cl:80/provisionyentrega/suscripcion/datosexcedidos/WSDatosExcedidosPS"),
			// new
			// QName("http://www.epcs.com/provisionyentrega/Suscripcion/DatosExcedidos",
			// "DatosExcedidosService"));
			locator = new DatosExcedidosService(new URL("http://esb.entel.cl:80/provisionyentrega/suscripcion/datosexcedidos/WSDatosExcedidosPS"), 
			        new QName("http://www.epcs.com/provisionyentrega/Suscripcion/DatosExcedidos", "DatosExcedidosService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio DatosExcedidosService: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + DatosExcedidosService.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio DatosExcedidosService: " + e.getMessage());
				e1.printStackTrace();
			}
		}
	
		return locator;
	}
		 */
	
	public static DatosExcedidosService getExcedidosInstanceService() throws EntelServicesLocatorException {
		DatosExcedidosService locator = null;
		try {
			// locator = new DatosExcedidosService(new
			// URL("http://esb.entel.cl:80/provisionyentrega/suscripcion/datosexcedidos/WSDatosExcedidosPS"),
			// new
			// QName("http://www.epcs.com/provisionyentrega/Suscripcion/DatosExcedidos",
			// "DatosExcedidosService"));
			locator = new DatosExcedidosService(new URL("http://esb.entel.cl:80/mediation/mediation/consultatrafico/med_n_px_consultarplanesmultimediavistaDExcedidosps"), 
			        new QName("http://www.epcs.com/provisionyentrega/Suscripcion/DatosExcedidos", "DatosExcedidosService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio DatosExcedidosService: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + DatosExcedidosService.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio DatosExcedidosService: " + e.getMessage());
				e1.printStackTrace();
			}
		}

		return locator;
	}

	public static ClientePerfilService getProfileInstanceService() throws EntelServicesLocatorException {
		ClientePerfilService locator = null;
		try {
			// locator = new ClientePerfilService(new
			// URL("http://esb.entel.cl/CLIENTES/GestionDePerfiles/Proxies/ClientePerfilService"),
			// new QName("http://www.epcs.com/cliente/perfil",
			// "ClientePerfilService"));
			locator = new ClientePerfilService(new URL("http://esb.entel.cl/CLIENTES/GestionDePerfiles/Proxies/ClientePerfilService"), new QName(
					"http://www.epcs.com/cliente/perfil", "ClientePerfilService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio ClientePerfilService: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + ClientePerfilService.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio ClientePerfilService: " + e.getMessage());
				e1.printStackTrace();
			}
		}
		/**
		 * forTest
		 */
		// return null;

		return locator;
	}

	public static WsPlanesMovilService getPlanInstanceService() throws EntelServicesLocatorException {
		WsPlanesMovilService locator = null;
		try {
			// locator = new WsPlanesMovilService(new
			// URL("http://sbepcs.entel.cl:80/EntelPCSMobile/Planes/px/pxPlanesSOAP"),
			// new QName("http://ws22.i2b.cl/", "wsPlanesMovilService"));
		    
		    
//			locator = new WsPlanesMovilService(new URL("http://sbepcs.entel.cl:80/EntelPCSMobile/Planes/px/pxPlanesSOAP"),
//					new QName("http://ws22.i2b.cl/", "wsPlanesMovilService"));
		  //TODO OJO, servicio que esta apunto solo a 1 NODO, esto es solo para prueba, favor ocupar osb comentado arriba
          locator = new WsPlanesMovilService(new URL("http://192.168.86.206:7001/EntelPCSMobile/Planes/px/pxPlanesSOAP"),
          new QName("http://ws22.i2b.cl/", "wsPlanesMovilService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio WsPlanesMovilService: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + WsPlanesMovilService.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio WsPlanesMovilService: " + e.getMessage());
				e1.printStackTrace();
			}
		}
		/**
		 * forTest
		 */
		// return null;

		return locator;
	}

	public static SolicitarContrasenaService getPasswordInstanceService() throws EntelServicesLocatorException {
		SolicitarContrasenaService locator = null;
		try {
			// locator = new SolicitarContrasenaService(new
			// URL("http://sbepcs.entel.cl:80/EntelPCSMobile/LoginConRut/px/pxSolicitarContrasenaSOAP"),
			// new QName("http://ws22.i2b.cl/", "SolicitarContrasenaService"));
			locator = new SolicitarContrasenaService(new URL("http://sbepcs.entel.cl:80/EntelPCSMobile/LoginConRut/px/pxSolicitarContrasenaSOAP"),
					new QName("http://ws22.i2b.cl/", "SolicitarContrasenaService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio SolicitarContrasenaService: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + SolicitarContrasenaService.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio SolicitarContrasenaService: " + e.getMessage());
				e1.printStackTrace();
			}
		}
		/**
		 * forTest
		 */
		// return null;

		return locator;
	}

	public static AutoAtencionService getAutoAtencionInstanceService() throws EntelServicesLocatorException {
		AutoAtencionService locator = null;
		try {
			// locator = new AutoAtencionService(new
			// URL("http://sbepcs.entel.cl:80/EntelPCSMobile/AutoAtencion/px/pxAutoAtencionSOAP"),
			// new QName("http://ws22.i2b.cl/", "SolicitarContrasenaService"));
			locator = new AutoAtencionService(new URL("http://sbepcs.entel.cl:80/EntelPCSMobile/AutoAtencion/px/pxAutoAtencionSOAP"), new QName(
					"http://ws22.i2b.cl/", "AutoAtencionService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio AutoAtencionService: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + AutoAtencionService.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio AutoAtencionService: " + e.getMessage());
				e1.printStackTrace();
			}
		}
		/**
		 * forTest
		 */
		// return null;

		return locator;
	}

	// seguridad clave
	public static ERPGestionDeSeguridadService getERPGestionDeSeguridadService() throws EntelServicesLocatorException {
		ERPGestionDeSeguridadService locator = null;
		try {
			// locator = new ERPGestionDeSeguridadService(new
			// URL("http://esb.entel.cl/ERP/GestionDeSeguridad/Proxies/ERPSeguridadService"),
			// new QName("http://www.epcs.com/erp/seguridad",
			// "ERPGestionDeSeguridadService"));
			locator = new ERPGestionDeSeguridadService(new URL("http://esb.entel.cl/ERP/GestionDeSeguridad/Proxies/ERPSeguridadService"), new QName(
					"http://www.epcs.com/erp/seguridad", "ERPGestionDeSeguridadService"));
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
		/**
		 * forTest
		 */
		// return null;

		return locator;
	}

	public static ClienteOrdenService_Service getClienteOrdenService() throws EntelServicesLocatorException {
		ClienteOrdenService_Service locator = null;
		try {
			// locator = new ClienteOrdenService_Service(new
			// URL("http://esb.entel.cl/CLIENTES/Orden/Proxies/ClienteOrdenService"),
			// new QName("http://www.epcs.com/cliente/orden",
			// "ClienteOrdenService"));
			locator = new ClienteOrdenService_Service(new URL("http://esb.entel.cl/CLIENTES/Orden/Proxies/ClienteOrdenService"),
					new QName("http://www.epcs.com/cliente/orden", "ClienteOrdenService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio ClienteOrdenService_Service: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + ClienteOrdenService_Service.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio ClienteOrdenService_Service: "
						+ e.getMessage());
				e1.printStackTrace();
			}
		}
		return locator;
	}

	public static AdminSuscripcionService_Service getBolsasSSCCService() throws EntelServicesLocatorException {
		AdminSuscripcionService_Service locator = null;
		try {
			// locator = new AdminSuscripcionService_Service(new
			// URL("http://esb.entel.cl/PROVISIONAMIENTOYENTREGA/AdminSuscripciones/Proxies/AdminSuscripcionService"),
			// new QName("http://www.epcs.com/administracion/suscripciones",
			// "AdminSuscripcionService"));
			locator = new AdminSuscripcionService_Service(new URL("http://esb.entel.cl/PROVISIONAMIENTOYENTREGA/AdminSuscripciones/Proxies/AdminSuscripcionService"), new QName(
					"http://www.epcs.com/administracion/suscripciones", "AdminSuscripcionService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio AdminSuscripcionService_Service: "
					+ e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + AdminSuscripcionService_Service.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio AdminSuscripcionService_Service: "
						+ e.getMessage());
				e1.printStackTrace();
			}
		}
		return locator;
	}

	public static SCOBPortTypeSOAPBindingQSService getBolsasPPPMovilService() throws EntelServicesLocatorException {
		SCOBPortTypeSOAPBindingQSService locator = null;
		try {
			// locator = new SCOBPortTypeSOAPBindingQSService(new
			// URL("http://esb.entel.cl:80/provisionyentrega/administracionsuscripcion/bolsaspppmovil/scobbolsasppps"),
			// new
			// QName("http://www.epcs.com/Provision/Suscripcion/bolsasppmovil",
			// "SCOBPortTypeSOAPBindingQSService"));
			locator = new SCOBPortTypeSOAPBindingQSService(new URL("http://esb.entel.cl:80/provisionyentrega/administracionsuscripcion/bolsaspppmovil/scobbolsasppps"), new QName(
					"http://www.epcs.com/Provision/Suscripcion/bolsasppmovil", "SCOBPortTypeSOAPBindingQSService"));
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
		return locator;
	}

	public static BolsasService getBolsasService() throws EntelServicesLocatorException {
		BolsasService locator = null;
		try {
			// locator = new BolsasService(new
			// URL("http://sbepcs.entel.cl/EntelPCSMobile/Bolsas/px/pxBolsasSOAP"),
			// new QName("http://ws22.i2b.cl/", "BolsasService"));
			locator = new BolsasService(new URL("http://sbepcs.entel.cl/EntelPCSMobile/Bolsas/px/pxBolsasSOAP"), new QName(
					"http://ws22.i2b.cl/", "BolsasService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio BolsasService: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + BolsasService.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio BolsasService: " + e.getMessage());
				e1.printStackTrace();
			}
		}
		return locator;
	}

	public static ValidaMsisdnService validaCompania() throws EntelServicesLocatorException {
		ValidaMsisdnService locator = null;
		try {
			/**
			 * linea que aplica
			 */
			// locator = new ValidaMsisdnService(new
			// URL("http://sbepcs.entel.cl:80/pxValidaMsisdnSubtelSoap"), new
			// QName(
			// "http://roble.unix/", "validaMsisdnService"));
			locator = new ValidaMsisdnService(new URL("http://sbepcs.entel.cl:80/pxValidaMsisdnSubtelSoap"), new QName(
					"http://roble.unix/", "validaMsisdnService"));
			// ambiente test (con problemas)
			// port = new ValidaMsisdnService(new
			// URL("http://sbepcs3t.entel.cl:80/pxValidaMsisdnSubtelSoap"), new
			// QName(
			// "http://roble.unix/", "validaMsisdnService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio ValidaMsisdnService: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + ValidaMsisdnService.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio ValidaMsisdnService: " + e.getMessage());
				e1.printStackTrace();
			}
		}
		return locator;
	}

	public static BillingBalanceService_Service getFacturacionService() throws EntelServicesLocatorException {
		BillingBalanceService_Service locator = null;
		try {
			// locator = new BillingBalanceService_Service(new URL(
			// "http://esb.entel.cl/BILLING/GestionDeBalances/Proxies/BillingBalanceService"),
			// new QName(
			// "http://www.epcs.com/billing/balance", "BillingBalanceService"));
			locator = new BillingBalanceService_Service(
					new URL("http://esb.entel.cl/BILLING/GestionDeBalances/Proxies/BillingBalanceService"), new QName(
							"http://www.epcs.com/billing/balance", "BillingBalanceService"));
		} catch (MalformedURLException e) {
			LOGGER.error("No es posible crear la llamada al servicio BillingBalanceService_Service: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + BillingBalanceService_Service.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio BillingBalanceService_Service: "
						+ e.getMessage());
				e1.printStackTrace();
			}
		}
		return locator;
	}

	public static BillingRegistroUsoService getTraficoResumenLocatorInstanceService() {
	    //TODO: Servicio a agregar al properties
	    BillingRegistroUsoService locator = null;
	    try {
		locator = new BillingRegistroUsoService(new URL("http://esb.entel.cl:80/BILLING/GestionDeRegistrosDeUso/Proxies/BillingRegistroUsoService"), new QName(
			"http://www.epcs.com/billing/registrouso", "BillingRegistroUsoService"));
		
		
	    } catch (MalformedURLException e) {
		LOGGER.error("No es posible crear la llamada al servicio BillingBalanceService_Service: " + e.getMessage());
		e.printStackTrace();
	    } catch (Exception e) {
		LOGGER.error("Error al inicializar el Servicio " + BillingBalanceService_Service.class, e);
		try {
			throw new EntelServicesLocatorException(e.getMessage());
		} catch (EntelServicesLocatorException e1) {
			LOGGER.error("No es posible crear la llamada al servicio BillingBalanceService_Service: "
					+ e.getMessage());
			e1.printStackTrace();
		}
	    }	
	    return locator;
	}

	public static BILTPXConsultarFamiliaMultiDispositivoService getFamiliaMDService(){
	    //TODO: Servicio a agregar al properties
	    BILTPXConsultarFamiliaMultiDispositivoService locator = null;
	    
	    try {
		locator = new BILTPXConsultarFamiliaMultiDispositivoService(new URL("http://esb.entel.cl/billing/orderingprod/gestproductosmd/bil_t_px_consultarfamiliamultidispositivops?wsdl"), 
			new QName("http://www.esa.com/Billing/OrderingProd/consultarFamiliaMultiDispositivo", "BIL_T_PX_consultarFamiliaMultiDispositivoService"));
		
		
	    } catch (MalformedURLException e) {
		LOGGER.error("No es posible crear la llamada al servicio consultarFamiliaMultiDispositivo: " + e.getMessage());
		e.printStackTrace();
	    } catch (Exception e) {
		LOGGER.error("Error al inicializar el Servicio " + BILTPXConsultarFamiliaMultiDispositivoService.class, e);
		try {
			throw new EntelServicesLocatorException(e.getMessage());
		} catch (EntelServicesLocatorException e1) {
			LOGGER.error("No es posible crear la llamada al servicio consultarFamiliaMultiDispositivo: "
					+ e.getMessage());
			e1.printStackTrace();
		}
	    }	
	    
	    return locator;
	}
	
	public static ConsultarCupoDisponible getCuposDisponiblesMDService(){
	    //TODO: Servicio a agregar al properties
		ConsultarCupoDisponible locator = null;
	    
	    try {
		locator = new ConsultarCupoDisponible(new URL("http://esb.entel.cl:80/marketsales/salesportal/portalventacliente/crm_t_px_consultarcupodisponiblemdonlinesgaps?wsdl"), 
			new QName("http://www.esa.com/MarketSales/SalesPortal/T/ConsultarCupoDisponibleMDOnLineSGA", "consultarCupoDisponible"));

		
	    } catch (MalformedURLException e) {
		LOGGER.error("No es posible crear la llamada al servicio ConsultarCupoDisponible: " + e.getMessage());
		e.printStackTrace();
	    } catch (Exception e) {
		LOGGER.error("Error al inicializar el Servicio " + ConsultarCupoDisponible.class, e);
		try {
			throw new EntelServicesLocatorException(e.getMessage());
		} catch (EntelServicesLocatorException e1) {
			LOGGER.error("No es posible crear la llamada al servicio ConsultarCupoDisponible: "
					+ e.getMessage());
			e1.printStackTrace();
		}
	    }	
	    
	    return locator;
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
        
        LOGGER.info("Instanciando servicio: " + Config.getAppProperty("biltValidarPlanMMAutogestionBindingQSEndpoint"));
//        LOGGER.info("Instanciando servicio: [http://esb.entel.cl/billing/orderingprod/gestprodmmautogestion/bil_t_px_validarplanmmautogestionps]");
        
        try {
            service =
                    new BILTValidarPlanMMAutogestionBindingQSService(
                            new URL("http://esb.entel.cl/billing/orderingprod/gestprodmmautogestion/bil_t_px_validarplanmmautogestionps"),
                            new QName("http://www.esa.com/Billing/OrderingProd/T/ValidarPlanMMAutogestion", "BIL_T_validarPlanMMAutogestionBindingQSService"));
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

}
