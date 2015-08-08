<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<cm:search id="velocidadPlan" query="idContenido = 'velocidad_plan_pp_bam'" useCache="true"  />
<f:view>
<style>
#cuotaTrafico {
    font-size: 6.1em;
}
#unidadMedicion {
    font-size: 1.3em;
}
#traficoPlan {
    font-size: 1.6em;
}
.textoNaranjo {
    color: #F78E1E;
    font-weight: bold;
}
.caja_cuota_no_div_centro .dato_tiempo .caja_tiempo {
	padding: 0 5px 0 0;
}
</style>
<script type="text/javascript">

 function loadSaldoBolsaPPBAM() {
	    var url='<%=request.getContextPath()%>/portlet/bolsabam/saldoBolsaPPBAMJson.faces';

		    setTimeout(function(){
	   		 alertaReintento('saldoBolsaPPBAM');    
	    	      },TIEMPOREINTENTO);

	    
	    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            success: function (resp){
                  //Evitar reintento
  	              flagRespuestas['saldoBolsaPPBAM'] = '1';

                  if(resp.estado == 'Ok'){
                    if ( resp.respuesta.tieneBolsa){
                    $('#numeroDias').html(resp.respuesta.dias);
                    $('#numeroHoras').html(resp.respuesta.horas);
                    $('#numeroMinutos').html(resp.respuesta.minutos);
                    $('#dashboardNombreBolsa').html(resp.respuesta.nombreBolsa);
                    $('#dashboardTraficoIncluido').html(resp.respuesta.traficoIncluido);
	                    $('#dashboard_TraficoIncluido').html(resp.respuesta.traficoIncluido);
	                    $('#dashboardVelocidadMaxima').html('<cm:getProperty node="${velocidadPlan[0]}" name="html" /> de descarga');//resp.respuesta.velocidadMaxima);
					if (resp.respuesta.tieneSaldo){
	                    	$('#cuotaTrafico').html(resp.respuesta.traficoRestante);
	                    	$('#unidadMedicion').html(resp.respuesta.traficoRestanteUnidad);
                    	$('#graficoTrafico').addClass('grafica_'+resp.respuesta.traficoGrafico);
	                    	$('#dashboardTooltip a').attr('href','#bolsaPPCuota');
					}else{						
							$('#dashboard_cajaCuota').attr('style','height:40px');
							$('#traficoPlan').attr('style','font-size:1.5em');
							$('#cuotaTrafico').html("&nbsp;");
	                    	$('#unidadMedicion').html("");				
                    	$('#graficoTrafico').addClass('grafica_100');
	                    	$('#dashboardTooltip a').attr('href','#bolsaPPSinCuota');
	                    	$('#dashboardTraficoIncluido').html('Sin restricci&oacute;n');
	 	                    $('#dashboard_TraficoIncluido').html('Sin restricci&oacute;n');
						}
                    }else{
                    	$('#dashboardTooltip').html('');
                    	$('#sinBolsa').show();
					}
                   

	    			$('#loading-tabla-saldoBolsaPPBAM').hide();
	    			$('#alerta-tabla-reintento-saldoBolsaPPBAM').hide();

	    			if ($.browser.msie) {
	    				$(".caja_top").each(function() {
	    					$(this).css('padding-top','2px');
	    				});
	    			}
	    			
	    			$('#contenido-tabla-saldoBolsaPPBAM').fadeIn();
	               }else{
	            	   showErrorMessages('saldoBolsaPPBAM',resp.detalle);
		               }	           
	            }
	        });
	}

	 $(document).ready(function() {
		 loadSaldoBolsaPPBAM();
	 });
	 	
	</script>

	<div style="display:none" id="bolsaPPCuota">	
		<p>Esta bolsa tiene una cuota de tr&aacute;fico asociada y  un plazo m&aacute;ximo de navegaci&oacute;n. Una vez consumida la cuota de tr&aacute;fico o alcanzado el plazo m&aacute;ximo para utilizarla (lo que ocurra primero), deber&aacute;s adquirir una nueva bolsa para seguir navegando</p>
	</div>
	<div style="display:none" id="bolsaPPSinCuota">	
		<p>Esta bolsa tiene un plazo m&aacute;ximo de navegaci&oacute;n. Una vez alcanzado dicho plazo, deber&aacute;s adquirir una nueva bolsa para seguir navegando</p>
	</div>
					<div class="db-tabla db-tabla-espacio">
						<div class="db_tabla_cabecera">
							<div class="db_cabecera_top"></div>
							<div class="db_cabecera_cuerpo clearfix">
								<div class="db_titulo db_saldo_recarga">Saldo de bolsa</div>
							</div>
						</div>
						<div class="db_tabla_cuerpo">
												
						<div class="alerta-tabla-reintento" id="alerta-tabla-reintento-saldoBolsaPPBAM"></div>
		                  <div id="loading-tabla-saldoBolsaPPBAM"></div>
		                   <div id="contenido-tabla-saldoBolsaPPBAM">
		                    
						<table cellspacing="0" cellpadding="0" class="traficoPlan" style="font-size:1.1em;height: 200px; padding: 0 0 0 0;">
	<tbody>
		                    
	<tr><td style="padding-top: 5px; text-align: center" colspan="2"><span><strong>Tráfico restante</strong></span></td></tr>

	<tr>
		<td style="padding-top: 5px;" class="col1" rowspan="2">
				<div id="dashboard_cajaCuota" style="text-align: center; ">
				<span id="cuotaTrafico">0</span>
				<span><strong id="unidadMedicion"></strong></span><br>			
				</div>				
									<div class="clearfix">
						<div style="float:left"><span id="traficoPlan">Bolsa <strong id="dashboard_TraficoIncluido" class="textoNaranjo"></strong></span></div>
						<div id="dashboardTooltip" style="float:left">																			
							<a class="ico_interrogacionNuevo autoTooltip" href="#bolsaPPCuota" ></a>																																																					
										</div>
									</div>
					<strong id="dashboardNombreBolsa">No existe bolsa vigente</strong><br>			
					Cuota de tr&aacute;fico incluida: <strong id="dashboardTraficoIncluido">0</strong><br>
					Velocidad: <strong id="dashboardVelocidadMaxima">Sin informaci&oacute;n</strong><br>
								</td>
		<td class="col2">	
			<div class="caja_cuota">
				<div class="contenedor_cuota_dato" style="width:40px">	
					<div id="graficoTrafico" class="grafica_dato grafica_0"></div>
								</div>
							</div>
		</td>
		
	</tr>
	<tr>
		<td rowspan="3">&nbsp;</td>
	</tr>
	
	</tbody>
