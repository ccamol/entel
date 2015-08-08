package com.epcs.formulariocontacto.dao;

import org.apache.log4j.Logger;

import com.epcs.bean.GenerarTicketBean;
import com.epcs.bean.GenerarTicketPortabilidadBean;
import com.epcs.cliente.perfil.ClientePerfilService;
import com.epcs.cliente.perfil.ClientePerfilServicePortType;
import com.epcs.cliente.perfil.types.GenerarTicketType;
import com.epcs.cliente.perfil.types.ResultadoGenerarTicketType;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 *
 */
public class FormularioContactoDAO {

	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(FormularioContactoDAO.class);

    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");


    /**
     * Genera un ticket desde el formulario de contacto
     *
     * @param ticket
     *            {@link GenerarTicketBean}
     *
     * @throws ServiceException
     * @throws DAOException
     */
    public void generarTicketSGA(GenerarTicketBean ticket) throws ServiceException,
            DAOException {

    	ClientePerfilServicePortType port = null;
        LOGGER.info("Instanciando el Port.");
        try {
            port = (ClientePerfilServicePortType) WebServiceLocator
                    .getInstance().getPort(ClientePerfilService.class,
                    		ClientePerfilServicePortType.class);

        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ClientePerfilServicePortType.class);
            LOGGER.error( new DAOException(e));
        }

        LOGGER.info("Configurando Datos de la peticion");

        GenerarTicketType request = new GenerarTicketType();
        request.setApellidos(ticket.getPrimerApellido()+" "+ticket.getSegundoApellido());
        request.setMail(ticket.getMail());
        request.setMensaje(ticket.getMensaje());
        request.setMotivo(ticket.getMotivo());
        request.setMsisdn(ticket.getNumeroPCS());
        request.setNombres(ticket.getPrimerNombre()+" "+ticket.getSegundoNombre());
        request.setRut(ticket.getRut());
        request.setTelefono(ticket.getIndicativoTelefono()+ticket.getTelefono());
        request.setTipo(ticket.getTipo());

        ResultadoGenerarTicketType response = null;

        try {
        	response = port.generarTicket(request);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
            		+ "generarTicket", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();
        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (!CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
        	LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }


    }

	public void generarTicketPortabilidadSGA(GenerarTicketPortabilidadBean ticket)
			throws ServiceException, DAOException {

		ClientePerfilServicePortType port = null;
		LOGGER.info("Instanciando el Port.");
		try {
			port = (ClientePerfilServicePortType) WebServiceLocator
					.getInstance().getPort(ClientePerfilService.class,
							ClientePerfilServicePortType.class);

		} catch (WebServiceLocatorException e) {
			LOGGER.error("Error al inicializar el Port "
					+ ClientePerfilServicePortType.class);
			LOGGER.error( new DAOException(e));
		}

		LOGGER.info("Configurando Datos de la peticion");

		GenerarTicketType request = new GenerarTicketType();
		request.setApellidos(ticket.getPrimerApellido() + " " + ticket.getSegundoApellido());
		request.setMail(ticket.getMail());

		String mensaje = "";

		if (ticket.getModeloEquipo() == null || ticket.getModeloEquipo().trim().isEmpty()) {
			mensaje = ticket.getMensaje() + ";" + ticket.getMarcaEquipo();
		} else {
			mensaje = ticket.getMensaje() + ";" + ticket.getMarcaEquipo() + ";" + ticket.getModeloEquipo();
		}

		request.setMensaje(mensaje);
		request.setMotivo("Portabilidad");
		request.setMsisdn(ticket.getNumeroPCS());
		request.setNombres(ticket.getPrimerNombre() + " " + ticket.getSegundoNombre());
		request.setRut(ticket.getRut());
		//RGUZMAN
		request.setTelefono(ticket.getIndicativoTelefono() + ticket.getTelefono());
		request.setTipo("");

		ResultadoGenerarTicketType response = null;

		try {
			response = port.generarTicket(request);
		} catch (Exception e) {
			LOGGER.error("Exception caught on Service invocation", e);
			LOGGER.error( new DAOException(e));
		}

		String codigoRespuesta = response.getRespuesta().getCodigo();
		String descripcionRespuesta = response.getRespuesta().getDescripcion();
		LOGGER.info("codigoRespuesta " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

		if (!CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
			LOGGER.error("Service error code received: " + codigoRespuesta
					+ " - " + descripcionRespuesta);
			LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
		}
	}

}
