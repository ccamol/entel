/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;

import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class DefineTemplate {

	private static final Logger LOGGER = Logger.getLogger(DefineTemplate.class);

	public Boolean isMediumTemplate(String userAgent) throws AppMobileException {
		try {
			String[] movilABuscar = Config.getMovilProperty("medium").split(",");

			for (int i = 0; i < movilABuscar.length; i++) {
				if (userAgent.toLowerCase().indexOf(movilABuscar[i].toLowerCase().trim())>=0){
				    LOGGER.info("Validacion medium template: Coincidio con movil: " + movilABuscar[i] );
				    return true;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Problema en validacion medium template: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public Boolean isLowTemplate(String userAgent) throws AppMobileException {
		try {
			String[] movilABuscar = Config.getMovilProperty("low").split(",");

			int userAgentLargo = userAgent.length();

			for (int i = 0; i < movilABuscar.length; i++) {
				int movilABuscarLargo = movilABuscar[i].length();

				for (int j = 0; j <= (userAgentLargo - movilABuscarLargo); j++) {
					if (userAgent.toLowerCase().regionMatches(j, movilABuscar[i].toLowerCase(), 0, movilABuscarLargo)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Problema en validacion low template: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public String defineTemplateNav(LoginVO user, String userAgent) {
		//TODO: Raro
		userAgent= userAgent==null ? "" : userAgent;
		
		try {
			if (isMediumTemplate(userAgent)){
				LOGGER.info("Se define mapeo template para cliente en sesion con el formato: "+(user.IsWeb()?"web":"app")+"_"+user.getMercado().toLowerCase()+"_medium");
				return (user.IsWeb()?"web":"app")+"_"+user.getMercado().toLowerCase()+"_medium";
			}
		} catch (AppMobileException e) {
			LOGGER.error("Problema al definir template de navegacion "+e.getMessage());
			e.printStackTrace();
		}
		LOGGER.info("Se define mapeo template para cliente en sesion con el formato: "+(user.IsWeb()?"web":"app")+"_"+user.getMercado().toLowerCase()+"_high");
		return (user.IsWeb()?"web":"app")+"_"+user.getMercado().toLowerCase()+"_high";
	}

	public String defineTemplateInicio(HttpServletRequest in) {
		String template = in.getHeader("User-Agent");
		try {
			if (isMediumTemplate(template)){
				return "success_medium";
			}
		} catch (AppMobileException e) {
			LOGGER.error("Problema al definir template inicio " + e.getMessage());
			e.printStackTrace();
		}
		return "success";
	}
	
	public String defineTemplateMercado(LoginVO cliente, HttpServletRequest req, boolean isSession) {
		if(cliente.getResultNav().contains("ss")){
		    if(!isSession){
		        SessionMap<String, Object> session = new SessionMap<String, Object>(req);
		        session.put(Constants.CLIENTE.getStringValue(), cliente);
		        LOGGER.info("Se crea session para [" + cliente.getMsisdn() + "]");
		    }
			return "webmobile";
		}else{
		    if(!isSession){
		        SessionMap<String, Object> session = new SessionMap<String, Object>(req);
		        session.put(Constants.CLIENTE.getStringValue(), cliente);
		        LOGGER.info("Se crea session para [" + cliente.getMsisdn() + "]");
		    }
		    return "saldo"+cliente.getMercado().toUpperCase();
		    }
		}
	
	public void setValueAppTemplate(HttpServletRequest req){
		String appValue = req.getParameter("app");
		if(appValue==null){
			return;
		}else if(appValue.equals("1")){
			SessionMap<String, Object> session = new SessionMap<String, Object>(req);
			session.put(Constants.APP_TEMPLATE.getStringValue(), appValue);
		}
	}
	
	public String defineTemplateRegistro(HttpServletRequest req) {
	    LOGGER.info("Ingresa a definicion de template");
	    SessionMap<String, Object> session = new SessionMap<String, Object>(req);
		String template = req.getHeader("User-Agent");
		String resultado="success_step_";
		
		
	    //TODO por algun motivo al borrar cache o desintalar la APPS
		// no posee JSESSIONID identicas cuando se ejecuta aplicacion
//		String appValue = (String) getSession().get("app_template");
		
		String appValue = req.getParameter("app");
		
		
		if(appValue==null){
		    LOGGER.info("Valor app ingresa con valor nulo");
			appValue="";
			
			String appValueAux = (String) getSession().get("app_template");
//			if(getSession().get("app_template").equals("1")){
			if(appValueAux!=null && appValueAux.equals("1")){
			    appValue = (String) getSession().get("app_template");
			}
		}else{
		    LOGGER.info("Se establece sesion para appValue");
		    session.put(Constants.APP_TEMPLATE.getStringValue(), appValue);
		}
		
		try {
			if (isMediumTemplate(template)){
				resultado = resultado + "medium_";
			}
		} catch (AppMobileException e) {
			LOGGER.error("Problema al definir template para registro " + e.getMessage());
			e.printStackTrace();
		}
		
		if(appValue.equals("1")){
		    LOGGER.info("Se lee app = 1 para en peticion de la APPS");
			resultado =  "app_" + resultado;
		}
		
		return resultado;
	}

	public Map<String, Object> getSession() {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		return attibutes;
	}
}
