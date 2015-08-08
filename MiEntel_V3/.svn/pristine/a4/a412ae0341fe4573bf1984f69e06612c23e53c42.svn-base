$(document).ready(function(){

	//Para mostrar informacion exclusiva al titular de la cuenta
	var opcionSelect = readCookie("opcion");
	if (opcionSelect) {
		var tipo = opcionSelect.split('_')[0];
		var nivelAcceso = opcionSelect.split('_')[1];
		if (((tipo == "ss") || (tipo == "cc")) && (nivelAcceso == "titular")) {
			$("#pendiente_titular").css({'display':'block'});
			$("#pendiente_usuario").css({'display':'none'});
		}
		else {
			$("#pendiente_titular").css({'display':'none'});
			$("#pendiente_usuario").css({'display':'block'});
		}
	}

	// Se despliega la capa para desinscribir la facturación 	
	$("div.desinscribir a.desinscribir").click(function(){
		//oculta el link desincribir
		$(this).parents("div.desinscribir").prev().hide();
		//Hace aparecer el box
		$(this).parents("div.desinscribir").hide().next().show();
		$(this).parents(".cabecera:first").next().children(".box-numero").show();
	});

	// Se oculta la capa para desinscribir la facturación
	$("div.cancelar a.cancelar").click(function(){
		//Aparece nuevamente el link desinscribir
		$(this).parents("div.cancelar").hide().prev().show();
		
		//esconde el box
		$(this).parents(".cabecera:first").next().children(".box-numero").hide();
	});
	
	// Mostrar tooltip
	$(".ver_tooltip").click(function (){
		if ( document.getElementById("tooltip").style.display == "none" ){
			document.getElementById("tooltip").style.display = "block";
			return false;
		}
	});	
	
	// Cerrar tooltip
	$(".tooltip_cerrar").click(function (){
		if ( document.getElementById("tooltip").style.display == "block" ){
			document.getElementById("tooltip").style.display = "none";
			return false;
		}
	});
	
	// Desplegar/Ocultar el formulario de modificación del email
	$(".modificar_email").click(function (){
			$("#bloque_modificar").show();
			$("#bloque_estado").hide();
			$("#texto_modificar").show();
			$("#texto_estado").hide();	
	});
	$(".cancelar_modificar").click(function (){
			$("#bloque_modificar").hide();
			$("#bloque_estado").show();
			$("#texto_modificar").hide();
			$("#texto_estado").show();
			//BUG FIX: NO FUNCIONA SELECCIONAR POR ESTE ID PORQUE JSF LO MODIFICA
			//document.getElementById("email_1").value = null;
			$('email_01').val('');
			document.getElementById("email_2").value = '';
			$("#globoError").hide();
	});
	// Despliega la alerta de facturación electrónica desinscrita
	$(".desinscribir_email").click(function (){
		if ( document.getElementById("facturacion_desinscrita").style.display == "none" ){
			document.getElementById("facturacion_desinscrita").style.display = "block";
			document.getElementById("facturacion_inscrita").style.display = "none";
		}
	});	
	
	// Despliega el paso 1 del PAT
	$(".inscribe_pat").click(function (){
		document.getElementById("pago-automatico-paso1").style.display = "block";
		document.getElementById("pago-automatico").style.display = "none";
		document.getElementById("titulo_pat_cambio").innerHTML = "Pago autom&aacute;tico con tarjeta de Cr&eacute;dito"; 
	});
	
	// Cancelar el PAT
	$(".cancelar_pat").click(function (){
		document.getElementById("pago-automatico-paso1").style.display = "none";
		document.getElementById("pago-automatico").style.display = "block";
		document.getElementById("titulo_pat_cambio").innerHTML = "Pago autom&aacute;tico"; 
		$("#globoError").hide();
		$('input').val('');
		$('#tipo_tarjeta').next().find('input').val('Seleccione');		
	});	
	
	// Despliega el paso 3 del PAT
	$(".confirmar_pat").click(function (){
		document.getElementById("pago-automatico-paso3").style.display = "block";
		document.getElementById("pago-automatico-paso2").style.display = "none";
	});	
	
	// Continuar la reposición de servicio
	$(".reposicion_continuar").click(function(){
		document.getElementById("reposicion_paso_1").style.display = "none";
		document.getElementById("reposicion_paso_2").style.display = "block";
	});
	
	// Cancelar la reposición de servicio
	$(".reposicion_cancelar").click(function(){
		document.getElementById("reposicion_paso_1").style.display = "block";
		document.getElementById("reposicion_paso_2").style.display = "none";
	});	
	
	// Reponer el servicio
	$(".reposicion_servicio").click(function(){
		document.getElementById("reposicion_paso_3").style.display = "block";
		document.getElementById("reposicion_paso_2").style.display = "none";
		document.getElementById("pago-caja").style.display = "none";
	});
	
	// Link de TB para pago en línea
	$("#TB_pago_en_linea").click(function(){
		$('#TB_closeWindowButton').click(tb_remove);
		window.location = 'pago_cuenta.html';
	});
	
	$(".generar_cupon").click(function(){
		var nroInput = $(".tablaFacturacion").find("input[name=periodo]").length;
		
		var error = true;
		for (i=1;i<=nroInput;i++){ 
			if ($("#periodo_"+i).is(':checked')){
				error = false;
			}
		}
		
		if (error){
			$(".caja_amarilla").show();
			return false;
		}
		else{
			$(".caja_amarilla").hide();
			
			tb_show("","TB_cupon_pago.html?height=460&amp;width=570", false);
		}
	});
	
	$(".pagar").click(function(){
		var nroInput = $(".tablaFacturacion").find("input[name=folio]").length;
		
		var error = true;
		for (i=1;i<=nroInput;i++){ 
			if ($("#folio_"+i).is(':checked')){
				error = false;
			}
		}
		
		if (error){
			$(".caja_amarilla").show();
			return false;
		}
		else{
			$(".caja_amarilla").hide();
			// Desplagar los iframes para los pagos
			if ( (document.getElementById("pago_iframe").style.display == "none") ){
				document.getElementById("pago_iframe").style.display = "block";
				document.getElementById("pago_tabla").style.display = "none";
				document.getElementById("boton_pagar").style.display = "none";
			}				
		}		
	});	

});

// Despliega la alerta de facturación electrónica pre-inscrita
function modificar(){
	if ( document.getElementById("facturacion_modificada").style.display == "none" ){
		document.getElementById("facturacion_modificada").style.display = "block";
		document.getElementById("facturacion_inscrita").style.display = "none";
	}
}

// Despliega el paso 2 del PAT
function continuar_pat(){
	if ( document.getElementById("pago-automatico-paso1").style.display == "block" ){
		document.getElementById("pago-automatico-paso1").style.display = "none";
		document.getElementById("pago-automatico-paso2").style.display = "block";
		$("#globoError").hide();
	}
}

// Solo campo numerico
function soloNumeros(evt){
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 31 || (key >= 48 && key <= 57)); 
}

// Solo campo alfabetico
function soloAlfabetico(evt){
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 32 || (key >= 65 && key <= 90) || (key >= 97 && key <= 122) || (key >= 192 && key <= 256) ); 
}

// Solo para el rut
function soloRut(evt) {
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 31 || (key >= 48 && key <= 57) || key == 75 || key == 107); 
}