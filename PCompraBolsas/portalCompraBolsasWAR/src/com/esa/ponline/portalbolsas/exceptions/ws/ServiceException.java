/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.exceptions.ws;

/**
 * Exception para los errores generados en el consumo de los Servicios desde el
 * Service Bus.<br>
 * Toda excepcion de este tipo, debe indicar el codigo de error (
 * <code>codigoRespusta</code>) y el mensaje del error (
 * <code>descripcionRespuesta</code>)
 * 
 * @author ccastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ServiceException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Codigo de error del Servicio
     */
    private String codigoRespuesta;

    /**
     * Mensaje descriptivo del error ocurrido en el servicio
     */
    private String descripcionRespuesta;

    /**
     * Constructor para excepciones de servicios.<br>
     * Toda instancia de esta excepcion debe indicar el codigo de error del
     * servicio y el mensaje descriptivo
     * 
     * @param codigoRespuesta
     *            codigo de error del Servicio
     * @param descripcionRespuesta
     *            mensaje de error del servicio
     */
    public ServiceException(String codigoRespuesta, String descripcionRespuesta) {
        super();
        this.codigoRespuesta = codigoRespuesta;
        this.descripcionRespuesta = descripcionRespuesta;
    }

    /**
     * @return the codigoRespuesta
     */
    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    /**
     * @param codigoRespuesta
     *            the codigoRespuesta to set
     */
    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    /**
     * @return the descripcionRespuesta
     */
    public String getDescripcionRespuesta() {
        return descripcionRespuesta;
    }

    /**
     * @param descripcionRespuesta
     *            the descripcionRespuesta to set
     */
    public void setDescripcionRespuesta(String descripcionRespuesta) {
        this.descripcionRespuesta = descripcionRespuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return codigoRespuesta + " - " + descripcionRespuesta;
    }

}
