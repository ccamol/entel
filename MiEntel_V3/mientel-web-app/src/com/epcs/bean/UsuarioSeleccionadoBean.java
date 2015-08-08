/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

/**
 * @author mmartinez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class UsuarioSeleccionadoBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * numeroPcs que se selecciono.
	 */
	private String numeroPcs;

    /**
     * rut de msisdn que se selecciono.
     */
    private RutBean rut;

    /**
     * nombre del usuario del msisdn que se selecciono.
     */
    private String nombreUsuario;
    
    /**
     * Submercado del usuario del msisdn que se selecciono
     */
    private String subMercado;

	public String getNumeroPcs() {
		return numeroPcs;
	}

	public void setNumeroPcs(String numeroPcs) {
		this.numeroPcs = numeroPcs;
	}

	public RutBean getRut() {
		return rut;
	}

	public void setRut(RutBean rut) {
		this.rut = rut;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

    public String getSubMercado() {
        return subMercado;
    }

    public void setSubMercado(String subMercado) {
        this.subMercado = subMercado;
    }
}
