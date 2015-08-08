<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it"  uri="/WEB-INF/tld/IteratorTag.tld"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<% 		
	String resaltador = "";
	if(request.getParameter("rowNumber") != null){
		resaltador = (Integer.parseInt(request.getParameter("rowNumber"))) % 2 == 0? "resaltador":"";
	}
%>

<cm:search id="headerAuthMsg2" query="idContenido = 'mensajeNoPermisos'" useCache="false"/>
	
<entel:view name="buzonVozPremium" inverse="true">

<!-- Fila de información -->     
<div class="tabla_fila clearfix <%=resaltador%>">

	<div class="columna1">
		<strong class="clearfix">Buz&oacute;n de Voz</strong>
		<label class="titulo_inferior">Cargo por uso</label>
	</div>
	<div class="columna2">
		<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
	</div>

    <div class="columna3">
    	<!-- Mensaje grande -->
        <h:panelGroup layout="block" styleClass="mensaje_grande #{administracionServicios.administracionServiciosBean.servicioBuzon.activo ? 'mensaje_grande_habilitado' :'mensaje_grande_deshabilitado'}">
        	<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioBuzon.activo ? 'Habilitado' :'Deshabilitado'}"/>
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

<entel:view name="buzonVozPremium">

<script>
	$(document).ready(function() {
		$('input[name="email"]').val('<h:outputText value="#{buzonVozController.emailBuzonPremium}"/>');
		$('input[name="emailaux"]').each(function() {
			$(this).val('<h:outputText value="#{buzonVozController.emailBuzonPremium}"/>');
			$(this).change(function() {
				$('input[name="email"]').val($(this).val());
			});
		});
		
		if($('#buzon_basico_hidden').val()=="true"){
			$('input[name="avisar"]')[0].checked = true;
		}
		if($('#buzon_premium_hidden').val()=="true"){
			$(".radio_configurar_buzon").trigger('click');
			$('input[name="avisar"]')[1].checked = true;
			
			if($('#tipoPerfil_hidden').val() == $('#perfilMMS_hidden').val()){
				$('input[name=tipoPerfil]')[0].checked = true;
			}else if($('#tipoPerfil_hidden').val() == $('#perfilMail_hidden').val()){
				$(".radio_configurar_premium").trigger('click');
				$('input[name=tipoPerfil]')[1].checked = true;
			}
		}
			
	});		
</script>

<!-- Fila de información -->
<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioBuzon.visible}">

