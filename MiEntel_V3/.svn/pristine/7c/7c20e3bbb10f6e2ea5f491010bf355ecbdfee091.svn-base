<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="pref" %>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>

<pref:getPreference name="Activacion" var="activar"/>
<c:if test="${activar == 1}">

<!--  <script type="text/javascript" src="${pageContext.request.contextPath}/portlet/nba/js/jquery-1.9.1.min.js"></script>
-->
<script type="text/javascript" src="${pageContext.request.contextPath}/portlet/nba/js/jquery.jcarousel.min.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/portlet/nba/js/jquery.fancybox.js"></script> -->
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/portlet/nba/js/jquery.fancybox.pack.js"></script> --> 

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/nba/style/skin.css"  >
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/nba/style/banner.css"  >
<!--  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/nba/style/jquery.fancybox.css"  > -->
<!-- <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/nba/style/thickbox-blindaje.css"  > --> 
<!-- <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/nba/style/thickbox-tabla.css"  > -->
<!-- <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/nba/style/thickbox.css"  > -->

<script type="text/javascript">

	var indice = 2;
	var nextPrev = -1;
	var numData=0;
	var cont=0;
	var last="next";
		
	var codOfertaBlindaje = 0;
	var grupoOfertaBlindaje = "";
	var urlNBA = "";
	function mycarousel_initCallback(carousel) {
	    jQuery('.jcarousel-control a').bind('click', function() {
	        carousel.scroll(jQuery.jcarousel.intval(jQuery(this).text()));
	        return false;
	    });

	    jQuery('.jcarousel-scroll select').bind('change', function() {
	        carousel.options.scroll = jQuery.jcarousel.intval(this.options[this.selectedIndex].value);
	        return false;
	    });

	   
		jQuery('#mycarousel-next').bind('click', function() {
	        nextPrev=-1;
			carousel.next();
			return false;
	    });
		
		jQuery('#mycarousel-prev').bind('click', function() {
			nextPrev=-2;
			carousel.prev();
			return false;
	    });
	    
	    jQuery('#minimizarBanner').bind('click', function() {
			if(div.style.display=="none"){
				carousel.stopAuto();
			}else{
				carousel.startAuto();
			}
	    });
	    
	};

	jQuery(document).ready(function() {
		
		jQuery('#minimizarID').bind('click', function() {
			div = document.getElementById("mycarousel2");
			
			state=0;
			if(div.style.display=="none"){
				div.style.display = "";
				state=0;
				$('#minimizarID').attr("src","${pageContext.request.contextPath}/portlet/nba/img/min.png");
			}else{
				div.style.display = "none";
				state=1;
				$('#minimizarID').attr("src","${pageContext.request.contextPath}/portlet/nba/img/max.png");
			}
			
			$.get('${pageContext.request.contextPath}/MinimizarBanner',{estado:state,movil:$('#movil').val(),subMercado:$('#subMercado').val(),flagBam:$('#flagBam').val()},function(){});
	       
	    });

		var tiempo=$('#time').val();
		if(tiempo==0){
			jQuery("#mycarousel").jcarousel({
		        scroll: 1,
				wrap: 'last',
				auto: 0,
				initCallback: mycarousel_initCallback,
				itemFirstOutCallback: {
					onBeforeAnimation: function(data) {

						if(nextPrev==-1){
							clickNext(indice);
						}
						if(nextPrev==-2){
							clickPrev(indice);
						}
						nextPrev=-1;
					
				  	}
				},
				buttonNextHTML: null,
				buttonPrevHTML: null
		    });
		}else{
			jQuery("#mycarousel").jcarousel({
		        scroll: 1,
				wrap: 'last',
				auto: 6,
				initCallback: mycarousel_initCallback,
				itemFirstOutCallback: {
					onBeforeAnimation: function(data) {

						if(nextPrev==-1){
							clickNext(indice);
						}
						if(nextPrev==-2){
							clickPrev(indice);
						}
						nextPrev=-1;
					
				  	}
				},
				buttonNextHTML: null,
				buttonPrevHTML: null
		    });
			}
		//$('.TB_ajaxContent2').fancybox();
		
	    
	}).css('visibility','visible');

		$("#mycarousel .enlace_destacado").each(function(i) {
			$(this).click(function(){
			
			indice = i+1;
				$("#mycarousel .enlace_destacado").removeClass("enlace_seleccionado");
				$(this).addClass("enlace_seleccionado");
			});
		});	
		
		function minimizar(){
		}
		//Revisar7
	//	function levantarLightbox2(url){		
			
	//			 $("#iframeUrlCargarOferta").attr("src", url);	
	//			 div = document.getElementById("iframeUrlCargarOferta");
	//			 div.style.display = "";
	//			 $('.TB_ajaxContent2').click();
				 
				 //$('#iframeUrlCargarOferta').contents().find('.no_mostrar').css('display', 'none');
				 //frame = $('#iframeUrlCargarOferta');
				 //div2 = $('#no_mostrar', frame);
				 //alert(div2.style.display);
				 
	//	}
		
		function clickHREF(data){
			
			if(numData!=data){
				indice=data-1;
				var total = $('#mycarousel .enlace_destacado').length;
		
				$('#mycarousel .enlace_destacado').removeClass('enlace_seleccionado');
				$('#mycarousel .enlace_destacado').eq(indice++).addClass('enlace_seleccionado');

				nextPrev=-1;
				
				limpiarLlenar(total,indice);
				
				if(indice == total) {
					indice = 0;
				}
				
				numData=data;
				nextPrev=-1;
				last=0;
				indice++;
				last="next";
				cont=0;
			}
			div = document.getElementById("mycarousel2");
			if(div.style.display=="none"){
				
				state=0;
				div.style.display = "";
				state=0;
				$.get('ActionServlet',{estado:state,movil:$('#movil').val(),subMercado:$('#subMercado').val(),flagBam:$('#flagBam').val()},function(){});
			}
		}
		function clickPrev(data){

				indice=data-1;
				var total = $('#mycarousel .enlace_destacado').length;
				$('#mycarousel .enlace_destacado').removeClass('enlace_seleccionado');
				
				if(cont==0){
					if(indice>0)
					indice=indice-2;
				}else{
					if(indice>0)
					indice=indice-1;
				}
				cont++;
				$('#mycarousel .enlace_destacado').eq(indice).addClass('enlace_seleccionado');
				
				nextPrev=-1;
				limpiarLlenar(total,indice);
					
				if(indice == total) {
					indice = 0;
				}
				indice=indice+1;
				numData=data;
				nextPrev=0;
				last="prev";
										
		}
		
		function clickNext(data){
				
				if(last=="prev")
					data++;
				indice=data-1;
				var total = $('#mycarousel .enlace_destacado').length;
				$('#mycarousel .enlace_destacado').removeClass('enlace_seleccionado');
				
				$('#mycarousel .enlace_destacado').eq(indice++).addClass('enlace_seleccionado');
				
				nextPrev=-1;
				limpiarLlenar(total,indice);
				if(indice == total) {
					indice = 0;
				}
				indice=indice+1;
				numData=data;
				last="next";
				cont=0;
		}
		
		function limpiarLlenar(total,indice){
			if(total>8){
					
				for(i=2;i<(total);i++){
					div = document.getElementById("id_"+i);
					div.style.display = "none";
				}
				if(indice==total){
					div = document.getElementById("id_"+(indice-1));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-2));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-3));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-4));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-5));
					div.style.display = "";
				}else{
					if(indice==0){
						div = document.getElementById("id_"+(indice+1));
						div.style.display = "";
						div = document.getElementById("id_"+(indice+2));
						div.style.display = "";
						div = document.getElementById("id_"+(indice+3));
						div.style.display = "";
						div = document.getElementById("id_"+(indice+4));
						div.style.display = "";
						div = document.getElementById("id_"+(indice+5));
						div.style.display = "";
						div = document.getElementById("id_"+(indice+6));
						div.style.display = "";
					}else{
						if(indice<(total-1)){
							div = document.getElementById("id_"+(indice));
							div.style.display = "";
							div = document.getElementById("id_"+(indice+1));
							div.style.display = "";
							div = document.getElementById("id_"+(indice+2));
							div.style.display = "";
							if(indice>1){
								div = document.getElementById("id_"+(indice-1));
								div.style.display = "";
							}
							if(indice>2){
								div = document.getElementById("id_"+(indice-2));
								div.style.display = "";
							}
						}
					}
				}
				if(indice==1){
					div = document.getElementById("id_"+(indice+1));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+2));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+3));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+4));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+5));
					div.style.display = "";
				}
				if(indice==2){
					div = document.getElementById("id_"+(indice+1));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+2));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+3));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+4));
					div.style.display = "";
				}
				if(indice==3){
					div = document.getElementById("id_"+(indice+1));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+2));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+3));
					div.style.display = "";
				}
				
				if(indice==(total-1)){
					div = document.getElementById("id_"+(indice));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-1));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-2));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-3));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-4));
					div.style.display = "";
				}
				if(indice==(total-2)){
					div = document.getElementById("id_"+(indice));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-1));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-2));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-3));
					div.style.display = "";
				}
			}			
		}	

		
