<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>

<script type="text/javascript" src="${pageContext.request.contextPath}/portlet/plan/js/excanvas.js"></script>        
<script type="text/javascript" src="${pageContext.request.contextPath}/portlet/plan/js/jquery.knob.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/portlet/plan/js/rainbowvis.js"></script>
	
<script type="text/javascript">
	   
	function loadCategoria() {
		
	    var url='<%=request.getContextPath()%>/portlet/plan/resumenPlanCategoriaJson.faces';
	    
	    setTimeout(function(){
	   		 alertaReintento('planCategoria');    
	    	      },TIEMPOREINTENTO);


	    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            success: function (resp){
                    //Evita reintento
                    flagRespuestas['planCategoria'] = '1'; 
                    if(resp.estado == 'Ok'){
                        porcentaje=""+resp.respuesta.porcentaje;
                        porcentaje=porcentaje.split(".")[0];
		                $('#categoria').html(resp.respuesta.nombreCategoria);
						$('#pesosAcumulados').html('$'+resp.respuesta.montoAcutal);
						$('#valor').html(porcentaje);
		                $('#porcentaje').val(porcentaje);
		                $('#semestre').html(resp.respuesta.semestre);

		                caso1="Para ser Prepago Plus y obtener los beneficios, debes acumular $50.000 en recargas en el semestre.";
		                caso2="Para mantener la categor&iacute;a Prepago Plus y sus beneficios, debes acumular $50.000 en recargas en el semestre.";
		                caso3="Haz alcanzado la meta de recargas de este semestre, ahora eres un Cliente Prepago Plus. Revisa tus beneficios aqu&iacute;.";
		                link = '<span class="db-tabla-texto-derecha"><a target="_blank" href="http://personas.entel.cl/PortalPersonas/appmanager/entelpcs/personas?_nfpb=true&_pageLabel=P68200347331371238568705"><u>+ info</u></a></span>';

		                if (porcentaje=="100"){
			                texto=caso3;
		                }else{
			                if (resp.respuesta.nombreCategoria == "Cliente Prepago PLUS"){
				                texto=caso2;
			                }else{
			                	texto=caso1;
				            }
		                }
		                $('#mensajeCategoria').html(texto+link);
			               
		                $('#alerta-tabla-reintento-plan2').hide();
		    			$('#loading-tabla-plan2').hide();
		    			$('#contenido-tabla-plan2').fadeIn();
		    			generaResumenPlanCategoria();		    			
                    }else{
                    	showErrorMessages('plan',resp.detalle);
                    }           
	            }
	        });
	}

			function timerDespliegue()
			{			
				$("#porcentaje").val(x);
				$("#valor").html(x+"%");
		//$("#valor").css('color', '#'+rainbow.colourAt(x));0154A0-193C7D
		$("#valor").css('color', '#193C7D');
				$("#porcentaje").trigger('change');
		//$("#porcentaje").trigger('configure', {'fgColor': '#'+rainbow.colourAt(x) }); 
		$("#porcentaje").trigger('configure', {'fgColor': '#193C7D'});
				$("#porcentaje").trigger('change');
				x++;
				if (x>porcentaje) {
					clearInterval(varTimerDespliegue);
					//$("#porcentaje").trigger('configure',{'readOnly': true});						
				}				
			}
				
	function generaResumenPlanCategoria(){	
		//Fix IE
		if (typeof G_vmlCanvasManager !== 'undefined')
		G_vmlCanvasManager.init_(document);
		//End Fix IE
		rainbow.setNumberRange(0, 100);
		rainbow.setSpectrum('red', 'yellow', 'green');
				porcentaje = $("#porcentaje").val();				
                $(".knob").knob({'readOnly':true});
				varTimerDespliegue=setInterval(function(){timerDespliegue()},1000/porcentaje);	
	}

	 var rainbow = new Rainbow(); 
	 var x=0;
	 var porcentaje = 0;
	 var varTimerDespliegue;

	$(document).ready(function() {
		 loadCategoria();
	 });
	 
	</script>
	<!--PLAN-->
	<style>
	.porcen {
		background: none repeat scroll 0 0 transparent;
		border: 0 none;
		/*color: #5CAE00;*/
		color: #193C7D;
		font: bold 18px Arial;
		height: 33px;
		margin-left: -77px;
		margin-top: 33px;
		padding: 0;
		position: absolute;
		text-align: center;
		vertical-align: middle;
		width: 54px;
	}
	</style>
		
	<div class="db-tabla db-tabla-vertical">
		<div>
			<h:messages id="messageList" showSummary="true"
				errorClass="error" />	
		</div>
		<div class="db-tabla-cabecera">
		
			<div class="db-cabecera-top"></div>
			<div class="db-cabecera-cuerpo">
				<div class="db-titulo db-titulo-plan">Categor&iacute;a Cliente</div>
			</div>
		</div>
		<div class="db-tabla-cuerpo">
		<div class="alerta-tabla-reintento" id="alerta-tabla-reintento-plan2"></div>
		     <div id="loading-tabla-plan2" style='background: url("/personas/framework/skins/mientel/img/loading.gif") no-repeat scroll center center rgba(0, 0, 0, 0);display: block;height: 10px;'></div>
             <div id="contenido-tabla-plan2" style="display:none">
             <div class="clearfix" style="margin-bottom:15px;">
				<span id="categoria" class="db-subtitulo-plan" style="text-align:left; padding-bottom: 10px;"></span>			
				<span id="mensajeCategoria" class="db-tabla-texto" style="font-size:12px"></span>
			</div>
			<div class="clearfix">
				<div class="grafico_recargas" style="float:left;width:120px">            
           			<input id="porcentaje" class="knob" data-angleOffset=-125 data-angleArc=250 data-displayInput=false data-width=100 data-height=100>
					<span id="valor" class="porcen"></span>
        		</div>
        		<div class="texto_recargas" style="float:left;width:108px">      			
					<span style="display:block">Recargas Acumuladas:</span>
					<span id="pesosAcumulados" style="display:block"></span>
					<span style="display:block; padding-top: 10px;">Semestre:</span>
					<span id="semestre" style="display:block"></span>
        		</div>
			</div>
			<div class="db-plan-boton-centrado clearfix">
				<a class="btnNaranjaGrande" 
			title="Recargar en linea" 
			href="<r:pageUrl pageLabel='${planController.pageLabelRecargaEnLinea}'></r:pageUrl>">
			<span>Recarga aqu&iacute;</span>
			</a>
			</div>
			</div>
		</div>
		<div class="db-tabla-pie"></div>
	</div>
<!--/PLAN-->

</f:view>
