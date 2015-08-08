/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao.imp;

import java.util.Locale;

import org.apache.log4j.Logger;

import com.epcs.billing.balance.BillingBalanceService;
import com.epcs.billing.balance.types.ConsultarFacturacionFullType;
import com.epcs.billing.balance.types.DetalleFacturaMesType;
import com.epcs.billing.balance.types.ResultadoConsultarFacturacionFullType;
import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.exceptions.ServiceException;
import com.esa.ponline.appmobile.exceptions.WSDAOException;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.util.Formato;
import com.esa.ponline.appmobile.util.TimeWatch;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.ws.EntelServices;
import com.esa.ponline.appmobile.ws.dao.IAccountDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA) 
 * version 1.0.0 
 * date 12-09-2014
 */

public class AccountImp implements IAccountDAO {

	private static final Logger LOGGER = Logger.getLogger(AccountImp.class);

	private static String[] meses = { "", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
			"Septiembre", "Octubre", "Noviembre", "Diciembre" };

	private BillingBalanceService setConnectionBillingBalanceService() {

		BillingBalanceService port = null;
		try {
			port = EntelServices.getFacturacionService().getBillingBalanceServicePort();
		} catch (EntelServicesLocatorException e) {
			LOGGER.error("No se ha podido iniciar el port " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("No es posible obtener el servicio BillingBalanceService " + e.getMessage());
			e.printStackTrace();
		}
		return port;
	}

	@Override
	public DetalleFacturaMesType obtenerEstadoCuenta(LoginVO in) throws WSDAOException, ServiceException {
	    	TimeWatch watch = TimeWatch.start();
		BillingBalanceService port = setConnectionBillingBalanceService();
		DetalleFacturaMesType datosFacturaMes = null;

		ConsultarFacturacionFullType request = new ConsultarFacturacionFullType();
		request.setMsisdn(in.getMsisdn());
		request.setRutTitular(in.getRut());
		// TODO: Config properties
		request.setIdSistema("USRWEB");

		try{
		ResultadoConsultarFacturacionFullType response = port.consultarFacturacionFull(request);

		if (response.getRespuesta().getCodigo().equalsIgnoreCase("0000")) {
			datosFacturaMes = response.getDetalleFacturaMes();
			if (datosFacturaMes.getEstadoMes().equals("1")) {
				datosFacturaMes.setEstadoMes("Pagado");
			} else {
				datosFacturaMes.setEstadoMes("No Pagado");
			}
			
			LOGGER.info(in.getMsisdn() + "|Servicio Estado Cuenta se encuentra:"+datosFacturaMes.getEstadoMes() );
        			LOGGER.info("Tiempo:" + watch.time());
			// return formatValues(datosFacturaMes);
			return formatValues(datosFacturaMes);
		} else {
			datosFacturaMes = new DetalleFacturaMesType();
			datosFacturaMes.setEstadoMes("nok");
        			LOGGER.error("Entrada: Msisdn->"+request.getMsisdn()+"<-RutTitular->"+
        				request.getRutTitular()+"<-idSistema->"+request.getIdSistema()+"<-");
			LOGGER.error(in.getMsisdn() + "|Servicio Estado Cuenta retorna:" + response.getRespuesta().getCodigo() );
        			LOGGER.error("Tiempo:" + watch.time());
			return datosFacturaMes;
		}
        	}catch(Exception e){
        	    datosFacturaMes = new DetalleFacturaMesType();
			datosFacturaMes.setEstadoMes("nok");
			LOGGER.error("Entrada: Msisdn->"+request.getMsisdn()+"<-RutTitular->"+
				request.getRutTitular()+"<-idSistema->"+request.getIdSistema()+"<-");
			LOGGER.error(in.getMsisdn() + "|Servicio Estado Cuenta fallo retorna:" + e.getMessage() );
			LOGGER.error("Tiempo:" + watch.time());
			return datosFacturaMes;
        	    
        	}
	}

	private DetalleFacturaMesType formatValues(DetalleFacturaMesType in) {
		DetalleFacturaMesType datosFacturaMes = new DetalleFacturaMesType();
		datosFacturaMes.setTotalActualMes(Config.getAppProperty("formatPrecio")+Formato.formatPuntos(in.getTotalActualMes(), Locale.GERMAN));
		datosFacturaMes.setSaldoAnteriorMes(Config.getAppProperty("formatPrecio")+Formato.formatPuntos(in.getSaldoAnteriorMes(), Locale.GERMAN));
		datosFacturaMes.setTotalPagoMes(Config.getAppProperty("formatPrecio")+Formato.formatPuntos(in.getTotalPagoMes(), Locale.GERMAN));
		datosFacturaMes.setEstadoMes(in.getEstadoMes());
		datosFacturaMes.setUrlImagenFactura(in.getUrlImagenFactura());

		String[] fechaPeriodoMesArray = in.getFechaPeriodoMes().split("/");
		datosFacturaMes.setFechaPeriodoMes(meses[Integer.parseInt(fechaPeriodoMesArray[0])] + " " + fechaPeriodoMesArray[1]);

		String[] fechaEmisionArray = in.getFechaEmisionMes().split("-");
		datosFacturaMes.setFechaEmisionMes(fechaEmisionArray[0] + 
				Config.getAppProperty(Constants.SEPARACION_FECHA.getStringValue()) +
//				meses[Integer.parseInt(fechaEmisionArray[1])] + 
				fechaEmisionArray[1] +
				Config.getAppProperty(Constants.SEPARACION_FECHA.getStringValue()) + 
				fechaEmisionArray[2]);

		String[] fechaVencimientoArray = in.getFechaVencimientoMes().split("/");
		datosFacturaMes.setFechaVencimientoMes(fechaVencimientoArray[0] + 
				Config.getAppProperty(Constants.SEPARACION_FECHA.getStringValue()) + 
//				meses[Integer.parseInt(fechaVencimientoArray[1])] + 
				fechaVencimientoArray[1] +
				Config.getAppProperty(Constants.SEPARACION_FECHA.getStringValue()) + 
				fechaVencimientoArray[2]);

		return datosFacturaMes;
	}

	private void obtenerCuentasPendientes() {

	}
}
