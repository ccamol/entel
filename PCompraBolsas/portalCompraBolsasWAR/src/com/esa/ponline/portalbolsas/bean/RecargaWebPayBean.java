/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.bean;

/**
 * Bean para recargas con tarjeta de credito
 * 
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class RecargaWebPayBean extends RecargaBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Double montoRecarga;

    private String codigoUnicoAutorizacion;
    
    private Long TBKMontoRecarga;

    // Campos retorno a efectuar recarga

    private Double montoBono;

    private String descuentoAcumulado;

    private Double nuevoSaldo;

    private String validezRecarga;

    private String mensajePublicidad;

    private String respuestaTransbank;

    private String fechaTransaccionTransbank;
    
    private String idp;
    
    private String codigoRespuestaTBK;
    
    private String descripcionRespuestaTBK;
    
    
    /**
     * @return the montoRecarga
     */
    public Double getMontoRecarga() {
        return montoRecarga;
    }

    /**
     * @param montoRecarga
     *            the montoRecarga to set
     */
    public void setMontoRecarga(Double montoRecarga) {
        this.montoRecarga = montoRecarga;
    }

    /**
     * @return the codigoUnicoAutorizacion
     */
    public String getCodigoUnicoAutorizacion() {
        return codigoUnicoAutorizacion;
    }

    /**
     * @param codigoUnicoAutorizacion
     *            the codigoUnicoAutorizacion to set
     */
    public void setCodigoUnicoAutorizacion(String codigoUnicoAutorizacion) {
        this.codigoUnicoAutorizacion = codigoUnicoAutorizacion;
    }

    /**
     * @return the montoBono
     */
    public Double getMontoBono() {
        return montoBono;
    }

    /**
     * @param montoBono
     *            the montoBono to set
     */
    public void setMontoBono(Double montoBono) {
        this.montoBono = montoBono;
    }

    /**
     * @return the descuentoAcumulado
     */
    public String getDescuentoAcumulado() {
        return descuentoAcumulado;
    }

    /**
     * @param descuentoAcumulado
     *            the descuentoAcumulado to set
     */
    public void setDescuentoAcumulado(String descuentoAcumulado) {
        this.descuentoAcumulado = descuentoAcumulado;
    }

    /**
     * @return the nuevoSaldo
     */
    public Double getNuevoSaldo() {
        return nuevoSaldo;
    }

    /**
     * @param nuevoSaldo
     *            the nuevoSaldo to set
     */
    public void setNuevoSaldo(Double nuevoSaldo) {
        this.nuevoSaldo = nuevoSaldo;
    }

    /**
     * @return the validezRecarga
     */
    public String getValidezRecarga() {
        return validezRecarga;
    }

    /**
     * @param validezRecarga
     *            the validezRecarga to set
     */
    public void setValidezRecarga(String validezRecarga) {
        this.validezRecarga = validezRecarga;
    }

    /**
     * @return the mensajePublicidad
     */
    public String getMensajePublicidad() {
        return mensajePublicidad;
    }

    /**
     * @param mensajePublicidad
     *            the mensajePublicidad to set
     */
    public void setMensajePublicidad(String mensajePublicidad) {
        this.mensajePublicidad = mensajePublicidad;
    }

    /**
     * @return the respuestaTransbank
     */
    public String getRespuestaTransbank() {
        return respuestaTransbank;
    }

    /**
     * @param respuestaTransbank
     *            the respuestaTransbank to set
     */
    public void setRespuestaTransbank(String respuestaTransbank) {
        this.respuestaTransbank = respuestaTransbank;
    }

    /**
     * @return the fechaTransaccionTransbank
     */
    public String getFechaTransaccionTransbank() {
        return fechaTransaccionTransbank;
    }

    /**
     * @param fechaTransaccionTransbank the fechaTransaccionTransbank to set
     */
    public void setFechaTransaccionTransbank(String fechaTransaccionTransbank) {
        this.fechaTransaccionTransbank = fechaTransaccionTransbank;
    }
    
    /**
     * @return the idp
     */
    public String getIdp() {
        return idp;
    }

    /**
     * @param idp the idp to set
     */
    public void setIdp(String idp) {
        this.idp = idp;
    }
    
    // ---------------------------------- metodos con formateo para Transbank

	/**
     * Entrega la propiedad montoRecarga con el formato que exige Transbank para
     * Webpay
     * 
     * @return montoRecarga en formato String, sin separadores de decimales ni
     *         miles y multiplicado por 100.<br>
     *         Ejemplo: si montoRecarga tiene el valor 4000, este metodo entrega
     *         el String "400000" (Sin las comillas)
     */
    public String getTBKMontoRecarga() {
        this.TBKMontoRecarga = this.getMontoRecarga().longValue() * 100;
        return TBKMontoRecarga.toString();
    }

	/**
	 * @return the codigoRespuestaTBK
	 */
	public String getCodigoRespuestaTBK() {
		return codigoRespuestaTBK;
	}

	/**
	 * @param codigoRespuestaTBK the codigoRespuestaTBK to set
	 */
	public void setCodigoRespuestaTBK(String codigoRespuestaTBK) {
		this.codigoRespuestaTBK = codigoRespuestaTBK;
	}

	/**
	 * @return the descripcionRespuestaTBK
	 */
	public String getDescripcionRespuestaTBK() {
		return descripcionRespuestaTBK;
	}

	/**
	 * @param descripcionRespuestaTBK the descripcionRespuestaTBK to set
	 */
	public void setDescripcionRespuestaTBK(String descripcionRespuestaTBK) {
		this.descripcionRespuestaTBK = descripcionRespuestaTBK;
	}
}
