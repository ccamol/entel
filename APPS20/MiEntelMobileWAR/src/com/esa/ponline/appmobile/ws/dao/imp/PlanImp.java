/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao.imp;

import org.apache.log4j.Logger;

import cl.i2b.ws22.Exception_Exception;
import cl.i2b.ws22.Plan;
import cl.i2b.ws22.WsPlanesMovil;

import com.epcs.cliente.perfil.ClientePerfilServicePortType;
import com.epcs.cliente.perfil.types.ConsultarPlanType;
import com.epcs.cliente.perfil.types.ConsultarXmlPlanesFullType;
import com.epcs.cliente.perfil.types.DetallePlanActualType;
import com.epcs.cliente.perfil.types.PlanFullType;
import com.epcs.cliente.perfil.types.ResultadoConsultaXmlplanesfullType;
import com.epcs.cliente.perfil.types.ResultadoConsultarPlanType;
import com.esa.billing.orderingprod.consultarfamiliamultidispositivo.BILTPXConsultarFamiliaMultiDispositivoServiceFaultMessage;
import com.esa.billing.orderingprod.consultarfamiliamultidispositivo.request.ConsultaFamiliaMultiDispositivoRequestType;
import com.esa.billing.orderingprod.consultarfamiliamultidispositivo.request.RequestType;
import com.esa.billing.orderingprod.consultarfamiliamultidispositivo.response.ConsultaFamiliaMultiDispositivoResponseType;
import com.esa.marketsales.salesportal.t.consultarcupodisponiblemdonlinesga.ConsultarCupoDisponibleServiceFaultMessage;
import com.esa.marketsales.salesportal.t.consultarcupodisponiblemdonlinesga.types.ConsultaCupoDisponibleRequestType;
import com.esa.marketsales.salesportal.t.consultarcupodisponiblemdonlinesga.types.ConsultaCupoDisponibleResponseType;
import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.util.Formato;
import com.esa.ponline.appmobile.util.TimeWatch;
import com.esa.ponline.appmobile.vo.plan.InfoPlanMMExcededVO;
import com.esa.ponline.appmobile.vo.plan.PlanVO;
import com.esa.ponline.appmobile.vo.plan.PlanesVO;
import com.esa.ponline.appmobile.ws.EntelServices;
import com.esa.ponline.appmobile.ws.dao.IPlanDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA) 
 * version 1.0.0 
 * date 15-09-2014
 */

public class PlanImp implements IPlanDAO {

	private static final Logger LOGGER = Logger.getLogger(PlanImp.class);

