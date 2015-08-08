/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
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
import com.epcs.cliente.perfil.types.ConsultaMercadoType;
import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.util.ArcFour;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.vo.RutVO;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.web.delegate.DelegateLogin;
import com.esa.ponline.appmobile.web.delegate.DelegateRegister;
import com.esa.ponline.appmobile.web.helper.ActionHelper;
import com.esa.ponline.appmobile.ws.EntelServices;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class SigninAction extends ActionSupport implements ServletResponseAware {

	private static final long serialVersionUID = -524257514124413768L;

	private static final Logger LOGGER = Logger.getLogger(SigninAction.class);

	private DelegateRegister delegateRegistro = new DelegateRegister();

	private DelegateLogin delegate = new DelegateLogin();

	private Map<String, String> maps = new HashMap<String, String>();

	protected HttpServletResponse servletResponse;
	
	private SessionMap<String, Object> sessionKey;

	private String errorService;

	private LoginVO cliente;

	private String pin;

	@Override
	public String execute() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			procesaJson(req);
			return "success";
		} catch (Exception e) {
			LOGGER.error("Ha ocurrido un problema en SigninAction " + e.getMessage());
			LOGGER.error("Se devuelve estado JSON = 99");
			e.printStackTrace();
			maps.clear();
			maps.put("estado", "99");
			return "success";
		}
	}

	private void procesaJson(HttpServletRequest req) {
		BufferedReader reader = null;
		try {
			reader = req.getReader();
		} catch (IOException e) {
			LOGGER.error("Problema al procesar json " + e.getMessage());
			LOGGER.error("Se devuelve estado JSON = 99");
			e.printStackTrace();
			maps.clear();
			maps.put("estado", "99");
			return;
		}

		JsonParser parser = new JsonParser();
		JsonObject json;

		try {
			json = (JsonObject) parser.parse(reader);
		} catch (Exception e) {
			LOGGER.error("Problema al parsear json " + e.getMessage());
			LOGGER.error("Se devuelve estado JSON = 99");
			maps.clear();
			maps.put("estado", "99");
			return;
		}

		JsonElement jsonKey = json.get("key");
		String jsonKeyData = null;

		if (jsonKey != null) {
			jsonKeyData = jsonKey.getAsString();
			String jsonKeyDataDecrypt = desencriptaTokenKey(jsonKeyData);
			String jKeyArray[] = jsonKeyDataDecrypt.split(",");
			LOGGER.info("Largo de la key: " + jKeyArray.length);

			if (jKeyArray.length >= 3) {
				sessionKey = new SessionMap<String, Object>(req);
				sessionKey.put("jsonKey", jsonKeyData);
				if (jKeyArray.length >= 4) {
					LOGGER.info("######");
					LOGGER.info("## Parametro Adicional BOLSA_DE_GATO ##");
					LOGGER.info("## FORMATO (version_de_software_app | modelo_dispositivo | version_OS) ##");
					LOGGER.info("## " + jKeyArray[3] + " ##");
					LOGGER.info("######");
				} else {
					LOGGER.info("## No se encuentra parametro adicional BOLSA_DE_GATO ##");
				}
			}
		}

		LOGGER.info("valor jsonKey: [" + jsonKey + "]");
		JsonElement jsonLogin = json.get("login");
		LOGGER.info("valor jsonLogin: [" + jsonLogin + "]");

		ActionContext.getContext().getSession().remove("cliente_registro");

		if (jsonLogin == null) {
			String key = (String) getSession().get("jsonKey");
			
			if (key != null) {
			    key = desencriptaTokenKey(key);
			    LOGGER.info("Se rescata key desde variable de session [jsonKey]");
			} else {
		        key = desencriptaTokenKey(jsonKeyData);
		        LOGGER.info("Se rescata key desde json [jsonKeyData]");
			}
			
			String datos[] = key.split(",");
			LOGGER.info("MSISDN: " + datos[0]);

			String reponseValidaCompania = null;
			try {
				reponseValidaCompania = obtieneCompania(key);
			} catch (EntelServicesLocatorException e1) {
				LOGGER.error("Error en servicio");
				LOGGER.error(Config.getAppProperty("validaMsisdnServiceEndPoint") + " " + e1.getMessage());
				e1.printStackTrace();
			} catch (Exception e) {
				LOGGER.error("Error en servicio");
				LOGGER.error(Config.getAppProperty("validaMsisdnServiceEndPoint") + " " + e.getMessage());
				e.printStackTrace();
			}

			if (reponseValidaCompania == null) {
				LOGGER.error("Error en servicio");
				LOGGER.error(Config.getAppProperty("validaMsisdnServiceEndPoint"));
				LOGGER.error("Se devuelve estado JSON = 99");
				maps.clear();
				maps.put("estado", "99");
				return;
			}

			if (!reponseValidaCompania.equals("001")) {
				// No es entel
				LOGGER.info("Usuario no pertenece a Entel");
				LOGGER.info("Se devuelve estado JSON = 1");
				maps.clear();
				maps.put("estado", "1");
			} else {
				// nuevo flujo registro
				try {
					if (!existeRegistro(key)) {
						if (errorService != null) {
							if (errorService.equals("99")) {
								LOGGER.error("No es posible validar datos registro");
								LOGGER.error("Problema en Servicio: " + Config.getAppProperty("profileServiceEndPoint"));
								maps.clear();
								maps.put("estado", "99");
								return;
							}
						}
						try {
							if (delegateRegistro.validaDatosRegistro(datos[0], datos[2])) {
								LOGGER.info("MSISDN [" + datos[0] + "] no posee registro | RUT & PIN Validos.");
								LOGGER.info("Se redirige a registro");
								this.pin = datos[2];
								SessionMap<String, Object> session = new SessionMap<String, Object>(req);
								cliente = new LoginVO();
								cliente.setMsisdn(datos[0]);
								cliente.setRut(datos[1]);
								LOGGER.info("Seteo del parametro [app] con valor [1]");
								String appValue = "1";
								LOGGER.info("Numero no posee registro.");
								LOGGER.info("Se devuelve estado JSON = 2");
								maps.clear();
								maps.put("estado", "2");
								session.put(Constants.APP_TEMPLATE.getStringValue(), appValue);
								session.put("pinSiginAction", pin);
								session.put(Constants.CLIENTE_REGISTRO_SIGNINLOGIN.getStringValue(), cliente);
								return;
							}
						} catch (Exception e) {
							LOGGER.error("No es posible validar datos registro " + e.getMessage());
							maps.clear();
							maps.put("estado", "99");
							e.printStackTrace();
							return;
						}
						LOGGER.info("Usuario ha ingresado datos invalidos");
						LOGGER.info("Se devuelve estado JSON = 1");
						maps.clear();
						maps.put("estado", "1");
					} else {
						if (loginValido(key)) {
							LOGGER.info("Login valido APP");
							LOGGER.info("Se devuelve estado JSON = 0");
							maps.clear();
							maps.put("estado", "0");
							// parametro 1, indica que el login se realiza desde
							// app
							maps.put("token", encriptaDatos(datos[0] + "," + datos[1] + "," + datos[2] + "," + "1"));
						} else {
							LOGGER.info("Usuario a ingresado datos erroneos");
							LOGGER.info("Se devuelve estado JSON = 1");
							maps.clear();
							maps.put("estado", "1");
						}
					}
				} catch (AppMobileException e) {
					LOGGER.error("AppMobileException: " + e.getMessage());
					LOGGER.error("Se devuelve estado JSON = 99");
					maps.clear();
					maps.put("estado", "99");
					e.printStackTrace();
					return;
				}
			}
		} else {
			String userData = jsonLogin.getAsString();
			LOGGER.info("token login: " + userData);
			userData = desencriptaTokenLogin(userData);
			if (loginValido(userData)) {
				cliente = new LoginVO();
				String[] dataCliente = userData.split(",");

				LOGGER.info("MSISDN: " + dataCliente[0]);

				cliente.setMsisdn(dataCliente[0]);
				cliente.setRut(dataCliente[1]);
				ActionHelper session = new ActionHelper();
				session.creaSession(cliente, req, false);
				cliente = (LoginVO) getSession().get("cliente");
				encriptaDatos(userData);
				try {
					generaCookie(userData);
				} catch (Exception e) {
					LOGGER.error("Error en la generacion de cookie para app " + e.getMessage());
					e.printStackTrace();
				}
				LOGGER.info("Login valido APP");
				LOGGER.info("Se devuelve estado JSON = 0");
				maps.clear();
				maps.put("estado", "0");
				maps.put("mercado", cliente.getMercado().toUpperCase());
				maps.put("titular", cliente.getNombreTitular());
				maps.put("nombre", cliente.getNombre());
				maps.put("aaa", String.valueOf(cliente.getAaa()));				
			} else {
				LOGGER.info("Usuario a ingresado datos erroneos");
				LOGGER.info("Se devuelve estado JSON = 1");
				maps.clear();
				maps.put("estado", "1");
			}
		}
	}

	private boolean existeRegistro(String key) throws AppMobileException {
		String datos[] = key.split(",");
		// forTest
		// return false;
		// fi forTest

		try {
			if (delegateRegistro.existeRegistro(datos[0])) {
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

	private void generaCookie(String datos) throws Exception {
		Cookie div = new Cookie("esaCookie", encriptaCookie(datos));
		div.setMaxAge(60 * 60 * 24 * Integer.parseInt(Config.getAppProperty(Constants.COOKIE_TIME.getStringValue())));
		servletResponse.addCookie(div);
	}

	private String encriptaCookie(String tokenDesencriptado) {
		return ArcFour.getInstance().encriptar(tokenDesencriptado,
				Config.getAppProperty(Constants.MZZO_KEY_II.getStringValue()));
	}

	private String encriptaDatos(String userData) {
		return ArcFour.getInstance().encriptar(userData, Config.getAppProperty(Constants.MZZO_KEY_II.getStringValue()));
	}

	private String desencriptaTokenLogin(String token) {
		String tokenDesencriptado = "";
		tokenDesencriptado = ArcFour.getInstance().desencriptar(token.toUpperCase(),
				Config.getAppProperty(Constants.MZZO_KEY_II.getStringValue()));
		return tokenDesencriptado;
	}

	private String desencriptaTokenKey(String token) {
		String tokenDesencriptado = "";
		tokenDesencriptado = ArcFour.getInstance().desencriptar(token.toUpperCase(),
				Config.getAppProperty(Constants.MZZO_KEY_I.getStringValue()));

		return tokenDesencriptado;
	}

	private Boolean loginValido(String key) {
		Boolean status = false;
		try {
			if (delegate.validaLogin(key)) {
				status = true;
			}
		} catch (Exception e) {
			LOGGER.info("Problema al validar login | " + e.getMessage());
			e.printStackTrace();
		}

		return status;
	}

	public Map<String, Object> getSession() {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		return attibutes;
	}

	public Map<String, String> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, String> maps) {
		this.maps = maps;
	}

	@Override
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}
}
