package com.epcs.cliente.perfil.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;

import javax.faces.event.PhaseEvent;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import common.Logger;

public class OfertaBlindajeControllerV2 implements Serializable {

	private static final Logger logger = Logger.getLogger(OfertaBlindajeControllerV2.class);

	private static final String URL_CONSULTAR_OFERTA_DISPONIBLE_V2 = MiEntelProperties.getProperty("parametros.blindaje.urlCargarOfertaV2");

	private String resultado = "";

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	private String crearMarcaOfertaV2() {
		String respuesta = "";
		ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
		try {
			String movil = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");

			URL url = new URL(URL_CONSULTAR_OFERTA_DISPONIBLE_V2 + "?msisdn=" + movil);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

			String entrada = "";
			if ((entrada = br.readLine()) != null) {
				respuesta = JsonHelper.toJsonResponse(entrada);
			}

		} catch (Exception e) {
			logger.error(e);
		}
		return respuesta;
	}

	public void initBlindaje(PhaseEvent event) {
		logger.info("Buscando ofertas...");
		resultado = crearMarcaOfertaV2();
		logger.info("El resultado es: " + resultado);
	}

}