	@Override
	public PlanesVO obtienePlanActual(String msisdn, String mercado, String aaa, String cuenta) {
	    //TODO: Y el try catch
	    	TimeWatch watch = TimeWatch.start();
		PlanesVO planes = new PlanesVO();
		String codigoPlan = null;
		String cantCupos;
		String msisdnPadre="";
		ConsultarPlanType consPlanType = new ConsultarPlanType();
		consPlanType.setMsisdn(msisdn);

		ResultadoConsultarPlanType responseConsultarPlanType = setConnectionProfileService().consultarPlan(consPlanType);
		LOGGER.info("Tiempo:" + watch.time());
		LOGGER.info("##### PARAMETROS DEL SERVICIO #####");
		LOGGER.info("MSISDN [" + msisdn + "]");
		LOGGER.info("MERCADO [" + mercado + "]");
		LOGGER.info("AAA [" + aaa + "]");
		LOGGER.info("CUENTA [" + cuenta + "]");
		LOGGER.info("### FIN PARAMETROS DEL SERVICIO ###");

		if (responseConsultarPlanType.getRespuesta().getCodigo().equals("0000")) {
			codigoPlan = responseConsultarPlanType.getInformacionPlan().getCdgPlanBscs();
			LOGGER.info("Codigo de plan actual [" + codigoPlan + "]");
			LOGGER.info("Categoria plan actual [" + responseConsultarPlanType.getInformacionPlan().getCatPlan() + "]");
		} else if (responseConsultarPlanType.getRespuesta().getCodigo().equals("0002")) {
			LOGGER.error(responseConsultarPlanType.getRespuesta().getCodigo());
			LOGGER.error(responseConsultarPlanType.getRespuesta().getDescripcion());
		}
		ResultadoConsultaXmlplanesfullType responseConsXMLPFType = new ResultadoConsultaXmlplanesfullType();
		responseConsXMLPFType = consultaXMLPlanesFull(codigoPlan, "");

		if (responseConsXMLPFType.getRespuesta().getCodigo().equals("0000")) {
			LOGGER.info("Tipo Plan: " + "[" + responseConsXMLPFType.getPlanFull().getTipoPlan() + "]");
			PlanFullType pfType = consultaPlanFullType(responseConsXMLPFType);
			pfType = responseConsXMLPFType.getPlanFull();
			if (pfType != null) {
				planes.setInfoPlanMMExcedido(fillInfoPlanMM(pfType));
				planes.setObjPlan(consultarPlanActualPxSoap(msisdn, codigoPlan, mercado));
				
				 if ( esMultiDispositivo( pfType.getTipoPlan(), pfType.getCodbscs2()) ){
					 //consulta servicio para saber los padres e hijos del numero
					 ConsultaFamiliaMultiDispositivoRequestType consultaFamiliaMultiDispositivoRequest = new ConsultaFamiliaMultiDispositivoRequestType();
					    RequestType value=new RequestType();
					    value.setMovil(msisdn);
					    consultaFamiliaMultiDispositivoRequest.setRequest(value);
					    
					    ConsultaFamiliaMultiDispositivoResponseType familiaMD;
						try {
							familiaMD = EntelServices.getFamiliaMDService().getBILTPXConsultarFamiliaMultiDispositivoServicePort().consultaFamiliaMultiDispositivo(consultaFamiliaMultiDispositivoRequest );
							 int cantMoviles = familiaMD.getResponse().getDetalle().getDetalleFamilia().size();
							 for (int i=0;i<cantMoviles;i++){
									if (familiaMD.getResponse().getDetalle().getDetalleFamilia().get(i).getClasificacionMovil().equalsIgnoreCase("PADRE")){
									    msisdnPadre=familiaMD.getResponse().getDetalle().getDetalleFamilia().get(i).getMovil();
									}
							 }
							 
							 
						} catch (BILTPXConsultarFamiliaMultiDispositivoServiceFaultMessage e) {
							LOGGER.error("No es posible obtener FamiliaMultiDispositivo " + e.getMessage());
							LOGGER.error("Tiempo:" + watch.time());
							
							e.printStackTrace();
						}
						
						// si el numero es padre consulta las lineas disponibles para mostrar
					if (msisdnPadre.contains(msisdn)){
						ConsultaCupoDisponibleRequestType consultaCupoDisponibleRequest = new ConsultaCupoDisponibleRequestType();
					    consultaCupoDisponibleRequest.setDispositivoPadre(msisdn);
					    consultaCupoDisponibleRequest.setCuentaBSCS(cuenta);
					    consultaCupoDisponibleRequest.setCodigoUsuario("MIENTEL");
					    
					    ConsultaCupoDisponibleResponseType cupoDisponible;
						try {
							cupoDisponible = EntelServices.getCuposDisponiblesMDService().getConsultarCupoDisponibleServicePort().consultarCupo(consultaCupoDisponibleRequest);
							 if (cupoDisponible.getMensajeSalida().getCodigo().equals("3")){
								 cantCupos = "0";
							 }else{
								 cantCupos = String.valueOf(cupoDisponible.getCantidadDisponibles());
							 }
							 planes.setLineasDisponiblees(cantCupos);
							
						} catch (ConsultarCupoDisponibleServiceFaultMessage e) {
							LOGGER.error("No es posible obtener consultaCupoDisponible " + e.getMessage());
							LOGGER.error("Tiempo:" + watch.time());
							e.printStackTrace();
						}
						
					   
					}
					
						
					    
				 }
			}else{
				//TODO set to null or another
//				planes.setInfoPlanMMExcedido(null);
//				planes.setObjPlan(null);
			}
		} else if(responseConsXMLPFType.getRespuesta().getCodigo().equals("0002")) {
			LOGGER.info("No posee plan full");
			planes.setObjPlan(consultarPlanActualPxSoap(msisdn, codigoPlan, mercado));
		} else {
			LOGGER.error("No es posible obtener detalle del plan");
			LOGGER.error("Codigo de respuesta: " + responseConsXMLPFType.getRespuesta().getCodigo());
			LOGGER.error("Descripcion de respuesta: " + responseConsXMLPFType.getRespuesta().getDescripcion());
		}
		return planes;
	}

