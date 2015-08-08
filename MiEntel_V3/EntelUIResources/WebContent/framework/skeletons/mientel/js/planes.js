function initMenuDesplegablePlanes() { 
	$('select.selectBox').selectBox();
	

	$('.aceptoCondiciones').change(function(e) {
		  var tr = $(this).parents('.lista-bolsas:first');
		      tr.find('.paso_2').find('a.confirma').toggle();		            
	 });
	
	/** Lista de bolsas, menu expandible*************/	
	$("#menu-desplegable-planes div.header a").not('.btnCambiarmePlan').click(function(){
		if($(this).hasClass("abierto")){
			$(this).removeClass("abierto").addClass("cerrado");
			$(this).parents("div.bolsa").removeClass("abierto");
			$(this).parents("div.header:first").next().hide();	
		}else{
			$(this).removeClass("cerrado").addClass("abierto");
			$(this).parents("div.bolsa").removeClass("cerrado").addClass("abierto");
			$(this).parents("div.header:first").next().show();	
		}
	});
	
	$("#menu-desplegable-planes div.header .btnCambiarmePlan").click(function(){
		$(this).hide();
		$(this).parents('.bolsa:first').find('div.lista-bolsas').find('div:first').hide().end().find('div.paso_2').show();
		
		return false;
	});
	
	//Menu: Opcion Plan Red
	//Evento Boton: "Cambiarme a este plan" y "Cancelar"
	$('div.lista-bolsas a.cambiarPlan, div.paso_2 a.cancelar').click(function() {
		
		var plan = $(this).parents('tr:first').find('td:first').text();
		var codbscs2 = $(this).parents('tr:first').find('td:first').attr('id');
		var precioPlan = $(this).parents('tr:first').find('td:first').next('td').text();
		var isTUSMS = $(this).parents('tr:first').find('input.TarifaUnicaConSMS').val();
		var tr = $(this).parents('.lista-bolsas:first');
		
		if(tr.find('.tabla_precios').is(':visible') && $(this).is('a.cambiarPlan')) {
			//tr.find('.tabla_precios').hide();
			$(this).hide();
			$(this).parents('tr:first').addClass('planSeleccionado');
			
			tr.find('.planes_contabla').find('tr').each(function(){
				if(!$(this).hasClass('planSeleccionado')){
					$(this).hide();
				}
			});
			
			$("#filaPrimera"+codbscs2).show();
			$("#filaSegunda"+codbscs2).show();
			
			//tr.find('.paso_2').find('.plan').text(plan).end().show();
			tr.find('.paso_2').show();
			tr.find('.paso_3').find('.plan').text(plan);
			$('input.codbscs2').val(codbscs2);
			$('input.nombreNuevoPlan').val(plan);
			$('input.valorNuevoPlan').val(precioPlan);
			
			if(isTUSMS == 'true'){
				tr.find('.paso_2').find('.disclaimer').html($('.tarifa_unica_con_SMS').html());
				tr.find('.paso_3').find('.disclaimer').html($('.tarifa_unica_con_SMS').html());
			}
			
			tr.find('.paso_2').find('.disclaimer').find('.nombrePlan').text(plan);
			tr.find('.paso_2').find('.disclaimer').find('.precioPlan').text(precioPlan).end().show();

		    var tipoPlanEscogido = $(this).parents('tr:first').find('input:first').attr('value'); // Se hace la busqueda del plan escogido en la lista               
		    if(tipoPlanEscogido!= undefined){   // se hace la confirmacion de que exista un plan destino              	
				planDestino = tipoPlanEscogido; 
				validarPlanOrigen(this); 
			}
			
		} else {
							
			tr.find('.paso_2').find('.aceptoCondiciones').removeAttr('checked');
			tr.find('.paso_2').hide();
			tr.find('.tabla_precios').show();			
			$(this).parents('div.bolsa:first').find('div.header').find("a.btnCambiarmePlan").attr('style','');
			
			tr.find('.planes_contabla').find('tr').each(function(){
				if($(this).hasClass('planSeleccionado')){
					$(this).removeClass('planSeleccionado');
					$(this).find('a.cambiarPlan').show();
				} else {
					$(this).show();
				}
			});
		}

		return false;
	});

	//Evento Boton: "Confirmar" cambio de plan
	$('div.paso_2 a.confirmar').click(function() {		
		var tr = $(this).parents('.lista-bolsas:first');
		tr.find('.paso_2').hide();
		tr.find('.paso_3').show();
		
		return false;
	});
	
	//Menu: Opcion Full Familia
	//Evento: Seleccionar
	$('#familia a.cambiarPlan').unbind().click(function() {
		
		var tr = $(this).parents('tr:first');
		$(this).hide().next('a').show();
		tr.addClass('filaSeleccionada').next('tr').show();
		
		return false;
	});
	//Evento: Cancelar
	$('#familia a.cancelarSeleccion').unbind().click(function() {
		var tr = $(this).parents('tr:first');
		$(this).hide().prev('a').show();
		tr.removeClass('filaSeleccionada').next('tr').hide();
		
		return false;
	});
	
	//Evento: Paso 2: Aceptar lineas
	$('#familia div.paso_2_familia a.aceptar').unbind().click(function() {
		var plan = $(this).parents('tr').prev('tr').hide().find('td:first').text();
		$(this).parents('td')
			.find('.paso_2_familia').hide().end()
			.find('.paso_4_familia')
				.find('div.plan').text(plan).end()
			.end()
			.find('.paso_3_familia')
				.find('div.plan').text(plan).end()
			.show();		
			
		return false;
	});
	//Evento: Paso 3: Confirmar
	$('#familia div.paso_3_familia a.confirmar').unbind().click(function() {
		var lineas = $(this).parents('td').find('.paso_2_familia .selectLineas');
		
		var salida = '';
		for(var i=0; i<lineas.length; i++) {
			salida += '<li>Linea '+(i+1)+': '+lineas[i].value+'</li>';	
		}
		
		$(this).parents('.paso_3_familia').hide()
			.next('.paso_4_familia')
				.find('ul.lineas').empty().html(salida).end()
			.show();
			
		return false;
	});
	//Evento: Paso 3: Cancelar
	$('#familia div.paso_3_familia a.cancelar').unbind().click(function() {
		$(this).parents('.paso_3_familia').hide()
			.prev('.paso_2_familia').show()
			.parents('tr').prev('tr').show();	
			
		return false;
	});
	//Evento: Paso 4: Cancelar
	$('#familia div.paso_4_familia a.deshacer').unbind().click(function() {
		$(this).parents('div.paso_4_familia').hide()
			.parents('tr')
				.find('.paso_2_familia').show().end()
			.hide()
			.prev().removeClass('filaSeleccionada')
				.find('a.cancelarSeleccion').hide().end()
				.find('a.cambiarPlan').show().end()
			.show();
			
		return false;
	});
	
	/**
	* Fix para botones en Safari 3
	*/
	if($.browser.safari && parseInt($.browser.version) <= 525) {
		 $('.btnVerde span, .btnVerdeDelgado span, .btnVerdeGrande span, .btnAzul span, .btnAzulDelgado span, .btnAzulGrande span').css('paddingBottom','8px');
	}
}



