/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ResumenHistoricoSuscripcionesBean {

	private String idSuscripcion;
	private String tipoContenido;
	private String descripcion;
	private String artista;
	private String fecha;	

	/**
     * @return the idSuscripcion
     */
    public String getIdSuscripcion() {
        return idSuscripcion;
    }

    /**
     * @param idSuscripcion the idSuscripcion to set
     */
    public void setIdSuscripcion(String idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    /**
	 * @return the tipoContenido
	 */
	public String getTipoContenido() {
		return tipoContenido;
	}

	/**
	 * @param tipoContenido
	 *            the tipoContenido to set
	 */
	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the artista
	 */
	public String getArtista() {
		return artista;
	}

	/**
	 * @param artista
	 *            the artista to set
	 */
	public void setArtista(String artista) {
		this.artista = artista;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
