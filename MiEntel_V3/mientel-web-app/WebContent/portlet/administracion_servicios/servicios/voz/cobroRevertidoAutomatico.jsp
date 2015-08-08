<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<h:form>
	<jsp:include page="/token.jsp" flush="true"/>
	<!-- Fila de información -->
	<div class="tabla_fila clearfix">
	
		<div class="estadoHabilitado clearfix ocultar">
			<div class="columna1">
				<strong class="clearfix">Cobro Revertido Autom&aacute;tico</strong>
			</div>
			<div class="columna2">
				<a class="ico_interrogacionNuevo autoTooltip" href="#TTCobroRevertido"></a>
			</div>
			<div class="columna3">
				<!-- Mensaje habilitado/deshabilitado -->
				<div class="mensaje habilitado">Habilitado</div>
				
				<!-- Btoón deshabilitado/habilitado -->
				<div class="btnDeshabilitar">
					<a class="btnAzulDelgado btnAncho2 caso1" href="#"><span>Deshabilitar</span></a>
				</div>
				
				<!-- Botón Configurar -->
				<div class="enlaceConfigurar">
					<a href="#" class="enlaceConfigurar ocultar">Configurar</a>
				</div>
			</div>
		</div>
		
		<div class="estadoHabilitado1 clearfix ocultar">
			<div class="columna1">
				<strong class="clearfix">Cobro Revertido Autom&aacute;tico</strong>
			</div>
			<div class="columna2">
				<a class="ico_interrogacionNuevo autoTooltip" href="#TTCobroRevertido"></a>
			</div>
			<div class="columna3">
				<!-- Mensaje grande -->
				<div class="mensaje_grande mensaje_grande_reloj">
					En proceso de <strong>habilitaci&oacute;n</strong>
				</div>
			</div>
		</div>
		
		<div class="estadoDeshabilitado clearfix">
			<div class="columna1">
				<strong class="clearfix">Cobro Revertido Autom&aacute;tico</strong>
			</div>
			<div class="columna2">
				<a class="ico_interrogacionNuevo autoTooltip" href="#TTCobroRevertido"></a>
			</div>
			<div class="columna3">
				<!-- Mensaje habilitado/deshabilitado -->
				<div class="mensaje deshabilitado">Deshabilitado</div>
				
				<!-- Btoón deshabilitado/habilitado -->
				<div class="btnHabilitar">
					<a class="btnAzulDelgado btnAncho caso3" href="#"><span>Habilitar</span></a>
				</div>
				
				<!-- Botón Configurar -->
				<div class="enlaceConfigurar">
					<a href="#" class="enlaceConfigurar ocultar">Configurar</a>
				</div>
			</div>
		</div>
		
		<div class="estadoDeshabilitado1 clearfix ocultar">

			<div class="columna1">
				<strong class="clearfix">Cobro Revertido Autom&aacute;tico</strong>
			</div>
			<div class="columna2">
				<a class="ico_interrogacionNuevo autoTooltip" href="#TTCobroRevertido"></a>
			</div>
			<div class="columna3">
				<!-- Mensaje habilitado/deshabilitado -->
				<div class="mensaje_ancho"><strong>Deshabilitar&aacute;s Cobro Revertido Autom&aacute;tico</strong><br />Los n&uacute;meros configurados ser&aacute;n eliminados.
				</div>
				
				<!-- Btoón deshabilitado/habilitado -->
				<div class="btnDeshabilitar">
					<a class="btnAzulDelgado btnAncho2 caso4" href="#"><span>Deshabilitar</span></a>
				</div>
				
				<!-- Botón Cancelar -->
				<div class="enlaceCancelar">
					<a href="#" class="enlaceCancelar">Cancelar</a>
				</div>
			</div>
		</div>
		
		<div class="estadoDeshabilitado2 clearfix ocultar">
			<div class="columna1">
				<strong class="clearfix">Cobro Revertido Autom&aacute;tico</strong>
			</div>
			<div class="columna2">
				<a class="ico_interrogacionNuevo autoTooltip" href="#TTCobroRevertido"></a>
			</div>
			<div class="columna3">
				<!-- Mensaje grande -->
				<div class="mensaje_grande mensaje_grande_reloj">
					En proceso de <strong>deshabilitaci&oacute;n</strong>
				</div>
			</div>
		</div>
		
	</div>
	<!-- /Fila de información -->


</h:form>                
