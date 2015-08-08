<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<f:view>
	<cm:search id="scriptSmartPixel" query="idContenido = 'scriptSmartPixel'" useCache="false"/>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript">
	
		function generarMarcaSmartPixel(){				   
				var url='<%=request.getContextPath()%>/portlet/dashboard/marcas/smartpixeJson.faces';
				$.ajax( {
					type : 'POST',
					url : url,	
					dataType:'json',
					timeout:100000,
					beforeSend: function(obj){
					
					},		
					success : function(data) {
						if(data.estado == 'Ok'){							 							
							 crearMarcaSmartPixel(data.respuesta.Atributo,data.respuesta.Seg,data.respuesta.Mercado,data.respuesta.FE);						
						}
				    }
			   });
		}
		$(document).ready(function() {			
			generarMarcaSmartPixel();
		});
	</script>			
	</head>
		<body>			
			<div id="smartPixelDiv"><cm:getProperty node="${scriptSmartPixel[0]}" name="html" /></div>
		</body>
	</html>

</f:view>