</script> 

<f:view beforePhase="#{nbaController.initNBA}">    

<cm:search id="scriptAdwords" query="idContenido = 'scriptAdwords'" useCache="false"/>
<pref:getPreference name="grupoOferta" var="grupoOferta"/>
<cm:search id="activar_nba" query="idContenido = 'activar_nba'" useCache="false"  />


<cm:search id="g1l1" query="idContenido = 'id_Grupo1_Linea1'" useCache="false"  />
<cm:search id="g1l2" query="idContenido = 'id_Grupo1_Linea2'" useCache="false"  />
<cm:search id="g1l3" query="idContenido = 'id_Grupo1_Linea3'" useCache="false"  />
<cm:search id="g1l31" query="idContenido = 'id_Grupo1_Linea3_1'" useCache="false"  />
<cm:search id="g1l4" query="idContenido = 'id_Grupo1_Linea4'" useCache="false"  />
<cm:search id="g1l41" query="idContenido = 'id_Grupo1_Linea4_1'" useCache="false"  />
<cm:search id="g1l5" query="idContenido = 'id_Grupo1_Linea5'" useCache="false"  />
<!-- 
<cm:search id="g2l1" query="idContenido = 'id_Grupo2_Linea1'" useCache="false"  />
<cm:search id="g2l2" query="idContenido = 'id_Grupo2_Linea2'" useCache="false"  />
<cm:search id="g2l3" query="idContenido = 'id_Grupo2_Linea3'" useCache="false"  />
<cm:search id="g2l4" query="idContenido = 'id_Grupo2_Linea4'" useCache="false"  />
<cm:search id="g2l41" query="idContenido = 'id_Grupo2_Linea4_1'" useCache="false"  />
 -->