function initMenuDesplegableTarifas() { 
	$('select.selectBox').selectBox();
	
	
	/** Lista de bolsas, menu expandible*************/	
	$("#menu-desplegable-planes div.header a").not('.btnCambiarmeTarifa, .btnDesactivado').click(function(){
		if($(this).hasClass("abierto")){
			$(this).removeClass("abierto").addClass("cerrado");
			$(this).parents("div.bolsa").removeClass("abierto");
			$(this).parents("div.header:first").next().hide();	
			
			$(this).parents(".bolsa").find('.btnDesactivado').hide();
			
		}else{
			$(this).removeClass("cerrado").addClass("abierto");
			$(this).parents("div.bolsa").removeClass("cerrado").addClass("abierto");
			$(this).parents("div.header:first").next().show();	
			
			$(this).parents(".bolsa").find('.btnDesactivado').show();	
		}
	});
	
	$("#menu-desplegable-planes div.header .btnCambiarmeTarifa").click(function(){
		$(this).hide();
		$(this).parents('.bolsa:first').find('div.lista-bolsas').find('div:first').hide().end().find('div.paso_2').show();
		
		return false;
	});
	
	//Menu: Opcion Plan Red
	//Evento Boton: "Cambiarme a este plan" y "Cancelar"
	$('div.lista-bolsas a.cambiarTarifa, div.paso_2 a.cancelar').click(function() {
		var plan = $(this).parents('tr:first').find('td:first').text();
		var tr = $(this).parents('.lista-bolsas:first');
		if(tr.find('.tabla_precios').is(':visible')) {
			tr.find('.tabla_precios').hide();
			tr.find('.paso_2').find('.plan').text(plan).end().show();
			tr.find('.paso_3').find('.plan').text(plan);			
			
		} else {
			tr.find('.paso_2').hide();
			tr.find('.tabla_precios').show();
			
			$(this).parents('div.bolsa:first').find('div.header').find("a.btnCambiarmeTarifa").attr('style','');
		}

		return false;
	});

	//Evento Boton: "Confirmar" cambio de plan
	$('div.paso_2 a.confirmar').click(function() {
		var tr = $(this).parents('.lista-bolsas:first');
		tr.find('.paso_2').hide();
		tr.find('.paso_3').show();
		
		return false;
	});
	
	//Menu: Opcion Full Familia
	//Evento: Seleccionar
	$('#familia a.cambiarTarifa').unbind().click(function() {
		var tr = $(this).parents('tr:first');
		$(this).hide().next('a').show();
		tr.addClass('filaSeleccionada').next('tr').show();
		
		return false;
	});
	//Evento: Cancelar
	$('#familia a.cancelarSeleccion').unbind().click(function() {
		var tr = $(this).parents('tr:first');
		$(this).hide().prev('a').show();
		tr.removeClass('filaSeleccionada').next('tr').hide();
		
		return false;
	});
	
	//Evento: Paso 2: Aceptar lineas
	$('#familia div.paso_2_familia a.aceptar').unbind().click(function() {
		var plan = $(this).parents('tr').prev('tr').hide().find('td:first').text();
		$(this).parents('td')
			.find('.paso_2_familia').hide().end()
			.find('.paso_4_familia')
				.find('div.plan').text(plan).end()
			.end()
			.find('.paso_3_familia')
				.find('div.plan').text(plan).end()
			.show();		
			
		return false;
	});
	//Evento: Paso 3: Confirmar
	$('#familia div.paso_3_familia a.confirmar').unbind().click(function() {
		var lineas = $(this).parents('td').find('.paso_2_familia .selectLineas');
		
		var salida = '';
		for(var i=0; i<lineas.length; i++) {
			salida += '<li>Linea '+(i+1)+': '+lineas[i].value+'</li>';	
		}
		
		$(this).parents('.paso_3_familia').hide()
			.next('.paso_4_familia')
				.find('ul.lineas').empty().html(salida).end()
			.show();
			
		return false;
	});
	//Evento: Paso 3: Cancelar
	$('#familia div.paso_3_familia a.cancelar').unbind().click(function() {
		$(this).parents('.paso_3_familia').hide()
			.prev('.paso_2_familia').show()
			.parents('tr').prev('tr').show();	
			
		return false;
	});
	//Evento: Paso 4: Cancelar
	$('#familia div.paso_4_familia a.deshacer').unbind().click(function() {
		$(this).parents('div.paso_4_familia').hide()
			.parents('tr')
				.find('.paso_2_familia').show().end()
			.hide()
			.prev().removeClass('filaSeleccionada')
				.find('a.cancelarSeleccion').hide().end()
				.find('a.cambiarPlan').show().end()
			.show();
			
		return false;
	});
	
	/**
	* Fix para botones en Safari 3
	*/
	if($.browser.safari && parseInt($.browser.version) <= 525) {
		 $('.btnVerde span, .btnVerdeDelgado span, .btnVerdeGrande span, .btnAzul span, .btnAzulDelgado span, .btnAzulGrande span').css('paddingBottom','8px');
	}
}

