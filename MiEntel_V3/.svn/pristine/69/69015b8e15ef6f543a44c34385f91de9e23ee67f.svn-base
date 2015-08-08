/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao.servicios;

import org.apache.log4j.Logger;

import com.epcs.bean.AdminServicioBuzonBean;
import com.epcs.bean.ResultadoServicioBean;
import com.epcs.cliente.orden.ClienteOrdenService;
import com.epcs.cliente.orden.ClienteOrdenService_Service;
import com.epcs.cliente.orden.types.AdministrarCobroRevertidoType;
import com.epcs.cliente.orden.types.AdministrarRecepcionCobroRevertidoType;
import com.epcs.cliente.orden.types.ResultadoAdministrarCobroRevertidoType;
import com.epcs.cliente.orden.types.ResultadoAdministrarRecepcionCobroRevertidoType;
import com.epcs.cliente.perfil.dao.AdministracionServiciosDAO;
import com.epcs.cliente.perfil.dao.util.ServiciosCobroRevertidoPropertiesHelper;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * Clase con metodos de administracion de servicios de Buzon .<br>
 * Esta clase no esta pensada para ser usada directamente, sino que como
 * Composicion de {@link AdministracionServiciosDAO}
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ServiciosCobroRevertidoDAO {

    private static final Logger LOGGER = Logger
            .getLogger(ServiciosCobroRevertidoDAO.class);

    /**
     * Valor de exito para la respuesta a un servicio
     */
    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");

    /**
     * 
     */
    public ServiciosCobroRevertidoDAO() {
    }

    /**
     * Realiza la activacion de un servicio de Buzon de Voz
     * 
     * @return {@link ResultadoServicioBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean agregarNumero(String numeroPcs,
            String numeroAgregar) throws DAOException, ServiceException {
        String accion = ServiciosCobroRevertidoPropertiesHelper.getAgregar();
        return this.administrarServiciosCobroRevertido(numeroPcs,
                numeroAgregar, accion);
    }

    /**
     * Realiza la desactivacion de un servicio Buzon de Voz
     * 
     * @return {@link ResultadoServicioBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean eliminarNumero(String numeroPcs,String numeroEliminar)
            throws DAOException, ServiceException {
        String accion = ServiciosCobroRevertidoPropertiesHelper.getEliminar();
        return this.administrarServiciosCobroRevertido(numeroPcs, numeroEliminar , accion);
    }
    
    
    /**
     * Realiza la desactivacion de un servicio Buzon de Voz
     * 
     * @return {@link ResultadoServicioBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean validarNumero(String numeroPcs, String numeroAgregar)
            throws DAOException, ServiceException {
        String accion = ServiciosCobroRevertidoPropertiesHelper.getValidar();
        return this.administrarServiciosCobroRevertido(numeroPcs, numeroAgregar, accion);
    }
    

    /**
     * Metodo para activacion/desactivacion de los servicios de Buzon de Voz<br>
     * <br>
     * Este metodo realiza la activacion/desactivacion del servicio.<br>
     * No se recomienda usar este metodo directamente, mas bien, emplear los
     * metodos de activar/desactivar especificos para cada servicio, puesto
     * estos resuelven los valores para el parametro <code>accion</code>
     * 
     * @param adminServicioBuzonBean
     *            {@link AdminServicioBuzonBean} con informacion de numero,
     *            email, rut y tipo de perfil del usuario
     * @param accion
     *            String que indica activar o desactivar
     * 
     * @see #activarServicio(AdminServicioBuzonBean)
     * @see #desactivarServicio(AdminServicioBuzonBean)
     * 
     * @return {@link ResultadoServicioBean} sin informacion de Correlativo de
     *         transaccion
     * 
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean administrarServiciosCobroRevertido(
            String numeroPcs, String numeroAgregar, String accion)
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

        AdministrarCobroRevertidoType request = new AdministrarCobroRevertidoType();
        ResultadoAdministrarCobroRevertidoType response = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setAccion(accion);
            request.setNumeroAgregado(numeroAgregar);
            request.setMsisdn(numeroPcs);

            if (LOGGER.isDebugEnabled()) {
                LOGGER
                        .debug("Solicitud de administracion de servicio cobro revertido para "
                                + numeroPcs);
                LOGGER.debug("    Numero Agregar : " + numeroAgregar);
                LOGGER.debug("    accion : " + accion);
            }

            LOGGER.info("Invocando servicio");
            response = port.administrarCobroRevertido(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
                    + "administrarCobroRevertido", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException(
                    "administrarCobroRevertido: Servicio no responde "
                            + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {

                ResultadoServicioBean resultado = new ResultadoServicioBean();
                resultado.setNumeroPcs(numeroPcs);

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(" Administracion de servicio cobro revertido "
                            + "realizada para : ");
                }

                return resultado;

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "administrarCobroRevertido", e);
                LOGGER.error( new DAOException(e));
            }

        }
        else {
            LOGGER.info("administrarCobroRevertido: Service error code received: "
                            + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new ResultadoServicioBean();
    }
    
    
    
    /**
     * Metodo para activacion/desactivacion de los servicios de Buzon de Voz<br>
     * <br>
     * Este metodo realiza la activacion/desactivacion del servicio.<br>
     * No se recomienda usar este metodo directamente, mas bien, emplear los
     * metodos de activar/desactivar especificos para cada servicio, puesto
     * estos resuelven los valores para el parametro <code>accion</code>
     * 
     * @param adminServicioBuzonBean
     *            {@link AdminServicioBuzonBean} con informacion de numero,
     *            email, rut y tipo de perfil del usuario
     * @param accion
     *            String que indica activar o desactivar
     * 
     * @see #activarServicio(AdminServicioBuzonBean)
     * @see #desactivarServicio(AdminServicioBuzonBean)
     * 
     * @return {@link ResultadoServicioBean} sin informacion de Correlativo de
     *         transaccion
     * 
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean administrarServiciosRecepcionCobroRevertido(
            String numeroPcs, String accion)
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

        AdministrarRecepcionCobroRevertidoType request = new AdministrarRecepcionCobroRevertidoType();
        ResultadoAdministrarRecepcionCobroRevertidoType response = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setAccion(accion);
            request.setMsisdn(numeroPcs);
            request.setFechaActual(DateHelper.format(new java.util.Date(), DateHelper.FORMAT_yyyyMMdd_HYPHEN));

            if (LOGGER.isDebugEnabled()) {
                LOGGER
                        .debug("Solicitud de administracion de servicio cobro revertido para "
                                + numeroPcs);
                LOGGER.debug("    Numero Agregar : ");
                LOGGER.debug("    accion : " + accion);
            }

            LOGGER.info("Invocando servicio");
            response = port.administrarRecepcionCobroRevertido(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
                    + "administrarRecepcionCobroRevertido", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException(
                    "administrarRecepcionCobroRevertido: Servicio no responde "
                            + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {

                ResultadoServicioBean resultado = new ResultadoServicioBean();
                resultado.setNumeroPcs(numeroPcs);

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(" Administracion de servicio cobro revertido "
                            + "realizada para : ");
                }

                return resultado;

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "administrarRecepcionCobroRevertido", e);
                LOGGER.error( new DAOException(e));
            }

        }
        else {
            LOGGER.info("administrarRecepcionCobroRevertido: Service error code received: "
                            + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new ResultadoServicioBean();
    }
    
    
    /**
     * Realiza la desactivacion de un servicio Buzon de Voz
     * 
     * @return {@link ResultadoServicioBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean activarRecepcionCobroRevertido(String numeroPcs)
            throws DAOException, ServiceException {
        String accion = ServiciosCobroRevertidoPropertiesHelper.getActivar();
        return this.administrarServiciosRecepcionCobroRevertido(numeroPcs, accion);
    }
    
    /**
     * Realiza la desactivacion de un servicio Buzon de Voz
     * 
     * @return {@link ResultadoServicioBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoServicioBean desactivarRecepcionCobroRevertido(String numeroPcs)
            throws DAOException, ServiceException {
        String accion = ServiciosCobroRevertidoPropertiesHelper.getDesactivar();
        return this.administrarServiciosRecepcionCobroRevertido(numeroPcs, accion);
    }

}
