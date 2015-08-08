<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<style type="text/css">
.lightbox .lb_main{
background-color: #FFF;
}
</style>
<script>
$(document).ready(function(){
	$('#pruebacancelar').click(tb_remove);
});
</script>
<f:view>
<cm:search id="nodo" query="idContenido = 'autorizacion_pat'" useCache="false"  />
		<div id="centro" class="contenedor_central">
		
		<h1><cm:getProperty node="${nodo[0]}" name="titulo" /></h1>
			
		<cm:getProperty node="${nodo[0]}" name="html" />
		<p class="texto_aut">
		<h:outputText value="#{pagoAutomaticoController.fechaActual}">
						<f:convertDateTime pattern="dd 'de' MMMM 'del' yyyy" locale="es"/>
						</h:outputText>
        <br>

		Para ver resoluci&oacute;n del Servicio de Impuestos Internos ingresa <a href="#">aqu&iacute;</a>. </p>
        <a class="btnAzulGrande btnAzulGrandeLargo" onclick="aceptarTerminos();tb_remove();" href="javascript:void(0);">
        <span>Aceptar</span>
        </a>
        <a href="#" id="pruebacancelar" class="btnCancelar">Cancelar</a>
        </div>
        
</f:view>

