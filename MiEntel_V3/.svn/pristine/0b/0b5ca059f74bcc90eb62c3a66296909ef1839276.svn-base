/**
 * @author Andres Pontt
 * @version 1.0
 * Archivo javascript para los efectos de la sección bolsas.
 */
$(document).ready(function(){
	/**
	 * Anchor para seleccionar fila y desactivar
	 * @param {Object} this
	 */
	$("a.desactivar").click(function(){
		$(this).parents("tr:first").hide().next().show();
	});
	
	/**
	 * Anchor para cancelar desactivacion; (Mis Bolsas)
	 * * @param {Object} this
	 */
	$(".desactivar a.cancelar").click(function(){
		$(this).parents("tr:first").hide().prev().fadeIn();
	});
	
	/**
	 * Anchor para confirmar desactivación (Mis Bolsas)
	 * @param {Object} this
	 */
	$(".desactivar a.confirmar").click(function(){
		
		//Esto es sólo la animación, debe ser ejecutado dentro de un callback ajax.
		$(this).parents("tr:first").hide().prev()
			.addClass('pendiente')
			.find("span:first")
				.removeClass("icon-activa")
				.addClass("icon-pendiente")
				.html("Pendiente de desactivaci&oacute;n")
			.end()
			.find("a.btnPng")
				.remove()
			.end()
			.show();
			
		
	});
	
	/* Lista de bolsas, menu expandible (Sección Contratar)*********/	
	
	
	/**
	 * Abre o cierra el menu expandible (Contratar)
	 */
	$("#menu-desplegable-planes-bolsas div.header a").click(function(){
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
	/**
	 * Abre o cierra el menu expandible (Contratar) Fix Planes
	 */

	/**
	 * alternativo, no borrar
	 * /**
	 
	$("#menu-desplegable-planes div.bolsa").click(function(){
		if($(this).hasClass("abierto")){
			$(this).find("a:first").removeClass("abierto").addClass("cerrado");
			$(this).removeClass("abierto");
			$(this).find("div.header:first").next().hide();
		}else{
			$(this).removeClass("cerrado").addClass("abierto");
			$(this).find("a:first").removeClass("cerrado").addClass("abierto");
			$(this).find("div.header:first").next().show();
		}
		
	});
	
	 */
	
	/**
	 * Cancela la confirmación (Contratar)
	 */
	$("#menu-desplegable-planes-bolsas div.lista-bolsas a.cancelar").click(function(){
		$(this).parents("div.fila:first").removeClass("confirmar");
		$(this).parents("div.paso_2:first").hide().prev().show();
	});
	
	
	/* Eventos para contratación de bolsas *************/	
	
	/**
	 * Cambia fila a modo confirmar (se oculta el boton)
	 */
	$("#menu-desplegable-planes-bolsas div.lista-bolsas a.contratar").click(function(){
		$(this).parents("div.fila:first").addClass("confirmar");
		$(this).hide().next().show();
		$(this).parents("div.fila:first").find("div.nota:first").hide();
		}
	);
	
	/**
	 * Confirma, se oculta la capa contratar
	 */
	$("#menu-desplegable-planes-bolsas div.lista-bolsas a.confirmar").click(function(){
		$(this).parents("div.contratar:first").prev().hide();
		$(this).parents("div.paso_2:first").hide().next().show();
		
	});
	
	/**
	 * 
	 */
	$("#menu-desplegable-planes-bolsas div.lista-bolsas .cancelar").click(function(){
		
	});
	
	
	/* Menu expandible seccion regalos*************/
	
	/**
	 * Se despliega la capa para regalar bolsa 
	 */	
	$("#menu-desplegable-planes-bolsas div.regalar a.regalar").click(function(){
		//oculta el boton comprar
		$(this).parents("div.regalar").prev().hide();
		//oculta la nota si esta abierta
		$(this).parents("div.bolsa").find("div.nota").hide();
		//Hace aparecer el box
		$(this).parents("div.regalar").hide().next().show();
		$(this).parents(".cabecera:first").next().children(".box-numero").show();
		$("#pasos").html("Paso 2 de 4");
	});
	
	/**
	 * Se oculta la capa para regalar bolsa
	 */
	$("#menu-desplegable-planes-bolsas div.cancelar a.cancelar").click(function(){	
		$(this).parents("div.cabecera").next().find("form.paso1:first").find('.globoError').hide();
		$(this).parents("div.cabecera").next().find("form.paso1:first").find('input').each(function() {
			this.disableError();
		});
	
		//Aparece nuevamente el botón regalar
		$(this).parents("div.cancelar").hide().prev().show();
		//resetea los valores
		$(this).parents("div.cabecera").next()
		.find(".txt-numero").val("");
				$(this).parents("div.cabecera").next()
		.find(".txt-renumero").val("");
		//Aparece nuevamente el boton comprar
		$(this).parents("div.cabecera").find("div.comprar").show();
		
		//Cierra el box y vuelve al paso1
		$(this).parents(".cabecera:first").next().children(".box-numero").hide();
		$(this).parents("div.cabecera").next().find("form.paso1:first").show();
		$(this).parents("div.cabecera").next().find("form.paso2:first").hide();
		$("#pasos").html("Paso 1 de 4");
		
	});
	
	/**
	 * Acepta y va al paso 1 (verificacion si los dos numeros telefonicos son iguales)
	 * se pueden seguir agregando condiciones, por ejemplo si el numero no pertenece a x compañia.
	 */
	  $("form.paso1 a.aceptar").click(function(){
		var formulario = $(this).parents("form:first");
		if (formulario.valid()) {
			if(formulario.find(".numeroPrepagoBolsa").val() == formulario.find(".numeroRePrepagoBolsa").val()){
			//Visualizamos el siguiente paso y copiamos el número
			$(this).parents(".paso1").hide().next().show().parents("div.bolsa").find(".span-numero").html($(this).parents("form").find(".txt-numero").val());
			$("#pasos").html("Paso 3 de 4");
			}else{
				$('#globoError').find('td:first').html("Por favor verifique los n&uacute;meros sean iguales");
				showGloboBolsa(formulario.find(".numeroRePrepagoBolsa"));
			}
	 }

	});
	
	/**
	 * 
	
	$("form.paso2 a.confirmar").click(function(){
		//Este efecto debe ser ejecutado posterior a la llamada ajax.
		$(this).parents("div.cuerpo").prev().find("div.cancelar").hide();
		$(this).parents("div.cuerpo").hide().next().show();
		$("#pasos").html("Paso 4 de 4");
	});
	 */
	
	/**
	 * En modo CC, en una misma fila se puede comprar y regalar, 
	 */
	$("div#menu-desplegable-planes-bolsas div.comprar a.comprar").click(function(){
		$(this).parents("div.bolsa:first").addClass("confirmar");
		//oculta la nota si esta abierta
		$(this).parents("div.bolsa").find("div.nota").hide();
		$(this).parents("div.cabecera:first").find("div.regalar").hide();
		
		$(this).hide().next().show();
		
		}
	);
	/**
	 * Confirma, se oculta la capa contratar
	 */
	$("div#menu-desplegable-planes-bolsas div.comprar a.confirmar").click(function(){
		$(this).parents("div.contratar:first").prev().hide();
		$(this).parents("div.paso_2:first").hide().next().show();
		$(this).parents("div.cabecera:first").find("div.precio").hide();
		
	});
	/**
	 * Cancela la confirmación CC Comprar 
	 */
	$("#menu-desplegable-planes-bolsas a.cancelar").click(function(){
		$(this).parents("div.bolsa:first").removeClass("confirmar");
		$(this).parents("div.paso_2:first").hide().prev().show();
		$(this).parents("div.cabecera:first").find("div.precio").show();
		$(this).parents("div.cabecera:first").find("div.regalar").show();
	});
	
	/**
	 * Despliega detalle (nota)en seccion regalar
	 */
	$("#menu-desplegable-planes-bolsas div.nombre").click(function(){
		
		//la condicion sirve para que no muestre la descripcion si la bolsa fue regalada
		if($(this).parents("div.bolsa:first")
				  .find(".paso3:first")
				  .is(":hidden") && 
				  $(this).parents("div.bolsa:first")
				  .find(".paso_3:first")
				  .is(":hidden")){
			$(this).parents("div.bolsa:first").find("div.nota").toggle();
			//si esta visible el box-numero para regalar se oculta
			$(this).parents("div.bolsa:first").find("div.box-numero").hide();
			$(this).parents("div.bolsa:first").find("div.cancelar").hide();
			$(this).parents("div.bolsa:first").find("div.regalar").show();
			$(this).parents("div.bolsa:first").find("div.comprar").show();
			$(this).parents("div.bolsa:first").find("a.comprar").show();
			$(this).parents("div.bolsa:first").find("div.precio").show();
			$(this).parents("div.bolsa:first").find("div.paso_2").hide();
			$(this).parents("div.bolsa:first").removeClass("confirmar");
		}

	});
	
	/**
	 * Despliega detalle (nota) caso particular para sección contratar
	 */
	$("div.fila div.nombre").click(function(){
		
		if($(this).parents("div.fila:first").find(".paso_3").is(":hidden")){
			$(this).parents("div.fila").find("div.nota:first").toggle();
			$(this).parents("div.fila:first")
				.removeClass("confirmar")
				.find("div.paso_2:first")
				.hide();
			$(this).parents("div.fila:first").find("div.contratar:first").show();
			$(this).parents("div.fila:first").find("a.contratar:first").show();		
		}
		
	});
	
});

