<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<f:view> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--[if IE 6]>    
    <style type="text/css">
        img {
            behavior: url("fixmypngs.htc");
        }
    </style>
<![endif]-->
<script type="text/javascript"> 
var mostroError = 0;

function cargarError() {
     mostroError = 1;
    $('.alerta-tabla-oferta-error').fadeIn();
    $('.alerta-tabla-oferta-error').html('<p>Informaci&oacuten de oferta no disponible</p>');

    $('#TB_closeWindowButton').css( {
	    left : "-110px",
	    top : "209px",
	    position : "relative"    
    });   
    $('#TB_closeWindowButton').html('X');        
};

function rechazarOferta(codOferta){
	var url='<%=request.getContextPath()%>/portlet/equipo/rechazoOfertaNBAJson.faces';
	var status;
	      
	$.ajax({
		type: 'POST',
	    url: url,
	    async: false, 
	    dataType: 'json',
	    data: {accion:'rechazarOferta',codOferta:codOferta},
        success: function(datos) {
            status = datos.status;
        }
	});
	return status;
}

function marcaSmartPixel(){	
	var url='<%=request.getContextPath()%>/portlet/equipo/TB_BlindajeNBAJson_Equipo.faces';
	var dataString = "&random="+Math.random()*99999;
	$.ajax( {
		type : 'POST',
		url : url,	
		dataType:'json',
		data: dataString,
		cache : false,
		beforeSend: function(obj){
			
		},		
		success : function(data) {
			var resp = data.respuesta;
			    if(resp.length > 10){
			    	crearMarcaAdwords(resp);
				}					
							
	   	}
 	});
}


