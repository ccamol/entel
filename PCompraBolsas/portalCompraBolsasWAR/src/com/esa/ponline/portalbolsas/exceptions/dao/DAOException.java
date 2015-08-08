/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.exceptions.dao;

/**
 * Excepcion para errores en la logica de las clases DAO de PortalCompraBolsas.<br>
 * Esta Excepcion NO es un Wrapper de {@link ServiceException} ni debe contener
 * como su <code>cause</code> una ServiceException, puesto ellas se trabajan
 * distinto.<br>
 * Esta clase esta pensada para casos como:
 * <ul>
 * <li>Parametros de entrada: valor null, formato incorrecto, listas vacias,
 * etc. que impidan al metodo de la clase DAO realizar la correcta logica que
 * implementa.
 * <li>Respuesta de servicios: En el escenario en que un servicio contesta
 * correctamente, pero su respuesta no es la esperada por el metodo del DAO.
 * 
 * </ul>
 * 
 * @author ccastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class DAOException extends Exception {

    private static final long serialVersionUID = 1L;

    public DAOException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public DAOException(Throwable cause) {
        super(cause);
    }

}
