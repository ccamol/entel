/* Propiedad de Entel. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import java.util.Date;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.EstadoCuentaMPTBean;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * Handler para el mensaje de Estado de Cuenta
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Servicio al Cliente, Entel
 *         Pcs)
 * 
 */
public class EstadoCuentaHandler extends AbstractMensajeHandler {

    /**
     * Logger para EstadoCuentaHandler
     */
    private static final Logger LOGGER = Logger
            .getLogger(EstadoCuentaHandler.class);

    @Override
    public String getMensajeId() {
        return MiEntelProperties
                .getProperty("mensajesParaTi.servicios.estadoCuenta.id");
    }

    @Override
    protected MensajeParaTiBean resolverMensaje(Node nodeMensajes,
            ProfileWrapper profile) throws Exception {

        MensajeParaTiBean json = new MensajeParaTiBean();
        Node mensaje = null;
        String mensajeBad = MensajesParaTiHelper
                .obtenerMensajeTipoBad("estadoCuenta");
        String txtMensaje = "";
        String idContenido = "";

        try {

            String msisdn = ProfileWrapperHelper.getPropertyAsString(profile,
                    "numeroPcs");
            String rutTitular = ProfileWrapperHelper.getPropertyAsString(
                    profile, "rutTitular");

            EstadoCuentaMPTBean bean = getDelegate()
                    .consultarEstadoCuenta(msisdn, rutTitular);

            if (bean.getEstadoMes().equals("Pagado")) {
                idContenido = MensajesParaTiHelper.getMensCuentaPagada();
            }
            else {
                if (bean.getFechaVencimiento().before(new Date())) {
                    idContenido = MensajesParaTiHelper.getMensCuentaVencida();
                }
                else {
                    idContenido = MensajesParaTiHelper.getMensCuentaEmitida();
                }
            }

            mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(nodeMensajes,
                    idContenido);            
            json.setEstado("OK");
            String url = mensaje.getProperty("url").getValue().getStringValue();
            txtMensaje = mensaje.getProperty("texto").getValue()
                    .getStringValue();
            txtMensaje = txtMensaje.replaceAll("<url>", url);
            txtMensaje = txtMensaje.replaceAll("<valorCuenta>", Utils
                    .formatMoneyPuntos(bean.getTotalPagoMes()));
            json.setValue(txtMensaje);
        } catch (DAOException de) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.info(mensajeBad);
        } catch (ServiceException se) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.info(mensajeBad);
        } catch (Exception e) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.info(mensajeBad);
        }

        return json;
    }

}
