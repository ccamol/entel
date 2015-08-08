/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.bean;

import java.io.Serializable;

/**
 * Bean de factibilidad de recarga multitienda
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class FactibilidadRecargaMultitiendaBean extends FactibilidadRecargaBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
        
    private String plataforma;
    
    private String tipoOperacion;
    
    private String codEmpresa;
    
    private String codLocal;
        
    private String codRespuestaTransA;
    
    private String codRespuestaTransB;
    
    private String codTransMultitienda;
    
    private Integer montoRecarga;
    
    private String montoBono;
    
    private String nuevoSaldo;
    
    private String validezRecarga;
    
    private String codigoUnicoAutorizacion;

    /**
     * @param plataforma
     * @param tipoOperacion
     * @param codEmpresa
     * @param codLocal
     * @param codRespuestaTransA
     * @param codRespuestaTransB
     * @param codTransMultitienda
     * @param montoRecarga
     * @param montoBono
     * @param nuevoSaldo
     * @param validezRecarga
     * @param codigoUnicoAutorizacion
     */
    public FactibilidadRecargaMultitiendaBean(String plataforma,
            String tipoOperacion, String codEmpresa, String codLocal,
            String codRespuestaTransA, String codRespuestaTransB,
            String codTransMultitienda, Integer montoRecarga, String montoBono,
            String nuevoSaldo, String validezRecarga,
            String codigoUnicoAutorizacion) {
        super();
        this.plataforma = plataforma;
        this.tipoOperacion = tipoOperacion;
        this.codEmpresa = codEmpresa;
        this.codLocal = codLocal;
        this.codRespuestaTransA = codRespuestaTransA;
        this.codRespuestaTransB = codRespuestaTransB;
        this.codTransMultitienda = codTransMultitienda;
        this.montoRecarga = montoRecarga;
        this.montoBono = montoBono;
        this.nuevoSaldo = nuevoSaldo;
        this.validezRecarga = validezRecarga;
        this.codigoUnicoAutorizacion = codigoUnicoAutorizacion;
    }
    
    public FactibilidadRecargaMultitiendaBean(){
        
    }

    /**
     * @return the plataforma
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * @param plataforma the plataforma to set
     */
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * @return the tipoOperacion
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * @param tipoOperacion the tipoOperacion to set
     */
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * @return the codEmpresa
     */
    public String getCodEmpresa() {
        return codEmpresa;
    }

    /**
     * @param codEmpresa the codEmpresa to set
     */
    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    /**
     * @return the codLocal
     */
    public String getCodLocal() {
        return codLocal;
    }

    /**
     * @param codLocal the codLocal to set
     */
    public void setCodLocal(String codLocal) {
        this.codLocal = codLocal;
    }

    /**
     * @return the codRespuestaTransA
     */
    public String getCodRespuestaTransA() {
        return codRespuestaTransA;
    }

    /**
     * @param codRespuestaTransA the codRespuestaTransA to set
     */
    public void setCodRespuestaTransA(String codRespuestaTransA) {
        this.codRespuestaTransA = codRespuestaTransA;
    }

    /**
     * @return the codRespuestaTransB
     */
    public String getCodRespuestaTransB() {
        return codRespuestaTransB;
    }

    /**
     * @param codRespuestaTransB the codRespuestaTransB to set
     */
    public void setCodRespuestaTransB(String codRespuestaTransB) {
        this.codRespuestaTransB = codRespuestaTransB;
    }

    /**
     * @return the codTransMultitienda
     */
    public String getCodTransMultitienda() {
        return codTransMultitienda;
    }

    /**
     * @param codTransMultitienda the codTransMultitienda to set
     */
    public void setCodTransMultitienda(String codTransMultitienda) {
        this.codTransMultitienda = codTransMultitienda;
    }

    /**
     * @return the montoRecarga
     */
    public Integer getMontoRecarga() {
        return montoRecarga;
    }

    /**
     * @param montoRecarga the montoRecarga to set
     */
    public void setMontoRecarga(Integer montoRecarga) {
        this.montoRecarga = montoRecarga;
    }

    /**
     * @return the montoBono
     */
    public String getMontoBono() {
        return montoBono;
    }

    /**
     * @param montoBono the montoBono to set
     */
    public void setMontoBono(String montoBono) {
        this.montoBono = montoBono;
    }

    /**
     * @return the nuevoSaldo
     */
    public String getNuevoSaldo() {
        return nuevoSaldo;
    }

    /**
     * @param nuevoSaldo the nuevoSaldo to set
     */
    public void setNuevoSaldo(String nuevoSaldo) {
        this.nuevoSaldo = nuevoSaldo;
    }

    /**
     * @return the validezRecarga
     */
    public String getValidezRecarga() {
        return validezRecarga;
    }

    /**
     * @param validezRecarga the validezRecarga to set
     */
    public void setValidezRecarga(String validezRecarga) {
        this.validezRecarga = validezRecarga;
    }

    /**
     * @return the codigoUnicoAutorizacion
     */
    public String getCodigoUnicoAutorizacion() {
        return codigoUnicoAutorizacion;
    }

    /**
     * @param codigoUnicoAutorizacion the codigoUnicoAutorizacion to set
     */
    public void setCodigoUnicoAutorizacion(String codigoUnicoAutorizacion) {
        this.codigoUnicoAutorizacion = codigoUnicoAutorizacion;
    }

}
