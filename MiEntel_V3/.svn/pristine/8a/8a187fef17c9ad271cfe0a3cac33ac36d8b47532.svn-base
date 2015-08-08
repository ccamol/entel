<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<f:view beforePhase="#{mensajesParaTiController.init}">

	<script type="text/javascript">

		function loadMensajesParaTi() {

			//lista de servicios a obtener mensajes
	    	var idServicios = '<h:outputText value="#{mensajesParaTiController.idServicios}" />';
	    	//urls de los servicios
	    	var urls = getUrlServicios(idServicios);
	    	//cantidad de mensajes por paginacion del portlet
	    	var itemsPorPag = '<h:outputText value="#{mensajesParaTiController.itemsPorPagina}" />';

			//Funcion para cargar contenido (mensajes) de cada servicio
	    	cargar_contenido(urls, itemsPorPag);
		}

		/**
		 * Entrega un array con las url para obtener los mensajes de los servicios indicados
		 * en idServicios
		 */
		function getUrlServicios(idServicios) {
	    	var url = '<h:outputText value="#{mensajesParaTiController.mensajesCommonURL}" />';

			var array = idServicios.split(',');

			$.each(array, function(index, value) {
				array[index] = url + '?id=' + value;
			});

	    	return array;
		}
		
	  	
		$(document).ready(function() {
			//loadMensajesParaTiBam();			
			loadMensajesParaTi();
		});
	</script>		
	
	<div class="db-tabla">	
		<div class="db-tabla-cabecera">
			<div class="db-cabecera-top"></div>
			<div class="db-cabecera-cuerpo">
				<div class="db-titulo db-titulo-mensaje">Mensajes para ti</div>
			</div>
		</div>
		<div class="db-tabla-cuerpo tablePrueba db-tabla-fix-mensaje">			
			<div id="alerta-tabla-mensaje"><h:outputText value="#{mensajesParaTiController.noHayMensajes}"/></div>
			<div id="loading-tabla-mensaje"></div>            
		</div>		
		<div class="db-tabla-pie" ></div>
	</div>
</f:view>