<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="pref" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/render" prefix="r"%>

<pref:getPreference name="pageLabel"  var="pageLabel"/>
<cm:search id="contenidoPortabilidadEntel" query="idContenido = 'contenidoPortabilidadEntel'" useCache="true"  />
<cm:search id="contenidoPortabilidadOtraCia" query="idContenido = 'contenidoPortabilidadOtraCia'" useCache="true"  />

<script type="text/javascript">
function cargaEstadoEquipo() {
	var urlEstadoEquipo = '<%=request.getContextPath()%>/portlet/equipo/cajaEstadoEquipoJson.faces';
	
	$.ajax({
		type: 'POST',
		url: urlEstadoEquipo, 
		dataType: 'json',
		success: function(resp) {
			if (resp.estado == 'Ok') {
				if (resp.respuesta.tipoBloqueo != '') {
					 $('.toolPpBox').fadeIn();
					 $('.firstPpItem').fadeIn();
					 var motivoText='';
						 if(resp.respuesta.tipoBloqueo=='5000' || resp.respuesta.tipoBloqueo=='5005' || resp.respuesta.tipoBloqueo=='5008')
							 motivoText = 'Robo';
						 if(resp.respuesta.tipoBloqueo=='5002' || resp.respuesta.tipoBloqueo=='5006' || resp.respuesta.tipoBloqueo=='5009')
							 motivoText = 'Hurto';
						 if(resp.respuesta.tipoBloqueo=='5001' || resp.respuesta.tipoBloqueo=='5007' || resp.respuesta.tipoBloqueo=='5010')
							 motivoText = 'Extrav&iacute;o';
													
						    if (resp.respuesta.tipoBloqueo=='5000' || resp.respuesta.tipoBloqueo=='5001' || resp.respuesta.tipoBloqueo=='5002'){
						    	$('#motivo1').fadeIn();
						    	$('#motivo1 .toolLink a').html(motivoText);  
						    					            
				            }else if (resp.respuesta.tipoBloqueo=='5003' || resp.respuesta.tipoBloqueo=='5004'){
						    	$('#motivo2').fadeIn();
							    	if(resp.respuesta.tipoBloqueo=='5003'){
							    		$('.toolMeta').html('<cm:getProperty node="${contenidoPortabilidadOtraCia[0]}" name="html" />');	
							    		$('#motivo2 .toolLink a').html('Portabilidad'); 
							    	}else{
							    		$('.toolMeta').html('<cm:getProperty node="${contenidoPortabilidadEntel[0]}" name="html" />');
							    		$('#motivo2 .toolLink a').html('Deuda'); 
							    	}						    
				            }
				            else if (resp.respuesta.tipoBloqueo=='5005' || resp.respuesta.tipoBloqueo=='5006' || resp.respuesta.tipoBloqueo=='5007' || 
						               resp.respuesta.tipoBloqueo=='5008' || resp.respuesta.tipoBloqueo=='5009' || resp.respuesta.tipoBloqueo=='5010'){
						    	$('#motivo1').fadeIn();
					    	    $('#motivo1 .toolLink a').html(motivoText); 
					    	    $('.txtCenter').fadeIn();
						    	$('#motivo2').fadeIn();	
						    	  if(resp.respuesta.tipoBloqueo=='5005' || resp.respuesta.tipoBloqueo=='5006' || resp.respuesta.tipoBloqueo=='5007'){
						    		  $('.toolMeta').html('<cm:getProperty node="${contenidoPortabilidadOtraCia[0]}" name="html" />');
						    		  $('#motivo2 .toolLink a').html('Portabilidad');
				                  }else{
						    		  $('.toolMeta').html('<cm:getProperty node="${contenidoPortabilidadEntel[0]}" name="html" />'); 
						    		  $('#motivo2 .toolLink a').html('Deuda');  					    	
				                 }
						    }								

						if (resp.respuesta.tieneBloqueoFactura == '1')// 1:Para Bloqueo por Factura Impaga	
							 $('.PpItem').fadeIn();	
						else 
							$('.firstPpItem').css('border-bottom','0px');	 						
						 	
				  }else{
					if (resp.respuesta.tieneBloqueoFactura == '1'){// 1:Para Bloqueo por Factura Impaga	
						  $('.toolPpBox').fadeIn();
						  $('.PpItem').fadeIn();	
						  $('.toolPpBox .cuerpo .PpItem').css('margin-top','3px');	
					} 
				}
			}	
	    } 
	});

}

$(document).ready(function() {	
 
	cargaEstadoEquipo();
});
</script>
<div class="cajalinks toolPpBox" style="display:none;">
  <div class="cabecera morada">
	<h1>Estado de <br>servicio</h1>
  </div>
  <div class="cuerpo">
	<ul>	
	   <li class="firstPpItem" style="display:none;display:inline-block;">Tu servicio se encuentra <strong>Bloqueado</strong> por:<br>
		  <span class="wrapToolLinks">
			  <div class="wrapComponent fLeft" style="display:none;" id="motivo1"><span  class="toolLink"><a href="<r:pageUrl pageLabel="${bloqueoDesbloqueoEquipoController.pageLabelBloqueoDesbloqueo}"></r:pageUrl>&contexto=bloqueo"></a></span></div>
                <div class="fLeft txtCenter"   style="display:none;">/</div>                         	
                  <div class="wrapComponent fLeft" style="display:none;" id="motivo2">
                    <div class="icoTool"> 
                         <span class="toolArrowTop"></span>                        
                         <span class="toolMeta"></span>
                         <span class="toolLink" ><a href="#"></a></span> 
                    </div>
                  </div>
           <!--End Tooltip-->  
		 </span>
	 </li>
	 <li class="PpItem" style="display:none;">Tu servicio se encuentra <strong>Suspendido</strong> por: <br>
		<span class="wrapToolLinks">
		    <div class="wrapComponent">                                
                  <span class="toolLink"><a href="<r:pageUrl pageLabel="${bloqueoDesbloqueoEquipoController.pageLabelPagoEnLinea}"></r:pageUrl>">Deuda</a></span>
            </div>
        </span>
	 </li>							
   </ul>
 </div>
<div class="pie"></div>
</div>