	private PlanVO consultarPlanActualPxSoap(String msisdn, String codePlan, String mercado) {
	    	TimeWatch watch = TimeWatch.start();
		PlanesVO planForm = new PlanesVO();
		PlanVO newPlan = new PlanVO();
		Plan planResponse = new Plan();
		
		try {
			planResponse = setConnectionWsPlanesService().consultarPlanActual(msisdn);
			LOGGER.info("Tiempo:" + watch.time());
//			String fixCargoFijo = obtenerFixCargoFijoPlan(codigoPlan);
//			
//			if (fixCargoFijo == null) {
//				newPlan.setCargoFijo(myFormatter.format(Long.parseLong(planSvc.getCargofijo())).replace(",", "."));
//			} else {
				newPlan.setCargoFijo(Config.getAppProperty(Constants.DOLLAR_SIGN.getStringValue()) + 
						Formato.formatMoneyPuntos(Double.valueOf(planResponse.getCargofijo())));
//			}
			
			newPlan.setCodigoPlan(codePlan);
			newPlan.setDescripcion(planResponse.getDescripcion());
			newPlan.setIdFamiliaPlan(planResponse.getIdFamiliaPlan());
			
			// TODO fix temporal para plan 14
			//if(planResponse.getIdFamiliaPlan().equals("6")){
			//    newPlan.setIdFamiliaPlan("2");
			//}else{
			//   
			//}
			
			LOGGER.info("IdFamiliaPlan: " + "[" + newPlan.getIdFamiliaPlan() + "]");
			if (Constants.TASA_PLAN__EN_SEGUNDOS.getStringValue().equalsIgnoreCase(planResponse.getTasacion())) {
				if (Constants.TIPO_PLAN_FAMILIA.getStringValue().equalsIgnoreCase(planResponse.getIdFamiliaPlan())) {
					newPlan.setMinOn(planResponse.getMinOn());
					newPlan.setMinOff(planResponse.getMinOff());
				} else {
					newPlan.setMinOn(obtenerMinutos(planResponse.getMinOn()));
					newPlan.setMinOff(obtenerMinutos(planResponse.getMinOff()));
				}
			} else {
				newPlan.setMinOn(planResponse.getMinOn());
				newPlan.setMinOff(planResponse.getMinOff());
			}
			newPlan.setValMinON(obtenerValorMinAdicional(planResponse.getValMinON()));
			newPlan.setValMinOFF(obtenerValorMinAdicional(planResponse.getValMinOFF()));
			newPlan.setValMinOTRO(obtenerValorMinAdicional(planResponse.getValMinOTRO()));
			newPlan.setTipoPlan(planResponse.getTipoPlan());
			
			
			
//			newPlan.setTipoPlan(mercado);
			
			//TODO Validacion plan 14 a 13 para fase qa
//			if (planResponse.getTipoPlan().equals("14")) {
//			    newPlan.setTipoPlan("13");
//            } else {
//                newPlan.setTipoPlan(planResponse.getTipoPlan());
//            }
			
			
			planForm.setObjPlan(newPlan);
		} catch (Exception_Exception e) {
			LOGGER.error("No es posible consultar plan actual para MSISDN ["+ msisdn + "]" + e.getMessage());
			LOGGER.error("Tiempo:" + watch.time());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("No es posible consultar plan actual para MSISDN ["+ msisdn + "]" + e.getMessage());
			LOGGER.error("Tiempo:" + watch.time());
			e.printStackTrace();
		}
		return newPlan;	
	}

