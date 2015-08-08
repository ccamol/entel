<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>

<f:view beforePhase="#{planBAMController.obtenerPlanBAMActual}">

<cm:search id="velocidad4G" query="idContenido = 'velocidad4G'" useCache="false"/>
<cm:search id="vel4GZonaExtrema" query="idContenido = 'vel4GZonaExtrema'" useCache="false"/>


<h1>Plan</h1>
<h:panelGroup rendered="#{(planBAMController.planBAMActual ne null)}">

			<!-- ESTRUCTURA TARIFARIA -->
			<div class="estructuraTarifaria">
				<h2 class="superchip">
					<strong><h:outputText value="#{planBAMController.planBAMActual.nombrePlan}"/></strong> 
					<!--   : <h:outputText value="#{planBAMController.descripcionPlan}"/> -->
				</h2>
						<!-- Tabla Tarificacion -->
						<div class="tabla">	
							<div class="planes_header_tabla clearfix">
								<div class="top"><span></span></div>
								<div class="main plan">
									<table>
		
										<tbody>
								     	<tr>
                                         <th width="85">Plan</th>
								         <th width="65">Cargo Fijo</th>
								         <h:panelGroup rendered="#{planBAMController.planBAMActual.valorAdicionalMB != ''}">
								         <th width="94">Valor Adicional MB</th>
								         </h:panelGroup>
								         <th width="94">Cuota Tr&aacute;fico Incluido</th>
								         <th width="104" class="">Velocidad M&aacute;xima de Descarga en 3G</th>
								         <th width="104" class="ultimo">Velocidad M&aacute;xima de Descarga en 4G</th>
										</tr>
										</tbody>
										</table>					
								</div>
								<div class="bottom"><span></span></div>
							</div>
							
							<div class="contenido_tabla">
								<table>
									<tbody>
										<tr>
		                                <td width="85"><h:outputText value="#{planBAMController.planBAMActual.nombrePlan}"/></td>
							         	<td width="65">$<h:outputText value="#{planBAMController.planBAMActual.cargoFijoPlan}"> 
							        	 <f:convertNumber currencyCode="CLP" locale="es" /></h:outputText>
							        	</td>
							        	<h:panelGroup rendered="#{planBAMController.planBAMActual.valorAdicionalMB != ''}">
						         		<td width="94"><h:outputText value="#{planBAMController.planBAMActual.valorAdicionalMB}"/></td>
						         		</h:panelGroup>
						         		<td width="94"><h:outputText value="#{planBAMController.planBAMActual.umbralFairUseMb}" converter="traficoDatosConverterBAMDouble"/></td>
							        	<td width="104"><h:outputText value="#{planBAMController.planBAMActual.velocidadMaxPlan}"/></td>
							        	<!--MOdificado fase 2 estructura tarifaria - zona extrema -->	
							        	<h:panelGroup rendered="#{planBAMController.zonaExtrema ne '1'}">
							        		<td width="104"><cm:getProperty node='${velocidad4G[0]}' name='html'/></td>
							        	</h:panelGroup>
							        	<h:panelGroup rendered="#{planBAMController.zonaExtrema eq '1'}">
							        		<td width="104"><cm:getProperty node='${vel4GZonaExtrema[0]}' name='html'/></td>
							        	</h:panelGroup>
							        	<!-- fin MOdificado fase 2 estructura tarifaria - zona extrema -->
										</tr>
									</tbody>
								</table>
							</div>
		
						</div>
						<!--/tabla-->				
					</div>	
					
					<div class="disclaimer">
				     	<h:outputText value="#{planBAMController.mensajeVelocidadTransmisionCM}" escape="false"></h:outputText>
			        </div>		
					<!-- /ESTRUCTURA TARIFARIA -->
					<div class="separacion_planes"></div>
</h:panelGroup>
<!-- MENSAJES  -->
<jsp:include page="../common/messages_table.jsp"></jsp:include>
<!--  /MENSAJES -->
</f:view>
