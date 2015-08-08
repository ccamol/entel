/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.bean.OrdenTrabajoMPTBean;
import com.epcs.bean.ServicioTecnicoMPTBean;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * MensajeHandler para servicio tecnico de equipos moviles
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al cliente,
 *         EntelPcs)
 * 
 */
public class ServicioTecnicoHandler extends AbstractMensajeHandler {

    /**
     * Logger para ServicioTecnicoHandler
     */
    private static final Logger LOGGER = Logger
            .getLogger(ServicioTecnicoHandler.class);
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#getMensajeId()
     */
    @Override
    public String getMensajeId() {
        return MiEntelProperties
                .getProperty("mensajesParaTi.servicios.servicioTecnico.id");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#resolverMensaje
     * (com.bea.content.Node, com.bea.p13n.usermgmt.profile.ProfileWrapper)
     */
    @Override
    protected MensajeParaTiBean resolverMensaje(Node nodeContenidoMensaje,
            ProfileWrapper profile) throws Exception {
        
        MensajeParaTiBean json = new MensajeParaTiBean();
        Node mensaje = null;
        String mensajeBad = MensajesParaTiHelper.obtenerMensajeTipoBad("servicioTecnico");
        String txtMensaje = "";
        String idContenido = "";
        ServicioTecnicoMPTBean bean = null;
        
        try {
            
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");

            bean = getDelegate().consultarServicioTecnico(msisdn);
            
            if (bean.getNumeroOrdenes() > 1) {
                idContenido = MensajesParaTiHelper.getMensPresupuestoNoPublicado();
            } else {
                OrdenTrabajoMPTBean ordenBean = bean.getOrden(0);
                if (ordenBean.getCodEstado().equals(MensajesParaTiHelper.getEstadoEquipoRecepcionado())) {
                    idContenido = MensajesParaTiHelper.getMensEquipoReparado();
                } else if (ordenBean.getCodEstado().equals(MensajesParaTiHelper.getEstadoPresupuestoPendiente())) {
                    idContenido = MensajesParaTiHelper.getMensEquipoNoReparado();
                } else {
                    idContenido = MensajesParaTiHelper.getMensPresupuestoNoPublicado();
                }                
            }
            
            mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(nodeContenidoMensaje, idContenido);
            String url = mensaje.getProperty("url").getValue().getStringValue();
            txtMensaje = mensaje.getProperty("texto").getValue()
                    .getStringValue().replaceAll("<url>", url);
            json.setEstado("OK");
            json.setValue(txtMensaje);            
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
