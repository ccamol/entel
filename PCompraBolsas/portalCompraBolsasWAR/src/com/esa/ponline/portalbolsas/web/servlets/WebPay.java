/**
 * 
 */
package com.esa.ponline.portalbolsas.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author ccastro
 *
 */

public class WebPay extends Init {

	private static final long serialVersionUID = 1L;
	
    private static final Logger LOGGER = Logger.getLogger(WebPay.class);

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    	if(idSessionLog(req, resp).equals("NOK")){
    		return;
    	} else {
        	idSessionLog(req, resp);
    	}
		LOGGER.info("doGet-WebPay");
		
		
		
		super.doGet(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    	if(idSessionLog(req, resp).equals("NOK")){
    		return;
    	} else {
        	idSessionLog(req, resp);
    	}
		LOGGER.info("doPost-WebPay");
		
		
		
		
		super.doPost(req, resp);
	}
}
