<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.bea.com/servers/portal/tags/netuix/render" prefix="render"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.epcs.recursoti.configuracion.MiEntelProperties"%>
<%@page import="com.epcs.recursoti.configuracion.error.ServiceMessages"%>
<meta http-equiv="cache-control" content="no-store">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   

<render:defineObjects />

<%
    com.bea.portlet.WindowURL url = com.bea.portlet.WindowURL
    .createWindowURL(request, response);

    String no_autentica = (String) com.bea.p13n.usermgmt.SessionHelper
    .getSession(request).getAttribute("no_autentica");
    String no_profile = (String) com.bea.p13n.usermgmt.SessionHelper
    .getSession(request).getAttribute("no_profile");
    String error_aplicacion = (String) com.bea.p13n.usermgmt.SessionHelper
    .getSession(request).getAttribute("error_aplicacion");

    ServiceMessages errorMessages = MiEntelProperties
    .getServiceMessages();
%>

<style type="text/css">
	#bloque-izq, #bloque-der { display: none; }
</style>
<div id="portlet-login">
	<!-- BREADCRUMB -->
	<div class="breadcrumb">
		<a href="http://www.entel.cl/personas">Home</a> /
		<a class="seleccionado" href="#">Login</a>
	</div>
	<!-- /BREADCRUMB -->
	<div class="clearfix">
		<h1 class="titulo_secciones"><img alt="" src="${skin.fullPath}/img/LB/ico_pc_seguro.gif"> Ingresa a Mi Entel</h1>
	</div>

	<div class="clearfix">
		<p class="bajada">
			Ingresando a Mi Entel, podr&aacute;s administrar tu cuenta, ver tu <strong>boleta</strong>
			 o <strong>factura</strong>, detalle de <strong>llamadas</strong>, <strong>cobros</strong>, 
			 <strong>pago</strong> de cuentas en l&iacute;nea y <strong>solicitudes</strong> v&iacute;a web.</p>
		<div class="columna_fullpage clearfix LB">

			<h2 class="titulo flotar_izquierda">M&oacute;vil</h2>
			<span class="bajada_titulo">Para clientes telefon&iacute;a m&oacute;vil</span>
			<div class="separador_naranjo"></div>

			<div class="clearfix contenedor_columnas">

				<script type="text/javascript">
					$(document).ready(function() {

						/*
						var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
						.css({top: "-999px", left: "-999px", position: 'absolute' })
						.appendTo('body');
						
						$('#globoError').hide();
						/*---*/
						
						/*
						$('input').keypress(function() {
							$('#globoError').fadeOut();	
						});
						/**/
						
						
						jQuery.validator.addMethod('numeroPcs', function(value, element){
							return /^[0-9]{8,11}$/.test(value);
						}, 'Ingresa un número válido.');
						
						var validatorConf = {
							onkeyup: false,
							onfocusout: false,
							errorPlacement: function(error, element) {
								
								var $form = element.parents('form');
								var firstError = $form.validate().errorList[0].message;
								
								$('#globoError').find('td:first').html(firstError);
								showGlobo2($form.validate().errorList[0].element);
							},
							rules: {
								username: {
									required: true,
									numeroPcs: true
								},
								rut2: {
									required: true,
									rut: true			
								},
								password: {
									required: true					
								}
							},
							messages: {
								username: {
									required: "Debes ingresar el número de tu Entel.",
									numeroPcs: "Ingresa un número válido."
								},
								rut2: {
									required: "Ingresa tu rut.",
									rut: "Ingresa un rut válido."
								},
								password: {
									required: "Ingresa tu contraseña."
								}
							}
						};

						$("#Rut2").blur(function() {
							formatearRut('#Rut2'); //.replace(/([0-9kK])$/, "-$1");
							$("#Rut").val( $("#Rut2").val().replace(/[^0-9kK]/g, "").toLowerCase() );
						});

						$("input[name='username']").inputNumerico();

						$("input[name='rut2']").inputRut();
						
						$("#backingFileLoginForm").validate(validatorConf);

						$("#btnLogin").click(function() {
							if ($("#backingFileLoginForm").valid())
								$("#backingFileLoginForm").submit();
						});
						$('#portlet-login form input').keyup(function(event) {
							if (event.keyCode == '13') {
								if ($("#backingFileLoginForm").valid())
									$("#portlet-login form").submit();
							}
						});
					});
				</script> 
				
				<%
     if (no_autentica != null) {
 %>
<div class="login-mensajes">
<%
    out.print(errorMessages.getErrorMessage("autenticacion", "noautentica"));
%>
</div>
<%
    }
    else if (error_aplicacion != null) {
%><div class="login-mensajes">
<%
    out.print(errorMessages.getErrorMessage("autenticacion", "general"));
%>
</div>
<%
    }
    else if (no_profile != null) {
%><div class="login-mensajes">
<%
    out.print(errorMessages.getErrorMessage("autenticacion", "perfilamiento"));
%>



</div>
<%
    }
%>

				<!-- Security Logout -->
				<c:if test="${!(empty sessionScope.securityLogoutMessage)}" >
					<div class="login-mensajes"><c:out value="${sessionScope.securityLogoutMessage}" /></div>
				</c:if>

				<form method="post" id="backingFileLoginForm" action="<%=url.toString()%>" type="POST">
					<jsp:include page="/token.jsp" flush="true"/>
					<input type="hidden" value="ingreso" name="funcion">
					<input type="hidden" name="ext" value="%26Sistema%3D1011%26Portal%3DON%26desdelogin%3D%26miEPCS%3DNEW%26MENU%3DSI">
					<input type="hidden" value="1011" name="Sistema">
					<input type="hidden" value="ON" name="Portal">				<!-- ON -->
					<input type="hidden" value="SI" name="desdelogin">			<!--SI -->
					<input type="hidden" id="buic_rutdv" name="buic_rutdv">
					<input type="hidden" value="NEW" name="miEPCS">
					<input type="hidden" value="yes" name="buic">
					
					<div class="item_formulario clearfix">
						<div class="label">Nº m&oacute;vil</div>
						<div class="input campo">
							<input type="text" maxlength="11" class="inputBox" tabindex="1" name="username">
						</div>
					</div>
					
					<div class="item_formulario clearfix">
						<div class="label">RUT usuario</div>
						<div class="input campo">
							<input type="text" maxlength="12" class="inputBox" id="Rut2" tabindex="2" name="rut2">
							<input type="hidden" id="Rut" name="rut" value="" />
						</div>
					</div>
					
					<div class="item_formulario clearfix">
						<div class="label">Clave</div>
						<div class="input campo">
							<input type="password" maxlength="4" class="inputBox" tabindex="3" name="password">
						</div>
					</div>
					<div class="contenedor_botones">
						<div class="solicitar_clave"><a id="btnSolicitarClaveEntel" href="#solicitar-clave" tabindex="5">Solicitar clave</a></div>
						<div class="contenedor_boton_azul campo">
							<a id="btnLogin" tabindex="4" class="btn_azul" href="#ingresar"><span>Ingresar</span></a>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
	
	<div class="relleno"></div>
</div>