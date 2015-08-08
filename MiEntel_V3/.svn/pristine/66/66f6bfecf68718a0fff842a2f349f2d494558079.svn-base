/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;

import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * Bean con los valores constantes al llamar a la URL de Pago de transbank, para
 * el medio de pago WebPay
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class PagoWebPayBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String urlFormAction;

    private String tipoTransaccion;

    private String numTrx;

    private String codigoTienda;

    private String urlResultado;

    private String urlFracaso;

    /**
     * 
     */
    public PagoWebPayBean() {
        super();

        loadConstants();
        
    }

    /**
     * Instancia las constantes de pago de webpay, agregando a las url de exito
     * y fracaso el context Path indicado en <code>contextPath</code>
     * 
     * @param contextPath
     *            Context Path que debe concatenarse al principio de las URL de
     *            exito y fracaso
     */
    public PagoWebPayBean(String contextPath) {
        super();

        loadConstants();

        this.urlResultado = contextPath + MiEntelProperties
                .getProperty("recarga.webpay.pago.TBK_URL_RESULTADO");
        
        this.urlFracaso = contextPath + MiEntelProperties
                .getProperty("recarga.webpay.pago.TBK_URL_FRACASO");
        
    }

    private void loadConstants() {
        this.urlFormAction = MiEntelProperties
                .getProperty("recarga.webpay.pago.action");

        this.tipoTransaccion = MiEntelProperties
                .getProperty("recarga.webpay.pago.TBK_TIPO_TRANSACCION");

        this.numTrx = MiEntelProperties
                .getProperty("recarga.webpay.pago.TBK_NUM_TRX");

        this.codigoTienda = MiEntelProperties
                .getProperty("recarga.webpay.pago.TBK_CODIGO_TIENDA_M001");

        this.urlResultado = MiEntelProperties
                .getProperty("recarga.webpay.pago.TBK_URL_RESULTADO");

        this.urlFracaso = MiEntelProperties
                .getProperty("recarga.webpay.pago.TBK_URL_FRACASO");
    }
    
    /**
     * @return the urlFormAction
     */
    public String getUrlFormAction() {
        return urlFormAction;
    }

    /**
     * @param urlFormAction
     *            the urlFormAction to set
     */
    public void setUrlFormAction(String urlFormAction) {
        this.urlFormAction = urlFormAction;
    }

    /**
     * @return the tipoTransaccion
     */
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    /**
     * @param tipoTransaccion
     *            the tipoTransaccion to set
     */
    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    /**
     * @return the numTrx
     */
    public String getNumTrx() {
        return numTrx;
    }

    /**
     * @param numTrx
     *            the numTrx to set
     */
    public void setNumTrx(String numTrx) {
        this.numTrx = numTrx;
    }

    /**
     * @return the codigoTienda
     */
    public String getCodigoTienda() {
        return codigoTienda;
    }

    /**
     * @param codigoTienda
     *            the codigoTienda to set
     */
    public void setCodigoTienda(String codigoTienda) {
        this.codigoTienda = codigoTienda;
    }

    /**
     * @return the urlResultado
     */
    public String getUrlResultado() {
        return urlResultado;
    }

    /**
     * @param urlResultado
     *            the urlResultado to set
     */
    public void setUrlResultado(String urlResultado) {
        this.urlResultado = urlResultado;
    }

    /**
     * @return the urlFracaso
     */
    public String getUrlFracaso() {
        return urlFracaso;
    }

    /**
     * @param urlFracaso
     *            the urlFracaso to set
     */
    public void setUrlFracaso(String urlFracaso) {
        this.urlFracaso = urlFracaso;
    }

}
