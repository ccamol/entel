<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:choose>
	<c:when test="${param.tipoPlan == 'tp'}">
		<c:set var="query">idContenido = 'url_miprimerplantp'</c:set>
	</c:when>
	<c:when test="${param.tipoPlan == 'tr'}">
		<c:set var="query">idContenido = 'url_miprimerplantr'</c:set>
	</c:when>
	<c:when test="${param.tipoPlan == 'mm'}">
		<c:set var="query">idContenido = 'url_miprimerplanmm'</c:set>
	</c:when>
</c:choose>

<cm:search id="cmMiPrimerPlan" query="${query}" useCache="false"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Ver Detalle de Planes</title>

<script type="text/javascript"> 
	var ini = 0;
	var fin = 0;
	var elapsed = 0;

	function centrarAlerta() {
		/* Obtener el alto del contendor  del objeto a centrar. */
	    var alto_canvas = $("#fancy_ajax").height();
	    var ancho_canvas = $("#fancy_ajax").widht();
	 
	    /* Obtener el valor del margen a aplicar.  */
	    var margen_top = (alto_canvas - $('.alerta-tabla-oferta-error').height())/2;
	    var margen_left = (ancho_canvas - $('.alerta-tabla-oferta-error').width())/2;
	 
	    /* Aplicar el Margen */
	    $('.alerta-tabla-oferta-error').css({top:margen_top, left:margen_left});
	}

	$(document).ready(function(){

		ini = (new Date()).getTime();

		if('${param.tipoPlan}' != 'mm'){
			if($.browser.msie){
				if(parseInt($.browser.version) <= 7){
					if('${param.tipoPlan}' == 'tp'){
						$("#frame_blindaje").attr("height", "381");
					} else if('${param.tipoPlan}' == 'tr'){
						$("#frame_blindaje").attr("height", "431");
					}
				}else{
					$("#frame_blindaje").attr("height", "361");
				}
			}else{
				$("#frame_blindaje").attr("height", "361");
			}
		}
		
		$('#frame_blindaje').load(function(response) {
			fin = (new Date()).getTime();
			elapsed = fin - ini;
			if(elapsed > 20000){
				$('.alerta-tabla-oferta-error').show();
			    centrarAlerta();
			}else{
				$('#frame_blindaje').show();
			}
		});

	});
	
</script>

</head>
<body>
	<div class="TB_ajaxContent2" allowtransparency="true" >
		<iframe id="frame_blindaje" style="display: none;" allowtransparency="true" src="<cm:getProperty node="${cmMiPrimerPlan[0]}" name="html" />"  frameBorder=0 width="621"  height="461"></iframe>
	</div>
	<cm:search id="infoPlanesND" query="idContenido = 'bloqueDetallePlanesND'" useCache="false"/>
	<cm:getProperty node="${infoPlanesND[0]}" name="html" />
</body>
</html>