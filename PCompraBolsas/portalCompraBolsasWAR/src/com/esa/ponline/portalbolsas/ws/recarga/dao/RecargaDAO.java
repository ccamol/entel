/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.ws.recarga.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.billing.recarga.BillingRecargaServicePortType;
import com.epcs.billing.recarga.types.ActualizarRegistroRecWebPayType;
import com.epcs.billing.recarga.types.ConsultarRegistroRecWebPayType;
import com.epcs.billing.recarga.types.DetalleMontoType;
import com.epcs.billing.recarga.types.FactibilidadWebPayType;
import com.epcs.billing.recarga.types.InsertarRegistroRecWebPayType;
import com.epcs.billing.recarga.types.RecargaWebPayType;
import com.epcs.billing.recarga.types.ResConsultarRegistroRecWebPayType;
import com.epcs.billing.recarga.types.RespuestaType;
import com.epcs.billing.recarga.types.ResultadoActualizarRegistroRecWebPayType;
import com.epcs.billing.recarga.types.ResultadoConsultarRegistroRecWebPayType;
import com.epcs.billing.recarga.types.ResultadoFactibilidadWebPayType;
import com.epcs.billing.recarga.types.ResultadoInsertarRegistroRecWebPayType;
import com.epcs.billing.recarga.types.ResultadoRecWebPayType;
import com.epcs.billing.recarga.types.ResultadoRecargaWebPayType;
import com.esa.ponline.portalbolsas.bean.FactibilidadRecargaWebPayBean;
import com.esa.ponline.portalbolsas.bean.RecargaWebPayBean;
import com.esa.ponline.portalbolsas.configuration.DateHelper;
import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;
import com.esa.ponline.portalbolsas.exceptions.dao.DAOException;
import com.esa.ponline.portalbolsas.exceptions.ws.EntelServicesLocatorException;
import com.esa.ponline.portalbolsas.exceptions.ws.ServiceException;
import com.esa.ponline.portalbolsas.util.PCBUtils;
import com.esa.ponline.portalbolsas.util.TimeWatch;
import com.esa.ponline.portalbolsas.ws.EntelServices;
import com.esa.ponline.portalbolsas.ws.recarga.dao.factory.RecargaTypeFactory;
import com.esa.ponline.portalbolsas.ws.recarga.dao.util.helper.WebPayHelper;

