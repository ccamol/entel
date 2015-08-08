<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<f:view>

<c:set var="urlAplicacion" value="${aplicacionExternaController.urlAplicacion}"/>
<c:set var="indiceApp" value="${aplicacionExternaController.indiceApp}"/>
<c:set var="indiceAppGestioncuenta" value="${aplicacionExternaController.indiceAppGestioncuenta}"/>
<c:set var="indiceAppPagoenlinea" value="${aplicacionExternaController.indiceAppPagoenlinea}"/>
<c:set var="userCodigoAAA" value="${aplicacionExternaController.userCodigoAAA}"/>

<script>
	$(document).ready(function(){
		var url = ('<h:outputText value="#{aplicacionExternaController.url}" escape="false"/>');
		$('#iframeLoadPage').attr('src',url);
	});
</script>

<!-- MENSAJES -->
<jsp:include page="../common/messages_table.jsp"></jsp:include>

<entel:view name="appExternaView">

	<iframe id="iframeLoadPage" allowtransparency="" frameborder="0"  
		scrolling="no" width="600" height="800">
	</iframe>

</entel:view>
<entel:view name="deniedAppExternaView">
	
<cm:search id="infoRestriccionAAA0" query="idContenido = 'infoRestriccionAAA0'" useCache="false"/>
<div class="contenedor-mensajes">
	<ul>
		<li class="mensaje-alerta">
			<div align="center"> 
				<span><cm:getProperty node="${infoRestriccionAAA0[0]}" name="html"/></span>
			</div> 
		</li>
	</ul>
 </div>

</entel:view>

</f:view>