function selNumMoviles(select) {
	var el = $(select);
	var numeroLineas = el.find('option:selected').val();
	
	el.parents('div.paso_2_familia').find('div.btn_aceptar').hide();
	
	if(numeroLineas > 0) {
		var salida = '';
		
		$.get('html/cambioplan_lineas.html', function(data) {			
			for(var i=numeroLineas; i >= 1; i--)
				salida += '<div class="lineas clearfix" style="position:relative;z-index:'+(i*10)+';">'+$(data).html()+'</div>';
			
			el.parent().next('.asignar_lineas').find('.lineas').remove();
			el.parent().next('.asignar_lineas').append(salida);
			
			el.parent().next('.asignar_lineas').find('.lineas').each(function(i) {
				$(this).find('span').html('Linea '+(i+1)+':'); 
			});
			
			el.parents('td:first').find('select.selectLineas').selectBox();
		});
	}
	
	el.parent().next('.asignar_lineas').show();
	
	/**
	 * Fix para botones en FF2
	 */
	if($.browser.mozilla && parseInt($.browser.version) <= 2) {
		$('.btnVerde, .btnVerdeDelgado, .btnVerdeGrande, .btnAzul, .btnAzulDelgado, .btnAzulGrande').css('display','-moz-inline-box');
	}
}

