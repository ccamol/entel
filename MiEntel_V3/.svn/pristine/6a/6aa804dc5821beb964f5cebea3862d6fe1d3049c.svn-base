var timerGlobo;
$(document).ready(function() {
	/*
	$('.enviarFormContacto').click(function(){
		$(".validarFormContacto").submit();
	});
	
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	*/
	
	$('input.inputBox').inputBox();
	
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
	
	$('textarea').keypress(function() {
		$('#globoError').fadeOut();	
	});
	
		
	
	var validatorConf = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showFormContacto($form.validate().errorList[0].element);
		}
	};
	
	$(".validarFormContacto").each(function() {
		$(this).validate(validatorConf);
	});
	
	
	jQuery.validator.addMethod('telopcional', function(value, element){ 
		if($(element).val() != '') {
			if($('.codigo_tel_adicional').val() == '02' || $('.codigo_tel_adicional').val() == '09'){
				if($(element).val().length != 8 ){
					return false;
				}
				return true;
			}	
			return true;
		}
		return true;
	}, 'Debes ingresar 8 d&iacute;gitos.');
	
	
	I2BClassValidator({
		rules: {
				nombre_11:{
					required:true
				},
				apellido_11: {
					required:true
				},
				num_epc_1: {
					required:true
				},
				email_11:{
					required:true,
					email:true
				},
				telAdicional_1: {
					telopcional: true
				},
				tipoContacto_1: {
					required: true
				},
				MotivoContacto_1: {
					required:true
				},
				mensajeContacto_1: {
					required:true,					
					maxlength:1000					
				}
			},
			messages: {
				nombre_11:{
					required:"Ingresa un nombre."
				},
				apellido_11: {
					required:"Ingresa un primer apellido."
				},
				num_epc_1: {
					required:"Ingresa un n&uacute;mero PCS."
				},
				email_11:{
					required:"Ingresa una direcci&oacute;n de email.",
					email:"Ingresa una direcci&oacute;n de email v&aacute;lida."
				},
				telAdicional_1: {
					telopcional: "Debes ingresar 8 d&iacute;gitos."
				},
				tipoContacto_1: {
					required:"Favor selecciona el tipo contacto."
				},
				MotivoContacto_1: {
					required: "Favor selecciona el motivo contacto."
				},
				mensajeContacto_1: {
					required: "Favor ingresa un mensaje.",
					maxlength: "Ingresa un m&aacute;ximo de 1000 caracteres."
				}
		}
	});
});

function showFormContacto(el) {
	var $input = $(el).parents('div:first');
	var punto = $input.offset();
	var $globo = $('#globoError');
	
	if ($globo.is(':hidden')) {
		$globo.fadeIn(200, function() {
			$(el).focus();
			$globo.show();
		});
		$globo.fadeOut(200, function() {			
			$globo.show();
		});	
	}
	
	$globo.css({
		'top': $input.offset().top + parseInt($input.height())/2 - parseInt($globo.css('height'))/2,
		'left': $input.offset().left + parseInt($input.css('width')) + 5 
	});
	
	$(window).resize(function() {
		$globo.css({
			'top': $input.offset().top + parseInt($input.height())/2 - parseInt($globo.css('height'))/2,
			'left': $input.offset().left + 300
		});
	});
	
	return false;
}