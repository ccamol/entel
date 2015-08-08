$(document).ready(function(){	
	
	  if( navigator.userAgent.indexOf('Safari/')  != -1 && navigator.userAgent.indexOf('Chrome/')  == -1 )  
		{ $('.codigo_tel_adicional').css('width','75'); }
		 else
		{ $('.codigo_tel_adicional').css('width','50'); }
	
	// Variable para el titulo
	var titulo = "";
	if ($("#seleccion_fecha").length) {
		$("#seleccion_fecha").datepicker('hide');
		
		$("#seleccion_fecha").datepicker({ 
			showOn: 'button', 
			buttonImage: '/personas/framework/skins/mientel/img/icons/icon_calendar_red.gif',
			buttonText: 'Selecciona una fecha',
			buttonImageOnly: true,
			showAnim: 'show',
			maxDate: '+0d'
		});
	}
	$("#seleccion_fecha").blur(function(){
		$('#globoError').hide();
	});
	// Para los combo select
	//$(".hora_robo").selectBox();

	// Para los input
	//$('input.inputBox').inputBox();
	$('input.inputBoxAmarillo').inputBoxAmarillo();
	
	//Codigo para ubicacion mediante select
	/*$(".sel-estas-consultando").change(function(){
		var url = window.location.href;
		//url = url.match(/([^_])+$/)[0];
		var url_1 = url.split('_');
		if(url_1.length > 1) {
			url_1 = url_1[0];
			var url_2 = url.split(url_1)[1];
			window.location.href =  $(this).val()+url_2;
		}
	});		
	
	var url = window.location.href;
	url = url.split('/')[url.split('/').length-1].split('_');
	if(url.length > 1) {
		url = url[0];
		url = (url=='pp'||url=='cc')?url:'ss';
		$(".sel-estas-consultando").get(0).setValue(url);
	}*/	
	
	$(".hora_robo").change(function(){
		$('#globoError').hide();
	});
	
	// Despliega bloque por robo o por extravio para Suscripcion
	$("input[name=bloqueo]").click(function(){
		var tipoBloqueo = $(this).attr('id');
		$("#debes_saber_inicial").hide();
		$("#bloqueo_1").show();
		$.scrollTo('#bloqueo_1', 500, {offset: { top: -10 } } );
		if ( tipoBloqueo == "bloqueo_robo" ){
			titulo = "Bloqueo por robo o hurto";
			$("h2.sin_icono").text(titulo);
			$("#datos_robo").show();
			$("#bloqueo_3").find("div#equipo_modelo").addClass("formulario_item");
			$("#bloqueo_3").find("div#equipo_modelo").removeClass("formulario_item_oculto");
			$("#bloqueo_3").find("div#equipo_serie").addClass("formulario_item");
			$("#bloqueo_3").find("div#equipo_serie").removeClass("formulario_item_oculto");
			$("#bloqueo_3").find("div#fecha_hora").addClass("formulario_item");
			$("#bloqueo_3").find("div#fecha_hora").removeClass("formulario_item_oculto");
			$(".formulario_bloqueo_1_extravio").hide();
			$(".formulario_bloqueo_1").show();
		}
		if ( tipoBloqueo == "bloqueo_extravio" ){
			titulo = "Bloqueo por extrav\xEDo";
			$("#globoError").hide();
			$("h2.sin_icono").text(titulo);
			$("#datos_robo").hide();
			$("#bloqueo_3").find("div#equipo_modelo").removeClass("formulario_item");
			$("#bloqueo_3").find("div#equipo_modelo").addClass("formulario_item_oculto");
			$("#bloqueo_3").find("div#equipo_serie").removeClass("formulario_item");
			$("#bloqueo_3").find("div#equipo_serie").addClass("formulario_item_oculto");
			$("#bloqueo_3").find("div#fecha_hora").removeClass("formulario_item");
			$("#bloqueo_3").find("div#fecha_hora").addClass("formulario_item_oculto");
			$(".formulario_bloqueo_1").hide();
			$(".formulario_bloqueo_1_extravio").show();
		}
	});
	
	// Despliega bloque por robo o por extravio para Cuenta Controlada y Prepago
	$("input[name=bloqueo_especial]").click(function(){
		var tipoBloqueo = $(this).attr('id');
		$("#debes_saber_inicial").hide();
		$("#bloqueo_1").show();
		$.scrollTo('#bloqueo_1', 500, {offset: { top: -10 } } );
		if ( tipoBloqueo == "bloqueo_robo" ){
			titulo = "Bloqueo por robo o hurto";
			$("#bloqueo_3").find("div#equipo_modelo").addClass("formulario_item");
			$("#bloqueo_3").find("div#equipo_modelo").removeClass("formulario_item_oculto");
			$("#bloqueo_3").find("div#equipo_serie").addClass("formulario_item");
			$("#bloqueo_3").find("div#equipo_serie").removeClass("formulario_item_oculto");
			$("#bloqueo_3").find("div#fecha_hora").addClass("formulario_item");
			$("#bloqueo_3").find("div#fecha_hora").removeClass("formulario_item_oculto");
			$("#globoError").hide();
			$("#bloqueo_2").hide();
			$("#bloqueo_2").find(".pasos:first").text("Paso 2 de 3");
			$("h2.sin_icono").text(titulo);
			$("#datos_robo").show();
			$(".formulario_bloqueo_1_extravio").hide();
			$(".formulario_bloqueo_1").show();
		}
		if ( tipoBloqueo == "bloqueo_extravio" ){
			titulo = "Bloqueo por extrav\xEDo";
			$("#globoError").hide();
			$("h2.sin_icono").text(titulo);
			$("#datos_robo").hide();
			$(".formulario_bloqueo_1").hide();
			$("#bloqueo_1").hide();
			$("#bloqueo_2").find(".pasos:first").text("Paso 1 de 2");
			$("#bloqueo_2").find(".link_volver").hide();
			$("#bloqueo_3").find("div#equipo_modelo").removeClass("formulario_item");
			$("#bloqueo_3").find("div#equipo_modelo").addClass("formulario_item_oculto");
			$("#bloqueo_3").find("div#equipo_serie").removeClass("formulario_item");
			$("#bloqueo_3").find("div#equipo_serie").addClass("formulario_item_oculto");
			$("#bloqueo_3").find("div#fecha_hora").removeClass("formulario_item");
			$("#bloqueo_3").find("div#fecha_hora").addClass("formulario_item_oculto");
			$("#bloqueo_2").show();
		}
	});	
	
	// Desplegar paso 2 de robo o extravio
	$("#bloqueo_paso_2").unbind().click(function(){
		bloqueoPaso2();
	});
	
	//Mostrar/Ocultar la fila para actualizar emial
	$(".mostrar_actualizar_email").click(function(){
		$('#globoError').hide();
		var email = $(".fila_email").find("strong:first").text();
		if ($(".fila_email").is(':visible')){
			$(".fila_email").hide();
			$(".fila_actualizar_email").show();
			$(".email_clave").val(email);
		}
		else{
			$(".mensajeError").unbind().remove();
			$(".fila_email").show();
			$(".fila_actualizar_email").hide();		
			$(".fila_email").find("strong:first").text(email);
		}
		return false;
	});
	
	// Aceptar la actualizacion de email
	$("#aceptar_actualizar_email").click(function(){
		if (validarEmail()){
			var email = $(".email_clave").val();
			$(".fila_email").show();
			$(".fila_actualizar_email").hide();
			//$(".fila_email").find("strong:first").text(email);
			$(".formulario_input_texto:first").html('<strong>'+email+'</strong>');
		}
		return false;
	});	
	
	$(".email_clave").click(function(){
		$(".mensajeError").unbind().fadeOut(200,function(){
			$(this).remove();
		});
	});

	// Muestra/Oculta el boton activado
	$("#aceptar_condiciones").click(function(){
		var estado = $(this).is(':checked');
		if ( estado ){
			$(".boton_desactivado").hide();
			$(".boton_activado").show();
		}
		else{
			$(".boton_desactivado").show();
			$(".boton_activado").hide();
		}
	});
	
	// Mostrar el mensaje para robo o extravio
	$(".bloquear_equipo").click(function(){
		bloqueoMensaje();
	});
	
	//Mostrar paso 2 de desbloqueo
	$(".desbloqueo_continuar").click(function(){
		if ($("#desbloqueo_1").is(':visible')){
			$("#desbloqueo_1").hide();
			$("#desbloqueo_2").show();
		}
		else{
			$("#desbloqueo_1").show();
			$("#desbloqueo_2").hide();
			$("#globoError").hide();
		}
		return false;
	});
	
	//Mostrar bloque con el historial
	$(".mostrar_historial").unbind().click(function(){
		var enlace = $(this).parents(".celda_tabla_derecha:first").find(".ver_historial");
		var cerrar = $(this).parents(".celda_tabla_derecha:first").find(".bloque_historial_cerrar");
		var span = $(this).parents(".estado:first").find("span");
		var historial = $(this).parents(".estado:first").next(".bloque_historial:first");
		if ( !historial.is(':visible') ){
			$(this).parents(".estado:first").css({
				'border-bottom':'1px solid #cfcdcd'
			})
			enlace.hide();
			cerrar.show();
			historial.show();
			span.hide();
		}
		else{
			$(this).parents(".estado:first").css({
				'border-bottom':'none'
			})
			enlace.show();
			cerrar.hide();
			historial.hide();
			span.show();
		}
		return false;
	});
	
	// Muestra el bloque para evaluar el presupesto
	$(".evaluar_presupuesto").unbind().click(function(){
		if ( !$("#presupuesto").is(':visible') ){
			$("#tablas_presupuesto").hide();
			$("#presupuesto").show();
		}
		else{
			$("#tablas_presupuesto").show();
			$("#presupuesto").hide();		
		}
		return false;
	});
	
	// Aceptar el presupesto
	$("#aceptar_presupuesto").click(function(){
		if ( $("#presupuesto").is(':visible') ){
			$("#tablas_presupuesto").show();
			$("#tabla_pendiente").hide();
			$("#tabla_aceptado").show();
			$("#mensaje_presupuesto_aceptado").show();
			$("#presupuesto_botones").hide();
			$("#presupuesto").hide();
		}
		return false;
	});
	
	// Rechazar el presupesto
	$("#rechazar_presupuesto").click(function(){
		if ( $("#presupuesto").is(':visible') ){
			$("#tablas_presupuesto").show();
			$("#tabla_pendiente").hide();
			$("#tabla_rechazado").show();
			$("#mensaje_presupuesto_rechazado").show();
			$("#presupuesto_botones").hide();
			$("#presupuesto").hide();
		}
		return false;
	});	
	
	//Valida que solo se ingresen caracteres numericos
	$(".input_numerico").keypress(function(evt){
		var key = evt.keyCode ? evt.keyCode : evt.which ;
		return (key <= 31 || (key >= 48 && key <= 57)); 
	});
	
});

