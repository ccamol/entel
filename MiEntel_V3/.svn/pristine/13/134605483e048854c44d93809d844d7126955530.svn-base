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
	
	var validadorFactElect = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showFactElectMod($form.validate().errorList[0].element);
		}
	};
	
	$(".form_modificar_email").each(function() {
		$(this).validate(validadorFactElect);
	});
	
	$(".form_modificar_email2").each(function() {
		$(this).validate(validadorFactElect);
	});
	
	
	I2BClassValidator({
		rules: {			
		email_01:{
			required:true,
			email:true
		},
		email_02: {
			required:true,
			email:true,
			equalTo: ".email_01"
		},
		email_012:{
			required:true,
			email:true
		},
		email_022:{
			required:true,
			email:true,
			equalTo: ".email_012"
		}
	},
	messages: {
		email_01: { 
			required: "Ingresa un e-mail v&aacute;lido.",
			email: "E-mail inv&aacute;lido."
		},
		email_02: {
			required: "Ingresa un e-mail v&aacute;lido.",
			email: "E-mail inv&aacute;lido.",
			equalTo: "E-mail no corresponde al ingresado anteriormente."
		},
		email_012: {
			required: "Ingresa un e-mail v&aacute;lido.",
			email: "Ingresa una direcci&oacute;n de e-mail v&aacute;lida."
		},
		email_022: {
			required: "Ingresa un e-mail v&aacute;lido.",
			email: "E-mail inv&aacute;lido.",
			equalTo: "E-mail no corresponde al ingresado anteriormente."
		}
	}

	});
});

function showFactElectMod(el) {
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