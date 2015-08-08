<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences"	prefix="preferences"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="es"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<%@page import="java.util.List"%>
<html>
<script type="text/javascript" src="js/jquery-1.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/portlet/equipo/js/jquery.i2b.selectBox.js"></script>
<link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/portlet/equipo/css/style.css"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<% 
String region = request.getParameter("idRegion");

String queryStr = "numRegion = "+region+"";
System.out.println("Query comunas : "+queryStr); 
%>
<cm:search id="comunas" query="<%=queryStr%>" useCache="true"/>
</head>
<body>	

<% int cantCom = 0;
	cantCom = comunas.length;
	int cont = 0;
%>
<data>
<c:set var="cantCom" scope="session" value="<%=cantCom%>"/>
<c:if test="${cantCom ==0 }">
empty
</c:if>
<c:if test="${cantCom >0 }">
<c:forEach var="i" begin="0" end="<%=cantCom-1%>">
	<cm:getProperty node="${comunas[i]}" name="nombreComuna" />?
</c:forEach>
</c:if>
</data>									
</body>
</html>