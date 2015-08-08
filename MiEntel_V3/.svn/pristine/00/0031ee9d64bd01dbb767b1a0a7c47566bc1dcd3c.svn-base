/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao;

import org.apache.log4j.Logger;

import com.epcs.cliente.perfil.ClientePerfilService;
import com.epcs.cliente.perfil.ClientePerfilServicePortType;
import com.epcs.cliente.perfil.types.ConsultarPerfilamientoType;
import com.epcs.cliente.perfil.types.ResultadoConsultarPerfilamientoType;
import com.epcs.cliente.perfil.types.ResumenPerfilamientoType;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.exception.DAOException;
import com.epcs.recursoti.exception.ServiceException;
import com.epcs.seguridad.bean.PerfilUsuarioBean;
import com.epcs.seguridad.bean.RutBean;
import com.epcs.seguridad.bean.UsuarioSeleccionadoBean;

/**
 * Esta clase tiene como finalidad ocultar el comportamiento (proxy) de la
 * logica de consumo del servicio de autenticacion de usuarios para el proyecto
 * MiEntel v3.0. A traves de la biblioteca
 * SMInfoLoginServiceSoapBindingQSService-client valida un usuario por medio de
 * su numero de movil, rut y clave.<br>
 * 
 * @author mmartinez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs).
 * @version 1.0 - Creacion
 * 
 * @author jlopez (I2B) en nombre Absalon Opazo (Atencion al Cliente, EntelPcs).
 * @version 1.1 - Se reemplaza metodo getUUPProperties por obtenerPerfilUsuario
 *          siguiendo las practicas definidas en la construccion de metodos de
 *          consumo de servicios de Entel
 * 
 * 
 */
public class PerfilDAO {
    
    /**
     * Constante con valor de Exito a respuesta de Servicio
     */
    private static final String CODIGO_RESPUESTA_OK = "0000";

    /**
     * Prefijo de pais y red movil, correspondiente a Chile.
     */
    private static final String MSISDN_PREFIX = "569";

    /**
     * Valor de mercado PP
     */
    private static final String MERCADO_PP = "PP";

    
    /**
     * Logger de la aplicacion.
     */
    private Logger LOGGER = Logger.getLogger(PerfilDAO.class);
    
    /**
     * Constructor del DAO. Se inicializa el acceso al componente que consume el
     * servicio remoto.
     */
    public PerfilDAO() {
        super();
    }
    
    /**
     * Obtiene el perfil de un usuario a partir de un numeroPcs (aka: msisdn)
     * 
     * @param numeroPcs
     *            String numero Pcs de usuario para quien se busca el perfil
     * @return {@link PerfilUsuarioBean} con perfil del usuario asociado al
     *         numeroPcs
     * @throws DAOException
     *             ante errores en la ejecucion del metodo
     * @throws ServiceException
     *             Si el Servcio retorna un mensaje de error
     */
    public PerfilUsuarioBean obtenerPerfilUsuario(final String numeroPcs)
            throws DAOException, ServiceException {

        if (numeroPcs == null || numeroPcs.isEmpty()) {
            throw new DAOException("numeroPcs null o vacio");
        }

        ClientePerfilServicePortType port;
        LOGGER.info("Instanciando el port "
                + ClientePerfilServicePortType.class);
        try {
            port = (ClientePerfilServicePortType) WebServiceLocator
                    .getInstance().getPort(ClientePerfilService.class,
                            ClientePerfilServicePortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ClientePerfilServicePortType.class, e);
            throw new DAOException(e);
        }

        LOGGER.info("Configurando Datos de la peticion");
        ConsultarPerfilamientoType request = new ConsultarPerfilamientoType();

        String msisdn = normalizarMsisdn(numeroPcs);
        request.setMsisdn(msisdn);

        LOGGER.info("Invocando servicio");
        ResultadoConsultarPerfilamientoType response;
        try {
            response = port.consultarPerfilamiento(request);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: " +
            		"consultarPerfilamiento", e);
            throw new DAOException(e);
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (codigoRespuesta == null || codigoRespuesta.isEmpty()) {
            throw new DAOException(
                    "consultarPerfilamiento: Servicio no responde "
                            + "con codigoRespuesta");
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {

                PerfilUsuarioBean perfil = new PerfilUsuarioBean();

                ResumenPerfilamientoType resumenType = response
                        .getResumenPerfilamiento();

                String mercado = resumenType.getMercado();
                
                //Usuarios mercado PP no tienen titular
                if (!MERCADO_PP.equalsIgnoreCase(mercado)) {
                    perfil.setRutTitular(RutBean.parseRut(resumenType
                            .getRutTitular()));
                    perfil.setNombreTitular(resumenType.getNombreTitular());
                }
                
                perfil.setMercado(mercado);
                
                perfil.setAaa(Integer.parseInt(resumenType.getAaa()));
                perfil.setFlagBam(Integer.parseInt(resumenType.getFlagBam()));
                perfil.setNombreUsuario(resumenType.getNombreUsuario());
                perfil.setNumeroCuenta(resumenType.getNumeroCuenta());
                perfil.setNumeroPcs(resumenType.getNumeroPcs());

                perfil.setRutUsuario(RutBean.parseRut(resumenType
                        .getRutUsuario()));
                perfil.setSubMercado(resumenType.getSubMercado());
                perfil.setGrupoCliente(resumenType.getGrupoCliente());
                perfil.setCategoriaCliente(resumenType.getCategoriaCliente());

                UsuarioSeleccionadoBean usuarioSeleccionado = new UsuarioSeleccionadoBean();
                usuarioSeleccionado.setNombreUsuario(resumenType
                        .getNombreUsuario());
                usuarioSeleccionado.setNumeroPcs(resumenType.getNumeroPcs());
                usuarioSeleccionado.setRut(RutBean.parseRut(resumenType
                        .getRutUsuario()));
                usuarioSeleccionado.setSubMercado(resumenType.getSubMercado());

                perfil.setUsuarioSeleccionado(usuarioSeleccionado);

                return perfil;

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "consultarPerfilamiento", e);
                throw new DAOException(e);
            }

        }
        else {
            LOGGER.error("consultarPerfilamiento: Service " +
            		"error code received: " + codigoRespuesta + 
            		" - " + descripcionRespuesta);
            throw new ServiceException(codigoRespuesta, descripcionRespuesta);
        }

    }

    /**
     * Elimina el prefijo "569" de estar presente en el msisdn
     * 
     * @param msisdn
     *            String con numero a normalizar
     * @return String msisdn sin prefijo "569" de estar presente
     */
    private String normalizarMsisdn(String msisdn) {

        if (msisdn.length() > 8 && msisdn.startsWith(MSISDN_PREFIX)) {
            return msisdn.substring(MSISDN_PREFIX.length());
        }
        else {
            return msisdn;
        }
        
    }
}
