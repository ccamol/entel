jQuery.validator.addMethod('InvitadoEPCS', function(value, element, params) {
	if(value != '55555555') {
		return true;
	}
	return false;
}, 'El n&uacute;mero invitado no es Entel PCS. Revisa el n&uacute;mero invitado o intenta con uno distinto.');

jQuery.validator.addMethod('PrepagoEPCS', function(value, element, params) {
	if(value != '66666666') {
		return true;
	}
	return false;
}, 'El n&uacute;mero invitado no es Prepago. Revisa el n&uacute;mero invitado o intenta con uno distinto.');

jQuery.validator.addMethod('yaComunik2EPCS', function(value, element, params) {
	if(value != '77777777') {
		return true;
	}
	return false;
}, 'El n&uacute;mero invitado ya tiene plan Comunik2 con otro m&oacute;vil. Revisa el n&uacute;mero invitado o intenta con uno distinto.');

jQuery.validator.addMethod('noSaldoEPCS', function(value, element, params) {
	if(value != '88888888') {
		return true;
	}
	return false;
}, 'El n&uacute;mero invitado no tiene saldo suficiente para realizar esta acci&oacute;n. Revisa el n&uacute;mero invitado o intenta con uno distinto.');

/*-------------------------------*/

$(document).ready(function() {

		
	//$('#globoError').hide();
	/*---*/
	
	$('input').keypress(function() {
		$(this).parents("form:first").find('.globoError2').fadeOut();
		if($(this).get(0).disableError) $(this).get(0).disableError();							 
	});
	
});


// Validacion Ingresa Numero Comunik2
$(document).ready(function() {
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
		if($(this).get(0).disableError) $(this).get(0).disableError();						 
	});
	
	var validatorConfCmk2 = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGlobo($form.validate().errorList[0].element);
		}
	};
	
	$('form[id*="ingresar"]').each(function() {
		$(this).validate(validatorConfCmk2);
	});
	
	I2BClassValidator({
		rules: {
		numero_frecuente: {
			required: true,
			minlength:8
			
		}				
	},
	messages: {
		numero_frecuente:{
			required:"Ingrese un n&uacute;mero.",
			minlength:"Ingrese un n&uacute;mero v&aacute;lido."
		}
	  }
	});
	
	$('form[id*="ingresar"]').each(function(i) {
		$(this).find('input').click(function() {
			if ($(this).parents('form').validate().toShow.length == 0) {
				$('#globoError').fadeOut(200);
			}
		});
	});
	/*---*/
	
	
	
	var linkSubmit = $(".ingresarNumero");
	
	linkSubmit.each(function() {
		var func = $(this).attr("onclick");
		$(this).removeAttr("onclick");
		$(this).click(function() {
			if($('form[id*="ingresar"]').valid()) {
				//Aqui la peticion ajax que registra la nueva clave
				//Luego despliega el siguiente mensaje
//				$('#clave_cambiada').slideDown(250);
//				$('input.claveActual, input[name=nuevaClave], input[name=nuevaClave2]').val('');
				func();
				return false;
			} else {
				return false;
			}
		});/**/
	});
});



//Validacion Editar Numero Comunik2
$(document).ready(function() {
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
		if($(this).get(0).disableError) $(this).get(0).disableError();						 
	});
	
	var validatorConfCmk2Editar = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGlobo($form.validate().errorList[0].element);
		}
	};
	
	$('form[id*="cambiar"]').each(function() {
		$(this).validate(validatorConfCmk2Editar);
	});
	
	I2BClassValidator({
		rules: {
		numero_frecuente_cambiar: {
			required: true,
			minlength:8
			
		}				
	},
	messages: {
		numero_frecuente_cambiar:{
			required:"Ingrese un n&uacute;mero.",
			minlength:"Ingrese un n&uacute;mero v&aacute;lido."
		}
	  }
	});
	
	$('form[id*="cambiar"]').each(function(i) {
		$(this).find('input').click(function() {
			if ($(this).parents('form').validate().toShow.length == 0) {
				$('#globoError').fadeOut(200);
			}
		});
	});	
	
	var linkSubmitEditar = $(".nuevoEditarNumero");
	
	linkSubmitEditar.each(function() {
		var func = $(this).attr("onclick");
		$(this).removeAttr("onclick");
		$(this).click(function() {
			if($('form[id*="cambiar"]').valid()) {
				//Aqui la peticion ajax que registra la nueva clave
				//Luego despliega el siguiente mensaje
//				$('#clave_cambiada').slideDown(250);
//				$('input.claveActual, input[name=nuevaClave], input[name=nuevaClave2]').val('');
				func();
				return false;
			} else {
				return false;
			}
		});/**/
	});
});






