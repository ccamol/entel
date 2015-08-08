/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import com.epcs.recursoti.configuracion.Utils;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class BolsaDisponibleBAMCCPP {
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private int precio;
    private int cuotaInicialVoucher;
    private String precioFormatted;
    private String cuotaInicialVoucherFormatted;
    private boolean flagHabilitada;
    private boolean flagCargaFactura;
    private boolean flagCargaSaldo;
    private String descripcionCargaFactura;
    private String descripcionCargaSaldo;
    
    public BolsaDisponibleBAMCCPP() {
        this.flagCargaSaldo = true;
        this.flagCargaFactura = true;
        this.flagHabilitada = true;
    }
    
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
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }
    
    /**
     * @param precio the precio to set
     */
    public void setPrecio(int precio) {
        this.precio = precio;
        this.precioFormatted = Utils.formatMoneyPuntos(new Double(precio));
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
    }

    /**
     * @return the precioFormatted
     */
    public String getPrecioFormatted() {
        return precioFormatted;
    }

    /**
     * @return the cuotaInicialVoucherFormatted
     */
    public String getCuotaInicialVoucherFormatted() {
        return cuotaInicialVoucherFormatted;
    }

    /**
     * @return the flagCargaFactura
     */
    public boolean isFlagCargaFactura() {
        return flagCargaFactura;
    }

    /**
     * @param flagCargaFactura the flagCargaFactura to set
     */
    public void setFlagCargaFactura(boolean flagCargaFactura) {
        this.flagCargaFactura = flagCargaFactura;
        verificaHabilitada();
    }

    /**
     * @return the flagCargaSaldo
     */
    public boolean isFlagCargaSaldo() {
        return flagCargaSaldo;
    }

    /**
     * @param flagCargaSaldo the flagCargaSaldo to set
     */
    public void setFlagCargaSaldo(boolean flagCargaSaldo) {
        this.flagCargaSaldo = flagCargaSaldo;
        verificaHabilitada();
    }

    /**
     * @return the flagHabilitada
     */
    public boolean isFlagHabilitada() {
        return flagHabilitada;
    }
    
    /**
     * @return the descripcionCargaFactura
     */
    public String getDescripcionCargaFactura() {
        return descripcionCargaFactura;
    }

    /**
     * @param descripcionCargaFactura the descripcionCargaFactura to set
     */
    public void setDescripcionCargaFactura(String descripcionCargaFactura) {
        this.descripcionCargaFactura = descripcionCargaFactura;
    }

    /**
     * @return the descripcionCargaSaldo
     */
    public String getDescripcionCargaSaldo() {
        return descripcionCargaSaldo;
    }

    /**
     * @param descripcionCargaSaldo the descripcionCargaSaldo to set
     */
    public void setDescripcionCargaSaldo(String descripcionCargaSaldo) {
        this.descripcionCargaSaldo = descripcionCargaSaldo;
    }

    private void verificaHabilitada() {
        if (flagCargaSaldo || flagCargaFactura) {
            flagHabilitada = true;
        } else if (!flagCargaSaldo && !flagCargaFactura) {
            flagHabilitada = false;
        }
    }
}