/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.orden.delegate;

import com.epcs.cliente.orden.dao.ReposicionServicioDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ReposicionServicioDelegate {
    
    private ReposicionServicioDAO reposicionServicioDAO;

    /**
     * @param reposicionServicioDAO the reposicionServicioDAO to set
     */
    public void setReposicionServicioDAO(ReposicionServicioDAO reposicionServicioDAO) {
        this.reposicionServicioDAO = reposicionServicioDAO;
    }
    
    /**
     * Devuelve un String indicando el estado del servicio para reposicion
     * @param numeroPcs
     * @return String  "PERMITE" o "REALIZADA"
     * @throws DAOException
     * @throws ServiceException
     */
    public String consultarEstadoReposicion(String numeroPcs)
            throws DAOException, ServiceException {
        return this.reposicionServicioDAO.consultarEstadoReposicion(numeroPcs);
    }
    
    
    public void reponerServicio(String numeroPcs, String tipoReposicion) throws DAOException,
            ServiceException {
        this.reposicionServicioDAO.reponerServicio(numeroPcs,tipoReposicion);
    }

}
