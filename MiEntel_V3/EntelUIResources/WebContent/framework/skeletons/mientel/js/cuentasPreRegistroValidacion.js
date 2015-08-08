$(document).ready(function() {
	
	$('input').keypress(function() {
		$('#globoError').hide();	
		if($(this).get(0).disableError) $(this).get(0).disableError();
	});

	var validadorPreRegistroTitular = {
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
		//$(this).validate(validadorPreRegistroTitular);
	});
	
	
	I2BClassValidator({
		rules: {
		rut_formingreso: {
				required: true,
				rut: true,
				maxlength: 16
			}
		},
		messages: {
			rut_formingreso: {
				required: "Debes ingresar tu RUT.",
				rut: "Ingresa un RUT v&aacute;lido.",
				maxlength: "Ingresa un RUT v&aacute;lido."
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
				func();
				return false;
			} else {
				return false;
			}
		});
	});
	
});