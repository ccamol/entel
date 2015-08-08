<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content"%>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>

<pref:getPreference name="pageLabelQualtrics" var="pageLabelQualtrics" />

<f:view beforePhase="#{marcaQualtricsController.generarMarca}">
	
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript">
	
		function generarMarcaQualtrics(){	
				var url='<%=request.getContextPath()%>/portlet/dashboard/marcas/qualtricsJson.faces';
				$.ajax( {
					type : 'POST',
					url : url,	
					dataType:'json',
					timeout:100000,
					beforeSend: function(obj){
						//$("#site-intercept-data").html("Cargando...");
					},		
					success : function(data) {
						if(data.estado == 'Ok'){
							$('#movilQ').val(data.respuesta.movil);
							$('#mercadoQ').val(data.respuesta.mercado);
							$('#segmentoQ').val(data.respuesta.segmento);
							$('#servicioQ').val("${pageLabelQualtrics}");
						}
				    },
				    error: function(data, strError){
					   	$('#movilQ').val("No hay informacion disponible");
						$('#mercadoQ').val("No hay informacion disponible");
						$('#segmentoQ').val("No hay informacion disponible");
						$('#servicioQ').val("No hay informacion disponible");
				    }
			   });
		}
		
	</script>			
	</head>
		<body>
			<div id="site-intercept-data">
				<h:outputText value="#{marcaQualtricsController.marcaQualtricsParams.contenidoScript}" escape="false"/>
				<input id="movilQ" type="hidden" value="<h:outputText value='#{marcaQualtricsController.marcaQualtricsParams.movil}'/>" />
				<input id="mercadoQ" type="hidden" value="<h:outputText value='#{marcaQualtricsController.marcaQualtricsParams.mercado}'/>" />
				<input id="segmentoQ" type="hidden" value="<h:outputText value='#{marcaQualtricsController.marcaQualtricsParams.segmento}'/>" />
				<input id="servicioQ" type="hidden" value="${pageLabelQualtrics}" />
			</div>
		</body>
	</html>

</f:view>