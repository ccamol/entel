$(document).ready(function(){
	
	//Valida que solo se ingresen caracteres numericos
	$(".input_numerico").keypress(function(evt){
		var key = evt.keyCode ? evt.keyCode : evt.which ;
		return (key < 32 || (key >= 48 && key <= 57)); 
	});	
	
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	/*---*/
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
    
	
	/***************************** VALIDACION DEL FORMULARIO **********************************************/
	/******************************************************************************************************/
	/******************************************************************************************************/
	$('.enviarMMS').click(function(){
		$(".formulario_SSM").submit();
	});
	
	$('.enviarIT').click(function(){
		$(".formularioIT").submit();
	});
	
	var validatorConfiguracionMMS = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showConfiguracionMMS($form.validate().errorList[0].element);
		}
	};
	
	var validatorConfiguracionIT = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showConfiguracionIT($form.validate().errorList[0].element);
		}
	};
	
	$(".formulario_SSM").each(function() {
		$(this).validate(validatorConfiguracionMMS);
	});
	
	$(".formularioIT").each(function() {
		$(this).validate(validatorConfiguracionIT);
	});
	
	I2BClassValidator({
		rules: {
			mssisdn:{
				required:true,
				number:true,
				minlength:8
			}
		},
		messages: {
			mssisdn:{
				required:"Debes ingresar el n&uacute;mero de tu Entel.",
				number: "Debes ingresar solo caractares num&eacute;ricos.",
				minlength:"El m&oacute;vil ingresado debe contener 8 d&iacute;gitos."
			}
		}
	});
	
	I2BClassValidator({
		rules: {
			mssitm:{
				required:true,
				number:true,
				minlength:8
			}
		},
		messages: {
			mssitm:{
				required:"Debes ingresar el n&uacute;mero de tu Entel.",
				number: "Debes ingresar solo caractares num&eacute;ricos.",
				minlength:"El m&oacute;vil ingresado debe contener 8 d&iacute;gitos."
			}
		}
	});
	
});


function showConfiguracionMMS(el) {
	
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

function showConfiguracionIT(el) {
	
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