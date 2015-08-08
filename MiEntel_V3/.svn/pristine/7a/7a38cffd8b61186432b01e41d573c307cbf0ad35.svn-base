<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<!-- Preferencias -->
<pref:getPreference name="imgDebesSaber" var="imgDebesSaber" />
<pref:getPreference name="urlMasInfoCDF" var="urlMasInfoCDF" />
<pref:getPreference name="urlEquiposCompatiblesCDF" var="urlEquiposCompatiblesCDF" />


<!-- Contenidos -->
<cm:search id="infoRestriccionAAA" query="idContenido = 'infoRestriccionAAA'" useCache="false"/>
<cm:search id="headerCDF" query="idContenido = 'headerCDF'" useCache="false"/>
<cm:search id="equipoSin3G" query="idContenido = 'equipoSin3G'" useCache="false"/>
<cm:search id="equipoEnRoaming" query="idContenido = 'equipoEnRoaming'" useCache="false"/>
<cm:search id="equipoEnRoamingSin3G" query="idContenido = 'equipoEnRoamingSin3G'" useCache="false"/>
<cm:search id="mensajeExitoSMS" query="idContenido = 'mensajeExitoSMS'" useCache="false"/>
<cm:search id="mensajeErrorSMS" query="idContenido = 'mensajeErrorSMS'" useCache="false"/>
<cm:search id="debesSaberCDF" query="idContenido = 'debesSaberCDF'" useCache="false"/>
<cm:search id="infoWappush" query="idContenido = 'infoWappush'" useCache="false"/>


