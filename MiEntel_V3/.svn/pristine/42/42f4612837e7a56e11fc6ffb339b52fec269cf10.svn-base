<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<f:view beforePhase="#{iShopSuscripcionesController.init}">

	<cm:search id="iShopSuscHeader" query="idContenido = 'iShopSuscHeader'" useCache="true" />
	<cm:search id="iShopSuscDebesSaber" query="idContenido = 'iShopSuscDebesSaber'" useCache="true" />

	<script type="text/javascript">
		var urlActivar = '<%=request.getContextPath()%>/portlet/ishop/suscripciones/iShopActivarSuscripcionJson.faces';
		var urlDesactivar = '<%=request.getContextPath()%>/portlet/ishop/suscripciones/iShopDesactivarSuscripcionJson.faces';

		function activarSuscripcion(row, idSuscripcion) {
			$('.mensaje-exito').fadeOut();
			$('.mensaje-error-pequeno').fadeOut();
			$.ajax({
				type: 'POST',
				url: urlActivar,
				dataType: 'json',
				data: {idSuscripcion:idSuscripcion},
				cache: false,
				success: function(resp) {
					if (resp.estado == 'Ok') {
						$(row).find('span').html('Desactivar');
						$(row).click(function() {
							desactivarSuscripcion(row, idSuscripcion);
						});
						$('.mensaje-exito').fadeIn();
					} else {
						$('.mensaje-error-pequeno').fadeIn();
					}
				}
			});
		}

		function desactivarSuscripcion(row, idSuscripcion) {
			$('.mensaje-exito').fadeOut();
			$('.mensaje-error-pequeno').fadeOut();
			$.ajax({
				type: 'POST',
				url: urlDesactivar,
				dataType: 'json',
				data: {idSuscripcion:idSuscripcion},
				success: function(resp) {
					if (resp.estado == 'Ok') {
						$(row).find('span').html('Activar');
						$(row).click(function() {
							activarSuscripcion(row, idSuscripcion);
						});
						$('.mensaje-exito').fadeIn();
					} else {
						$('.mensaje-error-pequeno').fadeIn();
					}
				}
			});
		}

		function verDetalle(link) {
			var tbody = $(link).parent().parent().parent();
			var tr = $(tbody).find('tr:last');
			if (tr.hasClass('oculto')) {
				$(link).html('Cerrar');
				tr.removeClass('oculto');
			} else {
				$(link).html('Detalle');
				tr.addClass('oculto');
			}
		}
	</script>

	<div class="ishop">

		<h1>Suscripción de contenidos</h1>

		<div class="mensaje-exito" style="display:none;">
			<div class="clearfix sub-contenedor">
				<div class="contenedor-imagen">
					<div class="imagen"></div>
				</div>
				<div class="texto">Suscripcion actualizada</div>
			</div>
		</div>

		<div class="mensaje-error-pequeno" style="display:none;">
			<div class="clearfix sub-contenedor">
				<div class="contenedor-imagen">
					<div class="imagen"></div>
				</div>
				<div class="texto">No se pudo realizar el cambio en la suscripción</div>
			</div>
		</div>

		<cm:getProperty node="${iShopSuscHeader[0]}" name="html" />

		<!-- Tabla Tarificacion -->
		<div class="tabla">
			<div class="header_tabla clearfix">
				<div class="top"><span></span></div>
				<div class="main">
					<table>
						<tr>
							<th width="160px">Suscripciones</th>
							<th width="55px">Precio</th>
							<th width="110px">Fecha Activación</th>
							<th width="100px">Descargas<br> Disponibles</th>
							<th>&nbsp;</th>
						</tr>
					</table>
				</div>
				<div class="bottom"><span></span></div>
			</div>
			<div class="contenido_tabla">
				<it:iterator var="susc" value="#{iShopSuscripcionesController.suscripciones}" rowIndexVar="row">
					<c:set var="style" value="#{row % 2 == 0 ? '': 'impar'}" scope="page" />
					<table>
						<tbody>
							<tr class="<h:outputText value="#{style}"/>">
								<td width="160px"><h:outputText value="#{susc.nombre}" /></td>
								<td width="55px"><h:outputText value="#{susc.precio}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></td>
								<td width="110px">
									<h:panelGroup rendered="#{susc.activado}">
										<h:outputText value="#{susc.fechaActivacion}" />
									</h:panelGroup>
									<h:panelGroup rendered="#{!susc.activado}">
										---
									</h:panelGroup>
								</td>
								<td width="100px"><h:outputText value="#{susc.descargasHechas == null ? '0' : susc.descargasHechas} de #{susc.maxDescargas}" />
									<h:panelGroup rendered="#{susc.activado}">
										| <a onclick="javascript:verDetalle(this);">Detalle</a>
									</h:panelGroup>
								</td>
								<td align="center">
									<h:panelGroup rendered="#{susc.estado != null}">
										<h:panelGroup rendered="#{susc.estado == 'desactivada'}">
											<h:panelGroup rendered="#{!susc.activado}">
												<a class="btnVerdeDelgado" onclick="javascript:activarSuscripcion(this,'<h:outputText value="#{susc.idSuscripcion}" />');"><span>Activar</span></a>
											</h:panelGroup>
											<h:panelGroup rendered="#{susc.activado}">
												<a class="btnVerdeDelgado" onclick="javascript:desactivarSuscripcion(this,'<h:outputText value="#{susc.idSuscripcion}" />');"><span>Desactivar</span></a>
											</h:panelGroup>
										</h:panelGroup>
										<h:panelGroup rendered="#{susc.estado == 'bloqueada'}">
											<a class="btnDesactivado"><span>Bloqueada</span></a>
										</h:panelGroup>
									</h:panelGroup>
									<h:panelGroup rendered="#{susc.estado == null}">
										<a class="btnVerdeDelgado" onclick="javascript:activarSuscripcion(this,'<h:outputText value="#{susc.idSuscripcion}" />');"><span>Activar</span></a>
									</h:panelGroup>
								</td>
							</tr>
							<!-- Detalle de Suscripciones -->
							<h:panelGroup rendered="#{susc.activado}">
								<tr class="oculto estado_pendiente">
									<td colspan="5">
										<div>
											<strong>Descargas realizadas: </strong><h:outputText value="#{susc.descargasHechas}" /><br />
											<!-- historico -->
											<h:panelGroup rendered="#{fn:length(susc.historico) > 0}">
												<div class="bloque_historial clearfix" style="display: block;">
													<div class="bloque_historial_item"><strong>Historico de Suscripciones</strong><br /></div>
													<it:iterator var="hist" value="#{susc.historico}" rowIndexVar="rowHist">
														<div class="bloque_historial_item bloque_historial_borde">
															<strong>Tipo de Contenido: </strong><h:outputText value="#{hist.tipoContenido}" /><br />
															<strong>Descripcion: </strong><h:outputText value="#{hist.descripcion}" /><br />
															<strong>Artista: </strong><h:outputText value="#{hist.artista}" /><br />
															<strong>Fecha: </strong><h:outputText value="#{hist.fecha}" />
														</div>
													</it:iterator>
												</div>
											</h:panelGroup>
											<!-- /historico -->
										</div>
									</td>
							  	</tr>
						  	</h:panelGroup>
						  	<!-- /Detalle de Suscripciones -->
						</tbody>
					</table>
				</it:iterator>
			</div>
		</div>
		<!-- /Tabla Tarificacion -->

		<a href='<h:outputText value="#{iShopSuscripcionesController.iShopLink}" />' class="enlaceExterno" target="_blank"><strong>Más información</strong></a>

		<!-- debes saber-->
		<div class="clearfix" id="debes-saber">
			<div class="img"><img alt="Debes saber" src="../framework/skins/mientel/img/debessaber.gif"></div>
			<div class="contenido">
				<cm:getProperty node="${iShopSuscDebesSaber[0]}" name="html" />
			</div>
		</div>
		<!-- /debes saber-->
	</div>
</f:view>