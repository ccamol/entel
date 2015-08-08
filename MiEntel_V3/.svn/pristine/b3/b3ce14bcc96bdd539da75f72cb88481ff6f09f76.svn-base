<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<f:view>
	<script type="text/javascript">
		var urlResponderAhora = '<%=request.getContextPath()%>/portlet/dashboard/encuestas/encuestasPerfiladasResponderJson.faces';
		var urlNoResponder = '<%=request.getContextPath()%>/portlet/dashboard/encuestas/encuestasPerfiladasNoResponderJson.faces';
		var urlNoPorAhora = '<%=request.getContextPath()%>/portlet/dashboard/encuestas/encuestasPerfiladasNoPorAhoraJson.faces';
        var urlHayEncuesta ='<%=request.getContextPath()%>/portlet/dashboard/encuestas/encuestasPerfiladasJson.faces';	  	
	  	
	  	function mostrarEncuesta() {
                $.ajax({
	            type: 'POST',
	            url: urlHayEncuesta,
	            success: function (resp){
                 var respuesta = resp.split(","); 
                 if($.trim(respuesta[0]) == 'true'){
                    $('#encuesta_responder').attr('href',$.trim(respuesta[1].replace("&amp;", "&")));
                    $('#contenido-tabla-encuestas').fadeIn();
                   }
	             }
	            });
	    }
	  	
	  	function accionResponderAhora() {
	  		$(".db-alerta-amarilla").fadeOut(350);
	  		$.ajax({
	            type: 'POST',
	            url: urlResponderAhora,
	            success: function(resp) { }
	        });	  		
	  	}
	  	
	  	function accionNoResponder() {
	  		$(".db-alerta-amarilla").fadeOut(350);
	  		$.ajax({
	            type: 'POST',
	            url: urlNoResponder,
				success: function(resp) { }
	        });	  	
	  	}	  	
	  	
	  	function accionNoPorAhora() {
	  		$(".db-alerta-amarilla").fadeOut(350);
	  		$.ajax({
	            type: 'POST',
	            url: urlNoPorAhora,
				success: function(resp) { }
	        });	  		
	  	}
	  	
	  	$(document).ready(function() {
	  	    mostrarEncuesta();
	  	});
  	
	</script>	
			
		<div id="contenido-tabla-encuestas" class="db-alerta-amarilla caso-especial clearfix" style="display: none">
			Tienes una encuesta sin responder.
			<a id="encuesta_responder" href='' onclick="javascript:accionResponderAhora();" target="_blank">Responder ahora</a>			
			<a id="encuesta_ocultar1" href="javascript:accionNoPorAhora();">No por ahora</a>
			<a id="encuesta_ocultar2" href="javascript:accionNoResponder();">No quiero responder</a>	
		</div>
</f:view>