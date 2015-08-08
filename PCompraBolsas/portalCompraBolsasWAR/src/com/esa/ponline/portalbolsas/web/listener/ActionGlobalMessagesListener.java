package com.esa.ponline.portalbolsas.web.listener;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public final class ActionGlobalMessagesListener implements
		ServletContextListener {
	
	private static final String DEFAULT_RESOURCE = "globalMessages";
	private static final String RESOURCE_FILE = "./aplsEPCS/planesMMAutogestion/config/";
	
//    private static final Logger LOGGER = Logger.getLogger(ActionGlobalMessagesListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//	    LOGGER.info("cargando archivos en la raiz");
	    
		URL[] urls;
		try {
			File file = new File(RESOURCE_FILE);
			URL url = file.toURI().toURL();
			urls = new URL[] { url };

			ClassLoader cl = new URLClassLoader(urls);
			LocalizedTextUtil.setDelegatedClassLoader(cl);
			LocalizedTextUtil.addDefaultResourceBundle(DEFAULT_RESOURCE);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

//	@Override
//	public void destroy() {
//	}
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//            FilterChain chain) throws IOException, ServletException {
//    	
////        HttpSession session = request.getRemoteHost();
////        Movil movilCliente = (Movil) session.getAttribute("movil");
//
//        try {
//            /*
//             * This code puts the value "userName" to the Mapped Diagnostic
//             * context. Since MDc is a static class, we can directly access it
//             * with out creating a new object from it. Here, instead of hard
//             * coding the user name, the value can be retrieved from a HTTP
//             * Request object.
//             */
//        	
//            MDC.put("idSession", request);
//
//            chain.doFilter(request, response);
//
//        } finally {
//            MDC.remove("idSession");
//        }
//
//    }
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		
//	}

}