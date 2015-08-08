<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<!-- Preferencias -->
<pref:getPreference name="idContenido" var="idContenido" defaultValue="tituloCanje" />
<pref:getPreference name="urlCategoriasClientes" var="urlVerCategoria" />


<!-- Declaracion de variabless -->
<c:set var="query">idContenido = '${idContenido}'</c:set>


<f:view beforePhase="#{vtasYMktgFidelizacionController.initCargarInfoPuntosCanje}">
    <entel:view name="canjePuntos">
	<h2 class="bullet_zonapuntos">
	 	<cm:search id="infoTitulo" query="${query}" useCache="false"/>
		<cm:getProperty node="${infoTitulo[0]}" name="titulo"/>
	</h2>
	</entel:view>
	<!-- Alerta de AAA -->
	<entel:view name="alertaAAAZonaPuntos">
	          <cm:search id="infoRestriccionAAA0" query="idContenido = 'infoRestriccionAAA'" useCache="false"/>
				<div class="contenedor-mensajes">
			        <ul>
				        <li class="mensaje-alerta">
					        <div align="center"> 
					        	<span><cm:getProperty node="${infoRestriccionAAA0[0]}" name="html"/></span>
					        </div> 
				        </li>
			        </ul>
			     </div>
	</entel:view>
	
	<entel:view name="cuadroVerPuntos">
						
		<div class="margen clearfix" id="pasos">
			<span class="flotar_izq">Tus puntos al: <span id="fechaActPuntos"><h:outputText value="#{vtasYMktgFidelizacionController.detallePuntos.fechaActPuntos}">
			<f:convertDateTime pattern="dd/MM/yyyy"/>
			</h:outputText> </span></span>
			<span class="flotar_der categoriaCli">
				Cliente Categor&iacute;a 
				<strong><h:outputText value="#{vtasYMktgFidelizacionController.categoriaClientePuntos}" /></strong> 
				<a class="verEnlace" target="_blank" href="${urlVerCategoria}">(Ver m&aacute;s)</a>
			</span>
			
		</div>
		<div class="linea-azul"></div>
					
		<!-- Caja Resumen -->
		<div class="caja_historial clearfix">
			<div class="caja_historial_top"></div>
			<div class="caja_historial_centro">
				
				<div class="historial_fila clearfix">
					<span class="texto_izq">Saldo contable</span>
					<span class="texto_der saldo_contable" id="saldoContable">					
						<h:outputText value="#{vtasYMktgFidelizacionController.detallePuntos.saldoPuntos}">
							<f:convertNumber currencyCode="CLP" locale="es" />
						</h:outputText>
					</span>
					
				</div>
				<div class="historial_fila ultima clearfix">
					<span class="texto_izq">Puntos por vencer 
						<span id="fechaVencPtos">(el <h:outputText value="#{vtasYMktgFidelizacionController.detallePuntos.fechaVencPuntos}">		
								  	<f:convertDateTime pattern="dd/MM/yyyy"/>
								  </h:outputText>)
						</span>
					</span>
					<span class="texto_der" id="puntosVencidos">
						<h:outputText value="#{vtasYMktgFidelizacionController.detallePuntos.puntosVencidos}">
							<f:convertNumber currencyCode="CLP" locale="es" />
						</h:outputText>
					</span>
				</div>
									
			</div>
			<entel:view name="canjePuntos"><div class="caja_historial_bottom"></div></entel:view>
		</div>
		
		<br /><br />
	</entel:view>

</f:view>