<!--<cm:search id="g3l1" query="idContenido = 'id_Grupo3_Linea1'" useCache="false"  />-->
<!--<cm:search id="g3l2" query="idContenido = 'id_Grupo3_Linea2'" useCache="false"  />-->
<!--<cm:search id="g3l3" query="idContenido = 'id_Grupo3_Linea3'" useCache="false"  />-->
<!--<cm:search id="g3l4" query="idContenido = 'id_Grupo3_Linea4'" useCache="false"  />-->
<!--<cm:search id="g3l41" query="idContenido = 'id_Grupo3_Linea4_1'" useCache="false"  />-->
<!--<cm:search id="g3l5" query="idContenido = 'id_Grupo3_Linea5'" useCache="false"  />-->


<cm:search id="g4l1" query="idContenido = 'id_Grupo4_Linea1'" useCache="false"  />
<cm:search id="g4l2" query="idContenido = 'id_Grupo4_Linea2'" useCache="false"  />
<cm:search id="g4l21" query="idContenido ='id_Grupo4_Linea2_1'" useCache="false"  />
<cm:search id="g4l3" query="idContenido = 'id_Grupo4_Linea3'" useCache="false"  />
<cm:search id="g4l31" query="idContenido = 'id_Grupo4_Linea3_1'" useCache="false"  />
<cm:search id="g4l4" query="idContenido = 'id_Grupo4_Linea4'" useCache="false"  />
<cm:search id="g4l41" query="idContenido = 'id_Grupo4_Linea4_1'" useCache="false"  />
<cm:search id="g4l5" query="idContenido = 'id_Grupo4_Linea5'" useCache="false"  />

<!--contenido campaña G4 ASOCIADO-->
<cm:search id="g4l1A" query="idContenido = 'id_Grupo4_Linea1_A'" useCache="false"  />
<cm:search id="g4l2A" query="idContenido = 'id_Grupo4_Linea2_A'" useCache="false"  />
<cm:search id="g4l21A" query="idContenido ='id_Grupo4_Linea2_1A'" useCache="false"  />
<cm:search id="g4l3A" query="idContenido = 'id_Grupo4_Linea3_A'" useCache="false"  />
<cm:search id="g4l31A" query="idContenido ='id_Grupo4_Linea3_1A'" useCache="false"  />

<!--<cm:search id="g5l1" query="idContenido = 'id_Grupo5_Linea1'" useCache="false"  />-->
<!--<cm:search id="g5l2" query="idContenido = 'id_Grupo5_Linea2'" useCache="false"  />-->
<!--<cm:search id="g5l4" query="idContenido = 'id_Grupo5_Linea4'" useCache="false"  />-->
<!--<cm:search id="g5l41" query="idContenido = 'id_Grupo5_Linea4_1'" useCache="false"  />-->
<!--<cm:search id="g5l5" query="idContenido = 'id_Grupo5_Linea5'" useCache="false"  />-->

<!--contenido campaña G6-->
<cm:search id="g6l1" query="idContenido = 'id_Grupo6_Linea1'" useCache="false"  /><!--BOLSAS MINUTOS-->
<cm:search id="g6l2" query="idContenido = 'id_Grupo6_Linea2'" useCache="false"  /><!--BOLSAS MENSAJES-->
<cm:search id="g6l3" query="idContenido = 'id_Grupo6_Linea3'" useCache="false"  /><!--BOLSAS INTERNET MOVIL-->
<cm:search id="g6l4" query="idContenido = 'id_Grupo6_Linea4'" useCache="false"  /><!--OFERTA BOLSA ASOCIADOS-->
<cm:search id="g6l41" query="idContenido = 'id_Grupo6_Linea4_1'" useCache="false"  /><!--OFERTA BOLSA ASOCIADOS-->
<cm:search id="g6l5" query="idContenido = 'id_Grupo6_Linea5'" useCache="false"  />
<cm:search id="g6l6" query="idContenido = 'id_Grupo6_Linea6'" useCache="false"  /><!--BOLSAS MIXTAS-->


<cm:search id="g7l1" query="idContenido = 'id_Grupo7_Linea1'" useCache="false"  />
<cm:search id="g7l2" query="idContenido = 'id_Grupo7_Linea2'" useCache="false"  />
<cm:search id="g7l4" query="idContenido = 'id_Grupo7_Linea4'" useCache="false"  />
<cm:search id="g7l41" query="idContenido = 'id_Grupo7_Linea4_1'" useCache="false"  />
<cm:search id="g7l5" query="idContenido = 'id_Grupo7_Linea5'" useCache="false"  />

