var timerGlobo;
$(document).ready(function() {
	// Agrego globo para mensajes de error en el body
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	/*---*/
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
		//if($(this).get(0).disableError) $(this).get(0).disableError();						 
	});
	
	$('.continuarPaso1').click(function(){
		//alert("click")		
		$(".formSuscripcion").submit();
		//alert("SD");
		//return false;
	});

	/*------------------------------------
		Validacion 
	------------------------------------*/	
	var validatorConf = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGlobo($form.validate().errorList[0].element);
		}
	};
	
	$(".formSuscripcion").each(function() {
		$(this).validate(validatorConf);
	});
		
	I2BClassValidator({
		rules: {
			numero_prepago: {
				required: true,
				minlength:8,
				EPCS: true 	//Simula no ser un numero EPCS: 55555555
			},
			numero_prepago_repeticion: {
				required: true,
				minlength: 8,
				equalTo: '.numero_prepago'
			}
		},
		messages: {
			numero_prepago:{
				required:"Ingrese un n&uacute;mero.",
				minlength:"Ingrese un n&uacute;mero v&aacute;lido."
			},
			numero_prepago_repeticion:{
				required:"Ingrese un n&uacute;mero.",
				minlength:"Ingrese un n&uacute;mero v&aacute;lido.",
				equalTo:"Los n&uacute;meros deben ser iguales"
			}
		}
	});

});

function showGlobo(el) {
	pintaCampoRojo(el);
	
	var $input = $(el).parents('.campo:first'); //.parents('.campo:first');
	var punto = $input.offset();
	var $globo = $('#globoError');
		
	punto.left += parseInt($input.width()) + 5;
	punto.top += parseInt($input.height())/2 - parseInt($globo.css('height'))/2;
		
	if ($globo.is(':hidden')) {
		$globo.fadeIn(200, function() {
			$(el).focus();							
		});
	}
	
	$globo.css({
		'top': punto.top,
		'left': punto.left
	});
	
	return false;
}