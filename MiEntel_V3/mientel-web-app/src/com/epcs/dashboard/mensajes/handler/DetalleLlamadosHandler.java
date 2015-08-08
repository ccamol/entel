/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ConsultarDetalleLlamadosMPTBean;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * MensajeHandler para detalles de llamados
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al cliente, EntelPcs)
 *
 */
public class DetalleLlamadosHandler extends AbstractMensajeHandler {

    

    /**
     * Logger para DetalleLlamadosHandler
     */
    private static final Logger LOGGER = Logger
            .getLogger(DetalleLlamadosHandler.class);
    

    /* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#getMensajeId()
     */
    @Override
    public String getMensajeId() {
        return MiEntelProperties
                .getProperty("mensajesParaTi.servicios.detalleLlamados.id");
    }

    /* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#resolverMensaje(com.bea.content.Node, com.bea.p13n.usermgmt.profile.ProfileWrapper)
     */
    @Override
    protected MensajeParaTiBean resolverMensaje(Node nodeContenidoMensaje,
            ProfileWrapper profile) throws Exception {
        
        MensajeParaTiBean json = new MensajeParaTiBean();
        Node mensaje = null;
        String mensajeBad = MensajesParaTiHelper.obtenerMensajeTipoBad("detalleLlamados");
        String txtMensaje = "";
        String idContenido = "";
        ConsultarDetalleLlamadosMPTBean bean = null;
        
        try {
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");
            String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(profile, "numeroCuenta");
            String rutTitular = ProfileWrapperHelper.getPropertyAsString(profile, "rutTitular");
            String aaa = ProfileWrapperHelper.getPropertyAsString(profile,"aaa");
            
            bean = getDelegate().consultarDetalleLlamados(msisdn, aaa, rutTitular, numeroCuenta);
            
            if (!bean.isConsultado()) {
                idContenido = MensajesParaTiHelper.getMensDetalleDisponible();
                mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(nodeContenidoMensaje, idContenido);
                String url = mensaje.getProperty("url").getValue().getStringValue();
                txtMensaje = mensaje.getProperty("texto").getValue().getStringValue();
                txtMensaje = txtMensaje.replaceAll("<url>", url).replaceAll(
                        "<periodo>", bean.getPeriodo());

                json.setEstado("OK");
                json.setValue(txtMensaje);
            } else {
                json.setEstado("HIDDEN");
                json.setValue(mensajeBad);
            }
        } catch (DAOException de) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.info(mensajeBad);
        } catch (ServiceException se) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.info(mensajeBad);
        } catch (SQLException e) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.error(mensajeBad, e);
        } catch (Exception e) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.info(mensajeBad);
        }
        
        return json;
    }

}
