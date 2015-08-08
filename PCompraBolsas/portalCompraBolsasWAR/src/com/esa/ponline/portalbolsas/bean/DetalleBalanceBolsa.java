package com.esa.ponline.portalbolsas.bean;

import java.io.Serializable;

/**
 * @author ccastro
 *
 */

public class DetalleBalanceBolsa implements Serializable {

    private static final long serialVersionUID = 2362882780989235498L;

    private String idBolsa;
    
    private String nombreBolsa;
    
    private String introMsj;
    
    private String mensaje;
    
    private String expiracion;

    /**
     * @return the idBolsa
     */
    public String getIdBolsa() {
        return idBolsa;
    }

    /**
     * @param idBolsa the idBolsa to set
     */
    public void setIdBolsa(String idBolsa) {
        this.idBolsa = idBolsa;
    }

    /**
     * @return the nombreBolsa
     */
    public String getNombreBolsa() {
        return nombreBolsa;
    }

    /**
     * @param nombreBolsa the nombreBolsa to set
     */
    public void setNombreBolsa(String nombreBolsa) {
        this.nombreBolsa = nombreBolsa;
    }

    /**
     * @return the introMsj
     */
    public String getIntroMsj() {
        return introMsj;
    }

    /**
     * @param introMsj the introMsj to set
     */
    public void setIntroMsj(String introMsj) {
        this.introMsj = introMsj;
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
     * @return the expiracion
     */
    public String getExpiracion() {
        return expiracion;
    }

    /**
     * @param expiracion the expiracion to set
     */
    public void setExpiracion(String expiracion) {
        this.expiracion = expiracion;
    }
}

