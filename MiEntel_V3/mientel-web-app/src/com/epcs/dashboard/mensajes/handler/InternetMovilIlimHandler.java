/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.bean.PlanBean;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * MensajeHandler para planes de internet ilimitado
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al cliente, EntelPcs)
 *
 */
public class InternetMovilIlimHandler extends AbstractMensajeHandler {
    
	//Codigos planes multimedia
	private static final String PLAN_MULTI_CC = MiEntelProperties
			.getProperty("parametros.tipoplan.ccmul.id");
	private static final String PLAN_MULTI_IPHONE = MiEntelProperties
			.getProperty("parametros.tipoplan.muliphone.id");
	private static final String PLAN_MULTI_RED = MiEntelProperties
			.getProperty("parametros.tipoplan.mulred.id");    
	private static final String PLAN_MULTI_RED_EXC = MiEntelProperties
			.getProperty("parametros.tipoplan.mulredexc.id");
	private static final String PLAN_MULTI_TD_EXC = MiEntelProperties
			.getProperty("parametros.tipoplan.mulexctd.id");
	private static final String PLAN_MULTI_TD_EXC_EMP = MiEntelProperties
			.getProperty("parametros.tipoplan.mulexctdemp.id");    

    /**
     * Logger para InternetMovilIlimHandler
     */
    private static final Logger LOGGER = Logger
            .getLogger(InternetMovilIlimHandler.class);
    
    /* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#getMensajeId()
     */
    @Override
    public String getMensajeId() {
        return MiEntelProperties
                .getProperty("mensajesParaTi.servicios.internetMovilIlimitado.id");
    }
    
    /* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#resolverMensaje(com.bea.content.Node, com.bea.p13n.usermgmt.profile.ProfileWrapper)
     */
    @Override
    protected MensajeParaTiBean resolverMensaje(Node nodeContenidoMensaje,
            ProfileWrapper profile) throws Exception {
        
        MensajeParaTiBean json = new MensajeParaTiBean();
        Node mensaje = null;
        String mensajeBad = MensajesParaTiHelper.obtenerMensajeTipoBad("internetMovilIlimitado");
        String txtMensaje = "";
        String idContenido = "";
        boolean tieneIM = false;
        
        try {
            
            /*
             * GAR0001-41. Se consulta IM para movil seleccionado en vez del movil logueado
             * String msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");
             */
        	String msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado");
			String mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
            String AAA = ProfileWrapperHelper.getPropertyAsString(profile, "aaa");
        	String subMercado = ProfileWrapperHelper.getPropertyAsString(profile,
    		"subMercado");        	

            tieneIM = getDelegate().consultarInternetMovilContratado(msisdn);
            
            if (tieneIM) {                
                idContenido = MensajesParaTiHelper.getMensInternetMovilActivo();
            } else {
            	PlanBean planActual = getDelegate().getPlanActual(msisdn, mercado, AAA);
            	if (isPlanMultimedia(planActual.getTipoPlan())) {
            		//Si es plan multimedia Internet Activo
            		idContenido = MensajesParaTiHelper.getMensInternetMovilActivo();
            	} else {
            		if(subMercado.equals(MiEntelProperties.getProperty("miEntel.subMercadoEMP"))
                			|| subMercado.equals(MiEntelProperties.getProperty("miEntel.subMercadoSGO"))
                			|| subMercado.equals("SGO") ){
            			//Si es asignacion interna Internet Activo
            			idContenido = MensajesParaTiHelper.getMensInternetMovilActivo();
            	} else {
                idContenido = MensajesParaTiHelper.getMensInternetMovilNoActivo();
            	}
            }
            }
            
            LOGGER.info("Internet Movil Dashboard idContenido:"+idContenido);
            
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
            LOGGER.error(mensajeBad, se);
        } catch (Exception e) {
            json.setEstado("BAD");
            json.setValue(mensajeBad);
            LOGGER.error(mensajeBad, e);
        }

        
        return json;
    }

    private boolean isPlanMultimedia(String tipoPlan) {
    	return (tipoPlan.equals(PLAN_MULTI_CC) || tipoPlan.equals(PLAN_MULTI_IPHONE) || tipoPlan.equals(PLAN_MULTI_RED) || 
    			tipoPlan.equals(PLAN_MULTI_RED_EXC) || tipoPlan.equals(PLAN_MULTI_TD_EXC) || tipoPlan.equals(PLAN_MULTI_TD_EXC_EMP));
    }
}
