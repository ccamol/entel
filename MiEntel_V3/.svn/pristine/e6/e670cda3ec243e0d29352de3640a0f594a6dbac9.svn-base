<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<f:view beforePhase="#{formularioSatisfaccionController.init}">

	<script type="text/javascript">	
		function upper(txt) {
			var txtUpper = $(txt).val().toUpperCase();
			$(txt).val(txtUpper);	
		}
	
		function closeGlobo(){
			$('#globoError').fadeOut();
		}
		
		$(document).ready(function(){
			$('.inputContacto').inputBox();	
		});
	</script>
	
	<!-- MENSAJES -->
	<jsp:include page="../common/messages_table.jsp"></jsp:include>
	
	<h:form styleClass="validarFormContacto" id="validarFormContacto">
		<jsp:include page="/token.jsp" flush="true"/>
	
		<!-- CENTRO -->
		<div id="centro" class="contenedor_central">
			<div class="registro_top">
				<h1>Formulario de Ingreso Comentarios</h1>
				<p></p>
			</div>
			
			<!-- DATOS PERSONALES -->
			<div class="clearfix separador"></div>
			<div class="fieldset">
				<div class="fila-campo clearfix">
					<label>Mis nombres son:</label>
					<div class="campo"><h:inputText value="#{formularioSatisfaccionController.primerNombre}" styleClass="inputContacto nombre_11" maxlength="20" onblur="upper(this);" onkeyup="upper(this);" onkeypress="return soloLetras(event);" style="width:120px;"/></div>
					<div class="campo"><h:inputText styleClass="inputContacto" maxlength="20" onblur="upper(this);" onkeyup="upper(this);" onkeypress="return soloLetras(event);" style="width:120px;" value="#{formularioSatisfaccionController.segundoNombre}"/></div>
				</div>
				
				<div class="fila-campo clearfix">
					<label>Mis apellidos son:</label>
					<div class="campo"><h:inputText styleClass="inputContacto apellido_11" maxlength="20" onblur="upper(this);" onkeyup="upper(this);" onkeypress="return soloLetras(event);" style="width:120px;" value="#{formularioSatisfaccionController.apellidoPaterno}"/></div>
					<div class="campo"><h:inputText styleClass="inputContacto" maxlength="20" onblur="upper(this);" onkeyup="upper(this);" onkeypress="return soloLetras(event);" style="width:120px;" value="#{formularioSatisfaccionController.apellidoMaterno}"/></div>
				</div>
				
				<div class="fila-campo clearfix">
					<label>Mi n&uacute;mero PCS es:</label>
					<div class="campo"><h:inputText styleClass="inputContacto num_epc_1 input_numerico" maxlength="8" onkeypress="return soloNumeros(event);" style="width:145px;" readonly="true" value="#{formularioSatisfaccionController.numeroPCS}"/></div>
				</div>
				 
				<div class="fila-campo clearfix">
					<label>Tel&eacute;fono adicional:</label>
					<div class="campo">				
						<h:selectOneMenu style="width:50px;" styleClass="codigo_tel_adicional selectBox" value="#{formularioSatisfaccionController.codigoTelAdicional}">
	        				<f:selectItems value="#{formularioSatisfaccionController.indicativosTelefono}"/>
		        		</h:selectOneMenu>
					</div>
					
					<div class="campo">
						<h:inputText styleClass="inputContacto input_numerico" maxlength="8" onkeypress="return soloNumeros(event);" style="width:145px;" value="#{formularioSatisfaccionController.telAdicional}"/>
					</div>
				</div>
				
				<div class="fila-campo clearfix">
					<label>Email</label>
					<div class="campo">
						<h:inputText styleClass="inputContacto email_11" maxlength="30" style="width:204px;" value="#{formularioSatisfaccionController.eMail}"/>
					</div>
				</div>
				 
				<div class="fila-campo clearfix">
					<label>Mensaje</label>
					<div class="campo campo_background">						
						<h:inputTextarea value="#{formularioSatisfaccionController.mensaje}" styleClass="mensajeContacto_1 textAreaContactenos"></h:inputTextarea>
					 </div>
				</div>                      
			</div>
			<!-- /DATOS PERSONALES -->
			
			<!-- BTN CONTINUAR -->
			<div class="fieldset">
				<div class="fila-campo clearfix">
					<label>&nbsp;</label>
					<div class="campo">
						<h:commandButton action="#{formularioSatisfaccionController.enviarDatos}" styleClass="boton-azul-enviar-form-contacto" />						
					</div>
				</div>
			</div>
			<!-- /BTN CONTINUAR -->
		</div>
		<!-- /CENTRO -->
	
	</h:form>	
</f:view>