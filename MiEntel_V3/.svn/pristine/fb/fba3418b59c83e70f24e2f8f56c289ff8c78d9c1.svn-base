jQuery(document).ready(function($) {
	
	//Menu Principal > Primer Nivel	
	$('#menu_vertical').accordion({
		header: '.stretch:not(.fijo)',
		active: '.abierto:not(.fijo)',
		autoHeight: false,
		collapsible: true,
		changestart: function() {
			$('div.contenido_stretcher:not(.fijo) .submenu_cabecera').removeClass('seleccionado');
			$('div.contenido_stretcher:not(.fijo) .submenu_contenido').slideUp();
		}
	});
	$('.ui-accordion-header').click(function(){
		$('.ui-accordion-header').removeClass('menu_abierto');
	});
	/**/
	//Menu Principal > Segundo Nivel	
	$('div.contenido_stretcher:not(.fijo) .submenu_cabecera').click(function() {
		$('div.contenido_stretcher:not(.fijo) .submenu_cabecera').removeClass('seleccionado');
		
		var contenido = $(this).next();		
		$('div.contenido_stretcher:not(.fijo) .submenu_contenido').not(contenido).slideUp();
		
		if(!contenido.is(':visible')) {
			contenido.slideDown();
			$(this).addClass('seleccionado');
		} else {
			contenido.slideUp();
		}
		return false;
	});
	
	$("div.fijo li.submenu_cabecera a").click(function() {
		return false;
	});
	if($('.stretch_amin').hasClass('seleccionado')){
		$('.stretch_no_amin').removeClass('abierto');
	}

});
