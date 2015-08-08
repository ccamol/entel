/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class AlertaCuotaTraficoBean implements Serializable,Comparable<AlertaCuotaTraficoBean> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String movil;

    private Date fechaEnvioSMS;
    
    private String fechaEnvioSMSFormat;

    private String consumoMBAlerta;

    private String txtSMS;

	/**
	 * @return the movil
	 */
	public String getMovil() {
		return movil;
	}

	/**
	 * @param movil the movil to set
	 */
	public void setMovil(String movil) {
		this.movil = movil;
	}


	/**
	 * @return the fechaEnvioSMS
	 */
	public Date getFechaEnvioSMS() {
		return fechaEnvioSMS;
	}

	/**
	 * @param fechaEnvioSMS the fechaEnvioSMS to set
	 */
	public void setFechaEnvioSMS(Date fechaEnvioSMS) {
		this.fechaEnvioSMS = fechaEnvioSMS;
	}

	/**
	 * @return the consumoMBAlerta
	 */
	public String getConsumoMBAlerta() {
		return consumoMBAlerta;
	}

	/**
	 * @param consumoMBAlerta the consumoMBAlerta to set
	 */
	public void setConsumoMBAlerta(String consumoMBAlerta) {
		this.consumoMBAlerta = consumoMBAlerta;
	}

	/**
	 * @return the txtSMS
	 */
	public String getTxtSMS() {
		return txtSMS;
	}

	/**
	 * @param txtSMS the txtSMS to set
	 */
	public void setTxtSMS(String txtSMS) {
		this.txtSMS = txtSMS;
	}	
	
	/**
	 * @return the fechaEnvioSMSFormat
	 */
	public String getFechaEnvioSMSFormat() {
		return fechaEnvioSMSFormat;
	}

	/**
	 * @param fechaEnvioSMSFormat the fechaEnvioSMSFormat to set
	 */
	public void setFechaEnvioSMSFormat(String fechaEnvioSMSFormat) {
		this.fechaEnvioSMSFormat = fechaEnvioSMSFormat;
	}

	@Override
	public int  compareTo(AlertaCuotaTraficoBean alertaCuotaTraficoBean) {
		 return alertaCuotaTraficoBean.getFechaEnvioSMS().compareTo(this.getFechaEnvioSMS());		
	}
   
	

}
