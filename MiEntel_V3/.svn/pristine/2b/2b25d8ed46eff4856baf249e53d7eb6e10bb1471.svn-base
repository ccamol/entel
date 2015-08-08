<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<pref:getPreference name="descAbonoPromocional" var="descAbonoPromocional" defaultValue=""/>

<!-- Contenidos -->
<cm:search id="infoRestriccionAAA" query="idContenido = 'infoRestriccionAAA'" useCache="false"/>

<f:view beforePhase="#{vtasYMktgFidelizacionCartolaController.initCargarHistorialCanje}">

	<h2 class="bullet_zonapuntos">
		Historial de Canje
	</h2>
	
	<!-- Alerta de AAA -->
	<entel:view name="alertaAAAZonaPuntos">
	          <cm:search id="infoRestriccionAAA0" query="idContenido = 'infoRestriccionAAA'" useCache="false"/>
				<div class="contenedor-mensajes">
			        <ul>
				        <li class="mensaje-alerta">
					        <div align="center"> 
					        	<span><cm:getProperty node="${infoRestriccionAAA0[0]}" name="html"/></span>
					        </div> 
				        </li>
			        </ul>
			     </div>
	</entel:view>
	
	<entel:view name="tablaHistorial">
		
		<script type="text/javascript">
			var tipo;
			var anio;
			var pagTotal;
			var puedeBusqueda=true;
	
			function paginarHistorial(){
				// Paginador --------------------------->
					
				$("#paginador-historial-canje").paginate({
					count 		: pagTotal,
					start 		: 1,
					display     : 5,
					border					: false,
					text_color  			: '#3a3a3a',
					background_color    	: 'none',	
					text_hover_color  		: '#2573AF',
					background_hover_color	: 'none', 
					rotate		: false,
					images		: false,
					mouse		: 'press',
					onChange: function(currval){				
						cambiarPaginaHistorialCanje(currval-1);
						desactivarBotones(currval);					
					}
				});
	
				$('.jPag-control-back, .jPag-control-front').css('opacity',0).css('cursor','default');
	
				
				if(pagTotal < 5){
					var ancho = (pagTotal * 30);	
					$('.ulwrapdiv').css('width',ancho+'px');				
				}else{
					ancho=150;				
				}			
				
				$('.jPag-backk').click(function(){
					$('.jPag-current').parent().prev().children('a').trigger('click');
				});
	
				$('.jPag-nextt').click(function(){
					$('.jPag-current').parent().next().children('a').trigger('click');
				});
	
				ancho2 = ancho+180;
				setTimeout('anchopaginador(ancho2);',1000);
				desactivarBotones(1);
				//-------------------------------------->
			}
	
			function desactivarBotones(actual){
				if(actual==pagTotal){
					$('.jPag-control-front').css('opacity',0).children('a').css('cursor','default');
					
				}else{
					$('.jPag-control-front').css('opacity',1).children('a').css('cursor','pointer');
				}
				if(actual==1){
					$('.jPag-control-back').css('opacity',0).children('a').css('cursor','default');
				}else{
					$('.jPag-control-back').css('opacity',1).children('a').css('cursor','pointer');
				}
			}
	
			function cambiarPaginaHistorialCanje(current){			
				$('.tr-historial-canje').remove();
				$('#table-historial-canje tbody').append(htmlLoading);
				var listaDetalleCurrent = listaPaginas[current].listaDetalle;
				$('#tr-total-puntos').remove();
				for(index in listaDetalleCurrent){
					var detalle = listaDetalleCurrent[index];
					$('#table-historial-canje tbody').append(htmlFilaHistorial);
					var nuevaFila = $('#table-historial-canje tbody .tr-historial-canje:last');
					if(index%2 == 0){
						nuevaFila.addClass('impar');
					}
					else{
						nuevaFila.addClass('par');
					}																	
					nuevaFila.find('.fecha-registro').html(detalle.fechaTransaccionStringFormat);
					nuevaFila.find('.puntos-registro').html(detalle.puntosTransaccionStringFormat);
					
					// Coloca el texto de la fila en negrita si la fila corresponde a abono promocional
					if (detalle.transaccionAbonoPromocional) {
						nuevaFila.css('font-weight', 'bold');											
					}
					
					nuevaFila.find('.desc-registro').html(detalle.descripcionTransaccion);
					nuevaFila.find('.tipo-registro').html(detalle.tipoTransaccion);
					nuevaFila.find('.saldo-registro').html(detalle.saldoTransaccionStringFormat);
				}
				if(tipo!=4){
	        		$('#table-historial-canje tbody').append(htmlTotalPuntos);
	        		$('#tr-total-puntos .total-puntos-pagina').text(listaPaginas[current].totalPuntos);
	            }
				$('#tr-loading-historial-canje').remove();			
			}
			
			$(document).ready(function() {
				$("select[id*=selectTipo]")[0].setValue('4');
				tipo = 4;
				anio = $($("select[id*=selectAnio]")[0]).val();
				loadTablaHistorialCanje();
					
				$('#boton-filtrar-historial-canje').click(function(){
					if(puedeBusqueda){
						tipo = $($("select[id*=selectTipo]")[0]).val();
						anio = $($("select[id*=selectAnio]")[0]).val();
						loadTablaHistorialCanje();
					}				
				});
	
				/*
				$('.span-cabecera-expiracion').each(function(i, e){
					var texto = $(e).text();
					if(parseInt(texto.length) > 16){
						$(e).parents('th').css('padding','9px 0px 9px 0px');
					}
				});
				*/
	
				$('.span-cabecera-expiracion').each(function(i, e){
					var texto = $(e).text();
					var arrTexto = texto.split(" ");
					$(e).html(arrTexto[0]+" "+arrTexto[1]+"<br/>"+arrTexto[2]);	
					$(e).parents('th').css('padding','7px 0px 7px 0px');			
				});
			});
	
			function anchopaginador(ancho){
				$('.jPaginate').css('width',ancho+'px').css('margin','0 auto');
			}
	
			var htmlMensajeHistorial = "<div id='mensaje-historial' class='mensaje-alerta-sistema-pequeno' style='width:400px;margin:20px auto;'>"+
									        "<div class='clearfix sub-contenedor'>"+
									        "<div class='contenedor-imagen'>"+
									        	"<div class='imagen'></div>"+
									        "</div>"+
									        "<div class='texto'></div>"+
									    "</div>"+
									"</div>";
			var htmlTotalPuntos = "<tr id='tr-total-puntos' class='impar nuevaTotal'>"+
										"<td class='bloquearDerecha' style='width: 110px;'><span class='letraTotal'>Total</span></td>"+
										"<td class='bloquearDerecha' style='width: 70px;'><span class='total-puntos-pagina letraTotal'></span></td>"+
										"<td class='bloquearColumna' style='width: 118px;'>&nbsp;</td>"+
										"<td class='bloquearColumna' style='width: 110px;'>&nbsp;</td>"+
										"<td class='bloquearDerecha' style='width: 70px;'>&nbsp;</td>"+
									"</tr>";
			var htmlLoading = "<tr id='tr-loading-historial-canje'>"+
								"<td id='td-cargando' colspan='5' style='text-align:center'><br><img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/></b><br><br></td>"+
								"</tr>";
	
			var htmlFilaHistorial = '<tr class="tr-historial-canje nuevaTabla">'+
										'<td class="fecha-registro bloquearDerecha" style="width: 110px; padding-left:0;"></td>'+
										'<td class="puntos-registro bloquearDerecha" style="width: 70px; padding-left:0;"></td>'+
										'<td class="desc-registro bloquearColumna" style="width: 118px;"></td>'+
										'<td class="tipo-registro bloquearColumna" style="width: 110px;"></td>'+
										'<td class="saldo-registro bloquearDerecha" style="width: 70px; padding-left:0;"></td>'+
									'</tr>';
			var htmlPaginador = '<div id="paginador-historial-canje" style="margin: auto; margin-top:20px;width: 330px;"></div>';
			var listaPaginas;
			
			function loadTablaHistorialCanje(){			
		    	
				var url='<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/obtenerDetalleCanjeJson.faces';						
				
			    $('.tr-historial-canje, #tr-total-puntos, #paginador-historial-canje, #mensaje-historial').remove();		    
			    $('#table-historial-canje tbody').append(htmlLoading);		    
	
			    puedeBusqueda = false;
			     
				$.ajax({
			            type: 'POST',
			            url: url,
			            dataType: 'json',
			            data: {filtroTipo:tipo, filtroAnio:anio, descAbonoPromocional:'${descAbonoPromocional}'},
			            success: function (resp){	
				            if(resp.estado == 'Ok'){ 
				            	if(resp.respuesta.length !=0 ){
									listaPaginas = resp.respuesta;
									pagTotal = listaPaginas.length;
									var listaDetallePrimera = listaPaginas[0].listaDetalle;
									for(index in listaDetallePrimera){
										var detalle = listaDetallePrimera[index];
										$('#table-historial-canje tbody').append(htmlFilaHistorial);
										var nuevaFila = $('#table-historial-canje tbody .tr-historial-canje:last');
										if(index%2 == 0){
											nuevaFila.addClass('impar');
										}
										else{
											nuevaFila.addClass('par');
										}																	
										nuevaFila.find('.fecha-registro').html(detalle.fechaTransaccionStringFormat);
										nuevaFila.find('.puntos-registro').html(detalle.puntosTransaccionStringFormat);

										// Coloca el texto de la fila en negrita si la fila corresponde a abono promocional
										if (detalle.transaccionAbonoPromocional) {
											nuevaFila.css('font-weight', 'bold');											
										}

										nuevaFila.find('.desc-registro').html(detalle.descripcionTransaccion);
										nuevaFila.find('.tipo-registro').html(detalle.tipoTransaccion);
										nuevaFila.find('.saldo-registro').html(detalle.saldoTransaccionStringFormat);
									}
									
									$('#tr-loading-historial-canje').remove();
									$('#div-footer-tabla-historial').append(htmlPaginador);
									paginarHistorial();
	
									if(tipo!=4){
					            		$('#table-historial-canje tbody').append(htmlTotalPuntos);
					            		$('#tr-total-puntos .total-puntos-pagina').text(listaPaginas[0].totalPuntos);
						            }
									
						        }
				            	else{
				            		$('#div-footer-tabla-historial').append(htmlMensajeHistorial);
				            		$('#tr-loading-historial-canje').remove();			            		
					            	$('#mensaje-historial .texto').html('No hay Informaci&oacute;n Hist&oacute;rica Disponible.');
					            }
					        }		            
				            else{
				            	$('#div-footer-tabla-historial').append(htmlMensajeHistorial);
				            	$('#tr-loading-historial-canje').remove();
				            	$('#mensaje-historial .texto').html(resp.detalle);
					        }
	
					        puedeBusqueda = true;           
			           }
		        });
	
			}
			
		</script>
		
		<div class="margen clearfix" id="pasos" style="margin-bottom:20px">
			
			<span class="flotar_der categoriaCli">
				Cliente Categoría 
				<strong><h:outputText value="#{vtasYMktgFidelizacionCartolaController.categoriaClientePuntos}" /></strong> 
				<a class="verEnlace" target="_blank" href="http://www.entel.cl/zona_puntos/">(Ver más)</a>
			</span>			
	    </div>
	
		<div class="clearfix" style="border-top: #E3EDF7 solid 1px; border-bottom: #E3E3E3 solid 1px; padding: 5px 0px;">
			<div style="width: 200px; float: left;">
				<span class="tituloNuevaTabla">Puntos Acumulados:</span>
			</div>
			<div style="width: 100px; float: left;">
				<span class="infoNuevaTabla">
					<h:outputText value="#{vtasYMktgFidelizacionCartolaController.detallePuntos.saldoPuntos}">
						<f:convertNumber currencyCode="CLP" locale="es" />
					</h:outputText>
				</span>
			</div>
			<div style="float: left;">
				<span class="descNuevaTabla">
					al <h:outputText value="#{vtasYMktgFidelizacionCartolaController.detallePuntos.fechaActPuntos}">
							<f:convertDateTime pattern="dd/MM/yyyy"/>
						</h:outputText> 
				</span>
			</div>
		</div>
		
		<div class="clearfix" style="margin-top: 15px;">
			<span class="tituloNuevaTabla">Puntos por Vencer:</span>
		</div>
		
		<div class="caja_historial bloquedaAltura clearfix">
			<div class="caja_historial_top"></div>
			<div class="caja_historial_centro">
				
				<f:verbatim rendered="#{vtasYMktgFidelizacionCartolaController.sizeExpiracion == 0}">
					<div class="mensaje-alerta-sistema-pequeno" style="width:400px;margin:20px auto;">
						<div class="clearfix sub-contenedor">
							<div class="contenedor-imagen">
								<div class="imagen"></div>
							</div>
							<div class="texto">No hay informaci&oacute;n de puntos por vencer.</div>
						</div>
					</div>					
				</f:verbatim>			
				
				<f:verbatim rendered="#{vtasYMktgFidelizacionCartolaController.sizeExpiracion > 0}">
					<table class="tabla-azul nueva">
					<thead>
						<tr class="cabecera">
							<it:iterator var="expiracion" value="#{vtasYMktgFidelizacionCartolaController.listaExpiracion}" rowIndexVar="index">
								<f:verbatim rendered="#{index < vtasYMktgFidelizacionCartolaController.sizeExpiracion}">
									<th style="width: 88px;">
										<span class="span-cabecera-expiracion"><strong><h:outputText value="#{expiracion.periodoVencimiento}"/></strong></span>
									</th>
								</f:verbatim>
								
								<f:verbatim rendered="#{index == vtasYMktgFidelizacionCartolaController.sizeExpiracion}">
									<th class="final" style="width: 88px;">
										<span class="span-cabecera-expiracion"><strong><h:outputText value="#{expiracion.periodoVencimiento}"/></strong></span>
									</th>
								</f:verbatim>								
								
							</it:iterator>							
						</tr>
					</thead>
					<tbody>
						<tr class="impar">
							<it:iterator var="expiracion" value="#{vtasYMktgFidelizacionCartolaController.listaExpiracion}" rowIndexVar="index">
								<td style="width: 88px;"><h:outputText value="#{expiracion.puntosStringFormat}"/></td>
							</it:iterator>							
						</tr>
					</tbody>
				</table>				
				</f:verbatim>			
									
			</div>
			<div class="caja_historial_bottom"></div>
		</div>
		
		<!-- Tabla zonapuntos -->
		<h:panelGroup>	
				
			<div class="caja_historial clearfix">
				<div class="caja_historial_top"></div>
				<div class="caja_historial_centro">	
					
					<div class="clearfix">
						<span class="tituloNuevaTabla">Cartola Histórica</span>
					</div>
					
					<h:form>
						<jsp:include page="/token.jsp" flush="true"/>
						<div class="clearfix" style="padding: 5px 0px 20px 0px;">
							<div style="width: 50px; float: left; padding-left: 20px; padding-top: 3px;">
								<span class="infoNuevaTabla recortado">Año</span>
							</div>
							<div style="width: 150px; float: left; padding-top: 3px;">
								<h:selectOneMenu id="selectAnio" styleClass="selectAnio selectBox" style="width: 130px; display: none; ">									
									<f:selectItems value="#{vtasYMktgFidelizacionCartolaController.aniosHistorialList}"/>
								</h:selectOneMenu>
							</div>
							<div style="width: 50px; float: left; padding-left: 0px; padding-top: 3px;">
								<span class="infoNuevaTabla recortado">Tipo</span>
							</div>
							<div style="width: 150px; float: left; padding-top: 3px;">
								<h:selectOneMenu id="selectTipo" styleClass="selectTipo selectBox" style="width: 130px; display: none; ">
									<f:selectItems value="#{vtasYMktgFidelizacionCartolaController.tiposHistorialList}"/>
								</h:selectOneMenu>
							</div>
							<div style="float: left;">
								<a id="boton-filtrar-historial-canje" class="btnAzulLargo">
									<span>Ver</span>
								</a>
							</div>
						</div>
					</h:form>
					
					<div id="divTablaHistorialCanje">
					
						<table id="table-historial-canje" class="tabla-azul nueva">
							<thead>
								<tr class="cabecera">
									<th style="width: 120px;"><strong>Fecha</strong></th>
									<th style="width: 80px;"><strong>Puntos</strong></th>
									<th style="width: 128px;"><strong>Descripción</strong></th>
									<th style="width: 120px;"><strong>Tipo</strong></th>
									<th class="final" style="width: 80px;"><strong>Saldo</strong></th>
								</tr>
							</thead>
							<tbody class="alterada">
																
							</tbody>
							
						</table>
						
						<div id="div-footer-tabla-historial">
							
						</div>						
					
					</div>
					
					
				</div>
				<div class="caja_historial_bottom"></div>
			</div>
		
		</h:panelGroup>
		<!--/tabla zonapuntos-->
	</entel:view>

</f:view>