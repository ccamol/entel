$(document).ready(function() {		
	$('.modificarLineaAdicional').click(function(){
		$(this).parents('.lineaAdicional').find('.campo').each(function() {
			if ($(this).attr('id') == '') {
				var inputVal = $(this).find('strong:first').hide().text();			
				$(this).find('input:not(:radio)').val(inputVal).end().find('.campo-amarillo').show();
			}
		});
		$('.modificarLineaAdicional').hide();
		$('.cancelarModificarLineaAdicional').show();
		
		$(this).parents('.lineaAdicional').find('#enviarSolicitud').hide();
		$(this).parents('.lineaAdicional').find('#guardarCambios').show();
	});
	
	$('.cancelarModificarLineaAdicional').click(function() {
		$(this).parents('.lineaAdicional').find('.campo').each(function() {
			$(this).find('strong').show().end().find('.campo-amarillo').hide();						  
		});
		$('.modificarLineaAdicional').show();
		$('.cancelarModificarLineaAdicional').hide();
		
		$(this).parents('.lineaAdicional').find('#enviarSolicitud').show();
		$(this).parents('.lineaAdicional').find('#guardarCambios').hide();
	});
});
