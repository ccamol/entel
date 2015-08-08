package com.epcs.bean;

import java.io.Serializable;

/**
 * Bean para la solicitud de activacion, desactivacion de Servicios de Buzon de
 * voz
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class AdminServicioBuzonBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String numeroPcs;

    private RutBean rutUsuario;

    private String email;

    private String tipoPerfil;

    /**
     * @return the numeroPcs
     */
    public String getNumeroPcs() {
        return numeroPcs;
    }

    /**
     * @param numeroPcs
     *            the numeroPcs to set
     */
    public void setNumeroPcs(String numeroPcs) {
        this.numeroPcs = numeroPcs;
    }

    /**
     * @return the rutUsuario
     */
    public RutBean getRutUsuario() {
        return rutUsuario;
    }

    /**
     * @param rutUsuario
     *            the rutUsuario to set
     */
    public void setRutUsuario(RutBean rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the tipoPerfil
     */
    public String getTipoPerfil() {
        return tipoPerfil;
    }

    /**
     * @param tipoPerfil
     *            the tipoPerfil to set
     */
    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

}