/**
 * DAO para los servicios de Recarga: multitiendas, tarjeta credito (webpay) y
 * entelticket
 * 
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class RecargaDAO {

    /**
     * Logger para RecargaDAO
     */
    private static final Logger LOGGER = Logger.getLogger(RecargaDAO.class);

    private static final String CODIGO_RESPUESTA_OK = LoadProperty.getProperty("servicios.codigoRespuesta.exito");

    private static final String TBK_COD_AUT_M001 = "TBK_COD_AUT_M001";

    // ------------------------------------------------------ WEBPAY

    /**
     * Ingresa una nueva recarga webpay, retornando el numero de orden de
     * compra.<br>
     * 
     * @param numeroPcs
     *            Numero al que se realiza la recarga
     * @param monto
     *            monto de la recarga
     * @param idp
     *            Identificador PCS asociado a la recarga
     * @return {@link RecargaWebPayBean} instancia de la recarga recien
     *         ingresada
     * @throws DAOException
     *             ante errores en la ejecucion del metodo
     * @throws ServiceException
     *             Si el Servcio retorna un mensaje de error
     */
    public RecargaWebPayBean ingresarRecargaWebPay(String numeroPcs,
            Double monto, String idp) throws DAOException, ServiceException {

        BillingRecargaServicePortType port = null;
        LOGGER.info("Instanciando el port " + BillingRecargaServicePortType.class);
        
        port = getBillingRecargaServiceConnection();

        InsertarRegistroRecWebPayType request = new InsertarRegistroRecWebPayType();
        ResultadoInsertarRegistroRecWebPayType response = null;

        String ordenCompra = WebPayHelper.generarIdOrdenDeCompra();
        LOGGER.info("Orden de compra generado: " + ordenCompra);

        Date fechaTransaccion = new Date();

        try {
            // Constantes, obtenidas desde properties
        	// REC_WPY
            String tipoPago = LoadProperty.getProperty("recarga.webpay.ingresar.tipoPago");
            request.setTipoPago(tipoPago);
            
            //El parametro4 es el numero en que se efectuara la recarga
            request.setParametro4(numeroPcs);

            // Valores generados
            request.setOrdCompra(ordenCompra);
            request.setFechaTransaccion(DateHelper.format(fechaTransaccion, DateHelper.FORMAT_yyyyMMdd_HHmmss));
            // IDP es obtenido como parametro del metodo
            request.setIDP(idp);
           
            //Monto
            DetalleMontoType detalleMonto = new DetalleMontoType();
            detalleMonto.setOrdenCompra(ordenCompra);
            detalleMonto.setMonto(String.valueOf(monto.longValue()));
            
            // REC
            String param = LoadProperty.getProperty("recarga.webpay.ingresar.parametroPersonalizable");
            detalleMonto.setParametroPersonalizable(param);
            
            request.getDetalleMonto().add(detalleMonto);
            
            LOGGER.info("Request -> getIdp: "+request.getIDP()+", monto: "+monto+" numeroPcs: "+numeroPcs+", param personalizable: "+param);
            LOGGER.info("## Invocando servicio InsertarRegistroRecWebPay ##");
            
            // TODO linea para insertar registro recarga webPay
            response = port.insertarRegistroRecWebPay(request);
            
            //Logger detallado para log de Recarga
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("ordenCompra: [" + ordenCompra + "] | Registro recarga WebPay ingresado");
                LOGGER.debug("ordenCompra: [" + ordenCompra + "] | NumeroPcs: " + numeroPcs);
                LOGGER.debug("ordenCompra: [" + ordenCompra + "] | Monto: " + monto);
                LOGGER.debug("ordenCompra: [" + ordenCompra + "] | idp: " + idp);
                LOGGER.debug("ordenCompra: [" + ordenCompra + "] | fecha transaccion: " + fechaTransaccion);
                LOGGER.debug("ordenCompra: [" + ordenCompra + "] | tipo pago: " + tipoPago);
                LOGGER.debug("ordenCompra: [" + ordenCompra + "] | param. personalizable: " + param);
            }
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: " + "insertarRegistroRecWebPay", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("CodigoRespuesta: " + codigoRespuesta);
        LOGGER.info("OrdenCompraRespuesta: "+response.getOrdenCompra());

        if (PCBUtils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException("insertarRegistroRecWebPay(: Servicio no responde "
                    + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            try {
                RecargaWebPayBean recarga = new RecargaWebPayBean();

                String ordenCompraResponse = response.getOrdenCompra();
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("ordenCompra: [" + ordenCompra + "] | Orden de compra obtenida: " + ordenCompra);
                }
                
                recarga.setOrdenCompra(ordenCompraResponse);
                recarga.setFechaSolicitud(fechaTransaccion);
                recarga.setMontoRecarga(monto);
                recarga.setNumeroPcs(numeroPcs);
                recarga.setIdp(idp);
                
                return recarga;
            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: " + "insertarRegistroRecWebPay" , e);
                LOGGER.error(new DAOException(e));
            }
        }
        else {
            LOGGER.error("insertarRegistroRecWebPay: Service error code received: "
                            + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new RecargaWebPayBean();
    }

    /**
     * Valida la factibilidad de recarga, de ser positivo, la solicitud es
     * ingresada a la cola de transacciones de recargas.<br>
     * Si es negativo, este metodo lanzara una ServiceException, con el codigo
     * de error y descripcion del motivo por el que no hay factibilidad
     * 
     * @param recarga
     *            {@link RecargaBean} Recarga solicitada
     * @throws DAOException
     *             ante errores en la ejecucion del metodo
     * @throws ServiceException
     *             Si no hay factibilidad de recarga, o el Servcio retorna un
     *             mensaje de error
     */
    public FactibilidadRecargaWebPayBean factibilidadRecargaWebPay(
            RecargaWebPayBean recarga, boolean eligeTuPromo, String codPromoF) throws DAOException, ServiceException {

        if (recarga == null) {
        	LOGGER.info("RecargaWebPayBean es nulo!");
            LOGGER.error(new DAOException("Recarga cannot be null"));
        }

        LOGGER.info("Instanciando el port " + BillingRecargaServicePortType.class);
        
        BillingRecargaServicePortType port = getBillingRecargaServiceConnection();;

        FactibilidadWebPayType request = RecargaTypeFactory.buildFactibilidadWebPayType(eligeTuPromo);
        ResultadoFactibilidadWebPayType response = null;

        // Codigo unico de transaccion
        String codUnicoTransaccion = WebPayHelper.generarCodigoUnicoTransaccion();
        
        LOGGER.info("ETPMovil: "+recarga.getNumeroPcs()+", Codigo unico transaccion: "+codUnicoTransaccion+", recarga.getOrdenCompra: "+recarga.getOrdenCompra());
        try {
            String fechaSolicitud = DateHelper.format(recarga.getFechaSolicitud(), DateHelper.FORMAT_yyyyMMdd);
            request.setFechaSolicitud(fechaSolicitud);

            String montoRecarga = String.valueOf(recarga.getMontoRecarga().longValue());
            request.setMontoRecarga(montoRecarga);
            request.setMsisdnRecarga(recarga.getNumeroPcs());
            request.setCodigoUnicoTransaccion(codUnicoTransaccion);
            
            if (!codPromoF.trim().equals("")) {
            	
            	request.setCodLocal(codPromoF);
            	
            	LOGGER.info("ETPMovil: " + recarga.getNumeroPcs() + ", request.getCodLocal: " + codPromoF+", eligeTuPromo: "+eligeTuPromo);
            	      	            
            } else {
            	
            	LOGGER.info("ETPMovil: " + recarga.getNumeroPcs() + ", request.getCodLocal: " + codPromoF+", eligeTuPromo: "+eligeTuPromo);
            }

            response = port.factibilidadWebPay(request);
            
            //Logger detallado para log de Recarga
            if(LOGGER.isDebugEnabled()) {
                
                String ordenCompra = recarga.getOrdenCompra();
                
                LOGGER.debug(ordenCompra + " Factibilidad recarga WebPay consultada");
                LOGGER.debug(ordenCompra + "     numeroPcs : " + recarga.getNumeroPcs());
                LOGGER.debug(ordenCompra + "     monto : " + montoRecarga);
                LOGGER.debug(ordenCompra + "     cod. unico transaccion : " + codUnicoTransaccion);
                LOGGER.debug(ordenCompra + "     fecha solicitud : " + fechaSolicitud);
                LOGGER.debug(ordenCompra + "     Local : " + request.getCodLocal()); 
            }
            
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: " + "factibilidadWebPay", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);

        if (PCBUtils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException("factibilidadWebPay: Servicio no responde " + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {
            	LOGGER.info("Resp Factib, codComercio: "+response.getResultadoFactibilidadWebPay().getCodComercio());
            	LOGGER.info("Resp Factib, codIntegrador: "+response.getResultadoFactibilidadWebPay().getCodIntegrador());
            	LOGGER.info("Resp Factib, codRespuesta: "+response.getResultadoFactibilidadWebPay().getCodigoRespuesta());
            	LOGGER.info("Resp Factib, codDistribucion: "+response.getResultadoFactibilidadWebPay().getCodDistribuidor());
                
                FactibilidadRecargaWebPayBean factibilidad = new FactibilidadRecargaWebPayBean();
                factibilidad.setNumeroPcs(response.getResultadoFactibilidadWebPay().getMsisdnRecarga());
                factibilidad.setCodUnicoTransaccion(response.getResultadoFactibilidadWebPay().getCodigoUnicoTransaccion());

                return factibilidad;
            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: " + "factibilidadWebPay", e);
                LOGGER.error( new DAOException(e));
            }
        }
        else {
        	LOGGER.info("Resp Factib, codComercio: "+response.getResultadoFactibilidadWebPay().getCodComercio());
        	LOGGER.info("Resp Factib, codIntegrador: "+response.getResultadoFactibilidadWebPay().getCodIntegrador());
        	LOGGER.info("Resp Factib, codRespuesta: "+response.getResultadoFactibilidadWebPay().getCodigoRespuesta());
        	LOGGER.info("Resp Factib, codDistribucion: "+response.getResultadoFactibilidadWebPay().getCodDistribuidor());
            
        	
            LOGGER.info("factibilidadWebPay: Service error code received: " + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new FactibilidadRecargaWebPayBean();
    }
    
    /**
     * Gets the account service connection.
     *
     * @return the account service connection
     */
    private BillingRecargaServicePortType getBillingRecargaServiceConnection() {
        TimeWatch watch = TimeWatch.start();
        BillingRecargaServicePortType port = null;

        try {
            port = EntelServices.getBillingRecargaServicePort().getBillingRecargaServicePort();
        } catch (EntelServicesLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + BillingRecargaServicePortType.class, e);
            LOGGER.error("Tiempo: " + watch.time() + " | " + BillingRecargaServicePortType.class);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Port " + BillingRecargaServicePortType.class, e);
            LOGGER.error("Tiempo: " + watch.time() + " | " + BillingRecargaServicePortType.class);
            e.printStackTrace();
        }
        return port;
    }

    public RecargaWebPayBean consultarRecargaWebPayBean(String ordenCompra)
            throws DAOException, ServiceException {
        LOGGER.debug("inicio proceso consultarRecargaWebPayBean");
        
        BillingRecargaServicePortType port = getBillingRecargaServiceConnection();

        ConsultarRegistroRecWebPayType request = new ConsultarRegistroRecWebPayType();
        ResultadoConsultarRegistroRecWebPayType response = null;
        
        try {
            request.setOrdenCompra(ordenCompra);

            response = port.consultarRegistroRecWebPay(request);
            
            //Logger detallado para log de Recarga
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(ordenCompra + " consulta registro recarga WebPay");
                LOGGER.debug("respuestaTransbank: " + response.getResultadoConsultarRegRecWebPay().getRespuestaTransbank());                
            }
            
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: consultarRegistroRecWebPay", e);
            LOGGER.error(new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        if (response.getResultadoConsultarRegRecWebPay() != null){
            LOGGER.info("codigoRespuesta " + codigoRespuesta);
            LOGGER.info("response.getResultadoConsultarRegRecWebPay().getIDP(): " + response.getResultadoConsultarRegRecWebPay().getIDP());
        } else{
            LOGGER.info("codigoRespuesta " + codigoRespuesta);
            LOGGER.info("getResultadoConsultarRegRecWebPay es nulo - sin respuesta");
        }


        if (PCBUtils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException("consultarRegistroRecWebPay: Servicio no respondio "
                    + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {
                ResConsultarRegistroRecWebPayType resultadoRegistroTransbank = response.getResultadoConsultarRegRecWebPay();

                //Logger detallado para log de Recarga
                if (LOGGER.isDebugEnabled()) {

                    LOGGER.debug("Resultado consulta registro recarga WebPay");
                    LOGGER.debug("OrdenCompra: " + resultadoRegistroTransbank.getOrdenCompra());
                    LOGGER.debug("IDP: " + resultadoRegistroTransbank.getIDP());
                    LOGGER.debug("FechaTransaccion: " + resultadoRegistroTransbank.getFechaTransaccion());
                    LOGGER.debug("Parametro4 [MSISDN]: " + resultadoRegistroTransbank.getParametro4());
                    LOGGER.debug("Parametro5: " + resultadoRegistroTransbank.getParametro5());
                    LOGGER.debug("Parametro6: " + resultadoRegistroTransbank.getParametro6());
                    LOGGER.debug("RespuestaTransbank: " + resultadoRegistroTransbank.getRespuestaTransbank());
                    LOGGER.debug("TipoRecarga: " + resultadoRegistroTransbank.getTipoRecarga());
                    LOGGER.debug("DetalleMonto: ");
                    Iterator<DetalleMontoType> it = resultadoRegistroTransbank.getDetalleMonto().iterator();
                    
                    while (it.hasNext()) {
                        LOGGER.debug("Monto: " + it.next().getMonto());
                    }
                }                

                RecargaWebPayBean recarga = new RecargaWebPayBean();
                
                recarga.setOrdenCompra(resultadoRegistroTransbank.getOrdenCompra());
                recarga.setNumeroPcs(resultadoRegistroTransbank.getParametro4());
                
                String codUnicoAutorizacion = extraerCodUnicoAutorizacion(resultadoRegistroTransbank.getRespuestaTransbank());
                recarga.setCodigoUnicoAutorizacion(codUnicoAutorizacion);
                
                recarga.setRespuestaTransbank(resultadoRegistroTransbank.getRespuestaTransbank());
                
                Date fechaSolicitud = DateHelper.parseDate(resultadoRegistroTransbank.getFechaTransaccion(), DateHelper.FORMAT_yyyyMMdd_HHmmss);
                recarga.setFechaSolicitud(fechaSolicitud);
                
                Double montoRecarga = obtenerMontoRecarga(resultadoRegistroTransbank.getDetalleMonto());
                recarga.setMontoRecarga(montoRecarga);
                recarga.setIdp(resultadoRegistroTransbank.getIDP());
                
                return recarga;
            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: " +
                        "consultarRegistroRecWebPay", e);
                LOGGER.error(new DAOException(e));
            }
        }
        else {
            LOGGER.error("method: Service error code received: " + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error(new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new RecargaWebPayBean();
    }
        
    /**
     * Extrae el valor del parametro TBK_COD_AUT_M001 desde el String de
     * respuesta recibido por Transbank.<br>
     * Si el parametro no se encuentra, o el string tiene un formato no valido,
     * este metodo retorna <code>null</code>
     * 
     * @param respuestaTransbank
     *            String original recibido por Transbank
     * @return String con valor de TBK_COD_AUT_M001. <code>null</code> en caso
     *         de no encontrarse, o formato invalido
     */
    private String extraerCodUnicoAutorizacion(String respuestaTransbank) {
        return WebPayHelper.extraerParametroTransbank(respuestaTransbank, TBK_COD_AUT_M001);
    }

    /**
     * Obtiene el monto de la recarga, a partir de la lista de detalles de monto
     * que retorna el registro de recarga.<br>
     * Actualmente, la logica de este metodo es obtener solo la primera
     * ocurrencia en la lista. Por tanto, este metodo retornara
     * <code>null</code> si la lista es null o <code>isEmpty()</code> retorna
     * <code>true</code><br>
     * Si la logica cambia en el futuro, debe modificarse este metodo.
     * 
     * @param detalleMonto
     *            List con detalles de montos
     * @return Double con monto de la recarga. <code>null</code> si se da la
     *         siguiente condicion:<br>
     * 
     *         <pre>
     * detalleMonto == null || detalleMonto.isEmpty()
     * </pre>
     * 
     */
    private Double obtenerMontoRecarga(List<DetalleMontoType> detalleMonto) {

        if(detalleMonto == null || detalleMonto.isEmpty()) {
            return null;
        }
        
        /*
         * Actualmente, la logica de obtencion de monto, extrae solo la 1era ocurrencia
         * de la lista.
         * Si la logica cambia en el futuro, modificar este metodo
         */
        Double monto = null;
        try {
            monto = Double.valueOf(detalleMonto.get(0).getMonto());
        } catch (Exception e) {
            return null;
        }
        
        return monto;
    }

    /**
     * Actualiza una recarga webpay con el resultado de Transbank
     * <code>recargaWebPay</code>
     * 
     * @param ordenCompra
     *            {@link RecargaWebPayBean} indica la recarga que debe
     *            realizarce.
     * @throws DAOException
     *             ante errores en la ejecucion del metodo
     * @throws ServiceException
     *             Si no hay factibilidad de recarga, o el Servcio retorna un
     *             mensaje de error
     */
    public void actualizarRecargaWebPay(String ordenCompra,
            String parametrosWebPay) throws DAOException,
            ServiceException {

        if (ordenCompra == null || parametrosWebPay == null) {
            LOGGER.error(new DAOException("Parametros de entrada no pueden ser null"));
        }

        BillingRecargaServicePortType port = getBillingRecargaServiceConnection();
        LOGGER.info("Instanciando el port " + BillingRecargaServicePortType.class);
        
        ActualizarRegistroRecWebPayType request = new ActualizarRegistroRecWebPayType();
        ResultadoActualizarRegistroRecWebPayType response = null;

        try {
            request.setOrdenCompra(ordenCompra);
            request.setParametrosTransBank(parametrosWebPay);

            LOGGER.info("request-parametrosTransbank: " + parametrosWebPay);
            LOGGER.info("## Invocando ActualizarRegistroRecWebPay ##");
            response = port.actualizarRegistroRecWebPay(request);
            
            //Logger detallado para log de Recarga
            if(LOGGER.isDebugEnabled()) {
                
                LOGGER.debug(ordenCompra + " Actualizacion recarga WebPay");
                LOGGER.debug(ordenCompra + "     ordenCompra : " + ordenCompra);
                LOGGER.debug(ordenCompra + "     parametrosTransbank : " + parametrosWebPay);
            }
            
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service " + "invocation: actualizarRegistroRecWebPay", e);
            LOGGER.error(new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("ordenCompra: " + response.getOrdenCompra());

        if (PCBUtils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException("actualizarRegistroRecWebPay: Servicio no respondio "
                            + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            String ordenCompraOut = response.getOrdenCompra();

            // Validaciones de consistencia de retorno de servicio

            // Orden de compra vacia
            if (PCBUtils.isEmptyString(ordenCompraOut)) {
                LOGGER.error(new DAOException("actualizarRegistroRecWebPay: Servicio no "
                		+ "retorno orden compra esperada: " + ordenCompraOut));
            }

            // Retorno de orden de compra no coincida con parametro de entrada
            if (!ordenCompraOut.equals(ordenCompra)) {
                LOGGER.warn("Orden de compra obtenida no concuerda con valor entregado: "
                                + "IN: '"
                                + ordenCompra
                                + "' OUT: '" + ordenCompraOut + "'");
            }
            
            //Logger detallado para log de Recarga
            if(LOGGER.isDebugEnabled()) {
                
                LOGGER.debug(ordenCompra + " Resultado actualizacion recarga WebPay");
                LOGGER.debug(ordenCompra + "     ordenCompra : " + ordenCompraOut);
            }

        }
        else {
            LOGGER.error("actualizarRegistroRecWebPay: Service error code received: "
                            + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error(new ServiceException(codigoRespuesta, descripcionRespuesta));
        }

    }

    /**
     * Hace efectiva una recarga webpay.<br>
     * La instancia de {@link RecargaWebPayBean} que retorna no es la misma que
     * se envia como parametro, ademas de contener campos de la respuesta al
     * servicio
     * 
     * @param recarga
     *            {@link RecargaWebPayBean} recarga a efectuarse
     * @return {@link RecargaWebPayBean} con los valores de la recarga efectuada,
     *         incluye informacion de Bonos, mensajes comerciales y nuevo saldo
     * @throws DAOException
     *             ante errores en la ejecucion del metodo
     * @throws ServiceException
     *             Si no hay factibilidad de recarga, o el Servcio retorna un
     *             mensaje de error
     */
    public RecargaWebPayBean efectuarRecargaWebPay(RecargaWebPayBean recarga, boolean eligeTuPromo)
            throws DAOException, ServiceException {

        BillingRecargaServicePortType port = getBillingRecargaServiceConnection();
        LOGGER.info("Instanciando el port " + BillingRecargaServicePortType.class);
        
        RecargaWebPayType request;
        ResultadoRecargaWebPayType response = null;
        
//        RespuestaType value = new RespuestaType();
//        value.setCodigo(CODIGO_RESPUESTA_OK);
//        value.setDescripcion("prueba_Dummy");
//        
//        ResultadoRecWebPayType value2 = new ResultadoRecWebPayType();
//    
//		response.setResultadoRecargaWebPay(value2);
//		response.setRespuesta(value);

        try {
            request = RecargaTypeFactory.buildRecargaWebPayType(eligeTuPromo);

            if (eligeTuPromo) {
				String[] idpPromo = recarga.getIdp().split("-");
				if (idpPromo.length == 2) {
					request.setCodLocal(idpPromo[1]);	
				}
			}
            
            request.setCodigoUnicoTransaccion(recarga.getOrdenCompra());
            request.setCodUnicoAutorizacion(recarga.getCodigoUnicoAutorizacion());
            request.setFechaSolicitud(DateHelper.format(recarga.getFechaSolicitud(), DateHelper.FORMAT_yyyyMMdd));
            
            String montoRecarga = String.valueOf(recarga.getMontoRecarga().longValue());
            request.setMontoRecarga(montoRecarga);
            
            request.setMsisdnRecarga(recarga.getNumeroPcs());
            
            LOGGER.info("request.getCodLocal: "+request.getCodLocal());
            LOGGER.info("## INVOCANDO RECARGA WEBPAY ##");
            
            response = port.recargaWebPay(request);
            
            //Logger detallado para log de Recarga
            if(LOGGER.isDebugEnabled()) {
                
                String ordenCompra = recarga.getOrdenCompra();
                
                LOGGER.debug(ordenCompra + "| Efectuar recarga WebPay");
                LOGGER.debug(ordenCompra + "| CodigoUnicoTransaccion : " + request.getCodigoUnicoTransaccion());
                LOGGER.debug(ordenCompra + "| CodigoUnicoAutorizacion : " + request.getCodUnicoAutorizacion());
                LOGGER.debug(ordenCompra + "| FechaSolicitud : " + request.getFechaSolicitud());
                LOGGER.debug(ordenCompra + "| MontoRecarga : " + request.getMontoRecarga());
                LOGGER.debug(ordenCompra + "| NumeroPcs : " + request.getMsisdnRecarga());
            }
            
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: recargaWebPay", e);
            LOGGER.error(new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);        
        
        LOGGER.info("ETPMovil: "+response.getResultadoRecargaWebPay().getMsisdnRecarga()+", "
        		+ "CodComercio: "+response.getResultadoRecargaWebPay().getCodComercio());
        
        LOGGER.info("CodLocal: "+response.getResultadoRecargaWebPay().getCodLocal()+ ", "
        		+ "MontoRecarga: "+response.getResultadoRecargaWebPay().getMontoRecarga());

        if (PCBUtils.isEmptyString(codigoRespuesta)) {
            LOGGER.error(new DAOException("recargaWebPay: Servicio no respondio "
                    + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {
                ResultadoRecWebPayType resultado = response.getResultadoRecargaWebPay();

                // Logger detallado para log de Recarga
                if (LOGGER.isDebugEnabled()) {

                    String ordenCompra = recarga.getOrdenCompra();

                    LOGGER.debug(ordenCompra + ": Resultado efectuar recarga WebPay");
                    LOGGER.debug(ordenCompra + ": CodigoUnicoTransaccion : " + resultado.getCodUnicoTransaccion());
                    LOGGER.debug(ordenCompra + ": FechaSolicitud : " + resultado.getFechaSolicitud());
                    LOGGER.debug(ordenCompra + ": MontoRecarga : " + resultado.getMontoRecarga());
                    LOGGER.debug(ordenCompra + ": NumeroPcs : " + resultado.getMsisdnRecarga());
                    LOGGER.debug(ordenCompra + ": MontoBono : " + resultado.getMontoBono());
                    LOGGER.debug(ordenCompra + ": DescuentoAcumulado : " + resultado.getDescuentoAcumulado());
                    LOGGER.debug(ordenCompra + ": NuevoSaldo : " + resultado.getNuevoSaldo());
                    LOGGER.debug(ordenCompra + ": ValidezRecarga : "+ resultado.getValidezRecarga());
                    LOGGER.debug(ordenCompra + ": MensajePublicidad : " + resultado.getMensajePublicidad());   
                }
                
                RecargaWebPayBean resultadoRecarga = new RecargaWebPayBean();

                resultadoRecarga.setCodigoUnicoAutorizacion(resultado.getCodUnicoAutorizacion());

                resultadoRecarga.setFechaSolicitud(DateHelper.parseDate(resultado.getFechaSolicitud(),
                        DateHelper.FORMAT_yyyyMMdd));

                resultadoRecarga.setFechaTransaccionTransbank(resultado.getFechaSolicitud());
                
                resultadoRecarga.setMontoRecarga(Double.valueOf(resultado.getMontoRecarga()));

                resultadoRecarga.setNumeroPcs(resultado.getMsisdnRecarga());

                resultadoRecarga.setOrdenCompra(resultado.getCodUnicoTransaccion());

                resultadoRecarga.setMontoBono(Double.valueOf(resultado.getMontoBono()));

                resultadoRecarga.setDescuentoAcumulado(resultado.getDescuentoAcumulado());

                resultadoRecarga.setNuevoSaldo(Double.valueOf(resultado.getNuevoSaldo()));

                resultadoRecarga.setValidezRecarga(resultado.getValidezRecarga());

                resultadoRecarga.setMensajePublicidad(resultado.getMensajePublicidad());
                
                resultadoRecarga.setCodigoRespuestaTBK(codigoRespuesta);
                
                resultadoRecarga.setDescripcionRespuestaTBK(descripcionRespuesta);

                // Atributos desde RecargaWebPayBean de entrada
				resultadoRecarga.setIdp(recarga.getIdp());
				resultadoRecarga.setNumeroPcs(recarga.getNumeroPcs());
				resultadoRecarga.setRespuestaTransbank(recarga.getRespuestaTransbank());

                return resultadoRecarga;

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service " + "response: recargaWebPay", e);
                LOGGER.error(new DAOException(e));
            }
        }
        else {
            LOGGER.error("recargaWebPay: Service error code received: " + codigoRespuesta + 
            		" - " + descripcionRespuesta);
            LOGGER.error(new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        RecargaWebPayBean resultadoRecarga = new RecargaWebPayBean();
        
        resultadoRecarga.setCodigoRespuestaTBK(codigoRespuesta);
        resultadoRecarga.setDescripcionRespuestaTBK(descripcionRespuesta);
        
        return resultadoRecarga;
    }
}