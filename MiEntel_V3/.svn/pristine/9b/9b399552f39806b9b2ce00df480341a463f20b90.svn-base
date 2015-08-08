<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<cm:search id="infoDebesSaber" query="idContenido = 'debesSaberCambioTarifa'" useCache="true"  />
<cm:search id="infoEfectivoComunik2" query="idContenido = 'infoEfectivoComunik2'" useCache="true"  />
<cm:search id="tituloTarifasPlus" query="idContenido = 'tituloTarifas'" useCache="false"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<f:view beforePhase="#{planController.obtenerPlanesDisponibles}">

  <entel:view name="cambiarPlan">	
    
    <h:panelGroup rendered="#{planController.existenPlanesDisponibles}">

					<h1>Cambio de Tarifa</h1>
				
					<h2 class="superchipOK">Tarifas disponibles</h2>
		
					<div class="mi_saldo cambiarPlanPrepago">
						<strong>Saldo: <span id="saldoCambioPlan">$<h:outputText value="#{planController.planResumen.saldo}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></span></strong>
						<a href="<r:pageUrl pageLabel='${planController.pageLabelRecargaEnLinea}'></r:pageUrl>">Ir a Recargas</a>
						
					</div>
					
					<h:panelGroup layout="block" rendered="#{!planController.validoPrestaLuka}"  
								  styleClass="mensaje-alerta-sistema-pequeno">						
				        <div class="clearfix sub-contenedor">
				            <div style="width:5%" class="contenedor-imagen">
				            	<div class="imagen"></div>
				            </div>
				            <div style="width:88%" class="texto"><h:outputText escape="false" value="#{planController.mensajeErrorPrestaLuka}" /></div>
				        </div>
				    </h:panelGroup>
					
					<p>Elige tu nueva tarifa:</p>
		
<div id="menu-desplegable-planes">
	<!-- Tarifas plus-->

 <h:panelGroup rendered="#{planController.validoTarifasPlus}">

	
				<div class="tpHeader">
                	<span><cm:getProperty node ="${tituloTarifasPlus[0]}" name="html"/></span>
                </div>							
                
               
               
