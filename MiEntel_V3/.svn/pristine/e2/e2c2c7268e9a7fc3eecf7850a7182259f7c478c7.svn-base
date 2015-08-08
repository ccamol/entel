<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<c:set var="query">idContenido = 'informacionSpeedTest'</c:set>

<f:view>


	<div class="db-tabla">
		<div class="db_tabla_cabecera">
			<div class="db_cabecera_top"></div>
	
			<div class="db_cabecera_cuerpo">
				<div class="db_titulo db_titulo_speedtest">Speed Test</div>
			</div>
		</div>
		<div class="db_tabla_cuerpo">
			<span class="texto_contenido clearfix">
				<cm:search id="infoDisclaimer" query="${query}" useCache="false"/><cm:getProperty node="${infoDisclaimer[0]}" name="html"/>
			</span>

			<div class="enlace_contenido">
				<a href="<h:outputText value="#{aplicacionExternaController.urlSpeedTestBAM}"></h:outputText>" target="_blank">
					<u>Realizar Speed Test</u>
				</a>
			</div>
				
		</div>
		<div class="db_tabla_pie"></div>
	</div>

</f:view>