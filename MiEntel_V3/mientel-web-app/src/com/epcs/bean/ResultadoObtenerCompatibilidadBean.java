/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ResultadoObtenerCompatibilidadBean {
    
    private Integer streaming;
    private Integer video;
    
    public ResultadoObtenerCompatibilidadBean(){
        
    }

    /**
     * @return the streaming
     */
    public Integer getStreaming() {
        return streaming;
    }

    /**
     * @param streaming the streaming to set
     */
    public void setStreaming(Integer streaming) {
        this.streaming = streaming;
    }

    /**
     * @return the video
     */
    public Integer getVideo() {
        return video;
    }

    /**
     * @param video the video to set
     */
    public void setVideo(Integer video) {
        this.video = video;
    }

}
