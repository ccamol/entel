<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
<!--
	$(document).ready(function() {
		if($.browser.msie) {
			$("#contenedor-mensajes").hide(); 
			$("#contenedor-mensajes-ie").show();
		}
		else {
			$("#contenedor-mensajes-ie").hide(); 
			$("#contenedor-mensajes").show();
		}
				});	
//-->
</script>

<!-- 
	JSP common con el codigo para mensajes de error/exito 
 -->
<div id="contenedor-mensajes" class="contenedor-mensajes">
	<h:messages id="messages-list" layout="table" 
		errorClass="mensaje-error" 
		infoClass="mensaje-informacion" showSummary="true" />
</div>

<div id="contenedor-mensajes-ie" class="contenedor-mensajes-ie" style="display: none;">
	<h:messages id="messages-list-ie" layout="table" 
		errorClass="mensaje-error" 
		infoClass="mensaje-informacion" showSummary="true" />
</div>      