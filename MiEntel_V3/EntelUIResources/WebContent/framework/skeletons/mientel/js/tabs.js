$(document).ready(function(){
	
	$('.linea_tabs > .tab').eq(0).addClass('seleccionado');

	$('.contenido_tabs > .contenido_tab').eq(0).css({'display':'block'});

	$('.tab').click(function(){
		$('.tab').each(function() {
			$(this).removeClass('seleccionado');
		});
		$(this).addClass('seleccionado');
		
		if ($(this).hasClass('contenido1')){
			$('.contenido_tab').css({'display': 'none'});
			$('.contenido1').css({'display': 'block'});
		}
		
		if ($(this).hasClass('contenido2')){
			$('.contenido_tab').css({'display': 'none'});
			$('.contenido2').css({'display': 'block'});
		}
		
		if ($(this).hasClass('contenido3')){
			$('.contenido_tab').css({'display': 'none'});
			$('.contenido3').css({'display': 'block'});
		}
		
		if ($(this).hasClass('contenido4')){
			$('.contenido_tab').css({'display': 'none'});
			$('.contenido4').css({'display': 'block'});
		}
		
		if ($(this).hasClass('contenido5')){
			$('.contenido_tab').css({'display': 'none'});
			$('.contenido5').css({'display': 'block'});
		}
	});
	
	//$('.contenido1').css({'display': 'block'});
	
	return false;
	
});
