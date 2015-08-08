<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>

<!-- 
Preference que indica si este lightbox compite con el de sumate. Si ambos lightbox están 
presentes en la misma pagina, se debe configurar este preference a '1' en ambos portlets
-->
<pref:getPreference name="simultaneidadBlindaje" var="simultaneidadBlindaje"/>

<!-- Preference que indica si se debe ocultar el lightbox -->
<pref:getPreference name="ocultarLightbox" var="ocultar" defaultValue="mostrar" />

<!-- Preference que indica el nombreLogico a usar para generar marca de GTM -->
<pref:getPreference name="nombreLogico" var="nombreLogico" defaultValue="Personas/Mi Entel/Bienvenida PP Plus" />

<!-- Preference que indica el idContenido para obtener el contenido con el HTML del lightbox -->
<pref:getPreference name="idContenidoLightbox" var="id_contenido" defaultValue="" />

<cm:search id="lightboxNode" query="idContenido = '${id_contenido}'" useCache="true"  />

<f:view beforePhase="#{historialPPAltoValor.initLimpieza}">

	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/prepagoAltoValor/css/style-lb.css"  >
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/prepagoAltoValor/css/jquery.fancybox-1.2.5.css"  >
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/portlet/prepagoAltoValor/js/jquery.fancybox-1.2.5.js"></script>
	
	<!-- Contenido con HTML del lightbox Prepago Plus -->
	<cm:getProperty node="${lightboxNode[0]}" name="html" />
	
	<script type="text/javascript">
	
		var ctx = '/personas';
		var prefSimultaneidad = '${simultaneidadBlindaje}';
		var ocultarLightbox = '${ocultar}';
	
		$(document).ready(function() {
		
			$("a.enlaceLightboxPPAV").fancybox({
				'overlayOpacity' : 0.5,
				'overlayColor' : '#000000',
				'hideOnContentClick' : false,
				'hideOnOverlayClick' : false,
				'showCloseButton'	:true,
				'padding' : 'auto',
				'scrolling' : 'no',
				'frameWidth'  : 500,
				'frameHeight' : 300   
			});
		
			$("#fancy_overlay").css({width: "100%"});
	
			if(ocultarLightbox = 'mostrar'){
				if(prefSimultaneidad == '0'){
					checkLightboxPPAV();
				}
			}
			
		});
		
		
		function checkLightboxPPAV(){
		
			var url = ctx + '/portlet/prepagoAltoValor/LB_BienvenidaPPAVJson.faces';
			$.ajax( {
				type : 'POST',
				url : url,
				dataType:'json',
				success : function(data) {
				
					if(data.estado == 'Ok'){
						if(data.respuesta == 'desplegar'){
							if(prefSimultaneidad == '0'){
								levantarLB();
							}else{
								if(document.cookie.indexOf('cookieSumate') < 0){
									levantarLB();
								}
							}
						}
				   }
			   }
			});
		}
		
		function levantarLB(){
			//Realizamos marca de GTM al levantar lightbox PP Plus
			dataLayer = dataLayer||[];
			dataLayer.push({
				'mx_content': '${nombreLogico}',
				'event': 'pageview'
			});
			
			if ($.browser.msie && parseInt($.browser.version) <= 6) {
				$('a.enlaceLightboxPPAV').trigger('click');
			}else{
				$('a.enlaceLightboxPPAV').click();
			}
		}
	
	</script>
	
</f:view>