	private String obtenerMinutos(String minutos) {
		int minTotal = 0;
		if (minutos != null && minutos.trim().length() > 0)
			minTotal = (Integer.parseInt(minutos) / Integer.parseInt(Constants.TASACION_PLANES_SEGUNDOS.getStringValue()));
		return String.valueOf(minTotal);
	}
	
	private String obtenerValorMinAdicional(String valor) {
		int valorTotal = 0;
		if (valor != null && valor.trim().length() > 0)
			valorTotal = Math.round((Float.parseFloat(valor) * Integer.parseInt(Constants.TASACION_PLANES_SEGUNDOS.getStringValue())));
		return Config.getAppProperty(Constants.DOLLAR_SIGN.getStringValue()) + String.valueOf(valorTotal);
	}


//	private String obtenerFixCargoFijoPlan(String idPlan) {
//		String cargoFijo = null;
//
//		try {
//			ISortableFilterablePagedList<Node> result = MobilePortletHelper.getContentSearch("idContenido", "fixCargoFijoPlanes");
//        Iterator<Node> it = result.iterator();
//
//        while (it.hasNext()) {
//        	Node n = it.next();
//        	
//        	if (n.getName().equals(idPlan)) {
//        		cargoFijo = n.getProperty("monto").getValue().getStringValue();
//        		break;
//        	}
//        }
//	} catch (RepositoryException e) {
//		LOGGER.error(new StringBuffer("An exception ocurred while ")
//			.append("executing PlanesController.obtenerFixCargoFijoPlan(). Exception. Details: ")
//			.append(e.getMessage()).toString(), e);
//	} catch (Exception e) {
//		LOGGER.error(new StringBuffer("An exception ocurred while ")
//			.append("executing PlanesController.obtenerFixCargoFijoPlan(). Exception. Details: ")
//			.append(e.getMessage()).toString(), e);
//	}
//	
//	return cargoFijo;
//}	

