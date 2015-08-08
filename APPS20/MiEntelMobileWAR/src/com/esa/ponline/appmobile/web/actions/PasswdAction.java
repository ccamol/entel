/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.util.DefineTemplate;
import com.esa.ponline.appmobile.util.Formato;
import com.esa.ponline.appmobile.web.delegate.DelegateRegister;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class PasswdAction extends ActionSupport{

	private static final Logger LOGGER = Logger.getLogger(PasswdAction.class);

	private static final long serialVersionUID = -1221020396783350228L;

	private DefineTemplate dt = new DefineTemplate();
	
	private DelegateRegister delegateRegistro = new DelegateRegister();
	
	private String msgErrorPasswd;
	
	@Override
	public String execute() throws Exception {
	    LOGGER.info("Se inicia peticion de clave");
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		dt.setValueAppTemplate(req);
		String action = req.getParameter("method:execute");
		
		if ("solicitaClave".equalsIgnoreCase(action)) {
				if (Formato.esNumerico(req.getParameter("msisdn")) && req.getParameter("msisdn").length() == 8) {
				LOGGER.info("Finaliza peticion de clave");
				return enviarSMS(req);
			}else{
//				setMsgErrorPasswd(Config.getAppProperty(Constants.ERROR_PASSWD.getStringValue()));
				setMsgErrorPasswd("Ingresa un n\u00famero v\u00e1lido");
				LOGGER.info("Finaliza peticion de clave");
				return dt.defineTemplateRegistro(req)+"one";
			}
		}
		
		LOGGER.info("Finaliza peticion de clave");
		return dt.defineTemplateRegistro(req)+"one";
		} catch (Exception e) {
			LOGGER.error("Excepcion lanzada debido a: " + e.getMessage());
			LOGGER.error(e);
			e.printStackTrace();
			return "error_general";
		}
	}
	
	private String enviarSMS(HttpServletRequest req) {
		String msisdn = req.getParameter("msisdn");
		String respuesta = null;
		String retorno = null;
		
		// for test
//		LOGGER.info(dt.defineTemplateRegistro(req)+"two");
//		retorno = dt.defineTemplateRegistro(req)+"two";
		// end for test
		
		try {
			respuesta = delegateRegistro.enviarSMS(msisdn);
//			respuesta = "0000";
		} catch (Exception error) {
			LOGGER.error("No es posible obtener la respuesta al solicitar enviar clave sms " + error.getMessage());
			LOGGER.error(error);
			error.printStackTrace();
		}
		
		try {
			if (respuesta.equals("0000")) {
				LOGGER.info(dt.defineTemplateRegistro(req)+"two");
				LOGGER.info("Envio de clave exitoso para MSISDN ["+msisdn+"]");
				retorno = dt.defineTemplateRegistro(req)+"two";
			} else if(respuesta.equals("0002")){
				setMsgErrorPasswd("Has superado el l\u00edmite de solicitudes de clave mensual");
				LOGGER.info("MSISDN ["+msisdn+"] ha superado la solicitud de clave mensual");
				retorno = dt.defineTemplateRegistro(req)+"one";
			} else if(respuesta.equals("0001")){
				setMsgErrorPasswd("N\u00famero no pertenece a Entel");
				LOGGER.info("MSISDN ingresado no pertenece a entel ["+msisdn+"]");
				retorno = dt.defineTemplateRegistro(req)+"one";
			}
		return retorno;
		} catch (Exception e) {
			LOGGER.error("Problema al enviar sms para cliente ["+msisdn+"] "+e.getMessage());
			LOGGER.error(e);
			e.printStackTrace();
		}
		return Constants.ERROR_GRAL.getStringValue();
	}

	public String getMsgErrorPasswd() {
		return msgErrorPasswd;
	}

	public void setMsgErrorPasswd(String msgErrorPasswd) {
		this.msgErrorPasswd = msgErrorPasswd;
	}

	public Map<String, Object> getSession() {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		return attibutes;
	}

}
