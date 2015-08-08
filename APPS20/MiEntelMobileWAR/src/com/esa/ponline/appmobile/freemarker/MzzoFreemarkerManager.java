package com.esa.ponline.appmobile.freemarker;

import javax.servlet.ServletContext;

import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import org.apache.log4j.Logger;
import org.apache.struts2.views.freemarker.FreemarkerManager;

public class MzzoFreemarkerManager extends FreemarkerManager {
    private static final Logger LOGGER = Logger
	    .getLogger(MzzoFreemarkerManager.class);

    @Override
    public void init(ServletContext servletContext) throws TemplateException {
	super.init(servletContext);
	LOGGER.info("Iniciando configuraciones Mzzo Freemarker Manager");
	
	// custom ftl exception handling
	config
		.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

}