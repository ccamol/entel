<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>

<f:view>
<pref:getPreference name="grupoOferta" var="grupoOferta"/>
<pref:getPreference name="idContenido" var="idContenido" defaultValue=" " />
<c:set var="query">idContenido = '${idContenido}'</c:set>
<cm:search id="accesoDashboard" query="${query}" useCache="false"/>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">
var codOfertaBlindaje = 0;
var grupoOfertaBlindaje = "";


function consultarOfertaBannerBolsaBlindaje(){		

    var parametros = {"grupoOferta":'${grupoOferta}',"isBanner":"SI"};
    var mostrarSiteIntercept = false;
    var aaa = "";
	var url='<%=request.getContextPath()%>/portlet/blindaje_oferta/blindajeOfertaJson.faces';
	var url2='<%=request.getContextPath()%>/portlet/bannerOferta/TB_UrlBannerJson.faces';
	$.ajax( {
		type : 'POST',
		url : url,	
		data:parametros,
		dataType:'json',		
		success : function(data) {			 
		 
			if(data.estado == 'Ok'){
					if (data.respuesta.codigo=='0001' && (data.respuesta.aaa=="2" || data.respuesta.aaa=="3")) {
						$('#CVM').val("Si");
						codOfertaBlindaje = data.respuesta.codOferta;
						grupoOfertaBlindaje = data.respuesta.grupo;
						$('#bannerOfertaBlindajeG6').show();

						/*if ($.browser.msie && parseInt($.browser.version) <= 6) {										
							$('.enlaceOferta').trigger('click');
						}else{
							$('.thickbox').click();						
						}	*/			
					}else{
						mostrarSiteIntercept = true;
						$('#bannerOfertaBlindajeG6').hide();
					}
					/*  else if(data.respuesta.codigo=='0002')
					{
						$('#caja_oferta_visa').show();							
					    $('#estado_oferta_visa').html(data.respuesta.estado+"!!");
					}*/
					if(mostrarSiteIntercept==true){							
						$('#CVM').val("No");
					 }					
			   }
		   }
	   });


}

function levantarOfertaBannerBolsaBlindaje(){		

	/*$('#CVM').val("Si");
	codOfertaBlindaje = data.respuesta.codOferta;
	grupoOfertaBlindaje = data.respuesta.grupo;*/
	if ($.browser.msie && parseInt($.browser.version) <= 6) {										
			$('.enlaceOferta').trigger('click');
	}else{
			$('.thickbox').click();						
	}				
	
					/*  else if(data.respuesta.codigo=='0002')
					{
						$('#caja_oferta_visa').show();							
					    $('#estado_oferta_visa').html(data.respuesta.estado+"!!");
					}*/				

}


$(document).ready(function() {
	$("a.enlaceOferta").fancybox({		
	    'overlayOpacity' : 0.5,
	    'overlayColor' : '#000000',
	    'hideOnContentClick' : false,
	    'hideOnOverlayClick' : false,
	    'showCloseButton'	:true,
	    'padding' : 'auto',
	    'scrolling' : 'no',
	    'frameWidth'  : 701,
	    'frameHeight' : 1000   
	   });
	  $("#fancy_overlay").css( {	
			width: "1400px"
	   });
	  consultarOfertaBannerBolsaBlindaje();					
});
</script>
</head>
<body >	
	<div id="bannerOfertaBlindajeG6" class="clearfix" style="width: 600px; padding-bottom: 15px;padding-top: 6px;display: none;">
		<div style="width:95%;margin-top: -20px;line-height:1.80em;z-index: 3;position: relative;  ">
			<a href="javascript:levantarOfertaBannerBolsaBlindaje();">
				<img src="${pageContext.request.contextPath}/portlet/bannerOferta/img/bannersumate_bolsas.png"/>
			</a>
		</div>
	</div>
	<h:outputLink value="TB_BlindajeBanner.faces" styleClass="thickbox"></h:outputLink>
	<h:outputLink value="TB_BlindajeBanner.faces" styleClass="enlaceOferta"></h:outputLink>
	<input id="CVM" type="hidden" value="" />		
	<div class="marca-oferta" id="marca-oferta"><cm:getProperty node="${scriptAdwords[0]}" name="html" /></div>
</body>
</html>
</f:view>