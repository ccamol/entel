<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>

<script type="text/javascript">
	   
	function loadPlan() {
		
	    var url='<%=request.getContextPath()%>/portlet/plan/resumenPlanJson.faces';
	    
	    setTimeout(function(){
	   		 alertaReintento('plan');    
	    	      },TIEMPOREINTENTO);


	    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            success: function (resp){
                    //Evita reintento
                    flagRespuestas['plan'] = '1'; 
                    if(resp.estado == 'Ok'){
		                $('#nombrePlan').html(resp.respuesta.nombrePlan);
		                $('#saldo').html('$'+resp.respuesta.saldoFormated);
		                $('#fechaExpiracion').html("Expira el: "+resp.respuesta.fechaExpiracionFormated);
	
		                $('#saldoReservado').html('$'+resp.respuesta.saldoReservadoFormated);
		                if(parseInt(resp.respuesta.saldoReservadoFormated)>0){
		                	$('#fechaExpiracionReservado').html("Expira el: "+resp.respuesta.fechaExpiracionSaldoReservadoFormated);
			            }		
		                if(resp.respuesta.prestaLuka){
		                	$('#prestaLuka').html("Tienes una deuda pendiente");
		                }else{
		                	$('#prestaLuka').html("No tienes deuda");
		                }            
		               
		                $('#alerta-tabla-reintento-plan').hide();
		    			$('#loading-tabla-plan').hide();
		    			$('#contenido-tabla-plan').fadeIn();
                    }else{
                    	showErrorMessages('plan',resp.detalle);
                    }           
	            }
	        });
	}

	$(document).ready(function() {
		 loadPlan();
	 });
	 
	</script>
	<!--PLAN-->

	<div class="db-tabla db-tabla-vertical">
		<div>
			<h:messages id="messageList" showSummary="true"
				errorClass="error" />	
		</div>
		<div class="db-tabla-cabecera">
		
			<div class="db-cabecera-top"></div>
			<div class="db-cabecera-cuerpo">
				<div class="db-titulo db-titulo-plan">Saldo</div>
			</div>
		</div>
		<div class="db-tabla-cuerpo">
		<div class="alerta-tabla-reintento" id="alerta-tabla-reintento-plan"></div>
		     <div id="loading-tabla-plan"></div>
             <div id="contenido-tabla-plan">			
			<ul id="tarifa-saldos">
				<li class="db-sin-borde clearfix separador-saldo"style="background:none">
					<span class="db-tabla-texto-izquierda "><strong>Saldo:</strong></span><br/>
					<span id="saldo" class="db-tabla-texto-izquierda"></span><br/>
					<span id="fechaExpiracion"></span>
				</li>
				<li class="db-sin-borde clearfix separador-saldo" style="background:none">
					<span class="db-tabla-texto-izquierda"><strong>Saldo Reservado:</strong></span><br/>
					<span id="saldoReservado"></span>					
					<span class="db-tabla-texto-derecha"><a target="_blank" href="http://personas.entel.cl/PortalPersonas/appmanager/entelpcs/personas?_nfpb=true&_pageLabel=P26200262741321457494091"><u>+ info</u></a></span>
				</li>
				<li class="db-sin-borde clearfix" style="background:none">
					<span class="db-tabla-texto-izquierda db-texto-corto"><strong>Presta Luka:</strong></span><br/>
					<span id="prestaLuka"></span>
					<span class="db-tabla-texto-derecha"><a target="_blank" href="http://personas.entel.cl/PortalPersonas/appmanager/entelpcs/personas?_nfpb=true&_pageLabel=P6000335201253120465281"><u>+ info</u></a></span>
				</li>
			</ul>
			</div>
		</div>
		<div class="db-tabla-pie"></div>
	</div>
<!--/PLAN-->

</f:view>
