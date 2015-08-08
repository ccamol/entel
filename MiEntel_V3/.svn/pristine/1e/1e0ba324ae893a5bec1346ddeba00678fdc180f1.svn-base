$(document).ready(function(e) {

	$('input').keypress(function() {
		$('#globoError').hide();	
		if($(this).get(0).disableError) $(this).get(0).disableError();
	});

	$.validator.addMethod("terminosRegistro", function(value, element) {
		if(!$("input[id*=terminosRegistro]").is(':checked')) {  
			return false;
		}
		return true;
	}, "Debes aceptar los t&eacute;rminos y condiciones antes de continuar.");

	var validadorRegistroTitular = {
			onkeyup: false,
			onfocusout: false,
			errorPlacement: function(error, element) {
				
				var $form = element.parents('form');
				var firstError = $form.validate().errorList[0].message;
				
				$('#globoError').find('td:first').html(firstError);
				showGloboRegistro($form.validate().errorList[0].element);
			}
	};
	
	$("div.mis-datos-registro-info form").each(function() {
		$(this).validate(validadorRegistroTitular);
	});

	var linkSubmit = $("div.guardar_clave_registro a");
	
	linkSubmit.each(function() {
		var func = $(this).attr("onclick");
		$(this).removeAttr("onclick");
		$(this).click(function() {
			if($("div.mis-datos-registro-info form").valid()) {
				func();
				return false;
			} else {
				return false;
			}
		});
	});
	
	I2BClassValidator({
		rules: {
				nombreRegistroUsuario1:{
					required:true
				},
				apellidoRegistroUsuario1: {
					required:true
				},
				apellidoRegistroUsuario2: {
					required:true
				},
				nacdiaRegistroUsuario: {
					required: true,
					number: true,
					minlength:2,
					min:1,
					max: function(){
							var ano = (  ($('.nacanoRegistroUsuario').val() == "AAAA")   ? (new Date().getFullYear()) :   $('.nacanoRegistroUsuario').val() );
				            var mes =  (  ($('.nacmesRegistroUsuario').val() == "MM")   ? ((new Date().getMonth())+1):   $('.nacmesRegistroUsuario').val()  );
							return parseInt( new Date(ano,mes, 0).getDate() );
					}
				},
				nacmesRegistroUsuario: {
					required:true,
					number:true,
					minlength:2,
					min:1,
					max: 12
				},
				nacanoRegistroUsuario: {
					required:true,
					number: true,
					minlength:4,
					min:1910,
					max: function() {
						var fecha = new Date();
						return parseInt(fecha.getFullYear()-10);
					}
				},
				emailRegistroUsuario:{
					required:true,
					email:true
				},
				emailRegistroUsuario2:{
					required:true,
					email:true,
					equalTo: '.emailRegistroUsuario'
				},
				
				terminosRegistroUsuario:{
					terminosRegistro:true
				},				
				
				regionRegistroUsuario: {
					required: true
				},
				
				ciudadRegistroUsuario: {
					required: true
				},
				comunaRegistroUsuario: {
					required:true
				},
				
				calleRegistroUsuario: {
					required:true
				},
				numeroRegistroUsuario: {
					required:true	
				},
				deptoCasaOtroRegistroUsuario: {
					required:true	
				},
				relacionTitular: {
					required: function() {
						return ($('.relacionTitular').val() == '');
					}
				},
				prefijoTelAdicionalRegistroUsuario: {
					required: function() {
						return ($('.telAdicionalRegistroUsuario').val() != '');
					}
				},
				telAdicionalRegistroUsuario: {
					required: function() {
						return ($('.prefijoTelAdicionalRegistroUsuario').val() != '');
					},
					minlength: 7
				},
				terminosCondiciones: {
					required: true
				}
			},
			messages: {
				nombreRegistroUsuario1:{
					required:"Ingresa un nombre."
				},
				apellidoRegistroUsuario1: {
					required:"Ingresa un primer apellido."
				},
				apellidoRegistroUsuario2: {
					required:"Ingresa un segundo apellido."
				},
				nacdiaRegistroUsuario: {
					required: "Ingresa un d&iacute;a en la fecha de nacimiento.",
					number: "Ingresa un d&iacute;a en la fecha de nacimiento.",
					minlength: "El d&iacute;a debe ser de 2 d&iacute;gitos.",
					min: "Favor introduce un d&iacute;a v&aacute;lido.",
					max: "Favor introduce un d&iacute;a v&aacute;lido del mes."
				},
				nacmesRegistroUsuario: {
					required: "Ingresa un mes en la fecha de nacimiento.",
					number: "Ingresa un mes en la fecha de nacimiento.",
					minlength: "El mes debe ser de 2 d&iacute;gitos.",
					min: "Favor introduce un mes v&aacute;lido.",
					max: "Favor introduce un mes menor o igual a 12."
				},
				nacanoRegistroUsuario: {
					required: "Ingresa un a&ntilde;o en la fecha de nacimiento.",
					number: "Ingresa un a&ntilde;o en la fecha de nacimiento.",
					minlength: "El a&ntilde;o debe ser de 4 d&iacute;gitos.",
					min: "Favor introduce un a&ntilde;o superior a 1910.",
					max: "A&ntilde;o de nacimiento no permitido entre el rango."
				},
				emailRegistroUsuario:{
					required:"Ingresa una direcci&oacute;n de email.",
					email:"Ingresa una direcci&oacute;n de email v&aacute;lida."
				},
				emailRegistroUsuario2: {
					required:"Ingresa una direcci&oacute;n de email.",
					email:"Ingresa una direcci&oacute;n de email v&aacute;lida.",
					equalTo: "Los email ingresados deben ser igual."
				},
				regionRegistroUsuario: {
					required:"Favor selecciona una regi&oacute;n."
				},
				
				ciudadRegistroUsuario: {
					required: "Favor selecciona una ciudad."
				},
				comunaRegistroUsuario: {
					required: "Favor selecciona una comuna."
				},
				
				calleRegistroUsuario: {
					required:"Favor ingresa una calle."
				},
				numeroRegistroUsuario: {
					required: "Favor ingresa un n&uacute;mero."	
				},
				deptoCasaOtroRegistroUsuario: {
					required: "Favor ingresa departamento, casa u otro."	
				},
				relacionTitular: {
					required: "Favor seleccione su relaci&oacute;n con el titular."
				},
				prefijoTelAdicionalRegistroUsuario: {
					required: "Favor selecciona un prefijo."
				},
				telAdicionalRegistroUsuario: {
					required: "Favor introduce un n&uacute;mero.",
					minlength: "Favor introduce un n&uacute;mero v&aacute;lido."
				},
				terminosCondiciones: {
					required: "Debes aceptar los t&eacute;rminos y condiciones antes de continuar."
				}
		}
	});
	
	
	// Seleccion por defecto de sexo al cargar form - radio button valor -> Hombre 
	//Opcion 1
	//$('input[name*=sexo]:eq(0)').attr('checked','checked');

	//Opcion 2
	$('input[name*=sexo]').each(function() {
			if($(this).val() == '1') { 
				$(this).attr('checked','checked'); 
			}
	});
	
	
	$(function() {
	    $('.emailRegistroUsuario2').bind('cut copy paste', function (e) {
	        e.preventDefault();
	        return false;
	    });
	});

	
	function showGloboRegistro(el) {
		var $input = $(el).parents('.campo:first');
		var punto = $input.offset();
		//alert(punto);
		var $globo = $('#globoError');
		
		if ($globo.is(':hidden')) {
			$globo.fadeIn(200, function() {
				$(el).focus();
				$globo.show();
		});
			$globo.fadeOut(0, function() {
				$globo.show();
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
		});
