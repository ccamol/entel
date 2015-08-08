/* Propiedad de Entel. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.util;


import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MensajeParaTiBean;

/**
 * Las clases que implementan {@link MensajeHandler} resuelven la obtencion de
 * un mensaje para 'Mensajes para ti'
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public interface MensajeHandler {

    /**
     * Entrega el mensaje para el que este handler esta pensado
     * @return {@link MensajeParaTiBean} con el 'Mensaje Para Ti' para el usuario
     * @throws Exception TODO
     */
    public MensajeParaTiBean getMensaje(ProfileWrapper profile) throws Exception;

    /**
     * Entrega el Id de mensaje para el que este MensajeHandler esta preparado
     * @return String con el id de mensaje que este handler procesa
     */
    public String getMensajeId();
    

}
