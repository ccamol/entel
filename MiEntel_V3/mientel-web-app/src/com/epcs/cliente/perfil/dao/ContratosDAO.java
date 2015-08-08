/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.bean.ContratoBean;
import com.epcs.bean.ListaContratosBean;
import com.epcs.cliente.perfil.ClientePerfilService;
import com.epcs.cliente.perfil.ClientePerfilServicePortType;
import com.epcs.cliente.perfil.types.DocumentoSAEDType;
import com.epcs.cliente.perfil.types.ListaContratosType;
import com.epcs.cliente.perfil.types.ResultadoListaContratosType;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.locator.WebServiceLocator;
import com.epcs.recursoti.configuracion.locator.WebServiceLocatorException;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

import com.esa.clientes.canalescontacto.miscontratos.MisContratosPortType;
import com.esa.clientes.canalescontacto.miscontratos.MisContratosService;
import com.esa.clientes.canalescontacto.miscontratos.types.MisContratosType;
import com.esa.clientes.canalescontacto.miscontratos.types.ResultadoMisContratosType;
//import com.esa.clientes.canalescontacto.miscontratos.types.DocumentoSAEDType;

/**
 * @author dvelez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ContratosDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ContratosDAO.class);
	public static final String CODIGO_RESPUESTA_OK = MiEntelProperties.getProperty("servicios.codigoRespuesta.exito");
    /**
     * Lista de Contratos Firmados electronicamente
     * 
     * @param rut (formateado)
     * @param numeroPagina
     * @param cantidadRegistros (cantidad Registros por pagina)
     * @param codigoFirmados (31,32 Firmados y no Firmados)
     * @return ResumenPlan
     * @throws DAOException
     * @throws ServiceException
     */
	public ListaContratosBean getContratosFirmados(String rut, int numeroPagina, 
			    int cantidadRegistros, String codigoFirmados ) 
	        throws DAOException, ServiceException{
		
		ClientePerfilServicePortType port = null;
		ListaContratosBean listaContratos = null;
		
        LOGGER.info("Instanciando el Port.");
        try {
            port = (ClientePerfilServicePortType) WebServiceLocator
                    .getInstance().getPort(ClientePerfilService.class,
                            ClientePerfilServicePortType.class);

        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + ClientePerfilService.class);
            LOGGER.error( new DAOException(e));
        }
        
        LOGGER.info("Configurando parametros de la peticion");
        ListaContratosType listaContratosRequest = new ListaContratosType();
        listaContratosRequest.setRut(rut);
        listaContratosRequest.setNumeroPagina(numeroPagina);
        listaContratosRequest.setCantidadRegistros(cantidadRegistros);
        listaContratosRequest.setCodigoFirmados(codigoFirmados);
        
        ResultadoListaContratosType listaContratosResponse = null;
        LOGGER.info("Invocando servicio");
        
        try{
        	
        	listaContratosResponse = port.listaContratos(listaContratosRequest);
	        
        }catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
           		 + "listaContratos.", e);
            LOGGER.error( new DAOException(e));
        }
        
        String codigoRespuesta = listaContratosResponse.getRespuesta().getCodigo();
        String descripcionRespuesta = listaContratosResponse.getRespuesta().getDescripcion();
        
        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
        
        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
        	listaContratos = new ListaContratosBean();
            listaContratos.setNumeroTotalRegistros(listaContratosResponse.getNumeroRegistros());
            
            
            List<DocumentoSAEDType> documentos = listaContratosResponse.getDocumentos();
            ArrayList<ContratoBean> contratos = new ArrayList<ContratoBean>();
            
            for(DocumentoSAEDType doc: documentos){
            	ContratoBean contrato = new ContratoBean();
            	contrato.setRut(doc.getRut());
            	contrato.setUrl(doc.getUrl());
            	contrato.setNumeroNegocio(doc.getNumeroNegocio());
            	contrato.setNumeroDocumento(doc.getNumeroDocumento());
            	contrato.setNombreDocumento(doc.getNombreDocumento());
            	contrato.setFechaDocumento(doc.getFechaDocumento());
            	contrato.setEstadoFirma(doc.getEstadoFirma());
            	contrato.setDescripcionFirma(doc.getDescripcionFirma());
            	
            	contratos.add(contrato);
            }
            listaContratos.setDocumentos(contratos);
            
        }
        else {
            LOGGER.info("Service error code received: " + codigoRespuesta+ " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }
        return listaContratos;
	}
	
	
	/**
     * Lista de Contratos Firmados electronicamente con movil
     * 
     * @param rut (formateado)
     * @param numeroPagina
     * @param cantidadRegistros (cantidad Registros por pagina)
     * @param codigoFirmados (31,32 Firmados y no Firmados)
     * @return ResumenPlan
     * @throws DAOException
     * @throws ServiceException
     */
	public ListaContratosBean getContratosFirmadosConMovil(String rut, String numeroPagina, 
			    String cantidadRegistros, String codigoFirmados, String flagRutTitular, String movil) 
	        throws DAOException, ServiceException{
		
		MisContratosPortType port = null;
		ListaContratosBean listaContratos;
		
        LOGGER.info("Instanciando el Port.");
        try {
            port = (MisContratosPortType) WebServiceLocator
                    .getInstance().getPort(MisContratosService.class,
                    		MisContratosPortType.class);

        } catch (WebServiceLocatorException e) {
            LOGGER.error("Error al inicializar el Port "
                    + MisContratosService.class);
            LOGGER.error( new DAOException(e));
        }
        
        LOGGER.info("Configurando parametros de la peticion");
        MisContratosType misContratosRequest = new MisContratosType();
        misContratosRequest.setRut(rut);
        misContratosRequest.setNumeroPagina(numeroPagina);
        misContratosRequest.setCantidadRegistros(cantidadRegistros);
        misContratosRequest.setCodigoFirmados(codigoFirmados);
        misContratosRequest.setFlagRutTitular(flagRutTitular);
        misContratosRequest.setMovil(movil);
        
        ResultadoMisContratosType misContratosResponse = null;
        LOGGER.info("Invocando servicio");
        
        try{
        	
        	misContratosResponse = port.misContratos(misContratosRequest);
	        
        }catch (Exception e) {
            LOGGER.error("Exception caught on Service invocation: "
           		 + "listaContratos.", e);
            LOGGER.error( new DAOException(e));
        }
        
        String codigoRespuesta = misContratosResponse.getRespuesta().getCodigo();
        String descripcionRespuesta = misContratosResponse.getRespuesta().getDescripcion();
        
        LOGGER.info("codigoRespuesta " + codigoRespuesta);
        LOGGER.info("descripcionRespuesta " + descripcionRespuesta);
        
        listaContratos = new ListaContratosBean();
        
        listaContratos.setCodigo(codigoRespuesta);
    	listaContratos.setMensaje(descripcionRespuesta);
        
        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {
        	//listaContratos = new ListaContratosBean();
            listaContratos.setNumeroTotalRegistros(misContratosResponse.getNumeroRegistros());
            
            
            List<com.esa.clientes.canalescontacto.miscontratos.types.DocumentoSAEDType> documentos = misContratosResponse.getDocumentos();
            ArrayList<ContratoBean> contratos = new ArrayList<ContratoBean>();
            
            for(com.esa.clientes.canalescontacto.miscontratos.types.DocumentoSAEDType doc: documentos){
            	ContratoBean contrato = new ContratoBean();
            	contrato.setRut(doc.getRut());
            	contrato.setUrl(doc.getUrl());
            	contrato.setNumeroNegocio(doc.getNumeroNegocio());
            	contrato.setNumeroDocumento(doc.getNumeroDocumento());
            	contrato.setNombreDocumento(doc.getNombreDocumento());
            	contrato.setFechaDocumento(doc.getFechaDocumento());
            	contrato.setEstadoFirma(doc.getEstadoFirma());
            	contrato.setDescripcionFirma(doc.getDescripcionFirma());
            	contrato.setMovil(doc.getMovil());
            	
            	contratos.add(contrato);
            }
            listaContratos.setDocumentos(contratos);
            
        }
        
        return listaContratos;
	}
}
