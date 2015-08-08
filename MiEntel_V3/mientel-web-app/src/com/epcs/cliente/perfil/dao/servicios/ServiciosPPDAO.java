/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao.servicios;

import org.apache.log4j.Logger;

import com.epcs.bean.ResultadoServicioBean;
import com.epcs.cliente.orden.ClienteOrdenService;
import com.epcs.cliente.orden.ClienteOrdenService_Service;
import com.epcs.cliente.orden.types.AdministracionServiciosDatosPPType;
import com.epcs.cliente.orden.types.AdministrarServiciosWAPType;
import com.epcs.cliente.orden.types.ResultadoAdministracionServiciosDatosPPType;
import com.epcs.cliente.orden.types.ResultadoAdministrarServiciosWAPType;
import com.epcs.cliente.orden.types.ServiciosDatosPPType;
import com.epcs.cliente.orden.types.ServiciosWAPType;
import com.epcs.cliente.perfil.dao.util.ServiciosPPPropertiesHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ServiciosPPDAO {

    private static final Logger LOGGER = Logger.getLogger(ServiciosPPDAO.class);

    /**
     * Valor de exito para la respuesta a un servicio
     */
    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");

    /**
     * 
     */
    public ServiciosPPDAO() {
    }

    /**
     * Solicita activacion de servicios MMS-GPRS para mercado PP
     * 
     * @param numeroPcs
     * @return {@link ResultadoServicioBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean activarMMSGPRS(String numeroPcs)
            throws DAOException, ServiceException {
        String accion = ServiciosPPPropertiesHelper.getActivar("mms-gprs");
        return this.administrarServiciosDatosPP(numeroPcs, accion);
    }

    /**
     * Solicita desactivacion de servicios MMS-GPRS para mercado PP
     * 
     * @param numeroPcs
     * @return {@link ResultadoServicioBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean desactivarMMSGPRS(String numeroPcs)
            throws DAOException, ServiceException {
        String accion = ServiciosPPPropertiesHelper.getDesactivar("mms-gprs");
        return this.administrarServiciosDatosPP(numeroPcs, accion);
    }

    /**
     * Metodo para activacion/desactivacion de servicio de datos del mercado PP:<br>
     * <ul>
     * <li>MMS-GPRS</li>
     * </ul>
     * <br>
     * No se recomienda usar este metodo directamente, mas bien, emplear los
     * metodos de activar/desactivar especificos para cada servicio, puesto
     * estos resuelven el valor de <code>accion</code>
     * 
     * @see #activarMMSGPRS(String)
     * @see #desactivarMMSGPRS(String)
     * 
     * @param numeroPcs
     *            String numero para quien se solicita activar/desactivar el
     *            servicio
     * @param accion
     *            String que indica accion activar o desactivar
     * @return {@link ResultadoServicioBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean administrarServiciosDatosPP(String numeroPcs,
            String accion) throws DAOException, ServiceException {

        ClienteOrdenService port = null;
        LOGGER.info("Instanciando el port " + ClienteOrdenService.class);
        try {
            port = (ClienteOrdenService) WebServiceLocator.getInstance()
                    .getPort(ClienteOrdenService_Service.class,
                            ClienteOrdenService.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ClienteOrdenService.class, e);
            LOGGER.error( new DAOException(e));
        }

        AdministracionServiciosDatosPPType request = new AdministracionServiciosDatosPPType();
        ResultadoAdministracionServiciosDatosPPType response = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setAccion(accion);
            request.setMsisdn(numeroPcs);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Solicitud administracion MMS-GPRS para : "
                        + numeroPcs);
                LOGGER.debug("    accion : " + accion);
            }

            LOGGER.info("Invocando servicio");
            response = port.administracionServiciosDatosPP(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
                    + "administracionServiciosDatosPP", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException(
                    "administracionServiciosDatosPP: Servicio no responde "
                            + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {

                ServiciosDatosPPType serviciosDatosPPType = response.getServiciosDatosPP();
                ResultadoServicioBean resultado = new ResultadoServicioBean();

                resultado.setNumeroPcs(serviciosDatosPPType.getMsisdn());

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Solicitud administracion MMS-GPRS realizda para : "
                            + numeroPcs);
                }
                return resultado;

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "administracionServiciosDatosPP", e);
                LOGGER.error( new DAOException(e));
            }

        }
        else {
            LOGGER.info("administracionServiciosDatosPP: Service error code received: "
                    + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new ResultadoServicioBean();
    }

    /**
     * Solicita activacion de servicios Wap para mercado PP
     * 
     * @param numeroPcs
     * @return {@link ResultadoServicioBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean activarWap(String numeroPcs)
            throws DAOException, ServiceException {
        String accion = ServiciosPPPropertiesHelper.getActivar("wap");
        return this.administrarServiciosWap(numeroPcs, accion);
    }

    /**
     * Solicita desactivacion de servicios Wap para mercado PP
     * 
     * @param numeroPcs
     * @return {@link ResultadoServicioBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean desactivarWap(String numeroPcs)
            throws DAOException, ServiceException {
        String accion = ServiciosPPPropertiesHelper.getDesactivar("wap");
        return this.administrarServiciosWap(numeroPcs, accion);
    }

    
    /**
     * Metodo para activacion/desactivacion de servicio Wap <br>
     * No se recomienda usar este metodo directamente, mas bien, emplear los
     * metodos de activar/desactivar especificos para cada servicio, puesto
     * estos resuelven el valor de <code>accion</code>
     * 
     * @see #activarWap(String)
     * @see #desactivarWap(String)
     * 
     * @param numeroPcs
     *            String numero para quien se solicita activar/desactivar el
     *            servicio
     * @param accion
     *            String que indica accion activar o desactivar
     * @return {@link ResultadoServicioBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean administrarServiciosWap(String numeroPcs,
            String accion) throws DAOException, ServiceException {

        ClienteOrdenService port = null;
        LOGGER.info("Instanciando el port " + ClienteOrdenService.class);
        try {
            port = (ClienteOrdenService) WebServiceLocator.getInstance()
                    .getPort(ClienteOrdenService_Service.class,
                            ClienteOrdenService.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ClienteOrdenService.class, e);
            LOGGER.error( new DAOException(e));
        }

        AdministrarServiciosWAPType request = new AdministrarServiciosWAPType();
        ResultadoAdministrarServiciosWAPType response = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setAccion(accion);
            request.setMsisdn(numeroPcs);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Solicitud administracion WAP para : " + numeroPcs);
                LOGGER.debug("    accion : " + accion);
            }

            LOGGER.info("Invocando servicio");
            response = port.administrarServiciosWAP(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
                    + "administrarServiciosWAP", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException(
                    "administrarServiciosWAP: Servicio no responde "
                            + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {

                ServiciosWAPType serviciosWAPType = response.getServiciosWap();
                ResultadoServicioBean resultado = new ResultadoServicioBean();

                resultado.setNumeroPcs(serviciosWAPType.getMsisdn());

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Solicitud administracion MMS-GPRS realizda para : "
                            + numeroPcs);
                }
                return resultado;

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "administrarServiciosWAP", e);
                LOGGER.error( new DAOException(e));
            }

        }
        else {
            LOGGER.info("administrarServiciosWAP: Service error code received: "
                    + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new ResultadoServicioBean();
    }
}