<!-- Contenidos para VOZ -->
<cm:search id="g10l1Voz" query="idContenido = 'grupo10_Linea1'" useCache="false"  />
<cm:search id="g10l2Voz" query="idContenido = 'grupo10_Linea2'" useCache="false"  />
<cm:search id="g10l3Voz" query="idContenido = 'grupo10_Linea3'" useCache="false"  />
<cm:search id="g10l4Voz" query="idContenido = 'grupo10_Linea4'" useCache="false"  />
<cm:search id="g10l5Voz" query="idContenido = 'grupo10_Linea5'" useCache="false"  />

<!-- Contenidos para BAM -->
<cm:search id="g10l1Bam" query="idContenido = 'grupo10_Linea1_B'" useCache="false"  />
<cm:search id="g10l2Bam" query="idContenido = 'grupo10_Linea2_B'" useCache="false"  />
<cm:search id="g10l3Bam" query="idContenido = 'grupo10_Linea3_B'" useCache="false"  />
<cm:search id="g10l4Bam" query="idContenido = 'grupo10_Linea4_B'" useCache="false"  />
<cm:search id="g10l5Bam" query="idContenido = 'grupo10_Linea5_B'" useCache="false"  /> 

<cm:search id="bckGrupo1" query="description = 'Grupo1'" useCache="false"  />
<cm:search id="bckGrupo3" query="description = 'Grupo3'" useCache="false"  />
<cm:search id="bckGrupo4" query="description = 'Grupo4'" useCache="false"  />
<cm:search id="bckGrupo5" query="description = 'Grupo5'" useCache="false"  />
<cm:search id="bckGrupo6" query="description = 'Grupo6'" useCache="false"  />
<cm:search id="bckGrupo7" query="description = 'Grupo7'" useCache="false"  />
<cm:search id="bckGrupo10Voz" query="description = 'Grupo10Voz'" useCache="false"  />
<cm:search id="bckGrupo10Bam" query="description = 'Grupo10Bam'" useCache="false"  />
<!-- lightbox -->

<!-- <div class="marca-oferta" id="marca-oferta"><cm:getProperty node="${scriptAdwords[0]}" name="html" /></div> -->
<!-- fin lightbox -->

<h:panelGroup rendered="#{nbaController.ocultar}">

