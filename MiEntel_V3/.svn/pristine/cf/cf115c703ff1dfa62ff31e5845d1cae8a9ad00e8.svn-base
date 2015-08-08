/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.orden.dao;

import org.apache.log4j.Logger;

import com.epcs.cliente.orden.ClienteOrdenService;
import com.epcs.cliente.orden.ClienteOrdenService_Service;
import com.epcs.cliente.orden.types.ConsultarEstadoReposicionType;
import com.epcs.cliente.orden.types.ReposicionServicioOrqType;
import com.epcs.cliente.orden.types.ResultadoConsultarEstadoReposicionType;
import com.epcs.cliente.orden.types.ResultadoReposicionServicioOrqType;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ReposicionServicioDAO {

    private static final Logger LOGGER = Logger
            .getLogger(ReposicionServicioDAO.class);

    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");

    public static final String REPOSICION_ID_SISTEMA = MiEntelProperties
            .getProperty("reposicionServicio.idSistema");

    public static final String REPOSICION_PASSWORD_SISTEMA = MiEntelProperties
            .getProperty("reposicionServicio.passwordSistema");

    public static final String FACTURA_PAGADA = MiEntelProperties
            .getProperty("facturasImpagas.pagado");

    public static final String FACTURA_NO_PAGADA = MiEntelProperties
            .getProperty("facturasImpagas.nopagado");

    public static final String REP_CON_COBRO = MiEntelProperties
            .getProperty("reposicionServicio.tipoReposicion.concobro");

    public static final String REP_SIN_COBRO = MiEntelProperties
            .getProperty("reposicionServicio.tipoReposicion.sincobro");

    /**
     * Devuelve un String indicando el estado del servicio para reposicion
     * @param numeroPcs
     * @return String  "PERMITE" o "REALIZADA"
     * @throws DAOException
     * @throws ServiceException
     */
    public String consultarEstadoReposicion(String numeroPcs)
            throws DAOException, ServiceException {

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

        ConsultarEstadoReposicionType request = new ConsultarEstadoReposicionType();
        ResultadoConsultarEstadoReposicionType resultadoConsultarEstadoReposicionType = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setMsisdn(numeroPcs);

            LOGGER.info("Invocando servicio");
            resultadoConsultarEstadoReposicionType = port
                    .consultarEstadoReposicion(request);
            
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = resultadoConsultarEstadoReposicionType
                .getRespuesta().getCodigo();
        String descripcionRespuesta = resultadoConsultarEstadoReposicionType
                .getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            return resultadoConsultarEstadoReposicionType.getTipoRespuesta();
        }
        else {
            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return "";
    }
    
    /**
     * 
     * @param numeroPcs
     * @param estadoFactura
     * @throws DAOException
     * @throws ServiceException
     */
    public void reponerServicio(String numeroPcs, String estadoFactura)
            throws DAOException, ServiceException {

        LOGGER.info("Instanciando el port");
        ClienteOrdenService port = null;
        try {
            port = (ClienteOrdenService) WebServiceLocator.getInstance()
                    .getPort(ClienteOrdenService_Service.class,
                            ClienteOrdenService.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ClienteOrdenService.class, e);
            LOGGER.error( new DAOException(e));
        }

        ReposicionServicioOrqType request = new ReposicionServicioOrqType();
        ResultadoReposicionServicioOrqType resultadoReposicionServicioOrqType = null;
        
        try {
            LOGGER.info("Configurando Datos de la peticion");
            request.setMsisdn(numeroPcs);
            request.setIdSistema(REPOSICION_ID_SISTEMA);
            request.setPasswordSistema(REPOSICION_PASSWORD_SISTEMA);

            String tipoReposicion = null;
            if (FACTURA_PAGADA.equals(estadoFactura)) {
                tipoReposicion = REP_SIN_COBRO;
            }
            else if (FACTURA_NO_PAGADA.equals(estadoFactura)) {
                tipoReposicion = REP_CON_COBRO;
            }
            else {
                LOGGER
                        .error("No se han configurado los parametros para facturasImpagas");
                LOGGER.error( new DAOException(
                        "No se han configurado los parametros para facturasImpagas"));
            }
            request.setTipoReposicion(tipoReposicion);

            LOGGER.info("Invocando servicio");
            resultadoReposicionServicioOrqType = port
                    .reposicionServicioOrq(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }
        
        String codigoRespuesta = resultadoReposicionServicioOrqType.getRespuesta()
                .getCodigo();
        String descripcionRespuesta = resultadoReposicionServicioOrqType
                .getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            LOGGER.info("ReposicionRealizada " + codigoRespuesta);

        }
        else {
            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
    }

}
