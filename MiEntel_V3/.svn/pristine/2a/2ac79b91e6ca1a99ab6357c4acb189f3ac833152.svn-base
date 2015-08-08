/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.bean.AlertaCuotaTraficoBean;
import com.epcs.bean.DetalleMiTraficoSSBean;
import com.epcs.bean.PlanBean;
import com.epcs.bean.ResumenTraficoBean;
import com.epcs.bean.TraficoDatosYMensajes;
import com.epcs.bean.TraficoEnLineaBean;
import com.epcs.bean.TraficoEnLineaPPCCBean;
import com.epcs.billing.gestregistrouso.consultatraficoppycc.Respuestadto;
import com.epcs.billing.gestregistrouso.consultatraficoppycc.Serviciotraficodto;
import com.epcs.billing.gestregistrouso.consultatraficoppycc.Serviciotraficoppcc;
import com.epcs.billing.gestregistrouso.consultatraficoppycc.ServiciotraficoppccService;
import com.epcs.billing.recarga.types.ConsultarHistoricoRecargasType;
import com.epcs.billing.registrouso.BillingRegistroUsoService;
import com.epcs.billing.registrouso.BillingRegistroUsoServicePortType;
import com.epcs.billing.registrouso.dao.util.MiTraficoSSHelper;
import com.epcs.billing.registrouso.dao.util.TraficoSSHelper;
import com.epcs.billing.registrouso.types.ConsultarPlanTraficoSSType;
import com.epcs.billing.registrouso.types.ConsultarTraficoEnLineaPrincipalType;
import com.epcs.billing.registrouso.types.ConsultarTraficoResumenType;
import com.epcs.billing.registrouso.types.DetallePlanType;
import com.epcs.billing.registrouso.types.DetalleTraficoPlanType;
import com.epcs.billing.registrouso.types.DetalleTraficoType;
import com.epcs.billing.registrouso.types.PlanType;
import com.epcs.billing.registrouso.types.ProductoType;
import com.epcs.billing.registrouso.types.RespuestaTraficoEnLineaType;
import com.epcs.billing.registrouso.types.ResultadoConsultarPlanTraficoSSType;
import com.epcs.billing.registrouso.types.ResultadoConsultarTraficoEnLineaPrincipalType;
import com.epcs.billing.registrouso.types.ResultadoConsultarTraficoResumenType;
import com.epcs.cliente.perfil.util.PlanHelper;

import com.epcs.provisionyentrega.suscripcion.datosexcedidos.DatosExcedidosPortType;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.DatosExcedidosService;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.entity.AlertaConsumoType;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.entity.ConsultaHistAlertaConsumoType;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.entity.HeaderInType;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.response.ArrayAlertaConsumoType;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.response.ConsultaHistAlertaConsumoResponseType;
import com.epcs.provisionyentrega.suscripcion.datosexcedidos.response.ResponseAlertaConsumoType;

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
public class TraficoDAO {

	private static final Logger LOGGER = Logger.getLogger(TraficoDAO.class);
	private static final Logger CAJA_TRAFICO_LOGGER = Logger.getLogger("cajaTraficoLog");
	
	private static final String CODIGO_RESPUESTA_OK = MiEntelProperties
			.getProperty("servicios.codigoRespuesta.exito");
	
    public static final String CODIGO_PREFIJO_ENTEL = MiEntelProperties.getProperty("prefijo.entel");
    public static final String CODIGO_APPNAME_PLANES_MM_EXCEDIDOS = MiEntelProperties.getProperty("parametro.servicio.planesMMExcedidos.appName");
    public static final String CODIGO_USERNAME_PLANES_MM_EXCEDIDOS = MiEntelProperties.getProperty("parametro.servicio.planesMMExcedidos.userName");
	

