/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.appmobile.vo.Bundle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * Aug 12, 2014
 */

public class BundleSSCCVO implements Serializable, Comparable<BundleSSCCVO> {
	
	private static final long serialVersionUID = -275085008105873434L;
	private String spCodigo;
    private String snCodigo;
    private String idBolsa;
    private String nombreBolsa;
    private List<String> descBolsa;
    private String observacion;
//    private Double costo;
    private String costo;
    private String icon;
    private Double cantidad;
    private String flagPromocion;
    private String tipoBolsa;
    private String tipoVigencia;
    private String vigencia;
    private String estado;
    private String nombreUserAction;
    private String tipoConsulta;
    private Date fechaPendiente;
    
    public String getSpCodigo() {
        return spCodigo;
    }

    public void setSpCodigo(String spCodigo) {
        this.spCodigo = spCodigo;
    }

    public String getSnCodigo() {
        return snCodigo;
    }

    public void setSnCodigo(String snCodigo) {
        this.snCodigo = snCodigo;
    }

    public String getNombreBolsa() {
        return nombreBolsa;
    }

    public void setNombreBolsa(String nombreBolsa) {
        this.nombreBolsa = nombreBolsa;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getFlagPromocion() {
        return flagPromocion;
    }

    public void setFlagPromocion(String flagPromocion) {
        this.flagPromocion = flagPromocion;
    }

    public String getTipoBolsa() {
        return tipoBolsa;
    }

    public void setTipoBolsa(String tipoBolsa) {
        this.tipoBolsa = tipoBolsa;
    }

    public String getTipoVigencia() {
        return tipoVigencia;
    }

    public void setTipoVigencia(String tipoVigencia) {
        this.tipoVigencia = tipoVigencia;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public List<String> getDescBolsa() {
        return descBolsa;
    }

    public void setDescBolsa(List<String> descBolsa) {
        this.descBolsa = descBolsa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaPendiente() {
        return fechaPendiente;
    }

    public void setFechaPendiente(Date fechaPendiente) {
        this.fechaPendiente = fechaPendiente;
    }

	public String getNombreUserAction() {
		return nombreUserAction;
	}

	public void setNombreUserAction(String nombreUserAction) {
		this.nombreUserAction = nombreUserAction;
	}
	
	@Override
	public int compareTo(BundleSSCCVO o) {
		if (this.nombreBolsa.length() < o.nombreBolsa.length()) {
			return -1;
		} else if (this.nombreBolsa.length() == o.nombreBolsa.length()) {
			return 0;
		}
		return 1;
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getIdBolsa() {
		return idBolsa;
	}

	public void setIdBolsa(String idBolsa) {
		this.idBolsa = idBolsa;
	}
}
