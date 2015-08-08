/**
 * @author Andres Pontt
 * @version 1.0
 * Archivo javascript para los efectos de la sección bolsas.
 */
$(document).ready(function(){
	$('input').focus(function(e) {
        $('.globoError').hide();
    });
	$('input').keypress(function(e) {
        $('.globoError').hide();
    });
	$(".zona-verano-caja div.regalar a.canjear").click(function(){
		//oculta el boton comprar
		//$(this).parents("div.regalar").prev().hide();
		//oculta la nota si esta abierta
		$('.bolsa').hide(); 
		$(this).parents("div.bolsa").show();
		$(this).parents("div.bolsa").find("div.nota").hide();
		//Hace aparecer el box
		$(this).parents("div.regalar").hide().next().show();
		$(this).parents(".cabecera:first").next().children(".box-numero").show();		
		$(".zona-verano-caja h2 span").html("Paso 2 de 3");
	});
	
	/**
	 * Se oculta la capa para regalar bolsa
	 */
	$("div.cancelar a.cancelarCanjear").click(function(){
		$('.globoError').hide();	
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
		$(".zona-verano-caja h2 span").html("Paso 1 de 3");
		$('.bolsa').show();
		
		
	});
	
	
	/*$("a.aceptarCanjear").click(function(){
		if($(this).parents('.paso1').find('input').val()==""){			
			$(this).parents('.fila').prev().children('.campo').children('.globoError').show();
			return false
		}
		$(this).parents('.cuerpo').prev().hide();
		$(this).parents('.cuerpo').parent('.bolsa').addClass('amarilla');
		$("div.cancelar a.cancelarCanjear").trigger('click');		
		$(this).parents('.bolsa').find('.cajaConfirmar').show();
		$(".zona-verano-caja h2 span").html("Paso 3 de 3");
		$('.bolsa').hide();
		$(this).parents("div.bolsa").show();
	    
    	var puntos = $(this).parents('div.bolsa').find('input.info_producto').val().split('|')[1];
    	var zona = $(this).parents('div.bolsa').find('input.info_producto').val().split('|')[3];
    	var descProducto  = $(this).parents('div.bolsa').find('input.info_producto').val().split('|')[4];    	
        $(this).parents('.bolsa').find('.texto_confirmar_canje').html(textoConfirmarCanje.replace('{producto}',descProducto).replace('{zona}',zona).replace('{puntos}',puntos));
    	
	});*/
	
	$('a.cancelarCanjear').click(function(e) {
		$(this).parents('.cajaConfirmar').parent('.bolsa').removeClass('amarilla');
        $(this).parents('.cajaConfirmar').next('.cabecera').show();
		$(this).parents('.cajaConfirmar').hide();
		
		$(".zona-verano-caja h2 span").html("Paso 1 de 3");
    });
	
	$('div.cancelarConfirmar a.cancelarCanjear').click(function(e) {
		$(this).parents('.cajaConfirmar').parent('.bolsa').removeClass('amarilla');
        $(this).parents('.cajaConfirmar').next('.cabecera').show();
		$(this).parents('.cajaConfirmar').hide();
		$('.bolsa').show();		
		$(this).parents('div.bolsa').find('div.cabeceraPaso1').show();
		$(".zona-verano-caja h2 span").html("Paso 1 de 3");
    });
	
	

	$("div.regalar a.canjear").live("click",function() {
		//$(this).parents("div.cabecera").next().find("form.paso1:first").find('a.aceptarCanjear').hide();	
	});
	$(document).ready(function(){	
		
		$("a.aceptarCanjear").click(function(){
		     var varBoton = this;
			  if($(this).parents('.paso1').find('.txt-numero').val().length < 6){
				  $(this).parents('.fila').prev().children('.campo').children('.globoError').show();				  
				  return false;
			  }
			  
			  if($(this).parents('.paso1').find('.txt-numero').val()==""){			
					$(this).parents('.fila').prev().children('.campo').children('.globoError').show();
					return false;
			  }
		                    
			  if($(this).parents('.paso1').find('.txt-numero').val().length == 6 && validandoClave=='0'){	   
				  validandoClave='1';
				  
				   var codigoStand= $(this).parents('div.bolsa').find('input.info_producto').val().split('|')[2];
				   var dataString = "codigoStand="+codigoStand+"&clave="+ $(this).parents('.paso1').find('.txt-numero').val();
				   varClaveGuardada=$(this).parents('.paso1').find('.txt-numero').val();
				   $.ajax({
						type: 'POST',
						url: urlValidarClave,
						data: dataString,
						async: true, 
						dataType: 'json',
						cache : false,
						success: function (resp){	
					     validandoClave='0';
					   if(resp.estado == 'Ok'){						   
						    $(varBoton).parents('.cuerpo').prev().hide();
							$(varBoton).parents('.cuerpo').parent('.bolsa').addClass('amarilla');
							$("div.cancelar a.cancelarCanjear").trigger('click');		
							$(varBoton).parents('.bolsa').find('.cajaConfirmar').show();
							$(".zona-verano-caja h2 span").html("Paso 3 de 3");
							$('.bolsa').hide();
							$(varBoton).parents("div.bolsa").show();
							//$(varBoton).parents('div.bolsa').find('input.clave_guardada').val($(this).parents('.paso1').find('input').val());
							
					    	var puntos = $(varBoton).parents('div.bolsa').find('input.info_producto').val().split('|')[1];
					    	var zona = $(varBoton).parents('div.bolsa').find('input.info_producto').val().split('|')[3];
					    	var descProducto  = $(varBoton).parents('div.bolsa').find('input.info_producto').val().split('|')[4];    	
					        $(varBoton).parents('.bolsa').find('.texto_confirmar_canje').html(textoConfirmarCanje.replace('{producto}',descProducto).replace('{zona}',zona).replace('{puntos}',puntos));
						   
						}else{
							varClaveGuardada='';
						     $(varBoton).parents('.cuerpo').prev().hide();
						     $(varBoton).parents('div.bolsa').find("div.cancelar a.cancelarCanjear").trigger('click'); 
						     $(varBoton).parents('div.bolsa').find('div.mensajes-lista-error').show();
						     $(varBoton).parents('div.bolsa').find('a.volverACanjear').show();
						     $(varBoton).parents('div.bolsa').find('div.mensajes-lista-error').html(resp.detalle);
						     $('.bolsa').hide();
							 $(varBoton).parents("div.bolsa").show();
						 }
						}
					});
			   }
		 });
		 
		/**
		 * Se oculta la capa para regalar bolsa
		 */	
		$("div.cancelarConfirmar a.volverACanjear").click(function(){			
			 var linkVolver = this;
			 $(linkVolver).parents('div.bolsa').find('div.mensajes-lista-error').hide();	
			 $(linkVolver).hide();	
			 $(linkVolver).parents('div.bolsa').find('div.cabeceraPaso1').show();
			 $(linkVolver).parents('div.bolsa').removeClass('exito');
			 $(linkVolver).parents('div.bolsa').find('div.cajaExitoCanje').html("");
			 $('.bolsa').show();
			 $(".zona-verano-caja h2 span").html("Paso 1 de 3");			 
		});
		

		$('a.confirmarCanjear').click(function(e) {			
			    var varImput = this;
			    var clave = varClaveGuardada;		    
			    var codProducto = $(varImput).parents('div.bolsa').find('input.info_producto').val().split('|')[0];
		    	var puntos = $(varImput).parents('div.bolsa').find('input.info_producto').val().split('|')[1];
		    	var codigoStand= $(varImput).parents('div.bolsa').find('input.info_producto').val().split('|')[2];	
		    	var zona = $(this).parents('div.bolsa').find('input.info_producto').val().split('|')[3];
		    	var descProducto  = $(this).parents('div.bolsa').find('input.info_producto').val().split('|')[4]; 		    	
		    	$('.bolsa').hide();
		    	
		    	 //INICIO Mensaje cargando
		    	 //$('div.regalar a.canjear').parents("div.cabecera").next().find("form.paso1:first").find('a.aceptarCanjear').hide();
			     $(varImput).parents('.cuerpo').prev().hide();
			     $(varImput).parents('.bolsa').find('.cajaConfirmar').hide();
			     $(varImput).parents('.bolsa').removeClass('amarilla');
			     $(varImput).parents('div.bolsa').find("div.cancelar a.cancelarCanjear").trigger('click'); 
			     $(varImput).parents('div.bolsa').find('div.fila_estado3').show();			     
			     //FIN Mensaje cargando
			     $('.bolsa').hide();
			     $(varImput).parents("div.bolsa").show();		     
				 var dataString = "&clave="+clave+"&codProducto="+codProducto+"&puntos="+puntos+"&codigoStand="+codigoStand;	
					 
					   $.ajax({
							type: 'POST',
							url: urlCanjear,
							data: dataString,
							async: true, 
							dataType: 'json',
							cache : false,
							success: function (resp){
						   $(varImput).parents('div.bolsa').find('div.fila_estado3').hide();
						    varClaveGuardada='';
							   if(resp.estado == 'Ok'){			
								        $('.bolsa').hide();
										$(varImput).parents('.bolsa').find('.cajaConfirmar').hide();
										$(varImput).parents('.bolsa').find('.cajaExitoCanje').show();
										$(varImput).parents('.cajaConfirmar').parent('.bolsa').removeClass('amarilla').addClass('exito');
										$(".zona-verano-caja h2 span").html("Paso 1 de 3");
										$(varImput).parents('div.bolsa').find('a.volverACanjear').show();										
										$(varImput).parents("div.bolsa").show();
										
										if(resp.respuesta.codRespuesta=='0'){
											$(varImput).parents('.bolsa').find('div.cajaExitoCanje').html("<p>"+textoExitoCanje.replace('{producto}',descProducto).replace('{zona}',zona).replace('{numeroPcs}',numeroPcs)+"<p/>");
										}else if(resp.respuesta.codRespuesta=='1'){
											$(varImput).parents('.bolsa').find('div.cajaExitoCanje').html("<p>"+textoExitoCanjeOB.replace('{producto}',descProducto).replace('{zona}',zona)+"<p/>");
										}
										
									    $(varImput).parents('div.bolsa').find('a.volverACanjear').css( {
									    	position : "relative",
									    	top :"24px"		  
										});
								}else{						    
									// $('div.regalar a.canjear').parents("div.cabecera").next().find("form.paso1:first").find('a.aceptarCanjear').hide();
								     $(varImput).parents('.cuerpo').prev().hide();
								     $(varImput).parents('.bolsa').find('.cajaConfirmar').hide();
								     $(varImput).parents('.bolsa').removeClass('amarilla');
								     $(varImput).parents('div.bolsa').find("div.cancelar a.cancelarCanjear").trigger('click'); 
								     $(varImput).parents('div.bolsa').find('div.mensajes-lista-error').show();
								     $(varImput).parents('div.bolsa').find('a.volverACanjear').show();
								     $(varImput).parents('div.bolsa').find('div.mensajes-lista-error').html(resp.detalle);	
								     $('.bolsa').hide();
									 $(varImput).parents("div.bolsa").show();
								}
							}
						});
			     });
		 
	});
	
	$('div.volver a.cancelarCanjear').click(function(e) {		
		var linkVolver = this;
		 $(linkVolver).parents('div.bolsa').find('div.mensajes-lista-error').hide();	
		 $(linkVolver).hide();	
		 $(linkVolver).parents('div.bolsa').find('div.cabeceraPaso1').show();
		 $('div.bolsa').find('div.cajaExitoCanje').html("");
		 $('.bolsa').show();
		 $(".zona-verano-caja h2 span").html("Paso 1 de 3");
		 
	});	
	
	$('.linea_tabs2 > .tab2').eq(0).addClass('seleccionado');

	$('.contenido_tabs > .contenido_tab').eq(0).css({'display':'block'});

	$('.tab2').click(function(){
		$('.tab2').each(function() {			
			$(this).removeClass('seleccionado');
		});
		$(this).addClass('seleccionado');
		
		if ($(this).hasClass('contenido1')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido1').css({'display': 'block'});
			volveralpasouno();
		}
		
		if ($(this).hasClass('contenido2')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido2').css({'display': 'block'});
			volveralpasouno();
		}
		
		if ($(this).hasClass('contenido3')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido3').css({'display': 'block'});
			volveralpasouno();
		}
		
		if ($(this).hasClass('contenido4')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido4').css({'display': 'block'});
			volveralpasouno();
		}
		
		if ($(this).hasClass('contenido5')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido5').css({'display': 'block'});
			volveralpasouno();
		}
		if ($(this).hasClass('contenido6')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido6').css({'display': 'block'});
		}
		if ($(this).hasClass('contenido7')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido7').css({'display': 'block'});
			volveralpasouno();
		}
		if ($(this).hasClass('contenido8')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido8').css({'display': 'block'});
		}
		if ($(this).hasClass('contenido9')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido9').css({'display': 'block'});
			volveralpasouno();
		}
		if ($(this).hasClass('contenido10')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido10').css({'display': 'block'});
		}
		if ($(this).hasClass('contenido11')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido11').css({'display': 'block'});
			volveralpasouno();
		}
		if ($(this).hasClass('contenido12')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido12').css({'display': 'block'});
			volveralpasouno();
		}
		if ($(this).hasClass('contenido13')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido13').css({'display': 'block'});
		}
		if ($(this).hasClass('contenido14')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido14').css({'display': 'block'});
			volveralpasouno();
		}
		if ($(this).hasClass('contenido15')){
			$('.zona-verano-caja .contenido_tabs .contenido_tab').css({'display': 'none'});
			$('.contenido15').css({'display': 'block'});
			volveralpasouno();
		}
	});
		
		

    if ($.browser.msie && parseInt($.browser.version) == 7 ) {	
    	$('.zona-verano-caja .contenido_tabs .debes-saber-caja .contenido').css({'margin-bottom': '-10px'});
	}
    if ($.browser.msie && parseInt($.browser.version) == 8 ) {	
    	$('.zona-verano-caja .contenido_tabs .debes-saber-caja .contenido').css({'margin-bottom': '-8px'});
	}
    
    if ($.browser.msie && parseInt($.browser.version) <= 6) {	
		$('.zona-verano-caja .contenido_tabs .debes-saber-caja .contenido').css({'margin-bottom': '-13px'});
	}
    
    
    /**
	 * Se oculta la capa para regalar bolsa
	 */	
	function volveralpasouno(){	
		 varClaveGuardada='';
		 var linkVolver = this;
		 $(linkVolver).parents('div.bolsa').find('div.mensajes-lista-error').hide();
		 $(linkVolver).parents('div.bolsa').find('div.cabeceraPaso1').show();
		 $(linkVolver).parents('div.bolsa').removeClass('exito');
		 $(linkVolver).parents('div.bolsa').find('div.cajaExitoCanje').html("");		 
		 $(".zona-verano-caja h2 span").html("Paso 1 de 3");
		 $('.bolsa').show();
		 $("div.cancelar a.cancelarCanjear").click();
		 $("div.cancelarConfirmar a.volverACanjear").click();
		 $('div.volver a.cancelarCanjear').click();
		 $('a.cancelarCanjear').click();
		 $('div.cancelarConfirmar a.cancelarCanjear').click();
	}
	
});

