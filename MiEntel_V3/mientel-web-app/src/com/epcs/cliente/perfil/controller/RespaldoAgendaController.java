package com.epcs.cliente.perfil.controller;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.EquipoFullBean;
import com.epcs.cliente.perfil.delegate.EquipoDelegate;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

public class RespaldoAgendaController implements Serializable{
	
	 private static final long serialVersionUID = 1L;

	    private static final Logger LOGGER = Logger
	            .getLogger(EquipoController.class);
	    
	    private EquipoFullBean  equipoFullBean;
	  
	    private EquipoDelegate   equipoDelegate;
	    
	    private String   msjCompatibidad ="";
	    
	    private static ParametrosHelper  parametrosHelper;
	    
	    private  String numeroPcsSeleccionado ;
	    
	    private  boolean estadoMovil;
	    
	    private static final String URL_RESPALDO_AGENDA= MiEntelProperties
        .getProperty("parametros.respaldoAgenda.url");
	    
	    private String url="";
	    
	    private SeguridadDelegate seguridadDelegate;
	    
	    private boolean visible = true;
	    
	    
	/**
	 * Inicializa los datos del Servicio Respaldo Agenda (Nivel de Compatibilidad)
	 * 
	 * @param event
	 */
	    
   	/**
     * @return the equipoDelegate
     */
    public EquipoDelegate getEquipoDelegate() {
        return equipoDelegate;
    }

    /**
     * @param equipoDelegate
     *            the equipoDelegate to set
     */
    public void setEquipoDelegate(EquipoDelegate equipoDelegate) {
        this.equipoDelegate = equipoDelegate;
    }
    
    
	/**
     * @return the seguridadDelegate
     */
    public SeguridadDelegate getSeguridadDelegate() {
        return seguridadDelegate;
    }

    /**
     * @param seguridadDelegate the seguridadDelegate to set
     */
    public void setSeguridadDelegate(SeguridadDelegate seguridadDelegate) {
        this.seguridadDelegate = seguridadDelegate;
    }
    
    
    
