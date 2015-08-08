<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it"  uri="/WEB-INF/tld/IteratorTag.tld"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<c:set var="query">idContenido = 'velocidadNavegacion'</c:set>
<f:view beforePhase="#{miTraficoBamController.init}">
<!-- MENSAJES  -->
<jsp:include page="../common/messages_table.jsp"></jsp:include>
<!--  /MENSAJES -->
<h:panelGroup rendered="#{miTraficoBamController.miTraficoBam != null}">
	<h1>Tr&aacute;fico</h1>
	
	<h2 class="superchip">
		<strong>Plan contratado:</strong>
		<h:outputText value="#{miTraficoBamController.miTraficoBam.nombrePlan}" />
	</h2>					
	<!-- Caja mejora tu plan -->

	<div class="mejora_plan clearfix">
		<div class="lista_detalles">
			<ul>
				<li>Tr&aacute;fico sin restricci&oacute;n </li>
				<li>Velocidad del plan: <h:outputText value="#{miTraficoBamController.miTraficoBam.velocidadMaxPlan}"/></li>
				<li>Cuota del plan: <h:outputText value="#{miTraficoBamController.miTraficoBam.umbralFairUse}"/><h:outputText value="#{miTraficoBamController.miTraficoBam.unidadUmbralFairUse}"/></li>
			</ul>
		</div>
		<!-- 
		<div class="boton_mejora">
			<a class="btnAzulGrandeLargo btnAzulGrande" href="<r:pageUrl pageLabel='${miTraficoBamController.pageLabelCambioPlan}'></r:pageUrl>"><span>Mejora tu plan</span></a>
		</div>
		 -->
	</div>
	<!-- fin Caja mejora tu plan -->
	
	<h2 class="ico_cuota">
		Cuota y velocidad de navegaci&oacute;n.
	</h2>					
	<!-- Caja cuota restante -->

	<div class="caja_cuota_externo clearfix">
		<div class="caja_cuota clearfix">
			
			<div class="contenedor_cuota_dato">
				<div class="clearfix">
					<div class="numero_dato">
						<span class="numero"><h:outputText value="#{miTraficoBamController.cuotaRestante}"/></span>
						<span class="tasa_transfer"><h:outputText value="#{miTraficoBamController.cuotaRestanteUnidad}"/></span>
					</div>

					<div style="margin:0 0 0 10px;" class="grafica_dato grafica_<h:outputText value="#{miTraficoBamController.porcentajeUtilizado}"/> "></div>
					<div class="restante_dato">
						<strong>Cuota Plan</strong><br />
						<h:outputText value="#{miTraficoBamController.miTraficoBam.umbralFairUse}"/><h:outputText value="#{miTraficoBamController.miTraficoBam.unidadUmbralFairUse}"/>
					</div>
				</div>
			</div>
			
			<div class="contenedor_maxima_velocidad">
				<span class="texto_velocidad clearfix">
				     <cm:search id="infoDisclaimer" query="${query}" useCache="false"/><cm:getProperty node="${infoDisclaimer[0]}" name="html"/>
				</span>
				<div class="clearfix">
					<div class="velocidad_dato"><h:outputText value="#{miTraficoBamController.velocidadActual}"/></div>
					<div class="velocidad_tasa_transfer"><h:outputText value="#{miTraficoBamController.velocidadActualUnidad}"/></div>
				</div>

			</div>
			
		</div>
	</div>
	
	<div class="texto_bajada_caja clearfix">
		Al usar toda la cuota de tr&aacute;fico, la velocidad de navegaci&oacute;n m&aacute;xima ser&aacute; de 200 Kbps.
	</div>
	<!-- fin Caja cuota restante -->
</h:panelGroup>
</f:view>
