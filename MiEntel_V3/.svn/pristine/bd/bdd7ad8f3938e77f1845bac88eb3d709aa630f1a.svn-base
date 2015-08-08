/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * Handler para mensajes de factura electronica
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al cliente, EntelPcs)
 *
 */
public class FacturaElectronicaHandler extends AbstractMensajeHandler {

    /**
     * Logger para FacturaElectronicaHandler
     */
    private static final Logger LOGGER = Logger
            .getLogger(FacturaElectronicaHandler.class);
    
    
    /* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#getMensajeId()
     */
    @Override
    public String getMensajeId() {
        return MiEntelProperties
                .getProperty("mensajesParaTi.servicios.facturacionElectronica.id");
    }

    
    /* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#resolverMensaje(com.bea.content.Node, com.bea.p13n.usermgmt.profile.ProfileWrapper)
     */
    @Override
    protected MensajeParaTiBean resolverMensaje(Node nodeMensajes,
            ProfileWrapper profile) throws Exception {

        MensajeParaTiBean json = new MensajeParaTiBean();
        Node mensaje = null;
        String mensajeBad = MensajesParaTiHelper.obtenerMensajeTipoBad("facturacionElectronica");
        String txtMensaje = "";
        String idContenido = "";

        try {
            
            String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(profile, "numeroCuenta");
            String rutTitular = ProfileWrapperHelper.getPropertyAsString(profile, "rutTitular");
            String mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");

            
            String estadoFE = getDelegate().consultarFacturaElectronica(rutTitular, numeroCuenta, mercado);
            if (estadoFE.equals("No Inscrito")) {
                idContenido = MensajesParaTiHelper.getMensFacturaNoInscrita();
                json.setEstado("OK");
                mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(nodeMensajes, idContenido);
                String url = mensaje.getProperty("url").getValue()
                        .getStringValue();
                txtMensaje = mensaje.getProperty("texto").getValue()
                        .getStringValue().replaceAll("<url>", url);
                json.setValue(txtMensaje);
            }
            else {
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
            LOGGER.error(mensajeBad, se);
        } catch (Exception e) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.error(mensajeBad, e);
        }

        return json;
    
    
    }

}
