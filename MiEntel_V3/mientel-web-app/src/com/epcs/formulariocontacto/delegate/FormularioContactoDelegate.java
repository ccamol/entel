package com.epcs.formulariocontacto.delegate;

import com.epcs.bean.GenerarTicketBean;
import com.epcs.bean.GenerarTicketPortabilidadBean;
import com.epcs.formulariocontacto.dao.FormularioContactoDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class FormularioContactoDelegate {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private FormularioContactoDAO formularioContactoDAO; 
    
    public FormularioContactoDelegate(){
    	formularioContactoDAO = new FormularioContactoDAO();
    }

    public void generarTicketSGA(GenerarTicketBean ticket) throws ServiceException,
    DAOException {
    	formularioContactoDAO.generarTicketSGA(ticket);
    }
    
    public void generarTicketPortabilidadSGA(GenerarTicketPortabilidadBean ticket) throws ServiceException,
    DAOException {
    	formularioContactoDAO.generarTicketPortabilidadSGA(ticket);
    }    

	public FormularioContactoDAO getFormularioContactoDAO() {
		return formularioContactoDAO;
	}

	public void setFormularioContactoDAO(FormularioContactoDAO formularioContactoDAO) {
		this.formularioContactoDAO = formularioContactoDAO;
	}
}