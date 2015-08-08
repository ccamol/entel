/* Propiedad de Entel. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.bean.PlanesFullBean;
import com.epcs.bean.ResumenTraficoBean;
import com.epcs.billing.registrouso.delegate.TraficoDelegate;
import com.epcs.cliente.perfil.dao.PlanDAO;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

/**
 * Handler para el mensaje de Datos Excedidos a los usuarios de MiEntel v3
 * 
 * @author esuarez (TECNOVA) en nombre de Absalon Opazo (Servicio al Cliente,
 *         EntelPcs)
 * 
 */
public class DatosExcedidosHandler extends AbstractMensajeHandler {

	/**
	 * Logger para DatosExcedidosHandler
	 */
	private static final Logger LOGGER = Logger
			.getLogger(DatosExcedidosHandler.class);
	private PlanDelegate planDelegate = new PlanDelegate();
	private ResumenTraficoBean resumenTrafico;
	private TraficoDelegate traficoDelegate = new TraficoDelegate();

	public static final String TIPO_PLAN_EXCEDIDO = MiEntelProperties
			.getProperty("servicios.codigoRespuesta.tipoPlan.excedido");

	@Override
	public String getMensajeId() {
		return MiEntelProperties
				.getProperty("mensajesParaTi.servicios.traficoDatosExcedidos.id");
	}

	@Override
	protected MensajeParaTiBean resolverMensaje(Node nodeMensajes,
			ProfileWrapper profile) throws Exception {

		String mercado = ProfileWrapperHelper.getPropertyAsString(profile,
				"mercado");
		String numeroPcsSeleccionado = ProfileWrapperHelper
				.getPropertyAsString(profile, "numeroPcsSeleccionado");
		String autoAtencion = ProfileWrapperHelper.getPropertyAsString(profile,
				"aaa");
		MensajeParaTiBean json = new MensajeParaTiBean();
		Node mensaje = null;
		// Apuntar al mensaje correcto
		String mensajeBad = MensajesParaTiHelper
				.obtenerMensajeTipoBad("datosExcedidos");

		try {
			if (MiEntelBusinessHelper.isMercadoSuscripcion(mercado)) {
				resumenTrafico = this.traficoDelegate.getResumenTrafico(
						numeroPcsSeleccionado, autoAtencion);
				PlanesFullBean planesFullBean;
				planDelegate.setPlanDAO(new PlanDAO());

				planesFullBean = planDelegate.consultaXmlPlanesFull(
						resumenTrafico.getCodbscs2(), "");
				if (planesFullBean != null) {
					if (planesFullBean.getPlanExcedido().equals(
							TIPO_PLAN_EXCEDIDO)) {
						// Es excedido?
						String txtMensaje = "";
						String idContenido = MensajesParaTiHelper
								.getMensDatosExcedidos();

						mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(
								nodeMensajes, idContenido);
						json.setEstado("OK");
						String url = mensaje.getProperty("url").getValue()
								.getStringValue();
						txtMensaje = mensaje.getProperty("texto").getValue()
								.getStringValue();
						txtMensaje = txtMensaje.replaceAll("<url>", url);
						json.setValue(txtMensaje);

					} else {
						json.setEstado("HIDDEN");
						json.setValue(mensajeBad);
					}
				}
			}
		} catch (Exception e) {
			json.setEstado("HIDDEN");
			json.setValue(mensajeBad);
			LOGGER.error(mensajeBad);
		}

		return json;
	}

}