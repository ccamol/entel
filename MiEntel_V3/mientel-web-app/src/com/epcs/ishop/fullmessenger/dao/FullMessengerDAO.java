/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.ishop.fullmessenger.dao;

import org.apache.log4j.Logger;

import com.epcs.cliente.perfil.ClientePerfilService;
import com.epcs.cliente.perfil.ClientePerfilServicePortType;
import com.epcs.cliente.perfil.types.ConsultarClaveType;
import com.epcs.cliente.perfil.types.ResultadoConsultarClaveType;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class FullMessengerDAO {
    

    /**
     * Logger para FullMessengerDAO
     */
    private static final Logger LOGGER = Logger
            .getLogger(FullMessengerDAO.class);
    
    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
    .getProperty("servicios.codigoRespuesta.exito");
    
    
    /**
     * Obtiene la clave de un usuario empleando su numero movil
     * @param numeroPcs numero del movil del usuario
     * @return claveUsuario <code>String</code> clave del usuario con el movil <code>numeroPcs</code>
     */
    public String obtenerClaveByNumeroPcs(String numeroPcs) throws DAOException, ServiceException{
        

        ClientePerfilServicePortType port = null;
        LOGGER.info("Instanciando el port " + ClientePerfilServicePortType.class);
        try {
            port = (ClientePerfilServicePortType) WebServiceLocator.getInstance().getPort(
                    ClientePerfilService.class, ClientePerfilServicePortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + ClientePerfilServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }

        
        ConsultarClaveType request = new ConsultarClaveType();
        ResultadoConsultarClaveType response = null;
        String claveEntel = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setMsisdn(numeroPcs);
            

            LOGGER.info("Invocando servicio");
            response = port.consultarClave(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: consultarClave",
                    e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException("consultarClave: Servicio no responde "
                    + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {

                claveEntel = response.getClave();
                if (Utils.isEmptyString(claveEntel)) {
                    LOGGER.error( new DAOException("consultarClave: El servicio no devolvio una clave valida "
                            + "para movil "+numeroPcs));
                }

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "consultarClave", e);
                LOGGER.error( new DAOException(e));
            }

        }
        else {
            LOGGER.error("consultarClave: Service error code received: "
                    + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        
        return claveEntel;

    }

}
