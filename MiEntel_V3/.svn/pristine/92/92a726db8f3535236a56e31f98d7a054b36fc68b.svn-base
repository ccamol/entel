<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>

<f:view>
	<script type="text/javascript">
		function loadSaldo() {
			var url='<%=request.getContextPath()%>/portlet/bolsabam/saldoFDTJson.faces';

			setTimeout(function() {
	 			alertaReintento('saldoFDT');    
	      	},TIEMPOREINTENTO);

		    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            success: function(resp) {	
                	if (resp.estado == 'Ok') {
                        //Evitar reintento
						flagRespuestas['saldoFDT'] = '1';
						                    	
                		$('.db-texto-izquierda').html("<strong>$" + resp.respuesta.saldo + "</strong>");
						$('.db-texto-derecha').html("Expira el: " + resp.respuesta.fechaExpiracion);

		    			$('#loading-tabla-saldoFDT').hide();
		    			$('#alerta-tabla-reintento-saldoFDT').hide();
		    			$('#contenido-tabla-saldoFDT').fadeIn();												
                	} else {
                		showErrorMessages('saldoFDT', resp.detalle);
                   	}	
	            }
            });	
		}

		$(document).ready(function() {
			loadSaldo();
		});
	</script>

	<div class="db-tabla">
		<div class="db-tabla-cabecera">
			<div class="db-cabecera-top"></div>
			<div class="db-cabecera-cuerpo">
				<div class="db-titulo db-titulo-saldo">Saldo</div>
			</div>
		</div>
		<div class="db-tabla-cuerpo">
			<div class="alerta-tabla-reintento" id="alerta-tabla-reintento-saldoFDT"></div>
			<div id="loading-tabla-saldoFDT"></div>
			<div id="contenido-tabla-saldoFDT">
				<ul>
					<li class="db_sin_borde clearfix">
						<span class="db-texto-izquierda db-texto-corto"></span>
						<span class="db-texto-derecha"></span>
					</li>
				</ul>
				<span>&nbsp;</span>
				<div class="boton-centrado clearfix">				
					<a class="btnVerdeGrande" href="<r:pageUrl pageLabel='${saldoBAMController.pageLabelRecarga}'></r:pageUrl>"><span>Recarga en línea</span></a>
				</div>
			</div>
		</div>
		<div class="db-tabla-pie"></div>
	</div>
</f:view>