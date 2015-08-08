<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content"
	prefix="cm"%>

<script type="text/javascript">
		var urlActualizaDatosBUIC = '<%=request.getContextPath()%>/portlet/administracion_servicios/servicios/mobileMarketing/actualizaDatosBUIC.faces';
		var urlCrearSuscMMKT = '<%=request.getContextPath()%>/portlet/administracion_servicios/servicios/mobileMarketing/creaSuscMMKT.faces';
</script>

<f:view beforePhase="#{administracionServicios.initCatalogoSAGEN}">
	
	<h:panelGroup>
		<br />
		<br />
		<div class="clearfix" id="serv_mm_estado_no_inscrito">
			<jsp:include page="/portlet/administracion_servicios/servicios/mobileMarketing/actualizacionDatos.jsp"></jsp:include>		
			<jsp:include page="/portlet/administracion_servicios/servicios/mobileMarketing/seleccionInteres.jsp"></jsp:include>
			<jsp:include page="/portlet/administracion_servicios/servicios/mobileMarketing/seleccionHorario.jsp"></jsp:include>
		</div>
	</h:panelGroup>
</f:view>