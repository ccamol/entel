/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.parametros.delegate;

import java.io.Serializable;
import java.util.List;

import com.epcs.bean.CiudadBean;
import com.epcs.bean.ComunaBean;
import com.epcs.bean.RegionBean;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.parametros.dao.ParametrosDAO;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ParametrosDelegate implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ParametrosDAO parametrosDAO;

    public ParametrosDelegate() {
        super();
    }

    /**
     * @return the parametrosDAO
     */
    public ParametrosDAO getParametrosDAO() {
        return parametrosDAO;
    }

    /**
     * @param parametrosDAO
     *            the parametrosDAO to set
     */
    public void setParametrosDAO(ParametrosDAO parametrosDAO) {
        this.parametrosDAO = parametrosDAO;
    }

    /**
     * Devuelve el listado de Regiones
     * @return
     * @throws DAOException
     */
    public List<RegionBean> getRegionesList() throws DAOException {
        return this.parametrosDAO.getRegionesList();
    }
   

    /**
     * Entrega la lista de ciudades de la region indicada en <code>region</code>
     * 
     * @param region
     *            {@link RegionBean} region de la que se desean las ciudades
     * @return List<CiudadBean> con las ciudades de la region
     *         <code>region</code>
     * @throws DAOException 
     */
    public List<CiudadBean> getCiudadesList(RegionBean region) throws DAOException {
        return this.parametrosDAO.getCiudadesList(region);
    }

    /**
     * Entrega la lista de comunas de la ciudad indicada en <code>ciudad</code>
     * 
     * @param ciudad
     *            {@link CiudadBean} ciudad de la que se desean las comunas
     * @return List<ComunaBean> con las comunas de la ciudad <code>ciudad</code>
     * @throws DAOException 
     */
    public List<ComunaBean> getComunasList(RegionBean region,CiudadBean ciudad) throws DAOException {
        return this.parametrosDAO.getComunasList(region,ciudad);
    }

}
