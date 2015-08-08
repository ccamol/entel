<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
	<div class="db-tabla">
	<cm:search id="nodo" query="idContenido = 'db_mensajes'" useCache="false"  />
		<div class="db-tabla-cabecera">
			<div class="db-cabecera-top"></div>
			<div class="db-cabecera-cuerpo">
				<div class="db-titulo db-titulo-mensaje"><cm:getProperty node="${nodo[0]}" name="titulo" /></div>
			</div>
		</div>
		<div class="db-tabla-cuerpo db-tabla-fix-mensaje">
			
			<cm:getProperty node="${nodo[0]}" name="html" />
		</div>
		<div class="db-tabla-pie"></div>
	</div>
