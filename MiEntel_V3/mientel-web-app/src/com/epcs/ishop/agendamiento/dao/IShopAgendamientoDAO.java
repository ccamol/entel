/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.ishop.agendamiento.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.bean.ResumenAgendamientoBean;
import com.epcs.bean.ResumenContenidoBean;
import com.epcs.provisionamientoyentrega.agendamiento.IShopAgendamientoService;
import com.epcs.provisionamientoyentrega.agendamiento.IShopAgendamientoServicePortType;
import com.epcs.provisionamientoyentrega.agendamiento.types.ConsultarAgendamientoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.ConsultarContenidoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.ContenidoAgendaType;
import com.epcs.provisionamientoyentrega.agendamiento.types.ContenidoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.DespausarAgendamientoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.DespausarTodosAgendamientoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.EliminarAgendamientoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.PausarAgendamientoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.PausarTodosAgendamientoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.ResultadoConsultarAgendamientoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.ResultadoConsultarContenidoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.ResultadoDespausarAgendamientoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.ResultadoDespausarTodosAgendamientoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.ResultadoEliminarAgendamientoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.ResultadoPausarAgendamientoType;
import com.epcs.provisionamientoyentrega.agendamiento.types.ResultadoPausarTodosAgendamientoType;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.exception.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class IShopAgendamientoDAO {

    /**
     * Logger para IShopAgendamientoDAO
     */
    private static final Logger LOGGER = Logger
            .getLogger(IShopAgendamientoDAO.class);

    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");    
    
    public List<ResumenContenidoBean> consultarContenido(String tipo) throws DAOException, ServiceException {        
        IShopAgendamientoServicePortType port;
        LOGGER.info("Instanciando el port " + IShopAgendamientoServicePortType.class);
        try {
            port = (IShopAgendamientoServicePortType) WebServiceLocator.getInstance().getPort(
                    IShopAgendamientoService.class, IShopAgendamientoServicePortType.class);
            

            ConsultarContenidoType request = new ConsultarContenidoType();
            ResultadoConsultarContenidoType response = null;

            try {

                LOGGER.info("Configurando Datos de la peticion");

                request.setTipo(tipo);

                LOGGER.info("Invocando servicio");
                response = port.consultarContenido(request);

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service invocation: "
                        + "consultarContenido", e);
                LOGGER.error( new DAOException(e));
            }

            String codigoRespuesta = response.getRespuesta().getCodigo();
            String descripcionRespuesta = response.getRespuesta()
                    .getDescripcion();

            LOGGER.info("codigoRespuesta " + codigoRespuesta);
            LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

            if (Utils.isEmptyString(codigoRespuesta)) {
                LOGGER.error( new DAOException("consultarContenido: Servicio no responde "
                        + "con codigoRespuesta"));
            }

            if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
                List<ResumenContenidoBean> contenido = new LinkedList<ResumenContenidoBean>();
                try {
                    for (ContenidoType contenidoType : response.getContenido()) {
                        
                        ResumenContenidoBean resumen = new ResumenContenidoBean();
                        
                        resumen.setDescripcion(contenidoType.getDescripcion());
                        resumen.setId(contenidoType.getId());
                        resumen.setUrl(contenidoType.getUrl());
                        contenido.add(resumen);
                    }
                    return contenido;
                } catch (Exception e) {
                    LOGGER.error("Exception caught on Service response: "
                            + "consultarContenido", e);
                    LOGGER.error( new DAOException(e));
                }
                
            }
            else {
                LOGGER.error("consultarContenido: Service error code received: "
                        + codigoRespuesta + " - " + descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,
                        descripcionRespuesta));
            }            
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + IShopAgendamientoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }   
        return new ArrayList<ResumenContenidoBean>();
    }
    
    public List<ResumenAgendamientoBean> consultarAgendamiento(String tipoServicio, String msisdn) throws DAOException, ServiceException {
        IShopAgendamientoServicePortType port;
        LOGGER.info("Instanciando el port " + IShopAgendamientoServicePortType.class);
        try {
            port = (IShopAgendamientoServicePortType) WebServiceLocator.getInstance().getPort(
                    IShopAgendamientoService.class, IShopAgendamientoServicePortType.class);

            ConsultarAgendamientoType request = new ConsultarAgendamientoType();
            ResultadoConsultarAgendamientoType response = null;

            try {

                LOGGER.info("Configurando Datos de la peticion");
                
                request.setTipo(tipoServicio);
                request.setMsisdn(msisdn);

                LOGGER.info("Invocando servicio");
                response = port.consultarAgendamiento(request);

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service invocation: "
                        + "consultarAgendamiento", e);
                LOGGER.error( new DAOException(e));
            }

            String codigoRespuesta = response.getRespuesta().getCodigo();
            String descripcionRespuesta = response.getRespuesta()
                    .getDescripcion();

            LOGGER.info("codigoRespuesta " + codigoRespuesta);
            LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

            if (Utils.isEmptyString(codigoRespuesta)) {
                LOGGER.error( new DAOException("consultarAgendamiento: Servicio no responde "
                        + "con codigoRespuesta"));
            }

            if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
                List<ResumenAgendamientoBean> agendamiento = new LinkedList<ResumenAgendamientoBean>();
                try {
                    for (ContenidoAgendaType contenidoType : response.getContenido()) {
                        if (!contenidoType.getId().isEmpty()) {
                            ResumenAgendamientoBean resumen = new ResumenAgendamientoBean();
                            resumen.setTipoServicio(tipoServicio);
                            resumen.setDescripcion(contenidoType.getDescripcion());
                            resumen.setHora(contenidoType.getHora());
                            resumen.setId(contenidoType.getId());
                            resumen.setPeriodo(contenidoType.getPeriodo());
                            resumen.setStatus(contenidoType.getStatus());
                            resumen.setTipo(contenidoType.getTipo());
                            agendamiento.add(resumen);
                        }
                    }
                    return agendamiento;
                } catch (Exception e) {
                    LOGGER.error("Exception caught on Service response: "
                            + "consultarAgendamiento", e);
                    LOGGER.error( new DAOException(e));
                }

            }
            else {
                LOGGER.error("consultarAgendamiento: Service error code received: "
                        + codigoRespuesta + " - " + descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,
                        descripcionRespuesta));
            }
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + IShopAgendamientoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }
        return new ArrayList<ResumenAgendamientoBean>();
    }
    
    public void pausarAgendamiento(String tipoMensajeria, String tipo, String idPedido, String hora, String msisdn) throws DAOException, ServiceException {
        IShopAgendamientoServicePortType port;
        LOGGER.info("Instanciando el port " + IShopAgendamientoServicePortType.class);
        try {
            port = (IShopAgendamientoServicePortType) WebServiceLocator.getInstance().getPort(
                    IShopAgendamientoService.class, IShopAgendamientoServicePortType.class);            

            PausarAgendamientoType request = new PausarAgendamientoType();
            ResultadoPausarAgendamientoType response = null;

            try {

                LOGGER.info("Configurando Datos de la peticion");
                
                request.setTipoMensajeria(tipoMensajeria);
                request.setTipo(tipo);
                request.setIdPedido(idPedido);
                request.setHora(hora);
                request.setMsisdn(msisdn);
                
                LOGGER.info("Invocando servicio");
                response = port.pausarAgendamiento(request);

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service invocation: "
                        + "pausarAgendamiento", e);
                LOGGER.error( new DAOException(e));
            }

            String codigoRespuesta = response.getRespuesta().getCodigo();
            String descripcionRespuesta = response.getRespuesta()
                    .getDescripcion();

            LOGGER.info("codigoRespuesta " + codigoRespuesta);
            LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

            if (Utils.isEmptyString(codigoRespuesta)) {
                LOGGER.error( new DAOException("pausarAgendamiento: Servicio no responde "
                        + "con codigoRespuesta"));
            }

            if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
                try {
                    return;
                } catch (Exception e) {
                    LOGGER.error("Exception caught on Service response: "
                            + "pausarAgendamiento", e);
                    LOGGER.error( new DAOException(e));
                }
            }
            else {
                LOGGER.error("pausarAgendamiento: Service error code received: "
                        + codigoRespuesta + " - " + descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,
                        descripcionRespuesta));
            }
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + IShopAgendamientoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }        
    }
    
    public void despausarAgendamiento(String tipoMensajeria, String tipo, String idPedido, String hora, String msisdn) throws DAOException, ServiceException {
        IShopAgendamientoServicePortType port;
        LOGGER.info("Instanciando el port " + IShopAgendamientoServicePortType.class);
        try {
            port = (IShopAgendamientoServicePortType) WebServiceLocator.getInstance().getPort(
                    IShopAgendamientoService.class, IShopAgendamientoServicePortType.class);            

            DespausarAgendamientoType request = new DespausarAgendamientoType();
            ResultadoDespausarAgendamientoType response = null;

            try {

                LOGGER.info("Configurando Datos de la peticion");
                
                request.setTipoMensajeria(tipoMensajeria);
                request.setTipo(tipo);
                request.setIdPedido(idPedido);
                request.setHora(hora);
                request.setMsisdn(msisdn);
                
                LOGGER.info("Invocando servicio");
                response = port.despausarAgendamiento(request);

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service invocation: "
                        + "despausarAgendamiento", e);
                LOGGER.error( new DAOException(e));
            }

            String codigoRespuesta = response.getRespuesta().getCodigo();
            String descripcionRespuesta = response.getRespuesta()
                    .getDescripcion();

            LOGGER.info("codigoRespuesta " + codigoRespuesta);
            LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

            if (Utils.isEmptyString(codigoRespuesta)) {
                LOGGER.error( new DAOException("despausarAgendamiento: Servicio no responde "
                        + "con codigoRespuesta"));
            }

            if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
                try {
                    return;
                } catch (Exception e) {
                    LOGGER.error("Exception caught on Service response: "
                            + "despausarAgendamiento", e);
                    LOGGER.error( new DAOException(e));
                }
            }
            else {
                LOGGER.error("despausarAgendamiento: Service error code received: "
                        + codigoRespuesta + " - " + descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,
                        descripcionRespuesta));
            }
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + IShopAgendamientoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }        
    }
    
    public void eliminarAgendamiento(String tipoMensajeria, String tipo, String idPedido, String hora, String msisdn) throws DAOException, ServiceException {
        IShopAgendamientoServicePortType port;
        LOGGER.info("Instanciando el port " + IShopAgendamientoServicePortType.class);
        try {
            port = (IShopAgendamientoServicePortType) WebServiceLocator.getInstance().getPort(
                    IShopAgendamientoService.class, IShopAgendamientoServicePortType.class);            

            EliminarAgendamientoType request = new EliminarAgendamientoType();
            ResultadoEliminarAgendamientoType response = null;

            try {

                LOGGER.info("Configurando Datos de la peticion");
                
                request.setTipoMensajeria(tipoMensajeria);
                request.setTipo(tipo);
                request.setIdPedido(idPedido);
                request.setHora(hora);
                request.setMsisdn(msisdn);
                
                LOGGER.info("Invocando servicio");
                response = port.eliminarAgendamiento(request);

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service invocation: "
                        + "eliminarAgendamiento", e);
                LOGGER.error( new DAOException(e));
            }

            String codigoRespuesta = response.getRespuesta().getCodigo();
            String descripcionRespuesta = response.getRespuesta()
                    .getDescripcion();

            LOGGER.info("codigoRespuesta " + codigoRespuesta);
            LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

            if (Utils.isEmptyString(codigoRespuesta)) {
                LOGGER.error( new DAOException("eliminarAgendamiento: Servicio no responde "
                        + "con codigoRespuesta"));
            }

            if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
                try {
                    return;
                } catch (Exception e) {
                    LOGGER.error("Exception caught on Service response: "
                            + "eliminarAgendamiento", e);
                    LOGGER.error( new DAOException(e));
                }
            }
            else {
                LOGGER.error("eliminarAgendamiento: Service error code received: "
                        + codigoRespuesta + " - " + descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,
                        descripcionRespuesta));
            }
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + IShopAgendamientoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }        
    }
    
    public void pausarTodosAgendamiento(String tipoMensajeria, String msisdn) throws DAOException, ServiceException {
        IShopAgendamientoServicePortType port;
        LOGGER.info("Instanciando el port " + IShopAgendamientoServicePortType.class);
        try {
            port = (IShopAgendamientoServicePortType) WebServiceLocator.getInstance().getPort(
                    IShopAgendamientoService.class, IShopAgendamientoServicePortType.class);
            

            PausarTodosAgendamientoType request = new PausarTodosAgendamientoType();
            ResultadoPausarTodosAgendamientoType response = null;

            try {

                LOGGER.info("Configurando Datos de la peticion");

                request.setTipo(tipoMensajeria);
                request.setMsisdn(msisdn);
                
                LOGGER.info("Invocando servicio");
                response = port.pausarTodosAgendamiento(request);

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service invocation: "
                        + "pausarTodosAgendamiento", e);
                LOGGER.error( new DAOException(e));
            }

            String codigoRespuesta = response.getRespuesta().getCodigo();
            String descripcionRespuesta = response.getRespuesta()
                    .getDescripcion();

            LOGGER.info("codigoRespuesta " + codigoRespuesta);
            LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

            if (Utils.isEmptyString(codigoRespuesta)) {
                LOGGER.error( new DAOException("pausarTodosAgendamiento: Servicio no responde "
                        + "con codigoRespuesta"));
            }

            if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
                try {
                    return;
                } catch (Exception e) {
                    LOGGER.error("Exception caught on Service response: "
                            + "pausarTodosAgendamiento", e);
                    LOGGER.error( new DAOException(e));
                }
            }
            else {
                LOGGER.error("pausarTodosAgendamiento: Service error code received: "
                        + codigoRespuesta + " - " + descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,
                        descripcionRespuesta));
            }
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + IShopAgendamientoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }        
    }
    
    public void despausarTodosAgendamiento(String tipoMensajeria, String msisdn) throws DAOException, ServiceException {
        IShopAgendamientoServicePortType port;
        LOGGER.info("Instanciando el port " + IShopAgendamientoServicePortType.class);
        try {
            port = (IShopAgendamientoServicePortType) WebServiceLocator.getInstance().getPort(
                    IShopAgendamientoService.class, IShopAgendamientoServicePortType.class);
            

            DespausarTodosAgendamientoType request = new DespausarTodosAgendamientoType();
            ResultadoDespausarTodosAgendamientoType response = null;

            try {

                LOGGER.info("Configurando Datos de la peticion");

                request.setTipo(tipoMensajeria);
                request.setMsisdn(msisdn);
                
                LOGGER.info("Invocando servicio");
                response = port.despausarTodosAgendamiento(request);

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service invocation: "
                        + "despausarTodosAgendamiento", e);
                LOGGER.error( new DAOException(e));
            }

            String codigoRespuesta = response.getRespuesta().getCodigo();
            String descripcionRespuesta = response.getRespuesta()
                    .getDescripcion();

            LOGGER.info("codigoRespuesta " + codigoRespuesta);
            LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

            if (Utils.isEmptyString(codigoRespuesta)) {
                LOGGER.error( new DAOException("despausarTodosAgendamiento: Servicio no responde "
                        + "con codigoRespuesta"));
            }

            if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
                try {
                    return;
                } catch (Exception e) {
                    LOGGER.error("Exception caught on Service response: "
                            + "despausarTodosAgendamiento", e);
                    LOGGER.error( new DAOException(e));
                }
            }
            else {
                LOGGER.error("despausarTodosAgendamiento: Service error code received: "
                        + codigoRespuesta + " - " + descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,
                        descripcionRespuesta));
            }
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + IShopAgendamientoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }        
    }    
}
