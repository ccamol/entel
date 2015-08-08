var clickTooltip = false;

$(document).ready(function(){
	
	$(".inputBox").val("");
	
	$('.caja_entel_mas').hide();	
	
	$('.enlace_promo').click(function(){
		$('.caja_entel_mas').show();
		$('.enlace_promo').hide();
	});					
});

function correccionZIndex() {
	var zIndex = 500;
	$('.tabla_formulario_fila').each(function() {
		$(this).css({
			position: 'relative',
			zIndex: zIndex--
		});
	});
}