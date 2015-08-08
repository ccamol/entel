/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class MensajesManager implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * List con handlers para mensajes
     */
    private List<MensajeHandler> handlers;

    /**
     * 
     */
    public MensajesManager() {
        super();
        this.handlers = new ArrayList<MensajeHandler>();
    }

    public List<MensajeHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<MensajeHandler> handlers) {
        this.handlers = handlers;
    }

    /**
     * Entrega el MensajeHandler que maneja el mensaje asociado
     * <code>mensajeId</code>
     * 
     * @param mensajeId
     * @return {@link MensajeHandler}
     */
    public MensajeHandler getMensajeHandler(String mensajeId) {

        MensajeHandler ret = null;
        for (MensajeHandler handler : handlers) {
            if (handler.getMensajeId().equals(mensajeId)) {
                ret = handler;
                break;
            }
        }
        return ret;

    }

}