<div id="bannerPrincipal_home" class="clearfix" style="position: relative;">
<div id="wrap">

  <div id="principal" class="jcarousel-skin-tango" style="position: relative;">
  	
		<div id="mycarousel" class="jcarousel-container jcarousel-container-horizontal" style="position: relative;">

			<div class="jcarousel-container jcarousel-container-horizontal-cabecera" style="position: relative; background-color: #0073AE;">
				<div class="mis_ofertas">
					<font color="white" size="3"><i><b>Mis Ofertas</b></i></font>
				</div>
				
				
				<div class="anterior" style="visibility:<h:outputText value='#{nbaController.ocultarPuntos}'/>;">
					<a href=""  id="mycarousel-prev"><font color="white">anterior</font></a>
				</div>
				
				
				<div class="siguiente" style="visibility:<h:outputText value='#{nbaController.ocultarPuntos}'/>;">
					<a href="" id="mycarousel-next"><font color="white">siguiente</font></a>
				</div>
				
				
				<div class="minimizar" id="minimizarBanner">
	 				<a href="javascript:minimizar();"><font color="white" > <img src="${pageContext.request.contextPath}/portlet/nba/img/min.png" id="minimizarID"  alt="alternative text" title="Minimizar"></font></a> 
				</div>
				
				<div class="puntos-inicio" style="visibility:<h:outputText value='#{nbaController.ocultarPuntos}'/>;">
					<font color="white">...</font>
				</div>
				<div class="puntos-fin" style="visibility:<h:outputText value='#{nbaController.ocultarPuntos}'/>;">
					<font color="white">...</font>
				</div>
				
				<div class="jcarousel-control"  style="visibility:<h:outputText value='#{nbaController.ocultarControl}'/>;"> 
					<h:panelGroup rendered="#{nbaController.ocultarControl!='hidden'}">
						<it:iterator var="item" value="#{nbaController.ofertas}" rowIndexVar="row" >
							
							<h:panelGroup rendered="#{row+1 == 1}">
								<a href="#" onclick="javascript:clickHREF(<h:outputText value="#{row+1}"/>)" class="enlace_destacado clearfix enlaceMargen <h:outputText value="#{item.seleccionado}"/>" style="display:<h:outputText value="#{item.oculto}"/>;<h:outputText value="#{item.estilo}"/>" id="id_<h:outputText value="#{row+1}"/>"><font><b><h:outputText value="#{row+1}"/></b></font></a>
							</h:panelGroup>
							<h:panelGroup rendered="#{row+1 > 1}">
								<a href="#" onclick="javascript:clickHREF(<h:outputText value="#{row+1}"/>)" class="enlace_destacado clearfix enlaceMargen <h:outputText value="#{item.seleccionado}"/>" style="display:<h:outputText value="#{item.oculto}"/>;<h:outputText value="#{item.estilo}"/>" id="id_<h:outputText value="#{row+1}"/>"><b><h:outputText value="#{row+1}"/></b></a>
							</h:panelGroup>
							
						</it:iterator>
					</h:panelGroup>
				</div>
			</div>
			<div class="jcarousel-clip jcarousel-clip-horizontal" style="overflow: hidden;  position: absolute; display :<h:outputText value="#{nbaController.minimizarBanner}"/>;" id="mycarousel2">
				
				<ul class="jcarousel-list jcarousel-list-horizontal" >
					<it:iterator var="item" value="#{nbaController.ofertas}" rowIndexVar="row">
						<li class="jcarousel-item jcarousel-item-horizontal jcarousel-item-<h:outputText value='#{row+1}'/> jcarousel-item-<h:outputText value='#{row+1}'/>-horizontal" jcarouselindex="<h:outputText value='#{row+1}'/>" style="float: left; list-style: none;">
							<a href="javascript:levantarLightbox2('<h:outputText value="#{item.url}"/>','<h:outputText value="#{item.codigoOferta}"/>','<h:outputText value="#{item.grupo}"/>');">
								<div style="position: relative;">
									
								<!-- GRUPO 1 -->
								<!-- <h:panelGroup rendered="#{item.grupo=='G1'}">
									  <div style="position: absolute; left: z-index: 1;">
									  	<img src="${pageContext.request.contextPath}/ShowProperty?nodeId=<cm:getProperty node="${bckGrupo1[0]}" name="cm_uid" />" >
									 <!-- </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea1"> 
									  	<cm:getProperty node="${g1l1[0]}" name="html" />
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea2"> 
										  <cm:getProperty node="${g1l2[0]}" name="html" />
										</div>
									  	<div class="<h:outputText value="#{item.grupo}"/> linea3"> 
									  		<cm:getProperty node="${g1l3[0]}" name="html" /><h:outputText value="#{item.valor}"/><cm:getProperty node="${g1l31[0]}" name="html" />
									  	</div>
									     <h:panelGroup rendered="#{item.movil!='-1'}">
										  <div class="<h:outputText value="#{item.grupo}"/> linea4">  
										  	<cm:getProperty node="${g1l4[0]}" name="html" />&nbsp;<h:outputText value="#{item.movil}"/><cm:getProperty node="${g1l41[0]}" name="html" />
										  </div>
									  </h:panelGroup>
										<div class="<h:outputText value="#{item.grupo}"/> linea5">  -->
										 	<!-- <font size="3" color="#ff6702"><i>PROMOCION</i></font> -->
										 <!--	<cm:getProperty node="${g1l5[0]}" name="html" />
										 </div>
								  </h:panelGroup>	-->
								  
								  <!-- GRUPO 2 
								  <h:panelGroup rendered="#{item.grupo=='G2'}">
									 <div style="position: absolute; left: z-index: 1;">
									  	<img src="${pageContext.request.contextPath}/ShowProperty?nodeId=/WLP%20Repository/2347" > 
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea1"> 
									  	<cm:getProperty node="${g2l1[0]}" name="html" />
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea2"> 
										  <cm:getProperty node="${g2l2[0]}" name="html" />
										</div>
									  	<div class="<h:outputText value="#{item.grupo}"/> linea3"> 
									  		<cm:getProperty node="${g2l3[0]}" name="html" /><h:outputText value="#{item.valor}"/><cm:getProperty node="${g2l31[0]}" name="html" />
									  	</div>
									     <h:panelGroup rendered="#{item.movil!='-1'}">
										  <div class="<h:outputText value="#{item.grupo}"/> linea4">  
										  	<cm:getProperty node="${g2l4[0]}" name="html" /><h:outputText value="#{item.movil}"/><cm:getProperty node="${g2l41[0]}" name="html" />
										  </div>
									  </h:panelGroup>
								  </h:panelGroup>
								  -->
								  <!-- GRUPO 3 -->
