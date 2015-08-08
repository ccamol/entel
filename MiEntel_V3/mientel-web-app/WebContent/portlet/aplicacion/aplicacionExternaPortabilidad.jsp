<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="pref" %>
<f:view>
<pref:getPreference name="app" var="origen"/>

	<script>
		$(document).ready(function(){
			var app ='${origen}';
			if(app=="consulta"){
				$('#iframeLoadPage').css( {
					'margin-left': "-14px" 
				 });  				 
			}else if(app=="solicitud"){
				$('#iframeLoadPage').css( {
					'margin-left': "-15px",
					'margin-top': "-25px" 
				 });				
			}						
			var url = ('<h:outputText value="#{aplicacionExternaController.urlPortabilidad}"/>').replace("amp;", "");
			$('#iframeLoadPage').attr('src',url);						
		});
	</script> 
	
    <div id="imgEspera" style="top: 70px;  position: relative;"></div>
   	
   <iframe  id="iframeLoadPage"  frameborder="0"  
	scrolling="no" width="600" height="800" style="z-index: 1">
   </iframe>

   <jsp:include page="../common/messages_table.jsp"></jsp:include>

</f:view>	
		
