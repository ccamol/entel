/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.delegate;

import com.epcs.bean.ResumenTraficoBamBean;
import com.epcs.billing.registrouso.dao.TraficoBamDAO;

/**
 * @author jroman (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoBamDelegate {
	
	private TraficoBamDAO traficoBamDAO;

	/**
	 * @return the traficoBamDAO
	 */
	public TraficoBamDAO getTraficoBamDAO() {
		return traficoBamDAO;
	}

	/**
	 * @param traficoBamDAO the traficoBamDAO to set
	 */
	public void setTraficoBamDAO(TraficoBamDAO traficoBamDAO) {
		this.traficoBamDAO = traficoBamDAO;
	}


    public ResumenTraficoBamBean getResumenTraficoBam(String numeroPcs,String mercado ,String autoAtencion, boolean logCajaTrafico) throws Exception {
        return this.traficoBamDAO.getResumenTraficoBam(numeroPcs,mercado, autoAtencion, logCajaTrafico);
    }

}
