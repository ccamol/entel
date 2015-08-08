/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.AlertaCuotaTraficoBean;
import com.epcs.bean.TraficoEnLineaBean;
import com.epcs.bean.TraficoEnLineaPPCCBean;
import com.epcs.billing.registrouso.delegate.TraficoDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.error.ServiceMessages;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoEnLineaController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(TraficoEnLineaController.class);

    private TraficoDelegate traficoDelegate;

    private List<TraficoEnLineaBean> traficoEnLinea;

    private List<TraficoEnLineaBean> detalleTraficoEnLinea;
    
    private List<TraficoEnLineaPPCCBean> traficoEnLineaPPCC;
    
    private List<TraficoEnLineaPPCCBean> detalleTraficoEnLineaPPCC;

    private String traficoEnLineaJson;
    
	private String traficoEnLineaPPCCJson;
    
    public List<TraficoEnLineaPPCCBean> paginaTotal;
    
    private int paginaActual;
    
    public String numPagina;
    
    public boolean errorDetalleTraficoEnLineaPPCC;
    
    public String errorMessage;
    
    private List<AlertaCuotaTraficoBean> alertaCuotaTrafico;
    
    private List<AlertaCuotaTraficoBean> detalleAlertaCuotaTrafico;
    
    private String paginasTotalACT;   
    
    private String alertaCuotaTraficoJson;
    
    public boolean errorAlertaCuotaTrafico;

    /**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorDetalleTraficoEnLineaPPCC
	 */
	public boolean isErrorDetalleTraficoEnLineaPPCC() {
		return errorDetalleTraficoEnLineaPPCC;
	}

	/**
	 * @param errorDetalleTraficoEnLineaPPCC the errorDetalleTraficoEnLineaPPCC to set
	 */
	public void setErrorDetalleTraficoEnLineaPPCC(
			boolean errorDetalleTraficoEnLineaPPCC) {
		this.errorDetalleTraficoEnLineaPPCC = errorDetalleTraficoEnLineaPPCC;
	}

	/**
	 * @return the traficoEnLineaPPCC
	 */
	public List<TraficoEnLineaPPCCBean> getTraficoEnLineaPPCC() {
		return traficoEnLineaPPCC;
	}

	/**
	 * @param traficoEnLineaPPCC the traficoEnLineaPPCC to set
	 */
	public void setTraficoEnLineaPPCC(
			List<TraficoEnLineaPPCCBean> traficoEnLineaPPCC) {
		this.traficoEnLineaPPCC = traficoEnLineaPPCC;
	}

	/**
	 * @return the detalleTraficoEnLineaPPCC
	 */
	public List<TraficoEnLineaPPCCBean> getDetalleTraficoEnLineaPPCC() {
		return detalleTraficoEnLineaPPCC;
	}

	/**
	 * @param detalleTraficoEnLineaPPCC the detalleTraficoEnLineaPPCC to set
	 */
	public void setDetalleTraficoEnLineaPPCC(
			List<TraficoEnLineaPPCCBean> detalleTraficoEnLineaPPCC) {
		this.detalleTraficoEnLineaPPCC = detalleTraficoEnLineaPPCC;
	}

	/**
	 * @return the traficoEnLineaPPCCJson
	 */
	public String getTraficoEnLineaPPCCJson() {
		return traficoEnLineaPPCCJson;
	}

	/**
	 * @param traficoEnLineaPPCCJson the traficoEnLineaPPCCJson to set
	 */
	public void setTraficoEnLineaPPCCJson(String traficoEnLineaPPCCJson) {
		this.traficoEnLineaPPCCJson = traficoEnLineaPPCCJson;
	}

	/**
	 * @return the paginaTotal
	 */
	public List<TraficoEnLineaPPCCBean> getPaginaTotal() {
		return paginaTotal;
	}

	/**
	 * @param paginaTotal the paginaTotal to set
	 */
	public void setPaginaTotal(List<TraficoEnLineaPPCCBean> paginaTotal) {
		this.paginaTotal = paginaTotal;
	}


	/**
	 * @return the paginaActual
	 */
	public int getPaginaActual() {
		return 1;
	}

	/**
	 * @param paginaActual the paginaActual to set
	 */
	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	/**
	 * @return the numPagina
	 */
	public String getNumPagina() {
		return numPagina;
	}

	/**
	 * @param numPagina the numPagina to set
	 */
	public void setNumPagina(String numPagina) {
		this.numPagina = numPagina;
	}

	/**
	 * @return the traficoEnLinea
	 */
	public List<TraficoEnLineaBean> getTraficoEnLinea() {
		return traficoEnLinea;
	}

	/**
	 * @param detalleTraficoEnLinea the detalleTraficoEnLinea to set
	 */
	public void setDetalleTraficoEnLinea(
			List<TraficoEnLineaBean> detalleTraficoEnLinea) {
		this.detalleTraficoEnLinea = detalleTraficoEnLinea;
	}

	/**
     * @return the traficoDelegate
     */
    public TraficoDelegate getTraficoDelegate() {
        return traficoDelegate;
    }

    /**
     * @param traficoDelegate
     *            the traficoDelegate to set
     */
    public void setTraficoDelegate(TraficoDelegate traficoDelegate) {
        this.traficoDelegate = traficoDelegate;
    }

    /**
     * @param traficoEnLinea
     *            the traficoEnLinea to set
     */
    public void setTraficoEnLinea(List<TraficoEnLineaBean> traficoEnLinea) {
        this.traficoEnLinea = traficoEnLinea;
    }

    /**
     * @return the traficoEnLineaJson
     */
    public String getTraficoEnLineaJson() {
        return traficoEnLineaJson;
    }

    /**
     * @param traficoEnLineaJson
     *            the traficoEnLineaJson to set
     */
    public void setTraficoEnLineaJson(String traficoEnLineaJson) {
        this.traficoEnLineaJson = traficoEnLineaJson;
    }
        

    /**
	 * @return the alertaCuotaTrafico
	 */
	public List<AlertaCuotaTraficoBean> getAlertaCuotaTrafico() {
		return alertaCuotaTrafico;
	}

	/**
	 * @param alertaCuotaTrafico the alertaCuotaTrafico to set
	 */
	public void setAlertaCuotaTrafico(
			List<AlertaCuotaTraficoBean> alertaCuotaTrafico) {
		this.alertaCuotaTrafico = alertaCuotaTrafico;
	}
	

	/**
	 * @return the paginaTotalACT
	 */
	public String getpaginasTotalACT() {
		return paginasTotalACT;
	}

	/**
	 * @param paginaTotalACT the paginaTotalACT to set
	 */
	public void setpaginasTotalACT(String paginasTotalACT) {
		this.paginasTotalACT = paginasTotalACT;
	}	

	/**
	 * @return the detallealertaCuotaTrafico
	 */
	public List<AlertaCuotaTraficoBean> getDetalleAlertaCuotaTrafico() {
		return detalleAlertaCuotaTrafico;
	}

	/**
	 * @param detallealertaCuotaTrafico the detallealertaCuotaTrafico to set
	 */
	public void setDetalleAlertaCuotaTrafico(
			List<AlertaCuotaTraficoBean> detalleAlertaCuotaTrafico) {
		this.detalleAlertaCuotaTrafico = detalleAlertaCuotaTrafico;
	}
	

	/**
	 * @return the alertaCuotaTraficoJson
	 */
	public String getAlertaCuotaTraficoJson() {
		return alertaCuotaTraficoJson;
	}	

	/**
	 * @return the errorAlertaCuotaTrafico
	 */
	public boolean isErrorAlertaCuotaTrafico() {
		return errorAlertaCuotaTrafico;
	}

	/**
	 * @param errorAlertaCuotaTrafico the errorAlertaCuotaTrafico to set
	 */
	public void setErrorAlertaCuotaTrafico(boolean errorAlertaCuotaTrafico) {
		this.errorAlertaCuotaTrafico = errorAlertaCuotaTrafico;
	}

	/**
	 * @param alertaCuotaTraficoJson the alertaCuotaTraficoJson to set
	 */
	public void setAlertaCuotaTraficoJson(String alertaCuotaTraficoJson) {
		this.alertaCuotaTraficoJson = alertaCuotaTraficoJson;
	}

	public void init(PhaseEvent event) {

        try {
            loadData();
        } catch (DAOException e) {
        	LOGGER.error("DAOException caught: "+e.getMessage(), e);
            traficoEnLineaJson = JsonHelper
                    .toJsonErrorMessage("El servicio no est&aacute; disponible de momento."
                            + " Por favor intente m&aacute;s tarde");

        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            traficoEnLineaJson = JsonHelper.toJsonServiceErrorMessage(
                    "gestionRegistroUso", e.getCodigoRespuesta());

        } catch (Exception e) {
            LOGGER.error("Exception caught: "+e.getMessage(), e);
            traficoEnLineaJson = JsonHelper
                    .toJsonErrorMessage("Ha ocurrido un error inesperado. "
                            + " Disculpe las molestias");
        }
    }

    private void loadData() throws Exception {
        ProfileWrapper profile = ProfileWrapperHelper
                .getProfile(JSFPortletHelper.getRequest());

        String numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,
                "numeroPcsSeleccionado");
        this.traficoEnLinea = this.traficoDelegate.getTraficoEnLinea(numeroPcs);
        // ordenar la lista de forma desc y tomar los 5 primeros
        Collections.<TraficoEnLineaBean> sort(this.traficoEnLinea);

        for (int i = 0; i < this.traficoEnLinea.size() && i < 5; i++) {
        
            TraficoEnLineaBean traficoEnLineaBean = this.traficoEnLinea.get(i);
            
            traficoEnLineaBean.setFechaFormated(DateHelper.format(
                    traficoEnLineaBean.getFecha(), "dd-MM-yyyy / HH:mm"));
            
            traficoEnLineaBean
                    .setValorFormated(Utils.formatMoney((traficoEnLineaBean
                            .getValor())));
            
            if ("VOZ".equals(traficoEnLineaBean.getTipoTrafico())) {

                //Converter TraficoVozConverter trabaja con valores en minutos
                //Trafico en linea retorna consumo de voz en segundos
                Double consumo = traficoEnLineaBean.getConsumoTraficoVoz() / 60;
                traficoEnLineaBean.setConsumoFormated(JsfUtil
                        .getAsConvertedString(consumo, "traficoVozConverter")
                        + " min");
            }
            else {
                traficoEnLineaBean.setConsumoFormated(JsfUtil
                        .getAsConvertedString(traficoEnLineaBean
                                .getConsumoTraficoDato(),
                                "traficoDatosConverter")
                        + " kb");
            }

        }

        this.traficoEnLinea = this.traficoEnLinea != null
                && this.traficoEnLinea.size() > 5 ? this.traficoEnLinea
                .subList(0, 5) : this.traficoEnLinea;
        this.traficoEnLineaJson = JsonHelper.toJsonResponse(this.traficoEnLinea);

    }
    
    public void initTraficoEnLinea(PhaseEvent event) {
    	String numeroPcs = "";
        try {

            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,"numeroPcsSeleccionado");
            
            this.traficoEnLineaPPCC = this.traficoDelegate.getTraficoEnLineaPPCC(numeroPcs,"1");
            
            if( this.traficoEnLineaPPCC != null ){
	            if( !this.traficoEnLineaPPCC.isEmpty() && this.traficoEnLineaPPCC.size() > 0 ){
		            Collections.<TraficoEnLineaPPCCBean> sort(this.traficoEnLineaPPCC);
		            this.traficoEnLineaPPCC = this.traficoEnLineaPPCC != null
		                    && this.traficoEnLineaPPCC.size() > 5 ? this.traficoEnLineaPPCC
		                    .subList(0, 5) : this.traficoEnLineaPPCC;
		            this.traficoEnLineaJson = JsonHelper.toJsonResponse(this.traficoEnLineaPPCC);
	            }else{
	            	traficoEnLineaJson = JsonHelper.toJsonErrorMessage("En este momento usted no cuenta con tr&aacute;fico registrado");
	            }
            }else{
            	traficoEnLineaJson = JsonHelper.toJsonErrorMessage("La informaci&oacute;n de tr&aacute;fico no se encuentra disponible en el momento.");
            }
        } catch (DAOException e) {
        	LOGGER.error("DAOException inesperado al obtener datos del plan", e);
            traficoEnLineaJson = JsonHelper
                    .toJsonErrorMessage("El servicio no est&aacute; disponible de momento."
                            + " Por favor intente m&aacute;s tarde");
            
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio. msisdn: "+numeroPcs+" - codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            traficoEnLineaJson = JsonHelper.toJsonServiceErrorMessage(
                    "gestionRegistroUso", e.getCodigoRespuesta());

        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener datos del plan", e);
            traficoEnLineaJson = JsonHelper
                    .toJsonErrorMessage("Ha ocurrido un error inesperado. "
                            + " Disculpe las molestias");
        }
    }
    
    
    /**
     * 
     * @return detalleTraficoEnLineavv
     * @throws Exception
     */
    public List<TraficoEnLineaBean> getDetalleTraficoEnLinea(){
    	String numeroPcs = "";
    	try{
        if( detalleTraficoEnLinea == null ){
            ProfileWrapper profile = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,
            "numeroPcsSeleccionado");
            detalleTraficoEnLinea = traficoDelegate.getTraficoEnLinea(numeroPcs);
            Collections.<TraficoEnLineaBean> sort(this.detalleTraficoEnLinea);    
            
        }
        } catch (DAOException e) {
            LOGGER.error("DAOException al Obtener detalle Trafico en Linea.", e);
            //TODO cambiar a mensaje en properties
            JSFMessagesHelper
                    .addErrorMessage("El servicio no esta disponible en este momento");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al Obtener detalle Trafico en Linea. msisdn: "+numeroPcs);
            JSFMessagesHelper.addErrorCodeMessage("gestionRegistroUso", e.getCodigoRespuesta());
        }catch (Exception e) {
            LOGGER.error("Exception inesperada al Obtener detalle Trafico en Linea.", e);
            //TODO cambiar a mensaje en properties
            JSFMessagesHelper
                    .addErrorMessage("El servicio no esta disponible en este momento");

        }
        return detalleTraficoEnLinea;
    }
    

    private void loadDataDetalleTraficoEnLineaPPCC(String numPagina) throws Exception {
		
    	detalleTraficoEnLineaPPCC = new LinkedList<TraficoEnLineaPPCCBean>();
    	
       
        ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        String numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,"numeroPcsSeleccionado");

        traficoEnLineaPPCC = traficoDelegate.getTraficoEnLineaPPCC(numeroPcs , numPagina);
        
        Collections.<TraficoEnLineaPPCCBean> sort(traficoEnLineaPPCC);

	    for (int i = 0; i < traficoEnLineaPPCC.size() && i <= 25; i++) {
	        detalleTraficoEnLineaPPCC.add(traficoEnLineaPPCC.get(i));
	    }
        
        this.setDetalleTraficoEnLineaPPCC(detalleTraficoEnLineaPPCC);
        this.traficoEnLineaPPCCJson = JsonHelper.toJsonResponse(detalleTraficoEnLineaPPCC);
    }
    
    /**
     * 
     * @return 
     * @throws Exception
     */
    public void initDetalleTraficoEnLineaPPCC(PhaseEvent event){
    	
    	try {
    		
    		numPagina = JsfUtil.getRequestParameter("numPagina");
    		loadDataDetalleTraficoEnLineaPPCC(numPagina);
	            
        }catch (DAOException e) {
        	LOGGER.error("DAOException caught: "+e.getMessage(), e);
            traficoEnLineaPPCCJson = JsonHelper
            .toJsonErrorMessage("El servicio no est&aacute; disponible de momento."
                    + " Por favor intente m&aacute;s tarde");

		} catch (ServiceException e) {
		    LOGGER.info("ServiceException caught codigo: " + e.getCodigoRespuesta()
		            + " - " + e.getDescripcionRespuesta());
		    traficoEnLineaPPCCJson = JsonHelper.toJsonServiceErrorMessage(
		            "gestionRegistroUso", e.getCodigoRespuesta());
		
		} catch (Exception e) {
		    LOGGER.error("Exception inesperado al obtener datos del plan", e);
		    traficoEnLineaPPCCJson = JsonHelper
		            .toJsonErrorMessage("Ha ocurrido un error inesperado. "
		                    + " Disculpe las molestias");
		}

    }
    
    /**
     * 
     * @return 
     * @throws Exception
     */
    public void initTraficoEnLineaPPCC(PhaseEvent event){
    	String numeroPcs = "";
    	try {
    		ServiceMessages serviceMessages = MiEntelProperties.getServiceMessages();
    		
    		numPagina = "1";
    		this.errorDetalleTraficoEnLineaPPCC = false;
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,"numeroPcsSeleccionado");
            
           traficoEnLineaPPCC = traficoDelegate.getTraficoEnLineaPPCC(numeroPcs , numPagina);
           
           if( this.traficoEnLineaPPCC != null ){
	            if(!traficoEnLineaPPCC.isEmpty() && traficoEnLineaPPCC.size() > 0 ){
		            Collections.<TraficoEnLineaPPCCBean> sort(traficoEnLineaPPCC);
		            this.paginaTotal = new ArrayList<TraficoEnLineaPPCCBean>();
		            for (int i = 1; i <= Integer.parseInt(traficoEnLineaPPCC.get(0).getPaginas()); i++) {
		            	TraficoEnLineaPPCCBean traficoEnLineaPPCCBean = new TraficoEnLineaPPCCBean();
		            	traficoEnLineaPPCCBean.setPaginas(String.valueOf(i));
		                this.paginaTotal.add(traficoEnLineaPPCCBean);
		            }
	            }else{
	            	this.errorDetalleTraficoEnLineaPPCC = true;
	            	//this.errorMessage = "En este momento usted no cuenta con trafico registrado";
	            	this.errorMessage = serviceMessages.getErrorCodeMessage("noTieneTraficoRegistrado", "detalleTraficoEnLineaPPCC");
	            }
           }else{
	        	this.errorDetalleTraficoEnLineaPPCC = true;
	        	//this.errorMessage = "La informacion de trafico no se encuentra disponible en el momento.";
	        	this.errorMessage = serviceMessages.getErrorCodeMessage("traficoNoDisponible", "detalleTraficoEnLineaPPCC");

           }    
        }catch (DAOException e) {
        	LOGGER.error("DAOException caught: "+e.getMessage(), e);
        	this.errorDetalleTraficoEnLineaPPCC = true;
            traficoEnLineaPPCCJson = JsonHelper
            .toJsonErrorMessage("El servicio no est&aacute; disponible de momento."
                    + " Por favor intente m&aacute;s tarde");
            
		} catch (ServiceException e) {
		    LOGGER.info("ServiceException caught msisdn: "+numeroPcs+" - " + e.getCodigoRespuesta()
		            + " - " + e.getDescripcionRespuesta());
		    this.errorDetalleTraficoEnLineaPPCC = true;
		    traficoEnLineaPPCCJson = JsonHelper.toJsonServiceErrorMessage(
		            "gestionRegistroUso", e.getCodigoRespuesta());
		    
		} catch (Exception e) {
		    LOGGER.error("Exception caught: "+e.getMessage(), e);
		    this.errorDetalleTraficoEnLineaPPCC = true;
		    traficoEnLineaPPCCJson = JsonHelper
		            .toJsonErrorMessage("Ha ocurrido un error inesperado. "
		                    + " Disculpe las molestias");
		    
		}

    }

    public void exportXLS() {
        
        OutputStream out 	= null;
        int fila 			= 0;

        
        try {
             
            HttpServletResponse response = JSFPortletHelper.getResponse();
            
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            String numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,"numeroPcsSeleccionado");
            

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=traficoEnLinea.xls");

        	
            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet s = w.createSheet("Trafico", 0);

            s.addCell(new Label(0, fila, "Tipo"));
            s.addCell(new Label(1, fila, "Destino o Emision"));
            s.addCell(new Label(2, fila, "Fecha/Hora"));
            s.addCell(new Label(3, fila, "Duracion"));
            s.addCell(new Label(4, fila, "Unidad"));
            s.addCell(new Label(5, fila, "Costo"));
            s.addCell(new Label(6, fila, "Saldo"));


            traficoEnLineaPPCC = traficoDelegate.getTraficoEnLineaPPCC(numeroPcs , "0");
            Iterator iter = traficoEnLineaPPCC.iterator();
            if (iter.hasNext()){
                fila = detalleXLS(iter, s, fila);
            }else{
                w.write();
                w.close();
                //return null;
            }
            w.write();
            w.close();
        } catch (Exception e) {
        	LOGGER.error("No ha sido posible descargar el archivo excel ", e);
        } finally {
            if (out != null){
                try {
                    out.close();
                }
                catch(IOException io) {
                }
            }
        }
        //return null;
    }
    
    public int detalleXLS(Iterator iter, WritableSheet s, int fila) {
        while (iter.hasNext()) {
            fila++;
            TraficoEnLineaPPCCBean elemento = (TraficoEnLineaPPCCBean)iter.next();

            try {
                s.addCell(new Label(0, fila, elemento.getTipoServicio().replaceAll("&nbsp;", "")));
                s.addCell(new Label(1, fila, elemento.getDestino().replaceAll("&nbsp;", "")));
                s.addCell(new Label(2, fila, elemento.getFechaLlamada().replaceAll("&nbsp;", "")+" - "+ elemento.getHoraLlamada().replaceAll("&nbsp;", "")));
                s.addCell(new Label(3, fila, elemento.getDuracion().replaceAll("&nbsp;", "")));
                s.addCell(new Label(4, fila, elemento.getUnidad().replaceAll("&nbsp;", "")));
                s.addCell(new Label(5, fila, elemento.getCosto().replaceAll("&nbsp;", "")));
                s.addCell(new Label(6, fila, elemento.getSaldo().replaceAll("&nbsp;", "")));
            } catch (java.util.NoSuchElementException e) {
                LOGGER.info("NO EXITEN MAS ELEMENTOS"); 
            } catch (Exception ee) {
                LOGGER.error(ee);
            }
        }
        return fila;
    }

    /**
     * @author Wilson Brochero Munoz
     * @param  event    
     * @return 
     * @throws Exception
     * @throws ServiceException
     * @throws DAOException
     */
    public void initHistoricoAlertaCuotaTrafico(PhaseEvent event){
    	String numeroPcs = "";
    	ServiceMessages serviceMessages = MiEntelProperties.getServiceMessages();  
    	try {
    		  		
    		String topeRegistrosPaginaACT = null;
    		
    		   try{
    			   topeRegistrosPaginaACT = MiEntelProperties.getProperty("parametro.ACT.topeRegistrosPagina");
    		    }catch(NullPointerException  e){
    		    	topeRegistrosPaginaACT="25";
    		    }
    		    if(topeRegistrosPaginaACT=="0"){
    		    	topeRegistrosPaginaACT="25";
    		    }
    		
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,"numeroPcsSeleccionado");
            
            alertaCuotaTrafico = traficoDelegate.getHistoricoAlertaCuotaTrafico(numeroPcs);
           
           if( this.alertaCuotaTrafico != null ){
	            if(!alertaCuotaTrafico.isEmpty() && alertaCuotaTrafico.size() > 0 ){
		            Collections.<AlertaCuotaTraficoBean> sort(alertaCuotaTrafico);
		            
		            double registros = alertaCuotaTrafico.size();
	    		    int registrosPagina = Integer.valueOf(topeRegistrosPaginaACT);
	    		    
	    		    this.paginasTotalACT = String.valueOf( (int) Math.ceil(registros/registrosPagina));
		           
	            }else{
	            	this.errorAlertaCuotaTrafico = true;
	            	//this.errorMessage = "En este momento usted no cuenta con trafico registrado";
	            	this.errorMessage = serviceMessages.getErrorCodeMessage("noTieneAlertasCuotaTraficoRegistradas", "detalleAlertasCuotaTrafico");
	            }
           }else{
	        	this.errorAlertaCuotaTrafico = true;
	        	//this.errorMessage = "La informacion de trafico no se encuentra disponible en el momento.";
	        	this.errorMessage = serviceMessages.getErrorCodeMessage("alertasCuotaTraficoNoDisponible", "detalleAlertasCuotaTrafico");

           }
        }catch (DAOException e) {
        	LOGGER.error("DAOException caught: "+e.getMessage(), e);
        	this.errorAlertaCuotaTrafico = true;
        	this.errorMessage  = serviceMessages.getErrorCodeMessage("noDisponible", "general");
            
		} catch (ServiceException e) {
		    LOGGER.info("ServiceException caught msisdn: "+numeroPcs+" - " + e.getCodigoRespuesta()
		            + " - " + e.getDescripcionRespuesta());
		    this.errorAlertaCuotaTrafico = true;
		    this.errorMessage = serviceMessages.getErrorCodeMessage(e.getCodigoRespuesta(), "detalleAlertasCuotaTrafico"); 
		    
		} catch (Exception e) {
		    LOGGER.error("Exception caught: "+e.getMessage(), e);
		    this.errorAlertaCuotaTrafico = true;
		    this.errorMessage="Ha ocurrido un error inesperado. "
		                    + " Disculpe las molestias";
		}

    }   
   
    
    /**
     * 
     * @return 
     * @throws Exception
     */
    public void initDetalleHistoricoAlertaCuotaTrafico(PhaseEvent event){
    	
    	try {
    		
    		numPagina = JsfUtil.getRequestParameter("numPagina");
    		loadDataDetalleHistoricoAlertaCuotaTrafico(numPagina);
	            
        }catch (DAOException e) {
        	LOGGER.error("DAOException caught: "+e.getMessage(), e);
        	alertaCuotaTraficoJson = JsonHelper
            .toJsonErrorMessage("El servicio no est&aacute; disponible de momento."
                    + " Por favor intente m&aacute;s tarde");

		} catch (ServiceException e) {
		    LOGGER.info("ServiceException caught codigo: " + e.getCodigoRespuesta()
		            + " - " + e.getDescripcionRespuesta());
		    alertaCuotaTraficoJson = JsonHelper.toJsonServiceErrorMessage(
		            "gestionRegistroUso", e.getCodigoRespuesta());
		
		} catch (Exception e) {
		    LOGGER.error("Exception inesperado al obtener datos del plan", e);
		    alertaCuotaTraficoJson = JsonHelper
		            .toJsonErrorMessage("Ha ocurrido un error inesperado. "
		                    + " Disculpe las molestias");
		}

    }
    
 private void loadDataDetalleHistoricoAlertaCuotaTrafico(String numPagina) throws Exception {
		
	   detalleAlertaCuotaTrafico = new LinkedList<AlertaCuotaTraficoBean>();  
	   
	   String topeRegistrosPaginaACT = null;
	   int registrosPagina =25;
	   try{
		   topeRegistrosPaginaACT = MiEntelProperties.getProperty("parametro.ACT.topeRegistrosPagina");		   
		   registrosPagina = Integer.valueOf(topeRegistrosPaginaACT);
		   
	    }catch(Exception  e){
	    	registrosPagina=25;
	    }
	    if(topeRegistrosPaginaACT=="0" || topeRegistrosPaginaACT==null){
	    	registrosPagina=25;
	    }
	    
        ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        String numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile,"numeroPcsSeleccionado");
        
        alertaCuotaTrafico = traficoDelegate.getHistoricoAlertaCuotaTrafico(numeroPcs);   
        
        Collections.<AlertaCuotaTraficoBean> sort(alertaCuotaTrafico);

	    for (int i = 0; i < alertaCuotaTrafico.size() && i < registrosPagina; i++) {
	        detalleAlertaCuotaTrafico.add(alertaCuotaTrafico.get(i));
	    }
        
        this.setDetalleAlertaCuotaTrafico(detalleAlertaCuotaTrafico);
        this.alertaCuotaTraficoJson = JsonHelper.toJsonResponse(detalleAlertaCuotaTrafico);
    } 
    
    
    
    
}
