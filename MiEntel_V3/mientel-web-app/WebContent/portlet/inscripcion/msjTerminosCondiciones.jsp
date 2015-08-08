<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				 	
<script type="text/javascript">
	$(document).ready(function(){

		$("#TB_window").css( {
			top:"45%"		
	    });		
		
		$("#TB_ajaxContent").css( {
			width:"940px",
			height:"560px"
	    });

		$(".lb_lightbox").css( {
			'margin-left':"-45px"
	    });

		$("div.terminos").css( {
			width:"889px"
	    });

		$(".lb_lightbox").css( {
			'margin-left':"-120px"
	    });
		
		$("td.lb_main").css( {
			background: "none repeat scroll 0 0 #FFFFFF"
	    });

		if ($.browser.msie && parseInt($.browser.version) == 7) {
			$("DIV#texto").css({
				width:"890px"
		    });
		}		
	});
 </script>

<body>
	<f:view beforePhase="#{cuentaController.initCuentaClienteBSCS}">
		<div id="centro" class="terminos">
			<div id="contrato-fe-noinscrito" style="display:block">			
				<h:outputText value="#{cuentaController.autorizacionVoluntariaContent}" escape="false"/>
			</div>
		</div>
	</f:view>
</body>
