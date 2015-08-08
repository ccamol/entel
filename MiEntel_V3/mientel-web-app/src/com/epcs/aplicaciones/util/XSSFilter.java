package com.epcs.aplicaciones.util;

import java.io.IOException;
import java.text.Normalizer;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epcs.recursoti.configuracion.MiEntelProperties;
 
/**
 * Filters Http requests and removes malicious characters/strings
 * (i.e. XSS) from the Query String
 */
public class XSSFilter implements Filter {
	
	private static final Logger LOGGER = Logger
    .getLogger(XSSFilter.class);
    private HttpSession sesion = null;
    private HttpServletResponse httpResponse;
    
	class XSSRequestWrapper extends HttpServletRequestWrapper {
 
		private String replace = "CLEAN";
		private Map<String, String[]> sanitizedQueryString;
		
		
		
		public XSSRequestWrapper(HttpServletRequest request) {
			super(request);
		}
		
		//QueryString overrides
		
		@Override
		public String getParameter(String name) {
			String parameter = null;
			String[] vals = getParameterMap().get(name); 
			
			if (vals != null && vals.length > 0) {
				parameter = vals[0];
			}
			
			return parameter;
		}
 
		@Override
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);
		}
		
		@Override
		public Enumeration<String> getParameterNames() {
			return Collections.enumeration(getParameterMap().keySet());
		}
 
		@SuppressWarnings("unchecked")
		@Override
		public Map<String,String[]> getParameterMap() {
			if(sanitizedQueryString == null) {
				Map<String, String[]> res = new HashMap<String, String[]>();
				Map<String, String[]> originalQueryString = super.getParameterMap();
				if(originalQueryString!=null) {
					LOGGER.info("**************************");
					LOGGER.info("*INICIO LOGGER Filtro XSS*");
					LOGGER.info("**************************");
					LOGGER.info("URL : "+super.getRequestURL().toString()+"?"+(super.getQueryString()!= null ? super.getQueryString() : ""));
					LOGGER.info("CAMBIAR EL VALOR");
					for (String key : (Set<String>) originalQueryString.keySet()) {
						String[] rawVals = originalQueryString.get(key);
						String[] snzVals = new String[rawVals.length];
						for (int i=0; i < rawVals.length; i++) {
							snzVals[i] = stripXSS(rawVals[i]);
							LOGGER.info("PARAMETRO "+key+": " + rawVals[i] + " POR " + (snzVals[i].equalsIgnoreCase(replace) ? "BLANCO" : snzVals[i]));
							if(snzVals[i].contains("CLEAN")){
								LOGGER.error("ERROR, Redirigiendo a "+MiEntelProperties.getProperty("URL.error"));
								snzVals[i] = "";
								try {
									httpResponse.sendRedirect(MiEntelProperties.getProperty("URL.error"));
									super.getSession().invalidate();
									LOGGER.info("SESION FINALIZADA? = "+super.getSession().isNew());
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						res.put(stripXSS(key), snzVals);
					}
					LOGGER.info("***********************");
					LOGGER.info("*FIN LOGGER Filtro XSS*");
					LOGGER.info("***********************");
				}
				sanitizedQueryString = res;
			}
			return sanitizedQueryString;
		}
 
		//TODO: Implement support for headers and cookies (override getHeaders and getCookies)
		
		/**
		 * Removes all the potentially malicious characters from a string
		 * @param value the raw string
		 * @return the sanitized string
		 */
		private String stripXSS(String value) {
			String cleanValue = null;
			if (value != null) {
				cleanValue = Normalizer.normalize(value, Normalizer.Form.NFD);
 
				// Avoid null characters
				cleanValue = cleanValue.replaceAll("\0", replace);
				
				// Avoid anything between script tags
				Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll(replace);
		 
				// Avoid anything in a src='...' type of expression
				scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll(replace);
 
				scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll(replace);
				
				// Remove any lonesome </script> tag
				scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll(replace);
 
				// Remove any lonesome <script ...> tag
				scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll(replace);
 
				// Avoid eval(...) expressions
				scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll(replace);
				
				// Avoid expression(...) expressions
				scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll(replace);
				
				// Avoid javascript:... expressions
				scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll(replace);
				
				// Avoid vbscript:... expressions
				scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll(replace);
				
				// Avoid onload= expressions
				scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll(replace);
				
				// Avoid SQL Injection
				scriptPattern = Pattern.compile("[\\w]*((%27)|('))\\s*((%6F)|o|(%4F))((%72)|r|(%52))|[\\w]*((%27)|('))\\s*((%61)|a|(%41))((%6E)|n|(%4E))((%64)|d|(%44))|(((%3E)|>|(%3C)|<))|(((%3E)|>|(%3C)|<)+.*[://.=/(/);'\"&#-]+.*)|(.*[://.=/(/);'\"&#-]+.*((%3E)|>|(%3C)|<)+)|(((%3C)|<)((%69)|i|(%49))((%6D)|m|(%4D))((%67)|g|(%47))[^\\n]+((%3E)|>))", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				cleanValue = scriptPattern.matcher(cleanValue).replaceAll(replace);
			}
			return cleanValue;
		}
	}
 
	@Override
	public void destroy() {
		LOGGER.info("XSSPreventionFilter: destroy()");
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		LOGGER.info("DENTRO DEL FILTRO TRAZA");
		System.out.println("DENTRO DEL FILTRO TRAZA");
		httpResponse = (HttpServletResponse) response;
		XSSRequestWrapper wrapper = new XSSRequestWrapper((HttpServletRequest)request);
		this.sesion = ((HttpServletRequest) request).getSession();
		if(!CSRFTokenManager.isToken((HttpServletRequest) request)){
			httpResponse.sendRedirect(MiEntelProperties.getProperty("URL.error"));
			((HttpServletRequest) request).getSession().invalidate();
			LOGGER.info("SESION FINALIZADA? = "+((HttpServletRequest) request).getSession().isNew());
			System.out.println("ERROR");
		}else{
			LOGGER.info("Token Valido");
		}
		chain.doFilter(wrapper, response);
		
	}
 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("XSSPreventionFilter: init()");
	}
	public String getToken(HttpSession sesion){
		if(sesion != null){
			return CSRFTokenManager.getTokenForSession(sesion);
		}
		return "";
	}
}