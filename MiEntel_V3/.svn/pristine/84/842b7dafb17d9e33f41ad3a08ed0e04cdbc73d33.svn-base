<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>
<f:view>

<cm:search id="bloqueMiPrimerPlanTP" query="idContenido = 'bloqueOfertaTarifaPlana'" useCache="false"/>
<cm:search id="bloqueMiPrimerPlanTR" query="idContenido = 'bloqueOfertaTarifaRed'" useCache="false"/>
<cm:search id="bloqueMiPrimerPlanMM" query="idContenido = 'bloqueOfertaMultimedia'" useCache="false"/>

<cm:search id="paso1" query="idContenido = 'paso1'" useCache="false"/>
<cm:search id="paso2" query="idContenido = 'paso2'" useCache="false"/>
<cm:search id="paso3" query="idContenido = 'paso3'" useCache="false"/>

<cm:search id="detallePlanes" query="idContenido = 'txtEnlaceVerDetalle'" useCache="false"/>

<cm:search id="checks" query="idContenido = 'checksBeneficios'" useCache="false"/>

<cm:search id="terminos" query="idContenido = 'terminosYcondiciones'" useCache="false"/>

<html>
<head>
</head>

<body>
<div id="container_migracion" style="position:relative;right:5px">
<div id="dhtmltooltip"></div>
   <div class="header_migracion">
    <div class="top_migracion">
      <!-- <p><span style="color:#F93;font-size:23px">C&aacute;mbiate</span><span style="font-size:20px"> ahora</span> <span style="color:#F93;font-size:23px">de Prepago a Plan</span><span style="font-size:23px"> en solo 3 pasos...</span></p> -->
    </div>
    <!--top-->

    <div class="lin2_migracion">
      <!-- <p><span style="font-size:20px">¡Sin evaluaci&oacute;n comercial!</span></p> -->
    </div>
    <!--lin2-->
    
   <div class="lista_migracion">
   <!--
   	<p><img src="../../framework/skins/mientel/img/migraciones/vineta_top.gif" width="4" height="4" alt="" />Debes mantener un saldo mayor al del costo del plan</p>
    <p><img src="../../framework/skins/mientel/img/migraciones/vineta_top.gif" width="4" height="4" alt="" /><strong>No puedes tener una deuda</strong> impaga con Entel (Presta Luca, etc.)</p> 
    -->
   </div>  
 	
  </div>
  <!--header-->
  <br/>
  <hr style="display: block;margin: 1em 0; padding: 0;height:1px; border:0; border-top: 1px solid #ccc;"/>
  <br/>

<cm:getProperty node="${paso1[0]}" name="html" />

<div class="planes_migracion">
    
	<div class="cuadro_plan_migracion plan_uno_migracion" >
     	<cm:getProperty node="${bloqueMiPrimerPlanTP[0]}" name="html" />
	    <div class="ver-detalles_migracion">
        	<a href="<%=request.getContextPath()%>/portlet/ofertas/migraciones/LB_Migracion.jsp?tipoPlan=tp" class="enlaceOfertaMigracionDefault"><cm:getProperty node="${detallePlanes[0]}" name="titulo" /><cm:getProperty node="${detallePlanes[0]}" name="html" /></a>
    	</div>
    </div>
    
    <div class="cuadro_plan_migracion plan_dos_migracion">
	    <cm:getProperty node="${bloqueMiPrimerPlanTR[0]}" name="html" />
	    <div class="ver-detalles_migracion">
	    	<a href="<%=request.getContextPath()%>/portlet/ofertas/migraciones/LB_Migracion.jsp?tipoPlan=tr" class="enlaceOfertaMigracionDefault TR"><cm:getProperty node="${detallePlanes[0]}" name="titulo" /><cm:getProperty node="${detallePlanes[0]}" name="html" /></a>
    	</div>
    </div>
    
    <div class="cuadro_plan_migracion plan_tres_migracion">
        <cm:getProperty node="${bloqueMiPrimerPlanMM[0]}" name="html" />
        <div class="ver-detalles_migracion">
        	<a href="<%=request.getContextPath()%>/portlet/ofertas/migraciones/LB_Migracion.jsp?tipoPlan=mm" class="enlaceOfertaMigracionMM"><cm:getProperty node="${detallePlanes[0]}" name="titulo" /><cm:getProperty node="${detallePlanes[0]}" name="html" /></a>
        </div>
    </div>
    
