$(document).ready(function() {	
	
	// Validacion formulario canje recarga
	
	$(".validar_form_recarga").click(function(){
		
		var formPadre =  $(this).parents("form.formularioRecargar");
		var fila = $(this).parents('.marcador_fila:first');				
		
		if(validarFormCanje(formPadre, '.numero_destino_canje', '.numero_destino_canje_repeticion')) {			
									
			var msisdnDestino = fila.find('.numero_destino_canje').val();			
			var monto = fila.find('.info_producto').val().split('|')[2];
			
			$('#monto_canje_recarga').val(monto);			
			$('#msisdn_destino_recarga').val(msisdnDestino);
			
			fila.find('.fila_estado4 ').hide();						
			ir_estado2(this);
			
		}
		return false;
	});
	
	
	// Validacion formulario canje bolsa
	 
	$('.validar_form_bolsa').click(function(){
		
		var formPadre =  $(this).parents("form.formBolsasCanje");
		var fila = $(this).parents('.marcador_fila:first');				
		
		if(validarFormCanje(formPadre, '.numero_destino_canje', '.numero_destino_canje_repeticion')) {
			
			var msisdnDestino = fila.find('.numero_destino_canje').val();												
			$('#msisdn_destino_bolsa').val(msisdnDestino);
			
			fila.find('.fila_estado4').hide();
			ir_estado2(this);							
			
		}	
									
		return false;
	});	
	
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	/*---*/
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
	
	$('input').focusout(function() {
		$('#globoError').fadeOut();	
	});
	
	
});


function validarFormCanje(form, classNum1, classNum2){
	
	var numero1 = $(form).find(classNum1).val();
	var numero2 = $(form).find(classNum2).val();
	var valida = false;
	var elem;
	var $globo = $('#globoError');
	
	if (numero1.length < 8){
		$globo.find('.body').html('Ingrese un n&uacute;mero v&aacute;lido.');
		elem = $(form).find(classNum1);
	}
	else{
		if ((numero1 != numero2)){
			$globo.find('.body').html('Los n&uacute;meros deben ser iguales.');
			elem = $(form).find(classNum2);
		}
		else{
			valida = true;
		}
	}
	
	if(!valida){
		showRecarga(elem);
	}
	
	return valida;
}


function showRecarga(el) {
	var $input = $(el).parents('div:first');
	var punto = $input.offset();
	var $globo = $('#globoError');
	
	if ($globo.is(':hidden')) {
		$globo.fadeIn(200, function() {
			$(el).focus();
		});
	}
	
	$globo.css({
		'top': $input.offset().top + parseInt($input.height())/2 - parseInt($globo.css('height'))/2,
		'left': $input.offset().left + parseInt($input.width()) + 5 
	});
	
	$(window).resize(function() {
		$globo.css({
			'top': $input.offset().top + parseInt($input.height())/2 - parseInt($globo.css('height'))/2,
			'left': $input.offset().left + parseInt($input.width()) + 5 
		});
	});
	return false;
}