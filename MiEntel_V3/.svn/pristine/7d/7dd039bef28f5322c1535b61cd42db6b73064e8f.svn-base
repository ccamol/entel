<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<f:view beforePhase="#{inscripcionController.init}">

<script type="text/javascript">
$(document).ready(function() {
	
	$('input.inputBox').inputBox();
	$('select.selectBox').selectBox();
	
	$('.mensaje-alerta').css({
		"color":"black",
		"font-weight":"normal"
	});
});
</script>

			<h1>Registro</h1>
			
			<!-- MENSAJES -->
			<jsp:include page="../common/messages_alert.jsp"></jsp:include>
	
			<h2 class="no_icon">Ingreso al Formulario de Registro</h2>

			<div class="mis-datos-info">
				<h:form id="ingreso-rut" styleClass="validarPreRegistroTitular">
					<jsp:include page="/token.jsp" flush="true"/>
	
					<div class="mis-datos-fila clearfix">
					<label>Movil Entel:</label>
		               <div class="campo">
		               <strong><h:outputText value="#{inscripcionController.numeroPcs}"/></strong>
		               </div>
					</div>
					
					<div class="mis-datos-fila clearfix">
					<label>RUT Usuario:</label>
		                <div class="campo">
		                <h:inputText 
		                value="#{inscripcionController.rutUsuario}" 
		                id="rut_formingreso" 
		                styleClass="inputBox rut_formingreso" 
		                maxlength="16" 
		                style="width:120px;"/>
		                </div>
					</div>
					
					
					<div class="mis-datos-fila clearfix">
					<label>&nbsp;</label>
					<div class="guardar_clave">              
			                       <h:commandLink 
			                       value="" 
			                       action="#{inscripcionController.accederFormInscripcion}"	
					    	styleClass="btnAzulGrande btnAzulLargo enviarPreFormulario"><span>Continuar</span></h:commandLink>
			               </div>
			        </div>

				</h:form>
			</div>

		
</f:view>