	// TODO Config properties
	private InfoPlanMMExcededVO fillInfoPlanMM(PlanFullType pfType) {
		if (pfType.getPlanExcedido().equals("S")
				&& (pfType.getTipoPlan().equals("13") || pfType.getTipoPlan().equals("14") 
					|| pfType.getTipoPlan().equals("31"))) {
			InfoPlanMMExcededVO infoPlanMM = new InfoPlanMMExcededVO();
			infoPlanMM.setTipoPlan("0");
			// CONDICIONES COMERCIALES
			String condicionComercial = "";
			String titulo = "";
			try {
				String value = "";
				if (pfType.getTipoPlan().equals("13")) {
					value = "tipoplanmultimedia13";
				} else if (pfType.getTipoPlan().equals("14")) {
					value = "tipoplanmultimedia14";
				}
				// condicionComercial =
				// MobilePortletHelper.getNodePropertyAsString("idContenido",
				// value, "html");
				// titulo =
				// MobilePortletHelper.getNodePropertyAsString("idContenido",
				// value, "titulo");
			} catch (Exception e) {
				LOGGER.error("Error al obtener el contenido de las condiciones informacion de plan MM: ", e);
			}
			infoPlanMM.setCondicionComercial(condicionComercial);
			infoPlanMM.setTituloCondComercial(titulo);
			// TIPO DE PLAN
			
			//TODO Validacion plan 14 a 13 para fase qa
//			if (pfType.getTipoPlan().equals("14")) {
//			    infoPlanMM.setTipoPlan("13");
//            } else {
//                infoPlanMM.setTipoPlan(pfType.getTipoPlan());
//            }
			infoPlanMM.setTipoPlan(pfType.getTipoPlan());
			String totalMinutos ;
			try{
			totalMinutos = (!pfType.getTotalMinutos().equals("")) ? String.valueOf(Double.valueOf(pfType
					.getTotalMinutos()) / 60) : "0";
			}catch(Exception e){
				LOGGER.error("No se puso convertir total Minutos, valor en XML " + pfType.getTotalMinutos());
				totalMinutos = "0";
			}
			String totalMinutosAdicional = (!pfType.getTotalMinutosAdicional().equals("")) ? String.valueOf(Double
					.valueOf(pfType.getTotalMinutosAdicional()) / 60) : "0";
			String nombreTasacion = "";
			String valorTasacion = "";

			infoPlanMM.setDescIMovil(pfType.getDescIMovil());
			infoPlanMM.setTotalMinutos(Formato.formatSinDecimal(Double.valueOf(totalMinutos)));
			infoPlanMM.setTotalMinutosAdicional(Formato.formatSinDecimal(Double.valueOf(totalMinutosAdicional)));

			String idPlanesmmFullIlim = Config.getAppProperty("idPlanesMMFullIlimitados") == null ? "1476,1475,1474" : Config.getAppProperty("idPlanesMMFullIlimitados");
			if (idPlanesmmFullIlim.contains(pfType.getCodbscs2())){
			    infoPlanMM.setEsIlimitado(true);
		        LOGGER.info("Cliente es ILIMITADO: ");
			}else{
			    infoPlanMM.setEsIlimitado(false);
			}   
			
			
			
			for (DetallePlanActualType responseList : pfType.getDetallePlaFull()) {
			    	
			    	if (Constants.TASACION_SMSTD.getStringValue().equals(responseList.getNombreTasacion())) {
			    	    nombreTasacion = responseList.getNombreTasacion();
			    	    valorTasacion = String.valueOf(Double.valueOf(responseList.getValorTasacion()) * 60);			    	    
			    	    infoPlanMM.setSMSTdNombreTasacion(nombreTasacion);
			    	    infoPlanMM.setSMSTdValorTasacion(Formato.formatSinDecimal(Double.valueOf(valorTasacion)));
			    	}
			    	
				if (Constants.TASACION_SMSMMS.getStringValue().equals(responseList.getNombreTasacion())) {
					nombreTasacion = responseList.getNombreTasacion();
					valorTasacion = String.valueOf(Double.valueOf(responseList.getValorTasacion()) * 60);
					infoPlanMM.setSmsMMSNetNombreTasacion(nombreTasacion);
					infoPlanMM.setSmsMMSNetvalorTasacion(Formato.formatSinDecimal(Double.valueOf(valorTasacion)));
				}
				
				if (Constants.TASACION_MTTD.getStringValue().equals(responseList.getNombreTasacion())) {
					nombreTasacion = responseList.getNombreTasacion();
					valorTasacion = String.valueOf(Double.valueOf(responseList.getValorTasacion()) * 60);
					infoPlanMM.setMTTDNombreTasacion(nombreTasacion);
					infoPlanMM.setMTTDvalorTasacion(Formato.formatSinDecimal(Double.valueOf(valorTasacion)));
				}

				if (Constants.TASACION_MBADI.getStringValue().equals(responseList.getNombreTasacion())) {
					nombreTasacion = responseList.getNombreTasacion();
					valorTasacion = String.valueOf(Double.valueOf(responseList.getValorTasacion()) * 60);
					infoPlanMM.setMBADINombreTasacion(nombreTasacion);
					infoPlanMM.setMBADIvalorTasacion(Formato.formatSinDecimal(Double.valueOf(valorTasacion)));
				}

				if (Constants.TASACION_SMSADI.getStringValue().equals(responseList.getNombreTasacion())) {
					nombreTasacion = responseList.getNombreTasacion();
					valorTasacion = String.valueOf(Double.valueOf(responseList.getValorTasacion()) * 60);
					infoPlanMM.setSMSADINombreTasacion(nombreTasacion);
					infoPlanMM.setSMSADIvalorTasacion(Formato.formatSinDecimal(Double.valueOf(valorTasacion)));
				}

				if (Constants.TASACION_MMSADI.getStringValue().equals(responseList.getNombreTasacion())) {
					nombreTasacion = responseList.getNombreTasacion();
					valorTasacion = String.valueOf(Double.valueOf(responseList.getValorTasacion()) * 60);
					infoPlanMM.setMMSADINombreTasacion(nombreTasacion);
					infoPlanMM.setMMSADIvalorTasacion(Formato.formatSinDecimal(Double.valueOf(valorTasacion)));
				}
				
				//Fix: Validaciones planes FULL multimedia
				if (infoPlanMM.getEsIlimitado()){					
				    infoPlanMM.setTotalMinutos("Ilimitado*");
				}
				if (pfType.getTotalMinutos().equalsIgnoreCase("ilimitado")){
					infoPlanMM.setTotalMinutos("Ilimitado*");
				}
				//smsMMSNetvalorTasacion
//                if (Constants.TASACION_MMS.getStringValue().equals(responseList.getNombreTasacion())) {
//                    nombreTasacion = responseList.getNombreTasacion();
//                    valorTasacion = String.valueOf(Double.valueOf(responseList.getValorTasacion()) * 60);
//                    infoPlanMM.setMMSADINombreTasacion(nombreTasacion);
//                    infoPlanMM.setMMSADIvalorTasacion(Formato.formatSinDecimal(Double.valueOf(valorTasacion)));
//                }
			}
			return infoPlanMM;
		}
		return null;
	}

