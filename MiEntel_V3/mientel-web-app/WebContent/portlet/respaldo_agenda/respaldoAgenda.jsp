<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>

<f:view beforePhase="#{respaldoAgendaController.initEquipos}">
<script type="text/javascript">

function abrirRespadoAgenda(){
	var url ='<h:outputText value="#{respaldoAgendaController.url}"/>';
	window.open(url);	
}

$(document).ready(function() {

    $("#condiciones").hide();
    var swCondiciones = false;
    
   $('.proveedor').click(function() {
     
      if(swCondiciones){
          $("#condiciones").hide();
           swCondiciones= false;
      }else{
         $("#condiciones").show(300);
          swCondiciones= true;        
      }      
   });
	
	
  $(":checkbox").click(function() {

	if ($(":checkbox").is(':checked')){	
		    $("#btIngresarActivado").show();
		    $("#btIngresarDesactivado").hide();		    
            $("#condiciones").show(300);            
        }else{     
        	$("#btIngresarActivado").hide();  
        	$("#btIngresarDesactivado").show();             
            $("#condiciones").hide(300);            
        }
	
        if(swCondiciones){	    
	         swCondiciones= false;
	     }else{	       
	         swCondiciones= true;        
	     }  
	
	});

		
});
    
</script>



<h:panelGroup rendered="#{respaldoAgendaController.visible}"> 

	 <h1>Respaldo Agenda</h1>
								
			<div class="clearfix">
			     <cm:search id="descripcion" query="idContenido = 'descripcion_servicio'" useCache="true"  />	  
				 <span class="fondo_imagen_respaldo"><cm:getProperty node="${descripcion[0]}" name="html" /></span>
			</div>											
		
	 <h2 class="movil">Nivel de compatibilidad con el equipo</h2>
	 
		<div class="info_telefono clearfix">
		    <%-- Imagen del equipo --%>
		     <div class="telefono">
		         <img id="tuEquipo-img" src="/personas/image?id='<h:outputText value="#{respaldoAgendaController.numeroPcsSeleccionado}"/>'" alt=""/>		        		     </div>
		     <div class="informacion">
		         <%-- Marca y modelo del equipo --%>
			         <span class="texto_info">
			             <strong>Equipo registrado en la red:</strong><br />                           
			               <h:outputText  value="#{ respaldoAgendaController.equipoFullBean.marca }" />&nbsp;<h:outputText value="#{ respaldoAgendaController.equipoFullBean.modelo}" />                              
			         </span>
			         <%-- Nivel de compatibilidad del equipo --%>
			         <span class="texto_info">
			             <strong>Nivel de compatibilidad:</strong><br />
			             <h:outputText value="#{respaldoAgendaController.msjCompatibidad}"/> 
			         </span>
		      </div>
		 </div>
		 
	<h2 class="titulo_globo">Acceso al servicio</h2>
	
		  <div class="info_proveedor clearfix">
		       <span class="proveedor" style="padding-top: 12px;">Para administrar tu informaci&oacute;n.</span>
		       <div id="btIngresarDesactivado"> 
		          <a id="botonIngresar" class="btnAzulGrandeDesactivado" onclick="javascript:;"/><span style="color:#ffffff">ingresa aqu&iacute;</span></a>
		       </div>
		       <div id="btIngresarActivado" style="display:none"> 
		          <a id="botonIngresar" class="btnAzulGrande" onclick="javascript:abrirRespadoAgenda();"/><span style="color:#ffffff">ingresa aqu&iacute;</span></a>
		       </div>
		       <br/><br/><br/>     
		       <h:selectBooleanCheckbox style="float:left;margin-right: 5px" id="checkbox" value="false" title="Acepto Términos y Condiciones"/> <a href="Javascript:condicionesHeight();" class="proveedor" style="background-image:none;">Acepto T&eacute;rminos y Condiciones</a>
		  </div>
		 <div id="condiciones">
		   <span class="texto_info">
		        <p>	  
				   <cm:search id="descripcion" query="idContenido = 'terminosycondiciones'" useCache="true"  />	  
				   <cm:getProperty node="${descripcion[0]}" name="html" />
				</p>   
		    </span>
		</div>
		<br/>  
</h:panelGroup> 
<jsp:include page="../common/messages_table.jsp"></jsp:include> 
</f:view> 
	 