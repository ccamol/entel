/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.balance.delegate;

import com.epcs.bean.FacturacionFullBean;
import com.epcs.bean.ResultadoConsultarDetalleCuenta;
import com.epcs.bean.ResumenFacturacionBean;
import com.epcs.billing.balance.dao.FacturacionDAO;
import com.epcs.dashboard.mensajes.dao.ConsultarDetalleLlamadosDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class FacturacionDelegate {

//    private static final Logger LOGGER = Logger
//            .getLogger(FacturacionDelegate.class);

    private FacturacionDAO facturacionDAO;
    private ConsultarDetalleLlamadosDAO consultarDetalleLlamadosDAO;

    public FacturacionDelegate() {
        facturacionDAO = new FacturacionDAO();
        consultarDetalleLlamadosDAO = new ConsultarDetalleLlamadosDAO();
    }

    /**
     * @return the facturacionDAO
     */
    public FacturacionDAO getFacturacionDAO() {
        return facturacionDAO;
    }

    /**
     * @param facturacionDAO
     *            the facturacionDAO to set
     */
    public void setFacturacionDAO(FacturacionDAO facturacionDAO) {
        this.facturacionDAO = facturacionDAO;
    }
    
    /**
     * @return the detalleLlamadosDAO
     */
    public ConsultarDetalleLlamadosDAO getConsultarDetalleLlamadosDAO() {
        return consultarDetalleLlamadosDAO;
    }

    /**
     * @param detalleLlamadosDAO the detalleLlamadosDAO to set
     */
    public void setConsultarDetalleLlamadosDAO(ConsultarDetalleLlamadosDAO consultarDetalleLlamadosDAO) {
        this.consultarDetalleLlamadosDAO = consultarDetalleLlamadosDAO;
    }

    /**
     * 
     * @param numeroPcs
     * @param rutTitular
     * @param idSistema
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    
    public FacturacionFullBean getFacturacionFull(String numeroPcs, String rutTitular)
    throws DAOException, ServiceException {
        return this.facturacionDAO.getFacturacionFull(numeroPcs, rutTitular);
    }
    
    /**
     * 
     * @param numeroPCS
     * @param folio
     * @param tipo
     * @param periodo
     * @return
     * @throws DAOException
     * @throws ServiceException
     */    
    public String getUrlImagenFactura(String numeroPCS, String folio, String tipo, String periodo)throws DAOException, ServiceException {
        return this.facturacionDAO.getUrlImagenFactura(numeroPCS, folio, tipo, periodo);
    }
    
    public ResumenFacturacionBean getResumenFacturacion(String numeroPcs, String rut, String numeroCuenta, boolean logCajaFacturacion)
            throws DAOException, ServiceException {
        return this.facturacionDAO.getResumenFacturacion(numeroPcs,rut,numeroCuenta,logCajaFacturacion);
    }
    
    /**
     * 
     * @param numeroPcs
     * @param idSistema
     * @return String "PAGADO" o "IMPAGO"
     * @throws DAOException
     * @throws ServiceException
     */
    public String estadoFacturasImpagas(String numeroPcs)
            throws DAOException, ServiceException {
        return this.facturacionDAO.estadoFacturasImpagas(numeroPcs);
    }
    
    public String consultarDetalleLlamados(String tipoConsulta, String idConsulta, String folio, String tipoDocumento, String iteracion) throws DAOException, ServiceException {
        return this.facturacionDAO.consultarDetalleLlamados(tipoConsulta, idConsulta, folio, tipoDocumento, iteracion);
    }
    
    public String consultarDetalleLlamadosFull(String numeroCuenta, String numeroPcs, String aaa, String folio, String tipoDocumento) throws DAOException, ServiceException {
        return this.facturacionDAO.consultarDetalleLlamadosFull(numeroCuenta, numeroPcs, aaa, folio, tipoDocumento);
    }
    
    public String consultarDetalleLlamadosFullMPT(String numeroCuenta, String msisdn, String numeroPcs, String aaa, String folio, String tipoDocumento, String periodo) throws DAOException, ServiceException, Exception {
        this.consultarDetalleLlamadosDAO.insertar(numeroCuenta, msisdn, aaa, folio, tipoDocumento, periodo);
        return this.facturacionDAO.consultarDetalleLlamadosFull(numeroCuenta, numeroPcs, aaa, folio, tipoDocumento);
    }
    
    /**
     * 
     * @param nroCuenta
     * @param nroDocumentoMes    
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    
    public ResultadoConsultarDetalleCuenta getDetalleDocumento(String nroCuenta , String nroDocumentoMes)
    throws DAOException, ServiceException {
        return this.facturacionDAO.getDetalleDocumento(nroCuenta, nroDocumentoMes);
    }  
    
    
    public boolean obtenerBloqueoDetalleLlamado(String msisdn) throws DAOException, ServiceException {
    	return this.facturacionDAO.obtenerBloqueoDetalleLlamado(msisdn);
    }
    
}
