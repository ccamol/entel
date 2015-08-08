$(document).ready(function(e) {

window.setTimeout(function() {
	$('.skype_pnh_container').html('');
	$('.skype_pnh_print_container').removeClass('skype_pnh_print_container');
}, 800);
	
$('#formulario_iphone_editable').hide();
$('#exitoEnvioI').hide();

$('.cancelarSolicitudI').click(function(){
	$('.desplegable_abierto_click').trigger('click');
	return false;
});

//Funcionalidad de formulario editable solicita iphone
$('.btnModificarDatosIphone').click(function(){
	
	//Paso de datos
	var nombre = $('#nombrevalorI').html();
	var apellido = $('#apellidovalorI').html();
	var movilentel = $('#movilentelvalorI').html();
	var otromovil = $('#otromovilvalorI').text();
	var rut = $('#rutvalorI').html();
	
	var nombreSplit = nombre.split(" ");
	var apellidoSplit = apellido.split(" ");
	var otromovilSplit = otromovil.split(" ");
	
	$('.nombre1editI').val($.trim(nombreSplit[0]));
	$('.nombre2editI').val($.trim(nombreSplit[1]));
	$('.apellido1editI').val($.trim(apellidoSplit[0]));
	$('.apellido2editI').val($.trim(apellidoSplit[1]));
	$('.movilentelI').val(movilentel);
	
	if ($.trim(otromovilSplit[0]).length == 1){
		$('.prefijoi').get(0).setValue("0" + $.trim(otromovilSplit[0]));
	} else {
		$('.prefijoi').get(0).setValue($.trim(otromovilSplit[0]));
	}	
	
	$('.telefonoaddI').val(otromovilSplit[1]);
	$('.ruteditI').val(rut);
	
	$('#formularioI').hide();
	$('#formulario_iphone_editable').show();
	
	return false;
});

$('.btnCancelarEdicionI').click(function(){
	$('#formulario_iphone_editable').hide();
	$('.prefijoi option').find("[value=32]").attr("selected","selected");
	$('#formularioI').show();
	return false;
});

$('.envioSolicitudI').click(function(){
	$('#exitoEnvioI').show();
	$('#formularioI').hide();
	$('#formulario_iphone_editable').hide();
	
	return false;
});

var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
	.css({top: "-999px", left: "-999px", position: 'absolute' })
	.appendTo('body');
	
$('#globoError').hide();
/*---*/

$('input').keypress(function() {
	$('#globoError').fadeOut();	
});

/***************************** VALIDACION DEL FORMULARIO IPHONE*****************************************/
/******************************************************************************************************/
/******************************************************************************************************/
$('.guardarDatosI').click(function(){
	maxlongitud = maxlongitud = parseInt($(".ampliacionNumerica").attr('maxlength'));
	$("form.formularioDatosI").find('input[name="telefonoaddI"]').rules('add', {
		required: true, 
		minlength:maxlongitud,
		maxlength:maxlongitud
	});
	if($("form.formularioDatosI").valid()) {		
		$('#nombrevalorI').html($('.nmbi1').val()+' '+$('.nmbi2').val());
		$('#apellidovalorI').html($('.api1').val()+' '+$('.api2').val());
		$('#otromovilvalorI').html(parseInt($('.prefijoi').val(),10)+' '+$('.teli').val());
		
		$('#formulario_iphone_editable').hide();
		$('#formularioI').show();
		
		window.setTimeout(function() {
				$('.skype_pnh_container').html('');
				$('.skype_pnh_print_container').removeClass('skype_pnh_print_container');
			}, 800);
	}
	return false;
});

var validatorSolicitudIphone = {
	onkeyup: false,
	onfocusout: false,
	errorPlacement: function(error, element) {
		
		var $form = element.parents('form');
		var firstError = $form.validate().errorList[0].message;
		
		$('#globoError').find('td:first').html(firstError);
		showIphoneSolicitud($form.validate().errorList[0].element);
	}
};

$(".formularioDatosI").each(function() {
	$(this).validate(validatorSolicitudIphone);	
});

I2BClassValidator({
	rules: {
			nombre1editI: {
				required: true
			},
			apellido1editI: {
				required: true
			},
			movilentelI: {
				required: true,
				minlength:8
			},
			telefonoaddI: {
				required: true,
				minlength:8,
				maxlength:8
			},
			ruteditI: {
				required: true,
				rut: true,
				maxlength: 16
			}
		},
		messages: {
			nombre1editI: {
				required: "Ingresa tu primer nombre."
			},
			apellido1editI: {
				required: "Ingresa tu apellido paterno."
			},
			movilentelI: {
				required: "Ingresa un m&oacute;vil Entel.",
				minlength: "Ingresa un m&oacute;vil Entel v&aacute;lido."
			},
			telefonoaddI: {
				required: "Ingresa otro tel&eacute;fono de contacto.",
				minlength: "Ingresa un tel&eacute;fono v&aacute;lido.",
				maxlength: "Ingresa un tel&eacute;fono v&aacute;lido."
			},
			ruteditI: {
				required: "Ingresa tu RUT.",
				rut: "Ingresa un RUT v&aacute;lido.",
				maxlength: "Ingresa un RUT v&aacute;lido."
			}
		}
});


});


function showIphoneSolicitud(el) {
	
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