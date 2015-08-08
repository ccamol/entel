/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.epcs.bean.DetalleBodegaVigenteBean;
import com.epcs.bean.EquiposSoporteBean;
import com.epcs.bean.ConsultaSintomaBean;
import com.epcs.bean.ObtenerBodegasVigentesBean;
import com.epcs.bean.ObtenerEquiposSoporteBean;
import com.epcs.bean.PreIngresoOTBean;
import com.epcs.bean.SintomaBean;
import com.epcs.bean.ValidaPreOTBean;
import com.epcs.cliente.contacto.BloqueoSubtelService;
import com.epcs.cliente.contacto.VerificacionBloqueoPortType;
import com.epcs.cliente.contacto.bloqueosubtel.types.ConsultarDatosEquipoPorImeiType;
import com.epcs.cliente.contacto.bloqueosubtel.types.ResultadoConsultarDatosEquipoPorImeiType;
import com.epcs.cliente.perfil.types.EnviarMailType;
import com.epcs.cliente.perfil.types.EquiposType;
import com.epcs.cliente.perfil.types.ObtenerEquiposSoporteType;
import com.epcs.cliente.perfil.types.ResultadoEnviarMailType;
import com.epcs.cliente.perfil.types.ResultadoObtenerEquiposSoporteType;
import com.epcs.cliente.perfil.ClientePerfilService;
import com.epcs.cliente.perfil.ClientePerfilServicePortType;

import com.epcs.cliente.problema.PreIngresoOtPortType;
import com.epcs.cliente.problema.PreIngresoOtService;
import com.epcs.cliente.problema.types.ConsultaSintomaRequestType;
import com.epcs.cliente.problema.types.ConsultaSintomaResponseType;
import com.epcs.cliente.problema.types.ListaSintomaType;
import com.epcs.cliente.problema.types.PreIngresoOTRequestType;
import com.epcs.cliente.problema.types.PreIngresoOTResponseType;
import com.epcs.cliente.problema.types.ValidaPreOTRequestType;
import com.epcs.cliente.problema.types.ValidaPreOTResponseType;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

import com.i2b.services.BodegasVigentes;
import com.i2b.services.BodegasVigentesService;
import com.i2b.services.DetalleBodega;
import com.i2b.services.ObtenerBodegasVigentes;
import com.i2b.services.ResponseBodegasVigentes;

