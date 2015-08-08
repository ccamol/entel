<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="pref" %>
<pref:getPreference name="mercado" var="mercado" defaultValue="" />
<c:choose>
	<c:when test="${mercado == 'suscripcion'}">
		<c:set var="query">idContenido = 'mensajes_ss'</c:set>
	</c:when>
	<c:when test="${mercado == 'cuenta controlada'}">
		<c:set var="query">idContenido = 'mensajes_cc'</c:set>
	</c:when>
	<c:when test="${mercado == 'prepago'}">
		<c:set var="query">idContenido = 'mensajes_pp'</c:set>
	</c:when>
	<c:otherwise><c:set var="query">idContenido = 'mensajes_dash_default'</c:set></c:otherwise>
</c:choose>
<cm:getProperty node="${nodo[0]}" name="html" />
	<div class="db-tabla">
		<div class="db-tabla-cabecera">
			<div class="db-cabecera-top"></div>
			<div class="db-cabecera-cuerpo">
				<div class="db-titulo db-titulo-mensaje">Mensajes para ti</div>
			</div>
		</div>
		<div class="db-tabla-cuerpo db-tabla-fix-mensaje">
			<cm:search id="nodo" query="${query}" useCache="false" />
			<cm:getProperty node="${nodo[0]}" name="html" />
		</div>
		<div class="db-tabla-pie"></div>
	</div>
