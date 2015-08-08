<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<f:view beforePhase="#{bolsaController.init}">
<cm:search id="nodoCondContratar" query="idContenido = 'condGenContratar'" useCache="true"  />
<cm:search id="nodoCondRegalar" query="idContenido = 'condGenRegalar'" useCache="true"  />

<cm:search id="infoContratar" query="idContenido = 'infoContratar'" useCache="true"  />
<cm:search id="infoRegalar" query="idContenido = 'infoRegalar'" useCache="true"  />
<cm:search id="infoContratadas" query="idContenido = 'infoContratadas'" useCache="true"  />
<cm:search id="infoMsjRecarga" query="idContenido = 'infoMsjRecarga'" useCache="true"  />

<script type="text/javascript">

function contratarBolsa(link,codBolsa,valor,tipo,nombre){
	var url='<%=request.getContextPath()%>/portlet/bolsa/misBolsasSSCCJson.faces';
	
	//Mostrar loading
	$('.link'+link).parents("div.contratar:first").prev().hide();
	$('.link'+link).parents("div.paso_2:first").hide().next().html("<img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;&nbsp;<b>Enviando...</b>");
	$('.link'+link).parents("div.paso_2:first").hide().next().show();
	      
	$.ajax({
		type: 'POST',
	    url: url,
	    async: false, 
	    dataType: 'json',
	    data: {accion:'contratar',codigoBolsa:codBolsa,valorBolsa:valor,tipoBolsa:tipo,nombreBolsa:nombre},
	    success: function (resp){
		    if(resp.estado == 'Ok'){		    	
		    	$('.link'+link).parents("div.paso_2:first").next().html("<h5>Has contratado esta bolsa</h5><p>"+resp.detalle+"</p>");
		    	crearMarcaTransaccionGTM(resp, tipo);
		    }else{
		    	$('.link'+link).parents("div.paso_2:first").next().html("<b>"+resp.detalle+"</b>");
		    }
	    }
	});
}


function contratarBolsaBlindaje(link,codBolsa,valor,tipo,codOferta){
	var url='<%=request.getContextPath()%>/portlet/bolsa/misBolsasSSCCJson.faces';
	
	//Mostrar loading
	$('.link'+link).parents("div.contratar:first").prev().hide();
	$('.link'+link).parents("div.paso_2:first").hide().next().html("<img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;&nbsp;<b>Enviando...</b>");
	$('.link'+link).parents("div.paso_2:first").hide().next().show();
	      
	$.ajax({
		type: 'POST',
	    url: url,
	    async: false, 
	    dataType: 'json',	    
	    data: {accion:'contratarBlindaje',codigoBolsa:codBolsa,valorBolsa:valor,tipoBolsa:tipo,codOferta:codOferta},
	    success: function (resp){
		    if(resp.estado == 'Ok'){
		    	$("#contratarBlindaje3").html("<h5 style=\"font-size:12px; font-weight:bolder;\">Has contratado esta bolsa</h5><p>"+resp.respuesta+"</p>");
		    }else{
		    	$("#contratarBlindaje3").html("<b>"+resp.detalle+"</b>");
		    }
	    }
	});
}
	
