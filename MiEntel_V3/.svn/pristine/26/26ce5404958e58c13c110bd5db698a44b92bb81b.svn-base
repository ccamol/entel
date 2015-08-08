<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it"  uri="/WEB-INF/tld/IteratorTag.tld"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<f:view beforePhase="#{suscripcionesController.initSaldoBolsasDisponiblesBAM}">
<cm:search id="velocidadPlan" query="idContenido = 'velocidad_plan_pp_bam'" useCache="true"  />
<cm:search id="traficoPlan" query="idContenido = 'trafico_pp_bam'" useCache="true"  />
<cm:search id="velocidad_max_navegacion" query="idContenido = 'vel_max_navegacion_pp_bam'" useCache="true"  />
<cm:search id="debessaber" query="idContenido = 'comprabolsa_pp_bam'" useCache="true"  />
<cm:search id="mensajesaldo" query="idContenido = 'mensaje_saldo_pp_bam'" useCache="true"  />
<cm:search id="cantidadMB" query="idContenido = 'cantidad_mb_pp_bam'" useCache="true"  />
<cm:search id="bolsaActiva" query="idContenido = 'bolsa_activa_bam'" useCache="true"  />
<cm:search id="informacionVelocidad" query="idContenido = 'informacionVelocidad_compraBolsas_bam'" useCache="true"  />
<cm:search id="informacionVelocidadFDT" query="idContenido = 'informacionVelocidad_compraBolsas_bam_fdt_pp'" useCache="true"  />
	
