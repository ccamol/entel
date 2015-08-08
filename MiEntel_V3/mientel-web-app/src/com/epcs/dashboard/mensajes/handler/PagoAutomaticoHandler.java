/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.EstadoPagoAutomaticoBean;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.billing.pago.delegate.PagoAutomaticoDelegate;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import common.Logger;

/**
 * Handler para mensajes de Pago Automatico con Tarjeta (PAT)
 * 
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al cliente,
 *         EntelPcs)
 */
public class PagoAutomaticoHandler extends AbstractMensajeHandler {

	private PagoAutomaticoDelegate pagoAutomaticoDelegate;
	private static final Logger LOGGER = Logger
			.getLogger(PagoAutomaticoHandler.class);

	public PagoAutomaticoDelegate getPagoAutomaticoDelegate() {
		return pagoAutomaticoDelegate;
	}

	public void setPagoAutomaticoDelegate(
			PagoAutomaticoDelegate pagoAutomaticoDelegate) {
		this.pagoAutomaticoDelegate = pagoAutomaticoDelegate;
	}

	@Override
	public String getMensajeId() {
		return MensajesParaTiHelper.getIdPagoAutomatico();
	}

	@Override
	protected MensajeParaTiBean resolverMensaje(Node nodeMensajes,
			ProfileWrapper profile) throws Exception {
		MensajeParaTiBean json = new MensajeParaTiBean();
		Node mensaje = null;
		String mensajeBad = "";
		String txtMensaje = "";
		String idContenido = "";

		try {
			String msisdn = ProfileWrapperHelper.getPropertyAsString(profile,
					"numeroPcsSeleccionado");
			String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(
					profile, "numeroCuenta");

			EstadoPagoAutomaticoBean estadoPAT = this.pagoAutomaticoDelegate
					.consultarEstadoPAT(msisdn, numeroCuenta);

			if (estadoPAT != null) {
				if (estadoPAT.getEstado().equals("PAT NO INSCRITO")) {
					idContenido = MensajesParaTiHelper.getMensPagoAutomatico();
					mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(
							nodeMensajes, idContenido);
					String url = mensaje.getProperty("url").getValue()
							.getStringValue();
					txtMensaje = mensaje.getProperty("texto").getValue()
							.getStringValue().replaceAll("<url>", url);
					json.setEstado("OK");
					json.setValue(txtMensaje);
				} else {
					json.setEstado("HIDDEN");
					json.setValue(mensajeBad);
				}
			} else {
				json.setEstado("HIDDEN");
				json.setValue(mensajeBad);
			}
		} catch (DAOException de) {
			json.setEstado("BAD");
			json.setValue(mensajeBad);
			LOGGER.error(mensajeBad, de);
		} catch (ServiceException se) {
			json.setEstado("BAD");
			json.setValue(mensajeBad);
			LOGGER.info(mensajeBad);
		} catch (Exception e) {
			json.setEstado("BAD");
			json.setValue(mensajeBad);
			LOGGER.error(mensajeBad, e);
		}

		return json;
	}
}
