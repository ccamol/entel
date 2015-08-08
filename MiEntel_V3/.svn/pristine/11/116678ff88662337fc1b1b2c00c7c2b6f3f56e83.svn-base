<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>

<f:view>
	<div class="estructuraTrafico">
		<h2 class="ico_grafico">
			<strong>Tráfico en Línea</strong>
		</h2>
		<div class="mensajeServicio">
			<p>Este servicio no se encuentra disponible aún para esta versión <strong>BETA</strong> de MiEntel. Si necesitas acceder al servicio seleccionado puedes hacerlo en la <a href="<h:outputText value="#{traficoController.URLMiEntelV2}" />">versión actual</a>.</p>
			<p>Agradecemos puedas dejarnos un comentario que nos permita mejorar esta versión de MiEntel.</p>
			<p>Gracias por tu paciencia.</p>
		</div>
		<!--
		<div class="tabla">	
			<div class="header_tabla clearfix">
				<div class="top"><span></span></div>
				<div class="main">
					<table>
						<tbody>
							<tr>
								<th width="120">Destinatario</th>
								<th>Tipo</th>
								<th width="100">Fecha/Hora inicio</th>
								<th width="70">Mnts/Kb</th>
								<th width="60" class="ultimo">Valor</th>
							</tr>
						</tbody>
					</table>					
				</div>
				<div class="bottom"><span></span></div>
			</div>
			<div class="contenido_tabla align_left">
				<table>
					<tbody>
						<it:iterator var="item" value="#{traficoEnLineaController.detalleTraficoEnLinea}" rowIndexVar="row">		
							<c:set var="style" value="#{row % 2 == 0 ? '': 'impar'}" scope="page" />					
							<tr class="<h:outputText value="#{style}"/>">
								<td width="120px"><h:outputText value="#{item.destinatario}" /></td>
								<td width="90px"><h:outputText value="#{item.descripcionTipoTrafico}" /></td>
								<td width="100px">
									<h:outputText value="#{item.fecha}" >
										<f:convertDateTime pattern="dd-MM-yyyy / HH:mm"/> 
									</h:outputText>
								</td>
								<td width="70px">
									<h:panelGroup rendered="#{item.tipoTrafico == 'VOZ'}">
										<h:outputText converter="traficoVozConverter" value="#{item.consumoTraficoVoz}" /> min
									</h:panelGroup>
									<h:panelGroup rendered="#{item.tipoTrafico != 'VOZ'}">
										<h:outputText converter="traficoDatosConverter" value="#{item.consumoTraficoDato}" /> kb
									</h:panelGroup>							
								</td>
								<td width="60px">
									<h:outputText value="#{item.valor}" >
										<f:convertNumber currencyCode="CLP" locale="es" />
									</h:outputText>
								</td>
							</tr>					
						</it:iterator>
					</tbody>
				</table>
			</div>
		</div>
		-->		
	</div>
</f:view>	