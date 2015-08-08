/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.Date;


/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class PuntosBean {
    
    private Date fechaActPuntos;
    private Date fechaVencPuntos;
    private Integer puntosVencidos;
    private Integer saldoPuntos;
    private String saldoPuntosFormated;
    private Integer puntosPromocion;
    private String fechaVencPromoFormated;
    private String puntosPromocionFormated;
    private String estadoPromocion;
    private Date fechaVencPromocion;
    
    public PuntosBean(){
        
    }

    /**
     * @return the fechaActPuntos
     */
    public Date getFechaActPuntos() {
        return fechaActPuntos;
    }

    /**
     * @param fechaActPuntos the fechaActPuntos to set
     */
    public void setFechaActPuntos(Date fechaActPuntos) {
        this.fechaActPuntos = fechaActPuntos;
    }

    /**
     * @return the fechaVencPuntos
     */
    public Date getFechaVencPuntos() {
        return fechaVencPuntos;
    }

    /**
     * @param fechaVencPuntos the fechaVencPuntos to set
     */
    public void setFechaVencPuntos(Date fechaVencPuntos) {
        this.fechaVencPuntos = fechaVencPuntos;
    }

    /**
     * @return the puntosVencidos
     */
    public Integer getPuntosVencidos() {
        return puntosVencidos;
    }

    /**
     * @param puntosVencidos the puntosVencidos to set
     */
    public void setPuntosVencidos(Integer puntosVencidos) {
        this.puntosVencidos = puntosVencidos;
    }

    /**
     * @return the saldoPuntos
     */
    public Integer getSaldoPuntos() {
        return saldoPuntos;
    }

    /**
     * @param saldoPuntos the saldoPuntos to set
     */
    public void setSaldoPuntos(Integer saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

	/**
	 * @return the saldoPuntosFormated
	 */
	public String getSaldoPuntosFormated() {
		return saldoPuntosFormated;
	}

	/**
	 * @param saldoPuntosFormated the saldoPuntosFormated to set
	 */
	public void setSaldoPuntosFormated(String saldoPuntosFormated) {
		this.saldoPuntosFormated = saldoPuntosFormated;
	}

	public Integer getPuntosPromocion() {
		return puntosPromocion;
	}

	public void setPuntosPromocion(Integer puntosPromocion) {
		this.puntosPromocion = puntosPromocion;
	}

	public Date getFechaVencPromocion() {
		return fechaVencPromocion;
	}

	public void setFechaVencPromocion(Date fechaVencPromocion) {
		this.fechaVencPromocion = fechaVencPromocion;
	}

	public String getPuntosPromocionFormated() {
		return puntosPromocionFormated;
	}

	public void setPuntosPromocionFormated(String puntosPromocionFormated) {
		this.puntosPromocionFormated = puntosPromocionFormated;
	}

	public String getEstadoPromocion() {
		return estadoPromocion;
	}

	public void setEstadoPromocion(String estadoPromocion) {
		this.estadoPromocion = estadoPromocion;
	}

	public String getFechaVencPromoFormated() {
		return fechaVencPromoFormated;
	}

	public void setFechaVencPromoFormated(String fechaVencPromoFormated) {
		this.fechaVencPromoFormated = fechaVencPromoFormated;
	}

	
	
	
}
