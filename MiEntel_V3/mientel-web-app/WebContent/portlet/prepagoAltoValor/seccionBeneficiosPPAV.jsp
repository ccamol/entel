<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>

<!-- Preference que indica el idContenido para obtener el contenido con el HTML del lightbox -->
<pref:getPreference name="idContenidoSeccion" var="id_contenido" defaultValue="" />

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/prepagoAltoValor/css/entel-fonts.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portlet/prepagoAltoValor/css/style.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/portlet/prepagoAltoValor/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/portlet/prepagoAltoValor/js/stretcher.js"></script>

<!-- Contenido con HTML de la seccion de beneficios Prepago Plus -->
<cm:search id="seccionNode" query="idContenido = '${id_contenido}'" useCache="false" />
<cm:getProperty node="${seccionNode[0]}" name="html" />