/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * Bean para Buzon de Voz
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ServicioBuzonBean implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String respuesta;
    private String descRespuesta;
    private String idServicio;
    private String tercerEstado;
    private String mostrar;
    private String mostrarEstado;

    private boolean visible = false;

    private boolean activo = false;
    
    private boolean basico = false;
    
    private boolean premium = false;
    
    private boolean tep = false;
    
    private String tipoPerfil;
    
    /**
     * @return the estado
     */
    public String getRespuesta() {
        return respuesta;
    }
    /**
     * @param estado the estado to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    /**
     * @return the desEstado
     */
    public String getDescRespuesta() {
        return descRespuesta;
    }
    /**
     * @param desEstado the desEstado to set
     */
    public void setDescRespuesta(String descRespuesta) {
        this.descRespuesta = descRespuesta;
    }
    /**
     * @return the idServicio
     */
    public String getIdServicio() {
        return idServicio;
    }
    /**
     * @param idServicio the idServicio to set
     */
    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }
    /**
     * @return the tercerEstado
     */
    public String getTercerEstado() {
        return tercerEstado;
    }
    /**
     * @param tercerEstado the tercerEstado to set
     */
    public void setTercerEstado(String tercerEstado) {
        this.tercerEstado = tercerEstado;
    }
    /**
     * @return the mostrar
     */
    public String getMostrar() {
        return mostrar;
    }
    /**
     * @param mostrar the mostrar to set
     */
    public void setMostrar(String mostrar) {
        this.mostrar = mostrar;
    }
    /**
     * @return the mostrarEstado
     */
    public String getMostrarEstado() {
        return mostrarEstado;
    }
    /**
     * @param mostrarEstado the mostrarEstado to set
     */
    public void setMostrarEstado(String mostrarEstado) {
        this.mostrarEstado = mostrarEstado;
    }
    
    public void setVisible(boolean visible) {
        this.visible = true;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }
    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
	 * @return the basico
	 */
	public boolean isBasico() {
		return basico;
	}
	/**
	 * @param basico the basico to set
	 */
	public void setBasico(boolean basico) {
		this.basico = basico;
	}

    /**
     * @return true si el buzon correspondea a premium. false si es basico
     */
    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

	/**
	 * @return the tep
	 */
	public boolean isTep() {
		return tep;
	}
	/**
	 * @param tep the tep to set
	 */
	public void setTep(boolean tep) {
		this.tep = tep;
	}
	/**
     * @return String Indica si es mms, mail o basico.
     */
    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }
 
}
