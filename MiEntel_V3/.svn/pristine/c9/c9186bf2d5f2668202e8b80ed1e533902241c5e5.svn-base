/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.orden.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.bean.BolsaBean;
import com.epcs.bean.BolsaPPBean;
import com.epcs.bean.ResultadoContratarBolsaBean;
import com.epcs.bean.SimultaneidadBolsasBean;
import com.epcs.bean.ValidacionCompraBolsaRegaloBean;
import com.epcs.cliente.orden.ClienteOrdenService;
import com.epcs.cliente.orden.ClienteOrdenService_Service;
import com.epcs.cliente.orden.types.BolsaPPType;
import com.epcs.cliente.orden.types.ComprarBolsaBAMPPType;
import com.epcs.cliente.orden.types.ComprarBolsasOnShootPPType;
import com.epcs.cliente.orden.types.ComprarBolsasOneShootSSCCType;
import com.epcs.cliente.orden.types.ContrataBolsaBBerrySSCCType;
import com.epcs.cliente.orden.types.ContrataBolsaSSCCType;
import com.epcs.cliente.orden.types.GrupoBolsasType;
import com.epcs.cliente.orden.types.ItemBolsasRegaladasSSType;
import com.epcs.cliente.orden.types.ObtenerBolsasOneShootType;
import com.epcs.cliente.orden.types.ObtenerBolsasRegaladasSSType;
import com.epcs.cliente.orden.types.ResultadoComprarBolsaBAMPPType;
import com.epcs.cliente.orden.types.ResultadoComprarBolsasOnShootPPType;
import com.epcs.cliente.orden.types.ResultadoComprarBolsasOneShootSSCCType;
import com.epcs.cliente.orden.types.ResultadoContrataBolsaBBerrySSCCType;
import com.epcs.cliente.orden.types.ResultadoContrataBolsaSSCCType;
import com.epcs.cliente.orden.types.ResultadoObtenerBolsasOneShootType;
import com.epcs.cliente.orden.types.ResultadoObtenerBolsasRegaladasSSType;
import com.epcs.cliente.orden.types.ResultadoValidaCompraBolsaOneShootSSCCType;
import com.epcs.cliente.orden.types.ResultadoValidarSimultaneidadBolsasCCType;
import com.epcs.cliente.orden.types.ValidaCompraBolsaOneShootSSCCType;
import com.epcs.cliente.orden.types.ValidarSimultaneidadBolsasCCItemType;
import com.epcs.cliente.orden.types.ValidarSimultaneidadBolsasCCType;
import com.epcs.cliente.perfil.consultabolsa.ConsultaBolsaServiceBindingQSService;
import com.epcs.cliente.perfil.consultabolsa.ConsultaBolsaServicePortType;
import com.epcs.cliente.perfil.consultabolsa.types.ConsultaBolsaRequestDocumentType;
import com.epcs.cliente.perfil.consultabolsa.types.ConsultaBolsaResponseDocumentType;
import com.epcs.provision.suscripcion.bolsaspp.ComprarServiceFaultMessage;
import com.epcs.provision.suscripcion.bolsaspp.ListarBolsasActivasServiceFaultMessage;
import com.epcs.provision.suscripcion.bolsaspp.SCOBPortType;
import com.epcs.provision.suscripcion.bolsaspp.SCOBPortTypeSOAPBindingQSService;
import com.epcs.provision.suscripcion.bolsaspp.types.ListarBolsasActivasResponseType;
import com.epcs.provision.suscripcion.bolsaspp.types.ComprarRequestType;
import com.epcs.provision.suscripcion.bolsaspp.types.ComprarResponseType;
import com.epcs.provision.suscripcion.bolsaspp.types.ServiceFaultType;
import com.epcs.provision.suscripcion.bolsaspp.types.ListarBolsasActivasRequestType.Mensaje;
import com.epcs.provision.suscripcion.bolsaspp.types.ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas.DetalleBono;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import java.io.Serializable;
import com.epcs.provision.suscripcion.bolsaspp.types.ListarBolsasActivasResponseType.Mensaje.Movil;
/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class BolsaDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(BolsaDAO.class);

    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");  
    
    public static final String CANAL_SCOB = MiEntelProperties
    .getProperty("parametros.bolsasSCOBPP.canal");
    
    
    public static final String PREFIJO = MiEntelProperties
    .getProperty("prefijo.entel");    
    
    public static final String CODIGO_RESPUESTA_OK_COMPRA_SCOB = MiEntelProperties
    .getProperty("servicios.codigoRespuesta.exitoScob");
    
    public static final String CODIGO_RESPUESTA_NO_SALDO_SCOB = MiEntelProperties
    .getProperty("servicios.codigoRespuesta.noSaldoScob");

    public static final String CODIGO_UNIDAD_KB = MiEntelProperties
    .getProperty("parametros.bolsasSCOBPP.unidadKB");
    
    public static final String CODIGO_UNIDAD_MB = MiEntelProperties
    .getProperty("parametros.bolsasSCOBPP.unidadMB");
    
    public static final String CODIGO_UNIDAD_IM = MiEntelProperties
    .getProperty("parametros.producto.internetMovil.id");
    
    public static final String CODIGO_UNIDAD_BA = MiEntelProperties
    .getProperty("parametros.producto.bam.id");
    
    public static final String CODIGO_UNIDAD_BB = MiEntelProperties
    .getProperty("parametros.producto.blackberry.id");
    
    public static final String CANAL_SCOB_BAM = MiEntelProperties
    .getProperty("parametros.bolsasBAMCCPP.canal");    


    /**
     * Listado de Bolsas Regaladas
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<BolsaBean> consultarBolsasRegaladasSS(final String msisdn)
            throws DAOException, ServiceException {

        ClienteOrdenService port = null;
        List<BolsaBean> listBolsasRegaladasBean = new ArrayList<BolsaBean>();

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

        LOGGER.info("Configurando Datos de la peticion");
        ObtenerBolsasRegaladasSSType request = new ObtenerBolsasRegaladasSSType();
        ResultadoObtenerBolsasRegaladasSSType response = null;

        LOGGER.info("Invocando servicio");
        try {

            request.setMsisdn(msisdn);
            response = port.obtenerBolsasRegaladasSS(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            List<ItemBolsasRegaladasSSType> listBolsasRegaladas = response
                    .getBolsasRegaladasSS().getBolsasRegaladasSSType();

            // Construir Lista de Bolsas Actuales
            for (ItemBolsasRegaladasSSType bolsaType : listBolsasRegaladas) {
                BolsaBean bolsaBean = new BolsaBean();
                bolsaBean.setNombreBolsa(bolsaType.getNombreBolsa());
                bolsaBean.setSnCodigo(bolsaType.getMovilRegalado());
                bolsaBean.setSpCodigo(bolsaType.getIdBolsa());
                bolsaBean.setDescBolsa(bolsaType.getDescBolsa()
                        .getItemDescBolsa());
                bolsaBean.setTipoBolsa(bolsaType.getTipoBolsa());
                bolsaBean.setVigencia(DateHelper.format(DateHelper.parseDate(
                        bolsaType.getFechaCompraBolsa(), "dd/MM/yy"),
                        DateHelper.FORMAT_ddMMyyyy_SLASH));
                try {
                    bolsaBean.setCosto(Double.parseDouble(bolsaType
                            .getValorBolsaSinIva()));
                } catch (NumberFormatException nfe) {
                    LOGGER.error("Valor de Bolsa Regalada no valido."
                            + nfe.getMessage());
                    bolsaBean.setCosto(new Double(0));
                }
                listBolsasRegaladasBean.add(bolsaBean);
            }
        }
        else {

            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));

        }

        return listBolsasRegaladasBean;
    }

    /**
     * Contratar una Bolsa para un usuario SS o CC
     * 
     * @param msisdn
     * @param codBolsa
     * @param valorBolsa
     * @param opcionActivacion
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoContratarBolsaBean contratarBolsaSSCC(final String msisdn,
            final String codBolsa, final double valorBolsa,
            final String opcionActivacion) throws DAOException,
            ServiceException {

        ResultadoContratarBolsaBean resultadoContratarBolsaBean = null;
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

        LOGGER.info("Configurando Datos de la peticion");
        ContrataBolsaSSCCType request = new ContrataBolsaSSCCType();
        ResultadoContrataBolsaSSCCType response = null;

        LOGGER.info("Invocando servicio");
        try {

            request.setMsisdn(msisdn);
            request.setCodBolsa(codBolsa);
            request.setOpcionActivacionBolsa(opcionActivacion);
            request.setValorBolsa(String.valueOf(valorBolsa));
            
            response = port.contrataBolsaSSCC(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            resultadoContratarBolsaBean = new ResultadoContratarBolsaBean();
            resultadoContratarBolsaBean.setCantidad(response.getCantidad());
            resultadoContratarBolsaBean.setTipoActivacion(response
                    .getTipoActivacion());
        }
        else {

            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));

        }

        return resultadoContratarBolsaBean;

    }

    /**
     * Contrata una bolsa bby mercado ss o cc
     * 
     * @param msisdn
     * @param codBolsa
     * @throws DAOException
     * @throws ServiceException
     */
    public void contratarBolsaBBerrySSCC(final String msisdn, final String codBolsa)
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

        LOGGER.info("Configurando Datos de la peticion");
        ContrataBolsaBBerrySSCCType request = new ContrataBolsaBBerrySSCCType();
        ResultadoContrataBolsaBBerrySSCCType response = null;

        LOGGER.info("Invocando servicio");
        try {

            request.setMsisdn(msisdn);
            request.setCodBolsa(codBolsa);

            response = port.contrataBolsaBBerrySSCC(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            LOGGER.info("Bolsa BBY Contratada. : ".concat(codBolsa).concat(",msisdn").concat(msisdn));
        }
        else {

            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));

        }
    }

    /**
     * Determina si el usuario ya tiene esa bolsa
     * 
     * @param msisdn
     * @param codBolsa
     * @return SimultaneidadBolsasBean
     * @throws DAOException
     * @throws ServiceException
     */
    public SimultaneidadBolsasBean validarSimultaneidadBolsaCC(final String msisdn,
            final String codBolsa) throws DAOException, ServiceException {

        ClienteOrdenService port = null;
        SimultaneidadBolsasBean simultaneidadBolsasBean = new SimultaneidadBolsasBean();
        List<BolsaBean> bolsasDuplicadas = new ArrayList<BolsaBean>();
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

        LOGGER.info("Configurando Datos de la peticion");
        ValidarSimultaneidadBolsasCCType request = new ValidarSimultaneidadBolsasCCType();
        
        ResultadoValidarSimultaneidadBolsasCCType response = null;

        LOGGER.info("Invocando servicio");
        try {
            request.setMsisdn(msisdn);
            request.setCodBolsa(codBolsa);

            response = port.validarSimultaneidadBolsasCC(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            simultaneidadBolsasBean.setFlagDuplicidad(!response
                    .getFlagDuplicidad().equals("0"));
            List<ValidarSimultaneidadBolsasCCItemType> listDupl = response
                    .getValidarSimultaneidadBolsasCC()
                    .getValidarSimultaneidadBolsasCCType();
            for (ValidarSimultaneidadBolsasCCItemType itemBolsa : listDupl) {
                BolsaBean bolsaBean = new BolsaBean();
                bolsaBean.setSnCodigo(itemBolsa.getSnCodigo());
                bolsaBean.setTipoBolsa(itemBolsa.getTipoBolsa());
                bolsaBean.setNombreBolsa(itemBolsa.getDescBolsa());
                bolsaBean.setFlagPromocion(itemBolsa.getGrupoRespuesta());
                bolsaBean.setSpCodigo(itemBolsa.getNumeroCuenta());
                bolsaBean.setObservacion(itemBolsa.getRut());
                bolsaBean.setTipoVigencia(itemBolsa.getSnCodigo());
                bolsasDuplicadas.add(bolsaBean);
            }
            simultaneidadBolsasBean.setBolsasDuplicadas(bolsasDuplicadas);
        }
        else {

            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));

        }
        return simultaneidadBolsasBean;
    }

    /**
     * Dtermina si se puede realizar la compra de la bolsa.
     * 
     * @param msisdnFrom
     * @param msisdnTo
     * @param tipoMercado
     * @param codBolsa
     * @param valorBolsa
     * @return ValidacionCompraBolsaRegaloBean
     * @throws DAOException
     * @throws ServiceException
     */
    public ValidacionCompraBolsaRegaloBean validarCompraBolsaBolsaRegaloSSCC(
            final String msisdnFrom, final String msisdnTo, final String tipoMercado,
            final String codBolsa, final double valorBolsa) throws DAOException,
            ServiceException {

        ClienteOrdenService port = null;
        ValidacionCompraBolsaRegaloBean validacionCompraBolsaRegaloBean = null;
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

        LOGGER.info("Configurando Datos de la peticion");
        ValidaCompraBolsaOneShootSSCCType request = new ValidaCompraBolsaOneShootSSCCType();
        ResultadoValidaCompraBolsaOneShootSSCCType response = null;

        LOGGER.info("Invocando servicio");
        try {

            request.setMsisdn(msisdnFrom);
            request.setMsisdnRegalo(msisdnTo);
            request.setFlagMercado(tipoMercado);
            request.setValorBolsa(String.valueOf(valorBolsa));
            request.setCodBolsa(codBolsa);
            
            response = port.validaCompraBolsaOneShootSSCC(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            validacionCompraBolsaRegaloBean = new ValidacionCompraBolsaRegaloBean();
            validacionCompraBolsaRegaloBean.setComando(response.getComando());
            validacionCompraBolsaRegaloBean.setMsisdnRegalo(response
                    .getMsisdnRegalo());
        }
        else {

            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));

        }
        return validacionCompraBolsaRegaloBean;
    }

    /**
     * Regala una bolsa de un usuario ss.
     * 
     * @param msisdnFrom
     * @param msisdnTo
     * @param tipoMercado
     * @param codBolsa
     * @param valorBolsa
     * @throws DAOException
     * @throws ServiceException
     */
    public void comprarBolsaRegaloSSCC(final String msisdnFrom, final String msisdnTo,
            final String tipoMercado, final String codBolsa, final double valorBolsa)
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

        LOGGER.info("Configurando Datos de la peticion");
        ComprarBolsasOneShootSSCCType request = new ComprarBolsasOneShootSSCCType();

        ResultadoComprarBolsasOneShootSSCCType response = null;

        LOGGER.info("Invocando servicio");
        try {
            request.setMsisdn(msisdnFrom);
            request.setMsisdnRegalo(msisdnTo);
            request.setTipoMercado(tipoMercado);
            request.setValorBolsa(String.valueOf(valorBolsa));
            request.setCodBolsa(codBolsa);

            response = port.comprarBolsasOneShootSSCC(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            LOGGER.info("Bolsa Regalada. : ".concat(codBolsa).concat("De:").concat(msisdnFrom).concat("Para:").concat(msisdnTo));
        }
        else {

            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));

        }

    }

    /**
     * Obtiene bolsas compradas y regaladas
     * 
     * @param msisdn
     * @return List<BolsaBean>
     * @throws DAOException
     * @throws ServiceException
     */
    public List<BolsaPPBean> obtenerBolsasOneShot(final String msisdn)
            throws DAOException, ServiceException {

        ClienteOrdenService port = null;
        List<BolsaPPBean> listBolsasCompradasBean = new ArrayList<BolsaPPBean>();

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

        LOGGER.info("Configurando Datos de la peticion");
        ObtenerBolsasOneShootType request = new ObtenerBolsasOneShootType();

        ResultadoObtenerBolsasOneShootType response = null;

        LOGGER.info("Invocando servicio");
        try {
            request.setMsisdn(msisdn);

            response = port.obtenerBolsasOneShoot(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            List<GrupoBolsasType> listGrupoBolsas = response
                    .getBolsasOneShoot().getGrupoBolsas().getGrupoBolsaItem();

            // Construir Lista de Bolsas Compradas
            for (GrupoBolsasType grupoType : listGrupoBolsas) {
                List<BolsaPPType> listBolsaPPType = grupoType
                        .getBolsasCompradasPP().getBolsaCompradaPP();
                BolsaPPBean bolsaBean;
                for (BolsaPPType bolsaType : listBolsaPPType) {
                    bolsaBean = new BolsaPPBean();
                    bolsaBean.setCodBolsa(bolsaType.getCodBolsa());
                    bolsaBean.setInstanciaBolsa(bolsaType.getInstanciaBolsa());
                    bolsaBean.setNombreBolsa(bolsaType.getNombreBolsa());
                    bolsaBean.setDescBolsa(bolsaType.getDescBolsa()
                            .getItemDescBolsa());
                    bolsaBean.setTipoBolsa(bolsaType.getTipoBolsa());
                    bolsaBean.setFechaExpiracion(DateHelper.parseDate(bolsaType
                            .getFechaExpiracion(), DateHelper.FORMAT_ddMMyyyy_HHmmss_SLASH));
                    bolsaBean.setCantidadBolsa(bolsaType.getCantidadBolsa());
                    
                    try{
                        bolsaBean.setUnidad(MiEntelProperties
                        .getProperty("parametros.tipobolsa.unidad."+bolsaType.getTipoBolsa()));
                    }catch (Exception e) {
                        LOGGER.warn("Propiedad no encontrada : parametros.tipobolsa.unidad."+bolsaType.getTipoBolsa());
                    }
                    
                    try {
                        bolsaBean.setValorBolsa(Double.parseDouble(bolsaType
                                .getValorBolsa()));
                    } catch (NumberFormatException nfe) {
                        LOGGER.warn("Valor de Bolsa Comprada no valido :"+bolsaType.getCodBolsa()
                                + nfe.getMessage());
                    }
                    
                    try {
                    	
                    	 bolsaBean.setSaldo(Double.parseDouble(bolsaType.getSaldo()));
                    	
                        // Internet Movil
                        if (CODIGO_UNIDAD_IM.equals(bolsaBean.getTipoBolsa())) {
                        	bolsaBean.setSaldoString(JsfUtil.getAsConvertedString(bolsaType.getSaldo(), "traficoDatosConverterKBMB"));
                        	bolsaBean.setUnidad(CODIGO_UNIDAD_MB);
                        }

                        // BAM
                        if (CODIGO_UNIDAD_BA.equals(bolsaBean.getTipoBolsa())) {
                        	bolsaBean.setSaldoString(JsfUtil.getAsConvertedString(bolsaType.getSaldo(), "traficoDatosConverterKBMB"));
                        	bolsaBean.setUnidad(CODIGO_UNIDAD_MB);
                        }

                        // Blackberry
                        if (CODIGO_UNIDAD_BB.equals(bolsaBean.getTipoBolsa())) {
                        	bolsaBean.setSaldoString(JsfUtil.getAsConvertedString(bolsaType.getSaldo(), "traficoDatosConverterKBMB"));
                        	bolsaBean.setUnidad(CODIGO_UNIDAD_MB);
                        }
                        
                        
                        try{
	                        if(bolsaBean.getUnidad()==null){                        
	                        	
	                        		if(bolsaBean.getNombreBolsa().indexOf(CODIGO_UNIDAD_MB) != -1){
	                        			bolsaBean.setSaldoString(JsfUtil.getAsConvertedString(bolsaType.getSaldo(), "traficoDatosConverterKBMB"));
	                                	bolsaBean.setUnidad(CODIGO_UNIDAD_MB);                        			
	                        		}	                        	
	                        }
                        } catch (Exception e) {
                            LOGGER.error("Exception al buscar el tipo de bolsa para : "+bolsaBean.getNombreBolsa(), e);           
                        }
                    	                                              
                    	                        
                    } catch (NumberFormatException nfe) {
                        LOGGER.warn("Saldo de Bolsa Comprada no valido :"+bolsaType.getCodBolsa()
                                + nfe.getMessage());
                    }
                    listBolsasCompradasBean.add(bolsaBean);
                }
            }
        }
        else {

            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return listBolsasCompradasBean;
    }

    /**
     * Comprar una bolsa para un usuario pp 
     * 
     * @param msisdn
     * @param cartaServicio
     * @param valorBolsa
     * @throws DAOException
     * @throws ServiceException
     */
    public void comprarBolsasOneShotPP(final String msisdn, final String cartaServicio,
            final double valorBolsa) throws DAOException, ServiceException {

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

        LOGGER.info("Configurando Datos de la peticion");
        ComprarBolsasOnShootPPType request = new ComprarBolsasOnShootPPType();
        ResultadoComprarBolsasOnShootPPType response = null;

        LOGGER.info("Invocando servicio");
        try {
            request.setMsisdn(msisdn);
            request.setCartaServicio(cartaServicio);
            request.setValorBolsa(String.valueOf(valorBolsa));
            
            response = port.comprarBolsasOnShootPP(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            LOGGER.info("Bolsa Comprada. : ".concat(cartaServicio).concat(",msisdn").concat(msisdn));
        }
        else {

            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }

    }
    
    
    /**
     * Comprar una bolsa para un usuario pp BAM
     * 
     * @param msisdn
     * @param cartaServicio
     * @param valorBolsa
     * @throws DAOException
     * @throws ServiceException
     */
    public void comprarBolsasPPBAM(final String msisdn, final String cartaServicio) throws DAOException, ServiceException {

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

        LOGGER.info("Configurando Datos de la peticion");
        ComprarBolsaBAMPPType request = new ComprarBolsaBAMPPType();
        ResultadoComprarBolsaBAMPPType response = null;

        LOGGER.info("Invocando servicio");
        try {
            request.setMsisdn(msisdn);
            request.setCodBolsa(cartaServicio);

            
            response = port.comprarBolsaBAMPP(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            LOGGER.info("Bolsa Comprada. : ".concat(cartaServicio).concat(",msisdn").concat(msisdn));
        }
        else {

            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }

    }
    
    /**
     * Obtiene bolsas servicio scob
     * 
     * @param msisdn
     * @return List<BolsaBean>
     * @throws DAOException
     * @throws ServiceException
     */
    public List<BolsaPPBean> obtenerBolsasScob(final String msisdn)
            throws DAOException, ServiceException {
    	
        List<BolsaPPBean> listBolsasCompradasBean = new ArrayList<BolsaPPBean>();
        SCOBPortType port = null;
        LOGGER.info("Instanciando el port " + SCOBPortType.class);
    
        try {
            port = (SCOBPortType) WebServiceLocator.getInstance().getPort(
                    SCOBPortTypeSOAPBindingQSService.class, SCOBPortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + SCOBPortType.class, e);
            LOGGER.error( new DAOException(e));
        } 
        LOGGER.info("Configurando Datos de la peticion");            
        Mensaje request = new Mensaje();
        ListarBolsasActivasResponseType.Mensaje response = null;       

        LOGGER.info("Invocando servicio");
        try {
            request.setMsisdn(PREFIJO+msisdn);
            request.setCanal(CANAL_SCOB);

            response = port.listarBolsasActivasRequestDocument(request);

        } catch (ListarBolsasActivasServiceFaultMessage e) {
            ServiceFaultType mensaje = e.getFaultInfo().getMensaje();
            String codigoRespuesta = mensaje.getCodigo();
            String descripcionRespuesta = mensaje.getDescripcion();
            
            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));                
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
                    + "listarBolsasActivasRequestDocument", e);
            LOGGER.error( new DAOException(e));
        }
        
        try {

            List<DetalleBono> listGrupoBolsas = response.getListadoCartasActivas().getDetalleBono();
            // Construir Lista de Bolsas Compradas
            for (DetalleBono grupoType : listGrupoBolsas) {               
	                   BolsaPPBean bolsaBean = new BolsaPPBean();                  
	                   bolsaBean.setNombreBolsa(grupoType.getDescripcion()); 
	                   bolsaBean.setFechaExpiracion(DateHelper.parseDate(grupoType.getTermino(), DateHelper.FORMAT_yyyyMMddhhmmss));	                   
	                   bolsaBean.setUnidad(grupoType.getUnidad());       
	                   bolsaBean.setDescSaldo(grupoType.getDescripcionSaldo());
	                   
                   try {
                	   bolsaBean.setDescSaldo(ParametrosHelper.getParseDescSaldo(bolsaBean.getDescSaldo()));                	  
                    } catch (Exception e) {
                        LOGGER.warn("Error parceando la descripcion del saldo :"+grupoType.getDescripcionSaldo()
                                + e.getMessage());
                    }  
	                  
                   try {
                	
                        bolsaBean.setSaldo(Double.parseDouble(grupoType.getSaldo()));
                   	   bolsaBean.setSaldoString(JsfUtil.getAsConvertedString(grupoType.getSaldo(), "traficoDatosConverterKBMB"));
                   	  
                   	   if(grupoType.getUnidad().equals(CODIGO_UNIDAD_KB)){
                   		 bolsaBean.setDescSaldo(bolsaBean.getSaldoString()+" "+ CODIGO_UNIDAD_MB);
                   	   }
                   	  
                   	   
                    } catch (NumberFormatException nfe) {
                        LOGGER.warn("Saldo de Bolsa Comprada no valido :"+grupoType.getSaldo()
                                + nfe.getMessage());
                    }
                    listBolsasCompradasBean.add(bolsaBean);                
            }
        }catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "listarBolsasActivasRequestDocument", e);
                LOGGER.error( new DAOException(e));
         }
        return listBolsasCompradasBean;
    }    
    
    /**
     * Obtiene bolsas servicio scob
     * 
     * @param msisdn
     * @return List<BolsaBean>
     * @throws DAOException
     * @throws ServiceException
     */
    public Movil obtenerMovil(final String msisdn)
            throws DAOException, ServiceException {
        List<BolsaPPBean> listBolsasCompradasBean = new ArrayList<BolsaPPBean>();
        SCOBPortType port = null;
        LOGGER.info("Instanciando el port " + SCOBPortType.class);
    
        try {
            port = (SCOBPortType) WebServiceLocator.getInstance().getPort(
                    SCOBPortTypeSOAPBindingQSService.class, SCOBPortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + SCOBPortType.class, e);
            LOGGER.error( new DAOException(e));
        } 
        LOGGER.info("Configurando Datos de la peticion");            
        Mensaje request = new Mensaje();
        ListarBolsasActivasResponseType.Mensaje response;       

        LOGGER.info("Invocando servicio");
        Movil movil = null;
		try {
            request.setMsisdn(PREFIJO+msisdn);
            request.setCanal(CANAL_SCOB);
            response = port.listarBolsasActivasRequestDocument(request);
            System.out.println("response.getMovil().getClass(): "+response.getMovil().getClass());
            movil = response.getMovil();
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
                    + "listarBolsasActivasRequestDocument", e);
            LOGGER.error( new DAOException(e));
        }
        return movil;
    }


    /**
     * Obtiene bolsas servicio scob
     * 
     * @param msisdn
     * @return List<BolsaBean>
     * @throws DAOException
     * @throws ServiceException
     */
    public List<BolsaPPBean> obtenerBolsasActivasBAMScob(final String msisdn)
            throws DAOException, ServiceException {
    	
        List<BolsaPPBean> listBolsasCompradasBean = new ArrayList<BolsaPPBean>();
        SCOBPortType port = null;
        LOGGER.info("Instanciando el port " + SCOBPortType.class);
    
        try {
            port = (SCOBPortType) WebServiceLocator.getInstance().getPort(
                    SCOBPortTypeSOAPBindingQSService.class, SCOBPortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + SCOBPortType.class, e);
            LOGGER.error( new DAOException(e));
        } 
        LOGGER.info("Configurando Datos de la peticion");            
        Mensaje request = new Mensaje();
        ListarBolsasActivasResponseType.Mensaje response = null;       

        LOGGER.info("Invocando servicio");
        try {
            request.setMsisdn(PREFIJO+msisdn);
            request.setCanal(CANAL_SCOB_BAM);

            response = port.listarBolsasActivasRequestDocument(request);

        } catch (ListarBolsasActivasServiceFaultMessage e) {
            ServiceFaultType mensaje = e.getFaultInfo().getMensaje();
            String codigoRespuesta = mensaje.getCodigo();
            String descripcionRespuesta = mensaje.getDescripcion();
            
            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));                
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
                    + "listarBolsasActivasRequestDocument", e);
            LOGGER.error( new DAOException(e));
        }
        
        try {
            List<DetalleBono> listGrupoBolsas = response.getListadoCartasActivas().getDetalleBono();
            BolsaPPBean bolsaBean = new BolsaPPBean();   
            bolsaBean.setFechaExpiracion(DateHelper.parseDate(response.getMovil().getFechaDesactivacion(), DateHelper.FORMAT_yyyyMMddhhmmss24HORAS));
            bolsaBean.setSaldo(Double.parseDouble(response.getMovil().getSaldo()));
            bolsaBean.setTipoBolsa("DATOSACTIVAS");
            listBolsasCompradasBean.add(bolsaBean); 
            // Construir Lista de Bolsas Compradas
            for (DetalleBono grupoType : listGrupoBolsas) {               
	                   bolsaBean = new BolsaPPBean();                  
	                   bolsaBean.setNombreBolsa(grupoType.getDescripcion()); 
	                   bolsaBean.setFechaExpiracion(DateHelper.parseDate(grupoType.getTermino(), DateHelper.FORMAT_yyyyMMddhhmmss24HORAS));	                   
	                   bolsaBean.setUnidad(grupoType.getUnidad());       
	                   bolsaBean.setDescSaldo(grupoType.getDescripcionSaldo());
	                   bolsaBean.setTipoBolsa(grupoType.getDestino());
                   try {
                	   bolsaBean.setDescSaldo(ParametrosHelper.getParseDescSaldo(bolsaBean.getDescSaldo()));                	  
                    } catch (Exception e) {
                        LOGGER.warn("Error parceando la descripcion del saldo :"+grupoType.getDescripcionSaldo()
                                + e.getMessage());
                    }  
	                  
                   try {
                        bolsaBean.setSaldo(Double.parseDouble(grupoType.getSaldo()));
                    } catch (NumberFormatException nfe) {
                        LOGGER.warn("Saldo de Bolsa Comprada no valido :"+grupoType.getSaldo()
                                + nfe.getMessage());
                    }
                    listBolsasCompradasBean.add(bolsaBean);                
            }
        }catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "listarBolsasActivasRequestDocument", e);
                LOGGER.error( new DAOException(e));
         }
        return listBolsasCompradasBean;
    } 
    
    /**
     * Comprar una bolsa scob para un usuario pp
     * 
     * @param msisdn
     * @param cartaServicio
     * @param valorBolsa
     * @throws DAOException
     * @throws ServiceException
     */
    public void comprarBolsasScobPP(final String msisdn, final String cartaServicio) throws DAOException, ServiceException {

    	SCOBPortType port = null;
        LOGGER.info("Instanciando el port " + SCOBPortType.class);
    
        try {
            port = (SCOBPortType) WebServiceLocator.getInstance().getPort(
                    SCOBPortTypeSOAPBindingQSService.class, SCOBPortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + SCOBPortType.class, e);
            LOGGER.error( new DAOException(e));
        } 

        LOGGER.info("Configurando Datos de la peticion");            
        ComprarRequestType.Mensaje request = new ComprarRequestType.Mensaje();
        ComprarResponseType.Mensaje response = null;  

        LOGGER.info("Invocando servicio");
        try {
        	request.setMsisdn(PREFIJO+msisdn);
        	request.setCodigo(cartaServicio);
            request.setCanal(CANAL_SCOB); 
            response = port.comprarRequestDocument(request);

        } catch (ComprarServiceFaultMessage e) {
            ServiceFaultType mensaje = e.getFaultInfo().getMensaje();
            String codigoRespuesta = mensaje.getCodigo();
            String descripcionRespuesta = mensaje.getDescripcion();
            
            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));                
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
                    + "comprarRequestDocument", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getCodigo();
        String descripcionRespuesta = response.getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK_COMPRA_SCOB.equals(codigoRespuesta)) {
            LOGGER.info("Bolsa Comprada. : ".concat(cartaServicio).concat(",msisdn").concat(msisdn));
        }
        else {

            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }

    }
    
    /**
     * Comprar una bolsa scob para un usuario bam pp
     * 
     * @param msisdn
     * @param cartaServicio
     * @param valorBolsa
     * @return 
     * @throws DAOException
     * @throws ServiceException
     */
    public int comprarBolsasScobBAMPP(final String msisdn, final String cartaServicio) throws DAOException, ServiceException {

    	SCOBPortType port = null;
        LOGGER.info("Instanciando el port " + SCOBPortType.class);
    
        try {
            port = (SCOBPortType) WebServiceLocator.getInstance().getPort(
                    SCOBPortTypeSOAPBindingQSService.class, SCOBPortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("BAMPP:"+msisdn+":Error al inicializar el Port "
                    + SCOBPortType.class, e);
            LOGGER.error( new DAOException(e));
        } 

        LOGGER.info("Configurando Datos de la peticion Compra Bolsa BAMPP");            
        ComprarRequestType.Mensaje request = new ComprarRequestType.Mensaje();
        ComprarResponseType.Mensaje response = null;  

        LOGGER.info("Invocando servicio BAMPP:"+msisdn+" con Carta:"+cartaServicio);
        
        try {
        	request.setMsisdn(PREFIJO+msisdn);
        	request.setCodigo(cartaServicio);
            request.setCanal(CANAL_SCOB_BAM);
            response = port.comprarRequestDocument(request);

        } catch (ComprarServiceFaultMessage e) {
            ServiceFaultType mensaje = e.getFaultInfo().getMensaje();
            String codigoRespuesta = mensaje.getCodigo();
            String descripcionRespuesta = mensaje.getDescripcion();
            
            LOGGER.error("BAMPP:"+msisdn+":Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));                
        } catch (Exception e) {
            LOGGER.error("BAMPP:"+msisdn+":Exception caught on Service invocation: "
                    + "comprarRequestDocument", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getCodigo();
        String descripcionRespuesta = response.getDescripcion();

        LOGGER.info("BAMPP:"+msisdn+":codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("BAMPP:"+msisdn+":descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK_COMPRA_SCOB.equals(codigoRespuesta)) {
            LOGGER.info("Bolsa BAMPP Comprada. : ".concat(cartaServicio).concat(",msisdn").concat(msisdn));
            return 0;
        }
        else {        	        	

            LOGGER.error("BAMPP:"+msisdn+":Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            if(CODIGO_RESPUESTA_NO_SALDO_SCOB.equals(codigoRespuesta)){
        		return -1;
        	}
            
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return -1;
    }
    
    public void ejectuarTeardown(final String msisdn) throws DAOException, ServiceException{
    	ConsultaBolsaServicePortType port = null;
        LOGGER.info("Instanciando el port " + SCOBPortType.class);
    
        try {
            port = (ConsultaBolsaServicePortType) WebServiceLocator.getInstance().getPort(
            		ConsultaBolsaServiceBindingQSService.class, ConsultaBolsaServicePortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Teardown :"+msisdn+":Error al inicializar el Port "
                    + SCOBPortType.class, e);
            LOGGER.error( new DAOException(e));
        } 
    
        LOGGER.info("Configurando Datos de la peticion Compra Bolsa BAMPP");            
        ConsultaBolsaRequestDocumentType request = new ConsultaBolsaRequestDocumentType();
        ConsultaBolsaResponseDocumentType response = null;  

        LOGGER.info("Invocando servicio teardown:"+msisdn);
        
        try {
        	request.setMsisdn(PREFIJO+msisdn);
            response = port.consultaBolsaServicePortType(request);                   
        } catch (Exception e) {
            LOGGER.error("BAMPP:"+msisdn+":Exception caught on Service invocation: "
                    + "comprarRequestDocument", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getCode();
        String descripcionRespuesta = response.getMessage();

        LOGGER.info("Teardown:"+msisdn+":codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("Teardown:"+msisdn+":descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK_COMPRA_SCOB.equals(codigoRespuesta)) {
            LOGGER.info("Teardown realizado al numero: ".concat(msisdn));
        }
        else {
            LOGGER.error("Teardown:"+msisdn+":Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
    }

}