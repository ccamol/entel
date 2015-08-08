/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.bean.ResumenProductosContratadosBean;
import com.epcs.cliente.perfil.ClientePerfilService;
import com.epcs.cliente.perfil.ClientePerfilServicePortType;
import com.epcs.cliente.perfil.types.BolsaActualType;
import com.epcs.cliente.perfil.types.ConsultarProductosType;
import com.epcs.cliente.perfil.types.ProductosActualesType;
import com.epcs.cliente.perfil.types.ResultadoConsultarProductosType;
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
public class ProductoDAO {

    private static final Logger LOGGER = Logger.getLogger(ProductoDAO.class);
    private static final Logger PRODUCTOS_LOGGER = Logger.getLogger("cajaProdContratadosLog");

    public static final String CODIGO_RESPUESTA_OK = MiEntelProperties.getProperty("servicios.codigoRespuesta.exito");

    /**
     * Entrega los productos contratados para el numero <code>numeroPcs</code>
     * dentro del mercado indicado en <code>mercado</code>
     * Este metodo SOLO REGISTRA en el log de la Caja de Productos Contratados 
     * del Dashboard y no en el log definido por defecto para la clase.
     * 
     * @param mercado
     *            String indicador de mercado
     * @param numeroPcs
     *            String numero PCS
     * @return {@link ResumenProductosContratadosBean} con los productos
     *         contratados
     * @throws DAOException
     *             En caso de un error al conectarse con los Servicios, o
     *             parametros incorrectos.
     * @throws ServiceException
     */
    public ResumenProductosContratadosBean getProductosContratados(
            String mercado, String numeroPcs) throws DAOException,
            ServiceException {

        ResumenProductosContratadosBean resumenProductosContratadosBean = new ResumenProductosContratadosBean();

        ClientePerfilServicePortType port = null;
        ClientePerfilService service = null;
        
        PRODUCTOS_LOGGER.info("****** Clase: com.epcs.cliente.perfil.dao.ProductoDAO ******");
        PRODUCTOS_LOGGER.info("Instanciando el port");
        
        try {
            port = (ClientePerfilServicePortType) WebServiceLocator
                    .getInstance().getPort(ClientePerfilService.class,
                            ClientePerfilServicePortType.class);
            service = (ClientePerfilService) WebServiceLocator
					.getInstance().getService(ClientePerfilService.class, false);
        } catch (WebServiceLocatorException e) {
        	PRODUCTOS_LOGGER.error("Error al inicializar el Port "
                    + ClientePerfilServicePortType.class, e);
            LOGGER.error( new DAOException(e));
        }

    	PRODUCTOS_LOGGER.info("Configurando Datos de la peticion");
        PRODUCTOS_LOGGER.info("****** INICIO INPUT ******");
        PRODUCTOS_LOGGER.info("Mercado:    " + mercado);
        PRODUCTOS_LOGGER.info("Msisdn:     " + numeroPcs);
    	PRODUCTOS_LOGGER.info("****** FIN INPUT ******");
        
        ConsultarProductosType consultarType = new ConsultarProductosType();
        consultarType.setMercado(mercado);
        consultarType.setMsisdn(numeroPcs);

        PRODUCTOS_LOGGER.info("Invocando servicio");
        PRODUCTOS_LOGGER.info("Service name: " + service.getServiceName());
	    PRODUCTOS_LOGGER.info("WSDL Document Location: " + service.getWSDLDocumentLocation());
        PRODUCTOS_LOGGER.info("Operacion: ConsultarProductos");
            
        ResultadoConsultarProductosType resultadoType = null;
        try {
            resultadoType = port.consultarProductos(consultarType);
        } catch (Exception e) {
        	PRODUCTOS_LOGGER.error("Exception caught on Service invocation: "
        		 + "consultarProductos.", e);
            LOGGER.error( new DAOException(e));
        }

        String codigoRespuesta = resultadoType.getRespuesta().getCodigo();
        String descripcionRespuesta = resultadoType.getRespuesta().getDescripcion();

        if (Utils.isEmptyString(codigoRespuesta)) {
        	PRODUCTOS_LOGGER.error("Servicio ConsultarProductos no respondio "
                    + "con codigoRespuesta");
            LOGGER.error( new DAOException("Servicio ConsultarProductos no respondio "
                    + "con codigoRespuesta"));
        }

    	PRODUCTOS_LOGGER.info("****** INICIO OUTPUT ******");
    	PRODUCTOS_LOGGER.info("codigoRespuesta " + codigoRespuesta);
        PRODUCTOS_LOGGER.info("descripcionRespuesta " + descripcionRespuesta);

        if (CODIGO_RESPUESTA_OK.equals(codigoRespuesta)) {

        	try{
        		ProductosActualesType productosActualesType = resultadoType
                .getProductos();

        		PRODUCTOS_LOGGER.info("****** Plan Actual ******");
                PRODUCTOS_LOGGER.info("productosActualesType.getPlanActual(): " + productosActualesType.getPlanActual()
	                        .getDscPlan());
                
		        resumenProductosContratadosBean
		                .setNombrePlanVoz(productosActualesType.getPlanActual()
		                        .getDscPlan());
		
		        List<String> listaBolsas = new LinkedList<String>();
		        List<BolsaActualType> bolsasActualesList = productosActualesType
		                .getBolsasActuales() != null ? productosActualesType
		                .getBolsasActuales().getBolsa()
		                : new LinkedList<BolsaActualType>();
		
		        PRODUCTOS_LOGGER.info("****** Listado de Bolsas o Productos ******");
		        for (BolsaActualType bolsaActual : bolsasActualesList) {
		
		            /*
		             * El plan internet es informado por el servicio como una
		             * "bolsa" contratada. Siguiendo la logica de Entel V2. El
		             * metodo isBolsaPlanInternet, revisa si la bolsa corresponde a
		             * un plan de datos contratados
		             */
		//            if (isBolsaPlanInternet(bolsaActual)) {
		//                resumenProductosContratadosBean
		//                        .setNombrePlanInternet(bolsaActual.getDscBolsa());
		//            }
		//            else {
		                if (!bolsaActual.getCsdSn().isEmpty()) {
	                		PRODUCTOS_LOGGER.info("bolsaActual.getCsdSn(): " + bolsaActual.getCsdSn());
	                		PRODUCTOS_LOGGER.info("bolsaActual.getCdgSp(): " + bolsaActual.getCdgSp());
	                		PRODUCTOS_LOGGER.info("bolsaActual.getDscBolsa(): " + bolsaActual.getDscBolsa());
		                	listaBolsas.add(bolsaActual.getCsdSn() + ";" + bolsaActual.getDscBolsa());
		                } else {
		                    listaBolsas.add(bolsaActual.getDscBolsa());
		                }                    
		//            }
		        }
		
		        resumenProductosContratadosBean.setNombreBolsas(listaBolsas);
		        
        	} catch (Exception e) {
                PRODUCTOS_LOGGER.error("Exception caught on Service response: "
               		 + "consultarProductos", e);
                LOGGER.error( new DAOException(e));
           }

        }
        else {
            PRODUCTOS_LOGGER.info("Service error code received: " + codigoRespuesta
                    + " - " + descripcionRespuesta);
            LOGGER.error( new ServiceException(codigoRespuesta, descripcionRespuesta));
        }

        return resumenProductosContratadosBean;

    }

    /**
     * Indica si la bolsa <code>bolsaActual</code> corresponde a un Plan de
     * Internet.<br>
     * Para ellos compara el coigo de <code>bolsaActual</code> con el valor
     * conocido de la bols de Plan de Internet
     * 
     * @param bolsaActual
     *            {@link BolsaActualType} bolsa a comparar
     * @return true si <code>bolsaActual</code> es Plan de Internet. false en
     *         caso contrario
     */
    private boolean isBolsaPlanInternet(BolsaActualType bolsaActual) {

        /*
         * Si el codigo de la bolsa coincide con el parametro de miEntel de
         * "bolsas.planInternet.codigo" la bolsa corresponde a un Plan de
         * Internet
         */
        return bolsaActual.getCdgSp().equalsIgnoreCase(
                MiEntelProperties.getProperty("bolsas.planInternet.codigo"));
    }  
}
