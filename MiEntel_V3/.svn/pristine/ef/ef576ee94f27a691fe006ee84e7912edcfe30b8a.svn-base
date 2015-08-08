<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it"  uri="/WEB-INF/tld/IteratorTag.tld"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>

<f:view beforePhase="#{suscripcionesController.initSaldoBolsasBAMPP}">
<cm:search id="velocidadPlan" query="idContenido = 'velocidad_plan_pp_bam'" useCache="true"  />
<cm:search id="traficoPlan" query="idContenido = 'trafico_pp_bam'" useCache="true"  />
<cm:search id="velocidad_max_navegacion" query="idContenido = 'vel_max_navegacion_pp_bam'" useCache="true"  />
<cm:search id="debessaber" query="idContenido = 'comprabolsa_pp_bam'" useCache="true"  />
<cm:search id="mensajesaldo" query="idContenido = 'mensaje_saldo_pp_bam'" useCache="true"  />
<cm:search id="cantidadMB" query="idContenido = 'cantidad_mb_pp_bam'" useCache="true"  />
<cm:search id="bolsaActiva" query="idContenido = 'bolsa_activa_bam'" useCache="true"  />
<cm:search id="informacionVelocidad" query="idContenido = 'informacionVelocidad_compraBolsas_bam'" useCache="true"  />
<cm:search id="informacionVelocidadPP" query="idContenido = 'informacionVelocidad_compraBolsas_bam_fdt_pp'" useCache="true"  />
<cm:search id="saldoReservado" query="idContenido = 'saldoReservado'" useCache="false"/>

<style>
#cuotaTrafico {
    font-size: 6.1em;
}
#unidadMedicion {
    font-size: 1.3em;
}
#traficoPlan {
    font-size: 1.5em;
}
.textoNaranjo {
    color: #F78E1E;
    font-weight: bold;
}
.dato_tiempo .caja_tiempo {
	padding: 0 5px 0 0;
}
</style>
<!-- MENSAJES -->
<jsp:include page="../common/messages_table_bampp.jsp"></jsp:include>

<div style="display:none" id="bolsaPPCuota">	
	<p>Esta bolsa tiene una cuota de tr&aacute;fico asociada y  un plazo m&aacute;ximo de navegaci&oacute;n. Una vez consumida la cuota de tr&aacute;fico o alcanzado el plazo m&aacute;ximo para utilizarla (lo que ocurra primero), deber&aacute;s adquirir una nueva bolsa para seguir navegando</p>
</div>
<div style="display:none" id="bolsaPPSinCuota">	
	<p>Esta bolsa tiene un plazo m&aacute;ximo de navegaci&oacute;n. Una vez alcanzado dicho plazo, deber&aacute;s adquirir una nueva bolsa para seguir navegando</p>
</div>
<div style="display:none" id="mensajeVigencia">	
	<p><strong>Vigencia</strong><br/>Luego de esta fecha deber&aacute;s realizar una nueva recarga para recuperar este monto.</p>
</div>
<h:panelGroup rendered="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean != null}">
<h1>Saldo de bolsas</h1>

