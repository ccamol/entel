/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.ws.recarga.dao.delegate;

import org.apache.log4j.Logger;

import com.esa.ponline.portalbolsas.bean.FactibilidadRecargaWebPayBean;
import com.esa.ponline.portalbolsas.bean.RecargaWebPayBean;
import com.esa.ponline.portalbolsas.exceptions.dao.DAOException;
import com.esa.ponline.portalbolsas.exceptions.ws.ServiceException;
import com.esa.ponline.portalbolsas.sec.dao.SeguridadDAO;
import com.esa.ponline.portalbolsas.ws.recarga.dao.RecargaDAO;


/**
 * Delegado de negocio para Recargas
 * 
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class RecargaDelegate {
    
    private static final Logger LOGGER = Logger.getLogger(RecargaDelegate.class);

    private RecargaDAO recargaDAO;

    private SeguridadDAO seguridadDAO;

    
    /**
     * Default constructor
     */
    public RecargaDelegate() {
        super();
        this.recargaDAO = new RecargaDAO();
        this.seguridadDAO = new SeguridadDAO();
    }
    
    
    /**
     * @param numeroPcs
     * @param monto
     * @param idp
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.billing.recarga.dao.RecargaDAO#ingresarRecargaWebPay(java.lang.String,
     *      java.lang.Double, java.lang.String)
     */
    public RecargaWebPayBean ingresarRecargaWebPay(String numeroPcs,
            Double monto, String codPromo) throws DAOException, ServiceException {
        
        String idp = seguridadDAO.consultarIDPAmpliacion(numeroPcs);
        return recargaDAO.ingresarRecargaWebPay(numeroPcs, monto, idp + codPromo);
    }
    
    /**
     * Evalua la factibilidad de recarga Webpay
     * 
     * @param recarga
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.billing.recarga.dao.RecargaDAO#factibilidadRecargaWebPay(RecargaWebPayBean)
     */
    public FactibilidadRecargaWebPayBean factibilidadRecargaWebPay(
            RecargaWebPayBean recarga, boolean eligeTuPromo, String codPromoF) throws DAOException, ServiceException {
        return recargaDAO.factibilidadRecargaWebPay(recarga, eligeTuPromo, codPromoF);
    }

    
    
    /**
     * @param ordenCompra
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.billing.recarga.dao.RecargaDAO#consultarRecargaWebPayBean(java.lang.String)
     */
    public RecargaWebPayBean consultarRecargaWebPayBean(
            String ordenCompra) throws DAOException, ServiceException {
        LOGGER.info("RecargaDelegate para consultarRecargaWebPayBean");
        return recargaDAO.consultarRecargaWebPayBean(ordenCompra);
    }

    /**
     * Actualiza la recarga con la respuesta de Transbank
     * 
     * @param ordenCompra
     * @param parametrosWebPay
     * @throws DAOException
     * @throws ServiceException
     * @see RecargaDAO#actualizarRecargaWebPay(String,
     *      ParametrosWebPay)
     */
    public void actualizarRecargaWebPay(String ordenCompra,
            String parametrosWebPay) throws DAOException,
            ServiceException {
        recargaDAO.actualizarRecargaWebPay(ordenCompra, parametrosWebPay);
    }

    /**
     * Hace efectiva la recarga webpay
     * 
     * @param recarga
     *            recarga a hacer efectiva
     * @return {@link RecargaWebPayBean} con recarga efectuada
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.billing.recarga.dao.RecargaDAO#efectuarRecargaWebPay(RecargaWebPayBean)
     */
    public RecargaWebPayBean efectuarRecargaWebPay(RecargaWebPayBean recarga, boolean eligeTuPromo)
            throws DAOException, ServiceException {
        return recargaDAO.efectuarRecargaWebPay(recarga, eligeTuPromo);
    }
 
}