<!--								  <h:panelGroup rendered="#{item.grupo=='G3'}">-->
<!--									     <div style="position: absolute; left: z-index: 1;">-->
<!--											<h:graphicImage value="#{item.img}"></h:graphicImage>-->
<!--										 </div>-->
<!--										 <div class="<h:outputText value="#{item.grupo}"/> linea1">  -->
<!--										 	 <cm:getProperty node="${g3l1[0]}" name="html" />-->
<!--										 </div>-->
<!--										 <div class="<h:outputText value="#{item.grupo}"/> linea2"> -->
<!--										 	<cm:getProperty node="${g3l2[0]}" name="html" />-->
<!--										 </div>-->
<!--										 <div class="<h:outputText value="#{item.grupo}"/> linea3"> -->
<!--										 	<cm:getProperty node="${g3l3[0]}" name="html" />-->
<!--										 </div>-->
<!--									  	<div class="<h:outputText value="#{item.grupo}"/> linea5"> -->
<!--										 	 <cm:getProperty node="${g3l5[0]}" name="html" />-->
<!--										 </div>-->
<!--								  </h:panelGroup>-->
								 <!-- // GRUPO 3 -->
								 
								  <!-- GRUPO 4 -->
								<h:panelGroup rendered="#{item.grupo=='G4' && item.movil =='-1'}">																				
									  <div style="position: absolute; left: z-index: 1;">
										   <h:graphicImage value="#{item.img}"></h:graphicImage>
										   <input type="hidden" name="idImagen" value="<h:outputText value="#{item.idImg}"/>">
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea1_<h:outputText value="#{item.idImg}"/>">   
									  	<cm:getProperty node="${g4l1[0]}" name="html" />
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea2">   
									  	<cm:getProperty node="${g4l2[0]}" name="html" /><h:outputText value='#{item.nombreOferta}'/><cm:getProperty node="${g4l21[0]}" name="html" />
									  </div>
									   <div class="<h:outputText value="#{item.grupo}"/> linea3">    
 										  	<cm:getProperty node="${g4l3[0]}" name="html" />
 										  	<h:outputText rendered="#{item.idImg !='3' }" style="font-size:27px;font-weight:bold;color:#FFF;" value="$ #{item.valorFormateado}"/>
 										  	<h:outputText rendered="#{item.idImg =='3'}" style="font-size:27px;font-weight:bold;color:#0154A0;" value="$ #{item.valorFormateado}"/>
 										  	<cm:getProperty node="${g4l31[0]}" name="html" />
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea5"> 
										 	<cm:getProperty node="${g4l5[0]}" name="html" />
									  </div>
								  </h:panelGroup>
								  <h:panelGroup rendered="#{item.grupo=='G4' && item.movil !='-1'}">																				
									  <div style="position: absolute; left: z-index: 1;">
										   <h:graphicImage value="#{item.img}"></h:graphicImage>
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea1_A_<h:outputText value="#{item.idImg}"/>">   
									  	<cm:getProperty node="${g4l1A[0]}" name="html" />
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea2_A">   
									  	<cm:getProperty node="${g4l2A[0]}" name="html" /><h:outputText value='#{item.movil}'/><cm:getProperty node="${g4l21A[0]}" name="html" />
									  </div>
									   <div class="<h:outputText value="#{item.grupo}"/> linea3_A">    
 										  	<cm:getProperty node="${g4l3A[0]}" name="html" /><h:outputText style="font-size:20px;font-weight:bold;" value="#{item.nombreOferta}"/><cm:getProperty node="${g4l31A[0]}" name="html" />
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea5_A"> 
										 	<cm:getProperty node="${g4l5[0]}" name="html" />
									  </div>
								  </h:panelGroup>
								  
								  <!-- GRUPO 5 -->
