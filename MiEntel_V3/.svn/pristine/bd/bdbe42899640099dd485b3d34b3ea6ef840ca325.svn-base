/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.vtasymktg.fidelizacion.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.administracion.suscripciones.delegate.SuscripcionesDelegate;
import com.epcs.bean.BolsaBean;
import com.epcs.bean.BolsasActualesDisponiblesBean;
import com.epcs.bean.CuentaClienteBean;
import com.epcs.bean.EstadoRenovacionEquipoBean;
import com.epcs.bean.FacturacionElectronicaBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.SituacionActualEquipoBean;
import com.epcs.bean.SmartPixelCustomParams;
import com.epcs.billing.prodfactura.delegate.FacturacionElectronicaDelegate;
import com.epcs.billing.prodfactura.util.FacturaElectronicaHelper;
import com.epcs.cliente.contacto.ClienteContactoService;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.cliente.perfil.delegate.EquipoDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * Controller para el portlet de smart pixel. Este controller carga las
 * variables necesarias para generar la marca de smart pixel a trav�s de un
 * llamado asincrono.
 * 
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 * @version 1.0 Version inicial
 */
public class SmartPixelController {

	private static final Logger LOGGER = Logger
			.getLogger(SmartPixelController.class);

	private static final String BOLSAS_BAM = MiEntelProperties
			.getProperty("parametros.bolsas.im.id");

	private SuscripcionesDelegate suscDelegate;

	private FacturacionElectronicaDelegate factElectDelegate;

	private EquipoDelegate equipoDelegate;
	
	private CuentaDelegate cuentaDelegate;

	private SmartPixelCustomParams customParams;

	private String respuestaJson;

	/**
	 * @return the suscDelegate
	 */
	public SuscripcionesDelegate getSuscDelegate() {
		return suscDelegate;
	}

	/**
	 * @param suscDelegate
	 *            the suscDelegate to set
	 */
	public void setSuscDelegate(SuscripcionesDelegate suscDelegate) {
		this.suscDelegate = suscDelegate;
	}

	/**
	 * @return the factElectDelegate
	 */
	public FacturacionElectronicaDelegate getFactElectDelegate() {
		return factElectDelegate;
	}

	/**
	 * @param factElectDelegate
	 *            the factElectDelegate to set
	 */
	public void setFactElectDelegate(
			FacturacionElectronicaDelegate factElectDelegate) {
		this.factElectDelegate = factElectDelegate;
	}

	/**
	 * @return the equipoDelegate
	 */
	public EquipoDelegate getEquipoDelegate() {
		return equipoDelegate;
	}

	/**
	 * @param equipoDelegate
	 *            the equipoDelegate to set
	 */
	public void setEquipoDelegate(EquipoDelegate equipoDelegate) {
		this.equipoDelegate = equipoDelegate;
	}

	public CuentaDelegate getCuentaDelegate() {
		return cuentaDelegate;
	}
	
	public void setCuentaDelegate(CuentaDelegate cuentaDelegate) {
		this.cuentaDelegate = cuentaDelegate;
	}
	
	
	/**
	 * @return the customParams
	 */
	public SmartPixelCustomParams getCustomParams() {
		return customParams;
	}

	/**
	 * @param customParams
	 *            the customParams to set
	 */
	public void setCustomParams(SmartPixelCustomParams customParams) {
		this.customParams = customParams;
	}

	/**
	 * @return the respuestaJson
	 */
	public String getRespuestaJson() {
		return respuestaJson;
	}

	/**
	 * @param respuestaJson
	 *            the respuestaJson to set
	 */
	public void setRespuestaJson(String respuestaJson) {
		this.respuestaJson = respuestaJson;
	}

