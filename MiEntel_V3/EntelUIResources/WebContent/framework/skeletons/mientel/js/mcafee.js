$(document).ready(function() {
	
	if(($("#mostrarPromoBanner").val() == "true" || $("#mostrarPromoSeguridad").val() == "true") 
			&& $("#mercado").val() == "CCSS"){
		/* Dejamos visible el Paso 1 de 2 y ocultamos todo lo demas */
		$('.caso_modificar').show();
		$(".tabla_inicial").hide();
		$(".email_editar").hide();
		$(".divs_activado").hide();
		$(".caso_paso2de2").hide();
		$(".caso_exito_final").hide();
		$(".paleta_desplegada").hide();
		$(".casilla_verde_reenviar").hide();
		$(".ancla_desactivado").hide();
		$('#caso_confirmar_compra').hide();
		
		/* Datos del plan promocional */
		if($("#mostrarPromoSeguridad").val() == "true"){
			if($('#valorPlanSelected').val() == "0"){
				$(".ancla_precio").html("Gratis");
			}else{
				$(".ancla_precio").html('$' + $('#valorPlanFormatted').val());
			}
			$("#aplicaPromo").val("true");
		}else{
			$(".ancla_precio").html("Gratis");
		}
		
		$(".ancla_plan").html($('#nombrePlanSelected').val());
		
		/* Botones de paso a Paso 2 de 2 */
		if($("#aplicaPromo").val() == "true"){
			$('#confirmar_paso1_disabled').hide();
			$('#confirmar_paso1_enabled').show();
		}else{
			$('#confirmar_paso1_disabled').show();
			$('#confirmar_paso1_enabled').hide();
		}
		
	}else{
		$(".caso_modificar").hide();
		$(".email_editar").hide();
		$(".divs_activado").hide();
		$(".caso_paso2de2").hide();
		$(".caso_exito_final").hide();
		$(".paleta_desplegada").hide();
		$(".casilla_verde_reenviar").hide();
		$(".ancla_desactivado").hide();
		$('#caso_confirmar_compra').hide();
	}
	
	
	$("a").click(function(){
		$(".no_email").hide();
		$(".email_invalido").hide();
	});
	
	$('.btnAzulGrandeDesactivado').click(function() {
		return false;
	});
	
	$("a.terminosBox").fancybox({
		   'overlayOpacity' : 0.5,
		   'overlayColor' : '#000000',
		   'hideOnContentClick' : false,
		   'hideOnOverlayClick' : false,
		   'padding' : 0,
		   'frameWidth'   : 630,
		   'frameHeight'   : 500,
		   'onComplete' : function(){$('.closer').click(function(){parent.$.fancybox.close();})}
	   
	   });
	
	/*
	 * Manejo de terminos y condicioes
	 */
	$('#terminos_condiciones').change(function(){
		if ($(this).is(':checked')) {
			$('#caso_confirmar_compra').show();
			$('#caso_confirmar_id').hide();
		}
		else if (!$(this).is(':checked')) {
			$('#caso_confirmar_compra').hide();
			$('#caso_confirmar_id').show();
		}
	});
	
	/*
	 * Ajuste de estilos para FireFox 2.0
	 */
	FF2=(function x(){})[-6]=='x';
	
	if(FF2){
		
		/*$(".ContenidoModificacion").css({
			'margin-top':'-11px'
		});*/
		
		$(".mensaje table").css({
			'margin-right':'95px'
		});
	}
	
	/*
	 * Pasar del caso default al caso modificar
	 */
	$(".caso_normal").click(function(){
		var nombrePlan = $(this).parents(".divs_fila:first").find(".contenido_tabla").text();
		var valorPlanFormatted = $(this).parents(".divs_fila:first").find(".dato_tabla_mcafee").text();
		var valorPlan = $(this).parents(".divs_fila:first").find("#valorPlan").val();
		var codigoPlan = $(this).parents(".divs_fila:first").find("#codigoPlan").val();
		var saldoSuficiente = $(this).parents(".divs_fila:first").find("#saldoSuficiente").val();
		var mercado = $("#mercado").val();
		
		if(valorPlanFormatted == "0" || valorPlanFormatted == ""){
			$(".ancla_precio").html("Gratis");
			$("#valorPlanSelected").val("0");
		}else{
			$(".ancla_precio").html(valorPlanFormatted);
			$("#valorPlanSelected").val(valorPlan);
		}
		$(".ancla_plan").html(nombrePlan);
		
		$("#codigoPlanSelected").val(codigoPlan);
		
		$(".tabla_inicial").hide();
		
		$(".contenido_encabezado").css({
			'display':'none'
		});
		
		if ($('.msjContratarH2').is(":hidden")){
			$('.msjContratarH2').show();
		}
		
		if(mercado == "CCSS"){
			var operacion = $(this).parents(".divs_fila:first").find("#operacion").val();
			var isPlanPromo = $(this).parents(".divs_fila:first").find("#isPlanPromo").val();

			$("#operacionPlanSelected").val(operacion);
			$("#isPlanPromoSelected").val(isPlanPromo);
			
			if(operacion != "Contratar"){
				$('.ancla_plan_actual').html($('#nombrePlanActual').val());
			}
			
			$('#confirmar_paso1_disabled').hide();
			$('#confirmar_paso1_enabled').show();
			
		}else{
			if($('.alerta_casilla_saldo').is(":visible")){
				$('#confirmar_paso1_disabled').show();
				$('#confirmar_paso1_enabled').hide();
			}else{
				if(saldoSuficiente != "true"){
					$('.faltaSaldo').show();
					$('#confirmar_paso1_disabled').show();
					$('#confirmar_paso1_enabled').hide();
				}else{
					$('#confirmar_paso1_disabled').hide();
					$('#confirmar_paso1_enabled').show();
				}
			}
		}
		
		
		
		$(".caso_modificar").css({
			'display':'block'
		});
		
		if ($('.caso_paso2de2').is(":visible")){
			$('.caso_paso2de2').hide();
		}
		
		return false;
	});
	
	$(".caso_cancelar_paso1").click(function(){
		
		$(".tabla_inicial").show();
		
		$(".contenido_encabezado").css({
			'display':'block'
		});	
		
		$(".caso_modificar").css({
			'display':'none'
		});	
		
		return false;
	});
	
	$(".caso_cancelar_paso2").click(function(){
		
		$(".caso_modificar").css({
			'display':'block'
		});	
		
		$(".caso_paso2de2").hide();
		
		return false;
	});
	
	$(".caso_cancelar_paso3").click(function(){
		
		$(".caso_exito_final").hide();
		$(".caso_paso2de2").show();
		
		return false;
	});
	
	$(".volver_inicio").click(function(){
		
		$(".caso_exito_final").hide();
		
		$(".tabla_inicial").show();
		$(".contenido_encabezado").css({
			'display':'block'
		});
		
		$(".caso_paso2de2").hide();
		
		return false;
	});
	
	/*
	 * Pasar del caso modificar al caso default
	 */
	$("a.caso_modificar").click(function(){
		
		SacarAlertas();
		
		$(this).parent().find(".caso_normal").show();
		$(this).hide();
		$(this).parent().parent().parent().parent().find(".caso_modificar").css({
			'display':'none'
		});
		return false;
	});
	
	/*
	 * Edicion de correo electrónico
	 */
	$(".email_editar_guardar").click(function(){
		SacarAlertas();
		
		var email = $(this).parent().parent().find(".email_edicion_input").val();
		
		if (email == "") {
			$(this).parent().parent().find(".no_email").show();
		}
		else if (!emailValido(email)) {
			$(this).parent().parent().find(".email_invalido").show();
		}
		else{
			/*Edicion de label para el correo*/
			$(".email_text").html(email);
			$(".ancla_email").html(email);		
			
			/*Llamado por Ajax*/
			actualizarEmail(email);
		}
		
		return false;
	});
	
	$(".btn_modificar_mail").click(function(){
		SacarAlertas();
		
		$(this).parents(".ContenidoModificacion:first").find(".email_editar").show();
		$(this).parents(".ContenidoModificacion:first").find(".email_no_editar").hide();
		
		$('.email_edicion_input').val($('.email1').text());
		
		return false;
	});
	
	$(".email_editar_cancelar").click(function(){
		
		$(this).parents(".ContenidoModificacion:first").find(".email_no_editar").show();
		$(this).parents(".ContenidoModificacion:first").find(".email_editar").hide();
		$(this).parents(".ContenidoModificacion:first").find(".email_edicion_input").val("");
		
		$("#actualizar-email-fracaso").hide();
		$("#actualizar-email-exito").hide();
		
		return false;
	});
	
	$(".email_edicion_input").keypress(function(){
		$(".mensaje").hide();
	});
	
	/*
	 * Confirmar y Activar
	 */
	$(".caso_confirmar").click(function(){
		
		if ($(".email_edicion_input").is(":visible")) {
		
			var email = $(".email_edicion_input").val();
			
			if (email == "") {
				$(".no_email").show();
			}
			else if (!emailValido(email)) {
				$(".email_invalido").show();
			}
			else {
				/*Edicion de label para el correo*/
				$(".email_edicion_input").val("");
				$(".email_text").html(email);
				
				$(".caso_paso2de2").show();
				$(".caso_modificar").hide();
			}
			
		}
		else{
			if ($('.email_text.email2').html() == ""){
				$('.email_text.email2').html($('.email_text.email1').html());
			}
			
			$(".caso_paso2de2").show();
			$(".caso_modificar").hide();
			
			if ($('.botones_caso_paso2de2').is(":hidden")){
				$('.botones_caso_paso2de2').show();
			}
			if ($('.contenedor_condiciones').is(":hidden")){
				$('.contenedor_condiciones').show();
			}
			if ($('.enlace_caso_paso2de2').is(":visible")){
				$('.enlace_caso_paso2de2').hide();
			}
			if ($('.mensaje-error-pequeno').is(":visible")){
				$('.mensaje-error-pequeno').hide();
			}
			
			if($('#operacionPlanSelected').val() != "Contratar"){
				$('.plan_actual_paso2').show();
			}
		}
			
		return false;
	});
	
	$(".btn_desactivar_activacion").click(function(){
		
		SacarAlertas();
		
		$(this).parents(".divs_fila:first").prev(".divs_fila").show();
		$(this).parents(".divs_fila:first").prev(".divs_fila").find(".caso_modificar").hide();
		$(this).parents(".divs_fila:first").prev(".divs_fila").find(".caso_normal").show();
		$(this).parents(".divs_fila:first").hide();
		
		$(".btnAzul").each(function(){
			if ( $(this).hasClass("caso_normal") ){
				$(this).show();
			}
		});
		
		return false;
	});
	
	/*
	 * En caso de ya tener un programa contratado
	 */
	$(".onload").show();
	
	var planesActivos = $(".onload").length;
	if ( planesActivos > 0) {
		$(".btnAzul").each(function(){
			if ($(this).hasClass("caso_normal")) {
				$(this).hide();
			}
		});
	}
	
	/*
	 * Manejo de paletas (oculta / desplegada) en pagina de plan  contratado 
	 */
	$(".palteta_oculta").click(function(){
		$(".paleta_desplegada").show();
		$(".palteta_oculta").hide();
		
		return false;
	});
	
	$(".paleta_cancelar").click(function(){
		$(".paleta_desplegada").hide();
		$(this).parents(".paleta_desplegada:fist").find(".email_no_editar:first").show();
		$(this).parents(".paleta_desplegada:fist").find(".email_editar:first").hide();
		$(this).parents(".paleta_desplegada:fist").find(".email_edicion_input:first").val("");
		
		$(".ancla_desactivado").hide();
		$(".ancla_reenviar").show();
		
		$(".palteta_oculta").show();
		
		return false;
	});
	
	$(".modificar_paleta").click(function(){
		$(this).parents(".ContenidoModificacion:fist").find(".ancla_reenviar:first").hide();
		$(this).parents(".ContenidoModificacion:fist").find(".ancla_desactivado:first").show();
		
		$(".btnDesactivado").click(function(){
			return false;
		});
	});
	
	$(".paleta_cancelar_desactivado").click(function(){
		$(this).parents(".ContenidoModificacion:fist").find(".ancla_desactivado:first").hide();
		$(this).parents(".ContenidoModificacion:fist").find(".ancla_reenviar:first").show();
	});
	
	$(".btn_reenviar").click(function(){
		$(".contenido_formulario_reenviar").hide();
		$(".casilla_verde_reenviar").show();
		
		return false;
	});
	
	$(".btnDesactivado").click(function(){
		return false;
	});
	
});


function emailValido(el){
	if(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(el)) {
		return true; 
	}
	else {
		return false;
	}
}

function SacarAlertas(){
	$(".no_email").hide();
	$(".email_invalido").hide();
}