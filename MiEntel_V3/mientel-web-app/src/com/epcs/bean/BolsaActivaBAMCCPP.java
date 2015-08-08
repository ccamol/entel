/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import com.epcs.recursoti.configuracion.Utils;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class BolsaActivaBAMCCPP {
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private String fechaActivacion;
    private String fechaExpiracion;    
    private int cuotaUtilizadaVoucher;
    private int cuotaInicialVoucher;
    private String cuotaUtilizadaVoucherFormatted;
    private String cuotaInicialVoucherFormatted;    
    private String saldoPlan;
    private int saldoBolsa;
    private String cuotaUtilizadaPlan;
    private boolean consumida;
    
    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }
    
    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
     * @return the fechaActivacion
     */
    public String getFechaActivacion() {
        return fechaActivacion;
    }
    
    /**
     * @param fechaActivacion the fechaActivacion to set
     */
    public void setFechaActivacion(String fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }
    
    /**
     * @return the fechaExpiracion
     */
    public String getFechaExpiracion() {
        return fechaExpiracion;
    }
    
    /**
     * @param fechaExpiracion the fechaExpiracion to set
     */
    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    
    /**
     * @return the cuotaInicial
     */
    public int getCuotaInicialVoucher() {
        return cuotaInicialVoucher;
    }
    
    /**
     * @param cuotaInicial the cuotaInicial to set
     */
    public void setCuotaInicialVoucher(int cuotaInicialVoucher) {
        this.cuotaInicialVoucher = cuotaInicialVoucher;
        this.cuotaInicialVoucherFormatted = Utils.formatMoney(new Double(cuotaInicialVoucher));
        verificarConsumo();
    }
    
    /**
     * @return the cuotaUtilizada
     */
    public int getCuotaUtilizadaVoucher() {
        return cuotaUtilizadaVoucher;
    }
    
    /**
     * @param cuotaUtilizada the cuotaUtilizada to set
     */
    public void setCuotaUtilizadaVoucher(int cuotaUtilizadaVoucher) {
        this.cuotaUtilizadaVoucher = cuotaUtilizadaVoucher;
        this.cuotaUtilizadaVoucherFormatted = Utils.formatMoney(new Double(cuotaUtilizadaVoucher));
        verificarConsumo();
    }    

    /**
     * @return the cuotaUtilizadaVoucherFormatted
     */
    public String getCuotaUtilizadaVoucherFormatted() {
        return cuotaUtilizadaVoucherFormatted;
    }

    /**
     * @return the cuotaInicialVoucherFormatted
     */
    public String getCuotaInicialVoucherFormatted() {
        return cuotaInicialVoucherFormatted;
    }

    /**
     * @return the saldoPlan
     */
    public String getSaldoPlan() {
        return saldoPlan;
    }

    /**
     * @param saldoPlan the saldoPlan to set
     */
    public void setSaldoPlan(String saldoPlan) {
        this.saldoPlan = saldoPlan;
    }

    /**
     * @return the saldoBolsa
     */
    public int getSaldoBolsa() {
        return saldoBolsa;
    }

    /**
     * @param saldoBolsa the saldoBolsa to set
     */
    public void setSaldoBolsa(int saldoBolsa) {
        this.saldoBolsa = saldoBolsa;
    }

    /**
     * @return the cuotaUtilizadaPlan
     */
    public String getCuotaUtilizadaPlan() {
        return cuotaUtilizadaPlan;
    }

    /**
     * @param cuotaUtilizadaPlan the cuotaUtilizadaPlan to set
     */
    public void setCuotaUtilizadaPlan(String cuotaUtilizadaPlan) {
        this.cuotaUtilizadaPlan = cuotaUtilizadaPlan;
    }

    /**
     * @return the bolsaConsumida
     */
    public boolean isConsumida() {
        return consumida;
    }
    
    private void verificarConsumo() {
        consumida = (cuotaInicialVoucher == cuotaUtilizadaVoucher);
    }
}
