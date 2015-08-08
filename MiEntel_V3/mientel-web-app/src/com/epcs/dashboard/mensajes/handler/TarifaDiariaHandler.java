/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.handler;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.BolsaBean;
import com.epcs.bean.BolsasActualesDisponiblesBean;
import com.epcs.bean.MensajeParaTiBean;
import com.epcs.bean.PlanBean;
import com.epcs.administracion.suscripciones.delegate.SuscripcionesDelegate;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.dashboard.mensajes.util.AbstractMensajeHandler;
import com.epcs.dashboard.mensajes.util.MensajesParaTiHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * MensajeHandler para mensajes comunicacion tarifa diaria
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al cliente, EntelPcs)
 *
 */


public class TarifaDiariaHandler extends AbstractMensajeHandler {
	
	
	    private static final String ID_PLANES_MULTIMEDIA_SS_EXCLUIR = MiEntelProperties.getProperty("parametros.tarifaDiaria.planesMultimediaSSExcluir"); 
	    private static final String ID_PLANES_MULTIMEDIA_CC_EXCLUIR = MiEntelProperties.getProperty("parametros.tarifaDiaria.planesMultimediaCCExcluir");	    
	    private static final String ID_PLANES_EMPRESA_EXCLUIR = MiEntelProperties.getProperty("parametros.tarifaDiaria.planesEmpresaExcluir");
	    private static final String ID_BOLSAS_IM_EXCLUIR = MiEntelProperties.getProperty("parametros.tarifaDiaria.bolsasIMExcluir");
	    
	    private PlanDelegate planDelegate;
	    private SuscripcionesDelegate suscripcionesDelegate;
	    
	    private PlanBean planBean;
	    private BolsasActualesDisponiblesBean bolsasActualesYDisponibles;
	  

	/**
     * Logger para TarifaDiariaHandler
     */
    private static final Logger LOGGER = Logger
            .getLogger(TarifaDiariaHandler.class);
    
    
	/* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#getMensajeId()
     */
    @Override
    public String getMensajeId() {
        return MiEntelProperties
        .getProperty("mensajesParaTi.servicios.tarifaDiaria.id");
    }

