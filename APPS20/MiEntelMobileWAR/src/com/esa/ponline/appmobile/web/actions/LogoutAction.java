/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.actions;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class LogoutAction extends ActionSupport implements ModelDriven<LoginVO>, ServletResponseAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4575840345448146878L;
	
	private static final Logger LOGGER = Logger.getLogger(LogoutAction.class);
	
	protected HttpServletResponse servletResponse;
	
	private LoginVO datosClientes;

	@Override
	public String execute() throws Exception {
		try {
			return logout();
		} catch (Exception e) {
			LOGGER.error("Ha ocurrido un problema en LogoutAction " + e.getMessage());
			e.printStackTrace();
			return "error_general";
		}
	}
	
	protected String logout() {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		try {
			if(isCookie(req)){
				killCookie();
			}
		} catch (Exception e) {
			LOGGER.error("Error en logout action: "+e.getMessage());
			e.printStackTrace();
		}
		LoginVO usuario = (LoginVO) getSession().get("cliente");
		
		if (usuario!=null){
			LOGGER.info("El usuario ["+usuario.getMsisdn()+"] ha deslogeado y finalizado su session");
		}else{
			LOGGER.info("El usuario ha deslogeado y finalizado su session");
		}
		
		ActionContext.getContext().getSession().remove("cliente");
		ActionContext.getContext().getSession().remove("cliente_registro");

		return "success";
	}
	
	@Override
	public LoginVO getModel() {
		return datosClientes;
	}
	
	public Boolean isCookie(HttpServletRequest in) throws Exception {
		String cookie = null;
		for (Cookie c : in.getCookies()) {
			if (c.getName().equals("esaCookie"))
				cookie = c.getValue();
		}
		if(cookie!=null){
			return true;
		}else{
			return false;
		}
	}
	
	private void killCookie() throws Exception {
		Cookie c = new Cookie("esaCookie", null);
		c.setMaxAge(0);
		servletResponse.addCookie(c);
	}

	@Override
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}
	
	private Map<String, Object> getSession() {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		return attibutes;
	}
	

}
