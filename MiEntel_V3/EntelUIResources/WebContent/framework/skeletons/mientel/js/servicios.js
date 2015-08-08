$(document).ready(function(){
	
	// Asigna estilo a los input y select
	// $('input.inputBox').inputBox();
	// $('select.selectBox').selectBox();
	
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
	.css({top: "-999px", left: "-999px", position: 'absolute' })
	.appendTo('body');
	
	$('#globoError').hide();
	/*---*/
	
	$('input').keypress(function() {
		$('#globoError').fadeOut();	
	});
	
	// Valida que solo se ingresen caracteres numericos
	$(".input_numerico").keypress(function(evt){
		var key = evt.keyCode ? evt.keyCode : evt.which ;
		return (key < 32 || (key >= 48 && key <= 57)); 
	});	
	
	$(".caso1").click(function(){
							   
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
							 
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado1").show();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado2").hide();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado").hide();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado").hide();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado1").hide();
		
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado2");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado");
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado1");
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado");
		$(this).parents(".tabla_fila:first").addClass("estadoDeshabilitado1");
		
		return false;
	});
	
	$(".casoH2").click(function(){
		   
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
							 
		$(this).parents(".tabla_fila:first").find(".confirmarHabilitar").show();
		$(this).parents(".tabla_fila:first").find(".opciones").hide();
		
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado");
		$(this).parents(".tabla_fila:first").addClass("estadoDeshabilitado1");
		
		return false;
	});
	
	$(".caso1H").click(function(){
		
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
				
		if($(this).hasClass('selectDias')){
			if($('.select_dias').val() == '')
			  {				
				return false;
			   }			
		 }		
		
		$(this).parents(".tabla_fila:first").find(".confirmarHabilitar").show();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado2").hide();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado").hide();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado").hide();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado1").hide();
		
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado2");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado");
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado1");
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado");
		$(this).parents(".tabla_fila:first").addClass("confirmarHabilitar");
		
	
		if($(this).parents(".tabla_fila:first").hasClass("resaltador")){		
			$(this).parents(".tabla_fila:first").removeClass("resaltador");	
			$(this).parents(".tabla_fila:first").addClass("marcarresaltador");
		 }
			
		return false;
	});
	
	$(".enlaceCancelarH").click(function(){		
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}

		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado1").hide();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado2").hide();		
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado").show();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado1").hide();
		$(this).parents(".tabla_fila:first").find(".confirmarHabilitar").hide();
		
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado2");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado");
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado1");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado1");
		$(this).parents(".tabla_fila:first").removeClass("confirmarHabilitar");
		$(this).parents(".tabla_fila:first").addClass("estadoDeshabilitado");		
		
		$(this).parents(".tabla_fila:first").find(".cerrarConfigurar").hide();
		$(this).parents(".tabla_fila:first").find(".btnAzulDelgado").show();
		$(this).parents(".tabla_fila:first").find(".configurar_contenido").hide();
		
		if($(this).parents(".tabla_fila:first").hasClass("marcarresaltador")){		
			$(this).parents(".tabla_fila:first").removeClass("marcarresaltador");	
			$(this).parents(".tabla_fila:first").addClass("resaltador");
		 }
		
		return false;
		
	});
	
	
	
	$(".caso3").click(function(){
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		if ($(this).hasClass('habilitarConfigurar')){
			if($(".autoTooltip").hasClass('activo')){
				$(".autoTooltip.activo").trigger("click");
			}
			
			$(this).parents(".columna3:first").find(".cerrarConfigurar").show();
			$(this).parent().parent().parent().find(".configurar_contenido").show();
			$(this).parents(".columna3:first").find(".enlaceConfigurar").hide();
			return false;
		}
		
		
		   if($(this).hasClass('cobroRevertido') || $(this).hasClass('avisale')){
			   
			    $(this).parents(".tabla_fila:first").find(".estadoDeshabilitado1").hide();
				$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado2").hide();
				$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado").hide();
				$(this).parents(".tabla_fila:first").find(".estadoHabilitado").hide();
				$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado3").show();
				$(this).parents(".tabla_fila:first").find(".confirmarHabilitar").hide(); // wbrochero
				
				$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado2");
				$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado");
				$(this).parents(".tabla_fila:first").addClass("estadoDeshabilitado3");
				$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado");
				$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado1");
				$(this).parents(".tabla_fila:first").removeClass("confirmarHabilitar");// wbrochero
			   
				
			}else{
				$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado1").hide();
				$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado2").hide();
				$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado").hide();
				$(this).parents(".tabla_fila:first").find(".estadoHabilitado").hide();
				$(this).parents(".tabla_fila:first").find(".estadoHabilitado1").show();
				$(this).parents(".tabla_fila:first").find(".confirmarHabilitar").hide(); // wbrochero
				
				$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado2");
				$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado");
				$(this).parents(".tabla_fila:first").addClass("estadoHabilitado1");
				$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado");
				$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado1");
				$(this).parents(".tabla_fila:first").removeClass("confirmarHabilitar");// wbrochero
			}
		
		
		
		return false;
	});
	
	$(".caso4").click(function(){
		
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado1").hide();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado2").show();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado").hide();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado").hide();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado1").hide();
		
		$(this).parents(".tabla_fila:first").addClass("estadoDeshabilitado2");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado");
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado1");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado1");
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado");		
		return false;		
	});
	
	$(".enlaceCancelar").click(function(){
										
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado1").hide();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado2").hide();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado").hide();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado").show();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado1").hide();
		
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado2");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado");
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado1");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado1");
		$(this).parents(".tabla_fila:first").addClass("estadoHabilitado");
		return false;
		
	});
	
	// Muestra la tabla de configuracion
	$(".enlaceConfigurar").click(function(){
		
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		$(this).parents(".columna3:first").find(".cerrarConfigurar").show();
		$(this).parent().parent().parent().find(".configurar_contenido").show();
		$(this).parents(".columna3:first").find(".enlaceConfigurar").hide();
		loadConfiguracionBuzon();
		return false;
	});
	
	function loadConfiguracionBuzon(){
		if($('#buzon_basico_hidden').val()=="true"){
			$('input[name="avisar"]')[0].checked = true;
		}
		if($('#buzon_premium_hidden').val()=="true"){
			$(".radio_configurar_buzon").trigger('click');
			$('input[name="avisar"]')[1].checked = true;
			
			if($('#tipoPerfil_hidden').val() == $('#perfilMMS_hidden').val()){
				$('input[name=tipoPerfil]')[0].checked = true;
			}else if($('#tipoPerfil_hidden').val() == $('#perfilMail_hidden').val()){
				$(".radio_configurar_premium").trigger('click');
				$('input[name=tipoPerfil]')[1].checked = true;
			}
		}
	}
	
	// Muestra la tabla de configuracion (Caso - edicion usuario)
	$(".enlaceConfigurar2").click(function(){
		
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		$(this).parents(".columna3:first").find(".cerrarConfigurar2").show();
		$(this).parent().parent().parent().find(".configurar_contenido").show();
		$(this).parents(".columna3:first").find(".enlaceConfigurar2").hide();
		return false;
	});
	
	// Cierra la tabla de configuracion
	$(".enlaceCerrar").click(function(){
		$(this).parents(".columna3:first").next(".configurar_contenido:first").find(".globoError").fadeOut(200);
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		$(this).parents(".columna3:first").find(".enlaceConfigurar").show();
		$(this).parent().parent().parent().parent().find(".configurar_contenido").hide();
		$(this).parents(".columna3:first").find(".cerrarConfigurar").hide();
		$("#alerta_oculta").hide();
		
		/*----*/
		var contenido = $(this).parents('.tabla_fila:first').find('div.configurar_contenido');
		contenido.find('div.configurar_contenido_inferior, div.configurar_contenido_inferior2').hide();
		contenido.find('.alerta_numero, .alerta_numero2').hide();
		var input = contenido.find('input[type=text]').not('input[readonly=true]').not('input[readonly=readonly]').val('');
		contenido.find('input[type=radio]').each(function() {
			$(this).get(0).checked = false;												  
		});
		
		/*-- Eliminar lo que sigue: solo para prototipo --*/
		contenido.find('.tabla_interna').each(function() {
			$(this).find('.tabla_interna_fila').each(function(i) {
				if(i>1) $(this).remove();
			});
		});		
		
		return false;
	});
	
	// Cierra la tabla de configuracion (Caso - edicion usuario)
	$(".enlaceCerrar2").click(function(){
		
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		$(this).parents(".columna3:first").find(".enlaceConfigurar2").show();
		$(this).parent().parent().parent().parent().find(".configurar_contenido").hide();
		$(this).parents(".columna3:first").find(".cerrarConfigurar2").hide();
		$("#alerta_oculta").hide();
		
		/*----*/
		var contenido = $(this).parents('.tabla_fila:first').find('div.configurar_contenido');
		contenido.find('div.configurar_contenido_inferior, div.configurar_contenido_inferior2').hide();
		contenido.find('.alerta_numero, .alerta_numero2').hide();
		contenido.find('input[type=text]').val('');
		contenido.find('input[type=radio]').each(function() {
			$(this).get(0).checked = false;												  
		});
		
		/*-- Eliminar lo que sigue: solo para prototipo --*/
		contenido.find('.tabla_interna').each(function() {
			$(this).find('.tabla_interna_fila').each(function(i) {
				if(i>1) $(this).remove();
			});
		});
		
		return false;
	});
	
	// Muestra el contenido inferior de la tabla de configuracion caso Buzon de
	// Voz
	$(".radio_configurar_buzon").click(function(){
		$(this).parents(".configurar_contenido:first").find(".globoError").fadeOut(200);
		switch ($(this).val()){
			case 'buzon_basico':
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior").hide();
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior1").show();
				break;
				
			case 'buzon_premium':
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior1").hide();
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior").show();
				break;
				
			default:
				return false;
		}
	});
	// Muestra el contenido inferior de la tabla de configuracion caso Buzon de
	// Voz premium
	$(".radio_configurar_premium").click(function(){
		$(this).parents(".configurar_contenido:first").find(".globoError").fadeOut(200);
		switch ($(this).val()){
			case 'vm2mms':
				$(this).parents(".configurar_contenido_inferior:first").find(".opcion_email").hide();
				break;
				
			case 'vm2email':
				$(this).parents(".configurar_contenido_inferior:first").find(".opcion_email").show();
				break;
				
			default:
				return false;
		}
	});
	
	// Muestra el contenido inferior de la tabla de configuracion caso avisame
	$(".radio_configurar").click(function(){
		$(this).parents(".configurar_contenido:first").find(".globoError").fadeOut(200);
		switch ($(this).val()){
			case 'avisar_todos':
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior").show();
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior2").hide();
				break;
				
			case 'avisar_a':
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior").hide();
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior2").show();
				break;
				
			default:
				return false;
		}
	});
	
	// Muestra el contenido inferior de la tabla de configuracion caso comunik-2
	$(".radio_configurar2").click(function(){
		
		switch ($(this).val()){
			case 'avisar_todos':
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior").show();
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior2").hide();
				break;
				
			case 'avisar_a':
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior").hide();
				$(this).parents(".configurar_contenido:first").find(".configurar_contenido_inferior2").show();
				break;
				
			default:
				return false;
		}
	});
	
	// Agrega un numero a la tabla de numeros en la opcion (Configurar)
	$(".btn_agregar1").click(function(){
		
		var formulario = $(this).parents("form:first");
		var numero = $(this).parents(".padre:first").find(".agregar_todos").val();
		
		var flagControl = false;
		var cantidadNumeros = $(this).parents(".padre:first").find(".tabla_interna_fila");
		for(var i=0; i<cantidadNumeros.length; i++) {
			var num = parseInt(cantidadNumeros.eq(i).find('div:first').text());
			if(num == numero) {
				flagControl = true;
			}
		}
		if(!flagControl) {

			if(formulario.valid()) {
				var filaDato = "<div class='tabla_interna_fila clearfix'><div class='tabla_interna_columna1'>"+numero+"</div><div class='tabla_interna_columna2'><a href='#' class='enlaceEliminar'>eliminar</a></div></div>";
				
				$(this).parents(".padre:first").find(".inputBox").val("");
				$(this).parents(".padre:first").find(".tabla_interna").append(filaDato);
				var numerosE=$('.numerosExcluidosLista').val();
				$('.numerosExcluidosLista').val(numerosE+numero+',');
				
				// Elimina un numero de la tabla
				$(".enlaceEliminar").click(function(){
					var numeroElim=$(this).parent().prev().html();
					var numerosE=$('.numerosExcluidosLista').val();
					$('.numerosExcluidosLista').val(numerosE.replace(numeroElim+",", ""));
					
					$(this).parents(".tabla_interna_fila:first").remove();
					$("#alerta_oculta").hide();
					$(".inputBox").val("");					
					return false;
				});
				
			}
			
		} else {
			$(this).parents(".padre:first").find(".alerta_numero2").find('td:first').html('Este n&uacute;mero ya ha sido registrado').end().show();
		}
		return false;
	});
	
	$(".btn_agregarConFecha").click(function(){
		
		var numero = $(this).parents(".padre:first").find(".agregar_todos").val();
		
		var flagControl = false;
		var cantidadNumeros = $(this).parents(".padre:first").find(".tabla_interna_fila");
		for(var i=0; i<cantidadNumeros.length; i++) {
			var num = parseInt(cantidadNumeros.eq(i).find('div:first').text());
			if(num == numero) {
				flagControl = true;
			}
		}
		if(!flagControl) {
		
			if((numero == "")||(numero.length < 8)){
				$(this).parents(".padre:first").find(".alerta_numero").find('td:first').html('Ingrese un n&uacute;mero v&aacute;lido').end().show();
				return false;
			}
			
			if (numero != ""){
				var filaDato = " <div class='tabla_interna_fila clearfix'><div class='tabla_interna_columna1_corto'>"+numero+"</div><div class='tabla_interna_columna2_corto'>01/11/2008</div><div class='tabla_interna_columna3_corto'><a href='#' class='enlaceEliminar'>eliminar</a></div></div>";
				
				$(this).parents(".padre:first").find(".inputBox").val("");
				$(this).parents(".padre:first").find(".tabla_interna").append(filaDato);
				
				// Elimina un numero de la tabla
				$(".enlaceEliminar").click(function(){
					$(this).parents(".tabla_interna_fila:first").remove();
					return false;
				});
				
			}
			
		} else {
			$(this).parents(".padre:first").find(".alerta_numero").find('td:first').html('Este n&uacute;mero ya ha sido registrado').end().show();
		}
		
		return false;
	});
	
	$(".btn_agregar2").click(function(){
		
		var formulario = $(this).parents("form:first");
		var numero2 = $(this).parents(".padre:first").find(".agregar_a").val();
		
		var flagControl = false;
		var cantidadNumeros = $(this).parents(".padre:first").find(".tabla_interna_fila");
		for(var i=0; i<cantidadNumeros.length; i++) {
			var num = parseInt(cantidadNumeros.eq(i).find('div:first').text());
			if(num == numero2) {
				flagControl = true;
			}
		}
		if(!flagControl) {
		
			if(formulario.valid()) {
				var filaDato = "<div class='tabla_interna_fila clearfix'><div class='tabla_interna_columna1'>"+numero2+"</div><div class='tabla_interna_columna2'><a href='#' class='enlaceEliminar eliminarAvisados'>eliminar</a></div></div>";
				
				$(this).parents(".padre:first").find(".inputBox").val("");
				$(this).parents(".padre:first").find(".tabla_interna").append(filaDato);
				var numerosA=$('.numerosAvisadosLista').val();				
				$('.numerosAvisadosLista').val(numerosA+numero2+',');				
						
				
				// Elimina un numero de la tabla
				$(".enlaceEliminar").click(function(){
					var numeroElim=$(this).parent().prev().html();
					var numerosA=$('.numerosAvisadosLista').val();
					$('.numerosAvisadosLista').val(numerosA.replace(numeroElim+",", ""));
					$(this).parents(".tabla_interna_fila:first").remove();
					return false;
				});
				
			}
			
		} else {
			$(this).parents(".padre:first").find(".alerta_numero").find('td:first').html('Este n&uacute;mero ya ha sido registrado').end().show();
		}
		return false;
	});
	
	$(".btn_agregar3").click(function(){
		var formulario = $(this).parents("form:first");
		var cantidadNumeros = $(this).parents(".padre:first").find(".tabla_interna_fila");
									  
		if(cantidadNumeros.length >= 3){
			$(this).parents(".padre:first").find(".alerta_oculta").show();
		}
		else{
			$("#alerta_oculta").hide();
			var numero = $(this).parents(".opciones:first").find(".agregar_todos").val();
			
			var flagControl = false;
			for(var i=0; i<cantidadNumeros.length; i++) {
				var num = parseInt(cantidadNumeros.eq(i).find('div:first').text());
				if(num == numero) {
					flagControl = true;
				}
			}
			if(!flagControl) {
				if(formulario.valid()) {
					var f = new Date();
					var fechaHoy = (f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear());		
					var filaDato = " <div class='tabla_interna_fila clearfix'><div class='tabla_interna_columna1_corto'>"+numero+"</div><div class='tabla_interna_columna2_corto'>"+fechaHoy+"</div><div class='tabla_interna_columna3_corto'><a href='#' class='enlaceEliminar eliminarCobro'>eliminar</a></div></div>";
					
					$(this).parents(".padre:first").find(".inputBox").val("");
					$(this).parents(".padre:first").find(".tabla_interna").append(filaDato);
					
					// Elimina un numero de la tabla
					$(".enlaceEliminar").click(function(){
						var fechaElim=$(this).parent().prev().html();			
						var numeroElim=$(this).parent().prev().prev().html();			
						var numerosCobro=$('input[id*=numerosCobroRevertido]').val();
						$('input[id*=numerosCobroRevertido]').val(numerosCobro.replace(numeroElim+",", ""));
						$(this).parents(".tabla_interna_fila:first").remove();
						$("#alerta_oculta").hide();
						$(".inputBox").val("");
						return false;
					});
					
					var numerosCobro=$('input[id*=numerosCobroRevertido]').val();
					$('input[id*=numerosCobroRevertido]').val(numerosCobro+numero+',');
				}
				
			} else {
				$(this).parents(".padre:first").find(".alerta_numero2").find('td:first').html('Este n&uacute;mero ya ha sido registrado').end().show();
			}
				
		}
		return false;
	});
	
	$(".btn_agregar_ccVoz").click(function(){

		var formulario = $(this).parents("form:first");		
		var cantidadNumeros = $(this).parents(".padre:first").find(".tabla_interna_fila");
									  
		if(cantidadNumeros.length >= 3){
			$(this).parents(".padre:first").find(".alerta_oculta").show();
		}
		else{
			$("#alerta_oculta").hide();
			var numero = $(this).parents(".opciones:first").find(".agregar_todos").val();
		
			var flagControl = false;
			for(var i=0; i<cantidadNumeros.length; i++) {
				var num = parseInt(cantidadNumeros.eq(i).find('div:first').text());
				if(num == numero) {
					flagControl = true;
				}
			}
			if(!flagControl) {
		
				if(formulario.valid()) {
					var filaDato = " <div class='tabla_interna_fila clearfix'><div class='tabla_interna_columna1'>"+numero+"</div><div class='tabla_interna_columna2'><a href='#' class='enlaceEliminar eliminarExcluidos'>eliminar</a></div></div>";
					
					$(this).parents(".padre:first").find(".inputBox").val("");
					$(this).parents(".padre:first").find(".tabla_interna").append(filaDato);
					var numerosE=$('.numerosExcluidosLista').val();
					$('.numerosExcluidosLista').val(numerosE+numero+',');
					
					// Elimina un numero de la tabla
					$(".enlaceEliminar").click(function(){						
						var numeroElim=$(this).parent().prev().html();
						var numerosE=$('.numerosExcluidosLista').val();
						$('.numerosExcluidosLista').val(numerosE.replace(numeroElim+",", ""));
						$(this).parents(".tabla_interna_fila:first").remove();
						$("#alerta_oculta").hide();
						$(".inputBox").val("");
						return false;
					});	
				}
				
			} else {
				$(this).parents(".padre:first").find(".alerta_numero2").find('td:first').html('Este n&uacute;mero ya ha sido registrado').end().show();
			}
		}
		return false;
	});
	
	// Saca los mensajes de error al hacer focus en los inputs
	$(".input_numerico").focus(function(){
		$(this).parents(".alertador:first").find(".alerta_numero").hide();
		$(this).parents(".alertador:first").find(".alerta_numero2").hide();
	});
	
	// Elimina un numero de la tabla
	$(".enlaceEliminar").click(function(){		
		if ($(this).hasClass('eliminarAvisados')){
			var numeroElim=$(this).parent().prev().html();
			var numerosA=$('.numerosAvisadosLista').val();
			$('.numerosAvisadosLista').val(numerosA.replace(numeroElim+",", ""));
		}
		if ($(this).hasClass('eliminarExcluidos')){
			var numeroElim=$(this).parent().prev().html();
			var numerosE=$('.numerosExcluidosLista').val();
			$('.numerosExcluidosLista').val(numerosE.replace(numeroElim+",", ""));
		}
		if ($(this).hasClass('eliminarCobro')){			
			var fechaElim=$(this).parent().prev().html();			
			var numeroElim=$(this).parent().prev().prev().html();			
			var numerosCobro=$('input[id*=numerosCobroRevertido]').val();
			$('input[id*=numerosCobroRevertido]').val(numerosCobro.replace(numeroElim+",", ""));
		}	
		$(this).parents(".tabla_interna_fila:first").remove();
		$("#alerta_oculta").hide();
		$(".inputBox").val("");
		return false;
	});
	
	// Accion de guardar configuracion
	$(".guardarConfiguracion").click(function(){
		
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		// $("input[name='email']").val($("input[name='emailaux']").val());
		
		if($(this).hasClass('guardarHabilitar')){
			if($(this).hasClass('premium')){
				var radioTipoPerfil = $("input:radio[name='tipoPerfil']:checked");
				// Opcion Email
				if(!radioTipoPerfil.hasClass('mostar_contenido_inferior')){
					var inputEmail = $(this).parent().parent().find(".emailaux");
					
					if (validarEmail(inputEmail)) {
						mostrarNextGuardarHabilitar($(this));
					}
				}else{
					mostrarNextGuardarHabilitar($(this));					
				}
				
				$(this).parents(".tabla_fila:first").find(".habilitarbasico").hide();
				$(this).parents(".tabla_fila:first").find(".habilitarpremium").show();
				
			}
			if($(this).hasClass('basico')){
				mostrarNextGuardarHabilitar($(this));
				
				$(this).parents(".tabla_fila:first").find(".habilitarpremium").hide();
				$(this).parents(".tabla_fila:first").find(".habilitarbasico").show();
			}
			

			if($(this).parents(".tabla_fila:first").hasClass("resaltador")){		
				$(this).parents(".tabla_fila:first").removeClass("resaltador");	
				$(this).parents(".tabla_fila:first").addClass("marcarresaltador");
			}
			
		}else{
			$(this).parents(".estadoHabilitado:first").find(".enlaceConfigurar").show();
			$(this).parents(".configurar_contenido:first").css("display", "none");
			
			$(this).parents(".estadoHabilitado:first").find(".cerrarConfigurar").hide();
			
			$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado1").hide();
			$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado2").hide();
			// $(this).parents(".tabla_fila:first").find(".estadoDeshabilitado3").show();
			$(this).parents(".tabla_fila:first").find(".confirmarHabilitar").show();
			$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado").hide();
			$(this).parents(".tabla_fila:first").find(".estadoHabilitado").hide();
			$(this).parents(".tabla_fila:first").find(".estadoHabilitado1").hide();
			
			// $(this).parents(".tabla_fila:first").addClass("estadoDeshabilitado3");
			$(this).parents(".tabla_fila:first").addClass("confirmarHabilitar");
			$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado2");
			$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado");
			$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado1");
			$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado1");
			$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado");		
			
			
			if($(this).parents(".tabla_fila:first").hasClass("resaltador")){		
				$(this).parents(".tabla_fila:first").removeClass("resaltador");	
				$(this).parents(".tabla_fila:first").addClass("marcarresaltador");
			 }
			 
			
			if($(this).hasClass('opActivarAT')){		
				$(this).parents(".tabla_fila:first").find(".activarAT").show();
				
				$(this).parents(".tabla_fila:first").find(".activarSA").hide();
				$(this).parents(".tabla_fila:first").find(".guardarAT").hide();
				$(this).parents(".tabla_fila:first").find(".guardarSA").hide();
				$("input[name='lista_numero_avisar_todos']").val($("input[name='lista_numero_avisar_todosaux']").val());			                 	
			}
			if($(this).hasClass('opActivarSA')){	
				$(this).parents(".tabla_fila:first").find(".activarSA").show();
				
				$(this).parents(".tabla_fila:first").find(".activarAT").hide();
				$(this).parents(".tabla_fila:first").find(".guardarAT").hide();
				$(this).parents(".tabla_fila:first").find(".guardarSA").hide();
				$("input[name='lista_numero_avisar_a']").val($("input[name='lista_numero_avisar_aaux']").val());				
			}
			if($(this).hasClass('opGuardarAt')){			
				
				$("input[name='lista_numero_avisar_todos']").val($("input[name='lista_numero_avisar_todosaux']").val());
				$(this).parents(".tabla_fila:first").find(".guardarAT").show();
				$(this).parents(".tabla_fila:first").find(".activarAT").hide();	
				$(this).parents(".tabla_fila:first").find(".activarSA").hide();
				$(this).parents(".tabla_fila:first").find(".guardarSA").hide();
			}
			if($(this).hasClass('opGuardarSA')){		
				
				$("input[name='lista_numero_avisar_a']").val($("input[name='lista_numero_avisar_aaux']").val());
				
				$(this).parents(".tabla_fila:first").find(".guardarSA").show();
				$(this).parents(".tabla_fila:first").find(".activarAT").hide();	
				$(this).parents(".tabla_fila:first").find(".activarSA").hide();
				$(this).parents(".tabla_fila:first").find(".guardarAT").hide();
			}
			
			
		}
		
		
		
		return false;
	});
	