    /* (non-Javadoc)
     * @see com.epcs.dashboard.mensajes.util.AbstractMensajeHandler#resolverMensaje(com.bea.content.Node, com.bea.p13n.usermgmt.profile.ProfileWrapper)
     */
    @Override
    protected MensajeParaTiBean resolverMensaje(Node nodeContenidoMensaje,
            ProfileWrapper profile) throws Exception {
        
        MensajeParaTiBean json = new MensajeParaTiBean();
        Node mensaje = null;
        String mensajeBad = MensajesParaTiHelper.obtenerMensajeTipoBad("tarifaDiaria");
        String txtMensaje = "";
        String idContenido = ""; 
        boolean sw=true;
        
        try {
        	
        	String mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
        	String subMercado = ProfileWrapperHelper.getPropertyAsString(profile, "subMercado");
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado");  
            String aaa = ProfileWrapperHelper.getPropertyAsString(profile, "aaa"); 
            
            planBean = planDelegate.getPlanActualSSCC(msisdn, mercado, aaa);
            
            if (planBean!=null) {
            	
	              if(mercado.equals("CC")){
	            	  if(isPlanEnLista(planBean.getCodbscs2(),ID_PLANES_MULTIMEDIA_CC_EXCLUIR))
	            		  sw=false;
	                    
	              }else if(mercado.equals("SS")){
	            	  if(isPlanEnLista(planBean.getCodbscs2(),ID_PLANES_MULTIMEDIA_SS_EXCLUIR) )
	            		  sw=false;
	                	
	              }
	              if(sw && isPlanEnLista(subMercado,ID_PLANES_EMPRESA_EXCLUIR))
	            	  sw=false;
	              
	              if(sw){
	            	  bolsasActualesYDisponibles = suscripcionesDelegate.consultarBolsasActualesDisponibles(msisdn);
	            	  if(!bolsasActualesYDisponibles.getBolsasActuales().isEmpty()){
	            		  for(BolsaBean bolsaActual : bolsasActualesYDisponibles.getBolsasActuales()){
	            			  if(isPlanEnLista(bolsaActual.getSnCodigo(),ID_BOLSAS_IM_EXCLUIR)){
	            				  sw = false;
	            				  break;
	            			  }
	            		  }
	            	  }
	              }
	               
            	  if(sw){
            		  idContenido = MensajesParaTiHelper.getMensComunicacionTarifaDiaria();	            	
	   	              mensaje = MensajesParaTiHelper.obtenerMensajeParaTi(nodeContenidoMensaje, idContenido);                
	   	              String url = mensaje.getProperty("url").getValue()
	   	                        .getStringValue();
	   	              txtMensaje = mensaje.getProperty("texto").getValue()
	   	                        .getStringValue().replaceAll("<url>", url);
	   	              json.setEstado("OK");
	   	              json.setValue(txtMensaje); 
            	  }else{
            		  mensajeBad = MensajesParaTiHelper.obtenerMensajeTipoBad("tarifaDiaria.condiciones");
            		  json.setEstado("HIDDEN");
                      json.setValue(mensajeBad);
            	  }            	   
	                
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
	 * @return the planDelegate
	 */
	public PlanDelegate getPlanDelegate() {
		return planDelegate;
	}

	/**
	 * @param planDelegate the planDelegate to set
	 */
	public void setPlanDelegate(PlanDelegate planDelegate) {
		this.planDelegate = planDelegate;
	}

	/**
	 * @return the suscripcionesDelegate
	 */
	public SuscripcionesDelegate getSuscripcionesDelegate() {
		return suscripcionesDelegate;
	}

	/**
	 * @param internetMovilDelegate the internetMovilDelegate to set
	 */
	public void setSuscripcionesDelegate(SuscripcionesDelegate internetMovilDelegate) {
		this.suscripcionesDelegate = internetMovilDelegate;
	}

	/**
	 * @return the planBean
	 */
	public PlanBean getPlanBean() {
		return planBean;
	}

	/**
	 * @param planBean the planBean to set
	 */
	public void setPlanBean(PlanBean planBean) {
		this.planBean = planBean;
	}
    
    
    /**
	 * @return the bolsasActualesYDisponibles
	 */
	public BolsasActualesDisponiblesBean getBolsasActualesYDisponibles() {
		return bolsasActualesYDisponibles;
	}

	/**
	 * @param bolsasActualesYDisponibles the bolsasActualesYDisponibles to set
	 */
	public void setBolsasActualesYDisponibles(BolsasActualesDisponiblesBean bolsasActualesYDisponibles) {
		this.bolsasActualesYDisponibles = bolsasActualesYDisponibles;
	}

	/**
     *  metodo para determinar si el plan actual esta excluido de tarifa diaria. 
     * @param event
     * @return (boolean sw)
     */
    
    private boolean isPlanEnLista (String planActual,String idContenido){
    	boolean sw = false;    	
    	String listaPlanes="";
    	String[] arrayPlanesExcluidos;
    	try{    		
    		    //String idContenido = MiEntelProperties.getProperty(id);
		   	    Node mensaje = JSFPortletHelper.getContentNode("idContenido", idContenido);
		        listaPlanes = mensaje.getProperty("html").getValue().getStringValue();
		        
		        if(listaPlanes!=null){
		        	arrayPlanesExcluidos = listaPlanes.split(",");
		        	int i =0;
		        	while(i < arrayPlanesExcluidos.length && sw == false){
		        	     if(arrayPlanesExcluidos[i].equals(planActual)){
		        	    	 sw=true;
		        	     }
		        		i++;
		        	}		        	
		        }
    	
        } catch (Exception e) {
            LOGGER.error("Exception al buscar informacion de planes excluidos de tarifa diaria en el CM.", e);           
        }
		return sw;     	
    }
    
    
}
