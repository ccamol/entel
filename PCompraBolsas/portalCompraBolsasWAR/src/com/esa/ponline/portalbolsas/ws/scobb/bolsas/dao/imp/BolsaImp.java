package com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.imp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.epcs.provision.suscripcion.bolsaspp.ListarBolsasServiceFaultMessage;
import com.epcs.provision.suscripcion.bolsaspp.SCOBPortType;
import com.epcs.provision.suscripcion.bolsaspp.types.*;
import com.epcs.provision.suscripcion.bolsaspp.types.ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas.DetalleBono;
import com.epcs.provision.suscripcion.bolsaspp.types.ListarBolsasResponseType.Mensaje.ListadoCartas.DetalleCartaServicio;
import com.esa.ponline.portalbolsas.bean.BolsaComprada;
import com.esa.ponline.portalbolsas.bean.BolsaComprar;
import com.esa.ponline.portalbolsas.bean.BolsaDisponibleMM;
import com.esa.ponline.portalbolsas.bean.DetalleBalanceBolsa;
import com.esa.ponline.portalbolsas.bean.DetalleBolsaCliente;
import com.esa.ponline.portalbolsas.bean.Movil;
import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;
import com.esa.ponline.portalbolsas.exceptions.PortalBolsasException;
import com.esa.ponline.portalbolsas.exceptions.ws.EntelServicesLocatorException;
import com.esa.ponline.portalbolsas.exceptions.ws.ServiceException;
import com.esa.ponline.portalbolsas.exceptions.ws.WSDAOException;
import com.esa.ponline.portalbolsas.util.Config;
import com.esa.ponline.portalbolsas.util.DateUtils;
import com.esa.ponline.portalbolsas.util.Formato;
import com.esa.ponline.portalbolsas.util.TimeWatch;
import com.esa.ponline.portalbolsas.ws.EntelServices;
import com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.IBolsaDAO;

/**
 * @author ccastro
 *
 */

public class BolsaImp implements IBolsaDAO {

    private static final Logger LOGGER = Logger.getLogger(BolsaImp.class);
    
    public static final String CANAL_SCOB = LoadProperty.getProperty("parametros.bolsasSCOBPP.canal");
    
    private String saldo;
    
