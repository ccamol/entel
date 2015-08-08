/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.balance.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.bean.DetalleFacturaMesBean;
import com.epcs.bean.DocumentoFacturaFullBean;
import com.epcs.bean.EstadoCuentaMPTBean;
import com.epcs.bean.FacturacionFullBean;
import com.epcs.bean.ResultadoConsultarDetalleCuenta;
import com.epcs.bean.ResumenFacturacionBean;
import com.epcs.billing.balance.BillingBalanceService;
import com.epcs.billing.balance.BillingBalanceService_Service;
import com.epcs.billing.balance.types.ConsultarDetalleFacturaType;
import com.epcs.billing.balance.types.ConsultarEstadoCuentaMPTType;
import com.epcs.billing.balance.types.ConsultarFacturacionFullType;
import com.epcs.billing.balance.types.ConsultarFacturasImpagasType;
import com.epcs.billing.balance.types.ConsultarResumenFacturacionType;
import com.epcs.billing.balance.types.DetalleFacturaMesType;
import com.epcs.billing.balance.types.DocumentoFacturaFullType;
import com.epcs.billing.balance.types.EstadoCuentaMPT;
import com.epcs.billing.balance.types.FacturaType;
import com.epcs.billing.balance.types.ResultadoConsultarDetalleFacturaType;
import com.epcs.billing.balance.types.ResultadoConsultarEstadoCuentaMPTType;
import com.epcs.billing.balance.types.ResultadoConsultarFacturacionFullType;
import com.epcs.billing.balance.types.ResultadoConsultarFacturasImpagasType;
import com.epcs.billing.balance.types.ResultadoConsultarResumenFacturacionType;
import com.epcs.billing.balance.types.ResumenFacturasType;
import com.epcs.billing.prodfactura.BillingProdFacturaService;
import com.epcs.billing.prodfactura.BillingProdFacturaService_Service;
import com.epcs.billing.prodfactura.types.ConsultarFacturaElectronicaMPTType;
import com.epcs.billing.prodfactura.types.DocumentoType;
import com.epcs.billing.prodfactura.types.ObtenerDetalleCuentaType;
import com.epcs.billing.prodfactura.types.ResultadoObtenerDetalleCuentaType;
import com.epcs.billing.registrouso.BillingRegistroUsoService;
import com.epcs.billing.registrouso.BillingRegistroUsoServicePortType;
import com.epcs.billing.registrouso.types.ConsultarDetalleLlamadosFullType;
import com.epcs.billing.registrouso.types.ConsultarDetalleLlamadosType;
import com.epcs.billing.registrouso.types.DetalleLlamadosFullType;
import com.epcs.billing.registrouso.types.DetalleLlamadosType;
import com.epcs.billing.registrouso.types.ResultadoConsultarDetalleLlamadosFullType;
import com.epcs.billing.registrouso.types.ResultadoConsultarDetalleLlamadosType;
import com.epcs.cliente.perfil.ClientePerfilService;
import com.epcs.cliente.perfil.ClientePerfilServicePortType;
import com.epcs.cliente.perfil.types.ConsultarBloqueoDetalleLlamadosType;
import com.epcs.cliente.perfil.types.ResultadoConsultarBloqueoDetalleLlamadosType;
import com.epcs.recursoti.configuracion.DateHelper;
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
public class FacturacionDAO {

    private static final Logger LOGGER = Logger.getLogger(FacturacionDAO.class);
    private static final Logger FACTURACION_LOGGER = Logger.getLogger("cajaFacturacionLog");

    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
            .getProperty("servicios.codigoRespuesta.exito");

    public static final String ESTADO_FACTURA_PAGADO = MiEntelProperties
            .getProperty("facturacionfull.estado.pagado");

    public static final String ESTADO_FACTURA_NO_PAGADO = MiEntelProperties
            .getProperty("facturacionfull.estado.nopagado");

    public static final String ID_SISTEMA_FACTURACION_FULL = MiEntelProperties
            .getProperty("facturacionfull.idsistema");

    public static final String ID_SISTEMA_STD_FACTURAS = MiEntelProperties
            .getProperty("facturasImpagas.idSistema");

