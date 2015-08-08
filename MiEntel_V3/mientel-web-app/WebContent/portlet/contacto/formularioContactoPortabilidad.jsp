<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>

<cm:search id="mensajeFormularioContactoPortabilidad" query="idContenido = 'mensajeFormularioContactoPortabilidad'" useCache="true"  />


<f:view beforePhase="#{formularioContactoPortabilidadController.init}">

	<script type="text/javascript">
	$(document).ready(function() {
		$('.inputContacto').inputBox();
		$('.port_email').val('<h:outputText value="#{formularioContactoPortabilidadController.usuario.email}"/>');
	});
	</script>
	
	<!-- MENSAJES -->
	<jsp:include page="../common/messages_table.jsp"></jsp:include>
	
	<h:form styleClass="validarFormContactoPortabilidad" id="validarFormContactoPortabilidad">
		<jsp:include page="/token.jsp" flush="true"/>
		<!-- CENTRO -->
		<div id="centro" class="contenedor_central" style="margin:0px;" >		
		
			<cm:getProperty node="${mensajeFormularioContactoPortabilidad[0]}" name="html" />
			
			<!-- DATOS PERSONALES -->
			<div class="clearfix separador"></div>
			<div class="fieldset">
				<div class="fila-campo clearfix">
					<label>Mis nombres son:</label>
					<div class="campo"><h:inputText value="#{formularioContactoPortabilidadController.ticketBean.primerNombre}" styleClass="inputContacto nombre" maxlength="20" style="width:120px;" disabled="true"/></div>
					<div class="campo"><h:inputText value="#{formularioContactoPortabilidadController.ticketBean.segundoNombre}" styleClass="inputContacto nombre2" maxlength="20" style="width:120px;" disabled="true"/></div>
				</div>
				<div class="fila-campo clearfix">
					<label>Mis apellidos son:</label>
					<div class="campo"><h:inputText value="#{formularioContactoPortabilidadController.ticketBean.primerApellido}" styleClass="inputContacto apellido" maxlength="20" style="width:120px;" disabled="true"/></div>
					<div class="campo"><h:inputText value="#{formularioContactoPortabilidadController.ticketBean.segundoApellido}" styleClass="inputContacto apellido2" maxlength="20" style="width:120px;" disabled="true"/></div>
				</div>
	            <div class="fila-campo clearfix">
					<label>Mi n&uacute;mero PCS es:</label>
					<div class="campo"><h:inputText value="#{formularioContactoPortabilidadController.ticketBean.numeroPCS}" styleClass="inputContacto num_epcs input_numerico" maxlength="8"  style="width:145px;" disabled="true"/></div>
				</div>
	            <div class="fila-campo clearfix">
					<label>Otro Tel&eacute;fono:</label>
					
					<!-- /RGUZMAN -->
					<div class="campo">
						<h:selectOneMenu style="width:50px;" styleClass="codigo_tel_adicional selectBox" value="#{formularioContactoPortabilidadController.ticketBean.indicativoTelefono}">
		        			<f:selectItems value="#{formularioContactoPortabilidadController.indicativosTelefono}"/>
		        		</h:selectOneMenu>
               		</div>
               		
					<div class="campo">
						<h:inputText value="#{formularioContactoPortabilidadController.ticketBean.telefono}" styleClass="inputContacto otro_numero input_numerico" maxlength="8" style="width:145px;"/>
					</div>
				</div>
				<div class="fila-campo clearfix">
					<label>Email</label>
					<div class="campo"><h:inputText value="#{formularioContactoPortabilidadController.ticketBean.mail}" styleClass="inputContacto port_email" maxlength="30" style="width:204px;" /></div>
				</div>
				<div class="fila-campo clearfix">
					<label>Marca Equipo</label>
					<div class="campo">				
						<h:selectOneMenu value="#{formularioContactoPortabilidadController.ticketBean.marcaEquipo}" styleClass="marcaEquipo selectBox" style="width:204px;" >
			        		<f:selectItems value="#{formularioContactoPortabilidadController.marcasEquipo}"/>
			        	</h:selectOneMenu>                         
	                </div>
				</div>
				<div class="fila-campo clearfix">
					<label>Modelo</label>
					<div class="campo"><h:inputText value="#{formularioContactoPortabilidadController.ticketBean.modeloEquipo}" styleClass="inputContacto modelo" maxlength="50" style="width:204px;"/></div>
				</div>			
				<div class="fila-campo clearfix">
					<label>Mensaje</label>
					<div class="campo campo_background">
	                	<h:inputTextarea value="#{formularioContactoPortabilidadController.ticketBean.mensaje}" styleClass="textAreaContactenos mensajePortabilidad"></h:inputTextarea>
	                </div>
				</div>
				<div class="fila-campo clearfix">
					<label>&nbsp;</label>
					<div class="campo">
						<p>M&aacute;ximo 1000 caracteres</p>
					</div>
				</div> 				                      
			</div>
			<!-- /DATOS PERSONALES -->
			
			<!-- BTN CONTINUAR -->
			<div class="fieldset">
				<div class="fila-campo clearfix">
					<label>&nbsp;</label>
					<div class="campo">				
						<h:commandButton action="#{formularioContactoPortabilidadController.generarTicketSGA}" styleClass="boton-azul-enviar-form-contacto"/>
					</div>
				</div>	
			</div>
			<!-- /BTN CONTINUAR -->
		</div>
	</h:form>
	<!-- /CENTRO -->

</f:view>