/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ConsultarBloqueoDesbloqueoBean;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.cliente.perfil.delegate.EquipoDelegate;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * MensajeHandler para mensajes de bloqueos, robo y extravio de equipos moviles
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al cliente, EntelPcs)
 *
 */


public class BloqueoRoboExtravioHandler extends AbstractMensajeHandler {
	
	
	    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION1 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP1"); // Robo
	    
	    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION2 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP2"); // Extravio

	    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION3 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP3"); // Hurto
	    
	    public static final String CODIGO_RESPUESTA_BLOQUEO_OPCION4 = MiEntelProperties.getProperty("servicios.codigoRespuesta.equipoBloqueadoOP4"); // Factura Impaga

	    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties.getProperty("servicios.codigoRespuesta.exito");
	    
	    private EquipoDelegate equipoDelegate;
	    
	    private ConsultarBloqueoDesbloqueoBean consultarBloqueoDesbloqueoBean ;
	    
	    /**
		 * @return the consultarBloqueoDesbloqueoBean
		 */
		public ConsultarBloqueoDesbloqueoBean getConsultarBloqueoDesbloqueoBean() {
			return consultarBloqueoDesbloqueoBean;
		}

		/**
		 * @param consultarBloqueoDesbloqueoBean the consultarBloqueoDesbloqueoBean to set
		 */
		public void setConsultarBloqueoDesbloqueoBean(
				ConsultarBloqueoDesbloqueoBean consultarBloqueoDesbloqueoBean) {
			this.consultarBloqueoDesbloqueoBean = consultarBloqueoDesbloqueoBean;
		}
	
	
	

    /**
	 * @return the equipoDelegate
	 */
	public EquipoDelegate getEquipoDelegate() {
		return equipoDelegate;
	}

	/**
	 * @param equipoDelegate the equipoDelegate to set
	 */
	public void setEquipoDelegate(EquipoDelegate equipoDelegate) {
		this.equipoDelegate = equipoDelegate;
	}

	/**
     * Logger para BloqueoRoboExtravioHandler
     */
    private static final Logger LOGGER = Logger
            .getLogger(BloqueoRoboExtravioHandler.class);
    
    /* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#getMensajeId()
     */
    @Override
    public String getMensajeId() {
        return MiEntelProperties
        .getProperty("mensajesParaTi.servicios.bloqueoRoboExtravio.id");
    }

    /* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#resolverMensaje(com.bea.content.Node, com.bea.p13n.usermgmt.profile.ProfileWrapper)
     */
    @Override
    protected MensajeParaTiBean resolverMensaje(Node nodeContenidoMensaje,
            ProfileWrapper profile) throws Exception {
        
        MensajeParaTiBean json = new MensajeParaTiBean();
        Node mensaje = null;
        String mensajeBad = "";//MensajesParaTiHelper.obtenerMensajeTipoBad("bloqueoRoboExtravio");
        String txtMensaje = "";
        String idContenido = "";   
        
        try {
        	
        	String mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado");            
            
            int DVImei = loadImeiDV(msisdn);
            
            consultarBloqueoDesbloqueoBean  =  equipoDelegate.getConsultarBloqueoDesbloqueo(msisdn, mercado , String.valueOf(DVImei));
            
            if (consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION1) || consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION2) || consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION3)) {                
                
            	if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION1))
            		idContenido = MensajesParaTiHelper.getMensMovilBloqueadRobo();
            	else if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION2))
            		idContenido = MensajesParaTiHelper.getMensMovilBloqueadoExtravio();
                else if(consultarBloqueoDesbloqueoBean.getCodigoRespuesta().equals(CODIGO_RESPUESTA_BLOQUEO_OPCION3))	
            		idContenido = MensajesParaTiHelper.getMensMovilBloqueadoHurto();
            	
                mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(nodeContenidoMensaje, idContenido);                
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
    
    /**
     * Obtiene el digito de verificacion para el imei
     * 
     * @param event
     */
    
    private int loadImeiDV( String msisdn ){
    	int imeiDV = 0;
    	try{
    		LOGGER.info("loadImeiDV");
    		String imeiEquipo = equipoDelegate.obtenerImei(msisdn);
    		imeiDV = calcularDigitoIMEI( imeiEquipo );
    		
    	}   catch (DAOException e) {
            LOGGER.error("DAOException al buscar informacion del imei.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException de servicio al buscar informacion de imei. msisdn: "+msisdn);
            JSFMessagesHelper.addErrorCodeMessage("general", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al buscar informacion de imei.", e);
            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
        }
		return imeiDV;     	
    }
    
    /**
     * Calcula el DV para el imei
     * 
     * @param event
     */    
    public int calcularDigitoIMEI(String imei) {

        int sum = 0;
  
        for (int i = 0; i < imei.length(); i++) {
            sum += Integer.parseInt(imei.substring(i, i + 1));
        }

        int delta[] = {0, 1, 2, 3, 4, -4, -3, -2, -1, 0};
        for (int i = imei.length() - 1; i >= 0; i -= 2) {
            int deltaIndex = Integer.parseInt(imei.substring(i, i + 1));
            int deltaValue = delta[deltaIndex];
            sum += deltaValue;
        }

        int mod10 = sum % 10;
        mod10 = 10 - mod10;
        if (mod10 == 10) {
            mod10 = 0;
        }

        return mod10;
    }

}
