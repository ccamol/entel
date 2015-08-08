<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<cm:search id="infoExitoInscripcion" query="idContenido = 'infoExitoInscripcion'" useCache="true"  />

<f:view>

		<script type="text/javascript">
				$(document).ready(function() {
					/* Se verifica que en la url se encuentre el parametro correspondiente a 
					la inscripcion.*/
		            var inscripcion = <h:outputText value="#{inscripcionController.parametroUrlInscripcion}"/>;
		            if(inscripcion){
		            	$('#inscripcion').find('.mensaje-exito').show();
			        }
			        
		        }); 					
		</script>
		
		<div id="inscripcion">
		    <div class="mensaje-exito" style="width: 533px; margin-left: 14px; display: none;">
		        <div class="clearfix sub-contenedor">
		            <div class="texto">
			            <strong>
			            	<cm:getProperty node="${infoExitoInscripcion[0]}" name="html"/>
			            </strong>
		            </div>
		        </div>
		    </div>
    	</div>
</f:view>