<f:view beforePhase="#{suscripcionCDFController.initObtenerSuscripcionesCDF}">

	<script type="text/javascript" >
	    function envia(){
	    	alert("function envia()");
	        $('#msisdn').val($('.inputBox').val());
	        $('#formularioWappush').submit();
	    }
	    
	    $(document).ready(function(){
	    	$('.movil').focus(function() {
			  if($('#respuestaSMS').css('display')=='block'){
			  	$('#respuestaSMS').css('display','none');
			  }
			  return false;
			});
	    });
	
	</script>
	
	<div class="ishop">
	
		<h1><cm:getProperty node="${headerCDF[0]}" name="titulo"/></h1>
		
		<p><cm:getProperty node="${headerCDF[0]}" name="html"/> </p><br />
		
		<!-- Alerta de AAA -->		
		<entel:view name="alertaAAACDF">
		        <cm:search id="infoRestriccionAAA0" query="idContenido = 'infoRestriccionAAA0'" useCache="false"/>
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
		
		<!-- Alerta de compatibilidad -->
		<h:panelGroup rendered="#{!suscripcionCDFController.respuestaOk}">
			<div class="mensaje-alerta-sistema-pequeno">
		        <div class="clearfix sub-contenedor">
		            <div class="contenedor-imagen">
		            	<div class="imagen"></div>
		            </div>
		            <div class="texto">
		            	<h:panelGroup rendered="#{!suscripcionCDFController.sin3G}">
		            		<span><strong><cm:getProperty node="${equipoSin3G[0]}" name="titulo"/></strong></span><br>
		            	</h:panelGroup>
		            	<h:panelGroup rendered="#{suscripcionCDFController.sin3GRoaming}">
		            		<span><strong><cm:getProperty node="${equipoEnRoamingSin3G[0]}" name="titulo"/></strong></span><br>
		            	</h:panelGroup>
		            	<h:panelGroup rendered="#{suscripcionCDFController.roaming}">
		            		<span><strong><cm:getProperty node="${equipoEnRoaming[0]}" name="titulo"/></strong></span><br>
		            	</h:panelGroup>
		            </div>
		            <p style="text-align: center;">
		            	<h:panelGroup rendered="#{suscripcionCDFController.sin3G}">
		            		<cm:getProperty node="${equipoSin3G[0]}" name="html"/>
		            	</h:panelGroup>
		            	<h:panelGroup rendered="#{suscripcionCDFController.sin3GRoaming}">
		            		<cm:getProperty node="${equipoEnRoamingSin3G[0]}" name="html"/>
		            	</h:panelGroup>
		            	<h:panelGroup rendered="#{suscripcionCDFController.roaming}">
		            		<cm:getProperty node="${equipoEnRoaming[0]}" name="html"/>
		            	</h:panelGroup>
		            </p>
		        </div>
		    </div>
		</h:panelGroup>
		
	    <h:panelGroup rendered="#{!suscripcionCDFController.errorActivarInactivar && suscripcionCDFController.respuestaActivarInactivar != null}">
           	<div class="mensaje-exito">
				<div class="clearfix sub-contenedor">
					<div class="texto">
						<strong><h:outputText value="#{suscripcionCDFController.respuestaActivarInactivar}" /></strong>
					</div>
				</div>
			</div>
       	</h:panelGroup>
       	
        <h:panelGroup rendered="#{suscripcionCDFController.errorActivarInactivar && suscripcionCDFController.respuestaActivarInactivar != null}">
           	<div class="mensaje-error-pequeno">
				<div class="clearfix sub-contenedor">
					<div class="contenedor-imagen">
						<div class="imagen"></div>
					</div>
					<div class="texto"><h:outputText value="#{suscripcionCDFController.respuestaActivarInactivar}" /></div>
				</div>
			</div>
        </h:panelGroup>
	
		<!-- Tabla Tarificacion -->
	
		<div class="tabla">	
		
			<div class="header_tabla clearfix">  
				<div class="top"><span></span></div>
				<div class="main">
					<table>
						<tr>
							<th width="140px">Suscripciones</th>
							<th width="55px">Precio</th>
		                    <th width="110px">Fecha Activaci&oacute;n</th>
		                    <th width="90px">Duraci&oacute;n</th>
		                    <th class="sinBorde">&nbsp;</th>
						</tr>
					</table>	
				</div>
				<div class="bottom"><span></span></div>
			
			</div>
		
			<div class="contenido_tabla">
			
				<table>
					<tbody>
					<it:iterator var="suscripcion" value="#{suscripcionCDFController.tablaSuscripciones}" rowIndexVar="index">
						<tr class="<h:outputText value="#{(index % 2) == 0 ? 'impar' : 'par'}"/>">
							<td width="140px"><h:outputText value="#{suscripcion.nombre}"/></td>
							<td width="55px">
								$<h:outputText value="#{suscripcion.precio}">
									<f:convertNumber currencyCode="CLP" locale="es" />
								 </h:outputText>
							</td>
							<td width="110px">
								<h:panelGroup rendered="#{suscripcion.fechaSuscripcion != null}">
									<h:outputText value="#{suscripcion.fechaSuscripcion}">
										<f:convertDateTime pattern="dd/MM/yyyy hh:mm"/>
									</h:outputText>
								</h:panelGroup>
								<h:panelGroup rendered="#{suscripcion.fechaSuscripcion == null}">
									<span>-- --</span>
								</h:panelGroup>
							<td width="90px"><h:outputText value="#{suscripcion.duracion}"/> D&iacute;as</td>
							<td>
								<entel:view name="listadoSuscripcionesCFD">
									<div class="botones" <h:outputText value="#{suscripcion.activa ? 'style=\"display:block\"' : (suscripcion.compatibilidad ? 'style=\"display:block\"' : 'style=\"display:none\"')}"/>>
										<h:form rendered="#{suscripcion.activa}">
											<jsp:include page="/token.jsp" flush="true"/>
											<h:commandLink id="enlaceInactivar" styleClass="btnVerdeDelgado" actionListener="#{suscripcionCDFController.inactivarSuscripcionesCDF}">
												<f:param name="idSuscripcion" value="#{suscripcion.idSuscripcion}"/>
												<f:verbatim><span>Desactivar</span></f:verbatim>
											</h:commandLink>
										</h:form>
										<h:form rendered="#{!suscripcion.activa}">
											<jsp:include page="/token.jsp" flush="true"/>
											<h:commandLink id="enlaceActivar" styleClass="btnVerdeDelgado" actionListener="#{suscripcionCDFController.activarSuscripcionesCDF}">
												<f:param name="idSuscripcion" value="#{suscripcion.idSuscripcion}"/>
												<f:verbatim><span>Contratar</span></f:verbatim>
											</h:commandLink>
										</h:form>
									</div>
									<div <h:outputText value="#{(!suscripcion.activa && !suscripcion.compatibilidad) ? 'style=\"display:block\"' : 'style=\"display:none\"'}"/>>
										*<cm:getProperty node="${equipoSin3G[0]}" name="titulo"/>
									</div>
								</entel:view>
								<entel:view name="alertaAAACDF">
								    --
								</entel:view>
							</td>
						</tr>
					</it:iterator>
					</tbody>
				</table>
			
			</div>
			
			
			<input type="hidden" id="mensajeExitoSMS" name="mensajeExitoSMS" value='<cm:getProperty node="${mensajeExitoSMS[0]}" name="html"/>'>
			<input type="hidden" id="mensajeErrorSMS" name="mensajeErrorSMS" value='<cm:getProperty node="${mensajeErrorSMS[0]}" name="html"/>'>
			
			<form id="formularioWappush" name="formularioWappush" action="http://wappush.entelpcs.cl/WapPushWeb/pageflow/envioWapPush/proxy.jsp" method="POST">
				<jsp:include page="/token.jsp" flush="true"/>
				<input type="hidden" value="65" id="idCampana" name="idCampana" >
				<input type="hidden" id="msisdn" name="msisdn" value="" >
			</form>
			
			<entel:view name="listadoSuscripcionesCFD">
				<h:panelGroup rendered="#{!suscripcionCDFController.roaming and suscripcionCDFController.cantContratadas > 0}">
					<div class="cajaCanal">
						<h4><strong><cm:getProperty node="${infoWappush[0]}" name="titulo"/></strong></h4>
						<div class="cajaAzul">
							<p><cm:getProperty node="${infoWappush[0]}" name="html"/></p>
							<div class="clearfix" style="margin:5px 0;">
								<div class="campo">
									<label>+ 56 9</label>
									<input class="inputBox input_numerico movil" type="text" name="movil" maxlength="8"/>
									
								</div>
								<a href="#" class="btnAzul" id="btnEnviarSMS" onclick="return wappush.llamarFlash(this);"><span>Enviar</span></a>
							</div>
							<div id="respuestaSMS" style="display:none;"></div>
						</div>
					</div>
				</h:panelGroup>	
			</entel:view>
			
		
		</div><!--/tabla-->
		
	    <a class="enlaceExterno" href="${urlMasInfoCDF}" target="_blank"><strong>M&aacute;s informaci&oacute;n</strong></a>
	
	    <!-- debes saber-->
	    <div id="debes-saber" class="clearfix">
	
			<div class="img"><img src="${imgDebesSaber}" alt="Debes saber" /></div>
	        <div class="contenido"><cm:getProperty node="${debesSaberCDF[0]}" name="html"/></div>
	
	    </div>
	    <!-- /debes saber-->
		
	</div>	
</f:view>