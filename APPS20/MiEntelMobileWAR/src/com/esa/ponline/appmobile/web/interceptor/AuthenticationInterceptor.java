/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.util.ArcFour;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.vo.RutVO;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.web.delegate.DelegateLogin;
import com.esa.ponline.appmobile.web.helper.ActionHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author esuarez (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class AuthenticationInterceptor implements Interceptor {

	private static final long serialVersionUID = -5011962009065225959L;
	
	private static final Logger LOGGER = Logger.getLogger(AuthenticationInterceptor.class);

	@Override
	public void destroy() {
		// release resources here
	}

	@Override
	public void init() {
		// create resources here
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
	    	HttpServletRequest req = ServletActionContext.getRequest();
	    	SessionMap<String, Object> session = new SessionMap<String, Object>(req);
		
		LoginVO user = (LoginVO) session.get("cliente");

		// Si no hay sesion se ve la cookie si no tiene se va al login, si hay
		// sesion se continua...
		if (user == null) {
			// TODO: Hay que parametrizar esto, que pantallas no necesitan sesion
			if (actionInvocation.getAction().toString().contains("LoginAction")
					|| actionInvocation.getAction().toString().contains("SigninAction") 
					|| actionInvocation.getAction().toString().contains("PasswdAction")
					|| actionInvocation.getAction().toString().contains("RegisterAction")
					|| actionInvocation.getAction().toString().contains("TermAction")
					|| actionInvocation.getAction().toString().contains("TestAction"))
			{
				return actionInvocation.invoke();
			} else {
				//Revisemos si existe la cookie
				if(isCookie(ServletActionContext.getRequest())){
					return actionInvocation.invoke();
				}else{
					//No hay cookie no podemos reconstruir session
					return "redirectLogin";
				}
			}
		} else {
			return actionInvocation.invoke();
		}
	}
	
	private  Boolean isCookie(HttpServletRequest req) throws Exception {
		String cookie = null;
		String userData = null;
		LoginVO cliente = new LoginVO();
		if (req.getCookies() != null && req.getCookies().length > 0) {
			for (Cookie c : req.getCookies()) {
				if (c.getName().equals("esaCookie"))
					cookie = c.getValue();
			}
		}
		if(cookie!=null){
			userData = desencriptaCookie(cookie);			
			//TODO: Verificar que los datos sean coherentes.
			String[] dataCliente = userData.split(",");
			LOGGER.info("Se encontro cookie para movil " + dataCliente[0]);
			cliente.setMsisdn(dataCliente[0]);
			cliente.setRut(dataCliente[1]);

			if (dataCliente.length >= 3) {
				cliente.setIsWeb(true);
				if (dataCliente.length >= 4 && dataCliente[3] != null) {
					if (dataCliente[3].equals("1")) {
						cliente.setIsWeb(false);
					}
				}
			}
		} else {
			return false;
		}
		
		if (loginValido(userData)) {
			ActionHelper session = new ActionHelper();
			
			// setiar en cookie valor de app
			session.creaSession(cliente, req, cliente.IsWeb());
			return true;
		}else{
			return false;
		}
	}
	
	private String desencriptaCookie(String token) {
		String tokenDesencriptado = "";
		tokenDesencriptado = ArcFour.getInstance().desencriptar(token.toUpperCase(),
				Config.getAppProperty(Constants.MZZO_KEY_II.getStringValue()));
		return tokenDesencriptado;
	}
	
	private Boolean loginValido(String key) {
		DelegateLogin delegate = new DelegateLogin();
		Boolean status = false;
		try {
			if (delegate.validaLogin(key)) {
				status = true;
			}
		} catch (Exception e) {
			LOGGER.error("No es posible validar el login " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public Map<String, Object> getSession() {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		return attibutes;
	}

}
