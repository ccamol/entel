package com.epcs.formularioreclamo.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.bean.CodeDescBean;
import com.epcs.bean.DetalleReclamoBean;
import com.epcs.bean.HistorialReclamosBean;
import com.epcs.bean.PaginaHistorialReclamosBean;
import com.epcs.bean.ReclamoBean;
import com.epcs.billing.balance.BillingBalanceService;
import com.epcs.billing.balance.BillingBalanceService_Service;
import com.epcs.billing.balance.types.ConsultarFacturacionFullType;
import com.epcs.billing.balance.types.DocumentoFacturaFullType;
import com.epcs.billing.balance.types.ResultadoConsultarFacturacionFullType;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.esa.cliente.contacto.sga.TicketSGAService;
import com.esa.cliente.contacto.sga.TicketSGAServicePortType;
import com.esa.cliente.contacto.sga.types.CreaTicketSgaRequestType;
import com.esa.cliente.contacto.sga.types.CrearTicketSgaResponseType;
import com.esa.clientes.problemas.detalle.DetalleReclamoService;
import com.esa.clientes.problemas.detalle.DetalleReclamoServicePortType;
import com.esa.clientes.problemas.detalle.types.RequestConsultaDetalleReclamoType;
import com.esa.clientes.problemas.detalle.types.ResponseConsultaDetalleReclamoType;
import com.esa.clientes.problemas.historicoreclamo.HistoricoReclamoService;
import com.esa.clientes.problemas.historicoreclamo.HistoricoReclamoServicePortType;
import com.esa.clientes.problemas.historicoreclamo.types.DetalleReclamoType;
import com.esa.clientes.problemas.historicoreclamo.types.RequestConsultaHistoricoReclamoType;
import com.esa.clientes.problemas.historicoreclamo.types.ResponseConsultaHistoricoReclamoType;
import com.esa.clientes.problemas.impugnacion.ImpugnacionService;
import com.esa.clientes.problemas.impugnacion.ImpugnacionServicePortType;
import com.esa.clientes.problemas.impugnacion.types.RequestIngresoImpugnacionType;
import com.esa.clientes.problemas.impugnacion.types.ResponseIngresoImpugnacionType;

/**
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 *
 */
