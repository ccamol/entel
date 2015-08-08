$(document).ready(function() {
	
	//Configuraci?n de casos
	$(".caso_exito").hide();
	$(".caso_no_fondos").hide();
	$(".caso_eliminar").hide();
	//$(".caso_cambiar").show();
	$(".caso_nuevo").hide();
	$(".tablaComunik2").hide();
	
	//En caso de Ingresar un nuevo n?mero
	$(".ingresarNumero").click(function(){
		
		if($("form#ingresar").valid()) {
			//Ocultar los divs necesarios
			$(".caso_exito").hide();
			$(".caso_no_fondos").hide();
			$(".caso_eliminar").hide();
			$(".caso_ingresar").hide();
			$(".caso_nuevo").hide();
			
			//Mostrar los divs correspondientes
			$(".caso_cambiar").show();
		}
		return false;
	});
	
	//En caso de Editar (Cambiar) un n?mero ya existente
	$(".cambiarNumero").click(function(){
		//Ocultar los divs necesarios	
		$(".caso_exito").hide();
		$(".caso_no_fondos").hide();
		$(".caso_eliminar").hide();
		$(".caso_ingresar").hide();
		$(".caso_cambiar").hide();
		
		//Mostrar los divs correspondientes
		$(".caso_nuevo").show();
		$(".nf_inputAmarillo").val("");
		
		return false;
	});
	
	//En caso de querer eliminar un n?mero ya existente
	$(".eliminarNumero").click(function(){
		//Ocultar los divs necesarios	
		$(".caso_exito").hide();
		$(".caso_no_fondos").hide();
		$(".caso_nuevo").hide();
		$(".caso_ingresar").hide();
		$(".caso_cambiar").hide();
		
		//Ocultar Contenedor gris
		$(".numero_frecuente").hide();
		
		//Mostrar los divs correspondientes
		$(".caso_eliminar").show();
		
		return false;		
		
	});
	
	//En caso de aceptar la edici?n de un n?mero
	$(".nuevoEditarNumero").click(function(){
		
		if($('form[id*="cambiar"]').valid()) {
			//Ocultar los divs necesarios	
			$(".caso_eliminar").hide();
			$(".caso_no_fondos").hide();
			$(".caso_nuevo").hide();
			$(".caso_ingresar").hide();
			$(".caso_cambiar").hide();
			
			//Ocultar Contenedor gris
			$(".numero_frecuente").hide();
			
			//Mostrar los divs correspondientes
			$(".caso_exito").show();	
		}
		
		return false;
	});
	

	
	//En caso de cancelar la edici?n de un n?mero ya existente
	$(".cancelarEditarNumero").click(function(){
		//Ocultar los divs necesarios	
		$(".caso_exito").hide();
		$(".caso_no_fondos").hide();
		$(".caso_eliminar").hide();
		$(".caso_ingresar").hide();
		$(".caso_nuevo").hide();
		
		//Mostrar los divs correspondientes
		$(".caso_cambiar").show();
		
		return false;
	});
	
	//En caso de cancelar la eliminaci?n de un n?mero ya existente
	$(".cancelarEliminaNumero").click(function(){
		//Ocultar los divs necesarios	
		$(".caso_exito").hide();
		$(".caso_no_fondos").hide();
		$(".caso_eliminar").hide();
		$(".caso_ingresar").hide();
		$(".caso_nuevo").hide();
		
		//Mostrar el Contenedor gris
		$(".numero_frecuente").show();
		
		//Mostrar los divs correspondientes
		$(".caso_cambiar").show();
		
		return false;
	});
	
	//En caso de confirmar la eliminaci?n de un n?mero
	$(".aceptarEliminaNumero").click(function(){
		//Ocultar los divs necesarios	
		$(".caso_exito").hide();
		$(".caso_no_fondos").hide();
		$(".caso_eliminar").hide();
		$(".caso_cambiar").hide();
		$(".caso_nuevo").hide();
		
		//Mostrar el Contenedor gris
		$(".numero_frecuente").show();
		
		//Mostrar los divs correspondientes
		$(".caso_ingresar").show();
		$(".caso_default").show();
		
		$(".debes_saber_largo").hide();
				
		//Mostrar los divs correspondientes
		$(".nf_input").val("");
		return false;
	});
		
	//Oculta las filas de edici?n dentro de las tablas
	$(".edicion").hide();
	$(".edicion_aceptada").hide();
	$(".edicion_aceptada_mensaje").hide();
	
	//Despliega la fila de edici?n espec?fica dentro de la tabla
	$(".rechazar_edicion").click(function(){
		$(this).parent().parent().parent().parent().find('.edicion_previa').hide();
		$(this).parent().parent().parent().parent().find('.edicion').show();
		return false;
	});
	
	$(".cancelar_rechazar").click(function(){
		$(this).parent().parent().parent().parent().parent().parent().find('.edicion').hide();
		$(this).parent().parent().parent().parent().parent().parent().find('.edicion_previa').show();
		return false;
	});
	
	//Despliega el mensaje de aceptaci?n de requerimiento
	$(".cambiar_edicion").click(function(){
		$(this).parent().parent().parent().parent().find('.edicion_previa').hide();
		$(this).parent().parent().parent().parent().find('.edicion_aceptada').show();
		return false;
	});
	
	$(".cancelar_aceptar").click(function(){
		$(this).parent().parent().parent().parent().parent().parent().find('.edicion_aceptada').hide();
		$(this).parent().parent().parent().parent().parent().parent().find('.edicion_previa').show();
		return false;
	});
	
	$(".aceptar_invitacion_def").click(function(){
		$(this).parent().parent().parent().parent().parent().parent().find('.edicion_aceptada').hide();
		$(this).parent().parent().parent().parent().parent().parent().find('.edicion_aceptada_mensaje').show();
		return false;
	});
	
	$(".cancelar_aceptar_def").click(function(){
		$(this).parent().parent().parent().parent().parent().parent().find('.edicion_aceptada').hide();
		$(this).parent().parent().parent().parent().parent().parent().find('.edicion_previa').show();
		return false;
	});	
	
	//Eimina la fila de edici?n espec?fica dentro de la tabla
	$(".aceptar_rechazar").click(function(){
		//Borrar la fila de la tabla correspondiente
		$(this).parent().parent().parent().parent().parent().parent().find('.edicion').fadeOut(350);
		
		//Cambiar de colores la tabla (cambio de clases)
		$(this).parent().parent().parent().parent().parent().parent().parent().find('.par').removeClass("par").addClass("ipmar");
		
		var html = "<td>95195195</td><td>Recibida</td><td>18 / Septiembre / 2009 - 10:31hrs.</td><td>Rechazada</td>";
		
		$(".tablaComunik2").html(html);
		
		//Pintar la Tabla;
		$("tr.objetoTabla").removeClass().addClass('impar objetoTabla');
		$("tr.objetoTabla:nth-child(odd)").addClass("par");
		
		$(".tablaComunik2").fadeIn(350);
		
		return false;
	});
	
	//Eimina la fila de edici?n espec?fica dentro de la tabla
	$(".aceptar_rechazar2").click(function(){
		
		$(".mensaje_invitado_comunik2").slideUp(350);
		$(".tabla_invitado_comunik2").slideUp(350);
		
		return false;
	});
	
});