/* MOBIL MARKETING */	
	
	/* /PASO 1 */
	$('#serv_mm_datos_personales').find('a.modificar').click(function() {
		
		var $cuadro = $(this).parents('#serv_mm_datos_personales');
		if($(this).hasClass('activo')) {			
			
			// Restaurar estado de link Modificar
			$(this).text('Modificar').removeClass('activo');
			
			// Desplegar datos
			$cuadro.removeClass('modoEdicion')
				.find('.campo').each(function() {
					$(this).find('strong').show().end().find('.yellowField').hide();						  
				});
			
			$cuadro.find('.fsmmdp_direccion_detalle').show();
			
			$cuadro.find('.fsmmdp_direccion').hide();	
				
			// Esconder boton guardar
			$cuadro.find('#bt_serv_dp_actualizar').hide();
			
			// Esconder boton continuar
			$cuadro.find('#bt_serv_continuar_1').show();
			
			$cuadro.find('#bt_serv_dp_act_confirmar').hide();
			
			// Esconder globo Error
			$('#globoError').hide();
			
		} else {
			// Cambiar estado de link Modificar
			$(this).text('Cancelar').addClass('activo');
			
			// Desplegar campos
			$cuadro.addClass('modoEdicion')
				.find('.campo').each(function() {
					var inputVal = $(this).find('strong:first').hide().text();
					$(this).find('input:not(:radio)').val(inputVal).end().find('.yellowField').show();
				});
			
			
			$cuadro.find('.fsmmdp_direccion_detalle').hide();
			
			$cuadro.find('.fsmmdp_direccion').show();
			
			// Esconder boton continuar
			$cuadro.find('#bt_serv_continuar_1').hide();
				
			// Desplegar boton guardar
			$cuadro.find('#bt_serv_dp_actualizar').show();
			
			$cuadro.find('#bt_serv_dp_act_confirmar').hide();
			
		}
		return false;
	});
	
	var $cuadro = $('#serv_mm_datos_personales');	
	$cuadro.find('#bt_serv_dp_actualizar').click(function() {		
		
		var form = $('form.form_serv_mm_datos_personales_MMKT');		
		if(form.valid()) {			
			$(this).hide();
			$('#bt_serv_dp_act_confirmar').show();						
		}			
		return false;
	});	
	
	$cuadro.find('#bt_serv_continuar_1 a').click(function() {
		if(!$(this).hasClass('deshabilitado')){
			$('#serv_mm_paso_1').hide();
			$('#serv_mm_paso_2').show();
		
			estadoBotonCampanas('#serv_mm_paso_2 #tabla_informacion', '#bt_serv_continuar_2');
		}
		return false;
	});
	
	$cuadro.find('#bt_serv_dp_act_confirmar a').click(function() {
		
		// Actualizacion de datos
		var dataString=
			{
				region: $("select[id*=regionpromociones]").val(),
			    ciudad: $("input[id*=hcpromociones]").val(),
			    comuna: $("input[id*=hcompromociones]").val(),
			    calle: $("input[id*=calleBUIC]").val(),
			    nmro: $("input[id*=nmroBUIC]").val(),
			    dpto: $("input[id*=dptoBUIC]").val(),
			    email: $("input[id*=emailBUIC]").val(),
			    sexo: $("input[type='radio'][id*=sexoBUIC]:checked").val()
			};
		$.ajax({
	    	type: 'POST',
	        url: urlActualizaDatosBUIC,
	        dataType: 'json',	      
	        data: dataString,
	        async: false,
	        success: function (resp){	
			
			}
		});
		
		var param = obtenerParametroURL("contexto");
		if (param==null || param=='undefined' || param==''){
			location.href = location.href + '&contexto=mobileMKT';
		}
		else{
			location.reload();
		}		
		return false;
		
	});
	/* /PASO 1 */
	
	/* PASO 2 */
	// Muestra la tabla de configuración
	$(".MMEnlaceConfigurar").click(function(){
		
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		$(this).parents(".columna3:first").find(".MMCerrarConfigurar").show();
		$(this).parent().parent().parent().find(".configurar_contenido").show();
		$(this).parents(".columna3:first").find(".MMEnlaceConfigurar").hide();
		return false;
	});
	
	
	// Cierra la tabla de configuración
	$(".MMEnlaceCerrar").click(function(){
		$(this).parents(".columna3:first").next(".configurar_contenido:first").find(".globoError").fadeOut(200);
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		$(this).parents(".columna3:first").find(".MMEnlaceConfigurar").show();
		$(this).parent().parent().parent().parent().find(".configurar_contenido").hide();
		$(this).parents(".columna3:first").find(".MMCerrarConfigurar").hide();
		$("#alerta_oculta").hide();
				
		return false;
	});
	
	$(".MMCaso3").click(function(){
							   
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		$(this).parents(".tabla_fila:first").find(".MMCerrarConfigurar").hide();   
		$(this).parents(".tabla_fila:first").find(".MMConf_cont").hide();   
		$(this).parents(".tabla_fila:first").find(".MMEnlaceConfigurar").show();
		
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado1").hide();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado2").hide();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado").hide();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado").show();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado1").hide();
		
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado2");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado");
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado1"); //
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado1");
		
		$(this).parents(".tabla_fila:first").find(".flagHabilitado").val("1");
		
		estadoBotonCampanas('#serv_mm_paso_2 #tabla_informacion', '#bt_serv_continuar_2');
		
		return false;
	});
	
	//Para actualizacion de inscripcion
	
	$(".MMCaso3B").click(function(){
		
		$(this).parents(".tabla_fila:first").find(".flagHabilitado").val("1");

		var categoriasSel = obtenerCategoriasSel();
		var dataString={
			categorias: categoriasSel
		};
		
		$.ajax({
	    	type: 'POST',
	        url: urlActualizarSuscMMKT,
	        dataType: 'json',	      
	        data: dataString,
	        async: false,
	        success: function (resp){								
			}
		});
	
		var param = obtenerParametroURL("contexto");
		if (param==null || param=='undefined' || param==''){
			location.href = location.href + '&contexto=mobileMKT';
		}
		else{
			location.reload();
		}
		return false;
	});
	
	$(".MMCaso4B").click(function(){
		$(this).parents(".tabla_fila:first").find(".flagHabilitado").val("0");	
		
		var categoriasSel = obtenerCategoriasSel();
		var dataString={
			categorias: categoriasSel
		};
		$.ajax({
	    	type: 'POST',
	        url: urlActualizarSuscMMKT,
	        dataType: 'json',	      
	        data: dataString,
	        async: false,
	        success: function (resp){					
			}
		});	
		
		var param = obtenerParametroURL("contexto");
		if (param==null || param=='undefined' || param==''){
			location.href = location.href + '&contexto=mobileMKT';
		}
		else{
			location.reload();
		}
		
		return false;		
	});
	
	$(".MMCaso4").click(function(){
		
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado1").hide();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado2").hide();
		$(this).parents(".tabla_fila:first").find(".estadoDeshabilitado").show();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado").hide();
		$(this).parents(".tabla_fila:first").find(".estadoHabilitado1").hide();
		
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado2");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado");
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado1");
		$(this).parents(".tabla_fila:first").removeClass("estadoDeshabilitado1");
		$(this).parents(".tabla_fila:first").removeClass("estadoHabilitado");

		$(this).parents(".tabla_fila:first").find(".flagHabilitado").val("0");
		estadoBotonCampanas('#serv_mm_paso_2 #tabla_informacion', '#bt_serv_continuar_2');
		
		return false;		
	});
	
	$("#bt_serv_volver_1").click(function(){
		var $this = $(".MMEnlaceCerrar");
		$this.parents(".MMColumna3").next(".MMConf_cont").find(".globoError").fadeOut(200);
		
		if($(".autoTooltip").hasClass('activo')){
			$(".autoTooltip.activo").trigger("click");
		}
		
		$('#serv_mm_paso_1').show();
		$('#serv_mm_paso_2').hide();
		return false;
	});
	
	$("#bt_serv_continuar_2").click(function(){
		
		if(!$(this).hasClass('deshabilitado')){
			/* CERRAR INFO PASO 2 */
			var $this = $(".MMEnlaceCerrar");
			$this.parents(".MMColumna3").next(".MMConf_cont").find(".globoError").fadeOut(200);
			
			if($(".autoTooltip").hasClass('activo')){
				$(".autoTooltip.activo").trigger("click");
			}
			
			$this.parents(".MMColumna3").find(".MMEnlaceConfigurar").show();
			$this.parent().parent().parent().parent().find(".MMConf_cont").hide();
			$this.parents(".MMColumna3").find(".MMCerrarConfigurar").hide();
			$("#alerta_oculta").hide();
			/* CERRAR INFO PASO 2 */
			
			$('#serv_mm_paso_2').hide();
			$('#serv_mm_paso_3').show();
		}
		return false;
	});	
	/* /PASO 2 */
	
	/* PASO 3 */	
	$("#horCualquiera").click(function(){
		$('#globoError').hide();
		$("#form_serv_especif_hor").hide();
		$("#form_serv_hor").show();		
		
	});
	
	$("#horEspecifico").click(function(){
		$('#globoError').hide();
		$("#form_serv_hor").hide();
		$("#form_serv_especif_hor").show();			
		
	});

	$("#bt_serv_finalizar").click(function(){
		
		if($('#horCualquiera').attr('checked')){			
			var $form = $('#form_serv_mm_horario');			
			if($form.valid()){
				var horaInicial = "0";
				var horaFinal = "23";
				var diasSel = "1,2,3,4,5,6,7";
				
				crearSuscripcionMMKT(horaInicial, horaFinal, diasSel);
			}
		}else{
			var $form = $('form#form_serv_mm_hora_especifica');			
			if($form.valid()){
				
				var horaInicial = $("select[id*=seh_desde]").val()!=null? parseInt($("select[id*=seh_desde]").val()): "";
				var horaFinal = $("select[id*=seh_hasta]").val()!=null? parseInt($("select[id*=hasta]").val()): "";
				var diasSel = "";
				
				$(".checkBoxDay").each(function(i) {
					var el = $(this);
					
					if (el.attr("checked")){
						if (diasSel!=""){
							diasSel += ",";
						}
						diasSel += el.val();
					}		
				});
				
				crearSuscripcionMMKT(horaInicial, horaFinal, diasSel);
			}
		}
		return false;
	});
	
	$("#bt_serv_volver_2").click(function(){
		$('#globoError').hide();
		$('#serv_mm_paso_2').show();
		$('#serv_mm_paso_3').hide();
		return false;
	});	
	/* /PASO 3 */
	
	$("#serv_mm_desincribir").click(function(){
		$("#sev_mm_caja_inscrito").hide();
		$("#sev_mm_caja_desincribir").show();		
		return false;
	});
	
	$("#serv_mm_desc_cancelar").click(function(){
		$("#sev_mm_caja_inscrito").show();
		$("#sev_mm_caja_desincribir").hide();		
		return false;
	});
	
	$("#serv_mm_desincribir_final").click(function(){
		//Eliminar suscripcion		
		$.ajax({
	    	type: 'POST',
	        url: urlEliminarSuscMMKT,
	        dataType: 'json',	
	        async: false,
	        success: function (resp){				
			}
		});		
		
		var param = obtenerParametroURL("contexto");
		if (param==null || param=='undefined' || param==''){
			location.href = location.href + '&contexto=mobileMKT';
		}
		else{
			location.reload();
		}
		return false;
				
	});		
	/* /MOBIL MARKETING */
});