</div>
<!--planes-->


<br/>
<cm:getProperty node="${paso2[0]}" name="html" />
	<br/>
<cm:getProperty node="${paso3[0]}" name="html" />
<br/>
<cm:getProperty node="${checks[0]}" name="html" />

<!--cuadro-checks-->

<div class="borde-final_migracion"></div>

<cm:getProperty node="${terminos[0]}" name="html" />

</div>
<!--container-->
<script type="text/javascript">

	$(document).ready(function(){ 
	
			$(".planes_migracion").find("h1").css( { background:"url()" }); 
		    $(".planes_migracion").find("p").css( { 'margin-left': "10px", 'margin-top':"-5px" });
				
		if($.browser.msie){
			if(parseInt($.browser.version) <= 7){
				$("a.enlaceOfertaMigracionDefault").fancybox({
				    'overlayOpacity' : 0.5,
				    'overlayColor' : '#000000',
				    'hideOnContentClick' : false,
				    'hideOnOverlayClick' : false,
				    'showCloseButton'	:true,
				    'padding' : 'auto',
				    'scrolling' : 'no',
				    'frameWidth'  : 621,
				    'frameHeight' : 381,
				    'width' : 621,
				    'height' : 381
				});
				$("a.enlaceOfertaMigracionDefault.TR").fancybox({
				    'overlayOpacity' : 0.5,
				    'overlayColor' : '#000000',
				    'hideOnContentClick' : false,
				    'hideOnOverlayClick' : false,
				    'showCloseButton'	:true,
				    'padding' : 'auto',
				    'scrolling' : 'no',
				    'frameWidth'  : 621,
				    'frameHeight' : 431,
				    'width' : 621,
				    'height' : 431
				});
			}else{
				$("a.enlaceOfertaMigracionDefault").fancybox({
				    'overlayOpacity' : 0.5,
				    'overlayColor' : '#000000',
				    'hideOnContentClick' : false,
				    'hideOnOverlayClick' : false,
				    'showCloseButton'	:true,
				    'padding' : 'auto',
				    'scrolling' : 'no',
				    'frameWidth'  : 621,
				    'frameHeight' : 361,
				    'width' : 621,
				    'height' : 361
				});
			}
		}else{
			$("a.enlaceOfertaMigracionDefault").fancybox({
			    'overlayOpacity' : 0.5,
			    'overlayColor' : '#000000',
			    'hideOnContentClick' : false,
			    'hideOnOverlayClick' : false,
			    'showCloseButton'	:true,
			    'padding' : 'auto',
			    'scrolling' : 'no',
			    'frameWidth'  : 621,
			    'frameHeight' : 361,
			    'width' : 621,
			    'height' : 361
			});
		}

		$("a.enlaceOfertaMigracionMM").fancybox({
		    'overlayOpacity' : 0.5,
		    'overlayColor' : '#000000',
		    'hideOnContentClick' : false,
		    'hideOnOverlayClick' : false,
		    'showCloseButton'	:true,
		    'padding' : 'auto',
		    'scrolling' : 'no',
		    'frameWidth'  : 621,
		    'frameHeight' : 461,
		    'width' : 621,
		    'height' : 461
		});
		

		  $("#ver-terminos").click(function () {
				$("#terminos").show();
				$("#ver-terminos").hide();
				$("#ver-terminos-into").show();
			});
	
		$("#ver-terminos-into").click(function () {
			$("#terminos").hide();
			$("#ver-terminos").show();	
			$("#ver-terminos-into").hide();
		});			 
	
	}); 

</script>


</body>
</html>
</f:view>