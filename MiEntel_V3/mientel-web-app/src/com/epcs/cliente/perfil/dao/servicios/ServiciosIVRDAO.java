/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao.servicios;

import java.util.Date;

import org.apache.log4j.Logger;

import com.epcs.bean.ResultadoServicioBean;
import com.epcs.cliente.orden.ClienteOrdenService;
import com.epcs.cliente.orden.ClienteOrdenService_Service;
import com.epcs.cliente.orden.types.AdministrarServiciosIVRPPType;
import com.epcs.cliente.orden.types.AdministrarServiciosIVRType;
import com.epcs.cliente.orden.types.ResultadoAdministrarServiciosIVRPPType;
import com.epcs.cliente.orden.types.ResultadoAdministrarServiciosIVRType;
import com.epcs.cliente.orden.types.ServiciosIVRType;
import com.epcs.cliente.perfil.dao.AdministracionServiciosDAO;
import com.epcs.cliente.perfil.dao.util.ServiciosIVRPropertiesHelper;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * Clase con metodos de administracion de servicios de IVR.<br>
 * Esta clase no esta pensada para ser usada directamente, sino que como
 * Composicion de {@link AdministracionServiciosDAO}
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ServiciosIVRDAO {

    
    private static final Logger LOGGER = Logger
            .getLogger(ServiciosIVRDAO.class);

    /**
     * Valor de exito para la respuesta a un servicio
     */
    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");

    
    /**
     * 
     */
    public ServiciosIVRDAO() {
    }
    

    /**
     * Metodo 'common' para la activacion de servicios IVR
     * 
     * @param numeroPcs
     * @param servicio
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean activarServicioIVR(String numeroPcs,
            String servicio) throws DAOException, ServiceException {

        String accion = ServiciosIVRPropertiesHelper.getActivar(servicio);
        String idTipoServicio = ServiciosIVRPropertiesHelper
                .getIdTipoServicio(servicio);
        Date fechaActual = new Date();

        return this.administrarServiciosIVR(numeroPcs, accion, idTipoServicio,
                fechaActual);
    }

    /**
     * Metodo 'common' para la desactivacion de servicios IVR
     * 
     * @param numeroPcs
     * @param servicio
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean desactivarServicioIVR(String numeroPcs,
            String servicio) throws DAOException, ServiceException {

        String accion = ServiciosIVRPropertiesHelper.getDesactivar(servicio);
        String idTipoServicio = ServiciosIVRPropertiesHelper
                .getIdTipoServicio(servicio);
        Date fechaActual = new Date();

        return this.administrarServiciosIVR(numeroPcs, accion, idTipoServicio,
                fechaActual);
    }

    /**
     * 
     * Metodo para activacion/desactivacion de los servicios:<br>
     * <ul>
     * <li>Notificacion SMS</li>
     * </ul>
     * <br>
     * Este metodo realiza la activacion/desactivacion del servicio.<br>
     * No se recomienda usar este metodo directamente, mas bien, emplear los
     * metodos de activar/desactivar especificos para cada servicio, puesto
     * estos resuelven los valores para los parametros <code>accion</code>, e
     * <code>idTipoServicio</code>
     * 
     * @see #activarServicioIVR(String, String)
     * @see #desactivarServicioIVR(String, String)
     * 
     * @param numeroPcs
     *            String numero para quien se solicita activar/desactivar el
     *            servicio
     * @param accion
     *            String que indica accion activar o desactivar
     * @param idTipoServicio
     *            String con indicador del tipo de servicio
     * @param fechaActual
     *            {@link Date} fecha con que debe registrarse la accion sobre el
     *            servicio
     * @return {@link ResultadoServicioBean} si el servicio finaliza
     *         correctamente
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean administrarServiciosIVR(String numeroPcs,
            String accion, String idTipoServicio, Date fechaActual)
            throws DAOException, ServiceException {

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

        AdministrarServiciosIVRType request = new AdministrarServiciosIVRType();
        ResultadoAdministrarServiciosIVRType response = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setAccion(accion);
            String fechaActualFormated = DateHelper.format(fechaActual,
                    DateHelper.FORMAT_yyyyMMdd_HYPHEN);
            request.setFechaActual(fechaActualFormated);
            request.setIdTipoServicio(idTipoServicio);
            request.setMsisdn(numeroPcs);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Solicitud administracion IVR para : "
                      + numeroPcs);
                LOGGER.debug("    idTipoServicio : " + idTipoServicio);
                LOGGER.debug("    accion : " + accion);
                LOGGER.debug("    fechaActual : " + fechaActualFormated);
            }

            LOGGER.info("Invocando servicio");
            response = port.administrarServiciosIVR(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
                    + "administrarServiciosIVR", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException(
                    "administrarServiciosIVR: Servicio no responde "
                            + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {

                ServiciosIVRType servicioIVR = response
                        .getAdmnistrarServiciosIVR();
                ResultadoServicioBean resultadoBean = new ResultadoServicioBean();

                resultadoBean.setCorrelativoTransferencia(servicioIVR
                        .getCorrelativoTransferencia());
                resultadoBean.setNumeroPcs(servicioIVR.getMsisdn());

                if (LOGGER.isDebugEnabled()) {

                    LOGGER.debug("Administracion servicio IVR realizada para "
                            + resultadoBean.getNumeroPcs());
                    LOGGER.debug("    correlativo : "
                            + resultadoBean.getCorrelativoTransferencia());
                }

                return resultadoBean;

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "administrarServiciosIVR", e);
                LOGGER.error( new DAOException(e));
            }

        }
        else {
            LOGGER
                    .info("administrarServiciosIVR: Service error code received: "
                            + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new ResultadoServicioBean();
    }

    /**
     * Metodo 'common' para la activacion de servicios IVR PP
     * 
     * @param numeroPcs
     * @param servicio
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean activarServicioIVRPP(String numeroPcs,
            String servicio) throws DAOException, ServiceException {

        String accion = ServiciosIVRPropertiesHelper.getActivar(servicio);
        String idTipoServicio = ServiciosIVRPropertiesHelper
                .getIdTipoServicio(servicio);
        Date fechaActual = new Date();

        return this.administrarServiciosIVRPP(numeroPcs, accion, idTipoServicio,
                fechaActual);
    }

    /**
     * Metodo 'common' para la desactivacion de servicios IVR PP
     * 
     * @param numeroPcs
     * @param servicio
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean desactivarServicioIVRPP(String numeroPcs,
            String servicio) throws DAOException, ServiceException {

        String accion = ServiciosIVRPropertiesHelper.getDesactivar(servicio);
        String idTipoServicio = ServiciosIVRPropertiesHelper
                .getIdTipoServicio(servicio);
        Date fechaActual = new Date();

        return this.administrarServiciosIVRPP(numeroPcs, accion, idTipoServicio,
                fechaActual);
    }


    /**
     * 
     * Metodo para activacion/desactivacion de los servicios:<br>
     * <ul>
     * <li>Buzon de Voz, mercado PP</li>
     * <li>Notificacion SMS, mercado PP</li>
     * </ul>
     * <br>
     * No se recomienda usar este metodo directamente, mas bien, emplear los
     * metodos de activar/desactivar especificos para cada servicio, puesto
     * estos resuelven los valores para los parametros <code>accion</code>, 
     * <code>idTipoServicio</code> y <code>fechaActual</code> 
     * 
     * @see #activarServicioIVRPP(String, String)
     * @see #desactivarServicioIVRPP(String, String)
     * 
     * @param numeroPcs
     *            String numero para quien se solicita activar/desactivar el
     *            servicio
     * @param accion
     *            String que indica accion activar o desactivar
     * @param idTipoServicio
     *            String con indicador del tipo de servicio
     * @param fechaActual
     *            {@link Date} fecha con que debe registrarse la accion sobre el
     *            servicio
     * @return {@link ResultadoServicioBean} si el servicio finaliza
     *         correctamente
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean administrarServiciosIVRPP(String numeroPcs,
            String accion, String idTipoServicio, Date fechaActual)
            throws DAOException, ServiceException {

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

        AdministrarServiciosIVRPPType request = new AdministrarServiciosIVRPPType();
        ResultadoAdministrarServiciosIVRPPType response = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setAccion(accion);

            String fechaActualFormated = DateHelper.format(fechaActual,
                    DateHelper.FORMAT_yyyyMMdd_HYPHEN);
            request.setFechaActual(fechaActualFormated);

            request.setIdTipoServicio(idTipoServicio);
            request.setMsisdn(numeroPcs);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Solicitud administracion IVR PP para : "
                        + numeroPcs);
                LOGGER.debug("    idTipoServicio : " + idTipoServicio);
                LOGGER.debug("    accion : " + accion);
                LOGGER.debug("    fechaActual : " + fechaActualFormated);
            }

            LOGGER.info("Invocando servicio");
            response = port.administrarServiciosIVRPP(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
                    + "administrarServiciosIVRPP", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException(
                    "administrarServiciosIVRPP: Servicio no responde "
                            + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {
                ServiciosIVRType servicioIvrType = response
                        .getAdmnistrarServiciosIVRPP();

                ResultadoServicioBean resultado = new ResultadoServicioBean();
                resultado.setNumeroPcs(servicioIvrType.getMsisdn());
                resultado.setCorrelativoTransferencia(servicioIvrType
                        .getCorrelativoTransferencia());

                if (LOGGER.isDebugEnabled()) {

                    LOGGER.debug("Administracion servicio IVR PP realizada para "
                            + resultado.getNumeroPcs());
                    LOGGER.debug("    correlativo : "
                            + resultado.getCorrelativoTransferencia());
                }

                return resultado;

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "administrarServiciosIVRPP", e);
                LOGGER.error( new DAOException(e));
            }

        }
        else {
            LOGGER.info("administrarServiciosIVRPP: Service error code received: "
                    + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new ResultadoServicioBean();
    }

    
}
