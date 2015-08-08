<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content"%>

<cm:search id="htmlPromoRecargaPP" query="idContenido = 'htmlPromoRecargaPP'" useCache="true"  />

<script type="text/javascript">
	$(document).ready(function() {
		if ($.browser.msie && parseInt($.browser.version) <= 9) {
			$("#TB_closeWindowButton").css({
		        padding : "5px 60px 60px 50px",        
		        position : "relative",
		        background: "transparent", 
		        left: "1px",   
		        'z-index': "99",
		        size: 0.1        
		    });

			$("#TB_title").css({
			    height: "5px",
			    width: "650px"    
		    });

			$("#TB_ajaxContent").css({
				width: "650px",
				height: "530px"
		    });
		    
			$('#TB_closeWindowButton').html("");
			
			$("td.lb_main").css({
		    	background: "transparent"
		    });
		    
		    $("td.lb_tt").css({
		    	background: "transparent"
		    });
		    
		    $("td.lb_bb").css({
		    	background: "transparent"
		    });
		    
		    $("td.lb_bb").css({
		    	background: "transparent"
		    });

		    $("td.lb_ll").css({
		    	background: "transparent"
		    });

		    $("td.lb_rr").css({
		    	background: "transparent"
		    });

		    $("td.lb_tl").css({
		    	background: "transparent"
		    });

		    $("td.lb_tr").css({
		    	background: "transparent"
		    });

		    $("td.lb_bl").css({
		    	background: "transparent"
		    });

		    $("td.lb_br").css({
		    	background: "transparent"
		    });

		    if (parseInt($.browser.version) == 9) {
		    	$(".nota").css({
			    	width: "290px"
			    });
			}
	    } else {
	    	$("#fancy_bg").html("");
			
			$("#fancy_inner").css({
				height: "0px"
			});			
	    }
	});
</script>

<div class="lb-recarga" id="lbRecargaEntel">			    
	<div class="lb-botones clearfix">
		<a href="#" class="btnNoMostrar" onclick="noMostrar();" title="Cerrar y no volver a mostrar"></a>
		<a href="#" class="btnCerrar" onclick="cerrar();" title="Cerrar"></a>
	</div>
	<div class="lb-contenido clearfix">
		<cm:getProperty node='${htmlPromoRecargaPP[0]}' name='html' />
    </div>
</div>