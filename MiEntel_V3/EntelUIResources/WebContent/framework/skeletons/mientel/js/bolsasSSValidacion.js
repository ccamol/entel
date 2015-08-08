var timerGlobo;
$(document).ready(function(){	
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	/*---*/
	
	$('input').blur(function() {
		$('#globoError').fadeOut();	
	});
    
	$('.btnPasoAceptar').click(function(){
	});
	
	/*------------------------------------
		Validacion 
	------------------------------------*/	
	var validatorConfBolsa = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGloboBolsa($form.validate().errorList[0].element);
		}
	};
	
	$(".paso1").each(function() {
		$(this).validate(validatorConfBolsa);
	});
	
	
	I2BClassValidator({
		rules: {
				numeroPrepagoBolsa:{
					required:true,
					number: true,
					min: 8
				},
				numeroRePrepagoBolsa: {
					required:true,
					number: true,
					min: 8
				}
			},
			messages: {
				numeroPrepagoBolsa:{
					required:"Ingresa el n&uacute;mero",
					number: "Ingresa el n&uacute;mero valido",
					min: "Ingresa el n&uacute;mero minimo con 8 caracteres"
				},
				numeroRePrepagoBolsa: {
					required:"Reingresa el n&uacute;mero",
					number: "Ingresa el n&uacute;mero valido",
					equalTo: "Por favor verifique los n&uacute;meros sean iguales",
					min: "Ingresa el n&uacute;mero minimo con 8 caracteres"
				}
			}
	});
	
});

function showGloboBolsa(el) {
	
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

function soloNumeros(evt){
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 31 || (key >= 48 && key <= 57)); 
}