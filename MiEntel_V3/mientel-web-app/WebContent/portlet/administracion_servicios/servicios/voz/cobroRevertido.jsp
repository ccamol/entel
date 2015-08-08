<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it"  uri="/WEB-INF/tld/IteratorTag.tld"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<% 		
	String resaltador = "";
	if(request.getParameter("rowNumber") != null){
		resaltador = (Integer.parseInt(request.getParameter("rowNumber"))) % 2 == 0? "resaltador":"";
	}
%>

<entel:view name="cobroRevertido" inverse="true"> 
<!-- Fila de información -->     
<div class="tabla_fila clearfix <%=resaltador%>">

	<div class="columna1">
		<strong class="clearfix">Cobro Revertido</strong>
		<label class="titulo_inferior">Cargo por uso</label>
	</div>
	<div class="columna2">
		<a class="ico_interrogacionNuevo autoTooltip ext" href="#TTComunicados"></a>
	</div>

    <div class="columna3">
    	<!-- Mensaje grande -->
        <h:panelGroup layout="block" styleClass="mensaje_grande #{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.activo ? 'mensaje_grande_habilitado' :'mensaje_grande_deshabilitado'}">
        	
        	<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.activo ? 'Habilitado' :'Deshabilitado'}"/>
        		
        	<br />Solo el titular o administrador de la cuenta, puede cambiar el estado.
        	<br />
        	<h:form>
				<jsp:include page="/token.jsp" flush="true"/>
        		<h:commandLink action="mis_usuarios" styleClass="enlace">Ir a tabla de atributos</h:commandLink>
        	</h:form>
        </h:panelGroup>

    </div>
</div>
<!-- /Fila de información -->
</entel:view>


<entel:view name="cobroRevertido" >
<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.visible}">

<script type="text/javascript">
	$(document).ready(function() {
	$("input[id*=numerosCobroRevertido]").val('<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.msisdnAsociadosToken}"/>');
	$("input[id*=actualesnumCobroRevertido]").val('<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.msisdnAsociadosToken}"/>');
    });
</script>

