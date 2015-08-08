/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.bea.portlet.GenericURL;
import com.epcs.bean.MsisdnAsociadoBean;
import com.epcs.bean.PerfilUsuarioBean;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.inscripcion.util.InscripcionHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * controller para portlet jsf de selector de cuenta.
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class SelectorCuentaController implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger
			.getLogger(SelectorCuentaController.class);

	private List<MsisdnAsociadoBean> numerosAsociados;

	private String currentMsisdn;

	private static final String COD_INGRESO_FORMRUT = MiEntelProperties
			.getProperty("inscripcion.formPreRegistro.codigo.ingresar");
	
	private static final String ASOCIADO_NOREGISTRADO = "N";
	
	private static final String START_PARAMETER_URL = "?";
    
	private static final String DEFAULT_PARAMETER_SEPARATOR = "&";

	
	public SelectorCuentaController(){
				
	    ProfileWrapper profileWrapper = ProfileWrapperHelper
        .getProfile(JSFPortletHelper.getRequest());

	    try {
	    
		    numerosAsociados = (List<MsisdnAsociadoBean>) JSFPortletHelper.getSession().getAttribute("numerosAsociados");   
		    
	        if (numerosAsociados == null) {
	
	            String movilUsuario = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcs");
	            String aaa = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
	            String flagBam = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"flagBam");
	            String subMercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"subMercado");
	            
	            if(MiEntelBusinessHelper.getAAATitular().equals(aaa)){
		            CuentaDelegate cuentaDelegate = new CuentaDelegate();
		            numerosAsociados = cuentaDelegate
		                    .getListaMsisdnAsociados(movilUsuario,subMercado,flagBam);
		            configurarParametrosSesion(movilUsuario);
	            }
	        }        

        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage());
            JSFMessagesHelper
                    .addServiceErrorMessage("obtenerNumerosAsociados");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught: "
                    + e.getCodigoRespuesta() + " - "
                    + e.getDescripcionRespuesta());
            JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles",
                    e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught: " + e.getMessage());
            JSFMessagesHelper
                    .addServiceErrorMessage("obtenerNumerosAsociados");
        }
  

	    // Actualizacion numero pcs seleccionado en lista HTML
	    String numeroPcsSeleccionado;
	    try {
	        String profileProperty = MiEntelProperties
	                .getProperty("miEntel.userProfile.numeroPcsSeleccionado.property");
	        numeroPcsSeleccionado = ProfileWrapperHelper
	                .getPropertyAsString(profileWrapper, profileProperty);	        	        
	        
	        setCurrentMsisdn(numeroPcsSeleccionado);	                	       
	        
	    } catch (Exception e) {
	        LOGGER.error("No es posible identificar numeroPcsSeleccionado",
	                e);
	        JSFMessagesHelper
	                .addServiceErrorMessage("obtenerNumerosAsociados");
	    }

	}
	
	// private CuentaDelegate cuentaDelegate;

	/**
	 * @return the msisdnAsociados
	 */
	public List<MsisdnAsociadoBean> getMsisdnAsociados() {
		return numerosAsociados;
	}

	/**
	 * @param msisdnAsociados
	 *            the msisdnAsociados to set
	 */
	public void setMsisdnAsociados(List<MsisdnAsociadoBean> msisdnAsociados) {
		this.numerosAsociados = msisdnAsociados;
	}

	// /**
	// * @return the cuentaDelegate
	// */
	// public CuentaDelegate getCuentaDelegate() {
	// return cuentaDelegate;
	// }
	//
	// /**
	// * @param cuentaDelegate
	// * the cuentaDelegate to set
	// */
	// public void setCuentaDelegate(CuentaDelegate cuentaDelegate) {
	// this.cuentaDelegate = cuentaDelegate;
	// }

	/**
	 * Este metodo permite cargar la lista de numeros de moviles asociados a la
	 * cuenta del usuario, a traves del servicio web.
	 * 
	 * @param event
	 *            evento de inicializacion de jsf.
	 */
	/*public void init(PhaseEvent event) {

		ProfileWrapper profileWrapper = ProfileWrapperHelper
				.getProfile(JSFPortletHelper.getRequest());

		if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())) {

			if (numerosAsociados == null) {

				try {
					String movilUsuario = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcs");
					String aaa = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
	                String flagBam = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"flagBam");
	                String subMercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"subMercado");
					
                    if(MiEntelBusinessHelper.getAAATitular().equals(aaa)){
					CuentaDelegate cuentaDelegate = new CuentaDelegate();
					numerosAsociados = cuentaDelegate
							.getListaMsisdnAsociados(movilUsuario,subMercado,flagBam);
					configurarEstadoEquipoSesion(movilUsuario);
					}

				} catch (DAOException e) {
					LOGGER.error("DAOException caught: " + e.getMessage(), e);
					JSFMessagesHelper
							.addServiceErrorMessage("obtenerNumerosAsociados");
				} catch (ServiceException e) {
					LOGGER.info("ServiceException caught: "
							+ e.getCodigoRespuesta() + " - "
							+ e.getDescripcionRespuesta());
					JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles",
							e.getCodigoRespuesta());
				} catch (Exception e) {
					LOGGER.error("Exception caught: " + e.getMessage(), e);
					JSFMessagesHelper
							.addServiceErrorMessage("obtenerNumerosAsociados");
				}
			}

			// Actualizacion numero pcs seleccionado en lista HTML
			String numeroPcsSeleccionado;
			try {
				String profileProperty = MiEntelProperties
						.getProperty("miEntel.userProfile.numeroPcsSeleccionado.property");
				numeroPcsSeleccionado = ProfileWrapperHelper
						.getPropertyAsString(profileWrapper, profileProperty);
				setCurrentMsisdn(numeroPcsSeleccionado);
			} catch (Exception e) {
				LOGGER.error("No es posible identificar numeroPcsSeleccionado",
						e);
				JSFMessagesHelper
						.addServiceErrorMessage("obtenerNumerosAsociados");
			}

		}

	}
*/

	/**
	 * Metodo que permite obtener un flag booleano que indica si el atributo de
	 * auto atencion del usuario actual corresponde a un usuario administrador
	 * de cuentas que tiene derecho a presentar el portlet de selector de
	 * cuentas.
	 * 
	 * @return "true" si el usuario
	 */
	public boolean isUserAdmin() {

		ProfileWrapper profileWrapper = ProfileWrapperHelper
				.getProfile(JSFPortletHelper.getRequest());
		try {

			String codigoAAATitular = MiEntelProperties
					.getProperty("aaa.titular.code");
			String userCodigoAAA = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "aaa");

			return codigoAAATitular.equals(userCodigoAAA);
		} catch (Exception e) {
			LOGGER.error("No se ha podido obtener el atributo "
					+ "de auto atencion para el usuario actual", e);
			return false;
		}
	}

	public void setCurrentMsisdn(String currentMsisdn) {
		this.currentMsisdn = currentMsisdn;
	}

	/**
	 * A traves de este metodo la lista desplegable del selector de cuentas
	 * muestra por defecto la seleccion realizada por el usuario.
	 * 
	 * @return el id del movil seleccionado.
	 */
	public String getCurrentMsisdn() {
		return currentMsisdn;
	}

	/**
	 * Este metodo permite obtener los valores a mostrar en la lista del
	 * selector de cuentas.
	 * 
	 * @return un arreglo de objeto SelectItem con la informcion a desplegar en
	 *         la lista.
	 * @throws DAOException
	 *             en caso de un error en el servicio de obtencion de la
	 *             informacion.
	 */
	public SelectItem[] getMsisdnAsociadoList() {
	    numerosAsociados = ( numerosAsociados != null ) ? numerosAsociados : Collections.<MsisdnAsociadoBean>emptyList();
		return JsfUtil.getSelectItemsMsisdnAsociados(numerosAsociados, false);
	}
	
	private MsisdnAsociadoBean busquedaAsociado(String numero) throws Exception{
		MsisdnAsociadoBean currentBean = null;
		if(numerosAsociados!=null){
			for(MsisdnAsociadoBean bean : numerosAsociados){
				if(bean.getNumeroPcs().equals(numero)){
					currentBean = bean;
				}
			}			
		}
		
		if(currentBean != null){
			LOGGER.info("Movil seleccionado, datos lista asociados: " 
					+ "\nNumeroPcs: " + currentBean.getNumeroPcs()
					+ " - FlagBam: " + currentBean.getFlagBam()
					+ " - Mercado: " + currentBean.getMercado()
					+ " - SubMercado: " + currentBean.getSubMercado());
			return currentBean;
		}
		else{
			LOGGER.error( new Exception("No se pudo obtener la lista de numeros asociados de la sesion"));
		}	
		return new MsisdnAsociadoBean();
	}
	
	public void cambiarCuenta(ValueChangeEvent changeEvent) {
		HttpServletRequest request = JSFPortletHelper.getRequest(FacesContext
				.getCurrentInstance());

		// Perfil de usuario manejado por UUP
		ProfileWrapper profileWrapper = ProfileWrapperHelper
				.getProfile(request);
		
		// se obtiene el numeroPcs seleccionado
		String msisdn = (String) changeEvent.getNewValue();
		LOGGER.info("Nuevo movil seleccionado: " + msisdn);
		
		MsisdnAsociadoBean currentBean = null;
		
		try{
			currentBean = busquedaAsociado(msisdn);
			this.currentMsisdn = msisdn;
			
			if(ASOCIADO_NOREGISTRADO.equals(currentBean.getFlagRegistrado())){
				InscripcionHelper insHelper = new InscripcionHelper();
				insHelper.startInscripcion(profileWrapper, msisdn);
				return;
			}			
		} catch (Exception e) {
			LOGGER.error("Exception caught: " + e.getMessage(), e);
			JSFMessagesHelper.addServiceErrorMessage("obtenerPerfil",
					new String[] { msisdn });
		}
		
		// Flag de error para programar a salida
		boolean errorFlag = false;

		try {
			// Actualizar perfil en UUP
			ProfileWrapperHelper.setProperty(profileWrapper, MiEntelProperties
					.getProperty("miEntel.userProfile.mercado.property"),
					currentBean.getMercado());

			ProfileWrapperHelper.setProperty(profileWrapper, MiEntelProperties
					.getProperty("miEntel.userProfile.flagBam.property"),
					currentBean.getFlagBam());
			
			ProfileWrapperHelper.setProperty(profileWrapper, MiEntelProperties
					.getProperty("miEntel.userProfile.numeroPcsSeleccionado.property"),
					currentBean.getNumeroPcs());				
			
            ProfileWrapperHelper.setProperty(profileWrapper, MiEntelProperties
                    .getProperty("miEntel.userProfile.subMercado.property"),
                    currentBean.getSubMercado());
            
            LOGGER.info("Movil Logueado: " + ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs"));
            LOGGER.info("Movil seleccionado, datos UUP: " 
					+ "\nNumeroPcs: " + ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado")
					+ " - FlagBam: " + ProfileWrapperHelper.getPropertyAsString(profileWrapper, "flagBam")
					+ " - Mercado: " + ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado")
					+ " - SubMercado: " + ProfileWrapperHelper.getPropertyAsString(profileWrapper, "subMercado"));

		} catch (IOException e) {
			LOGGER.error("No es posible realizar cambio "
					+ "de n&uacute;mero consultado", e);
			JSFMessagesHelper.addServiceErrorMessage("cambiarCuenta",
					new String[] { "asociada al numero " + msisdn });
			errorFlag = true;

		} catch (Exception e) {
			LOGGER.error("No ha sido posible actualizar el perfil "
					+ "de usuario con la informacion obtenida del "
					+ "servicio de perfilamiento", e);
			JSFMessagesHelper.addServiceErrorMessage("cambiarCuenta",
					new String[] { "asociada al numero " + msisdn });
			errorFlag = true;
		} finally {
			// Se prepara salida
			prepareResponse(request, errorFlag);
		}
				
	}
	
	

	/**
	 * 
	 * @param changeEvent
	 */
	/*
	public void cambiarCuenta(ValueChangeEvent changeEvent) {

		HttpServletRequest request = JSFPortletHelper.getRequest(FacesContext
				.getCurrentInstance());

		// Perfil de usuario manejado por UUP
		ProfileWrapper profileWrapper = ProfileWrapperHelper
				.getProfile(request);
		
		// se obtiene el numeroPcs seleccionado
		String msisdn = (String) changeEvent.getNewValue();
		PerfilUsuarioBean perfil = null;

		try {
			CuentaDelegate cuentaDelegate = new CuentaDelegate();
			perfil = cuentaDelegate.obtenerPerfilUsuario(msisdn);

		} catch (DAOException e) {
			LOGGER.error("DAOException caught: " + e.getMessage(), e);
			JSFMessagesHelper.addServiceErrorMessage("obtenerPerfil",
					new String[] { msisdn });

		} catch (ServiceException e) {

			if (e.getCodigoRespuesta().equals(COD_INGRESO_FORMRUT)) {
				// Se inicia el ciclo para la inscripcion de usuario , flujo
				// selector de cuenta.
				InscripcionHelper insHelper = new InscripcionHelper();
				insHelper.startInscripcion(profileWrapper, msisdn);
				return;

			} else {

				LOGGER.info("ServiceException caught: msisdn: "+msisdn+" - "
						+ e.getCodigoRespuesta() + " - "
						+ e.getDescripcionRespuesta());
				JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", e
						.getCodigoRespuesta());
			}

		} catch (Exception e) {
			LOGGER.error("Exception caught: " + e.getMessage(), e);
			JSFMessagesHelper.addServiceErrorMessage("obtenerPerfil",
					new String[] { msisdn });
		}

		// Flag de error para programar a salida
		boolean errorFlag = false;

		try {
			// Actualizar perfil en UUP
			ProfileWrapperHelper.setProperty(profileWrapper, MiEntelProperties
					.getProperty("miEntel.userProfile.mercado.property"),
					perfil.getMercado());

			ProfileWrapperHelper.setProperty(profileWrapper, MiEntelProperties
					.getProperty("miEntel.userProfile.flagBam.property"),
					perfil.getFlagBam());

			if (perfil.getUsuarioSeleccionado() != null) {
				ProfileWrapperHelper
						.setProperty(
								profileWrapper,
								MiEntelProperties
										.getProperty("miEntel.userProfile.numeroPcsSeleccionado.property"),
								perfil.getUsuarioSeleccionado().getNumeroPcs());

				ProfileWrapperHelper
						.setProperty(
								profileWrapper,
								MiEntelProperties
										.getProperty("miEntel.userProfile.nombreUsuarioSeleccionado.property"),
								perfil.getUsuarioSeleccionado()
										.getNombreUsuario());

				ProfileWrapperHelper
						.setProperty(
								profileWrapper,
								MiEntelProperties
										.getProperty("miEntel.userProfile.rutUsuarioSeleccionado.property"),
								perfil.getUsuarioSeleccionado().getRut()
										.toString());
				
                ProfileWrapperHelper
                        .setProperty(
                                profileWrapper,
                                MiEntelProperties
                                        .getProperty("miEntel.userProfile.subMercado.property"),
                                perfil.getUsuarioSeleccionado().getSubMercado());		
                
                setCurrentMsisdn(perfil.getUsuarioSeleccionado().getNumeroPcs());
                
			}

		} catch (IOException e) {
			LOGGER.error("No es posible realizar cambio "
					+ "de n&uacute;mero consultado", e);
			JSFMessagesHelper.addServiceErrorMessage("cambiarCuenta",
					new String[] { "asociada al numero " + msisdn });
			errorFlag = true;

		} catch (Exception e) {
			LOGGER.error("No ha sido posible actualizar el perfil "
					+ "de usuario con la informacion obtenida del "
					+ "servicio de perfilamiento", e);
			JSFMessagesHelper.addServiceErrorMessage("cambiarCuenta",
					new String[] { "asociada al numero " + msisdn });
			errorFlag = true;
		} finally {
			// Se prepara salida
			prepareResponse(request, errorFlag);
		}

	}
	*/

	private void prepareResponse(HttpServletRequest request, boolean error) {

		if (error) {
			FacesContext.getCurrentInstance().renderResponse();
		} else {
			// redirect a desktop del mercado al que corresponde numeroPcs
			// seleccionado
			HttpServletResponse response = JSFPortletHelper.getResponse();
			try {
				// Path portal de la aplicacion
				String portalPath = Utils.getMiEntelPortalPath(request);

				// URL por defecto: Login de Mientel, en protocolo http
				GenericURL defaultURL = GenericURL.createGenericURL(request,response);
				defaultURL.setTemplate("default");
				defaultURL.setContextualPath(portalPath);

				// Url final hacia donde se enviara el request entrante
				String url = defaultURL.toString(true);
				
				
				
				//INICIO 
				
				HttpServletRequest servletRequest = (HttpServletRequest) request;
				String queryString = servletRequest.getQueryString();
				
				url = url.concat(DEFAULT_PARAMETER_SEPARATOR + queryString);
				   
				// FIN 

				
				
				response.sendRedirect(url);

				// Se indica que al ciclo de JSF que la peticion debe finalizar
				FacesContext.getCurrentInstance().responseComplete();

			} catch (Exception e) {
				LOGGER.error("Unexpected error on redirect", e);
				FacesContext.getCurrentInstance().renderResponse();
			}

		}
	}
	
	
    /**
     * 
     * @param asociados
     */
    private synchronized void configurarParametrosSesion(String msisdn) {
        try {
            if (numerosAsociados != null) {
                for (MsisdnAsociadoBean msisdnAsociadoBean : numerosAsociados) {
                    if (msisdnAsociadoBean.getNumeroPcs().equals(msisdn)) {                    	
                    	
                        HttpSession session = JSFPortletHelper.getSession();
                        session.setAttribute("flagEstadoMsisdn",
                                msisdnAsociadoBean.getFlagEstadoMsisdn());
                        break;
                    }
                }
                JSFPortletHelper.getSession().setAttribute("numerosAsociados", numerosAsociados);
            }
        } catch (Exception e) {
            LOGGER.warn("No fue posible configurarEstadoEquipo en Sesion ", e);
        }
    }

}
