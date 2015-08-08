<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>

<!-- Contenidos -->
<cm:search id="encabezadoMcAfee" query="idContenido = 'encabezadoMcAfee'" useCache="false"/>
<cm:search id="pieDePaginaMcAfeePP" query="idContenido = 'pieDePaginaMcAfeePP'" useCache="false"/>
<cm:search id="msjContratarPlan" query="idContenido = 'msjContratarPlan'" useCache="false"/>
<cm:search id="msjDescargaSoft" query="idContenido = 'msjDescargaMcAfee'" useCache="false"/>
<cm:search id="msjFinalExito" query="idContenido = 'msjFinalExito'" useCache="false"/>
<cm:search id="msjFinalExitoActLic" query="idContenido = 'msjFinalExitoActLic'" useCache="false"/>
<cm:search id="msjSaldoUsrPP" query="idContenido = 'msjSaldoUsuariosPP'" useCache="false"/>
<cm:search id="msjPlanesDisp" query="idContenido = 'msjPlanesDisponibles'" useCache="false"/>
<cm:search id="msjVerificaEmail" query="idContenido = 'msjVerificacionEmail'" useCache="false"/>

<!-- Preferences -->
<pref:getPreference name="pageLabelRecargasBAM" var="pageLabelRecargasBAM" defaultValue="" />
<pref:getPreference name="urlPlanesSeguridadFAQ" var="urlPlanesSeguridadFAQ" defaultValue="" />



