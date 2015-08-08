 var timerGlobo;
$(document).ready(function(){	
	  
	$.validator.addMethod("rut", function(value, element) {
		return this.optional(element) || $.Rut.validar(value);
	}, "Ingresa un rut v&aacute;lido.");
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
    
	$('.submitClassTrigger').click(function(){
		$(".formulario_pat").submit();
	});
	

	var validatorConf = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGloboPAT($form.validate().errorList[0].element);
		}
	};
	
	$(".formulario_pat").each(function() {
		$(this).validate(validatorConf);
	});
	
	I2BClassValidator({
		rules: {
				titular:{
					required:true
				},
				rut: {
					required:true
				},
				emailpat:{
					required:true,
					email:true
				},
				telefono_area:{
					required:true,
					digits:true
				},
				telefono:{
					required:true,
					digits:true
				},
				tipo_tarjeta:{
					required:true,
					min:1
				},
				tarjeta:{
					required:true,
					digits:true
				},
				chechboxTerminos: {
					required:true
				}
			},
			messages: {
				titular:{
					required:"Ingresa el nombre del titular."
				},
				rut: {
					required:"Ingresa el rut del titular."
				},
				emailpat:{
					required:"Ingresa la direcci&oacute;n de email.",
					email:"Ingresa una direcci&oacute;n de email v&aacute;lida."
				},
				telefono_area:{
					required:"Ingresa el c&oacute;digo de &aacute;rea.",
					digits:"Ingresa s&oacute;lo n&uacute;meros."
				},
				telefono:{
					required:"Ingresa el tel&eacute;fono.",
					digits:"Ingresa s&oacute;lo n&uacute;meros."
				},
				tipo_tarjeta:{
					required:"Selecciona un tipo de tarjeta de cr&eacute;dito.",
					min:"Selecciona un tipo de tarjeta de cr&eacute;dito."
				},
				tarjeta:{
					required:"Ingresa el n&uacute;mero de la tarjeta de cr&eacute;dito.",
					digits:"Ingresa s&oacute;lo n&uacute;meros."
				},
				chechboxTerminos: {
					required:"Antes de continuar, debe aceptar los t&eacute;rminos."
				}
			}
	});
	
	
});

function showGloboPAT(el) {
	
	
	if ($('#globoError').length < 1) {
		var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
			.css({top: "-999px", left: "-999px", position: 'absolute' })
			.appendTo('body');
			
		$('#globoError').hide();

	
	}
	
	
	//pintaCampoRojo(el);
	//var $input = $(el).parents('.fila_campo').find('div.campo:last');//.find('div:last');
	var $input = $(el).parents('div:first');
	var punto = $input.offset();
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
			'left': parseInt($input.width()) + 5
		});					  
	});
	
	return false;
}


function soloNumeros(evt){
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 31 || (key >= 48 && key <= 57)); 
}