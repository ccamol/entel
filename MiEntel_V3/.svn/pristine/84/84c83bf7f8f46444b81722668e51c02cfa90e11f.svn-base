/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.EquiposContratadosYArriendoMPTBean;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.bean.RutBean;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * MensajeHandler para arriendo de equipos
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al cliente,
 *         EntelPcs)
 * 
 */
public class EquiposArriendoHandler extends AbstractMensajeHandler {

    

    /**
     * Logger para EquiposArriendoHandler
     */
    private static final Logger LOGGER = Logger
            .getLogger(EquiposArriendoHandler.class);
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#getMensajeId()
     */
    @Override
    public String getMensajeId() {
        return MiEntelProperties
                .getProperty("mensajesParaTi.servicios.equiposArriendo.id");
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
        EquiposContratadosYArriendoMPTBean bean = null;
        Node mensaje = null;
        String mensajeBad = MensajesParaTiHelper.obtenerMensajeTipoBad("equiposArriendo");
        String txtMensaje = "";
        String idContenido = "";
        
        try {
            
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");
            String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(profile, "numeroCuenta");
            String rutTitular = ProfileWrapperHelper.getPropertyAsString(profile, "rutTitular");
            
            RutBean rut = new RutBean(rutTitular);
            
            bean = getDelegate().consultarEquiposContratadosYArriendo(msisdn, rut, numeroCuenta);
            
            if (bean.getEquiposContratoVigente() > 0) {
                idContenido = MensajesParaTiHelper.getMensConEquipoArriendo();
            } else {
                idContenido = MensajesParaTiHelper.getMensSinEquipoArriendo();
            }
            
            mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(nodeContenidoMensaje, idContenido);
            String url = mensaje.getProperty("url").getValue().getStringValue();            
            
            if (bean.getEquiposContratoVigente() > 0) {
                String cantLineas = String.valueOf(bean.getEquiposContratoVigente());
                txtMensaje = mensaje.getProperty("texto").getValue()
                        .getStringValue().replaceAll("<url>", url).replaceAll(
                                "<numEquipos>", cantLineas);
            }
            else {
                txtMensaje = mensaje.getProperty("texto").getValue()
                        .getStringValue().replaceAll("<url>", url);
            }
            
            json.setEstado("OK");
            json.setValue(txtMensaje);            
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
