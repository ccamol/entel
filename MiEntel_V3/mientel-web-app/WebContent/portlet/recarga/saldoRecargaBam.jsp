<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>



<cm:search id="saldoReservado" query="idContenido = 'saldoReservado'" useCache="false"/>

<f:view>

	<script type="text/javascript">
	
    function loadSaldoRecargaBam(){
    	var url='<%=request.getContextPath()%>/portlet/recarga/saldoRecargaBamJson.faces';
    	setTimeout(function(){
    		alertaReintento('saldoRecargaBam');    
    	      },TIEMPOREINTENTO);
	    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            success: function (resp){            		
                    //Evitar reintento
	    	        flagRespuestas['saldoRecargaBam'] = '1';
	    	        if(resp.estado == 'Ok'){
		                $('#saldo').html('$'+resp.respuesta.saldoFormated);
		                $('#fechaExpiracion').html(resp.respuesta.fechaExpiracionFormated);
	
		                if(resp.respuesta.saldoReservadoMonto == 'error'){
		                	$('#iconoTooltip').hide();
		                	$('#saldoReservadoMonto').html("<strong>No disponible</strong>");
		                	
				        }else{				        	
				        	$('#saldoReservadoMonto').html('$'+resp.respuesta.saldoReservadoMonto);
					    }
		                
	 	    			$('#loading-tabla-saldoRecargaBam').hide();
	 	    			$('#alerta-tabla-reintento-saldoRecargaBam').hide();
	 	    			$('#contenido-tabla-saldoRecargaBam').fadeIn();
	    	        }	           
	            }
	        });

        } 

    $(document).ready(function() {
    	loadSaldoRecargaBam();
	  	});
	</script>
<div class="db-tabla ieFix" id="saldoRecargaBam">
		<div class="db-tabla-cabecera">
			<div class="db-cabecera-top"></div>
			<div class="db-cabecera-cuerpo clearfix">
				<div class="db-titulo db-saldo-recarga">Saldo de recarga</div>
			</div>
		</div>
		<div class="db-tabla-cuerpo">
				<ul>
					<li class="no-borde">
						<div class="clearfix">
							<span class="db-texto-izquierda">Saldo disponible para comprar bolsa</span>
							<span id="saldo" class="db-texto-derecha"></span>
						</div>
					</li>
					<li class="no-borde">
						<div class="clearfix">
							<span class="db-texto-izquierda">Fecha de expiraci&oacute;n</span>
							<span id="fechaExpiracion" class="db-texto-derecha"></span>
						</div>
					</li>
					
					<li class="no-borde">
						<div class="clearfix">
							<span class="db-texto-izquierda">Saldo Reservado</span>
							<a id="iconoTooltip" href="#contenidoTooltip" class="ico_interrogacionNuevo autoTooltip ext" tooltip="Primer tooltip"></a>
							<span id="saldoReservadoMonto" class="db-texto-derecha"></span>
						</div>
					</li>
					
				</ul>
				<a class="btnVerdeGrande"
					title="Recargar" href="<r:pageUrl pageLabel='${planBAMController.pageLabelSaldoRecargaBam}'></r:pageUrl>">
					<span>Recargar</span>
				</a>
			</div>

		<div class="db-tabla-pie"></div>
	</div>
	
	<!-- Contenido del tooltip de saldo reservado  -->
	<div id="contenidoTooltip" style="display:none">
        <cm:getProperty node="${saldoReservado[0]}" name="html"/>
    </div>
	
</f:view>