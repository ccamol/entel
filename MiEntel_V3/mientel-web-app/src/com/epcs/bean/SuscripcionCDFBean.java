/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.Date;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class SuscripcionCDFBean {
    
    private String idSuscripcion;
    private String nombre;
    private Double precio;
    private Integer duracion;
    private String mercado;
    private Integer streaming;
    private Integer video;
    private Date fechaSuscripcion;

    /**
     * Gets the value of the idSuscripcion property.
     * @return {@link String }
     */
    public String getIdSuscripcion() {
        return idSuscripcion;
    }

    /**
     * Sets the value of the idSuscripcion property.
     * @param value {@link String }
     */
    public void setIdSuscripcion(String value) {
        this.idSuscripcion = value;
    }

    /**
     * Gets the value of the nombre property.
     * @return {@link String }
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * @param value {@link String }
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the precio property.
     * @return {@link String }
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Sets the value of the precio property.
     * @param value {@link String }
     */
    public void setPrecio(Double value) {
        this.precio = value;
    }

    /**
     * Gets the value of the duracion property.
     * @return {@link String } 
     */
    public Integer getDuracion() {
        return duracion;
    }

    /**
     * Sets the value of the duracion property.
     * @param value {@link String }     
     */
    public void setDuracion(Integer value) {
        this.duracion = value;
    }

    /**
     * Gets the value of the mercado property.
     * @return {@link String }  
     */
    public String getMercado() {
        return mercado;
    }

    /**
     * Sets the value of the mercado property.
     * @param value {@link String } 
     */
    public void setMercado(String value) {
        this.mercado = value;
    }

    /**
     * Gets the value of the streaming property.
     * @return {@link String }
     */
    public Integer getStreaming() {
        return streaming;
    }

    /**
     * Sets the value of the streaming property.
     * @param value {@link String }   
     */
    public void setStreaming(Integer value) {
        this.streaming = value;
    }

    /**
     * Gets the value of the video property.
     * @return {@link String }
     */
    public Integer getVideo() {
        return video;
    }

    /**
     * Sets the value of the video property.
     * @param value {@link String }
     */
    public void setVideo(Integer value) {
        this.video = value;
    }

    /**
     * Gets the value of the fechaSuscripcion property.
     * @return {@link java.uitl.Date }
     */
    public Date getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    /**
     * Sets the value of the fechaSuscripcion property.
     * @param value {@link String }
     */
    public void setFechaSuscripcion(Date value) {
        this.fechaSuscripcion = value;
    }

}

