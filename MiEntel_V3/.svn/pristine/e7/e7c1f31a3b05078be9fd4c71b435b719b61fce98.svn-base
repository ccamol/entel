<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="pref" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript" src="${pageContext.request.contextPath}/portlet/nba/js/jquery.jcarousel.min.js"></script>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/nba/style/skin.css"  >
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/nba/style/banner.css"  >



<script type="text/javascript">

	var indice = 2;
	var nextPrev = -1;
	var numData=0;
	var cont=0;
	var last="next";
		
	var codOfertaBlindaje = 0;
	var grupoOfertaBlindaje = "";
	var url = "";
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
			}else{
				div.style.display = "none";
				state=1;
			}
			
			
	       
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

		function clickHREF(data){
		
			if(numData!=data){
				
				indice=data-1;
				var total = $('#mycarousel .enlace_destacado').length;
		
				$('#mycarousel .enlace_destacado').removeClass('enlace_seleccionado');
				$('#mycarousel .enlace_destacado').eq(indice++).addClass('enlace_seleccionado');

				nextPrev=-1;
	
				
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

			divx = document.getElementById("mycarousel");
			div = document.getElementById("mycarousel2");
			if(div.style.display=="none"){				
				state=0;
				div.style.display = "";
				state=0;				
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
				
				if(indice == total) {
					indice = 0;
				}
				indice=indice+1;
				numData=data;
				last="next";
				cont=0;
		}

			
		
</script> 


 
<f:view beforePhase="#{diferenciacionPPAltoValorController.init}">
<cm:search id="bolsasPlus" query="idContenido = 'idBolsasPlus'" useCache="false"/>
<cm:search id="tarifasPlus" query="idContenido = 'idTarifasPlus'" useCache="false" />
<cm:search id="infoPlus" query="idContenido = 'infoPlus'" useCache="false" />
<pref:getPreference name="linkBolsasPlus" var="redireccionarBolsasPlus"/>
<pref:getPreferences  name="linkTarifasPlus" var="redireccionarTarifasPlus" />



<h:panelGroup rendered="#{diferenciacionPPAltoValorController.validoCarruselPPAltoValor}">


<div id="bannerPrincipal_home" class="clearfix" style="position: relative;">
<div id="wrap">

  <div id="principal" class="jcarousel-skin-tango" style="position: relative;">
  	
<div id="mycarousel" class="jcarousel-container jcarousel-container-horizontal" style="position: relative;">

          <div class="jcarousel-container jcarousel-container-horizontal-cabecera" style="position: relative; background-color: #0073AE;">
				<div class="mis_ofertas">
					<font color="white" size="3"><i><b>Promociones Prepago Plus </b></i></font>
				</div>
				

				
				<div class="minimizar" id="minimizarBanner">
	 				<a href="javascript:minimizar();"><font color="white" > <img src="${pageContext.request.contextPath}/portlet/nba/img/min.png" id="minimizarID"  alt="alternative text" title="Minimizar"></font></a> 
				</div>

				
				<div class="jcarousel-control"  style="left:240px; visibility:<h:outputText value='#{hidden}'/>;"> 
					<h:panelGroup >						
					   <a href="#" onclick="javascript:clickHREF(1),clickNext(1)" class="enlace_destacado clearfix enlaceMargen enlace_seleccionado "  id="id_1"><font><b style="margin:6px;">1</b></font> </a>
					</h:panelGroup >						
					<h:panelGroup rendered="#{diferenciacionPPAltoValorController.validoBannerBolsasPlus}">
					   <a href="#" onclick="javascript:clickHREF(2),clickNext(2)" class="enlace_destacado clearfix enlaceMargen "  id="id_2"/><font><b style="margin:6px;">2</b></font></a>
					   <h:panelGroup rendered="#{diferenciacionPPAltoValorController.validoBannerTarifasPlus}">
					   <a href="#" onclick="javascript:clickHREF(3),clickNext(3)" class="enlace_destacado clearfix enlaceMargen "  id="id_3"><font><b style="margin:6px;">3</b></font></a>
					  </h:panelGroup>
					</h:panelGroup>
					
					<h:panelGroup rendered="#{!diferenciacionPPAltoValorController.validoBannerBolsasPlus}">					   
					   <h:panelGroup rendered="#{diferenciacionPPAltoValorController.validoBannerTarifasPlus}">
					   <a href="#" onclick="javascript:clickHREF(2),clickNext(2)" class="enlace_destacado clearfix enlaceMargen "  id="id_2"><font><b style="margin:6px;">2</b></font></a>
					  </h:panelGroup>
					</h:panelGroup>
					
					
				    
				</div>
            </div>

			<div class="jcarousel-clip jcarousel-clip-horizontal" style="overflow: hidden;  position: absolute; display :block;" id="mycarousel2">
				
				<ul class="jcarousel-list jcarousel-list-horizontal" >
				
				
				    <h:panelGroup >						
						<li class="jcarousel-item jcarousel-item-horizontal jcarousel-item-1 jcarousel-item-1-horizontal" jcarouselindex="1" style="float: left; list-style: none;">
							<div class=" linea1">  
                         	   <a  onclick="mxTracker._trackPromo('Banner','Beneficios_MP1');">
                               		<cm:getProperty node="${infoPlus[0]}" name="html" />
                                </a>
							</div>
						</li>
					 </h:panelGroup>	
				
				 <h:panelGroup rendered="#{diferenciacionPPAltoValorController.validoBannerBolsasPlus}">
						
						<li class="jcarousel-item jcarousel-item-horizontal jcarousel-item-2 jcarousel-item-2-horizontal" jcarouselindex="2" style="float: left; list-style: none;">
							<div class=" linea2">  
                         	   <a  href="<r:pageUrl pageLabel='${diferenciacionPPAltoValorController.linkBolsasPPAltoValor}'/>"  onclick="mxTracker._trackPromo('Banner','Bolsas_MP1');">
                               		<cm:getProperty node="${bolsasPlus[0]}" name="html" />
                                </a>
							</div>
						</li>
					 </h:panelGroup>

					<h:panelGroup rendered="#{diferenciacionPPAltoValorController.validoBannerTarifasPlus}">
						<li class="jcarousel-item jcarousel-item-horizontal jcarousel-item-3 jcarousel-item-3-horizontal" jcarouselindex="3" style="float: left; list-style: none;">
					    	<div class=" linea3">  
                          		<a  href="<r:pageUrl pageLabel='${diferenciacionPPAltoValorController.linkTarifasPPaltovalor}'/>" onclick="mxTracker._trackPromo('Banner','Tarifas_MP1');">    	   
                              		<cm:getProperty node="${tarifasPlus[0]}" name="html" />
						  		</a>	
							</div>
					    </li>
					</h:panelGroup>
						
								

			    </ul>
				
			</div>
</div>
</div>
</div>
</div>



</h:panelGroup>

</f:view>
