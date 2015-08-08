/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao.imp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import cl.i2b.ws22.Bolsa;
import cl.i2b.ws22.Bolsas;

import com.epcs.administracion.suscripciones.AdminSuscripcionService;
import com.epcs.administracion.suscripciones.types.BolsaActualType;
import com.epcs.administracion.suscripciones.types.BolsaType;
import com.epcs.administracion.suscripciones.types.ConsultarBolsasActualYDisponibleFullSSCCType;
import com.epcs.administracion.suscripciones.types.ResultadoConsultarBolsasActualYDisponibleFullSSCCType;
import com.epcs.cliente.orden.ClienteOrdenService;
import com.epcs.cliente.orden.types.ContrataBolsaSSCCType;
import com.epcs.cliente.orden.types.ResultadoContrataBolsaSSCCType;
import com.epcs.provision.suscripcion.bolsasppmovil.SCOBPortType;
import com.epcs.provision.suscripcion.bolsasppmovil.types.ListarBolsasActivasRequestType.Mensaje;
import com.epcs.provision.suscripcion.bolsasppmovil.types.ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas.DetalleBono;
import com.epcs.provision.suscripcion.bolsasppmovil.types.ListarBolsasResponseType;
import com.epcs.provision.suscripcion.bolsasppmovil.types.RequestType;
import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.exceptions.ServiceException;
import com.esa.ponline.appmobile.exceptions.WSDAOException;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.util.DateUtils;
import com.esa.ponline.appmobile.util.Formato;
import com.esa.ponline.appmobile.util.TimeWatch;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMCCPP;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMSSCC;
import com.esa.ponline.appmobile.vo.Bundle.BundleCCPPVO;
import com.esa.ponline.appmobile.vo.Bundle.BundleSSCCVO;
import com.esa.ponline.appmobile.vo.Bundle.BundleDetailBalance;
import com.esa.ponline.appmobile.vo.Bundle.PurchasedPPBundle;
import com.esa.ponline.appmobile.ws.EntelServices;
import com.esa.ponline.appmobile.ws.dao.IBundleDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online,
 *         EntelSA) version 1.0.0 date 04-09-2014
 */

public class BundleImp implements IBundleDAO {

	private static final Logger LOGGER = Logger.getLogger(BundleImp.class);
	
	public static final String CANAL = Config.getAppProperty("canal");