function seleccionarNumero(el) {
	var flag = true;
	$(el).parents('.asignar_lineas:first').find('select').each(function() {
		if($(this).val() == 0) flag = false;						  
	});
	
	if(flag) $(el).parents('div.paso_2_familia').find('div.btn_aceptar').show();
	else $(el).parents('div.paso_2_familia').find('div.btn_aceptar').hide();
}

function soloNumeros(evt){
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 31 || (key >= 48 && key <= 57)); 
}

function soloLetras(evt) {
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 31 || (key >= 65 && key <= 90) || (key >= 97 && key <= 122));
}

function initNumeroFrecuente() {
	
	var numero = $('div.numero_frecuente div.paso_1 div.numero').text();
	var nombre = $('div.numero_frecuente div.paso_1 div.nombre').text();
	
	//Paso 1: Cambiar
	$('div.numero_frecuente div.paso_1').find('a.cambiar').click(function() {
		
		//$('div.numero_frecuente input.nf_input').val(numero);	
		$('div.numero_frecuente input.nf_nombre').val(nombre);	
		
		
		$(this).parents('div.numero_frecuente')
			.find('div.paso_1').hide().end()
			.find('div.paso_modificar').show();	
		
		$('div.numero_frecuente select').next().hide();

		//Paso Modificar: Cancelar
		$('div.numero_frecuente div.paso_modificar').find('a.cancelar').unbind().click(function() {
			$(this).parents('div.numero_frecuente')
				.find('div.paso_modificar').hide().end()
				.find('div.paso_1').show();
			return false;
		});
		
		//Paso Modificar: Aceptar
		$('div.numero_frecuente div.paso_modificar').find('a.aceptar').unbind().click(function() {
			if($('div.numero_frecuente').find('input.nf_input').val().length == parseInt($('div.numero_frecuente').find('input.nf_input').attr('maxlength'))) {
				//$('#globoError').fadeOut(100);
				$(this).parents('div.numero_frecuente').addClass('activo')
					.find('div.msg').html('El N&uacute;mero Frecuente ha sido modificado.').show().end()
					.find('div.paso_1 div.botonera').hide().end()
					.find('div.paso_modificar').hide().end()
					.find('div.paso_1').show();
					
				$('div.numero_frecuente div.paso_1 div.numero').text($('select.nf_select').val()+" "+$('input.nf_input').val());
				$('div.numero_frecuente div.paso_1 div.nombre').text($('input.nf_nombre').val());
				
			} else {
				
				$('#globoError').fadeIn(200);
				$('div.numero_frecuente input.nf_input').unbind().click(function() {
					$('#globoError').fadeOut(200);							 
				});
			}
			
			return false;
		});
		
		return false;
	});
	
	//Paso 1: Eliminar
	$('div.numero_frecuente div.paso_1').find('a.eliminar').click(function() {
		$(this).parents('div.numero_frecuente').addClass('activo')
			.find('div.paso_1 div.alerta_borrar').show().end()
			.find('div.paso_1 div.botonera').hide();
		return false;
	});
	
	//Paso Borrar Numero: Cancelar
	$('div.numero_frecuente div.paso_1 div.alerta_borrar').find('a.cancelar').click(function() {
		$(this).parents('div.numero_frecuente').removeClass('activo')
			.find('div.paso_1 div.botonera').show().end()
			.find('div.paso_1 div.alerta_borrar').hide();
		return false;
	});
	
	//Paso Borrar Numero: Confirmar
	$('div.numero_frecuente div.paso_1 div.alerta_borrar').find('a.confirmar').click(function() {
		$(this).parents('div.numero_frecuente')
			.find('div.paso_1').hide().end()
			.find('div.paso_nuevo').show();
		return false;
	});
	
	//Paso Nuevo Numero: Nuevo Numero
	$('div.numero_frecuente').find('a.nuevoNumero').click(function() {
		$('div.numero_frecuente input.nf_input').val('');
		$('div.numero_frecuente select').next().hide();
		$(this).parents('div.paso_nuevo').hide()
			.parents('div.numero_frecuente').removeClass('activo').find('div.paso_modificar').show();
			
		//Paso Modificar: Cancelar
		$('div.numero_frecuente div.paso_modificar').find('a.cancelar').unbind().click(function() {
			$(this).parents('div.numero_frecuente').removeClass('activo')
				.find('div.paso_modificar').hide().end()
				.find('div.paso_nuevo').show();
			$(this).parents('div.numero_frecuente').find('div#globoError').hide();
			return false;
		});	
		
		//Paso Modificar: Aceptar
		$('div.numero_frecuente div.paso_modificar').find('a.aceptar').click(function() {
			if($('div.numero_frecuente').find('input.nf_input').val().length == parseInt($('div.numero_frecuente').find('input.nf_input').attr('maxlength'))) {
				$('#globoError').fadeOut(100);
				$(this).parents('div.numero_frecuente').addClass('activo')
					.find('div.paso_modificar').hide().end()
					.find('div.paso_1').show()
						.find('div.alerta_borrar').hide().end()
						.find('div.msg').html('El N&uacute;mero Frecuente ha sido guardado').show();
						
				$('div.numero_frecuente div.paso_1 div.numero').text($('select.nf_select').val()+" "+$('input.nf_input').val());
				
			} else {
				$('#globoError').fadeIn(200);
				$('div.numero_frecuente input.nf_input').unbind().keypress(function() {
					$('#globoError').fadeOut(200);							 
				});	
			}
			return false;
		});
		
		return false;
	});

	
	$('div.numero_frecuente input#movil').click(function() {
		$('div.numero_frecuente select').next().hide();
		//$('div.numero_frecuente input.nf_input').val(numero);
		$('div.numero_frecuente input.nf_input').val('').attr('maxlength',8);
	});
	
	$('div.numero_frecuente input#red').click(function() {
		$('div.numero_frecuente select').next().show();
		$('div.numero_frecuente input.nf_input').val('').attr('maxlength',8);
	});
}


