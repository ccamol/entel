$(document).ready(function() {
	
	/*---------------------------------
		Pintar selects amarillos
	---------------------------------*/
	
	$('select.selectBoxAmarillo').selectBoxAmarillo();
	zIndex = 500;
	$('.misDatos_fila').each(function() {
		$(this).css({
			position: 'relative',
			zIndex: zIndex--
		});
	});
	
	$("#centro div.mis-datos-guardar a").each(function() {
		var func = this.getAttributeNode('onclick').nodeValue;
		$(this).removeAttr('onclick').data("func", func);
	});
	
	/*---------------------------------
		Tabs
	---------------------------------*/
	$('ul.tabs').find('li:not(li.linkModificar)').each(function() {
		$(this).find('a').click(function() {
			
			//Cancelar edicion si esta abierta
			var linkModificar = $(this).parents('ul.tabs:first').find('li.linkModificar a');
			if(linkModificar.hasClass('activo')) {
				linkModificar.trigger('click');
			}
										
			//Pintar tab seleccionado
			$(this).parents('ul.tabs:first').find('li:not(li.linkModificar)').removeClass('seleccionado');
			$(this).parents('li:first').addClass('seleccionado');
			
			//Cambiar contenido
			var identificador = $(this).attr('href').toString();
			$(this).parents('ul.tabs:first').find('li:not(li.linkModificar)').find('a').each(function() {
																		  
				var tempID = $(this).attr('href').toString();
				if(tempID != identificador) {
					$(tempID).removeClass('oculto').hide();
				} else {
					$(tempID).removeClass('oculto').show();
				}
			});
			
			return false;
		});
	});
		
	/*---------------------------------
		Funcion modificar mis datos
	---------------------------------*/
	$('div.mis-datos-info').find('a.modificar').click(function() {
		if($(this).hasClass('activo')) {
			//Restaurar estado de link Modificar
			$(this).text('Modificar').removeClass('activo');
			
			//Mostrar mensaje boleta electronica
			if ($(this).parents('.icono-email').length) {
				if ($('.modificarChkBE').length) {
					$('.modificarChkBE').show();
				}
				if ($("input[id*=boletaElectronica]").is(':checked')) {
					$('.mis-datos-guardar-be').show();	
				}				
			}
			
			//Desplegar datos
			$(this).parents('.mis-datos-info').removeClass('modoEdicion')
				.find('.campo').each(function() {
					$(this).find('strong').show().end().find('.campo-amarillo').hide();						  
				});
				
			//Esconder boton guardar
			$(this).parents('.mis-datos-info').find('.mis-datos-guardar').hide();
			
			//Esconder globo Error
			$('#globoError').hide();
			
		} else {
			//Cambiar estado de link Modificar
			$(this).text('Cancelar').addClass('activo');
			
			//Ocultar mensaje boleta electronica (Modo edicion)
			if ($(this).parents('.icono-email').length) {
				if ($('.modificarChkBE').length) {
					$('.modificarChkBE').hide();
				}
				$('.mis-datos-guardar-be').hide();
			}
			
			//Desplegar campos
			$(this).parents('.mis-datos-info').addClass('modoEdicion')
				.find('.campo').each(function() {
					var inputVal = $(this).find('strong:first').hide().text();
					$(this).find('input:not(:radio)').val(inputVal).end().find('.campo-amarillo').show();
				});
				
			//Desplegar boton guardar
			$(this).parents('.mis-datos-info')
				.find('.mis-datos-guardar').show().click(function() {
						
					var form = $(this).parents('form');
					if($('form.'+form.attr('class')).valid()) {
																	 
						$(this).parents('.mis-datos-info:first').find('.campo').each(function() {
							var inputVal = $(this).find('input:checked').val() || $(this).find('.campo-amarillo input').val() || $(this).find('.campo-amarillo input:not(:radio), .campo-amarillo input:checked, .campo-amarillo select').val() || $(this).find('.campo-amarillo span, .campo-amarillo strong').text();
							$(this).find('strong').html(inputVal).show().end().find('.campo-amarillo').hide();
						});
						
						$(this).parents('.mis-datos-info:first').find('.campo').hide();
						
						//Valida: ejecuto lo que estaba en el atributo onclick de este "command" de JSF.
						var $a = $(this).find('a:first');
						eval($a.data("func").replace('return false', ''));
						
						$(this).parents('.mis-datos-info:first').removeClass('modoEdicion').find('a.modificar').text('Modificar').removeClass('activo').end();
						$(this).parents('.mis-datos-info:first').find('.mis-datos-guardar').hide();
						
						$('#globoError').fadeOut();	
					}
					
					return false;
				});
		}
		
		return false;
	});
	
	$('input[id*=fecha_nacimiento]').datepicker({
	    showOn: "both",
	    buttonImage: '../framework/skins/mientel/img/icons/icon_calendar_red.gif',
	    buttonImageOnly: true,
	    buttonText: 'Selecciona una fecha',
	    dateFormat: 'dd/mm/yy',
		showAnim: 'show',
		maxDate: '+0d',
		changeMonth: true,
		changeYear: true,
		yearRange: "-100:+0"
	});
	$("img.ui-datepicker-trigger").css({
		'cursor' : 'pointer',
		'vertical-align' : 'bottom',
		'left' : '5px',
		'top' : '5px',
		'position' : 'relative'
	});
	//fix para IE7, span derecho de datepicker
	if ($.browser.msie && $.browser.version == '7.0') {
		$("#sr-fecha_nacimiento").css({
			'margin' : '-16px 0 0 0'			
		});
		$("img.ui-datepicker-trigger").css({
			'left' : '10px'
		});
	}
	
});


function soloNumeros(evt){
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 31 || (key >= 48 && key <= 57));
}

function soloLetras(evt) {
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 31 || (key >= 65 && key <= 90) || (key >= 97 && key <= 122) || (key == 209 || key == 241) || (key == 225 || key == 193) || (key == 233 || key == 201) || (key == 237 || key ==205) || (key == 243 || key == 211) || (key == 250 || key == 218) || key == 46 || (key <= 40 ) && ( key >= 37 )); 
}
