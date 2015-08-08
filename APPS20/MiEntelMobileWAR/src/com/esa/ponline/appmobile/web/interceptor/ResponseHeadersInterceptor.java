/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.interceptor;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.esa.ponline.appmobile.util.Formato;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author esuarez (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class ResponseHeadersInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
    private static final Logger LOGGER = Logger
	    .getLogger(ResponseHeadersInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
	HttpServletResponse response = (HttpServletResponse) invocation
		.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);

	HttpServletRequest request = (HttpServletRequest) invocation
		.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);

	// Actual Action called
	String result = invocation.invoke();

	if (!response.isCommitted()) {

		CharResponseWrapper wrapper = new CharResponseWrapper(response);

		ServletActionContext.setResponse(wrapper);

		// render HTML to client
		String htmlReturned = wrapper.toString();

		// LOGGER.info(request.getHeaders("ETag").toString());

//TODO: Hay que corriger esto
	    String accionRetornada = invocation.getAction().toString();
        if (htmlReturned.length()>0) {
		if (accionRetornada.contains("LogoutAction")
			|| accionRetornada.contains("LoginAction")
			|| accionRetornada.contains("BundleAction")
			|| accionRetornada.contains("TrafficAction")
			|| accionRetornada.contains("MenuAction")
			|| accionRetornada.contains("AccountAction")
			|| accionRetornada.contains("PlanAction")
			|| accionRetornada.contains("SigninAction")
					// TODO eliminar cache registro
			|| accionRetornada.contains("RegisterAction")) {
		    //LOGGER.debug(accionRetornada + " no se le asigna Etag");
		    response.setHeader("Cache-Control",
			    "no-cache, no-store, must-revalidate"); // HTTP 1.1.
				response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				response.setDateHeader("Expires", 0); // Proxies.
				response.setHeader("ETag", null);

			} else {
		    //LOGGER.debug(accionRetornada + " se le asigna Etag");
		    Date future = new Date(
			    ((new Date()).getTime() + 1000 * 10 * 60l));
				response.addDateHeader("Expires", future.getTime());
		    response.setHeader("Cache-Control", "max-age=" + 10 * 60
			    + "");
				response.addHeader("cache-Control", "public");
				response.setHeader("ETag", Formato.getMD5(htmlReturned));
			}
		}

		// LOGGER.info(request.getHeaders("ETag").toString());

		// Al tomar el control debemos nosotros renderear el HTML

		PrintWriter out = response.getWriter();
		out.write(htmlReturned);
		out.flush();
	}

		return result;
	}

}