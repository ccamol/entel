package com.epcs.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.billing.balance.util.ArcFour;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

public class ProfileWrapperBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ProfileWrapper profile;

    public ProfileWrapperBean() {
        super();

        FacesContext fc = FacesContext.getCurrentInstance();
        profile = ProfileWrapperHelper.getProfile(JSFPortletHelper
                .getRequest(fc));
    }

    public String getRutTitular() {
        return this
                .getProfileProperty("miEntel.userProfile.rutTitular.property");
    }

    public String getNumeroPcs() {
        return this
                .getProfileProperty("miEntel.userProfile.numeroPcs.property");
    }

    public String getRutUsuario() {
        return this
                .getProfileProperty("miEntel.userProfile.rutUsuario.property");
    }

    public String getNombreUsuario() {
        return this
                .getProfileProperty("miEntel.userProfile.nombreUsuario.property");
    }

    public String getMercado() {
        return this.getProfileProperty("miEntel.userProfile.mercado.property");
    }

    public String getFlagBam() {
        return this.getProfileProperty("miEntel.userProfile.flagBam.property");
    }

    public String getAaa() {
        return this.getProfileProperty("miEntel.userProfile.aaa.property");
    }

    public String getNombreTitular() {
        return this
                .getProfileProperty("miEntel.userProfile.nombreTitular.property");
    }

    public String getNumeroCuenta() {
        return this
                .getProfileProperty("miEntel.userProfile.numeroCuenta.property");
    }

    public String getNumeroPcsSeleccionado() {
        return this
                .getProfileProperty("miEntel.userProfile.numeroPcsSeleccionado.property");
    }

    public String getRutSeleccionado() {
        return this
                .getProfileProperty("miEntel.userProfile.rutUsuarioSeleccionado.property");
    }

    public String getNombreUsuarioSeleccionado() {
        return this
                .getProfileProperty("miEntel.userProfile.nombreUsuarioSeleccionado.property");
    }
    
    public String getSubMercado() {
        return this
                .getProfileProperty("miEntel.userProfile.subMercado.property");
    }
    
    public String getSubMercadoSeleccionado() {
        return this
                .getProfileProperty("miEntel.userProfile.subMercadoSeleccionado.property");
    }
    
    public String getGrupoCliente() {
        return this
                .getProfileProperty("miEntel.userProfile.grupoCliente.property");
    }
    
    public String getCategoriaCliente() {
        return this
                .getProfileProperty("miEntel.userProfile.categoriaCliente.property");
    }
    
    public String getNumeroPcsEncriptado() {
        return this
                .getProfileProperty("miEntel.userProfile.numeroPcsEncriptado.property");
    }

    public boolean isPresent() {
        return profile != null;
    }

    /**
     * 
     * @param property
     *            String con el nombre de la property de aplicacion.properties
     *            que indica el nombre de la propiedad del perfil de usuario
     * @return String con el valor de la propiedad del perfil de usuario. null
     *         en caso de Exception o perfil es null
     */
    private String getProfileProperty(String property) {

        if (profile == null) {
            return null;
        }
        else {
            try {
                String profileProperty = MiEntelProperties
                        .getProperty(property);
                return ProfileWrapperHelper.getPropertyAsString(profile,
                        profileProperty);
            } catch (Exception e) {
                return null;
            }
        }
    }

}