    /**
     * 
     * @param numeroPcs
     * @param autoAtencion
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ResumenTraficoBean getResumenTrafico(String numeroPcs,
            String autoAtencion) throws DAOException, ServiceException {
        ResumenTraficoBean resumenTraficoBean = null;

        BillingRegistroUsoServicePortType port = null;
        BillingRegistroUsoService service = null;
        CAJA_TRAFICO_LOGGER.info("****** Clase: com.epcs.billing.registrouso.dao.TraficoDAO ******");
        CAJA_TRAFICO_LOGGER.info("Instanciando el port");
        try {
            port = (BillingRegistroUsoServicePortType) WebServiceLocator
                    .getInstance().getPort(BillingRegistroUsoService.class,
                            BillingRegistroUsoServicePortType.class);
            service = (BillingRegistroUsoService) WebServiceLocator
            		.getInstance().getService(BillingRegistroUsoService.class, false);            
        } catch (WebServiceLocatorException e) {
            CAJA_TRAFICO_LOGGER.error("Error al inicializar el Port "
                    + BillingRegistroUsoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }

        CAJA_TRAFICO_LOGGER.info("Configurando Datos de la peticion");
        CAJA_TRAFICO_LOGGER.info("****** INICIO INPUT ******");
        ConsultarTraficoResumenType request = new ConsultarTraficoResumenType();
        request.setMsisdn(numeroPcs);
        request.setAutoAtencion(autoAtencion);
        
        CAJA_TRAFICO_LOGGER.info("Msisdn: " + numeroPcs);
        CAJA_TRAFICO_LOGGER.info("AutoAtencion: " + autoAtencion);
        CAJA_TRAFICO_LOGGER.info("****** FIN INPUT ******");

        CAJA_TRAFICO_LOGGER.info("Invocando servicio");
        CAJA_TRAFICO_LOGGER.info("Service name:           " + service.getServiceName());
	    CAJA_TRAFICO_LOGGER.info("WSDL Document Location: " + service.getWSDLDocumentLocation());
        CAJA_TRAFICO_LOGGER.info("Operacion:              ConsultarTraficoResumen");
        
        ResultadoConsultarTraficoResumenType response = null;
        try {
            response = port.consultarTraficoResumen(request);
        } catch (Exception e) {
            CAJA_TRAFICO_LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();
        
        if (Utils.isEmptyString(codigoRespuesta)) {
        	CAJA_TRAFICO_LOGGER.error("Servicio ConsultarTraficoResumen no respondio "
                    + "con codigoRespuesta");
            LOGGER.error( new DAOException("Servicio ConsultarTraficoResumen no respondio "
                    + "con codigoRespuesta"));
        }

        CAJA_TRAFICO_LOGGER.info("****** INICIO OUTPUT ******");
        CAJA_TRAFICO_LOGGER.info("codigoRespuesta:      " + codigoRespuesta);
        CAJA_TRAFICO_LOGGER.info("descripcionRespuesta: " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            List<ProductoType> productosList = response.getProducto();

            try {
                /*
                 * Por cada ProductoType se pregunta a que servicio/producto
                 * corresponde y se asigna al bean de ResumenTraficoBean.
                 */
            	CAJA_TRAFICO_LOGGER.info("****** Trafico de Productos ******");
                resumenTraficoBean = new ResumenTraficoBean();
                for (ProductoType producto : productosList) {
                	CAJA_TRAFICO_LOGGER.info("****** Inicio Producto ******");
                	CAJA_TRAFICO_LOGGER.info("producto.getCodbscs1():      " + producto.getCodbscs1());
                	CAJA_TRAFICO_LOGGER.info("producto.getCodbscs2():      " + producto.getCodbscs2());
                	CAJA_TRAFICO_LOGGER.info("producto.getFechaRegistro(): " + producto.getFechaRegistro());
                	CAJA_TRAFICO_LOGGER.info("producto.getIdProducto():    " + producto.getIdProducto());
                	CAJA_TRAFICO_LOGGER.info("producto.getKey1():          " + producto.getKey1());
                	CAJA_TRAFICO_LOGGER.info("producto.getKey2():          " + producto.getKey2());
                	CAJA_TRAFICO_LOGGER.info("producto.getKey3():          " + producto.getKey3());
                	CAJA_TRAFICO_LOGGER.info("producto.getKey4():          " + producto.getKey4());
                	CAJA_TRAFICO_LOGGER.info("producto.getKey5():          " + producto.getKey5());
                	CAJA_TRAFICO_LOGGER.info("producto.getKey6():          " + producto.getKey6());
                	CAJA_TRAFICO_LOGGER.info("producto.getKey7():          " + producto.getKey7());
                	CAJA_TRAFICO_LOGGER.info("producto.getMsisdn():        " + producto.getMsisdn());
                	CAJA_TRAFICO_LOGGER.info("producto.getNombrePlan():    " + producto.getNombrePlan());
                	CAJA_TRAFICO_LOGGER.info("producto.getTipoPlan():      " + producto.getTipoPlan());
                	CAJA_TRAFICO_LOGGER.info("producto.getTipoTasacion():  " + producto.getTipoTasacion());
                	
                	//Se asigna el codigo Codbscs2
                	resumenTraficoBean.setCodbscs2(producto.getCodbscs2());
                    
                    //Metodo que resuelve el trafico al que pertenece cada producto
                    solveProductoType(resumenTraficoBean, producto);
                    
                    CAJA_TRAFICO_LOGGER.info("****** Fin Producto ******");
                }

