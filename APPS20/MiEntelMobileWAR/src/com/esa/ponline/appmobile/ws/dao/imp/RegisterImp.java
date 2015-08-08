/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao.imp;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.epcs.cliente.perfil.ClientePerfilServicePortType;
import com.epcs.cliente.perfil.types.ActualizarMisDatosType;
import com.epcs.cliente.perfil.types.ResultadoActualizarMisDatosType;
import com.epcs.cliente.perfil.types.ResultadoValidarMovilBuicType;
import com.epcs.cliente.perfil.types.ValidarMovilBuicType;
import com.epcs.erp.seguridad.ERPGestionDeSeguridadServicePortType;
import com.epcs.erp.seguridad.types.AutenticarUsuarioType;
import com.epcs.erp.seguridad.types.ResultadoAutenticarUsuarioType;
import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.util.TimeWatch;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.ws.EntelServices;
import com.esa.ponline.appmobile.ws.dao.IRegisterDAO;
import com.esa.ponline.appmobile.ws.dao.imp.cache.CacheEnviarSMS;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class RegisterImp implements IRegisterDAO {

	private static final Logger LOGGER = Logger.getLogger(RegisterImp.class);

	static LoadingCache<String, String> cacheEnviarSMS = CacheBuilder.newBuilder().
			maximumSize(Long.parseLong(Config.getAppProperty("maxSizeCacheEnviarSMS")))
			.expireAfterWrite(Long.parseLong(Config.getAppProperty("cacheEnviarSMS_Time")), 
					TimeUnit.SECONDS).build(new CacheEnviarSMS());

	@Override
	public String registrarUsuario(LoginVO usuarioVO) throws AppMobileException {
	    //TODO: no tiene try/catch
	    	TimeWatch watch = TimeWatch.start();
		ClientePerfilServicePortType port = null;
		try {
			port = EntelServices.getProfileInstanceService().getClientePerfilServicePort();
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			LOGGER.error("Servicio " + Config.getAppProperty("profileServiceEndPoint"));
			error.printStackTrace();
		}
		ActualizarMisDatosType actualizacionRequest = new ActualizarMisDatosType();
		ResultadoActualizarMisDatosType actualizacionResponse = null;
		String resp = null;
		String email = "";
		String emailDominio = "";

		String arrayEmail[] = usuarioVO.getEmail().split("@");

		if (arrayEmail != null && arrayEmail.length > 1) {
			email = arrayEmail[0];
			emailDominio = arrayEmail[1];
		}

		actualizacionRequest.setEmail(email);
		actualizacionRequest.setEmailDominio(emailDominio);
		actualizacionRequest.setMsisdn(usuarioVO.getMsisdn());
		actualizacionRequest.setRut(usuarioVO.getRut());
		actualizacionRequest.setEmailDirec2(email);
		actualizacionRequest.setEmailDominio2(emailDominio);

		actualizacionResponse = port.actualizarMisDatos(actualizacionRequest);

		LOGGER.info("##### PARAMETROS DEL USARIO A REGISTRAR #####");
		LOGGER.info("1.- MSISDN: " + actualizacionRequest.getMsisdn());
		LOGGER.info("2.- EMAIL: " + actualizacionRequest.getEmail());
		LOGGER.info("3.- EMAIL DOMINIO: " + actualizacionRequest.getEmailDominio());
		LOGGER.info("4.- EMAIL 2: " + actualizacionRequest.getEmailDirec2());
		LOGGER.info("5.- EMAIL DOMINIO 2: " + actualizacionRequest.getEmailDominio2());
		LOGGER.info("6.- RUT: " + actualizacionRequest.getRut().substring(0, 4)+"XXXXX");
		LOGGER.info("### FIN PARAMETROS DEL USARIO A REGISTRAR ###");
		LOGGER.info("Tiempo:" + watch.time());
		// forTest
		// String codigoRespuesta = "0000";
		// String descripcionRespuesta = "OK";
		// fi forTest

		// forTest
		// if
		// (!actualizacionResponse.getRespuesta().getCodigo().equalsIgnoreCase("0000"))
		// {
		// fi forTest
		if (actualizacionResponse.getRespuesta().getCodigo().equalsIgnoreCase("0000")) {
			LOGGER.info("Respuesta Codigo Servicio: [" + actualizacionResponse.getRespuesta().getCodigo()+"]");
			LOGGER.info("Respuesta Descripcion Servicio: [" + actualizacionResponse.getRespuesta().getDescripcion()+"]");
			LOGGER.info("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			LOGGER.info("Usuario Registrado");
			resp = "success";
		} else {
			resp = actualizacionResponse.getRespuesta().getCodigo();
			LOGGER.error("Se lanza error al registrar usuario en el servicio.");
			LOGGER.error("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			LOGGER.error("Respuesta Codigo Servicio: [" + actualizacionResponse.getRespuesta().getCodigo()+"]");
			LOGGER.error("Respuesta Descripcion Servicio: [" + actualizacionResponse.getRespuesta().getDescripcion()+"]");
		}
		return resp;
	}

	@Override
	public String enviarSMS(String msisdn) throws AppMobileException {
		String respuesta;
		try {

			LOGGER.info("Generando llamando al envio de SMS");
			respuesta = cacheEnviarSMS.get(msisdn);
		} catch (ExecutionException e) {
			respuesta = "9999";
			LOGGER.error("Error desconocido al llamar envioSMS");
		}

		return respuesta;
	}

	@Override
	public boolean existeRegistro(String msisdn) throws AppMobileException {
	    //TODO: falta try/catch
	    	TimeWatch watch = TimeWatch.start();
		ClientePerfilServicePortType port = null;
		try {
			port = EntelServices.getProfileInstanceService().getClientePerfilServicePort();
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			error.printStackTrace();
		}	 
			 
		ValidarMovilBuicType movil = new ValidarMovilBuicType();
		boolean resp = false;

		movil.setMsisdn(msisdn);
		ResultadoValidarMovilBuicType response = port.validarMovilBuic(movil);
		LOGGER.info("Tiempo:" + watch.time());
		if (response.getRespuesta().getCodigo().equals("0000")) {
			LOGGER.info("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			LOGGER.info("MSISDN: [" + msisdn + "]");
			LOGGER.info("Respuesta Codigo Servicio: [" + response.getRespuesta().getCodigo() + "]");
			LOGGER.info("Respuesta Descripcion Servicio: [" + response.getRespuesta().getDescripcion() + "]");
			resp = true;
		} else {
			LOGGER.info("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			LOGGER.info("MSISDN: [" + msisdn + "]");
			LOGGER.info("Habilitado para registro.");
			LOGGER.info("Respuesta Codigo Servicio: [" + response.getRespuesta().getCodigo() + "]");
			LOGGER.info("Respuesta Descripcion Servicio: [" + response.getRespuesta().getDescripcion() + "]");
		}
		return resp;
	}

	@Override
	public boolean validaDatosRegistro(String msisdn, String pin) throws AppMobileException {
	    //TODO: esta mal, try catch
	    	TimeWatch watch = TimeWatch.start();
		ERPGestionDeSeguridadServicePortType port = null;
		try {
			port = EntelServices.getERPGestionDeSeguridadService().getERPGestionDeSeguridadServicePort();
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			error.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Problema en servicio " + e.getMessage());
			e.printStackTrace();
		}
		boolean resp = false;

		AutenticarUsuarioType usuario = new AutenticarUsuarioType();

		usuario.setClave(pin);
		usuario.setMsisdn(msisdn);

		LOGGER.info("#### PARAMETROS DE INGRESO SERVICIO ####");
		LOGGER.info("Servicio: " + Config.getAppProperty("erpGestionDeSeguridadServiceEndPoint"));
		LOGGER.info("MSISDN: [" + msisdn + "]");
		LOGGER.info("#### FIN PARAMETROS DE INGRESO SERVICIO ####");

		ResultadoAutenticarUsuarioType response = port.autenticarUsuario(usuario);
		LOGGER.info("Tiempo:" + watch.time());

		if (response.getRespuesta().getCodigo().equalsIgnoreCase("0000")) {
			LOGGER.info("Servicio: " + Config.getAppProperty("erpGestionDeSeguridadServiceEndPoint"));
			LOGGER.info("MSISDN: [" + msisdn + "]");
			LOGGER.info("Respuesta Codigo Servicio: [" + response.getRespuesta().getCodigo() + "]");
			LOGGER.info("Respuesta Descripcion Servicio: [" + response.getRespuesta().getDescripcion() + "]");
			LOGGER.info("MSISDN & PIN Validos.");
			resp = true;
		} else {
			LOGGER.error("Servicio: " + Config.getAppProperty("erpGestionDeSeguridadServiceEndPoint"));
			LOGGER.error("MSISDN: [" + msisdn + "]");
			LOGGER.error("Codigo Response Service: " + response.getRespuesta().getCodigo() + "]");
			LOGGER.error("Respuesta Descripcion Servicio: [" + response.getRespuesta().getDescripcion() + "]");
		}
		return resp;
	}
}
