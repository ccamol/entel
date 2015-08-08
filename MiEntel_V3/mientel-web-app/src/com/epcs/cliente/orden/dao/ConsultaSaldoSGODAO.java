package com.epcs.cliente.orden.dao;

import org.apache.log4j.Logger;

import com.epcs.bean.DetalleConsultaSaldoSGOBean;
import com.epcs.cliente.orden.ClienteOrdenService;
import com.epcs.cliente.orden.ClienteOrdenService_Service;
import com.epcs.cliente.orden.types.ConsultarSaldoSGOType;
import com.epcs.cliente.orden.types.ResultadoConsultarSaldoSGOType;
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
public class ConsultaSaldoSGODAO {

	private static final Logger LOGGER = Logger
    .getLogger(ConsultaSaldoSGODAO.class);

	public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
    .getProperty("servicios.codigoRespuesta.exito");
	
	public DetalleConsultaSaldoSGOBean consultarSGO(String numeroPcs)throws DAOException, ServiceException{
		
		ClienteOrdenService port = null;
        LOGGER.info("Instanciando el port");
        try {
            port = (ClienteOrdenService) WebServiceLocator.getInstance()
                    .getPort(ClienteOrdenService_Service.class,
                            ClienteOrdenService.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ClienteOrdenService.class, e);
            LOGGER.error( new DAOException(e));
        }
        
        DetalleConsultaSaldoSGOBean bean = new DetalleConsultaSaldoSGOBean();
        ConsultarSaldoSGOType request = new ConsultarSaldoSGOType();
        ResultadoConsultarSaldoSGOType response = new ResultadoConsultarSaldoSGOType();
        
        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setMsisdn(numeroPcs);

            LOGGER.info("Invocando servicio");
            response = port.consultarSaldoSGO(request);
                        
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }
        
        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
        	bean.setDiasExpiracion(response.getDetalleSaldoSGO().getDiasExpiracion());
            bean.setNombrePlan(response.getDetalleSaldoSGO().getNombrePlan());
            bean.setSaldo(response.getDetalleSaldoSGO().getSaldo());
            return bean;
        }
        else{
        	LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
         return new DetalleConsultaSaldoSGOBean();       		
	}
	
}
