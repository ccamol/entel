/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.pago.delegate;

import com.epcs.bean.EstadoPagoAutomaticoBean;
import com.epcs.bean.EstadoPromoPagoAutomaticoBean;
import com.epcs.bean.PagoAutomaticoBean;
import com.epcs.billing.pago.dao.PagoAutomaticoDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class PagoAutomaticoDelegate {
    
    private PagoAutomaticoDAO pagoAutomaticoDAO;

    /**
     * @param pagoAutomaticoDAO the pagoAutomaticoDAO to set
     */
    public void setPagoAutomaticoDAO(PagoAutomaticoDAO pagoAutomaticoDAO) {
        this.pagoAutomaticoDAO = pagoAutomaticoDAO;
    }
    
    /**
     * Consultar el estado de pago automatico
     * @param msisdn
     * @param numeroCuenta
     * @return null si no esta inscrito
     * @throws DAOException
     * @throws ServiceException
     */
    public EstadoPagoAutomaticoBean consultarEstadoPAT(String msisdn, String numeroCuenta)throws DAOException,ServiceException{
    return this.pagoAutomaticoDAO.consultarEstadoPAT(msisdn, numeroCuenta);
    }
    
      
    /**
     *    
     * @param rut
     * @param numeroCuenta
     * @return null si no esta inscrito
     * @throws DAOException
     * @throws ServiceException
     */
    public EstadoPromoPagoAutomaticoBean consultarEstadoPromoPAT(String rut, String numeroCuenta)throws DAOException,ServiceException{
    return this.pagoAutomaticoDAO.consultarEstadoPromoPAT(rut, numeroCuenta);
    }
    
    /**
     * Realiza el registro de la sol de pago automatico
     * @param pagoAutomaticoBean
     * @param msisdn
     * @param numeroCuenta
     * @throws DAOException
     * @throws ServiceException
     */
    public void inscribirPAT(PagoAutomaticoBean pagoAutomaticoBean,
            String msisdn, String numeroCuenta) throws DAOException, ServiceException {
        this.pagoAutomaticoDAO.inscribirPAT(pagoAutomaticoBean, msisdn, numeroCuenta);
    }

}
