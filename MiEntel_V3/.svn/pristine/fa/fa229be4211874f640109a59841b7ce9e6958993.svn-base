<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r" uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content"%>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<cm:search id="infoRegistro" query="idContenido = 'infoRegistro'" useCache="true" />
<cm:search id="infoMovilNoBuic" query="idContenido = 'infoMovilNoBuic'" useCache="true" />
<cm:search id="infoMovilRutNoAsociado" query="idContenido = 'infoMovilRutNoAsociado'" useCache="true" />
<cm:search id="infoFacturaElectronica" query="idContenido = 'infoFacturaElectronica'" useCache="true" />

<f:view beforePhase="#{inscripcionController.init}">

	<style type="text/css">
		#bloque-izq, #bloque-der { 
			display: none !important; 
		}
		
		#msjTerminosRegistro {
			height: 180px; 
			border: 1px solid #A9A9A9; 
			overflow-y: auto; 
			overflow-x: hidden; 
			font: 12px Arial; 
			padding: 5px 7px; 
			text-align: justify;
		}
		
		#msjTerminosRegistro ol, ul {
			padding-left: 15px;
			padding-bottom: 15px;
		}
		
		#msjTerminosRegistro h3 {
			margin-top: 10px;
		}
	</style>
	
	<script type="text/javascript">
		var respBoolean = <h:outputText value="#{inscripcionController.redireccionarHomeEntel}"/>;	
		if (respBoolean) {
			location.href='<h:outputText value="#{inscripcionController.urlHomeEntel}"/>';
		}

		function widthDivCentro() {
			$('#centro').width("889px");
		}
	
		function setAreasRegistroUsuario() {
			$("input[id*=hideCiudad]").val($("select[id*=ciudadpromociones]").val());
			$("input[id*=hideComuna]").val($("select[id*=comunapromociones]").val());
		}

		function upper(txt) {
			var txtUpper = $(txt).val().toUpperCase();
			$(txt).val(txtUpper);	
		}			
	 
		$(document).ready(function() {
			$('input.inputBox').inputBox(); 
			widthDivCentro();

			$('.mensaje-alerta').css({
				"color":"black",
				"font-weight":"normal"
			});
			
			$("a.enlaceTermCondcs").fancybox({	
				'title': 'texto', 	
				'overlayOpacity' : 0.5,
				'overlayColor' : '#000000',
				'hideOnContentClick' : false,
				'hideOnOverlayClick' : false,
				'showCloseButton'	:true,
				'padding' : 'auto',
				'scrolling' : 'no',
				'frameWidth'  : 620,
				'frameHeight' : 600
			});
			
			$("#fancy_overlay").css( {	
				width: "1400px"
			});

			dataLayer = dataLayer||[];
			dataLayer.push({
				'mx_content': 'Personas/Mi Entel/Registro/Datos',
				'event': 'pageview'
			});   
		});
	</script>
	
	<!-- MENSAJES -->
	<jsp:include page="../common/messages_alert.jsp"></jsp:include>
		 
	<div class="mis-datos-registro-info">
		<h:form id="validarRegistroTitular" styleClass="validarRegistroTitular" title="formRegistro">
			<jsp:include page="/token.jsp" flush="true"/>
			<div class="registro_top">
				<h1>Formulario de Registro</h1>
				<p><cm:getProperty node="${infoRegistro[0]}" name="html" /></p>
			</div>
					
			<!-- NUMERO PCS -->			
			<div class="fieldset">
				<div class="mis-datos-fila clearfix">
					<label>Tu n&uacute;mero es:</label>
					<div class="campo">
						<strong><h:outputText value="#{inscripcionController.numeroPcs}" /></strong>
					</div>
					<h:inputHidden value="#{inscripcionController.numeroPcs}" />
				</div>
			</div>
			<!-- /NUMERO PCS -->
							
			<!-- RUT -->
			<div class="fieldset">
				<div class="mis-datos-fila clearfix">
					<label>RUT:</label>
					<div class="campo">
						<strong><h:outputText value="#{inscripcionController.rutUsuario}" converter="rutConverter" /></strong>
					</div>
					<h:inputHidden value="#{inscripcionController.rutUsuario}" />
				</div>
			</div>
			<!-- /RUT -->
				
			<!-- E-MAIL -->
			<div class="fieldset">
				<div class="mis-datos-fila clearfix">
					<label>Email:</label>
					<div class="campo" style="position: relative; z-index: 1">
						<h:inputText id="email" styleClass="inputBox emailRegistroUsuario" value="#{inscripcionController.usuarioBean.email}" 
							maxlength="30" style="width:200px;" title="email"></h:inputText>
					</div>
				</div>
			</div>
			<!-- /E-MAIL -->
			
			<!-- CONFIRMAR E-MAIL -->
			<div class="fieldset">
				<div class="mis-datos-fila clearfix">
					<label>Confirmar email:</label>
					<div class="campo" style="position: relative; z-index: 1">
						<h:inputText id="email2" styleClass="inputBox emailRegistroUsuario2"
							value="#{inscripcionController.usuarioBean.email}" maxlength="30" style="width:200px;">
						</h:inputText>
					</div>
				</div>
			</div>
			<!-- /CONFIRMAR E-MAIL -->
				
			<!-- TELEFONO ADICIONAL -->
			<div class="fieldset">
				<div class="mis-datos-fila clearfix">
					<label>Tel&eacute;fono adicional:</label>
					<div class="campo" style="position: relative; z-index: 6">
						<h:selectOneMenu onchange="" id="regionareas" value="#{inscripcionController.usuarioBean.prefijoTelefonoAdicional}" 
							styleClass="selectBox prefijoTelAdicionalRegistroUsuario" style="width:62px;" title="indTelefonoAdicional">
								<f:selectItems value="#{inscripcionController.areasList}"/>
						</h:selectOneMenu>
					</div>
					<div class="campo" style="position: relative; z-index: 1">
						<h:inputText id="tel_adi" value="#{inscripcionController.usuarioBean.telefonoAdicional}"
							styleClass="inputBox telAdicionalRegistroUsuario" onkeypress="return soloNumeros(event);" style="width:130px;" title="telefonoAdicional" />
					</div>
				</div>
			</div>
			<!-- /TELEFONO ADICIONAL -->			
			
			<!-- TERMINOS Y CONDICIONES -->
			<div class="fieldset">
				<div class="mis-datos-fila clearfix">
					<label>T&eacute;rminos y Condiciones de Mi Entel:</label>
		 		</div>
		 		
		 		<div id="msjTerminosRegistro">
		 			<h:outputText value="#{inscripcionController.msjTerminosRegistro}" escape="false" />
		 		</div>		

				<div class="fieldset">
					<div class="mis-datos-fila clearfix">
						<div class="campo" style="position: relative; z-index: 1; padding-top: 5px">							
							<p>
								<h:selectBooleanCheckbox id="terminosRegistro" styleClass="terminosRegistroUsuario" style="margin-right: 8px" value="false" />
								Acepto los t&eacute;rminos y condiciones de Mi Entel: 
							</p>
						</div>						
					</div>
					
					<!-- BOLETA POR EMAIL -->
					<h:panelGroup rendered="#{!inscripcionController.mercadoPrepago && inscripcionController.rutTitular 
						&& inscripcionController.cuentaClienteBean != null && !inscripcionController.inscritoFacturacionElectronica}">					
						<div class="mis-datos-fila clearfix" style="padding-bottom: 20px">
							<div class="campo" style="position: relative; z-index: 1; padding-top: 5px">
								<h:selectBooleanCheckbox id="boletaElectronica" value="#{inscripcionController.suscripcionFactElectronica}" />
							</div>
							<p>
								Deseo recibir mi boleta por email. Al suscribirme acepto los 
								<h:outputLink value="msjTerminosCondiciones.faces" styleClass="thickbox">t&eacute;rminos y condiciones </h:outputLink> 
								de boleta por email.<br />
							</p>
						</div>
						
						<div class="caja verde clearfix" style="text-align: center; padding: 10px 0 0px 10px">
							<p><strong><cm:getProperty node="${infoFacturaElectronica[0]}" name="html" /></strong></p>
						</div>
					</h:panelGroup>
					<!-- /BOLETA POR EMAIL -->					
				</div>			
		 		
			</div>
			<!-- /TERMINOS Y CONDICIONES -->
                    
			<!-- BTN CONTINUAR -->
			<div class="fieldset">
				<div class="mis-datos-fila clearfix">
					<label>&nbsp;</label>
					<div class="guardar_clave_registro">
						<h:commandLink value="" action="#{inscripcionController.confirmarRegistro}" styleClass="btnAzulGrande btnAzulLargo enviarFormulario">
							<span>Continuar</span>
						</h:commandLink>
					</div>
				</div>
			</div>
			<!-- /BTN CONTINUAR -->
		</h:form>
	</div>				
</f:view>