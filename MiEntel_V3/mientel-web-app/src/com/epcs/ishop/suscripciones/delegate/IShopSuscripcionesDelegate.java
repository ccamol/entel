/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.ishop.suscripciones.delegate;

import java.util.List;

import com.epcs.bean.ResumenHistoricoSuscripcionesBean;
import com.epcs.bean.ResumenSuscripcionesBean;
import com.epcs.bean.ResumenSuscripcionesOrqBean;
import com.epcs.ishop.suscripciones.dao.IShopSuscripcionesDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.exception.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class IShopSuscripcionesDelegate {
    
    private IShopSuscripcionesDAO iShopSuscripcionesDAO;

    /**
     * @return the iShopSuscripcionesDAO
     */
    public IShopSuscripcionesDAO getiShopSuscripcionesDAO() {
        return iShopSuscripcionesDAO;
    }

    /**
     * @param iShopSuscripcionesDAO the iShopSuscripcionesDAO to set
     */
    public void setiShopSuscripcionesDAO(IShopSuscripcionesDAO iShopSuscripcionesDAO) {
        this.iShopSuscripcionesDAO = iShopSuscripcionesDAO;
    }
    
    public void desuscribir(String msisdn, String idSuscripcion) throws DAOException, ServiceException {
        this.iShopSuscripcionesDAO.desuscribir(msisdn, idSuscripcion);
    }
    
    public List<ResumenHistoricoSuscripcionesBean> obtenerHistoricoSuscripciones(String msisdn, String fechaDesde, String fechaHasta, String idSuscripcion) throws DAOException, ServiceException {
        return this.iShopSuscripcionesDAO.obtenerHistoricoSuscripciones(msisdn, fechaDesde, fechaHasta, idSuscripcion);
    }
    
    public List<ResumenSuscripcionesBean> obtenerSuscripciones() throws DAOException, ServiceException {
        return this.iShopSuscripcionesDAO.obtenerSuscripciones();
    }
    
    public List<ResumenSuscripcionesBean> obtenerSuscripcionesUsuario(String msisdn) throws DAOException, ServiceException {
        return this.iShopSuscripcionesDAO.obtenerSuscripcionesUsuario(msisdn);
    }
    
    public void suscribir(String msisdn, String idSuscripcion) throws DAOException, ServiceException {
        this.iShopSuscripcionesDAO.suscribir(msisdn, idSuscripcion);
    }
    
    public List<ResumenSuscripcionesOrqBean> obtenerSuscripcionesUsuarioOrq(String msisdn) throws DAOException, ServiceException {
        return this.iShopSuscripcionesDAO.obtenerSuscripcionesUsuarioOrq(msisdn);
    }
}
