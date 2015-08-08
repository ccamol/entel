<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="cm"      uri="http://www.bea.com/servers/portal/tags/content" %>  
<cm:search id="error_aceptar_rechazar" query="idContenido = 'error_aceptar_rechazar'" useCache="true"  />
<cm:search id="texto_tafira_diaria_confirmar" query="idContenido = 'texto_tafira_diaria_confirmar'" useCache="true"  />
<cm:search id="texto_tafira_diaria_final" query="idContenido = 'texto_tafira_diaria_final'" useCache="true"  />
<cm:search id="texto_tafira_diaria_confirmar_rechazarar" query="idContenido = 'texto_tafira_diaria_confirmar_rechazarar'" useCache="true"  />
<cm:search id="texto_tafira_diaria_final_rechazarar" query="idContenido = 'texto_tafira_diaria_final_rechazarar'" useCache="true"  />
<cm:search id="comodin_espacio" query="idContenido = 'comodin_espacio'" useCache="true"  />
<cm:search id="texto_check_ctd" query="idContenido = 'texto_check_ctd'" useCache="true"  />
<f:view beforePhase="#{tarifaDiariaController.init}">
<body>
<script type="text/javascript">
function ocultartodo(){
	$('.nueva-tarifa-im-paso1-botones').hide();
	$('#nueva-tarifa-im-paso2').hide();	
	$('#nueva-tarifa-im-paso3').hide();	
	$('#nueva-tarifa-im-paso2-rechazar').hide();	
	$('#nueva-tarifa-im-paso3-rechazar').hide();
	$('#comodin_espacio').show();		
	return false;
}
// == Aceptar
/*$(".btn-aceptar-info").live("click",function() {
	ocultartodo();
	$('#nueva-tarifa-im-paso2').show();	
	$('#comodin_espacio').hide();
	return false;
});*/

$(".btn-aceptar-info").live("click",function() {
	 ocultartodo();
	 $('.cargandoAceptarRechazar').show();
	 $('#comodin_espacio').show();	
	 aceptarRechazar('1');
	return false;
});

//== Rechazar
/*$(".btn-rechazar-info").live("click",function() {
	ocultartodo();
	$('#nueva-tarifa-im-paso2-rechazar').show();	
	$('#comodin_espacio').hide();
	return false;
});*/

$(".btn-rechazar-info").live("click",function() {
	ocultartodo();
	$('.cargandoAceptarRechazar').show();
	aceptarRechazar('2');	
	return false;
});

$("#cancelar-info").live("click",function() {
	ocultartodo();
	$('.nueva-tarifa-im-paso1-botones').show();		
	return false;
});
$(document).ready(function() {
	$(".aceptoCondiciones").change(function(e) {
		if($("#btn-aceptar-info").hasClass("btnGrisGrande")){
			$("#btn-aceptar-info").removeClass("btnGrisGrande").addClass("btn-aceptar-info").addClass("btnNaranjaGrande");
			$("#btn-rechazar-info").removeClass("btnGrisGrande").addClass("btn-rechazar-info").addClass("btnNaranjaGrande");
		}else{
			$("#btn-aceptar-info").removeClass("btnNaranjaGrande").removeClass("btn-aceptar-info").addClass("btnGrisGrande");
			$("#btn-rechazar-info").removeClass("btnNaranjaGrande").removeClass("btn-rechazar-info").addClass("btnGrisGrande");
		}      
	});
});


var url='<%=request.getContextPath()%>/portlet/plan/aceptarRechazarTarifaDiariaJson.faces';
var pnm='<h:outputText value="#{tarifaDiariaController.productoNavegacionMovil}"  />';
function aceptarRechazar(accion){	
	$.ajax({
		type: 'POST',
		data : 'accion='+accion+'&pnm='+pnm+'&r='+Math.random(),
		url: url,
		async: true, 		
		dataType: 'json',		
		success: function (resp){
			if(resp.estado == 'Ok'){	
				  if(accion=='1'){
					   $('#nueva-tarifa-im-paso3').show();	
				   }else{
					   $('#nueva-tarifa-im-paso3-rechazar').show();
				   }				
				       $('.cargandoAceptarRechazar').hide();	
			   }else{
				$('.error_aceptar_rechazar').show();
				$('.cargandoAceptarRechazar').hide();
			}	   		
		}
	});		
}

