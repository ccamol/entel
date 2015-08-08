/* Propiedad de Entel. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

/**
 * Handler para el mensaje de Bienvenida a los usuarios de MiEntel v3
 * 
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Servicio al Cliente, Entel
 *         Pcs)
 * 
 */
public class FacturacionEmpresasHandler extends AbstractMensajeHandler {
	
	/**
     * Logger para FacturacionEmpresasHandler
     */
    private static final Logger LOGGER = Logger
            .getLogger(FacturacionEmpresasHandler.class);

    @Override
    public String getMensajeId() {
        return MiEntelProperties
                .getProperty("mensajesParaTi.servicios.facturacionEmpresas.id");
    }

    @Override
    protected MensajeParaTiBean resolverMensaje(Node nodeMensajes,
            ProfileWrapper profile) throws Exception {

        MensajeParaTiBean json = new MensajeParaTiBean();
        Node mensaje = null;
        String mensajeBad = MensajesParaTiHelper
                .obtenerMensajeTipoBad("facturacionEmpresas");

        try {
        	
        	String subMercado = ProfileWrapperHelper.getPropertyAsString(profile,
            		"subMercado");
        	String txtMensaje = "";
            String idContenido = "";
            
        	if(subMercado.equals(MiEntelProperties.getProperty("miEntel.subMercadoEMP"))
        			|| subMercado.equals(MiEntelProperties.getProperty("miEntel.subMercadoSGO"))){
        		
        		idContenido = MensajesParaTiHelper.getMensFactEmpresas();

                mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(nodeMensajes,
                        idContenido);            
                json.setEstado("OK");
                String url = mensaje.getProperty("url").getValue().getStringValue();
                txtMensaje = mensaje.getProperty("texto").getValue()
                        .getStringValue();
                txtMensaje = txtMensaje.replaceAll("<url>", url);
        	}
        	
            json.setValue(txtMensaje);
        } catch (Exception e) {
            json.setEstado("HIDDEN");
            json.setValue(mensajeBad);
            LOGGER.error(mensajeBad, e);
        }

        return json;
    }

}