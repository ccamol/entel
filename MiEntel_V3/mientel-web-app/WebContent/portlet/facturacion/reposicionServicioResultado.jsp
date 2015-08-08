<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<f:view>
<cm:search id="nodo" query="idContenido = 'msj_duracion_reposicion'" useCache="true"  />
<h1>Reposici&oacute;n de servicio</h1>
	<p><cm:getProperty node="${nodo[0]}" name="html" /></p>
	<br/>
	
	<!-- MENSAJES -->
	<jsp:include page="../common/messages_table.jsp"></jsp:include>

</f:view>