<!--								  <h:panelGroup rendered="#{item.grupo=='G5'}">-->
<!--									     <div style="position: absolute; left: z-index: 1;">-->
<!--											<img src="${pageContext.request.contextPath}/ShowProperty?nodeId=<cm:getProperty node="${bckGrupo5[0]}" name="cm_uid" />" >-->
<!--										 </div>-->
<!--										 <div class="<h:outputText value="#{item.grupo}"/> linea1">  -->
<!--										 	 <cm:getProperty node="${g5l1[0]}" name="html" />-->
<!--										 </div>-->
<!--										 <div class="<h:outputText value="#{item.grupo}"/> linea2"> -->
<!--										 	<cm:getProperty node="${g5l2[0]}" name="html" />-->
<!--										 </div>-->
<!--										 <h:panelGroup rendered="#{item.movil!='-1'}">-->
<!--										  	<div class="<h:outputText value="#{item.grupo}"/> linea4"> -->
<!--												  <cm:getProperty node="${g5l4[0]}" name="html" />&nbsp;<h:outputText value="#{item.movil}"/><cm:getProperty node="${g5l41[0]}" name="html" />-->
<!--											</div>-->
<!--									  	</h:panelGroup>-->
<!--									  	<div class="<h:outputText value="#{item.grupo}"/> linea5"> -->
<!--										 	 <font size="3" color="#ff6702"><i>PROMOCION</i></font> -->
<!--										 	 <cm:getProperty node="${g5l5[0]}" name="html" />-->
<!--										 </div>-->
<!--								  </h:panelGroup>-->
								 <!-- // GRUPO 5 -->
								  
								  <!-- GRUPO 6 -->
								  <h:panelGroup rendered="#{item.grupo=='G6'}">
									  <div style="position: absolute; left: z-index: 1;">
									  <h:graphicImage value="#{item.img}"></h:graphicImage>
									  <h:inputHidden value="#{item.idImg}"/>
									  </div>
									<!--BANNER PARA BOLSAS MINUTOS-->
									  <h:panelGroup rendered="#{item.tipoBolsa=='MINUTOS'}">
									  	<div class="<h:outputText value="#{item.grupo}"/> linea1 MIN<h:outputText value="#{item.idImg}"/>">    
 										  	<cm:getProperty node="${g6l1[0]}" name="html" />
									  	</div>
									  	<h:panelGroup rendered="#{item.movil !='-1'}">
									  		<div class="<h:outputText value="#{item.grupo}"/> linea4 MIN<h:outputText value="#{item.idImg}"/>">  
											  	<cm:getProperty node="${g6l4[0]}" name="html" />&nbsp;<h:outputText style="font-size:24px;" value='#{item.movil}'/><cm:getProperty node="${g6l41[0]}" name="html" />
										  	</div>
									  	</h:panelGroup>
									  	<div class="<h:outputText value="#{item.grupo}"/> linea5 MIN<h:outputText value="#{item.idImg}"/>"> 
									 		<cm:getProperty node="${g6l5[0]}" name="html" />
									 	</div>
									  </h:panelGroup>
									  
									<!--BANNER PARA BOLSAS SMS-->
									  <h:panelGroup rendered="#{item.tipoBolsa=='SMS'}">
									  	<div class="<h:outputText value="#{item.grupo}"/> linea2 SMS<h:outputText value="#{item.idImg}"/>">    
 										  	<cm:getProperty node="${g6l2[0]}" name="html" />
									  	</div>
									  	<h:panelGroup rendered="#{item.movil !='-1'}">
											<div class="<h:outputText value="#{item.grupo}"/> linea4 SMS<h:outputText value="#{item.idImg}"/>">  
											  	<cm:getProperty node="${g6l4[0]}" name="html" />&nbsp;<h:outputText style="font-size:24px;" value='#{item.movil}'/><cm:getProperty node="${g6l41[0]}" name="html" />
											</div>
										</h:panelGroup>
										<div class="<h:outputText value="#{item.grupo}"/> linea5 SMS<h:outputText value="#{item.idImg}"/>"> 
									 		<cm:getProperty node="${g6l5[0]}" name="html" />
									 	</div>
									  </h:panelGroup>
									  
									  
									<!-- BANNER PARA BOLSAS INTERNET MOVIL -->  										  
									  <h:panelGroup rendered="#{item.tipoBolsa=='IM'}">
									  	<div class="<h:outputText value="#{item.grupo}"/> linea3 IM<h:outputText value="#{item.idImg}"/>">    
 										  	<cm:getProperty node="${g6l3[0]}" name="html" />
									  	</div>
									  	<h:panelGroup rendered="#{item.movil !='-1'}">
									  		<div class="<h:outputText value="#{item.grupo}"/> linea4 IM<h:outputText value="#{item.idImg}"/>">  
											  	<cm:getProperty node="${g6l4[0]}" name="html" />&nbsp;<h:outputText style="font-size:24px;" value='#{item.movil}'/><cm:getProperty node="${g6l41[0]}" name="html" />
										  	</div>
									  	</h:panelGroup>
									  	<div class="<h:outputText value="#{item.grupo}"/> linea5 IM<h:outputText value="#{item.idImg}"/>"> 
									 		<cm:getProperty node="${g6l5[0]}" name="html" />
									 	</div>
									  </h:panelGroup>
										<!-- BANNER PARA BOLSAS MIXTAS -->  										  
									  <h:panelGroup rendered="#{item.tipoBolsa=='MIXTA'}">
									  	<div class="<h:outputText value="#{item.grupo}"/> linea3 MIX<h:outputText value="#{item.idImg}"/>">    
 										  	<cm:getProperty node="${g6l6[0]}" name="html" />
									  	</div>
									  	<h:panelGroup rendered="#{item.movil !='-1'}">
									  		<div class="<h:outputText value="#{item.grupo}"/> linea4 MIX<h:outputText value="#{item.idImg}"/>"> 
											  	<cm:getProperty node="${g6l4[0]}" name="html" />&nbsp;<h:outputText style="font-size:24px;" value='#{item.movil}'/><cm:getProperty node="${g6l41[0]}" name="html" />
										  	</div>
									  	</h:panelGroup>
									  	<div class="<h:outputText value="#{item.grupo}"/> linea5 MIX<h:outputText value="#{item.idImg}"/>"> 
									 		<cm:getProperty node="${g6l5[0]}" name="html" />
									 	</div>
									  </h:panelGroup>
						</h:panelGroup>

						<!-- GRUPO 7 -->
								  <h:panelGroup rendered="#{item.grupo=='G7'}">
									  <div style="position: absolute; left: z-index: 1;">
								  <img src="${pageContext.request.contextPath}/ShowProperty?nodeId=<cm:getProperty node="${bckGrupo7[0]}" name="cm_uid" />" >
									  </div>
									  
										  <div class="<h:outputText value="#{item.grupo}"/> linea1" >  
										  	<cm:getProperty node="${g7l1[0]}" name="html" /> 
										  </div>
									 
									  <div class="<h:outputText value="#{item.grupo}"/> linea2">   
									  	<cm:getProperty node="${g7l2[0]}" name="html" />
									  </div>
									  <!-- <h:panelGroup rendered="#{item.movil!='-1'}">
									  <div class="<h:outputText value="#{item.grupo}"/> linea4">   
									  	<cm:getProperty node="${g7l4[0]}" name="html" />&nbsp;<h:outputText value='#{item.movil}'/><cm:getProperty node="${g7l41[0]}" name="html" />
									  </div>
									   </h:panelGroup> -->
									    <div class="<h:outputText value="#{item.grupo}"/> linea5"> 
										 	<!-- <font size="3" color="#ff6702"><i>PROMOCION</i></font> -->
										 	<cm:getProperty node="${g7l5[0]}" name="html" />
									 </div>
								  </h:panelGroup>
								  
								  <!-- GRUPO 10 VOZ -->
								  <h:panelGroup rendered="#{item.grupo == 'G10'}">
									  <div style="position: absolute; left: z-index: 1;">
									  	<h:graphicImage value="#{item.img}"></h:graphicImage>
									  	<h:inputHidden value="#{item.idImg}"/>
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea1">   
									  	<cm:getProperty node="${g10l1Voz[0]}" name="html" />
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea2">
									  	<cm:getProperty node="${g10l2Voz[0]}" name="html" />
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea3">
<!--									  	<cm:getProperty node="${g10l3Voz[0]}" name="html" />-->
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea4">
<!--									  	<cm:getProperty node="${g10l4Voz[0]}" name="html" />-->
									  </div>
									  <div class="<h:outputText value="#{item.grupo}"/> linea5">    
									  	<cm:getProperty node="${g10l5Voz[0]}" name="html" />
									  </div>
								  </h:panelGroup>
								  
								  <!-- GRUPO 10 BAM -->
