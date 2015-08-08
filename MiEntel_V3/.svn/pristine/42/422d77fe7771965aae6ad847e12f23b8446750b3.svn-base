<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<f:view beforePhase="#{iShopAgendamientoController.init}">	
	<script type="text/javascript">	
		$(document).ready(function(){
			$('.AgenEliminar').click(function(e){
				var filaAgen = $(this).parent().parent();				
				if ($(filaAgen).hasClass('impar')) {
					$(filaAgen).attr('id','impar');
					$(filaAgen).removeClass('impar');
				}
				$(filaAgen).addClass('alertaAgen');
				$(filaAgen).find('.AgenConfirmar').show();
				$(filaAgen).find('.AgenCancelar').show();
				$(this).hide();
			});
			
			$('.AgenCancelar').click(function(e){
				var filaAgen = $(this).parent().parent();
				if ($(filaAgen).attr('id') == 'impar') {
					$(filaAgen).attr('id','');
					$(filaAgen).addClass('impar');
				}	
				$(filaAgen).removeClass('alertaAgen');
				$(filaAgen).find('.AgenConfirmar').hide();
				$(filaAgen).find('.AgenEliminar').show();
				$(this).hide();
			});
		});
		
		var urlPausa = '<%=request.getContextPath()%>/portlet/ishop/agendamiento/iShopPausarAgendamientoJson.faces';
		var urlDespausar = '<%=request.getContextPath()%>/portlet/ishop/agendamiento/iShopDespausarAgendamientoJson.faces';
		var urlEliminar = '<%=request.getContextPath()%>/portlet/ishop/agendamiento/iShopEliminarAgendamientoJson.faces';
		var urlPausaTodos = '<%=request.getContextPath()%>/portlet/ishop/agendamiento/iShopPausarTodosAgendamientoJson.faces';
		var urlDespausaTodos = '<%=request.getContextPath()%>/portlet/ishop/agendamiento/iShopDespausarTodosAgendamientoJson.faces';
	
		function pausarAgendamiento(row, tipoMensajeria, tipo, idPedido, hora) {
			$('.mensaje-exito').fadeOut();
			$('.mensaje-error-pequeno').fadeOut();			
			$.ajax({
				type: 'POST',
				url: urlPausa, 
				dataType: 'json',
				data: {tipoMensajeria:tipoMensajeria, tipo:tipo, idPedido:idPedido, hora:hora},
				success: function(resp) {
					if(resp.estado == 'Ok') {
						$(row).find('span').html('Activar');
						$(row).removeClass('btnAzulDelgado');
						$(row).addClass('btnVerdeDelgado');
						$(row).click(function() {
							despausarAgendamiento(row, tipoMensajeria, tipo, idPedido, hora);
						});						
						$('.mensaje-exito').find('.texto').html('Agendamiento actualizado');
						$('.mensaje-exito').fadeIn();																
					} else {
						$('.mensaje-error-pequeno').fadeIn();
					}
				}
			});
		}

		function despausarAgendamiento(row, tipoMensajeria, tipo, idPedido, hora) {
			$('.mensaje-exito').fadeOut();
			$('.mensaje-error-pequeno').fadeOut();
			$.ajax({
				type: 'POST',
				url: urlDespausar, 
				dataType: 'json',
				data: {tipoMensajeria:tipoMensajeria, tipo:tipo, idPedido:idPedido, hora:hora},
				success: function(resp) {
					if(resp.estado == 'Ok') {
						$(row).find('span').html('Pausar');
						$(row).removeClass('btnVerdeDelgado');
						$(row).addClass('btnAzulDelgado');												
						$(row).click(function() {
							pausarAgendamiento(row, tipoMensajeria, tipo, idPedido, hora);
						});
						$('.mensaje-exito').find('.texto').html('Agendamiento actualizado');
						$('.mensaje-exito').fadeIn();																
					} else {
						$('.mensaje-error-pequeno').fadeIn();
					}
				}
			});
		}			
		
		function eliminarAgendamiento(row, tipoMensajeria, tipo, idPedido, hora) {
			$('.mensaje-exito').fadeOut();
			$('.mensaje-error-pequeno').fadeOut();
			$.ajax({
				type: 'POST',
				url: urlEliminar, 
				dataType: 'json',
				data: {tipoMensajeria:tipoMensajeria, tipo:tipo, idPedido:idPedido, hora:hora},
				success: function(resp) {
					if(resp.estado == 'Ok') {
						$(row).parents("tr:first").remove();
						$('.mensaje-exito').find('.texto').html('Agendamiento eliminado');
						$('.mensaje-exito').fadeIn();																
					} else {
						$('.mensaje-error-pequeno').fadeIn();
					}
				}
			});
		}
		
		function pausarTodos(link) {
			$('.mensaje-exito').fadeOut();
			$('.mensaje-error-pequeno').fadeOut();
			$.ajax({
				type: 'POST',
				url: urlPausaTodos, 
				dataType: 'json',
				success: function(resp) {
					if(resp.estado == 'Ok') {
						$('.contenido_tabla').find('tbody:first').find('tr').find('td:last').find('a').find("span:contains('Pausar')").each(function(index) {
							var a = $(this).parent();
							var tr = $(a).parent();
							var hidden = $(tr).find('input[type=hidden]');				
							
							var tipoMensajeria = $(hidden[0]).val();
							var tipo = $(hidden[1]).val();
							var idPedido = $(hidden[2]).val();
							var hora = $(hidden[3]).val();
							
							$(this).html('Activar');
							$(a).removeClass('btnAzulDelgado');
							$(a).addClass('btnVerdeDelgado');							
							$(a).click(function() {
								despausarAgendamiento(a, tipoMensajeria, tipo, idPedido, hora);
							});
							$(link).find('span').html('Activar Todos');							
							$(link).removeClass('btnAzulDelgado');
							$(link).addClass('btnVerdeDelgado');
							$(link).click(function() {
								despausarTodos(link);
							});							
							$('.mensaje-exito').find('.texto').html('Todos los agendamientos activos han sido pausados');
							$('.mensaje-exito').fadeIn();
						});																
					} else {
						$('.mensaje-error-pequeno').fadeIn();
					}
				}
			});	
		}
		
		function despausarTodos(link) {
			$('.mensaje-exito').fadeOut();
			$('.mensaje-error-pequeno').fadeOut();
			$.ajax({
				type: 'POST',
				url: urlDespausaTodos, 
				dataType: 'json',
				success: function(resp) {
					if(resp.estado == 'Ok') {
						$('.contenido_tabla').find('tbody:first').find('tr').find('td:last').find('a').find("span:contains('Pausar')").each(function(index) {
							var a = $(this).parent();
							var tr = $(a).parent();
							var hidden = $(tr).find('input[type=hidden]');				
							
							var tipoMensajeria = $(hidden[0]).val();
							var tipo = $(hidden[1]).val();
							var idPedido = $(hidden[2]).val();
							var hora = $(hidden[3]).val();
							
							$(this).html('Pausar');
							$(a).removeClass('btnVerdeDelgado');
							$(a).addClass('btnAzulDelgado');							
							$(a).click(function() {
								pausarAgendamiento(a, tipoMensajeria, tipo, idPedido, hora);
							});
							$(link).find('span').html('Pausar Todos');
							$(link).removeClass('btnVerdeDelgado');
							$(link).addClass('btnAzulDelgado');
							$(link).click(function() {
								pausarTodos(link);
							});
							$('.mensaje-exito').find('.texto').html('Todos los agendamientos pausados han sido activados');
							$('.mensaje-exito').fadeIn();
						});																
					} else {
						$('.mensaje-error-pequeno').fadeIn();
					}
				}
			});	
		}				
	</script>
	
	<div class="ishop">
		<h1>Mis Agendamientos</h1>
		
		<h:panelGroup rendered="#{fn:length(iShopAgendamientoController.agendamiento) == 0}">
			<div class="mensaje-alerta-sistema" style="width:548px;">
				<div class="clearfix sub-contenedor">
					<div class="contenedor-imagen">
						<div class="imagen"></div>
					</div>
					<div class="texto">No tienes ningún agendamiento</div>
				</div>
			</div>		
		</h:panelGroup>
		
		<h:panelGroup rendered="#{fn:length(iShopAgendamientoController.agendamiento) > 0}">
			<div class="mensaje-exito" style="display:none;">
				<div class="clearfix sub-contenedor">
					<div class="contenedor-imagen">
						<div class="imagen"></div>
					</div>
					<div class="texto"></div>
				</div>
			</div>
		
			<div class="mensaje-error-pequeno" style="display:none;">
				<div class="clearfix sub-contenedor">
					<div class="contenedor-imagen">
						<div class="imagen"></div>
					</div>
					<div class="texto">No se pudo cambiar el estado del agendamiento</div>
				</div>
			</div>		
			
			<div class="margen" id="pasos">Estado de todos tus agendados</div>
			<div class="linea-azul"></div>	
			
			<!-- Tabla Tarificacion -->
			<div class="tabla">	
				<div class="header_tabla clearfix">                    	
					<div class="top"><span></span></div>
					<div class="main">
						<table>
							<tr>
								<th width="140px" style="border:none;"></th>
								<th width="90px" style="border:none;">Formato</th>
								<th width="90px" style="border:none;">Per&iacute;odo</th>
								<th width="50px" style="border:none;">&nbsp;</th>
								<th width="50px">&nbsp;</th>                                    
							</tr>								
						</table>					
					</div>
					<div class="bottom"><span></span></div>
				</div>
				<div class="contenido_tabla">
					<table>
						<tbody>
							<it:iterator var="ag" value="#{iShopAgendamientoController.agendamiento}" rowIndexVar="row">		
								<c:set var="style" value="#{row % 2 == 0 ? '': 'impar'}" scope="page" />					
								<tr class="filaAgen <h:outputText value="#{style}"/>">
									<td width="140px"><h:outputText value="#{ag.descripcion}" /></td>
									<td width="90px"><h:outputText value="#{ag.tipoServicio}" /></td>
									<td width="90px">Diario</td>
									<td width="50px">
										<a class="AgenConfirmar oculto" onclick="javascript:eliminarAgendamiento(this, '<h:outputText value="#{ag.tipoServicio}" />','<h:outputText value="#{ag.tipo}" />','<h:outputText value="#{ag.id}" />','<h:outputText value="#{ag.hora}" />');">Confirmar</a>
										<a class="AgenEliminar">Eliminar</a>
										<a class="AgenCancelar oculto">Cancelar</a>
									</td>							
									<td width="50px" align="center">
										<h:inputHidden value="#{ag.tipoServicio}" />
										<h:inputHidden value="#{ag.tipo}" />
										<h:inputHidden value="#{ag.id}" />
										<h:inputHidden value="#{ag.hora}" />
										<h:panelGroup rendered="#{ ag.status == 'false' || ag.status == 'A' }">
											<a class="btnAzulDelgado" onclick="javascript:pausarAgendamiento(this, '<h:outputText value="#{ag.tipoServicio}" />','<h:outputText value="#{ag.tipo}" />','<h:outputText value="#{ag.id}" />','<h:outputText value="#{ag.hora}" />');"><span>Pausar</span></a>										
										</h:panelGroup>
										<h:panelGroup rendered="#{ ag.status == 'true' || ag.status == 'S' }">
											<a class="btnVerdeDelgado" onclick="javascript:despausarAgendamiento(this, '<h:outputText value="#{ag.tipoServicio}" />','<h:outputText value="#{ag.tipo}" />','<h:outputText value="#{ag.id}" />','<h:outputText value="#{ag.hora}" />');"><span>Activar</span></a>
										</h:panelGroup>
									</td>								
								</tr>					
							</it:iterator>																					
						</tbody>
					</table>
					<table>
						<tbody>
							<tr>
								<td width="425px"></td>
								<td>
									<h:panelGroup rendered="#{iShopAgendamientoController.tieneActivos}">	
										<a class="btnAzulDelgado alargar" onclick="javascript:pausarTodos(this);"><span>Pausar Todos</span></a>
									</h:panelGroup>
									<h:panelGroup rendered="#{!iShopAgendamientoController.tieneActivos}">	
										<a class="btnVerdeDelgado alargar" onclick="javascript:despausarTodos(this);"><span>Activar Todos</span></a>
									</h:panelGroup>
								</td>
							</tr>
						</tbody>
					</table>											
				</div>
			</div>
		</h:panelGroup>
		<!--/tabla-->	
	</div>
</f:view>