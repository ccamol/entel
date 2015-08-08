$(document).ready(function(e) {	
	//abrir descripcion
	$(".lista-bolsas a.underline").click(function(){
		$(this).parent().parent().find('.descripcion').toggle();
	});	
	
	//seleccionar bolsa a contratar paso 1 a paso 2
	$(".lista-bolsas a.contratarBolsa").click(function(){
		if($(this).hasClass('btnDesactivado')) {
			return false
		}		
		$(this).parents("div.paso1:first").hide();
		$(this).parents("div.paso1:first").next('div.paso2:first').show();
	}); 
	
	//Cancelar contratar bolsa
	$(".lista-bolsas a.cancelarBolsa").click(function(){
		$(this).parents("div.paso2:first").hide();
		$(this).parents("div.paso2:first").prev('div.paso1:first').show();
		$("input[name=radioPago]").removeAttr("checked");
		$('.continuarBolsa').removeClass('btnAzul').addClass('btnDesactivado');
	});	
	
	//confirmar seleccion contratar bolsa paso 2 a paso 3
	$(".lista-bolsas a.continuarBolsa").click(function(){
		if($(this).hasClass('btnDesactivado')){
			return false
		}
		$(this).parents("div.paso2:first").hide();
		$(this).parents("div.paso2:first").next('div.paso3:first').show();		
	});
	
	//Cancelar seleccion contratar bolsa
	$(".lista-bolsas a.cancelarBolsa2").click(function(){
		$(this).parents("div.paso3:first").hide();
		$(this).parents("div.paso3:first").prev('div.paso2:first').show();
		$("input[name=radioPago]").removeAttr("checked");
		$('.continuarBolsa').removeClass('btnAzul').addClass('btnDesactivado');
	});
	
	$("input[name=radioPago]").removeAttr("checked");
	
	$("input[name=radioPago]").click(function(){
		$('.continuarBolsa').removeClass('btnDesactivado').addClass('btnAzul');
		if ($(this).val() == 'FACTURA') {
			$('.metodoPago').find('li:last').html('Modo de pago: Cargo contra Factura');
		} else if ($(this).val() == 'SALDO') {
			$('.metodoPago').find('li:last').html('Modo de pago: Cargo contra Recarga');
		}		
	});
	
	//abrir descripcion en bolsas compradas
	$(".bolsas-compradas .fila div.bolsa").click(function(){
		$(this).parent().find('#listado').toggle();
	});	
});