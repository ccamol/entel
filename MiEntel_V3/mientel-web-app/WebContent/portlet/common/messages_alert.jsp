<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 
	JSP common para mensajes de alerta/aviso
 -->
	<div class="contenedor-mensajes">
		<h:messages id="messages-list" layout="list" 
			errorClass="mensaje-alerta" 
		    showSummary="true" />
	</div>


