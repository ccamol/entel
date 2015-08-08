<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view beforePhase="#{planController.obtenerHistorialComunik2}">  
<h:panelGroup rendered="#{planController.comunik2}">    
  <div class="historialComunik2">	
  <h3 class="title_historial_comunik2" style="font-weight:normal"><a href="javascript:;" class="expandirTablaHistorial">Ver Historial Tarifa Comunik2</a></h3>
     <div class="linea-azul"></div>
     <div style="display:none;" id="histoial_comunik2">
        
		 <table class="tabla-azul" style="margin-top: 3px" >
		 	 <tbody>
                <tr class="cabecera">
                    <th width="60">N&deg; EPCS</th>	                        
                    <th width="85">Invitaci&oacute;n</th>
                    <th width="220">Fecha</th>
                    <th width="">Respuesta</th>
                </tr>
			  <h:panelGroup rendered="#{planController.listcomunik2Bean!= null}">						        
				   <it:iterator var="itemHistorial" value="#{planController.listcomunik2Bean}" rowIndexVar="indexHistotirial">
				     <c:set var="style" value="#{indexHistotirial % 2 == 0 ? 'impar': 'par'}" scope="page" />	
					   <!-- fila normal -->
		                <tr class="<h:outputText value="#{style}"/> objetoTabla">
		                    <td><h:outputText value="#{itemHistorial.numeroPcs}"/></td>
		                    <td><h:outputText value="#{itemHistorial.tipoInvitacion}"/></td>
		                    <td>
		                        <h:outputText value="#{itemHistorial.fecha}"> 
		                        <f:convertDateTime pattern="dd/MMMM/yyyy - hh:mm" locale="es" />
		                        </h:outputText>
		                        hrs
		                    </td>
		                    <td><h:outputText value="#{itemHistorial.respuesta}"/></td>
		               </tr>			                				                   
			      </it:iterator>			            			            				
			   </h:panelGroup>					    
			 </tbody>
		  </table>
		</div>  
	</div>
	<br></br>
<jsp:include page="../common/messages_table.jsp"></jsp:include>
</h:panelGroup>	

<script type="text/javascript">

		$("a.expandirTablaHistorial").click(function(){			
			if($("a.expandirTablaHistorial").hasClass("cerrado")){
				$(this).removeClass("cerrado").addClass("abierto");
				$('#histoial_comunik2').hide();	
			}else{
				$(this).removeClass("abierto").addClass("cerrado");	
				$('#histoial_comunik2').show();
			}
		});
</script>
	
</f:view>