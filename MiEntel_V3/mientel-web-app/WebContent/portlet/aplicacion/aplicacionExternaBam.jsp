<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<f:view>

<c:set var="urlAplicacion" value="${aplicacionExternaController.urlAplicacionBam}"/>
<c:set var="indiceApp" value="${aplicacionExternaController.indiceApp}"/>
<script>
	$(document).ready(function(){
		var url = ('<h:outputText value="#{aplicacionExternaController.urlAplicacionBam}"/>').replace("amp;", "");
		$('#iframeLoadPage').attr('src',url);		
	});
</script>

		<iframe 
		id="iframeLoadPage" 
		allowtransparency="" 
		frameborder="0" 
		scrolling="no" 
		width="566px" 
		height="800">
		</iframe>
		
</f:view>