function abrirThickbox(){
	
	if($(".autoTooltip").hasClass('activo')){
		$(".autoTooltip.activo").trigger("click");
	}
	
	tb_show("","TB_condiciones.html?height=515&amp;width=535", false);
	return false;
}

function emailValido(el){
	if(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(el)) {
		return true;
	}
	else {
		return false;
	}
}

function validarEmail(el){
	
	var email = $(el).val();
	if (email == "") {
		$('#globoError').find('td:first').html("Ingrese un correo electr&oacute;nico");
		showEmailBuzonPremium(el);
		return false;
	}else if(!emailValido(email)) {
		$('#globoError').find('td:first').html("Ingrese un correo electr&oacute;nico v&aacute;lido");
		showEmailBuzonPremium(el);
		return false;
	}else{
		return true;
	}
	
}

function showEmailBuzonPremium(el) {
	
	var $globo = $('#globoError');
	var input = $(el).parents('div:first');
		
	if ($globo.is(':hidden')) {
		$globo.fadeIn(200, function() {
			$(el).focus();							
		});
	}
	
	$globo.css({
		'top': input.offset().top + parseInt(input.height())/2 - parseInt($globo.css('height'))/2,
		'left': input.offset().left + parseInt(input.width()) + 5 
	});
	
	$(window).resize(function() {
		$globo.css({
			'top': input.offset().top + parseInt(input.height())/2 - parseInt($globo.css('height'))/2,
			'left': input.offset().left + parseInt(input.width()) + 5 
		});
	});
	
	return false;
}