// Despliega el paso 2 para robo o extravio
function bloqueoPaso2(){
	if ($("#seleccion_fecha")) {
		$("#seleccion_fecha").datepicker('hide');
	}
	if ($("#bloqueo_1").is(':visible')){
		$("#bloqueo_0").hide();
		$("#bloqueo_1").hide();
		$("#bloqueo_2").show();
		$.scrollTo('#contenido', 500, {offset: { top: -10 } } );
	}
	else{
		$("#globoError").hide();
		$(".mensajeError").remove();
		$("#bloqueo_0").show();
		$("#bloqueo_1").show();
		$("#bloqueo_2").hide();
		$("input[name=clave_desbloqueo]").val("");
		$("input[name=re_clave_desbloqueo]").val("");
		$("input[name=telefono_contacto]").val("");
		$.scrollTo('#contenido', 500, {offset: { top: -10 } } );		
	}
	//return false;
}

// Despliega el paso 3 para robo o extravio
function bloqueoPaso3(){
	if ($("#bloqueo_2").is(':visible')) {
		if ( $("#bloqueo_0").is(':visible') ){
			$("#bloqueo_2").hide();
			$("#bloqueo_0").hide();
			$("#bloqueo_3").find(".pasos:first").text("Paso 2 de 2");
			$("#bloqueo_3").show();
			$.scrollTo('#contenido', 500, {offset: { top: -10 } } );
		}
		else{
			$("#bloqueo_2").hide();
			$("#bloqueo_3").show();
			$.scrollTo('#contenido', 500, {offset: { top: -10 } } );			
		}
	}
	else{
		if ( $("#bloqueo_0").is(':visible') ){
			$("#bloqueo_2").show();
			$("#bloqueo_0").show();
			$("#bloqueo_3").find(".pasos:first").text("Paso 2 de 2");
			$("#bloqueo_3").hide();
			$.scrollTo('#contenido', 500, {offset: { top: -10 } } );
		}
		else{
			if ($("#bloqueo_3").find(".pasos:first").text() == "Paso 2 de 2") {
				$("#bloqueo_0").show();
			}
			else {
				$("#bloqueo_0").hide();
			}
			$("#bloqueo_2").show();
			$("#bloqueo_3").hide();
			$.scrollTo('#contenido', 500, {offset: { top: -10 } } );			
		}		
	}
	//return false;
}

