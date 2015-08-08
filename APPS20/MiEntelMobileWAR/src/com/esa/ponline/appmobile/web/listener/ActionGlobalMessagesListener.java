package com.esa.ponline.appmobile.web.listener;

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
	private static final String RESOURCE_FILE = "./aplsEPCS/mientel/config/";

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
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

}