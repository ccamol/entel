/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.vtasymktg.fidelizacion.delegate;

import org.apache.log4j.Logger;

import com.epcs.bean.ZonaPerfilBean;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.vtasymktg.fidelizacion.dao.BeneficiosDAO;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class BeneficiosDelegate {

    private static final Logger LOGGER = Logger
            .getLogger(BeneficiosDelegate.class);

    private BeneficiosDAO beneficiosDAO;   
    
    /**
     * 
     * @param numeroPcs
     * @param rut
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ZonaPerfilBean getZonaPerfil(String numeroPcs, String rut) throws DAOException, ServiceException {
    	return beneficiosDAO.getZonaPerfil(numeroPcs, rut);
    }
    
    /**
     * 
     * @param msisdnSponsor
     * @param msisdnRecibe
     * @param rut
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ZonaPerfilBean getZonaPerfilRegalo(String msisdnSponsor, String msisdnRecibe, String rut) throws DAOException, ServiceException {
    	return beneficiosDAO.getZonaPerfilRegalo(msisdnSponsor, msisdnRecibe, rut);
    }

	/**
	 * @return the beneficiosDAO
	 */
	public BeneficiosDAO getBeneficiosDAO() {
		return beneficiosDAO;
	}

	/**
	 * @param beneficiosDAO the beneficiosDAO to set
	 */
	public void setBeneficiosDAO(BeneficiosDAO beneficiosDAO) {
		this.beneficiosDAO = beneficiosDAO;
	}
    
}