function mostrarNextGuardarHabilitar(el){
	$(el).parents(".estadoHabilitado:first").find(".enlaceConfigurar").show();
	$(el).parents(".configurar_contenido:first").hide();
	$(el).parents(".estadoHabilitado:first").find(".cerrarConfigurar").hide();
	
	$(el).parents(".tabla_fila:first").find(".estadoDeshabilitado1").hide();
	$(el).parents(".tabla_fila:first").find(".estadoDeshabilitado2").hide();
	$(el).parents(".tabla_fila:first").find(".estadoDeshabilitado3").hide();
	$(el).parents(".tabla_fila:first").find(".estadoDeshabilitado").hide();
	$(el).parents(".tabla_fila:first").find(".estadoHabilitado").hide();
	$(el).parents(".tabla_fila:first").find(".confirmarHabilitar").show();
	
	$(el).parents(".tabla_fila:first").removeClass("estadoDeshabilitado3");
	$(el).parents(".tabla_fila:first").removeClass("estadoDeshabilitado2");
	$(el).parents(".tabla_fila:first").removeClass("estadoDeshabilitado");
	$(el).parents(".tabla_fila:first").addClass("confirmarHabilitar");
	$(el).parents(".tabla_fila:first").removeClass("estadoDeshabilitado1");
	$(el).parents(".tabla_fila:first").removeClass("estadoHabilitado");
	
	return false;
}

