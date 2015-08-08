/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.epcs.cliente.perfil.ClientePerfilServicePortType;
import com.epcs.cliente.perfil.types.ConsultarPlanActualPPType;
import com.epcs.cliente.perfil.types.ConsultarPlanResumenCCType;
import com.epcs.cliente.perfil.types.ConsultarPlanResumenPPType;
import com.epcs.cliente.perfil.types.PlanActualPPType;
import com.epcs.cliente.perfil.types.ResPlanResumenCCType;
import com.epcs.cliente.perfil.types.ResPlanResumenPPType;
import com.epcs.cliente.perfil.types.ResultadoConsultarPlanActualPPType;
import com.epcs.cliente.perfil.types.ResultadoConsultarPlanResumenCCType;
import com.epcs.cliente.perfil.types.ResultadoConsultarPlanResumenPPType;
import com.esa.billing.billingcons.t.conssaldobonoextra.ConsSaldoBonoExtraServicePortType;
import com.esa.billing.billingcons.t.conssaldobonoextra.types.ConsultarSaldoParametersType;
import com.esa.billing.billingcons.t.conssaldobonoextra.types.ConsultarSaldoResponseType;
import com.esa.mediation.mediation.n.obtenertraficoindividualag.ObtenerTraficoIndividualAGPort;
import com.esa.mediation.mediation.n.obtenertraficoindividualag.request.ObtenerTraficoIndividualAGReq;
import com.esa.mediation.mediation.n.obtenertraficoindividualag.response.ObtenerTraficoIndividualAGRes;
import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.util.DateUtils;
import com.esa.ponline.appmobile.util.Formato;
import com.esa.ponline.appmobile.util.TimeWatch;
import com.esa.ponline.appmobile.ws.EntelServices;
import com.esa.ponline.appmobile.ws.dao.IMenuDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class MenuImp implements IMenuDAO {

	private static final Logger LOGGER = Logger.getLogger(MenuImp.class);

	@Override
	public List<PlanActualPPType> consultarPlanActualPP(String msisdn) throws AppMobileException {
	    	TimeWatch watch = TimeWatch.start();
		List<PlanActualPPType> planPP = new ArrayList<PlanActualPPType>();
		ResultadoConsultarPlanActualPPType response = new ResultadoConsultarPlanActualPPType();
		PlanActualPPType planActual;

		ClientePerfilServicePortType port = null;
		try {
			port = EntelServices.getProfileInstanceService().getClientePerfilServicePort();
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio: "
					+ Config.getAppProperty("profileServiceEndPoint") +" " + error.getMessage());
			error.printStackTrace();
		}

		ConsultarPlanActualPPType consulta = new ConsultarPlanActualPPType();
		consulta.setMsisdn(msisdn);

		try{
		response = port.consultarPlanActualPP(consulta);
		    LOGGER.info("Tiempo:" + watch.time());
		if (response.getRespuesta().getDescripcion().equalsIgnoreCase(Constants.OK.getStringValue())) {
			planActual = new PlanActualPPType();
			planActual.setIdTarifa(response.getPlanActualPP().getIdTarifa());
			planActual.setTipoPlan(response.getPlanActualPP().getTipoPlan());
			planActual.setFlagVisible(response.getPlanActualPP().getFlagVisible());
			planActual.setNombrePlan(response.getPlanActualPP().getNombrePlan());
			planActual.setDescripcionPlan(response.getPlanActualPP().getDescripcionPlan());
			planPP.add(planActual);

			LOGGER.info("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			LOGGER.info("MSISDN: [" + msisdn + "]");
			LOGGER.info("Codigo Response Service: " + response.getRespuesta().getCodigo());
			LOGGER.info("Descripcion Response Service: " + response.getRespuesta().getDescripcion());
		} else {
			planActual = new PlanActualPPType();
			planActual.setIdTarifa("");
			planActual.setTipoPlan("");
			planActual.setFlagVisible("");
			planActual.setNombrePlan("");
			planActual.setDescripcionPlan("");
			planPP.add(planActual);

			LOGGER.error("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
        			LOGGER.error("Entrada: Msisdn ->"+msisdn+"<-");
			LOGGER.error("Codigo Response Service: " + response.getRespuesta().getCodigo());
			LOGGER.error("Descripcion Response Service: " + response.getRespuesta().getDescripcion());
		}
        		
		}catch(Exception e){
		    planActual = new PlanActualPPType();
			planActual.setIdTarifa("");
			planActual.setTipoPlan("");
			planActual.setFlagVisible("");
			planActual.setNombrePlan("");
			planActual.setDescripcionPlan("");
			planPP.add(planActual);

			LOGGER.error("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			LOGGER.error("Entrada: Msisdn ->"+msisdn+"<-");
			LOGGER.error("Tiempo:" + watch.time());
			LOGGER.error("Mensaje error :"+e.getMessage());
		}
		return planPP;
	}
	
    //TODO CC3 saldoSecundario
    @Override
    public ConsultarSaldoResponseType consultarSaldoSecundario(String msisdn)
            throws AppMobileException {
        TimeWatch watch = TimeWatch.start();
        LOGGER.info("se inicia llamada a servicio SaldoBonoExtra (saldo secundario)");
        
        ConsultarSaldoResponseType consultarSaldoResponseType = new ConsultarSaldoResponseType();
        ConsSaldoBonoExtraServicePortType port2 = null;
        
//        try {
//            port2 = EntelServices.getConsSaldoBonoExtraServiceBindingQSService().
//                    getConsSaldoBonoExtraServiceBindingQSPort();
//            LOGGER.info("Tiempo:" + watch.time());
//            watch.reset();
//        } catch (EntelServicesLocatorException e) {
//            e.printStackTrace();
//        }
//        
//        ConsultarSaldoParametersType consultarSaldoRequest = new ConsultarSaldoParametersType();
//        consultarSaldoRequest.setMsisdn(Constants.PREFIJO_ENTEL.getStringValue()+msisdn);
//        //TODO saldoSecundario canalPromocional llevar a property 
//        consultarSaldoRequest.setCanalpromocion("ASDF");
//        
//        //TODO USER AGENT must be setted to works
//
//        try {
//            //TODO forzar error para visualizar objetos nulos
//            consultarSaldoResponseType = port2.consultarSaldo(consultarSaldoRequest);
//            LOGGER.info("Tiempo:" + watch.time());            
//        } catch (Exception e) {
//            consultarSaldoResponseType = new ConsultarSaldoResponseType();
//            LOGGER.error("Servicio: " + Config.getAppProperty("consultarSaldoSecundarioService"));
//            LOGGER.error("Entrada: Msisdn ["+msisdn+"]");
//            LOGGER.error("Tiempo:" + watch.time());
//            LOGGER.error("Mensaje error :"+e.getMessage());
//        }
        
        return consultarSaldoResponseType;
    }

    
    
	@Override
	public List<ResPlanResumenPPType> consultarPlanResumenPP(String msisdn) throws AppMobileException {
	    TimeWatch watch = TimeWatch.start();
		List<ResPlanResumenPPType> planResumenPP = new ArrayList<ResPlanResumenPPType>();
		ResultadoConsultarPlanResumenPPType responsePlanResumen = new ResultadoConsultarPlanResumenPPType();

		ResPlanResumenPPType resumenPlanActual = new ResPlanResumenPPType();

		ClientePerfilServicePortType port = null;

		LOGGER.info("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
		LOGGER.info("para consultar resumen de prepago");

		try {
		    port = EntelServices.getProfileInstanceService().getClientePerfilServicePort();
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			error.printStackTrace();
		}

		ConsultarPlanResumenPPType consultaResumen = new ConsultarPlanResumenPPType();
		consultaResumen.setMsisdn(msisdn);

		try {
		    responsePlanResumen = port.consultarPlanResumenPP(consultaResumen);

		    LOGGER.info("Tiempo:" + watch.time());

		if (responsePlanResumen.getRespuesta().getDescripcion().equalsIgnoreCase(Constants.OK.getStringValue())) {
			resumenPlanActual.setFechaExpiracion(DateUtils.formatoSeparacionFecha(responsePlanResumen
					.getResPlanResumenPP().getFechaExpiracion()));
			resumenPlanActual.setFechaExpiracionSaldoReservado(DateUtils
					.formatoSeparacionFechaReservado(responsePlanResumen.getResPlanResumenPP()
							.getFechaExpiracionSaldoReservado()));
			if (responsePlanResumen.getResPlanResumenPP().getFechaExpiracionSaldoReservado().length() >= 1) {
				LOGGER.info("PREPAGO MSISDN: [" + msisdn + "] | TIENE saldo reservado");
			} else {
				LOGGER.info("PREPAGO MSISDN: [" + msisdn + "] | NO TIENE saldo reservado");
			}
			resumenPlanActual.setNombrePlan(responsePlanResumen.getResPlanResumenPP().getNombrePlan());
			resumenPlanActual.setSaldo(Formato.formatPuntos(responsePlanResumen.getResPlanResumenPP().getSaldo(),
					Locale.GERMAN));
			resumenPlanActual.setSaldoReservado(Formato.formatPuntos(responsePlanResumen.getResPlanResumenPP()
					.getSaldoReservado().replace("-", ""), Locale.GERMAN));
			planResumenPP.add(resumenPlanActual);

			LOGGER.info("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			LOGGER.info("MSISDN: [" + msisdn + "]");
			LOGGER.info("Codigo Response Service: " + responsePlanResumen.getRespuesta().getCodigo());
			LOGGER.info("Descripcion Response Service: " + responsePlanResumen.getRespuesta().getDescripcion());
		} else {
			resumenPlanActual = new ResPlanResumenPPType();
			resumenPlanActual.setFechaExpiracion("");
			resumenPlanActual.setFechaExpiracionSaldoReservado("");
			resumenPlanActual.setNombrePlan("");
			resumenPlanActual.setSaldo("");
			resumenPlanActual.setSaldoReservado("");
			planResumenPP.add(resumenPlanActual);

			LOGGER.error("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			LOGGER.error("Entrada: Msisdn ->"+msisdn+"<-");
			LOGGER.error("Codigo Response Service: " + responsePlanResumen.getRespuesta().getCodigo());
			LOGGER.error("Descripcion Response Service: " + responsePlanResumen.getRespuesta().getDescripcion());
		}
		}catch(Exception e){
			resumenPlanActual = new ResPlanResumenPPType();
			resumenPlanActual.setFechaExpiracion("");
			resumenPlanActual.setFechaExpiracionSaldoReservado("");
			resumenPlanActual.setNombrePlan("");
			resumenPlanActual.setSaldo("");
			resumenPlanActual.setSaldoReservado("");
			planResumenPP.add(resumenPlanActual);

			LOGGER.error("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			LOGGER.error("Entrada: Msisdn ["+msisdn+"]");
			LOGGER.error("Tiempo:" + watch.time());
			LOGGER.error("Mensaje error :"+e.getMessage());
		}
		return planResumenPP;
	}

	@Override
	public ResPlanResumenPPType consultaSaldoPP(String msisdn) throws AppMobileException {
	    	TimeWatch watch = TimeWatch.start();
		ClientePerfilServicePortType port = null;

		LOGGER.info("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
		LOGGER.info("para consultar saldo prepago");

		try {
			port = EntelServices.getProfileInstanceService().getClientePerfilServicePort();
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			error.printStackTrace();
		}
		ResultadoConsultarPlanResumenPPType response = new ResultadoConsultarPlanResumenPPType();
		ResPlanResumenPPType resumenPlanActual = new ResPlanResumenPPType();

		ConsultarPlanResumenPPType consulta = new ConsultarPlanResumenPPType();
		consulta.setMsisdn(msisdn);

		try{
		response = port.consultarPlanResumenPP(consulta);
        		LOGGER.info("Tiempo:" + watch.time());
		if (response.getRespuesta().getDescripcion().equalsIgnoreCase(Constants.OK.getStringValue())) {
			resumenPlanActual.setFechaExpiracion(DateUtils.formatoSeparacionFecha(response.getResPlanResumenPP()
					.getFechaExpiracion()));
			resumenPlanActual.setNombrePlan(response.getResPlanResumenPP().getNombrePlan());
			resumenPlanActual.setSaldo(response.getResPlanResumenPP().getSaldo());

			LOGGER.info("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
        			LOGGER.info("Entrada: MSISDN [" + msisdn + "]");
			LOGGER.info("Codigo Response Service: " + response.getRespuesta().getCodigo());
			LOGGER.info("Descripcion Response Service: " + response.getRespuesta().getDescripcion());
		} else {
			resumenPlanActual.setFechaExpiracion("");
			resumenPlanActual.setNombrePlan("");
			resumenPlanActual.setSaldo("");

			LOGGER.error("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
        			LOGGER.error("Entrada: MSISDN [" + msisdn + "]");
			LOGGER.error("Codigo Response Service: " + response.getRespuesta().getCodigo());
			LOGGER.error("Descripcion Response Service: " + response.getRespuesta().getDescripcion());
		}
		}catch(Exception e){
		    resumenPlanActual.setFechaExpiracion("");
			resumenPlanActual.setNombrePlan("");
			resumenPlanActual.setSaldo("");

			LOGGER.error("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			LOGGER.error("Entrada: MSISDN [" + msisdn + "]");
			LOGGER.error("Tiempo:" + watch.time());
			LOGGER.error("Mensaje error :"+e.getMessage());
		}
		return resumenPlanActual;
	}

	@Override
	public ResPlanResumenCCType consultarPlanResumenCC(String msisdn) throws AppMobileException {
	    	TimeWatch watch = TimeWatch.start();
		ClientePerfilServicePortType port = null;

		LOGGER.info("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
		LOGGER.info("para consultar resumen de cuenta controlada");

		try {
			port = EntelServices.getProfileInstanceService().getClientePerfilServicePort();
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			error.printStackTrace();
		}
		ResultadoConsultarPlanResumenCCType response = new ResultadoConsultarPlanResumenCCType();
		ResPlanResumenCCType resumenPlanActual = new ResPlanResumenCCType();

		ConsultarPlanResumenCCType consulta = new ConsultarPlanResumenCCType();
		consulta.setAaa("1");
		consulta.setMsisdn(msisdn);

		try{
		response = port.consultarPlanResumenCC(consulta);
        		LOGGER.info("Tiempo:" + watch.time());
        
		if (response.getRespuesta().getDescripcion().equalsIgnoreCase(Constants.OK.getStringValue())) {
			resumenPlanActual.setFechaExpiracion(DateUtils.formatoSeparacionFecha(response.getResPlanResumenCC()
					.getFechaExpiracion()));
			resumenPlanActual.setNombrePlan(response.getResPlanResumenCC().getNombrePlan());
			resumenPlanActual.setSaldo(Formato.formatPuntos(response.getResPlanResumenCC().getSaldo(), Locale.GERMAN));

			LOGGER.info("Servicio: "+ Config.getAppProperty("profileServiceEndPoint"));
        			LOGGER.info("MSISDN: [" + msisdn + "] AAA [1] ");
			LOGGER.info("Codigo Response Service: " + response.getRespuesta().getCodigo());
			LOGGER.info("Descripcion Response Service: " + response.getRespuesta().getDescripcion());
		} else {
			resumenPlanActual.setFechaExpiracion("");
			resumenPlanActual.setNombrePlan("");
			resumenPlanActual.setSaldo("");

			LOGGER.error("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
        			LOGGER.error("Entrada: MSISDN [" + msisdn + "] AAA [1] ");
			LOGGER.error("Codigo Response Service: " + response.getRespuesta().getCodigo());
			LOGGER.error("Descripcion Response Service: " + response.getRespuesta().getDescripcion());
		}
		}catch(Exception e){
		    resumenPlanActual.setFechaExpiracion("");
			resumenPlanActual.setNombrePlan("");
			resumenPlanActual.setSaldo("");

			LOGGER.error("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			LOGGER.error("Entrada: MSISDN [" + msisdn + "] AAA [1] ");
			LOGGER.error("Tiempo:" + watch.time());
			LOGGER.error("Mensaje error :"+e.getMessage());
		}
		return resumenPlanActual;
	}
	
}