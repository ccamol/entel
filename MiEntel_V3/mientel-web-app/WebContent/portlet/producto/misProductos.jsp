<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>

<f:view>
<script type="text/javascript">

function decode(input){
    return $('<div/>').html(input).text();
}

function crearPaginador(){
	
	$("#centro .db-paginador-seccion").click(function(){
		var tablaCuerpo = $(this).parents(".db-tabla-cuerpo:first");
		var listas = tablaCuerpo.find("ul");
		var href = $(this).attr('href');
		var link = $(this).parents(".db-paginador:first").find(".db-paginador-seccion");

		for (i=0;i<listas.length;i++){
			//listas.eq(i).addClass('lista-tabla-oculta');
			listas.eq(i).hide();
			link.eq(i).removeClass('db-paginador-seccion-selecionado');
		}
		tablaCuerpo.find(href).fadeIn(500);
		$(this).addClass('db-paginador-seccion-selecionado');
		
		return false;
	});
	
	$("#centro .db-paginador-back").click(function(){
		var el  = $(this).parent().find(".db-paginador-seccion-selecionado").prev();
		if (el.hasClass('db-paginador-seccion')) {
			el.click();
		} else {
			$(this).parent().find("a.db-paginador-seccion:last").click();
		}
		return false;
	});
	
	$("#centro .db-paginador-next").click(function(){
		var el  = $(this).parent().find(".db-paginador-seccion-selecionado").next();
		if (el.hasClass('db-paginador-seccion')) {
			el.click();
		} else {
			$(this).parent().find("a.db-paginador-seccion:first").click();
		}
		return false;
	});
}
	   
	 function loadProductosContratados() {
	    var url='<%=request.getContextPath()%>/portlet/producto/productosContratadosJson.faces';

		    setTimeout(function(){
	   		 alertaReintento('productos');    
	    	      },TIEMPOREINTENTO);

	    
	    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            success: function (resp){
                  //Evitar reintento
  	              flagRespuestas['productos'] = '1';

                  if(resp.estado == 'Ok'){
                  var list = resp.respuesta;
                  
                  var htm = ''; 
                  var pag = '';
                  htm = '<ul id="listapc">';
		          $.each(list, function(itemNo, item) {
		        	var url = '';
		        	var txt = item;		        	
		        	
		        	/*
		        	 * El item 0 corresponde al plan actual
		        	 */
		        	if (itemNo == 0) {
		        		url = '<r:pageUrl pageLabel="${productoController.pageLabelPlanes}" />';
		        	} else if (itemNo >= 1) {

		        		var itemDecode = decode(item);
		        		var arrItem = itemDecode.split(";");        		
		        		txt = arrItem[1];
		        		
		        		if (arrItem[0] == 'IM' || arrItem[0] == 'IMPP') {
		        			url = '<r:pageUrl pageLabel="${productoController.pageLabelInternet}" />';
		        		} else {
							var pageLabel = '<h:outputText value="#{productoController.pageLabelBolsas}" />';

							if (pageLabel == 'bamcc_ccpp_bolsas_page' || arrItem.length == 1) {
								txt = arrItem[0];
							}

							url = '<r:pageUrl pageLabel="${productoController.pageLabelBolsas}" />&tab=activas';
		        		}
		        	}
	        							
					htm += '<li class="clearfix">';
					htm += '<span class="db-tabla-texto-izquierda">'+ txt +'</span>';
				    htm += '<span class="db-tabla-texto-derecha"><a href="' + url + '"><u>Ver detalle</u></a></span>';
				    htm += '</li>';

                   if(itemNo != 0 && ((itemNo + 1) % 3) == 0){ 
				      htm += '</ul>';
					  // Caso de borde: cantidad total de productos es multiplo de 3 
				    if(!((itemNo + 1) == list.length)){
				       htm += '<ul id="listapc_'+itemNo+'" class="lista-tabla-oculta">';
				     }
                    }
					
					// Caso de borde: se llega a ultimo producto -->
                    if((itemNo + 1) == list.length){
					htm += '</ul>';
                    }
				  });

		          //se crea el paginador
		           pag = '<a href="#" class="db-paginador-item db-paginador-back"></a>';
			       pag += '<a href="#listapc" class="db-paginador-item db-paginador-seccion db-paginador-seccion-selecionado"></a>';
		          $.each(list, function(itemNo, item) {

		        	  if((itemNo != 0) && ((itemNo + 1) % 3 == 0)){
		        		  if(!((itemNo + 1) == list.length)){
		    				pag += '<a href="#listapc_'+itemNo+'" class="db-paginador-item db-paginador-seccion"></a>';
		        		  }
		        	  }
	                    
					  });
                     pag += '<a href="#" class="db-paginador-item db-paginador-next"></a>';

                      
		            $('#productoscontratados').html(htm);
		               if(list.length > 3){ //si solo son 3 no mostrar paginador
		                 $('#productoscontratadospag').html(pag);  
		                  crearPaginador();
		               }
	    			$('#loading-tabla-productos').hide();
	    			$('#alerta-tabla-reintento-productos').hide();
	    			$('#contenido-tabla-productos').fadeIn();
	               }else{
	            	   showErrorMessages('productos',resp.detalle);
		               }	           
	            }
	        });
	}

	 $(document).ready(function() {
		 loadProductosContratados();
	 });
	 	
	</script>




<div class="db-tabla">
	<div>
		<h:messages id="messageList" showSummary="true"  />	
	</div>
	<div class="db-tabla-cabecera">
		<div class="db-cabecera-top"></div>
		<div class="db-cabecera-cuerpo">
			<div class="db-titulo db-titulo-productos">Mis Productos</div>
		</div>
	</div>
	<div class="db-tabla-cuerpo">
	<div class="alerta-tabla-reintento" id="alerta-tabla-reintento-productos"></div>
	  <div id="loading-tabla-productos"></div>
      <div id="contenido-tabla-productos">
		<span id="productoscontratados"></span>
		<div class="db-paginador clearfix">
		    <span id="productoscontratadospag"></span>
		</div>
     </div>
	</div>
	<div class="db-tabla-pie"></div>
</div>
</f:view>