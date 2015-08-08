$(document).ready(function() {
	
	$('.enviarSolicitudLinea').click(function(){
		if ($(".datosLineaModificar").is(':visible')){
			$(".lineaAdicionalSolicitud").submit();
		}		
	});
	
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	/*---*/
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
	
	var validadorLineaAdicional = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showAdicional($form.validate().errorList[0].element);
		}
	};
	
	$(".lineaAdicionalSolicitud").each(function() {
		$(this).validate(validadorLineaAdicional);
	});
	
	
	I2BClassValidator({
		rules: {
				nombreLineaAdicional1:{
					required:true
				},
				apellidoLineaAdicional1: {
					required:true
				},
				apellidoLineaAdicional2: {
					required:true
				},
				telcontactoLineaAdicional: {
					required:true,
					minlength:6
				}
			},
			messages: {
				nombreLineaAdicional1:{
					required:"Ingresa un nombre."
				},
				apellidoLineaAdicional1: {
					required:"Ingresa un primer apellido."
				},
				apellidoLineaAdicional2: {
					required:"Ingresa un segundo apellido."
				},
				telcontactoLineaAdicional: {
					required: "Favor ingresa un n&uacute;mero."	
				}
		}
	});
});

function showAdicional(el) {
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