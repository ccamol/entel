<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content"%>

<cm:search id="linkSoporteEntel" query="idContenido = 'linkSoporteEntel'" useCache="true"  />

<f:view beforePhase="#{equipoController.initEquipos}">

<!-- MENSAJES -->
<div id="errorMSG" align="center" class="contenedor-mensajes">
	<h:messages id="messageList" 
		styleClass="mensajes-lista"
		errorClass="mensaje-error" 
		infoClass="mensaje-informacion" showSummary="true" />
</div>

<h:panelGroup rendered="#{equipoController.pinPuk!=null}">
<h1>Equipo</h1>
<div class="descripcion_equipo clearfix">
				<div class="imagen_equipo">
					<div class="pngFix"> 
                    	<h:graphicImage url="/image?id=#{equipoController.numeroPcs}.png" width="110px" height="130px" alt=""/> 
                    </div>						
					<div class="descripcion">
						<span><h:outputText value="#{equipoController.resumenEquipo.marca}" />&nbsp;<h:outputText value="#{equipoController.resumenEquipo.modelo}" /></span>						
						<cm:getProperty node="${linkSoporteEntel[0]}" name="html" />
					</div>
				</div>

				<div class="pinpuk">
					<div class="cuadropinpuk">
						<span><strong>Pin:</strong> <h:outputText value="#{equipoController.pinPuk.pin1}" /></span>
						<span><strong>Puk:</strong> <h:outputText value="#{equipoController.pinPuk.puk1}" /></span>
					</div>
						
						
						<!-- <a href="#">&iquest;Qu&eacute; es Pin y Puk?</a> -->
						
						<h:outputLink value="pinPuk.faces" styleClass="pinPukBox" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Banda Ancha Movil/Equipo/Pin y Puk');">&iquest;Qu&eacute; es Pin y Puk?</h:outputLink>
										
				</div>
			</div>

</h:panelGroup>
			
</f:view>