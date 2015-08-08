var timerGlobo;
var maxlongitud ;
$(document).ready(function() {
	// Agrego globo para mensajes de error en el body
	var $globo = $('<div id="globoErrorTiket" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	/*---*/
	
	$('input').blur(function() {
		$('#globoError').fadeOut();	
	});


	/*------------------------------------
		Validacion 
	------------------------------------*/	
	var validatorConfTicket = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {	
		
		         maxlongitud = parseInt($(".ampliacionNumerica").attr('maxlength'));		         
		         jQuery.validator.addMethod('numero_prepago_ticket', function(value, element){ 
		     		if($(element).val().length < maxlongitud ) {
		     			return false;
		     		}
		     		else {
		     			return true;
		     		}
		     		return false;
		     	}, 'Ingrese un numero valido.');
		         
		        
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGloboTicket($form.validate().errorList[0].element);
			
		}
	};
	
	 

	
	$(".formRecEntelTicket").each(function() {
		$(this).validate(validatorConfTicket);
	});
		
	I2BClassValidator({
		rules: {
			numero_prepago_ticket: {
				required: true
				//minlength:maxlongitud
			},
			numero_eticket: {
				required: true,
				minlength: 3
			},
			numero_eticket_repeticion: {
				required: true,
				minlength: 3,
				equalTo: '.numero_eticket'
			}
		},
		messages: {
			numero_prepago_ticket:{
				required:"Ingrese un numero."
				//minlength:"Ingrese un numero valido."
			},
			numero_eticket:{
				required:"Ingresa un numero secreto",
				minlength:"Ingresa un numero secreto, minimo 3 digitos"
			},
			numero_eticket_repeticion:{
				required:"Ingresa un numero secreto",
				minlength:"Ingresa un numero secreto, minimo 3 digitos",
				equalTo:"No coincide con el numero de entelticket"
			}
		}
	});

});

function showGloboTicket(el) {
	
	var $input = $(el).parents('.campo:first'); //.parents('.campo:first');
	//var $input = $(el).parents('div:first');
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