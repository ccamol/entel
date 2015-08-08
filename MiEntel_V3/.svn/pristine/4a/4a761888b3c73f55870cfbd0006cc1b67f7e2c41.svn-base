/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.delegate;


import java.util.List;

import com.epcs.bean.HistorialComunik2Bean;
import com.epcs.bean.Plan4GLteBean;
import com.epcs.bean.PlanBean;
import com.epcs.bean.PlanDisponibleBean;
import com.epcs.bean.PlanPPBean;
import com.epcs.bean.PlanesFullBean;
import com.epcs.bean.PlanesMultimediaBean;
import com.epcs.bean.ResultadoConsultarPuntosBean;
import com.epcs.bean.ResumenPlan;
import com.epcs.bean.SolicitudComunik2Bean;
import com.epcs.cliente.perfil.dao.PlanDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class PlanDelegate {


    //private static final Logger LOGGER = Logger.getLogger(PlanDelegate.class);
    private PlanDAO planDAO;
    
    /**
     * Injection del dao
     * @param planDAO
     */
    public void setPlanDAO(PlanDAO planDAO){
        this.planDAO = planDAO;
    } 
    
    /**
     * Resumen del Plan del Usuario CC
     * 
     * @param cta
     * @param atributoAA
     * @return ResumenPlan
     * @throws DAOException
     * @throws ServiceException
     */
    public ResumenPlan getPlanResumenCC(String cta, String atributoAA)
    throws DAOException, ServiceException {
        return this.planDAO.getPlanResumenCC(cta, atributoAA); 
    }
    
    /**
     * Obtiene Resumen del Plan de un Uusario PP
     * @param cta
     * @return ResumenPlan
     * @throws DAOException
     * @throws ServiceException
     */
    public ResumenPlan getPlanResumenPP(String cta)
            throws DAOException, ServiceException {
        return this.planDAO.getPlanResumenPP(cta);
    }
    
    /**
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public PlanPPBean getPlanActualPP(String numeroPcsSeleccionado)
    throws DAOException, ServiceException {
        return this.planDAO.obtenerPlanActualPP(numeroPcsSeleccionado);
    }
    
    
    /**
     * 
     * @param numeroPcsSeleccionado
     * @param mercado
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public PlanDisponibleBean getPlanesDisponibles(String numeroPcsSeleccionado)
    throws DAOException, ServiceException {
        return this.planDAO.obtenerPlanesDisponibles(numeroPcsSeleccionado);
    }
    
    /**
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public PlanDisponibleBean getPlanesDisponiblesPP(String numeroPcsSeleccionado)
    throws DAOException, ServiceException {
        return this.planDAO.obtenerPlanesDisponiblesPP(numeroPcsSeleccionado);
    }
    

    /**
     * 
     * @param numeroPcsSeleccionado
     * @param codigoNuevoPlan
     * @throws DAOException
     * @throws ServiceException
     */
    public void cambiarPlanSSCC(String numeroPcsSeleccionado, String codigoNuevoPlan)
    throws DAOException, ServiceException {
        this.planDAO.cambiarPlanSSCC(numeroPcsSeleccionado, codigoNuevoPlan);
    }
    

    /**
     * 
     * @param numeroPcsSeleccionado
     * @param codigoNuevoPlan
     * @throws DAOException
     * @throws ServiceException
     */
    public void cambiarPlanPP(String numeroPcsSeleccionado, String codigoNuevoPlan)
    throws DAOException, ServiceException {
        this.planDAO.cambiarPlanPP(numeroPcsSeleccionado, codigoNuevoPlan);
    }
    
    
    /**
     * 
     * @param numeroPcsSeleccionado
     * @param numeroRecibeSolicitud
     * @throws DAOException
     * @throws ServiceException
     */
    public void cambiarPlanComunik2PP(String numeroPcsSeleccionado, String numeroRecibeSolicitud)
    throws DAOException, ServiceException {
        this.planDAO.cambiarPlanComunik2PP(numeroPcsSeleccionado, numeroRecibeSolicitud);
    }
    
    /**
     * 
     * @param numeroPcsSeleccionado
     * @param idSlot
     * @param numeroRecibeSolicitud
     * @throws DAOException
     * @throws ServiceException
     */
    public void agregarmodificarNumeroFrecuentePP(String numeroPcsSeleccionado, String idSlot, String numeroRecibeSolicitud, int codAccion)
    throws DAOException, ServiceException {
        this.planDAO.administrarNumeroFrecuentePP(numeroPcsSeleccionado, idSlot, numeroRecibeSolicitud,codAccion);
    }

    /**
     * 
     * @param numeroPcsSeleccionado
     * @param idSlot
     * @throws DAOException
     * @throws ServiceException
     */
    public void eliminarNumeroFrecuentePP(String numeroPcsSeleccionado, String idSlot)
    throws DAOException, ServiceException {
        this.planDAO.eliminarNumeroFrecuentePP(numeroPcsSeleccionado, idSlot);
    }
    

    /**
     * 
     * @param numeroPcsSeleccionado
     * @param mercado
     * @param atributoAA
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public PlanBean getPlanActualSSCC(String numeroPcsSeleccionado, String mercado, String atributoAA)
    throws DAOException, ServiceException {
        return this.planDAO.obtenerPlanActualSSCC(numeroPcsSeleccionado, mercado, atributoAA);
    }
    
    /**
     * 
     * @param msisdn
     * @throws DAOException
     * @throws ServiceException
     */
    public void validacionBloqueoTemporal(String msisdn)
    throws DAOException, ServiceException{
    	this.planDAO.validacionBloqueoTemporal(msisdn);
    }
    
  
    /**
     * 
     * @param msisdn
     * @param codbscs2
     * @throws DAOException
     * @throws ServiceException
     */
    public void validacionComercial(String msisdn, String codbscs2)
    throws DAOException, ServiceException{
    	this.planDAO.validacionComercial(msisdn, codbscs2);
    }
    
    
    /**
     * 
     * @param msisdn    
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<HistorialComunik2Bean> obtenerHistorialComunik2(String msisdn) 
      throws DAOException, ServiceException
      {
    	return this.planDAO.obtenerHistorialComunik2(msisdn);
      }
    
    
    /**
     * 
     * @param msisdn    
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public SolicitudComunik2Bean obtenerSolicitudComunik2(String msisdn) 
      throws DAOException, ServiceException
      {
    	return this.planDAO.obtenerSolicitudComunik2(msisdn);
      }
    
    /**
     * 
     * @param msisdn    
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public void administrarSolicitudComunik2(String msisdn, String accion) 
      throws DAOException, ServiceException
      {
    	 this.planDAO.administrarSolicitudComunik2(msisdn , accion);
      }


	 /**
    * Metodo que retorna la informacion full de un plan 
    * @author Wilson Brochero Munoz
    * @param  codbscs2
    * @param  tipoPlan
    * @return PlanesFullBean
    * @throws DAOException
    * @throws ServiceException     
    * */   
    public PlanesFullBean consultaXmlPlanesFull(String codbscs2, String  tipoPlan) 
      throws DAOException, ServiceException
      {
    	 return this.planDAO.consultaXmlPlanesFull(codbscs2,tipoPlan);
      }
    
    /**
     * Meto que retorna informacion de planes excedidos dado un numero movil
     * @author Wilson Brochero Munoz
     * @param  codbscs2
     * @return boolean
     * @throws DAOException
     * @throws ServiceException     
     * */   
	public PlanesMultimediaBean consultarPlanMultimediaExcedido(String movil)
            throws DAOException, ServiceException {
		 return this.planDAO.consultarPlanMultimediaExcedido(movil);
	}	 
    

	/**
	 * Metodo que retorna la informacion de la disponibilidad de un plan 4G LTE
	 * a partir del ingreso del codigo del Plan.
	 * 
	 * @author Anthony David Cajamarca Acuna
	 * @param codigoPlan
	 * @return Plan4GLteBean
	 * @throws DAOException
	 * @throws ServiceException
	 * */

	public Plan4GLteBean consultarDisponibilidad4GLte(String codigoPlan)
			throws DAOException, ServiceException {
		return this.planDAO.consultarDisponibilidadPlan4GLte(codigoPlan);
	}
	
	//SC 31-07-2014 Luis || Categoría de Cliente Dashboard alineada con ZonaMisPuntos
	/**
     * Obtiene Informacion de los puntos de un usuario
     * @param infoPuntos {@link ResultadoConsultarPuntosBean} con la informacion de puntos del usuario
     * @param msisdn numero movil del usuario. Se usara para consultar la categoria de usuario
     * @return {@link ResultadoConsultarPuntosBean} <code>infoPuntos</code> actualizado con la categoria
     * @throws ServiceException
     * @throws DAOException
     */
    public ResultadoConsultarPuntosBean consultarCategoriaCliente (
    		ResultadoConsultarPuntosBean infoPuntos, String msisdn) throws DAOException, ServiceException {
    	infoPuntos.setCategoriaCliente(planDAO.consultarCategoriaCliente(msisdn));
        return infoPuntos;
    }
    
    //FIN SC 31-07-2014 Luis
	
}
