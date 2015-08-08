<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="preferences" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

<preferences:getPreference name="pageLabel" var="pageLabel"/>

<script type="text/javascript">
	   
	function loadPlan() {		
		
	    var url='<%=request.getContextPath()%>/portlet/plan/resumenPlanSGOJson.faces';

	    setTimeout(function(){
		    		alertaReintento('plan');    
	    	      },TIEMPOREINTENTO);

	    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            success: function (resp){
	                flagRespuestas['plan'] = '1';
                    if(resp.estado == 'Ok'){
	                $('#saldo').html('Saldo: $ '+resp.respuesta.saldo);
    	           
	                if(resp.respuesta.submercadoEMPSGO == true ){
	                	$('#bloqueInfoPlanSgo').show();
		            }
	                
	                if( resp.respuesta.diasExpiracion != 'NONE' ){
	                	$('#fechaExpiracion').html("Expira el:"+resp.respuesta.diasExpiracion);
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
		var param = obtenerParametroURL("contexto");
		if (param == 'saldoRecargosSGO') {
		    //$.scrollTo('#pie-Entel',  1000, {offset:{top:3000}});	   
			//parent.location="#marcaSGO"; 
		}
		loadPlan();
	});
	</script>

	<!-- PLAN -->
	
<div id="bloqueInfoPlanSgo" style="display:none">
	<div>
		<h:messages id="messageList" errorClass="mensajaError" showSummary="true" />
	</div>
	<div class="db-tabla">
		<div class="db-tabla-cabecera">
			<div class="db-cabecera-top"></div>
			<div class="db-cabecera-cuerpo">
				<div class="db-titulo db-titulo-plan">Saldo cuenta personal</div>
			</div>
		</div>
		<div class="db-tabla-cuerpo">
		<div class="alerta-tabla-reintento" id="alerta-tabla-reintento-plan"></div>
		 <div id="loading-tabla-plan"></div>
         <div id="contenido-tabla-plan">
			<span id="nombrePlan" class="db-subtitulo-plan"></span>
			<ul>
				<li class="db_sin_borde clearfix">
					<span id="saldo" style="display: block; float: left; width: 100px;"></span>
					<span id="fechaExpiracion" style="display: block; float: left; text-align: right; width: 118px;"></span>
				</li>
			</ul>
			<div class="db-plan-boton-centrado clearfix">	
			<a class="btnVerdeGrande" 
			title="Recargar en linea" 
			href="<r:pageUrl pageLabel='${pageLabel}'></r:pageUrl>"
			onclick="mxTracker._trackPromo('Caja Saldo','Recarga en Linea');">
			<span>Recarga en l&iacute;nea</span>
			</a>
			</div>
			</div>
		</div>
		<div class="db-tabla-pie"></div>
	</div>
</div>	
	<!-- /PLAN -->	
	 <a name="marcaSGO" id="marcaSGO" ></a>
</f:view>
