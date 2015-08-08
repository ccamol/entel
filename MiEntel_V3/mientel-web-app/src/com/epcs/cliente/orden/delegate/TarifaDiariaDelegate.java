/**
 * 
 */
package com.epcs.cliente.orden.delegate;

import com.epcs.bean.ResumenPlanPPBAM;
import com.epcs.bean.TarifaDiaria;
import com.epcs.cliente.orden.dao.TarifaDiariaDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;


/**
 * @author Wilson
 *
 */
public class TarifaDiariaDelegate {
	
	
	private TarifaDiariaDAO tarifaDiariaDAO;
   
    
    public TarifaDiariaDelegate(){
    	
    	tarifaDiariaDAO = new TarifaDiariaDAO();
    }


	/**
	 * @return the tarifaDiariaDAO
	 */
	public TarifaDiariaDAO getTarifaDiariaDAO() {
		return tarifaDiariaDAO;
	}


	/**
	 * @param tarifaDiariaDAO the tarifaDiariaDAO to set
	 */
	public void setTarifaDiariaDAO(TarifaDiariaDAO tarifaDiariaDAO) {
		this.tarifaDiariaDAO = tarifaDiariaDAO;
	}
    
    
    /**
     * Devuelve si fue aceptada/rechazada/o sin acciones.
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public TarifaDiaria consultarEstadoTarifaDiaria(String msisdn)
            throws DAOException, ServiceException {
            return this.tarifaDiariaDAO.consultarEstadoTarifaDiaria(msisdn);
    }
    
    
    /**
     * Registrar aceptar/rechazar/ Tarifa Diaria
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public boolean registrarAceptarRechazarTarifaDiaria(TarifaDiaria tarifaDiaria)
            throws DAOException, ServiceException {
            return this.tarifaDiariaDAO.registrarAceptarRechazarTarifaDiaria(tarifaDiaria);
    }
    
    
    /**
     * Registrar Visita tarifa diaria.
     * 
     * @param msisdn
     * @param mercado
     * @param fechaActual
     * @return
     * @throws DAOException    
     * @throws Exception
     */
    public void marcarVisita(String msisdn, String mercado,String fechaActual)
            throws DAOException {
            this.tarifaDiariaDAO.marcarVisita(msisdn, mercado, fechaActual);
    }


}
