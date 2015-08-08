$(document).ready(function() {

	$.validator.addMethod("claveEntel", function(value, element) {
	  if( value.substring(0,1) == '0')
		  return false;			  
	  return true;
	}, "No puede comenzar su clave  con car&aacute;cter cero (0)");
	
	// Agrego globo para mensajes de error en el body
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	/*---*/
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
	
	/*$(".submit_bloqueo_2").click(function(){ 
 		var formulario = $(this).parents("form:first"); 
 		if (formulario.valid()) { 
   			if(formulario.find(".clave_desbloqueo").val() == formulario.find(".re_clave_desbloqueo").val()){ 
   				bloqueoPaso3(); 
   			}else{ 
    			$('#globoError').find('td:first').html("Por favor verifique los n&uacute;meros sean iguales"); 
    			showGloboBolsa(formulario.find(".re_clave_desbloqueo")); 
   			} 
  		} 
	});
	 * */
	
	$(".submit_bloqueo_2").click(function(){ 
		if ($(".fila_actualizar_email").is(':hidden') && $("#email_clave").val() != "" ){ 
			var formulario = $(this).parents("form:first"); 
			if (formulario.valid()) { 
				if(formulario.find(".clave_desbloqueo").val() == formulario.find(".re_clave_desbloqueo").val()){ 
					bloqueoPaso3(); 
				}else{ 
					$('#globoError').find('td:first').html("Por favor verifique los n&uacute;meros sean iguales"); 
					showGloboBolsa(formulario.find(".re_clave_desbloqueo")); 
				} 
			} 
		} 		 
	});
	
	/*------------------------------------
		Validacion 
	------------------------------------*/	
	var validatorConf = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGlobo($form.validate().errorList[0].element);
		}
	};
	
	$(".formulario_bloqueo_2").each(function() {
		$(this).validate(validatorConf);
	});
		
	I2BClassValidator({
		rules: {
			telefono_contacto: {
				required: true,
				minlength: 8
			},
			re_clave_desbloqueo: {
				required: true,
				claveEntel: true,
				minlength: 4
			},
			clave_desbloqueo: {
				required: true,
				claveEntel: true,
				minlength:4
			}
		},
		messages: {
			telefono_contacto:{
				required:"Ingresa un tel&eacute;fono de contacto.",
				minlength:"El tel&eacute;fono debe tener al menos ocho (8) d&iacute;gitos."
			},
			re_clave_desbloqueo:{
				required:"Reingresa la clave de desbloqueo.",
				claveEntel:"No puede comenzar su clave  con carar&aacute;cter cero (0)",
				minlength:"La clave debe tener cuatro (4) d&iacute;gitos."
			},
			clave_desbloqueo:{
				required:"Ingresa la clave de desbloqueo.",
				claveEntel:"No puede comenzar su clave  con car&aacute;cter cero (0)",
				minlength:"La clave debe tener cuatro (4) d&iacute;gitos."
			}
		}
	});

});

function showGlobo(el) {
	//pintaCampoRojo(el);
	
	$(".mensajeError").fadeOut(200,function(){
		$(this).remove();
	});
	var $input = $(el).parents('.mostrar_globo').find('div:first');
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