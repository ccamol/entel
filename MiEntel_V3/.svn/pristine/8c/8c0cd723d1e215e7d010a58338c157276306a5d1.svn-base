/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * MensajeHandler para mensaje de bolsas y planes complementarios. 
 * Opera para Voz y Bam
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al cliente, EntelPcs)
 *
 */
public class BolsasPlanesCompHandler extends AbstractMensajeHandler {

    

    /**
     * Logger para BolsasPlanesCompHandler
     */
    private static final Logger LOGGER = Logger
            .getLogger(BolsasPlanesCompHandler.class);
    
    /* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#getMensajeId()
     */
    @Override
    public String getMensajeId() {
        return MiEntelProperties
                .getProperty("mensajesParaTi.servicios.bolsasPlanesComplementarios.id");
    }

    
    /* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#resolverMensaje(com.bea.content.Node, com.bea.p13n.usermgmt.profile.ProfileWrapper)
     */
    @Override
    protected MensajeParaTiBean resolverMensaje(Node nodeMensajes,
            ProfileWrapper profile) throws Exception {
        MensajeParaTiBean json = new MensajeParaTiBean();
        Node mensaje = null;
        String mensajeBad = MensajesParaTiHelper.obtenerMensajeTipoBad("bolsasPlanesComplementarios");
        String txtMensaje = "";
        String idContenido = "";
        boolean tieneBolsas = false;
        String msisdn = "";
        
        try {
            
            msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");
            String flagBAM = ProfileWrapperHelper.getPropertyAsString(profile, "flagBam");

            //Identifica si user es Bam o Voz. Segun el caso es el servicio que emplea
            if(MiEntelBusinessHelper.isUserBam(flagBAM)) {
                tieneBolsas = getDelegate().consultarBolsasPlanesBAM(msisdn);
            }
            else {
                String mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
                tieneBolsas = getDelegate().consultarBolsasPlanes(mercado, msisdn);
            }

            if (!tieneBolsas) {                
                idContenido = MensajesParaTiHelper.getMensOptimizarBolsa();
                mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(nodeMensajes, idContenido);
                String url = mensaje.getProperty("url").getValue()
                        .getStringValue();
                txtMensaje = mensaje.getProperty("texto").getValue()
                        .getStringValue().replaceAll("<url>", url);
                json.setEstado("OK");
                json.setValue(txtMensaje);
            }
            else {
                json.setEstado("HIDDEN");
                json.setValue(mensajeBad);
            }
        } catch (DAOException de) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.error(mensajeBad + "msisdn : "+msisdn, de);
        } catch (ServiceException se) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.warn(mensajeBad + "msisdn : "+msisdn);
        } catch (Exception e) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.error(mensajeBad + "msisdn : "+msisdn , e);
        }
        
        return json;
    }

}
