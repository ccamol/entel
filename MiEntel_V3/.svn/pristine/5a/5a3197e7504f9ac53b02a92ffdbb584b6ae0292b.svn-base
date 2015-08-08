$(document).ready(function() {
	//Enlaces Sociales

	//Despliege Caja: Compartir Pagina
	$('#boton-Compartir-Pagina').mousedown(function(e) {
		$('#caja_info_compartir').hide();
		
		var compartir = $('#caja_compartir');
		if(!compartir.is(':visible')) {
			compartir.show();
			$(document).bind('mousedown', function(e){
				if($(e.target).parents('#caja_compartir').length < 1) {
					compartir.hide();
					if(!$('#caja_info_compartir').is(':visible')) $(document).unbind('mousedown');	
					return false;
				}
			});
		} else {
			compartir.hide();
		}
		return false;
	});
	
	//Tabs Caja Compartir
	$("#compartir_tab").click(function(){		
		$('#caja_compartir').find(".seleccionado").removeClass("seleccionado");
		$(this).addClass("seleccionado");
		
		$(".contenido_compartir").show();
		$(".marcadores_sociales").hide();	
	});	
	
	$("#marca_sociales_tab").click(function(){		
		$('#caja_compartir').find(".seleccionado").removeClass("seleccionado");
		$(this).addClass("seleccionado");
		
		$(".contenido_compartir").hide();
		$(".marcadores_sociales").show();
	});	
	
	//Cancelar Formulario Compartir
	$('#btnCompartirCancelar').click(function() {
		$('#caja_compartir').hide();
		return false;
	});
	
});