<it:iterator var="tarifasPlus" value="#{planController.planDisponible.planesDisponiblesPPAltoValor}" rowIndexVar="index">	
					<c:set var="style" value="#{index % 2 == 0 ? 'impar': 'par'}" scope="page" />
			
					<!-- bolsa -->
					<div class="bolsa marcar <h:outputText value="#{style}"/> clearfix ">
					
	
							
						<!-- Plan Comunik2 -->	
						<h:panelGroup rendered="#{tarifasPlus.idTarifa eq tipoPlanBean.planComunik2Id}">
							
							<div class="header" style="display:block;">
								<f:verbatim rendered="#{!planController.validoPrestaLuka}">						
							    	 <a href="#" style="display:none" class="flotar_der btnDesactivado" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Tarifa o cambio de tarifa/Tarifas disponibles/Contratar');"><span>Cambiarme a esta tarifa</span></a>   
							    	
							    </f:verbatim>
							    <f:verbatim rendered="#{planController.validoPrestaLuka}">						
							    	<a href="#" class="btnNaranjaDelgado1 btnCambiarmeTarifa cambiarTarifa flotar_der paso2Comunik2" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Tarifa o cambio de tarifa/Tarifas disponibles/Contratar');">Cambiarme a esta tarifa</a>
							    
							    	
							    </f:verbatim>
							    
								 <a href="javascript:;" class="expandir" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Tarifa o cambio de tarifa/Tarifas disponibles/Inicio');"><h:outputText value="#{tarifasPlus.nombrePlan}"/></a>
								
								<span><h:outputText value="#{tarifasPlus.breveDescripcion}"/></span>
							</div>
							
							<div style="display: none;" class="lista-bolsas note-plan">
						
								
								<!-- tabla bolsas-->
								<div class="tabla_precios">
									
									<div class="disclaimer" style="padding: 0 0 0 0">
										<it:iterator var="itemGlosa" value="#{tarifasPlus.glosaFormated}" rowIndexVar="indexGlosa">	
											<h:panelGroup rendered="#{indexGlosa > 0}">
												- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
											</h:panelGroup>
									    </it:iterator>
									</div>
									
								</div>
						
								<!--/tabla bolsas-->
								
								<div class="paso_2">
									
									<div class="prepago_confirmar_edicion clearfix">
				
										<form id="cambioPlan" name="formulario_comunik2" method="get" action="#">
											<jsp:include page="/token.jsp" flush="true"/>
											<h5><strong>Ahora ingresa el n&uacute;mero de usuario que quieres asociar a tu plan Comunik2 y con el que podr&aacute;s hablar gratis.</strong></h5>
																
											<div class="formulario_ingresar comunik2 clearfix">
												<div class="campo campo2 mostrar_globo">
													<input type="text" maxlength="8" name="numero_frecuente_cambioPlan" class="nf_input comunik2Input" onkeypress="return soloNumeros(event);" style="width: 115px;" />
												</div>
												<div class="ubicador_boton">
						
													<a href="#" class="btnAzul ingresarNumeroCambioPlan ingresar" onclick="javascript:cambiarPlanComunik2('<h:outputText value="#{tarifasPlus.nombrePlan}"/>',<h:outputText value="#{tarifasPlus.costoCambioPlan}"/>);"><span>Ingresar</span></a>
													<a href="#" class="cancelar"><u>Cancelar</u></a>
												
													<a id="loadingCambioPlan"></a>
												</div>
											</div>
											
										    <br />
												<cm:getProperty node="${infoEfectivoComunik2[0]}" name="html" />
										</form>
									</div>
									
									<div class="disclaimer">
										<it:iterator var="itemGlosa" value="#{tarifasPlus.glosaFormated}" rowIndexVar="indexGlosa">	
											<h:panelGroup rendered="#{indexGlosa > 0}">
												- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
											</h:panelGroup>
									    </it:iterator>
									</div>
						
								</div><!--/paso_2-->
								
								<div class="paso_3">
									
									<div class="prepago_exito_edicion clearfix"></div>
						

									<div class="disclaimer" style="padding: 0 0 0 0">
										<it:iterator var="itemGlosa" value="#{tarifasPlus.glosaFormated}" rowIndexVar="indexGlosa">	
											<h:panelGroup rendered="#{indexGlosa > 0}">
												- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
											</h:panelGroup>
									    </it:iterator>
									</div>
									
								</div><!--/paso_3-->
			
								</div>
					</h:panelGroup>
					<!-- /Plan Comunik2 btnCambiarmeTarifa cambiarTarifa flotar_der -->
							
							
			        <h:panelGroup rendered="#{tarifasPlus.idTarifa ne tipoPlanBean.planComunik2Id}">
								<div class="header" >
							
									<f:verbatim rendered="#{!planController.validoPrestaLuka}">						
								    	 <a href="#" style="display:none" class="flotar_der btnDesactivado" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Tarifa o cambio de tarifa/Tarifas disponibles/Contratar');"  ><span>Cambiarme a esta tarifa</span></a>   
								    	
								    </f:verbatim>
								    <f:verbatim rendered="#{planController.validoPrestaLuka}">
								  		 	
								    	 <a href="#" class="btnCambiarmeTarifa  cambiarTarifa  flotar_der"onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Tarifa o cambio de tarifa/Tarifas disponibles/Contratar');"><span>Cambiarme a esta tarifa</span></a>
								    	
								  							
              		                </f:verbatim>
						    
									<a href="javascript:;" class="expandir"><h:outputText value="#{tarifasPlus.nombrePlan}"/></a>
									<span><h:outputText value="#{tarifasPlus.breveDescripcion}"/></span>
								
								</div>
		
							
								<div style="display: none;" class="lista-bolsas note-plan">
								
								<!--tabla_precios-->
									<div class="tabla_precios">
									    
										<cm:getProperty node="${infoDebesSaber[0]}" name="html" />
										
										<div style="width: 520px">&nbsp;</div>
										<div class="disclaimer" style="padding: 0 0 0 0">
										<it:iterator var="itemGlosa" value="#{tarifasPlus.glosaFormated}" rowIndexVar="indexGlosa">	
												<h:panelGroup rendered="#{indexGlosa > 0}">
													- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
												</h:panelGroup>
										</it:iterator>
										
										</div>
									</div>
									<!--/tabla_precios-->
									
									<div class="paso_2">
									
									
										<div class="paso_confirmar clearfix">
											<span>Vas a cambiar a esta tarifa</span>
											<a href="#" class="cancelar !important"><u>Cancelar</u></a>
											<a href="#" class="btnVerdeDelgado confirmar" onclick="javascript:confirmarCambioTarifa('<h:outputText value="#{tarifasPlus.nombrePlan}"/>',<h:outputText value="#{tarifasPlus.idTarifa}"/>,<h:outputText value="#{tarifasPlus.costoCambioPlan}"/>)"><span>Confirmar</span></a> 
											
											
											
											<a id="loadingCambioPlan"></a>
										</div>
										
										<cm:getProperty node="${infoDebesSaber[0]}" name="html" />
										
										<div style="width: 520px">&nbsp;</div>
										<div class="disclaimer" style="padding: 0 0 0 0">
											<it:iterator var="itemGlosa" value="#{tarifasPlus.glosaFormated}" rowIndexVar="indexGlosa">	
												<h:panelGroup rendered="#{indexGlosa > 0}">
													- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
												</h:panelGroup>
										    </it:iterator>
										</div>
										
										
									</div><!--/paso_2-->
									
									<div class="paso_3">
										<div class="paso_confirmar clearfix">
											<div class="prepago_confirmar clearfix"></div>
	
										</div>
										
										<cm:getProperty node="${infoDebesSaber[0]}" name="html" />
										
										<div style="width: 520px">&nbsp;</div>
										<div class="disclaimer" style="padding: 0 0 0 0">
											<it:iterator var="itemGlosa" value="#{tarifasPlus.glosaFormated}" rowIndexVar="indexGlosa">	
												<h:panelGroup rendered="#{indexGlosa > 0}">
													- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
												</h:panelGroup>
										    </it:iterator>
										</div>
									</div><!--/paso_3-->

								</div>
							</h:panelGroup>	
								
								
							</div>
						<!-- /bolsa -->
					
