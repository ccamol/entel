<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content"%>

<cm:search id="infoCajaEjecutivoVIP" query="idContenido = 'infoCajaEjecutivoVIP'" useCache="false" />

<f:view beforePhase="#{ejecutivoVIPController.init}">
	<h:panelGroup rendered="#{ejecutivoVIPController.datosEjecutivo != null}">
		<div id="caja_ejecutivo_vip">
			<div class="cajalinks clearfix">
				<div class="cabecera cabecera_ejecutivo_azul clearfix">
					<h1><cm:getProperty node="${infoCajaEjecutivoVIP[0]}" name="titulo" /></h1>
				</div>
				<div class="cuerpo">
					<!-- Info Ejecutivo VIP -->
					<div id="info_ejecutivo">									
						<cm:getProperty node="${infoCajaEjecutivoVIP[0]}" name="html" />
					</div>
				    <!-- Datos Ejecutivo VIP -->
				    <div align="center" id="datos_ejecutivo">
						<b><h:outputText value="#{ejecutivoVIPController.datosEjecutivo.nombre}" /> <h:outputText value="#{ejecutivoVIPController.datosEjecutivo.apellidoPaterno}" /></b><br />
						Tel&eacute;fono: <h:outputText value="#{ejecutivoVIPController.datosEjecutivo.telefono}" /><br />
						Email: <h:outputText value="#{ejecutivoVIPController.datosEjecutivo.email}" />
					</div>				    
				</div>
				<div class="pie"></div>
			</div>
		</div>
	</h:panelGroup>
</f:view>