<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it"  uri="/WEB-INF/tld/IteratorTag.tld"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>


<cm:search id="avisoCambioBolsa" query="idContenido = 'avisoCambioBolsa'" useCache="true"  />
<cm:search id="confirmacionCambioBolsa" query="idContenido = 'confirmacionCambioBolsa'" useCache="true"  />
<cm:search id="marcaBolsasIMVoz" query="idContenido = 'marcaBolsasIMVoz'" useCache="true"  />

<f:view beforePhase="#{internetMovilController.init}">
	<script type="text/javascript">
	
	function contratarBolsa(link,codBolsa,nombreBolsa,valor,tipo){
	        var url='<%=request.getContextPath()%>/portlet/internetmovil/miPlanInternetMovilJson.faces';
	        
	        //Mostrar loading
	        var tr = $('.link'+link).parents(".lista-bolsas:first");
	        tr.find('.paso_2').html("<img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;<b>Enviando...</b>");
	        tr.find('.paso_2').show();      
	        
		    $.ajax({
		            type: 'POST',
		            url: url,
		            async: false, 
		            dataType: 'json',
		            data: {accion:'contratar',codigoBolsa:codBolsa,nombreBolsa:nombreBolsa,valorBolsa:valor,tipoBolsa:tipo},
		            success: function (resp){
		             if(resp.estado == 'Ok'){
		            	 tr.find('.paso_2').hide();
			             //tr.find('.paso_3').html("<h5>Haz cambiado a esta bolsa.</h5><p>"+resp.respuesta+"</p>");
			             tr.find('.paso_3').show();
			             
			             crearMarcaTransaccionGTM(resp);
	                 }else{
	                	 $('.mensajeError'+link).html(resp.detalle);
	                	 $('.mensajeErrorP'+link).show();
	                     tr.find('.paso_2').hide();
	                     }
		            }
		        });
	}

	function crearMarcaTransaccionGTM(resp) {
		var idTransaccion = resp.respuesta.idTransaccion;
		var codigoProducto = resp.respuesta.skuID;
		var nombreProducto = resp.respuesta.nombre;

		mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Internet movil/Contrata tu bolsa/Conversion');

		mxTracker._addTrans(idTransaccion, '', resp.respuesta.valorTransaccion);
		mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Fee plan', resp.respuesta.nuevoValor, '1');
		mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Costo cambio plan', resp.respuesta.costoOperacional, '1');

		dataLayer = dataLayer||[];
		dataLayer.push({'event': 'tracktrans', 'tracktrans': true});
	}	
	
	</script>
	<h1>Internet m&oacute;vil</h1>
    <div id="marca-adwords"></div>
	<!-- MENSAJES -->
	<jsp:include page="../common/messages_table.jsp"></jsp:include>

	<!-- ESTRUCTURA TARIFARIA -->
	<h:panelGroup rendered="#{! (empty internetMovilController.bolsasADBean.bolsasActuales)}">
	<div class="estructuraTarifaria">
		<h2 class="superchip">			
			<strong>Mi Bolsa:</strong>
                  <it:iterator value="#{internetMovilController.bolsasADBean.bolsasActuales}" var="bolsaAct" rowIndexVar="bolsaIndex">
                  <h:outputText rendered="#{bolsaIndex == 0}" value="#{bolsaAct.nombreBolsa}"/>
                  </it:iterator>                     
		</h2>
		
		<!-- Tabla Tarificacion -->
		<div class="header_tabla clearfix">
			<div class="top"><span></span></div>
			<div class="main">
				<table>
					<tr>
						<th width="130">Internet m&oacute;vil</th>
						<th width="110">Precio bolsa</th>
						<!--  <th width="110">MB Incluidos</th> -->
						<th width="110" class="ultimo">Estado</th>
					</tr>
				</table>					
			</div>
			<div class="bottom"><span></span></div>
		</div>
		<div class="contenido_tabla">

			<table class="tablaFacturacion">
				<tbody>
				<it:iterator value="#{internetMovilController.bolsasADBean.bolsasActuales}" var="bolsaActual">
					<tr>
						<td width="130"><h:outputText value="#{bolsaActual.nombreBolsa}"/></td>
						<td width="110">$<h:outputText value="#{bolsaActual.costo}">
						<f:convertNumber currencyCode="CLP" locale="es" />
						</h:outputText></td>
						<!-- 
						<td width="110">
						<h:outputText value="-" rendered="#{bolsaActual.estado == 'PA' || bolsaActual.estado == 'PD'}"/>
						<h:outputText value="#{bolsaActual.cantidad > internetMovilController.cantidadMbIlimitado ? 'Ilimitado' : bolsaActual.cantidad}"/>
						</td>
						-->
						<td width="110">
						 <h:outputText value="Activa" rendered="#{bolsaActual.estado == 'A'}"/>
					  	 <h:outputText escape="false" value="Pendiente Activaci&oacute;n" rendered="#{bolsaActual.estado == 'PA'}"/>
					  	 <h:outputText escape="false" value="Pendiente Desactivaci&oacute;n" rendered="#{bolsaActual.estado == 'PD'}"/>
					  	</td>
					</tr>
                </it:iterator>
				</tbody>
			</table>
		</div>
	</div>
	<div class="disclaimer">
	    <it:iterator value="#{bolsaActual.descBolsa}" var="strDescBolsa">
		 <h:outputText value="#{strDescBolsa}"/></br>
              </it:iterator>
	</div>		
	<!-- /ESTRUCTURA TARIFARIA -->
	</h:panelGroup>
	
	<h:panelGroup rendered="#{(empty internetMovilController.bolsasADBean.bolsasActuales)}">
	<!-- ESTRUCTURA TARIFARIA -->
	<div class="estructuraTarifaria">				
		<cm:search id="avisoNoPlanMovil" query="idContenido = 'avisoNoPlanMovil'" useCache="true"  />
		<h2 class="superchip">
			<cm:getProperty node="${avisoNoPlanMovil[0]}" name="titulo" />
		</h2>				
		<!-- Aviso no plan -->
		<div class="aviso_no_plan clearfix">
			<cm:getProperty node="${avisoNoPlanMovil[0]}" name="html" />
		</div>
	</div>
	<!-- /ESTRUCTURA TARIFARIA -->
	</h:panelGroup>
			
			
	<div class="separacion_planes"></div>            

	<h2 class="superchipOK"><h:outputText value="#{(empty internetMovilController.bolsasADBean.bolsasActuales) ? 'Contrata tu bolsa' : 'Cambio de bolsa'}"/></h2>
	
	<h:panelGroup rendered="#{! (empty internetMovilController.bolsasADBean.bolsasDisponibles)}">	
    
	    <p>Selecciona de la siguiente lista de planes que tenemos para ti a cual deseas cambiarte:</p>
	    
	    <!-- Caja amarilla -->
	    <cm:search id="sinPermiso" query="idContenido = 'sinPermisoContPlan'" useCache="true"  />
		<h:panelGroup layout="block" rendered="#{profile.aaa != miEntelBean.AAATitular && profile.aaa != miEntelBean.AAAControlTotal}" styleClass="caja amarilla margen">
			<h6><strong><cm:getProperty node="${sinPermiso[0]}" name="titulo" /></strong></h6>
			<p><cm:getProperty node="${sinPermiso[0]}" name="html" /></p>
		</h:panelGroup>
		<!-- /Caja amarilla -->		    		   
		
		<div id="menu-desplegable-planes"><!-- bolsa -->
		<it:iterator value="#{internetMovilController.bolsasADBean.bolsasDisponibles}" var="bolsaDispo" rowIndexVar="bolsaIndex">
           	<div class="bolsa <h:outputText value="#{bolsaIndex % 2 == 0 ? 'par' : 'impar'}"/> clearfix">
	         	<div class="header">
	         		<a href="javascript:;" class="cerrado" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Internet movil/Contrata tu bolsa/Desplegar');"><h:outputText value="#{bolsaDispo.nombreBolsa}"/></a>
	        	</div>
		       	<div style="display: none;" class="lista-bolsas" id="red">
		
					<div class="tabla_precios">
					
						<div class="planes_header_tabla clearfix">
							<div class="top"><span></span></div>
							<div class="main">
								<table>
									<tbody><tr>
										<th width="120" class="primera">Nombre de la bolsa</th>
										<th width="70">Precio Bolsa</th>
										<!-- <th width="70">MB Incluidos</th>-->
										<th width="150" class="ultimo">&nbsp;</th>
									</tr>
								</tbody></table>					
							</div>
							<div class="bottom"><span></span></div>
						</div>
						
						<table class="planes_contabla">
							<tbody>
								<tr>
									<td width="120" class="primera"><h:outputText value="#{bolsaDispo.nombreBolsa}"/></td>
									<td width="70">$<h:outputText value="#{bolsaDispo.costo}">
									<f:convertNumber currencyCode="CLP" locale="es" />
				 					</h:outputText></td>
									<!-- <td width="70"><h:outputText value="#{bolsaDispo.cantidad > internetMovilController.cantidadMbIlimitado ? 'Ilimitado' : bolsaDispo.cantidad}"></h:outputText></td>-->
									<td width="150">
										<entel:view name="contratarPlanIM">
											<a class="<h:outputText value="#{(empty internetMovilController.bolsasADBean.bolsasActuales) ? 'btnContratarBolsa' : 'btnCambiarmePlan'}"/> cambiarPlan" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Internet movil/Contrata tu bolsa/Contratar');"></a>
										</entel:view>
										<entel:view name="contratarPlanIM" inverse="true">
											<div class="mensaje">
												Deshabilitado
												<br>
												Solo el titular o el administrador de la cuenta puede cambiar el estado de la cuenta.
												<br>
											</div>
										</entel:view>							
																	
									</td>
								</tr>
							</tbody>
						</table>
						<div class="disclaimer">
							 <it:iterator value="#{bolsaDispo.descBolsa}" var="strDescBolsa">
							 <h:outputText escape="false" value="#{strDescBolsa}"/>
			                </it:iterator>
						</div>
					</div><!--/tabla_precios-->
				
					<div class="paso_2">
						<div class="nombre_plan">
							<div class="nombre_plan_top"><span></span></div>
							<div class="nombre_plan_main">Nombre de la bolsa</div>
							<div class="nombre_plan_bottom"><span></span></div>
						</div>
						
						<h:panelGroup rendered="#{empty internetMovilController.bolsasADBean.bolsasActuales}" layout="block" styleClass="paso_confirmar clearfix">
							<span class="plan"><h:outputText value="#{bolsaDispo.nombreBolsa}"/></span>
							<span style="padding-right:10px;">Vas a contratar esta bolsa</span>
							<a class="cancelar" href="#">Cancelar</a>
							<a onclick="contratarBolsa('<h:outputText value="#{bolsaIndex}"/>','<h:outputText value="#{bolsaDispo.snCodigo}"/>','<h:outputText value="#{bolsaDispo.nombreBolsa}"/>','<h:outputText value="#{bolsaDispo.costo}"/>','<h:outputText value="#{bolsaDispo.tipoBolsa}"/>');" class="btnVerdeDelgado link<h:outputText value="#{bolsaIndex}"/>" href="javascript:void(0);"><span>Confirmar</span></a>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{! (empty internetMovilController.bolsasADBean.bolsasActuales)}" layout="block" styleClass="paso_confirmar con_bolsas clearfix">
							<p><span class="plan"><h:outputText value="#{bolsaDispo.nombreBolsa}"/></span></p>				
							
							<p>
								<cm:getProperty node="${confirmacionCambioBolsa[0]}" name="titulo"/>
								<h:outputText styleClass="negrilla-span" value="#{bolsaDispo.nombreBolsa}"/></br>
								<cm:getProperty node="${confirmacionCambioBolsa[0]}" name="html"/>					
							</p>								
																								
							<ul>
								<it:iterator value="#{internetMovilController.bolsasADBean.bolsasActuales}" var="bolsaAct">
			                    	<f:verbatim rendered="#{bolsaAct.estado == 'A'}">
			                    		<li><h:outputText value="#{bolsaAct.nombreBolsa}"/></li>
			                    	</f:verbatim>                    	
			                    </it:iterator> 									
							</ul>	
														
							<cm:getProperty node="${avisoCambioBolsa[0]}" name="html"/>		
							
							<a class="cancelar" href="#">Cancelar</a>
							<a onclick="contratarBolsa('<h:outputText value="#{bolsaIndex}"/>','<h:outputText value="#{bolsaDispo.snCodigo}"/>','<h:outputText value="#{bolsaDispo.nombreBolsa}"/>','<h:outputText value="#{bolsaDispo.costo}"/>','<h:outputText value="#{bolsaDispo.tipoBolsa}"/>');" class="btnVerdeDelgado link<h:outputText value="#{bolsaIndex}"/>" href="javascript:void(0);"><span>Confirmar</span></a>
						</h:panelGroup>
						
						<!-- 
						<div class="disclaimer">
							 <it:iterator value="#{bolsaDispo.descBolsa}" var="strDescBolsa">
							 <h:outputText escape="false" value="#{strDescBolsa}"/>
			                </it:iterator>
						</div>
						-->
					</div><!--/paso_2-->
				
					<div class="paso_3">
						<div class="nombre_plan">
							<div class="nombre_plan_top"><span></span></div>
							<div class="nombre_plan_main">Nombre de la bolsa</div>
							<div class="nombre_plan_bottom"><span></span></div>
						</div>
						<div class="paso_confirmar clearfix">
							<span class="plan"><h:outputText value="#{bolsaDispo.nombreBolsa}"/></span>
							<div>
								<h5>Has cambiado a esta bolsa</h5>
								<div>
									- Tu solicitud ha sido recibida.
									<br>
									- El cambio se efectuará el día de mañana.
								</div>
							</div>
						</div>
						<div class="disclaimer">
							 <it:iterator value="#{bolsaDispo.descBolsa}" var="strDescBolsa">
							 <h:outputText escape="false" value="#{strDescBolsa}"/>
			                 </it:iterator>
						</div>
					</div><!--/paso_3-->
				</div>
			</div>
		    <div class="mensaje-error-pequeno mensajeErrorP<h:outputText value="#{bolsaIndex}"/>" style="display:none;">
			    <div class="clearfix sub-contenedor">
			    	<div class="contenedor-imagen">
			       		<div class="imagen"></div>
			     	</div>
			    	<div class="texto mensajeError<h:outputText value="#{bolsaIndex}"/>"></div>
			    </div>
		    </div>
		<!-- /bolsa -->
		</it:iterator>
		<!-- /bolsa -->
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			initMenuDesplegablePlanes();
		});
	</script>
	</h:panelGroup>
	<h:panelGroup rendered="#{empty internetMovilController.bolsasADBean.bolsasDisponibles}">
		<div class="mensaje-alerta-sistema-pequeno">
			<div class="clearfix sub-contenedor">
				<div class="contenedor-imagen">
					<div class="imagen"></div>
				</div>
				<div class="texto">No hay Planes Disponibles.</div>
			</div>
		</div>
	</h:panelGroup>			
</f:view>
