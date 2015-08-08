/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.seguridad.bean;

import java.io.Serializable;

/**
 * Bean que encapsula las respuestas de los mecanismos de autenticacion (providers).
 * @author mmartinez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class LoginRespuestaBean implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 5150920272867755152L;
    /**
     * codigo de la repuesta. en algunos casos el 0 o 0000 indica que el usuario es valido.
     */
    private String codigoRespuesta;
    /**
     * encapsula el mensaje desde la respuesta del servicio de autenticacion.
     */
    private String mensajeRespuesta;

    /**
     * @param codigoRespuesta the codigoRespuesta to set
     */
    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    /**
     * @return the codigoRespuesta
     */
    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    /**
     * @param mensajeRespuesta the mensajeRespuesta to set
     */
    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    /**
     * @return the mensajeRespuesta
     */
    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }
}
