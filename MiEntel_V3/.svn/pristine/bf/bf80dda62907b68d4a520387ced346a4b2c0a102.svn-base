/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.inteligencianegocio.satisfaccioncliente.delegate;

import java.io.Serializable;

import com.epcs.bean.MarcaEstadisticaBean;
import com.epcs.inteligencianegocio.satisfaccioncliente.dao.EstadisticasDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/** 
 * @author zmanotas (I2B) 
 */
public class EstadisticasDelegate implements Serializable {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private EstadisticasDAO estadisticasDAO;
	
	/**
     * 
     */
    public EstadisticasDelegate() {
        this.estadisticasDAO = new EstadisticasDAO();
    }


    /**
     * 
     * @param marcaEstadisticaBean
     * @throws DAOException
     * @throws ServiceException
     */
    public void agregarMarcaEstadistica(MarcaEstadisticaBean  marcaEstadisticaBean)
            throws DAOException, ServiceException {
        this.estadisticasDAO.agregarMarcaEstadistica(marcaEstadisticaBean);
    }


    /**
     * @return the estadisticasDAO
     */
    public EstadisticasDAO getEstadisticasDAO() {
        return estadisticasDAO;
    }


    /**
     * @param estadisticasDAO the estadisticasDAO to set
     */
    public void setEstadisticasDAO(EstadisticasDAO estadisticasDAO) {
        this.estadisticasDAO = estadisticasDAO;
    }
}
