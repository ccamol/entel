var timerGlobo;
$(document).ready(function(){	

	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
	

    
	/*------------------------------------
		Validacion Datos Personales
	------------------------------------*/
	var validatorConf = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGloboMisDatos($form.validate().errorList[0].element);
		}
	};
	
	$("div.mis-datos-info form").each(function() {
		$(this).validate(validatorConf);
	});
	
	/*------------------------------------
		Validacion Email
	------------------------------------*/
	
	

	
	/*------------------------------------
		Validacion Direccion Promociones
	------------------------------------*/
	jQuery.validator.addMethod('calle', function(value, element){ 
		if($(element).val() != '') {
			return true;
		}
		else {
			return false;
		}
		return false;
	}, 'Ingresa una calle.');
	
	jQuery.validator.addMethod('numero', function(value, element){ 
		if($(element).val() != '') {
			return true;
		}
		else {
			return false;
		}
		return false;
	}, 'Ingresa un n&uacute;mero.');
	
	jQuery.validator.addMethod('select_regiones_bloqueo', function(value, element){ 
		if($('.select_regiones_bloqueo option:selected').html() != '' && $('.select_regiones_bloqueo option:selected').html()!= null && $('.select_regiones_bloqueo option:selected').html()!='Seleccione') {
		//if($(element).html() != '' && $(element).html()!= null && $(element).html()!='Seleccione' ) {
			return true;
		}
		else {
			return false;
		}
		return false;
	}, 'Favor seleccione una regi&oacute;n.');
	
	jQuery.validator.addMethod('select_ciudad_bloqueo', function(value, element){ 	
	   if($('.select_ciudad_bloqueo option:selected').html() != '' && $('.select_ciudad_bloqueo option:selected').html()!= null && $('.select_ciudad_bloqueo option:selected').html()!='Seleccione') {
		//if($(element).html() != '' && $(element).html()!= null && $(element).html()!='Seleccione' ) {
			return true;
		}
		else {
			return false;
		}
		return false;
	}, 'Favor seleccione una ciudad.');
	
	jQuery.validator.addMethod('select_comuna_bloqueo', function(value, element){ 		
		if($('.select_comuna_bloqueo option:selected').html() != '' && $('.select_comuna_bloqueo option:selected').html()!= null &&  $('.select_comuna_bloqueo option:selected').html()!='Seleccione') {
		//if($(element).html() != '' && $(element).html()!= null && $(element).html()!='Seleccione' ) {
		   return true;
		}
		else {
			return false;
		}
		return false;
	}, 'Favor seleccione una comuna.');
	
	
	/*------------------------------------
		Validacion Direccion Factura
	------------------------------------*/
	jQuery.validator.addMethod('direccion', function(value, element){ 
		if($(element).val() != '') {
			return true;
		}
		else {
			return false;
		}
		return false;
	}, 'Ingresa un n&uacute;mero.');
	
	
	/*------------------------------------
		Validacion Otros Datos
	------------------------------------*/
		
	jQuery.validator.addMethod('fecha', function(value, element){
		var el = $(element);
		var patt = /([0-9]{1,2})\/([0-9]{1,2})\/([0-9]{4})/;
		if (!patt.test(value)) {
			return false;
		} else {
			var arr = value.match(patt);
			var dia = parseInt(arr[1]);
			var mes = parseInt(arr[2]) - 1;
			var anio = parseInt(arr[3]);
			dteDate=new Date(anio,mes,dia);
			return ((dia==dteDate.getDate()) && (mes==dteDate.getMonth()) && (anio==dteDate.getFullYear()));
		}
		return false;
	}, 'Ingresa una fecha v&aacute;lida.');
	
	I2BClassValidator({
		rules: {
			nombre_1: {
				required: true,
				minlength: 2
			},
			apellido_1: {
				required: true,
				minlength: 2
			},
			apellido_2: {
				required: true,
				minlength: 2
			},
			email: {
				required: true,
				email: true,
				minlength: 5
			},
			email2: {
				required: true,
				email: true,
				minlength: 5,
				equalTo: '.email'
			},
			fecha_nacimiento: {
				required: true,
				fecha: true
			},
			calle: {
				required: true,
				minlength: 2
			},
			numero: {
				required: true,
				minlength: 1
			},
			direccion: {
				required: true,
				minlength: 4
			},
			fono_prefijo: {
				required: function() {
					return ($('.fono').val() != '');
				}
			},
			fono: {
				required: function() {
					return ($('.fono_prefijo').val() != '');
				},
				minlength: 6,
				maxlength: 8
			},
			select_regiones_bloqueo: {
				select_regiones_bloqueo:true
			},
			select_ciudad_bloqueo: {				
				select_ciudad_bloqueo:true
			},
			select_comuna_bloqueo: {				
				select_comuna_bloqueo:true
			}
			
		},
		messages: {
			nombre_1: {
				required: "Ingresa un nombre.",
				minlength: "Ingresa un nombre v&aacute;lido."
			},
			apellido_1: {
				required: "Ingresa un apellido.",
				minlength: "Ingresa un apellido v&aacute;lido."
			},
			apellido_2: {
				required: "Ingresa un apellido.",
				minlength: "Ingresa un apellido v&aacute;lido."
			},
			email:{
				required:"Ingresa un email v&aacute;lido.",
				minlength:"Ingresa un email v&aacute;lido.",
				email:"Ingresa un email v&aacute;lido."
			},
			email2:{
				required:"Ingresa un email v&aacute;lido.",
				minlength:"Ingresa un email v&aacute;lido.",
				email:"Ingresa un email v&aacute;lido.",
				equalTo: "Emails no coinciden."
			},
			fecha_nacimiento: {
				required: "Ingresa tu fecha de nacimiento.",
				fecha: "Ingresa una fecha v&aacute;lida en formato DD/MM/AAAA."
			},
			direccion:{
				required:"Ingresa una direcci&oacute;n.",
				minlength:"Ingresa una direcci&oacute;n."
			},
			calle:{
				required:"Ingresa una calle.",
				minlength:"Ingresa una calle."
			},
			numero:{
				required:"Ingresa un n&uacute;mero.",
				minlength:"Ingresa un n&uacute;mero."
			},
			fono_prefijo: {
				required: "Favor seleccione un prefijo."
			},
			fono: {
				required: "Favor introduce un n&uacute;mero.",
				minlength: "Favor introduce un n&uacute;mero v&aacute;lido.",
				maxlength: "Favor introduce un n&uacute;mero v&aacute;lido."
			},			
			select_regiones_bloqueo: {				
				select_regiones_bloqueo:"Favor seleccione una regi&oacute;n."
			},
			select_ciudad_bloqueo: {				
				select_ciudad_bloqueo:"Favor seleccione una ciudad."
			},
			select_comuna_bloqueo: {				
				select_comuna_bloqueo:"Favor seleccione una comuna."
			}
			
		}
	});
	/**/
	
});


