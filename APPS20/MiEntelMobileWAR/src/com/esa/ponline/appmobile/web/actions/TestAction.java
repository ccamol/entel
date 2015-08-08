/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.actions;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class TestAction extends ActionSupport {

    private static final long serialVersionUID = -3221417135681885623L;

    private static final Logger LOGGER = Logger.getLogger(TestAction.class);

    private static List<String> userData = new ArrayList<String>();

    public String execute() {
	userData.clear();
	
	HttpServletRequest request = ServletActionContext.getRequest();

	Enumeration<String> headersNames = request.getHeaderNames();
	while (headersNames.hasMoreElements()) {
	    String current = headersNames.nextElement();
	    LOGGER.debug("Header " + current + " => "
		    + request.getHeader(current));
	    userData.add("Header " + current + " => "
		    + request.getHeader(current));
	}

	String method = request.getMethod();
	Map<String, Object> parameters = ActionContext.getContext()
		.getParameters();

	for (Map.Entry<String, Object> entry : parameters.entrySet()) {
	    Object obj = entry.getValue();
	    String[] strArray = (String[]) obj;
	    String strValue = new String();
	    if (strArray != null) {
		strValue = strArray[0];
	    }
	    LOGGER.debug(method + " " + entry.getKey() + " => " + strValue);
	    userData.add(method + " " + entry.getKey() + " => " + strValue);
	}

	return SUCCESS;
    }

    public List<String> getUserData() {
	return this.userData;
    }

    public void setUserData(List<String> userData) {
	this.userData = userData;
    }

}