	  public void initEquipos(PhaseEvent event) {
	        try {
	            ProfileWrapper profileWrapper = ProfileWrapperHelper
	                    .getProfile(JSFPortletHelper.getRequest());
	             numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
	                    profileWrapper, "numeroPcsSeleccionado");
	                          
	             String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
	             
	             
	             int DVImei = loadImeiDV(numeroPcsSeleccionado);
	             
	             this.estadoMovil = equipoDelegate.tieneBloqueoExistente(numeroPcsSeleccionado,mercado,String.valueOf(DVImei));
	             
		       if(!"PP".equalsIgnoreCase(mercado) && estadoMovil)
		        {
		    	   this.visible = false;
		    	   JSFMessagesHelper.addSuccessMessage(MiEntelProperties.getProperty("parametros.respaldoAgenda.mensajeError"));    	
		        }
		          else
		        {
		        	  nivelCompatibilidad(numeroPcsSeleccionado);	
		        	  this.visible = true;
		        }
	            
	               
	            
	        }  catch (DAOException e) {
	            LOGGER.error("DAOException al buscar informacion de equipo.", e);
	            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
	        } catch (ServiceException e) {
	            LOGGER.info("ServiceException de servicio al buscar informacion de equipo. msisdn: "+numeroPcsSeleccionado);
	            JSFMessagesHelper.addErrorCodeMessage("general", e.getCodigoRespuesta());
	        } catch (Exception e) {
	            LOGGER.error("Exception al buscar informacion de equipo.", e);
	            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
	        }
	    }	
	  
	  
	  private void nivelCompatibilidad(String numeroPcsSeleccionado){
	        try{
	        	
	        this.equipoFullBean = this.equipoDelegate.getEquipoCompatibilidad(numeroPcsSeleccionado); 
	                    	 
	        String idContenido ="";  
	       
			if (ParametrosHelper.getExisteParametro("respaldoAgenda.bkpContSW",
					equipoFullBean.getBkpContSW())) {
				idContenido = MiEntelProperties
						.getProperty("respaldoAgenda.idContenido.compartido");

			} else if (equipoFullBean.getBkpContSyncML().equals("1")) {

				idContenido = MiEntelProperties
						.getProperty("respaldoAgenda.idContenido.equipo");

			} else if (equipoFullBean.getBkpContSIM().equals("1")) {

				idContenido = MiEntelProperties
						.getProperty("respaldoAgenda.idContenido.sim");

			} else {

				idContenido = MiEntelProperties
						.getProperty("respaldoAgenda.idContenido.noCompatible");
			}	           	 
	   		
		   	Node mensaje = JSFPortletHelper.getContentNode("idContenido", idContenido);
     	   	this.setMsjCompatibidad(mensaje.getProperty("html").getValue().getStringValue());
		   	
		   	
	        }   catch (DAOException e) {
	            LOGGER.error("Error al buscar informacion de equipo.", e);
	            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
	        } catch (ServiceException e) {
	            LOGGER.info("Error de servicio al buscar informacion de equipo.");
	            JSFMessagesHelper.addErrorCodeMessage("general", e.getCodigoRespuesta());
	        } catch (Exception e) {
	            LOGGER.error("Error al buscar informacion de equipo.", e);
	            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
	        }   
	    }
	  
	  public String getUrlRespaldoAgenda() {
	        String idp = "";
	        try {
	        	
	            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
                numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
	        	
                seguridadDelegate = new SeguridadDelegate();
                
	            idp = this.seguridadDelegate.consultarIDP(numeroPcsSeleccionado);
	            
	            this.equipoFullBean = this.equipoDelegate.getEquipoCompatibilidad(numeroPcsSeleccionado); 
	            
	            Calendar cal = Calendar.getInstance();
	            StringBuffer sb=new StringBuffer();

	            sb.append("Movil:");
	            sb.append(numeroPcsSeleccionado);
	            sb.append("|");
	            sb.append("Marca:");
	            sb.append(equipoFullBean.getMarca());
	            sb.append("|");
	            sb.append("Modelo:");
	            sb.append(equipoFullBean.getModelo());
	            sb.append("|");
	            sb.append("timestamp:");
	            sb.append(cal.getTimeInMillis());
	            sb.append("|");
	            sb.append("IP:");
	            sb.append(JSFPortletHelper.getRequest().getRemoteAddr());	            
	            LOGGER.info(sb.toString());
	           
	            return (URL_RESPALDO_AGENDA.concat(idp));
	            
	            
	            
	        } catch (DAOException e) {
	            LOGGER.error("DAOException al obtener el IDP", e);
	            return "";
	        } catch (ServiceException e) {
	            LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
	                    + " - " + e.getDescripcionRespuesta());
	            return "";
	        } catch (Exception e) {
	            LOGGER.error("Exception inesperado al obtener la URL de MiEntelV2", e);
	            return "";
	        }
	    }	  


	public String getNumeroPcsSeleccionado() {
		return numeroPcsSeleccionado;
	}

	public void setNumeroPcsSeleccionado(String numeroPcsSeleccionado) {
		this.numeroPcsSeleccionado = numeroPcsSeleccionado;
	}

	/**
	 * @return the msjCompatibidad
	 */
	public String getMsjCompatibidad() {
		return msjCompatibidad;
	}

	/**
	 * @param msjCompatibidad the msjCompatibidad to set
	 */
	public void setMsjCompatibidad(String msjCompatibidad) {
		this.msjCompatibidad = msjCompatibidad;
	}

	public EquipoFullBean getEquipoFullBean() {
		return equipoFullBean ;
	}

	public void setEquipoFullBean(EquipoFullBean equipoFullBean) {
		this.equipoFullBean = equipoFullBean;
	}
	

    

	public String getUrl() {		
		url = getUrlRespaldoAgenda();		
        return url;		
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
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
