/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ResultadoObtenerSuscripcionCDFOrqBean {
    
	private RespuestaBean respuesta;
    private List<SuscripcionCDFOrqBean> suscripciones;
    
    public ResultadoObtenerSuscripcionCDFOrqBean(){
    	respuesta = new RespuestaBean();
    }

    /**
	 * @return the respuesta
	 */
	public RespuestaBean getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(RespuestaBean respuesta) {
		this.respuesta = respuesta;
	}

	/**
     * @return the suscripciones
     */
    public List<SuscripcionCDFOrqBean> getSuscripciones() {
    	if(suscripciones == null){
    		suscripciones = new LinkedList<SuscripcionCDFOrqBean>();
    	}
        return suscripciones;
    }

    /**
     * @param suscripciones the suscripciones to set
     */
    public void setSuscripciones(List<SuscripcionCDFOrqBean> suscripciones) {
        this.suscripciones = suscripciones;
    }
        
}
