var timerGlobo;
$(document).ready(function(){
	
	//$('select.selectBoxAmarillo').selectBoxAmarillo();
		
	// Agrego globo para mensajes de error en el body
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	//
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
	
	$('.selectBox').live("change", function () {
		$('#globoError').fadeOut();
		if ($(this).get(0).disableError) 
			$(this).get(0).disableError();
        
    });
	
	
	$('.checkBox').live("click", function () {
		if($(this).is(':checked')){
			$('#globoError').fadeOut();
				if ($(this).get(0).disableError) 
					$(this).get(0).disableError();
			}
    });
	
	jQuery.validator.addMethod('fecha', function(value, element, params) {
		if(!$(element).val() || (/^[0-9]{2}\/[0-9]{2}\/[0-9]{4}/.test(element.value))) {
			return check_date(element.value);
		}
		return false;
	}, 'Ingresa una fecha de nacimiento v&aacute;lida.');

	//-----------------------------------------------
	//	Validacion Datos De Horario Seleccionado
	//-------------------------------------------------
	
	jQuery.validator.addMethod('noCero', function(value, element, params) {
		var swTmp = false;
		if(value != 0){
			swTmp = true;	
		}		
		return swTmp;
	}, 'Seleccione una hora.');
	
	jQuery.validator.addMethod('desdeHasta', function(value, element, params) {
		var swTmp = false;
		var value2 = $('select[name="seh_desde"]').val();		
		if(value != 0 && value2 != 0){
			if(parseInt(value) > parseInt(value2)){
				swTmp = true;
			}
		}		
		return swTmp;
	}, 'Seleccione Desde menor que hasta.');
	
	$("form#form_serv_mm_hora_especifica").validate({
		onkeyup: false,
		onfocusout: false,
		rules: {
			seh_desde:{
				required: true,
				noCero: true				
			},
			seh_hasta:{
				required: true,
				noCero: true,
				desdeHasta: true
			},
			seh_dia:{
				required: true
			},
			seh_condiciones:{
				required: true
			}
		},
		messages: {
			seh_desde:{
				required:"Seleccione hora desde.",
				noCero:"Seleccione hora desde."
												
			},
			seh_hasta:{
				required:"Seleccione hora hasta.",
				noCero:"Seleccione hora hasta.",
				desdeHasta:"Seleccione hasta mayor que desde."
			},
			seh_dia:{
				required:"Seleccione por lo menos un día."
				
			},
			seh_condiciones:{
				required:"Acepte las Condiciones."
			}	
		},
		errorPlacement: function(error, element) {			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGloboMMKT($form.validate().errorList[0].element);
		}
	});
	
	$('form#form_serv_mm_horario').validate({
		onkeyup: false,
		onfocusout: false,
		rules: {			
			seh_condiciones:{
				required: true
			}
		},
		messages: {			
			seh_condiciones:{
				required:"Acepte las Condiciones."
			}	
		},
		errorPlacement: function(error, element) {			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGloboMMKT($form.validate().errorList[0].element);
		}
	});
	
	
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
	
	// Delete all chars except 0..9 
	for(i = 0; i < DateValue.length; i++) {
		if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		 	DateTemp = DateTemp + DateValue.substr(i,1);
		}
	}
	DateValue = DateTemp;
	
	// Always change date to 8 digits - string
	// if year is entered as 2-digit / always assume 20xx 
	if (DateValue.length == 6) {
	  DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); 
	}
	if(DateValue.length != 8) {
	  err = 19;
	}
	  
	// year is wrong if year = 0000 
	year = DateValue.substr(4,4);
	if (year == 0) {
	  err = 20;
	}
	if(year > maxYear) {
		return false;
	}
	
	
	//Validation of month
	month = DateValue.substr(2,2);
	if ((month < 1) || (month > 12)) {
	  err = 21;
	}
	
	//Validation of day
	day = DateValue.substr(0,2);
	if (day < 1) {
	 err = 22;
	}
	
	// Validation leap-year / february / day
	if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	  leap = 1;
	}
	
	if ((month == 2) && (leap == 1) && (day > 29)) {
	  err = 23;
	}
	if ((month == 2) && (leap != 1) && (day > 28)) {
	  err = 24;
	}
	
	//Validation of other months
	if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	  err = 25;
	}
	if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	  err = 26;
	}
	// if 00 ist entered, no error, deleting the entry
	if ((day == 0) && (month == 0) && (year == 00)) {
	  err = 0; day = ""; month = ""; year = ""; seperator = "";
	}
	// if no error, write the completed date to Input-Field (e.g. 13.12.2001)
	if (err == 0) {
	  //DateField.value = day + seperator + month + seperator + year;
	  return true;
	}
	// Error-message if err != 0 
	else {
	  return false;
	}
}