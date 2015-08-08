<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>

<pref:getPreference name="saldoMinimo" var="saldoMinimo"/>
<pref:getPreference name="fechaInicioPromo" var="fechaInicioPromo"/>
<pref:getPreference name="fechaFinPromo" var="fechaFinPromo"/>

<f:view>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<script type="text/javascript">						
				$(document).ready(function() {
				    var parametros = {"saldoMinimo" : '${saldoMinimo}', 
						    		  "fechaInicioPromo" : '${fechaInicioPromo}', 
						    		  "fechaFinPromo" : '${fechaFinPromo}'};
					var url = '<%=request.getContextPath()%>/portlet/recarga/TB_recargaPPJson.faces';
					$.ajax({
						type: 'POST',
						url: url,	
						data: parametros,
						dataType: 'json',		
						success: function(data) {
							if (data.estado == 'Ok') {
								if ($.browser.msie) {
									$('.thickbox').click();
								} else {							
									$('.enlacePromo').fancybox({
									    'overlayOpacity': 0.5,
									    'overlayColor': '#000000',
									    'hideOnContentClick': false,
									    'hideOnOverlayClick': false,
									    'showCloseButton': false,
									    'padding': 'auto',
									    'scrolling': 'no',
									    'frameWidth': 650,
									    'frameHeight': 530   
									});
									
									$('.enlacePromo').click();								
								}
							}
						}
					});
				});
	
				function cerrar() {
					if ($.browser.msie) {
						tb_remove();
					} else {						
						$.fn.fancybox.close();
					}
				}
	
				function noMostrar() {
					var url = '<%=request.getContextPath()%>/portlet/recarga/noMostrarRecargaPPJson.faces';
					$.ajax({
						type: 'POST',
						url: url,
						dataType: 'json',		
						success: function(data) {
							if (data.estado == 'Ok') {
								if ($.browser.msie) {
									tb_remove();
								} else {						
									$.fn.fancybox.close();
								}
							}
						}
					});				
				}
			</script>			
		</head>
		<body>
			<h:outputLink value="TB_recargaPP.faces" styleClass="thickbox"></h:outputLink>
			<h:outputLink value="TB_recargaPP.faces" styleClass="enlacePromo"></h:outputLink>	
		</body>
	</html>
</f:view>