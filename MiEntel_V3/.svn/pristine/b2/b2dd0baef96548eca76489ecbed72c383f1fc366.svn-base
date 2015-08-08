/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import javax.faces.event.PhaseEvent;
import org.apache.log4j.Logger;
import org.ujac.util.StringUtils;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MiTraficoBamBean;
import com.epcs.bean.ResumenTraficoBamBean;
import com.epcs.bean.TraficoBamProductoBean;
import com.epcs.billing.registrouso.delegate.TraficoBamDelegate;
import com.epcs.cliente.perfil.util.PlanHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jroman (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class MiTraficoBamController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = Logger
    .getLogger(MiTraficoBamController.class);

    private TraficoBamDelegate traficoBamDelegate;

    
    private ResumenTraficoBamBean resumenTraficoBam;
    
    private MiTraficoBamBean miTraficoBam;
    
    private Integer porcentajeUtilizado;
    
    private String cuotaRestante; 
    private String cuotaRestanteUnidad; 
    
    private String velocidadActual;
    private String velocidadActualUnidad;
    
    public String getVelocidadActual() {
		return velocidadActual;
	}


	public void setVelocidadActual(String velocidadActual) {
		this.velocidadActual = velocidadActual;
	}


	public String getVelocidadActualUnidad() {
		return velocidadActualUnidad;
	}


	public void setVelocidadActualUnidad(String velocidadActualUnidad) {
		this.velocidadActualUnidad = velocidadActualUnidad;
	}
    
    
    public void init(PhaseEvent event) {

        String numeroPcsSeleccionado = "";
        String autoAtencion = "";
        String mercado = "";
        
        try {
            
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcsSeleccionado");
            autoAtencion = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "aaa");
            mercado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "mercado");
                        
            //Se indica al metodo que NO REGISTRE en el log de la caja de Facturacion del Dashboard 
        	boolean logCajaFacturacion = false;
            resumenTraficoBam = this.traficoBamDelegate.getResumenTraficoBam(
                    numeroPcsSeleccionado,mercado, autoAtencion, logCajaFacturacion);

            if( resumenTraficoBam != null ){
          
            	miTraficoBam = new MiTraficoBamBean();
            	miTraficoBam.setUmbralFairUseGB(parseInt(resumenTraficoBam.getUmbralFairUseGB()));
            	miTraficoBam.setVelocidadMaxPlan(resumenTraficoBam.getVelocidadMaxPlan());
            	miTraficoBam.setCuotaRestanteMB(resumenTraficoBam.getCuotaRestanteMB().intValue());
            	miTraficoBam.setVelocidadActual(resumenTraficoBam.getVelocidadActual());
               	for(TraficoBamProductoBean traficoBamProductoBean : resumenTraficoBam.getTraficoBamProductoBeanList() ){
            		if(StringUtils.equals(traficoBamProductoBean.getIdProducto(), MiEntelProperties.getProperty("idProducto.traficoBam"))){
            			miTraficoBam.setCuotaUtilizada(Double.parseDouble(traficoBamProductoBean.getKey1()));
            			miTraficoBam.setNombrePlan(PlanHelper.extraerNombrePlan(traficoBamProductoBean.getNombrePlan()));
            		}
            	}
               	
               	String umbralFairUSe = resumenTraficoBam.getUmbralFairUseMB();
               	double umbralFair = Double.parseDouble(umbralFairUSe);
               	configurarUmbral(umbralFair);
               	
               	calculatePorcentajeUtilizado();
            	calculateCuotaRestante();
            	calculateVelocidadActual();
            	
            }

        } catch (DAOException e) {
            LOGGER.error("DAOException al buscar otros datos de Usuario", e);
            JSFMessagesHelper.addServiceErrorMessage("noDisponible");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al buscar otros datos de Usuario. msisdn: "+numeroPcsSeleccionado);
            JSFMessagesHelper.addErrorCodeMessage("gestionRegistroUso", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception no esperada al buscar datos de usuario", e);
            JSFMessagesHelper.addServiceErrorMessage("inesperado");
        }
    }


	/**
	 * Metodo que retorna la cantidad de cuota de navegacion utilizada en la
	 * unidad adecuada
	 * 
	 * @return - Cuota restante
	 */
	public String getCuotaRestante() {
	    return cuotaRestante;
	}
	
	  /**
	 * Metodo que retorna la unidad de cuota de navegacion utilizada en la
	 * unidad adecuada
	 * 
	 * @return - Unidad Cuota restante
	 */
	public String getCuotaRestanteUnidad() {
	    return cuotaRestanteUnidad;
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

            miTraficoBam.setUmbralFairUse(String.valueOf(cuota));
            miTraficoBam.setUnidadUmbralFairUse("KB");
        }
        else if (cuotaNumerica < 1024 && cuotaNumerica >= 1) { // La unidad son
                                                               // MB
        	miTraficoBam.setUmbralFairUse(String.valueOf(cuotaNumerica
                    .intValue()));
        	miTraficoBam.setUnidadUmbralFairUse("MB");
        }
        else if (cuotaNumerica >= 1024) { // La unidad son GB
            cuota = cuotaNumerica / 1024;

            DecimalFormat unDecimal = new DecimalFormat("#.#");
            miTraficoBam.setUmbralFairUse(String.valueOf(unDecimal
                    .format(cuota)));
            miTraficoBam.setUnidadUmbralFairUse("GB");

        }
    }
	
	/**
     * Metodo que retorna la cantidad de cuota de navegacion utilizada en la
     * unidad adecuada
     * 
     * @return - Cuota restante
     */
    private void calculateCuotaRestante() {
        StringBuffer cuotaString = new StringBuffer();
        Float cuotaNumerica = resumenTraficoBam.getCuotaRestanteMB();

        if (cuotaNumerica < 1) { // La unidad son KB
            cuotaNumerica = cuotaNumerica * 1024;

            cuotaString.append(cuotaNumerica.intValue());
            cuotaRestanteUnidad="KB";
        }

        if (cuotaNumerica < 1024 && cuotaNumerica >= 1) { // La unidad son MB
            cuotaString.append(cuotaNumerica.intValue());
            cuotaRestanteUnidad="MB";
        }

        if (cuotaNumerica >= 1024) { // La unidad son GB
            cuotaNumerica = cuotaNumerica / 1024;

            DecimalFormat unDecimal = new DecimalFormat("#.#");
            cuotaString.append(unDecimal.format(cuotaNumerica));
            cuotaRestanteUnidad="GB";
        }
        cuotaRestante = cuotaString.toString();
    }
	

	/**
	 * Metodo que retorna la velocidad actual
	 * @return
	 */
	
	private void calculateVelocidadActual() {
        Integer vel =  Integer.parseInt(miTraficoBam.getVelocidadActual());
	
        if (vel>=1024){
        	velocidadActualUnidad="Mbps";
        	vel=vel/1024;
        	velocidadActual=vel.toString();
        }else{
        	velocidadActualUnidad="Kbps";
        	velocidadActual=vel.toString();
        }        
    }
	
	/**
	  * Retorna el porcentaje utilizado en Mod 20.
	  * @return
	  */
	 public Integer getPorcentajeUtilizado() {
		 return porcentajeUtilizado;
	 }
	 
	 /**
      * Retorna el porcentaje utilizado en Mod 20.
      * @return
      */
     private void calculatePorcentajeUtilizado() {
         if(resumenTraficoBam.getPorcentajeRestante() != null ){
             porcentajeUtilizado = 100 - ( resumenTraficoBam.getPorcentajeRestante().intValue() - ( resumenTraficoBam.getPorcentajeRestante().intValue() % 20));  
         }else{
             porcentajeUtilizado = 0;
         }
     }
     
     
     /**
      * Metodo que se encarga de obtener y retornar el valor de la preferencia
      * del portlet en solicitud dependiendo del mercado como clave asociada al
      * valor.
      * 
      * @return
      */
     public String getPageLabelCambioPlan() {
         String pageLabel;
         try {

             final ProfileWrapper profileWrapper = ProfileWrapperHelper
                     .getProfile(JSFPortletHelper.getRequest());

             pageLabel = JSFPortletHelper.getPreference(JSFPortletHelper
                     .getPreferencesObject(), ProfileWrapperHelper
                     .getPropertyAsString(profileWrapper, "mercado"), null);

         } catch (Exception e) {
             LOGGER.error("No se ha podido obtener el pageLabel"
                     + e.getMessage(), e);
             pageLabel = "";
         }
         return pageLabel;
     }
    
	   /**
     * Metodo privado utilitario para parsear los atributos 
     */

    private int parseInt(String string){
    	return Integer.parseInt(string);
    }
    
    
	/**
	 * @return the resumenTraficoBam
	 */
	public ResumenTraficoBamBean getResumenTraficoBam() {
		return resumenTraficoBam;
	}

	/**
	 * @param resumenTraficoBam the resumenTraficoBam to set
	 */
	public void setResumenTraficoBam(ResumenTraficoBamBean resumenTraficoBam) {
		this.resumenTraficoBam = resumenTraficoBam;
	}

	/**
	 * @return the traficoBamDelegate
	 */
	public TraficoBamDelegate getTraficoBamDelegate() {
		return traficoBamDelegate;
	}

	/**
	 * @param traficoBamDelegate the traficoBamDelegate to set
	 */
	public void setTraficoBamDelegate(TraficoBamDelegate traficoBamDelegate) {
		this.traficoBamDelegate = traficoBamDelegate;
	}
	 /**
	 * @return the miTraficoBam
	 */
	public MiTraficoBamBean getMiTraficoBam() {
		return miTraficoBam;
	}

	/**
	 * @param miTraficoBam the miTraficoBam to set
	 */
	public void setMiTraficoBam(MiTraficoBamBean miTraficoBam) {
		this.miTraficoBam = miTraficoBam;
	}


}
