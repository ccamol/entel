/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.appmobile.vo.Bundle;

import java.io.Serializable;

import com.esa.ponline.appmobile.util.Formato;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * Aug 20, 2014
 */

public class BundleCCPPVO implements Serializable, Comparable<BundleCCPPVO> {

	private static final long serialVersionUID = 8029803958692638246L;
	private String codigo;
    private String nombre;
    private String nombreUserAction;
    private String descripcion;
    private String descripcionComercial;
    private String unidad;
    private String icon;
    private String cantInicial;
    private String tipoOferta;
    private String precio;
    private int cuotaInicialVoucher;
    private String precioFormatted;
    private String cuotaInicialVoucherFormatted;
    private boolean flagHabilitada;
    private boolean flagCargaFactura;
    private boolean flagCargaSaldo;
    private String descripcionCargaFactura;
    private String descripcionCargaSaldo;
    
    public BundleCCPPVO() {
        this.flagCargaSaldo = true;
        this.flagCargaFactura = true;
        this.flagHabilitada = true;
    }

    public BundleCCPPVO(String nombreUserAction) {
    	this.nombreUserAction = nombreUserAction;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

    public int getCuotaInicialVoucher() {
        return cuotaInicialVoucher;
    }
    
    public void setCuotaInicialVoucher(int cuotaInicialVoucher) {
        this.cuotaInicialVoucher = cuotaInicialVoucher;
        this.cuotaInicialVoucherFormatted = Formato.formatMoney(new Double(cuotaInicialVoucher));
    }

    public String getPrecioFormatted() {
        return precioFormatted;
    }

    public String getCuotaInicialVoucherFormatted() {
        return cuotaInicialVoucherFormatted;
    }

    public boolean isFlagCargaFactura() {
        return flagCargaFactura;
    }

    public void setFlagCargaFactura(boolean flagCargaFactura) {
        this.flagCargaFactura = flagCargaFactura;
        verificaHabilitada();
    }

    public boolean isFlagCargaSaldo() {
        return flagCargaSaldo;
    }

    public void setFlagCargaSaldo(boolean flagCargaSaldo) {
        this.flagCargaSaldo = flagCargaSaldo;
        verificaHabilitada();
    }

    public boolean isFlagHabilitada() {
        return flagHabilitada;
    }
    
    public String getDescripcionCargaFactura() {
        return descripcionCargaFactura;
    }

    public void setDescripcionCargaFactura(String descripcionCargaFactura) {
        this.descripcionCargaFactura = descripcionCargaFactura;
    }

    public String getDescripcionCargaSaldo() {
        return descripcionCargaSaldo;
    }

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

	@Override
	public int compareTo(BundleCCPPVO o) {
		if (this.nombre.length() < o.nombre.length()) {
			return -1;
		} else if (this.nombre.length() == o.nombre.length()) {
			return 0;
		}
		return 1;
	}

	public String getNombreUserAction() {
		return nombreUserAction;
	}

	public void setNombreUserAction(String nombreUserAction) {
		this.nombreUserAction = nombreUserAction;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getDescripcionComercial() {
		return descripcionComercial;
	}

	public void setDescripcionComercial(String descripcionComercial) {
		this.descripcionComercial = descripcionComercial;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getCantInicial() {
		return cantInicial;
	}

	public void setCantInicial(String cantInicial) {
		this.cantInicial = cantInicial;
	}

	public String getTipoOferta() {
		return tipoOferta;
	}

	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}
}