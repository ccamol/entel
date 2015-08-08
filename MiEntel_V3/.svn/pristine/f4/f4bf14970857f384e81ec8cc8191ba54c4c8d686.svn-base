/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.prodfactura.delegate;

import com.epcs.bean.FacturacionElectronicaBean;
import com.epcs.billing.prodfactura.dao.FacturacionElectronicaDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class FacturacionElectronicaDelegate {

//    private static final Logger LOGGER = Logger
//    .getLogger(FacturacionElectronicaDelegate.class);
    
    private FacturacionElectronicaDAO facturacionElectronicaDAO;

    /**
     * @return the facturacionElectronicaDAO
     */
    public FacturacionElectronicaDAO getFacturacionElectronicaDAO() {
        return facturacionElectronicaDAO;
    }

    /**
     * @param facturacionElectronicaDAO the facturacionElectronicaDAO to set
     */
    public void setFacturacionElectronicaDAO(
            FacturacionElectronicaDAO facturacionElectronicaDAO) {
        this.facturacionElectronicaDAO = facturacionElectronicaDAO;
    }
    
    /**
     * 
     * @param rut
     * @param nroCuenta
     * @return
     * @throws DAOException
     * @throws ServiceException
     */    
    public FacturacionElectronicaBean getFacturacionElectronica(String rut, String nroCuenta)throws DAOException, ServiceException {
        return facturacionElectronicaDAO.getFacturacionElectronica(rut, nroCuenta);
    }
    
    /**
     * 
     * @param rut
     * @param nroCuenta
     * @param email
     * @param msisdn
     * @throws DAOException
     * @throws ServiceException
     */
    public void inscribirServicioFacturacionElectronica(
            String rut, String nroCuenta, 
            String email, String msisdn
            )throws DAOException, ServiceException {
        facturacionElectronicaDAO.inscribirServicioFacturacionElectronica(rut, nroCuenta, email, msisdn);
    }
    
    /**
     * 
     * @param rut
     * @param nroCuenta
     * @param email
     * @param msisdn
     * @throws DAOException
     * @throws ServiceException
     */
    public void actualizarServicioFacturacionElectronica(
            String rut, String nroCuenta, 
            String email, String msisdn
            )throws DAOException, ServiceException {
        facturacionElectronicaDAO.actualizarServicioFacturacionElectronica(rut, nroCuenta, email, msisdn);
    }
    
    /**
     * 
     * @param idSistema
     * @param rut
     * @param nroCuenta
     * @param codigoMotivo
     * @throws DAOException
     * @throws ServiceException
     */
    public void cancelarServicioFacturacionElectronica(
            String rut, String nroCuenta, String codigoMotivo
            )throws DAOException, ServiceException{
        facturacionElectronicaDAO.cancelarServicioFacturacionElectronica(rut, nroCuenta, codigoMotivo);
    }
    /**
     * 
     * @param rutTitular
     * @param nroCuenta
     * @return boolean
     * @throws DAOException 
     */
	public boolean getFacturacionElectronicaEstado(String rutTitular,
			String nroCuenta) throws DAOException {
		return facturacionElectronicaDAO.getFacturacionElectronicaEstado(rutTitular,nroCuenta);
	}
    
}
