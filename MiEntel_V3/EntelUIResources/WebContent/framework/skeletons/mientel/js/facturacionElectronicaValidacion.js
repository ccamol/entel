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
	
	var validadorInscFactElect = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showInscFactElectMod($form.validate().errorList[0].element);
		}
	};
	
	$(".ingresar_email").each(function() {
		$(this).validate(validadorInscFactElect);
	});
	
	
	I2BClassValidator({
		rules: {
		email_10: {
			required: true,
			email: true,
			minlength: 5
		},
		email_20: {
			required: true,
			email: true,
			minlength: 5,
			equalTo: '.email_10'
		}
	},
	messages: {
		email_10: {
			required:"Ingresa un e-mail v&aacute;lido.",
			email:"E-mail inv&aacute;lido.",
			minlength:"Ingresa un e-mail v&aacute;lido."
		},
		email_20: {
			required:"Ingresa un e-mail v&aacute;lido.",
			email:"E-mail inv&aacute;lido.",
			minlength:"Ingresa un e-mail v&aacute;lido.",
			equalTo: "E-mail no corresponde al ingresado anteriormente."
		}
	}

	});
});

function showInscFactElectMod(el) {
	var $input = $(el).parents('div:first');
	var punto = $input.offset();
	//alert(punto);
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