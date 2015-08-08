/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.balance.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResumenFacturacionBean;
import com.epcs.billing.balance.delegate.FacturacionDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class FacturacionController implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(FacturacionController.class);
    private static final Logger FACTURACION_LOGGER = Logger.getLogger("cajaFacturacionLog");

    private ResumenFacturacionBean resumenFacturacionBean;
    
    private String jsonResumenFacturacion;

    // Delegate
    private FacturacionDelegate facturacionDelegate;

    public FacturacionController() {
        super();
    }

    /**
     * @return the facturacionDelegate
     */
    public FacturacionDelegate getFacturacionDelegate() {
        return facturacionDelegate;
    }

    /**
     * @param facturacionDelegate
     *            the facturacionDelegate to set
     */
    public void setFacturacionDelegate(FacturacionDelegate facturacionDelegate) {
        this.facturacionDelegate = facturacionDelegate;
    }

    /**
     * Metodo de inicializacion de controller
     * 
     * @return
     */
    public void init(PhaseEvent event) {
        
        try {
        	
        	FACTURACION_LOGGER.info("INICIO REGISTRO LOG CAJA FACTURACION");
            FACTURACION_LOGGER.info("****** Clase: com.epcs.billing.balance.controller.FacturacionController ******");
            loadData();
            FACTURACION_LOGGER.info("FIN REGISTRO LOG CAJA FACTURACION");
            
        } catch (DAOException e) {
        	FACTURACION_LOGGER.error("DAOException al obtener resumen de facturacion", e);
        	FACTURACION_LOGGER.info("FIN REGISTRO LOG CAJA FACTURACION");
            jsonResumenFacturacion = JsonHelper.toJsonErrorMessage("El servicio no est&aacute; disponible de momento." 
            + " Por favor intente m&aacute;s tarde");
            
        } catch (ServiceException e) {
        	FACTURACION_LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            FACTURACION_LOGGER.info("FIN REGISTRO LOG CAJA FACTURACION");
            jsonResumenFacturacion = JsonHelper.toJsonServiceErrorMessage("gestionBalance", e.getCodigoRespuesta());
           
        } catch (Exception e) {
        	FACTURACION_LOGGER.error("Exception inesperado al obtener resumen facturacion", e);
            FACTURACION_LOGGER.info("FIN REGISTRO LOG CAJA FACTURACION");
            jsonResumenFacturacion = JsonHelper.toJsonErrorMessage("Ha ocurrido un error inesperado. " 
                    + " Disculpe las molestias");
        }
    }
	
	/**
     * Metodo que permite obtener un flag booleano que indica si el atributo de
     * auto atencion del usuario actual corresponde a un usuario administrador
     * de cuentas que tiene derecho a presentar mas que el detalle de llamados
     * 
     * @return "true" si el usuario es aaa=3
     */
    public boolean isUserAdmin() {
        ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(JSFPortletHelper.getRequest());
        try {

            String codigoAAATitular = MiEntelProperties
                    .getProperty("aaa.titular.code");
            String userCodigoAAA = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "aaa");
            
            return codigoAAATitular.equals(userCodigoAAA);
        } catch (Exception e) {
            LOGGER.error("No se ha podido obtener el atributo " +
                    "de auto atencion para el usuario actual", e);
            return false;
        }
    }
    
    /**
     * Metodo que permite obtener un flag booleano que indica si el atributo de
     * auto atencion del usuario actual corresponde a un usuario intermedio
     * 
     * @return "true" si el usuario es aaa=2
     */
    public boolean isUserTotal() {
        ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(JSFPortletHelper.getRequest());
        try {

            String codigoAAATotal = MiEntelProperties
                    .getProperty("aaa.controlTotal.code");
            String userCodigoAAA = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "aaa");
            
            return codigoAAATotal.equals(userCodigoAAA);
        } catch (Exception e) {
            LOGGER.error("No se ha podido obtener el atributo " +
                    "de auto atencion para el usuario actual", e);
            return false;
        }
    }
    
    /**
     * Metodo que permite obtener un flag booleano que indica si el atributo de
     * auto atencion del usuario actual corresponde a un usuario parcial
     * 
     * @return "true" si el usuario es aaa=1
     */
    public boolean isUserParcial() {
        ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(JSFPortletHelper.getRequest());
        try {

            String codigoAAATotal = MiEntelProperties
                    .getProperty("aaa.controlParcial.code");
            String userCodigoAAA = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "aaa");
            
            return codigoAAATotal.equals(userCodigoAAA);
        } catch (Exception e) {
            LOGGER.error("No se ha podido obtener el atributo " +
                    "de auto atencion para el usuario actual", e);
            return false;
        }
    }
    
    
    /**
     * Metodo que permite obtener un flag booleano que indica si el atributo de
     * auto atencion del usuario actual corresponde a un usuario intermedio
     * 
     * @return "true" si el usuario es aaa=0
     */
    public boolean isUserConsulta() {
        ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(JSFPortletHelper.getRequest());
        try {

            String codigoAAAConsulta = MiEntelProperties
                    .getProperty("aaa.consulta.code");
            String userCodigoAAA = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "aaa");
            
            return codigoAAAConsulta.equals(userCodigoAAA);
        } catch (Exception e) {
            LOGGER.error("No se ha podido obtener el atributo " +
                    "de auto atencion para el usuario actual", e);
            return false;
        }
    }
    
	
	    /**
     * Metodo que permite obtener un tipo de dato string que indica el mercado del usuario actual
     * @return ss,cc o pp
     */
    public String getMercadoUser() {
        ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(JSFPortletHelper.getRequest());
        try {

            String mercado = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "mercado").toLowerCase();
            
            return mercado;
        } catch (Exception e) {
            LOGGER.error("No se ha podido obtener el mercado " +
                    " para el usuario actual", e);
            return "";
        }
    }
    
    /**
     * 
     * @return
     */
    public String getMensajePermiso(){
    	return  MiEntelProperties
        .getProperty("facturacion.usuario.consulta");
    }
    
	/**
     * Metodo que permite obtener un string que indica el codigo del atributo de
     * auto atencion del usuario actual
     * 
     * @return 3,2,1 o 0
     */
    public String getUserCodigoAAA() {
        ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(JSFPortletHelper.getRequest());
        try {
            String userCodigoAAA = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "aaa");
            
            return userCodigoAAA;
        } catch (Exception e) {
            LOGGER.error("No se ha podido obtener el codigo del atributo " +
                    "de auto atencion para el usuario actual", e);
            return "";
        }
    }
    
    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del mercado como clave asociada al valor.
     * @return
     */
    public String getPageLabelPagoEnLinea(){
    	try{
    	  ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
    		
		 return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                 profileWrapper, "mercado"), null);
		 
        }catch(Exception e){
        	LOGGER.error("No se ha podido obtener el pageLabel"+ e.getMessage(), e);
        	return "";
        }
    
    }
    
    private void loadData() throws DAOException, ServiceException, Exception {

        ProfileWrapper profileWrapper = ProfileWrapperHelper
                .getProfile(JSFPortletHelper.getRequest());
        String numeroPcs = ProfileWrapperHelper.getPropertyAsString(
                profileWrapper, "numeroPcs");
        String rut = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
                "rutTitular");
        String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(
                profileWrapper, "numeroCuenta");

        FACTURACION_LOGGER.info("Movil: " + numeroPcs + " - Rut: " + rut);
        
        //Se indica al metodo que REGISTRE en el log de la caja de Facturacion del Dashboard 
		boolean logCajaFacturacion = true;
        resumenFacturacionBean = facturacionDelegate.getResumenFacturacion(
                numeroPcs, rut, numeroCuenta, logCajaFacturacion);
        
        int ultimoDiaMes = getUltimoDiaMes(resumenFacturacionBean
                .getFechaPeriodo());
        
        /*resumenFacturacionBean.setTotalPagoFormated(Utils
                .formatMoney((resumenFacturacionBean.getTotalPago())));*/
        
        resumenFacturacionBean.setTotalPagoFormated(Utils.
        formatMoneyPuntos((resumenFacturacionBean.getTotalPago())));
        
        resumenFacturacionBean.setFechaEmisionFormated(DateHelper.format(
                resumenFacturacionBean.getFechaEmision(),
                "dd 'de' MMMM 'de' yyyy"));
        resumenFacturacionBean.setFechaVencimientoFormated(DateHelper.format(
                resumenFacturacionBean.getFechaVencimiento(),
                "dd 'de' MMMM 'de' yyyy"));
        resumenFacturacionBean.setFechaPeriodoFormated(DateHelper.format(
                resumenFacturacionBean.getFechaPeriodo(), " '01 al' "
                        + ultimoDiaMes + " 'de' MMMM 'de' yyyy"));
        
        resumenFacturacionBean.setUrlImagenFactura(
        		resumenFacturacionBean.getUrlImagenFactura());
        
        

        //Cantidad de intervalos en que debe ajustarse el grafico de facturacion
        String numIntervalosGrafico = MiEntelProperties.getProperty("facturacion.resumen.grafico.numIntervalos");
        resumenFacturacionBean.setNumIntervalosGrafico(numIntervalosGrafico);
        
        setResumenFacturacionBean(resumenFacturacionBean);
        // Json
        jsonResumenFacturacion = JsonHelper
                .toJsonResponse(resumenFacturacionBean);
        
    }

    /**
     * Calcula el ultimo dia del mes para la fecha indicada en <code>date</code>
     * @param date
     * @return
     */
    private int getUltimoDiaMes(Date date) {

        int anno = Integer.parseInt(DateHelper.format(date, "yyyy"));
        int mes = Integer.parseInt(DateHelper.format(date, "MM"));

        return DateHelper.ultimoDiaMes(anno, mes);

    }
    
    /**
     * @return the resumenFacturacionBean
     */
    public ResumenFacturacionBean getResumenFacturacionBean() {
       return resumenFacturacionBean;
    }

    /**
     * @param resumenFacturacionBean
     *            the resumenFacturacionBean to set
     */
    public void setResumenFacturacionBean(
            ResumenFacturacionBean resumenFacturacionBean) {
        this.resumenFacturacionBean = resumenFacturacionBean;
    }

    /**
     * @return the jsonResumenFacturacion
     */
    public String getJsonResumenFacturacion() {
        return jsonResumenFacturacion;
    }

    /**
     * @param jsonResumenFacturacion
     *            the jsonResumenFacturacion to set
     */
    public void setJsonResumenFacturacion(String jsonResumenFacturacion) {
        this.jsonResumenFacturacion = jsonResumenFacturacion;
    }

	/**
     * Consulta el estado en que se encuentran todas las facturas del cliente
     * 
     * @param numeroPcs
     * @param idSistema
     * @return String "PAGADO" o "IMPAGO"
     */
    public String getEstadoFacturasImpagas() {
        String numeroPcs = "";
        String result = "";
        try {
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            numeroPcs = ProfileWrapperHelper.getPropertyAsString(
                    profileWrapper, "numeroPcsSeleccionado");
            result = this.facturacionDelegate.estadoFacturasImpagas(numeroPcs);
        } catch (Exception e) {
            LOGGER.error("error estadoFacturasImpagas", e);
        }
        return result;
    }
}