<div class="tabla_fila clearfix <%=resaltador%>">
	<h:form id="formBuzonVoz" styleClass="validar_email" >  
		<jsp:include page="/token.jsp" flush="true"/>
	<!-- Estado habilitado (estado 1) -->
	<div class="estadoHabilitado clearfix <h:outputText value="#{administracionServicios.administracionServiciosBean.servicioBuzon.activo? '' :'ocultar'}"/>">
		<div class="columna1">
			<strong class="clearfix">Buz&oacute;n de Voz</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje habilitado/deshabilitado -->
			<div class="mensaje habilitado">Habilitado</div>

			<!-- Btoon deshabilitado/habilitado -->
			<h:panelGroup rendered="#{!administracionServicios.administracionServiciosBean.servicioBuzon.tep}">
				<div class="btnDeshabilitar">
					<a class="btnAzulDelgado btnAncho2 caso1" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Deshabilitar');"><span>Deshabilitar</span></a>
				</div>
			</h:panelGroup>
			
			<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioBuzon.tep}">
				<div class="mensaje msjActualizacion">Actualizacion en proceso</div>
			</h:panelGroup>

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
				<div class="clearfix"><strong>Selecciona tu buz&oacute;n</strong></div>
				<div class="opciones clearfix">
					<div class="contenido_superior_opcion">
						<label class="label_radio_configurar">
							<input type="radio" value="buzon_basico" name="avisar" 
								class="radio_configurar_buzon mostar_contenido_inferior" />B&aacute;sico</label>
					</div>
					
					<div class="contenido_superior_opcion">
						<label class="label_radio_configurar">
							<entel:view name="radioPremium">
								<input type="radio" value="buzon_premium" name="avisar" class="radio_configurar_buzon"
								 	<h:outputText rendered="#{administracionServicios.administracionServiciosBean.servicioBuzon.premium}" value="checked=\"checked\"" /> />Premium ($490/mes)
							</entel:view>	
							<entel:view name="radioPremium" inverse="true">
								<input type="radio" value="buzon_premium" name="avisar" class="radio_configurar_buzon"
									<h:outputText rendered="#{administracionServicios.administracionServiciosBean.servicioBuzon.premium}" value="checked=\"checked\"" /> />Premium ($490/mes)
							</entel:view>							
						</label>
					</div>
										
				</div>
			</div>
			<!-- /Contenido común Configurar/cerrar -->

			<!-- Contenido de buzón básico -->
			<div class="configurar_contenido_inferior1 clearfix padre">

				<div class="opciones centrar">
					<a class="btnAzulGrande guardarConfiguracion guardarHabilitar basico" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Habilitado');">
			          <span>Guardar configuraci&oacute;n</span>
			        </a>
				</div>

			</div>
			<!-- /Contenido de buzón básico -->

			<!-- Contenido de buzón premium -->
			<div class="configurar_contenido_inferior clearfix padre">

				<entel:view name="permisosAutoatencion">
				
					<div class="opciones">
						<strong>Puedes elegir entre recibir tus mensajes de voz como MMS o Email.</strong>
					</div>
	
					<div class="opciones clearfix">
	
						<div class="contenido_superior_opcion">
							<label class="label_radio_configurar flotar_izq">
								<input type="radio" name="tipoPerfil" class="radio_configurar_premium mostar_contenido_inferior"
									value="<h:outputText value="#{buzonVozController.tipoPerfilMMS}"/>"
									<h:outputText rendered="#{administracionServicios.administracionServiciosBean.servicioBuzon.tipoPerfil eq buzonVozController.tipoPerfilMMS}" value="checked=\"checked\"" /> />MMS</label>
							<a class="ico_interrogacionNuevo toolTop_margen autoTooltip ext" href="#TTBuzondeVozMMS"></a>
						</div>
											
						<div class="contenido_superior_opcion">
							<label class="label_radio_configurar toolTop_margen flotar_izq">
								<input type="radio" name="tipoPerfil" class="radio_configurar_premium"
									value="<h:outputText value="#{buzonVozController.tipoPerfilMail}"/>" />Email</label>
							<a class="ico_interrogacionNuevo toolTop_margen autoTooltip ext" href="#TTBuzondeVozEMAIL"></a>
						</div>
	
					</div>
	
					<div class="opcion_email clearfix">
							<label>Email:</label>
							<input type="text" name="emailaux" class="inputBox emailaux" style="width: 150px;" maxlength="30" value="" />
					</div>
					
					<div class="opciones centrar">
						<a class="btnAzulGrande guardarConfiguracion guardarHabilitar premium" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Habilitado');">
				           <span>Guardar configuraci&oacute;n</span>
				        </a>
					</div>
					
				</entel:view>
				
				<entel:view name="permisosAutoatencion" inverse="true">
					<div class="deshabilitado">
						<cm:getProperty node='${headerAuthMsg2[0]}' name='html'/>
					</div>
				</entel:view>
				
			</div>
			<!-- /Contenido de buzón premium -->		                            

		</div>
		<!-- /Contenido de Configurar/cerrar -->

	</div>
	<!-- /Estado habilitado (estado 1) -->
	
	<!-- Paso Confirmar Habilitar -->
  <div class="confirmarHabilitar clearfix ocultar">	
		<div class="columna1">
			<strong class="clearfix">Buz&oacute;n de Voz</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip" href="#TTCobroRevertido"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje habilitado/deshabilitado -->
			<div class="mensaje_ancho"><strong>Habilitar&aacute;s el Buz&oacute;n de Voz</strong>
			</div>
			
			<!-- Btoon deshabilitado/habilitado -->
		<!-- Btoon deshabilitado/habilitado -->
			<div class="btnHabilitar habilitarbasico">
			    <h:commandLink styleClass="btnAzulDelgado btnAncho caso3" action="#{buzonVozController.activarBuzonBasico}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Habilitar');"><span>Habilitar</span></h:commandLink>
			</div>
			<div class="btnHabilitar habilitarpremium">
			    <h:commandLink styleClass="btnAzulDelgado btnAncho caso3" action="#{buzonVozController.activarBuzonPremium}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Habilitar');"><span>Habilitar</span></h:commandLink>
			</div>
			
			<!-- Boton Cancelar -->
			<div class="enlaceCancelar">
				<a href="#" class="enlaceCancelarH">Cancelar</a>
			</div>
		</div>
	</div>
	<!-- Fin Paso Confirmar Habilitar -->

	<!-- Estado habilitado (estado 2) -->
	<div class="estadoHabilitado1 clearfix ocultar">
		<div class="columna1">
			<strong class="clearfix">Buz&oacute;n de Voz</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje grande -->
			<div class="mensaje_grande mensaje_grande_reloj">
				En proceso de <strong>habilitaci&oacute;n</strong>
			</div>
		</div>
	</div>
	<!-- /Estado habilitado (estado 2) -->

	<div class="estadoDeshabilitado clearfix <h:outputText value="#{administracionServicios.administracionServiciosBean.servicioBuzon.activo? 'ocultar' : ''}"/>">
		<div class="columna1">
			<strong class="clearfix">Buz&oacute;n de Voz</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje habilitado/deshabilitado -->
			<div class="mensaje deshabilitado">Deshabilitado</div>

			<!-- Btoon deshabilitado/habilitado -->
			<h:panelGroup rendered="#{!administracionServicios.administracionServiciosBean.servicioBuzon.tep}">
				<div class="btnHabilitar">
					<!--<a class="btnAzulDelgado btnAncho caso3" href="#"><span>Habilitar</span></a>-->
					<a class="btnAzulDelgado btnAncho enlaceConfigurar" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Habilitar');"><span>Habilitar</span></a>
				</div>
			</h:panelGroup>
			
			<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioBuzon.tep}">
				<div class="mensaje msjActualizacion">Actualizacion en proceso</div>
			</h:panelGroup>

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
				<div class="clearfix"><strong>Selecciona tu buz&oacute;n</strong></div>
				<div class="opciones clearfix">
					<div class="contenido_superior_opcion">
						<label class="label_radio_configurar">
							<input type="radio" value="buzon_basico" name="avisar" class="radio_configurar_buzon mostar_contenido_inferior"
								<h:outputText rendered="#{administracionServicios.administracionServiciosBean.servicioBuzon.basico}" value="checked=\"checked\"" /> />B&aacute;sico
						</label>
					</div>
					
					<div class="contenido_superior_opcion">
						<label class="label_radio_configurar">
							<entel:view name="radioPremium">
								<input type="radio" value="buzon_premium" name="avisar" class="radio_configurar_buzon"
									<h:outputText rendered="#{administracionServicios.administracionServiciosBean.servicioBuzon.premium}" value="checked=\"checked\"" /> />Premium ($490/mes)
							</entel:view>	
							<entel:view name="radioPremium" inverse="true">
								<input type="radio" value="buzon_premium" name="avisar" class="radio_configurar_buzon"
									<h:outputText rendered="#{administracionServicios.administracionServiciosBean.servicioBuzon.premium}" value="checked=\"checked\"" /> />Premium ($490/mes)
							</entel:view>
						</label>
					</div>
									
				</div>
			</div>
			<!-- /Contenido común Configurar/cerrar -->

			<!-- Contenido de buzón básico -->
			<div class="configurar_contenido_inferior1 clearfix padre">

				<div class="opciones centrar">				
			      <a class="btnAzulGrande guardarConfiguracion guardarHabilitar basico" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Habilitado');">
			      <span>Guardar configuraci&oacute;n</span>
			      </a>			
				</div>

			</div>
			<!-- /Contenido de buzón básico -->

			<!-- Contenido de buzón premium -->
			<div class="configurar_contenido_inferior clearfix padre">
				
				
				<entel:view name="permisosAutoatencion">
					
					<div class="opciones">
						<strong>Puedes elegir entre recibir tus mensajes de voz como MMS o Email.</strong>
					</div>
	
					<div class="opciones clearfix">
	
						<div class="contenido_superior_opcion">
							<label class="label_radio_configurar flotar_izq">
								<input type="radio" name="tipoPerfil" class="radio_configurar_premium mostar_contenido_inferior" 
										value="<h:outputText value="#{buzonVozController.tipoPerfilMMS}"/>" />MMS</label>
							<a class="ico_interrogacionNuevo toolTop_margen autoTooltip ext" href="#TTBuzondeVozMMS"></a>
						</div>
	
						<div class="contenido_superior_opcion">
							<label class="label_radio_configurar toolTop_margen flotar_izq">
								<input type="radio" name="tipoPerfil" class="radio_configurar_premium" 
										value="<h:outputText value="#{buzonVozController.tipoPerfilMail}"/>" />Email</label>
							<a class="ico_interrogacionNuevo toolTop_margen autoTooltip ext" href="#TTBuzondeVozEMAIL"></a>
						</div>
	
					</div>
	
					<div class="opcion_email clearfix">
							<label>Email:</label>
							<input type="text" name="emailaux" class="inputBox emailaux" style="width: 150px;" maxlength="30" value="" />
					</div>
	
					<div class="opciones centrar">
						<a class="btnAzulGrande guardarConfiguracion guardarHabilitar premium" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Habilitado');">
				           <span>Guardar configuraci&oacute;n</span>			           
				        </a>
					</div>
				</entel:view>
				
				<entel:view name="permisosAutoatencion" inverse="true">
					<div class="deshabilitado">
						<cm:getProperty node='${headerAuthMsg2[0]}' name='html'/>
					</div>
				</entel:view>
				
				
			</div>
			<!-- /Contenido de buzón premium -->
			
		</div>
		<!-- /Contenido de Configurar/cerrar -->
		
	</div>
	<!-- /Estado deshabilitado -->
	
	<!-- Estado deshabilitado 1-->
	<div class="estadoDeshabilitado1 clearfix ocultar">

		<div class="columna1">
			<strong class="clearfix">Buz&oacute;n de Voz</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje habilitado/deshabilitado -->
			<div class="mensaje_ancho"><strong>Deshabilitar&aacute;s Buz&oacute;n de Voz</strong><br />Los n&uacute;meros configurados ser&aacute;n eliminados.
			</div>
			
			<!-- Btoon deshabilitado/habilitado -->
			<div class="btnDeshabilitar">
				<h:commandLink styleClass="btnAzulDelgado btnAncho2 caso4" action="#{buzonVozController.desactivarBuzon}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Deshabilitado');">
					<span>Deshabilitar</span></h:commandLink>
			</div>
			
			<!-- Boton Cancelar -->
			<div class="enlaceCancelar">
				<a href="#" class="enlaceCancelar">Cancelar</a>
			</div>
		</div>
	</div>
	<!-- /Estado deshabilitado 1 -->
	
	<!-- Estado deshabilitado 2 -->
	<div class="estadoDeshabilitado2 clearfix ocultar">
		<div class="columna1">
			<strong class="clearfix">Buz&oacute;n de Voz</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje grande -->
			<div class="mensaje_grande mensaje_grande_reloj">
				En proceso de <strong>deshabilitaci&oacute;n</strong>
			</div>
		</div>
	</div>
	<!-- /Estado deshabilitado 3 -->
	
	<div class="estadoDeshabilitado3 clearfix ocultar">
		<div class="columna1">
			<strong class="clearfix">Buz&oacute;n de Voz</strong>
			<label class="titulo_inferior">Cargo por uso</label>
		</div>
		<div class="columna2">
			<a class="ico_interrogacionNuevo autoTooltip ext" href="#TTBuzondeVoz"></a>
		</div>
		<div class="columna3">
			<!-- Mensaje grande -->
			<div class="mensaje_grande mensaje_grande_reloj">
				<strong>Buz&oacute;n de Voz se ha configurado</strong>
			</div>
		</div>
	</div>
	<input type="hidden" name="email" value=""/> 
	</h:form>