</it:iterator>
<div class="separacion_planes"></div>
</h:panelGroup>
		                      
                  	<!-- fin tarifasPlus -->

					<it:iterator var="plan" value="#{planController.planDisponible.planesDisponiblesPP}" rowIndexVar="index">	
					<c:set var="style" value="#{index % 2 == 0 ? 'impar': 'par'}" scope="page" />
											
					<!-- bolsa -->
					<div class="bolsa <h:outputText value="#{style}"/> clearfix">
							
						<!-- Plan Comunik2 -->	
						<h:panelGroup rendered="#{plan.idTarifa eq tipoPlanBean.planComunik2Id}">
							
							<div class="header" style="display:block;">
								<f:verbatim rendered="#{!planController.validoPrestaLuka}">						
							    	<a href="#" style="display:none" class="flotar_der btnDesactivado" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Tarifa o cambio de tarifa/Tarifas disponibles/Contratar');"><span>Cambiarme a esta tarifa</span></a>   
							    </f:verbatim>
							    <f:verbatim rendered="#{planController.validoPrestaLuka}">						
							    	<a href="#" class="btnCambiarmeTarifa cambiarTarifa flotar_der paso2Comunik2" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Tarifa o cambio de tarifa/Tarifas disponibles/Contratar');">Cambiarme a esta tarifa</a>
							    </f:verbatim>
							    
								<a href="javascript:;" class="expandir" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Tarifa o cambio de tarifa/Tarifas disponibles/Inicio');"><h:outputText value="#{plan.nombrePlan}"/></a>
								<span><h:outputText value="#{plan.breveDescripcion}"/></span>
							</div>
							
							<div style="display: none;" class="lista-bolsas note-plan">
						
								
								<!-- tabla bolsas-->
								<div class="tabla_precios">
									
									<div class="disclaimer" style="padding: 0 0 0 0">
										<it:iterator var="itemGlosa" value="#{plan.glosaFormated}" rowIndexVar="indexGlosa">	
											<h:panelGroup rendered="#{indexGlosa > 0}">
												- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
											</h:panelGroup>
									    </it:iterator>
									</div>
									
								</div>
						
								<!--/tabla bolsas-->
								
								<div class="paso_2">
									
									<div class="prepago_confirmar_edicion clearfix">
				
										<form id="cambioPlan" name="formulario_comunik2" method="get" action="#">
											<jsp:include page="/token.jsp" flush="true"/>
											<h5><strong>Ahora ingresa el n&uacute;mero de usuario que quieres asociar a tu plan Comunik2 y con el que podr&aacute;s hablar gratis.</strong></h5>
																
											<div class="formulario_ingresar comunik2 clearfix">
												<div class="campo campo2 mostrar_globo">
													<input type="text" maxlength="8" name="numero_frecuente_cambioPlan" class="nf_input comunik2Input" onkeypress="return soloNumeros(event);" style="width: 115px;" />
												</div>
												<div class="ubicador_boton">
						
													<a href="#" class="btnAzul ingresarNumeroCambioPlan ingresar" onclick="javascript:cambiarPlanComunik2('<h:outputText value="#{plan.nombrePlan}"/>',<h:outputText value="#{plan.costoCambioPlan}"/>);"><span>Ingresar</span></a>
													<a href="#" class="cancelar"><u>Cancelar</u></a>
												
													<a id="loadingCambioPlan"></a>
												</div>
											</div>
											
										    <br />
												<cm:getProperty node="${infoEfectivoComunik2[0]}" name="html" />
										</form>
									</div>
									
									<div class="disclaimer">
										<it:iterator var="itemGlosa" value="#{plan.glosaFormated}" rowIndexVar="indexGlosa">	
											<h:panelGroup rendered="#{indexGlosa > 0}">
												- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
											</h:panelGroup>
									    </it:iterator>
									</div>
						
								</div><!--/paso_2-->
								
								<div class="paso_3">
									
									<div class="prepago_exito_edicion clearfix"></div>
						

									<div class="disclaimer" style="padding: 0 0 0 0">
										<it:iterator var="itemGlosa" value="#{plan.glosaFormated}" rowIndexVar="indexGlosa">	
											<h:panelGroup rendered="#{indexGlosa > 0}">
												- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
											</h:panelGroup>
									    </it:iterator>
									</div>
									
								</div><!--/paso_3-->
			
								</div>
					</h:panelGroup>
					<!-- /Plan Comunik2 -->
							
							
			        <h:panelGroup rendered="#{plan.idTarifa ne tipoPlanBean.planComunik2Id}">
								<div class="header" style="display:block;">
									<f:verbatim rendered="#{!planController.validoPrestaLuka}">						
								    	<a href="#" style="display:none" class="flotar_der btnDesactivado" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Tarifa o cambio de tarifa/Tarifas disponibles/Contratar');"><span>Cambiarme a esta tarifa</span></a>   
								    </f:verbatim>
								    <f:verbatim rendered="#{planController.validoPrestaLuka}">						
								    	<a href="#" class="btnCambiarmeTarifa cambiarTarifa flotar_der" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Tarifa o cambio de tarifa/Tarifas disponibles/Contratar');">Cambiarme a esta tarifa</a>
								    </f:verbatim>
									<a href="javascript:;" class="expandir"><h:outputText value="#{plan.nombrePlan}"/></a>
									<span><h:outputText value="#{plan.breveDescripcion}"/></span>
								</div>
							
								<div style="display: none;" class="lista-bolsas note-plan">
								
								<!--tabla_precios-->
									<div class="tabla_precios">
									    
										<cm:getProperty node="${infoDebesSaber[0]}" name="html" />
										
										<div style="width: 520px">&nbsp;</div>
										<div class="disclaimer" style="padding: 0 0 0 0">
										<it:iterator var="itemGlosa" value="#{plan.glosaFormated}" rowIndexVar="indexGlosa">	
												<h:panelGroup rendered="#{indexGlosa > 0}">
													- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
												</h:panelGroup>
										</it:iterator>
										
										</div>
									</div>
									<!--/tabla_precios-->
									
									<div class="paso_2">
									
									
										<div class="paso_confirmar clearfix">
											<span>Vas a cambiar a esta tarifa</span>
											<a href="#" class="cancelar"><u>Cancelar</u></a>
											<a href="#" class="btnVerdeDelgado confirmar" onclick="javascript:confirmarCambioTarifa('<h:outputText value="#{plan.nombrePlan}"/>',<h:outputText value="#{plan.idTarifa}"/>,<h:outputText value="#{plan.costoCambioPlan}"/>)"><span>Confirmar</span></a>
											
											<a id="loadingCambioPlan"></a>
										</div>
										
										<cm:getProperty node="${infoDebesSaber[0]}" name="html" />
										
										<div style="width: 520px">&nbsp;</div>
										<div class="disclaimer" style="padding: 0 0 0 0">
											<it:iterator var="itemGlosa" value="#{plan.glosaFormated}" rowIndexVar="indexGlosa">	
												<h:panelGroup rendered="#{indexGlosa > 0}">
													- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
												</h:panelGroup>
										    </it:iterator>
										</div>
										
										
									</div><!--/paso_2-->
									
									<div class="paso_3">
										<div class="paso_confirmar clearfix">
											<div class="prepago_confirmar clearfix"></div>
	
										</div>
										
										<cm:getProperty node="${infoDebesSaber[0]}" name="html" />
										
										<div style="width: 520px">&nbsp;</div>
										<div class="disclaimer" style="padding: 0 0 0 0">
											<it:iterator var="itemGlosa" value="#{plan.glosaFormated}" rowIndexVar="indexGlosa">	
												<h:panelGroup rendered="#{indexGlosa > 0}">
													- <h:outputText value="#{itemGlosa}"></h:outputText>.<br />
												</h:panelGroup>
										    </it:iterator>
										</div>
									</div><!--/paso_3-->

								</div>
							</h:panelGroup>	
								
								
							</div>
						<!-- /bolsa -->
					
					</it:iterator>

			        </div>		 
					<script type="text/javascript">
							$(document).ready(function(){ 
									initMenuDesplegableTarifas();
									comunik2();
									comunik2Validacion();
							});
					</script>
					 
	</h:panelGroup>
	
	
	<!-- MENSAJES -->
	<jsp:include page="../common/messages_table.jsp"></jsp:include>

	
	</entel:view>
	
	
	<script type="text/javascript">
	/**
	 * 
	 */
	function confirmarCambioTarifa(nombreTarifa,codNuevoPlan,costoCambioPlan){

	       var url='<%=request.getContextPath()%>/portlet/plan/cambioPlanPPJson.faces';

	       //Loading 
	       $('a#loadingCambioPlan').html("<img width='180px' src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;&nbsp;<b>Enviando...</b><br>");
	       $('div#menu-desplegable-planes').find('a.cancelar').hide(); 
	       $('div#menu-desplegable-planes').find('a.confirmar').hide();
	       
		       $.ajax({
	                type: 'POST',
	                url: url,
	                async: false,
	                dataType: 'json',
	                data: {codigoNuevoPlan:codNuevoPlan, nombreNuevoPlan:nombreTarifa, valorNuevoPlan:costoCambioPlan},
	                success: function (resp){
		                 if(resp.estado == 'Ok'){		                	 
		                	 $('a#loadingCambioPlan').html(''); $('div.prepago_confirmar').html('<h5>Te has cambiado a esta tarifa</h5><strong>Tu nueva tarifa es '+ nombreTarifa +'</strong>').css('display','block');
		                	 actualizarSaldo(1);
		                	 actualizarNroFrecuente();
		                	 crearMarcaTransaccionGTM(resp);
		                 }else{
		                	 $('a#loadingCambioPlan').html(''); $('div.prepago_confirmar').html('<strong>'+resp.detalle+'</strong>').css('display','block');
				         }

		               $('div#menu-desplegable-planes').find('a.cancelar').show(); 
		      	       $('div#menu-desplegable-planes').find('a.confirmar').show();
                   }
	       });

	}

	/**
	 * 
	 */
	function actualizarSaldo(opcion){
		//opcion: 1(Actualiza toda la informacion de Plan Actual)
		//opcion: 2(Actualiza solo la informacion de los saldos, tanto en Plan Actual como en Cambio de Plan)
		
		 var url='<%=request.getContextPath()%>/portlet/plan/resumenPlanJson.faces';

		 if(opcion==1){
		     	$('.tarifaPP').html("<img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;&nbsp;<b>Actualizando Tarifa Actual...</b><br>");
		 }
		     
			 $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            success: function (resp){
                 flagRespuestas['plan'] = '1'; 
                 if(resp.estado == 'Ok'){

                     if(opcion == 1){
	                	 $('span#saldoCambioPlan').html('$'+resp.respuesta.saldoFormated);
	                	 $('.tarifaPP').load("<%=request.getContextPath()%>/portlet/plan/planActualPP.faces");
	                	 
		                  }else{
	                	  $('span#saldoPlanActual').html('$'+resp.respuesta.saldoFormated);
	                	  $('span#saldoCambioPlan').html('$'+resp.respuesta.saldoFormated); 	
		              }

                   }          
	            }
	        });
	}

	function actualizarNroFrecuente(){
		var urlFrecuente='<%=request.getContextPath()%>/portlet/plan/numeroFrecuentePP.faces';
		var urlFavorito='<%=request.getContextPath()%>/portlet/plan/numeroFavoritoPP.faces';
		$('.numeroFrecuentePP').html("<img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;&nbsp;<b>Actualizando Informacion...</b><br>");
		$('.numeroFavoritoPP').html("<img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;&nbsp;<b>Actualizando Informacion...</b><br>");
        $('.numeroFrecuentePP').load(urlFrecuente,{llamadoAjax:true});
        $('.numeroFavoritoPP').load(urlFavorito,{llamadoAjax:true});
	}

	$('#menu-desplegable-planes').ajaxComplete(function(e, xhr, settings) {
		if (settings.url == '<%=request.getContextPath()%>/portlet/plan/cambioPlanPPJson.faces') {
			if($('.historialComunik2').css('display') == 'block'){
				var urlHistorialComunik2='<%=request.getContextPath()%>/portlet/plan/historialPlanComunik2.faces';
				$('.historialComunik2').html("<img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;&nbsp;<b>Actualizando Informacion...</b><br>");
			    $('.historialComunik2').load(urlHistorialComunik2);
			}
		}
	});

	/**
	 * 
	 */
	function cambiarPlanComunik2(nombreTarifa,costoCambioPlan){

	       var url='<%=request.getContextPath()%>/portlet/plan/cambioPlanComunik2PPJson.faces';
	       
		   if($('div.formulario_ingresar').find('.comunik2Input').val().length == parseInt($('div.formulario_ingresar').find('input.comunik2Input').attr('maxlength'))) {
			   var nroRecibeSolicitud = $('input.comunik2Input').val();

		       //Loading 
		       $('a#loadingCambioPlan').html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img width='180px' src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;&nbsp;<b>Enviando ...</b>");
		       $('div#menu-desplegable-planes').find('a.cancelar').hide(); 
		       $('div#menu-desplegable-planes').find('a.ingresar').hide();
		       
			       $.ajax({
		                type: 'POST',
		                url: url,
		                async: false,
		                dataType: 'json',
		                data: {numeroRecibeSolicitud:nroRecibeSolicitud},
		                success: function (resp){
			                 if(resp.estado == 'Ok'){
			                	 $('a#loadingCambioPlan').html(''); $('div.prepago_exito_edicion').html('<span>Tu invitaci&oacute;n al n&uacute;mero '+nroRecibeSolicitud+' para unirse a '+nombreTarifa+' fue enviada con &eacute;xito. <br> Para que comiencen a utilizar este servicio, el m&oacute;vil invitado debe aceptar tu invitaci&oacute;n</span>');		
			                	 actualizarSaldo(1);
			                	 actualizarNroFrecuente();
			                 }else{
			                	 $('a#loadingCambioPlan').html(''); $('div.prepago_exito_edicion').html(resp.detalle).css('display','block');
					         }
	
			               $('div#menu-desplegable-planes').find('a.cancelar').show(); 
			      	       $('div#menu-desplegable-planes').find('a.ingresar').show();
	                }
		       });

		   }

	}

	function crearMarcaTransaccionGTM(resp) {
		var idTransaccion = resp.respuesta.idTransaccion;
		var codigoProducto = resp.respuesta.skuID;
		var nombreProducto = resp.respuesta.nombre;

		mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Tarifa o cambio de tarifa/Tarifas disponibles/Conversion');

		mxTracker._addTrans(idTransaccion, '', resp.respuesta.valorTransaccion);
		mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Fee tarifa', resp.respuesta.nuevoValor, '1');
		mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Costo cambio tarifa', resp.respuesta.costoOperacional, '1');

		dataLayer = dataLayer||[];
		dataLayer.push({'event': 'tracktrans', 'tracktrans': true});
	}	

	</script>
</f:view>
