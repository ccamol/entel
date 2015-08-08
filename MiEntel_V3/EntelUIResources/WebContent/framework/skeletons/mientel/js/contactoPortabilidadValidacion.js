$(document).ready(function() {
	
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	/*---*/
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
	
	$('textarea').keypress(function() {
		$('#globoError').fadeOut();	
	});
	
	var validador = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showFormContactoPortabilidad($form.validate().errorList[0].element);
		}
	};
	
	$(".validarFormContactoPortabilidad").each(function() {
		$(this).validate(validador);
	});
	
	
	I2BClassValidator({
		rules: {
				otro_numero: {
					required: true
				},
				port_email: {
					required: true,
					email: true
				},
				mensajePortabilidad: {
					required: true,					
					maxlength: 1000					
				},
				marcaEquipo: {
					required: true
				}
			},
			messages: {
				otro_numero: {
					required: "Ingresa un tel&eacute;fono de contacto."
				},				
				port_email: {
					required: "Ingresa una direcci&oacute;n de email.",
					email: "Ingresa una direcci&oacute;n de email v&aacute;lida."
				},
				mensajePortabilidad: {
					required: "Favor ingresa un mensaje.",
					maxlength: "Ingresa un m&aacute;ximo de 1000 caracteres."
				},
				marcaEquipo: {
					required: "Selecciona la marca del equipo."
				}
		}
	});
});

function showFormContactoPortabilidad(el) {
	var $input = $(el).parents('div:first');
	var punto = $input.offset();	
	var $globo = $('#globoError');
	
	if ($globo.is(':hidden')) {
		$globo.fadeIn(0, function() {
			$(el).focus();
			$globo.show();
		});
		$globo.fadeOut(0, function() {
			$globo.show();
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