</div>

<input type="hidden" id="buzon_basico_hidden" value="<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioBuzon.basico}" />"/>
<input type="hidden" id="buzon_premium_hidden" value="<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioBuzon.premium}" />"/>
<input type="hidden" id="tipoPerfil_hidden" value="<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioBuzon.tipoPerfil}" />"/>
<input type="hidden" id="perfilBasico_hidden" value="<h:outputText value="#{buzonVozController.tipoPerfilBasico}" />"/>
<input type="hidden" id="perfilMail_hidden" value="<h:outputText value="#{buzonVozController.tipoPerfilMail}" />"/>
<input type="hidden" id="perfilMMS_hidden" value="<h:outputText value="#{buzonVozController.tipoPerfilMMS}" />"/>


</h:panelGroup>

<h:panelGroup rendered="#{!administracionServicios.administracionServiciosBean.servicioBuzon.visible}"> 
	<jsp:include page="../servicioNoDisponible.jsp" flush="true">
		<jsp:param value="Buz&oacute;n de Voz" name="serviceName"/>
		<jsp:param value="Cargo por uso" name="cargo"/>
		<jsp:param value="TTBuzondeVoz" name="tooltip"/>
	</jsp:include>
</h:panelGroup>	                

<!-- /Fila de información -->

</entel:view>