public class FormularioReclamoDAO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(FormularioReclamoDAO.class);

	/** The Constant CODIGO_RESPUESTA_OK. */
	public static final String CODIGO_RESPUESTA_OK = MiEntelProperties
			.getProperty("servicios.codigoRespuesta.exito");

	/** The Constant TOPE_REGISTROS_PAGINA_HISTORIAL. */
	public static final String TOPE_REGISTROS_PAGINA_HISTORIAL = MiEntelProperties
			.getProperty("parametro.reclamos.topeRegistrosPagina");

	/** The Constant ID_SISTEMA_FACTURACION_FULL. */
	public static final String ID_SISTEMA_FACTURACION_FULL = MiEntelProperties
			.getProperty("facturacionfull.idsistema");
	
	/** The Constant NOMBRE_ITEM. */
	public static final String NOMBRE_ITEM = MiEntelProperties
			.getProperty("parametros.reclamo.itemTotal");
	
	/** The Constant PRODUCTO_RECLAMADO. */
	public static final String PRODUCTO_RECLAMADO = MiEntelProperties
	.getProperty("parametros.reclamo.servicio.productoReclamado");	
	
	/** The Constant RECLAMO_CIA_RECLAMADA. */
	public static final String RECLAMO_CIA_RECLAMADA = MiEntelProperties
	.getProperty("parametros.reclamo.servicio.ciaReclamada");
	
	/** The Constant SEPARADOR_RESPUESTA. */
    public static final String SEPARADOR_RESPUESTA = MiEntelProperties
    .getProperty("parametros.reclamo.separadorRespuesta");
    
    /** The Constant TIPO. */
	public static final String TIPO = MiEntelProperties
			.getProperty("parametros.reclamo.servicio.tipo");
	
	 /** The Constant ESTADO_SOLUCIONADO. */
	public static final String ESTADO_SOLUCIONADO = MiEntelProperties
			.getProperty("parametros.reclamo.estadoSolucionado");
    
    
    public static final String PLATAFORMA = MiEntelProperties
    .getProperty("parametros.reclamo.plataforma");
    
	/** The Constant BOLETA_Y_FACTURA. */
	public String BOLETA_FACTURA = MiEntelProperties
			.getProperty("parametros.tipoReclamo.equivalente.comercial.1");
	
	/** The Constant WORKFLOW. */
	public static final String WORKFLOW = MiEntelProperties
			.getProperty("parametros.reclamo.servicio.workflow");
	
	/** The Constant WORKFLOW_BOLETA_FACTURA. */
	public static final String WORKFLOW_BOLETA_FACTURA = MiEntelProperties
			.getProperty("parametros.reclamo.servicio.workflowBoletaFactura");
    
    
	
	/**
	 * Ingresa un reclamo.
	 *
	 * @param reclamo the reclamo
	 * @return the string
	 * @throws ServiceException the service exception
	 * @throws DAOException the dAO exception
	 */
	public String ingresarReclamo(ReclamoBean reclamo) throws ServiceException, DAOException {
		ImpugnacionServicePortType port = null;
		LOGGER.info("Instanciando el Port.");
		
		try {
			port = (ImpugnacionServicePortType) WebServiceLocator.getInstance()
					.getPort(ImpugnacionService.class, ImpugnacionServicePortType.class);
		} catch (WebServiceLocatorException e) {
			LOGGER.error("Error al inicializar el Port " + ImpugnacionServicePortType.class);
			LOGGER.error( new DAOException(e));
		}

		LOGGER.info("Configurando Datos de la peticion creaImpugnacion");
		
		RequestIngresoImpugnacionType creaImpugnacion = new RequestIngresoImpugnacionType();		
		
		reclamo.setWorkflow(WORKFLOW);		
		
		creaImpugnacion.setRutCliente(reclamo.getRutCliente());
		creaImpugnacion.setNumeroReclamado(reclamo.getNroReclamado());
		creaImpugnacion.setProductoReclamado(PRODUCTO_RECLAMADO);
		creaImpugnacion.setCompaniaReclamada(RECLAMO_CIA_RECLAMADA);
		creaImpugnacion.setTipoReclamo(TIPO);
		creaImpugnacion.setMotivoReclamo(reclamo.getMotivoReclamo());
		creaImpugnacion.setMensajeAlfanumerico(reclamo.getMensaje());
		creaImpugnacion.setDireccionReclamada(reclamo.getDireccionReclamada());
		creaImpugnacion.setTelefonoFijo("");
		creaImpugnacion.setEmailContacto(reclamo.getEmailContacto());
		creaImpugnacion.setCheckEmail(reclamo.getCheckEmail());
		creaImpugnacion.setEmailCuenta(reclamo.getEmailCuenta() );
		creaImpugnacion.setEmailDominio(reclamo.getEmailDominio());
		creaImpugnacion.setCheckSms("");
		creaImpugnacion.setNumeroSms("");
		creaImpugnacion.setCheckDirPostal(reclamo.getCheckDireccion());
		creaImpugnacion.setDireccion(reclamo.getDireccion());
		creaImpugnacion.setDirNumero(reclamo.getDirNumero());
		creaImpugnacion.setDirAdicional(reclamo.getDirAdicional());
		creaImpugnacion.setDirComuna(reclamo.getDirComuna());
		creaImpugnacion.setDirCiudad(reclamo.getDirCiudad());
		creaImpugnacion.setDirRegion(reclamo.getDirRegion());
		creaImpugnacion.setDirCodPostal(reclamo.getDirCodPostal());
		creaImpugnacion.setDetalleNotificacion("");
		
		creaImpugnacion.setPlataforma(PLATAFORMA);
		creaImpugnacion.setSuboperacion(reclamo.getTipoReclamo());
		creaImpugnacion.setNrodocumento(reclamo.getNroDocumento());		
		creaImpugnacion.setTipoDescuento("");
		creaImpugnacion.setMundo(reclamo.getMundo());
		creaImpugnacion.setWorkflow(reclamo.getWorkflow());
		
		com.esa.clientes.problemas.impugnacion.types.DetalleReclamoType detalleReclamoType = new com.esa.clientes.problemas.impugnacion.types.DetalleReclamoType();
		com.esa.clientes.problemas.impugnacion.types.ListadoReclamosType listadoReclamosType = new com.esa.clientes.problemas.impugnacion.types.ListadoReclamosType(); 
		
		if(reclamo.getMotivoReclamo().equals(BOLETA_FACTURA)){
			
			if(reclamo.getCuenta()!=null){				
				creaImpugnacion.setCuentapago(reclamo.getCuenta());					
			}else{
				creaImpugnacion.setCuentapago("");
			}
			//creaImpugnacion.setCuentapago(reclamo.getCuenta());
			creaImpugnacion.setTipodocumento(reclamo.getTipoDocumento().equals("B")?"Boleta":"Factura");
			detalleReclamoType.setNombreItem(NOMBRE_ITEM);
			detalleReclamoType.setMontoImpugnado(reclamo.getMontoImpugnado());
			//detalleReclamoType.setNumeroDocumento(reclamo.getNroDocumento());
			detalleReclamoType.setValorItem(reclamo.getValorItem());
			
		}else{			
			creaImpugnacion.setTipodocumento("N/A");			
			creaImpugnacion.setCuentapago("N/A");
			creaImpugnacion.setNrodocumento("");
			detalleReclamoType.setNombreItem("N/A");
			detalleReclamoType.setMontoImpugnado("0");			
			detalleReclamoType.setValorItem("0");				
				
		}
		
		listadoReclamosType.getListadoFinal().add(detalleReclamoType);
		creaImpugnacion.setDetalleReclamo(listadoReclamosType);
		
		LOGGER.info(reclamo.toString());		

		ResponseIngresoImpugnacionType response = null;
		try {
			LOGGER.info("Invocando servicio");
			response = port.ingresoImpugnacion(creaImpugnacion);
		} catch (Exception e) {
			LOGGER.error("Exception caught on Service invocation: ingresarReclamo", e);
			LOGGER.error( new DAOException(e));
		}

		String codigoRespuesta = response.getCodigo();
		String descripcionRespuesta = response.getDescripcion();
		String idReclamo = response.getNumeroSubtel();
		LOGGER.info("codigoRespuesta " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
		LOGGER.info("idReclamo " + idReclamo);

		
		if (!CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
			LOGGER.error("Service error code received: " + codigoRespuesta + " - " + descripcionRespuesta);
			LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
		}
		
		return idReclamo;
	}
	
	/**
	 * Metodo para obtener el historial de reclamos de un cliente.
	 *
	 * @param rutCliente the rut cliente
	 * @return List<HistorialDetalleBean>
	 * @throws ServiceException the service exception
	 * @throws DAOException the DAO exception
	 */
	public List<PaginaHistorialReclamosBean> getHistorialReclamos(String rutCliente) throws ServiceException, DAOException {
        List<HistorialReclamosBean> historialReclamo = null;
    	List<PaginaHistorialReclamosBean> paginaHistorial = null;
    	List<DetalleReclamoType> detallesOrdenado;
    	HistoricoReclamoServicePortType port = null;
		LOGGER.info("Instanciando el Port.");
		
		try {
			port = (HistoricoReclamoServicePortType) WebServiceLocator.getInstance()
					.getPort(HistoricoReclamoService.class, HistoricoReclamoServicePortType.class);
		} catch (WebServiceLocatorException e) {
			LOGGER.error("Error al inicializar el Port " + HistoricoReclamoServicePortType.class);
			LOGGER.error( new DAOException(e));
		}

		LOGGER.info("Configurando Datos de la peticion consultaHistorico");
		RequestConsultaHistoricoReclamoType consultaHistorico = new RequestConsultaHistoricoReclamoType();
		consultaHistorico.setRutCliente(rutCliente);

		LOGGER.info("Invocando servicio");
		ResponseConsultaHistoricoReclamoType response = null;
		
		try {
			response = port.consultaHistoricoReclamo(consultaHistorico);
		} catch (Exception e) {
			LOGGER.error("Exception caught on Service invocation: consultaHistorico", e);
			LOGGER.error( new DAOException(e));
		}
		
		String codigoRespuesta = response.getCodigo();
		String descripcionRespuesta = response.getDescripcion();
		LOGGER.info("codigoRespuesta " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);	
		
		if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {	
			
			try {				
			    	historialReclamo = new ArrayList<HistorialReclamosBean>();
			    	paginaHistorial = new ArrayList<PaginaHistorialReclamosBean>();    	
					List<DetalleReclamoType> detalles = response.getDetalleReclamo().getListadoFinal();		
					double topeRegistros = Double.parseDouble(TOPE_REGISTROS_PAGINA_HISTORIAL);
				    double numeroPaginas = Math.ceil((double) detalles.size() / topeRegistros);
					int paginaTotal = detalles.size() > topeRegistros ? ((int) numeroPaginas >= numeroPaginas ? (int) numeroPaginas : (int) (numeroPaginas + 1))	: 1;
					
					detalles = ordenarDescendente(detalles);
					
					int contador=0;
			        boolean sw =true;
						    
					for(int i=0; i < paginaTotal;i++){
				    	PaginaHistorialReclamosBean paginaHistorialReclamos = new PaginaHistorialReclamosBean();	    	
						int l=0;			
						historialReclamo = new ArrayList<HistorialReclamosBean>();
			           while( l < topeRegistros && sw  ){ 	    	    
				    	 if (contador < detalles.size()) {
				    		 DetalleReclamoType  detalle = (DetalleReclamoType) detalles.get(contador);
								HistorialReclamosBean historialReclamosBean = new HistorialReclamosBean();
								historialReclamosBean.setMotivo(detalle.getMotivo());
								historialReclamosBean.setEstado(detalle.getEstado());			
								historialReclamosBean.setNumeroReclamo(detalle.getNroReclamo());
							
								if(detalle.getFechaIngreso()!=null){
									if(!detalle.getFechaIngreso().equals("")){
										historialReclamosBean.setFechaDeIngreso(DateHelper.parseDate(detalle.getFechaIngreso(),	DateHelper.FORMAT_yyyyMMdd_HYPHEN));								
										historialReclamosBean.setFechaDeIngresoTest(DateHelper.format(DateHelper.parseDate(detalle.getFechaIngreso(),	DateHelper.FORMAT_yyyyMMdd_HYPHEN),DateHelper.FORMAT_yyyyMMdd_HYPHEN));
									}
									
								}
								
								
								if(detalle.getEstado().equals(ESTADO_SOLUCIONADO)){
									if(detalle.getFechaRespuesta()!=null){
										if(!detalle.getFechaRespuesta().equals("") && !detalle.getFechaRespuesta().equals(" ")&& !detalle.getFechaRespuesta().equals("-")){	
											historialReclamosBean.setFechaDeRespuestaTest(DateHelper.format(DateHelper.parseDate(detalle.getFechaRespuesta(), DateHelper.FORMAT_yyyyMMdd_HYPHEN),DateHelper.FORMAT_yyyyMMdd_HYPHEN));
											historialReclamosBean.setFechaDeRespuesta(DateHelper.parseDate(detalle.getFechaRespuesta(), DateHelper.FORMAT_yyyyMMdd_HYPHEN));
										}	
									}	
								}
								
								
								historialReclamo.add(historialReclamosBean);
								contador++;
			                    l++;
				    	 }else{
				    		 sw = false;
				    	 }		
						}
						paginaHistorialReclamos.setListaDetalle(historialReclamo);
						paginaHistorial.add(paginaHistorialReclamos);
						
					}
			} catch (Exception e) {
				LOGGER.error("Exception caught on Service response", e);
				LOGGER.error( new DAOException(e));
			}
		} else {
			LOGGER.error("Service error code received: " + codigoRespuesta + " - " + descripcionRespuesta);
			LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
		}	

		return paginaHistorial;
	}
	
	/**
	 * Metodo que permite la consulta del destalle de un reclamo.
	 *
	 * @param nroReclamo the nro reclamo
	 * @return DetalleReclamoBean
	 * @throws ServiceException the service exception
	 * @throws DAOException the DAO exception
	 */
	public DetalleReclamoBean consultarDetalleReclamo(String nroReclamo) throws ServiceException, DAOException {
		DetalleReclamoServicePortType port = null;
		LOGGER.info("Instanciando el Port.");
		
		try {
			port = (DetalleReclamoServicePortType) WebServiceLocator.getInstance()
					.getPort(DetalleReclamoService.class, DetalleReclamoServicePortType.class);
		} catch (WebServiceLocatorException e) {
			LOGGER.error("Error al inicializar el Port " + DetalleReclamoServicePortType.class);
			LOGGER.error( new DAOException(e));
		}

		LOGGER.info("Configurando Datos de la peticion consultarDetalleReclamo");
		RequestConsultaDetalleReclamoType consultaReclamo = new RequestConsultaDetalleReclamoType();
		consultaReclamo.setNroReclamo(nroReclamo);
		LOGGER.info("Invocando servicio");
		ResponseConsultaDetalleReclamoType response = null;
		try {
			response = port.consultaDetalleReclamo(consultaReclamo);
		} catch (Exception e) {
			LOGGER.error("Exception caught on Service invocation: consultarDetalleReclamo", e);
			LOGGER.error( new DAOException(e));
		}		
		
		String codigoRespuesta = response.getCodigo();
		String descripcionRespuesta = response.getDescripcion();
		LOGGER.info("codigoRespuesta " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
		DetalleReclamoBean detalleReclamoBean = new DetalleReclamoBean();
		
		if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
			try {				
				detalleReclamoBean.setEstado(response.getEstadoReclamo());
				detalleReclamoBean.setAntecedentes(response.getAntecedentes());
				detalleReclamoBean.setNumeroReclamado(response.getNumeroReclamado());		
				detalleReclamoBean.setFechaIngreso(DateHelper.parseDate(response.getFechaIngreso(), DateHelper.FORMAT_yyyyMMdd_HYPHEN));
				detalleReclamoBean.setFechaRespuesta(DateHelper.parseDate(response.getFechaRespuesta(), DateHelper.FORMAT_yyyyMMdd_HYPHEN));
				
				detalleReclamoBean.setFechaIngresoText(response.getFechaIngreso());
				detalleReclamoBean.setFechaRespuestaText(response.getFechaRespuesta());
				
				
				detalleReclamoBean.setEmail(response.getEmailCuenta()+"@"+response.getEmailDominio());
				detalleReclamoBean.setRut(response.getRutCliente());
				detalleReclamoBean.setMotivo(response.getMotivoReclamo());
				detalleReclamoBean.setRespuesta(response.getDetalleNotificacion());	
				detalleReclamoBean.setDireccionReclamada(response.getDireccionReclamada());
				
				//detalleReclamoBean.
				
				detalleReclamoBean.setNotificacion("");				
				
				if(response.getCheckEmail().equals("SI") ){
					detalleReclamoBean.setNotificacion("Email: "+detalleReclamoBean.getEmail());
				}
				
				if (response.getCheckDirPostal().equals("SI")) {
					if (!detalleReclamoBean.getNotificacion().equals("")) {
						 detalleReclamoBean.setNotificacion(detalleReclamoBean
								.getNotificacion()
								+ " / Direcci&oacuten: "
								+ response.getDireccion());
					} else {
						detalleReclamoBean.setNotificacion("Direcci&oacuten: "
								+ response.getDireccion());
					}

				}				
				
		        if(response.getDetalleReclamo()!=null){			
		        	if(response.getDetalleReclamo().getListadoFinal().size()>0){
		        		detalleReclamoBean.setNumeroDeDocumento( response.getDetalleReclamo().getListadoFinal().get(0).getNumeroDocumento());
		        		double monto = Double.parseDouble(response.getDetalleReclamo().getListadoFinal().get(0).getMontoImpugnado()); 
		        		detalleReclamoBean.setMonto(Utils.formatMoneyPuntos(monto));
		        	 }else{
		        		 detalleReclamoBean.setNumeroDeDocumento("");	
		        	 }			
				}else{
					detalleReclamoBean.setNumeroDeDocumento("");	
				}

			} catch (Exception e) {
				LOGGER.error("Exception caught on Service response", e);
				LOGGER.error( new DAOException(e));
			}
		} else {
			LOGGER.error("Service error code received: " + codigoRespuesta + " - " + descripcionRespuesta);
			LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
		}
		
		
		return detalleReclamoBean;
	}	
	
    /**
     * Metodo que permite la consulta del monto cobrado de un documento (factura) indicado.
     *
     * @param msisdn the msisdn
     * @param rutTitular the rut titular
     * @return List<DocumentoFacturaFullType>
     * @throws DAOException the dAO exception
     * @throws ServiceException the service exception
     */
	public List<DocumentoFacturaFullType> consultarDocumentoReclamado(String msisdn, String rutTitular) throws DAOException, ServiceException {
		List<DocumentoFacturaFullType> documentosFactFullList = new ArrayList<DocumentoFacturaFullType>();
		BillingBalanceService port = null;
		LOGGER.info("Instanciando el port");
		try {
			port = (BillingBalanceService) WebServiceLocator.getInstance()
					.getPort(BillingBalanceService_Service.class, BillingBalanceService.class);
		} catch (WebServiceLocatorException e) {
			LOGGER.error("Error al inicializar el Port " + BillingBalanceService.class, e);
			LOGGER.error( new DAOException(e));
		}

		ConsultarFacturacionFullType request = new ConsultarFacturacionFullType();
		ResultadoConsultarFacturacionFullType facturasType = null;

		try {

			LOGGER.info("Configurando Datos de la peticion");
			request.setMsisdn(msisdn);
			request.setRutTitular(rutTitular);
			request.setIdSistema(ID_SISTEMA_FACTURACION_FULL);
			LOGGER.info("Invocando servicio");
			facturasType = port.consultarFacturacionFull(request);

		} catch (Exception e) {
			LOGGER.error("Exception caught on Service invocation", e);
			LOGGER.error( new DAOException(e));
		}

		String codigoRespuesta = facturasType.getRespuesta().getCodigo();
		String descripcionRespuesta = facturasType.getRespuesta().getDescripcion();
		LOGGER.info("codigoRespuesta " + codigoRespuesta);
		LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

		if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
			try {
				documentosFactFullList = new ArrayList<DocumentoFacturaFullType>(facturasType.getDocumentosFacturasFull());
			} catch (Exception e) {
				LOGGER.error("Exception caught on Service response", e);
				LOGGER.error( new DAOException(e));
			}
		} else {
			LOGGER.error("Service error code received: " + codigoRespuesta + " - " + descripcionRespuesta);
			LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
		}

		return documentosFactFullList;
	}
	
	/**
	 * Gets the page array.
	 *
	 * @param pageNumber the page number
	 * @param data the data
	 * @param pageSize the page size
	 * @return the page array
	 */
	public List<DetalleReclamoType> getPageArray(int pageNumber, List<DetalleReclamoType> data, int pageSize) {
		int fromIndex = ((pageNumber - 1) * pageSize);
		int toIndex = data.size() - fromIndex < pageSize ? (fromIndex + (data.size() - fromIndex)) : (pageNumber * pageSize);
		List<DetalleReclamoType> pageData = data.subList(fromIndex, toIndex);
		return pageData;
	}
	 
	public List<DetalleReclamoType>  ordenarDescendente(List<DetalleReclamoType> detalles) {
		for (int i = 0; i < detalles.size(); i++) {
			Date fechaUno = DateHelper.parseDate(detalles.get(i)
					.getFechaIngreso(), DateHelper.FORMAT_yyyyMMdd_HYPHEN);
			
			int numReclamoUno = Integer.parseInt(detalles.get(i).getNroReclamo());
			
			for (int j = 0; j < detalles.size(); j++) {
				Date fechaDos = DateHelper.parseDate(detalles.get(j)
						.getFechaIngreso(), DateHelper.FORMAT_yyyyMMdd_HYPHEN);
				int numReclamoDos= Integer.parseInt(detalles.get(j).getNroReclamo());
				
				if (fechaUno.after(fechaDos) || (fechaUno.equals(fechaDos) && numReclamoUno > numReclamoDos )) {
					DetalleReclamoType aux = detalles.get(i);
					detalles.set(i, detalles.get(j));
					detalles.set(j, aux);
				}
			}
		}
		return detalles;
	}
	 
	
	
}
