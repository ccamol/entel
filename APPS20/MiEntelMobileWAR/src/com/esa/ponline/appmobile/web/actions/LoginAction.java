/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.actions;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletResponseAware;

import unix.roble.Operator;
import unix.roble.ServiceException_Exception;
import unix.roble.ValidaMsisdnService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.epcs.cliente.perfil.types.ConsultaMercadoType;
import com.epcs.cliente.perfil.types.ResultadoConsultarPerfilamientoType;
import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.util.ArcFour;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.util.DefineTemplate;
import com.esa.ponline.appmobile.util.Formato;
import com.esa.ponline.appmobile.util.Rut;
import com.esa.ponline.appmobile.vo.RutVO;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.web.delegate.DelegateLogin;
import com.esa.ponline.appmobile.web.delegate.DelegateRegister;
import com.esa.ponline.appmobile.web.helper.ActionHelper;
import com.esa.ponline.appmobile.ws.EntelServices;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class LoginAction extends ActionSupport implements ServletResponseAware, ModelDriven<LoginVO> {

	private static final long serialVersionUID = -524257514124413768L;

	private static final Logger LOGGER = Logger.getLogger(LoginAction.class);

	private DelegateRegister delegateRegistro = new DelegateRegister();

	private DelegateLogin delegate = new DelegateLogin();

	private DefineTemplate dt = new DefineTemplate();

	protected HttpServletResponse servletResponse;
	
	private String errorService;

	private String mensajeError;

	private LoginVO cliente;

	private boolean checkMe;

	private String msisdn;

	private String pin;

	private String rut;

	public String execute() throws Exception {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			String action = req.getParameter("method:execute");

			if ("Login".equalsIgnoreCase(action)) {
				if (isValidFields(req)) {
					return validaUsuario(req);
				} else {
					return dt.defineTemplateInicio(req);
				}
			}
			return dt.defineTemplateInicio(req);

		} catch (Exception e) {
			LOGGER.error("Exception " + e);
			LOGGER.error("Ha ocurrido un problema en LoginAction " + e.getMessage());
			e.printStackTrace();
			return "error_general";
		}
	}

	private String validaUsuario(HttpServletRequest req) {
		cliente = new LoginVO();
		// String userAgent = req.getHeader("User-agent");
		String userData = req.getParameter("msisdn") + "," + Rut.formatTrim(req.getParameter("rut")) + ","
				+ req.getParameter("pin") + "," + req.getHeader("User-Agent");
		cliente.setMsisdn(req.getParameter("msisdn"));
		cliente.setRut(Rut.formatTrim(req.getParameter("rut")));
		boolean isSession = false;

		// consulta registro
		String mercadoResponse = null;
		try {
			mercadoResponse = obtieneCompania("569" + cliente.getMsisdn());
		} catch (EntelServicesLocatorException e1) {
			LOGGER.error("Error en servicio");
			LOGGER.error(Config.getAppProperty("validaMsisdnServiceEndPoint") + " " + e1.getMessage());
			e1.printStackTrace();
		}
		if (mercadoResponse == null) {
			LOGGER.error("Error en servicio");
			LOGGER.error(Config.getAppProperty("validaMsisdnServiceEndPoint"));
			setMensajeError("Este servicio no se encuentra disponible, inténtalo más tarde");
			return dt.defineTemplateInicio(req);
		}

		if (mercadoResponse.equals("001")) {
			if (existeRegistro(cliente.getMsisdn())) {
				try {
					if (delegate.validaLogin(userData)) {
						ActionHelper session = new ActionHelper();
						session.creaSession(cliente, req, true);
						cliente = (LoginVO) getSession().get("cliente");
						if (req.getParameter("saveSession") == null) {
							generaCookie(req);
						}
						
						if (cliente != null){
						    LOGGER.info("Informacion cliente se logra obtener por variable de session [cliente]");
						    LOGGER.info("Login correcto msisdn " + cliente.getMsisdn() + " mercado " + cliente.getMercado());
						    isSession = true;
						} else {
						    cliente = new LoginVO();
						    ActionHelper actionHelper = new ActionHelper();
						    ResultadoConsultarPerfilamientoType response = actionHelper.obtienePerfil(req.getParameter("msisdn"));
						    		    
						    cliente.setMsisdn(req.getParameter("msisdn"));
						    cliente.setRut(Rut.formatTrim(req.getParameter("rut")));
						    
						    actionHelper.creaSession(cliente, req, true);
						    
						    LOGGER.info("No es posible obtener variable de session [cliente]");
						    LOGGER.info("No es posible realizar login con session [cliente]");
						    LOGGER.info("Se efectua autenticacion con objeto [LoginVO]");
						}
						return dt.defineTemplateMercado(cliente, req, isSession);
					} else {
						setMensajeError("Los datos ingresados no son v\u00e1lidos");
						return dt.defineTemplateInicio(req);
					}
				} catch (Exception e) {
					LOGGER.error("Se ha producido un problema al intentar autenticar usuario " + e.getMessage());
					setMensajeError("Los datos ingresados no son v\u00e1lidos");
					return dt.defineTemplateInicio(req);
				}
			} else {
				if (errorService != null) {
					if (errorService.equals("99")) {
						LOGGER.error("No es posible validar datos registro");
						LOGGER.error("Problema en Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
						setMensajeError("Este servicio no se encuentra disponible, inténtalo más tarde");
						return dt.defineTemplateInicio(req);
					}
				}
				try {
					if (delegateRegistro.validaDatosRegistro(cliente.getMsisdn(), req.getParameter("pin"))) {
						LOGGER.info("Se redirige al usuario registro paso 3");
						SessionMap<String, Object> session = new SessionMap<String, Object>(req);
						session.put("pinSiginAction", req.getParameter("pin"));
						session.put(Constants.CLIENTE_REGISTRO_SIGNINLOGIN.getStringValue(), cliente);
						return "registro";
					} else {
						LOGGER.info("Usuario ha ingresado datos no validos");
						LOGGER.info("Usuario se mantiene en login");
						setMensajeError("Los datos ingresados no son v\u00e1lidos");
						return dt.defineTemplateInicio(req);
					}
				} catch (Exception e) {
					LOGGER.error("No es posible validar usuario " + e.getMessage());
					e.printStackTrace();
				}
				return "error_general";
			}
		} else {
			setMensajeError("N\u00famero no pertenece a Entel");
			return dt.defineTemplateInicio(req);
		}
	}

	private boolean existeRegistro(String msisdn) {
		// forTest
		// return false;
		// fi forTest

		try {
			if (delegateRegistro.existeRegistro(msisdn)) {
				return true;
			}
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio: " + Config.getAppProperty("profileServiceEndPoint")
					+ error.getMessage());
			error.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("No es posible validar registro debido a: " + e.getMessage());
			LOGGER.error("Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
			e.printStackTrace();
			setErrorService("99");
		}
		return false;
	}
	
	private void setErrorService(String string) {
		this.errorService = "99";
	}

	private String obtieneCompania(String key) throws EntelServicesLocatorException {
		String datos[] = key.split(",");
		ConsultaMercadoType consultaMercado = new ConsultaMercadoType();
		consultaMercado.setMsisdn(datos[0]);

		Operator resCompania = new Operator();
		try {
			resCompania = EntelServices.validaCompania().getValidaMsisdnPort().validaMisdn("569" + datos[0]);
		} catch (ServiceException_Exception e) {
			LOGGER.error("Problema en servicio " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Error al inicializar el Servicio " + ValidaMsisdnService.class, e);
			try {
				throw new EntelServicesLocatorException(e.getMessage());
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("No es posible crear la llamada al servicio ValidaMsisdnService: " + e.getMessage());
				e1.printStackTrace();
			}
		}

		if (resCompania.getOperatorId() != null) {
			return resCompania.getOperatorId();
		} else {
			return null;
		}
	}

	private boolean isValidFields(HttpServletRequest req) {
		boolean mensaje = true;
		try {
			if (!(Formato.esNumerico(req.getParameter("msisdn")) && req.getParameter("msisdn").length() == 8)) {
				mensaje = false;
			}
		} catch (Exception e) {
			LOGGER.error("Error en validacion de campos: " + e.getMessage());
			e.printStackTrace();
		}

		RutVO.parseRut(req.getParameter("rut"));
		if (!RutVO.validate()) {
			mensaje = false;
		}
		try {
			if (!(Formato.esNumerico(req.getParameter("pin")) && req.getParameter("pin").length() == 4)) {
				mensaje = false;
			}
		} catch (Exception e) {
			LOGGER.error("Error en validacion de campos: " + e.getMessage());
			e.printStackTrace();
		}
		if (!mensaje) {
			setMensajeError("Debes ingresar datos v\u00e1lidos");
		}
		return mensaje;
	}

	private String encriptaCookie(String tokenDesencriptado) {
		return ArcFour.getInstance().encriptar(tokenDesencriptado,
				Config.getAppProperty(Constants.MZZO_KEY_II.getStringValue()));
	}

	private void generaCookie(HttpServletRequest in) throws Exception {
		String datos = in.getParameter("msisdn") + "," + in.getParameter("rut") + "," + in.getParameter("pin");
		// temporalmente se setea en duro ",0", se debe llevar a properties
		Cookie div = new Cookie("esaCookie", encriptaCookie(datos + ",0"));
		div.setMaxAge(60 * 60 * 24 * Integer.parseInt(Config.getAppProperty(Constants.COOKIE_TIME.getStringValue())));
		servletResponse.addCookie(div);
	}

	@Override
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	public Map<String, Object> getSession() {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		return attibutes;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	@Override
	public LoginVO getModel() {
		return cliente;
	}

	public boolean isCheckMe() {
		return checkMe;
	}

	public void setCheckMe(boolean checkMe) {
		this.checkMe = checkMe;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
}
