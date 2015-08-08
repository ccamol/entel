/* Propiedad de Entel. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * Handler para el mensaje de Bienvenida a los usuarios de MiEntel v3
 * 
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Servicio al Cliente, Entel
 *         Pcs)
 * 
 */
public class RecargaWebPayHandler extends AbstractMensajeHandler {
	
	/**
     * Logger para BienvenidaHandler
     */
    private static final Logger LOGGER = Logger
            .getLogger(RecargaWebPayHandler.class);

    @Override
    public String getMensajeId() {
        return MiEntelProperties
                .getProperty("mensajesParaTi.servicios.recargaWebPay.id");
    }

    @Override
    protected MensajeParaTiBean resolverMensaje(Node nodeMensajes,
            ProfileWrapper profile) throws Exception {

        MensajeParaTiBean json = new MensajeParaTiBean();
        Node mensaje = null;
        String mensajeBad = MensajesParaTiHelper
                .obtenerMensajeTipoBad("recargaWebPay");

        try {
        	String txtMensaje = "";
            String idContenido = MensajesParaTiHelper.getMensRecargaWebpay();

            mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(nodeMensajes,
                    idContenido);            
            json.setEstado("OK");
            String url = mensaje.getProperty("url").getValue().getStringValue();
            txtMensaje = mensaje.getProperty("texto").getValue()
                    .getStringValue();
            txtMensaje = txtMensaje.replaceAll("<url>", url);
            json.setValue(txtMensaje);
        } catch (Exception e) {
            json.setEstado("HIDDEN");
            json.setValue(mensajeBad);
            LOGGER.error(mensajeBad);
        }

        return json;
    }

}
