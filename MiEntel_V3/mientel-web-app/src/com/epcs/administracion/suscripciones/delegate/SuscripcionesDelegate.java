/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.administracion.suscripciones.delegate;

import java.util.List;

import com.epcs.administracion.suscripciones.dao.SuscripcionesBolsaDAO;
import com.epcs.bean.BolsaBean;
import com.epcs.bean.BolsaPPBAMBean;
import com.epcs.bean.BolsaPPBean;
import com.epcs.bean.BolsasActualesDisponiblesBean;
import com.epcs.bean.OfertaBolsaBlindaje;
import com.epcs.bean.ResumenPlanPPBAM;
import com.epcs.bean.SaldoBolsaPPBAMBean;
import com.epcs.bean.SaldoYBolsaDisponiblesCompraBAMBean;
import com.epcs.cliente.perfil.dao.BolsaBlindajeDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class SuscripcionesDelegate {

    private SuscripcionesBolsaDAO suscripcionesBolsaDAO;
    private BolsaBlindajeDAO bolsaBlindajeDAO;
    
    public SuscripcionesDelegate(){
    	
    	bolsaBlindajeDAO = new BolsaBlindajeDAO();
    }
    
    /**
     * Consultar las bolsas actuales y disponibles de un usuario ss
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public BolsasActualesDisponiblesBean consultarBolsasActualesDisponibles(
            String msisdn) throws DAOException, ServiceException {
        return suscripcionesBolsaDAO.consultarBolsasActualesDisponibles(msisdn);
    }
    
    
    /**
     * Listado de Bolsas BB Actuales de un usuario
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public OfertaBolsaBlindaje obtenerBolsaBlindaje(String msisdn){
    	return bolsaBlindajeDAO.ObtenerBolsa(msisdn);
}
    public boolean movilTieneOferta(String msisdn){
    	return bolsaBlindajeDAO.movilTieneOferta(msisdn);
}
    
    public List<BolsaBean> consultarBolsasActualesBBerryCC(String msisdn)
            throws DAOException, ServiceException {
        return suscripcionesBolsaDAO.consultarBolsasActualesBBerryCC(msisdn);
    }
    
    
    /**
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<BolsaBean> consultarBolsasDisponiblesRegalo(String msisdn,String mercado)
            throws DAOException, ServiceException {
        return suscripcionesBolsaDAO.consultarBolsasDisponiblesRegalo(msisdn,mercado);
    }
    
       

    /**
     * @return the suscripcionesBolsaDAO
     */
    public SuscripcionesBolsaDAO getSuscripcionesBolsaDAO() {
        return suscripcionesBolsaDAO;
    }
    


    /**
     * @param suscripcionesBolsaDAO the suscripcionesBolsaDAO to set
     */
    public void setSuscripcionesBolsaDAO(SuscripcionesBolsaDAO suscripcionesBolsaDAO) {
        this.suscripcionesBolsaDAO = suscripcionesBolsaDAO;
    } 
    
    
    /**
     * Listado de bolsas compradas de un usuario pp BAM
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<BolsaPPBAMBean> consultarBolsasCompradasPPBAM(String msisdn)throws DAOException, ServiceException{
        return this.suscripcionesBolsaDAO.consultarBolsasCompradasPPBAM(msisdn);
    }
    
    
    
    /**
     * Bolsas Actual de un usuario pp BAM
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public SaldoBolsaPPBAMBean consultarSaldoBolsaActualPPBAM(String msisdn)throws DAOException, ServiceException{
        return this.suscripcionesBolsaDAO.consultarSaldoBolsaVigentePPBAM(msisdn);
    }
    
    /**
     * Devuelve el saldo de la bolsa usuario pp y el listado de bolsas dipsonibles BAM
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public SaldoYBolsaDisponiblesCompraBAMBean consultarSaldoYBolsaDisponiblesCompraPPBAM(String msisdn, String fdt)
            throws DAOException, ServiceException {
           return this.suscripcionesBolsaDAO.consultarSaldoYBolsaDisponiblesCompraPPBAM(msisdn,fdt);
    }
    
    /**
     * Devuelve el saldo de la bolsa usuario pp y el listado de bolsas dipsonibles BAM
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ResumenPlanPPBAM consultarResumenPlanPPBAM(String msisdn)
            throws DAOException, ServiceException {
            return this.suscripcionesBolsaDAO.consultarResumenPlanPPBAM(msisdn);
    }
    
    /**
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<BolsaPPBean> obtenerBolsasDisponiblesScob(String msisdn)
            throws DAOException, ServiceException {
        return suscripcionesBolsaDAO.obtenerBolsasDisponiblesScob(msisdn);
    }

    
}
