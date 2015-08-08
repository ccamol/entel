/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.Date;

import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 * Clase que encapsula la info de una suscripciones CDF despues de la orquestacion 
 *
 */
public class SuscripcionCDFOrqBean {
    
    private String idSuscripcion;
    private String nombre;
    private Double precio;
    private Integer duracion;
    private String mercado;
    private Date fechaSuscripcion;
    private boolean activa;
    private boolean compatibilidad;
    
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }
    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    /**
     * @return the duracion
     */
    public Integer getDuracion() {
        return duracion;
    }
    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    /**
     * @return the mercado
     */
    public String getMercado() {
        return mercado;
    }
    /**
     * @param mercado the mercado to set
     */
    public void setMercado(String mercado) {
        this.mercado = mercado;
    }
    
    /**
     * @return the fechaSuscripcion
     */
    public Date getFechaSuscripcion() {
        return fechaSuscripcion;
    }
    /**
     * @param fechaSuscripcion the fechaSuscripcion to set
     */
    public void setFechaSuscripcion(Date fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }
    
    /**
     * @return the estado de activacion
     */
    public boolean isActiva() {
        return activa;
    }
    /**
     * @param estado the estado de activacion to set
     */
    public void setActiva(String estado) {
        this.activa = estado.equals(MiEntelProperties
                .getProperty("ishop.suscripcioncdf.estadoActivacion.1"));
    }
    /**
     * @return the compatibilidad
     */
    public boolean getCompatibilidad() {
        return compatibilidad;
    }
    /**
     * @param compatibilidad the compatibilidad to set
     */
    public void setCompatibilidad(boolean compatibilidad) {
        this.compatibilidad = compatibilidad;
    }

}
