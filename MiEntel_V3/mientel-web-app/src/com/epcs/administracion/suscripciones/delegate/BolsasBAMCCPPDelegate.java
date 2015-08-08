/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.administracion.suscripciones.delegate;

import com.epcs.administracion.suscripciones.dao.BolsasBAMCCPPDAO;
import com.epcs.bean.ResumenBolsasActivasBAMCCPP;
import com.epcs.bean.ResumenBolsasDisponiblesBAMCCPP;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class BolsasBAMCCPPDelegate {

    private BolsasBAMCCPPDAO bolsasBAMCCPPDAO;

    public ResumenBolsasDisponiblesBAMCCPP listarBolsasDisponibles(String msisdn)
            throws DAOException, ServiceException {
        return bolsasBAMCCPPDAO.listarBolsasDisponibles(msisdn);
    }

    public ResumenBolsasActivasBAMCCPP listarBolsasActivas(String msisdn)
            throws DAOException, ServiceException {
        return bolsasBAMCCPPDAO.listarBolsasActivas(msisdn);
    }

    public void comprarBolsa(String msisdn, String codBolsa, String tipoCobro)
            throws DAOException, ServiceException {
        this.bolsasBAMCCPPDAO.comprarBolsa(msisdn, codBolsa, tipoCobro);
    }

    /**
     * @return the bolsasBAMCCPPDAO
     */
    public BolsasBAMCCPPDAO getBolsasBAMCCPPDAO() {
        return bolsasBAMCCPPDAO;
    }

    /**
     * @param bolsasBAMCCPPDAO
     *            the bolsasBAMCCPPDAO to set
     */
    public void setBolsasBAMCCPPDAO(BolsasBAMCCPPDAO bolsasBAMCCPPDAO) {
        this.bolsasBAMCCPPDAO = bolsasBAMCCPPDAO;
    }
}