	@Override
	public ResultadoContrataBolsaSSCCType contratarBolsaSSCC(String msisdn,
			String codBolsa, double valorBolsa, String opcionActivacion)
			throws WSDAOException, ServiceException {

	    	TimeWatch watch = TimeWatch.start();
		ClienteOrdenService port = null;

		LOGGER.info("Instanciando el port");
		try {
			port = EntelServices.getClienteOrdenService()
					.getClienteOrdenServicePort();
		} catch (EntelServicesLocatorException e) {
			LOGGER.error("Error al inicializar el Port "
					+ ClienteOrdenService.class, e);
			throw new WSDAOException(e);
		}

		LOGGER.info("Configurando Datos de la peticion");
		ContrataBolsaSSCCType request = new ContrataBolsaSSCCType();
		ResultadoContrataBolsaSSCCType response;

		LOGGER.info("Invocando servicio");
		try {

			request.setMsisdn(msisdn);
			request.setCodBolsa(codBolsa);
			request.setOpcionActivacionBolsa(opcionActivacion);
			request.setValorBolsa(String.valueOf(valorBolsa));

			response = port.contrataBolsaSSCC(request);
			LOGGER.info("Tiempo:" + watch.time());
		} catch (Exception e) {
		    	LOGGER.error("Entrada: Msisdn ->"+request.getMsisdn()+
		    	"<-  Codigo Bolsa ->" + request.getCodBolsa() +
		    	"<-  Opcion activacion cbolsa ->" +request.getOpcionActivacionBolsa()+
		    	"<-  Valor Bolsa ->" +request.getValorBolsa()+"<-");
		    	
			LOGGER.error("Error en el metodo contrataBolsaSSCC", e);
			LOGGER.error("Tiempo:" + watch.time());
			throw new WSDAOException(e);
		}

		String codigoRespuesta = response.getRespuesta().getCodigo();
		String descripcionRespuesta = response.getRespuesta().getDescripcion();

		LOGGER.info("codigoRespuesta  " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

		// TODO Config properties
		if (codigoRespuesta.equalsIgnoreCase("0000")) {
			// resultadoContratarBolsaBean = new ResultadoContratarBolsaBean();
			// resultadoContratarBolsaBean.setCantidad(response.getCantidad());
			// resultadoContratarBolsaBean.setTipoActivacion(response.getTipoActivacion());
		} else {
		    	LOGGER.error("Entrada: Msisdn ->"+request.getMsisdn()+
			    	"<-  Codigo Bolsa ->" + request.getCodBolsa() +
			    	"<-  Opcion activacion cbolsa ->" +request.getOpcionActivacionBolsa()+
			    	"<-  Valor Bolsa ->" +request.getValorBolsa()+"<-");
		    	
			LOGGER.error("Error contrataBolsaSSCC devuelve: " + codigoRespuesta
					+ " - " + descripcionRespuesta);
			throw new ServiceException(codigoRespuesta, descripcionRespuesta);
		}

		return response;
	}

	@Override
	public AvailableBundleBAMSSCC getBundleAvailableSSCC(String msisdn)
			throws WSDAOException, ServiceException {
	    
	    	TimeWatch watch = TimeWatch.start();
		AvailableBundleBAMSSCC bolsasActualesDisponiblesBean = new AvailableBundleBAMSSCC();
		List<BundleSSCCVO> listBolsasActualesBean = new ArrayList<BundleSSCCVO>();
		List<BundleSSCCVO> listBolsasDisponiblesBean = new ArrayList<BundleSSCCVO>();
		List<BundleSSCCVO> bolsas = new ArrayList<BundleSSCCVO>();

		Bolsas port = null;

		LOGGER.info("Instanciando el port");
		try {
			port = EntelServices.getBolsasService().getBolsasPort();
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			error.printStackTrace();
		}

		LOGGER.info("Configurando Datos de la peticion");
		// ConsultarBolsasActualYDisponibleFullSSCCType request = new
		// ConsultarBolsasActualYDisponibleFullSSCCType();
		// request.setMsisdn(msisdn);

		List<Bolsa> response;

		// ConsultarBolsasContratadasDisponiblesSSCC bcd = new
		// ConsultarBolsasContratadasDisponiblesSSCC();
		// // TODO Config properties
		// bcd.setCanal("9");
		// bcd.setMsisdn(msisdn);

		LOGGER.info("Invocando servicio");
		try {
			// response =
			// port.consultarBolsasActualYDisponibleFullSSCC(request);
			response = port.consultarBolsasContratadasDisponiblesSSCC(msisdn,
					"9");
			LOGGER.info("Tiempo:" + watch.time());
		} catch (Exception e) {
		    	LOGGER.error("Entrada: Msisdn ->"+msisdn+"<-  Canal ->9<-");
			LOGGER.error("Exception en servicio consultarBolsasContratadasDisponiblesSSCC", e);
			LOGGER.error("Tiempo:" + watch.time());
			throw new WSDAOException(e);
		}

		// Construir Lista de Bolsas Actuales
		for (Bolsa bolsa : response) {
			if (bolsa.getTipoConsulta().equalsIgnoreCase("bolsas actual")) {
				listBolsasActualesBean.add(buildBolsa(bolsa));
			}
		}

		// Construir Lista de Bolsas Disponibles
		for (Bolsa bolsa : response) {
			if (bolsa.getTipoConsulta().equalsIgnoreCase("bolsas disponibles")) {
				listBolsasDisponiblesBean.add(buildBolsa(bolsa));
			}
		}

		// Construir Lista de Bolsas
		for (Bolsa bolsa : response) {
			bolsas.add(buildBolsa(bolsa));
		}

		// String codigoRespuesta = response.getRespuesta().getCodigo();
		// String descripcionRespuesta =
		// response.getRespuesta().getDescripcion();
		//
		// LOGGER.info("codigoRespuesta  " + codigoRespuesta);
		// LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
		//
		// // if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
		// if (codigoRespuesta.equalsIgnoreCase("0000")) {
		//
		// List<BolsaActualType> listBolsasActuales =
		// response.getBolsasActuales().getItemBolsaActual();
		// List<BolsaType> listBolsasDisponibles =
		// response.getBolsasDisponibles().getItemBolsa();
		//
		// // Construir Lista de Bolsas Actuales
		// for (BolsaActualType bolsaType : listBolsasActuales) {
		// listBolsasActualesBean.add(buildBolsaActual(bolsaType));
		// }
		//
		// // Construir Lista de Bolsas Disponibles
		// for (BolsaType bolsaType : listBolsasDisponibles) {
		// listBolsasDisponiblesBean.add(buildBolsa(bolsaType));
		// }
		//
		// bolsasActualesDisponiblesBean.setBolsasActuales(listBolsasActualesBean);
		// bolsasActualesDisponiblesBean.setBolsasDisponibles(listBolsasDisponiblesBean);
		//
		// } else {
		//
		// LOGGER.error("Service error code received: " + codigoRespuesta +
		// " - " + descripcionRespuesta);
		// throw new ServiceException(codigoRespuesta, descripcionRespuesta);
		//
		// }
		bolsasActualesDisponiblesBean.setBolsasActuales(listBolsasActualesBean);
		bolsasActualesDisponiblesBean
				.setBolsasDisponibles(listBolsasDisponiblesBean);
		bolsasActualesDisponiblesBean.setBolsas(bolsas);

		return bolsasActualesDisponiblesBean;
	}

	private BundleSSCCVO buildBolsa(Bolsa bolsa) {
		BundleSSCCVO bolsaBean = new BundleSSCCVO();

		bolsaBean.setCosto(Config.getAppProperty("formatPrecio")
				+ Formato.formatPuntos(bolsa.getPrecio(), Locale.GERMAN));

		bolsaBean.setNombreBolsa(bolsa.getDescripcion());
		bolsaBean.setSnCodigo(bolsa.getIdBolsa());
		bolsaBean.setTipoBolsa(bolsa.getTipoBolsa());
		bolsaBean.setTipoConsulta(bolsa.getTipoConsulta());
		bolsaBean.setSpCodigo(bolsa.getCodigo());

		return bolsaBean;
	}

	@Override
	public AvailableBundleBAMSSCC getActualBundleAvailableSSCC(String msisdn)
			throws WSDAOException, ServiceException {
	    	TimeWatch watch = TimeWatch.start();
		AvailableBundleBAMSSCC bolsasActualesDisponiblesBean = new AvailableBundleBAMSSCC();
		List<BundleSSCCVO> listBolsasActualesBean = new ArrayList<BundleSSCCVO>();
		List<BundleSSCCVO> listBolsasDisponiblesBean = new ArrayList<BundleSSCCVO>();

		AdminSuscripcionService port = null;

		LOGGER.info("Instanciando el port");
		try {
			port = EntelServices.getBolsasSSCCService()
					.getAdminSuscripcionServicePort();
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			error.printStackTrace();
		}

		LOGGER.info("Configurando Datos de la peticion");
		ConsultarBolsasActualYDisponibleFullSSCCType request = new ConsultarBolsasActualYDisponibleFullSSCCType();
		request.setMsisdn(msisdn);

		ResultadoConsultarBolsasActualYDisponibleFullSSCCType response;

		LOGGER.info("Invocando servicio");
		try {
			response = port.consultarBolsasActualYDisponibleFullSSCC(request);
			LOGGER.info("Tiempo:" + watch.time());
		} catch (Exception e) {
		    	LOGGER.error("Entrada: Msisdn ->"+request.getMsisdn()+"<-");
			LOGGER.error("Exception caught on Service invocation consultarBolsasActualYDisponibleFullSSCC", e);
			LOGGER.error("Tiempo:" + watch.time());
			throw new WSDAOException(e);
		}

		String codigoRespuesta = response.getRespuesta().getCodigo();
		String descripcionRespuesta = response.getRespuesta().getDescripcion();

		LOGGER.info("codigoRespuesta  " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

		// if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
		if (codigoRespuesta.equalsIgnoreCase("0000")) {

			List<BolsaActualType> listBolsasActuales = response
					.getBolsasActuales().getItemBolsaActual();
			List<BolsaType> listBolsasDisponibles = response
					.getBolsasDisponibles().getItemBolsa();

			// Construir Lista de Bolsas Actuales
			for (BolsaActualType bolsaType : listBolsasActuales) {
				listBolsasActualesBean.add(buildBolsaActual(bolsaType));
			}

			// Construir Lista de Bolsas Disponibles
			for (BolsaType bolsaType : listBolsasDisponibles) {
				listBolsasDisponiblesBean.add(buildBolsa(bolsaType));
			}

			bolsasActualesDisponiblesBean
					.setBolsasActuales(listBolsasActualesBean);
			bolsasActualesDisponiblesBean
					.setBolsasDisponibles(listBolsasDisponiblesBean);

		} else {
		    	LOGGER.error("Entrada: Msisdn ->"+request.getMsisdn()+"<-");
			LOGGER.error("consultarBolsasActualYDisponibleFullSSCC Service error code received: " + codigoRespuesta
					+ " - " + descripcionRespuesta);
			throw new ServiceException(codigoRespuesta, descripcionRespuesta);

		}

		return bolsasActualesDisponiblesBean;
	}

	// TODO Config properties
	public com.epcs.provision.suscripcion.bolsasppmovil.types.ComprarResponseType.Mensaje comprarBolsa(
			String msisdn, String codBolsa, String tipoCobro)
			throws WSDAOException, ServiceException {
	    	TimeWatch watch = TimeWatch.start();
		SCOBPortType port = null;
		LOGGER.info("Instanciando el port " + SCOBPortType.class);
		try {
			port = EntelServices.getBolsasPPPMovilService()
					.getSCOBPortTypeSOAPBindingQSPort();

			// ComprarRequestType.Mensaje request = new
			// ComprarRequestType.Mensaje();
			com.epcs.provision.suscripcion.bolsasppmovil.types.ComprarResponseType.Mensaje response = null;

			com.epcs.provision.suscripcion.bolsasppmovil.types.ComprarRequestType.Mensaje request = new com.epcs.provision.suscripcion.bolsasppmovil.types.ComprarRequestType.Mensaje();

			try {
				LOGGER.info("Configurando Datos de la peticion");
				request.setMsisdn(Formato.validaMsisdn(msisdn));
				request.setCanal(CANAL);
				request.setCodigo(codBolsa);
				request.setPlataforma("");
				request.setUsuario("");
				request.setCobro(tipoCobro);
				LOGGER.info("Invocando servicio");
				response = port.comprarRequestDocument(request);
				LOGGER.info("Tiempo:" + watch.time());
			} catch (Exception e) {
			    	LOGGER.error("Entrada: Msisdn ->"+request.getMsisdn()+"<- Canal ->"+
			    		request.getCanal()+"<- Codigo ->"+request.getCodigo()+"<- Plataforma ->"+request.getPlataforma()+
			    		"<- Usuario ->" + request.getUsuario()+"<- Cobroo ->"+request.getCobro()+"<-");
				LOGGER.error("Exception caught on Service invocation: "
						+ "comprarRequestDocument", e);
				LOGGER.error("Tiempo:" + watch.time());
				throw new WSDAOException(e);
			}

			try {
				String codigoRespuesta = response.getCodigo();
				String descripcionRespuesta = response.getDescripcion();

				if (codigoRespuesta.equals("200")) {
					LOGGER.info("Transaccion exitosa para MSISDN [" + msisdn
							+ "]");
					LOGGER.info("Codigo Servicio Compra Bolsa: "
							+ codigoRespuesta);
					LOGGER.info("Descripcion Servicio Compra Bolsa"
							+ descripcionRespuesta);
					return response;
				} else {
					LOGGER.info("Transaccion FALLIDA para MSISDN [" + msisdn
							+ "]");
					LOGGER.error("Entrada: Msisdn ->"+request.getMsisdn()+"<- Canal ->"+
				    		request.getCanal()+"<- Codigo ->"+request.getCodigo()+"<- Plataforma ->"+request.getPlataforma()+
				    		"<- Usuario ->" + request.getUsuario()+"<- Cobroo ->"+request.getCobro()+"<-");
					
					LOGGER.info("Codigo Servicio Compra Bolsa: "
							+ codigoRespuesta);
					LOGGER.info("Descripcion Servicio Compra Bolsa: "
							+ descripcionRespuesta);
					return response;
				}
			} catch (Exception e) {
			    	LOGGER.error("Entrada: Msisdn ->"+request.getMsisdn()+"<- Canal ->"+
			    		request.getCanal()+"<- Codigo ->"+request.getCodigo()+"<- Plataforma ->"+request.getPlataforma()+
			    		"<- Usuario ->" + request.getUsuario()+"<- Cobroo ->"+request.getCobro()+"<-");
				LOGGER.error("Exception caught on Service response: "
						+ "comprarRequestDocument", e);
				throw new WSDAOException(e);
			}
		} catch (EntelServicesLocatorException e) {
			LOGGER.error("Error al inicializar el Port " + SCOBPortType.class,
					e);
			throw new WSDAOException(e);
		}
	}

	@Override
	public ArrayList<PurchasedPPBundle> obtenerSaldoBolsasPP(String msisdn) {
	    	TimeWatch watch = TimeWatch.start();
		ArrayList<PurchasedPPBundle> listaSaldoBolsas = new ArrayList<PurchasedPPBundle>();
		Collection<BundleDetailBalance> detalleSaldo = new ArrayList<BundleDetailBalance>();
		SCOBPortType port = null;

		try {
			port = EntelServices.getBolsasPPPMovilService()
					.getSCOBPortTypeSOAPBindingQSPort();
		} catch (EntelServicesLocatorException e1) {
		    LOGGER.error("Error al inicializar el Port " + SCOBPortType.class,
				e1);
			e1.printStackTrace();
		} catch (Exception e) {
		    LOGGER.error("Error al inicializar el Port " + SCOBPortType.class,
				e);
		    e.printStackTrace();
		}

		Mensaje men = new Mensaje();
		
		try {

			

			men.setCanal(CANAL);
			men.setMsisdn("569" + msisdn);
			com.epcs.provision.suscripcion.bolsasppmovil.types.ListarBolsasActivasResponseType.Mensaje bolsasResponse = port
					.listarBolsasActivasRequestDocument(men);
			LOGGER.info("Tiempo:" + watch.time());
			detalleSaldo = null;
			// detalleSaldo = leerXML();
			if (!bolsasResponse.getListadoCartasActivas().getDetalleBono()
					.isEmpty()
					&& bolsasResponse.getListadoCartasActivas() != null
					&& bolsasResponse.getListadoCartasActivas()
							.getDetalleBono().size() > 0) {

				for (DetalleBono detalleBono : bolsasResponse
						.getListadoCartasActivas().getDetalleBono()) {

					if (detalleSaldo != null) {
						if (true) {
							// Se agregan las bolsas que no existen en el XML.
							PurchasedPPBundle bolsa = new PurchasedPPBundle();
							bolsa.setNombreBolsa(detalleBono.getDescripcion());
							bolsa.setFechaExp(DateUtils.formatFecha(detalleBono
									.getTermino()));
							// TODO Implementar logica para mostrar mb minutos,
							// para mostrar saldo unidad
							bolsa.setSaldoUnidad(detalleBono
									.getDescripcionSaldo());
							bolsa.setTipoBolsa("Dispones de ");
							bolsa.setCarta("");
							listaSaldoBolsas.add(bolsa);
						}
					} else {
						PurchasedPPBundle bolsa = new PurchasedPPBundle();
						bolsa.setNombreBolsa(detalleBono.getDescripcion());
						bolsa.setFechaExp(DateUtils.formatFecha(detalleBono
								.getTermino()));

						if (detalleBono.getUnidad().equalsIgnoreCase("kb")) {
							LOGGER.info("Se formatea descripcion del saldo para mejor entendimiento del usuario de tipo "
									+ detalleBono.getUnidad());
							Long kbytesBam = 0L;
							kbytesBam = Long.parseLong(detalleBono.getSaldo());

							int cont = 0;
							Double kbs = kbytesBam.doubleValue();
							while (kbs > 1024) {
								kbs = kbs / 1024;
								cont++;
							}
							String sufijo = "";
							switch (cont) {
							case 0:
								sufijo = " KB";
								break;
							case 1:
								sufijo = " MB";
								break;
							case 2:
								sufijo = " GB";
								break;
							case 3:
								sufijo = " TB";
								break;
							}
							DecimalFormat df = new DecimalFormat("#.##");
							bolsa.setSaldoUnidad(df.format(kbs) + sufijo);
							bolsa.setMensaje("Dispones de ");
							bolsa.setCarta("");

						} else if (detalleBono.getUnidad().equalsIgnoreCase(
								"seg")) {
							LOGGER.info("Se formatea descripcion del saldo para mejor entendimiento del usuario de tipo "
									+ detalleBono.getUnidad());
							Long seconds = 0L;
							seconds = Long.parseLong(detalleBono.getSaldo());

							int cont = 0;
							Double sec = seconds.doubleValue();

							if (sec > 60) {
								sec = sec / 60;
								cont = 1;
							}

							String sufijo = "";
							switch (cont) {
							case 0:
								sufijo = " SEG";
								break;
							case 1:
								sufijo = " MIN";
								break;
							}
							DecimalFormat df = new DecimalFormat("#.##");
							bolsa.setSaldoUnidad(df.format(sec) + sufijo);
							bolsa.setMensaje("Dispones de ");
							bolsa.setCarta("");

							// TODO si otras bolsas poseen datos que se deban
							// formatear, deben ir en la siguiente validacion
							// del else
						} else {
							LOGGER.info(detalleBono.getUnidad());
							bolsa.setSaldoUnidad(detalleBono
									.getDescripcionSaldo());
							bolsa.setMensaje("Dispones de ");
							bolsa.setCarta("");
						}
						listaSaldoBolsas.add(bolsa);
					}
				}
			} else {
				LOGGER.info("No existen bolsas activas asociadas a MSISDN ["
						+ msisdn + "]");
			}
		} catch (Exception e) {
		    

		    LOGGER.error("Entrada: Msisdn ->"+men.getMsisdn()+"<- Canal ->"+
			    men.getCanal()+"<- ");
			LOGGER.error("No es posible listar bolsas activas asociadas a MSISDN ["
					+ msisdn + "], debido a error.");
			LOGGER.error("Tiempo:" + watch.time());
			LOGGER.error(e.getMessage());
		}
		return listaSaldoBolsas;
	}

	public AvailableBundleBAMCCPP getAllAvailablePPBundles(String msisdn)
			throws WSDAOException, ServiceException {
	    	TimeWatch watch = TimeWatch.start();

		SCOBPortType port = null;
		try {
			port = EntelServices.getBolsasPPPMovilService()
					.getSCOBPortTypeSOAPBindingQSPort();
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			error.printStackTrace();
		}
		LOGGER.info("Instanciando el port " + SCOBPortType.class);
		RequestType request = new RequestType();
		ListarBolsasResponseType.Mensaje response;

		try {
			LOGGER.info("Configurando Datos de la peticion");
			request.setMsisdn(Formato.validaMsisdn(msisdn));
			request.setCanal(CANAL);
			LOGGER.info("Invocando servicio");
			response = port.listarBolsasRequestDocument(request);
			LOGGER.info("Tiempo:" + watch.time());
		} catch (Exception e) {
		    	LOGGER.error("Entrada: Msisdn ->"+request.getMsisdn()+"<- Canal ->"+
		    		request.getCanal()+"<- ");
			LOGGER.error("Exception caught on Service invocation: "
					+ "listarBolsasDisponiblesBAMRequestDocument", e);
			LOGGER.error("Tiempo:" + watch.time());
			throw new WSDAOException(e);
		}

		try {
			AvailableBundleBAMCCPP resumen = new AvailableBundleBAMCCPP();
			resumen.setMercado(response.getMovil().getMercado());
			resumen.setMsisdn(response.getMovil().getMsisdn());

			for (ListarBolsasResponseType.Mensaje.ListadoCartas.DetalleCartaServicio det : response
					.getListadoCartas().getDetalleCartaServicio()) {
				BundleCCPPVO b = new BundleCCPPVO();

				b.setCodigo(det.getCodigo());
				b.setNombre(det.getNombre());
				b.setDescripcion(det.getDescripcion());
				b.setDescripcionComercial(det.getDescripcionComercial());
				b.setUnidad(det.getUnidad());
				b.setTipoOferta(det.getTipoOferta());
				// b.setPrecio(Double.parseDouble(det.getPrecio()));
				b.setPrecio(Config.getAppProperty("formatPrecio")
						+ Formato.formatPuntos(det.getPrecio(), Locale.GERMAN));

				resumen.addBolsa(b);
			}
			return resumen;
		} catch (Exception e) {
		    LOGGER.error("Entrada: Msisdn ->"+request.getMsisdn()+"<- Canal ->"+
		    		request.getCanal()+"<- ");
			LOGGER.error("Exception caught on Service response: "
					+ "listarBolsasDisponiblesBAMRequestDocument", e);
			throw new WSDAOException(e);
		}
	}

	@Override
	public AvailableBundleBAMSSCC getAllAvailableSSCCBundles(String msisdn)
			throws AppMobileException, ServiceException, WSDAOException {
	    	TimeWatch watch = TimeWatch.start();
		AvailableBundleBAMSSCC bolsasActualesDisponiblesSSCC = new AvailableBundleBAMSSCC();
		List<BundleSSCCVO> listBolsasActualesBean = new ArrayList<BundleSSCCVO>();
		List<BundleSSCCVO> listBolsasDisponiblesBean = new ArrayList<BundleSSCCVO>();

		AdminSuscripcionService port = null;

		LOGGER.info("Instanciando el port");
		try {
			port = EntelServices.getBolsasSSCCService()
					.getAdminSuscripcionServicePort();
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			error.printStackTrace();
		}

		LOGGER.info("Configurando Datos de la peticion");
		ConsultarBolsasActualYDisponibleFullSSCCType request = new ConsultarBolsasActualYDisponibleFullSSCCType();
		request.setMsisdn(msisdn);

		ResultadoConsultarBolsasActualYDisponibleFullSSCCType response = null;

		LOGGER.info("Invocando servicio");
		response = port.consultarBolsasActualYDisponibleFullSSCC(request);
		LOGGER.info("Tiempo:" + watch.time());
		String codigoRespuesta = response.getRespuesta().getCodigo();
		String descripcionRespuesta = response.getRespuesta().getDescripcion();

		LOGGER.info("codigoRespuesta  " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

		// TODO Config properties
		if (codigoRespuesta.equalsIgnoreCase("0000")) {
			List<BolsaActualType> listBolsasActuales = response
					.getBolsasActuales().getItemBolsaActual();
			List<BolsaType> listBolsasDisponibles = response
					.getBolsasDisponibles().getItemBolsa();

			// Construir Lista de Bolsas Actuales
			for (BolsaActualType bolsaType : listBolsasActuales) {
				listBolsasActualesBean.add(buildBolsaActual(bolsaType));
			}

			// Construir Lista de Bolsas Disponibles
			for (BolsaType bolsaType : listBolsasDisponibles) {
				listBolsasDisponiblesBean.add(buildBolsa(bolsaType));
			}

			bolsasActualesDisponiblesSSCC
					.setBolsasActuales(listBolsasActualesBean);
			bolsasActualesDisponiblesSSCC
					.setBolsasDisponibles(listBolsasDisponiblesBean);

			// } else if (CODIGO_RESPUESTA_NO_INFO.equals(codigoRespuesta)) {
		} else if (codigoRespuesta.equalsIgnoreCase("0002")) {
		    LOGGER.warn("Entrada: Msisdn ->"+request.getMsisdn()+ "<- ");
			LOGGER.warn("Service error code received: " + codigoRespuesta
					+ " - " + descripcionRespuesta);
			return null;
		} else {
		    LOGGER.error("Entrada: Msisdn ->"+request.getMsisdn()+ "<- ");
			LOGGER.error("Service error code received: " + codigoRespuesta
					+ " - " + descripcionRespuesta);
			LOGGER.error("Tiempo:" + watch.time());
			throw new ServiceException(codigoRespuesta, descripcionRespuesta);
		}

		return bolsasActualesDisponiblesSSCC;
	}

	private BundleSSCCVO buildBolsa(BolsaType bolsaType) {
		BundleSSCCVO bolsaBean = new BundleSSCCVO();

		bolsaBean.setCantidad(Double.parseDouble(bolsaType.getCantidad()));
		// bolsaBean.setCosto(Double.parseDouble(bolsaType.getCosto()));
		bolsaBean.setCosto(Config.getAppProperty("formatPrecio")
				+ Formato.formatPuntos(bolsaType.getCosto(), Locale.GERMAN));
		// b.setPrecio(Double.parseDouble(Formato.formatPuntos(det.getPrecio(),
		// Locale.GERMAN)));

		bolsaBean.setDescBolsa(bolsaType.getDescBolsa().getItemDescBolsa());
		bolsaBean.setFlagPromocion(bolsaType.getFlagPromocion());
		bolsaBean.setNombreBolsa(bolsaType.getNombreBolsa());
		bolsaBean.setObservacion(bolsaType.getObservacion());
		bolsaBean.setSpCodigo(bolsaType.getSpCodigo());
		bolsaBean.setSnCodigo(bolsaType.getSnCodigo());
		bolsaBean.setTipoBolsa(bolsaType.getTipoBolsa());
		bolsaBean.setTipoVigencia(bolsaBean.getTipoVigencia());
		bolsaBean.setVigencia(bolsaType.getVigencia());

		return bolsaBean;
	}

	private BundleSSCCVO buildBolsaActual(BolsaActualType bolsaType) {
		BundleSSCCVO bolsaSSCCVO = new BundleSSCCVO();
		try {
			try {
				// bolsaSSCCVO.setCosto(Double.parseDouble(bolsaType.getCosto()));
				bolsaSSCCVO.setCosto(Config.getAppProperty("formatPrecio")
						+ Formato.formatPuntos(bolsaType.getCosto(),
								Locale.GERMAN));
				// Double.parseDouble(Formato.formatPuntos(bolsaType.getCosto(),
				// Locale.GERMAN))
			} catch (NumberFormatException nfe) {
				LOGGER.warn(
						"Costo de bolsa no valida:"
								.concat(bolsaType.getNombreBolsa())
								.concat("->Cantidad:")
								.concat(bolsaType.getCosto()), nfe.getCause());
			}
			try {
				bolsaSSCCVO.setCantidad(Double.parseDouble(bolsaType
						.getCantidad()));
			} catch (NumberFormatException nfe) {
				LOGGER.warn(
						"Cantidad de bolsa no valida:"
								.concat(bolsaType.getNombreBolsa())
								.concat("->Cantidad:")
								.concat(bolsaType.getCantidad()),
						nfe.getCause());
			}
			bolsaSSCCVO.setDescBolsa(bolsaType.getDescBolsa()
					.getItemDescBolsa());
			bolsaSSCCVO.setFlagPromocion(bolsaType.getFlagPromocion());
			bolsaSSCCVO.setNombreBolsa(bolsaType.getNombreBolsa());
			bolsaSSCCVO.setObservacion(bolsaType.getObservacion());
			bolsaSSCCVO.setSpCodigo(bolsaType.getSpCodigo());
			bolsaSSCCVO.setSnCodigo(bolsaType.getSnCodigo());
			bolsaSSCCVO.setTipoBolsa(bolsaType.getTipoBolsa());
			bolsaSSCCVO.setTipoVigencia(bolsaSSCCVO.getTipoVigencia());
			bolsaSSCCVO.setVigencia(bolsaType.getVigencia());

			LOGGER.info("Estado de la bolsa: " + bolsaType.getEstado());

			if (bolsaType.getEstado().equalsIgnoreCase("A")) {
				bolsaSSCCVO.setEstado("Activa");
			} else if (bolsaType.getEstado().equalsIgnoreCase("PA")) {
				bolsaSSCCVO.setEstado("Pendiente de Activaci\u00f3n");
			} else if (bolsaType.getEstado().equalsIgnoreCase("")) {
				bolsaSSCCVO.setEstado("Pendiente de Activaci\u00f3n");
			} else {
				LOGGER.info("La bolsa viene del servicio en formato: "
						+ bolsaType.getEstado());
				bolsaSSCCVO.setEstado(bolsaType.getEstado());
			}
			bolsaSSCCVO.setFechaPendiente(DateUtils.parseDate(
					bolsaType.getFechaPendiente(),
					DateUtils.FORMAT_yyyyMMdd_HYPHEN));
		} catch (Exception ex) {
			LOGGER.warn("Excepcion al construir bolsa: ".concat(bolsaType
					.getNombreBolsa()), ex);
		}
		return bolsaSSCCVO;
	}
}
