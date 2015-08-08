<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="preferences" %>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<f:view>
<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-WB3K" height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<script>	 
	// Nombre Logico Pagina
	<h:outputText value="#{googleTagManagerController.mxContent}" rendered="#{not empty googleTagManagerController.mxContent}" />
	
	// Segmento Tipo Contrato
	<h:outputText value="#{googleTagManagerController.mxSeg1}" rendered="#{not empty googleTagManagerController.mxSeg1}" />
	// Segmento AAA
	<h:outputText value="#{googleTagManagerController.mxSeg2}" rendered="#{not empty googleTagManagerController.mxSeg2}" />
	// Segmento Identificador Cliente
	<h:outputText value="#{googleTagManagerController.mxSeg3}" rendered="#{not empty googleTagManagerController.mxSeg3}" />
	// Segmento Comercial RUT
	<h:outputText value="#{googleTagManagerController.mxSeg5}" rendered="#{not empty googleTagManagerController.mxSeg5}" />
	// Segmento CVM
	<h:outputText value="#{googleTagManagerController.mxSeg7}" rendered="#{not empty googleTagManagerController.mxSeg7}" />	
	// Segmento Contexto
	<h:outputText value="#{googleTagManagerController.mxSeg8}" rendered="#{not empty googleTagManagerController.mxSeg8}" />		
	// Segmento Multilinea
	<h:outputText value="#{googleTagManagerController.mxSeg9}" rendered="#{not empty googleTagManagerController.mxSeg9}" />
    
	(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
	new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
	j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
	'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
	})(window,document,'script','dataLayer','GTM-WB3K');
</script>
</f:view>
