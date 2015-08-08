<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>

<!-- Lightbox MM Full -->
<cm:search id="contenidoTDE" query="idContenido = 'ventanaTDE'" useCache="true"  />
<cm:search id="imagenTDE" query="description = 'imagenTDE'" useCache="true"  />

<pref:getPreference name="fechaVigenciaLBMMFull" var="vigenciaLBMMFull"/>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/portlet/dashboard/mensajes/datosExcedidos/css/planMultimedia.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/portlet/dashboard/mensajes/datosExcedidos/css/jquery.fancybox.css" />
<style>
	.fcCenter {
		background: url(${pageContext.request.contextPath}/ShowProperty?nodeId=<cm:getProperty node="${imagenTDE[0]}" name="cm_uid" />) no-repeat left top;
	}
</style>
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
	  	
		function levantarTDE(){
			if ($.browser.msie && parseInt($.browser.version) <= 6) {										
					$('.enlaceTDE').trigger('click');
			}else{
					$('.enlaceTDE').click();						
			}		
			
			
		}

		function checkMensajes(){
            if($("a.enlaceTDE").length != 0){
                  //Moverlo al inicio
                  elePRIM=$("#lista_1 li:first").html();
                  eleTDE=$("a.enlaceTDE").parent().html();
                  $("a.enlaceTDE").parent().html(elePRIM);
                  $("#lista_1 li:first").html(eleTDE);   
                                                   
                  $("a.enlaceTDE").fancybox({   
                	  'overlayOpacity' : 0.5,
                      'overlayColor' : '#000000',
                      'hideOnContentClick' : false,
                      'hideOnOverlayClick' : false,
                      'showCloseButton'   :true,                      
                      'padding' : 'auto',
                      'scrolling' : 'no',              	       
              	      'frameWidth'  : 880,
            	      'frameHeight' : 507
                  });   

				   
				  $("#fancy_overlay").css( {
                		width: "100%"
           			});

				  validarTDE();
         			
            }
      	}


		//var cantLlamados = 0;
		
		/*
		Esta funcion valida si debe levatar el lightbox MM Full,
		dependiendo del despliegue del lightbox de blindaje
		*/
      	function validarTDE(){

          	var hoy = new Date();
           	var fechaLimite = new Date('${vigenciaLBMMFull}');

           	//Validamos vigencia del lightbox de MM Full
           	if(hoy < fechaLimite){
           		if(document.cookie.indexOf('cookieSumate')<0 && document.cookie.indexOf('cookieTDE')<0){
            		levantarTDE();
             		document.cookie='cookieTDE=1';
        		}
           	}

      	}
      	

		
		$(document).ready(function() {
			loadMensajesParaTi();	
		});

		/*
		$(window).load(function($) {
			varTimerDespliegue=setInterval(function(){checkMensajes()},500);
		});
		*/

		
	</script>
	
	<div class="db-tabla">	
		<div class="db-tabla-cabecera">
			<div class="db-cabecera-top"></div>
			<div class="db-cabecera-cuerpo">
				<div class="db-titulo db-titulo-mensaje">Mensajes para ti</div>
			</div>
		</div>
		<div class="db-tabla-cuerpo tablePrueba db-tabla-fix-mensaje">			
			<div id="alerta-tabla-mensaje"><h:outputText value="#{mensajesParaTiController.noHayMensajes}" /></div>
			<div id="loading-tabla-mensaje"></div>            
		</div>		
		<div class="db-tabla-pie"></div>
	</div>
	
	
	<div class="fbOculto" id="TDEVentana">
		<cm:getProperty node="${contenidoTDE[0]}" name="html" />
	</div>
	
</f:view>