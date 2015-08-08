/**
 * 
 */
package com.epcs.cliente.orden.dao;
import java.io.Serializable;

import org.apache.log4j.Logger;


import com.epcs.bean.TarifaDiaria;
import com.epcs.cliente.perfil.dao.OfertaBlindajeEntelDAO;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.esa.cliente.orden.tarifadiaria.AceptacionRechazoRegistro;
import com.esa.cliente.orden.tarifadiaria.AceptacionRechazoResponse;
import com.esa.cliente.orden.tarifadiaria.ComunicacionTarifaDiariaImpl;
import com.esa.cliente.orden.tarifadiaria.RespuestaServicio;
import com.esa.cliente.orden.tarifadiaria.TarifaDiariaService;


/**
 * @author Wilson
 *
 */
public class TarifaDiariaDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(TarifaDiariaDAO.class);

    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties.getProperty("parametros.tarifaDiaria.exitoTarifaDiaria");
    public static final String CODIGO_RESPUESTA_ACEPTADA = MiEntelProperties.getProperty("parametros.tarifaDiaria.aceptada");
    public static final String CODIGO_RESPUESTA_RECHAZADA= MiEntelProperties.getProperty("parametros.tarifaDiaria.rechazada");
    
    
    private TarifaDiariaAccesDAO tarifaDiariaAccesDAO;

	public TarifaDiariaDAO() {
		tarifaDiariaAccesDAO = new TarifaDiariaAccesDAO();
	}
    
    
    /**
     * Consultar Estado Tarifa Diaria
     * 
     * @param msisdn
     * @return boolean.
     * @throws DAOException
     * @throws ServiceException
     */
    public TarifaDiaria consultarEstadoTarifaDiaria(final String msisdn)
            throws DAOException, ServiceException {    	
    	TarifaDiaria tarifaDiaria = null;
        ComunicacionTarifaDiariaImpl port = null;        

        LOGGER.info("Instanciando el port");
        try {
            port = (ComunicacionTarifaDiariaImpl) WebServiceLocator.getInstance()
                    .getPort(TarifaDiariaService.class,
                    		ComunicacionTarifaDiariaImpl.class);
           
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ComunicacionTarifaDiariaImpl.class, e);
            LOGGER.error( new DAOException(e));
        }

        LOGGER.info("Configurando Datos de la peticion");    
        AceptacionRechazoResponse response = null;

        LOGGER.info("Invocando servicio");
        try {
        	  
        	response = port.consultaTarifaDiariaService(msisdn);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuestaServicio().getCodigoRespuesta()+""; 
        String descripcionRespuesta = response.getRespuestaServicio().getDescripcionRespuesta();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
        	tarifaDiaria = new TarifaDiaria();        	
        	tarifaDiaria.setEstadoRespuesta(response.getAceptacionRechazo().getEstadoRespuesta());
        	tarifaDiaria.setFechaRechazoAceptacion(response.getAceptacionRechazo().getFechaAceptacionRechazo());        
        }        

        return tarifaDiaria;
    }   
    
    
    /**
     * Consultar Estado Tarifa Diaria
     * 
     * @param msisdn
     * @return boolean.
     * @throws DAOException
     * @throws ServiceException
     */
    public boolean registrarAceptarRechazarTarifaDiaria(TarifaDiaria tarifaDiaria)
            throws DAOException, ServiceException {

    	boolean sw = false; 
        ComunicacionTarifaDiariaImpl port = null;        

        LOGGER.info("Instanciando el port");
        try {
            port = (ComunicacionTarifaDiariaImpl) WebServiceLocator.getInstance()
                    .getPort(TarifaDiariaService.class,
                    		ComunicacionTarifaDiariaImpl.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ComunicacionTarifaDiariaImpl.class, e);
            LOGGER.error( new DAOException(e));
        }

        LOGGER.info("Configurando Datos de la peticion");
        AceptacionRechazoRegistro request = new AceptacionRechazoRegistro();
        RespuestaServicio response = null;

        LOGGER.info("Invocando servicio");
        try {        	
        	request.setCanalAceptacion(tarifaDiaria.getCanalAceptacion());
        	request.setCuenta(tarifaDiaria.getCuenta());
        	request.setEstadoRespuesta(tarifaDiaria.getEstadoRespuesta());
        	request.setFechaAceptacionRechazo(tarifaDiaria.getFechaRechazoAceptacion());
        	request.setNumeroMovil(tarifaDiaria.getNumeroMovil());
        	request.setPlataforma(tarifaDiaria.getPlataforma());
        	request.setRutTitular(tarifaDiaria.getRutTitular());
        	request.setProductoNavegacionMovil(tarifaDiaria.getProducto());
        	request.setRutUsuario(tarifaDiaria.getRutUsuario());
        	request.setUsuario(tarifaDiaria.getUsuario());
        	request.setGrupoCliente(tarifaDiaria.getGrupoUsuario());
        	response = port.aceptaTarifaDiariaService(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getCodigoRespuesta()+""; 
        String descripcionRespuesta = response.getDescripcionRespuesta();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {          
        	   sw=true;         
        }
        else {

            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));

        }

        return sw;
    }   
    
    
    
    
    /**
     * Registrar Visita tarifa diaria.
     * 
     * @param msisdn
     * @param mercado
     * @param fechaActual
     * @return
     * @throws DAOException      * 
     */
    public void marcarVisita(String msisdn, String mercado,String fechaActual) throws DAOException{ 
    	
    	 try {        	
    		 tarifaDiariaAccesDAO.marcarVisita(msisdn, mercado, fechaActual);

         } catch (Exception e) {
             LOGGER.error("Exception registrando visita tarifa diaria", e);
             LOGGER.error( new DAOException(e));
         }
    	
    }
    

}
