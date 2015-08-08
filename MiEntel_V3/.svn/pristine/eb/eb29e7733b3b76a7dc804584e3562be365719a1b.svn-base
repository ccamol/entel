<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>

<f:view>

<cm:search id="infoVelocidad2G3G4G" query="idContenido = 'infoVelocidad2G3G4G'" useCache="false"/>


<script type="text/javascript">

 function loadTraficoBAMCC() {
	    var url='<%=request.getContextPath()%>/portlet/trafico/traficoBAMCCJson.faces';

		    setTimeout(function(){
	   		 alertaReintento('traficoBAMCC');    
	    	      },TIEMPOREINTENTO);
    
	    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            success: function (resp){
                  //Evitar reintento
  	              flagRespuestas['traficoBAMCC'] = '1';

                  if(resp.estado == 'Ok'){
                     
                      $('#cantidadRestante').html(resp.respuesta.cuotaRestante);
					  
					  if (resp.respuesta.umbralFairUse == undefined || resp.respuesta.unidadUmbralFairUse == undefined) {
						  $('#loading-tabla-traficoBAMCC').hide();
						  $('#alerta-tabla-reintento-traficoBAMCC').html('Informacion de trafico no disponible');						  
						  $('#alerta-tabla-reintento-traficoBAMCC').show();
						  return;						  
					  }
					  
					  $('#numeroFairUse').html(resp.respuesta.umbralFairUse);
                      $('#unidadFairUse').html(resp.respuesta.unidadUmbralFairUse);
                      $('#imagenBarra').attr("class","img_barras barras"+resp.respuesta.porcentajeCuotaRestante);
                      if(resp.respuesta.ilimitado == true){
                      $('#numeroFairUse').html("&nbsp;");
                      $('#imagenBarra').attr("style","margin-left:-30px");
                        if(resp.respuesta.saldoMonto != ""){
                           $('#tituloCantidadRestante').html("Saldo&nbsp;recarga");
                           $('#cantidadRestante').html("$"+resp.respuesta.saldoMonto);
                         }else{
                           $('#textopie').show();
                         }
                         }
                      $('#imagenBarra').show(); 
                      

	    			$('#loading-tabla-traficoBAMCC').hide();
	    			$('#alerta-tabla-reintento-traficoBAMCC').hide();
	    			$('#contenido-tabla-traficoBAMCC').fadeIn();
	               }else{
	            	   showErrorMessages('traficoBAMCC',resp.detalle);
		               }	           
	            }
	        });
	}

	 $(document).ready(function() {
		 $('#tooltipVelocidad').mouseover(function (event) {
			 if(!$('#tooltipVelocidad').hasClass('activo')){
				event.preventDefault();
				$('#tooltipVelocidad').click();
			 }	 
		  });
		  		 
		 loadTraficoBAMCC();
	 });
	 	
</script>

<div class="db-tabla db-tabla-espacio">
	<div class="db_tabla_cabecera">
		<div class="db_cabecera_top"></div>
		<div class="db_cabecera_cuerpo clearfix">
			<div class="db_titulo db_saldo_recarga">Tr&aacute;fico disponible</div>
		</div>
	</div>
	<div class="db_tabla_cuerpo">	
		<div class="alerta-tabla-reintento" id="alerta-tabla-reintento-traficoBAMCC"></div>
        <div id="loading-tabla-traficoBAMCC"></div>
        <div id="contenido-tabla-traficoBAMCC">
			<div class="info_trafico_restante clearfix">
             	<div class="numero_dato">
        			<span class="numero" id="numeroFairUse"></span>
        			<span class="tasa_transfer" id="unidadFairUse"></span>
          		</div>
				<div id="imagenBarra" class="img_barras barras" style="margin:0 0 0 10px;"></div>
				<div class="imfo_tasa_transfer">
					<span id="tituloCantidadRestante"><strong>Restante</strong></span><br />
					<span id="cantidadRestante"></span>
				</div>
			</div>	
			
			<!-- informacion de velocidad -->
			<span id="textopie" class="texto_pie_borde" style="margin-top: 5px">
				<a id="tooltipVelocidad" href="#contenidoTooltipVelocidad" class="ico_interrogacionNuevo autoTooltip ext tooltip2" tooltip="Primer tooltip" style="float:right"></a>
				<cm:getProperty node="${infoVelocidad2G3G4G[0]}" name="titulo"/>
			</span>
									
			<span id="textopie" class="texto_pie_borde" style="display: none;">
				Una vez consumido el tr&aacute;fico incluido, podr&aacute;s recargar para seguir navegando.
			</span>
 		</div>
	</div>
	<div class="db_tabla_pie"></div>
</div>

<!-- Contenido del tooltip de info de velocidad -->
<div id="contenidoTooltipVelocidad" style="display:none">
     <cm:getProperty node="${infoVelocidad2G3G4G[0]}" name="html"/>
</div>	


</f:view>