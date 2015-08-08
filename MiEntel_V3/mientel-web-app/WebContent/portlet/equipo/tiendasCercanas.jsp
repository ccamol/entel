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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/portlet/equipo/css/style.css"/>
<% 
	String region = request.getParameter("idRegion");
	String comuna = request.getParameter("idCiudad");
	String queryStr = "codRegion = "+region+" && comuna = '" +comuna+ "'";
%>
<cm:search id="tiendas" query="<%=queryStr%>" useCache="true" max="10" />


</head>
<body>	
<div class="Result_tabla" style="display:block;">
<% 
	int cant = 0;
	cant = tiendas.length;
	int cont = 0;
%>

	<table class="table_result" cellpadding="0" cellspacing="0">
	<% 
		if(cant > 0) {
	%>
            <tbody>
                <tr class="enca">
                    <th style="width:121px;">Tienda</th>
                    <th style="width:119px;">Direcci&oacute;n</th>
                    <th style="width:111px">Lunes a Viernes</th>
                    <th style="width:74px">S&aacute;bados</th>
                    <th style="width:128px">Domingos y Feriados</th>
                </tr>
            </tbody>
			<c:forEach var="i" begin="0" end="<%=cant-1%>">
	   			<% if(cont%2 != 0){%>
			<tr class="lista">
				<td class="N_tienda"><cm:getProperty node="${tiendas[i]}" name="nombreTienda" /></td>
				<td class="D_tienda"><cm:getProperty node="${tiendas[i]}" name="direccion" /></td>
				<td class="HLV_tienda"><cm:getProperty node="${tiendas[i]}" name="horarioLunesViernes" /></td>
				<td class="HS_tienda"><cm:getProperty node="${tiendas[i]}" name="horarioSabado" /></td>
				<td class="HDF_tienda"><cm:getProperty node="${tiendas[i]}" name="horarioDomingosFestivos" /></td>
			</tr>
		<%  }else {%>
				<tr class="lista impar_b1">
			<td class="N_tienda"><cm:getProperty node="${tiendas[i]}" name="nombreTienda" /></td>
			<td class="D_tienda"><cm:getProperty node="${tiendas[i]}" name="direccion" /></td>
			<td class="HLV_tienda"><cm:getProperty node="${tiendas[i]}" name="horarioLunesViernes" /></td>
			<td class="HS_tienda"><cm:getProperty node="${tiendas[i]}" name="horarioSabado" /></td>
			<td class="HDF_tienda"><cm:getProperty node="${tiendas[i]}" name="horarioDomingosFestivos" /></td>
		</tr>			
		<%} cont++;%>
	</c:forEach>
	<%		
		} else {
	%>
	<tr>
		<td>No existen tiendas en la regi&oacute;n y comuna señalada, por favor intenta con una comuna cercana.</td>
	</tr>
	<%		
		}
	%>
</table>
</div>
</body>
</html>


