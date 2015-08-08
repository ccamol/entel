package com.epcs.cliente.perfil.bean;

import java.io.Serializable;

import java.util.Date;

public class Oferta implements Serializable, Comparable<Oferta>{
	
	private static final long serialVersionUID = 1L;
	
	private String ofertaId;
    private String grupoOferta;
	private String codBscsOferta;
    private String rut;
    private String dv;
    private boolean visitada;
    private String urlOferta;
    private String movil;
    private int tipo;
    private String estado;
    private boolean rechazada;
    private int codigoSolicito;
    private Date fechaSolicito;
    private int cargoFijo;
    private String tipoBolsaPlan;
    private String codPlantilla;

    public Oferta(String ofertaId, String grupoOferta, boolean visitada) {
        this.ofertaId = ofertaId;
        this.grupoOferta = grupoOferta;
        this.visitada = visitada;
    }

    public Oferta() {
    }

    public String getCodPlantilla() {
		return codPlantilla;
	}

	public void setCodPlantilla(String codPlantilla) {
		this.codPlantilla = codPlantilla;
	}

	public boolean isRechazada() {
        return rechazada;
    }

    public void setRechazada(boolean rechazada) {
        this.rechazada = rechazada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getGrupoOferta() {
        return grupoOferta;
    }

    public void setGrupoOferta(String grupoOferta) {
        this.grupoOferta = grupoOferta;
    }

    public String getOfertaId() {
        return ofertaId;
    }

    public void setOfertaId(String ofertaId) {
        this.ofertaId = ofertaId;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public boolean isVisitada() {
        return visitada;
    }

    public void setVisitada(boolean visitada) {
        this.visitada = visitada;
    }

    public String getUrlOferta() {
        return urlOferta;
    }

    public void setUrlOferta(String urlOferta) {
        this.urlOferta = urlOferta;
    }

    public int getCodigoSolicito(){
        return codigoSolicito;
    }

    public void setCodigoSolicito(int codigoSolicito){
        this.codigoSolicito = codigoSolicito;
    }

    public Date getFechaSolicito(){
        return fechaSolicito;
    }

    public void setFechaSolicito(Date fechaSolicito){
        this.fechaSolicito = fechaSolicito;
    }

	public void setCodBscsOferta(String codBscsOferta) {
		this.codBscsOferta = codBscsOferta;
	}

	public String getCodBscsOferta() {
		return codBscsOferta;
	}

	/**
	 * @return the cargoFijo
	 */
	public int getCargoFijo() {
		return cargoFijo;
	}

	/**
	 * @param cargoFijo the cargoFijo to set
	 */
	public void setCargoFijo(int cargoFijo) {
		this.cargoFijo = cargoFijo;
	}

	/**
	 * @return the tipoBolsaPlan
	 */
	public String getTipoBolsaPlan() {
		return tipoBolsaPlan;
	}

	/**
	 * @param tipoBolsaPlan the tipoBolsaPlan to set
	 */
	public void setTipoBolsaPlan(String tipoBolsaPlan) {
		this.tipoBolsaPlan = tipoBolsaPlan;
	}
	
	@Override
	public int compareTo(Oferta o) {        
        
        if(this.cargoFijo < o.getCargoFijo())
        	return -1;
        else if(this.cargoFijo == o.getCargoFijo())
        	return 0;
        else
        	return 1;        
    }
	
}