/**
 * @author jmanzur,haltamiranda (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class PreIngresoOTDAO implements Serializable {
    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(PreIngresoOTDAO.class);
    
    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties 
    .getProperty("servicios.codigoRespuesta.exito");
    
    public static final String CODIGO_RESPUESTA_BODEGASVIGENTES_OK = "OK";
    
    public static final String CODIGO_RESPUESTA_PREINGRESOOT_OK = "0";
    
    public static final String CODIGO_GENERAOT_OK = "0";
    
    public String filtro;
    public String cadena;
    
    /**
     * Consulta de sintomas para el pre-ingreso de ordenes de servicio.
     *   
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ConsultaSintomaBean consultarSintomas()
            throws DAOException, ServiceException {

        LOGGER.info("Instanciando el Port.");
        PreIngresoOtPortType port = null;
        try {
            port = (PreIngresoOtPortType) WebServiceLocator
                    .getInstance().getPort(PreIngresoOtService.class,
                    		PreIngresoOtPortType.class);

        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + PreIngresoOtService.class);
            LOGGER.error( new DAOException(e));
        }

        ConsultaSintomaRequestType requestType = new ConsultaSintomaRequestType();
        ConsultaSintomaResponseType responseType = null;

        try {            
            responseType = port.consultaSintoma(requestType);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: consultarMisDatos", e);            
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = responseType.getCodigo();
        String descripcionRespuesta = responseType.getMensaje();

        LOGGER.info("Invocando servicio consultaSintoma, codigoRespuesta: "+codigoRespuesta+",descripcionRespuesta:"+descripcionRespuesta);        
        
      	ConsultaSintomaBean consultaSintomaBean = new ConsultaSintomaBean();
      	
    	consultaSintomaBean.setCodigoRespuesta(codigoRespuesta);
    	consultaSintomaBean.setMensajeRespuesta(descripcionRespuesta);

        if (CODIGO_RESPUESTA_PREINGRESOOT_OK.equals(codigoRespuesta)) {            
            try {           	
            	if((responseType.getSintomas() != null) && (!responseType.getSintomas().isEmpty())){            		
            		 consultaSintomaBean.setListaSintoma(new ArrayList<SintomaBean>());
            		 String sintoma="";
            		 for (ListaSintomaType responseList : responseType.getSintomas()) {
            			 SintomaBean sintomaBean = new SintomaBean();
            			 sintomaBean.setCodigo(responseList.getCodigo());
            			 sintoma = responseList.getSintoma().substring(0, 1).toUpperCase() + responseList.getSintoma().substring(1, responseList.getSintoma().length()).toLowerCase();
            			 sintomaBean.setSintoma(sintoma);
            			 try{
            				 sintomaBean.setAccesorio(MiEntelProperties.getProperty("preingresoot.accesoriosfallas." + responseList.getCodigo() + ""));
            			 }catch (Exception e) {
            	                LOGGER.info("Exception no existe en properties preingresoot.accesoriosfallas." + responseList.getCodigo() + ""); 
            	                sintomaBean.setAccesorio("");
            	         }
            			 
            		    try{								
							if(isSintomaHabilitado(sintomaBean.getCodigo())){
							   consultaSintomaBean.getListaSintoma().add(sintomaBean);
							}  
						}catch (Exception e) {
						LOGGER.info("Exception no se pudo filtrar el codigo "+sintomaBean.getCodigo()+" Error:" +e); 
						}
						       
            			 
            		 }
            	}                
                return consultaSintomaBean;
                
            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response", e);            
                LOGGER.error( new DAOException(e));            }
        }
        else {
            LOGGER.error("Service error code received: "+codigoRespuesta+" - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new ConsultaSintomaBean();
    }
    
    /**
     * Consulta de validacion de movil y serie para pre-ingreso de orden de servicio tecnico.
     * 
     * @param movil
     * @param serie
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ValidaPreOTBean validaPreOT(String movil, String serie)
            throws DAOException, ServiceException {
        
        PreIngresoOtPortType port = null;
        try {
            port = (PreIngresoOtPortType) WebServiceLocator
                    .getInstance().getPort(PreIngresoOtService.class,
                    		PreIngresoOtPortType.class);

        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + PreIngresoOtService.class);
            LOGGER.error( new DAOException(e));
        }

        ValidaPreOTRequestType requestType = new ValidaPreOTRequestType();
        ValidaPreOTResponseType responseType = null;

        try {
        	requestType.setMovil(movil);
        	requestType.setSerie(serie);            
            responseType = port.validaPreOT(requestType);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: consultarMisDatos", e);            
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = responseType.getCodigo();
        String descripcionRespuesta = responseType.getMensaje();
        LOGGER.info("Invocando servicio validaPreOT, codigoRespuesta: " + codigoRespuesta+" ,descripcionRespuesta: "+descripcionRespuesta);
        
        if (CODIGO_RESPUESTA_PREINGRESOOT_OK.equals(codigoRespuesta)) {            
            try {            	
            	ValidaPreOTBean validaPreOTBean = new ValidaPreOTBean();              	
              	validaPreOTBean.setCodigoRespuesta(codigoRespuesta);
              	validaPreOTBean.setMensajeRespuesta(descripcionRespuesta);                
                return validaPreOTBean;                
            } catch (Exception e) {
                LOGGER.error("Exception caught on Service response", e);            
                LOGGER.error( new DAOException(e));            
            }
        }
        else {
            LOGGER.error("Service error code received: "+codigoRespuesta+" - "+descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return new ValidaPreOTBean();
    }
    
    /**
     * Registra un pre-ingreso de orden de servicio tecnico.
     * 
     * @param movil
     * @param serie
     * @param fonoContacto
     * @param mailContacto
     * @param comentario
     * @param sintoma
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public PreIngresoOTBean preIngresoOT(String movil,String serie,String fonoContacto,String mailContacto,String comentario,ArrayList<SintomaBean> sintoma)
            throws DAOException, ServiceException {
        
        PreIngresoOtPortType port = null;
        try {
            port = (PreIngresoOtPortType) WebServiceLocator
                    .getInstance().getPort(PreIngresoOtService.class,
                    		PreIngresoOtPortType.class);

        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + PreIngresoOtService.class);
            LOGGER.error( new DAOException(e));
        }

        PreIngresoOTRequestType requestType = new PreIngresoOTRequestType();
        PreIngresoOTResponseType responseType = null;

        try {        	
        	requestType.setMovil(movil);
        	requestType.setSerie(serie);
        	requestType.setFonoContacto(fonoContacto);
        	requestType.setMailContacto(mailContacto);
        	requestType.setComentario(comentario);        	
        	for (SintomaBean responseList : sintoma) {
        		requestType.getSintoma().add(responseList.getCodigo());
        	}
        	
        	LOGGER.info("Request preIngresoOT, Movil: "+movil+", Serie: "+serie+", FonoContacto: "+fonoContacto+", MailContacto: "+mailContacto+" , Comentario: "+comentario+" ,Sintomas: "+sintoma);
        	
            responseType = port.preIngresoOT(requestType);

        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: consultarMisDatos", e);            
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = responseType.getCodigo();
        String descripcionRespuesta = responseType.getMensaje();

        LOGGER.info("Invocando servicio preIngresoOT, codigoRespuesta: "+codigoRespuesta+", descripcionRespuesta: "+descripcionRespuesta);

        if (CODIGO_RESPUESTA_PREINGRESOOT_OK.equals(codigoRespuesta)) {
            
            try {
            	PreIngresoOTBean preIngresoOTBean = new PreIngresoOTBean();
              	
            	preIngresoOTBean.setCodigoRespuesta(codigoRespuesta);
            	preIngresoOTBean.setNmroOrden(responseType.getNmroOrden());
            	preIngresoOTBean.setMensajeRespuesta(descripcionRespuesta);
                
                return preIngresoOTBean;
                
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
        return new PreIngresoOTBean();
    }
    
    /**
     * Obtener bodegas vigentes.
     * 
     * @param rutAgente     
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ObtenerBodegasVigentesBean obtenerBodegasVigentes(String rutAgente)
            throws DAOException, ServiceException {
        
        BodegasVigentes port = null;
        try {
            port = (BodegasVigentes) WebServiceLocator
                    .getInstance().getPort(BodegasVigentesService.class,
                    		BodegasVigentes.class);

        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + PreIngresoOtService.class);
            LOGGER.error( new DAOException(e));
        }
        ObtenerBodegasVigentes requestType = new ObtenerBodegasVigentes();
        ResponseBodegasVigentes responseType = null;

        try {
        	requestType.setRutAgente(rutAgente);           
            responseType = port.obtenerBodegasVigentes(rutAgente);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: obtenerBodegasVigentes", e);            
            LOGGER.error( new DAOException(e));
        }
        String codigoRespuesta = responseType.getStatus();
        String descripcionRespuesta = responseType.getMensaje();

        LOGGER.info("Invocando servicio obtenerBodegasVigentes, codigoRespuesta: "+codigoRespuesta+",descripcionRespuesta: "+descripcionRespuesta);     
        
        ObtenerBodegasVigentesBean obtenerBodegasVigentesBean = new ObtenerBodegasVigentesBean();
      	
    	obtenerBodegasVigentesBean.setCodigoRespuesta(codigoRespuesta);            	
    	obtenerBodegasVigentesBean.setMensajeRespuesta(descripcionRespuesta);

        if (CODIGO_RESPUESTA_BODEGASVIGENTES_OK.equals(codigoRespuesta)) {
            
            try {
            	if((responseType.getDetalle() != null) && (!responseType.getDetalle().isEmpty())){           	
					 
					for (DetalleBodega responseList : responseType.getDetalle()) {
						 DetalleBodegaVigenteBean detalleBodegaVigenteBean = new DetalleBodegaVigenteBean();						 
						 detalleBodegaVigenteBean.setCiudad(responseList.getCiudad());
						 detalleBodegaVigenteBean.setCodigoBodega(responseList.getCodigoBodega());
						 detalleBodegaVigenteBean.setCodigoSucursal(responseList.getCodigoSucursal());
						 detalleBodegaVigenteBean.setComuna(responseList.getComuna());
						 detalleBodegaVigenteBean.setDireccionSucursal(responseList.getDireccionSucursal());
						 detalleBodegaVigenteBean.setFlagAtiendePublico(responseList.getFlagAtiendePublico());
						 detalleBodegaVigenteBean.setHorarioDomingo(responseList.getHorarioDomingo());
						 detalleBodegaVigenteBean.setHorarioLunesViernes(responseList.getHorarioLunesViernes());
						 detalleBodegaVigenteBean.setNombreSucursal(responseList.getNombreSucursal());
						 detalleBodegaVigenteBean.setProvincia(responseList.getProvincia());
						 detalleBodegaVigenteBean.setRegion(responseList.getRegion());
						 detalleBodegaVigenteBean.setSectorRepartoSucursal(responseList.getSectorRepartoSucursal());						 
						 
						 obtenerBodegasVigentesBean.getDetalleBodegaVigente().add(detalleBodegaVigenteBean);					 
					}
	           	}
	               
               return obtenerBodegasVigentesBean;
                
                
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
        return new ObtenerBodegasVigentesBean();
    }
    
    
    /**
     * Obtener equipos soporte.
     * 
     * @param Dv
     * @param Email
     * @param Msisdn
     * @param RutSinDv       
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public ObtenerEquiposSoporteBean obtenerEquiposSoporte(String Dv,String Email,String Msisdn,String RutSinDv)
            throws DAOException, ServiceException {
        
        ClientePerfilServicePortType port = null;
        try {
            port = (ClientePerfilServicePortType) WebServiceLocator
                    .getInstance().getPort(ClientePerfilService.class,
                    		ClientePerfilServicePortType.class);

        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + PreIngresoOtService.class);
            LOGGER.error( new DAOException(e));
        }

        ObtenerEquiposSoporteType requestType = new ObtenerEquiposSoporteType();        
        ResultadoObtenerEquiposSoporteType responseType = null;

        try {        	
        	requestType.setDv(Dv);
        	requestType.setEmail(Email);
        	requestType.setMsisdn(Msisdn);
        	requestType.setRutSinDv(RutSinDv);        	
            
            responseType = port.obtenerEquiposSoporte(requestType);
        } catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: obtenerBodegasVigentes", e);            
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = responseType.getRespuesta().getCodigo();
        String descripcionRespuesta = responseType.getRespuesta().getDescripcion();
        LOGGER.info("Invocando servicio obtenerEquiposSoporte, codigoRespuesta: "+codigoRespuesta+", descripcionRespuesta: "+descripcionRespuesta);        
        
        ObtenerEquiposSoporteBean obtenerEquiposSoporteBean = new ObtenerEquiposSoporteBean();
      	
        obtenerEquiposSoporteBean.setCodigoRespuesta(codigoRespuesta);
        obtenerEquiposSoporteBean.setMensajeRespuesta(descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
            
            try {
            	if((responseType.getListaEquipos() != null) && (!responseType.getListaEquipos().isEmpty())){            		
            		obtenerEquiposSoporteBean.setListaEquiposSoporte(new ArrayList<EquiposSoporteBean>());            		
            		int count=0;
					for (EquiposType responseList : responseType.getListaEquipos()) {						
						if(responseList.getCodigoGeneraOT().equals(CODIGO_GENERAOT_OK)){
						
							EquiposSoporteBean equiposSoporteBean = new EquiposSoporteBean();					
							equiposSoporteBean.setImei(responseList.getImei());						
							equiposSoporteBean.setMarca(responseList.getMarca());
							equiposSoporteBean.setCodigoGeneraOT(responseList.getCodigoGeneraOT());
							equiposSoporteBean.setMensajeGeneraOT(responseList.getMensajeGeneraOT());
							
							String strFecha = responseList.getFechaNegocio();
							strFecha = formatearFecha(strFecha,"dd-MMM-yyyy");
							int mesAntiguedad = fechasDiferenciaEnMeses(strFecha);
					        
					        equiposSoporteBean.setFechaNegocio(strFecha);
							equiposSoporteBean.setMesAntiguedad(String.valueOf(mesAntiguedad));
					        
							obtenerEquiposSoporteBean.getListaEquiposSoporte().add(equiposSoporteBean);
							count++;
						}
					}
					obtenerEquiposSoporteBean.setTotalEquipos(count);
	           	}           	
	               
               return obtenerEquiposSoporteBean;
                
                
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
        return new ObtenerEquiposSoporteBean();
    }
    
    
    /**
     * Obtener equipos soporte.
     *     
     * @param imei       
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public EquiposSoporteBean consultaDescripcionMovil(String imei) throws DAOException, ServiceException {
        
    	VerificacionBloqueoPortType port = null;
    	try {
	            port = (VerificacionBloqueoPortType) WebServiceLocator
	            		.getInstance().getPort(BloqueoSubtelService.class, 
	            				VerificacionBloqueoPortType.class);
	        }catch (WebServiceLocatorException e) {
	        	LOGGER.error("Error al inicializar el Port "
	                    + VerificacionBloqueoPortType.class, e);
	            LOGGER.error( new DAOException(e));
	        }            
        	
        	ConsultarDatosEquipoPorImeiType consultarDatosEquipoPorImeiTypeRequest = new ConsultarDatosEquipoPorImeiType();       	
        	consultarDatosEquipoPorImeiTypeRequest.setImei(imei);            
        	ResultadoConsultarDatosEquipoPorImeiType resultadoConsultarDatosEquipoPorImeiTypeResponse = null;
        	
        	LOGGER.info("consultarDatosEquipoPorImeiOperation, IMEI:"+imei);
        	
        	try{
        		
        		resultadoConsultarDatosEquipoPorImeiTypeResponse = port.consultarDatosEquipoPorImeiOperation(consultarDatosEquipoPorImeiTypeRequest);
        	}catch (Exception e) {
            	LOGGER.error("Exception caught on Service invocation: "
                        + "consultarDatosEquipoPorImeiOperation", e);
                LOGGER.error( new DAOException(e));
			}
        	
        	LOGGER.info("Invocando servicio consultarDatosEquipoPorImeiOperation");
        	String codigoRespuesta  = resultadoConsultarDatosEquipoPorImeiTypeResponse.getRespuesta().getCodigo();
        	String mensajeRespuesta  = resultadoConsultarDatosEquipoPorImeiTypeResponse.getRespuesta().getDescripcion();
        	
        	EquiposSoporteBean equiposSoporteBean = new EquiposSoporteBean();
        	
        	if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
        		try{        			      			
        			equiposSoporteBean.setImei(resultadoConsultarDatosEquipoPorImeiTypeResponse.getConsultarDatosEquipoPorImei().getRegistro().get(0).getImei());
        			equiposSoporteBean.setMarca(resultadoConsultarDatosEquipoPorImeiTypeResponse.getConsultarDatosEquipoPorImei().getRegistro().get(0).getMarcaModelo());
        			
        			String strFecha = resultadoConsultarDatosEquipoPorImeiTypeResponse.getConsultarDatosEquipoPorImei().getRegistro().get(0).getFechaOperacionComercial();					
					strFecha = formatearFecha(strFecha,"MM/dd/yyyy");					
					int mesAntiguedad = fechasDiferenciaEnMeses(strFecha);
					
					equiposSoporteBean.setMesAntiguedad(String.valueOf(mesAntiguedad));
					equiposSoporteBean.setFechaNegocio(strFecha);
        		}catch (Exception e) {
        			LOGGER.error("Exception caught on Service response: "+"consultarDatosEquipoPorImeiOperation", e);
                    LOGGER.error( new DAOException(e));
				}	
        	}else{
        		LOGGER.error("Service error code received: "+codigoRespuesta+" - "+mensajeRespuesta);
                LOGGER.error( new ServiceException(codigoRespuesta, mensajeRespuesta));
        	}
            
        return equiposSoporteBean;
    }
    
    
    public int fechasDiferenciaEnMeses(String strFecha) throws DAOException {
    	Date fechaInicial = null;
        Date fechaFinal = new Date();
        try {
	    	//MESES ANTIGUEDAD
	        DateFormat formatter = new SimpleDateFormat("dd-MM-yy", new Locale("ES"));
	        fechaInicial = (Date)formatter.parse(strFecha);
	        
	        //MESES ANTIGUEDAD    	
	    	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	        String fechaInicioString = df.format(fechaInicial);
        
            fechaInicial = df.parse(fechaInicioString);
            String fechaFinalString = df.format(fechaFinal);        
            fechaFinal = df.parse(fechaFinalString);
        } catch (Exception e) {
	        LOGGER.error("Exception problema al formatear las fechas para los meses de antiguedad", e);            
	        LOGGER.error( new DAOException(e));
	    }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24)) / 30;
        return ( (int) dias);
    }
    
    public String formatearFecha(String fecha,String formato) throws DAOException{
    	try {
    		//"dd-MMM-yyyy"
    		//"MMM/dd/yyyy"
	    	DateFormat formatter = new SimpleDateFormat(formato, new Locale("ES"));
	        Date date = (Date)formatter.parse(fecha);
	        fecha = DateHelper.format(date, "dd-MM-yy");
        } catch (Exception e) {
	        LOGGER.error("Exception problema al formatear las fechas para los meses de antiguedad", e);            
	        LOGGER.error( new DAOException(e));
	    }
    	return fecha;
    }
    
    
    /**
     * 
     * @param from
     * @param to
     * @param message
     * @param subject
     * @throws DAOException
     * @throws ServiceException
     */
	public void enviarMail(String from, String to, String message, String subject) throws DAOException,
			ServiceException {
		
		ClientePerfilServicePortType port = null;
		try {
			port = (ClientePerfilServicePortType) WebServiceLocator
					.getInstance().getPort(ClientePerfilService.class,
							ClientePerfilServicePortType.class);

		} catch (WebServiceLocatorException e) {
			LOGGER.error("Error al inicializar el Port "
					+ ClientePerfilService.class);
			LOGGER.error( new DAOException(e));
		}

		EnviarMailType request = new EnviarMailType();
		ResultadoEnviarMailType response = null;

		try {
			request.setFrom(from);
			request.setMessage(message);
			request.setSubject(subject);
			request.setTo(to);			
			response = port.enviarMail(request);
		} catch (Exception e) {
			LOGGER.error("Exception caught on Service invocation: consultarMisDatos",e);
			LOGGER.error( new DAOException(e));
		}

		String codigoRespuesta = response.getRespuesta().getCodigo();
		String descripcionRespuesta = response.getRespuesta().getDescripcion();
		
		LOGGER.info("Invocando servicio enviarMail, codigoRespuesta: "+codigoRespuesta+" ,descripcionRespuesta: "+descripcionRespuesta);

		if (!CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
			LOGGER.info("Service error code received: " + codigoRespuesta
					+ " - " + descripcionRespuesta);
			LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
		}
	}
	
	
	private boolean isSintomaHabilitado(String sintoma){
    	boolean sw = false;    	
    	String listaSintomas="";    	
    	try{    		
    		    
		   	    Node mensaje = JSFPortletHelper.getContentNode("idContenido", "filtroSintomas");
		        listaSintomas = mensaje.getProperty("html").getValue().getStringValue();
		        
		        if(listaSintomas!=null){
		            filtro = "[" + sintoma + "]";
					if(listaSintomas.indexOf(filtro) != -1){
						sw=true;        
					}    	
		        }
    	
        } catch (Exception e) {
            LOGGER.error("Exception al buscar informacion de planes excluidos de tarifa diaria en el CM.", e);           
        }
		return sw;     	
    }
    
	
	
	
}