<!-- Fila de información -->
<div class="tabla_fila clearfix <%=resaltador%>">
    <h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.activo}">
	<!-- Estado habilitado (estado 1) clase OCULTAR para inicio desahilitado -->
	<div class="estadoHabilitado clearfix ">
		<div class="columna1">
			<strong class="clearfix">Cobro Revertido</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip ext" href="#TTComunicados"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje habilitado/deshabilitado -->
			<div class="mensaje habilitado">Habilitado</div>
			
			<!-- Btoón deshabilitado/habilitado -->
			<div class="btnDeshabilitar">
				<a class="btnAzulDelgado btnAncho2 caso1" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Cobro Revertido/Deshabilitar');"><span>Deshabilitar</span></a>
			</div>
			
			<!-- Cabecera del boton configurar/cerrar -->
			<div class="enlaceConfigurar">
				<a href="#" class="enlaceConfigurar" style="color: #3a3a3a; margin: 3px 0 0 10px;">Configurar</a>
			</div>
			<div class="cerrarConfigurar">
				<a href="#" class="enlaceCerrar">Cerrar</a>
			</div>
			<!-- /Cabecera del boton configurar/cerrar -->
		</div>
		
		<!-- Contenido de Configurar/cerrar -->
		<div class="configurar_contenido clearfix">
		
			<!-- Contenido común Configurar/cerrar -->
			<div class="configurar_contenido_superior clearfix padre">
				<div class="clearfix"><strong>Puedes agregar un m&aacute;ximo de 3 n&uacute;meros Prepago</strong></div>
				<form name="validar_numero" class="validar_numero"  action="#">
					<jsp:include page="/token.jsp" flush="true"/>
					<div class="opciones alertador clearfix">
						<div class="opciones_elemento campo">
							<input type="text" name="numero" class="inputBox numero_avisale input_numerico agregar_todos" style="width: 75px;" maxlength="8"/>
						</div>
						<div class="opciones_boton">
							<a class="btnAzul largo btn_agregar3" href="#"><span>Agregar n&uacute;mero Prepago PCS</span></a>
						</div>
						<!--<div class="alerta_numero2 alerta_prepago"><table><tr><td>Ingrese un n&uacute;mero v&aacute;lido</td></tr></table></div>-->
					</div>
				</form>
				
				<!-- Alerta oculta -->
				<div id="alerta_oculta" class="alerta_oculta clearfix">
					<label class="alerta_label">M&aacute;ximo 3 n&uacute;meros</label>
				</div>
				<!-- /Alerta oculta -->
				
				<!-- Tabla de numeros -->
				<div class="opciones tabla_interna">
				    <div class="top_tabla"></div>
					<div class="main_tabla clearfix">
						<div class="tabla_interna_columna1_corto">
							N&uacute;mero
						</div>
						Fecha de activaci&oacute;n
					</div>
					<div class="bottom_tabla"></div>
					
						<it:iterator value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.msisdnAsociados}" var="msisdn">
					     <div class="tabla_interna_fila clearfix">
						<div class="tabla_interna_columna1_corto"><h:outputText value="#{msisdn.msisdn}"/></div>
						<div class="tabla_interna_columna2_corto"><h:outputText value="#{msisdn.fechaInscripcion}">
						                                          <f:convertDateTime pattern="dd/MM/yyyy"/>
						                                          </h:outputText></div>
						<div class="tabla_interna_columna3_corto">
							<a href="#" class="enlaceEliminar eliminarCobro">eliminar</a>
						</div>
					</div>
					</it:iterator>
				</div>
				<!-- /Tabla de numeros -->
				
				<div class="opciones centrar">
				    <h:form id="cobroRevertidoForm">
					<jsp:include page="/token.jsp" flush="true"/>
				    <h:commandLink action="#{cobroRevertidoController.modificarListaCobroRevertido}" styleClass="btnAzulGrande guardarConfiguracion" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Cobro Revertido/Habilitado');"><span>Guardar configuraci&oacute;n</span></h:commandLink>
				    <h:inputHidden value="#{cobroRevertidoController.msisdnActuales}" id="actualesnumCobroRevertido"/>
				    <h:inputHidden value="#{cobroRevertidoController.msisdnAsociados}" id="numerosCobroRevertido"/> 
				    </h:form>
				</div>
					
			</div>
			<!-- /Contenido común Configurar/cerrar -->
			
			<div class="contenido_inferior_prepago clearfix">
				<div class="clearfix">
					<div class="fila_dato">
						<strong>Valor minuto Prepago</strong><br />$<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.valorMinutoPrepago}">
						<f:convertNumber currencyCode="CLP" locale="es" />
						</h:outputText>
					</div>
					<div class="fila_dato">
						<strong>Saldo Mes minutos</strong><br />
						<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.saldoMesMinutos}"></h:outputText>
					</div>
				</div>
				<div class="clearfix">
					<div class="fila_dato">
						<strong>M&aacute;ximo Mensual</strong><br />$
						<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.maximoMensual}">
						 <f:convertNumber currencyCode="CLP" locale="es" />
						</h:outputText>
						
					</div>
					<div class="fila_dato">
						<strong>Saldo mes</strong><br />$
                        <h:outputText value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.saldoMes}">
                        <f:convertNumber currencyCode="CLP" locale="es" />
                        </h:outputText>
					</div>
				</div>
			</div>
			
		</div>
		<!-- /Contenido de Configurar/cerrar -->		
	</div>
	</h:panelGroup>
	
	<!-- Paso Confirmar Habilitar -->
	<h:form id="confirmarCobro">
		<jsp:include page="/token.jsp" flush="true"/>
	  <div class="confirmarHabilitar clearfix ocultar">	
			<div class="columna1">
				<strong class="clearfix">Cobro Revertido</strong>
				<label class="titulo_inferior">Cargo por uso</label>
			</div>
			<div class="columna2">
				<a class="ico_interrogacionNuevo autoTooltip" href="#TTCobroRevertido"></a>
			</div>
			<div class="columna3">
				<!-- Mensaje habilitado/deshabilitado -->
				<div class="mensaje_ancho"><strong>Habilitar&aacute;s Cobro Revertido</strong><br />
				</div>
				
				<!-- Btoon deshabilitado/habilitado -->
			<!-- Btoon deshabilitado/habilitado -->
				<div class="btnHabilitar">
				    <h:commandLink  styleClass="btnAzulDelgado btnAncho caso3 cobroRevertido" action="#{cobroRevertidoController.modificarListaCobroRevertido}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Cobro Revertido/Habilitado');"><span>Habilitar</span></h:commandLink>
				</div>
				
				<!-- Boton Cancelar -->
				<div class="enlaceCancelar">
					<a href="#" class="enlaceCancelarH">Cancelar</a>
				</div>
			</div>
		</div>	
		<h:inputHidden value="#{cobroRevertidoController.msisdnActuales}" id="actualesnumCobroRevertido"/>
	    <h:inputHidden value="#{cobroRevertidoController.msisdnAsociados}" id="numerosCobroRevertido"/>
	</h:form>	
	<!-- Fin Paso Confirmar Habilitar -->
	
	<div class="estadoHabilitado1 clearfix ocultar">
		<div class="columna1">
			<strong class="clearfix">Cobro Revertido</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip ext" href="#TTComunicados"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje grande -->
			<div class="mensaje_grande mensaje_grande_reloj">
				En proceso de <strong>habilitaci&oacute;n</strong>
			</div>
		</div>
	</div>
	
	<h:panelGroup rendered="#{!administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.activo}">
	<!-- Estado deshabilitado clase OCULTAR para inicio habilitado-->
	<div class="estadoDeshabilitado clearfix">
		<div class="columna1">
			<strong class="clearfix">Cobro Revertido</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip ext" href="#TTComunicados"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje habilitado/deshabilitado -->
			<div class="mensaje deshabilitado">Deshabilitado</div>
			
			<!-- Btoón deshabilitado/habilitado -->
			<div class="btnHabilitar">
				<a class="btnAzulDelgado btnAncho enlaceConfigurar" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Cobro Revertido/Habilitar');"><span>Habilitar</span></a>
			</div>
			
			<!-- Boton Habilitar/Cancelar -->		                            
			<div class="cerrarConfigurar">
				<a href="#" class="enlaceCerrar habilitarCancelar">Cancelar</a>
			</div>
			<!-- Boton Configurar/Cancelar -->
		</div>
		
		<!-- Contenido de Configurar/cerrar -->
		<div class="configurar_contenido clearfix">
		
			<!-- Contenido común Configurar/cerrar -->
			<div class="configurar_contenido_superior clearfix padre">
				<div class="clearfix"><strong>Puedes agregar un m&aacute;ximo de 3 n&uacute;meros Prepago</strong></div>
				<form name="validar_numero" class="validar_numero"  action="#">
					<jsp:include page="/token.jsp" flush="true"/>
					<div class="opciones alertador clearfix">
						<div class="opciones_elemento campo">
							<input type="text" name="numero" class="inputBox numero_avisale input_numerico agregar_todos" style="width: 75px;" maxlength="8"/>
						</div>
						<div class="opciones_boton">
							<a class="btnAzul largo btn_agregar3" href="#"><span>Agregar n&uacute;mero Prepago PCS</span></a>
						</div>
						<!--<div class="alerta_numero2 alerta_prepago"><table><tr><td>Ingrese un n&uacute;mero v&aacute;lido</td></tr></table></div>-->
					</div>
				</form>
				
				<!-- Alerta oculta -->
				<div id="alerta_oculta" class="alerta_oculta clearfix">
					<label class="alerta_label">M&aacute;ximo 3 n&uacute;meros</label>
				</div>
				<!-- /Alerta oculta -->
				
				<!-- Tabla de numeros -->
				<div class="opciones tabla_interna">
					<!-- <input type="hidden" class="numerosCobroRevertido" value="93799181,85294855," /> -->
					<div class="top_tabla"></div>
					<div class="main_tabla clearfix">
						<div class="tabla_interna_columna1_corto">
							N&uacute;mero
						</div>
						Fecha de activaci&oacute;n
					</div>
					<div class="bottom_tabla"></div>
					<it:iterator value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.msisdnAsociados}" var="msisdn">
					<div class="tabla_interna_fila clearfix">
						<div class="tabla_interna_columna1_corto"><h:outputText value="#{msisdn.msisdn}"/></div>
						<div class="tabla_interna_columna2_corto"><h:outputText value="#{msisdn.fechaInscripcion}">
						                                          <f:convertDateTime pattern="dd/MM/yyyy"/>
						                                          </h:outputText></div>
						<div class="tabla_interna_columna3_corto">
							<a href="#" class="enlaceEliminar eliminarCobro">eliminar</a>
						</div>
					</div>
					</it:iterator>
				</div>
				<!-- /Tabla de numeros -->
				
				<div class="opciones centrar">
				<h:form id="cobroRevertidoSaveConfForm">
					<jsp:include page="/token.jsp" flush="true"/>
				    <a class="btnAzulGrande guardarConfiguracion" href="#">
			          <span>Guardar configuraci&oacute;n</span>
			        </a>					
				    <h:inputHidden value="#{cobroRevertidoController.msisdnActuales}" id="actualesnumCobroRevertido2"/>
				    <h:inputHidden value="#{cobroRevertidoController.msisdnAsociados}" id="numerosCobroRevertido2"/>
				</h:form>
				</div>
					
			</div>
			<!-- /Contenido común Configurar/cerrar -->
			
			<div class="contenido_inferior_prepago clearfix">
				<div class="clearfix">
					<div class="fila_dato">
						<strong>Valor minuto Prepago</strong><br />$<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.valorMinutoPrepago}">
						<f:convertNumber currencyCode="CLP" locale="es" />
						</h:outputText>
					</div>
					<div class="fila_dato">
						<strong>Saldo Mes minutos</strong><br /><h:outputText value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.saldoMesMinutos}"></h:outputText>
					</div>
				</div>
				<div class="clearfix">
					<div class="fila_dato">
						<strong>M&aacute;ximo Mensual</strong><br />$
						<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.maximoMensual}">
						 <f:convertNumber currencyCode="CLP" locale="es" />
						</h:outputText>
					</div>
					<div class="fila_dato">
						<strong>Saldo mes</strong><br />$
						<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.saldoMes}">
						<f:convertNumber currencyCode="CLP" locale="es" />
						</h:outputText>
					</div>
				</div>
			</div>
			
		</div>
		<!-- /Contenido de Configurar/cerrar -->
	</div>
	</h:panelGroup>
	<div class="estadoDeshabilitado1 clearfix ocultar">

		<div class="columna1">
			<strong class="clearfix">Cobro Revertido</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip ext" href="#TTComunicados"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje habilitado/deshabilitado -->
			<div class="mensaje_ancho"><strong>Deshabilitar&aacute;s Cobro Revertido</strong><br />Los n&uacute;meros configurados ser&aacute;n eliminados.
			</div>
			
			<!-- Btoón deshabilitado/habilitado -->
			<div class="btnDeshabilitar">
			<h:form id="cobroRevertidoFormHab">
				<jsp:include page="/token.jsp" flush="true"/>
			    <h:commandLink action="#{cobroRevertidoController.desactivarCobroRevertido}" styleClass="btnAzulDelgado btnAncho2 caso4" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Cobro Revertido/Deshabilitado');"><span>Deshabilitar</span></h:commandLink>
			</h:form>
			</div>
			
			<!-- Botón Cancelar -->
			<div class="enlaceCancelar">
				<a href="#" class="enlaceCancelar">Cancelar</a>
			</div>
		</div>
	</div>
	
	<div class="estadoDeshabilitado2 clearfix ocultar">
		<div class="columna1">
			<strong class="clearfix">Cobro Revertido</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip ext" href="#TTComunicados"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje grande -->
			<div class="mensaje_grande mensaje_grande_reloj">
				En proceso de <strong>deshabilitaci&oacute;n</strong>
			</div>
		</div>
	</div>
	
	<div class="estadoDeshabilitado3 clearfix ocultar">
		<div class="columna1">
			<strong class="clearfix">Cobro Revertido</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip ext" href="#TTComunicados"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje grande -->
			<div class="mensaje_grande mensaje_grande_reloj">
				<strong>Cobro Revertido se ha configurado</strong>
			</div>
		</div>
	</div>
	
</div>
<!-- /Fila de información -->
</h:panelGroup>
<h:panelGroup rendered="#{!administracionServicios.administracionServiciosBean.servicioCobroRevertidoBean.visible}"> 
	<jsp:include page="../servicioNoDisponible.jsp" flush="true">
    	<jsp:param value="Cobro Revertido" name="serviceName"/>
        <jsp:param value="Cargo por uso" name="cargo"/>
        <jsp:param value="TTComunicados" name="tooltip"/>
     </jsp:include>
</h:panelGroup>	        
</entel:view>