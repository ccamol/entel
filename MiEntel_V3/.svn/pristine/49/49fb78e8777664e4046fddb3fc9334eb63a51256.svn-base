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
	
	$('.continuar_credito').click(function(){
		//alert("click")		
		$(".formCredito").submit();
		//alert("SD");
		//return false;
	});
	
	/*------------------------------------
		Validacion 
	------------------------------------*/	
	var validatorConfCredito = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGloboCredito($form.validate().errorList[0].element);
		}
	};
	
	$(".formCredito").each(function() {
		$(this).validate(validatorConfCredito);
	});
	
	$(".recargaCredito").click(function(){
		maxlongitud = parseInt($(".ampliacionNumerica").attr('maxlength'));
		$("form.formCredito").find('.numero_prepago_credito').rules('add', {
			required: true, 
			minlength:maxlongitud,
			maxlength:maxlongitud
		});
	});	
	
	I2BClassValidator({
		rules: {
			numero_prepago: {
				required: true,
				minlength:8,
				maxlength:8
			},
	
			numero_prepago_credito: {
				required: true,
				minlength:8
				//maxlength:8
			}
		},
		messages: {
			numero_prepago:{
				required:"Ingrese un n&uacute;mero.",
				minlength:"Ingrese un n&uacute;mero v&aacute;lido."
			},
		
			numero_prepago_credito:{
				required:"Ingrese un n&uacute;mero.",
				minlength:"Ingrese un n&uacute;mero v&aacute;lido."
			}
		}
	});

});

function showGloboCredito(el) {
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