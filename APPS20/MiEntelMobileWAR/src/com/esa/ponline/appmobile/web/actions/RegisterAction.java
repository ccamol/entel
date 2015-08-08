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
import org.apache.struts2.interceptor.SessionAware;

import com.epcs.cliente.perfil.types.ConsultaMercadoType;
import com.epcs.cliente.perfil.types.ObtenerRutTitularType;
import com.epcs.cliente.perfil.types.RespuestaType;
import com.epcs.cliente.perfil.types.ResultadoConsultaMercadoType;
import com.epcs.cliente.perfil.types.ResultadoObtenerRutTitularType;
import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.util.ArcFour;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.util.DefineTemplate;
import com.esa.ponline.appmobile.util.EmailValidator;
import com.esa.ponline.appmobile.util.Formato;
import com.esa.ponline.appmobile.util.Rut;
import com.esa.ponline.appmobile.vo.RutVO;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.web.delegate.DelegateRegister;
import com.esa.ponline.appmobile.web.helper.ActionHelper;
import com.esa.ponline.appmobile.ws.EntelServices;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class RegisterAction extends ActionSupport implements SessionAware,
		ServletResponseAware {

	private static final long serialVersionUID = -6376465858684538408L;

	private static final Logger LOGGER = Logger.getLogger(RegisterAction.class);

	private DelegateRegister delegateRegistro = new DelegateRegister();
	
	private String signLogin = new String();

	private DefineTemplate dt = new DefineTemplate();

	private HttpServletResponse servletResponse;

	private String msgErrorReg;

	private String msgErrorRegTwo;

	private String msgErrorRegThree;

	private LoginVO registroCliente;

	private SessionMap<String, Object> session;

	private String pin;

	private String msisdn;

	@Override
	public String execute() throws Exception {

	    LOGGER.info("Inicia peticion de registro");
		// fortest
		// return "success_step_three";
		// fi forTest

		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			String action = req.getParameter("method:execute");
			dt.setValueAppTemplate(req);

			LoginVO userSigninLogin = (LoginVO) getSession().get(
					Constants.CLIENTE_REGISTRO_SIGNINLOGIN.getStringValue());

			if (userSigninLogin != null) {
				LOGGER.info("Se redirige al usuario al paso 3 del registro");
				session.put("signLogin", "OK");
				setSignLogin(Constants.OK.getStringValue());
				return ingresarRutClave(req, userSigninLogin);
			}

			if ("sms".equalsIgnoreCase(action)) {
				if (Formato.esNumerico(req.getParameter("msisdn"))
						&& req.getParameter("msisdn").length() == 8) {
					RespuestaType mercadoResponse = obtieneMercadoMovil(req
							.getParameter("msisdn"));
					if (mercadoResponse.getCodigo().equals("0000")) {
						// se cambia para prueba
						if (!existeRegistro(req.getParameter("msisdn"))) {
							return enviarSMS(req);
						} else {
							setMsgErrorReg("N\u00famero ya se encuentra registrado");
							LOGGER.info("Usuario es redirigido al paso 1 del registro");
							return dt.defineTemplateRegistro(req) + "one";
						}
					} else {
						setMsgErrorReg("Ingresa un n\u00famero Entel v\u00e1lido");
						return dt.defineTemplateRegistro(req) + "one";
					}
				} else {
					setMsgErrorReg("Debes ingresar un n\u00famero v\u00e1lido");
					return dt.defineTemplateRegistro(req) + "one";
				}
			}

			if ("rutClave".equalsIgnoreCase(action)) {
				ActionContext.getContext().getSession().remove("signLogin");
				// forTest
				// ingresarRutClave(req);
				// fi forTest

				if (isValidStepTwo(req)) {
					return ingresarRutClave(req, null);
				} else {
				    LOGGER.info("Usuario es redirigido al paso 2 del registro");
					return dt.defineTemplateRegistro(req) + "two";
				}
			} else if ("registraMail".equalsIgnoreCase(action)) {
				if (isValidStepThree(req)) {
				    LOGGER.info("Finaliza peticion de registro");
					return registrarUsuario(req);
				} else {
					String sLogin = (String) getSession().get("signLogin");
					if (sLogin!=null) {
						setSignLogin(sLogin);
					} else {
						setSignLogin("");
					}
					LOGGER.info("Usuario es redirigido al paso 3 del registro");
					return dt.defineTemplateRegistro(req) + "three";
				}
			}

			// TODO Return para prueba setp two
			// return "success_step_two";

			// TODO Return para prueba setp two para APP
			// return "app_success_step_medium_two";

			// TODO Return para prueba setp three
			// return "success_step_medium_three";

			// Return que aplica
			LOGGER.info("Usuario es redirigido al paso 1 del registro");
			return dt.defineTemplateRegistro(req) + "one";
		} catch (Exception e) {
			LOGGER.error("Ha ocurrido un problema en RegisterAction "
					+ e.getMessage());
			LOGGER.error(e);
			e.printStackTrace();
			return "error_general";
		}
	}

	private boolean isValidStepThree(HttpServletRequest req) {
		boolean isValid = true;
		EmailValidator validaMail = new EmailValidator();

		try {
			if (!validaMail.validate(req.getParameter("email"))) {
				isValid = false;
			}
		} catch (Exception e) {
			LOGGER.error("Problema en validacion de campos: " + e.getMessage());
			e.printStackTrace();
		}

		if (!isValid) {
			setMsgErrorRegThree("Ingresa un email v\u00e1lido");
		}

		return isValid;
	}

	private RespuestaType obtieneMercadoMovil(String msisdn) {
		ConsultaMercadoType consultaMercado = new ConsultaMercadoType();
		consultaMercado.setMsisdn(msisdn);

		ResultadoConsultaMercadoType resMercado = null;
		try {
			resMercado = EntelServices.getProfileInstanceService()
					.getClientePerfilServicePort()
					.consultaMercado(consultaMercado);
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			LOGGER.error(error);
			error.printStackTrace();
		} catch (Exception e) {
            LOGGER.error("Excepcion lanzada debido a: " + e.getMessage());
            LOGGER.error(e);
            e.printStackTrace();
        }

		return resMercado.getRespuesta();
	}

	private boolean existeRegistro(String msisdn) {
		// forTest
		// return false;
		// fi forTest

		try {
			if (delegateRegistro.existeRegistro(msisdn)) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("No es posible validar registro" + e.getMessage());
            LOGGER.error(e);			
			e.printStackTrace();
		}
		return false;
	}

	private String ingresarRutClave(HttpServletRequest req, LoginVO in) {
		registroCliente = (LoginVO) getSession().get("cliente_registro");
		String msisdn = null;

		// for test
		// registroCliente = new LoginVO();
		// registroCliente.setMsisdn("81560126");
		// return dt.defineTemplateRegistro(req) + "three";
		// fi for test

		if (in != null) {
			msisdn = in.getMsisdn();
			registroCliente = new LoginVO();
			registroCliente.setMsisdn(msisdn);
			registroCliente.setRut(Rut.formatTrim(in.getRut()));
			this.pin = (String) getSession().get("pinSiginAction");
		} else {
			msisdn = registroCliente.getMsisdn();
			registroCliente.setRut(Rut.formatTrim(req.getParameter("rut")));
			this.pin = req.getParameter("clave");
		}

		ObtenerRutTitularType obtenerDatosRut = new ObtenerRutTitularType();
		obtenerDatosRut.setMsisdn(msisdn);

		registroCliente.setMsisdn(msisdn);

		ConsultaMercadoType consultaMercado = new ConsultaMercadoType();
		consultaMercado.setMsisdn(registroCliente.getMsisdn());

		ResultadoConsultaMercadoType resMercado = null;
		try {
			resMercado = EntelServices.getProfileInstanceService()
					.getClientePerfilServicePort()
					.consultaMercado(consultaMercado);
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
            LOGGER.error(error);			
			error.printStackTrace();
		} catch (Exception e) {
            LOGGER.error("Excepcion lanzada debido a: " + e.getMessage());
            LOGGER.error(e);            
            e.printStackTrace();
        }

		ResultadoObtenerRutTitularType resDatosRut = null;
		try {
			resDatosRut = EntelServices.getProfileInstanceService()
					.getClientePerfilServicePort()
					.obtenerRutTitular(obtenerDatosRut);
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			LOGGER.error(error);
			error.printStackTrace();
		} catch (Exception e) {
            LOGGER.error("Excepcion lanzada debido a: " + e.getMessage());
            LOGGER.error(e);            
            e.printStackTrace();
        }

		if (resDatosRut.getRespuesta().getCodigo().equals("0000")) {
			registroCliente.setNombreTitular(resDatosRut
					.getNombreCompletoTitular());
			registroCliente.setNombre("");
		} else {
			registroCliente.setNombre("");
			LOGGER.info("Respuesta del servicio con codigo " + "["
					+ resDatosRut.getRespuesta().getCodigo() + "]");
			LOGGER.info("Respuesta del servicio con descripcion " + "["
					+ resDatosRut.getRespuesta().getDescripcion() + "]");
		}

		registroCliente.setMercado(resMercado.getMercado());
		registroCliente.setIsWeb(isWebTemplate());
		String userAgent = req.getHeader("User-agent");
		registroCliente.setResultNav(dt.defineTemplateNav(registroCliente,
				userAgent));
		session.put("pin", pin);
		session.put(Constants.CLIENTE_REGISTRO.getStringValue(), registroCliente);

		LOGGER.info("Se remueve la sesion "
				+ Constants.CLIENTE_REGISTRO_SIGNINLOGIN.getStringValue());
		ActionContext
				.getContext()
				.getSession()
				.remove(Constants.CLIENTE_REGISTRO_SIGNINLOGIN.getStringValue());

		return dt.defineTemplateRegistro(req) + "three";
	}

	private String registrarUsuario(HttpServletRequest req) {
		registroCliente = (LoginVO) getSession().get("cliente_registro");
		String email = req.getParameter("email");
		try {
			if(email != null){
				registroCliente.setEmail(email);
			}else{
				registroCliente.setEmail("");
				LOGGER.info("Email en registro contiene " + email);
			}
		} catch (Exception e) {
			LOGGER.error("No se ha podido leer parametro email ingresado por el usuario");
			LOGGER.error("Email en registro contiene " + email);
		}
		ActionHelper userSession = new ActionHelper();
		
//		String passwd = (String) getSession().get("pin");
//		String key = (registroCliente.getMsisdn() + "," + registroCliente.getRut() + "," + passwd);

		try {
			// forTest
			// String resultadoRegistro = "success";
			// fi forTest

			// linea que aplica
			String resultadoRegistro = delegateRegistro
					.registrarUsuario(registroCliente);
			
			//linea forTest
			//resultadoRegistro = "success";
			//
			if (resultadoRegistro.equalsIgnoreCase("success")) {

				userSession.creaSession(registroCliente, req, isWebTemplate());
				ActionContext.getContext().getSession().remove("pin");
				ActionContext.getContext().getSession().remove("signLogin");
				ActionContext.getContext().getSession().remove("cliente_registro");

				if (!isWebTemplate()) {
					generaCookie(registroCliente);
				}

				// TODO: Esta logica hay que revisarla, podria cambiar y esta en
				// duro
				if (registroCliente.getMercado().equalsIgnoreCase("ss")) {
					return "trafico";
				} else {
					return "menu";
				}

			} else {
			    //Caso especial, informar  0008 u 0009
			    if (resultadoRegistro.equalsIgnoreCase("0008") || resultadoRegistro.equalsIgnoreCase("0009")){
				userSession.creaSession(registroCliente, req, isWebTemplate());
				ActionContext.getContext().getSession().remove("pin");
				ActionContext.getContext().getSession().remove("signLogin");
				ActionContext.getContext().getSession().remove("cliente_registro");

				if (!isWebTemplate()) {
					generaCookie(registroCliente);
				}
							
				if (resultadoRegistro.equalsIgnoreCase("0008")){
				Config.cacheRestriccionZona.put(registroCliente.getMsisdn(), true);
				LOGGER.info ("Se carga marca de restriccion puntos zona a msisdn: " + registroCliente.getMsisdn());				
				}else{
				    LOGGER.info ("Registro devuelve codigo 0009");
				}
				
				if (registroCliente.getMercado().equalsIgnoreCase("ss")) {
					return "trafico";
				} else {
					return "menu";
				}
			    }else{
				//Error
				session.remove("cliente_registro");
				LOGGER.error("No es posible registrar usuario, servicio responde codigo diferente a 0000: " + resultadoRegistro);
				return "error_general";
			}
			}
		} catch (Exception e) {
			LOGGER.error("No es posible registrar usuario " + e.getMessage());
			LOGGER.error(e);
			e.printStackTrace();
		}
		return "login";
	}

	private void generaCookie(LoginVO user) throws Exception {
		LOGGER.info("Se genera cookie para registro del msisdn: ["
				+ user.getMsisdn() + "], realizado a traves de "
				+ (user.IsWeb() ? "WEB" : "APP"));
		String passwd = (String) getSession().get("pin");
		String datos = user.getMsisdn() + "," + user.getRut() + "," + passwd;
		Cookie div = new Cookie("esaCookie", encriptaCookie(datos
				+ (user.IsWeb() ? ",0" : ",1")));
		div.setMaxAge(60 * 60 * 24 * Integer.parseInt(Config
				.getAppProperty(Constants.COOKIE_TIME.getStringValue())));
		servletResponse.addCookie(div);
	}

	private String encriptaCookie(String tokenDesencriptado) {
		return ArcFour.getInstance().encriptar(tokenDesencriptado,
				Config.getAppProperty(Constants.MZZO_KEY_II.getStringValue()));
	}

	@Override
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	private boolean isValidStepTwo(HttpServletRequest req) {
		String clave = req.getParameter("clave");
		registroCliente = (LoginVO) getSession().get("cliente_registro");

		// for test
		// registroCliente = new LoginVO();
		// registroCliente.setMsisdn("81560126");
		// end for test
		String userRut = Rut.formatTrim(req.getParameter("rut"));

		RutVO.parseRut(userRut);

		if (!RutVO.validate() || !RutVO.parseRut(userRut).isRutValido()) {
			setMsgErrorRegTwo("Ingresa la informaci\u00f3n requerida");
			return false;
		}
		try {
			if (!(Formato.esNumerico(req.getParameter("clave")) && req
					.getParameter("clave").length() == 4)) {
				setMsgErrorRegTwo("Alguno de los datos ingresados es incorrecto");
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("Error en la validacion de los datos para el registro en el segundo paso "
					+ e.getMessage());
			LOGGER.error(e);
			e.printStackTrace();
		}
		try {
			if (delegateRegistro.validaDatosRegistro(
					registroCliente.getMsisdn(), clave)) {
				return true;
			} else {
				setMsgErrorRegTwo("Alguno de los datos ingresados es incorrecto");
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("Error en la validacion de los datos para el registro en el segundo paso "
					+ e.getMessage());
			LOGGER.error(e);
			e.printStackTrace();
		}
		setMsgErrorRegTwo("Servicio no disponible");
		return false;
	}

	private String enviarSMS(HttpServletRequest req) {
		registroCliente = new LoginVO();
		String msisdn = req.getParameter("msisdn");
		String respuesta = null;

		// es el que aplica
		try {
			respuesta = delegateRegistro.enviarSMS(msisdn);
		} catch (Exception e1) {
			LOGGER.error("No es posible obtener la respuesta al solicitar enviar clave sms "
					+ e1.getMessage());
			LOGGER.error(e1);
			e1.printStackTrace();
		}

		// forTest
		// no mostrara mensaje si el numero pertenece a otra compañia
		// respuesta = "0000";
		// fi forTest

		try {
			if (respuesta.equals("0000")) {

				registroCliente.setMsisdn(req.getParameter("msisdn"));
				session.put(Constants.CLIENTE_REGISTRO.getStringValue(),
						registroCliente);
				String template = dt.defineTemplateRegistro(req) + "two";
				LOGGER.info(template);
				LOGGER.info("Envio de clave exitoso para MSISDN [" + msisdn
						+ "]");
				return template;
			} else if (respuesta.equals("0002")) {
				setMsgErrorReg("Has superado el l\u00edmite de solicitudes de clave mensual");
				LOGGER.warn("MSISDN [" + msisdn
						+ "] ha superado la solicitud de clave");
				return dt.defineTemplateRegistro(req) + "one";
			} else if (respuesta.equals("0001")) {
				setMsgErrorReg("N\u00famero no pertenece a entel");
				LOGGER.warn("MSISDN ingresado no pertenece a entel [" + msisdn
						+ "]");
				return dt.defineTemplateRegistro(req) + "one";
			}
		} catch (Exception e) {
			LOGGER.error("No es posible enviar sms para msisdn [" + msisdn
					+ "]" + e.getMessage());
			LOGGER.error(e);
			e.printStackTrace();
		}
		return Constants.ERROR_GRAL.getStringValue();
	}

	public void validate() {
		if (getMsisdn() != null) {
			if (getMsisdn().length() == 0) {
				addFieldError("msisdn", "User Name is required");
			}
		}
	}

	private boolean isWebTemplate() {
	    LOGGER.info("Validando si es template web o apps");
		String appValue = (String) getSession().get("app_template");
		if (appValue == null) {
			appValue = "";
		}
		if (appValue.equals("1")) {
			return false;
		}
		return true;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		session = (SessionMap<String, Object>) map;
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

	public String getMsgErrorReg() {
		return msgErrorReg;
	}

	public void setMsgErrorReg(String msgErrorReg) {
		this.msgErrorReg = msgErrorReg;
	}

	public String getMsgErrorRegTwo() {
		return msgErrorRegTwo;
	}

	public void setMsgErrorRegTwo(String msgErrorRegTwo) {
		this.msgErrorRegTwo = msgErrorRegTwo;
	}

	public String getMsgErrorRegThree() {
		return msgErrorRegThree;
	}

	public void setMsgErrorRegThree(String msgErrorRegThree) {
		this.msgErrorRegThree = msgErrorRegThree;
	}

	public String getSignLogin() {
		return signLogin;
	}

	public void setSignLogin(String signLogin) {
		this.signLogin = signLogin;
	}

}