</script>

<div class="clearfix nuevaFormaMovil" align="justify">     
          <div class="contenedor_central" id="centro" >
	          <div id="nueva-tarifa-im-paso1">
                   <h:outputText value="#{tarifaDiariaController.contenido }" escape="false" />                  
			       <h:panelGroup rendered="#{tarifaDiariaController.mostrarAceptarRechazar}">			       
				       <div id="caja-alternativas" class="nueva-tarifa-im-paso1-botones">				           
					             <div class="condicionesCheck">           
					                 <center><input type="checkbox" class="aceptoCondiciones" />
					                    <label style="font-size:12px;"><cm:getProperty node="${texto_check_ctd[0]}" name="html" /></label>				                    
					                  </center>  
					                  <br><br>
					             </div>  					        
				            <div class="alternativas">
				                <a class="btnGrisGrande" id="btn-aceptar-info" href="javascript:;"  ><span style="width:100px;text-align:center;">Aceptar</span></a>
				            </div>
				            <div class="alternativas ultima">
				                <a class="btnGrisGrande" id="btn-rechazar-info" href="javascript:;"><span style="width:100px;text-align:center;">Rechazar</span></a>
				            </div>
				        </div>
			        </h:panelGroup>	
			        <h:panelGroup rendered="#{!tarifaDiariaController.mostrarAceptarRechazar}">				           	       
				       <h:outputText value="#{tarifaDiariaController.contenidoAceptadaRechazada}" escape="false" />
				     </h:panelGroup>	
			   </div> 
			 	  	 	
	  	 	<!-- MENSAJES -->
	  	 	<div align="center">
	          <jsp:include page="../common/messages_table.jsp"></jsp:include>
	        </div>        
       </div>
       
		<div id="nueva-tarifa-im-paso2" style="display:none;" >
		    	<cm:getProperty node="${texto_tafira_diaria_confirmar[0]}" name="html" /> 					        
		        <div id="caja-alternativas">
		            <div class="alternativas">
		                <a class="btnNaranjaGrande" id="btn-confirmar-info" href="javascript:;"><span style="width:100px;text-align:center;">Confirmar</span></a>
		            </div>
		            <div class="alternativas ultima">
		                <a id="cancelar-info" href="javascript:;">Cancelar</a>
		            </div>
		         </div>
		 </div>					    
		 <div id="nueva-tarifa-im-paso3" style="display:none;">
		    	<cm:getProperty node="${texto_tafira_diaria_final[0]}" name="html" /> 
		  </div>	
		  
		  <div id="nueva-tarifa-im-paso2-rechazar" style="display:none;">
		    	<cm:getProperty node="${texto_tafira_diaria_confirmar_rechazarar[0]}" name="html" /> 					        
		        <div id="caja-alternativas">
		            <div class="alternativas">
		                <a class="btnNaranjaGrande" id="btn-confirmar-info-rechazar" href="javascript:;"><span style="width:100px;text-align:center;">Confirmar</span></a>
		            </div>
		            <div class="alternativas ultima">
		                <a id="cancelar-info" href="javascript:;">Cancelar</a>
		            </div>
		         </div>
		 </div>					    
		 <div id="nueva-tarifa-im-paso3-rechazar" style="display:none;">
		    	<cm:getProperty node="${texto_tafira_diaria_final_rechazarar[0]}" name="html" /> 
		 </div>
	  	 <div class="cargandoAceptarRechazar" style="display:none" >	
	  	      <div id ="comodin_espacio">
	                      <cm:getProperty node="${comodin_espacio[0]}" name="html" /> 
	          </div>   	     		  	 
			  <center>
			  	   <img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'  style="position: relative"/>
			  </center>
		      <div id ="comodin_espacio">
	                      <cm:getProperty node="${comodin_espacio[0]}" name="html" /> 
	          </div> 
	          <br></br><br> 	 
	  	 </div>	  	 
	  		  	 
</div> 
	      <div class="error_aceptar_rechazar" style="display:none" >	      
		       <div class="caja amarilla margen">
					<center><h6><cm:getProperty node="${error_aceptar_rechazar[0]}" name="html" /></h6></center>
				</div>                              	
          </div>      	
</body>
</f:view>