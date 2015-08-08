<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<c:set var="query">idContenido = 'informacionVelocidad'</c:set>

<f:view>

	<script type="text/javascript">
	
    function loadTraficoBam(){
    	var url='<%=request.getContextPath()%>/portlet/trafico/traficoBamJson.faces';
    	setTimeout(function(){
    		alertaReintento('traficoBam');    
    	      },TIEMPOREINTENTO);
	    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            success: function (resp){
                    //Evitar reintento
	    	        flagRespuestas['traficoBam'] = '1';
	    	        if(resp.estado == 'Ok'){
		                $('#fechaRegistro').html(resp.respuesta.fechaRegistroFormated);
		                
	                    $('#cuotaUmbralFairUSe').html(resp.respuesta.umbralFairUse);
	                    $('#unidadFairUse').html(resp.respuesta.unidadUmbralFairUse);

		                $('#cuotaRestante').html(resp.respuesta.cuotaRestante);
		                
		                
	 	    			$('#loading-tabla-traficoBam').hide();
	 	    			$('#alerta-tabla-reintento-traficoBam').hide();
	 	    			$('#contenido-tabla-traficoBam').fadeIn();
	    	        }else{
	    	             showErrorMessages('traficoBam',resp.detalle);
	    	        }	           
	            }
	        });

        } 

    $(document).ready(function() {
		loadTraficoBam();
	  	});
	</script>

	<div class="db-tabla">
		<div class="db-tabla-cabecera">
			<div class="db-cabecera-top"></div>
			<div class="db-cabecera-cuerpo clearfix">
				<div class="db-titulo db-titulo-trafico">Tr&aacute;fico</div>
				<div id="fechaRegistro" class="db-titulo-derecha"></div>
			</div>
		</div>
		<div class="db-tabla-cuerpo">
			<div class="alerta-tabla-reintento" id="alerta-tabla-reintento-traficoBam"></div>
			<div id="loading-tabla-traficoBam"></div>
		    <div id="contenido-tabla-traficoBam">
	
					<ul class="borde">
						<li class="no-borde">
							<div class="clearfix">
								<span class="db-texto-izquierda">Cuota contratada</span>
								<span id="unidadFairUse"  class="db-tabla-texto-derecha"></span>
								<span id="cuotaUmbralFairUSe" class="db-tabla-texto-derecha"></span>
							</div>
						</li>
		
						<li class="no-borde">
							<div class="clearfix">
								<span class="db-texto-izquierda">Restante</span>
								<span id="cuotaRestante" class="db-tabla-texto-derecha"></span>
							</div>
						</li>
					</ul>
				
					<span class="texto-pie">
						<cm:search id="infoDisclaimer" query="${query}" useCache="false"/><cm:getProperty node="${infoDisclaimer[0]}" name="html"/>
						<a href="<r:pageUrl pageLabel='${traficoBamController.pageLabelTraficoBam}'></r:pageUrl>">
							<span>Ver tr&aacute;fico</span>
						</a>
					</span>
			</div>
		  </div>
		<div class="db-tabla-pie"></div>
	</div>
</f:view>