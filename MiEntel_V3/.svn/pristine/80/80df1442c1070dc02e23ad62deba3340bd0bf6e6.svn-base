$(document).ready(function(){

	if(navigator.userAgent.indexOf('Safari/') != -1 && navigator.userAgent.indexOf('Chrome') == -1)  {
		if(navigator.userAgent.indexOf('Version/4') != -1
			|| navigator.userAgent.indexOf('Version/5') != -1)  {
			var w = $('.monto_recarga_safari').width();
			$('.monto_recarga_safari').css('width', w+35);
		}	
	}
	
	if ($.browser.msie)  {
		if ($.browser.version == '6.0') {
			$('.alinearVerBolsaIE').css('margin', '17px 10px 0px 50px');
		}
	}
	
	//Asigna estilo a los input y select
	$('select.selectBox').selectBox();
	
	//$(".inputBox").val("");

	/*validacionSuscripcion();
	$(".continuarPaso1").click(function(){
		if($("form#formSuscripcion").valid()) {
			window.location = "ss_paso2.html";
		}
		
		return false;
	});*/
	//Fin de validacion para númeos iguales
	
	//Discriminación de select en Cuenta Controlada
	$("#select_ctacontrolada").change(function(){
		if($(this).val() == 1){
			$('#contenido_ctacontrolada').load('html/cc_paso1_multitiendas.html', function() { 
				initPasosCtacontrolada(); 
				//eventualizaTooltips();
				//correccionZIndex();
				//validacionMultitiendas();
			});			
		}
		else{
			if ($(this).val() == 2){
				$('#contenido_ctacontrolada').load('html/cc_paso1_credito.html', function() { 
					initPasosCtacontrolada();
					//validacionCredito();
				});
			}
			else{
				if ($(this).val() == 0){
					$('#contenido_ctacontrolada').load('html/cc_paso1_entelticket.html', function() { 
						initPasosCtacontrolada();
						//validacionEntelTicket();
					});
				}
			}
		}
		return false;
	});
	
	//Discriminación de select en Prepago
	$("#select_prepago").change(function(){
		if($(this).val() == 1){
			$('#contenido_prepago').load('html/pp_paso1_multitiendas.html', function() { 
				//validacionMultitiendas();
				initPasosPrepago(); 
				eventualizaTooltips(); 
				correccionZIndex();
			});			
		}
		else{
			if ($(this).val() == 2){
				$('#contenido_prepago').load('html/pp_paso1_credito.html', function() { 
					//validacionCredito();
					initPasosPrepago();
				 });
			}
			else{
				if ($(this).val() == 0){
					$('#contenido_prepago').load('html/pp_paso1_entelticket.html', function() { 
						//validacionEntelTicket();
						initPasosPrepago();
					});
				}
			}
		}
		return false;
	});
	
});

function correccionZIndex() {
	var zIndex = 500;
	$('.tabla_formulario_fila').each(function() {
		$(this).css({
			position: 'relative',
			zIndex: zIndex--
		});
	});
}

function initPasosPrepago(){
	
	//Valida que solo se ingresen caracteres numericos
	$(".input_numerico").keypress(function(evt){
		var key = evt.keyCode ? evt.keyCode : evt.which ;
		return (key < 32 || (key >= 48 && key <= 57)); 
	});
	
	$(".inputBox").focus(function(){
		$(".mensaje_alerta0").hide();
		$(".mensaje_alerta1").hide();
	});
	
	//Asigna estilo a los input y select
	$('#contenido_prepago').find('input.inputBox').inputBox();
	$('#contenido_prepago').find('input.inputBoxAmarillo').inputBoxAmarillo();
	$('#contenido_prepago').find('select.selectBox').selectBox();
	
	//Pasos de Entel-ticket
	/*$(".continuar_eticket").click(function(){	
		if($('form#formEntelTicket').valid()) {
			$('#contenido_prepago').load('html/pp_paso2_entelticket.html', function() { 
				initPasosPrepago(); 
			});
		}
		return false;
	});*/
	
	$(".recargar_eticket").click(function(){
		$('#contenido_prepago').load('html/pp_paso3_entelticket.html', function() { 
			initPasosPrepago(); 
		});
		return false;
	});
	
	$(".cancelar_eticket").click(function(){
		$('#contenido_prepago').load('html/pp_paso1_entelticket.html', function() { 
			initPasosPrepago();
			//validacionEntelTicket();
		});
		return false;
	});
	
	//Pasos de Tarjeta de Multitiendas
	$(".continuar_multitiendas").click(function(){
		if($("form.formMultitienda").valid()) {
			$('#contenido_prepago').load('html/pp_paso2_multitiendas.html', function() { 
				initPasosPrepago(); 
				//$('#globoError, #tooltip1').hide();
			});
		}
		return false;
	});
	
	$(".recargar_multitiendas").click(function(){
		$('#contenido_prepago').load('html/pp_paso3_multitiendas.html', function() { 
			initPasosPrepago(); 
		});
		return false;
	});
	
	$(".cancelar_multitiendas").click(function(){
		$('#contenido_prepago').load('html/pp_paso1_multitiendas.html', function() { 
			initPasosPrepago(); 
			//eventualizaTooltips(); 
			//correccionZIndex();
			//validacionMultitiendas();
		});
		return false;
	});
	
	//Pasos de Tarjeta de Credito
	$(".continuar_credito").click(function(){
		if($('form#formCredito').valid()) {
			$('#contenido_prepago').load('html/pp_paso2_credito.html', function() { 
				initPasosPrepago(); 
			});
		}
		return false;
	});
	
	$(".recargar_credito").click(function(){
		$('#contenido_prepago').load('html/pp_paso3_credito.html', function() { 
			initPasosPrepago(); 
		});
		return false;
	});
	
	$(".cancelar_credito").click(function(){
		$('#contenido_prepago').load('html/pp_paso1_credito.html', function() { 
			initPasosPrepago(); 
			//validacionCredito();
		});
		return false;
	});
	
}

