<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>

<cm:search id="ingresoMientelV3" query="idContenido = 'ingresoMientelV3'" useCache="true"  />
<cm:search id="clientesTelefonia" query="idContenido = 'clientesTelefonia'" useCache="true"  />
<cm:search id="tituloIngresoMientelV3" query="idContenido = 'tituloIngresoMientelV3'" useCache="true"  />

<meta http-equiv="cache-control" content="public">
<meta http-equiv="cache-control" content="private">
<meta http-equiv="cache-control" content="no-store">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">   

<f:view beforePhase="#{loginController.init}">
	<style type="text/css">
		
		#bloque-izq, #bloque-der { 
		display: none !important; 
		}
		
		.icoComputer{
		display:block;
		background: url("../../framework/skins/mientel/img/LB/ico_pc_seguro.gif") no-repeat;
		width:46px;
		height: 28px;
		padding-right: 13px;
		float: left;
		}
		
	</style>

	

	<script type="text/javascript">

        var submited = false;
        function submitForm(){
            if(!submited){
            	if($('.backingFileLoginForm').valid()){
                	submited = true;
                	return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        	
        }	
	
		$(document).ready(function() {

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
			
			$(".backingFileLoginForm").validate(validatorConf);


		
			$('#portlet-login form input').keyup(function(event) {
				if (event.keyCode == '13') {
					if ($(".backingFileLoginForm").valid()){
						//$(".backingFileLoginForm").submit();
						$('#formLogin2_1_j_id_id1\\:backingFileLoginForm\\:btnLogin').click();
					}
				}
			});
		});

		
	</script>

<div id="portlet-login">

	<!-- BREADCRUMB -->
	<div class="breadcrumb">
		<a href="http://www.entel.cl/personas">Home</a> /
		<a class="seleccionado" href="#">Login</a>
	</div>
	<!-- /BREADCRUMB -->
	
	<div class="clearfix">
		<h1 class="titulo_secciones clarfix"><span class="icoComputer"></span><cm:getProperty node="${tituloIngresoMientelV3[0]}" name="html" /></h1>
	</div>

	<div class="clearfix">
		<div><cm:getProperty node="${ingresoMientelV3[0]}" name="html" /></div>
		<div class="columna_fullpage clearfix LB">

			<h2 class="titulo flotar_izquierda">Móvil</h2>
			<span class="bajada_titulo"><cm:getProperty node="${clientesTelefonia[0]}" name="html" /></span>
			<div class="separador_naranjo"></div>

			<div class="clearfix contenedor_columnas">
			
			<!-- MENSAJES -->
			<jsp:include page="../common/messages_alert.jsp"></jsp:include>

				<!-- Security Logout -->
				<c:if test="${!(empty sessionScope.securityLogoutMessage)}" >
					<div class="login-mensajes"><c:out value="${sessionScope.securityLogoutMessage}" /></div>
				</c:if>
				
				<h:form id="backingFileLoginForm"  styleClass="backingFileLoginForm">
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
						<div class="label">N° móvil</div>
						<div class="input campo">
							<input type="text" maxlength="11" class="inputBox" tabindex="1" name="username" style="width: 141px">
						</div>
					</div>
					
					<div class="item_formulario clearfix">
						<div class="label">RUT usuario</div>
						<div class="input campo">
							<input type="text" maxlength="12" class="inputBox" id="Rut2" tabindex="2" name="rut2" style="width: 141px">
							<input type="hidden" id="Rut" name="rut" value="" />
						</div>
					</div>
					
					<div class="item_formulario clearfix">
						<div class="label">Clave</div>
						<div class="input campo">
							<input type="password" maxlength="4" class="inputBox" tabindex="3" style="width: 141px" name="password">
						</div>
					</div>
					<div class="contenedor_botones">
						<div class="solicitar_clave"><a id="btnSolicitarClaveEntel" href="<h:outputText value="#{loginController.urlSolicitaClave}"></h:outputText>" tabindex="5">Solicitar clave</a></div>

						 <div class="contenedor_boton_azul campo">
							<h:commandLink 
							id="btnLogin" 
							action="#{loginController.login}" 
							styleClass="btnNaranjaGrande" 
							onclick="return submitForm();">
							<span>Ingresar</span>
							</h:commandLink>
						</div> 
					</div>
				</h:form>
			</div>
		</div>
	</div>
	
	<div class="relleno"></div>
</div>

</f:view>