$(document).ready(function() {
	$("#frame_blindaje").attr("src", url);
	//alert(""+url+ " " + codOfertaBlindaje + " "+ grupoOfertaBlindaje);
	if ($.browser.msie && parseInt($.browser.version) <= 6) {			
				
			$("#fancy_close").css( {
				top : "-2px",
				left : "575px",
			    padding:"5px",
			    'padding-right':"75px"			  
			});
			$("#fancy_inner").css( {	
			   width:"0%",	
			   top : "-13px"    
			});		
			
			$("#fancy_inner").pngFix();	
			
			$("#fancy_bg").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("div#fancy_bg_n").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("div#fancy_bg_ne").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("div#fancy_bg_e").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("div#fancy_bg_se").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("div#fancy_bg_s").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("div#fancy_bg_sw").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("div#fancy_bg_w").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});		
			$("div#fancy_bg_nw").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("div#fancy_title").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("div#fancy_title div").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("td#fancy_title_left").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("td#fancy_title_main").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	
			$("td#fancy_title_right").css( {	
				filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			});	    
			$("#fancy_close").css( {
			 filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='../framework/skins/mientel/img/blindaje_oferta/blank.gif') alpha(opacity=0.5)"
			 });
	}
	else
	{
		

	   if ($.browser.msie && parseInt($.browser.version) == 8) {
			  $("#TB_window").css( {
					top:"38.5%" ,
					left:"49%",
					position:"absolute"
					
			    });	
	     }else{
	    	 $("#TB_window").css( {
	 			top:"37.5%" ,
	 			left:"49%",
	 			position:"absolute"	
	 	     });
		 }   
	         
		$("#TB_ajaxContent").css( {
			width:"705px",
			height: "685px"		
	    });
	    $("td.lb_main").css( {
	    	background: "transparent"
	    });
	    $("td.lb_tt").css( {
	    	background: "transparent"
	    });
	    $("td.lb_bb").css( {
	    	background: "transparent"
	    });
	    $("td.lb_bb").css( {
	    	background: "transparent"
	    });   

		  if ($.browser.msie && parseInt($.browser.version) == 7) {
			  
			  $("#TB_closeWindowButton").css( {
			        padding : "5px 60px 60px 50px",        
			        position : "relative",
			        background:"transparent", 
			        left:"1px",   
			        'z-index':"99",
			        zise:0.1        
			    });			    
				$('#TB_closeWindowButton').html(".");	
				
			   
		  }else{
				$("#TB_closeWindowButton").css( {
			        padding : "5px 70px 50px",        
			        position : "relative",
			        background:"url()",    
			        'z-index':"99"          
			    });			    
				$('#TB_closeWindowButton').html("");

		   }
		   	    
	    if (codOfertaBlindaje != 0){
		   
	    	if(grupoOfertaBlindaje.indexOf("G7")>=0){

				  if ($.browser.msie && parseInt($.browser.version) == 7) {
					  $("#TB_closeAjaxWindow").append("<br/><a id='TB_closeRejectWindowButton' href='#'></a>");
					  
					  $("#TB_closeRejectWindowButton").css( {
					        padding : "14px 205px 10px 10px",
					        margin: "0 145px 0 0",        
					        position : "relative", 
					        background:"transparent",
					        top: "45px",
					        left:"1px",   
					        'z-index':"99",
					        zise:0.1        
					    });			    
						$('#TB_closeRejectWindowButton').html(".");		
						$("#TB_closeRejectWindowButton").click(function () {
							
							rechazarOferta(codOfertaBlindaje);			
							marcaSmartPixel();
							tb_remove();
			                });	
					   
				  }else{
					    $("#TB_closeAjaxWindow").append("<br/><a id='TB_closeRejectWindowButton' href='#'></a>");
						$("#TB_closeRejectWindowButton").css( {
					        padding : "14px 205px 10px 10px", 
					        margin: "0 145px 0 0",       
					        position : "relative",
					        top: "45px",
					        background:"url()",'z-index':"99"   
						           
					    });			    
						$('#TB_closeRejectWindowButton').html("");		
						$("#TB_closeRejectWindowButton").click(function () {
			
							rechazarOferta(codOfertaBlindaje);	
							marcaSmartPixel();
							tb_remove();
			                });	
				  } 
				  
			}else{
				
			  if ($.browser.msie && parseInt($.browser.version) == 7) {
				  $("#TB_closeAjaxWindow").append("<br/><a id='TB_closeRejectWindowButton' href='#'></a>");
				  $("#TB_closeRejectWindowButton").css( {
				        padding : "5px 146px 146px 38px",        
				        position : "relative",
				        background:"transparent",
				        top: "66px",
				        left:"1px",   
				        'z-index':"99",
				        zise:0.1        
				    });			    
					$('#TB_closeRejectWindowButton').html(".");		
					$("#TB_closeRejectWindowButton").click(function () {
						
						rechazarOferta(codOfertaBlindaje);	
						marcaSmartPixel();
						tb_remove();
		                });	
				   
			  }else{
				    $("#TB_closeAjaxWindow").append("<br/><a id='TB_closeRejectWindowButton' href='#'></a>");
					$("#TB_closeRejectWindowButton").css( {
				        padding : "4px 106px 24px",        
				        position : "relative",
				        top: "22px",
						left: "-145px",
				        background:"url()",'z-index':"99"   
					           
				    });			    
					$('#TB_closeRejectWindowButton').html("");		
					$("#TB_closeRejectWindowButton").click(function () {
		
						rechazarOferta(codOfertaBlindaje);	
						marcaSmartPixel();
						tb_remove();
		                });	
			   } 
			  }

	 	 }
	
		$("#TB_ajaxWindowTitle").css( {		
		    padding: "1px 5px 1px 5px"    
	     });
	     
      }

		$('.frame_blindaje').hide(); 
		$('.alerta-tabla-oferta-error').hide();

		//$("#TB_overlay").click(tb_remove);

		$("#TB_overlay").click(function () {			
			tb_remove();
			marcaSmartPixel();			
            });	

		$("#TB_closeWindowButton").click(function () {
			marcaSmartPixel();			
        });	
		
   
        var i = 1;	   

		$('#frame_blindaje').load(function(response) {
		   timer = setTimeout("cargarError();", 20000);			
			if (i == 1) 
			{
				if (mostroError == 0) {
					 $('.frame_blindaje').show();
				}
		     }	
			clearTimeout(timer);
			i = i + 1;
		});
	});

</script>
		<div class="TB_ajaxContent" allowtransparency="true" >		    	        
				<iframe id="frame_blindaje" allowtransparency="true" src="<h:outputText value="" />"  frameBorder=0 width="701"  height="1315"></iframe>
		</div>
		<div class="alerta-tabla-oferta-error" id="alerta-blidaje"></div> 
		<div class="cerrar-ie6" id="cerrar-ie6" ></div>		
</f:view>