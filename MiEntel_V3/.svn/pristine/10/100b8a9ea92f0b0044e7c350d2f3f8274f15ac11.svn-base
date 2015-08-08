/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.ishop.agendamiento.delegate;

import java.util.List;

import com.epcs.bean.ResumenAgendamientoBean;
import com.epcs.bean.ResumenContenidoBean;
import com.epcs.ishop.agendamiento.dao.IShopAgendamientoDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.exception.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class IShopAgendamientoDelegate {
    
    private IShopAgendamientoDAO iShopAgendamientoDAO;

    /**
     * @return the iShopAgendamientoDAO
     */
    public IShopAgendamientoDAO getiShopAgendamientoDAO() {
        return iShopAgendamientoDAO;
    }

    /**
     * @param iShopAgendamientoDAO the iShopAgendamientoDAO to set
     */
    public void setiShopAgendamientoDAO(IShopAgendamientoDAO iShopAgendamientoDAO) {
        this.iShopAgendamientoDAO = iShopAgendamientoDAO;
    }
    
    public List<ResumenContenidoBean> consultarContenido(String tipo) throws DAOException, ServiceException {
        return this.iShopAgendamientoDAO.consultarContenido(tipo);
    }
    
    public List<ResumenAgendamientoBean> consultarAgendamiento(String tipoServicio, String msisdn) throws DAOException, ServiceException {
        return this.iShopAgendamientoDAO.consultarAgendamiento(tipoServicio, msisdn);
    }
    
    public void pausarAgendamiento(String tipoMensajeria, String tipo, String idPedido, String hora, String msisdn) throws DAOException, ServiceException {
        this.iShopAgendamientoDAO.pausarAgendamiento(tipoMensajeria, tipo, idPedido, hora, msisdn);
    }
    
    public void despausarAgendamiento(String tipoMensajeria, String tipo, String idPedido, String hora, String msisdn) throws DAOException, ServiceException {
        this.iShopAgendamientoDAO.despausarAgendamiento(tipoMensajeria, tipo, idPedido, hora, msisdn);
    }
    
    public void eliminarAgendamiento(String tipoMensajeria, String tipo, String idPedido, String hora, String msisdn) throws DAOException, ServiceException {
        this.iShopAgendamientoDAO.eliminarAgendamiento(tipoMensajeria, tipo, idPedido, hora, msisdn);
    }
    
    public void pausarTodosAgendamiento(String tipoMensajeria, String msisdn) throws DAOException, ServiceException {
        this.iShopAgendamientoDAO.pausarTodosAgendamiento(tipoMensajeria, msisdn);
    }
    
    public void despausarTodosAgendamiento(String tipoMensajeria, String msisdn) throws DAOException, ServiceException {
        this.iShopAgendamientoDAO.despausarTodosAgendamiento(tipoMensajeria, msisdn);
    }    
}
