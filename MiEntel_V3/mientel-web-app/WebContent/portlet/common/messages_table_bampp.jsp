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

		var temp=$("#contenedor-mensajes .mensaje-informacion td").html();
		if (temp!=null){			
			var tempSplit=$.trim(temp).split(":");
			if (tempSplit[0]=="bolsaCuota"){
				$("#contenedor-mensajes .mensaje-informacion td").css("text-align","center");
				$("#contenedor-mensajes-ie .mensaje-informacion td").css("text-align","center");
				var msg="Tu bolsa: "+tempSplit[1]+", ha sido activada exitosamente.<br/>"+
						"Para seguir navegando debes restablecer tu conexi&oacute;n utilizado el software de tu Banda Ancha M&oacute;vil,"+
						" o si est&aacute;s conectado desde tu Tablet 3G te sugerimos actualizar la p&aacute;gina de tu navegador.";
				$("#contenedor-mensajes .mensaje-informacion td").css("text-align","left");
				$("#contenedor-mensajes-ie .mensaje-informacion td").css("text-align","left");				
				$("#contenedor-mensajes .mensaje-informacion td").html(msg);
				$("#contenedor-mensajes-ie .mensaje-informacion td").html(msg);				
			}
		}
		//Hacemos el teardown luego de cargar TODA la pagina
		$(window).load(function () {
			var doTeardown = '<%=request.getContextPath()%>/portlet/bolsabam/comprarBolsasTeardown.faces';
			$.post(doTeardown);	
		});	
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
