/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.prodfactura.dao;

import org.apache.log4j.Logger;

import com.epcs.bean.FacturacionElectronicaBean;
import com.epcs.billing.prodfactura.BillingProdFacturaService;
import com.epcs.billing.prodfactura.BillingProdFacturaService_Service;
import com.epcs.billing.prodfactura.types.CancelarServicioFacturacionElectronicaType;
import com.epcs.billing.prodfactura.types.ConsultarFacturaElectronicaMPTType;
import com.epcs.billing.prodfactura.types.ConsultarFacturacionElectronicaType;
import com.epcs.billing.prodfactura.types.DetalleFacturacionElectronicaType;
import com.epcs.billing.prodfactura.types.InscribirServicioFacturacionElectronicaType;
import com.epcs.billing.prodfactura.types.ResultadoCancelarServicioFacturacionElectronicaType;
import com.epcs.billing.prodfactura.types.ResultadoConsultarFacturaElectronicaMPTType;
import com.epcs.billing.prodfactura.types.ResultadoConsultarFacturacionElectronicaType;
import com.epcs.billing.prodfactura.types.ResultadoInscribirServicioFacturacionElectronicaType;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * DAO para Factura Electronica
 * 
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class FacturacionElectronicaDAO {

    private static final Logger LOGGER = Logger.getLogger(FacturacionElectronicaDAO.class);

    private static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");

    private static final String CODIGO_YA_EXISTE = MiEntelProperties
            .getProperty("facturacionElectronica.existe");

    private static final String ID_SISTEMA_FACTURACION_ELECTRONICA = MiEntelProperties
            .getProperty("facturacionElectronica.idsistema");

    private static final String TIPO_INSCRIPCION = MiEntelProperties
            .getProperty("facturacionElectronica.tipoOperacion.inscripcion");

    private static final String TIPO_ACTUALIZACION = MiEntelProperties
            .getProperty("facturacionElectronica.tipoOperacion.actualizacion");


    /**
     * 
     * @param idSistema
     * @param rut
     * @param nroCuenta
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public FacturacionElectronicaBean getFacturacionElectronica(String rut,
            String nroCuenta) throws DAOException, ServiceException {

        FacturacionElectronicaBean factElectronica = new FacturacionElectronicaBean();

        BillingProdFacturaService port = null;
        LOGGER.info("Instanciando el port");
        try {
            port = (BillingProdFacturaService) WebServiceLocator.getInstance()
                    .getPort(BillingProdFacturaService_Service.class,
                            BillingProdFacturaService.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + BillingProdFacturaService.class, e);
            LOGGER.error( new DAOException(e));
        }

        ConsultarFacturacionElectronicaType request = new ConsultarFacturacionElectronicaType();
        ResultadoConsultarFacturacionElectronicaType resp = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setIdSistema(ID_SISTEMA_FACTURACION_ELECTRONICA);
            request.setNroCuenta(nroCuenta);
            request.setRut(rut);

            LOGGER.info("Invocando servicio");
            resp = port.consultarFacturacionElectronica(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = resp.getRespuesta().getCodigo();
        String descripcionRespuesta = resp.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            
            try {
                
                DetalleFacturacionElectronicaType detalle = resp
                        .getDetalleFacturacionElectronica();
                factElectronica.setMsisdn(detalle.getMsisdn());
                factElectronica.setCodigoGrupoCliente(detalle
                        .getCodigoGrupoCliente());
                factElectronica.setCorreoFactura(detalle.getCorreoFactura());
                factElectronica.setEstadoFE(detalle.getEstadoFE());
                factElectronica.setFechaHoraInscripcion(detalle
                        .getFechaHoraInscripcion());
                factElectronica.setGrupoCliente(detalle.getCodigoGrupoCliente());
                factElectronica.setNroCuenta(detalle.getNroCuenta());
                factElectronica.setParametro10(detalle.getParametro10());
                factElectronica.setParametro13(detalle.getParametro13());
                factElectronica.setParametro6(detalle.getParametro6());
                factElectronica.setParametro7(detalle.getParametro7());
                factElectronica.setParametro9(detalle.getParametro9());
                factElectronica.setRut(detalle.getRut());
                
            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response", e);
                LOGGER.error( new DAOException(e));
            }
        }
        else {
            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }

        return factElectronica;

    }
    
    /**
     * 
     * @param rut
     * @param nroCuenta
     * @param email
     * @param msisdn
     * @param tipoOperacion
     * @throws DAOException
     * @throws ServiceException
     */
    private void servicioFacturacionElectronica(String rut, String nroCuenta,
            String email, String msisdn, String tipoOperacion)
            throws DAOException, ServiceException {

        try {

            BillingProdFacturaService port;
            LOGGER.info("Instanciando el port");

            port = (BillingProdFacturaService) WebServiceLocator.getInstance()
                    .getPort(BillingProdFacturaService_Service.class,
                            BillingProdFacturaService.class);

            InscribirServicioFacturacionElectronicaType insc = new InscribirServicioFacturacionElectronicaType();
            insc.setEmail(email);
            insc.setIdSistema(ID_SISTEMA_FACTURACION_ELECTRONICA);
            insc.setMsisdn(msisdn);
            insc.setNroCuenta(nroCuenta);
            insc.setRut(rut);
            insc.setTipoOperacion(tipoOperacion);

            ResultadoInscribirServicioFacturacionElectronicaType result = port
                    .inscribirServicioFacturacionElectronica(insc);

            String codigoRespuesta = result.getRespuesta().getCodigo();
            String descripcionRespuesta = result.getRespuesta()
                    .getDescripcion();

            LOGGER.info("codigoRespuesta  " + codigoRespuesta);
            LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

            if (CODIGO_YA_EXISTE.equals(codigoRespuesta)
                    && TIPO_INSCRIPCION.equals(tipoOperacion)) {
                servicioFacturacionElectronica(rut, nroCuenta, email, msisdn,
                        TIPO_ACTUALIZACION);
            }
            else if (!CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
                LOGGER.error("Service error code received: " + codigoRespuesta
                        + " - " + descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,
                        descripcionRespuesta));
            }

        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + BillingProdFacturaService.class, e);
            LOGGER.error( new DAOException(e));
        }

    }
    
    /**
     * 
     * @param rut
     * @param nroCuenta
     * @param email
     * @param msisdn
     * @throws DAOException
     * @throws ServiceException
     */
    public void inscribirServicioFacturacionElectronica(String rut,
            String nroCuenta, String email, String msisdn) throws DAOException,
            ServiceException {
        servicioFacturacionElectronica(rut, nroCuenta, email, msisdn,
                TIPO_INSCRIPCION);
    }
    
    /**
     * 
     * @param rut
     * @param nroCuenta
     * @param email
     * @param msisdn
     * @throws DAOException
     * @throws ServiceException
     */
    public void actualizarServicioFacturacionElectronica(String rut,
            String nroCuenta, String email, String msisdn) throws DAOException,
            ServiceException {
        servicioFacturacionElectronica(rut, nroCuenta, email, msisdn,
                TIPO_ACTUALIZACION);
    }
    
    /**
     * 
     * @param idSistema
     * @param rut
     * @param nroCuenta
     * @param codigoMotivo
     * @throws DAOException
     * @throws ServiceException
     */
    public void cancelarServicioFacturacionElectronica(String rut,
            String nroCuenta, String codigoMotivo) throws DAOException,
            ServiceException {

        try {

            BillingProdFacturaService port;
            LOGGER.info("Instanciando el port");

            port = (BillingProdFacturaService) WebServiceLocator.getInstance()
                    .getPort(BillingProdFacturaService_Service.class,
                            BillingProdFacturaService.class);

            CancelarServicioFacturacionElectronicaType request = new CancelarServicioFacturacionElectronicaType();
            request.setCodigoMotivo(codigoMotivo);
            request.setIdSistema(ID_SISTEMA_FACTURACION_ELECTRONICA);
            request.setNroCuenta(nroCuenta);
            request.setRut(rut);

            ResultadoCancelarServicioFacturacionElectronicaType result = port
                    .cancelarServicioFacturacionElectronica(request);

            String codigoRespuesta = result.getRespuesta().getCodigo();
            String descripcionRespuesta = result.getRespuesta()
                    .getDescripcion();

            LOGGER.info("codigoRespuesta  " + codigoRespuesta);
            LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

            if (!CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
                LOGGER.error("Service error code received: " + codigoRespuesta
                        + " - " + descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,
                        descripcionRespuesta));
            }

        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + BillingProdFacturaService.class, e);
            LOGGER.error( new DAOException(e));
        }

    }
    
    public String consultarFacturaElectronica(String rut, String nroCuenta, String mercado)
            throws DAOException, ServiceException {

        BillingProdFacturaService port;
        LOGGER.info("Instanciando el port " + BillingProdFacturaService.class);
        try {
            port = (BillingProdFacturaService) WebServiceLocator.getInstance()
                    .getPort(BillingProdFacturaService_Service.class,
                            BillingProdFacturaService.class);

            ConsultarFacturaElectronicaMPTType request = new ConsultarFacturaElectronicaMPTType();
            ResultadoConsultarFacturaElectronicaMPTType response = null;

            try {

                LOGGER.info("Configurando Datos de la peticion");
                request.setMercado(mercado);
                request.setRut(rut);
                request.setNroCuenta(nroCuenta);
                request.setIdSistema(ID_SISTEMA_FACTURACION_ELECTRONICA);

                LOGGER.info("Invocando servicio");
                response = port.consultarFacturaElectronicaMPT(request);

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service invocation: "
                        + "consultarFacturaElectronicaMPT", e);
                LOGGER.error( new DAOException(e));
            }
            
            String codigoRespuesta = response.getRespuesta().getCodigo();
            String descripcionRespuesta = response.getRespuesta()
                    .getDescripcion();

            LOGGER.info("codigoRespuesta " + codigoRespuesta);
            LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

            if (Utils.isEmptyString(codigoRespuesta)) {
                LOGGER.error( new DAOException(
                        "consultarFacturaElectronicaMPT: Servicio no responde "
                                + "con codigoRespuesta"));
            }

            if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
                try {
                    return response.getFacturaElectronicaMPT().getEstadoFE();
                } catch (Exception e) {
                    LOGGER.error("Exception caught on Service response: "
                            + "consultarFacturaElectronicaMPT", e);
                    LOGGER.error( new DAOException(e));
                }
            }
            else {
                LOGGER.error("consultarFacturaElectronicaMPT: Service error code received: "
                                + codigoRespuesta
                                + " - "
                                + descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,
                        descripcionRespuesta));
            }
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + BillingProdFacturaService.class, e);
            LOGGER.error( new DAOException(e));
        }
        return "";
    }

	public boolean getFacturacionElectronicaEstado(String rutTitular,
			String nroCuenta) throws DAOException {
		boolean factElectronica=false;

        BillingProdFacturaService port = null;
        LOGGER.info("Instanciando el port");
        try {
            port = (BillingProdFacturaService) WebServiceLocator.getInstance()
                    .getPort(BillingProdFacturaService_Service.class,
                            BillingProdFacturaService.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + BillingProdFacturaService.class, e);
            LOGGER.error( new DAOException(e));
        }

        ConsultarFacturacionElectronicaType request = new ConsultarFacturacionElectronicaType();
        ResultadoConsultarFacturacionElectronicaType resp = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setIdSistema(ID_SISTEMA_FACTURACION_ELECTRONICA);
            request.setNroCuenta(nroCuenta);
            request.setRut(rutTitular);

            LOGGER.info("Invocando servicio");
            resp = port.consultarFacturacionElectronica(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = resp.getRespuesta().getCodigo();
        String descripcionRespuesta = resp.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            
            factElectronica=true;
        }
        else {
        	factElectronica=false;
        }

		return factElectronica;
	}    
}

    