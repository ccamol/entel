/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bam.saldoReservado.delegate;

import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.bam.saldoReservado.dao.SaldoReservadoDAO;
import cl.tecnova.entel.homebampp.saldoreservado.SaldoCongeladoService.types.*;


public class SaldoReservadoDelegate {
	
	 private SaldoReservadoDAO saldoReservadoDAO;
	 
	 public SaldoReservadoDelegate(){
		 saldoReservadoDAO = new SaldoReservadoDAO();
	 }

	 /**
     * Obtener saldo reservadoPP
     * 
     * @return String
     * @throws DAOException
     * @throws ServiceException
     */    

    public SaldoCongeladoResponseDocumentType getSaldoReservado(String msisdn)throws DAOException,ServiceException{
    	return this.saldoReservadoDAO.getSaldoReservado(msisdn);
    }
  
}
