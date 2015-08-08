/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

import cl.i2b.ws22.Plan;
import cl.i2b.ws22.Trafico;

import com.epcs.billing.registrouso.BillingRegistroUsoService;
import com.epcs.billing.registrouso.BillingRegistroUsoServicePortType;
import com.epcs.billing.registrouso.types.ConsultarTraficoResumenType;
import com.epcs.billing.registrouso.types.ProductoType;
import com.epcs.billing.registrouso.types.ResultadoConsultarTraficoResumenType;
import com.epcs.cliente.perfil.types.ConsultarXmlPlanesFullType;
import com.epcs.cliente.perfil.types.DetallePlanActualType;
import com.epcs.cliente.perfil.types.PlanFullType;
import com.epcs.cliente.perfil.types.ResultadoConsultaXmlplanesfullType;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.entity.ConsultaPlanesMultimediaType;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.entity.DatosExcedidosType;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.entity.HeaderInType;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.response.ConsultaPlanesMultimediaResponseType;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.response.ResponseConsultaPlanType;
import com.esa.billing.orderingprod.consultarfamiliamultidispositivo.request.ConsultaFamiliaMultiDispositivoRequestType;
import com.esa.billing.orderingprod.consultarfamiliamultidispositivo.request.RequestType;
import com.esa.billing.orderingprod.consultarfamiliamultidispositivo.response.ConsultaFamiliaMultiDispositivoResponseType;
import com.esa.mediation.mediation.n.obtenertraficoindividualag.ObtenerTraficoIndividualAGFaultMessage;
import com.esa.mediation.mediation.n.obtenertraficoindividualag.ObtenerTraficoIndividualAGPort;
import com.esa.mediation.mediation.n.obtenertraficoindividualag.request.ObtenerTraficoIndividualAGReq;
import com.esa.mediation.mediation.n.obtenertraficoindividualag.response.ObtenerTraficoIndividualAGRes;
import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.util.DateUtils;
import com.esa.ponline.appmobile.util.Formato;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.util.TimeWatch;
import com.esa.ponline.appmobile.vo.PlanMMAG_VO;
import com.esa.ponline.appmobile.vo.PlanMultimediaVO;
import com.esa.ponline.appmobile.vo.traffic.TrafficVO;
import com.esa.ponline.appmobile.ws.EntelServices;
import com.esa.ponline.appmobile.ws.dao.ITrafficDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class TrafficImp implements ITrafficDAO {

	private static final Logger LOGGER = Logger.getLogger(TrafficImp.class);

	@Override
	public List<TrafficVO> consultaTrafico(String msisdn,String aaa) throws AppMobileException {
	    TimeWatch watch = TimeWatch.start();
	    List<TrafficVO> trafico = new ArrayList<TrafficVO>();
	    LOGGER.info("Inicio consulta trafico para numero movil " + msisdn);
	    try {
		
		BillingRegistroUsoService locator = EntelServices.getTraficoResumenLocatorInstanceService();
		BillingRegistroUsoServicePortType service = locator.getBillingRegistroUsoServicePort();
		
		ConsultarTraficoResumenType consultarTraficoResumen =  new ConsultarTraficoResumenType();
		consultarTraficoResumen.setMsisdn(msisdn);
		consultarTraficoResumen.setAutoAtencion(aaa);
		ResultadoConsultarTraficoResumenType respuesta = service.consultarTraficoResumen(consultarTraficoResumen);
		LOGGER.info("Tiempo:" + watch.time());
		Iterator<ProductoType> productoIterator = respuesta.getProducto().iterator();
		while (productoIterator.hasNext()) {
		    TrafficVO traff = new TrafficVO();
		    ProductoType producto =  productoIterator.next();
		    producto.getIdProducto();
		    if ("ENTRE MOVILES".equalsIgnoreCase(producto.getIdProducto())){
			producto.setIdProducto("SMS");
			producto.setKey1(String.valueOf(NumberUtils.toInt(producto.getKey1(),0) + NumberUtils.toInt(producto.getKey3(),0)));
		    }
			
		    if ("NET".equalsIgnoreCase(producto.getIdProducto())){
			producto.setIdProducto("TRAF");
			producto.setKey1(obtenerTrafico(producto.getKey1()+"+"+producto.getKey3()));
		    }
		    if ("BAJO".equalsIgnoreCase(producto.getIdProducto())){
			producto.setIdProducto("TRAF");
			producto.setKey1(obtenerTrafico(producto.getKey1()+"+"+producto.getKey3()));
		    }
		    
		    
			traff.setTipoTrafico(producto.getIdProducto());
			traff.setPeriodo(producto.getFechaRegistro().replace("-", "."));
			
			if (producto.getIdProducto().equals("TRAF")) {
				traff.setTrafico(obtenerTrafico(producto.getKey1().replace(":", ".")));
			} else {
				traff.setTrafico(producto.getKey1());
			}
			trafico.add(traff);
		    
		}
		
		
	    }catch (Exception e) {
		LOGGER.error("consulta trafico, Input:[ movil: " + msisdn + " ] [ aaa: " + aaa + "]: " + e.getMessage());
		LOGGER.error("Tiempo:" + watch.time());
		e.printStackTrace();
	    } finally {
    		LOGGER.info("Fin consultando trafico para numero movil " + msisdn);
	    }
	    return trafico;
	}
	
    /**
     * Gets the AG service connection.
     *
     * @return the AG service connection
     */
    private ObtenerTraficoIndividualAGPort getObtenerTraficoAGConnection() {
        TimeWatch watch = TimeWatch.start();
        ObtenerTraficoIndividualAGPort port = null;

        try {
            port = EntelServices.getObtenerTraficoIndividualAGService().getObtenerTraficoIndividualAGPort();
        } catch (EntelServicesLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + ObtenerTraficoIndividualAGPort.class, e);
            LOGGER.error("Tiempo: " + watch.time() + " | " + ObtenerTraficoIndividualAGPort.class);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Port " + ObtenerTraficoIndividualAGPort.class, e);
            LOGGER.error("Tiempo: " + watch.time() + " | " + ObtenerTraficoIndividualAGPort.class);
            e.printStackTrace();
        }
        return port;
    }

	//TODO validar caso excedido para mmAutogestion
