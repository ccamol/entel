<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ page import="com.epcs.aplicaciones.util.XSSFilter" %>
<% 
	XSSFilter token = new XSSFilter();
	String formToken = token.getToken(request.getSession()); 
	request.setAttribute("CSRFToken", formToken);%>
	
		<input type="hidden" value='<%=formToken%>' name="CSRFToken">
	
	