function check_date(value){
	var checkstr = "0123456789";

	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var maxYear = 2009;
	var leap = 0;
	var err = 0;
	var i;
	
	err = 0;
	DateValue = value;
	
	/* Delete all chars except 0..9 */
	for(i = 0; i < DateValue.length; i++) {
		if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		 	DateTemp = DateTemp + DateValue.substr(i,1);
		}
	}
	DateValue = DateTemp;
	
	/* Always change date to 8 digits - string*/
	/* if year is entered as 2-digit / always assume 20xx */
	if (DateValue.length == 6) {
	  DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); 
	}
	if(DateValue.length != 8) {
	  err = 19;
	}
	  
	/* year is wrong if year = 0000 */
	year = DateValue.substr(4,4);
	if (year == 0) {
	  err = 20;
	}
	if(year > maxYear) {
		return false;
	}
	
	
	/* Validation of month*/
	month = DateValue.substr(2,2);
	if ((month < 1) || (month > 12)) {
	  err = 21;
	}
	
	/* Validation of day*/
	day = DateValue.substr(0,2);
	if (day < 1) {
	 err = 22;
	}
	
	/* Validation leap-year / february / day */
	if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	  leap = 1;
	}
	
	if ((month == 2) && (leap == 1) && (day > 29)) {
	  err = 23;
	}
	if ((month == 2) && (leap != 1) && (day > 28)) {
	  err = 24;
	}
	
	/* Validation of other months */
	if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	  err = 25;
	}
	if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	  err = 26;
	}
	/* if 00 ist entered, no error, deleting the entry */
	if ((day == 0) && (month == 0) && (year == 00)) {
	  err = 0; day = ""; month = ""; year = ""; seperator = "";
	}
	/* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	if (err == 0) {
	  //DateField.value = day + seperator + month + seperator + year;
	  return true;
	}
	/* Error-message if err != 0 */
	else {
	  return false;
	}
}

function showGloboMisDatos(el) {
	
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
			'left': $input.offset().left + parseInt($input.width()) + 5 
		});					  
	});
	
	return false;
}
