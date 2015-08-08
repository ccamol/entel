<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>

<f:view>
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
                     
                    $('#numeroDias').html(resp.respuesta.dias);
                    $('#numeroHoras').html(resp.respuesta.horas);
                    $('#numeroMinutos').html(resp.respuesta.minutos);                   
                    if(resp.respuesta.tieneSaldo == false && resp.respuesta.submercado !='FDT'){
                      $('#sinSaldo').show(); 
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
							<div class="db_dato_tiempo clearfix">
								<div class="caja_tiempo">
									<div class="caja_top"></div>
									<div class="caja_middle">
										<span class="numero" id="numeroDias"></span>
										<span class="texto">d&iacute;as</span>
									</div>
									<div class="caja_bottom"></div>
								</div>								
								<div class="caja_tiempo">
									<div class="caja_top"></div>
									<div class="caja_middle">
										<span class="numero" id="numeroHoras"></span>
										<span class="texto">horas</span>
									</div>
									<div class="caja_bottom"></div>
								</div>								
								<div class="caja_tiempo">
									<div class="caja_top"></div>
									<div class="caja_middle">
										<span class="numero" id="numeroMinutos"></span>
										<span class="texto">min</span>
									</div>
									<div class="caja_bottom"></div>
								</div>
							</div>
							<span class="texto_pie_borde" id="sinSaldo" style="display: none;">
								Para navegar debes adquirir una bolsa.<br />
								<a href="<r:pageUrl pageLabel='${suscripcionesController.pageLabelCompraBolsa}'></r:pageUrl>">Comprar bolsa</a>
							</span>
     					   </div>						
						
					   </div>
					   <div class="db_tabla_pie"></div>
					</div>
</f:view>