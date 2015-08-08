/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.delegate;

import java.io.Serializable;

import com.epcs.bean.ListaContratosBean;
import com.epcs.cliente.perfil.dao.ContratosDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author dvelez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ContratosDelegate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ContratosDAO contratosDAO;
	
	public ContratosDelegate(){
		super();
		contratosDAO = new ContratosDAO();
	}

	
	
	/**
	 * @return the contratosDAO
	 */
	public ContratosDAO getContratosDAO() {
		return contratosDAO;
	}



	/**
	 * @param contratosDAO the contratosDAO to set
	 */
	public void setContratosDAO(ContratosDAO contratosDAO) {
		this.contratosDAO = contratosDAO;
	}


    /**
     * Lista de Contratos Firmados electronicamente
     * 
     * @param rut (formateado)
     * @param numeroPagina
     * @param cantidadRegistros (cantidad Registros por pagina)
     * @param codigoFirmados (31,32 Firmados y no Firmados)
     * @return ListaContratosBean
     * @throws DAOException
     * @throws ServiceException
     * @return ListaContratosBean
     */
	public ListaContratosBean obtenerListaContratosFirmados(String rut, int numeroPagina, 
		    int cantidadRegistros, String codigoFirmados) throws DAOException, ServiceException{
		
		return this.contratosDAO.getContratosFirmados(rut, numeroPagina, cantidadRegistros, codigoFirmados);
	}
	
	/**
     * Lista de Contratos Firmados electronicamente con movil
     * 
     * @param rut (formateado)
     * @param numeroPagina
     * @param cantidadRegistros (cantidad Registros por pagina)
     * @param codigoFirmados (31,32 Firmados y no Firmados)
     * @return ListaContratosBean
     * @throws DAOException
     * @throws ServiceException
     * @return ListaContratosBean
     */
	public ListaContratosBean obtenerListaContratosFirmadosConMovil(String rut, String numeroPagina, 
		    String cantidadRegistros, String codigoFirmados, String flagRutTitular, String movil) throws DAOException, ServiceException{
		
		return this.contratosDAO.getContratosFirmadosConMovil(rut, numeroPagina, cantidadRegistros, codigoFirmados, flagRutTitular, movil);
	}
	

}
