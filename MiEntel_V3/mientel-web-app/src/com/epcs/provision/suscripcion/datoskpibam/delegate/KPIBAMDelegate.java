/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.provision.suscripcion.datoskpibam.delegate;

import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.provision.suscripcion.datoskpibam.dao.KIPBAMDAO;
import com.esa.provision.suscripcion.datoskpibam.types.IngresarDatosKpiBAMResponseType;


public class KPIBAMDelegate {
	
	 private KIPBAMDAO kpiBamDAO;
	 
	 public KPIBAMDelegate(){
		 kpiBamDAO = new KIPBAMDAO();
	 }

     /**
     * Ingresar marca KPI
     * 
     * @return String
     * @throws DAOException
     * @throws ServiceException
     */
    public IngresarDatosKpiBAMResponseType insertarKPIBAM(String codArea, String nombreUsuario, String codIndicador, String fecha, String valor)throws DAOException,ServiceException{
    	return this.kpiBamDAO.insertarKPIBAM(codArea,nombreUsuario,codIndicador,fecha, valor);
    }
    
    /**
     * Consultar Origen tipo plan BAM CC
     * 
     * @return boolean
     * @throws DAOException
     * @throws ServiceException
     */
    public String esMovilSAPC(String movil)throws DAOException,ServiceException{
    	return this.kpiBamDAO.esMovilSAPC(movil);
    }

}
