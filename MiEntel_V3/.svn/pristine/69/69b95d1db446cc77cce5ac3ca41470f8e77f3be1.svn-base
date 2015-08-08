/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.delegate;

import com.epcs.bean.BloqueoEquipoBean;
import com.epcs.bean.ContactoPorRenovacionBean;
import com.epcs.bean.DatosUsuarioBloqueoBean;
import com.epcs.bean.DocumentoAperturaOTBean;
import com.epcs.bean.InformeTecnicoOTBean;
import com.epcs.bean.OrdenTrabajoVigenteBean;
import com.epcs.bean.PinPukBean;
import com.epcs.bean.ResumenEquipoBean;
import com.epcs.bean.SituacionActualEquipoBean;
import com.epcs.cliente.perfil.dao.EquipoDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class EquipoBAMDelegate {
	

//    private static final Logger LOGGER = Logger.getLogger(EquipoDelegate.class);

    private EquipoDAO equipoDAO;

    /**
     * @return the equipoDAO
     */
    public EquipoDAO getEquipoDAO() {
        return equipoDAO;
    }

    /**
     * @param equipoDAO
     *            the equipoDAO to set
     */
    public void setEquipoDAO(EquipoDAO equipoDAO) {
        this.equipoDAO = equipoDAO;
    }

    /**
     * 
     * @return bean con los datos del equipo del usuario en Sesion
     */
    public ResumenEquipoBean getResumenEquipo(String numeroPcs)
            throws DAOException, ServiceException {
        return this.equipoDAO.getResumenEquipo(numeroPcs);
    }
    
    /**
     * 
     * @return bean con los datos del situacion actual equipo
     */
    public SituacionActualEquipoBean obtenerSituacionActualEquipo(
    		String numeroPCS, String rut, String nroCuenta,
    		String rutSinDV, String DV
    		)throws DAOException, ServiceException {
    	return this.equipoDAO.obtenerSituacionActualEquipo(numeroPCS, rut, nroCuenta, rutSinDV, DV);    	
    }
    
    /**
     * 
     * @return bean con los datos del PIN-PUK
     */
    public PinPukBean consultarPinPuk(String numeroPCS)throws Exception{
    	return this.equipoDAO.consultarPinPuk(numeroPCS);
    }

    /**
     * 
     * Delegate contacto por renovacion
     */
    public void contactoPorRenovacion(ContactoPorRenovacionBean contacto)throws DAOException, ServiceException{
    	equipoDAO.contactoPorRenovacion(contacto);
    }
    
    /**
     * 
     * Delegate contacto por renovacion
     * @return DatosUsuarioBloqueoBean
     */
    public DatosUsuarioBloqueoBean obtenerDatosUsuarioBloqueo(String rutSinDV, String DV)
    throws DAOException, ServiceException {
    	return equipoDAO.obtenerDatosUsuarioBloqueo(rutSinDV, DV);
    }
    
    /**
     * 
     * Actualizar datos de bloueo de usuario
     */
    public void actualizarDatosUsuarioBloqueo(
    		DatosUsuarioBloqueoBean datosUsuarioBloqueo, String rutSinDV, 
    		String DV, String numeroPCS)
    throws DAOException, ServiceException {
    	equipoDAO.actualizarDatosUsuarioBloqueo(datosUsuarioBloqueo, rutSinDV, DV, numeroPCS);
    }
    
    /**
     * 
     * Delegado Bloqueo por extravio
     * 
     */
    public void bloquearEquipoPorExtravio(BloqueoEquipoBean bloq)throws DAOException, ServiceException {
    	equipoDAO.bloquearEquipoPorExtravio(bloq);
    }
    
    /**
     * 
     * Delegado Bloqueo por robo
     * 
     */
    public void bloquearEquipoPorRobo(BloqueoEquipoBean bloq)throws DAOException, ServiceException {
    	equipoDAO.bloquearEquipoPorRobo(bloq);
    }
    
    /**
     * 
     * Delegado bloqueo existente
     * @return boolean
     */
    public boolean tieneBloqueoExistente(String numeroPCS,String mercado,String DVImei)throws DAOException, ServiceException {
    	return equipoDAO.tieneBloqueoExistente(numeroPCS,mercado, DVImei);
    }
    
    /**
     * 
     * Delegado Ordenes vigentes
     * @return OrdenTrabajoVigenteBean
     */
    public OrdenTrabajoVigenteBean obtenerOrdenesDeTrabajoVigente(String numeroPCS)throws DAOException, ServiceException {
    	return equipoDAO.obtenerOrdenesDeTrabajoVigente(numeroPCS);
    }
    
    /**
     * 
     * Delegado Imei
     * @return String
     */
    public String obtenerImei(String numeroPCS)throws DAOException, ServiceException {
    	return equipoDAO.obtenerImei(numeroPCS);
    }
    
    /**
     * 
     * Delegado aceptar presupuesto
     */
    public void aceptarRechazarPresupuesto(String nroOrden, String accion)
    throws DAOException, ServiceException{
    	equipoDAO.aceptarRechazarPresupuesto(nroOrden, accion);
    }
    
    /**
     * 
     * Delegado obtener apertura de documento de OT
     */
    public DocumentoAperturaOTBean obtenerAperturaDocumentoOT(String nroOrden)throws DAOException, ServiceException{
    	return equipoDAO.obtenerAperturaDocumentoOT(nroOrden);
    }
    
    /**
     * 
     * Delegado obtener informe tecnico
     */
    public InformeTecnicoOTBean obtenerInformeTecnicoOT(String nroOrden)throws DAOException, ServiceException{
    	return equipoDAO.obtenerInformeTecnicoOT(nroOrden);
    }
    
}
