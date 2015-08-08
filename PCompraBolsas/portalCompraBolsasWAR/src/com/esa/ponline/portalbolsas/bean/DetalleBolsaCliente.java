package com.esa.ponline.portalbolsas.bean;

import java.io.Serializable;

import com.epcs.provision.suscripcion.bolsaspp.types.ListarBolsasResponseType.Mensaje.ListadoCartas.DetalleCartaServicio;

/**
 * @author ccastro
 *
 */

public class DetalleBolsaCliente extends DetalleCartaServicio implements Serializable {

    private static final long serialVersionUID = 1714382180989879102L;
    
	private String saldoInsuficiente;

    public DetalleBolsaCliente() {
        super();
    }

	/**
	 * @return the saldoInsuficiente
	 */
	public String getSaldoInsuficiente() {
		return saldoInsuficiente;
	}

	/**
	 * @param saldoInsuficiente the saldoInsuficiente to set
	 */
	public void setSaldoInsuficiente(String saldoInsuficiente) {
		this.saldoInsuficiente = saldoInsuficiente;
	}

}
