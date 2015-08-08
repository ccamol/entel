/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.clientes.perfilesclientes.ejecutivo.dao;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.epcs.bean.EjecutivoVIPBean;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.esa.clientes.perfilesclientes.sgacarterasvip.WSConsultaEjecutivoPorANIPortType;
import com.esa.clientes.perfilesclientes.sgacarterasvip.WSSGAConsultaEjecutivoService;
import com.esa.clientes.perfilesclientes.sgacarterasvip.types.EntradaConsultaEjecutivo;
import com.esa.clientes.perfilesclientes.sgacarterasvip.types.SalidaConsultaEjecutivoType;

/**
 * @author zmanotas (I2B) 
 */
public class EjecutivoVIPDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(EjecutivoVIPDAO.class);
    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
    .getProperty("servicios.codigoRespuesta.exito");    
	
	/**
	 * 
	 * @param msisdn
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
    public EjecutivoVIPBean obtenerDatosEjecutivo(String msisdn)
			throws DAOException, ServiceException {

    	WSConsultaEjecutivoPorANIPortType port = null;
    	EjecutivoVIPBean datosEjecutivo = new EjecutivoVIPBean();

		LOGGER.info("Instanciando el port " + WSConsultaEjecutivoPorANIPortType.class);
		
		try {
			port = (WSConsultaEjecutivoPorANIPortType) WebServiceLocator
					.getInstance().getPort(WSSGAConsultaEjecutivoService.class,
							WSConsultaEjecutivoPorANIPortType.class);
		} catch (WebServiceLocatorException e) {
			LOGGER.error("Error al inicializar el Port " + WSConsultaEjecutivoPorANIPortType.class, e);
			LOGGER.error( new DAOException(e));
		}

		LOGGER.info("Configurando Datos de la peticion");
		EntradaConsultaEjecutivo request = new EntradaConsultaEjecutivo();

		request.setMovil(msisdn);		

		LOGGER.info("Invocando servicio");
		SalidaConsultaEjecutivoType response = null;
		try {
			response = port.consultaEjecutivo(request);
		} catch (Exception e) {
			LOGGER.error("Exception caught on Service invocation: obtenerDatosEjecutivo", e);
			LOGGER.error( new DAOException(e));
		}

		String codigoRespuesta = response.getCodigo();
		String descripcionRespuesta = response.getMensaje();

		LOGGER.info("codigoRespuesta " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

		if (Utils.isEmptyString(String.valueOf(codigoRespuesta))) {
			LOGGER.error( new DAOException("obtenerDatosEjecutivo: Servicio no respondio con codigoRespuesta"));
		}

		if (codigoRespuesta.equals(CODIGO_RESPUESTA_OK)) {
			try {
				datosEjecutivo = new EjecutivoVIPBean();
				datosEjecutivo.setNombre(response.getNombreEjecutivo());
				datosEjecutivo.setApellidoPaterno(response.getApellidoPaternoEjecutivo());
				datosEjecutivo.setApellidoMaterno(response.getApellidoMaternoEjecutivo());
				datosEjecutivo.setEmail(response.getMailContactoEjecutivo());
				datosEjecutivo.setTelefono("600 3610 000");
			} catch (Exception e) {
				LOGGER.error("Exception caught on Service response: obtenerDatosEjecutivo", e);
				LOGGER.error( new DAOException(e));
			}

		} else {
			LOGGER.info("obtenerDatosEjecutivo: Service error code received: "
				+ codigoRespuesta + " - " + descripcionRespuesta);
			LOGGER.error( new ServiceException(String.valueOf(codigoRespuesta), descripcionRespuesta));
		}

		return datosEjecutivo;
	}	

}