<!--								  <h:panelGroup rendered="#{item.grupo=='G10' && item.ofertaBAM}">-->
<!--									  <div style="position: absolute; left: z-index: 1;">-->
<!--									  	<h:graphicImage value="#{item.img}"></h:graphicImage>-->
<!--									  </div>-->
<!--									  <div class="<h:outputText value="#{item.grupo}"/> linea1">   -->
<!--									  	<cm:getProperty node="${g10l1Bam[0]}" name="html" />-->
<!--									  </div>-->
<!--									  <div class="<h:outputText value="#{item.grupo}"/> linea2">-->
<!--									  	<cm:getProperty node="${g10l2Bam[0]}" name="html" />-->
<!--									  </div>-->
<!--									  <div class="<h:outputText value="#{item.grupo}"/> linea3">    -->
<!--									  	<cm:getProperty node="${g10l3Bam[0]}" name="html" />-->
<!--									  </div>-->
<!--									  <div class="<h:outputText value="#{item.grupo}"/> linea4">    -->
<!--									  	<cm:getProperty node="${g10l4Bam[0]}" name="html" />-->
<!--									  </div>-->
<!--									  <div class="<h:outputText value="#{item.grupo}"/> linea5">    -->
<!--									  	<cm:getProperty node="${g10l5Bam[0]}" name="html" />-->
<!--									  </div>-->
<!--								  </h:panelGroup>-->
								  
								</div>
							</a>
						</li>
					</it:iterator>
				</ul>
				
			</div>
		</div>
	</div>
</div>
</div>
</h:panelGroup >
<!-- <a id='TB_closeRejectWindowButton' href='#' onClick='_gaq.push(['_trackEvent', 'FE_Sumate_2012_v1', 'lightbox', 'no_mostrar']);'></a>
<a id="TB_closeWindowButton" href="#" style="padding: 5px 70px 50px; position: relative; background: url("") repeat scroll 0% 0% transparent; z-index: 99;"></a>
<div class="TB_ajaxContent2" >	
<iframe id="iframeUrlCargarOferta"  allowtransparency="true"  style="display: none;"  width="701"  height="1315" src=""></iframe> 
</div> -->
<script type="text/javascript">

function levantarLightbox2(urlOferta, codOferta, grupoOferta){		

	$('#CVM').val("Si");
	codOfertaBlindaje = codOferta;
	grupoOfertaBlindaje = grupoOferta;
	urlNBA = urlOferta;
	if ($.browser.msie && parseInt($.browser.version) <= 6) {
		$('.enlaceOferta.ofertaNBA').trigger('click');
	}else{
		$('.thickbox.ofertaNBA').click();
	}				
	
	 
	// div = document.getElementById("iframeUrlCargarOferta");
	// div.style.display = "";
	// $('.TB_ajaxContent2').click();
	 
	 //$('#iframeUrlCargarOferta').contents().find('.no_mostrar').css('display', 'none');
	 //frame = $('#iframeUrlCargarOferta');
	 //div2 = $('#no_mostrar', frame);
	 //alert(div2.style.display);
	 
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
});
</script>
<h:outputLink value="TB_Blindaje_NBA.faces" styleClass="thickbox ofertaNBA"></h:outputLink>
<h:outputLink value="TB_Blindaje_NBA.faces" styleClass="enlaceOferta ofertaNBA"></h:outputLink>
<input id="CVM" type="hidden" value="" />		

<input type="hidden" value="<h:outputText value='#{nbaController.movil}'/>" id="movil" />
<input type="hidden" value="<h:outputText value='#{nbaController.subMercado}'/>" id="subMercado" />
<input type="hidden" value="<h:outputText value='#{nbaController.flagBam}'/>" id="flagBam" />
<input type="hidden" value="<h:outputText value='#{nbaController.time}'/>" id="time" />
</f:view>
</c:if>