// Despliega el mensaje para robo o extravio
function bloqueoMensaje(){
	if ( $("#bloqueo_3").is(':visible') ){
		$("#bloqueo_3").hide();
		$("#bloqueo_4").show();
		$.scrollTo('body', 500, {offset: { top: -10 } } );
	}
	return false;
}

// Mustra el mensaje de desbloqueo
function desbloqueoMensaje(){
	if ($("#desbloqueo_2").is(':visible')){
		$("#desbloqueo_2").hide();
		$("#desbloqueo_3").show();
	}
	return false;
}

function validarEmail(){
	if($(".fila_actualizar_email").is(':visible')){
		var email = $(".email_clave").val();
		var input = $('.fila_actualizar_email').find('.formulario_error');
		if (email == ""){
			var globo = ('<div class="mensajeError textoError"><table><tr><td class="body">Ingresa una direcci&oacute;n de email.</td></tr></table></div>');
			mostrarError(globo,input);
			return false;
		}
		else{
			$(".mensajeError").fadeOut(200,function(){
				$(this).remove();
			});
			if(!validarFormatoEmail(email)){
				var globo = ('<div class="mensajeError textoError"><table><tr><td class="body">Ingresa una direcci&oacute;n de email v&aacute;lida.</td></tr></table></div>');
				mostrarError(globo,input);
				return false;
			}
			else{
				$(".mensajeError").fadeOut(200,function(){
					$(this).remove();
				});
				return true;
			}
		}
	}
	else{
		return true;
	}
}

function validarFormatoEmail(email){
	if(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(email)) {
		return true; 
	}
	else{
		return false;
	}
}

function mostrarError(globo,input){
	input.append(globo);
	input.find(".mensajeError").css({
		'top':-15,
		'left':205
	});
	input.find(".mensajeError").fadeIn(200,function(){
		$("#globoError").hide();
	});
	return false;
}

