<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="pref" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<pref:getPreference name="pageLabel" var="pageLabel"/>
<pref:getPreference name="tipo_caja"  var="tipo_caja"/>
<pref:getPreference name="idContenido" var="id_contenido" defaultValue="" />
<cm:search id="nodo" query="idContenido = '${id_contenido}'" useCache="false"  />
<f:view>
<c:choose>
  <c:when test="${tipo_caja == 0}">	
	<script type="text/javascript">
	//<![CDATA[
	     $(document).ready(function() {

	    	 $("div#ImgCargandoCajaOferta").css( {
					'background-image':"url(../framework/skins/mientel/img/loading.gif)"	
			 });
	         var mostraOferta = '<h:outputText value="#{ofertaBlindajeController.mostrarOferta}" />';
	         
	         if(mostraOferta=='OK')
	        	 $('#caja_oferta_visa').show();
	         else
	        	 $('#caja_oferta_visa').hide();
	    	
	    	$('#nombre_usuario_visa').html('<h:outputText value="#{ofertaBlindajeController.nombreUsuario }" /> ');   
	    	$('#estado_oferta_visa').html('<h:outputText value="#{ofertaBlindajeController.nombreEstadoOferta }"/>!!');  
	        
	     });
	//]]>  
	</script>		
	<div id="caja_oferta_visa" style="display:none">	
		<div class="cajalinks clearfix">		    
			<div class="cabecera  cabecera_nueva_azul clearfix">
				<h1><cm:getProperty node="${nodo[0]}" name="titulo" /></h1>
			</div>
			<div class="cuerpo">			
			   <a href="<r:pageUrl pageLabel='${pageLabel}'></r:pageUrl>" style="text-decoration:none">
				  <cm:getProperty node="${nodo[0]}" name="html" />
			   </a>
			</div>
			<div class="pie"></div>
		</div>
	</div>	   
  </c:when>
  <c:otherwise>
     <div class="cajalinks">		    
			<div class="cabecera  cabecera_nueva_azul clearfix">
				<h1><cm:getProperty node="${nodo[0]}" name="titulo" /></h1>
			</div>
			<div class="cuerpo">			
				<cm:getProperty node="${nodo[0]}" name="html" />
			</div>
			<div class="pie"></div>
		</div>		
   </c:otherwise>
</c:choose>
</f:view>