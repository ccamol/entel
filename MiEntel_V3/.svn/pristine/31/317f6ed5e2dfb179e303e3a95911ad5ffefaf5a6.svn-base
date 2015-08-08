/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.bean.ResumenTraficoBAMCCBean;
import com.epcs.bean.ResumenTraficoBamBean;

import com.epcs.bean.TraficoBamProductoBean;
import com.epcs.billing.registrouso.BillingRegistroUsoService;
import com.epcs.billing.registrouso.BillingRegistroUsoServicePortType;
import com.epcs.billing.registrouso.types.ConsultarTraficoBAMCCType;
import com.epcs.billing.registrouso.types.ConsultarTraficoSSBAMType;
import com.epcs.billing.registrouso.types.DetallePlanesBAMType;
import com.epcs.billing.registrouso.types.DetalleTraficoBAMCCType;
import com.epcs.billing.registrouso.types.ProductoType;
import com.epcs.billing.registrouso.types.ResultadoConsultarTraficoBAMCCType;
import com.epcs.billing.registrouso.types.ResultadoConsultarTraficoSSBAMType;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jroman (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoBamDAO {

    private static final Logger LOGGER = Logger.getLogger(TraficoBamDAO.class);
    private static final Logger CAJA_TRAFICO_LOGGER = Logger.getLogger("cajaTraficoLog");
    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties.getProperty("servicios.codigoRespuesta.exito");
   
    /**
     * 
     * @param numeroPcs
     * @param autoAtencion
     * @param mercado
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ResumenTraficoBamBean getResumenTraficoBam(String numeroPcs,String mercado, String autoAtencion, boolean logCajaTrafico) throws DAOException, ServiceException {
        ResumenTraficoBamBean resumenTraficoBamBean = null;

        BillingRegistroUsoServicePortType port = null;
        BillingRegistroUsoService service = null;
        
        if(logCajaTrafico){
        	CAJA_TRAFICO_LOGGER.info("****** Clase: com.epcs.billing.registrouso.dao.TraficoBamDAO ******");
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
        	if(logCajaTrafico){
            	CAJA_TRAFICO_LOGGER.error("Error al inicializar el Port "
                        + BillingRegistroUsoServicePortType.class, e);
            }else{
            LOGGER.error("Error al inicializar el Port "
                    + BillingRegistroUsoServicePortType.class, e);
            }
            LOGGER.error( new DAOException(e));
        }

        if(logCajaTrafico){
        	CAJA_TRAFICO_LOGGER.info("Configurando Datos de la peticion");
        	CAJA_TRAFICO_LOGGER.info("****** INICIO INPUT ******");
        	CAJA_TRAFICO_LOGGER.info("Msisdn: " + numeroPcs);
            CAJA_TRAFICO_LOGGER.info("AutoAtencion: " + autoAtencion);
            CAJA_TRAFICO_LOGGER.info("Mercado: " + mercado);
            CAJA_TRAFICO_LOGGER.info("****** FIN INPUT ******");
        }else{
        LOGGER.info("Configurando Datos de la peticion");
        }
        
        ConsultarTraficoSSBAMType request = new ConsultarTraficoSSBAMType();
        request.setMsisdn(numeroPcs);
        request.setAaa(autoAtencion);
        request.setMercado(mercado);

        if(logCajaTrafico){
        	CAJA_TRAFICO_LOGGER.info("Invocando servicio");
            CAJA_TRAFICO_LOGGER.info("Service name:           " + service.getServiceName());
    	    CAJA_TRAFICO_LOGGER.info("WSDL Document Location: " + service.getWSDLDocumentLocation());
            CAJA_TRAFICO_LOGGER.info("Operacion:              ConsultarTraficoSSBAM");
        }else{
        LOGGER.info("Invocando servicio");
        }
        
        ResultadoConsultarTraficoSSBAMType response = null;

        try {
            response = port.consultarTraficoSSBAM(request);
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
        	CAJA_TRAFICO_LOGGER.error("Servicio ConsultarTraficoSSBAM no respondio "
                    + "con codigoRespuesta");
            LOGGER.error( new DAOException("Servicio ConsultarTraficoSSBAM no respondio "
                    + "con codigoRespuesta"));
        }

        if(logCajaTrafico){
        	CAJA_TRAFICO_LOGGER.info("****** INICIO OUTPUT ******");
            CAJA_TRAFICO_LOGGER.info("codigoRespuesta:      " + codigoRespuesta);
            CAJA_TRAFICO_LOGGER.info("descripcionRespuesta: " + descripcionRespuesta);
        }else{
        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
        }

        
        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

            List<ProductoType> productosList = response.getProducto();

            try {
                /*
                 * Por cada ProductoType se pregunta a que servicio/producto
                 * corresponde y se asigna al bean de ResumenFacturacionBean.
                 */
                resumenTraficoBamBean = new ResumenTraficoBamBean();
                DetallePlanesBAMType detallePlanesBAMType = response.getDetallePlanesBAM();
                
                if(logCajaTrafico){
	                CAJA_TRAFICO_LOGGER.info("****** Inicio Trafico BAM ******");
	                CAJA_TRAFICO_LOGGER.info("****** Inicio Detalle Plan BAM ******");
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getCicloFairUsePlan():      " + detallePlanesBAMType.getCicloFairUsePlan());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getCodigoError():      " + detallePlanesBAMType.getCodigoError());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getCodigoPlanBSCS(): " + detallePlanesBAMType.getCodigoPlanBSCS());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getContrato():    " + detallePlanesBAMType.getContrato());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getCuotaRestanteMB():          " + detallePlanesBAMType.getCuotaRestanteMB());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getCuotaUtilizadaMB():          " + detallePlanesBAMType.getCuotaUtilizadaMB());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getDescripcionPlan():          " + detallePlanesBAMType.getDescripcionPlan());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getDiaResetFairUsePlan():          " + detallePlanesBAMType.getDiaResetFairUsePlan());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getEqosidHLR():          " + detallePlanesBAMType.getEqosidHLR());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getEstado():          " + detallePlanesBAMType.getEstado());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getEstadoTransaccion():          " + detallePlanesBAMType.getEstadoTransaccion());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getFechaEstadoTransaccion():        " + detallePlanesBAMType.getFechaEstadoTransaccion());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getFechaFairUseAplicado():    " + detallePlanesBAMType.getFechaFairUseAplicado());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getFechaIngresoTransaccion():      " + detallePlanesBAMType.getFechaIngresoTransaccion());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getIndicadorFairUseAplicado():  " + detallePlanesBAMType.getIndicadorFairUseAplicado());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getIndicadorFairUseConfig():          " + detallePlanesBAMType.getIndicadorFairUseConfig());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getMercadoPlan():        " + detallePlanesBAMType.getMercadoPlan());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getMovil():    " + detallePlanesBAMType.getMovil());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getOrigenDatos():      " + detallePlanesBAMType.getOrigenDatos());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getPorcentajeRestante():  " + detallePlanesBAMType.getPorcentajeRestante());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getTipoTransaccion():  " + detallePlanesBAMType.getTipoTransaccion());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getUmbralFairUseGB():  " + detallePlanesBAMType.getUmbralFairUseGB());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getUmbralFairUseMB():  " + detallePlanesBAMType.getUmbralFairUseMB());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getVelocidadActual():  " + detallePlanesBAMType.getVelocidadActual());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getVelocidadFairUse():  " + detallePlanesBAMType.getVelocidadFairUse());
	            	CAJA_TRAFICO_LOGGER.info("detallePlanesBAMType.getVelocidadMaxPlan():  " + detallePlanesBAMType.getVelocidadMaxPlan());
	            	CAJA_TRAFICO_LOGGER.info("****** Fin Detalle Plan BAM ******");
                }
                
                resumenTraficoBamBean.setCicloFairUsePlan(detallePlanesBAMType.getCicloFairUsePlan());
                resumenTraficoBamBean.setCodigoError(detallePlanesBAMType.getCodigoError());
                resumenTraficoBamBean.setCodigoPlanBSCS(detallePlanesBAMType.getCodigoPlanBSCS());
                resumenTraficoBamBean.setContrato(detallePlanesBAMType.getContrato());
                
                resumenTraficoBamBean.setCuotaRestanteMB(Float.parseFloat(detallePlanesBAMType.getCuotaRestanteMB()));
                
                resumenTraficoBamBean.setCuotaUtilizadaMB(parseDouble(detallePlanesBAMType.getCuotaUtilizadaMB()));
                
                resumenTraficoBamBean.setDescripcionPlan(detallePlanesBAMType.getDescripcionPlan());
                resumenTraficoBamBean.setDiaResetFairUsePlan(detallePlanesBAMType.getDiaResetFairUsePlan());
                resumenTraficoBamBean.setEqosidHLR(detallePlanesBAMType.getEqosidHLR());
                resumenTraficoBamBean.setEstado(detallePlanesBAMType.getEstado());
                resumenTraficoBamBean.setEstadoTransaccion(detallePlanesBAMType.getEstadoTransaccion());
                resumenTraficoBamBean.setFechaEstadoTransaccion(DateHelper.parseDate(detallePlanesBAMType.getFechaEstadoTransaccion(), 
                		DateHelper.FORMAT_yyyyMMdd_HHmmss));
                resumenTraficoBamBean.setFechaFairUseAplicado(DateHelper.parseDate(detallePlanesBAMType.getFechaFairUseAplicado(), 
                		DateHelper.FORMAT_yyyyMMdd_HHmmss));
                resumenTraficoBamBean.setFechaIngresoTransaccion(DateHelper.parseDate(detallePlanesBAMType.getFechaIngresoTransaccion(), 
                		DateHelper.FORMAT_yyyyMMdd_HHmmss));
                resumenTraficoBamBean.setIndicadorFairUseAplicado(detallePlanesBAMType.getIndicadorFairUseAplicado());
                resumenTraficoBamBean.setIndicadorFairUseConfig(detallePlanesBAMType.getIndicadorFairUseConfig());
                resumenTraficoBamBean.setMercadoPlan(detallePlanesBAMType.getMercadoPlan());
                resumenTraficoBamBean.setMovil(detallePlanesBAMType.getMovil());
                resumenTraficoBamBean.setOrigenDatos(detallePlanesBAMType.getOrigenDatos());
                resumenTraficoBamBean.setPorcentajeRestante(Float.parseFloat(detallePlanesBAMType.getPorcentajeRestante()));
                resumenTraficoBamBean.setTipoTransaccion(detallePlanesBAMType.getTipoTransaccion());
                
                resumenTraficoBamBean.setUmbralFairUseGB(detallePlanesBAMType.getUmbralFairUseGB());
                
                resumenTraficoBamBean.setUmbralFairUseMB(detallePlanesBAMType.getUmbralFairUseMB());
                
                resumenTraficoBamBean.setVelocidadActual(detallePlanesBAMType.getVelocidadActual());
                
                resumenTraficoBamBean.setVelocidadFairUse(detallePlanesBAMType.getVelocidadFairUse());
                
                resumenTraficoBamBean.setVelocidadMaxPlan(detallePlanesBAMType.getVelocidadMaxPlan());
                
                resumenTraficoBamBean.setTraficoBamProductoBeanList(new ArrayList<TraficoBamProductoBean>());
                                
                if(logCajaTrafico)
                	CAJA_TRAFICO_LOGGER.info("****** Inicio Trafico de Productos ******");
                
                for (ProductoType producto : productosList) {
                	
                	if(logCajaTrafico){
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
                	}
                	
                	TraficoBamProductoBean traficoBamProductoBean = new TraficoBamProductoBean();
                	traficoBamProductoBean.setTipoPlan(producto.getTipoPlan());
                	traficoBamProductoBean.setCodbscs1(producto.getCodbscs1());
                	traficoBamProductoBean.setCodbscs2(producto.getCodbscs2());
                	traficoBamProductoBean.setNombrePlan(producto.getNombrePlan());
                	traficoBamProductoBean.setTipoTasacion(producto.getTipoTasacion());
                	
                	traficoBamProductoBean.setFechaRegistro(DateHelper.parseDate(producto.getFechaRegistro(), 
                											DateHelper.FORMAT_ddMMyyyy_HYPHEN));
                	
                	traficoBamProductoBean.setIdProducto(producto.getIdProducto());
                	
                	traficoBamProductoBean.setKey1(producto.getKey1());
                	
                	traficoBamProductoBean.setKey2(producto.getKey2());
                	traficoBamProductoBean.setKey3(producto.getKey3());
                	traficoBamProductoBean.setKey4(producto.getKey4());
                	traficoBamProductoBean.setKey5(producto.getKey5());
                	traficoBamProductoBean.setKey6(producto.getKey6());
                	traficoBamProductoBean.setKey7(producto.getKey7());
                	
                	if(logCajaTrafico)
                		CAJA_TRAFICO_LOGGER.info("****** Fin Producto ******");
                	resumenTraficoBamBean.getTraficoBamProductoBeanList().add(traficoBamProductoBean);
                	
                }

                if(logCajaTrafico){
	                CAJA_TRAFICO_LOGGER.info("****** Fin Trafico de Productos ******");
	                CAJA_TRAFICO_LOGGER.info("****** Fin Trafico BAM ******");
	                CAJA_TRAFICO_LOGGER.info("****** INICIO OUTPUT ******");
                }

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

        return resumenTraficoBamBean;
    }
    
    
    
    /**
     * 
     * @param numeroPcs
     * @param autoAtencion
     * @param mercado
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ResumenTraficoBAMCCBean consultarTraficoBAMCC(String numeroPcs, boolean logCajaTrafico) throws DAOException, ServiceException {
        ResumenTraficoBAMCCBean resumenTraficoBAMCCBean = null;

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

        if(logCajaTrafico){
        	CAJA_TRAFICO_LOGGER.info("Configurando Datos de la peticion");
    		CAJA_TRAFICO_LOGGER.info("****** INICIO INPUT ******");
    		CAJA_TRAFICO_LOGGER.info("Msisdn: " + numeroPcs );
        	CAJA_TRAFICO_LOGGER.info("****** FIN INPUT ******");
        }else{
        LOGGER.info("Configurando Datos de la peticion");
        }
        
        ConsultarTraficoBAMCCType request = new ConsultarTraficoBAMCCType();
        request.setMsisdn(numeroPcs);

        if(logCajaTrafico){
        	CAJA_TRAFICO_LOGGER.info("Invocando servicio");
        	CAJA_TRAFICO_LOGGER.info("Service name: " + service.getServiceName());
		    CAJA_TRAFICO_LOGGER.info("WSDL Document Location: " + service.getWSDLDocumentLocation());
            CAJA_TRAFICO_LOGGER.info("Operacion: ConsultarTraficoBAMCC");
        }else{
        LOGGER.info("Invocando servicio");
        }
        ResultadoConsultarTraficoBAMCCType response = null;

        try {
            response = port.consultarTraficoBAMCC(request);
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
        	CAJA_TRAFICO_LOGGER.error("Servicio ConsultarTraficoBAMCC no respondio "
                    + "con codigoRespuesta");
            LOGGER.error( new DAOException("Servicio ConsultarTraficoBAMCC no respondio "
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
               resumenTraficoBAMCCBean = new ResumenTraficoBAMCCBean();
                
                DetalleTraficoBAMCCType detalleTraficoBAMType = response.getDetalleTraficoBAMCC();
                
                if(logCajaTrafico){
                	CAJA_TRAFICO_LOGGER.info("****** Incio Detalle Trafico BAM ******");
                    CAJA_TRAFICO_LOGGER.info("detalleTraficoBAMType.getFechaActivacion() " + detalleTraficoBAMType.getFechaActivacion());
                    CAJA_TRAFICO_LOGGER.info("detalleTraficoBAMType.getFechaDesactivacion() " + detalleTraficoBAMType.getFechaDesactivacion());
                    CAJA_TRAFICO_LOGGER.info("detalleTraficoBAMType.getFechaExpiracion() " + detalleTraficoBAMType.getFechaExpiracion());
                    CAJA_TRAFICO_LOGGER.info("detalleTraficoBAMType.getSaldoMonto() " + detalleTraficoBAMType.getSaldoMonto());
                    CAJA_TRAFICO_LOGGER.info("detalleTraficoBAMType.getSaldoNavegacion() " + detalleTraficoBAMType.getSaldoNavegacion());
                    CAJA_TRAFICO_LOGGER.info("****** Fin Detalle Trafico BAM ******");
                }
                resumenTraficoBAMCCBean.setCodPlan(detalleTraficoBAMType.getCdgPlan());
                resumenTraficoBAMCCBean.setFechaActivacion(DateHelper.parseDate(detalleTraficoBAMType.getFechaActivacion(),DateHelper.FORMAT_yyyyMMddhhmmss));
                resumenTraficoBAMCCBean.setFechaDesactivacion(DateHelper.parseDate(detalleTraficoBAMType.getFechaDesactivacion(),DateHelper.FORMAT_yyyyMMddhhmmss));
                resumenTraficoBAMCCBean.setFechaExpiracion(DateHelper.parseDate(detalleTraficoBAMType.getFechaExpiracion(),DateHelper.FORMAT_yyyyMMddhhmmss));
                resumenTraficoBAMCCBean.setSaldoMonto(Double.parseDouble(detalleTraficoBAMType.getSaldoMonto()));
                resumenTraficoBAMCCBean.setSaldoNavegacion(detalleTraficoBAMType.getSaldoNavegacion());
           
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

        return resumenTraficoBAMCCBean;
    }
    
    
    private Double parseDouble( String string  ){
  		return Double.parseDouble(string);
    }
    

}
