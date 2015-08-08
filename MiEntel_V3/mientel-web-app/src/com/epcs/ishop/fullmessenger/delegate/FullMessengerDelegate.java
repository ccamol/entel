/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.ishop.fullmessenger.delegate;

import com.epcs.ishop.fullmessenger.dao.FullMessengerDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class FullMessengerDelegate {
    
    private FullMessengerDAO fullMessengerDAO;
    
    public FullMessengerDelegate(){
        fullMessengerDAO = new FullMessengerDAO();
    }

    /**
     * @return the fullMessengerDAO
     */
    public FullMessengerDAO getFullMessengerDAO() {
        return fullMessengerDAO;
    }

    /**
     * @param fullMessengerDAO the fullMessengerDAO to set
     */
    public void setFullMessengerDAO(FullMessengerDAO fullMessengerDAO) {
        this.fullMessengerDAO = fullMessengerDAO;
    }
    
    /**
     * Obtiene la clave del usuario empleando su numeroPcs
     * @param numeroPcs
     * @return {@link String} clave del usario
     */
    public String obtenerClaveByNumeroPcs(String numeroPcs) throws DAOException, ServiceException{
        return fullMessengerDAO.obtenerClaveByNumeroPcs(numeroPcs);
    }

}