$(document).ready(function() {
	
	/*------------------------------------
			Validacion Numero
	------------------------------------*/
	$("form#ingresar").each(function (){
		var numero = $(this).find("#numero");
		// Agrego globo para mensajes de error en el body
		var $globo = $('<div class="globoError2 mensaje2"><div class="alerta_top mensaje2"></div><div class="alerta_centro"><table><tr><td class="body">msg</td></tr></table></div><div class="alerta_bottom"></div></div>')
			.css({top: "-999px", left: "-999px", position: 'absolute' });
		$(this).find(".globoError2").remove();
		$(this).append($globo);
		$(this).validate({
		
			onkeyup: false,
			onfocusout: false,
			rules: {
				numero_frecuente: {
					required: true,
					minlength:8,
					InvitadoEPCS: true,		
					PrepagoEPCS: true, 		
					yaComunik2EPCS: true, 	
					noSaldoEPCS: true		
				}				
			},
			messages: {
				numero_frecuente:{
					required:"Ingrese un n&uacute;mero.",
					minlength:"Ingrese un n&uacute;mero v&aacute;lido."
				}
			},
			errorPlacement: function(error, element) {
				
				var $form = element.parents('form:first');
				var firstError = $form.validate().errorList[0].message;
				
				$form.find('.globoError2:first').find('td:first').html(firstError);
				showGlobo($form.validate().errorList[0].element);
			}
		});
	});
	
	$("form#cambiar").each(function (){
		var numero = $(this).find("#numero");
		// Agrego globo para mensajes de error en el body
		var $globo = $('<div class="globoError2 mensaje2"><div class="alerta_top mensaje2"></div><div class="alerta_centro"><table><tr><td class="body">msg</td></tr></table></div><div class="alerta_bottom"></div></div>')
			.css({top: "-999px", left: "-999px", position: 'absolute' });
		$(this).find(".globoError2").remove();
		$(this).append($globo);
		$(this).validate({
		
			onkeyup: false,
			onfocusout: false,
			rules: {
				numero_frecuente_cambiar: {
					required: true,
					minlength:8,
					InvitadoEPCS: true,		
					PrepagoEPCS: true, 		
					yaComunik2EPCS: true, 	
					noSaldoEPCS: true		
				}				
			},
			messages: {
				numero_frecuente_cambiar:{
					required:"Ingrese un n&uacute;mero.",
					minlength:"Ingrese un n&uacute;mero v&aacute;lido."
				}
			},
			errorPlacement: function(error, element) {
				
				var $form = element.parents('form:first');
				var firstError = $form.validate().errorList[0].message;
				
				$form.find('.globoError2:first').find('td:first').html(firstError);
				showGlobo($form.validate().errorList[0].element);
			}
		});
	});
	
	$(".inputBox").keypress(function(){
		$(this).parents("form:first").find(".globoError2").fadeOut(200);
	});
});

/*
function showGlobo(el) {
	
	pintaCampoRojo(el);
	
	var $input = $(el).parents('.campo:first'); //.parents('.campo:first');
	var punto = $input.offset();
	var $globo = $(el).parents("form:first").find('.globoError2');
		
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
/**/

function comunik2Validacion(){
		
	$("form#cambioPlan").each(function (){
		var numero = $(this).find("#numero");
		
		// Agrego globo para mensajes de error en el body
		var $globo = $('<div class="globoError2 mensaje2"><div class="alerta_top mensaje2"></div><div class="alerta_centro"><table><tr><td class="body">msg</td></tr></table></div><div class="alerta_bottom"></div></div>')
			.css({top: "-999px", left: "-999px", position: 'absolute' });
		$(this).find(".globoError2").remove();
		$(this).append($globo);
		$(this).validate({
		
			onkeyup: false,
			onfocusout: false,
			rules: {
				numero_frecuente_cambioPlan: {
					required: true,
					minlength:8,
					InvitadoEPCS: true,		
					PrepagoEPCS: true, 		
					yaComunik2EPCS: true, 	
					noSaldoEPCS: true		
				}				
			},
			messages: {
				numero_frecuente_cambioPlan:{
					required:"Ingrese un n&uacute;mero.",
					minlength:"Ingrese un n&uacute;mero v&aacute;lido."
				}
			},
			errorPlacement: function(error, element) {
				
				var $form = $("form#cambioPlan");
				var firstError = $form.validate().errorList[0].message;
				
				$form.find('.globoError2:first').find('td:first').html(firstError);
				showGloboCP($form.validate().errorList[0].element);
			}
		});
	});
	
	$(".nf_input").keypress(function(){
		$(this).parents("form:first").find(".globoError2").fadeOut(200);
		$(this).get(0).disableError();
	});
}

function comunik2(){
	$('input.nf_input').inputBox();
	//$('select.nf_select').selectBox();
	
	$(".paso2Comunik2").click(function(){
		$(".comunik2Input").val("");
	});
	
	$(".ingresarNumeroCambioPlan").click(function(){
	
		if ($("form#cambioPlan").valid()) {
			$(this).parent().parent().parent().parent().parent().parent().find(".paso_2").hide();
			$(this).parent().parent().parent().parent().parent().parent().find(".paso_3").show();
		}
		return false;
	});
	
}

function showGloboCP(el) {
	
	pintaCampoRojo(el);
	
	var $input = $(el).parents('.campo:first'); //.parents('.campo:first');
	var punto = $input.offset();
	var $globo = $(el).parents("form:first").find('.globoError2');
		
	if ($globo.is(':hidden')) {
		$globo.fadeIn(200, function() {
			$(el).focus();							
		});
	}
	
	$globo.css({
		'top': 27,
		'left': 200
	});
	
	return false;
	
}
