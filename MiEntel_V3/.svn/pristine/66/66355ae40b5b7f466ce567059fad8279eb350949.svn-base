<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<% 
	String resaltador = "";
	if(request.getParameter("rowNumber") != null){
		resaltador = (Integer.parseInt(request.getParameter("rowNumber"))) % 2 == 0? "resaltador":"";
	}
%>

<entel:view name="avisoVencFactura" inverse="true"> 
<!-- Fila de información -->     
<div class="tabla_fila clearfix <%=resaltador%>">

	<div class="columna1">
		<strong class="clearfix">Aviso vencimiento factura</strong>
		<label class="titulo_inferior">Cargo por uso</label>
	</div>
	<div class="columna2">
		<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisoVencimiento"></a>
	</div>

    <div class="columna3">
    	<!-- Mensaje grande -->
        <h:panelGroup layout="block" styleClass="mensaje_grande #{administracionServicios.administracionServiciosBean.servicioFactElectronicaBean.activo ? 'mensaje_grande_habilitado' :'mensaje_grande_deshabilitado'}">
        	<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioFactElectronicaBean.activo ? 'Habilitado' :'Deshabilitado'}"/>
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


<entel:view name="avisoVencFactura">
<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioFactElectronicaBean.visible}">

<script type="text/javascript">
	$(document).ready(function() {
	$(".select_dias")[0].setValue('<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioFactElectronicaBean.parametro6}"/>');
    });
	</script>