</table>	
<div style="height:100px;border: 1px solid #DADADA; margin: 0 0px 0 0px">	
							<div style="text-align: center; font-size: 1.5em; font-weight: bold; padding: 5px;">Tiempo de vigencia</div>		                   
		                    <!-- /Edison MOD -->
		                    
							<div class="db_dato_tiempo clearfix" style="padding-top:0;">
							<table>
									<tr>
										<td style="padding-left: 5px;">
								<div class="caja_tiempo" style="padding-left: 0px; padding-right: 0px;">
									<div class="caja_top"></div>
									<div class="caja_middle">
										<span class="numero" id="numeroDias">0</span>
										<span class="texto">d&iacute;as</span>

									</div>
									<div class="caja_bottom"></div>
								</div>
								</td>
								<td>
								<div class="caja_tiempo" style="padding-left: 0px; padding-right: 0px;">
									<div class="caja_top"></div>
									<div class="caja_middle">
										<span class="numero" id="numeroHoras">0</span>
										<span class="texto">horas</span>

									</div>
									<div class="caja_bottom"></div>
								</div>
								</td><td>
								<div class="caja_tiempo" style="padding-left: 0px; padding-right: 0px;">
									<div class="caja_top"></div>
									<div class="caja_middle">
										<span class="numero" id="numeroMinutos">0</span>
										<span class="texto">min</span>

									</div>
									<div class="caja_bottom"></div>
								</div>
								</td>
								</tr>
								</table>
							</div>
							</div>
							<span class="texto_pie_borde" id="sinBolsa" style="display: none;">
								Para navegar debes adquirir una bolsa.<br />
								<a href="<r:pageUrl pageLabel='${suscripcionesController.pageLabelCompraBolsa}'></r:pageUrl>">Comprar bolsa</a>
							</span>
</div>						
							
						
					   </div>
					   <div class="db_tabla_pie"></div>
					</div>
</f:view>
