$(document).ready(function(){
	
	$('.tabZonaPunto').click(function(){
		$(".autoTooltip.activo").trigger("click");
	});
	
	var numero1;		
	
	$('input.inputBox').inputBox();
	$('select.selectMontoRecarga').selectBox();
	$('select.selectPeriodo').selectBox();
	
	$('.fila_estado2').hide();
	$('.fila_estado4').hide();				
	
	/*
	 * Manejo de estados
	 */
	
	// Boton hacia estado inicial de canje
	$('.ir_estado1').click(function(){
		var marcador = $(this).parents('.marcador_fila:first');
		marcador.find('.inputBox').val("");
		marcador.find('.fila_estado1').show();
		marcador.find('.fila_estado2').hide();
		marcador.find('.fila_estado4').hide();			
		
		//Quitar mensajes de exito y error
		marcador.find('.resultados_recarga').hide();
		marcador.find('.resultados_bolsa').hide();
		marcador.find('.mensaje_exito_recarga, .mensaje_error_recarga, mensaje_exito_bolsa, mensaje_error_bolsa').hide();
		
		// Volver visible las opcion de canje o recarga		
		if(marcador.parents('#div_bolsas_canje').length > 0){			
			$('#ul_bolsas_canje').show();
			$('#cont-pasos-bolsas').html('Paso 1 de '+cantPasos);
		}
		else{
			$('#cont-pasos-recargas').html('Paso 1 de '+cantPasos);
		}
		marcador.parents('.tabla_bolsas').find('.marcador_fila').not(marcador).show();
		
		return false;
	});
	
	// Boton hacia confirmacion para canjear
	$('.ir_estado2').click(function(){			
		ir_estado2(this);
		return false;
	});		
	
	// Boton hacia loading canje
	$('.ir_estado3').click(function(){
		var marcador = $(this).parents('.marcador_fila:first');			
		marcador.find('.fila_estado2').hide();					
		
		return false;
	});
	
	// Boton hacia formulario canje
	$('.ir_estado4').click(function(){
		var marcador = $(this).parents('.marcador_fila:first');		
		marcador.find('.fila_estado4').show();				
		
		marcador.find('.fila_estado1').hide();
		marcador.find('.fila_estado2').hide();
		
		// Dejar visible solo la opcion de canje o recarga			
		if(marcador.parents('#div_bolsas_canje').length > 0){			
			$('#ul_bolsas_canje').hide();
			$('#cont-pasos-bolsas').html('Paso 2 de '+cantPasos);
		}
		else{
			$('#cont-pasos-recargas').html('Paso 2 de '+cantPasos);
		}
		marcador.parents('.tabla_bolsas').find('.marcador_fila').not(marcador).hide();			
		
		return false;
	});			
	
});

// Funcion separada porque no siempre se va a generar el 'a' con clase ir_estado2
function ir_estado2(e){
	// Esconder demas opciones de canje - recarga		
	var fila = $(e).parents('.marcador_fila:first');
	fila.find('.fila_estado2').show();
	fila.find('.fila_estado1').hide();
	fila.find('.fila_estado4').hide();		
	
	var nombreBolsa = fila.find('.desc_producto').val();
	var numeroDestino = fila.find('.numero_destino_canje').val();
	var puntos = fila.find('.info_producto').val().split('|')[1];
	var monto = fila.find('.info_producto').val().split('|')[2];
	
	// Texto de confirmacion
	var textoConfirmacion = '';
	if(fila.is('.fila_bolsa')){
		textoConfirmacion = textoConfirmacionBolsa.replace('(nombre)',nombreBolsa).replace('(numero)',numeroDestino).replace('(puntos)',puntos);
		fila.find('.mensaje_confirmacion').text(textoConfirmacion);
	}
	else{
		textoConfirmacion = textoConfirmacionRecarga.replace('(monto)',monto).replace('(numero)',numeroDestino).replace('(puntos)',puntos);
		fila.find('.mensaje_confirmacion').text(textoConfirmacion);
	}		
	
	// Dejar visible solo la opcion de canje o recarga y aumentar paso			
	if(fila.parents('#div_bolsas_canje').length > 0){			
		$('#ul_bolsas_canje').hide();
		if(esPP == '1'){
			$('#cont-pasos-bolsas').html('Paso 2 de '+cantPasos);
		}
		else{
			$('#cont-pasos-bolsas').html('Paso 3 de '+cantPasos);
		}
	}
	else{
		if(esPP == '1'){
			$('#cont-pasos-recargas').html('Paso 2 de '+cantPasos);
		}
		else{
			$('#cont-pasos-recargas').html('Paso 3 de '+cantPasos);
		}			
	}
	fila.parents('.tabla_bolsas').find('.marcador_fila').not(fila).hide();
}