 /**
     * Obtener datos de la facturacion full
     * @param numeroPcs
     * @param rutTitular
     * @param idSistema
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public FacturacionFullBean getFacturacionFull(String numeroPcs,
            String rutTitular) throws DAOException, ServiceException {

        FacturacionFullBean facturacionFull = new FacturacionFullBean();

        BillingBalanceService port = null;
        
        LOGGER.info("Instanciando el port");
        try {
            port = (BillingBalanceService) WebServiceLocator.getInstance()
                    .getPort(BillingBalanceService_Service.class,
                            BillingBalanceService.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + BillingBalanceService.class, e);
            LOGGER.error( new DAOException(e));
        }

        
        ConsultarFacturacionFullType request = new ConsultarFacturacionFullType();
        ResultadoConsultarFacturacionFullType resultadoConsultarFacturacionFull = null;
        
        try {

            LOGGER.info("Configurando Datos de la peticion");        
            request.setMsisdn(numeroPcs);
            request.setRutTitular(rutTitular);
            request.setIdSistema(ID_SISTEMA_FACTURACION_FULL);

            LOGGER.info("Invocando servicio");
            resultadoConsultarFacturacionFull = port.consultarFacturacionFull(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }        
        
        String codigoRespuesta = resultadoConsultarFacturacionFull
                .getRespuesta().getCodigo();

        String descripcionRespuesta = resultadoConsultarFacturacionFull
                .getRespuesta().getDescripcion();
        
        LOGGER.info("codigoRespuestaFULL  " + codigoRespuesta);
        LOGGER.info("descripcionRespuestaFULL " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
        
            try {
                ArrayList<DocumentoFacturaFullBean> documentosFacturasFull = new ArrayList<DocumentoFacturaFullBean>();
                ArrayList<DocumentoFacturaFullType> documentosFactFull = new ArrayList<DocumentoFacturaFullType>(
                        resultadoConsultarFacturacionFull.getDocumentosFacturasFull()
                        );
                
                for( DocumentoFacturaFullType doc : documentosFactFull ){
                    DocumentoFacturaFullBean bean = new DocumentoFacturaFullBean();   
                    bean.setCodEstado(doc.getEstado());
                    bean.setEstado(ESTADO_FACTURA_PAGADO.equals(doc.getEstado()) ? "Pagado" : ESTADO_FACTURA_NO_PAGADO.equals(doc.getEstado()) ? "No pagado" : "No disponible");
                    bean.setFechaEmision(DateHelper.parseDate(doc.getFechaEmision(), DateHelper.FORMAT_ddMMyyyy_HYPHEN));
                    bean.setFechaPeriodoEmision(DateHelper.format(bean.getFechaEmision(), "MM/yyyy"));
                    bean.setFechaPeriodo(doc.getFechaPeriodo());
                    bean.setFechaVencimiento(DateHelper.parseDate(doc.getFechaVencimiento(), DateHelper.FORMAT_ddMMyyyy_SLASH));
                    bean.setFolio(doc.getFolio());
                    bean.setMontoCobrosDescuentos(Double.parseDouble(doc.getMontoCobrosDescuentos()));
                    bean.setMontoServAdicional(Double.parseDouble(doc.getMontoServAdicional()));
                    bean.setMontoTelefoniaMovil(Double.parseDouble(doc.getMontoTelefoniaMovil()));
                    bean.setMontoTotal(Double.parseDouble(doc.getMontoTotal()));
                    bean.setNumeroCuenta(doc.getNumeroCuenta());
                    bean.setTipo(doc.getTipo());
                    bean.setMontoString(doc.getMontoTotal());
                    bean.setMesFactura(solveMesFactura(doc.getFechaEmision()));                                      
                    
                    documentosFacturasFull.add(bean);
                }
       
                java.util.Collections.sort(documentosFacturasFull, new java.util.Comparator<DocumentoFacturaFullBean>()
                {
                    public int compare( DocumentoFacturaFullBean a, DocumentoFacturaFullBean b )
                       {
                    	   Date fecha1 = a.getFechaEmision();
                    	   Date fecha2 = b.getFechaEmision();
                    	   
                    	   return (-1)*fecha1.compareTo(fecha2);
                       }
                } );
                
                DetalleFacturaMesType detalleFactMes = resultadoConsultarFacturacionFull.getDetalleFacturaMes();
                DetalleFacturaMesBean detalleFacturaMes = new DetalleFacturaMesBean();                
                detalleFacturaMes.setCodEstadoMes(detalleFactMes.getEstadoMes());
                detalleFacturaMes.setEstadoMes(ESTADO_FACTURA_PAGADO.equals(detalleFactMes.getEstadoMes()) ? "Pagado" : ESTADO_FACTURA_NO_PAGADO.equals(detalleFactMes.getEstadoMes()) ? "No pagado" : "No disponible");
                detalleFacturaMes.setFechaEmisionMes(DateHelper.parseDate(detalleFactMes.getFechaEmisionMes(), DateHelper.FORMAT_ddMMyyyy_HYPHEN));
                detalleFacturaMes.setFechaPeriodoMes(DateHelper.parseDate(detalleFactMes.getFechaPeriodoMes(), "MM/yyyy"));
                detalleFacturaMes.setFechaVencimientoMes(DateHelper.parseDate(detalleFactMes.getFechaVencimientoMes(), DateHelper.FORMAT_ddMMyyyy_SLASH));
                detalleFacturaMes.setSaldoAnteriorMes(Double.parseDouble(detalleFactMes.getSaldoAnteriorMes()));
                detalleFacturaMes.setTotalActualMes(Double.parseDouble(detalleFactMes.getTotalActualMes()));
                detalleFacturaMes.setTotalPagoMes(Double.parseDouble(detalleFactMes.getTotalPagoMes()));                
                detalleFacturaMes.setUrlImagenFactura(detalleFactMes.getUrlImagenFactura());   
                
                
                facturacionFull.setDocumentosFacturasFull( documentosFacturasFull );
                facturacionFull.setDetalleFacturaMes( detalleFacturaMes );
            
            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response", e);
                LOGGER.error( new DAOException(e));
            }
        
        } else {
            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        
        LOGGER.info("FIN INICIANDO EL FULL");
                
        return facturacionFull;
        
    }

    /**
     * Obtener la imagen correspondiente a una factura
     * 
     * @param numeroPCS
     * @param folio
     * @param tipo
     * @param periodo
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public String getUrlImagenFactura(String numeroPCS, String folio,
            String tipo, String periodo) throws DAOException, ServiceException {

        BillingBalanceService port = null;
        LOGGER.info("Instanciando el port");
        try {
            port = (BillingBalanceService) WebServiceLocator.getInstance()
                    .getPort(BillingBalanceService_Service.class,
                            BillingBalanceService.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + BillingBalanceService.class, e);
            LOGGER.error( new DAOException(e));
        }
        
        ConsultarDetalleFacturaType consd = new ConsultarDetalleFacturaType();
        ResultadoConsultarDetalleFacturaType resultadoDetalleFactura = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");        
            consd.setMsisdn(numeroPCS);
            consd.setFolioDocumento(folio);
            consd.setPeriodoDocumento(periodo);
            consd.setTipoDocumento(tipo);
            
            LOGGER.info("msisdn: " + consd.getMsisdn());
            LOGGER.info("folio: " + consd.getFolioDocumento());
            LOGGER.info("periodo: " + consd.getPeriodoDocumento());
            LOGGER.info("tipo: " + consd.getTipoDocumento());

            LOGGER.info("Invocando servicio");
            resultadoDetalleFactura = port.consultarDetalleFactura(consd);
            
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }
        
        String codigoRespuesta = resultadoDetalleFactura.getRespuesta()
                .getCodigo();
        String descripcionRespuesta = resultadoDetalleFactura.getRespuesta()
                .getDescripcion();

        LOGGER.info("codigoRespuesta  " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            LOGGER.info("Imagen:  "
                    + resultadoDetalleFactura.getUrlImagenDocumento());
            return resultadoDetalleFactura.getUrlImagenDocumento();
        }
        else {
            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        
        return "";
    }

    /**
     * Obtener datos de la facturacion actual y consumo de los ultimos 6 meses
     * 
     * @return ResumenFacturacionBean
     */
    public ResumenFacturacionBean getResumenFacturacion(String numeroPcs,
            String rut, String numeroCuenta, boolean logCajaFacturacion) throws DAOException,
            ServiceException {

        BillingBalanceService port = null;
        BillingBalanceService_Service service = null;
        
        if(logCajaFacturacion){
        	FACTURACION_LOGGER.info("****** Clase: com.epcs.billing.balance.dao.FacturacionDAO ******");
        	FACTURACION_LOGGER.info("Instanciando el port");
        }else{
        	LOGGER.info("Instanciando el port");
        }
        
        try {
            port = (BillingBalanceService) WebServiceLocator.getInstance()
                   .getPort(BillingBalanceService_Service.class,
                            BillingBalanceService.class);
            service = (BillingBalanceService_Service) WebServiceLocator
					.getInstance().getService(BillingBalanceService_Service.class, false);
        } catch (WebServiceLocatorException e) {
            if(logCajaFacturacion){
            	FACTURACION_LOGGER.error("Error al inicializar el Port " + BillingBalanceService.class , e);
            }else{
            	LOGGER.error("Error al inicializar el Port " + BillingBalanceService.class , e);
            }
            LOGGER.error( new DAOException(e));
        }

        ConsultarResumenFacturacionType request = new ConsultarResumenFacturacionType();
        ResultadoConsultarResumenFacturacionType resumenFacturacionResponseType = null;

        try {

            if(logCajaFacturacion){
            	FACTURACION_LOGGER.info("Configurando Datos de la peticion");
                FACTURACION_LOGGER.info("****** INICIO INPUT ******");
                FACTURACION_LOGGER.info("Msisdn:    " + numeroPcs);
                FACTURACION_LOGGER.info("Rut:       " + rut);
                FACTURACION_LOGGER.info("NroCuenta: " + numeroCuenta);
                FACTURACION_LOGGER.info("****** FIN INPUT *********");
            }else{
            	LOGGER.info("Configurando Datos de la peticion");
            }
            
            request.setMsisdn(numeroPcs);
            request.setRut(rut);
            request.setNroCuenta(numeroCuenta);
            
            if(logCajaFacturacion){
            	FACTURACION_LOGGER.info("Invocando servicio");
                FACTURACION_LOGGER.info("Service name: " + service.getServiceName());
    		    FACTURACION_LOGGER.info("WSDL Document Location: " + service.getWSDLDocumentLocation());
                FACTURACION_LOGGER.info("Operacion: ConsultarResumenFacturacion");
            }else{
            	LOGGER.info("Invocando servicio");
            }
            
            resumenFacturacionResponseType = port
                    .consultarResumenFacturacion(request);

        } catch (Exception e) {
            if(logCajaFacturacion)
            	FACTURACION_LOGGER.error("Exception caught on Service invocation", e);
            else
            	LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = resumenFacturacionResponseType.getRespuesta().getCodigo();
        String descripcionRespuesta = resumenFacturacionResponseType
                .getRespuesta().getDescripcion();
        
        if (Utils.isEmptyString(codigoRespuesta)) {
        	FACTURACION_LOGGER.error("Servicio ConsultarResumenFacturacion no respondio "
                    + "con codigoRespuesta");
            LOGGER.error( new DAOException("Servicio ConsultarResumenFacturacion no respondio "
                    + "con codigoRespuesta"));
        }

        if(logCajaFacturacion){
        	FACTURACION_LOGGER.info("****** INICIO OUTPUT ******");
	        FACTURACION_LOGGER.info("codigoRespuesta: " + codigoRespuesta);
	        FACTURACION_LOGGER.info("descripcionRespuesta: " + descripcionRespuesta);
        }else{
        	LOGGER.info("codigoRespuesta " + codigoRespuesta);
        	LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            try {
                
                // Bean de retorno Service Bus
                ResumenFacturasType resumenFacturasType = resumenFacturacionResponseType
                        .getResumenFacturas();
                // Bean de Dominio
                ResumenFacturacionBean resumenFacturacionBean = new ResumenFacturacionBean();
    
                // Lista facturas
                List<FacturaType> facturas = resumenFacturasType.getFacturas();
                if (facturas == null || facturas.isEmpty()) {
                    if(logCajaFacturacion)
                    	FACTURACION_LOGGER.warn("No se encontraron facturas");
                    else
                    	LOGGER.warn("No se encontraron facturas");
            
                }
                else {
                    if(logCajaFacturacion){
                    	FACTURACION_LOGGER.info("Cantidad de Facturas " + facturas.size());
                    }else{
                    	LOGGER.info("Facturas size " + facturas.size());
                    }
                    
                    List<String> listaMeses = new LinkedList<String>();
                    List<String> listaFacturaMeses = new LinkedList<String>();
        
                    /*
                     * Ultima factura corresponde al mes en curso, el resto del List es el
                     * historial de facturacion
                     */
                    int idxUltimaFactura = facturas.size() - 1;
                    FacturaType facturaActual = facturas.get(idxUltimaFactura);
         
                    // Fecha vencimiento dd/MM/yyyy
                    resumenFacturacionBean
                    .setFechaVencimiento(solveFechaVencimiento(facturaActual
                            .getFechaVencimiento()));
                    
                    // Fecha emision dd-MM-yyyy
                    resumenFacturacionBean
                            .setFechaEmision(solveFechaEmision(facturaActual
                                    .getFechaEmision()));
                    
                    // Fecha periodo MM/yyyy
                    resumenFacturacionBean
                            .setFechaPeriodo(solveFechaPeriodo(facturaActual
                            		.getFechaPeriodoMes()));
         
                    resumenFacturacionBean.setUrlImagenFactura(facturaActual
                    		.getUrlImagenFactura());
                   
                    resumenFacturacionBean.setEstadoDocumento(facturaActual.getEstadoDocumento());
                 
                   
                    // Valor total de facturacion actual  
                    Double valorTotal = new Double(facturaActual.getValorDocumento());
                    resumenFacturacionBean.setTotalPago(valorTotal);
                    
                    if(logCajaFacturacion){
                        FACTURACION_LOGGER.info("****** Inicio Historial de Facturacion ******");
                    }
                   
                    for (int i = 0; i < resumenFacturasType.getFacturas().size(); i++) {
                    	FacturaType factura = resumenFacturasType.getFacturas().get(i);
                    	
                    	if(logCajaFacturacion){
                    		if(i < idxUltimaFactura)
                    			FACTURACION_LOGGER.info("****** Inicio Factura ******");
                    		else
                    			FACTURACION_LOGGER.info("****** Inicio Factura Actual ******");
                        	FACTURACION_LOGGER.info("facturaType.getFolio(): " + factura.getFolio());
                        	FACTURACION_LOGGER.info("facturaType.getTipoDocumento(): " + factura.getTipoDocumento());
                        	FACTURACION_LOGGER.info("facturaType.getFechaPeriodoMes(): " + factura.getFechaPeriodoMes());
                        	FACTURACION_LOGGER.info("facturaType.getFechaEmision(): " + factura.getFechaEmision());
                        	FACTURACION_LOGGER.info("facturaType.getFechaVencimiento(): " + factura.getFechaVencimiento());
                        	FACTURACION_LOGGER.info("facturaType.getValorDocumento(): " + factura.getValorDocumento());
                        	FACTURACION_LOGGER.info("facturaType.getEstadoDocumento(): " + factura.getEstadoDocumento());
                        	FACTURACION_LOGGER.info("facturaType.getUrlImagenFactura(): " + factura.getUrlImagenFactura());
                        	if(i < idxUltimaFactura)
                    			FACTURACION_LOGGER.info("****** Fin Factura **********");
                    		else
                    			FACTURACION_LOGGER.info("****** Fin Factura Actual *********");
                    	}

                        listaMeses.add(solveMesFactura(factura.getFechaEmision()));
                        listaFacturaMeses.add(factura.getValorDocumento());
                    }
                   
                    if(logCajaFacturacion){
                    	FACTURACION_LOGGER.info("****** Fin Historial de Facturacion *********");
                    	FACTURACION_LOGGER.info("****** FIN OUTPUT ******");
                    }

        //            LOGGER.info("Meses " + listaMeses);
        //            LOGGER.info("Factura Meses " + listaFacturaMeses);

                    resumenFacturacionBean.setFacturacionMeses(listaFacturaMeses);
                    resumenFacturacionBean.setMeses(listaMeses);
                    
                    resumenFacturacionBean.setFolio(facturaActual.getFolio());
                    resumenFacturacionBean.setTipoDocumento(facturaActual.getTipoDocumento());
                }
    
                

    //            LOGGER.info("end");
                return resumenFacturacionBean;

            } catch (Exception e) {
                if(logCajaFacturacion)
                	FACTURACION_LOGGER.error("Exception caught on Service response", e);
                else
                	LOGGER.error("Exception caught on Service response", e);
                LOGGER.error( new DAOException(e));
            }

        }
        else {
            if(logCajaFacturacion)
            	FACTURACION_LOGGER.error("Service error code received: " + codigoRespuesta
                        + " - " + descripcionRespuesta);
            else
            	LOGGER.error("Service error code received: " + codigoRespuesta
                        + " - " + descripcionRespuesta);
            
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new ResumenFacturacionBean();
    }
 
    private String solveMesFactura(final String fechaEmision)
            throws DAOException {
        String mesFactura = DateHelper
                .getMonthName(solveFechaEmision(fechaEmision));
        if (mesFactura == null) {
            LOGGER.error( new DAOException(
                    "fechaEmision no contiene formato esperado: '"
                            + DateHelper.FORMAT_ddMMyyyy_HYPHEN + "' - "
                            + fechaEmision));
        }
        return Utils.capitalize(mesFactura);
    }
 
    /**
     * Metodo utilitaria para resolver una fecha de emision desde el String
     * retornado por un servicio
     * 
     * @param fechaEmision
     * @return
     * @throws DAOException
     */
    private Date solveFechaEmision(final String fechaEmision) throws DAOException {
        return solveDate(fechaEmision, DateHelper.FORMAT_ddMMyyyy_HYPHEN,
                "fecha de emision");
    }

    /**
     * Metodo utilitaria para resolver una fecha de vencimiento desde el String
     * retornado por un servicio
     * 
     * @param fechaVencimiento
     * @return
     * @throws DAOException
     */
    private Date solveFechaVencimiento(final String fechaVencimiento)
            throws DAOException {
        return solveDate(fechaVencimiento, DateHelper.FORMAT_ddMMyyyy_SLASH,
                "fecha de vencimiento");
    }
    
    
    /**
     * Metodo utilitaria para resolver una fecha de vencimiento desde el String
     * retornado por un servicio
     * 
     * @param fechaVencimiento
     * @return
     * @throws DAOException
     */
    private Date solveFechaPeriodo(final String fechaPeriodo)
            throws DAOException {
        return solveDate(fechaPeriodo, DateHelper.FORMAT_MMyyyy_SLASH,
                "fecha periodo");
    }
    
    
    

    /**
     * Metodo utilitario para la obtencion de objetos Date a partir de fechas
     * String obtenidas desde las respuestas de Servicios.<br>
     * La utilidad de este metodo es reducir boiler-code en los metodos de
     * negocios, evitando un exceso de Complejidad ciclomatica en ellos.
     * 
     * @param strDate
     *            String fecha a parsear
     * @param format
     *            formato de la fecha (ver {@link DateHelper})
     * @param errorMessage
     *            String con la parte de personalizada del mensaje de error, en
     *            caso de fallar el parsing.
     * @return {@link Date}
     * @throws DAOException
     *             si la fecha no puede ser parseada
     */
    private Date solveDate(final String strDate, final String format,
            final String errorMessage) throws DAOException {
        Date date = DateHelper.parseDate(strDate, format);
        if (date == null) {
            LOGGER.error( new DAOException(errorMessage
                    + " no contiene formato esperado: '" + format + "' - "
                    + strDate));
        }
        return date;

    }    
    
    /**
     * Consulta el estado en que se encuentran todas las facturas del cliente
     * 
     * @param numeroPcs
     * @return String "PAGADO" o "IMPAGO" 
     * @throws DAOException
     * @throws ServiceException
     */
    public String estadoFacturasImpagas(String numeroPcs)
            throws DAOException, ServiceException {

        BillingBalanceService port = null;
        LOGGER.info("Instanciando el port");
        try {
            port = (BillingBalanceService) WebServiceLocator.getInstance()
                    .getPort(BillingBalanceService_Service.class,
                            BillingBalanceService.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + BillingBalanceService.class, e);
            LOGGER.error( new DAOException(e));
        }

        ConsultarFacturasImpagasType request = new ConsultarFacturasImpagasType();
        ResultadoConsultarFacturasImpagasType facturasImpagasType = null;

        try {

            LOGGER.info("Configurando Datos de la peticion");
            request.setMsisdn(numeroPcs);
            request.setIdSistema(ID_SISTEMA_STD_FACTURAS);

            LOGGER.info("Invocando servicio");
            facturasImpagasType = port.consultarFacturasImpagas(request);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = facturasImpagasType.getRespuesta().getCodigo();
        String descripcionRespuesta = facturasImpagasType.getRespuesta()
                .getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            LOGGER.info("Estado Factura :"
                    + facturasImpagasType.getEstadoFactura());

            return facturasImpagasType.getEstadoFactura();

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
     * @return
     */
    public String consultarDetalleLlamados(String tipoConsulta, String idConsulta, String folio, String tipoDocumento, String iteracion) throws DAOException, ServiceException {
        BillingRegistroUsoServicePortType port = null;
        String detalleLlamados = "";
        
        LOGGER.info("Instanciando el port " + BillingRegistroUsoServicePortType.class);
        try {
            port = (BillingRegistroUsoServicePortType) WebServiceLocator.getInstance().getPort(
                    BillingRegistroUsoService.class, BillingRegistroUsoServicePortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + BillingRegistroUsoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }        
        
        ConsultarDetalleLlamadosType request = new ConsultarDetalleLlamadosType();
        ResultadoConsultarDetalleLlamadosType response = null;

        try {
            LOGGER.info("Configurando Datos de la peticion");
            request.setTipoConsulta(tipoConsulta);
            request.setIdentificadorConsulta(idConsulta);
            request.setFolio(folio);
            request.setTipoDocumento(tipoDocumento);
            request.setIteracion(iteracion);
            LOGGER.info("Invocando servicio");
            response = port.consultarDetalleLlamados(request);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: " + "method", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException("method: Servicio no responde "
                    + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            try {
                DetalleLlamadosType detalle = response.getDetalleLlamados();
                detalleLlamados = detalle.getDetalleLlamados();
            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "method", e);
                LOGGER.error( new DAOException(e));
            }
        } else {
            LOGGER.error("method: Service error code received: "
                    + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        
        return detalleLlamados;
    }
    
    public String consultarDetalleLlamadosFull(String numeroCuenta, String numeroPcs, String aaa, String folio, String tipoDocumento) throws DAOException, ServiceException {
        BillingRegistroUsoServicePortType port = null;
        String detalleLlamadosFull = "";
        
        LOGGER.info("Instanciando el port " + BillingRegistroUsoServicePortType.class);
        try {
            port = (BillingRegistroUsoServicePortType) WebServiceLocator.getInstance().getPort(
                    BillingRegistroUsoService.class, BillingRegistroUsoServicePortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + BillingRegistroUsoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }        
        
        ConsultarDetalleLlamadosFullType request = new ConsultarDetalleLlamadosFullType();
        ResultadoConsultarDetalleLlamadosFullType response = null;

        try {
            LOGGER.info("Configurando Datos de la peticion");
            request.setNumeroCuenta(numeroCuenta);
            request.setMsisdn(numeroPcs);
            request.setAaa(aaa);
            request.setFolio(folio);
            request.setTipoDocumento(tipoDocumento);            
            LOGGER.info("Invocando servicio");
            response = port.consultarDetalleLlamadosFull(request);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: " + "method", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (Utils.isEmptyString(codigoRespuesta)) {
            LOGGER.error( new DAOException("method: Servicio no responde "
                    + "con codigoRespuesta"));
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            try {
                DetalleLlamadosFullType detalle = response.getDetalleLlamadosFull();
                detalleLlamadosFull = detalle.getDetalleLlamadosFull();
            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response: "
                        + "method", e);
                LOGGER.error( new DAOException(e));
            }
        } else {
            LOGGER.error("method: Service error code received: "
                    + codigoRespuesta + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        
        return detalleLlamadosFull;
    }
    
    public EstadoCuentaMPTBean consultarEstadoCuenta(String msisdn,
            String rutTitular) throws DAOException, ServiceException {

        BillingBalanceService port;
        EstadoCuentaMPTBean bean = new EstadoCuentaMPTBean();
        LOGGER.info("Instanciando el port " + BillingBalanceService.class);
        try {
            port = (BillingBalanceService) WebServiceLocator.getInstance()
                    .getPort(BillingBalanceService_Service.class,
                            BillingBalanceService.class);

            ConsultarEstadoCuentaMPTType request = new ConsultarEstadoCuentaMPTType();
            ResultadoConsultarEstadoCuentaMPTType response = null;

            try {

                LOGGER.info("Configurando Datos de la peticion");

                request.setMsisdn(msisdn);
                request.setRutTitular(rutTitular);
                request.setIdSistema(ID_SISTEMA_FACTURACION_FULL);

                LOGGER.info("Invocando servicio");
                response = port.consultarEstadoCuentaMPT(request);

            } catch (Exception e) {
                LOGGER.error("Exception caught on Service invocation: "
                        + "consultarEstadoCuentaMPT", e);
                LOGGER.error( new DAOException(e));
            }

            String codigoRespuesta = response.getRespuesta().getCodigo();
            String descripcionRespuesta = response.getRespuesta()
                    .getDescripcion();

            LOGGER.info("codigoRespuesta " + codigoRespuesta);
            LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

            if (Utils.isEmptyString(codigoRespuesta)) {
                LOGGER.error( new DAOException(
                        "consultarEstadoCuentaMPT: Servicio no responde "
                                + "con codigoRespuesta"));
            }

            if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
                try {
                    EstadoCuentaMPT datosCuenta = response.getEstadoCuentaMPT();
                    bean.setTotalPagoMes(Double.parseDouble(datosCuenta
                            .getTotalPagoMes()));
                    bean.setFechaVencimiento(DateHelper.parseDate(datosCuenta
                            .getFechaVencimiento(),
                            DateHelper.FORMAT_ddMMyyyy_SLASH));
                    bean.setEstadoMes(ESTADO_FACTURA_PAGADO.equals(datosCuenta
                            .getEstadoMes()) ? "Pagado"
                            : ESTADO_FACTURA_NO_PAGADO.equals(datosCuenta
                                    .getEstadoMes()) ? "No pagado"
                                    : "No disponible");
                    return bean;
                } catch (Exception e) {
                    LOGGER.error("Exception caught on Service response: "
                            + "consultarEstadoCuentaMPT", e);
                    LOGGER.error( new DAOException(e));
                }
            }
            else {
                LOGGER
                        .error("consultarEstadoCuentaMPT: Service error code received: "
                                + codigoRespuesta
                                + " - "
                                + descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,
                        descripcionRespuesta));
            }
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + BillingBalanceService.class, e);
            LOGGER.error( new DAOException(e));
        }
        return new EstadoCuentaMPTBean();
    } 
    
    /**
     * Obtener datos de detalle de un documento
     * @param nroCuenta
     * @param nroDocumentoMes    
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoConsultarDetalleCuenta getDetalleDocumento(String nroCuenta , String nroDocumentoMes) throws DAOException, ServiceException {

    	BillingProdFacturaService port;
    	ResultadoConsultarDetalleCuenta detalleCuenta = new ResultadoConsultarDetalleCuenta(); 
        LOGGER.info("Instanciando el port " + BillingProdFacturaService.class);
        try {
		        port = (BillingProdFacturaService) WebServiceLocator.getInstance()
		                    .getPort(BillingProdFacturaService_Service.class,
		                            BillingProdFacturaService.class);
		
		         ObtenerDetalleCuentaType request = new ObtenerDetalleCuentaType();
		         ResultadoObtenerDetalleCuentaType response = null;
		         
		        
			        try {
				            LOGGER.info("Configurando Datos de la peticion");        
				            request.setNroCuenta(nroCuenta);
				
				            LOGGER.info("Invocando servicio");
				            response = port.obtenerDetalleCuenta(request);
			
			        } catch (Exception e) {
			            LOGGER.error("Exception caught on Service invocation", e);
			            LOGGER.error( new DAOException(e));
			        }        
		        
		        String codigoRespuesta = response.getRespuesta().getCodigo();
		        String descripcionRespuesta = response.getRespuesta().getDescripcion();
		        
		        LOGGER.info("codigoRespuestaFULL  " + codigoRespuesta);
		        LOGGER.info("descripcionRespuestaFULL " + descripcionRespuesta);

             if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
        
		            try {      
		                 ArrayList< DocumentoType> documentosDetalleCuenta = new ArrayList<DocumentoType> (response.getDocumento()); 
		                
		               for(DocumentoType doc : documentosDetalleCuenta ){
		            	   if(doc.getNumeroDocumento().equals(nroDocumentoMes)){  
			            	   detalleCuenta.setDocumentoGUID(doc.getDocumentoGUID());
			            	   detalleCuenta.setCantidadPaginas(doc.getCantidadPaginas());
			            	   detalleCuenta.setCantidadSecciones(doc.getCantidadSecciones());
			            	   detalleCuenta.setFechaVencimiento(doc.getFechaVencimiento());
			            	   detalleCuenta.setNumeroDocumento(doc.getNumeroDocumento());
			            	   detalleCuenta.setSaldoPagar(doc.getSaldoPagar());
			            	   detalleCuenta.setTipoDocumento(doc.getTipoDocumento());  
		            	   }
		                }
		            
		            } catch (Exception e) {
		                LOGGER.error("Exception caught on Service response", e);
		                LOGGER.error( new DAOException(e));
		            }	
        
	        } else {
	            LOGGER.error("Service error code received: " + codigoRespuesta
	                    + " - " + descripcionRespuesta);
	            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
	        }
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + BillingProdFacturaService.class, e);
            LOGGER.error( new DAOException(e));
        } 
                
        return  detalleCuenta;      
    } 
    
    /**
     * Obtener bloqueo detalle llamado       
     * @param msisdn    
     * @return 
     * @throws DAOException
     * @throws ServiceException
     */
    public boolean obtenerBloqueoDetalleLlamado(String msisdn) throws DAOException, ServiceException {
  	boolean respuesta = false;
  	
    	ClientePerfilServicePortType port;
    	ResultadoConsultarDetalleCuenta detalleCuenta = new ResultadoConsultarDetalleCuenta(); 
        LOGGER.info("Instanciando el port " + ClientePerfilServicePortType.class);
        try {
        	  port = (ClientePerfilServicePortType) WebServiceLocator.getInstance().getPort(
            		  ClientePerfilService.class, ClientePerfilServicePortType.class);
		        
		     ConsultarBloqueoDetalleLlamadosType  request = new ConsultarBloqueoDetalleLlamadosType();
		     ResultadoConsultarBloqueoDetalleLlamadosType  response = null;
		        
	        try {
		            LOGGER.info("Configurando Datos de la peticion");        
		            request.setMsisdn(msisdn);		
		            LOGGER.info("Invocando servicio");
		            response = port.consultarBloqueoDetalleLlamados(request);
	
	        } catch (Exception e) {
	            LOGGER.error("Exception caught on Service invocation", e);
	            LOGGER.error( new DAOException(e));
	        }        
		        
		        String codigoRespuesta      = response.getRespuesta().getCodigo();
		        String descripcionRespuesta = response.getRespuesta().getDescripcion();
		        
		        LOGGER.info("codigoRespuestaFULL  " + codigoRespuesta);
		        LOGGER.info("descripcionRespuestaFULL " + descripcionRespuesta);

             if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {          
		            try {     
		            	String bloqueo = response.getPermiteVisulizarDetalle();
		            	if(bloqueo!=null){
		            		if(bloqueo.equals("true")){
		            			respuesta=true;
		            		}
		            	}    		                
		            } catch (Exception e) {
		                LOGGER.error("Exception caught on Service response", e);
		                LOGGER.error( new DAOException(e));
		            }	
        
	        } else {
	            LOGGER.error("Service error code received: " + codigoRespuesta
	                    + " - " + descripcionRespuesta);
	            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
	        }
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + BillingProdFacturaService.class, e);
            LOGGER.error( new DAOException(e));
        }                   
        return  respuesta;      
    }     
    
    
}
