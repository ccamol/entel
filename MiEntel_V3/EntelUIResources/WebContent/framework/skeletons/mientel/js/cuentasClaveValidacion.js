$(document).ready(function() {
	$('input.inputBox').inputBox();
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
		if($(this).get(0).disableError) $(this).get(0).disableError();						 
	});
	
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
	
	$("div.mis-datos-info form").each(function() {
		//$(this).validate(validatorConf);
	});
	
	I2BClassValidator({
		rules: {
			clave_actual:{
				required: true,
				minlength: 4
			},
			nueva_clave: {
				required: true,
				minlength: 4
			},
			nueva_clave2: {
				required: true,
				equalTo: '.nueva_clave',
				minlength: 4
			}
		},
		messages: {
			clave_actual:{
				required:"Ingresa tu clave actual.",
				minlength: "La clave debe ser de 4 d&iacute;gitos."
			},
			nueva_clave: {
				required:"Ingresa tu nueva clave.",
				minlength: "La clave debe ser de 4 d&iacute;gitos."
			},
			nueva_clave2: {
				required:"Reingresa tu nueva clave.",
				equalTo: "Tus nuevas claves no coinciden.",
				minlength: "La clave debe ser de 4 d&iacute;gitos."
			}
		}
	});
	
	$("form").each(function(i) {
		$(this).find('input').click(function() {
			if ($(this).parents('form').validate().toShow.length == 0) {
				$('#globoError').fadeOut(200);
			}
		});
	});
	/*---*/
	
	
	
	var linkSubmit = $("div.guardar_clave a");
	
	linkSubmit.each(function() {
		var func = $(this).attr("onclick");
		$(this).removeAttr("onclick");
		$(this).click(function() {
			if($("div.mis-datos-info form").valid()) {
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


function soloNumeros(evt){
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 31 || (key >= 48 && key <= 57)); 
}