	public void generarMarca(PhaseEvent phase) {
		try {
			customParams = new SmartPixelCustomParams();

			ProfileWrapper profile = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());

			String numeroPCS = ProfileWrapperHelper.getPropertyAsString(
					profile, "numeroPcsSeleccionado");
			//Datos de usuario
			String rutUsuario = ProfileWrapperHelper.getPropertyAsString(
					profile, "rutUsuarioSeleccionado");
			
			String mercado = ProfileWrapperHelper.getPropertyAsString(profile,
			"mercado");
			
			Map<String, String> resp = new HashMap<String, String>();
			
			String rutTitular = "";
			
			if(MiEntelBusinessHelper.isMercadoPrepago(mercado)){
				//Datos de titular
				rutTitular = ProfileWrapperHelper.getPropertyAsString(
						profile, "rutUsuario");	
			}else{
				rutTitular = ProfileWrapperHelper.getPropertyAsString(
						profile, "rutTitular");
			}
			
			String nroCuenta = ProfileWrapperHelper.getPropertyAsString(
					profile, "numeroCuenta");
			
			
			String subMercado = ProfileWrapperHelper.getPropertyAsString(
					profile, "subMercado");
			LOGGER.info("Submercado="+subMercado);
			
			RutBean rutBean = RutBean.parseRut(rutUsuario);
			String rutSinDV = Long.toString(rutBean.getNumero());
			String DV = String.valueOf(rutBean.getDigito());

			// Atributo
			if (MiEntelBusinessHelper.isMercadoPrepago(mercado)) {
				customParams.setAtributo(MiEntelProperties
						.getProperty("parametros.smartPixel.atributo.titular"));
			} else if (rutUsuario.equals(rutTitular)) {
				customParams.setAtributo(MiEntelProperties
						.getProperty("parametros.smartPixel.atributo.titular"));
			} else {
				customParams
						.setAtributo(MiEntelProperties
								.getProperty("parametros.smartPixel.atributo.noTitular"));
			}


			// Mercado
			customParams.setMercado(mercado);
			
			//Segmento
			customParams.setSegmento(obtenerSegmento(rutUsuario));
		
			if(!MiEntelBusinessHelper.isMercadoPrepago(mercado)){			
			
			// Facturacion Electronica
			FacturacionElectronicaBean factElect = null;
					
			
            try{
            	  factElect = this.getFacturacionElectronica(rutTitular, nroCuenta);
			 } catch (Exception e) {
				 customParams.setFacturaElectronica("");
	         }	
			
			  if(factElect!=null){ 
					if (FacturaElectronicaHelper.isInscrita(factElect.getEstadoFE())
							|| FacturaElectronicaHelper.isInscritaModificada(factElect
									.getEstadoFE()))
						customParams.setFacturaElectronica(MiEntelProperties
								.getProperty("parametros.smartPixel.fe.estado.activo"));
					else
						customParams
								.setFacturaElectronica(MiEntelProperties
										.getProperty("parametros.smartPixel.fe.estado.inactivo"));	
			   }else{
				   customParams.setFacturaElectronica("");
			   }
			}
			
			resp.put("Atributo", customParams.getAtributo());
			resp.put("Seg",customParams.getSegmento().equals("")?MiEntelProperties.getProperty("parametros.smartPixel.segmento.noinfo"):customParams.getSegmento());
			resp.put("Mercado",customParams.getMercado());
			resp.put("FE",customParams.getFacturaElectronica().equals("")?MiEntelProperties.getProperty("parametros.smartPixel.fe.pp"):customParams.getFacturaElectronica());
					
			respuestaJson = JsonHelper.toJsonResponse(resp);

		} catch (Exception e) {
			LOGGER
					.error(e.getMessage(), e);	
			respuestaJson = JsonHelper.toJsonErrorMessage("Error");
		}

	}

	private String obtenerSegmento(String rut){
		String segmento = "";
		try{
			List<CuentaClienteBean> cuentaClienteBeans = cuentaDelegate.obtenerCuentaCliente(rut);
			Iterator<CuentaClienteBean> cuentas = cuentaClienteBeans.iterator();
			boolean swPersona = false, swEmpresa = false;
			while(cuentas.hasNext()){
				CuentaClienteBean cuentaClienteBean = cuentas.next();
				LOGGER.info("Codigo grupo = "+cuentaClienteBean.getCodGrupo());
				if(swPersona==false && ParametrosHelper.getExisteParametro("smartPixel.grupos.persona",cuentaClienteBean.getCodGrupo())){
					segmento+=MiEntelProperties.getProperty("parametros.smartPixel.segmento.persona")+",";
					swPersona=true;
				}
				if(swEmpresa==false && ParametrosHelper.getExisteParametro("smartPixel.grupos.empresa",cuentaClienteBean.getCodGrupo())){
					segmento+=MiEntelProperties.getProperty("parametros.smartPixel.segmento.empresa")+",";
					swEmpresa=true;
				}
				if(swEmpresa){
					segmento = "Empresas";
				}
				if(swPersona){
					segmento = "Persona";
				}
				if(!swEmpresa && !swEmpresa){
					segmento="NO existe informacion";
				}
				LOGGER.info("El segmento es "+segmento);
			}
			
		}catch (DAOException e) {
			segmento="NO existe informacion";
			LOGGER.error("Error obteniendo segmento de cliente", e);
		}catch (ServiceException e) {
			segmento="NO existe informacion";
			LOGGER.error("Error obteniendo segmento de cliente", e);
		}catch (Exception e) {
			segmento="NO existe informacion";
			LOGGER.error("Error obteniendo segmento de cliente", e);
		}
		return segmento;
	}
	
	private SituacionActualEquipoBean obtenerSituacionActualEquipo(
			String numeroPCS, String rut, String nroCuenta, String rutSinDV,
			String DV) {

		SituacionActualEquipoBean situacionAct = null;

		try {
			situacionAct = equipoDelegate.obtenerSituacionActualEquipo(
					numeroPCS, rut, nroCuenta, rutSinDV, DV);
		} catch (DAOException e) {
			LOGGER.error(
					"Smart Pixel. DAOException consultando situacion actual de "
							+ "movil: " + numeroPCS, e);
		} catch (ServiceException e) {
			LOGGER
					.info("Smart Pixel. ServiceException consultando situacion actual de "
							+ "movil: " + numeroPCS, e);
		} catch (Exception e) {
			LOGGER.error(
					"Smart Pixel. Exception consultando situacion actual de "
							+ "movil: " + numeroPCS, e);
		}

		return situacionAct;
	}

	private FacturacionElectronicaBean getFacturacionElectronica(String rut,
			String nroCuenta) {

		FacturacionElectronicaBean factElect = null;
		try {
			factElect = factElectDelegate.getFacturacionElectronica(rut, nroCuenta);

		} catch (DAOException e) {
			LOGGER.error("Smart Pixel. DAOException consultando facturacion electronica para rut: " + rut, e);
		} catch (ServiceException e) {
			LOGGER.info("Smart Pixel. ServiceException consultando facturacion electronica para rut: " + rut, e);
		} catch (Exception e) {
			LOGGER.error("Smart Pixel. Exception consultando facturacion electronica para rut: " + rut, e);
		}
		return factElect;
	}

	private Map<String, String> consultarInetMovilYBolsas(String numeroPCS) {

		BolsasActualesDisponiblesBean bolsasActYDisp = null;
		Map<String, String> respuesta = new HashMap<String, String>();
		try {
			bolsasActYDisp = suscDelegate
					.consultarBolsasActualesDisponibles(numeroPCS);
			Iterator<BolsaBean> itBolsa = bolsasActYDisp.getBolsasActuales()
					.iterator();
			String bolsas = "";
			while (itBolsa.hasNext()) {
				BolsaBean bolsa = itBolsa.next();
				if (BOLSAS_BAM.contains(bolsa.getTipoBolsa())) {
					respuesta.put("InternetMovil", "OK");
				}
				bolsas = bolsas.equals("") ? bolsa.getSnCodigo() : "+"
						+ bolsa.getSnCodigo();
			}
			respuesta.put("bolsas", bolsas);

		} catch (DAOException ex) {
			LOGGER.error("Smart Pixel. DAOException consultando bolsas para "
					+ "movil: " + numeroPCS, ex);
		} catch (ServiceException e) {
			LOGGER.info("Smart Pixel. ServiceException consultando para "
					+ "movil: " + numeroPCS, e);
		} catch (Exception e) {
			LOGGER.error("Smart Pixel. Exception consultando bolsas para "
					+ "movil: " + numeroPCS, e);
		}

		return respuesta;
	}

}