function regalarBolsa(nroLinkRegalar,codBolsa,valBolsa){
	var url='<%=request.getContextPath()%>/portlet/bolsa/misBolsasSSCCJson.faces';
	var nroPcs = $('.linkRegalar'+nroLinkRegalar).parents(".paso2").find(".span-numero").html();
	$("#pasos").html("Paso 4 de 4");
	$('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").prev().find("div.cancelar").hide();
    var msj = $('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").hide().next().html();
    $('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").hide().next().html("<img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;&nbsp;<b>Enviando...</b>"); 
	$('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").hide().next().show();
    $('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").hide().next().attr("class","caja verde paso3");
	$.ajax({
        type: 'POST',
        url: url,
        async: false, 
        dataType: 'json',
        data: {accion:'regalar',numeroPrepago:nroPcs,codigoBolsa:codBolsa,valorBolsa:valBolsa},
        success: function (resp){
         if(resp.estado == 'Ok'){
        	 $('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").hide().next().html(msj);
         }else{
             $('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").next().attr("class","");
        	 $('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").next().html("<div class='mensaje-error-pequeno'><div class='clearfix sub-contenedor'><div class='contenedor-imagen'><div class='imagen'></div></div><div class='texto'>"+resp.detalle+"</div></div></div>");
        	 $('.linkRegalar'+nroLinkRegalar).parents("div.bolsa").find("div.cabecera").find("div.regalar").show();
        	 $('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").attr("style","");
        	 $('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").find("div.box-numero").hide();
        	 $('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").find("form.paso2").hide();
        	 $('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").find("form.paso1").show();
             $('.linkRegalar'+nroLinkRegalar).parents("div.cuerpo").next().fadeOut(5000);
             $("#pasos").html("Paso 1 de 4");
             }
        }
	});	
}

$(document).ready(function() {
	var param = obtenerParametroURL("tab");
	if (param == 'contratadas') {
		$('.tab.contenido1').removeClass('seleccionado');
		$('.contenido_tab.contenido1').css({'display': 'none'});
		$('.tab.contenido3').addClass('seleccionado');			
		$('.contenido_tab.contenido3').css({'display': 'block'});
	}
	$('div.linea_tabs').find('div.tab.seleccionado').trigger('click');
});

function obtenerParametroURL(name) {
	var regexS = "[\\?&]"+name+"=([^&#]*)";
	var regex = new RegExp(regexS);
	var tmpURL = window.location.href;
	var results = regex.exec(tmpURL);

	if (results == null) {
		return "" ;
	} else {
		return results[1];
	}
}

function crearMarcaTransaccionGTM(resp, tipo) {
	var idTransaccion = resp.respuesta.idTransaccion;
	var codigoProducto = resp.respuesta.skuID;
	var nombreProducto = resp.respuesta.nombre;
	
	mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Bolsas/Contratar Bolsas/' + tipo + '/Conversion');

	mxTracker._addTrans(idTransaccion, '', resp.respuesta.valorTransaccion);
	mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Fee tarifa', resp.respuesta.nuevoValor, '1');
	mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Costo cambio tarifa', resp.respuesta.costoOperacional, '1');

	var dataLayer = dataLayer||[];
	dataLayer.push({'event': 'tracktrans', 'tracktrans': true});
}

</script>
<h2 class="bullet">Bolsas</h2>	
			
			<div class="linea_tabs clearfix">

			<entel:view name="contratarBolsas">	

				<div class="tab contenido1" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Bolsas/Contratar Bolsas');">

					<span class="contratar_bolsas">
						Contratar bolsas
					</span>
				</div>

			</entel:view>

			<entel:view name="bolsasContratadas">	
							
                <div class="tab contenido3" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Bolsas/Bolsas Contratadas');">
					<span class="bolsas_contratadas">
						Bolsas contratadas
					</span>
				</div>
			</entel:view>				


			<h:panelGroup rendered="#{bolsaController.mercadoSuscripcion}">
			<entel:view name="regalarBolsas">	

				<div class="tab contenido2" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Bolsas/Regalar Bolsas');">

					<span class="regalar_bolsas">
						Regalar bolsas
					</span>
				</div>
				
			</entel:view>
			
			<entel:view name="bolsasRegaladas">
							
				<div class="tab contenido4" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Bolsas/Bolsas Regaladas');">
					<span class="historico">
						Bolsas Regaladas
					</span>
				</div>
	         </entel:view>


			</h:panelGroup>

			<h:panelGroup rendered="#{!bolsaController.mercadoSuscripcion}">
			<entel:view name="bolsasRegaladas">	
				
                 <div class="tab contenido5" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Bolsas/Bolsas Activas');">
					<span class="bolsas_contratadas">
						Bolsas Activas
					</span>
				</div>
			</entel:view>	
			</h:panelGroup>

			</div>

			
			<div class="contenido_tabs">
				
				<entel:view name="contratarBolsas">	
				
				<div class="contenido_tab contenido1">
					
					<br /><br />
					<h:panelGroup rendered="#{!bolsaController.mercadoSuscripcion}">
						<div class="mi_saldo contratarBolsaPrepago clearfix" style="background-position: 20px 15px;">
							<div style="float:left; padding-top: 8px;">
								<strong>Saldo: $<h:outputText value="#{planController.saldoActualCC}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></strong>
							</div>
							<div class="msjRecarga">
								<cm:getProperty node="${infoMsjRecarga[0]}" name="html" />
							</div>
							<div style="float:left;">
								<a class="botonRecargarNaranja" style="padding-left: 0" href="<r:pageUrl pageLabel='${planController.pageLabelRecargaEnLinea}'></r:pageUrl>"></a>
							</div>
						</div>
					</h:panelGroup>
					<p><cm:getProperty node="${infoContratar[0]}" name="html" /></p>
					
					<h:panelGroup rendered="#{profile.aaa != miEntelBean.AAATitular && profile.aaa != miEntelBean.AAAControlTotal}">
					<cm:search id="sinPermiso" query="idContenido = 'sinPermisoContBolsa'" useCache="true"  />
					<!-- Caja amarilla -->
					<div class="caja amarilla margen">
						<h6><strong><cm:getProperty node="${sinPermiso[0]}" name="titulo" /></strong></h6>
						<p><cm:getProperty node="${sinPermiso[0]}" name="html" /></div>
					<!-- /Caja amarilla -->
                    </h:panelGroup>	
                                        				
                    <!-- CAJA AMARRILA MENSAJE NO HAY BOLSAS -->
					<h:panelGroup rendered="#{!bolsaController.existenBolsasDisp}">
						<div class="mensaje-alerta-sistema-pequeno" style="width:548px;">
                            <div class="clearfix sub-contenedor">
                            <div class="contenedor-imagen">
                            <div class="imagen"></div>
                            </div>
                            <div class="texto">No hay bolsas disponibles.</div>
                            </div>
                        </div>
                    </h:panelGroup>
                    
                    <!--/jguerrero 20120727--open-->
				
				<style>
				div.confirmar {
    				background: none repeat scroll 0 0 #FFF1A8;
				}
				div.lista-bolsas .fila .contratar a.contratar {
				    display: inline;
				    margin-left: 185px;
				    text-decoration: none;
				}
				div#menu-desplegable-planes-bolsas .contratar a.confirmar {
				    text-decoration: none;
				}
				</style>
				 <h:panelGroup rendered="#{bolsaController.existenBolsasRecomendadaDisponible}">
				 <h2 class="titulo-recomendamos margen">Te Recomendamos:</h2>
                    <div class='bolsa par'>
                    
							
							<div class="lista-bolsas">
							   
								<!-- fila-->
								<div id="bloque1" class="fila clearfix">
				                    <div class="nombre" style="width:150px; padding: 0 0 0 4px; float:left;"><a href="javascript:;" class="underline" style="margin-top:0px;"><h:outputText value="#{bolsaController.bolsaBlindaje.nombreBolsa}"/></a></div>

				                    
				                    <div class="precio" style="width:90px; display:inline; float:left;">$<h:outputText value="#{bolsaController.bolsaBlindaje.valorBolsa}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></div>
				                    <div class="contratar" style="float:left;">
				                    
									
                                    	
                                      <!-- JG DEPENDIENDO DEL ESTADO DE USAURIO SE HABILITARA ESTA OPCION -->
                                    							
											    	<a id="contratarBlindaje1" href="javascript:;" class="btnVerdeDelgado alargar contratar"><span>Contratar</span></a>
		
	                                        <!-- DEPENDIENDO DEL ESTADO DE USAURIO SE HABILITARA ESTA OPCION -->
	                                     
					                        <div id="contratarBlindaje2" class="paso_2" style="display:none;">
					                            <span class="titulo" style="color:#F00; font-size:10px; margin-top:8px; font-weight:bold; width:115px; margin-right:5px;">Contratar&aacute;s esta Bolsa</span>					                            
					                            <a id="confirmar1" href="javascript:void(0);" class="btnVerdeDelgado alargar link"><span>Confirmar</span></a>
					                            <a id="cancelar1" href="javascript:;" class="cancelar">Cancelar</a>
	
					                        </div>
					                        
                                            <div id="contratarBlindaje3" class="paso_3" style="display:none;">
					                            </div>		                        
				  					 </div>

								 	 <!-- nota (Descripcion -->
									<div class="nota" style="display:none">
										<ul>
										
										</ul>
									</div>
									<!--/nota/descripcion-->

				                </div>
				                <!--/fila-->
				             </div>
				             <script> 
								//seleccionar bolsa a contratar paso 1 a paso 2
								$("#contratarBlindaje1").click(function(){
									if($(this).hasClass('btnDesactivado')) {
										return false
									}		
									$("#contratarBlindaje1").hide();
									$("#bloque1").css( {
								        background: "none repeat scroll 0 0 #FFF1A8"
								    });
									$("#contratarBlindaje2").show();
								}); 

								//seleccionar bolsa a contratar paso 2 a paso 1
								$("#cancelar1").click(function(){
									if($(this).hasClass('btnDesactivado')) {
										return false
									}		
									$("#contratarBlindaje2").hide();
									$("#bloque1").css( {
								        background: "none repeat scroll 0 0 #FFFFFF"
								    });
									$("#contratarBlindaje1").show();
								}); 

								//seleccionar bolsa a contratar paso 2 a paso 3
								$("#confirmar1").click(function(){
									if($(this).hasClass('btnDesactivado')) {
										return false
									}		
									$("#contratarBlindaje2").hide();
									$("#contratarBlindaje3").show();
									contratarBolsaBlindaje('00','<h:outputText value="#{bolsaController.bolsaBlindaje.codigoBolsa}"/>','<h:outputText value="#{bolsaController.bolsaBlindaje.valorBolsa}"/>','<h:outputText value="#{bolsaController.bolsaBlindaje.tipoBolsa}"/>','<h:outputText value="#{bolsaController.bolsaBlindaje.codigo}"/>');
									
								}); 
								
								</script>
								
						</div>
						
						 <h2 class="titulo-recomendamos margen">Otras Bolsas:</h2>
						 </h:panelGroup>
						 
						<!--/jguerrero 20120727--close-->
                    
					<!-- Menu expandible de familias de bolsas -->
                   <h:panelGroup rendered="#{bolsaController.existenBolsasDisp}">
                   
                   	
					<h:panelGroup layout="block" rendered="#{profile.mercado == miEntelBean.siglaCuentaControlada 
															&& !bolsaController.validoPrestaLuka}"  
							  styleClass="mensaje-alerta-sistema-pequeno">						
				        <div class="clearfix sub-contenedor">
				            <div style="width:5%" class="contenedor-imagen">
				            	<div class="imagen"></div>
				            </div>
				            <div style="width:88%" class="texto"><h:outputText escape="false" value="#{bolsaController.mensajeErrorPrestaLuka}" /></div>
				        </div>
				    </h:panelGroup>	
					
					<div id="menu-desplegable-planes-bolsas">
					<it:iterator value="#{bolsaController.grupoBolsasDisponibles}" var="grupoBolsa" rowIndexVar="indexGrupo">
						<!-- bolsa -->
						<div class='<h:outputText value="#{indexGrupo % 2 == 0 ? 'bolsa par' : 'bolsa impar'}"/>'>
							<div class="header"><a href="javascript:;" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Bolsas/Contratar Bolsas/<h:outputText value="#{grupoBolsa.tipoBolsa}"/>/Inicio');"><h:outputText value="#{grupoBolsa.tipoBolsa}"/></a></div>
							<div class="lista-bolsas" style="display:none">
							   <it:iterator value="#{grupoBolsa.bolsasDisponibles}" var="bolsa" rowIndexVar="bolsaIndex">
								<!-- fila-->
								<div class="fila clearfix">
				                    <div class="nombre"><a href="javascript:;" class="underline"><h:outputText value="#{bolsa.nombreBolsa}"/></a></div>

				                    <div class="precio">$<h:outputText value="#{bolsa.costo}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></div>
				                    <div class="contratar">
                                    	
                                      <!-- DEPENDIENDO DEL ESTADO DE USAURIO SE HABILITARA ESTA OPCION -->
                                    	<h:panelGroup rendered="#{profile.aaa != miEntelBean.AAATitular && profile.aaa != miEntelBean.AAAControlTotal}">
				                       	<cm:search id="soloAdminCuenta" query="idContenido = 'soloAdminCuenta'" useCache="true"  />
				                       	<div class="mensaje"><cm:getProperty node="${soloAdminCuenta[0]}" name="titulo" /><br /><cm:getProperty node="${soloAdminCuenta[0]}" name="html" /><br />
                                        </div>
                                        </h:panelGroup>
                                        
                                        <!-- DEPENDIENDO DEL ESTADO DE USAURIO SE HABILITARA ESTA OPCION -->

                                        <!-- HABILITAR DEPENDIENDO DEL CASO -->
										<h:panelGroup rendered="#{profile.aaa == miEntelBean.AAATitular || profile.aaa == miEntelBean.AAAControlTotal}">
											
											<f:verbatim rendered="#{profile.mercado == miEntelBean.siglaCuentaControlada}">
												<f:verbatim rendered="#{!bolsaController.validoPrestaLuka}">						
											    	<a href="#" class="bolsa-disabled btnDesactivado"><span>Contratar</span></a>   
											    </f:verbatim>
											    <f:verbatim rendered="#{bolsaController.validoPrestaLuka}">						
											    	<a href="javascript:;" class="btnVerdeDelgado alargar contratar" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Bolsas/Contratar Bolsas/<h:outputText value="#{grupoBolsa.tipoBolsa}"/>/Contratar');"><span>Contratar</span></a>
											    </f:verbatim>	
											</f:verbatim>
											
											<f:verbatim rendered="#{profile.mercado == miEntelBean.siglaSuscripcion}">
													<a href="javascript:;" class="btnVerdeDelgado alargar contratar" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Bolsas/Contratar Bolsas/<h:outputText value="#{grupoBolsa.tipoBolsa}"/>/Contratar');"><span>Contratar</span></a>
											</f:verbatim>
											
	                                        <!-- DEPENDIENDO DEL ESTADO DE USAURIO SE HABILITARA ESTA OPCION -->
	                                     
					                        <div class="paso_2" style="display:none;">
					                            <div class="titulo">Vas a contratar esta bolsa</div>
					                            <a onclick="contratarBolsa('<h:outputText value="#{indexGrupo}"/><h:outputText value="#{bolsaIndex}"/>','<h:outputText value="#{bolsa.snCodigo}"/>','<h:outputText value="#{bolsa.costo}"/>','<h:outputText value="#{grupoBolsa.tipoBolsa}"/>','<h:outputText value="#{bolsa.nombreBolsa}"/>');" href="javascript:void(0);" class="btnVerdeDelgado alargar link<h:outputText value="#{indexGrupo}"/><h:outputText value="#{bolsaIndex}"/>"><span>Confirmar</span></a>
					                            <a href="javascript:;" class="cancelar">Cancelar</a>
	
					                        </div>
					                        <div class="paso_3" style="display:none;">
					                            <h5>Has contratado esta bolsa</h5>
					                            <p>La bolsa queda pendiente de activaci&oacute;n, se activar&aacute; dentro de 4 horas</p>
					                        </div>
                                        </h:panelGroup>
				                        
				  					 </div>

								 	 <!-- nota (Descripcion -->
									<div class="nota" style="display:none">
										<ul>
										<it:iterator value="#{bolsa.descBolsa}" var="strDescBolsa">
											<li><h:outputText escape="false" value="#{strDescBolsa}"/></li>
										</it:iterator>
										</ul>
									</div>
									<!--/nota/descripcion-->

				                </div>
				                <!--/fila-->
				                </it:iterator>
						    	
							</div>
								
						</div>
						<!-- /bolsa -->
						</it:iterator>
					</div>
					<!-- /Menu expandible-->
					<!-- debes saber-->

					<div id="debes-saber" class="clearfix">
						<div class="img"><img src="../framework/skins/mientel/img/debessaber.gif" alt="Debes saber" /></div>
						<div class="contenido">
							<h5><cm:getProperty node="${nodoCondContratar[0]}" name="titulo" /></h5>
							<cm:getProperty node="${nodoCondContratar[0]}" name="html" />						</div>
					</div>
					<!-- /debes saber-->
					</h:panelGroup>
				</div>
				
				</entel:view>

				<entel:view name="regalarBolsas">
				<div class="contenido_tab contenido2">
					
					<br /><br />

					<p><cm:getProperty node="${infoRegalar[0]}" name="html" /></p>
					
					
					     <!-- CAJA AMARRILA MENSAJE NO HAY BOLSAS -->
					<h:panelGroup rendered="#{!bolsaController.existenBolsasDispReg}">
					<div class="caja amarilla margen">
						<h6><strong>No hay bolsas disponibles para regalo.</strong></h6>
					</div>
                    </h:panelGroup>
                  
					<h:panelGroup rendered="#{bolsaController.existenBolsasDispReg}">
					<div id="pasos" class="margen">
						Paso 1 de 4
					</div>
					<div class="linea-azul"></div>
					<p><strong>Bolsas para regalar: </strong></p>

					<!-- Menu expandible de familias de bolsas -->
					<div id="menu-desplegable-planes-bolsas">
					<it:iterator value="#{bolsaController.bolsasDispRegalo}" var="bolsaRegalo" rowIndexVar="indexBolsaRegalo">
						<!-- bolsa -->
						<div class='<h:outputText value="#{indexBolsaRegalo % 2 == 0 ? 'bolsa par clearfix' : 'bolsa impar clearfix'}"/>'>
							<div class="cabecera clearfix">
								<div class="nombre float-izq">
								<a href="javascript:;" class="cerrado"><h:outputText value="#{bolsaRegalo.nombreBolsa}"/></a>
								</div>
		
								<div class="precio">$<h:outputText value="#{bolsaRegalo.costo}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></div>
								<div class="comprar" style="display:none">
										<!-- debe estar incluido para el despliegue del detalle -->
										<div class="paso_3" style="display:none"></div>
								</div>
								<div class="regalar">
									<f:verbatim rendered="#{profile.mercado == miEntelBean.siglaCuentaControlada}">
										<f:verbatim rendered="#{!bolsaController.validoPrestaLuka}">						
									    	<a href="#" class="bolsa-disabled btnDesactivado"><span>Regalar</span></a>   
									    </f:verbatim>
									    <f:verbatim rendered="#{bolsaController.validoPrestaLuka}">						
									    	<a href="javascript:;" class="btnVerdeDelgado alargar regalar"><span>Regalar</span></a>
									    </f:verbatim>	
									</f:verbatim>
									
									<f:verbatim rendered="#{profile.mercado == miEntelBean.siglaSuscripcion}">
											<a href="javascript:;" class="btnVerdeDelgado alargar regalar"><span>Regalar</span></a>
									</f:verbatim>
									
								</div>
								
								<div class="cancelar" style="display:none;"><a href="javascript:;" class="cancelar">Cancelar</a></div>
							</div>
							<!-- cuerpo -->
							<div class="cuerpo clearfix">

								<div class="box-numero" style="display:none;">
									<!-- formulario (paso 1) -->
									<form class="paso1" style="" action="">
										<jsp:include page="/token.jsp" flush="true"/>
										<fieldset>
											<h5>Ingresa el n&uacute;mero de tel&eacute;fono Prepago al cual le vas a regalar la bolsa</h5>
											<div class="fila clearfix">
												<div class="fila_label">

													<label>Ingresa n&uacute;mero EPCS</label>
												</div>
												<div class="fila_input campo">
													<input type="text" style="width:125px" maxlength="8" class="txt-numero numeroPrepagoBolsa" name="txt-numero" />
												</div>
											</div>
											<div class="fila clearfix">
												<div class="fila_label">

													<label>Reingresa n&uacute;mero EPCS</label>
												</div>
												<div class="fila_input campo clearfix">
													<input type="text" style="width:125px" maxlength="8" class="txt-renumero numeroRePrepagoBolsa" name="txt-renumero" />
												</div>
											</div>
											<div class="fila clearfix">
												<label>&nbsp;</label>

												<a href="javascript:;" class="btnVerdeDelgado alargar aceptar btnPasoAceptar"><span>Aceptar</span></a>								
											</div>
											<!-- globo de validacion -->
											<div class="globoError mensaje fixPng"><table><tr><td class="body">msg</td></tr></table></div>
										</fieldset>
									</form>
									<!-- formulario (paso 2) -->
									<form class="paso2" action="#" style="display:none;">
											<jsp:include page="/token.jsp" flush="true"/>
										<fieldset>
											<h5>Vas a regalar la bolsa al n&uacute;mero <span class="span-numero">98765432</span></h5>
											<p>El valor de la bolsa ser&aacute; cargado a tu cuenta, 
											en tu pr&oacute;xima factura por una vez.</p>
											<a href="javascript:regalarBolsa('<h:outputText value="#{indexBolsaRegalo}"/>','<h:outputText value="#{bolsaRegalo.snCodigo}"/>','<h:outputText value="#{bolsaRegalo.costo}"/>');" class="btnVerdeDelgado alargar confirmar linkRegalar<h:outputText value="#{indexBolsaRegalo}"/>"><span>Confirmar</span></a>	
										</fieldset>

									</form>
								</div>	
							</div>
							<!--/cuerpo -->
							<!-- caja verde confirmacion -->
							<div class="caja verde paso3" style="display:none;">
								<p><strong>Haz regalado esta bolsa al n&uacute;mero <span class="span-numero">98765432</span></strong></p>
								<p>El valor de la bolsa ser&aacute; cargado a tu cuenta en tu pr&oacute;xima factura, por s&oacute;lo una vez.</p>

							</div>
							<!-- /caja verde confirmacion -->
							<!-- nota (Descripcion -->
							<div class="nota" style="display:none;">
								<ul>
								<it:iterator value="#{bolsaRegalo.descBolsa}" var="strDescBolsa">
									<li><h:outputText escape="false" value="#{strDescBolsa}"/></li>
								</it:iterator>
								</ul>

							</div>
							<!--/nota/descripcion-->
								
						</div>
						<!-- /bolsa -->
						</it:iterator>
						<!-- bolsa -->
					</div>
					<!-- /Menu expandible-->
					<!-- debes saber-->
					<div id="debes-saber" class="clearfix">
						<div class="img"><img src="../framework/skins/mientel/img/debessaber.gif" alt="Debes saber"/></div>

						<div class="contenido">
							<h5><cm:getProperty node="${nodoCondRegalar[0]}" name="titulo" /></h5>
							<cm:getProperty node="${nodoCondRegalar[0]}" name="html" />						</div>
					</div>
					<!-- /debes saber-->
                  </h:panelGroup>
					
				</div>
				</entel:view>
				
				
				<entel:view name="bolsasContratadas">
				<div class="contenido_tab contenido3">
					
					<br /><br />
					<p><cm:getProperty node="${infoContratadas[0]}" name="html" /></p>
				
					<!-- tabla bolsas-->
					<table class="tabla-azul">
		
							<tr class="cabecera">
								<th width="140px">Bolsa</th>
								<th width="50px">Valor</th>
								<th width="180px">Estado</th>
							</tr>
						    
						<it:iterator value="#{bolsaController.bolsasActuales}" var="bolsaActual" rowIndexVar="rowIndex">
							<!-- fila normal -->
							<tr class='<h:outputText value="#{bolsaActual.estado == 'PA' ? 'par pendiente' : ''}"/>'>
								<td><h:outputText value="#{bolsaActual.nombreBolsa}"/></td>
								<td>$<h:outputText value="#{bolsaActual.costo}">
								<f:convertNumber currencyCode="CLP" locale="es" />
								     </h:outputText></td>
								<td>
								 <h:panelGroup rendered="#{bolsaActual.estado == 'A'}">
								 <span class="">Activa</span>
							  	 </h:panelGroup>
							  	 <h:panelGroup rendered="#{bolsaActual.estado == 'PA'}">
								 <span class="icon-pendiente">Pendiente de activaci&oacute;n</span>
							  	 </h:panelGroup>
							  	 <h:panelGroup rendered="#{bolsaActual.estado == 'PD'}">
								 <span class="icon-pendiente">Pendiente de desactivaci&oacute;n</span>
							  	 </h:panelGroup>
								</td>
							</tr>
							<!-- /fila normal -->
						    </it:iterator>
							<!-- fila desactivar -->
							
					</table>
                     <!-- CAJA AMARRILA MENSAJE NO HAY BOLSAS CONTRATADAS-->
					<h:panelGroup rendered="#{!bolsaController.existenBolsasContratadas}">
					     
						 <div class="mensaje-alerta-sistema-pequeno" style="width:548px;">
                            <div class="clearfix sub-contenedor">
                            <div class="contenedor-imagen">
                            <div class="imagen"></div>
                            </div>
                            <div class="texto">No tienes bolsas contratadas.</div>
                            </div>
                           </div>
                         
                    </h:panelGroup>
					<!--/tabla bolsas-->
				</div>
				</entel:view>
				
				<entel:view name="bolsasRegaladas">
				<div class="contenido_tab contenido4">
					<table class="tabla-azul historico">
						<thead>
							<tr class="cabecera">
								<th>Bolsa</th>
								<th>Valor</th>

								<th>Destinatario</th>
								<th>Fecha</th>
							</tr>
						</thead>
						<tbody>
						<it:iterator value="#{bolsaController.bolsasRegaladas}" var="bolsaRegalada" rowIndexVar="indexBr">
							<tr class='<h:outputText value="#{indexBr % 2 == 0 ? 'impar' : 'par'}"/>'>
								<td><h:outputText value="#{bolsaRegalada.nombreBolsa}"/></td>
								<td>$<h:outputText value="#{bolsaRegalada.costo}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></td>
								<td><h:outputText value="#{bolsaRegalada.snCodigo}"/></td>
		                        <td><h:outputText value="#{bolsaRegalada.vigencia}"/></td>
							</tr>
						</it:iterator>	
					</tbody>

					</table>
					<!--/tabla bolsas-->
						<h:panelGroup rendered="#{!bolsaController.existenBolsasRegaladas}">
						 <div class="mensaje-alerta-sistema-pequeno" style="width:548px;">
                            <div class="clearfix sub-contenedor">
                            <div class="contenedor-imagen">
                            <div class="imagen"></div>
                            </div>
                            <div class="texto">No has regalado bolsas.</div>
                            </div>
                           </div>
						</h:panelGroup>
				</div>
				</entel:view>
				
				
				<div class="contenido_tab contenido5">
					
					<!-- tabla bolsas-->
					<table class="tabla-azul">
		
							<tr class="cabecera">
								<th width="140px">Bolsa</th>
								<th width="170px">Vencimiento</th>
								<th width="">Saldo</th>
							</tr>
						    <it:iterator var="bolsaComprada" value="#{bolsaController.bolsasActualesPP}" rowIndexVar="bcIndex">
							<!-- fila normal -->
							<tr class="<h:outputText value="#{(bcIndex % 2) == 0 ? 'impar' : 'par'}"/>">
								<td><h:outputText value="#{bolsaComprada.nombreBolsa}"/></td>
								<td><span class="icon-activa">Vence el 
								<h:outputText value="#{bolsaComprada.fechaExpiracion}">
								<f:convertDateTime pattern="dd-MM-yyyy HH:mm:ss"/>
								</h:outputText></span></td>
		                        <td>
		                            <div>
		                             <h:outputText rendered="#{bolsaComprada.tipoBolsa != 'VOZ' && bolsaComprada.unidad != 'MB'}" value="#{bolsaComprada.saldo}">
		                             <f:convertNumber currencyCode="CLP" locale="es" />
		                             </h:outputText>		                             
		                             <h:outputText rendered="#{bolsaComprada.tipoBolsa != 'VOZ' && bolsaComprada.unidad == 'MB'}" value="#{bolsaComprada.saldoString}" />
		                             <h:outputText rendered="#{bolsaComprada.tipoBolsa == 'VOZ' }" value="#{bolsaComprada.saldo}" converter="saldoMinutosBolsaConverter" />
		                             <h:outputText value=" "/>
		                             <h:outputText value="#{bolsaComprada.unidad}"/>
		                            </div>
		                        </td>
							</tr>
							</it:iterator>
							<!-- /fila normal -->
					</table>
			    <h:panelGroup rendered="#{!bolsaController.existenbolsasActualesPP}">
			    <div class="mensaje-alerta-sistema-pequeno" style="width:548px;">
                   <div class="clearfix sub-contenedor">
                   <div class="contenedor-imagen">
                    <div class="imagen"></div>
                    </div>
                    <div class="texto">No tienes Bolsas Activas.</div>
                    </div>
                  </div>
			     </h:panelGroup>
					<!--/tabla bolsas-->
 			    </div>
					
			</div>
</f:view>
