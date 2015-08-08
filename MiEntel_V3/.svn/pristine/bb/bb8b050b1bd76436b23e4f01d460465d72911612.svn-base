<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>

<cm:search id="mensajeFormularioContacto" query="idContenido = 'mensajeFormularioContacto'" useCache="true"  />


<f:view beforePhase="#{formularioContactoController.init}">

	<script type="text/javascript">
	$(document).ready(function(){
		$('.inputContacto').inputBox();
		$('.email_11').val('<h:outputText value="#{formularioContactoController.usuario.email}"/>');
	});
	</script>

	
	<!-- MENSAJES -->
	<jsp:include page="../common/messages_table.jsp"></jsp:include>
	
	<h:form styleClass="validarFormContacto" id="validarFormContacto" title="formContacto">
		<jsp:include page="/token.jsp" flush="true"/>
	<!-- CENTRO -->
	<div id="centro" class="contenedor_central" style="margin:0px;" >
	<!-- name="validarFormContacto" class="validarFormContacto" method="post" action="contacto.html" -->		
	
		<cm:getProperty node="${mensajeFormularioContacto[0]}" name="html" />
		
		<!-- DATOS PERSONALES -->
                 <div class="clearfix separador"></div>
		<div class="fieldset">
			<div class="fila-campo clearfix">
				<label>Mis nombres son:</label>
				<div class="campo"><h:inputText value="#{formularioContactoController.ticketBean.primerNombre}" styleClass="inputContacto nombre_11" maxlength="20" style="width:120px;" disabled="true"/></div>
				<div class="campo"><h:inputText styleClass="inputContacto" maxlength="20"  style="width:120px;"  value="#{formularioContactoController.ticketBean.segundoNombre}" disabled="true"/></div>

			</div>
			<div class="fila-campo clearfix">
				<label>Mis apellidos son:</label>
				<div class="campo"><h:inputText styleClass="inputContacto apellido_11"  maxlength="20"  style="width:120px;" value="#{formularioContactoController.ticketBean.primerApellido}" disabled="true"/></div>
				<div class="campo"><h:inputText styleClass="inputContacto"  maxlength="20"  style="width:120px;" value="#{formularioContactoController.ticketBean.segundoApellido}" disabled="true"/></div>
			</div>
                     <div class="fila-campo clearfix">
				<label>Mi tel&eacute;fono m&oacute;vil:</label>
				<div class="campo"><h:inputText styleClass="inputContacto num_epc_1 input_numerico" maxlength="8"  style="width:145px;" value="#{formularioContactoController.ticketBean.numeroPCS}" disabled="true" title="numeroPcs"/></div>
			</div>
                     <div class="fila-campo clearfix">
				<label>Tel&eacute;fono adicional:</label>
				<div class="campo">
					
					<h:selectOneMenu style="width:50px;" styleClass="codigo_tel_adicional selectBox" value="#{formularioContactoController.ticketBean.indicativoTelefono}" title="indNumeroAdicional">
	        			<f:selectItems value="#{formularioContactoController.indicativosTelefono}"/>
	        		</h:selectOneMenu>
				
               	</div>
				<div class="campo">
				<h:inputText styleClass="telAdicional_1 inputBox input_numerico" maxlength="8" style="width:145px;" value="#{formularioContactoController.ticketBean.telefono}" title="numeroAdicional" />
				</div>
			</div>
                     <div class="fila-campo clearfix">
				<label>Email</label>

				<div class="campo"><h:inputText styleClass="inputContacto email_11" maxlength="30" style="width:204px;" value="#{formularioContactoController.ticketBean.mail}" title="email"/></div>
			</div>
                     <div class="fila-campo clearfix">
				<label>Tipo Contacto</label>
				<div class="campo">
				
				<h:selectOneMenu value="#{formularioContactoController.ticketBean.tipo}" styleClass="tipoContacto_1 selectBox" style="width:204px;" title="tipoContacto" >
	        		<f:selectItems value="#{formularioContactoController.tiposFormularioContacto}"/>
	        	</h:selectOneMenu>
                         
                </div>
			</div>
                     <div class="fila-campo clearfix">
				<label>Motivo Contacto</label>
				<div class="campo">
				
					<h:selectOneMenu style="width:204px;" styleClass="MotivoContacto_1 selectBox" value="#{formularioContactoController.ticketBean.motivo}" title="motivoContacto">
		        		<f:selectItems value="#{formularioContactoController.motivosFormularioContacto}"/>
		        	</h:selectOneMenu>
				
                         </div>
			</div>
                     <div class="fila-campo clearfix">
				<label>Mensaje</label>
				<div class="campo campo_background">
                         	<!-- <textarea cols="0" rows="0" name="mensajeContacto" class="mensajeContacto textAreaContactenos"></textarea>  -->
                         	<h:inputTextarea value="#{formularioContactoController.ticketBean.mensaje}" styleClass="mensajeContacto_1 textAreaContactenos" title="mensaje"></h:inputTextarea>
                         </div>
			</div>
			<div class="fila-campo clearfix">
				<label>&nbsp;</label>
				<div class="campo">
					<p>M&aacute;ximo 1000 caracteres</p>
				</div>
			</div>
		</div><!-- /DATOS PERSONALES -->
		
		<!-- BTN CONTINUAR -->
		<div class="fieldset">
			<div class="fila-campo clearfix">
				<label>&nbsp;</label>
				<div class="campo">				
					<h:commandButton action="#{formularioContactoController.generarTicketSGA}" styleClass="boton-azul-enviar-form-contacto"/>				
				<!-- <a id="btnContinuar" class="btnAzulGrande btnAzulGrandeLargo enviarFormContacto"><span>Enviar</span></a>   -->
				</div>
			</div>

		</div>
		<!-- /BTN CONTINUAR -->
                 
		
	</div>
	
	</h:form>
             <!-- /CENTRO -->

</f:view>