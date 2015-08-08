/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.controller;

import java.io.Serializable;
import java.text.DecimalFormat;


import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;
import org.ujac.util.StringUtils;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResumenTraficoBamBean;
import com.epcs.bean.TraficoBamProductoBean;
import com.epcs.bean.TraficoBamSSBean;
import com.epcs.billing.registrouso.delegate.TraficoBamDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.error.ServiceMessages;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jroman (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoBamController implements Serializable {

    private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger
    .getLogger(TraficoBamController.class);
	
	private static final Logger CAJA_TRAFICO_LOGGER = Logger.getLogger("cajaTraficoLog");

	private TraficoBamDelegate traficoBamDelegate;

	private ResumenTraficoBamBean resumenTraficoBam;

	private TraficoBamSSBean traficoBamSS;

	private String traficoBamSSJson;
 
	 public void init(PhaseEvent event) {

	        String numeroPcsSeleccionado = "";
	        String autoAtencion = "";
	        String mercado = "";
	        String rutSeleccionado = "";

	        try {
	            
	            ProfileWrapper profileWrapper = ProfileWrapperHelper
	                    .getProfile(JSFPortletHelper.getRequest());
	            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
	                    profileWrapper, "numeroPcsSeleccionado");
	            rutSeleccionado = ProfileWrapperHelper.getPropertyAsString(
	                    profileWrapper, "rutUsuarioSeleccionado");
	            autoAtencion = ProfileWrapperHelper.getPropertyAsString(
	                    profileWrapper, "aaa");
	            mercado = ProfileWrapperHelper.getPropertyAsString(
	                    profileWrapper, "mercado");
	                        
	            CAJA_TRAFICO_LOGGER.info("INICIO REGISTRO LOG CAJA TRAFICO BAM");
	        	CAJA_TRAFICO_LOGGER.info("****** Clase: com.epcs.billing.registrouso.controller.TraficoBamController ******");            
	            CAJA_TRAFICO_LOGGER.info("Movil: " + numeroPcsSeleccionado + " - Rut: " + rutSeleccionado);
	            
	            //Se indica al metodo que REGISTRE en el log de la caja de Facturacion del Dashboard 
	        	boolean logCajaFacturacion = true;
	            resumenTraficoBam = this.traficoBamDelegate.getResumenTraficoBam(
	                    numeroPcsSeleccionado,mercado, autoAtencion, logCajaFacturacion);
	            
	            if( resumenTraficoBam == null ){
	            	ServiceMessages errorMessages = MiEntelProperties.getServiceMessages();
			        CAJA_TRAFICO_LOGGER.warn(errorMessages.getErrorMessage("noinfoservicios"));
			        
	            	traficoBamSSJson = JsonHelper
	                .toJsonServiceErrorMessage("noinfoservicios");
	            }else{          
	            	traficoBamSS = new TraficoBamSSBean();
	            	//traficoBamSS.setCuotaUmbralFairUSe(parseInt(resumenTraficoBam.getUmbralFairUseGB()));
	            	
	            	
	               	String umbralFairUSe = resumenTraficoBam.getUmbralFairUseMB();
	               	double umbralFair = Double.parseDouble(umbralFairUSe);
	               	configurarUmbral(umbralFair);
	               	traficoBamSS.setCuotaRestante(calcularCuotaRestante());
	            	
	            	for(TraficoBamProductoBean traficoBamProductoBean : resumenTraficoBam.getTraficoBamProductoBeanList() ){
	            		if(StringUtils.equals(traficoBamProductoBean.getIdProducto(), MiEntelProperties.getProperty("idProducto.traficoBam"))){
	            			traficoBamSS.setCuotaUtilizada(Double.parseDouble(traficoBamProductoBean.getKey1()));
	            			traficoBamSS.setFechaRegistro(traficoBamProductoBean.getFechaRegistro());
	            			traficoBamSS.setFechaRegistroFormated(DateHelper.format(traficoBamProductoBean.getFechaRegistro(),"'al' dd-MM-yyyy"));
	            		}
	            	}
	                traficoBamSSJson = JsonHelper.toJsonResponse(traficoBamSS);
	            }
	            
	            CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO BAM");

	        } catch (DAOException e) {
	        	CAJA_TRAFICO_LOGGER.error("DAOException caught", e);
	        	CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO BAM");
	            //mensaje de "No disponible"
	            traficoBamSSJson = JsonHelper
	                    .toJsonServiceErrorMessage("noDisponible");

	        } catch (ServiceException e) {
	        	CAJA_TRAFICO_LOGGER.info("ServiceException caught: msisdn: "+ numeroPcsSeleccionado + " - " + e.getCodigoRespuesta()
	                    + " - " + e.getDescripcionRespuesta());
	        	CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO BAM");
	            traficoBamSSJson = JsonHelper.toJsonServiceErrorMessage(
	                    "gestionRegistroUso", e.getCodigoRespuesta());

	        } catch (Exception e) {
	        	CAJA_TRAFICO_LOGGER.error("Exception inesperado al obtener datos del plan", e);
	        	CAJA_TRAFICO_LOGGER.info("FIN REGISTRO LOG CAJA TRAFICO BAM");
	            traficoBamSSJson = JsonHelper
	                    .toJsonServiceErrorMessage("inesperado");

	        }
	    }	
	
	 
	  /**
	     * Metodo que retorna la cantidad de cuota de navegacion utilizada en la
	     * unidad adecuada
	     * 
	     * @return - Cuota restante
	     */
	    private void configurarUmbral(final Double cuotaNumerica) {
	        double cuota = 0;
	        if (cuotaNumerica < 1) { // La unidad son KB
	            cuota = cuotaNumerica * 1024;

	            traficoBamSS.setUmbralFairUse(String.valueOf(cuota));
	            traficoBamSS.setUnidadUmbralFairUse("KB");
	        }
	        else if (cuotaNumerica < 1024 && cuotaNumerica >= 1) { // La unidad son
	                                                               // MB
	        	traficoBamSS.setUmbralFairUse(String.valueOf(cuotaNumerica
	                    .intValue()));
	        	traficoBamSS.setUnidadUmbralFairUse("MB");
	        }
	        else if (cuotaNumerica >= 1024) { // La unidad son GB
	            cuota = cuotaNumerica / 1024;

	            DecimalFormat unDecimal = new DecimalFormat("#.#");
	            traficoBamSS.setUmbralFairUse(String.valueOf(unDecimal
	                    .format(cuota)));
	            traficoBamSS.setUnidadUmbralFairUse("GB");

	        }
	    }
	 
		/**
		 * Metodo que retorna la cantidad de cuota de navegacion utilizada en la
		 * unidad adecuada
		 * 
		 * @return - Cuota restante
		 */
		public String calcularCuotaRestante() {
			StringBuffer cuotaString = new StringBuffer();
			Float cuotaNumerica = resumenTraficoBam.getCuotaRestanteMB();

			if (cuotaNumerica < 1) { // La unidad son KB
				cuotaNumerica = cuotaNumerica * 1024;

				cuotaString.append(cuotaNumerica.intValue());
				cuotaString.append("KB");
			}

			if (cuotaNumerica < 1024 && cuotaNumerica >= 1) { // La unidad son MB
				cuotaString.append(cuotaNumerica.intValue());
				cuotaString.append("MB");
			}

			if (cuotaNumerica >= 1024) { // La unidad son GB
				cuotaNumerica = cuotaNumerica / 1024;

				DecimalFormat unDecimal = new DecimalFormat("#.#");
				cuotaString.append(unDecimal.format(cuotaNumerica));
				cuotaString.append("GB");
			}
			return cuotaString.toString();
		}
	 
	 
	/**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del mercado como clave asociada al valor.
     * @return
     */
    public String getPageLabelTraficoBam(){
        try{

          ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            
         return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                 profileWrapper, "mercado"), null);
         
        }catch(Exception e){
        	CAJA_TRAFICO_LOGGER.error("No se ha podido obtener el pageLabel "+ e.getMessage(), e);
            return "";
        }
    
    }

    /**
     * @return the resumenTraficoBam
     */
    public ResumenTraficoBamBean getResumenTraficoBam() {
        return resumenTraficoBam;
    }

    /**
     * @param resumenTraficoBam
     *            the resumenTraficoBam to set
     */
    public void setResumenTraficoBam(ResumenTraficoBamBean resumenTraficoBam) {
        this.resumenTraficoBam = resumenTraficoBam;
    }    
    
    /**
     * @return the traficoBamSS
     */
    public TraficoBamSSBean getTraficoBamSS() {
        return traficoBamSS;
    }

    /**
     * @param traficoBamSS
     *            the traficoBamSS to set
     */
    public void setResumenTraficoBam(TraficoBamSSBean traficoBamSS) {
        this.traficoBamSS = traficoBamSS;
    } 
    
    /**
     * @return the traficoBamDelegate
     */	
    public TraficoBamDelegate getTraficoBamDelegate() {
		return traficoBamDelegate;
	}

    /**
     * @param traficoBamDelegate
     *            the traficoBamDelegate to set
     */

	public void setTraficoBamDelegate(TraficoBamDelegate traficoBamDelegate) {
		this.traficoBamDelegate = traficoBamDelegate;
	}


    /**
     * @return the traficoBamSSJson
     */
    public String getTraficoBamSSJson() {
        return traficoBamSSJson;
    }

    /**
     * @param traficoBamSSJson
     *            the traficoBamSSJson to set
     */
    public void setTraficoBamSSJson(String traficoBamSSJson) {
        this.traficoBamSSJson = traficoBamSSJson;
    }

}