//    private boolean esPlanMMAutogestion(String tipoPlan,String idPlan){
	//TODO establecer logs
	private ObtenerTraficoIndividualAGRes obtenerTraficoMMAGG(String msisdn){
	    ObtenerTraficoIndividualAGRes obtenerTraficoIndividualAGRes = new ObtenerTraficoIndividualAGRes();
        
        ObtenerTraficoIndividualAGReq obtenerTraficoIndividualAGRequest = new ObtenerTraficoIndividualAGReq();
        obtenerTraficoIndividualAGRequest.setMovil(Constants.PREFIJO_ENTEL.getStringValue()+msisdn);
        
        ObtenerTraficoIndividualAGPort port = getObtenerTraficoAGConnection();
        
        try {
            obtenerTraficoIndividualAGRes = 
                    port.obtenerTraficoIndividualAG(obtenerTraficoIndividualAGRequest);
        } catch (ObtenerTraficoIndividualAGFaultMessage e) {
            LOGGER.error("Problema en servicio ObtenerTraficoIndividualAGRes: " + e.getMessage());
            e.printStackTrace();
        }
        
        return obtenerTraficoIndividualAGRes;
    }
	
	//TODO MMAG
    @Override
    public List<PlanMMAG_VO> consultarTraficoMMAGExcedido(String msisdn)
            throws AppMobileException {
        //TODO: se llama a dos servicios, no queda claro el try/cach
        TimeWatch watch = TimeWatch.start();
        List<PlanMMAG_VO> lplanMMAG_VO = new ArrayList<PlanMMAG_VO>();
        try {

            Plan planActual = new Plan();

            LOGGER.info("Servicio de Planes, Endpoint: " + Config.getAppProperty("excedidosServiceEndPoint"));
            LOGGER.info("Servicio de planes, Metodo: consultarPlanActual , Input:[ movil: " + msisdn + " ]");

            planActual = EntelServices.getPlanInstanceService().getWsPlanesMovilPort().consultarPlanActual(msisdn);
            LOGGER.info("Tiempo:" + watch.time());
            watch.reset();
            String codigoPlan = planActual.getCodigoPlan();

            try {
                LOGGER.info("Servicio de xmlPlanFull, Endpoint: " + Config.getAppProperty("profileServiceEndPoint"));
                LOGGER.info("Servicio de xmlPlanFull, Metodo: consultaXmlPlanesFull , Input:[ codbscs2: " + codigoPlan
                        + " TipoPlan: ]");
                
                //TODO servicio MMAG Trafico CAPS
                ObtenerTraficoIndividualAGRes obtenerTraficoIndividualAGRes = new ObtenerTraficoIndividualAGRes();
                obtenerTraficoIndividualAGRes = obtenerTraficoMMAGG(msisdn);
                
                //TODO valida si es MMAG
                if (obtenerTraficoIndividualAGRes.getCodigo().equals("0000")) {
                    PlanMMAG_VO planMMAG_VO = new PlanMMAG_VO();
                    
                    planMMAG_VO.setCuotaTrafico(String.valueOf(obtenerTraficoIndividualAGRes.getCuotaTrafico().getValue()));
                    planMMAG_VO.setCodigo(String.valueOf(obtenerTraficoIndividualAGRes.getConsumoCap1().getValue()));
                    planMMAG_VO.setCosumoCap1(String.valueOf(obtenerTraficoIndividualAGRes.getConsumoCap1().getValue()));
                    planMMAG_VO.setCosumoCap2(String.valueOf(obtenerTraficoIndividualAGRes.getConsumoCap2().getValue()));
                    planMMAG_VO.setCosumoCap3(String.valueOf(obtenerTraficoIndividualAGRes.getConsumoCap3().getValue()));
                    planMMAG_VO.setDescWSMensaje(obtenerTraficoIndividualAGRes.getDescripcion());
                    planMMAG_VO.setMovil(obtenerTraficoIndividualAGRes.getMovil().getValue());
                    planMMAG_VO.setDescPlan(planActual.getDescripcion());
                    
                    // FORMATEA FEHA Y CONVIERTE KB A MB
                    //07/07/2015 01:48:34
                    String fecha = DateUtils.formatFechaMMAG(obtenerTraficoIndividualAGRes.getFechaConsulta().getValue());
                    
                    //al 07 de julio
                    String fechaReferencia = DateUtils.formatFechaString(fecha, DateUtils.DIAMESYEARBE,
                            DateUtils.ALDIAMES);
                    
                    //a las 01:48 hrs.
                    String horaReferencia = DateUtils.formatFechaString(fecha, DateUtils.DIAMESYEARHORABE,
                            DateUtils.ALASHORA);
                    
                    //al 07 de julio 01:48
                    String fechaHoraReferencia = DateUtils.formatFechaString(fecha, DateUtils.DIAMESYEARHORABE,
                            DateUtils.ALDIAMESHORA);
                    
                    planMMAG_VO.setHoraReferencia("");
                    planMMAG_VO.setFechaHoraReferencia("");
                    planMMAG_VO.setFechaReferencia("");
                    
                    //TODO fechaConsulta -> fechaDatos
                    planMMAG_VO.setFechaConsulta(fechaReferencia+" "+horaReferencia);
                    
                    Double cuotaTraficoCap01 = Double
                          .valueOf(Formato.isEmptyString(String.valueOf(obtenerTraficoIndividualAGRes.getConsumoCap1().getValue())) ? "0.0"
                          : String.valueOf(obtenerTraficoIndividualAGRes.getConsumoCap1().getValue()));
                    Double porcentajeConsumido = 100.0;
                    Double pRoundConsumido = 100.0;
                    Double consumido = cuotaTraficoCap01;
                    
                    Double totalTrafico = getMegabytesMMAG(String.valueOf(obtenerTraficoIndividualAGRes.getCuotaTrafico().getValue()));
                    
                    LOGGER.info("### Datos Consumo para MMAutogestion ###");
                    LOGGER.info("MSISDN ->" + msisdn + "<- | CuotaTrafico: " + planMMAG_VO.getCuotaTrafico());
                    LOGGER.info("MSISDN ->" + msisdn + "<- | CosumoCap1: " + planMMAG_VO.getCosumoCap1());
                    LOGGER.info("MSISDN ->" + msisdn + "<- | CosumoCap2: " + planMMAG_VO.getCosumoCap2());
                    LOGGER.info("MSISDN ->" + msisdn + "<- | CosumoCap3: " + planMMAG_VO.getCosumoCap3());
                    
                    if (totalTrafico==0.0){
                        LOGGER.warn("La linea MMAutogestion MSISDN ->" + msisdn + "<- posee cuotaTrafico=0");
                        porcentajeConsumido = Double.valueOf(planMMAG_VO.getCuotaTrafico());
                        pRoundConsumido = porcentajeConsumido - (porcentajeConsumido % 10);
                        
                        //TODO Validar este caso, si es empresa y autogestion entonces si aplica, caso contrario retorno nulo
//                        return null;
                    } else {
                        // CALCULA EL PROCENTAJE
                        if (cuotaTraficoCap01 <= totalTrafico) {
                            porcentajeConsumido = (cuotaTraficoCap01 / totalTrafico) * 100;
                            pRoundConsumido = porcentajeConsumido - (porcentajeConsumido % 10);
                        } else {
                            consumido = totalTrafico;
                        }
                    }
                    
                    planMMAG_VO.setConsumido(Formato.formatUnDecimal(consumido));
                    planMMAG_VO.setPorcentajeConsumido(Formato.formatUnDecimal((porcentajeConsumido)));
                    planMMAG_VO.setProundConsumido(Formato.formatPuntos(String.valueOf(pRoundConsumido),
                            Locale.GERMAN));
                    
                    lplanMMAG_VO.add(planMMAG_VO);
                } else {
                    LOGGER.info("Servicio de traficoMMAG Codigo error:"
                            + obtenerTraficoIndividualAGRes.getCodigo());
                    LOGGER.info("Servicio de traficoMMAG Desc error:"
                            + obtenerTraficoIndividualAGRes.getDescripcion());
                    return null;
                }
                return lplanMMAG_VO;
                
            } catch (Exception e) {
                LOGGER.error("Problema en la llamada a consulta de trafico excedido MMAG " + e.getMessage());
                LOGGER.error("Tiempo:" + watch.time());
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            LOGGER.error("Tiempo:" + watch.time());
            return null;
        }
    }
    
//    private String calcularGraficoPorcentaje(){
//        
//    }
	
	private String obtenerNombrePlan(String msisdn) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
	public List<PlanMultimediaVO> consultaTraficoExcedido(String msisdn) throws AppMobileException {
	    //TODO: se llama a dos servicios, no queda claro el try/cach
	    TimeWatch watch = TimeWatch.start();
		List<PlanMultimediaVO> planesMultimedia = new ArrayList<PlanMultimediaVO>();
		try {

			Plan planActual = new Plan();

			LOGGER.info("Servicio de Planes, Endpoint: " + Config.getAppProperty("excedidosServiceEndPoint"));
			LOGGER.info("Servicio de planes, Metodo: consultarPlanActual , Input:[ movil: " + msisdn + " ]");

			planActual = EntelServices.getPlanInstanceService().getWsPlanesMovilPort().consultarPlanActual(msisdn);
			LOGGER.info("Tiempo:" + watch.time());
			watch.reset();
			String codigoPlan = planActual.getCodigoPlan();

			try {
				ConsultarXmlPlanesFullType cXmlpfType = new ConsultarXmlPlanesFullType();
				ResultadoConsultaXmlplanesfullType resultadoCXmlpfType = new ResultadoConsultaXmlplanesfullType();
				PlanFullType pfType = new PlanFullType();

				cXmlpfType.setCodbscs2(codigoPlan);
				cXmlpfType.setTipoPlan("");

				LOGGER.info("Servicio de xmlPlanFull, Endpoint: " + Config.getAppProperty("profileServiceEndPoint"));
				LOGGER.info("Servicio de xmlPlanFull, Metodo: consultaXmlPlanesFull , Input:[ codbscs2: " + codigoPlan
						+ " TipoPlan: ]");

				// OUTPUT DEL SERVICIO
				resultadoCXmlpfType = EntelServices.getProfileInstanceService().getClientePerfilServicePort()
						.consultaXmlPlanesFull(cXmlpfType);
				LOGGER.info("Tiempo:" + watch.time());
				pfType = resultadoCXmlpfType.getPlanFull();

				// PLANFULL =! NULL o EL CAMPO PLANEXCEDIDO = S
				if (resultadoCXmlpfType.getRespuesta().getCodigo()
						.equals(Constants.XMLPLANES_CODIGO_OK.getStringValue())) {
					if (pfType != null) {
						if (pfType.getPlanExcedido().equals(Constants.XMLPLANES_PLANEXCEDIDO.getStringValue())) {
							planesMultimedia = obtenerPlanesMultimedia(msisdn, pfType);
						} else {
							// TODO Implementar logica en caso de no ser
							// excedido
						}
					}
				} else {
					LOGGER.info("Servicio de xmlPlanFull Codigo error:"
							+ resultadoCXmlpfType.getRespuesta().getCodigo());
					LOGGER.info("Servicio de xmlPlanFull Desc error:"
							+ resultadoCXmlpfType.getRespuesta().getDescripcion());
					return null;
				}
				return planesMultimedia;
			} catch (Exception e) {
				LOGGER.error("Problema en la llamada a consulta de trafico excedido " + e.getMessage());
				LOGGER.error("Tiempo:" + watch.time());
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			LOGGER.error("Error: " + e.getMessage());
			LOGGER.error("Tiempo:" + watch.time());
			return null;
		}
	}

	@Override
	public String consultaNombrePlan(String msisdn) throws AppMobileException {
	    	TimeWatch watch = TimeWatch.start();
		String nombrePlan = "";

		LOGGER.info("Inicio consulta nombre plan para numero movil " + msisdn);
		try {
			Trafico service = EntelServices.getTraficoLocatorInstanceService().getTraficoPort();
			nombrePlan = service.obtenerPlan(msisdn, "SS");
			LOGGER.info("Tiempo:" + watch.time());
		} catch (Exception e) {
			LOGGER.error("consulta nombre plan, Input:[ movil: " + msisdn + " ] : " + e.getMessage());
			LOGGER.error("Tiempo:" + watch.time());
			e.printStackTrace();
		} finally {
			LOGGER.info("Fin consultando nombre plan para numero movil " + msisdn);
		}
		return nombrePlan;
	}

	public List<PlanMultimediaVO> obtenerPlanesMultimedia(String msisdn, PlanFullType pfType) {
	    TimeWatch watch = TimeWatch.start();
		List<PlanMultimediaVO> planesMultimedia = new ArrayList<PlanMultimediaVO>();
		PlanMultimediaVO planMultimedia = new PlanMultimediaVO();
		// CONDICION DE PRORRATEO
		planMultimedia.setCondProrrateo(pfType.getCondProrrateo());

		try {
			ConsultaPlanesMultimediaType cpmType = new ConsultaPlanesMultimediaType();
			ConsultaPlanesMultimediaResponseType cpmrType = new ConsultaPlanesMultimediaResponseType();
			ResponseConsultaPlanType responseConsultaPlanType = new ResponseConsultaPlanType();

			// PREFIJO_ENTEL
			String movilPref = getFormatoWorldNumber(msisdn);

			// INPUT DEL SERVICIO DATOSEXCEDIDOS
			HeaderInType headerInType = new HeaderInType();
			headerInType.setAppName(Constants.TRAFICOEXCEDIDO_APPNAME.getStringValue());
			headerInType.setUserName(Constants.TRAFICOEXCEDIDO_USERNAME.getStringValue());

			cpmType.setMovil(validaMsisdn(msisdn));
			cpmType.setHeaderIn(headerInType);

			LOGGER.info("Servicio de TraficoExcedidos, Endpoint: " + Config.getAppProperty("excedidosServiceEndPoint"));
			LOGGER.info("Servicio de TraficoExcedidos, Metodo: consultaPlanesMultimedia , Input:[ movil:" + movilPref
					+ " HeaderIn ( AppName: " + headerInType.getAppName() + " UserName: " + headerInType.getUserName()
					+ " ) ]");

			
			// Primero se verifica si es multidispositivo
			String msisdnPadre="";
			 if ( esMultiDispositivo( pfType.getTipoPlan(), pfType.getCodbscs2()) ){
    				
			    ConsultaFamiliaMultiDispositivoRequestType consultaFamiliaMultiDispositivoRequest = new ConsultaFamiliaMultiDispositivoRequestType();
			    RequestType value=new RequestType();
			    value.setMovil(msisdn);
			    consultaFamiliaMultiDispositivoRequest.setRequest(value);
			    
			    ConsultaFamiliaMultiDispositivoResponseType algo = EntelServices.getFamiliaMDService().getBILTPXConsultarFamiliaMultiDispositivoServicePort().consultaFamiliaMultiDispositivo(consultaFamiliaMultiDispositivoRequest );
			    int cantMoviles = algo.getResponse().getDetalle().getDetalleFamilia().size();
			    
			    for (int i=0;i<cantMoviles;i++){
				if (algo.getResponse().getDetalle().getDetalleFamilia().get(i).getClasificacionMovil().equalsIgnoreCase("PADRE")){
				    msisdnPadre=algo.getResponse().getDetalle().getDetalleFamilia().get(i).getMovil();
				    }
			    }			    		    
			    LOGGER.info("Plan MD movil actual:" + msisdn);
			    LOGGER.info("Plan MD padre del movil actual:" + msisdnPadre);			    
			}

			// LLAMADO AL METODO DEL SERVICIO
			if ( esMultiDispositivo( pfType.getTipoPlan(),pfType.getCodbscs2()) ){
				//Cambiamos el valor al padre y llena valores
				//cpmType.setMovil(validaMsisdn("56956683062"));
				cpmType.setMovil(validaMsisdn(msisdnPadre));
				
				//TODO Aqui se deberia usar otro servicio ojo
				cpmrType = EntelServices.getExcedidosInstanceService().getDatosExcedidosSoapPort()
					.consultaPlanesMultimedia(cpmType);
				responseConsultaPlanType = cpmrType.getReturn();

			}else{
			    cpmrType = EntelServices.getExcedidosInstanceService().getDatosExcedidosSoapPort()
					.consultaPlanesMultimedia(cpmType);
			    LOGGER.info("Tiempo:" + watch.time());
			    responseConsultaPlanType = cpmrType.getReturn();
			}
		
			if (responseConsultaPlanType.getCodigo().equals(Constants.PLANESMULTIMEDIA_EXCEDIDO_CODIGO_OK.getStringValue())) {
				DatosExcedidosType datosExcedidosType = new DatosExcedidosType();
				datosExcedidosType = responseConsultaPlanType.getDatosExcedidos();

				// INDICA SI EL PLAN ES EXCEDIDO.
				if (datosExcedidosType.getClasificacionPlan().equals(
						Constants.PLANESMULTIMEDIA_EXCEDIDO_PLANEXCEDIDO.getStringValue()) 
				    		||  esMultiDispositivo( pfType.getTipoPlan(),pfType.getCodbscs2())) {

					// FORMATEA FEHA Y CONVIERTE KB A MB
					String fecha = DateUtils.formatFecha(datosExcedidosType.getFechaReferencia());
					String fechaReferencia = DateUtils.formatFechaString(fecha, DateUtils.DIAMESYEARBE,
							DateUtils.ALDIAMES);
					String horaReferencia = DateUtils.formatFechaString(fecha, DateUtils.DIAMESYEARHORABE,
							DateUtils.ALASHORA);
					String fechaHoraReferencia = DateUtils.formatFechaString(fecha, DateUtils.DIAMESYEARHORABE,
							DateUtils.ALDIAMESHORA);
					String velocidadPlan = datosExcedidosType.getVelocidadPlan();
					Double cuotaTraficoUtil = Double
							.valueOf(Formato.isEmptyString(datosExcedidosType.getTotalTrafico()) ? "0.0"
									: datosExcedidosType.getTotalTrafico()) / 1024;
					Double valorTotalTrafico = getMegabytes(datosExcedidosType.getValorTotalTrafico());
					Double traficoExcedido = getMegabytes(datosExcedidosType.getTraficoExcedido());
					Double valorMBExcedido = 0.0;
					Double totalTrafico = getMegabytes(datosExcedidosType.getCuotaTraficoUtil());
					Double pConsumido = 100.0;
					Double pRoundConsumido = 100.0;
					Double consumido = cuotaTraficoUtil;

					// CALCULA EL PROCENTAJE
					if (cuotaTraficoUtil <= totalTrafico) {
						pConsumido = (cuotaTraficoUtil / totalTrafico) * 100;
						pRoundConsumido = pConsumido - (pConsumido % 10);
					} else {
						consumido = totalTrafico;
					}

					for (DetallePlanActualType responseList : pfType.getDetallePlaFull()) {
						if (Constants.TASACION_MBADI.getStringValue().equals(responseList.getNombreTasacion())) {
							valorMBExcedido = Double.valueOf(responseList.getValorTasacion()) * 60;
							break;
						}
					}

					// SETEAN planesMultimedia CON LA
					// RESPUESTA DE LOS SERVICIO
					planMultimedia.setCodigo(responseConsultaPlanType.getCodigo());
					planMultimedia.setHoraReferencia(horaReferencia);
					planMultimedia.setFechaHoraReferencia(fechaHoraReferencia);
					planMultimedia.setFechaReferencia(fechaReferencia);
					planMultimedia.setVelocidadPlan(velocidadPlan);
					planMultimedia.setCuotaTraficoUtil(Formato.formatUnDecimal(cuotaTraficoUtil));
					planMultimedia.setValorTotalTrafico(Formato.formatSinDecimal(valorTotalTrafico));
					planMultimedia.setTraficoExcedido(Formato.formatUnDecimal(traficoExcedido));
					planMultimedia.setValorMBExcedido(Config.getAppProperty(Constants.DOLLAR_SIGN.getStringValue())
							+ Formato.formatPuntos(String.valueOf(valorMBExcedido), Locale.GERMAN));
					planMultimedia.setTotalTrafico(Formato.formatUnDecimal(totalTrafico));
					planMultimedia.setClasificacionPlan(datosExcedidosType.getClasificacionPlan());
					planMultimedia.setCodigoPlanBSCS(datosExcedidosType.getCodigoPlanBSCS());
					planMultimedia.setNombrePlanBSCS(datosExcedidosType.getNombrePlanBSCS());
					planMultimedia.setDescPlanBSCS(datosExcedidosType.getDescPlanBSCS());
					planMultimedia.setConsumido(Formato.formatUnDecimal(consumido));
					planMultimedia.setpConsumido(Formato.formatUnDecimal(pConsumido));
					planMultimedia.setProundConsumido(Formato.formatPuntos(String.valueOf(pRoundConsumido),
							Locale.GERMAN));
					// return
					// planesMultimedia.add(planMultimedia);
					planesMultimedia.add(planMultimedia);
				} else {
					return null;
				}
			} else {
				LOGGER.error("Servicio de Datos Excedidos Codigo error:" + responseConsultaPlanType.getCodigo());
				LOGGER.error("Servicio de Datos Excedidos Desc error:" + responseConsultaPlanType.getDescCodigo());
				LOGGER.error("Tiempo:" + watch.time());
				return null;
			}
		} catch (Exception e) {
			LOGGER.error("No es posible obtener plan multimedia " + e.getMessage());
			LOGGER.error("Tiempo:" + watch.time());
			e.printStackTrace();
			return null;
		}

		return planesMultimedia;
	}
	
    private Double getMegabytesMMAG(String dato) {
        Double resultado;

        if (Formato.isEmptyString(dato)) {
            resultado = 0.0;
        } else {
            resultado = Double.valueOf(dato);
        }

        return resultado;
    }	

	private Double getMegabytes(String dato) {
		Double resultado;

		if (Formato.isEmptyString(dato)) {
			resultado = 0.0;
		} else {
			resultado = Double.valueOf(dato) / 1024;
		}

		return resultado;
	}

	private String validaMsisdn(String msisdn) {
		String movilPref = "";
		if (msisdn.length() == Integer.valueOf(Constants.LONGITUD_CONPREFIJO_MSISDN_ENTEL.getStringValue())) {
			movilPref = msisdn;
		} else if (msisdn.length() == Integer.valueOf(Constants.LONGITUD_SINPREFIJO_MSISDN_ENTEL.getStringValue())) {
			movilPref = Constants.PREFIJO_ENTEL.getStringValue() + msisdn;
		} else {
			LOGGER.error("Formato de movil invalido: " + msisdn);
			try {
				throw new Exception();
			} catch (Exception e) {
				LOGGER.error("INICIO: " + e.getMessage());
			}
		}
		return movilPref;
	}

	private String obtenerTrafico(String trafico) {
		StringTokenizer tokens = new StringTokenizer(trafico, "+");
		Double segundos = 0.0, minutos = 0.0;
		String seg, min;
		Double aux;

		String valor;
		int posicion;

		while (tokens.hasMoreTokens()) {
			valor = tokens.nextToken();

			posicion = valor.lastIndexOf(".");
			minutos = minutos + Integer.valueOf(valor.substring(0, posicion));
			segundos = segundos + Double.valueOf(valor.substring(posicion + 1, valor.length()));
		}

		aux = segundos / 60;
		minutos = minutos + aux;
		segundos = segundos % 60;

		if (minutos < 10)
			min = String.valueOf("0" + minutos.intValue());
		else
			min = String.valueOf(minutos.intValue());
		if (segundos < 10)
			seg = String.valueOf("0" + segundos.intValue());
		else
			seg = String.valueOf(segundos.intValue());

		return min + ":" + seg;
	}

	public String getFormatoWorldNumber(String msisdn) throws Exception {
		String movilPref;
		if (msisdn.length() == Integer.valueOf(Constants.LONGITUD_CONPREFIJO_MSISDN_ENTEL.getStringValue())) {
			movilPref = msisdn;
		} else if (msisdn.length() == Integer.valueOf(Constants.LONGITUD_SINPREFIJO_MSISDN_ENTEL.getStringValue())) {
			movilPref = Constants.PREFIJO_ENTEL.getStringValue() + msisdn;
		} else {
			LOGGER.error("Formato de movil invalido: " + msisdn);
			throw new Exception();
		}
		return movilPref;
	}

	private boolean esMultiDispositivo(String tipoPlan,String idPlan){
	    boolean esMulti=false;	    
	    String tipoPlanMD = Config.getAppProperty("tipoPlanMultiDispositivo") == null ? "30,31" : Config.getAppProperty("tipoPlanMultiDispositivo");
	    
	    if (tipoPlanMD.contains(tipoPlan))
		esMulti=true;
	    
	    //if ("1481,1482,1495".contains(idPlan))
		//esMulti=true;
	    
	    return esMulti;
	}

    
	/**
	 * TODO Optimizacion de codigo stanb-by
	 */
	// public List<PlanMultimediaVO> consultaPlanMultimedia(String msisdn) {
	// ConsultaPlanesMultimediaType cpmType = new
	// ConsultaPlanesMultimediaType();
	// ConsultaPlanesMultimediaResponseType cpmrType = new
	// ConsultaPlanesMultimediaResponseType();
	// ResponseConsultaPlanType responseConsultaPlanType = new
	// ResponseConsultaPlanType();
	// PlanMultimediaVO planMultimedia = new PlanMultimediaVO();
	// List<PlanMultimediaVO> planesMultimedia = new
	// ArrayList<PlanMultimediaVO>();
	//
	// // INPUT DEL SERVICIO DATOSEXCEDIDOS
	// HeaderInType headerInType = new HeaderInType();
	// headerInType.setAppName(Constants.TRAFICOEXCEDIDO_APPNAME.getStringValue());
	// headerInType.setUserName(Constants.TRAFICOEXCEDIDO_USERNAME.getStringValue());
	//
	// cpmType.setMovil(validaMsisdn(msisdn));
	// cpmType.setHeaderIn(headerInType);
	//
	// LOGGER.info("Servicio de TraficoExcedidos, Endpoint: " +
	// Config.getAppProperty("excedidosServiceEndPoint"));
	// LOGGER.info("Servicio de TraficoExcedidos, Metodo: consultaPlanesMultimedia , Input:[ movil:"
	// + msisdn
	// + " HeaderIn ( AppName: " + headerInType.getAppName() + " UserName: " +
	// headerInType.getUserName()
	// + " ) ]");
	//
	// // LLAMADO AL METODO DEL SERVICIO
	// cpmrType =
	// EntelServices.getExcedidosInstanceService().getDatosExcedidosSoapPort()
	// .consultaPlanesMultimedia(cpmType);
	// responseConsultaPlanType = cpmrType.getReturn();
	//
	// // RESPUESTA OK
	// if
	// (responseConsultaPlanType.getCodigo().equals(Constants.PLANESMULTIMEDIA_EXCEDIDO_CODIGO_OK.getStringValue()))
	// {
	// DatosExcedidosType datosExcedidosType = new DatosExcedidosType();
	// datosExcedidosType = responseConsultaPlanType.getDatosExcedidos();
	//
	// // INDICA SI EL PLAN ES EXCEDIDO.
	// if (datosExcedidosType.getClasificacionPlan().equals(
	// Constants.PLANESMULTIMEDIA_EXCEDIDO_PLANEXCEDIDO.getStringValue())) {
	//
	// // FORMATEA FEHA Y CONVIERTE KB A MB
	// String fecha =
	// Formato.formatFecha(datosExcedidosType.getFechaReferencia());
	// String fechaReferencia = Formato.formatFechaString(fecha,
	// Formato.FORMAT_DiaMesYearBE,
	// Formato.FORMAT_AlDiaMes);
	// String horaReferencia = Formato.formatFechaString(fecha,
	// Formato.FORMAT_DiaMesYearHoraBE,
	// Formato.FORMAT_AlasHora);
	// String fechaHoraReferencia = Formato.formatFechaString(fecha,
	// Formato.FORMAT_DiaMesYearHoraBE,
	// Formato.FORMAT_AlDiaMesHora);
	// String velocidadPlan = datosExcedidosType.getVelocidadPlan();
	// Double cuotaTraficoUtil = Double
	// .valueOf(Formato.isEmptyString(datosExcedidosType.getTotalTrafico()) ?
	// "0.0"
	// : datosExcedidosType.getTotalTrafico()) / 1024;
	// Double valorTotalTrafico =
	// Double.valueOf(Formato.isEmptyString(datosExcedidosType
	// .getValorTotalTrafico()) ? "0.0" :
	// datosExcedidosType.getValorTotalTrafico()) / 1024;
	// Double traficoExcedido = Double
	// .valueOf(Formato.isEmptyString(datosExcedidosType.getTraficoExcedido()) ?
	// "0.0"
	// : datosExcedidosType.getTraficoExcedido()) / 1024;
	// Double valorMBExcedido = 0.0;
	// Double totalTrafico = Double
	// .valueOf(Formato.isEmptyString(datosExcedidosType.getCuotaTraficoUtil())
	// ? "0.0"
	// : datosExcedidosType.getCuotaTraficoUtil()) / 1024;
	// Double pConsumido = 100.0;
	// Double pRoundConsumido = 100.0;
	// Double consumido = cuotaTraficoUtil;
	//
	// // CALCULA EL PROCENTAJE
	// if (cuotaTraficoUtil <= totalTrafico) {
	// pConsumido = (cuotaTraficoUtil / totalTrafico) * 100;
	// pRoundConsumido = pConsumido - (pConsumido % 10);
	// } else {
	// consumido = totalTrafico;
	// }
	//
	// for (DetallePlanActualType responseList : pfType.getDetallePlaFull()) {
	// if
	// (Constants.TASACION_MBADI.getStringValue().equals(responseList.getNombreTasacion()))
	// {
	// valorMBExcedido = Double.valueOf(responseList.getValorTasacion()) * 60;
	// break;
	// }
	// }
	//
	// // SETEAN planesMultimedia CON LA
	// // RESPUESTA DE LOS SERVICIO
	// planMultimedia.setCodigo(responseConsultaPlanType.getCodigo());
	// planMultimedia.setHoraReferencia(horaReferencia);
	// planMultimedia.setFechaHoraReferencia(fechaHoraReferencia);
	// planMultimedia.setFechaReferencia(fechaReferencia);
	// planMultimedia.setVelocidadPlan(velocidadPlan);
	// planMultimedia.setCuotaTraficoUtil(Formato.formatUnDecimal(cuotaTraficoUtil));
	// planMultimedia.setValorTotalTrafico(Formato.formatSinDecimal(valorTotalTrafico));
	// planMultimedia.setTraficoExcedido(Formato.formatUnDecimal(traficoExcedido));
	// planMultimedia.setValorMBExcedido(Formato.formatPuntos(String.valueOf(valorMBExcedido),
	// Locale.GERMAN));
	// planMultimedia.setTotalTrafico(Formato.formatUnDecimal(totalTrafico));
	// planMultimedia.setClasificacionPlan(datosExcedidosType.getClasificacionPlan());
	// planMultimedia.setCodigoPlanBSCS(datosExcedidosType.getCodigoPlanBSCS());
	// planMultimedia.setNombrePlanBSCS(datosExcedidosType.getNombrePlanBSCS());
	// planMultimedia.setDescPlanBSCS(datosExcedidosType.getDescPlanBSCS());
	// planMultimedia.setConsumido(Formato.formatUnDecimal(consumido));
	// planMultimedia.setpConsumido(Formato.formatUnDecimal(pConsumido));
	// planMultimedia.setProundConsumido(Formato.formatPuntos(String.valueOf(pRoundConsumido),
	// Locale.GERMAN));
	// // return
	// // planesMultimedia.add(planMultimedia);
	// planesMultimedia.add(planMultimedia);
	// } else {
	// return null;
	// }
	// } else {
	// LOGGER.info("Servicio de Datos Excedidos Codigo error:" +
	// responseConsultaPlanType.getCodigo());
	// LOGGER.info("Servicio de Datos Excedidos Desc error:" +
	// responseConsultaPlanType.getDescCodigo());
	// return null;
	// }
	//
	// }
	//
	// public List<PlanFullType> consultaPlanFull(String codPlan) {
	// List<PlanFullType> planFull = new ArrayList<PlanFullType>();
	// ConsultarXmlPlanesFullType cXmlpfType = new ConsultarXmlPlanesFullType();
	// ResultadoConsultaXmlplanesfullType resultadoCXmlpfType = new
	// ResultadoConsultaXmlplanesfullType();
	// PlanFullType pfType = new PlanFullType();
	// PlanMultimediaVO planMultimedia = new PlanMultimediaVO();
	//
	// cXmlpfType.setCodbscs2(codPlan);
	// cXmlpfType.setTipoPlan("");
	//
	// LOGGER.info("Servicio de xmlPlanFull, Endpoint: " +
	// Config.getAppProperty("profileServiceEndPoint"));
	// LOGGER.info("Servicio de xmlPlanFull, Metodo: consultaXmlPlanesFull , Input:[ codbscs2: "
	// + codPlan
	// + " TipoPlan: ]");
	//
	// resultadoCXmlpfType =
	// EntelServices.getProfileInstanceService().getClientePerfilServicePort()
	// .consultaXmlPlanesFull(cXmlpfType);
	// pfType = resultadoCXmlpfType.getPlanFull();
	//
	// if
	// (resultadoCXmlpfType.getRespuesta().getCodigo().equals(Constants.XMLPLANES_CODIGO_OK.getStringValue()))
	// {
	// if (pfType != null) {
	// if
	// (pfType.getPlanExcedido().equals(Constants.XMLPLANES_PLANEXCEDIDO.getStringValue()))
	// {
	// planMultimedia.setCondProrrateo(pfType.getCondProrrateo());
	//
	// }
	// }
	// }
	//
	// return planFull;
	// }
	//
	// @Override
	// public List<PlanMultimediaVO> consultaTraficoExcedido(String msisdn)
	// throws AppMobileException {
	// try {
	//
	// Plan planActual = new Plan();
	//
	// LOGGER.info("Servicio de Planes, Endpoint: " +
	// Config.getAppProperty("planServiceEndPoint"));
	// LOGGER.info("Servicio de planes, Metodo: consultarPlanActual , Input:[ movil: "
	// + msisdn + " ]");
	//
	// planActual =
	// EntelServices.getPlanInstanceService().getWsPlanesMovilPort().consultarPlanActual(msisdn);
	//
	// consultaPlanFull(planActual.getCodigoPlan());
	// formatPrefijoEntel();
	// consultaPlanMultimedia();
	//
	// return planesMultimedia;
	// } catch (Exception e) {
	// LOGGER.error("Error: " + e.getMessage());
	// return null;
	// }
	// }
	
	
	   
    /*public List<TrafficVO> consultaTrafico(String msisdn) throws AppMobileException {
        List<TrafficVO> trafico = new ArrayList<TrafficVO>();
        LOGGER.info("Inicio consulta trafico para numero movil " + msisdn);
        try {
            Trafico service = EntelServices.getTraficoLocatorInstanceService().getTraficoPort();
            String[] servicios = Constants.SERVICIOS_TRAFICO_ENTEL_PCS;
            TraficoResponse response = null;

            for (int i = 0; i < servicios.length; i++) {

                try {
                    response = service.obtenerTrafico(validaMsisdn(msisdn), servicios[i]);
                } catch (Exception e) {
                    LOGGER.error("consulta trafico, Input:[ movil: " + msisdn + " ] " + e.getMessage());
                    e.printStackTrace();
                    response = null;
                }
                if (response != null) {
                    TrafficVO traff = new TrafficVO();
                    traff.setTipoTrafico(servicios[i]);
                    traff.setOtros(response.getOtros());
                    traff.setPeriodo(response.getPeriodo().substring(0, 2) + "."
                            + response.getPeriodo().substring(3, 5) + "." + response.getPeriodo().substring(6, 10));

                    if (servicios[i].equals("TRAF")) {
                        traff.setTrafico(obtenerTrafico(response.getTrafico()));
                    } else if (servicios[i].equals("ISHOP")) {
                        traff.setTrafico(response.getTrafico());
                    } else {
                        traff.setTrafico(response.getTrafico());
                    }
                    trafico.add(traff);
                }
            }
        } catch (Exception e) {
            LOGGER.error("consulta trafico, Input:[ movil: " + msisdn + " ] : " + e.getMessage());
            e.printStackTrace();
        } finally {
            LOGGER.info("Fin consultando trafico para numero movil " + msisdn);
        }
        return trafico;
    }
*/
}
