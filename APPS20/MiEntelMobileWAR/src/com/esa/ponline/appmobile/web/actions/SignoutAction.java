/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class SignoutAction extends ActionSupport implements ServletResponseAware {

	private static final long serialVersionUID = -524257514124413768L;

	private static final Logger LOGGER = Logger.getLogger(SignoutAction.class);

	protected HttpServletResponse servletResponse;

	private Map<String, String> maps = new HashMap<String, String>();

	@Override
	public String execute() {
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		try {
			if(isCookie(req)){
				killCookie();
			}
		} catch (Exception e) {
			LOGGER.error("Error en signout action: "+e.getMessage());
			e.printStackTrace();
			maps.clear();
			maps.put("estado", "1");
			
		} finally{		
			ActionContext.getContext().getSession().remove("cliente");
			
			maps.clear();
			maps.put("estado", "0");
		}
		return "success";
		} catch (Exception e) {
			LOGGER.error("Ha ocurrido un problema en SignoutAction " + e.getMessage());
			e.printStackTrace();
			return "error_general";
		}
	}
	
	//TODO: Codigo duplicado
	public Boolean isCookie(HttpServletRequest in) throws Exception {
		String cookie = null;
		
		if (in.getCookies()==null){
			return false;
		}
		
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
