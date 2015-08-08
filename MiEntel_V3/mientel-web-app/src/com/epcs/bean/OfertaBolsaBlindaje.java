package com.epcs.bean;

public class OfertaBolsaBlindaje {

    private long codigo;
    private String codigoBolsa;
    
    private String nombreBolsa;
    private String tipoBolsa;
    private String descripcionBolsa1;
    private String descripcionBolsa2;
    private String descripcionBolsa3;
    private String descripcionBolsa4;
    
    private String numeroMovil;
    
    private String descripcionArg1;
    private String descripcionArg2;
    private String descripcionArg3;
    private String descripcionArg4;
    private String descripcionArg5;
    private String descripcionArg6;
    
    int valorBolsa;
    

    public OfertaBolsaBlindaje(){
    }
    
    public OfertaBolsaBlindaje(long codigo, String codigoBolsa, int valorBolsa, String nombreBolsa, String tipoBolsa, String descripcionBolsa1, String descripcionBolsa2, String descripcionBolsa3, String descripcionBolsa4, String numeroMovil, String descripcionArg1, String descripcionArg2, String descripcionArg3, String descripcionArg4, String descripcionArg5, String descripcionArg6) {
        this.codigo = codigo;
        this.codigoBolsa = codigoBolsa;
        this.nombreBolsa = nombreBolsa;
        this.tipoBolsa = tipoBolsa;
        this.descripcionBolsa1 = descripcionBolsa1;
        this.descripcionBolsa2 = descripcionBolsa2;
        this.descripcionBolsa3 = descripcionBolsa3;
        this.descripcionBolsa4 = descripcionBolsa4;
        this.numeroMovil = numeroMovil;
        this.descripcionArg1 = descripcionArg1;
        this.descripcionArg1 = descripcionArg2;
        this.descripcionArg1 = descripcionArg3;
        this.descripcionArg1 = descripcionArg4;
        this.descripcionArg1 = descripcionArg5;
        this.descripcionArg1 = descripcionArg6;
        this.valorBolsa = valorBolsa;
    }
    
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public void setCodigoBolsa(String codigoBolsa) {
        this.codigoBolsa = codigoBolsa;
    }
    
    public void setTipoBolsa(String tipoBolsa) {
        this.tipoBolsa = tipoBolsa;
    }

    public void setDescripcionArg1(String descripcionArg1) {
        this.descripcionArg1 = descripcionArg1;
    }

    public void setDescripcionArg2(String descripcionArg2) {
        this.descripcionArg2 = descripcionArg2;
    }

    public void setDescripcionArg3(String descripcionArg3) {
        this.descripcionArg3 = descripcionArg3;
    }

    public void setDescripcionArg4(String descripcionArg4) {
        this.descripcionArg4 = descripcionArg4;
    }

    public void setDescripcionArg5(String descripcionArg5) {
        this.descripcionArg5 = descripcionArg5;
    }

    public void setDescripcionArg6(String descripcionArg6) {
        this.descripcionArg6 = descripcionArg6;
    }

    public void setDescripcionBolsa1(String descripcionBolsa1) {
        this.descripcionBolsa1 = descripcionBolsa1;
    }

    public void setDescripcionBolsa2(String descripcionBolsa2) {
        this.descripcionBolsa2 = descripcionBolsa2;
    }

    public void setDescripcionBolsa3(String descripcionBolsa3) {
        this.descripcionBolsa3 = descripcionBolsa3;
    }

    public void setDescripcionBolsa4(String descripcionBolsa4) {
        this.descripcionBolsa4 = descripcionBolsa4;
    }

    public void setNumeroMovil(String numeroMovil) {
        this.numeroMovil = numeroMovil;
    }

    public void setValorBolsa(int valorBolsa) {
        this.valorBolsa = valorBolsa;
    }

    public long getCodigo() {
        return codigo;
    }

    public String getCodigoBolsa() {
        return codigoBolsa;
    }

    public String getTipoBolsa() {
        return tipoBolsa;
    }
    
    public String getDescripcionArg1() {
        return descripcionArg1;
    }

    public String getDescripcionArg2() {
        return descripcionArg2;
    }

    public String getDescripcionArg3() {
        return descripcionArg3;
    }

    public String getDescripcionArg4() {
        return descripcionArg4;
    }

    public String getDescripcionArg5() {
        return descripcionArg5;
    }

    public String getDescripcionArg6() {
        return descripcionArg6;
    }

    public String getDescripcionBolsa1() {
        return descripcionBolsa1;
    }

    public String getDescripcionBolsa2() {
        return descripcionBolsa2;
    }

    public String getDescripcionBolsa3() {
        return descripcionBolsa3;
    }

    public String getDescripcionBolsa4() {
        return descripcionBolsa4;
    }

    public String getNumeroMovil() {
        return numeroMovil;
    }

    public int getValorBolsa() {
        return valorBolsa;
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

    
}