<f:view beforePhase="#{planesSeguridadBAMPPController.initCargarPlanesSeguridadBAM}">

	<script type="text/javascript">
	
	    function confirmarCompraPlan(){
	    	var url='<%=request.getContextPath()%>/portlet/mcafee/mcAfeePPComprarPlanJson.faces';
	    	var codigo = $.trim($('#codigoPlanSelected').val());
	    	var valor = $.trim($('#valorPlanSelected').val());
	    	var email = $.trim($('.email2').text());
	    	$(".loading_respuesta_compra").show();
	    	
		    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            data:{codigoPlan: codigo, valorPlan: valor, email: email},
	            success: function (resp){
		            $('.loading_respuesta_compra').hide();
	    	        if(resp.estado == 'Ok'){
	    	        	$(".caso_exito_final").show();
	    	        	$(".caso_paso2de2").hide();
	    	        	$('.fechaActivacion').html(resp.respuesta.fechaActivacionPlanFormatted + '.');
	    	        	$('.fechaVencimiento').html(resp.respuesta.fechaVencimientoPlanFormatted + '.');
	    	        	$('.nuevoSaldo').html(resp.respuesta.detallePlan);
	    	        	$('.urlDescargaPlanSegBAM').html('<a href="'+ resp.respuesta.urlDescarga +'">direcci&oacute;n</a> ');
	    	        	
	    	        	crearMarcaTransaccionGTM(resp);
	    	        }else{
	    	        	$('#texto-compra-plan-fracaso').html(resp.detalle);
	    	        	$('#compra-plan-fracaso').show();
	    	        	$('.botones_caso_paso2de2').hide();
	    	        	$('.contenedor_condiciones').hide();
	    	        	$('.enlace_caso_paso2de2').show();
		    	    }
	            }
			});
	    };

	    function actualizarEmail(email){
	    	var url='<%=request.getContextPath()%>/portlet/mcafee/mcAfeeActualizarEmailJson.faces';
	    	
		    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            data:{email: email},
	            success: function (resp){
	    	        if(resp.estado == 'Ok'){
	    	        	$('#texto-actualizar-email-exito').html(resp.detalle);
	    	        	$('#actualizar-email-exito').show();
	    	        	setTimeout("$('#actualizar-email-exito').hide();",2000);
	    	        	return true;
	    	        }else{
	    	        	$('#texto-actualizar-email-fracaso').html(resp.detalle);
	    	        	$('#actualizar-email-fracaso').show();
	    	        	setTimeout("$('#actualizar-email-fracaso').hide();",2000);
	    	        	return false;
		    	    }
	            }
			});
	    };

		function crearMarcaTransaccionGTM(resp) {
			var idTransaccion = resp.respuesta.transaccionGTM.idTransaccion;
			var codigoProducto = resp.respuesta.transaccionGTM.skuID;
			var nombreProducto = resp.respuesta.transaccionGTM.nombre;

			mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Banda Ancha Movil/McAfee Antivirus/' + nombreProducto + '/Conversion');

			mxTracker._addTrans(idTransaccion, '', resp.respuesta.valorTransaccion);
			mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Fee plan', resp.respuesta.nuevoValor, '1');
			mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Costo cambio plan', resp.respuesta.costoOperacional, '1');

			dataLayer = dataLayer||[];
			dataLayer.push({'event': 'tracktrans', 'tracktrans': true});
		}
	</script>

	<!-- centro -->
	<div>
	                	
		<div class="tabla_inicial clearfix">
			
		    <div class="tabla_inicial clearfix">
		        <h1><cm:getProperty node="${encabezadoMcAfee[0]}" name="titulo"/></h1>
		        <div class="contenido_encabezado clearfix">
		            <div class="clearfix">
		                <span class="encabezado_imagen">
		                	<cm:getProperty node="${encabezadoMcAfee[0]}" name="html"/>
		                </span>
		                <div class="logo_mcafee"></div>
		            </div>								
		        </div>
		    </div>
		    
		    <h:panelGroup rendered="#{planesSeguridadBAMPPController.tienePlanActual}">
			    <div class="estructuraTarifaria">
					<h2 class="superchip">
						<strong>Plan actual:</strong>
						<h:outputText value="#{planesSeguridadBAMPPController.planActual.nombrePlan}"/>.
					</h2>
					
					<!-- Tabla Tarificacion -->
					<div class="header_tabla clearfix">
						<div class="top"><span></span></div>
						<div class="main">
							<table>
								<tbody><tr>
									<th width="134">Plan</th>
									<th width="114">Fecha de compra</th>
									<th width="114">Fecha de vencimiento</th>
								</tr>
							</tbody></table>					
						</div>
						<div class="bottom"><span></span></div>
					</div>
					<div class="contenido_tabla">
						<table class="tablaFacturacion">
							<tbody>
								<tr>
									<td width="134">
										<h:outputText value="#{planesSeguridadBAMPPController.planActual.nombrePlan}"/>
									</td>
									<td width="114">
										<h:outputText value="#{planesSeguridadBAMPPController.planActual.fechaActivacionPlan}">
											<f:convertDateTime pattern="dd/MM/yyyy"/>
										</h:outputText>
									</td>
									<td width="114">
										<h:outputText value="#{planesSeguridadBAMPPController.planActual.fechaVencimientoPlan}">
											<f:convertDateTime pattern="dd/MM/yyyy"/>
										</h:outputText>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="disclaimer">
					(*) <cm:getProperty node="${msjDescargaSoft[0]}" name="titulo"/> <a href="<h:outputText value="#{planesSeguridadBAMPPController.urlDescarga}"/>">aqu&iacute;</a>, <cm:getProperty node="${msjDescargaSoft[0]}" name="html"/>
					(**) <a href="${urlPlanesSeguridadFAQ}" class="enlace_listado inline" target="_blank">Revisa las Preguntas Frecuentes</a>
				</div>
		    </h:panelGroup>
			
			<h:panelGroup rendered="#{planesSeguridadBAMPPController.hayPlanesDisponibles}">
				<!-- Tabla Azul -->
		        <div id="tabla_azul_divs" class="clearfix">
	        		<div class="tabla_cabecera clearfix">
		           		<div class="columna columna1 primero">Plan</div>
						<div class="columna columna2 ultimo">Valor</div>
		       		</div>
					<it:iterator var="plan" value="#{planesSeguridadBAMPPController.planesDisponiblesPP}" rowIndexVar="index">
						<div class="divs_fila clearfix">
							<div class="clearfix">
								<div class="columna columna1">
									<span class="contenido_tabla"><h:outputText value="#{plan.nombrePlan}"/></span>
									<div class="paddiner">
										<a href="#contenido<h:outputText value="#{plan.codigoPlan}" />" class="ico_interrogacionNuevo autoTooltip ext" tooltip="Primer tooltip"></a>
									</div>
								</div>
								<div class="columna columna2">
									<h:panelGroup rendered="#{plan.valorPlan != 0}">
										<div class="dato_tabla_mcafee">
				                            $<h:outputText value="#{plan.valorPlan}" converter="planConverter"/>
				                        </div>
									</h:panelGroup>
			                        <div class="botones_tabla">
			                        	<h:panelGroup rendered="#{planesSeguridadBAMPPController.planActualActivo}">
			                        		<div class="btnDesactivado"><span><h:outputText value="#{plan.textoBoton}"/></span></div>
			                        	</h:panelGroup>
			                        	<h:panelGroup rendered="#{!planesSeguridadBAMPPController.planActualActivo}">
			                        		<a href="#" class="btnAzul caso_normal" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Banda Ancha Movil/McAfee Antivirus/<h:outputText value="#{plan.nombrePlan}"/>/<h:outputText value="#{plan.textoBoton}"/>');"><span><h:outputText value="#{plan.textoBoton}"/></span></a>
			                        	</h:panelGroup>
			                        </div>
								</div>
							</div>
							<input type="hidden" id="valorPlan" value="<h:outputText value="#{plan.valorPlan}"/>" />
							<input type="hidden" id="codigoPlan" value="<h:outputText value="#{plan.codigoPlan}"/>" />
							<input type="hidden" id="saldoSuficiente" value="<h:outputText value="#{plan.saldoSuficiente}"/>" />
						</div>
					</it:iterator>
					
					<input type="hidden" id="valorPlanSelected" value="" />
					<input type="hidden" id="codigoPlanSelected" value="" />
					
	        	</div>
	        	<div class="tabla_inicial clearfix">
		        	<div class="contenido_pie_pagina clearfix">
			        	<div class="clearfix">
			            	<div class="pie_pagina_imagen">
			            		<cm:getProperty node="${pieDePaginaMcAfeePP[0]}" name="html"/>
							</div>
		                </div>								
		            </div>
		        </div>
	        </h:panelGroup>
	        <h:panelGroup rendered="#{!planesSeguridadBAMPPController.hayPlanesDisponibles}">
	       		<div class="mensaje-alerta-sistema-pequeno">
			        <div class="clearfix sub-contenedor">
			            <div class="contenedor-imagen">
			            	<div class="imagen"></div>
			            </div>
			            <div class="texto"><cm:getProperty node="${msjPlanesDisp[0]}" name="html"/></div>
			        </div>
			    </div>
	       	</h:panelGroup>
			
		</div>
		
		<!-- Paso 1 de 2 - Contenido de la funcionalidad de la pagina -->
		<div class="ContenidoModificacion caso_modificar clearfix" style="display: none; ">
			
			<div class="clearfix">
	            <h1><cm:getProperty node="${encabezadoMcAfee[0]}" name="titulo"/></h1>
				<h2 style="padding-left: 0pt;">Paso 1 de 2<br><cm:getProperty node="${msjContratarPlan[0]}" name="html"/></h2>
	        </div>
			
			<div class="caja_informacion_plan">
				<div class="fila">
					<label class="indicador">Valor:</label>
					<label class="dato"><span class="ancla_precio"></span>.</label>
				</div>
				<div class="fila">
					<label class="indicador">Plan seleccionado:</label>
					<label class="dato"><span class="ancla_plan"></span>.</label>
				</div>
			</div>
				
			<span class="texto_tab destacado_amarillo"><cm:getProperty node="${msjVerificaEmail[0]}" name="html"/></span>
			
			<!-- Casilla de edicion de correo electronico -->
			<div class="casilla email_no_editar clearfix">
				<div class="columna_uno">
					<span class="email_text_label">Tu Email:</span> <span class="email_text email1"><h:outputText value="#{planesSeguridadBAMPPController.mailUsuario}"/></span>
				</div>
				<div class="columna_dos ee">
					<a href="#" class="btn_modificar_mail">
						<span>Modificar email</span>
					</a>
				</div>
			</div>
		
			<div class="casilla email_editar clearfix" style="display: none; ">
				<div class="columna_uno">
					
					<span class="texto_input">
						<strong>Tu Email:</strong>
					</span>
					<div class="input_inline">
						<input type="text" class="inputBox email_edicion_input" value="" name="email" maxlength="50" style="width: 167px;" />
					</div>
					
			
					<div class="mensaje mensaje_alerta email_invalido">
	                	<table class="tabla_alerta">
	                        <tbody>
	                            <tr>
	                                <td class="tabla_alerta">Ingrese un correo electrónico v&aacute;lido</td>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
					<div class="mensaje mensaje_alerta no_email">
	                	<table class="tabla_alerta">
	                        <tbody>
	                            <tr>
	                                <td class="tabla_alerta">Ingrese un correo electrónico</td>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
					
				</div>
				<div class="columna_dos_extendida">
					<a href="#" class="email_editar_cancelar">Cancelar</a>
					<a href="#" class="btnAzul email_editar_guardar"><span>Guardar</span></a>
				</div>
				
			</div>
			
			<br />
			
			<!-- Mensaje Error -->		    
		    <div id="actualizar-email-fracaso" class="mensaje-error clearfix" style="display: none;">        
		        <div class="contenedor-imagen">
		            <div class="imagen"></div>
		            <div id="texto-actualizar-email-fracaso" class="texto"></div>
		        </div>
		    </div>
		    
		    <!-- Mensaje Exito -->
		    <div id="actualizar-email-exito" class="mensaje-exito-sin-fondo clearfix" style="display: none;">        
		        <div class="contenedor-imagen">
		        	<div class="imagen"></div>
		            <div id="texto-actualizar-email-exito" class="texto"></div>
		        </div>
		    </div>
		    
			<!-- Fin de la casilla de edicion de correo electronico -->
			
			<h:panelGroup rendered="#{planesSeguridadBAMPPController.vencimientoSaldo != null}">
				<div class="casilla saldo clearfix casilla_saldo">
					<div class="columna_uno">
						<strong>Tu Saldo:</strong> 
							<span class="saldo_text">
								$<h:outputText value="#{planesSeguridadBAMPPController.saldoUsuario}">
									<f:convertNumber currencyCode="CLP" locale="es"/>
								</h:outputText>.
							</span> 
						Vigente al 
							<h:outputText value="#{planesSeguridadBAMPPController.vencimientoSaldo}">
								<f:convertDateTime pattern="dd 'de' MMMM 'de' yyyy" locale="es"/>
							</h:outputText>.
					</div>
					<div class="columna_dos">
						<a class="btn_recargar_saldo"
						href="<r:pageUrl pageLabel='${pageLabelRecargasBAM}'></r:pageUrl>">
							<span>Recargar</span>
						</a>					
					</div>
					<br><br>
					<span class="texto_tab"><cm:getProperty node="${msjSaldoUsrPP[0]}" name="titulo"/></span>
					
			        <span class="texto_tab destacado_amarillo faltaSaldo" style="display: none;">
			        	<strong><cm:getProperty node="${msjSaldoUsrPP[0]}" name="html"/></strong>
			        </span>
				</div>
			</h:panelGroup>
			
			<h:panelGroup rendered="#{planesSeguridadBAMPPController.vencimientoSaldo == null}">
				<div class="mensaje-alerta-sistema-pequeno alerta_casilla_saldo">
			        <div class="clearfix sub-contenedor">
			            <div class="contenedor-imagen">
			            	<div class="imagen"></div>
			            </div>
			            <div class="texto">
			            	No existe informacion de saldo.
			            </div>
			        </div>
			    </div>
			</h:panelGroup>
						
			<div class="contenedor_boton_tab">
				<a href="#" class="btnCancelar caso_cancelar_paso1">Cancelar</a>
				<a href="#" id="confirmar_paso1_disabled" class="btnAzulGrandeDesactivado"><span>Continuar</span></a>
				<a href="#" id="confirmar_paso1_enabled" class="btnAzulGrande caso_confirmar" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Banda Ancha Movil/McAfee Antivirus/' + $('span.ancla_plan').html() + '/Continuar');"><span>Continuar</span></a>
			</div>
			
		</div>
		<!-- Fin del paso 1 de 2 -->
			
		<!-- Paso 2 de 2 - 	Cuadro de confirmacion de datos -->
		<div class="caso_paso2de2 clearfix" style="display: none; ">
			
			<div class="clearfix">
	            <h1><cm:getProperty node="${encabezadoMcAfee[0]}" name="titulo"/></h1>
				<h2 style="padding-left: 0pt;">Paso 2 de 2<br><cm:getProperty node="${msjContratarPlan[0]}" name="html"/></h2>
	        </div>
	        
			<div class="loading loading_respuesta_compra" style="display: none;"></div>
			
			<!-- Mensaje Error -->
	        <div id="compra-plan-fracaso" class="mensaje-error-pequeno compra-plan-fracaso" style="display: none;">
		        <div class="clearfix sub-contenedor">
		            <div class="contenedor-imagen">
		            	<div class="imagen"></div>
		            </div>
		            <div id="texto-compra-plan-fracaso" class="texto"></div>
		        </div>
		    </div>
		    
			<div class="cuadro_datos_confirmacion clearfix">
				<div class="fila_cuadro clearfix">
					<div class="label">Tu Email:</div>
					<div class="dato"><span class="email_text email2"></span></div>
				</div>
				<div class="fila_cuadro clearfix">
					<div class="label">Plan seleccionado:</div>
					<div class="dato"><span class="ancla_plan"></span></div>
				</div>
				<div class="fila_cuadro clearfix">
					<div class="label">Valor:</div>
					<div class="dato"><span class="ancla_precio"></span></div>
				</div>
			</div>
			
			<div class="contenedor_condiciones clearfix">
				<div class="input">
					<input type="checkbox" name="terminos_condiciones" id="terminos_condiciones">
				</div>
				<div class="label">
					Acepto los t&eacute;rminos y condiciones.
					<h:outputLink value="TB_detalle_mcafee.faces" styleClass="terminosBox">Ver</h:outputLink>
				</div>
			</div>
			
			<div class="contenedor_boton_tab botones_caso_paso2de2">
				<a href="#" class="btnCancelar caso_cancelar_paso2">Cancelar</a>
				<a href="#" id="caso_confirmar_id" class="btnAzulGrandeDesactivado"><span>Confirmar y contratar</span></a>
				<a href="#" id="caso_confirmar_compra" class="btnVerdeGrande caso_confirmar_comprar" onclick="confirmarCompraPlan()" style="display: none; "><span>Confirmar y contratar</span></a>
			</div>
			
			<div class="enlace_final clearfix enlace_caso_paso2de2" style="display: none;">
				<a href="<r:pageUrl pageLabel='${planesSeguridadBAMPPController.pageLabelPlanes}'></r:pageUrl>" class="btnCancelar_noleft">Volver a <cm:getProperty node="${encabezadoMcAfee[0]}" name="titulo"/></a>
			</div>
			
		</div>					
		<!-- fin de Contenido de la funcionalidad de la pagina -->
		
		<!-- Cuadro de exito -->
		<div class="caso_exito_final clearfix" style="display: none; ">
			
			<div class="clearfix">
	            <h1><cm:getProperty node="${encabezadoMcAfee[0]}" name="titulo"/></h1>
	        </div>
			
			<div class="cuadro_datos_exito clearfix">
				
				<div class="tic_verde">
					<cm:getProperty node="${msjFinalExitoActLic[0]}" name="titulo"/>
					<cm:getProperty node="${msjFinalExito[0]}" name="titulo"/> <span class="urlDescargaPlanSegBAM"></span> <cm:getProperty node="${msjFinalExito[0]}" name="html"/>
					<br><br>
					<ul>
						<li>Valor del plan: <span class="ancla_precio"></span></li>
						<li class="li_fechaActivacion">Fecha activaci&oacute;n: <span class="fechaActivacion"></span></li>
						<li class="li_fechaVencimientoPP">Fecha de vencimiento licencia: <span class="fechaVencimiento"></span></li>
						<li class="li_nuevoSaldo">Tu nuevo saldo es: $<span class="nuevoSaldo"></span></li>
					</ul>
				</div>
				
			</div>
			
			<div class="enlace_final clearfix">
				<a href="#" class="btnCancelar_noleft volver_inicio">Volver a <cm:getProperty node="${encabezadoMcAfee[0]}" name="titulo"/></a>
			</div>
		</div>
	</div>
	<!-- /centro -->
	
	<!-- CONTENIDO TOOLTIPS -->
	<it:iterator var="plan" value="#{planesSeguridadBAMPPController.planesDisponiblesPP}" >
		<div id="contenido<h:outputText value="#{plan.codigoPlan}" />" style="display:none">
	        <strong><h:outputText value="#{plan.nombrePlan}" /></strong>
	        <p><h:outputText value="#{plan.detallePlan}" /></p>
	    </div>
	</it:iterator>
</f:view>
