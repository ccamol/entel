<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Contenido</title>
</head>
	<f:view beforePhase="#{facturacionFullController.init}">	

     
	 <script type="text/javascript">
			
		$(document).ready(function() {
				
				$("#fancy_inner").css( {
					height : "800px",
					width : "1030px",
					top : "-38px",
					left : "-120px"
				});
	
				$("#fancy_outer").css( {
					height : "800px",
					width : "1030px",
					top : "-38px",
					left : "-120px"
				});
				
		    if ($.browser.msie && parseInt($.browser.version) <= 6) {		
		    	$("div#fancy_close").css( {
 					top : "17px",
 					left : "930px"
 				});	
		    	 $("div#fancy_close").css( {
						filter:"",
						'background-image':"url(../framework/skins/mientel/img/fancybox/fancy_closebox.gif)"	
				 });
		    }else{
		    	$("div#fancy_close").css( {
					top : "25px",
					left : "940px"
				});
			}

		  $("#iframe_factura").css( {
				height : "800px",
				width : "1030px"
		   });
			   
			$("#TB_ajaxContent").css( {
				height : "650px",
				width : "1030px"
			});
			$("#TB_closeWindowButton").css( {
				left : "90%",
				top : "10%",
				position : "absolute",
				'z-index' : "99"
			});

			$(".lb_lightbox").css( {
				height : "650px",
				width : "1020px",
				'margin-left' : '-150px',
				top : '-92px'
			});
			$("#iframe_factura").css( {
				height : "800px",
				width : "1030px"
			});
			   
	});
</script>
	<body>		    
		<div class="TB_ajaxContent2">		
				<iframe id="iframe_factura" src="<h:outputText value='#{facturacionFullController.urlFactura}' />" width="100%" height="500"> </iframe>
	   </div>	
	</body>
	</f:view>
</html>