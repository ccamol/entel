<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="preferences" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>
<%@ taglib prefix="render" uri="http://www.bea.com/servers/portal/tags/netuix/render" %>

<%@page import="com.bea.netuix.servlets.controls.page.PagePresentationContext"%>
<%
    PagePresentationContext context = PagePresentationContext
            .getPagePresentationContext((HttpServletRequest) pageContext.getRequest());
%>

<c:set var="contextTitle" value="<%=context.getTitle()%>"></c:set>

<f:view>

	<render:defineObjects/>
	<preferences:getPreference name="mientel.aaa.security.error" defaultValue="" var="idContenido" />

	<h1><c:out value="${contextTitle}" /></h1>

	<div class="clearfix" style="width:600px;" >
	    <div class="mensaje-alerta-sistema">
	        <div class="clearfix sub-contenedor">
	            <div class="contenedor-imagen">
	                <div class="imagen"></div>
	            </div>
	            <div class="texto">

					<c:if test="${empty idContenido}">
						<c:out value="${miEntelBean.accessDeniedDefaultMessage}" />
					</c:if>
					<c:if test="${!(empty idContenido)}">
						<cm:search id="mensaje" query="idContenido = '${idContenido}'" useCache="false" />
						<cm:getProperty node="${mensaje[0]}" name="html" />
					</c:if>

	            </div>
	        </div>
	    </div>
	</div>

</f:view>