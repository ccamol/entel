<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content"%>

<f:view beforePhase="#{bolsasBAMCCPPController.init}">

	<c:set var="esSAPC" value="${bolsasBAMCCPPController.esSAPC}" />

	<cm:search id="descripcionServicio" query="idContenido = 'descripcionServicio'" useCache="true" />
	<cm:search id="mensajeMedioPago" query="idContenido = 'mensajeMedioPago'" useCache="true" />
	<cm:search id="mensajeExito" query="idContenido = 'mensajeExito'" useCache="true" />
	<cm:search id="infoMsjRecarga" query="idContenido = 'infoMsjRecarga'" useCache="true"  />

	<script type="text/javascript">
		var urlComprar = '<%=request.getContextPath()%>/portlet/bolsabam/bolsasBAMCCPPJson.faces';
	
		function comprarBolsa(link, codBolsa, nombreBolsa, valorBolsa) {
			var tipoCobro = $('input[name=radioPago]').val();
			
			$.ajax({
				type: 'POST',
				url: urlComprar, 
				dataType: 'json',
				data: {codBolsa:codBolsa, tipoCobro:tipoCobro, nombreBolsa:nombreBolsa, valorBolsa:valorBolsa},
				success: function(resp) {					
					if (resp.estado == 'Ok') {
						$(link).parents("div.paso3:first").hide();
						$(link).parents("div.paso3:first").next('div.paso4:first').show();
						crearMarcaTransaccionGTM(resp);
					} else {
						$('div.paso4 p').html('Servicio no disponible');
						$('div.paso4 div.paso_3 ').html('No se pudo completar la operaci&oacute;n.<br/> Por favor intente m&aacute;s tarde.');
						$(link).parents("div.paso3:first").hide();
						$(link).parents("div.paso3:first").next('div.paso4:first').show();
					}
				}
			});
			
					
		}

		function obtenerParametroURL(name) {		
			var regexS = "[\\?&]"+name+"=([^&#]*)";
			var regex = new RegExp(regexS);
			var tmpURL = window.location.href
			var results = regex.exec(tmpURL);
		
			if (results == null) {
				return "" ;
			} else {
				return results[1];
			}
		}		

		$(document).ready(function() {
			var param = obtenerParametroURL("tab");
			if (param == 'activas') {
				$('.tab.contenido1').removeClass('seleccionado');
				$('.contenido_tab.contenido1').css({'display': 'none'});
				$('.tab.contenido2').addClass('seleccionado');			
				$('.contenido_tab.contenido2').css({'display': 'block'});
			}
			$('div.linea_tabs').find('div.tab.seleccionado').trigger('click');
		});

		function crearMarcaTransaccionGTM(resp) {
			var idTransaccion = resp.respuesta.idTransaccion;
			var codigoProducto = resp.respuesta.skuID;
			var nombreProducto = resp.respuesta.nombre;
			
			mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Banda Ancha Movil/Bolsas/Comprar bolsas/Conversion');

			mxTracker._addTrans(idTransaccion, '', resp.respuesta.valorTransaccion);
			mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Fee tarifa', resp.respuesta.nuevoValor, '1');
			mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Costo cambio tarifa', resp.respuesta.costoOperacional, '1');

			var dataLayer = dataLayer||[];
            dataLayer.push({'event': 'tracktrans', 'tracktrans': true});
		}


	</script>
	
	<h2 class="bullet">Bolsas</h2>	
	
	<c:choose>
	<c:when test="${esSAPC == 'SAPC'}">
	
	<div class="linea_tabs clearfix">
		<div class="tab contenido1 seleccionado" style="display: block;" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Banda Ancha Movil/Bolsas/Comprar Bolsas');">
			<span class="contratar_bolsas">
				Comprar Bolsas
			</span>
		</div>
		<div class="tab contenido2" style="display: block;" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Banda Ancha Movil/Bolsas/Bolsas Compradas');">
			<span class="bolsas_compradas">
				Bolsas Compradas
			</span>
		</div>
	</div>
	
    <div class="contenido_tabs">
		<div class="contenido_tab contenido1" style="display: block;">
			<br/>		
			<div class="mi_saldo contratarBolsaPrepago clearfix" style="background-position: 20px 15px;">
				<div style="float:left; padding-top: 8px;">
					<strong>Saldo: $ <h:outputText value="#{bolsasBAMCCPPController.bolsasDisponibles.saldoPlanFormatted}" /></strong>
				</div>
				<div class="msjRecarga">
					<cm:getProperty node="${infoMsjRecarga[0]}" name="html" />
				</div>
				<div style="float:left;">
					<a class="botonRecargarNaranja" style="padding-left: 0" href="<r:pageUrl pageLabel='${bolsasBAMCCPPController.pageLabelRecarga}'></r:pageUrl>"></a>
				</div>
			</div>
			
			<p class="descripcion"><cm:getProperty node="${descripcionServicio[0]}" name="html" /></p>
			
			<!-- Mensaje para cuando no tiene bolsas disponibles -->
			<h:panelGroup rendered="#{fn:length(bolsasBAMCCPPController.bolsasDisponibles.bolsas) == 0}">
				<div class="caja amarilla margen">
					<h6><strong>No hay Bolsas Disponibles.</strong></h6>
				</div>
		    </h:panelGroup>
			
			<!-- Menu expandible de familias de bolsas -->
			<h:panelGroup rendered="#{fn:length(bolsasBAMCCPPController.bolsasDisponibles.bolsas) > 0}">
				<div id="menu-desplegable-planes-bolsas">
					<!-- bolsa -->
					<div class="lista-bolsas ccpp abierto" style="display:block">						
	            		<it:iterator var="bd" value="#{bolsasBAMCCPPController.bolsasDisponibles.bolsas}">
	                        <!-- BOlSA -->
							<!-- fila paso1-->
							<div class="fila paso1 clearfix" style="display: block;">
			                    <div class="nombre" style="padding-left: 15px;">
			                    	<a class="underline" style="text-decoration: underline; cursor: pointer;">
			                    		<p><h:outputText value="#{bd.nombre}" /></p>
		                    		</a>
	                    		</div>
			                    <div class="precio">
			                    	<p>$ <h:outputText value="#{bd.precioFormatted}" /></p>
		                    	</div>
								<h:panelGroup rendered="#{!bd.flagHabilitada}">
									<div class="contratar">
									    <div class="mensajeSaldoComprarBolsaPrepago">
									        <h:outputText value="#{bd.descripcionCargaSaldo}" />
									    </div>
								    </div>
								</h:panelGroup>
								<h:panelGroup rendered="#{bd.flagHabilitada}">
									<div class="contratar boton">				                    
				                    	<c:set var="habilitada" value="#{bd.flagHabilitada ? 'btnAzul contratarBolsa': 'btnDesactivado contratarBolsa'}" />
										<a class='<h:outputText value="#{habilitada}" />' onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Banda Ancha Movil/Bolsas/Comprar bolsas/Seleccionar');"><span>&nbsp;Seleccionar&nbsp;</span></a>
									</div>
								</h:panelGroup>
								<br /><br />
								<ul class="descripcion" style="display: none; font-size: 11px; padding-left: 15px; list-style: disc inside none; width: 260px">
                                	<li><h:outputText value="#{bd.descripcion}" /></li>
                           		</ul>								
    		                </div>
			                <!--/fila paso1-->
	                              
	                        <!-- fila paso2-->
							<div class="fila paso2 clearfix" style="display: none; background: none repeat scroll 0 0 #FFF1A8; border-bottom: 1px solid #CFC489; border-top: 1px solid #CFC489; padding-left: 15px;">
	                          	<div class="metodoPago" style="display: block; float: left; width: 435px;">
	                                <p><strong>Vas a contratar <h:outputText value="#{bd.nombre}" />, valor $<h:outputText value="#{bd.precioFormatted}" /></strong></p>
	                                <p><cm:getProperty node="${mensajeMedioPago[0]}" name="html" /></p>
	                                <fieldset>
	                                	<h:panelGroup rendered="#{bd.flagCargaSaldo}">
		                                    <input type="radio" name="radioPago" value="SALDO" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Banda Ancha Movil/Bolsas/Comprar bolsas/Cargo');">
		                                    <label>Cargo contra Recarga</label>
	                                    </h:panelGroup>
	                                	<h:panelGroup rendered="#{!bd.flagCargaSaldo}">
	                                		<input type="radio" name="radioPago" value="" disabled="disabled">
		                                    <label>Cargo contra Recarga (<h:outputText value="#{bd.descripcionCargaSaldo}" />)</label>
	                                    </h:panelGroup>	                                    
	                                </fieldset>
	                                <fieldset>
	                                	<h:panelGroup rendered="#{bd.flagCargaFactura}">
		                                    <input type="radio" name="radioPago" value="FACTURA" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Banda Ancha Movil/Bolsas/Comprar bolsas/Cargo');">
		                                    <label>Cargo contra Factura</label>
	                                    </h:panelGroup>
	                                	<h:panelGroup rendered="#{!bd.flagCargaFactura}">
	                                		<input type="radio" name="radioPago" value="" disabled="disabled">
		                                    <label>Cargo contra Factura (<h:outputText value="#{bd.descripcionCargaFactura}" />)</label>
	                                    </h:panelGroup>                                            
	                                </fieldset>
	                                <a class="continuarBolsa btnDesactivado" style="margin: 10px 0;"><span>&nbsp;Continuar&nbsp;</span></a>                                    
	                            </div>
	                            <div class="botones" style="display: block; float: left; width: 60px; padding-left: 30px;">
	                               <a class="cancelar cancelarBolsa">Cancelar</a>
	                            </div>
			                </div>
			                <!--/fila paso2-->
	                        
	                        <!-- fila paso3-->
							<div class="fila paso3 clearfix" style="display: none; background: none repeat scroll 0 0 #FFF1A8; border-bottom: 1px solid #CFC489; border-top: 1px solid #CFC489; padding-left: 15px;">
	                          	<div class="metodoPago" style="display: block; float: left; width: 435px;">
	                                <p><strong>Confirma tu compra:</strong></p>
	                                <ul style="list-style: disc inside none;">
	                                   	<li>Bolsa: <h:outputText value="#{bd.nombre}" /></li>
	                                    <li>Valor: $<h:outputText value="#{bd.precioFormatted}" /></li>
	                                    <li></li>
	                                </ul>
	                                <a class="btnVerde confirmarBolsa" onclick="javascript:comprarBolsa(this, '<h:outputText value="#{bd.codigo}" />', '<h:outputText value="#{bd.nombre}" />', '<h:outputText value="#{bd.precio}" />');"><span>&nbsp;Confirmar</span></a>                                    
	                            </div>
	                            <div class="botones" style="display: block; float: left; width: 60px; padding-left: 30px;">
	                                <a class="cancelar cancelarBolsa2">Cancelar</a>
	                            </div>
			                </div>
			                <!--/fila paso3-->
	                        
	                        <!-- fila paso4-->
							<div class="fila paso4 clearfix" style="background: none repeat scroll 0 0 #FFF1A8; border-bottom: 1px solid #CFC489; border-top: 1px solid #CFC489; padding-left: 15px;">
	                          	<div class="nombre"><a class="underline" style="text-decoration: underline; cursor: pointer;"><p><h:outputText value="#{bd.nombre}" /></p></a></div>
	                              <div class="contratar">
	                                  <div class="paso_3">
									      <cm:getProperty node="${mensajeExito[0]}" name="html" />
	                                  </div>
	                              </div>
			                </div>
			                <!--/fila paso4-->		                
	                        <!-- /BOLSA -->                                    		
						</it:iterator>
					</div>
					<!-- /bolsa -->				
				</div>
			</h:panelGroup>			
			<!-- /Menu expandible-->
		</div>
		
        <div class="contenido_tab contenido2" style="display: none;">	
			<table class="tabla-azul-ccpp">
                <tbody>
                    <tr class="cabecera">
                        <th width="173px" style="text-align: center">Bolsa</th>
                        <th width="89px" style="text-align: center">Saldo</th>
                        <!-- <th width="120px" style="text-align: center">Fecha Activacion</th> -->
                        <th width="120px" style="text-align: center">Fecha Expiración</th>
                    </tr>
			    </tbody>
			</table>
					
			<!-- Mensaje para cuando no tiene bolsas activas -->
			<h:panelGroup rendered="#{fn:length(bolsasBAMCCPPController.bolsasActivas.bolsas) == 0}">
				<div class="mi_saldo contratarBolsaPrepago" style="text-align: center; font-size: 12px;">
					No tienes bolsas activas
				</div>				
			</h:panelGroup>
			
			<h:panelGroup rendered="#{fn:length(bolsasBAMCCPPController.bolsasActivas.bolsas) > 0}">
	            <div class="bolsas-compradas" style="width: 557px;">            	
	            	<it:iterator var="ba" value="#{bolsasBAMCCPPController.bolsasActivas.bolsas}" rowIndexVar="row">
						<c:set var="style" value="#{row % 2 == 0 ? 'par': 'impar'}" scope="page" />
	           	    	<div class="fila <h:outputText value="#{style}"/> clearfix">
	                    	<div class="bolsa"><h:outputText value="#{ba.nombre}" /></div>
	                    	<div class="saldo"><h:outputText value="#{ba.saldoBolsa}" /></div>
	                    	<!-- <div class="activacion"><h:outputText value="#{ba.fechaActivacion}" /></div> -->
	                    	<div class="expiracion"><h:outputText value="#{ba.fechaExpiracion}" /></div>
	                    	<div id="listado" style="display: none; font-size: 11px; padding: 0 0 10px 12px; width: 100%; list-style: disc inside none;">
		                   		<ul>
		                        	<li><h:outputText value="#{ba.descripcion}" /></li>
		                    	</ul>
	                		</div>               		
	            		</div>
	            	</it:iterator>            	
	            </div>
            </h:panelGroup>	
        </div>				
    </div>
    </c:when>
    <c:otherwise>
    	<div class="contenedor-mensajes"> 
	        <ul class="mensajes-lista">			        			       
		        <h:outputText value="Tu plan actual no permite la compra de bolsas" styleClass="mensaje-error" ></h:outputText>				        
	        </ul> 
	    </div>
    </c:otherwise>
    
    </c:choose>
</f:view>