<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view beforePhase="#{lineaAdicionalController.init}">	
	<script type="text/javascript">
		function upper(txt) {
			var txtUpper = $(txt).val().toUpperCase();
			$(txt).val(txtUpper);	
		}

		function guardarCambios() {
			$('span[id*=nombre1]').html($('input[id*=nombre1_mod]').val());
			$('span[id*=nombre2]').html($('input[id*=nombre2_mod]').val());
			$('span[id*=apellido1]').html($('input[id*=apellido1_mod]').val());
			$('span[id*=apellido2]').html($('input[id*=apellido2_mod]').val());
			$('span[id*=telefonoAdicional]').html($('input[id*=telefonoAdicional_mod]').val());		

			//RGUZMAN
			$('span[id*=codigoTelefonoAdicional]').html($('select[id*=codigoTelefonoAdicional_mod]').val());		
			var test = $('span[id*=codigoTelefonoAdicional]').html();
			
			$('.cancelarModificarLineaAdicional').trigger('click');
		}

		var url = '<%=request.getContextPath()%>/portlet/cuenta/solicitarLineaAdicional.faces';
		
		function solicitarLineaAdicional() {
			var lineaUso = $('input[name=lineaUso]:checked').val();
			var nombre1 = $('span[id*=nombre1]').html();
			var nombre2 = $('span[id*=nombre2]').html();
			var apellido1 = $('span[id*=apellido1]').html();
			var apellido2 = $('span[id*=apellido2]').html();
			var telefonoAdicional = $('span[id*=codigoTelefonoAdicional]').html() + $('span[id*=telefonoAdicional]').html();
			
			$.ajax({
				type: 'POST',
				url: url, 
				dataType: 'json',
				data: {lineaUso:lineaUso, nombre1:nombre1, nombre2:nombre2, apellido1:apellido1, apellido2:apellido2, telefonoAdicional:telefonoAdicional},
				success: function(resp) {
					if (resp.estado == 'Ok') {
						$('.mensaje-error-pequeno').hide();
						$('.mensaje-exito').fadeIn();
					} else {
						$('.mensaje-exito').hide();
						$('.mensaje-error-pequeno').fadeIn();
					}					
				}
			});
		}		
	</script>
	
	<!-- No está en grupo habil -->
	<h:panelGroup rendered="#{!lineaAdicionalController.enGrupoHabil}">
		<div class="mensaje-alerta-sistema" style="width:548px;">
			<div class="clearfix sub-contenedor">
				<div class="contenedor-imagen">
					<div class="imagen"></div>
				</div>
				<div class="texto">No puedes solicitar una linea adicional.</div>
			</div>
		</div>		
	</h:panelGroup>
		
	<!-- Está en grupo habil -->
	<h:panelGroup rendered="#{lineaAdicionalController.enGrupoHabil}">
		<!-- Form linea adicional -->
		<div class="lineaAdicional">
			<div class="banner">&nbsp;</div>

			<div class="mensaje-exito" style="display:none;">
				<div class="clearfix sub-contenedor">
					<div class="contenedor-imagen">
						<div class="imagen"></div>
					</div>
					<div class="texto">Se envió la solicitud de linea adicional</div>
				</div>
			</div>
			
			<div class="mensaje-error-pequeno" style="display:none;">
				<div class="clearfix sub-contenedor">
					<div class="contenedor-imagen">
						<div class="imagen"></div>
					</div>
					<div class="texto">No se pudo enviar la solicitud de linea adicional</div>
				</div>
			</div>
			
			<h1>Solicita una línea adicional</h1>
			
			<p>Envíanos esta solicitud de contacto, y un ejecutivo te llamará dentro de 48 horas hábiles si la evaluación comercial es aprobada.</p><br>		
				
			<h3>1. Selecciona el uso que le darás a tu línea adicional</h3>
			<div class="usoLinea">				
				<div class="radioUso">
					<label class="labeRadioUso">
						<input type="radio" class="radioUsoLinea" id="lineaUso" name="lineaUso" checked="checked" value="ParaVoz"><span>Para Voz</span>
					</label>
					<a class="enlaceExterno" href='<h:outputText value="#{lineaAdicionalController.linkPlanesVoz}" />' target="_blank">Ver información de planes de voz</a>
				</div>
				<div class="radioUso">
					<label class="labeRadioUso">
						<input type="radio" class="radioUsoLinea" id="lineaUso" name="lineaUso" value="ParaBam"><span>Para Banda Ancha Móvil</span>
					</label>
					<a class="enlaceExterno" href='<h:outputText value="#{lineaAdicionalController.linkBAM}" />' target="_blank">Ver información de Banda Ancha Móvil</a>
				</div>
				<div class="radioUso">
					<label class="labeRadioUso">
						<input type="radio" class="radioUsoLinea" id="lineaUso" name="lineaUso" value="ParaiPhone3"><span>Para un iPhone 3</span>
					</label>
					<a class="enlaceExterno" href='<h:outputText value="#{lineaAdicionalController.linkiPhone3}" />' target="_blank">Ver información de iPhone 3</a>
				</div>
				<div class="radioUso">
					<label class="labeRadioUso">
						<input type="radio" class="radioUsoLinea" id="lineaUso" name="lineaUso" value="ParaiPhone4"><span>Para un iPhone 4</span>
					</label>
					<a class="enlaceExterno" href='<h:outputText value="#{lineaAdicionalController.linkiPhone4}" />' target="_blank">Ver información de iPhone 4</a>
				</div>			               	
			</div>	
			
			<h3>2. Completa tus datos
				<a style="text-decoration: underline;" class="modificar modificarLineaAdicional">Modificar</a>
				<a style="display: none;" class="modificar cancelarModificarLineaAdicional">Cancelar</a>
			</h3>
	
			<!-- datos personales modificar -->
			<div class="datosLinea" style="display: block;">          
				<div class="fieldset">
					<div class="fila-campo clearfix">
						<label>Nombres:</label>
						<div class="campo">
							<div class="campo-amarillo oculto">
								<span class="left"></span><h:inputText id="nombre1_mod" value="#{lineaAdicionalController.primerNombre}" required="true" requiredMessage="Ingresa Tu Primer Nombre" maxlength="20" onblur="upper(this);" onkeyup="upper(this);" onkeypress="return soloLetras(event);" style="width:150px;" /><span class="right"></span>
							</div>
							<strong><h:outputText id="nombre1" value="#{lineaAdicionalController.primerNombre}"/></strong>
						</div>
						<div class="campo">
							<div class="campo-amarillo oculto">
								<span class="left"></span><h:inputText id="nombre2_mod" value="#{lineaAdicionalController.segundoNombre}" required="true" requiredMessage="Ingresa Tu Segundo Nombre" maxlength="20" onblur="upper(this);" onkeyup="upper(this);" onkeypress="return soloLetras(event);" style="width:150px;" /><span class="right"></span>
							</div>
							<strong><h:outputText id="nombre2" value="#{lineaAdicionalController.segundoNombre}"/></strong>
						</div>
					</div>				
					<div class="fila-campo clearfix">
						<label>Apellidos:</label>
						<div class="campo">
							<div class="campo-amarillo oculto">
								<span class="left"></span><h:inputText id="apellido1_mod" value="#{lineaAdicionalController.primerApellido}" required="true" requiredMessage="Ingresa Tu Primer Apellido" maxlength="20" onblur="upper(this);" onkeyup="upper(this);" onkeypress="return soloLetras(event);" style="width:150px;" /><span class="right"></span>
							</div>
							<strong><h:outputText id="apellido1" value="#{lineaAdicionalController.primerApellido}"/></strong>
						</div>
						<div class="campo">
							<div class="campo-amarillo oculto">
								<span class="left"></span><h:inputText id="apellido2_mod" value="#{lineaAdicionalController.segundoApellido}" required="true" requiredMessage="Ingresa Tu Segundo Apellido" maxlength="20" onblur="upper(this);" onkeyup="upper(this);" onkeypress="return soloLetras(event);" style="width:150px;" /><span class="right"></span>
							</div>
							<strong><h:outputText id="apellido2" value="#{lineaAdicionalController.segundoApellido}"/></strong>
						</div>
					</div>
					<div class="fila-campo clearfix">
						<label>Móvil Entel:</label>
						<div class="campo" id="numeroPCS">
							<strong><h:outputText value="#{lineaAdicionalController.usuario.numeroPCS}" /></strong>
						</div>				
					</div>
					<div class="fila-campo clearfix">
						<label>Teléfono Adicional:</label>
						
						<!-- /RGUZMAN -->
						<div class="campo">
							<div class="campo-amarillo oculto">
								<h:selectOneMenu style="width:50px;" id="codigoTelefonoAdicional_mod" styleClass="codigo_tel_adicional selectBox" value="" required="true" requiredMessage="Selecciona Codigo de Area">
					        		<f:selectItems value="#{lineaAdicionalController.indicativosTelefono}"/>
					        	</h:selectOneMenu>								
							</div>
							<strong><h:outputText id="codigoTelefonoAdicional" value=""/></strong>
						</div>						
						<!-- /RGUZMAN -->
						
						<div class="campo">
							<div class="campo-amarillo oculto">
								<span class="left"></span><h:inputText id="telefonoAdicional_mod" value="" required="true" requiredMessage="Ingresa Tu Telefono Adicional" maxlength="8" onblur="upper(this);" onkeyup="upper(this);" onkeypress="return soloNumeros(event);" style="width:150px;" /><span class="right"></span>
							</div>
							<strong><h:outputText id="telefonoAdicional" value=""/></strong>
						</div>
					</div>
					<div class="fila-campo clearfix">
						<label>RUT:</label>
						<div class="campo" id="rut">
							<strong><h:outputText value="#{lineaAdicionalController.usuario.rut}" converter="rutConverter" /></strong>
						</div>						
					</div>
				</div>				
				<div id="enviarSolicitud">
					<a onclick="javascript:solicitarLineaAdicional();" class="btnAzulGrande btnAzulLargo"><span>Enviar Solicitud</span></a>						
				</div>
				<div id="guardarCambios" class="oculto">
					<a onclick="javascript:guardarCambios();" class="btnAzulGrande btnAzulLargo"><span>Guardar Cambios</span></a>					
				</div>
			</div>
			<!-- /datos personales modificar -->
		</div>
		<!-- /Form linea adicional -->
	</h:panelGroup>	
</f:view>