                CAJA_TRAFICO_LOGGER.info("****** FIN OUTPUT ******");

            } catch (Exception e) {
                CAJA_TRAFICO_LOGGER.error(
                        "Exception inesperada al recuperar resumen trafico", e);
                LOGGER.error( new DAOException(e));
            }

        }
        else {
        	CAJA_TRAFICO_LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }

        return resumenTraficoBean;
    }

    /**
     * Logica para resolver a que tipo de trafico pertenece la instancia de
     * <code>producto</code> dentro del objeto <code>resumenTraficoBean</code>
     * 
     * @param resumenTraficoBean
     * @param producto
     * @throws Exception
     */
    private void solveProductoType(ResumenTraficoBean resumenTraficoBean,
            ProductoType producto) throws Exception {

        // Trafico Voz
        if (TraficoSSHelper.isProductoVoz(producto)) {
            try {
                resumenTraficoBean = TraficoSSHelper.asignarTraficoVoz(
                        resumenTraficoBean, producto);
            } catch (Exception e) {
                LOGGER.error(
                        "No se pudo obtener trafico de voz para numeroPcs "
                                + producto.getMsisdn(), e);
                LOGGER.error( new DAOException(
                        "No se pudo obtener trafico de voz para numeroPcs "
                                + producto.getMsisdn(), e));
            }
        }

        // Mensajeria
        if (TraficoSSHelper.isProductoMensajes(producto)) {
            resumenTraficoBean = TraficoSSHelper.asignarTraficoMensajes(
                    resumenTraficoBean, producto);
        }

        // Internet Movil
        if (TraficoSSHelper.isProductoInternetMovil(producto)) {
            resumenTraficoBean = TraficoSSHelper.asignarTraficoInternetMovil(
                    resumenTraficoBean, producto);
        }

        // BAM
        if (TraficoSSHelper.isProductoBandaAnchaMovil(producto)) {
            resumenTraficoBean = TraficoSSHelper.asignarTraficoBandaAnchaMovil(
                    resumenTraficoBean, producto);
        }

        // Blackberry
        if (TraficoSSHelper.isProductoBlackberry(producto)) {
            resumenTraficoBean = TraficoSSHelper.asignarTraficoBlackberry(
                    resumenTraficoBean, producto);
        }
    }
    
    /**
     * Devuelve el listado del trafico del usuario
     * @param msisdn del Usuario en sesion
     * @throws DAOException
     * @return List<TraficoEnLineaBean>
     */
    public List<TraficoEnLineaPPCCBean> getTraficoEnLineaPPCC(String msisdn, String pagina) throws DAOException, ServiceException {

    	//List<TraficoEnLineaPPCCBean> listTraficoEnLineaPPCC = new LinkedList<TraficoEnLineaPPCCBean>();
    	List<TraficoEnLineaPPCCBean> listTraficoEnLineaPPCC = new ArrayList<TraficoEnLineaPPCCBean>();
        
    	Serviciotraficoppcc port = null;
    	LOGGER.info("Instanciando el port");
        try {
            port = (Serviciotraficoppcc) WebServiceLocator
                    .getInstance().getPort(ServiciotraficoppccService.class,
                    		Serviciotraficoppcc.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + Serviciotraficoppcc.class, e);
            LOGGER.error( new DAOException(e));
        }

        LOGGER.info("Configurando Datos de la peticion");
        LOGGER.info("Misdn :" + msisdn);
        LOGGER.info("Pagina :" + pagina);

        LOGGER.info("Invocando servicio");
        Respuestadto response = null;
                
        try {
            response = port.consultaTraficoPPCC(msisdn, pagina);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }
        
        ArrayList<Serviciotraficodto> listTrafLinea =  new ArrayList<Serviciotraficodto>(response.getTrafico());
        
            for (Serviciotraficodto serviciotraficodto : listTrafLinea) {

                try {
                    TraficoEnLineaPPCCBean traficoEnLineaPPCCBean = createTraficoEnLineaPPCCBean(serviciotraficodto);
                    listTraficoEnLineaPPCC.add(traficoEnLineaPPCCBean);
                } catch (Exception e) {
                    LOGGER.error("Exception caught al obtener Trafico "
                            + "en linea desde response de servicio", e);
                    LOGGER.error( new DAOException(e));
                }
            }
        return listTraficoEnLineaPPCC;

    }
    
    /**
     * Metodo utilitario para instanciar objetos {@link TraficoEnLineaPPCCBean} a
     * partir de una instancia de {@link Serviciotraficodto}.<br>
     * 
     * @param serviciotraficodto
     * @return {@link TraficoEnLineaPPCCBean}
     * @throws Exception
     */
    private TraficoEnLineaPPCCBean createTraficoEnLineaPPCCBean(final Serviciotraficodto serviciotraficodto) throws Exception {

        TraficoEnLineaPPCCBean traficoEnLineaPPCCBean = new TraficoEnLineaPPCCBean();
        traficoEnLineaPPCCBean.setCosto(serviciotraficodto.getCosto());
        traficoEnLineaPPCCBean.setDestino(serviciotraficodto.getDestino());
        traficoEnLineaPPCCBean.setDuracion(serviciotraficodto.getDuracion());
        traficoEnLineaPPCCBean.setFechaLlamada(serviciotraficodto.getFechaLlamada());
        traficoEnLineaPPCCBean.setHoraLlamada(serviciotraficodto.getHoraLlamada());
        traficoEnLineaPPCCBean.setPaginas(serviciotraficodto.getPaginas());
        traficoEnLineaPPCCBean.setSaldo(serviciotraficodto.getSaldo());
        traficoEnLineaPPCCBean.setTipoServicio(serviciotraficodto.getTipoServicio());
        traficoEnLineaPPCCBean.setUnidad(serviciotraficodto.getUnidad());

        return traficoEnLineaPPCCBean;
    }
    
    /**
     * Devuelve el listado del trafico del usuario
     * @param msisdn del Usuario en sesion
     * @throws DAOException
     * @return List<TraficoEnLineaBean>
     */
    public List<TraficoEnLineaBean> getTraficoEnLinea(String msisdn)
            throws DAOException, ServiceException {
        List<TraficoEnLineaBean> listTraficoEnLinea = new LinkedList<TraficoEnLineaBean>();
        BillingRegistroUsoServicePortType port = null;
        LOGGER.info("Instanciando el port");
        try {
            port = (BillingRegistroUsoServicePortType) WebServiceLocator
                    .getInstance().getPort(BillingRegistroUsoService.class,
                            BillingRegistroUsoServicePortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + BillingRegistroUsoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }

        LOGGER.info("Configurando Datos de la peticion");
        LOGGER.info("Misdn :" + msisdn);

        String fechafinal = DateHelper.format(new java.util.Date(),
                DateHelper.FORMAT_yyyyMMddhhmmss);
        LOGGER.info("fecha final :" + fechafinal);
        String fechainicial = DateHelper.format(DateHelper.addDays(
                new java.util.Date(), -2), DateHelper.FORMAT_yyyyMMddhhmmss);
        LOGGER.info("fecha inicial :" + fechainicial);

        ConsultarTraficoEnLineaPrincipalType request = new ConsultarTraficoEnLineaPrincipalType();
        request.setMsisdn(msisdn);
        request.setFechaInicial(fechainicial);
        request.setFechaFinal(fechafinal);

        LOGGER.info("Invocando servicio");
        ResultadoConsultarTraficoEnLineaPrincipalType response = null;
        try {
            response = port.consultarTraficoEnLineaPrincipal(request);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(response.getRespuesta()
                .getCodigo())) {
            List<RespuestaTraficoEnLineaType> listTrafLinea = response
                    .getRespuestaTraficoEnLinea();

            for (RespuestaTraficoEnLineaType respuestaTraficoEnLineaType : listTrafLinea) {

                try {
                    TraficoEnLineaBean traficoEnLineaBean = createTraficoEnLineaBean(respuestaTraficoEnLineaType);
                    listTraficoEnLinea.add(traficoEnLineaBean);
                } catch (Exception e) {
                    LOGGER.error("Exception caught al obtener Trafico "
                            + "en linea desde response de servicio", e);
                    LOGGER.error( new DAOException(e));
                }
            }
           
        }
        else {
            LOGGER
                    .error("Respuesta del Servicio :"+codigoRespuesta+ "Descripcion:"+descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta,descripcionRespuesta));
        }

        return listTraficoEnLinea;

    }
    
    /**
     * Metodo utilitario para instanciar objetos {@link TraficoEnLineaBean} a
     * partir de una instancia de {@link RespuestaTraficoEnLineaType}.<br>
     * 
     * @param respuestaTraficoEnLineaType
     * @return {@link TraficoEnLineaBean}
     * @throws Exception
     */
    private TraficoEnLineaBean createTraficoEnLineaBean(
            final RespuestaTraficoEnLineaType respuestaTraficoEnLineaType)
            throws Exception {

        TraficoEnLineaBean traficoEnLineaBean = new TraficoEnLineaBean();
        traficoEnLineaBean.setDestinatario(respuestaTraficoEnLineaType
                .getDestino());
        traficoEnLineaBean
                .setDescripcionTipoTrafico(respuestaTraficoEnLineaType
                        .getDescTipoLlamada());
        traficoEnLineaBean.setTipoTrafico(respuestaTraficoEnLineaType
                .getFlagTipoTrafico());
        traficoEnLineaBean.setValor(Double
                .parseDouble(respuestaTraficoEnLineaType.getCosto()));
        LOGGER.info("hora ini:" + respuestaTraficoEnLineaType.getHoraInicio());
        if ("VOZ".equals(respuestaTraficoEnLineaType.getFlagTipoTrafico())) {
            traficoEnLineaBean.setConsumoTraficoVoz(Double
                    .parseDouble(respuestaTraficoEnLineaType.getDuracion()));
        }
        else {
            traficoEnLineaBean.setConsumoTraficoDato(Integer
                    .parseInt(respuestaTraficoEnLineaType.getDuracion()));
        }
        try {

            traficoEnLineaBean.setFecha(DateHelper.parseDate(
                    respuestaTraficoEnLineaType.getFechaInicio() + " "
                            + respuestaTraficoEnLineaType.getHoraInicio(),
                    "dd.MM.yy hh:mm:ss"));
        } catch (Exception e) {
            LOGGER.error("El formato de la fecha no es valido : fecha:"
                    + respuestaTraficoEnLineaType.getFechaInicio()
                    + " fromato: dd.MM.yy hh:mm:ss", e);
            LOGGER.error( new DAOException(
                    "El formato de la fecha no es valido : fecha:"
                            + respuestaTraficoEnLineaType.getFechaInicio()
                            + " fromato: dd.MM.yy hh:mm:ss", e));
        }

        return traficoEnLineaBean;
    }
    
    
    /**
     * 
     * @param numeroPcs
     * @param autoAtencion
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<DetalleMiTraficoSSBean> obtenerMiTrafico(String numeroPcs, String periodo, String mercado, boolean logCajaTrafico) throws DAOException, ServiceException {
        List<DetalleMiTraficoSSBean> listdetalleMiTraficoSSBean= new LinkedList<DetalleMiTraficoSSBean>();
        BillingRegistroUsoServicePortType port = null;
        BillingRegistroUsoService service = null;
        if(logCajaTrafico){
        	CAJA_TRAFICO_LOGGER.info("Clase: com.epcs.billing.registrouso.dao.TraficoDAO");
        	CAJA_TRAFICO_LOGGER.info("Instanciando el port");
        }else{
        LOGGER.info("Instanciando el port");
        }
        try {
            port = (BillingRegistroUsoServicePortType) WebServiceLocator
                    .getInstance().getPort(BillingRegistroUsoService.class,
                            BillingRegistroUsoServicePortType.class);
            service = (BillingRegistroUsoService) WebServiceLocator
    				.getInstance().getService(BillingRegistroUsoService.class, false);
		    
        } catch (WebServiceLocatorException e) {
        	if(logCajaTrafico)
        		CAJA_TRAFICO_LOGGER.error("Error al inicializar el Port "
                        + BillingRegistroUsoServicePortType.class, e);
        	else
            LOGGER.error("Error al inicializar el Port "
                    + BillingRegistroUsoServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }

        if(logCajaTrafico)
        	CAJA_TRAFICO_LOGGER.info("Configurando Datos de la peticion");
        else
        LOGGER.info("Configurando Datos de la peticion");
        
        ConsultarPlanTraficoSSType request = new ConsultarPlanTraficoSSType();
        ResultadoConsultarPlanTraficoSSType response = null;
        try {
            
        	if(logCajaTrafico){
        		CAJA_TRAFICO_LOGGER.info("****** INICIO INPUT ******");
        		CAJA_TRAFICO_LOGGER.info("Msisdn: " + numeroPcs );
            	CAJA_TRAFICO_LOGGER.info("PeriodoConsulta: " + periodo );
            	CAJA_TRAFICO_LOGGER.info("****** FIN INPUT ******");
        	}else{
        	LOGGER.info("TraficoDAO numeroPcs :::::" + numeroPcs );
        	LOGGER.info("TraficoDAO periodo :::::::" + periodo );
        	}
        	
            request.setMsisdn(numeroPcs);
            request.setPeriodoConsulta(periodo);
            
            if(logCajaTrafico){
            	CAJA_TRAFICO_LOGGER.info("Invocando servicio");
            	CAJA_TRAFICO_LOGGER.info("Service name: " + service.getServiceName());
    		    CAJA_TRAFICO_LOGGER.info("WSDL Document Location: " + service.getWSDLDocumentLocation());
                CAJA_TRAFICO_LOGGER.info("Operacion: ConsultarPlanTraficoSS");
            }else{
            	LOGGER.info("Invocando servicio");
            }
            
            response = port.consultarPlanTraficoSS(request);
        } catch (Exception e) {
        	if(logCajaTrafico){
        		CAJA_TRAFICO_LOGGER.error("Exception caught on Service invocation", e);
        	}else{
            LOGGER.error("Exception caught on Service invocation", e);
        	}
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = response.getRespuesta().getCodigo();
        String descripcionRespuesta = response.getRespuesta().getDescripcion();

        if (Utils.isEmptyString(codigoRespuesta)) {
        	CAJA_TRAFICO_LOGGER.error("Servicio ConsultarPlanTraficoSS no respondio "
                    + "con codigoRespuesta");
            LOGGER.error( new DAOException("Servicio ConsultarPlanTraficoSS no respondio "
                    + "con codigoRespuesta"));
        }

        if(logCajaTrafico){
        	CAJA_TRAFICO_LOGGER.info("****** INICIO OUTPUT ******");
    		CAJA_TRAFICO_LOGGER.info("codigoRespuesta: " + codigoRespuesta);
    		CAJA_TRAFICO_LOGGER.info("descripcionRespuesta: " + descripcionRespuesta);
        }else{
        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
        }

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
        	

            try {
            	
            	if(!logCajaTrafico)
            	LOGGER.info("TraficoDAO ::::::::::::: CODIGO_RESPUESTA_OK ::::::::::: ");
                
            	List<PlanType> planTypeList = response.getPlanTrafico();
                PlanBean planBean;
                
                if(!logCajaTrafico)
                	LOGGER.info("response.getPlanTrafico() >>>>" + response.getPlanTrafico() );
                
                for (PlanType plan : planTypeList) {
                	
                	planBean = new PlanBean();
                	
                    DetalleMiTraficoSSBean detalleMiTraficoSSBean = new DetalleMiTraficoSSBean();
                    
                    DetallePlanType detallePlanType = plan.getPlan();
	
                    if(logCajaTrafico){
                    	CAJA_TRAFICO_LOGGER.info("****** Incio Plan Actual ******");
	                    CAJA_TRAFICO_LOGGER.info("detallePlanType.getTipoPlan() " + detallePlanType.getTipoPlan());
	                    CAJA_TRAFICO_LOGGER.info("detallePlanType.getCodbscs1() " + detallePlanType.getCodbscs1());
	                    CAJA_TRAFICO_LOGGER.info("detallePlanType.getCodbscs2() " + detallePlanType.getCodbscs2());
	                    CAJA_TRAFICO_LOGGER.info("detallePlanType.getNombrePlan() " + detallePlanType.getNombrePlan());
	                    CAJA_TRAFICO_LOGGER.info("detallePlanType.getTipoTasacion() " + detallePlanType.getTipoTasacion());
	                    CAJA_TRAFICO_LOGGER.info("TraficoDAO MERCADO " + mercado);
	                    CAJA_TRAFICO_LOGGER.info("detallePlanType.getTotalMinutos() " + detallePlanType.getTotalMinutos() );
	                    CAJA_TRAFICO_LOGGER.info("detallePlanType.getTotalMinutosAdicional() " + detallePlanType.getTotalMinutosAdicional());
	                    CAJA_TRAFICO_LOGGER.info("detallePlanType.getCargoFijoPlan() " + detallePlanType.getCargoFijoPlan() );
	                    CAJA_TRAFICO_LOGGER.info("detallePlanType.getDescIMovil() " + detallePlanType.getDescIMovil() );
	                    CAJA_TRAFICO_LOGGER.info("detallePlanType.getLimiteIMovil() " + detallePlanType.getLimiteIMovil());
	                    CAJA_TRAFICO_LOGGER.info("****** Fin Plan Actual ******");
                    }else{
                    	LOGGER.info("detallePlanType.getTipoPlan() ::::::" + detallePlanType.getTipoPlan());
                        LOGGER.info("detallePlanType.getCodbscs1() ::::::" + detallePlanType.getCodbscs1());
                        LOGGER.info("detallePlanType.getCodbscs2() ::::::" + detallePlanType.getCodbscs2());
                        LOGGER.info("detallePlanType.getNombrePlan() ::::" + detallePlanType.getNombrePlan());
                        LOGGER.info("detallePlanType.getTipoTasacion() ::" + detallePlanType.getTipoTasacion());
                    LOGGER.info("TraficoDAO MERCADO ::::" + mercado);
                        LOGGER.info("detallePlanType.getTotalMinutos() :::" + detallePlanType.getTotalMinutos() );
                        LOGGER.info("detallePlanType.getTotalMinutosAdicional() :::" + detallePlanType.getTotalMinutosAdicional());
                        LOGGER.info("detallePlanType.getCargoFijoPlan() ::::" + detallePlanType.getCargoFijoPlan() );
                        LOGGER.info("detallePlanType.getDescIMovil() ::::" + detallePlanType.getDescIMovil() );
                        LOGGER.info("detallePlanType.getLimiteIMovil() ::" + detallePlanType.getLimiteIMovil());
                    }
                    
                    planBean.setTipoPlan(detallePlanType.getTipoPlan());
                    planBean.setCodbscs1(detallePlanType.getCodbscs1());
                    planBean.setCodbscs2(detallePlanType.getCodbscs2());
                    planBean.setNombrePlan(PlanHelper.extraerNombrePlan(detallePlanType.getNombrePlan()));
                    planBean.setTipoTasacion(detallePlanType.getTipoTasacion());
                    planBean.setTipoMercado(mercado);
                    planBean.setTotalMinutos(PlanHelper.convertirSegundosaMinutos(detallePlanType.getTotalMinutos()));
                    planBean.setTotalMinutosAdicional(PlanHelper.convertirSegundosaMinutos(detallePlanType.getTotalMinutosAdicional()));
                    planBean.setCargoFijoPlan(Double.parseDouble(detallePlanType.getCargoFijoPlan()));
                    planBean.setDescIMovil(!Utils.isEmptyString(detallePlanType.getDescIMovil()) ? detallePlanType.getDescIMovil() : "");
                    planBean.setLimiteIMovil(!Utils.isEmptyString(detallePlanType.getLimiteIMovil()) ? detallePlanType.getLimiteIMovil() : "");
                    planBean.setListaTasaciones(PlanHelper.construirDetalleTasacionesPlanTraficoSSCC(detallePlanType.getDetallePlanActual(),detallePlanType.getTipoPlan(),detallePlanType.getTipoTasacion()));
                    
                    DetalleTraficoType detalleTraficoType = plan.getDetalleTrafico();
                    
                    if(logCajaTrafico)
                    	CAJA_TRAFICO_LOGGER.info("****** Inicio Detalle Trafico ******");
                    
                    for (DetalleTraficoPlanType detalleTraficoPlanType :  detalleTraficoType.getItemDetalleTraficoType()){
                    	
	                    if(logCajaTrafico){
	                    	CAJA_TRAFICO_LOGGER.info("****** Incio Item Detalle Trafico ******");
    	                    CAJA_TRAFICO_LOGGER.info("detalleTraficoPlanType.getIdProducto() " + detalleTraficoPlanType.getIdProducto());
    	                    CAJA_TRAFICO_LOGGER.info("detalleTraficoPlanType.getFechaInicioTraf() " + detalleTraficoPlanType.getFechaInicioTraf());
    	                    CAJA_TRAFICO_LOGGER.info("detalleTraficoPlanType.getFechaFinTraf() " + detalleTraficoPlanType.getFechaFinTraf());
	                    	CAJA_TRAFICO_LOGGER.info("detalleTraficoPlanType.getNombreTasacionPro1() " + detalleTraficoPlanType.getNombreTasacionPro1());
		                    CAJA_TRAFICO_LOGGER.info("detalleTraficoPlanType.getValorTasacionPro1() " + detalleTraficoPlanType.getValorTasacionPro1());
	                    	CAJA_TRAFICO_LOGGER.info("detalleTraficoPlanType.getNombreTasacionPro2() " + detalleTraficoPlanType.getNombreTasacionPro2());
		                    CAJA_TRAFICO_LOGGER.info("detalleTraficoPlanType.getValorTasacionPro2() " + detalleTraficoPlanType.getValorTasacionPro2());
	                    	CAJA_TRAFICO_LOGGER.info("detalleTraficoPlanType.getNombreTasacionPro3() " + detalleTraficoPlanType.getNombreTasacionPro3());
		                    CAJA_TRAFICO_LOGGER.info("detalleTraficoPlanType.getValorTasacionPro3() " + detalleTraficoPlanType.getValorTasacionPro3());
		                    CAJA_TRAFICO_LOGGER.info("detalleTraficoPlanType.getNombreTasacionPro4() " + detalleTraficoPlanType.getNombreTasacionPro4());
		                    CAJA_TRAFICO_LOGGER.info("detalleTraficoPlanType.getValorTasacionPro4() " + detalleTraficoPlanType.getValorTasacionPro4());
                    	}else{
                    		LOGGER.info("FOR DE DetalleTraficoPlanType N -->"+ detalleTraficoType.getItemDetalleTraficoType().size());
                    		LOGGER.info("detalleTraficoPlanType.getIdProducto() ::::::::::::" + detalleTraficoPlanType.getIdProducto());
    	                    LOGGER.info("detalleTraficoPlanType.getFechaInicioTraf() :::::::" + detalleTraficoPlanType.getFechaInicioTraf());
    	                    LOGGER.info("detalleTraficoPlanType.getFechaFinTraf() ::::::::::" + detalleTraficoPlanType.getFechaFinTraf());
                    		LOGGER.info("detalleTraficoPlanType.getNombreTasacionPro1() ::::" + detalleTraficoPlanType.getNombreTasacionPro1());
		                    LOGGER.info("detalleTraficoPlanType.getValorTasacionPro1() :::::" + detalleTraficoPlanType.getValorTasacionPro1());
                    		LOGGER.info("detalleTraficoPlanType.getNombreTasacionPro2() ::::" + detalleTraficoPlanType.getNombreTasacionPro2());
		                    LOGGER.info("detalleTraficoPlanType.getValorTasacionPro2() :::::" + detalleTraficoPlanType.getValorTasacionPro2());
                    		LOGGER.info("detalleTraficoPlanType.getNombreTasacionPro3() ::::" + detalleTraficoPlanType.getNombreTasacionPro3());
		                    LOGGER.info("detalleTraficoPlanType.getValorTasacionPro3() :::::" + detalleTraficoPlanType.getValorTasacionPro3());
		                    LOGGER.info("detalleTraficoPlanType.getNombreTasacionPro4() ::::" + detalleTraficoPlanType.getNombreTasacionPro4());
		                    LOGGER.info("detalleTraficoPlanType.getValorTasacionPro4() :::::" + detalleTraficoPlanType.getValorTasacionPro4());
                    	}

	                    solvePlanType(detalleMiTraficoSSBean, detalleTraficoPlanType,detallePlanType, logCajaTrafico);
	                    
	                    if(logCajaTrafico)
	                    	CAJA_TRAFICO_LOGGER.info("****** Fin Item Detalle Trafico ******");
	                    
                    }
                    
                    if(logCajaTrafico)
                    	CAJA_TRAFICO_LOGGER.info("****** Fin Detalle Trafico ******");
                    
                    if(!logCajaTrafico){
                    LOGGER.info("TraficoDAO FINALIZA solvePlanType");
                	LOGGER.info(":::::::::::::::::::::::::::::::::::::::::::::::::");
                    }
                   
                    detalleMiTraficoSSBean.setPlanBean(planBean);
                    listdetalleMiTraficoSSBean.add(detalleMiTraficoSSBean);
                    
                }

                if(logCajaTrafico)
                	CAJA_TRAFICO_LOGGER.info("****** FIN OUTPUT ******");

            } catch (Exception e) {
            	if(logCajaTrafico)
                    CAJA_TRAFICO_LOGGER.error(
                            "Exception inesperada al recuperar resumen trafico", e);
            	else
                LOGGER.error(
                        "Exception inesperada al recuperar resumen trafico", e);
                LOGGER.error( new DAOException(e));
            }

        }
        else {
        	if(logCajaTrafico)
                CAJA_TRAFICO_LOGGER.error("Service error code received: " + codigoRespuesta
                        + " - " + descripcionRespuesta);
        	else
            LOGGER.error("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
        	
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        
        
        return listdetalleMiTraficoSSBean;
    }
    
    /**
     * 
     * @param detalleMiTraficoSSBean
     * @param detalleTraficoPlanType
     * @param detallePlanType
     * @throws Exception
     */
    private void solvePlanType(DetalleMiTraficoSSBean detalleMiTraficoSSBean,
            DetalleTraficoPlanType detalleTraficoPlanType,DetallePlanType detallePlanType, boolean logCajaTrafico) throws Exception {
    	
    	
        detalleTraficoPlanType.setIdProducto(detalleTraficoPlanType.getIdProducto().replaceAll("\n", ""));
        // Trafico Voz
        if (MiTraficoSSHelper.isProductoVoz(detalleTraficoPlanType.getIdProducto())) {
            try{           
            	
            	if(logCajaTrafico){
            		CAJA_TRAFICO_LOGGER.info(">>>>>>>>>>> es Producto Voz");
            	}else{
            	LOGGER.info("TraficoDAO solvePlanType  >>>> isProductoVoz :::::");
            	LOGGER.info(":::::::::::::::::::::::::::::::::::::::::::::::::");
            	}
            	
                detalleMiTraficoSSBean.setTraficoVoz(MiTraficoSSHelper.buildTraficoVoz(detallePlanType.getTipoPlan(), detallePlanType.getCodbscs2(), detalleTraficoPlanType));
                detalleMiTraficoSSBean.setFecha(DateHelper.parseDate(detalleTraficoPlanType.getFechaInicioTraf(), DateHelper.FORMAT_ddMMyyyy_HYPHEN));
                detalleMiTraficoSSBean.setFechaFin(DateHelper.parseDate(detalleTraficoPlanType.getFechaFinTraf(), DateHelper.FORMAT_ddMMyyyy_HYPHEN));
            } catch (Exception e) {
                if(logCajaTrafico)
            		CAJA_TRAFICO_LOGGER.error("No se pudo obtener trafico de voz ", e);
            	else
            		LOGGER.error("No se pudo obtener trafico de voz ", e);
                
                LOGGER.error( new DAOException("No se pudo obtener trafico de voz " , e));
            }
        }

        // Mensajeria
        if (MiTraficoSSHelper.isProductoMensajes(detalleTraficoPlanType.getIdProducto())) 
        {
        	if(logCajaTrafico){
        		CAJA_TRAFICO_LOGGER.info("TraficoDAO >>>> es Producto Mensajes");
        	}else{
        	LOGGER.info("TraficoDAO solvePlanType  >>>> isProductoMensajes :::::");
        	LOGGER.info(":::::::::::::::::::::::::::::::::::::::::::::::::");
        	}
        	
            detalleMiTraficoSSBean.getTraficoMensajes().addAll((MiTraficoSSHelper.asignarTraficoMensajes(detalleTraficoPlanType)));
        }

        // Internet Movil
        if (MiTraficoSSHelper.isProductoInternetMovil(detalleTraficoPlanType.getIdProducto())) {
        	
        	if(logCajaTrafico){
        		CAJA_TRAFICO_LOGGER.info("TraficoDAO >>>> es Producto InternetMovil");
        	}else{
        	LOGGER.info("TraficoDAO solvePlanType  >>>> isProductoInternetMovil :::::");
        	LOGGER.info(":::::::::::::::::::::::::::::::::::::::::::::::::");
        	}
        	
            TraficoDatosYMensajes traficoDatosYMensajes = MiTraficoSSHelper.asignarTraficoInternetMovil(detalleTraficoPlanType);
            detalleMiTraficoSSBean.getTraficoDatos().add(traficoDatosYMensajes);
        }

        // BAM
        if (MiTraficoSSHelper.isProductoBandaAnchaMovil(detalleTraficoPlanType.getIdProducto())) {
        	if(logCajaTrafico){
        		CAJA_TRAFICO_LOGGER.info("TraficoDAO >>>> es Producto Banda Ancha Movil");
        	}else{
        	LOGGER.info("TraficoDAO solvePlanType  >>>> isProductoBandaAnchaMovil :::::");
        	LOGGER.info(":::::::::::::::::::::::::::::::::::::::::::::::::");
        	}
        	
            TraficoDatosYMensajes traficoDatosYMensajes = MiTraficoSSHelper.asignarTraficoBandaAnchaMovil(detalleTraficoPlanType);
            detalleMiTraficoSSBean.getTraficoDatos().add(traficoDatosYMensajes);
        }

        // Blackberry
        if (MiTraficoSSHelper.isProductoBlackberry(detalleTraficoPlanType.getIdProducto())) {
        	if(logCajaTrafico){
        		CAJA_TRAFICO_LOGGER.info("TraficoDAO >>>> es Producto Blackberry");
        	}else{
        	LOGGER.info("TraficoDAO solvePlanType  >>>> isProductoBlackberry :::::");
        	LOGGER.info(":::::::::::::::::::::::::::::::::::::::::::::::::");
        	}
        	
            TraficoDatosYMensajes traficoDatosYMensajes = MiTraficoSSHelper.asignarTraficoBlackberry(detalleTraficoPlanType);
            detalleMiTraficoSSBean.getTraficoDatos().add(traficoDatosYMensajes);
        }

    }
    
   
    /**
	 * Devuelve historia de alertas de cuota de trafico
     * @author Wilson Brochero Munoz
     * @param  event    
     * @return List<AlertaCuotaTraficoBean>
     * @throws Exception
     * @throws ServiceException
     * @throws DAOException
     */
    public List<AlertaCuotaTraficoBean> getHistoricoAlertaCuotaTrafico(String msisdn) throws DAOException, ServiceException {

    	List<AlertaCuotaTraficoBean> listAlertaCuotaTrafico = new ArrayList<AlertaCuotaTraficoBean>();
        
    	DatosExcedidosPortType port = null;
    	LOGGER.info("Instanciando el port");
        try {
            port = (DatosExcedidosPortType) WebServiceLocator
                    .getInstance().getPort(DatosExcedidosService.class,
                    		DatosExcedidosPortType.class);
        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + DatosExcedidosPortType.class, e);
            LOGGER.error( new DAOException(e));
        }

        LOGGER.info("Configurando Datos de la peticion");
        LOGGER.info("Misdn :" + msisdn);        

        LOGGER.info("Invocando servicio");
        ConsultaHistAlertaConsumoResponseType response = null;
        ConsultaHistAlertaConsumoType request = new ConsultaHistAlertaConsumoType();
        
        HeaderInType headerInType = new HeaderInType();
        headerInType.setAppName(CODIGO_APPNAME_PLANES_MM_EXCEDIDOS);
	    headerInType.setUserName(CODIGO_USERNAME_PLANES_MM_EXCEDIDOS);
	    request.setHeaderIn(headerInType);
	    request.setMovil(CODIGO_PREFIJO_ENTEL+msisdn);
     
        try {
            response = port.consultaHistAlertaConsumo(request);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation", e);
            LOGGER.error( new DAOException(e));
        }
        
        String codigoRespuesta = response.getReturn().getCodigo();
  	    String descripcionRespuesta = response.getReturn().getDescCodigo();

        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
          
        if(response!=null){
        	  
        	if(response.getReturn().getCodigo().equals(CODIGO_RESPUESTA_OK)){
        		
        		ResponseAlertaConsumoType responseAlertaConsumoType = response.getReturn();
        		ArrayAlertaConsumoType  arrayAlertaConsumoType = responseAlertaConsumoType.getAlertaConsumo();
        		List<AlertaConsumoType> listAlertaConsumo = new ArrayList<AlertaConsumoType>(arrayAlertaConsumoType.getItem());
        	
        		 for (AlertaConsumoType alertaConsumoType : listAlertaConsumo) {
        			 AlertaCuotaTraficoBean alertaCuotaTraficoBean = new AlertaCuotaTraficoBean();
        			 alertaCuotaTraficoBean.setTxtSMS(alertaConsumoType.getTxtSMS());
        			 alertaCuotaTraficoBean.setConsumoMBAlerta(alertaConsumoType.getConsumoMBAlerta());
        			 alertaCuotaTraficoBean.setMovil(alertaConsumoType.getMovil()); 
					try {
						
						alertaCuotaTraficoBean
								.setFechaEnvioSMS(DateHelper
										.parseDate(
												alertaConsumoType
														.getFechaEnvioSMS(),
												DateHelper.FORMAT_yyyyMMddhhmmss));
						alertaCuotaTraficoBean
								.setFechaEnvioSMSFormat(DateHelper
										.format(
												DateHelper
														.parseDate(
																alertaConsumoType
																		.getFechaEnvioSMS(),
																DateHelper.FORMAT_yyyyMMddhhmmss),
												DateHelper.FORMAT_ddMMyyyy_HHmmss_SLASH));
					} catch (NumberFormatException nfe) {
						LOGGER.warn("Error formateando fecha envio SMS :"
								+ alertaConsumoType.getFechaEnvioSMS()
								+ nfe.getMessage());
					}
                     listAlertaCuotaTrafico.add(alertaCuotaTraficoBean);
				}
        	} else {
                LOGGER
                .error("Respuesta del Servicio : "+codigoRespuesta+ " Descripcion: "+descripcionRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta,descripcionRespuesta));
        	}
        }
        return listAlertaCuotaTrafico;

    }
    
    
    
    
}