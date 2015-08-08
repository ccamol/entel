$(document).ready(function(){
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
	
	$.validator.addMethod("validar_fecha_actual",function(value, element, param) {		
		
		var sw = true;
        var f = new Date();			
			
		if( value.substring(6,10) > f.getFullYear() )
		{
		   sw = false;
		}
		else
		{
			if( value.substring(6,10) == f.getFullYear() )
			{
				 if(value.substring(3, 5) > (f.getMonth() +1) )
				 {
					  sw = false;
				 }
				 else
				 {
				   if(value.substring(3, 5) == (f.getMonth() +1) )
				   {
					 if(value.substring(0, 2) > f.getDate())
					 {
						sw = false;
					 }
				   } 
				}
			}
		}
		
		return sw;		
		
	}, "validar_fecha_actual");


    
	
	/***************************** VALIDACION DEL FORMULARIO **********************************************/
	/******************************************************************************************************/
	/******************************************************************************************************/
	$('.submit_bloqueo_1').click(function(){
		$(".formulario_bloqueo_1").submit();
	});
	
	var validatorBloqueoEquipo = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showBloqueoEquipo($form.validate().errorList[0].element);
		}
	};
	
	$(".formulario_bloqueo_1").each(function() {
		$(this).validate(validatorBloqueoEquipo);
	});
	
	I2BClassValidator({
		rules: {
			fecha_robo:{
				required:true,
				validar_fecha_actual :true
			},
			
			hora_robo: {
				required:true,
				min:1
			}
		},
		messages: {
			fecha_robo:{
				required:"Ingresa la fecha de robo o hurto.",
				validar_fecha_actual:"La fecha ingresada es mayor a la actual."
			},

			hora_robo:{
				required:"Selecciona una hora de robo o hurto.",
				min:"Selecciona una hora de robo o hurto."
			}			
		}
	});
	
});


function showBloqueoEquipo(el) {
	
	//pintaCampoRojo(el);
	
	var $input = $(el).parents('.mostrar_globo:first')//.find('div:first');
	var punto = $input.offset();
	var $globo = $('#globoError');

	punto.left += parseInt($input.width()) + 25;
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