	@Override
	public ArrayList<BolsaComprada> listarBolsasActivas(String msisdn) {
	    	TimeWatch watch = TimeWatch.start();
		ArrayList<BolsaComprada> listaSaldoBolsas = new ArrayList<BolsaComprada>();
		Collection<DetalleBalanceBolsa> detalleSaldo = new ArrayList<DetalleBalanceBolsa>();
		SCOBPortType port = null;

		try {
			port = EntelServices.getScobBolsasPPPSPort().getSCOBPortTypeSOAPBindingQSPort();
		} catch (EntelServicesLocatorException e1) {
		    LOGGER.error("Error al inicializar el Port " + SCOBPortType.class,
				e1);
			e1.printStackTrace();
		} catch (Exception e) {
		    LOGGER.error("Error al inicializar el Port " + SCOBPortType.class,
				e);
		    e.printStackTrace();
		}

		ListarBolsasActivasRequestType.Mensaje men = new ListarBolsasActivasRequestType.Mensaje();
		
		try {
			men.setCanal(CANAL_SCOB);
			men.setMsisdn(msisdn);
			ListarBolsasActivasResponseType.Mensaje bolsasResponse = port
					.listarBolsasActivasRequestDocument(men);
			LOGGER.info("Tiempo:" + watch.time());
			detalleSaldo = null;
			// detalleSaldo = leerXML();
			if (!bolsasResponse.getListadoCartasActivas().getDetalleBono()
					.isEmpty()
					&& bolsasResponse.getListadoCartasActivas() != null
					&& bolsasResponse.getListadoCartasActivas()
							.getDetalleBono().size() > 0) {

				for (DetalleBono detalleBono : bolsasResponse.getListadoCartasActivas().getDetalleBono()) {

					if (detalleSaldo != null) {
						if (true) {
							// Se agregan las bolsas que no existen en el XML.
							BolsaComprada bolsa = new BolsaComprada();
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
						BolsaComprada bolsa = new BolsaComprada();
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


    @Override
    public ComprarResponseType.Mensaje comprarBolsa(String msisdn, String codigo, String cobro)
            throws PortalBolsasException, ServiceException, WSDAOException {
        TimeWatch watch = TimeWatch.start();

        SCOBPortType port = getScobServiceConnection();
        LOGGER.info("Inicia tiempo coneccion del servicio: " + watch.time() + " | " + SCOBPortType.class);
        
        ComprarResponseType.Mensaje response = null;
        ComprarRequestType.Mensaje request = new ComprarRequestType.Mensaje();

        try {
            LOGGER.info("Configurando Datos de la peticion");
            request.setMsisdn(Formato.validaMsisdn(msisdn));
            request.setCanal(CANAL_SCOB);
            request.setCodigo(codigo);
            request.setPlataforma("");
            request.setUsuario("");
            request.setCobro(cobro);
            LOGGER.info("Invocando servicio");
            response = port.comprarRequestDocument(request);
            LOGGER.info("Tiempo para tx comprarBolsa en servicio: " + watch.time() + " | " + SCOBPortType.class);
        } catch (Exception e) {
            LOGGER.error("Entrada: Msisdn ->" + request.getMsisdn() + "<- Canal ->" + request.getCanal()
                    + "<- Codigo ->" + request.getCodigo() + "<- Plataforma ->" + request.getPlataforma()
                    + "<- Usuario ->" + request.getUsuario() + "<- Cobroo ->" + request.getCobro() + "<-");
            LOGGER.error("Exception caught on Service invocation: " + "comprarRequestDocument", e);
            LOGGER.error("Tiempo:" + watch.time());
            throw new WSDAOException(e);
        }

        try {
            String codigoRespuesta = response.getCodigo();
            String descripcionRespuesta = response.getDescripcion();

            if (codigoRespuesta.equals("200")) {
                LOGGER.info("Transaccion exitosa para MSISDN [" + msisdn + "]");
                LOGGER.info("Codigo Servicio Compra Bolsa: " + codigoRespuesta);
                LOGGER.info("Descripcion Servicio Compra Bolsa" + descripcionRespuesta);
                return response;
            } else {
                LOGGER.info("Transaccion FALLIDA para MSISDN [" + msisdn + "]");
                LOGGER.error("Entrada: Msisdn ->" + request.getMsisdn() + "<- Canal ->" + request.getCanal()
                        + "<- Codigo ->" + request.getCodigo() + "<- Plataforma ->" + request.getPlataforma()
                        + "<- Usuario ->" + request.getUsuario() + "<- Cobro ->" + request.getCobro() + "<-");

                LOGGER.info("Codigo Servicio Compra Bolsa: " + codigoRespuesta);
                LOGGER.info("Descripcion Servicio Compra Bolsa: " + descripcionRespuesta);
                return response;
            }
        } catch (Exception e) {
            LOGGER.error("Entrada: Msisdn ->" + request.getMsisdn() + "<- Canal ->" + request.getCanal()
                    + "<- Codigo ->" + request.getCodigo() + "<- Plataforma ->" + request.getPlataforma()
                    + "<- Usuario ->" + request.getUsuario() + "<- Cobro ->" + request.getCobro() + "<-");
            LOGGER.error("Exception caught on Service response: " + "comprarRequestDocument", e);
            throw new WSDAOException(e);
        }
    }

    @Override
    public ArrayList<DetalleBolsaCliente> listarBolsas(String msisdn) {
        TimeWatch watch = TimeWatch.start();
        ArrayList<DetalleBolsaCliente> detalleBolsasCliente = new ArrayList<DetalleBolsaCliente>();

        Collection<DetalleBalanceBolsa> detalleSaldo = new ArrayList<DetalleBalanceBolsa>();

        SCOBPortType port = null;

        try {
            port = EntelServices.getScobBolsasPPPSPort().getSCOBPortTypeSOAPBindingQSPort();
        } catch (EntelServicesLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + SCOBPortType.class, e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Port " + SCOBPortType.class, e);
            e.printStackTrace();
        }

        RequestType mensaje = new RequestType();

        try {

        	//canal para portal movil
//            mensaje.setCanal("PMOVIL");
            
            //canal para planes mm autogestion
            mensaje.setCanal(CANAL_SCOB);
            
            //PLAN PP NORMAL
//          mensaje.setMsisdn("56977467726");
            //PLAN MM Autogestion
//          mensaje.setMsisdn("56956275885");            
            mensaje.setMsisdn(msisdn);

            ListarBolsasResponseType.Mensaje bolsasResponse = port.listarBolsasRequestDocument(mensaje);
            
            LOGGER.info("Tiempo:" + watch.time());
            detalleSaldo = null;
            if (!bolsasResponse.getListadoCartas().getDetalleCartaServicio().isEmpty()
                    && bolsasResponse.getListadoCartas() != null
                    && bolsasResponse.getListadoCartas().getDetalleCartaServicio().size() > 0) {
            	
            	saldoSuficiente(msisdn);

                for (DetalleCartaServicio detalleCarta : bolsasResponse.getListadoCartas().getDetalleCartaServicio()) {

                    if (detalleSaldo == null) {
                        if (true) {
                            DetalleBolsaCliente bolsa = new DetalleBolsaCliente();
                            bolsa.setCodigo(detalleCarta.getCodigo());
                            bolsa.setDescripcion(detalleCarta.getDescripcion());
                            bolsa.setDescripcionComercial(detalleCarta.getDescripcionComercial());
                            bolsa.setDescripcionTecnica(detalleCarta.getDescripcionTecnica());
                            bolsa.setDestino(detalleCarta.getDestino());
                            bolsa.setFinExposicion(detalleCarta.getFinExposicion());
                            bolsa.setInicioExposicion(detalleCarta.getInicioExposicion());
                            bolsa.setNombre(detalleCarta.getNombre());
                            bolsa.setOrden(detalleCarta.getOrden());
                            bolsa.setPrecio(detalleCarta.getPrecio());
                            bolsa.setTipoOferta(detalleCarta.getTipoOferta());
                            bolsa.setUnidad(detalleCarta.getUnidad());
                            
                            bolsa.setVigencia(detalleCarta.getVigencia());
                            //TODO linea para mostrar y validar vigencia
//                            if(detalleCarta.getVigencia().equals("")){
//                            	bolsa.setVigencia("");
//                            }else{
//                            	bolsa.setVigencia(", " + detalleCarta.getVigencia());
//                            }
                            // TODO validar este caso para mensaje de saldo insuficiente en el front
                            bolsa.setSaldoInsuficiente(
                            		Integer.parseInt(getSaldo()) >= Integer.parseInt(detalleCarta.getPrecio()) ? "OK" : "saldoInsuficiente");
                            detalleBolsasCliente.add(bolsa);
                        }
                    } else {
                        DetalleBolsaCliente bolsa = new DetalleBolsaCliente();

                        detalleBolsasCliente.add(bolsa);
                    }
                }
            } else {
                LOGGER.info("No existen bolsas activas asociadas a MSISDN [" + msisdn + "]");
            }
        } catch (ListarBolsasServiceFaultMessage e) {
            ServiceFaultType msg = e.getFaultInfo().getMensaje();
            String codigoRespuesta = msg.getCodigo();
            String descripcionRespuesta = msg.getDescripcion();
            
            LOGGER.error("Service error code received: " + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error(new ServiceException(codigoRespuesta, descripcionRespuesta));      
        } catch (Exception e) {
            LOGGER.error("Entrada: Msisdn ->" + mensaje.getMsisdn() + "<- Canal ->" + mensaje.getCanal() + "<- ");
            LOGGER.error("No es posible listar bolsas activas asociadas a MSISDN [" + msisdn + "]");
            LOGGER.error("Probablemente el cliente MSISDN [" + msisdn + "] no tiene aprovisionado el canal [" + mensaje.getCanal() + "] para el ambiente: " + LoadProperty.getProperty("scobPortTypeSOAPBindingQSServiceEndPoint"));            
            LOGGER.error("Tiempo:" + watch.time());
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return detalleBolsasCliente;

    }

    private void saldoSuficiente(String msisdn) {
        TimeWatch watch = TimeWatch.start();
        
        SCOBPortType port = getScobServiceConnection();

        // Mensaje men = new Mensaje();
        ListarBolsasActivasRequestType.Mensaje mensaje = new ListarBolsasActivasRequestType.Mensaje();
        ListarBolsasActivasResponseType.Mensaje respMovilCliente = null;        

        try {
            mensaje.setCanal(CANAL_SCOB);
            mensaje.setMsisdn(msisdn);
            
            LOGGER.info("CANAL_SCOB: " + CANAL_SCOB);
            LOGGER.info("MSISDN: " + msisdn);
            
            respMovilCliente = port.listarBolsasActivasRequestDocument(mensaje);
            LOGGER.info("Tiempo:" + watch.time());    	
        } catch (Exception e) {
            LOGGER.error("Entrada: Msisdn ->" + mensaje.getMsisdn() + "<- Canal ->" + mensaje.getCanal() + "<- ");
            LOGGER.error("Tiempo:" + watch.time());
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } 
        setSaldo(respMovilCliente.getMovil().getSaldo());
        //TODO setSaldo en duro para mostrar vista
//        setSaldo("3000");
	}

	@Override
    public Movil desplegarInfoCliente(String msisdn) throws PortalBolsasException, ServiceException, WSDAOException {
        TimeWatch watch = TimeWatch.start();
        Movil movil = null;

        SCOBPortType port = null;

        try {
            port = EntelServices.getScobBolsasPPPSPort().getSCOBPortTypeSOAPBindingQSPort();
        } catch (EntelServicesLocatorException e1) {
            LOGGER.error("Error al inicializar el Port " + SCOBPortType.class, e1);
            e1.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Port " + SCOBPortType.class, e);
            e.printStackTrace();
        }

        // Mensaje men = new Mensaje();
        ListarBolsasActivasRequestType.Mensaje mensaje = new ListarBolsasActivasRequestType.Mensaje();

        try {
            mensaje.setCanal(CANAL_SCOB);
            mensaje.setMsisdn(msisdn);
            
            LOGGER.info("CANAL_SCOB: " + CANAL_SCOB);
            LOGGER.info("MSISDN: " + msisdn);
            
            ListarBolsasActivasResponseType.Mensaje respMovilCliente = port.listarBolsasActivasRequestDocument(mensaje);
            LOGGER.info("Tiempo:" + watch.time());
            // detalleSaldo = leerXML();

            if (respMovilCliente.getMovil().getMercado() != null) {
                Movil movilCliente = new Movil();

                movilCliente.setCambiosPlan(respMovilCliente.getMovil().getCambiosPlan());
                movilCliente.setCategoria(respMovilCliente.getMovil().getCategoria());
                movilCliente.setCreacionFrecuente(respMovilCliente.getMovil().getCreacionFrecuente());
                movilCliente.setEstado(respMovilCliente.getMovil().getEstado());
                movilCliente.setEstadoBSCS(respMovilCliente.getMovil().getEstadoBSCS());
                movilCliente.setFechaActivacion(respMovilCliente.getMovil().getFechaActivacion());
                movilCliente.setFechaActivacionBSCS(respMovilCliente.getMovil().getFechaActivacionBSCS());
                movilCliente.setFechaCambioPlan(respMovilCliente.getMovil().getFechaCambioPlan());
                movilCliente.setFechaConversion(respMovilCliente.getMovil().getFechaConversion());
                //dd/MM/yyyy
                movilCliente.setFechaDesactivacion(DateUtils.formatoSeparacionFecha(respMovilCliente.getMovil().getFechaDesactivacion()));
                movilCliente.setFechaEliminacion(respMovilCliente.getMovil().getFechaEliminacion());
                movilCliente.setFechaUltimaLlamada(respMovilCliente.getMovil().getFechaUltimaLlamada());
                movilCliente.setFrecuentes(respMovilCliente.getMovil().getFrecuentes());
                movilCliente.setGrupoBSCS(respMovilCliente.getMovil().getGrupoBSCS());
                movilCliente.setIccid(respMovilCliente.getMovil().getIccid());
                movilCliente.setImsi(respMovilCliente.getMovil().getImsi());
                movilCliente.setMercado(respMovilCliente.getMovil().getMercado());
                movilCliente.setModificacionFrecuente(respMovilCliente.getMovil().getModificacionFrecuente());
                movilCliente.setMsisdn(respMovilCliente.getMovil().getMsisdn());
                movilCliente.setPin(respMovilCliente.getMovil().getPin());
                movilCliente.setPin2(respMovilCliente.getMovil().getPin2());
                movilCliente.setPlan(respMovilCliente.getMovil().getPlan());
                movilCliente.setPlanBSCS(respMovilCliente.getMovil().getPlanBSCS());
                movilCliente.setProveedor(respMovilCliente.getMovil().getProveedor());
                movilCliente.setPuk(respMovilCliente.getMovil().getPuk());
                movilCliente.setPuk2(respMovilCliente.getMovil().getPuk2());
                movilCliente.setRecargas(respMovilCliente.getMovil().getRecargas());
                movilCliente.setRutBSCS(respMovilCliente.getMovil().getRutBSCS());
                
                //TODO setSaldoCliente en duro para validar vista
//                movilCliente.setSaldo("1000");
                movilCliente.setSaldo(respMovilCliente.getMovil().getSaldo());
                
                LOGGER.info("getSaldo: " + respMovilCliente.getMovil().getSaldo());
                LOGGER.info("getRutBSCS: " + respMovilCliente.getMovil().getRutBSCS());
                LOGGER.info("getRecargas: " + respMovilCliente.getMovil().getRecargas());
                LOGGER.info("getPuk2: " + respMovilCliente.getMovil().getPuk2());
                LOGGER.info("getPuk: " + respMovilCliente.getMovil().getPuk());
                LOGGER.info("getProveedor: " + respMovilCliente.getMovil().getProveedor());
                LOGGER.info("getPlanBSCS: " + respMovilCliente.getMovil().getPlanBSCS());
                LOGGER.info("getPlan: " + respMovilCliente.getMovil().getPlan());
                LOGGER.info("getPin2: " + respMovilCliente.getMovil().getPin2());
                LOGGER.info("getPin: " + respMovilCliente.getMovil().getPin());
                LOGGER.info("getMsisdn: " + respMovilCliente.getMovil().getMsisdn());
                LOGGER.info("getModificacionFrecuente: " + respMovilCliente.getMovil().getModificacionFrecuente());
                LOGGER.info("getMercado: " + respMovilCliente.getMovil().getMercado());
                LOGGER.info("getImsi: " + respMovilCliente.getMovil().getImsi());
                LOGGER.info("getIccid: " + respMovilCliente.getMovil().getIccid());
                LOGGER.info("getGrupoBSCS: " + respMovilCliente.getMovil().getGrupoBSCS());
                LOGGER.info("getCambiosPlan: " + respMovilCliente.getMovil().getCambiosPlan());
                LOGGER.info("getCategoria: " + respMovilCliente.getMovil().getCategoria());
                LOGGER.info("getCreacionFrecuente: " + respMovilCliente.getMovil().getCreacionFrecuente());
                LOGGER.info("getEstado: " + respMovilCliente.getMovil().getEstado());
                LOGGER.info("getEstadoBSCS: " + respMovilCliente.getMovil().getEstadoBSCS());
                LOGGER.info("getFechaActivacion: " + respMovilCliente.getMovil().getFechaActivacion());
                LOGGER.info("getFechaActivacionBSCS: " + respMovilCliente.getMovil().getFechaActivacionBSCS());
                LOGGER.info("getFechaCambioPlan: " + respMovilCliente.getMovil().getFechaCambioPlan());
                LOGGER.info("getFechaConversion: " + respMovilCliente.getMovil().getFechaConversion());
                LOGGER.info("getFechaDesactivacion (formato: dd/MM/yyyy): " + respMovilCliente.getMovil().getFechaDesactivacion());
                LOGGER.info("getFechaEliminacion: " + respMovilCliente.getMovil().getFechaEliminacion());
                LOGGER.info("getFechaUltimaLlamada: " + respMovilCliente.getMovil().getFechaUltimaLlamada());
                LOGGER.info("getFrecuentes: " + respMovilCliente.getMovil().getFrecuentes());                
                
                movil = movilCliente;
            }
            respMovilCliente.getMovil().getMsisdn();

        } catch (Exception e) {
            LOGGER.error("Entrada: Msisdn ->" + mensaje.getMsisdn() + "<- Canal ->" + mensaje.getCanal() + "<- ");
            LOGGER.error("No es posible obtener informacion sobre descripcion movil para MSISDN [" + msisdn + "]");
            LOGGER.error("Probablemente el cliente MSISDN [" + msisdn + "] no tiene aprovisionado el canal [" + mensaje.getCanal() + "] para el ambiente: " + LoadProperty.getProperty("scobPortTypeSOAPBindingQSServiceEndPoint"));            
            LOGGER.error("Tiempo:" + watch.time());
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return movil;

    }
    
    public BolsaDisponibleMM getBolsaSeleccionDetalleMM(String msisdn) throws WSDAOException, ServiceException {
        TimeWatch watch = TimeWatch.start();

        SCOBPortType port = getScobServiceConnection();
        
        LOGGER.info("Instanciando el port " + SCOBPortType.class);
        RequestType request = new RequestType();
        ListarBolsasResponseType.Mensaje response;

        try {
            LOGGER.info("Configurando Datos de la peticion");
            request.setMsisdn(Formato.validaMsisdn(msisdn));
            request.setCanal(CANAL_SCOB);
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
            BolsaDisponibleMM resumen = new BolsaDisponibleMM();
            resumen.setMercado(response.getMovil().getMercado());
            resumen.setMsisdn(response.getMovil().getMsisdn());

            for (ListarBolsasResponseType.Mensaje.ListadoCartas.DetalleCartaServicio det : response
                    .getListadoCartas().getDetalleCartaServicio()) {
                BolsaComprar b = new BolsaComprar();

                b.setCodigo(det.getCodigo());
                b.setNombre(det.getNombre());
                b.setDescripcion(det.getDescripcion());
                b.setDescripcionComercial(det.getDescripcionComercial());
                b.setUnidad(det.getUnidad());
                b.setTipoOferta(det.getTipoOferta());
                b.setPrecioInt(det.getPrecio());
                
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

    /**
     * Gets the account service connection.
     *
     * @return the account service connection
     */
    private SCOBPortType getScobServiceConnection() {
        TimeWatch watch = TimeWatch.start();
        SCOBPortType port = null;

        try {
            port = EntelServices.getScobBolsasPPPSPort().getSCOBPortTypeSOAPBindingQSPort();
        } catch (EntelServicesLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + SCOBPortType.class, e);
            LOGGER.error("Tiempo: " + watch.time() + " | " + SCOBPortType.class);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Port " + SCOBPortType.class, e);
            LOGGER.error("Tiempo: " + watch.time() + " | " + SCOBPortType.class);
            e.printStackTrace();
        }
        return port;
    }

	/**
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}


}