<h:form>            
		<jsp:include page="/token.jsp" flush="true"/>
		<!-- Fila de informacion -->
		<div class="tabla_fila clearfix <%=resaltador%>">
		<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioFactElectronicaBean.activo}">
			<div class="estadoHabilitado clearfix">
				<div class="columna1">
					<strong class="clearfix">Aviso vencimiento factura</strong>
					<label class="titulo_inferior">Cargo por uso</label>
				</div>
				<div class="columna2">
					<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisoVencimiento"></a>
				</div>
				<div class="columna3">
				   <!-- Mensaje habilitado/deshabilitado -->
				   <div class="mensaje habilitado">Habilitado</div>
				   
				   <!-- Btoon deshabilitado/habilitado -->
				   <div class="btnDeshabilitar">
					   <a class="btnAzulDelgado btnAncho2 caso1" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mensajeria/Aviso vencimiento factura/Deshabilitar');"><span>Deshabilitar</span></a>
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
						<div class="clearfix">Selecciona los d&iacute;as de anticipaci&oacute;n al vencimiento de tu factura que prefieres recibir el aviso a trav&eacute;s de un mensaje de texto en tu m&oacute;vil.</div>
						<div class="opciones alertador">
							<div class="opciones_elemento">
								<h:selectOneListbox id="select_dias" style="width: 60px;" styleClass="selectBox select_dias" value="#{avisoFactElectronicaController.numeroDias}">
									<f:selectItems id="select_dias_options" value="#{avisoFactElectronicaController.diasAnticipacionItems}"/>
								</h:selectOneListbox>
							</div>
							<div class="btnHabilitar">
				                   <a class="btnAzul largo caso1H selectDias" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mensajeria/Aviso vencimiento factura/Habilitado');"><span>Guardar</span>
				                   </a>			    
			                </div>
						</div>
					</div>
					<!-- /Contenido común Configurar/cerrar -->
				   
				</div>
				<!-- /Contenido de Configurar/cerrar -->
			   
			</div>
		</h:panelGroup>
	 	<!-- Paso Confirmar Habilitar -->
			  <div class="confirmarHabilitar clearfix ocultar">	
					<div class="columna1">
						<strong class="clearfix">Aviso vencimiento factura</strong>
		                <label class="titulo_inferior">Cargo por uso</label>
					</div>
					<div class="columna2">
						<a class="ico_interrogacionNuevo autoTooltip" href="#TTCobroRevertido"></a>
					</div>
					<div class="columna3">
						<!-- Mensaje habilitado/deshabilitado -->
						<div class="mensaje_ancho"><strong>Habilitar&aacute;s Aviso vencimiento factura</strong><br />
						</div>
						
						<!-- Btoon deshabilitado/habilitado -->
					<!-- Btoon deshabilitado/habilitado -->
						<div class="btnHabilitar">
						    <h:commandLink  styleClass="btnAzulDelgado btnAncho caso3" action="#{avisoFactElectronicaController.activarFacturaElectronica}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mensajeria/Aviso vencimiento factura/Habilitado');"><span>Habilitar</span></h:commandLink>
						</div>
						
						<!-- Boton Cancelar -->
						<div class="enlaceCancelar">
							<a href="#" class="enlaceCancelarH">Cancelar</a>
						</div>
					</div>
				</div>
	         <!-- Fin Paso Confirmar Habilitar -->		  
		   
			<div class="estadoHabilitado1 clearfix ocultar">
				<div class="columna1">
					<strong class="clearfix">Aviso vencimiento factura</strong>
					<label class="titulo_inferior">Sin cargo</label>
				</div>
				<div class="columna2">
					<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisoVencimiento"></a>
				</div>
				<div class="columna3">
					<!-- Mensaje grande -->
					<div class="mensaje_grande mensaje_grande_reloj">
						En proceso de <strong>habilitaci&oacute;n</strong>
					</div>
				</div>
			</div>
		   
		<h:panelGroup rendered="#{!administracionServicios.administracionServiciosBean.servicioFactElectronicaBean.activo}">
			<div class="estadoDeshabilitado clearfix">
				<div class="columna1">
					<strong class="clearfix">Aviso vencimiento factura</strong>
					<label class="titulo_inferior">Sin cargo</label>
				</div>
				<div class="columna2">
					<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisoVencimiento"></a>
				</div>
				<div class="columna3">
					<!-- Mensaje habilitado/deshabilitado -->
					<div class="mensaje deshabilitado">Deshabilitado</div>
				   
					<!-- Btoon deshabilitado/habilitado -->
					<div class="btnHabilitar">
					<a href="#" class="btnAzulDelgado btnAncho enlaceConfigurar" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mensajeria/Aviso vencimiento factura/Habilitar');"><span>Habilitar</span></a>
					</div>
				   
				<!-- Boton Habilitar/Cancelar -->                              
                 <div class="cerrarConfigurar">
                      <a href="#" class="enlaceCerrar habilitarCancelar">Cancelar</a>
                 </div>
                <!-- Boton Configurar/Cancelar -->
				   <!-- /Cabecera del boton configurar/cerrar -->
				</div>
				
				  <!-- Contenido de Configurar/cerrar -->
			   <div class="configurar_contenido clearfix">
			   
				<!-- Contenido común Configurar/cerrar -->
					<div class="configurar_contenido_superior clearfix">
						<div class="clearfix">Selecciona los d&iacute;as de anticipaci&oacute;n al vencimiento de tu factura que prefieres recibir el aviso a trav&eacute;s de un mensaje de texto en tu m&oacute;vil.</div>
						<div class="opciones alertador">
							<div class="opciones_elemento">
								<h:selectOneListbox id="select_dias2" style="width: 60px;" styleClass="selectBox select_dias" value="#{avisoFactElectronicaController.numeroDias}">
									<f:selectItems id="select_dias_options2" value="#{avisoFactElectronicaController.diasAnticipacionItems}"/>
								</h:selectOneListbox>
							</div>
							
							<div class="btnHabilitar">
			                   <a class="btnAzul largo caso1H selectDias" href="#" ><span>Guardar</span>
				                </a>			    
			                </div>							
						</div>
					</div>
					<!-- /Contenido común Configurar/cerrar -->
				   
				</div>
				<!-- /Contenido de Configurar/cerrar -->
				
			</div>
		</h:panelGroup>

			<div class="estadoDeshabilitado1 clearfix ocultar">

				<div class="columna1">
					<strong class="clearfix">Aviso vencimiento factura</strong>
					<label class="titulo_inferior">Sin cargo</label>
				</div>
				<div class="columna2">
					<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisoVencimiento"></a>
				</div>
				<div class="columna3">
					<!-- Mensaje habilitado/deshabilitado -->
					<div class="mensaje_ancho"><strong>Deshabilitar&aacute;s Aviso vencimiento factura</strong>
					</div>
				   
					<!-- Btoon deshabilitado/habilitado -->
					<div class="btnDeshabilitar">
             			<h:commandLink styleClass="btnAzulDelgado btnAncho2 caso4" action="#{avisoFactElectronicaController.desactivarFacturaElectronica}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mensajeria/Aviso vencimiento factura/Deshabilitado');">
             					<span>Deshabilitar</span></h:commandLink>					
					</div>
				   
					<!-- Boton Cancelar -->
					<div class="enlaceCancelar">
						<a href="#" class="enlaceCancelar">Cancelar</a>
					</div>
				</div>
			</div>
		   
			<div class="estadoDeshabilitado2 clearfix ocultar">
				<div class="columna1">
					<strong class="clearfix">Aviso vencimiento factura</strong>
					<label class="titulo_inferior">Sin cargo</label>
				</div>
				<div class="columna2">
					<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisoVencimiento"></a>
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
					<strong class="clearfix">Aviso vencimiento factura</strong>
					<label class="titulo_inferior">Sin cargo</label>
				</div>
				<div class="columna2">
					<a class="ico_interrogacionNuevo autoTooltip" href="#TTAvisoVencimiento"></a>
				</div>
				<div class="columna3">
					<!-- Mensaje grande -->
					<div class="mensaje_grande mensaje_grande_reloj">
						<strong>Aviso vencimiento factura en proceso de configuraci&oacute;n</strong>
					</div>
				</div>
			</div>
 
		</div>
		<!-- /Fila de informacion -->
	</h:form>   
</h:panelGroup> 

<h:panelGroup rendered="#{!administracionServicios.administracionServiciosBean.servicioFactElectronicaBean.visible}"> 
	<jsp:include page="../servicioNoDisponible.jsp" flush="true">
    	<jsp:param value="Aviso vencimiento factura" name="serviceName"/>
        <jsp:param value="Sin cargo" name="cargo"/>
        <jsp:param value="TTAvisoVencimiento" name="tooltip"/>
     </jsp:include>
</h:panelGroup>	               
</entel:view>