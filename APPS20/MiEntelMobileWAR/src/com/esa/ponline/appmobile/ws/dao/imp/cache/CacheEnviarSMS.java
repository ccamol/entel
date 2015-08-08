package com.esa.ponline.appmobile.ws.dao.imp.cache;

import org.apache.log4j.Logger;

import com.esa.ponline.appmobile.util.security.PostOnDemmand;
import com.google.common.cache.CacheLoader;

public class CacheEnviarSMS extends CacheLoader<String, String> {

	private static final Logger LOGGER = Logger.getLogger(CacheEnviarSMS.class);

	@Override
	public String load(String msisdn) throws Exception {
		String respuestaLogica = null;

		if (estaEnListaBlanca(msisdn)) {
			LOGGER.info("Numero en lista blanca: " + msisdn);
			respuestaLogica = "0000";
		} else {
			//respuestaLogica = PostOnDemmand.post(Config.getAppProperty("url.solicitudClave.mientel"), msisdn);
			//LOGGER.info("Se efectua post a: " + (Config.getAppProperty("url.solicitudClave.mientel")));
			respuestaLogica = PostOnDemmand.post("http://mipcs.entelpcs.com/mipcs2/enviarClaveSitioMovil.do", msisdn);
			LOGGER.info("Se efectua post a: http://mipcs.entelpcs.com/mipcs2/enviarClaveSitioMovil.do");

			if (respuestaLogica.equals("0000")) {
				LOGGER.info("Servicio responde ok con codigo: " + respuestaLogica);
			} else {
				LOGGER.error("No es posible enviar sms.");
				LOGGER.error("Codigo del servicio que especifica este problema: " + respuestaLogica);
			}
		}
		return respuestaLogica;
	}

	private boolean estaEnListaBlanca(String msisdn) {
		boolean status = false;

		String numeros="42142571,42142561,42142552,"+
				"42142543,42142538,42142523,42142517,42142514,78779800,"+
				"56302588,62000720,62000347,81999648,81999649,42142543,"+
				"42142514,42142552,42142561,42142538,42142517,42142523,"+
				"78779614,42142571,42142561,42142552,42142543,42142538,"+
				"42142523,42142517,42142514,42142568,42142558,42142554,"+
				"42142507,42142505,42142502,42142501,42142562,67961158,77467726";

		if (numeros.contains(msisdn)) {
			status = true;
		}

		return status;
	}
}
