package com.epcs.cliente.perfil.controller.servicios.voz;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.AdminServicioBuzonBean;
import com.epcs.bean.ResultadoServicioBean;
import com.epcs.bean.RutBean;
import com.epcs.cliente.perfil.delegate.AdministracionServiciosDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * Controlador para la administracin del servicio de Buzon de voz
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class BuzonVozController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para BuzonVozController
     */
    private static final Logger LOGGER = Logger
            .getLogger(BuzonVozController.class);
    
    //------------------------------------- Constantes
    
    private static final String BUZON_MMS = MiEntelProperties
            .getProperty("adminServicios.buzon.tipoPerfil.vm2mms");

    private static final String BUZON_EMAIL = MiEntelProperties
            .getProperty("adminServicios.buzon.tipoPerfil.vm2email");

    private static final String BUZON_BASICO = MiEntelProperties
            .getProperty("adminServicios.buzon.tipoPerfil.basico");

    //-------------------------------------- Atributos

    /**
     * @return id de buzon de voz basico
     */
    public String getTipoPerfilBasico() {
        return BUZON_BASICO;
    }

    /**
     * @return id de buzon de voz mms
     */
    public String getTipoPerfilMMS() {
        return BUZON_MMS;
    }

    /**
     * @return id de buzon de voz mail
     */
    public String getTipoPerfilMail() {
        return BUZON_EMAIL;
    }
    
    //---------------------------------------- Action methods
    
    /**
     * Action method para activar servicio buzon voz
     * 
     * @return String callback null
     */
    public String activarBuzonBasico() {
        return this.activarBuzonCommon(BUZON_BASICO);
    }

    /**
     * Action method para activar servicio buzon voz premium, tanto mms como mail.<br>
     * Este metodo determina el tipo de buzon premium, por el radio button 'tipoPerfil'
     * 
     * @return String callback null
     */
    public String activarBuzonPremium() {
        String tipoPerfil = getFieldTipoPerfil();
        return this.activarBuzonCommon(tipoPerfil);
    }
    
    /**
     * 
     * @return Valor del radio buton HTML 'tipoPerfil'
     */
    private String getFieldTipoPerfil() {
        return JsfUtil.getRequestParameter("tipoPerfil");
    }
    
    /**
     * Action method para desactivar servicio buzon voz
     * 
     * @return String callback null
     */
    public String desactivarBuzon() {
        
        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");
            String rutUsuario = ProfileWrapperHelper.getPropertyAsString(
                    profile, "rutUsuarioSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.desactivacion(numeroPcsSeleccionado, RutBean
                    .parseRut(rutUsuario), BUZON_BASICO);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "desactivacionBuzonVoz", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al activar servicio", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionBuzonVoz");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "desactivacionBuzonVoz");
        }
        
        return null;
    }
    
    public String getEmailBuzonPremium() {
        return this.obtenerEmailBuzonPremium();
    }
    
    /**
     * @return valor del input html 'email'
     */
    private String getFieldEmail() {
        return JsfUtil.getRequestParameter("email");
    }

    //-------------------------------------------- Privates
    
    /**
     * Metodo 'common' para activacion de buzon de voz
     * 
     * @param tipoPerfil
     *            String indicando buzon basico o premium (mms o mail)
     *            
     * @see #activarBuzonBasico()
     * @see #activarBuzonPremium()
     * 
     * @return null
     */
    private String activarBuzonCommon(String tipoPerfil) {

        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");
            String rutUsuario = ProfileWrapperHelper.getPropertyAsString(
                    profile, "rutUsuarioSeleccionado");

            ResultadoServicioBean resultado;

            resultado = this.activacion(numeroPcsSeleccionado, RutBean
                    .parseRut(rutUsuario), tipoPerfil);

            // mensaje exito
            JSFMessagesHelper.addServiceSuccessMessage("adminServicios",
                    "activacionBuzonVoz", new String[] { resultado
                            .getNumeroPcs() });

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al activar servicio", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionBuzonVoz");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught");
            JSFMessagesHelper.addErrorCodeMessage("adminServicios", e
                    .getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught", e);
            JSFMessagesHelper.addErrorMessage("adminServicios", "activacionBuzonVoz");
        }
        
        return null;
    }
    
    /**
     * Wrapper para logica de invocacion a delegate
     * 
     * @see AdministracionServiciosDelegate#activarBuzonVoz(AdminServicioBuzonBean)
     */
    private ResultadoServicioBean activacion(String numeroPcs,
            RutBean rutUsuario, String tipoPerfil) throws DAOException,
            ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        AdminServicioBuzonBean adminServicioBuzonBean = new AdminServicioBuzonBean();
        adminServicioBuzonBean.setEmail(this.getFieldEmail());
        adminServicioBuzonBean.setNumeroPcs(numeroPcs);
        adminServicioBuzonBean.setRutUsuario(rutUsuario);
        adminServicioBuzonBean.setTipoPerfil(tipoPerfil);
        return delegate.activarBuzonVoz(adminServicioBuzonBean);
    }
    
    /**
     * Wrapper para logica de invocacion a delegate
     * 
     * @see AdministracionServiciosDelegate#desactivarBuzonVoz(AdminServicioBuzonBean)
     */
    private ResultadoServicioBean desactivacion(String numeroPcs,
            RutBean rutUsuario, String tipoPerfil) throws DAOException,
            ServiceException {
        AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
        AdminServicioBuzonBean adminServicioBuzonBean = new AdminServicioBuzonBean();
        adminServicioBuzonBean.setEmail(this.getFieldEmail());
        adminServicioBuzonBean.setNumeroPcs(numeroPcs);
        adminServicioBuzonBean.setRutUsuario(rutUsuario);
        adminServicioBuzonBean.setTipoPerfil(tipoPerfil);
        return delegate.desactivarBuzonVoz(adminServicioBuzonBean);
    }
    
    /**
     * 
     * @return String con email registrado para buzon premium. String vacio si
     *         no tiene registrado. Este metodo no arroga las excepciones regulares 
     *         de un metodo que llama un servicio
     */
    private String obtenerEmailBuzonPremium() {
        
        try {

            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            String numeroPcsSeleccionado = ProfileWrapperHelper
                    .getPropertyAsString(profile, "numeroPcsSeleccionado");
            String rutUsuario = ProfileWrapperHelper.getPropertyAsString(
                    profile, "rutUsuarioSeleccionado");

            AdministracionServiciosDelegate delegate = new AdministracionServiciosDelegate();
            return delegate.obtenerEmailBuzonPremium(numeroPcsSeleccionado, RutBean.parseRut(rutUsuario));

        } catch (DAOException e) {
            LOGGER.error("DAOException caught al consultar email del buzon premium", e);
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught al consultar email del buzon premium");
        } catch (Exception e) {
            LOGGER.error("Exception caught al consultar email del buzon premium", e);
        }
        
        //Si el servicio falla, no se debe detener la ejecucion de la funcionalidad
        return "";
    }

}