	private ResultadoConsultaXmlplanesfullType consultaXMLPlanesFull(String codPlan, String tipoPlan) {
	    	TimeWatch watch = TimeWatch.start();
		ResultadoConsultaXmlplanesfullType resultadoCXmlpfType = new ResultadoConsultaXmlplanesfullType();
		ConsultarXmlPlanesFullType cXmlpfType = new ConsultarXmlPlanesFullType();

		cXmlpfType.setCodbscs2(codPlan);
		cXmlpfType.setTipoPlan(tipoPlan);

		try{
		resultadoCXmlpfType = setConnectionProfileService().consultaXmlPlanesFull(cXmlpfType);
		    LOGGER.info("Tiempo:" + watch.time());
		}catch(Exception e){
		    LOGGER.error("Entrada: Codigo BSCS2 ["+cXmlpfType.getCodbscs2()+ "] Tipo Plan ["+cXmlpfType.getTipoPlan()+"]");
		    LOGGER.error("Tiempo:" + watch.time());
		    LOGGER.error("Error en servicio consultaXmlPlanesFull",e);
		}
		return resultadoCXmlpfType;
	}

	private PlanFullType consultaPlanFullType(ResultadoConsultaXmlplanesfullType in) {
		PlanFullType pfType = new PlanFullType();
		pfType = in.getPlanFull();

		return pfType;
	}

	private ClientePerfilServicePortType setConnectionProfileService() {

		ClientePerfilServicePortType port = null;
		try {
			port = EntelServices.getProfileInstanceService().getClientePerfilServicePort();
		} catch (EntelServicesLocatorException e) {
			LOGGER.error("No se ha podido iniciar el port " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("No es posible obtener el servicio ClientePerfilServicePortType " + e.getMessage());
			e.printStackTrace();
		}
		return port;
	}

	private WsPlanesMovil setConnectionWsPlanesService() {

		WsPlanesMovil port = null;
		try {
			port = EntelServices.getPlanInstanceService().getWsPlanesMovilPort();
		} catch (EntelServicesLocatorException e) {
			LOGGER.error("No se ha podido iniciar el port " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("No es posible obtener el servicio WsPlanesMovil " + e.getMessage());
			e.printStackTrace();
		}
		return port;
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
}
