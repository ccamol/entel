<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="preferences" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="com.bea.netuix.servlets.controls.page.PagePresentationContext"%>
<%
    PagePresentationContext context = PagePresentationContext
            .getPagePresentationContext((HttpServletRequest) pageContext.getRequest());
%>

<f:view>

	<preferences:getPreference name="mientel.redirect.url" var="url" />
	<preferences:getPreference name="mientel.redirect.url.target" var="url_target" defaultValue="_self" />
	<preferences:getPreference name="mientel.redirect.title" var="title" />
	

	<script type="text/javascript">
		window.location="<c:out value="${url}"/>";	
	</script>



</f:view>
