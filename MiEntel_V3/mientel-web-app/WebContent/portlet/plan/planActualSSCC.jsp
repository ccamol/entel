<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>

<pref:getPreference name="idContenido" var="idContenido" defaultValue="tituloPlanActual" />
<c:set var="query">idContenido = '${idContenido}'</c:set>

<cm:search id="infoTasacionUnidad" query="idContenido = 'infoTasacionUnidad'" useCache="true"  />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view beforePhase="#{planController.obtenerPlanActual}">

<h:panelGroup rendered="#{(planController.planActualSSCC ne null)}">
		
<h1><cm:search id="infoTitulo" query="${query}" useCache="false"/><cm:getProperty node="${infoTitulo[0]}" name="titulo"/></h1>

	<!-- ESTRUCTURA TARIFARIA -->
	<div class="estructuraTarifaria">
		<h2 class="superchip">
			<strong><h:outputText value="#{planController.planActualSSCC.nombrePlan}"/> </strong>
			<c:if test="${!(profile.mercado eq miEntelBean.siglaSuscripcion)}">
				<strong>: </strong>
				<h:outputText value="#{planController.descripcionPlan}"/>
			</c:if>			
		</h2>

	<h:panelGroup rendered="#{planController.planResumen.mercadoCuentaControlada}" >

			<div class="info info_saldo clearfix">
                  <div class="ico_saldo div_saldo">
                      Saldo: <span id="saldoPlanActual" class="resaltado">&nbsp;$&nbsp; 
                      <h:outputText value="#{planController.planResumen.saldo}">
                      	<f:convertNumber currencyCode="CLP" locale="es" />
                      </h:outputText></span>
                  </div>

                  <div class="ico_cal">
                      Expira: <h:outputText value="#{planController.planResumen.fechaExpiracion}"><f:convertDateTime pattern="dd/MM/yyyy" locale="es" /></h:outputText>
                  </div>
              </div>
		</h:panelGroup>		

		<!-- Tabla Tarificacion -->
		<div class="tabla">	
			<div class="planes_header_tabla clearfix">
				<div class="top"><span></span></div>
				<div class="main plan">
					<table>
						<tbody>
						
							<!--   ESTRUCTURA DEL CONTENIDO SUPERIOR DE LA TABLA - COLUMNAS   -->

						<tr>
							<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan ne tipoPlanBean.planGrupalEmpresasDatosExcedidos )}">
								<th rowspan="2" width="30%"><b>Cargo fijo</b></th>
							</h:panelGroup>
							
							<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planGrupalEmpresasDatosExcedidos )}">
								<th rowspan="3" width="20%"><b>Cargo fijo</b></th>
							</h:panelGroup>
							
							<!-- PLAN TARIFA PLANA - SS CC -->
								<!-- TipoPlanNormal SS -->
									<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaPlanaSs) and
									(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaSuscripcion)}">
										<th rowspan="2"><b>Minutos Incluidos</b></th>
										<th class="borde_inferior" colspan="2"><b>Valor por minuto Adicional</b></th>
									</h:panelGroup>
								<!-- TipoPlanNormal SS -->
		
								
								<!-- TipoPlanNormal CC -->
									<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaPlanaCc) and
									(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada) and (planController.planCCNuevo ne planController.planActualSSCC.codbscs2)}">
										<th class="borde_inferior" colspan="2"><b>Valor por minuto Adicional</b></th>
									</h:panelGroup>
									
									<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaPlanaCc) and
									(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada) and (planController.planCCNuevo eq planController.planActualSSCC.codbscs2)}">
										<th class="borde_inferior" colspan="3"><b>Incluido<b></th>
										<th class="borde_inferior" colspan="2"><b>Tarifas</b></th>
										<th class="borde_inferior" colspan="2"><b>Adicional</b></th>
									</h:panelGroup>
								<!-- TipoPlanNormal CC -->
							<!-- PLAN TARIFA PLANA - SS CC  -->
							
							
							<!-- PLAN RED - SS CC -->
								<!-- TipoPlanRed SS -->
										<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedSs) and
										(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaSuscripcion)}">
											
											<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional ne tipoPlanBean.flagHidden)}">
												<th class="borde_inferior" colspan="2"><b>Incluido<b></th>
												<th class="borde_inferior" colspan="4"><b>Adicional</b></th>
											</h:panelGroup>
											<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional eq tipoPlanBean.flagHidden)}">
												<th class="borde_inferior" colspan="1"><b>Incluido<b></th>
												<th class="borde_inferior" colspan="4"><b>Adicional</b></th>
											</h:panelGroup>
										</h:panelGroup>
								<!-- TipoPlanRed SS -->
								
								
								<!-- TipoPlanRed CC -->
										<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedCc) and
										(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada)}">
											<th class="borde_inferior" colspan="2"><b>Valor por Minuto</b></th>
										</h:panelGroup>
								<!-- TipoPlanRed CC -->
							<!-- PLAN RED - SS CC -->
							
							
							<!--   PLAN GLOBAL - TARIFA UNICA FRECUENTE SS  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaUnicaFrec)}">
										<th class="borde_inferior" colspan="2"><b>Minutos incluidos</b></th>
										<th class="borde_inferior" colspan="2"><b>Valor por Minuto<b></th>
								</h:panelGroup>
							<!--  PLAN GLOBAL - TARIFA UNICA FRECUENTE SS  -->
							

							<!--   PLAN JOVEN - SS     -->
							<!--   PLAN FULL - SS      -->
							<!--   PLAN TARIFA UNICA - SS  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planJoven or 
									planController.planActualSSCC.tipoPlan eq tipoPlanBean.planFull or 
									planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaUnica)}">
								
									<th rowspan="2"><b>Minutos incluidos</b></th>
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<th><b><h:outputText value="#{tasacion.nombre}"/></b></th>
									 </it:iterator>
									
								</h:panelGroup>
							<!--   PLAN JOVEN - SS     -->
							<!--   PLAN FULL - SS      -->
							<!--   PLAN TARIFA UNICA - SS  -->
							

							<!--  PLAN RED FIJA - SS CC -->
							<!-- TipoPlanRedFija SS-->
									<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedFija) and
									(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaSuscripcion)}">
										<th class="borde_inferior" colspan="1"><b>Incluido<b></th>
										<th class="borde_inferior" colspan="5"><b>Adicional</b></th>
									</h:panelGroup>
							<!-- TipoPlanRedFija SS-->
							
							<!-- TipoPlanRedFijaCC-->
									<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedFija) and
									(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada)}">
										<th class="borde_inferior" colspan="1"><b>Incluido<b></th>
										<th class="borde_inferior" colspan="5"><b>Adicional</b></th>
									</h:panelGroup>
							<!-- TipoPlanRedFijaCC-->
							<!--  PLAN RED FIJA - SS CC -->
							

							<!--  PLAN FAMILIA - SS  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planFamilia)}">
	
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex ge 1) and (tasacionIndex le 2) }">
										<th><b><h:outputText value="#{tasacion.nombre}"/></b></th>
										</h:panelGroup>
									</it:iterator>
	
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{tasacionIndex eq 3}">
										<th><b><h:outputText value="#{tasacion.nombre}"/></b></th>
										</h:panelGroup>
									</it:iterator>
									
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{tasacionIndex eq 0}">
										<th><b><h:outputText value="#{tasacion.nombre}"/></b></th>
										</h:panelGroup>
									</it:iterator>
								</h:panelGroup>
							<!--  PLAN FAMILIA - SS  -->
							
							<!--  PLAN RED EMPRESA - SS  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedEmpresa)}">
									<th rowspan="2">A m&oacute;viles Entel</th>
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion">
										<th><b><h:outputText value="#{tasacion.nombre}"/></b></th>
									</it:iterator>
								</h:panelGroup>
							<!--  PLAN RED EMPRESA - SS -->
							
							
							<!--  PLAN MULTIMEDIA - CC -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaCc)}">
									<th class="borde_inferior" colspan="2"><b>Incluido<b></th>
									<th class="borde_inferior" colspan="3"><b>Tarifas</b></th>
								</h:panelGroup>
							<!--  PLAN MULTIMEDIA - CC -->
							
							
							<!--  PLAN MULTIMEDIA IPHONE - SS  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaIphone)}">
								<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional ne tipoPlanBean.flagHidden)}">
									<th class="borde_inferior" colspan="4"><b>Incluido<b></th>
									<th class="borde_inferior" colspan="4"><b>Adicional</b></th>
								</h:panelGroup>
								<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional eq tipoPlanBean.flagHidden)}">
									<th class="borde_inferior" colspan="3"><b>Incluido<b></th>
									<th class="borde_inferior" colspan="3"><b>Adicional</b></th>
								</h:panelGroup>
								
								</h:panelGroup>
							<!--  PLAN MULTIMEDIA IPHONE - SS  -->
							
							<!--  PLAN MULTIMEDIA RED - SS  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRed)}">
									<th class="borde_inferior" colspan="4"><b>Incluido<b></th>
									<th class="borde_inferior" colspan="4"><b>Adicional</b></th>
								</h:panelGroup>
							<!--  PLAN MULTIMEDIA RED - SS  -->
							
							<!--  PLAN MULTIMEDIA RED EXCEDIDO - SS  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedido )and
							(planController.cargarCargoFijo eq '1' )}">
									<th class="borde_inferior" colspan="4"><b>Incluido<b></th>
									<th class="borde_inferior" colspan="4"><b>Tarifas adicionales</b></th>
								</h:panelGroup>
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedido ) and
							(planController.cargarCargoFijo ne '1' )}">
							
							       <th class="borde_inferior" colspan="4"><b>Incluido<b></th>
								</h:panelGroup>
							<!--  PLAN MULTIMEDIA RED EXCEDIDO - SS  -->
							
							<!--  PLAN MULTIMEDIA RED EXCEDIDO TODO DESTINO - SS  MODIFICADO ESTRUCTURA TARIFARIA-->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedidoTodoDestino ) and
							(planController.cargarCargoFijo ne '1' )}">
							
							<h:panelGroup rendered="#{planController.planActualSSCC.totalMinutos ne '0'}">
							  <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasaciones.valor eq 0) }">
										<th class="borde_inferior" colspan="2"><b>Incluido<b></th>
										</h:panelGroup>
							  </it:iterator>				
							
							<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasaciones.valor ne 0) }">
										<th class="borde_inferior" colspan="3"><b>Incluido<b></th>
										</h:panelGroup>
							  </it:iterator>
							  </h:panelGroup>				
							
							<h:panelGroup rendered="#{planController.planActualSSCC.totalMinutos eq '0'}">
							<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasaciones.valor eq 0) }">
										<th class="borde_inferior" colspan="1"><b>Incluido<b></th>
										</h:panelGroup>
							  </it:iterator>
							  <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasaciones.valor ne 0) }">
										<th class="borde_inferior" colspan="2"><b>Incluido<b></th>
										</h:panelGroup>
							  </it:iterator>
							
							</h:panelGroup>
							
							
									<th class="borde_inferior" colspan="4"><b>Tarifas adicionales</b></th>
								</h:panelGroup>
								
								
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedidoTodoDestino ) and
							(planController.cargarCargoFijo eq '1' )}">
							
							<h:panelGroup rendered="#{planController.planActualSSCC.totalMinutos ne '0'}">
								<th class="borde_inferior" colspan="3"><b>Incluido<b></th>
                            </h:panelGroup>
							<h:panelGroup rendered="#{planController.planActualSSCC.totalMinutos eq '0'}">
								<th class="borde_inferior" colspan="2"><b>Incluido<b></th>
							</h:panelGroup>
								</h:panelGroup>
								
							<!--  PLAN MULTIMEDIA RED EXCEDIDO TODO DESTINO - SS  MODIFICADO ESTRUCTURA TARIFARIA-->
							
					<!-- --------------------------------------------------- INICIO -------------------------------------------------------------------------- -->			
						
							<!--  ESTRUCTURA TARIFARIA Planes Empresa -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planEmpresa ) and
							(planController.cargarCargoFijo ne '1' )}">
							
							<h:panelGroup rendered="#{planController.planActualSSCC.totalMinutos ne '0'}">
							  <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasaciones.valor eq 0) }">
										<th class="borde_inferior" colspan="2"><b>Incluido<b></th>
										</h:panelGroup>
							  </it:iterator>				
							
							<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasaciones.valor ne 0) }">
										<th class="borde_inferior" colspan="3"><b>Incluido<b></th>
										<th class="borde_inferior" colspan="4"><b>Tarifas adicionales</b></th>
										</h:panelGroup>
							  </it:iterator>
							  </h:panelGroup>				
							
							<h:panelGroup rendered="#{planController.planActualSSCC.totalMinutos eq '0'}">
							<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasaciones.valor eq 0) }">
										<th class="borde_inferior" colspan="1"><b>Incluido<b></th>
										
										</h:panelGroup>
							  </it:iterator>
							  <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasaciones.valor ne 0) }">
										<th class="borde_inferior" colspan="1"><b>Incluido<b></th>
										<th class="borde_inferior" colspan="4"><b>Tarifas adicionales</b></th>
										
										</h:panelGroup>
							  </it:iterator>
							
							</h:panelGroup>
							
							
									<!--  <th class="borde_inferior" colspan="4"><b>Tarifas adicionales</b></th>  -->
								</h:panelGroup>
								
								
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planEmpresa ) and
							(planController.cargarCargoFijo eq '1' )}">
							
							<h:panelGroup rendered="#{planController.planActualSSCC.totalMinutos ne '0'}">
								<th class="borde_inferior" colspan="3"><b>Incluido<b></th>
                            </h:panelGroup>
							<h:panelGroup rendered="#{planController.planActualSSCC.totalMinutos eq '0'}">
								<th class="borde_inferior" colspan="1"><b>Incluido<b></th>
							</h:panelGroup>
							
								</h:panelGroup>
								
							<!--  PLAN MULTIMEDIA RED EXCEDIDO TODO DESTINO - SS  MODIFICADO ESTRUCTURA TARIFARIA-->
							
						
					<!-- -----------------------------------------------------------FIN------------------------------------------------------------------ -->
							
							<!--  PLAN MULTIMEDIA RED EXCEDIDO TODO DESTINO EMPRESAS - SS  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedidoTodoDestinoEmpresas )}">
									<th class="borde_inferior" colspan="3"><b>Incluido<b></th>
									<th class="borde_inferior" colspan="2"><b>Adicional</b></th>
								</h:panelGroup>
							<!--  PLAN MULTIMEDIA RED EXCEDIDO TODO DESTINO - SS  -->	
							
							<!--  PLAN GRUPAL DATOS EXCEDIDOS EMPRESAS - SS  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planGrupalEmpresasDatosExcedidos )}">
									<tr>
									    <th class="borde_inferior" rowspan="2"><b>Cargo fijo por Linea</b></th>
									    <th class="borde_inferior" colspan="3"><b>Inclu&iacute;do</b></th>
									    <th class="borde_inferior" colspan="2"><b>Adicional</b></th>
								    </tr>															
								</h:panelGroup>
							<!--  PLAN GRUPAL DATOS EXCEDIDOS EMPRESAS - SS  -->
							
							<!--  OTROS PLANES - SS  -->
								<h:panelGroup rendered="#{((planController.planActualSSCC.tipoPlan gt tipoPlanBean.planMultimediaRed && 
								                            planController.planActualSSCC.tipoPlan gt tipoPlanBean.planMultimediaRedExcedido && 
								                            planController.planActualSSCC.tipoPlan gt tipoPlanBean.planMultimediaRedExcedidoTodoDestino &&
								                            planController.planActualSSCC.tipoPlan gt tipoPlanBean.planMultimediaRedExcedidoTodoDestinoEmpresas &&
								                            planController.planActualSSCC.tipoPlan gt tipoPlanBean.planGrupalEmpresasDatosExcedidos &&
								                            planController.planActualSSCC.tipoPlan gt tipoPlanBean.planEmpresa
								                            ))}">
								   <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion">
										<th><b><h:outputText value="#{tasacion.nombre}"/></b></th>
									</it:iterator>
								</h:panelGroup>
							<!--  OTROS PLANES - SS  -->

						</tr>
							<!--   ESTRUCTURA DEL CONTENIDO SUPERIOR DE LA TABLA - COLUMNAS   -->
						    

							<!--   ESTRUCTURA DEL CONTENIDO SUPERIOR DE LA TABLA - SUBCOLUMNAS   -->
							
							<!--  PLAN TARIFA PLANA - SS CC  -->
							<!-- TipoPlanNormal SS -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaPlanaSs) and
								(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaSuscripcion)}">
									<tr>
									   	<!-- Tasacion Horario Normal -->
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
												<h:panelGroup rendered="#{tasacionIndex eq 1}"> 
														<th><h:outputText value="#{tasacion.nombre}"></h:outputText></th> 
											  	</h:panelGroup> 
									   </it:iterator>
									   
									   <!-- Tasacion Horario Bajo -->
									   <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											  	<h:panelGroup rendered="#{tasacionIndex eq 0}"> 
											  	 		<th><h:outputText value="#{tasacion.nombre}"></h:outputText></th> 
											  	</h:panelGroup>
										</it:iterator>
									</tr>
								</h:panelGroup>
							<!-- TipoPlanNormal SS -->
							
							<!-- TipoPlanNormal CC -->
							<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaPlanaCc) and
								(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada) and (planController.planCCNuevo ne planController.planActualSSCC.codbscs2)}">
									<tr>
									   	<!-- Tasacion Horario Normal -->
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
												<h:panelGroup rendered="#{tasacionIndex eq 1}"> 
														<th><h:outputText value="#{tasacion.nombre}"></h:outputText></th> 
											  	</h:panelGroup> 
									   </it:iterator>
									   
									   <!-- Tasacion Horario Bajo -->
									   <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											  	<h:panelGroup rendered="#{tasacionIndex eq 0}"> 
											  	 		<th><h:outputText value="#{tasacion.nombre}"></h:outputText></th> 
											  	</h:panelGroup>
										</it:iterator>
									</tr>
								</h:panelGroup>
								
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaPlanaCc) and
									(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada) and (planController.planCCNuevo eq planController.planActualSSCC.codbscs2)}">
										<tr>
										
																					
										 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>	
										<th>Cuota tr&aacute;fico Internet m&oacute;vil</th>										
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>
										</tr>
										
									</h:panelGroup>
							
							<!-- TipoPlanNormal CC -->
							<!--  PLAN TARIFA PLANA - SS CC -->


							<!--  PLAN RED - SS CC  -->
							<!-- TipoPlanRed SS -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedSs) and
								(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaSuscripcion)}">
									<tr>
									
										<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional ne tipoPlanBean.flagHidden)}">
											 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											    <h:panelGroup rendered="#{tasacionIndex lt 2}">
												 <th><h:outputText value="#{tasacion.nombre}"/></th>
												</h:panelGroup>
											 </it:iterator>
										</h:panelGroup>
										
										<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional eq tipoPlanBean.flagHidden)}">
											<th>Minutos todo destino</th>
										</h:panelGroup>

										 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
										 </it:iterator>
									</tr>
								</h:panelGroup>
							<!-- TipoPlanRed SS -->
							
							<!-- TipoPlanRed CC -->		
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedCc) and
								(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada)}">
									<tr>
										 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
										</it:iterator>
									</tr>
								</h:panelGroup>
							<!-- TipoPlanRed CC-->
							<!--  PLAN RED - SS CC  -->


							<!--   PLAN GLOBAL - TARIFA UNICA FRECUENTE SS   -->
							<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaUnicaFrec)}">
								     <tr>
										 
										 <th>Todo Destino</th>
										 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
												 <h:panelGroup rendered="#{tasacionIndex eq 3}">
													<th><h:outputText value="#{tasacion.nombre}"/></th>
												</h:panelGroup>
										 </it:iterator>	
										 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
												 <h:panelGroup rendered="#{tasacionIndex lt 2}">
													<th><h:outputText value="#{tasacion.nombre}"/></th>
												</h:panelGroup>
										 </it:iterator>				
									 </tr>
								</h:panelGroup>
							<!--   PLAN GLOBAL - TARIFA UNICA FRECUENTE SS   -->


							<!--  PLAN RED FIJA - SS CC   -->
								<!-- TipoPlanRedFija SS -->
								    <h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedFija) and
								    (planController.planActualSSCC.tipoMercado eq miEntelBean.siglaSuscripcion)}">
									 <th>Minutos incluidos</th>
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
										<th><h:outputText value="#{tasacion.nombre}"/></th>
									 </it:iterator>
									</h:panelGroup>
								<!-- TipoPlanRedFija SS -->
								
								<!-- TipoPlanRedFija CC -->
								    <h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedFija) and
								    (planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada)}">
								     <th>Minutos Red Fija y 1 Nro Frecuente</th>
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
										<th><h:outputText value="#{tasacion.nombre}"/></th>
									 </it:iterator>
									</h:panelGroup>
								<!-- TipoPlanRedFija CC -->
							<!--  PLAN RED FIJA - SS CC  -->
							
							<!--  PLAN MULTIMEDIA - CC  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaCc)}">
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
										  <th><h:outputText value="#{tasacion.nombre}"/></th>
										</h:panelGroup>
									</it:iterator>
									
										<th rowspan="2">Internet M&oacute;vil</th>
										
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
										   <th><h:outputText value="#{tasacion.nombre}"/></th>
										</h:panelGroup>
									</it:iterator>
									
								</h:panelGroup>
							<!--  PLAN MULTIMEDIA - CC  -->

							<!--  PLAN MM IPHONE - SS  -->	
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaIphone)}">
										<tr>
										<!-- COLUMNA 1 -->
										<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional ne tipoPlanBean.flagHidden)}">
											<th>Minutos On-Net</th>
										</h:panelGroup>
										<!-- COLUMNA 1 -->
										
										<!-- COLUMNA 2 -->
										<th>Minutos todo destino</th>
										<!-- COLUMNA 2 -->
										
										<!-- COLUMNA 3 -->
										<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional ne tipoPlanBean.flagHidden)}"> 
											<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
												<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
												<th><h:outputText value="#{tasacion.nombre}"/></th>
												</h:panelGroup>
											</it:iterator>
										</h:panelGroup>
										<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional eq tipoPlanBean.flagHidden)}">
											<th width='70px'>SMS y MMS</th>
										</h:panelGroup>
										<!-- COLUMNA 3 -->
										
										<!-- COLUMNA 4 -->
										<th>Internet M&oacute;vil</th>
										<!-- COLUMNA 4 -->
										
										<!-- COLUMNA 5 -->
										<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional ne tipoPlanBean.flagHidden)}">
											<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
												<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
												<th><h:outputText value="#{tasacion.nombre}"/></th>
												</h:panelGroup>
											</it:iterator>
										</h:panelGroup>

										<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional eq tipoPlanBean.flagHidden)}">
											<th>Minutos</th>
											<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
												<h:panelGroup rendered="#{(tasacionIndex gt 1)}">
												<th><h:outputText value="#{tasacion.nombre}"/></th>
												</h:panelGroup>
											</it:iterator>
										</h:panelGroup>
										<!-- COLUMNA 5 -->
										
										
										</tr>
								</h:panelGroup>
							<!--  PLAN MM IPHONE - SS  -->
							
							
							<!--  PLAN MM RED - SS  -->	
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRed)}">
										<tr>
										<th>Minutos On-Net y Red Fija</th>
										<th>Minutos Otras compa&ntilde;&iacute;as</th>
										 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>
										<th>Internet M&oacute;vil</th>
										
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>
										</tr>
								</h:panelGroup>
							<!--  PLAN MM RED - SS  -->
							
							<!--  PLAN MM RED EXCEDIDO - SS  -->	
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedido)}">
										<tr>
										<th>Minutos a Entel o red Fija</th>
										<th>Minutos a otras compa&ntilde;&iacute;as</th>										
										<th>Cuota tráfico Internet móvil</th>			
										 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>
									   <h:panelGroup rendered="#{(planController.cargarCargoFijo eq '1' )}">						
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>
										</h:panelGroup>
										</tr>
										
								</h:panelGroup>
							<!--  PLAN MM RED EXCEDIDO - SS  -->
							
							<!--  PLAN MM RED EXCEDIDO TODO DESTINO - SS  MODIFICADO ESTRUCTURA TARIFARIA-->	
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedidoTodoDestino)}">
										<tr>
										<h:panelGroup rendered="#{planController.planActualSSCC.totalMinutos ne '0'}">	
										<th>Minutos todo destino</th>
										</h:panelGroup>
										<th>Cuota tráfico Internet móvil</th>											
										 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasacion.valor ne 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>
										<h:panelGroup rendered="#{(planController.cargarCargoFijo ne '1' )}">																			
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>
										</h:panelGroup>
										</tr>
								</h:panelGroup>
							<!--  PLAN MM RED EXCEDIDO TODO DESTINO - SS  MODIFICADO ESTRUCTURA TARIFARIA-->
							
							
							
						<!-- --------------------------------------------------- INICIO -------------------------------------------------------------------------- -->
						
						<!--  SS  MODIFICADO ESTRUCTURA TARIFARIA-->
												
						<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planEmpresa )}">
										<tr>
										<h:panelGroup rendered="#{planController.planActualSSCC.totalMinutos ne '0'}">	
										<th>Minutos todo destino</th>
										</h:panelGroup>
										<th>Cuota tráfico Internet móvil</th>								
										
										<h:panelGroup rendered="#{(planController.cargarCargoFijo ne '1' )}">			
										 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasacion.valor ne 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>
										</h:panelGroup>
										
										<h:panelGroup rendered="#{(planController.cargarCargoFijo ne '1' )}">																			
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>
										</h:panelGroup>
										</tr>
								</h:panelGroup>
						
						<!--  SS  MODIFICADO ESTRUCTURA TARIFARIA-->		
								
					    <!-- --------------------------------------------------- FIN -------------------------------------------------------------------------- -->
							<!--  PLAN MM RED EXCEDIDO TODO DESTINO EMPRESAS - SS  -->	
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedidoTodoDestinoEmpresas)}">
										<tr>
										<th>Minutos grupales a todo destino</th>										
										 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>
										<th>Cuotra Trafico</th>										
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
											<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
											<th><h:outputText value="#{tasacion.nombre}"/></th>
											</h:panelGroup>
										</it:iterator>
										</tr>
								</h:panelGroup>
							<!--  PLAN MM RED EXCEDIDO TODO DESTINO EMPRESAS - SS  -->	
							
							<!-- PLAN GRUPAL DATOS EXCEDIDOS EMPRESAS - SS -->
							<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planGrupalEmpresasDatosExcedidos)}">							
								<tr style="font-size: 10px">
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{tasacionIndex gt 0 and tasacionIndex lt 3}">										
											<td style="border: 1px solid #CBD9E6; text-align: center"><b><h:outputText value="#{tasacion.nombre}" /></b></td>
										</h:panelGroup>																			
									</it:iterator>
									
									<td style="border: 1px solid #CBD9E6; text-align: center"><b>Cuota tr&aacute;fico por l&iacute;nea</b></td>
									
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{tasacionIndex gt 2}">										
											<td style="border: 1px solid #CBD9E6; text-align: center"><b><h:outputText value="#{tasacion.nombre}" /></b></td>
										</h:panelGroup>																			
									</it:iterator>
								</tr>
							</h:panelGroup>	
							<!-- PLAN GRUPAL DATOS EXCEDIDOS EMPRESAS - SS -->

							<!--   ESTRUCTURA DEL CONTENIDO SUPERIOR DE LA TABLA - SUBCOLUMNAS   -->
						
						
						<tr><td></td></tr>
						<tr><td colspan="10"><div class="bottom"><span></span></div></td></tr>	
		
						</tbody>
						
						
						<tbody class="contenido_plan">
						
							<!--   ESTRUCTURA DEL CONTENIDO INTERNO DE LA TABLA - DATA   -->
						
						<tr>
						
							<td class="primera">$<h:outputText value="#{planController.planActualSSCC.cargoFijoPlan}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></td>
							
								<!--  PLAN TARIFA PLANA - SS CC  -->
								<!-- TipoPlanNormal SS -->
									<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaPlanaSs) and
									(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaSuscripcion)}">
									   <td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
									    
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
												<h:panelGroup rendered="#{tasacionIndex eq 1}"> 
														<td>$<h:outputText value="#{tasaciones.valor}" converter="planConverter"/></td> 
											  	</h:panelGroup> 
									   </it:iterator>
									   
									   <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
											  	<h:panelGroup rendered="#{tasacionIndex eq 0}"> 
											  	 		<td>$<h:outputText value="#{tasaciones.valor}" converter="planConverter"/></td> 
											  	</h:panelGroup>
										</it:iterator>
									</h:panelGroup>
								<!-- TipoPlanNormal SS -->
	
								<!-- TipoPlanNormal CC -->
									<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaPlanaCc) and
									(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada) and (planController.planCCNuevo ne planController.planActualSSCC.codbscs2)}">
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
												<h:panelGroup rendered="#{tasacionIndex eq 1}"> 
														<td>$<h:outputText value="#{tasaciones.valor}" converter="planConverter"/></td> 
											  	</h:panelGroup> 
									   </it:iterator>
									   
									   <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
											  	<h:panelGroup rendered="#{tasacionIndex eq 0}"> 
											  	 		<td>$<h:outputText value="#{tasaciones.valor}" converter="planConverter"/></td> 
											  	</h:panelGroup>
										</it:iterator>
									</h:panelGroup>
									
									<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaPlanaCc) and
									(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada) and (planController.planCCNuevo eq planController.planActualSSCC.codbscs2)}">
									
									
																		 
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
										<td><h:outputText value="#{planController.planActualSSCC.descIMovil}"/></td>
										
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 1)}">
											<td><h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex gt 0) and (tasacionIndex gt 1)}">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									</h:panelGroup>
							   <!-- TipoPlanNormal CC -->
							
							<!--  PLAN TARIFA PLANA - SS CC  -->
								

							<!--  PLAN RED - SS CC -->
							<!-- TipoPlanRed SS -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedSs) and
								(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaSuscripcion)}">
										<td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
										
										<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional ne tipoPlanBean.flagHidden)}">
											<td><h:outputText value="#{planController.planActualSSCC.totalMinutosAdicional}" converter="planConverter"/></td>
										</h:panelGroup>
										
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
											<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</it:iterator>
								</h:panelGroup>
							<!-- TipoPlanRed SS -->

							<!-- TipoPlanRed CC -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedCc) and
								(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada)}">
										<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
											<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</it:iterator>
								</h:panelGroup>
							<!-- TipoPlanRed CC -->
							<!--  PLAN RED - SS CC -->
							

							<!--   PLAN GLOBAL - TARIFA UNICA FRECUENTE SS   -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaUnicaFrec)}" >
									<td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
									
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										 <h:panelGroup rendered="#{tasacionIndex eq 3}">
										 	<td><h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										 </h:panelGroup>
									</it:iterator>
									
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										 <h:panelGroup rendered="#{tasacionIndex lt 2}">
										 	<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										 </h:panelGroup>
									</it:iterator>
									
								</h:panelGroup>
							<!--   PLAN GLOBAL - TARIFA UNICA FRECUENTE SS   -->
							

							<!--  PLAN JOVEN - SS          -->
							<!--  PLAN FULL - SS           -->
							<!--  PLAN TARIFA UNICA - SS   -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planJoven) or 
								(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planFull) or 
								(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planTarifaUnica)}" >
									<td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
									
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
									</it:iterator>
								</h:panelGroup>
							<!--    PLAN JOVEN - SS          -->
							<!--    PLAN FULL - SS           -->
							<!--    PLAN TARIFA UNICA - SS   -->
							

							<!--  PLAN RED FIJA - SS CC -->
							<!-- TipoPlanRedFija SS -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedFija) and
								(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaSuscripcion)}">
									<td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
									
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
									</it:iterator>
	
								</h:panelGroup>
							<!-- TipoPlanRedFija SS -->
							
							<!-- TipoPlanRedFija CC -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedFija) and
								(planController.planActualSSCC.tipoMercado eq miEntelBean.siglaCuentaControlada)}">
								
								    <td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
								
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
									</it:iterator>
	
								</h:panelGroup>
							<!-- TipoPlanRedFija CC -->
							<!--  PLAN RED FIJA - SS CC -->
							
							<!--  PLAN FAMILIA - SS  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planFamilia)}">
	
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex ge 1) and (tasacionIndex le 2)}">
										<td><h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{tasacionIndex eq 3}">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{tasacionIndex eq 0}">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
								
								</h:panelGroup>
							<!--  PLAN FAMILIA - SS  -->
							
							<!--  PLAN RED EMPRESA - SS  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planRedEmpresa)}">
									<td>Ilimitados</td>
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
									</it:iterator>
								</h:panelGroup>
							<!--   PLAN RED EMPRESA - SS  -->
							
							
							<!--  PLAN MULTIMEDIA - CC -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaCc)}">
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
										<td>$<h:outputText value="#{tasaciones.valor}" converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									
									<td><h:outputText value="#{planController.planActualSSCC.descIMovil}"/></td>
										
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
										<td>$<h:outputText value="#{tasaciones.valor}" converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									
								</h:panelGroup>
							<!--  PLAN MULTIMEDIA - CC -->
	
							<!--  PLAN MULTIMEDIA IPHONE - SS -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaIphone)}">
									
									<td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
										
									<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutosAdicional ne tipoPlanBean.flagHidden)}">
										<td><h:outputText value="#{planController.planActualSSCC.totalMinutosAdicional}" converter="planConverter"/></td>
									</h:panelGroup>									
									 
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
										<td><h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									
									<td><h:outputText value="#{planController.planActualSSCC.descIMovil}"/></td>
										
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									
								</h:panelGroup>
							<!--  PLAN MULTIMEDIA IPHONE - SS  -->
							
							
							<!--  PLAN MM RED - SS -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRed)}">
								
									<td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
									<td><h:outputText value="#{planController.planActualSSCC.totalMinutosAdicional}" converter="planConverter"/></td>
									 
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
										<td><h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									
									<td><h:outputText value="#{planController.planActualSSCC.descIMovil}"/></td>
										
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									
								</h:panelGroup>
							<!--  PLAN MM RED - SS  -->
							
						    <!--  PLAN MM RED EXCEDIDO - SS -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedido)}">
								
									<td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
									<td><h:outputText value="#{planController.planActualSSCC.totalMinutosAdicional}" converter="planConverter"/></td>
									<td><h:outputText value="#{planController.planActualSSCC.descIMovil}"/></td> 
									
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
										<td><h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>	
									<h:panelGroup rendered="#{(planController.cargarCargoFijo eq '1' )}">									
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									</h:panelGroup>
								</h:panelGroup>
							<!--  PLAN MM RED  EXCEDIDO - SS  -->
							
							<!--  PLAN MM RED EXCEDIDO TODO DESTINO - SS  MODIFICADO ESTRUCTURA TARIFARIA-->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedidoTodoDestino)}">
									<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutos ne '0' )}">	
										<td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
									</h:panelGroup>
									<td><h:outputText value="#{planController.planActualSSCC.descIMovil}"/></td>
																		 
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasaciones.valor ne 0)}">
										<td><h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									<h:panelGroup rendered="#{(planController.cargarCargoFijo ne '1' )}">	
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									</h:panelGroup>
									
								</h:panelGroup>
							<!--  PLAN MM RED  EXCEDIDO TODO DESTINO - SS  MODIFICADO ESTRUCTURA TARIFARIA -->
							
							<!-- --------------------------------------------------- INICIO -------------------------------------------------------------------------- -->
							
								<!--  ESTRUCTURA TARIFARIA-->
								
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planEmpresa )}">
									<h:panelGroup rendered="#{(planController.planActualSSCC.totalMinutos ne '0' )}">	
										<td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
									</h:panelGroup>
									<td><h:outputText value="#{planController.planActualSSCC.descIMovil}"/></td>
									
									<h:panelGroup rendered="#{(planController.cargarCargoFijo ne '1' )}">									 
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0) and (tasaciones.valor ne 0)}">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
								 </h:panelGroup>
										
									<h:panelGroup rendered="#{(planController.cargarCargoFijo ne '1' )}">	
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									</h:panelGroup>
									
								</h:panelGroup>
							<!--   ESTRUCTURA TARIFARIA -->
							
							<!-- --------------------------------------------------- FIN -------------------------------------------------------------------------- -->
							

							<!--  PLAN MM RED EXCEDIDO TODO DESTINO EMPRESAS- SS -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedidoTodoDestinoEmpresas)}">
								
									<td><h:outputText value="#{planController.planActualSSCC.totalMinutos}" converter="planConverter"/></td>
																		 
									 <it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex eq 0)}">
										<td><h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									
									<td><h:outputText value="#{planController.planActualSSCC.descIMovil}"/></td>
										
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<h:panelGroup rendered="#{(tasacionIndex gt 0)}">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
										</h:panelGroup>
									</it:iterator>
									
								</h:panelGroup>
							<!--  PLAN MM RED  EXCEDIDO TODO DESTINO EMPRESAS- SS  -->
							
							<!-- PLAN GRUPAL DATOS EXCEDIDOS EMPRESAS - SS -->
							<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planGrupalEmpresasDatosExcedidos)}">												  			
								<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasacion" rowIndexVar="tasacionIndex">
									<h:panelGroup rendered="#{tasacionIndex eq 0 or tasacionIndex gt 2}">
										<td>$<h:outputText value="#{tasacion.valorNumerico}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></td>
									</h:panelGroup>
									
									<h:panelGroup rendered="#{tasacionIndex eq 1}">																			
										<td><h:outputText value="#{planController.planActualSSCC.totalMinutosNumerico}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></td>
									</h:panelGroup>	
									
									<h:panelGroup rendered="#{tasacionIndex eq 2}">										
										<td><h:outputText value="#{tasacion.valorNumerico}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></td>
										<td><h:outputText value="#{planController.planActualSSCC.descIMovil}" /></td>
									</h:panelGroup>																			
								</it:iterator>
							</h:panelGroup>
							<!-- PLAN GRUPAL DATOS EXCEDIDOS EMPRESAS - SS -->

							<!--  OTROS PLANES SS CC  -->
								<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan gt tipoPlanBean.planMultimediaRed && 
								                           planController.planActualSSCC.tipoPlan gt tipoPlanBean.planMultimediaRedExcedido &&
								                           planController.planActualSSCC.tipoPlan gt tipoPlanBean.planMultimediaRedExcedidoTodoDestino &&
								                           planController.planActualSSCC.tipoPlan gt tipoPlanBean.planMultimediaRedExcedidoTodoDestinoEmpresas &&
								                           planController.planActualSSCC.tipoPlan gt tipoPlanBean.planGrupalEmpresasDatosExcedidos &&
								                           planController.planActualSSCC.tipoPlan gt tipoPlanBean.planEmpresa )}">
									<it:iterator value="#{planController.planActualSSCC.listaTasaciones}" var="tasaciones" rowIndexVar="tasacionIndex">
										<td>$<h:outputText value="#{tasaciones.valor}"  converter="planConverter"/></td>
									</it:iterator>
								</h:panelGroup>
							<!--  OTROS PLANES SS CC  -->
						</tr>
						
					    <!--   ESTRUCTURA DEL CONTENIDO INTERNO DE LA TABLA  DATA   -->

						</tbody>
						</table>					
				</div>
			</div>
			
		</div>
		<!--/tabla-->				
	</div>	
	
	<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedido ) or
	(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedidoTodoDestino ) or
	(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRedExcedidoTodoDestinoEmpresas )   }">
		<div class="disclaimer"><h:outputText value="#{planController.mensajePlanesMMExcedidosCM }" escape="false"></h:outputText></div>
	</h:panelGroup>
	
	
	<div class="disclaimer">
	   <cm:getProperty node="${infoTasacionUnidad[0]}" name="html"/>
	</div>
	
	<h:panelGroup rendered="#{(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaIphone) or
	(planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaRed) or (planController.planActualSSCC.tipoPlan eq tipoPlanBean.planMultimediaCc)}">
		<div class="disclaimer"><h:outputText value="#{planController.mensajeVelocidadTransmisionCM}" escape="false"></h:outputText></div>
	</h:panelGroup>
	
	<!-- /ESTRUCTURA TARIFARIA -->

	<div class="separacion_planes"></div>
	
	</h:panelGroup>
	
	<!-- MENSAJES -->
	<jsp:include page="../common/messages_table.jsp"></jsp:include>

</f:view>