<div class="caja_cuota_externo clearfix">
	<div class="caja_cuota clearfix">
		<div class="contenedor_cuota_dato">												
							
			<table cellspacing="0" cellpadding="0" class="traficoPlan" style="font-size:1.1em;height: 200px; padding: 0 0 0 20px;">
				<tbody>
				
					<tr><td style="padding-top: 5px; padding-right:40px; text-align: center" colspan="2"><span><strong style="font-size:13px">Tráfico restante</strong></span></td></tr>

					<tr>
						<td style="padding-top: 5px;" class="col1" rowspan="2">								
						
							<h:panelGroup rendered="#{suscripcionesController.tieneBolsaActiva}">
								<h:panelGroup rendered="#{suscripcionesController.esBolsaConTrafico }">		
									<div style="text-align: center; ">
										<span id="cuotaTrafico"><h:outputText value="#{suscripcionesController.saldoRestante}" escape="false"/></span>
										<span id="unidadMedicion"><strong><h:outputText value="#{suscripcionesController.saldoRestanteUnidad}" escape="false"/></strong></span><br>			
									</div>
									<div class="clearfix">	
										<div style="float:left"><span id="traficoPlan">Bolsa <strong class="textoNaranjo"><h:outputText value="#{suscripcionesController.saldoBolsa}"/></strong></span></div>
										<div style="float:left">
											<a class="ico_interrogacionNuevo autoTooltip" href="#bolsaPPCuota" ></a>
										</div>
									</div>
									<strong><h:outputText value="#{suscripcionesController.nombreBolsa}"/></strong><br/>						
									Cuota de tr&aacute;fico incluida: <strong><h:outputText value="#{suscripcionesController.saldoBolsa}"/></strong><br/>
									Velocidad: <strong><cm:getProperty node="${velocidadPlan[0]}" name="html" /> de descarga</strong><br/>
								</h:panelGroup>
								
								<h:panelGroup rendered="#{!suscripcionesController.esBolsaConTrafico }">									
									<div style="text-align: center; height:40px;">
										<!-- 
											<span id="cuotaTrafico"><h:outputText value="#{suscripcionesController.saldoRestante}" escape="false"/></span>
											<span id="unidadMedicion"><strong><h:outputText value="#{suscripcionesController.saldoRestanteUnidad}" escape="false"/></strong></span><br>			
										-->
									</div>
									<div class="clearfix">
										<div style="float:left"><span id="traficoPlan">Bolsa <strong class="textoNaranjo">Sin restricci&oacute;n</strong></span></div>
										<div style="float:left">
											<a class="ico_interrogacionNuevo autoTooltip" href="#bolsaPPSinCuota" ></a>
										</div>
									</div>	
									<strong><h:outputText value="#{suscripcionesController.nombreBolsa}"/></strong><br/>						
									Cuota de tr&aacute;fico incluida: <strong>Sin restricci&oacute;n</strong><br/>
									Velocidad: <strong><cm:getProperty node="${velocidadPlan[0]}" name="html" /> de descarga</strong><br/>
								</h:panelGroup>
							</h:panelGroup>
							<h:panelGroup rendered="#{!suscripcionesController.tieneBolsaActiva}">
								<div style="text-align: center; ">
									<span id="cuotaTrafico">0</span>
									<span id="unidadMedicion"></span><br>			
								</div>														
									<span id="traficoPlan">Bolsa</span><br>
									<strong>No existe bolsa vigente</strong><br>			
									Cuota de tr&aacute;fico incluida: <strong>0</strong><br>
									Velocidad: <strong>Sin informaci&oacute;n</strong><br>		
							</h:panelGroup>
						</td>
						<td class="col2">		
							<div class="grafica_dato grafica_<h:outputText value="#{suscripcionesController.traficoGrafico}"/>"></div>		
						</td>
						
					</tr>
					<tr>
						<td rowspan="3">&nbsp;</td>
					</tr>
				
				</tbody>
			</table>							
									
			<div class="clearfix" style="height:110px;border: 1px solid #DADADA; margin: 0 10px 0 10px">	
				<div style="text-align: center; font-size: 1.5em; font-weight: bold; padding: 5px;">Tiempo de vigencia</div>

				<div class="caja_cuota_no_div_centro tiempoNavegacion" >
					
					<div class="dato_tiempo clearfix" style="padding-left: 5px; padding-right: 0px;">
					
						<table>
							<tr>
								<td style="padding-left: 10px;">
									<div class="caja_tiempo" style="padding-left: 0px; padding-right: 0px;">
										<div class="caja_top"></div>
										<div class="caja_middle">
											<span class="numero"><h:outputText value="#{suscripcionesController.diasExpiracion}"/></span>
											<span class="texto">d&iacute;as</span>
										</div>
										<div class="caja_bottom"></div>
									</div>
								</td>
								<td style="padding-left: 5px;">
									<div class="caja_tiempo" style="padding-left: 0px; padding-right: 0px;">
										<div class="caja_top"></div>
										<div class="caja_middle">
											<span class="numero"><h:outputText value="#{suscripcionesController.horasExpiracion}"/></span>
											<span class="texto">horas</span>
										</div>
										<div class="caja_bottom"></div>
									</div>
								</td>
								<td style="padding-left: 5px;">
									<div class="caja_tiempo" style="padding-left: 0px; padding-right: 0px;">
										<div class="caja_top"></div>
										<div class="caja_middle">
											<span class="numero"><h:outputText value="#{suscripcionesController.minutosExpiracion}"/></span>
											<span class="texto">min</span>
										</div>
										<div class="caja_bottom"></div>
									</div>
								</td>
							</tr>
						</table>
					</div>				
				</div>	
			
			</div>								
								<!-- adasdasdd -->
		</div>
							
		<div class="contenedor_maxima_velocidad">

			<table cellspacing="0" cellpadding="0" class="traficoPlan" style="width:258px; margin-top: -1px; padding-top: 1px;">
			
				<tbody><tr><td colspan="2" style="padding-bottom: 25px;padding-top: 5px; text-align: center"><strong style="padding-bottom: 10px; font-size:13px;">Disponible para comprar bolsa:</strong></td></tr>
				
					<tr>
						<td class="col3">
							<table cellspacing="0" cellpadding="0">
										
								<tbody>
									<tr class="saldoVigente">
										<td style="text-align: center">
											<div class="cajaSaldo">
												<span style="font-size: 3.9em; color:#F78E1E; display: block;">$<h:outputText value="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoRecargas}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></span>		
													<div>
														<div>
															<span style="display: block; font-size: 1.3em; padding-bottom: 8px; padding-top: 25px;">
																<strong>Saldo vigente hasta:</strong>
																<span style="letter-spacing: 1px;"></span><h:outputText value="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.vigenciaSaldo}"><f:convertDateTime type="date" pattern ="dd/MM/yyyy" /></h:outputText>
																<h:panelGroup rendered="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoBolsaPPBAM.tieneSaldo}">
																	<div style="float:right">
																		<a class="ico_interrogacionNuevo autoTooltip" href="#mensajeVigencia" ></a>
																	</div> 
																</h:panelGroup>																			
															</span>																		
														</div>
														
														<!-- info de saldo reservado -->														
														<h:panelGroup rendered="#{suscripcionesController.saldoReservadoMonto != 0 && suscripcionesController.saldoReservadoMonto != 'error'}">															
															<div style="width: 258px; overflow: auto; zoom: 1;">																
																<span style="display: block; border-bottom: 1px solid #CDDAE7; font-size: 1.2em; padding-bottom: 10px; width: 258px;">
																<a href="#contenidoTooltipSaldoReservado" class="ico_interrogacionNuevo autoTooltip ext" tooltip="Primer tooltip" style="float:right"></a>
																	Posees un <strong>Saldo reservado</strong> de $<h:outputText value="#{suscripcionesController.saldoReservadoMonto}"/>																																	
																</span>																
															</div>
														</h:panelGroup>		
															
														<h:panelGroup rendered="#{suscripcionesController.saldoReservadoMonto == 'error'}">	
															<div style="width: 258px; overflow: auto; zoom: 1;">																
																<span style="display: block; border-bottom: 1px solid #CDDAE7; font-size: 1.2em; padding-bottom: 10px; width: 258px;">																
																	Posees un <strong>Saldo reservado</strong> de <strong>No disponible</strong>																														
																</span>																
															</div>															
														</h:panelGroup>															
													
													</div>
											</div>
											<h:panelGroup rendered="#{!suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoBolsaPPBAM.tieneSaldo}">
												<div class="alerta-tabla-reintento" style="position:relative;display:block;margin-top: 10px;margin-bottom: 10px;">Para navegar debes recargar y comprar una bolsa.</div>
											</h:panelGroup>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td class="col4">							
							<h:panelGroup rendered="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoBolsaPPBAM.tieneSaldo}">
								<div style="margin-left: 60px;margin-top:10px;padding-top:15px" class="boton_naranja">
									<a class="btnNaranjaGrande" id="btnRecargar" href="?_nfpb=true&_pageLabel=bampp_regargas_page"
									   onclick="mxTracker._trackPromo('Boton','Recarga Mi BAM');"><span> Recarga <b>Mi BAM</b> </span></a>
								</div>
							</h:panelGroup>
							<h:panelGroup rendered="#{!suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoBolsaPPBAM.tieneSaldo}">
								<div style="margin-left: 60px;" class="boton_naranja">
									<a class="btnNaranjaGrande" id="btnRecargar" href="?_nfpb=true&_pageLabel=bampp_regargas_page"
									   onclick="mxTracker._trackPromo('Boton','Recarga Mi BAM');"><span> Recarga <b>Mi BAM</b> </span></a>
								</div>
							</h:panelGroup>
						</td>
					</tr>
				</tbody>
			</table>


		
		</div>
										
	</div>