<!-- MENSAJES -->
<jsp:include page="../common/messages_table.jsp"></jsp:include>
<h:panelGroup rendered="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean != null}">
<h1>Saldo de bolsas</h1>
					
					<h2 class="ico_cuota">
						<strong>Tiempo restante de navegaci&oacute;n</strong>
					</h2>					
					<!-- Caja cuota restante -->

					<div class="caja_cuota_no_div clearfix">
						<div class="caja_cuota_no_div_arriba"></div>
						<div class="caja_cuota_no_div_centro tiempoNavegacion" >
							
							<div class="dato_tiempo clearfix">								
								<div class="caja_tiempo">
									<div class="caja_top"></div>
									<div class="caja_middle">
										<span class="numero"><h:outputText value="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoBolsaPPBAM.dias}"/></span>
										<span class="texto">d&iacute;as</span>

									</div>
									<div class="caja_bottom"></div>
								</div>
								
								<div class="caja_tiempo">
									<div class="caja_top"></div>
									<div class="caja_middle">
										<span class="numero"><h:outputText value="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoBolsaPPBAM.horas}"/></span>
										<span class="texto">horas</span>

									</div>
									<div class="caja_bottom"></div>
								</div>
								
								<div class="caja_tiempo">
									<div class="caja_top"></div>
									<div class="caja_middle">
										<span class="numero"><h:outputText value="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoBolsaPPBAM.minutos}"/></span>
										<span class="texto">min</span>

									</div>
									<div class="caja_bottom"></div>
								</div>
							</div>
							
							<h:panelGroup rendered="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoBolsaPPBAM.tieneSaldo}">
							<div class="lista_detalles">
								<strong>Bolsa : <h:outputText value="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoBolsaPPBAM.nombreBolsa}"/></strong>
								<ul>
									<li>Tr&aacute;fico incluido: <cm:getProperty node="${traficoPlan[0]}" name="html" /></li>
									<li>Velocidad del plan: <cm:getProperty node="${velocidadPlan[0]}" name="html" /></li>
								</ul>
							</div>
							</h:panelGroup>
							<h:panelGroup rendered="#{!suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoBolsaPPBAM.tieneSaldo}">
                            <div class="lista_detalles mensaje_no_bolsa">
							<strong>Para navegar debes adquirir una bolsa.</strong>
							</div>
							</h:panelGroup>
						</div>

				<div class="caja_cuota_no_div_abajo"></div>
					</div>
					<!-- fin Caja tiempo restante -->              
             <h:panelGroup rendered="#{suscripcionesController.submercado!='FDT' || (suscripcionesController.submercado=='FDT' && suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoBolsaPPBAM.tieneSaldo)}">
                    <h2 class="ico_cuota">
						<strong>Cuota y velocidad de navegaci&oacute;n.</strong>
					</h2>
					<div class="caja_cuota_externo clearfix">
						<div class="caja_cuota clearfix">
							
							<div class="contenedor_cuota_dato">
								<div class="clearfix">
									<div class="numero_dato">
										<span class="numero"></span>
										<span class="tasa_transfer">&nbsp;</span>
									</div>
									<div class="grafica_dato grafica_100"></div>
									<div class="restante_dato">
                                        <br></br> 
										<strong><cm:getProperty node="${traficoPlan[0]}" name="html" /></strong>
										
									</div>
								</div>
							</div>
							
							<div class="contenedor_maxima_velocidad">

								<span class="texto_velocidad clearfix">
									<h:panelGroup rendered="#{suscripcionesController.submercado=='FDT'}">
	                                  <cm:getProperty node="${informacionVelocidadFDT[0]}" name="html"/>
								    </h:panelGroup>
								    <h:panelGroup rendered="#{suscripcionesController.submercado!='FDT'}">
	                                  <cm:getProperty node="${informacionVelocidad[0]}" name="html"/>
								    </h:panelGroup>																
								</span>
								<div class="clearfix">
									<div class="velocidad_dato"><cm:getProperty node="${velocidad_max_navegacion[0]}" name="html" /></div>
									<div class="velocidad_tasa_transfer"><cm:getProperty node="${velocidad_max_navegacion[0]}" name="titulo" /></div>
								</div>
							</div>
							
						</div>
					</div>
					<!-- fin Caja cuota restante -->	
			</h:panelGroup>	
					
					
					<h2 class="ico_cuota">
						<strong>Bolsas</strong>
					</h2>
										
					<p><cm:getProperty node="${mensajesaldo[0]}" name="html" /></p>
					
					<h:panelGroup rendered="#{!suscripcionesController.saldoSuficiente}">
						<div class="mensaje_saldo clearfix">
							<span class="saldo">Saldo: $<h:outputText value="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoRecargas}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></span>
							<a href="<r:pageUrl pageLabel='${suscripcionesController.pageLabelRecargaEnLinea}'></r:pageUrl>">Ir a Recargas</a>
	
						</div>
					</h:panelGroup>
					
					
					<div class="caja_bolsas_bam clearfix">
					<it:iterator value="#{suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.bolsasDisponibles}" var="bolsa" rowIndexVar="rowIndex">
						<div class="bolsa_bam <h:outputText value="#{rowIndex % 2 == 0 ? 'impar' : '' }"/> clearfix">
							<div class="paso2 clearfix">
								<div class="columna_nombrePlan"><strong><h:outputText value="#{bolsa.nombreBolsa}"/></strong></div>
								<div class="columna_precioPlan">$<h:outputText value="#{bolsa.precio}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></div>
								<div class="columna_cantidadMb"><cm:getProperty node="${cantidadMB[0]}" name="html" /></div>
								<div class="columna_comprar">
								    <h:panelGroup rendered="#{(suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoRecargas < bolsa.precio) && bolsa.estadoCompra == 'DISPONIBLE' }">
										<div class="mensajeSaldoComprarBolsaPrepago" style="width: 200px;">
											No tienes saldo suficiente para <br/>realizar esta operaci&oacute;n. 
											<a href="<r:pageUrl pageLabel='${suscripcionesController.pageLabelRecargaEnLinea}'></r:pageUrl>">Ir a Recargas</a>
										</div>
                                    </h:panelGroup>
								    <h:panelGroup rendered="#{(suscripcionesController.saldoYBolsaDisponiblesCompraBAMBean.saldoRecargas > bolsa.precio) && bolsa.estadoCompra == 'DISPONIBLE' }">
									<a class="btnVerdeDelgado alargar ir_paso3" href="#"><span>Comprar</span></a>
									</h:panelGroup>
							        <h:panelGroup rendered="#{bolsa.estadoCompra == 'ACTIVA'}">
									<cm:getProperty node="${bolsaActiva[0]}" name="html"/>
									</h:panelGroup>
									<h:panelGroup rendered="#{bolsa.estadoCompra == 'NO DISPONIBLE' }">
									<a class="btnDesactivado alargar" style="text-decoration: none;" href="javascript:void(0);"><span>Comprar</span></a>
									</h:panelGroup>
								</div>
							</div>
							<div class="paso3 clearfix">
								<div class="columna_nombrePlan"><strong><h:outputText value="#{bolsa.nombreBolsa}"/></strong></div>
								<div class="columna_precioPlan">$<h:outputText value="#{bolsa.precio}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></div>

								<div class="columna_cantidadMb"><strong>Vas a comprar esta bolsa</strong></div>
								<div class="columna_comprar">
								 <h:form>
								 	<jsp:include page="/token.jsp" flush="true"/>     
		                            <h:commandLink immediate="true" value="" action="#{suscripcionesController.comprarBolsaPPBAM}" styleClass="btnVerdeDelgado alargar flotar_izq"><span>Confirmar</span>
		                            <f:param name="cartaServicio" value="#{bolsa.idBolsa}"/>
		                            <f:param name="valorBolsa" value="#{bolsa.precio}"/>
		                            <f:param name="nombreBolsa" value="#{bolsa.nombreBolsa}"/>
		                            </h:commandLink>
		                            <a href="#" class="enlace_cancelar ir_paso2">Cancelar</a>
   	                             </h:form>
								</div>
							</div>
							<div class="paso4 clearfix">

								<div class="columna_nombrePlan"><strong>Plan 1 d&iacute;a</strong></div>
								<div class="columna_mensajeExito">
									<span class="tituloExito">Has comprado esta plan</span>
									<span class="textoExito">Este plan queda pendiente de activaci&oacute;n, se activar&aacute; dentro de 4 horas.</span>
								</div>

							</div>
						</div>
						<br>
						</it:iterator>
					</div>
				
					<br>
					<div class="debessaber">
						<cm:getProperty node="${debessaber[0]}" name="html" />
					</div>
			
</h:panelGroup>
</f:view>