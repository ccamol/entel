$(document).ready(function(){
	
	$('.desplegable_colapsado').hide();
	$('#formulario_editable').hide();
	$('#exitoEnvio').hide();
	
	//Valida que solo se ingresen caracteres numericos
	$(".input_numerico").keypress(function(evt){
		var key = evt.keyCode ? evt.keyCode : evt.which ;
		return (key < 32 || (key >= 48 && key <= 57)); 
	});	
	
	//Valida que solo se ingresen caracteres numericos y 'K' o 'k'
	$(".input_rut").keypress(function(evt){
		var key = evt.keyCode ? evt.keyCode : evt.which ;
		return (key <= 31 || (key >= 48 && key <= 57) || key == 75 || key == 107); 
	});	
	
	//Funcionalidad de desplegables
	$('.desplegable_colapsado').click(function(){
		$(this).parents('.desplegable:first').find('.desplegable_abierto').show();
		$(this).hide();
		
		return false;
	});
	
	$('.desplegable_abierto_click').click(function(){
		$(this).parents('.desplegable:first').find('.desplegable_colapsado').show();
		$(this).parents('.desplegable:first').find('.desplegable_abierto').hide();
		
		return false;
	});
	
	
	//Funcionalidad de formulario editable
	$('.btnModificarDatos').click(function(){
		
		//Paso de datos
		var nombre = $('#nombrevalor').html();
		var apellido = $('#apellidovalor').html();
		var movilentel = $('#movilentelvalor').html();
		var otromovil = $('#otromovilvalor').text();
		var rut = $('#rutvalor').html();
		
		var nombreSplit = nombre.split(" ");
		var apellidoSplit = apellido.split(" ");
		var otromovilSplit = otromovil.split(" ");
		
		
		$('.nombre1edit').val($.trim(nombreSplit[0]));
		$('.nombre2edit').val($.trim(nombreSplit[1]));
		$('.apellido1edit').val($.trim(apellidoSplit[0]));
		$('.apellido2edit').val($.trim(apellidoSplit[1]));
		$('.movilentel').val(movilentel);
		
		if ($.trim(otromovilSplit[0]).length == 1){
			$('.prefijoRenova').get(0).setValue("0" + $.trim(otromovilSplit[0]));
		} else {
			$('.prefijoRenova').get(0).setValue($.trim(otromovilSplit[0]));
		}
		
		$('.telefonoadd').val($.trim(otromovilSplit[1]));
		$('.rutedit').val(rut);
		
		$('#formulario').hide();
		$('#formulario_editable').show();
		
		return false;
	});
	
	$('.btnCancelarEdicion').click(function(){
		$('#formulario_editable').hide();
		$('#formulario').show();
		
		return false;
	});
	
	/* Funcionalidad de la validacion del formulario 
	$('.solicitarContacto').click(function(){
		$('.boton_formulario').hide();
		$('#formulario').show();
		
		return false;
	});
	
	$('.cancelarSolicitud').click(function(){
		$('#formulario').hide();
		$('.boton_formulario').show();
		
		return false;
	});*/
	
	/*$('.guardarDatos').click(function(){
		if($("form.formularioDatos").valid()) {
			var nombre1edit = $('#nombre1edit').val();
			var nombre2edit = $('#nombre2edit').val();
			var apellido1edit = $('#apellido1edit').val();
			var apellido2edit = $('#apellido2edit').val();
			var otromoviledit = $('#telefonoadd').val();
			//var rut = $('#rutedit').val();
			
			$('#nombrevalor').html(nombre1edit + " " + nombre2edit);
			$('#apellidovalor').html(apellido1edit + " " + apellido2edit);
			$('#movilentelvalor').html(movilenteledit);
			$('#otromovilvalor').html(otromoviledit);
			//$('#rutvalor').html(rut);
			
			$('#formulario_editable').hide();
			$('#formulario').show();	
		}
			
		return false;
	});*/
	
	$('.envioSolicitud').click(function(){
		$('#exitoEnvio').show();
		$('#formulario').hide();
		$('#formulario_editable').hide();
		
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
    
	
	/***************************** VALIDACION DEL FORMULARIO*****************************************/ 
	/******************************************************************************************************/ 
	/******************************************************************************************************/ 
	$('.guardarDatos').click(function(){
		maxlongitud = maxlongitud = parseInt($(".ampliacionNumerica").attr('maxlength'));
		$("form.formularioDatos").find('input[name="telefonoadd"]').rules('add', {
			required: true, 
			minlength:maxlongitud,
			maxlength:maxlongitud
		});
		
		if($("form.formularioDatos").valid()) {
			$('#nombrevalor').html($('.nombre1edit').val()+' '+$('.nombre2edit').val()); 
			$('#apellidovalor').html($('.apellido1edit').val()+' '+$('.apellido2edit').val()); 
			$('#otromovilvalor').text($('.prefijoRenova').val() + ' ' + $('.telefonoadd').val()); 
			 
			$('#formulario_editable').hide(); 
			$('#formulario').show(); 
			 
			window.setTimeout(function() { 
					$('.skype_pnh_container').html(''); 
					$('.skype_pnh_print_container').removeClass('skype_pnh_print_container'); 
				}, 800); 
		} 
		return false; 
	}); 
	 
	var validatorSolicitudRenovacion = { 
		onkeyup: false, 
		onfocusout: false, 
		errorPlacement: function(error, element) { 
			 
			var $form = element.parents('form'); 
			var firstError = $form.validate().errorList[0].message; 
			 
			$('#globoError').find('td:first').html(firstError); 
			showRenovacionSolicitud($form.validate().errorList[0].element); 
		} 
	}; 
	 
	$(".formularioDatos").each(function() { 
		$(this).validate(validatorSolicitudRenovacion);	 
	}); 
	 
	I2BClassValidator({ 
		rules: { 
				nombre1edit: { 
					required: true 
				}, 
				apellido1edit: { 
					required: true 
				}, 
				movilentel: { 
					required: true, 
					minlength:8
				}, 
				telefonoadd: { 
					required: true, 
					minlength:8,
					maxlength:8
				}, 
				rutedit: { 
					required: true, 
					rut: true, 
					maxlength: 16 
				} 
			}, 
			messages: { 
				nombre1edit: { 
					required: "Ingresa tu primer nombre." 
				}, 
				apellido1edit: { 
					required: "Ingresa tu apellido paterno." 
				}, 
				movilentel: { 
					required: "Ingresa un m&oacute;vil Entel.", 
					minlength: "Ingresa un m&oacute;vil Entel v&aacute;lido." 
				}, 
				telefonoadd: { 
					required: "Ingresa otro tel&eacute;fono de contacto.", 
					minlength: "Ingresa un tel&eacute;fono v&aacute;lido.",
					maxlength: "Ingresa un tel&eacute;fono v&aacute;lido."
				}, 
				rutedit: { 
					required: "Ingresa tu RUT.", 
					rut: "Ingresa un RUT v&aacute;lido.", 
					maxlength: "Ingresa un RUT v&aacute;lido." 
				} 
			} 
	}); 
	 
	 
	}); 
	 
	 
	function showRenovacionSolicitud(el) { 
		 
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

function abrirThickbox(){
	
	if($(".autoTooltip").hasClass('activo')){
		$(".autoTooltip.activo").trigger("click");
	}
	
	tb_show("","TB_condiciones.html?height=515&amp;width=535", false);
	return false;
}