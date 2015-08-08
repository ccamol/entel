var timerGlobo;
$(document).ready(function() {
	// Agrego globo para mensajes de error en el body
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
		.css({top: "-999px", left: "-999px", position: 'absolute' })
		.appendTo('body');
		
	$('#globoError').hide();
	/*---*/
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();			
		//if($(this).get(0).disableError) $(this).get(0).disableError();						 
	});
	
	/*$('.continuar_multitiendas').click(function(){
		//alert("click")		
		//$(".formMultitienda").submit();
		//alert("SD");
		//return false;
	});*/

	/*------------------------------------
		Validacion 
	------------------------------------*/	
	var validatorMultitiendaConf = {
		onkeyup: false,
		onfocusout: false,
		errorPlacement: function(error, element) {
		
			maxlongitud = parseInt($(".ampliacionNumerica").attr('maxlength'));		         
	        jQuery.validator.addMethod('numero_prepago_multi', function(value, element){ 
	    		if($(element).val().length < maxlongitud ) {
	    			return false;
	    		}
	    		else {
	    			return true;
	    		}
	    		return false;
	    	}, 'Ingrese un numero valido.');
			
			var $form = element.parents('form');
			var firstError = $form.validate().errorList[0].message;
			
			$('#globoError').find('td:first').html(firstError);
			showGloboMultitienda($form.validate().errorList[0].element);
		}			
	};		
	
	$(".formMultitienda").each(function() {		
		$(this).validate(validatorMultitiendaConf);
	});
		
	I2BClassValidator({
		rules: {
			tarjeta_multitienda: {
				required: true				
			},
			numero_prepago_multi: {
				required: true
				//minlength:8
				//EPCS: true 	//Simula no ser un numero EPCS: 55555555
			},
			numero_tarjeta: {
				required: true,
				minlength: 1
			},
			clave: {
				required: true,
				minlength: 4
			},
			monto_recarga: {
				required: true
			},
			cuotas:{
				required: true
			}
		},
		messages: {
			tarjeta_multitienda:{
				required:"Selecciona una tarjeta de multitienda",
				min:"Selecciona una tarjeta de multitienda"
			},
			numero_prepago_multi:{
				required:"Ingrese un n&uacute;mero."
				//minlength:"Ingrese un n&uacute;mero v&aacute;lido."
			},
			numero_tarjeta:{
				required:"Ingresa un n&uacute;mero de tarjeta",
				minlength:"Ingresa un n&uacute;mero de tarjeta"
			},
			clave:{
				required:"Ingresa tu clave secreta",
				minlength:"Ingresa tu clave secreta, son 4 digitos."
			},
			monto_recarga: {
				required: "Selecciona un monto"
			},
			cuotas:{
				required: "Selecciona el numero de cuotas"
			}
		}
	});

});

function showGloboMultitienda(el) {
	pintaCampoRojo(el);
	
	if ($('#globoError').length < 1) { 
		var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>') 
			.css({top: "-999px", left: "-999px", position: 'absolute' }) 
			.appendTo('body'); 
			 
		$('#globoError').hide(); 
 
	 
	} 
	 
	 
	//pintaCampoRojo(el); 
	//var $input = $(el).parents('.fila_campo').find('div.campo:last');//.find('div:last'); 
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
			'left': parseInt($input.width()) + 5 
		});					   
	}); 
	 
	return false;
}