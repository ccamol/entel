$(document).ready(function() {
	var claveGuardada="";
	var ciudadSeleccionada;
	//clave de prueba
		
	
	$.validator.addMethod("comunaCiudad", function(value, element) {
	  if ($('.regionBloqueo').val() != 13 || $('.ciudadBloqueo').val() != 43){
		  return true
	  }else{
		  if(value == 0){
			  return false;
		  }
	  }
	  return true;
	}, "Debe seleccionar una comuna");
		
	
	var $globo = $('<div id="globoErrorBloqueo" class="globoErrorBloqueo mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');

	$('#globoErrorBloqueo').hide();
	/*---*/	
	
	$('input').keypress(function() {
		$('#globoErrorBloqueo').fadeOut();	
	});	
	
	$('.ui-datepicker-trigger, .fechaRobo, .campoHoraRobo, .campohorarobo').hover(function() {
		$('#globoErrorBloqueo').fadeOut();	
	});
	
	$('input').change(function() {
		$('#globoErrorBloqueo').fadeOut();	
	});	
	
	$.validator.addMethod("clave_inicia_cero", function(value, element) {
		
		if(value!=''){	  
		  if(value.substring(0, 1)=='0'){
			  return false;   
		  }
		} 
		  return true;
	  
	}, 'Tu clave no debe comenzar con "0"');
	
	var validarModificarDireccionBloqueo = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoErrorBloqueo').find('td:first').html(firstError);
			showModificarDireccion($form.validate().errorList[0].element);
		}
	};
	
	$(".modificarDireccion").each(function() {
		$(this).validate(validarModificarDireccionBloqueo);
	});
	
	I2BClassValidator({
		rules: {
				regionBloqueo:{
					required:true,
					min: 1
				},
				ciudadBloqueo: {
					required:true,
					min: 1
				},
				comunaBloqueo: {
					comunaCiudad:true
				},
				calleBloqueo: {
					required:true
				},
				numeroBloqueo: {
					required:true,	
					digits: true				
				}
			},
			messages: {
				regionBloqueo:{
					required:"Debe seleccionar una Regi&oacute;n",
					min: "Debe seleccionar una Regi&oacute;n"
				},
				ciudadBloqueo: {
					required:"Debe seleccionar una Ciudad",
					min: "Debe seleccionar una Ciudad"
				},
				comunaBloqueo: {
					comunaCiudad:"Debe seleccionar una Comuna"
				},
				calleBloqueo: {
					required:"Debe ingresar una calle"
				},
				numeroBloqueo: {
					required:"Debe ingresar un n&uacute;mero",
					digits: "Ingresa s&oacute;lo n&uacute;meros."		
				}
			}
	});
	
	
	// validacion modificar mail
	var validarModificarMailBloqueo = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoErrorBloqueo').find('td:first').html(firstError);
			showModificarMail($form.validate().errorList[0].element);
		}
	};
	
	$(".modificarMail").each(function() {
		$(this).validate(validarModificarMailBloqueo);
	});
	
	I2BClassValidator({
		rules: {
				nuevoEmailBloqueo:{
					required:true,
					email: true
				}
			},
			messages: {
				nuevoEmailBloqueo:{
					required:"Debe ingresar un email",
					email: "Debe ingresar un email v&aacute;lido"
				}
		}
	});
	
	// validacion formulario prinicpal
	var validarFormBloqueo = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			$('#globoErrorBloqueo').find('td:first').html(firstError);
			showFormBloqueo($form.validate().errorList[0].element);
		}
	};
	
	$(".bloqueoFormulario").each(function() {
		$(this).validate(validarFormBloqueo);
	});
	
	I2BClassValidator({
		rules: {
				dirBloqueo:{
					required:true
				},
				emailBloqueo:{
					required:true
				},
				telAdicionalNumBloqueo:{
					required:true,
					digits: true
				},
				telAdicionalBloqueo:{
					required:true,
					digits: true,
					minlength:7
				},
				fechaRobo:{
					required:true
				},
				claveBloqueo:{
					clave:true,
					clave_inicia_cero: true
				},
				claveBloqueo1:{
					clave1:true,
					claveDesbloqueo: true
				}
			},
			messages: {
				dirBloqueo:{
					required:"Debe ingresar una direcci&oacute;n"
				},
				emailBloqueo:{
					required:"Debe ingresar un email"
				},
				telAdicionalNumBloqueo:{
					required:"Debe ingresar el c&oacute;digo de &aacute;rea",
					digits: "Debe ingresar solo numeros"
				},
				telAdicionalBloqueo:{
					required:"Debe ingresar el numero de tel&eacute;fono",
					digits: "Debe ingresar solo numeros",
					minlength: "Debe ingresar un numero de tel&eacute;fono v&aacute;lido"
				},
				fechaRobo:{
					required:"Debe seleccionar una fecha"
				},
				claveBloqueo:{
					clave:"Debe ingresar una clave",
					clave_inicia_cero:'Tu clave no debe comenzar con "0"'
				},
				claveBloqueo1:{
					clave1:"Debe reingresar la clave correctamente"
				}
		}
	});
	
	//validar clave desbloqueo igual a registrada
	var validarDesbloqueoEquipoPerdida = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoErrorBloqueo').find('td:first').html(firstError);
			showDesbloqueoPerdida($form.validate().errorList[0].element);
		}
	};
	
	$(".desbloqueoEquipoPerdida").each(function() {
		$(this).validate(validarDesbloqueoEquipoPerdida);
	});
	
	I2BClassValidator({
		rules: {
				claveDesbloqueoPerdida:{
					required:true,
					claveDesbloqueo: true
				}
			},
			messages: {
				claveDesbloqueoPerdida:{
					required:"Debe ingresar una clave de desbloqueo",
					claveDesbloqueo: "La clave ingresada no corresponde a la ingresada en el proceso de bloqueo."
				}
			}
	});
	
});
function showModificarDireccion(el) {	
	var $input = $(el).parents('div:first');
	var punto = $input.offset();
	var $globo = $('#globoErrorBloqueo');
	
	if ($globo.is(':hidden')) {
		$globo.fadeIn(200, function() {
			$(el).focus();
		});
	}
	
	$globo.css({
		'top': ($input.offset().top + parseInt($input.height())/2 - parseInt($globo.css('height'))/2)+5,
		'left': $input.offset().left + parseInt($input.width()) + 5  
	});	
	
	$(window).resize(function() {
		$globo.css({
			'top': ($input.offset().top + parseInt($input.height())/2 - parseInt($globo.css('height'))/2)+5,
			'left': $input.offset().left + parseInt($input.width()) + 5  
		});
	});
	return false;
}

function showModificarMail(el) {
	var $input = $(el).parents('div:first');
	var punto = $input.offset();
	// alert(punto);
	var $globo = $('#globoErrorBloqueo');

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
function showFormBloqueo(el) {
	var $input = $(el).parents('div:first');
	var punto = $input.offset();

	var $globo = $('#globoErrorBloqueo');

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

function showDesbloqueoPerdida(el) {
	var $input = $(el).parents('div:first');
	var punto = $input.offset();

	var $globo = $('#globoErrorBloqueo');

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