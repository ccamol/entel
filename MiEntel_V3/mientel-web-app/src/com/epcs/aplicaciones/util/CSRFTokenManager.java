package com.epcs.aplicaciones.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * A manager for the CSRF token for a given session. The {@link #getTokenForSession(HttpSession)} should used to
 * obtain the token value for the current session (and this should be the only way to obtain the token value).
 * 
 * @author Eyal Lupu
 */
final class CSRFTokenManager {

	/**
	 * The token parameter name
	 */
	static final String CSRF_PARAM_NAME = "CSRFToken";

	/**
	 * The location on the session which stores the token
	 */
	private final static String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenManager.class.getName() + ".tokenval";

	static String getTokenForSession (HttpSession session) {
		String token = null;
		// I cannot allow more than one token on a session - in the case of two requests trying to
		// init the token concurrently
		synchronized (session) {
			token = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
			if (null==token) {
				token=UUID.randomUUID().toString();
				session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
			}
		}
		return token;
	}

	/**
	 * Extracts the token value from the session
	 * @param request
	 * @return
	 */
	static String getTokenFromRequest(HttpServletRequest request) {
		return request.getParameter(CSRF_PARAM_NAME);
	}
	
	static boolean isToken(HttpServletRequest request) {
		if(request.getMethod().equalsIgnoreCase("POST")){
			System.out.println("URL : "+request.getRequestURL().toString()+"?"+(request.getQueryString()!= null ? request.getQueryString() : ""));
			return getTokenForSession(request.getSession()).equalsIgnoreCase(getTokenFromRequest(request));
		}else{
			return true;
		}
		
	}

	private CSRFTokenManager() {};
}
