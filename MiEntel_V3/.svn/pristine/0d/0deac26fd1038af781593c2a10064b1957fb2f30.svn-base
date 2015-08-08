/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.formulariosatisfaccion.controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.CodeDescBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.formulariosatisfaccion.delegate.FormularioSatisfaccionDelegate;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class FormularioSatisfaccionController {

    private FormularioSatisfaccionDelegate formularioSatisfaccionDelegate;
    private CuentaDelegate cuentaDelegate;
    private UsuarioBean usuario;    
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroPCS;
    private String codigoTelAdicional;
    private String telAdicional;
    private String eMail;
    private String mensaje;
    private SelectItem[] indicativosTelefono;
    private static final Logger LOGGER = Logger
            .getLogger(FormularioSatisfaccionController.class);

    /**
     * @return the formularioSatisfaccionDelegate
     */
    public FormularioSatisfaccionDelegate getFormularioSatisfaccionDelegate() {
        return formularioSatisfaccionDelegate;
    }

    /**
     * @param formularioSatisfaccionDelegate
     *            the formularioSatisfaccionDelegate to set
     */
    public void setFormularioSatisfaccionDelegate(
            FormularioSatisfaccionDelegate formularioSatisfaccionDelegate) {
        this.formularioSatisfaccionDelegate = formularioSatisfaccionDelegate;
    }

    /**
     * @return the cuentaDelegate
     */
    public CuentaDelegate getCuentaDelegate() {
        return cuentaDelegate;
    }

    /**
     * @param cuentaDelegate the cuentaDelegate to set
     */
    public void setCuentaDelegate(CuentaDelegate cuentaDelegate) {
        this.cuentaDelegate = cuentaDelegate;
    }

    /**
     * @return the usuario
     */
    public UsuarioBean getUsuario() {
        return usuario;
    }

    /**
     * @return the primerNombre
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * @param primerNombre the primerNombre to set
     */
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    /**
     * @return the segundoNombre
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * @param segundoNombre the segundoNombre to set
     */
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    /**
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return the numeroPCS
     */
    public String getNumeroPCS() {
        return numeroPCS;
    }

    /**
     * @param numeroPCS the numeroPCS to set
     */
    public void setNumeroPCS(String numeroPCS) {
        this.numeroPCS = numeroPCS;
    }

    /**
     * @return the codigoTelAdicional
     */
    public String getCodigoTelAdicional() {
        return codigoTelAdicional;
    }

    /**
     * @param codigoTelAdicional the codigoTelAdicional to set
     */
    public void setCodigoTelAdicional(String codigoTelAdicional) {
        this.codigoTelAdicional = codigoTelAdicional;
    }

    /**
     * @return the telAdicional
     */
    public String getTelAdicional() {
        return telAdicional;
    }

    /**
     * @param telAdicional the telAdicional to set
     */
    public void setTelAdicional(String telAdicional) {
        this.telAdicional = telAdicional;
    }

    /**
     * @return the eMail
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * @param eMail the eMail to set
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    /**
     * Metodo que devuelve el listado de indicativos telefonicos para ser mostrados en un combo en la pagina
     */
    public SelectItem[] getIndicativosTelefono() {
        if (indicativosTelefono == null) {
            List<CodeDescBean> indTelList = ParametrosHelper.getIndicativoTelefono();
            indicativosTelefono = JsfUtil.getSelectItemsCodeDesc(indTelList, false);
        }
        return indicativosTelefono;
    }

    public void init(PhaseEvent event) {
        try {
            loadData();
        } catch (DAOException e) {
            LOGGER.error("Error al obtener datos para el formulario de satisfaccion", e);
        } catch (ServiceException e) {
            LOGGER.info("Error de servicio codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener datos para el formulario de satisfaccion", e);
        }
    }

    private void loadData() throws DAOException, ServiceException, Exception {
        
    	ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        
    	String msisdn = ProfileWrapperHelper.getPropertyAsString(profile,"numeroPcs");
        String rut = ProfileWrapperHelper.getPropertyAsString(profile,"rutUsuario");

        usuario = cuentaDelegate.obtenerUsuario(msisdn, rut);
        
        primerNombre = primerNombre == null ? usuario.getPrimerNombre() : primerNombre;
        segundoNombre = segundoNombre == null ? usuario.getSegundoNombre() : segundoNombre;
        apellidoPaterno = apellidoPaterno == null ? usuario.getApellidoPaterno() : apellidoPaterno;
        apellidoMaterno = apellidoMaterno == null ? usuario.getApellidoMaterno() : apellidoMaterno;
        numeroPCS = numeroPCS == null ? usuario.getNumeroPCS() : numeroPCS;
        eMail = eMail == null ? usuario.getEmail() : eMail;        
    }
    
    public String enviarDatos() {
        String response = "";
        
        try {
            ProfileWrapper profile = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            
            String mercado = ProfileWrapperHelper.getPropertyAsString(profile,
                    "mercado");
            String rut = ProfileWrapperHelper.getPropertyAsString(profile,
                    "rutUsuario");

            String nombres = primerNombre + " " + segundoNombre;
            String apellidos = apellidoPaterno + " " + apellidoMaterno;
            String telefonoAdicional = codigoTelAdicional + telAdicional;
            
            RutBean rutBean = new RutBean(rut);
            String rutSinDV =  String.valueOf(rutBean.getNumero());
            
            this.formularioSatisfaccionDelegate.insertar(numeroPCS, rutSinDV, mercado,
                    nombres, apellidos, telefonoAdicional, eMail, Utils.removeLineBreak(mensaje));
            
            /*this.formularioSatisfaccionDelegate.insertar(numeroPCS, rut, mercado,
                    nombres, apellidos, telefonoAdicional, eMail, Utils.removeLineBreak(mensaje));*/
            
            response = "success";
        } catch (SQLException e) {
            LOGGER.error("Error al registrar en la base de datos.", e);
            JSFMessagesHelper.addErrorCodeMessage("formularioSatisfaccion", "noEnviado");            
        } catch (Exception e) {
            LOGGER.error("Error al enviar formulario de satisfaccion.", e);
            JSFMessagesHelper.addErrorCodeMessage("formularioSatisfaccion", "noEnviado");
        }

        return response;
    }
}
