<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="pref"  uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>

<pref:getPreference name="idContenido" var="idContenido" defaultValue=" " />
<c:set var="query">idContenido = '${idContenido}'</c:set>

<f:view>

<script type="text/javascript">

	function loadTuEquipoBAM(){
		var url='<%=request.getContextPath()%>/portlet/equipo/tuEquipoJson.faces';

		setTimeout(function(){
  			 alertaReintento('tuEquipoBAM');    
   		},TIEMPOREINTENTO);
		
		$.ajax({
			type: 'POST',
			url: url,
			dataType: 'json',
			success: function (data){

				flagRespuestas['tuEquipoBAM'] = '1';
				
				if(data.estado == 'Ok'){

					var marca = data.respuesta.marca;
					var modelo = data.respuesta.modelo;
					var urlImg = '/personas/image?id='+data.respuesta.numeroPcs;

					$('#tuEquipoBAM-marca').text(marca);
					$('#tuEquipoBAM-modelo').text(modelo);
					$('#tuEquipoBAM-img').attr('src',urlImg);	

					$('#loading-tabla-tuEquipoBAM').hide();
					$('#alerta-tabla-reintento-tuEquipoBAM').hide();
	    			$('#contenido-tabla-tuEquipoBAM').fadeIn();
	    			$('.db-ultimo-equipo-lista').fadeIn();															
					
				}
				else{
					showErrorMessages('tuEquipoBAM',data.detalle);
			    }								
				
			}
		});
	}

	$(document).ready(function() {
		loadTuEquipoBAM();
	});
</script>

<div class="cajalinks">
	<div class="cabecera naranja">

		<h1>Tu equipo</h1>
	</div>
	<div class="cuerpo">
	
		<div class="db-ultimo-equipo">
			<div class="alerta-tabla-reintento" id="alerta-tabla-reintento-tuEquipoBAM"></div>
			<div id="loading-tabla-tuEquipoBAM"></div>									
		    <div id="contenido-tabla-tuEquipoBAM">
		    	<p>El &uacute;ltimo equipo registrado en la red es:</p><br>
				<div align="center" class="pngFix"><img id="tuEquipoBAM-img" src="" alt="Ultimo Equipo Registrado" width="110px" height="140px"/></div>
				<p class="db-ultimo-equipo-nombre">
					<strong>									
						<span id="tuEquipoBAM-marca"></span>&nbsp;
						<span id="tuEquipoBAM-modelo"></span>
				    </strong>
				</p>
		    </div>											  		
		</div>
				
		<br>	
		<!-- LISTA DE LINKS --> 		
		    <cm:search id="linkTuEquipoBAM" query="${query}" useCache="false" />	   
            <cm:getProperty node="${linkTuEquipoBAM[0]}" name="html" default=" "/>   
		<!--/ LISTA DE LINKS -->
		
	</div>
	<div class="pie"></div>
</div>
</f:view>
