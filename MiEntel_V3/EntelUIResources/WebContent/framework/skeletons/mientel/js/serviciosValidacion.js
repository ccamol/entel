$(document).ready(function() {
	
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	/*---*/
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
	
	var validatorServicios = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showServicios($form.validate().errorList[0].element);
		}
	};
	
	$(".validar_numero").each(function() {
		$(this).validate(validatorServicios);
	});
	
	I2BClassValidator({
		rules: {
			numero_avisale: {
				required: true,
				minlength:8
			}
		},
		messages: {
			numero_avisale:{
				required:"Ingrese un n&uacute;mero.",
				minlength:"Ingrese un n&uacute;mero v&aacute;lido."
			}
		}
	});
});

function showServicios(el) {
	var $input = $(el).parents('div:first');
	var punto = $input.offset();
	//alert(punto);
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