</div>
<!-- 
			<span class="texto_velocidad clearfix">									
					  <cm:getProperty node="${informacionVelocidadPP[0]}" name="html"/>	
			</span>
			<div class="clearfix">
				<div class="velocidad_dato"><cm:getProperty node="${velocidad_max_navegacion[0]}" name="html" /></div>
				<div class="velocidad_tasa_transfer"><cm:getProperty node="${velocidad_max_navegacion[0]}" name="titulo" /></div>
			</div>
		
 -->
<!-- fin Caja cuota restante -->	



<!--					<h2 class="ico_cuota">-->
<!--						<strong>Bolsas</strong>-->
<!--					</h2>-->
						
<h2 class="bullet">Bolsas</h2>
<div id="marca-adwords"><cm:getProperty node='${marcaBolsasPPVoz[0]}' name='html'/></div>
			
	<div class="linea_tabs clearfix">

		<div class="tab contenido1">
			<span class="contratar_bolsas">
				Comprar bolsas
			</span>
		</div>

		<div class="tab contenido2">
			<span class="bolsas_contratadas">
				Bolsas Activas
			</span>
		</div>

	</div>

			
    <div class="contenido_tabs">

		<div class="contenido_tab contenido1">
			<br />	
								
			<p><cm:getProperty node="${mensajesaldo[0]}" name="html" /></p>
			
			<h:panelGroup rendered="#{!suscripcionesController.saldoSuficiente}">
				<div class="mensaje_saldo clearfix">
					<span class="saldo">Saldo: $<h:outputText value="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoRecargas}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></span>
					<a href="<r:pageUrl pageLabel='${suscripcionesController.pageLabelRecargaEnLinea}'></r:pageUrl>">Ir a Recargas</a>
				</div>
			</h:panelGroup>
			
			<h:panelGroup rendered="#{suscripcionesController.vigenciaActivas ne '' }">
				<div class="mensaje_saldo clearfix">
					<span class="saldo">						
						Te informamos que tienes más de una bolsa BAM activa y la fecha de vigencia máxima de estas expira el 
						<h:outputText value="#{suscripcionesController.vigenciaActivas}"></h:outputText>
					</span>								
				</div>
			</h:panelGroup>
			
			<div class="caja_bolsas_bam clearfix">
				<div class="bolsa_bam  clearfix" style="background-color:#E3EDF8">
					<div class="paso2 clearfix" style="color:#064687">
						<div class="columna_nombrePlan"><strong>Bolsa</strong></div>
						<div class="columna_precioPlan"><strong>Valor</strong></div>
						<div class="columna_cantidadMb"><strong>Cuota tr&aacute;fico</strong></div>
						<div class="columna_comprar"></div>
				</div>
			</div>
			<it:iterator value="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.bolsasDisponibles}" var="bolsa" rowIndexVar="rowIndex">
				<div class="bolsa_bam <h:outputText value="#{rowIndex % 2 == 0 ? 'impar' : '' }"/> clearfix">
					<div class="paso2 clearfix">
						<div class="columna_nombrePlan"><a class="underline" style="text-decoration: underline;" href="javascript:;"><h:outputText value="#{bolsa.nombreBolsa}"/></a></div>
						<div class="columna_precioPlan">$<h:outputText value="#{bolsa.valorBolsa}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></div>
						<div class="columna_cantidadMb"><h:outputText value="#{bolsa.descSaldo}" escape="false"></h:outputText></div>
						<div class="columna_comprar">
							<h:panelGroup rendered="#{(suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoRecargas < bolsa.valorBolsa) }">
								
								<div class="mensajeSaldoComprarBolsaPrepago" style="width: 200px;">
									No tienes saldo suficiente para <br/>realizar esta operaci&oacute;n. 
									<a href="<r:pageUrl pageLabel='${suscripcionesController.pageLabelRecargaEnLinea}'></r:pageUrl>">Ir a Recargas</a>
								</div>
								
							</h:panelGroup>
							<h:panelGroup rendered="#{(suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoRecargas > bolsa.valorBolsa) }">
									<a class="btnVerdeDelgado alargar ir_paso3" href="#"><span>Comprar</span></a>											
							</h:panelGroup>
						</div>
					</div>
					<div class="paso3 clearfix">
						<div class="columna_nombrePlan"><strong><h:outputText value="#{bolsa.nombreBolsa}"/></strong></div>
						<div class="columna_precioPlan">$<h:outputText value="#{bolsa.valorBolsa}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></div>

						<div class="columna_cantidadMb" style="padding: 10px 0 0;"><strong><h:outputText value="#{bolsa.descSaldo}" escape="false"></h:outputText></strong></div>
						<div class="columna_comprar">
						<div style="font-size: 1.3em; padding-top:5px;">Vas a comprar esta bolsa</div>							
						 <h:form>
							<jsp:include page="/token.jsp" flush="true"/>
							<h:commandLink onclick="this.disabled=true;"  immediate="true" value="" action="#{suscripcionesController.comprarBolsaPPBAM}" styleClass="btnVerdeDelgado alargar flotar_izq"><span>Confirmar</span>
							<f:param name="cartaServicio" value="#{bolsa.codBolsa}"/>
							<f:param name="valorBolsa" value="#{bolsa.valorBolsa}"/>
							<f:param name="nombreBolsa" value="#{bolsa.nombreBolsa}"/>
							</h:commandLink>
							<a href="#" class="enlace_cancelar ir_paso2">Cancelar</a>
						 </h:form>
						</div>
					</div>
					<div class="paso4 clearfix">

						<div class="columna_nombrePlan"><strong>Plan 1 d&iacute;a</strong></div>
						<div class="columna_mensajeExito">
							<span class="tituloExito">Has comprado este plan</span>
							<span class="textoExito">Este plan queda pendiente de activaci&oacute;n, se activar&aacute; dentro de 4 horas.</span>
						</div>
					</div>
					<div> 
						<div style="display: none;border-top:none;margin:0;" class="nota">
							<ul>													
								<li class="item_descripcion" style="list-style: none;padding:0 0 0 8px;"><h:outputText value="#{bolsa.descComercial}"></h:outputText></li>
							</ul>
						</div>
					</div>
				</div>
				<br>
			</it:iterator>
			
			</div>
			
			<br>
			<div class="debessaber clearfix">
				<cm:getProperty node="${debessaber[0]}" name="html" />
			</div>
		</div>
		<div class="contenido_tab contenido2">
					
			<!-- tabla bolsas-->
			<table class="tabla-azul">
				<tr class="cabecera">
					<th width="140px">Bolsa</th>
					<th width="130px">Vencimiento</th>
					<th width="160px">Tr&aacute;fico restante</th>
				</tr>
				<it:iterator var="bolsaComprada" value="#{suscripcionesController.bolsasActivasBAMPP}" rowIndexVar="bcIndex">
					<!-- fila normal -->
					<tr class="<h:outputText value="#{(bcIndex % 2) == 0 ? 'impar' : 'par'}"/>">
						<td><h:outputText value="#{bolsaComprada.nombreBolsa}"/></td>
						<td><span class="icon-activa">Vence el 
						<h:outputText value="#{bolsaComprada.fechaExpiracion}">
						<f:convertDateTime pattern="dd-MM-yyyy HH:mm:ss"/>
						</h:outputText></span></td>		                       
						<td><h:outputText value="#{bolsaComprada.descSaldo}"/></td>
					</tr>
				</it:iterator>
				<!-- /fila normal -->
			</table>
			<h:panelGroup rendered="#{!suscripcionesController.existenBolsasContratadas}">
			  <div class="mensaje-alerta-sistema-pequeno" style="width:548px;">
					<div class="clearfix sub-contenedor">
					<div class="contenedor-imagen">
					<div class="imagen"></div>
					</div>
					<div class="texto">No tienes bolsas Activas.</div>
					</div>
			   </div>
			 </h:panelGroup>
			<!--/tabla bolsas-->
		</div>
	</div>
					
<!-- Contenido del tooltip de saldo reservado  -->
<div id="contenidoTooltipSaldoReservado" style="display:none">
     <cm:getProperty node="${saldoReservado[0]}" name="html"/>
</div>					
					
					
<script type="text/javascript">
$("a.underline").click(function(){
	$(this).parent().parent().parent().find('.nota').toggle();
}); 
</script>			
</h:panelGroup>
</f:view>