function initPasosCtacontrolada(){
	
	//Valida que solo se ingresen caracteres numericos
	$(".input_numerico").keypress(function(evt){
		var key = evt.keyCode ? evt.keyCode : evt.which ;
		return (key < 32 || (key >= 48 && key <= 57)); 
	});
	
	$(".inputBox").focus(function(){
		$(".mensaje_alerta0").hide();
		$(".mensaje_alerta1").hide();
	});
	
	//Asigna estilo a los input y select
	$('#contenido_ctacontrolada').find('input.inputBox').inputBox();
	$('#contenido_ctacontrolada').find('input.inputBoxAmarillo').inputBoxAmarillo();
	$('#contenido_ctacontrolada').find('select.selectBox').selectBox();
	
	//Pasos de Entel-ticket
	/*$(".continuar_eticket").click(function(){
		if($('form#formEntelTicket').valid()) {
			$('#contenido_ctacontrolada').load('html/cc_paso2_entelticket.html', function() { 
				initPasosCtacontrolada(); 
			});
		}
		
		return false;
	});*/
	
	$(".recargar_eticket").click(function(){
		$('#contenido_ctacontrolada').load('html/cc_paso3_entelticket.html', function() { 
			initPasosCtacontrolada(); 
		});
		return false;
	});
	
	$(".cancelar_eticket").click(function(){
		$('#contenido_ctacontrolada').load('html/cc_paso1_entelticket.html', function() { 
			initPasosCtacontrolada(); 
			//validacionEntelTicket();
		});
		return false;
	});
	
	//Pasos de Tarjeta de Multitiendas
	$(".continuar_multitiendas").click(function(){
		if($("form.formMultitienda").valid()) {
			$('#contenido_ctacontrolada').load('html/cc_paso2_multitiendas.html', function() { 
				initPasosCtacontrolada(); 
				//$('#globoError, #tooltip1').hide();
			});
		}
		return false;
	});
	
	$(".recargar_multitiendas").click(function(){
		$('#contenido_ctacontrolada').load('html/cc_paso3_multitiendas.html', function() { 
			initPasosCtacontrolada(); 
		});
		return false;
	});
	
	$(".cancelar_multitiendas").click(function(){
		$('#contenido_ctacontrolada').load('html/cc_paso1_multitiendas.html', function() { 
			initPasosCtacontrolada(); 
			//eventualizaTooltips(); 
			//correccionZIndex();
			//validacionMultitiendas();
		});
		return false;
	});
	
	//Pasos de Tarjeta de Credito
	$(".continuar_credito").click(function(){
		if($('form#formCredito').valid()) {
			$('#contenido_ctacontrolada').load('html/cc_paso2_credito.html', function() { 
				initPasosCtacontrolada(); 
			});
		}
		return false;
	});
	
	$(".recargar_credito").click(function(){
		$('#contenido_ctacontrolada').load('html/cc_paso3_credito.html', function() { 
			initPasosCtacontrolada(); 
		});
		return false;
	});
	
	$(".cancelar_credito").click(function(){
		$('#contenido_ctacontrolada').load('html/cc_paso1_credito.html', function() { 
			initPasosCtacontrolada(); 
			//validacionCredito();
		});
		return false;
	});
}