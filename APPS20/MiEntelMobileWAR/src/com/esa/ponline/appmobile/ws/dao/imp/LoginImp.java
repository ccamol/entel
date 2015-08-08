/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao.imp;

import org.apache.log4j.Logger;

import com.epcs.erp.seguridad.ERPGestionDeSeguridadServicePortType;
import com.epcs.erp.seguridad.types.AutenticarUsuarioOrqType;
import com.epcs.erp.seguridad.types.ResultadoAutenticarUsuarioType;
import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.exceptions.WSDAOException;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.util.TimeWatch;
import com.esa.ponline.appmobile.ws.EntelServices;
import com.esa.ponline.appmobile.ws.dao.ILoginDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class LoginImp implements ILoginDAO {

	private static final Logger LOGGER = Logger.getLogger(LoginImp.class);

	@Override
	public boolean validaLogin(String key) throws AppMobileException {
	    	TimeWatch watch = TimeWatch.start();
		String[] datos = key.split(",");
		LOGGER.info("Inicio validacion numero movil " + datos[0]);
		ERPGestionDeSeguridadServicePortType port = null;
		try {
			port = EntelServices.getERPGestionDeSeguridadService().getERPGestionDeSeguridadServicePort();

			if (port == null) {
				throw new WSDAOException("No hay cliente de servicios disponible");
			}

			AutenticarUsuarioOrqType request = new AutenticarUsuarioOrqType();
			ResultadoAutenticarUsuarioType response;

			request.setMsisdn(datos[0]);
			request.setRut(datos[1]);
			request.setClave(datos[2]);

			response = port.autenticarUsuarioOrq(request);
			LOGGER.info("Tiempo:" + watch.time());
			if (response == null) {
				throw new WSDAOException("autenticarUsuarioOrq: Servicio no entrega resultado");
			}

			String codigoRespuesta = response.getRespuesta().getCodigo();
			String descripcionRespuesta = response.getRespuesta().getDescripcion();

			LOGGER.info("Servicio: " + Config.getAppProperty("erpGestionDeSeguridadServiceEndPoint"));
			LOGGER.info("codigoRespuesta " + codigoRespuesta);
			LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

			if (isEmptyString(codigoRespuesta)) {
				throw new WSDAOException("autenticarUsuarioOrq: Servicio no responde con codigoRespuesta");
			}

			// TODO Config properties
			if (response.getRespuesta().getCodigo().equalsIgnoreCase("0000")) {
				LOGGER.info("Verificacion correcta de las credenciales del usuario.");
				LOGGER.info("Codigo Response Service: " + response.getRespuesta().getCodigo());
				LOGGER.info("Descripcion Response Service: " + response.getRespuesta().getDescripcion());
				return true;
			} else {
				LOGGER.error("No ha sido posible la verificacion de las credenciales del usuario");
				LOGGER.error("Servicio: " + Config.getAppProperty("erpGestionDeSeguridadServiceEndPoint"));
				LOGGER.error("Codigo Response Service: " + response.getRespuesta().getCodigo());
				LOGGER.error("Descripcion Response Service: " + response.getRespuesta().getDescripcion());
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("Problema al validar login para msisdn: [" + datos[0] + "] " + e.getMessage());
			LOGGER.error("Servicio: " + Config.getAppProperty("erpGestionDeSeguridadServiceEndPoint"));
			LOGGER.error("Tiempo:" + watch.time());
			e.printStackTrace();
		}
		return false;
	}

	private boolean isEmptyString(String str) {
		return str == null || str.trim().equals("");
	}
}
