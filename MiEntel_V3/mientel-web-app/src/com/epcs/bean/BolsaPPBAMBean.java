/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class BolsaPPBAMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idBolsa;
    private String nombreBolsa;
    private Date fechaInicio;
    private Date fechaFin;
    private int cantidad;
    private String saldoTiempo;
    private double precio;
    private String descripcion;
    private String estadoCompra;
    private String tiempoDuracion;
    private String unidad;
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * @return the estadoCompra
     */
    public String getEstadoCompra() {
        return estadoCompra;
    }
    /**
     * @param estadoCompra the estadoCompra to set
     */
    public void setEstadoCompra(String estadoCompra) {
        this.estadoCompra = estadoCompra;
    }
    /**
     * @return the tiempoDuracion
     */
    public String getTiempoDuracion() {
        return tiempoDuracion;
    }
    /**
     * @param tiempoDuracion the tiempoDuracion to set
     */
    public void setTiempoDuracion(String tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }
    /**
     * @return the unidad
     */
    public String getUnidad() {
        return unidad;
    }
    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
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
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }
    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }
    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }
    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    /**
     * @return the saldoTiempo
     */
    public String getSaldoTiempo() {
        return saldoTiempo;
    }
    /**
     * @param saldoTiempo the saldoTiempo to set
     */
    public void setSaldoTiempo(String saldoTiempo) {
        this.saldoTiempo = saldoTiempo;
    }
    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

}
