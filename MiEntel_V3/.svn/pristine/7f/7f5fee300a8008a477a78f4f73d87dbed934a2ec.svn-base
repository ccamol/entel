$(document).ready(function() {
	
	$('.enviarFormMMKT').click(function(){
		$(".validarFormMMKT").submit();
	});
	
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
	
	var validarFormMMKT = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGloboMMKT($form.validate().errorList[0].element);
		}
	};
	
	$(".validarFormMMKT").each(function() {
		$(this).validate(validarFormMMKT);
	});
	
	
	I2BClassValidator({
		rules: {
				ciudad_MMKT:{
					required: true,
					noCero: true				
				},
				comuna_MMKT:{
					required: true,
					noCero: true
				},
				calle_MMKT: {
					required: true,
					minlength: 2
				},
				numero_MMKT: {
					required: true,
					minlength: 1
				},
				email_MMKT: {
					required:true,
					email:true
				},
				sexo_MMKT:{
					required:true
				}
			},
			messages: {	
				ciudad_MMKT:{
					required:"Selecciona una ciudad.",
					noCero:"Selecciona una ciudad."
				},
				comuna_MMKT:{
					required:"Selecciona una comuna.",
					noCero:"Selecciona una comuna."
				},
				calle_MMKT:{
					required:"Ingresa una calle.",
					minlength:"Ingresa una calle."
				},
				numero_MMKT:{
					required:"Ingresa un n&uacute;mero.",
					minlength:"Ingresa un n&uacute;mero."
				},
				email_MMKT:{
					required:"Ingresa una direcci&oacute;n de email.",
					email:"Ingresa una direcci&oacute;n de email v&aacute;lida."
				},
				sexo_MMKT:{
					required: "Elija el sexo."
				}
		}
	});
});

function showGloboMMKT(el) {
	//var $input = $(el).parents('div:first');
	var $input = $(el).parents('.campo:first');
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