// Numero Favorito
function initNumeroFavorito() {
	
	var numero = $('div.numero_favorito div.paso_1 div.numero').text();
	var nombre = $('div.numero_favorito div.paso_1 div.nombre').text();
	
	//Paso 1: Cambiar
	$('div.numero_favorito div.paso_1').find('a.cambiar').click(function() {
		
		//$('div.numero_favorito input.nf_input').val(numero);	
		$('div.numero_favorito input.nf_nombre').val(nombre);
		

		$(this).parents('div.numero_favorito')
			.find('div.paso_1').hide().end()
			.find('div.paso_modificar').show();	
		
		$('div.numero_favorito select').next().hide();
			
		//Paso Modificar: Cancelar
		$('div.numero_favorito div.paso_modificar').find('a.cancelar').unbind().click(function() {
			$(this).parents('div.numero_favorito')
				.find('div.paso_modificar').hide().end()
				.find('div.paso_1').show();
			return false;
		});
		
		//Paso Modificar: Aceptar
		$('div.numero_favorito div.paso_modificar').find('a.aceptar').unbind().click(function() {
			if($('div.numero_favorito').find('input.nf_input').val().length == parseInt($('div.numero_favorito').find('input.nf_input').attr('maxlength'))) {
				//$('#globoError').fadeOut(100);
				$(this).parents('div.numero_favorito').addClass('activo')
					.find('div.msg').html('El N&uacute;mero Favorito ha sido modificado.').show().end()
					.find('div.paso_1 div.botonera').hide().end()
					.find('div.paso_modificar').hide().end()
					.find('div.paso_1').show();
					
				$('div.numero_favorito div.paso_1 div.numero').text($('div.numero_favorito select.nf_select').val()+" "+$('div.numero_favorito input.nf_input').val());
				$('div.numero_favorito div.paso_1 div.nombre').text($('div.numero_favorito input.nf_nombre').val());
				
				} else {
				
				$('#globoError').fadeIn(200);
				$('div.numero_favorito input.nf_input').unbind().click(function() {
					$('#globoError').fadeOut(200);							 
				});
			}
			
			return false;
		});
		
		return false;
	});
	
	//Paso 1: Eliminar
	$('div.numero_favorito div.paso_1').find('a.eliminar').click(function() {
		$(this).parents('div.numero_favorito').addClass('activo')
			.find('div.paso_1 div.alerta_borrar').show().end()
			.find('div.paso_1 div.botonera').hide();
		return false;
	});
	
	//Paso Borrar Numero: Cancelar
	$('div.numero_favorito div.paso_1 div.alerta_borrar').find('a.cancelar').click(function() {
		$(this).parents('div.numero_favorito').removeClass('activo')
			.find('div.paso_1 div.botonera').show().end()
			.find('div.paso_1 div.alerta_borrar').hide();
		return false;
	});
	
	//Paso Borrar Numero: Confirmar
	$('div.numero_favorito div.paso_1 div.alerta_borrar').find('a.confirmar').click(function() {
		$(this).parents('div.numero_favorito')
			.find('div.paso_1').hide().end()
			.find('div.paso_nuevo').show();
		return false;
	});
	
	//Paso Nuevo Numero: Nuevo Numero Favorito
	$('div.numero_favorito').find('a.nuevoNumero').click(function() {
		$('div.numero_favorito input.nf_input').val('');
		$('div.numero_frecuente select').next().hide();
		$(this).parents('div.paso_nuevo').hide()
			.parents('div.numero_favorito').removeClass('activo').find('div.paso_modificar').show();
			
		//Paso Modificar: Cancelar
		$('div.numero_favorito div.paso_modificar').find('a.cancelar').unbind().click(function() {
			$(this).parents('div.numero_favorito').removeClass('activo')
				.find('div.paso_modificar').hide().end()
				.find('div.paso_nuevo').show();
			$(this).parents('div.numero_favorito').find('div#globoError').hide();
			return false;
		});	
		
		//Paso Modificar: Aceptar
		$('div.numero_favorito div.paso_modificar').find('a.aceptar').click(function() {
			if($('div.numero_favorito').find('input.nf_input').val().length == parseInt($('div.numero_favorito').find('input.nf_input').attr('maxlength'))) {
				$('#globoError').fadeOut(100);
				$(this).parents('div.numero_favorito').addClass('activo')
					.find('div.paso_modificar').hide().end()
					.find('div.paso_1').show()
						.find('div.alerta_borrar').hide().end()
						.find('div.msg').html('El N&uacute;mero Favorito ha sido guardado').show();
						
				$('div.numero_favorito div.paso_1 div.numero').text($('div.numero_favorito select.nf_select').val()+" "+$('div.numero_favorito input.nf_input').val());
				
			} else {
				$('#globoError').fadeIn(200);
				$('div.numero_favorito input.nf_input').unbind().keypress(function() {
					$('#globoError').fadeOut(200);							 
				});	
			}
			return false;
		});
		
		return false;
	});

	
	$('div.numero_favorito input#movil').click(function() {
		$('div.numero_favorito select').next().hide();
		//$('div.numero_favorito input.nf_input').val(numero);
		$('div.numero_favorito input.nf_input').val('').attr('maxlength',8);
	});
	
	$('div.numero_favorito input#red').click(function() {
		$('div.numero_favorito select').next().show();
		$('div.numero_favorito input.nf_input').val('').attr('maxlength',7);
	});
	
}


	/*
	 * funcion para controlar la muestra del mensaje de advertencia para cambio de plan
	 * */
	 function validarPlanOrigen (obj)
	 {	
		var response = false;
		//planOrigen="11";		
		if (planEsMultimedia(planesMdia,planOrigen) && !planEsMultimedia(planesMdia , planDestino)) 
		{
			response = true;
			var tr = $(obj).parents('.lista-bolsas:first');
			
			tr.find('.paso_2').removeClass('normal');
			tr.find('.paso_2').addClass('advertencia');	
			tr.find('.paso_2').find('.aceptoCondiciones').removeAttr('checked');
			tr.find('.paso_2').find('a.confirma').hide();
		}
		return response;
	 }
     
     /* 
      * funcion que regresa true cuando una valor esta en una lista,
      * los dos valores son pasados por paremetros.
     **/
	function  planEsMultimedia (listaPlanes , plan)
	{		
		var  sw = false;
		if(listaPlanes.split(",").length > 0){			
			var planes = new Array(listaPlanes.split(",").length);
			planes = listaPlanes.split(",");			
			$.each(planes, function() 
			{				
				if( plan == this )
				 {	
					sw = true;			
				 }
			 });			
		}
		return sw;
	}