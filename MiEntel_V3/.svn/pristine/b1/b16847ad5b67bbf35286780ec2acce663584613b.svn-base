/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class SaldoYBolsaDisponiblesCompraBAMBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * @return the saldoBolsaPPBAM
     */
    public SaldoBolsaPPBAMBean getSaldoBolsaPPBAM() {
        return saldoBolsaPPBAM;
    }
    /**
     * @param saldoBolsaPPBAM the saldoBolsaPPBAM to set
     */
    public void setSaldoBolsaPPBAM(SaldoBolsaPPBAMBean saldoBolsaPPBAM) {
        this.saldoBolsaPPBAM = saldoBolsaPPBAM;
    }
    /**
     * @return the bolsasDisponibles
     */
    public List<BolsaPPBean> getBolsasDisponibles() {
        return bolsasDisponibles;
    }
    /**
     * @param bolsasDisponibles the bolsasDisponibles to set
     */
    public void setBolsasDisponibles(List<BolsaPPBean> bolsasDisponibles) {
        this.bolsasDisponibles = bolsasDisponibles;
    }
    /**
     * @param saldoRecargas the saldoRecargas to set
     */
    public void setSaldoRecargas(double saldoRecargas) {
        this.saldoRecargas = saldoRecargas;
    }
    /**
     * @return the saldoRecargas
     */
    public double getSaldoRecargas() {
        return saldoRecargas;
    }
        
    public Date getVigenciaSaldo() {
		return vigenciaSaldo;
	}
	public void setVigenciaSaldo(Date vigenciaSaldo) {
		this.vigenciaSaldo = vigenciaSaldo;
	}

	public List<BolsaPPBean> getBolsasActivas() {
		return bolsasActivas;
	}
	public void setBolsasActivas(List<BolsaPPBean> bolsasActivas) {
		this.bolsasActivas = bolsasActivas;
	}

    private SaldoBolsaPPBAMBean saldoBolsaPPBAM;
    private List<BolsaPPBean> bolsasDisponibles;
    private List<BolsaPPBean> bolsasActivas;    
    private double saldoRecargas;
    private Date vigenciaSaldo;

}
