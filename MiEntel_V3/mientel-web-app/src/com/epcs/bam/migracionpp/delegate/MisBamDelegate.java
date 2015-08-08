/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bam.migracionpp.delegate;

import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.bam.migracionpp.dao.MisbamDAO;


public class MisBamDelegate {
	
	 private MisbamDAO misbamDAO;
	 
	 public MisBamDelegate(){
		 misbamDAO = new MisbamDAO();
	 }

	 /**
     * Consultar base de clientes MIS BAM
     * @param rut
     * @param movil
     * @return plan oferta
     * @throws DAOException
     * @throws ServiceException
     */
    public String getMisBAM(String rut, String movil)throws DAOException,ServiceException{
    	return this.misbamDAO.getMisBAM(rut, movil);
    }
    
    
    public Integer getMisBAMSolicitud(String movil)throws DAOException,ServiceException{
    	return this.misbamDAO.getMisBAMSolicitud(movil);
    }
}
