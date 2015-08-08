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

<entel:view name="avisale" inverse="true"> 
<!-- Fila de información -->     
<div class="tabla_fila clearfix <%=resaltador%>">

	<div class="columna1">
		<strong class="clearfix">Av&iacute;sale</strong>
		<label class="titulo_inferior">Sin cargo</label>
	</div>
	<div class="columna2">
		<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisale"></a>
	</div>

    <div class="columna3">
    	<!-- Mensaje grande -->
        <h:panelGroup layout="block" styleClass="mensaje_grande #{administracionServicios.administracionServiciosBean.servicioAvisale.activo ? 'mensaje_grande_habilitado' :'mensaje_grande_deshabilitado'}">
        	<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioAvisale.activo ? 'Habilitado' :'Deshabilitado'}"/>
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

<entel:view name="avisale">

<script type="text/javascript">

	var tipoLista = '<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.tipoLista}" />';

	$(document).ready(function() {

		if(tipoLista == 'negra' || tipoLista == 'default') {
			$("#radio_avisar_todos").attr("checked","checked");
		}
		if(tipoLista == 'blanca' || tipoLista == '') {
			$("#radio_solo_avisar_a").attr("checked","checked");
		}
		
	});

</script>

<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioAvisale.visible}">

<!-- Fila de información -->
<div class="tabla_fila clearfix <%=resaltador%>">

	<!-- Estado habilitado (estado 1) -->
	<div class="estadoHabilitado clearfix <h:outputText value="#{administracionServicios.administracionServiciosBean.servicioAvisale.activo ? '' :'ocultar'}"/>">
		<div class="columna1">
			<strong class="clearfix">Av&iacute;sale</strong>
			<label class="titulo_inferior">Sin cargo</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisale"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje habilitado/deshabilitado -->
			<div class="mensaje habilitado">Habilitado</div>
			
			<!-- Btoón deshabilitado/habilitado -->
			<div class="btnDeshabilitar">
				<a class="btnAzulDelgado btnAncho2 caso1" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Avisale/Deshabilitar');"><span>Deshabilitar</span></a>
			</div>
			
			<!-- Cabecera del boton configurar/cerrar -->
			<div class="enlaceConfigurar">
				<a href="#" class="enlaceConfigurar">Configurar</a>
			</div>
			<div class="cerrarConfigurar">
				<a href="#" class="enlaceCerrar">Cerrar</a>
			</div>
			<!-- /Cabecera del boton configurar/cerrar -->                           
		</div>
		
		<!-- Contenido de Configurar/cerrar -->
		<div class="configurar_contenido clearfix">
		
			<!-- Contenido común Configurar/cerrar -->
			<div class="configurar_contenido_superior clearfix">
				<div class="clearfix"><strong>Elige lo que deseas hacer:</strong></div>
				<div class="opciones clearfix">
					<div class="contenido_superior_opcion">
						<label class="label_radio_configurar">
							<input id="radio_avisar_todos" type="radio" value="avisar_todos" name="avisar" 
								class="radio_configurar mostar_contenido_inferior" />Avisar a todos</label>
					</div>
					<div class="contenido_superior_opcion">
						<label class="label_radio_configurar">
							<input id="radio_solo_avisar_a" type="radio" value="avisar_a" name="avisar" 
								class="radio_configurar" />Solo avisar a</label>
					</div>
				</div>
			</div>
			<!-- /Contenido común Configurar/cerrar -->
			
			<!-- Contenido de avisar a todos -->
			<div class="configurar_contenido_inferior clearfix padre">
			
				<div class="opciones">
					<strong>Si deseas excluir n&uacute;meros, ingresalos en la siguiente tabla.</strong>
				</div>

				<form name="validar_numero" class="validar_numero" action="#">
					<jsp:include page="/token.jsp" flush="true"/>
					<div class="opciones alertador clearfix">
						<div class="opciones_elemento campo">
							<input type="text" name="numero"  class="inputBox numero_avisale input_numerico agregar_todos" style="width: 150px;" maxlength="8"/>
						</div>
						<div class="opciones_boton">
							<a class="btnAzul largo btn_agregar_ccVoz" href="#"><span>No Avisar</span></a>
						</div>
					</div>
				</form>
				
				<!-- Alerta oculta -->
				<div id="alerta_oculta" class="alerta_oculta clearfix">
					<label class="alerta_label">M&aacute;ximo 3 n&uacute;meros</label>
				</div>
				<!-- /Alerta oculta -->
				
				<h:form id="avisaleForm_guardarConfiguracion_avisarTodos">
				<jsp:include page="/token.jsp" flush="true"/>
				<!-- Tabla de numeros excluidos -->
				<div class="opciones tabla_interna">

					<input type="hidden" class="numerosExcluidosLista" name="lista_numero_avisar_todosaux" 
						value="<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.numerosCommaSeparated}" 
						rendered="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.tipoLista == avisaleController.avisarTodos}" />" />
					
					<div class="top_tabla"></div>
					<div class="main_tabla">
						N&uacute;meros Excluidos
					</div>
					<div class="bottom_tabla"></div>
					
					<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.tipoLista == avisaleController.avisarTodos}">
					<it:iterator value="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.numerosAvisale}" var="numeroAvisale">
					<div class="tabla_interna_fila clearfix">
						<div class="tabla_interna_columna1">
							<h:outputText value="#{numeroAvisale}"/>
						</div>
						<div class="tabla_interna_columna2">
							<a href="#" class="enlaceEliminar eliminarExcluidos">eliminar</a>
						</div>
					</div>
					</it:iterator>    
					</h:panelGroup>
					
				</div>
				<!-- /Tabla de numeros -->
				
				<div class="opciones centrar">				
					 <a class="btnAzulGrande guardarConfiguracion opGuardarAt" href="#">
			          <span>Guardar configuraci&oacute;n</span>
			         </a>	
				</div>
				</h:form>
				
			</div>
			<!-- /Contenido de avisar a todos -->
			
			<!-- Contenido de avisar a -->
			<div class="configurar_contenido_inferior2 clearfix padre">
			
				<div class="opciones">
					<strong>Quiero avisarle solo a:</strong>
				</div>
				<form name="validar_numero" class="validar_numero" action="#">
					<jsp:include page="/token.jsp" flush="true"/>
					<div class="opciones alertador clearfix">
						<div class="opciones_elemento campo">
							<input type="text" name="numero" class="inputBox numero_avisale input_numerico agregar_a" style="width: 80px;" maxlength="8"/>
						</div>
						<div class="opciones_boton">
							<a class="btnAzul largo btn_agregar2" href="#"><span>Agregar n&uacute;mero Entel PCS</span></a>
						</div>
					</div>
				</form>
				
				<h:form id="avisaleForm_guardarConfiguracion_soloAvisarA">
					<jsp:include page="/token.jsp" flush="true"/>
				<!-- Tabla de numeros a avisar-->
				<div class="opciones tabla_interna">

					<input type="hidden" class="numerosAvisadosLista" name="lista_numero_avisar_aaux" 
						value="<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.numerosCommaSeparated}" 
						rendered="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.tipoLista == avisaleController.soloAvisarA}" />" />
					
					<div class="top_tabla"></div>
					<div class="main_tabla">
						N&uacute;meros que ser&aacute;n avisados
					</div>
					<div class="bottom_tabla"></div>
					
					<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.tipoLista == avisaleController.soloAvisarA}">
					<it:iterator value="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.numerosAvisale}" var="numeroAvisale">
					<div class="tabla_interna_fila clearfix">
						<div class="tabla_interna_columna1">
							<h:outputText value="#{numeroAvisale}"/>
						</div>
						<div class="tabla_interna_columna2">
							<a href="#" class="enlaceEliminar">eliminar</a>
						</div>
					</div>
					</it:iterator>    
					</h:panelGroup>

				</div>
				<!-- /Tabla de numeros -->
				
				<div class="opciones centrar">
				     <a class="btnAzulGrande guardarConfiguracion opGuardarSA" href="#">
			          <span>Guardar configuraci&oacute;n</span>
			         </a>
				</div>
				</h:form>						
				
			</div>
			<!-- /Contenido de avisar a -->
			
		</div>
		<!-- /Contenido de Configurar/cerrar -->
		
	</div>
	<!-- /Estado habilitado (estado 1) -->
	
	<div class="estadoHabilitado1 clearfix ocultar">
		<div class="columna1">
			<strong class="clearfix">Av&iacute;sale</strong>
			<label class="titulo_inferior">Sin cargo</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisale"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje grande -->
			<div class="mensaje_grande mensaje_grande_reloj">
				En proceso de <strong>habilitaci&oacute;n</strong>
			</div>
		</div>
	</div>
	
	<div class="estadoDeshabilitado clearfix <h:outputText value="#{administracionServicios.administracionServiciosBean.servicioAvisale.activo ? 'ocultar' : ''}"/>">
		
		<div class="columna1">
			<strong class="clearfix">Av&iacute;sale</strong>
			<label class="titulo_inferior">Sin cargo</label>
		</div>
		
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisale"></a>
		</div>
		
		<div class="columna3">
			<!-- Mensaje habilitado/deshabilitado -->
			<div class="mensaje deshabilitado">Deshabilitado</div>
			
			<!-- Btoón deshabilitado/habilitado -->
			<div class="btnHabilitar">
				<a class="btnAzulDelgado btnAncho enlaceConfigurar" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Avisale/Habilitar');"><span>Habilitar</span></a>
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
			<div class="configurar_contenido_superior clearfix">
				<div class="clearfix"><strong>Elige lo que deseas hacer:</strong></div>
				<div class="opciones clearfix">
					<div class="contenido_superior_opcion">
						<label class="label_radio_configurar">
							<input type="radio" value="avisar_todos" 
								name="avisar" class="radio_configurar mostar_contenido_inferior" />Avisar a todos</label>
					</div>
					<div class="contenido_superior_opcion">
						<label class="label_radio_configurar">
							<input type="radio" value="avisar_a" 
								name="avisar" class="radio_configurar" />Solo avisar a</label>
					</div>
				</div>
			</div>
			<!-- /Contenido común Configurar/cerrar -->
			
			<!-- Contenido de avisar a todos -->
			<div class="configurar_contenido_inferior clearfix padre">
			
				<div class="opciones">
					<strong>Puedes excluir hasta 3 n&uacute;meros, los cuales no recibir&aacute;n aviso.</strong>
				</div>

				<form name="validar_numero" class="validar_numero" action="#">
					<jsp:include page="/token.jsp" flush="true"/>
					<div class="opciones alertador clearfix">
						<div class="opciones_elemento campo">
							<input type="text" name="numero"  class="inputBox numero_avisale input_numerico agregar_todos" style="width: 150px;" maxlength="8"/>
						</div>
						<div class="opciones_boton">
							<a class="btnAzul largo btn_agregar_ccVoz" href="#"><span>No Avisar</span></a>
						</div>
					</div>
				</form>
				
				<!-- Alerta oculta -->
				<div id="alerta_oculta" class="alerta_oculta clearfix">
					<label class="alerta_label">M&aacute;ximo 3 n&uacute;meros</label>
				</div>
				<!-- /Alerta oculta -->
				

				<h:form id="avisaleForm_guardarConfiguracion_avisarTodos2">
					<jsp:include page="/token.jsp" flush="true"/>
				<!-- Tabla de numeros excluidos -->
				<div class="opciones tabla_interna">
				
					<input type="hidden" class="numerosExcluidosLista" name="lista_numero_avisar_todosaux" 
						value="<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.numerosCommaSeparated}" 
						rendered="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.tipoLista == avisaleController.avisarTodos}" />" />

					<div class="top_tabla"></div>
					<div class="main_tabla">
						N&uacute;meros Excluidos
					</div>
					<div class="bottom_tabla"></div>
					
					<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.tipoLista == avisaleController.avisarTodos}">
					<it:iterator value="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.numerosAvisale}" var="numeroAvisale">
					<div class="tabla_interna_fila clearfix">
						<div class="tabla_interna_columna1">
							<h:outputText value="#{numeroAvisale}"/>
						</div>
						<div class="tabla_interna_columna2">
							<a href="#" class="enlaceEliminar eliminarExcluidos">eliminar</a>
						</div>
					</div>
					</it:iterator>    
					</h:panelGroup>

				</div>
				<!-- /Tabla de numeros -->

				<div class="opciones centrar">					
				     <a class="btnAzulGrande guardarConfiguracion opActivarAT" href="#">
			          <span>Guardar configuraci&oacute;n</span>
			         </a>		
				</div>

				</h:form>

			</div>
			<!-- /Contenido de avisar a todos -->
			
			<!-- Contenido de avisar a -->
			<div class="configurar_contenido_inferior2 clearfix padre">
			
				<div class="opciones">
					<strong>Quiero avisarle solo a:</strong>
				</div>
				<form name="validar_numero" class="validar_numero" action="#">
					<jsp:include page="/token.jsp" flush="true"/>
					<div class="opciones alertador clearfix">
						<div class="opciones_elemento campo">
							<input type="text" name="numero" class="inputBox numero_avisale input_numerico agregar_a" style="width: 80px;" maxlength="8"/>
						</div>
						<div class="opciones_boton">
							<a class="btnAzul largo btn_agregar2" href="#"><span>Agregar n&uacute;mero Entel PCS</span></a>
						</div>
					</div>
				</form>

				<h:form id="avisaleForm_guardarConfiguracion_soloAvisarA2">				
					<jsp:include page="/token.jsp" flush="true"/>
				<!-- Tabla de numeros a avisar-->
				<div class="opciones tabla_interna">

					<input type="hidden" class="numerosAvisadosLista" name="lista_numero_avisar_aaux" 
						value="<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.numerosCommaSeparated}" 
						rendered="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.tipoLista == avisaleController.soloAvisarA}" />" />

					<div class="top_tabla"></div>
					<div class="main_tabla">
						N&uacute;meros que ser&aacute;n avisados
					</div>
					<div class="bottom_tabla"></div>

					<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.tipoLista == avisaleController.soloAvisarA}">
					<it:iterator value="#{administracionServicios.administracionServiciosBean.servicioListaAvisaleBean.numerosAvisale}" var="numeroAvisale">
					<div class="tabla_interna_fila clearfix">
						<div class="tabla_interna_columna1">
							<h:outputText value="#{numeroAvisale}"/>
						</div>
						<div class="tabla_interna_columna2">
							<a href="#" class="enlaceEliminar">eliminar</a>
						</div>
					</div>
					</it:iterator>    
					</h:panelGroup>					
   
				</div>
				<!-- /Tabla de numeros -->
				
				<div class="opciones centrar">
				    <a class="btnAzulGrande guardarConfiguracion opActivarSA" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Avisale/Habilitado');">
			          <span>Guardar configuraci&oacute;n</span>
			         </a>					
				</div>

				</h:form>										
				
			</div>
			<!-- /Contenido de avisar a -->
			
		</div>
		<!-- /Contenido de Configurar/cerrar -->
	</div>
	
	<div class="estadoDeshabilitado1 clearfix ocultar">

		<div class="columna1">
			<strong class="clearfix">Av&iacute;sale</strong>
			<label class="titulo_inferior">Sin cargo</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisale"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje habilitado/deshabilitado -->
			<div class="mensaje_ancho"><strong>Deshabilitar&aacute;s Av&iacute;sale</strong><br />Los n&uacute;meros configurados ser&aacute;n eliminados.
			</div>
			
			<!-- Btoón deshabilitado/habilitado -->
			<div class="btnDeshabilitar">
			<h:form id="avisaleForm_deshabilitar">
				<jsp:include page="/token.jsp" flush="true"/>
				<h:commandLink styleClass="btnAzulDelgado btnAncho2 caso4" action="#{avisaleController.desactivarAvisale}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Avisale/Deshabilitado');">
						<span>Deshabilitar</span></h:commandLink>
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
			<strong class="clearfix">Av&iacute;sale</strong>
			<label class="titulo_inferior">Sin cargo</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisale"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje grande -->
			<div class="mensaje_grande mensaje_grande_reloj">
				En proceso de <strong>deshabilitaci&oacute;n</strong>
			</div>
		</div>
	</div>
	<!-- Paso Confirmar Habilitar -->
	<h:form id="confirmarAvisale">
		<jsp:include page="/token.jsp" flush="true"/>
	  <div class="confirmarHabilitar clearfix ocultar">	
			<div class="columna1">
				<strong class="clearfix">Av&iacute;sale</strong>
				<label class="titulo_inferior">Sin cargo</label>
			</div>
			<div class="columna2">
				<a class="ico_interrogacionNuevo autoTooltip" href="#TTCobroRevertido"></a>
			</div>
			<div class="columna3">
				<!-- Mensaje habilitado/deshabilitado -->
				<div class="mensaje_ancho"><strong>Habilitar&aacute;s Av&iacute;sale</strong><br />Los n&uacute;meros configurados ser&aacute;n Habilitados.
				</div>
				
				<!-- Btoon deshabilitado/habilitado -->
			<!-- Btoon deshabilitado/habilitado -->
				
				<div class="btnHabilitar activarAT">
				    <h:commandLink  styleClass="btnAzulDelgado btnAncho caso3 avisale" action="#{avisaleController.activarAvisarTodos}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Avisale/Habilitado');"><span>Habilitar</span></h:commandLink>
				</div>
				<div class="btnHabilitar activarSA">
				    <h:commandLink  styleClass="btnAzulDelgado btnAncho caso3 avisale" action="#{avisaleController.activarSoloAvisarA}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Avisale/Habilitado');"><span>Habilitar</span></h:commandLink>
				</div>
				<div class="btnHabilitar guardarAT">
				    <h:commandLink  styleClass="btnAzulDelgado btnAncho caso3 avisale" action="#{avisaleController.guardarConfiguracionAvisarTodos}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Avisale/Habilitado');"><span>Habilitar</span></h:commandLink>
				</div>
				<div class="btnHabilitar guardarSA">
				    <h:commandLink  styleClass="btnAzulDelgado btnAncho caso3 avisale" action="#{avisaleController.guardarConfiguracionSoloAvisarA}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Avisale/Habilitado');"><span>Habilitar</span></h:commandLink>
				</div>
				
				<!-- Boton Cancelar -->
				<div class="enlaceCancelar">
					<a href="#" class="enlaceCancelarH">Cancelar</a>
				</div>
			</div>
		</div>
		<input type="hidden" class="numerosExcluidosLista" name="lista_numero_avisar_todos" />
		<input type="hidden" class="numerosAvisadosLista" name="lista_numero_avisar_a" 	/>
		
		</h:form>
	<!-- Fin Paso Confirmar Habilitar -->
	
	<div class="estadoDeshabilitado3 clearfix ocultar">
		<div class="columna1">
			<strong class="clearfix">Av&iacute;sale</strong>
			<label class="titulo_inferior">Sin cargo</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip ext" href="#TTAvisale"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje grande -->
			<div class="mensaje_grande mensaje_grande_reloj">
				<strong>Av&iacute;sale se ha configurado</strong>
			</div>
		</div>
	</div>
	
</div>

<!-- /Fila de información -->
</h:panelGroup>

<h:panelGroup rendered="#{!administracionServicios.administracionServiciosBean.servicioAvisale.visible}"> 
	<jsp:include page="../servicioNoDisponible.jsp" flush="true">
		<jsp:param value="Numeraci&oacute;n 609" name="serviceName"/>
		<jsp:param value="" name="cargo"/>
		<jsp:param value="TooltipNumeracion609" name="tooltip"/>
    </jsp:include>
</h:panelGroup>
</entel:view>