function obtenerCategoriasSel(){
	var categoriasSel = "";
	
	var cajasSel = $(".tabla_fila");	
	var categoriaActual;
	
	$(cajasSel).each(function(i) {
		var el = $(this);
		
		if (el.find(".flagHabilitado").val()=="1"){
			if (categoriasSel!=""){
				categoriasSel += "|";
			}			
			categoriaActual = el.find(".idCategoria").val() + "-" + el.find(".nombreCategoria").val();
			categoriasSel += categoriaActual;
		}		
	});
	
	return categoriasSel;	
}

function crearSuscripcionMMKT(horaInicial, horaFinal, diasSel){	
	
	var categoriasSel = obtenerCategoriasSel();
	var dataString=	{
			categorias: categoriasSel,
			diasSusc: diasSel,
		    horaInicial: horaInicial,
		    horaFinal: horaFinal	    
	};
	$.ajax({
    	type: 'POST',
        url: urlCrearSuscMMKT,
        dataType: 'json',	      
        data: dataString,
        async: false,
        success: function (resp){			
		}
	});
	
	var param = obtenerParametroURL("contexto");
	if (param==null || param=='undefined' || param==''){
		location.href = location.href + '&contexto=mobileMKT';
	}
	else{
		location.reload();
	}
	return false;
}

function estadoBotonCampanas(tabla, boton){
	 var swBoton = false;
	 $(tabla+' .tabla_fila').each(function(){
	 if($(this).find('.estadoHabilitado').is(':visible') || $(this).find('.estadoDeshabilitado1').is(':visible')){
	   swBoton = true;
	   return false;
	  }
	 });  
	 if(swBoton){
	  $(boton).addClass('btnAzulGrande').removeClass('deshabilitado btnAzulGrandeDesactivado');
	  $(boton).removeAttr('style');
	 }else{
	  $(boton).addClass('deshabilitado btnAzulGrandeDesactivado').removeClass('btnAzulGrande');
	  $(boton).css({ width: 108, 'text-align': 'center' });
	 }
	}
