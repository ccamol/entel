/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.appmobile.vo.Bundle;

import java.io.Serializable;
import java.util.List;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * Aug 26, 2014
 */

public class AvailableBundleBAMSSCC implements Serializable {
    
	private static final long serialVersionUID = -609802566632360204L;
	private List<BundleSSCCVO> bolsasActuales;
    private List<BundleSSCCVO> bolsasDisponibles;
    private List<BundleSSCCVO> bolsas;
    
    public List<BundleSSCCVO> getBolsasActuales() {
        return bolsasActuales;
    }

    public void setBolsasActuales(List<BundleSSCCVO> bolsasActuales) {
        this.bolsasActuales = bolsasActuales;
    }

    public List<BundleSSCCVO> getBolsasDisponibles() {
        return bolsasDisponibles;
    }

    public void setBolsasDisponibles(List<BundleSSCCVO> bolsasDisponibles) {
        this.bolsasDisponibles = bolsasDisponibles;
    }

	public List<BundleSSCCVO> getBolsas() {
		return bolsas;
	}

	public void setBolsas(List<BundleSSCCVO> bolsas) {
		this.bolsas = bolsas;
	}

}
