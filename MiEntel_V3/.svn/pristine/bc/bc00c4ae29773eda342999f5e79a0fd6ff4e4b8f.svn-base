<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<f:view beforePhase="#{miTraficoBAMCCController.initTrafico}">
<!-- MENSAJES  -->
<jsp:include page="../common/messages_table.jsp"></jsp:include>
<!--  /MENSAJES -->
<h:panelGroup rendered="#{miTraficoBAMCCController.traficoBamCCBean != null}">
	<h1>Tr&aacute;fico</h1>
	
	<h2 class="superchip">
		<strong>Plan contratado:</strong>
		<h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.nombrePlan}" />
	</h2>					
	<!-- Caja mejora tu plan -->

	<div class="mejora_plan clearfix">
		<div class="lista_detalles">
			<ul>
				<li>Tr&aacute;fico incluido: <h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.umbralFairUse}"/><h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.unidadUmbralFairUse}"/></li>
				<!-- <li>Velocidad del plan: <h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.velocidadMaxPlan}"/></li> -->
				<li>Valor tr&aacute;fico adicional por MB: <h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.valorTraficoAdicional}"/></li>			
			</ul>
		</div>
		<!-- 
		<div class="boton_mejora">
			<a class="btnAzulGrandeLargo btnAzulGrande" href="<r:pageUrl pageLabel='${miTraficoBAMCCController.pageLabelCambioPlan}'></r:pageUrl>"><span>Mejora tu plan</span></a>
		</div>
		 -->
	</div>
	<!-- fin Caja mejora tu plan -->
	
	<h2 class="ico_cuota">
		Tr&aacute;fico disponible.
	</h2>		
	
	<!-- <div class="caja_cuota_externo clearfix"> -->
	<div class="clearfix" >
		<div class="caja_cuota clearfix">
			
			<div class="contenedor_cuota_dato" style="border: 1px solid #CDDAE7; padding-top: 10px; padding-bottom: 10px;">
				<div class="clearfix">
					<div class="numero_dato">
						<span class="numero"><h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.saldoMonto != '' ? '$' : ''}"/><h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.saldoMonto != '' ? miTraficoBAMCCController.traficoBamCCBean.saldoMonto : miTraficoBAMCCController.traficoBamCCBean.cuotaRestante}"/></span>
						<span class="tasa_transfer"><h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.saldoMonto != '' ? '' : ''}"/><h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.saldoMonto != '' ? '' : miTraficoBAMCCController.traficoBamCCBean.cuotaRestanteUnidad}"/></span>
					</div>
					
					<div class="grafica_dato grafica_<h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.porcentajeCuotaRestante}"/>"></div>
					<div class="restante_dato">
					<h:panelGroup rendered="#{!miTraficoBAMCCController.traficoBamCCBean.ilimitado}">
					    <strong><h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.saldoMonto != '' ? 'Saldo recarga' : 'Cuota Plan'}"/></strong><br />
						<h:outputText escape="false" value="#{miTraficoBAMCCController.traficoBamCCBean.ilimitado == true ? '&nbsp;' : miTraficoBAMCCController.traficoBamCCBean.umbralFairUse}"/>
						<h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.unidadUmbralFairUse}"/>
						<br/><br/>
					</h:panelGroup>
					<h:panelGroup rendered="#{miTraficoBAMCCController.traficoBamCCBean.ilimitado}">
					  <br/>
					  <br/>
					 <strong>Sin restricci&oacute;n</strong>
					</h:panelGroup>										
					</div>
				</div>
			</div>
			
			<!-- 
			<div class="contenedor_maxima_velocidad">

				<span class="texto_velocidad clearfix">
					Velocidad m&aacute;xima de navegaci&oacute;n.
				</span>
				<div class="clearfix">
					<div class="velocidad_dato"><h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.velocidadMaxPlanValor}"/></div>
					<div class="velocidad_tasa_transfer"><h:outputText value="#{miTraficoBAMCCController.traficoBamCCBean.velocidadMaxPlanUnidad}"/></div>
					
				</div>
			</div>
			-->
		</div>
	</div>
	
	<div class="texto_bajada_caja clearfix">
		
	</div>
	<!-- fin Caja cuota restante -->
</h:panelGroup>
</f:view>
