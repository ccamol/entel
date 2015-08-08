/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.controller;

import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.CodeDescBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.cliente.perfil.delegate.EquipoDelegate;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class LineaAdicionalController {

    /**
     * Logger para LineaAdicionalController
     */
    private static final Logger LOGGER = Logger
            .getLogger(LineaAdicionalController.class);
    private CuentaDelegate cuentaDelegate;
    private EquipoDelegate equipoDelegate;
    private String linkPlanesVoz;
    private String linkBAM;
    private String linkiPhone3;
    private String linkiPhone4;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String respuestaJson;
    private boolean enGrupoHabil;
    private UsuarioBean usuario;
    //RGUZMAN
    private SelectItem[] indicativosTelefono;
    
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
     * @return the linkPlanesVoz
     */
    public String getLinkPlanesVoz() {
        return linkPlanesVoz;
    }
    
    /**
     * @return the linkBAM
     */
    public String getLinkBAM() {
        return linkBAM;
    }
    
    /**
     * @return the linkiPhone3
     */
    public String getLinkiPhone3() {
        return linkiPhone3;
    }
    
    /**
     * @return the linkiPhone4
     */
    public String getLinkiPhone4() {
        return linkiPhone4;
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
     * @return the primerApellido
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * @param primerApellido the primerApellido to set
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * @return the segundoApellido
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * @param segundoApellido the segundoApellido to set
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * @return the respuestaJson
     */
    public String getRespuestaJson() {
        return respuestaJson;
    }

    /**
     * @param respuestaJson the respuestaJson to set
     */
    public void setRespuestaJson(String respuestaJson) {
        this.respuestaJson = respuestaJson;
    }

    /**
     * @return the enGrupoHabil
     */
    public boolean isEnGrupoHabil() {
        return enGrupoHabil;
    }

    /**
     * @return the usuario
     */
    public UsuarioBean getUsuario() {
        return usuario;
    } 

    public void init(PhaseEvent event) {        
        try {
            verificaGrupoHabil();            
            if (isEnGrupoHabil()) {
                loadData();
            }
        } catch (DAOException e) {
            LOGGER.error("DAOException al obtener datos para solicitud de linea adicional", e);
        } catch (ServiceException e) {
            LOGGER.info("ServiceException codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al obtener datos para solicitud de linea adicional", e);
        }        
    }
    
    private void verificaGrupoHabil() {
        try {
            HttpServletRequest request = JSFPortletHelper.getRequest();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
            
            String rut = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutTitular");            
            enGrupoHabil = equipoDelegate.estaEnGrupoHabil(rut);
        } catch (DAOException e) {
            LOGGER.error("DAOException al verificar grupo habil", e);
        } catch (ServiceException e) {
            LOGGER.info("ServiceException codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception inesperada al verificar grupo habil.", e);
        }
    }
    
    private void loadData() throws Exception {
        linkPlanesVoz = MiEntelProperties.getProperty("lineaAdicional.linkPlanesVoz");
        linkBAM = MiEntelProperties.getProperty("lineaAdicional.linkBAM");
        linkiPhone3 = MiEntelProperties.getProperty("lineaAdicional.linkiPhone3");
        linkiPhone4 = MiEntelProperties.getProperty("lineaAdicional.linkiPhone4");
        
        HttpServletRequest request = JSFPortletHelper.getRequest();
        ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
        
        String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");
        String rut = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutTitular");

        this.usuario = this.cuentaDelegate.obtenerUsuario(msisdn, rut);
        this.primerNombre = this.usuario.getPrimerNombre();
        this.segundoNombre = this.usuario.getSegundoNombre();
        this.primerApellido = this.usuario.getApellidoPaterno();
        this.segundoApellido = this.usuario.getApellidoMaterno();      
    }
    
    public void enviarSolicitud(PhaseEvent event) {
    	String msisdn = "";
        try {
            HttpServletRequest request = JSFPortletHelper.getRequest();
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
            
            msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");
            
            primerNombre = JsfUtil.getRequestParameter("nombre1");
            segundoNombre = JsfUtil.getRequestParameter("nombre2");
            primerApellido = JsfUtil.getRequestParameter("apellido1");
            segundoApellido = JsfUtil.getRequestParameter("apellido2");
            
            String nombreCompleto = primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido;
            String lineaUso = JsfUtil.getRequestParameter("lineaUso");
            String formulario = MiEntelProperties.getProperty("lineaAdicional.formulario." + lineaUso);            
            String rut = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutTitular");
            String telefonoAdicional = JsfUtil.getRequestParameter("telefonoAdicional");
            
            this.equipoDelegate.solicitaLineaAdicional(msisdn, nombreCompleto, formulario, rut, telefonoAdicional);
            respuestaJson = JsonHelper.toJsonResponse("Ok");            
        } catch (DAOException e) {
            LOGGER.error("EDAOException al obtener datos para solicitud de linea adicional", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error en solicitud de linea adicional");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException msisdn: "+msisdn+" - codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
            respuestaJson = JsonHelper.toJsonErrorMessage("Error en solicitud de linea adicional");
        } catch (Exception e) {
            LOGGER.error("Exception inesperado al enviar solicitud de linea adicional", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error en solicitud de linea adicional");
        }
    }
    
    /**
     * RGUZMAN 
     * Metodo que devuelve el listado de indicativos telefonicos para ser mostrados en un combo en la pagina
     */
    public SelectItem[] getIndicativosTelefono() {

        if (indicativosTelefono == null) {
            List<CodeDescBean> indTelList = ParametrosHelper.getIndicativoTelefono();
            indicativosTelefono = JsfUtil.getSelectItemsCodeDesc(indTelList, false);
        }

        return indicativosTelefono;
    }
}
