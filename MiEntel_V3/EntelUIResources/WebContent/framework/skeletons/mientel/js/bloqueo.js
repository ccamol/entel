var datosBloqueo = new Array();
var equipoBloqueo;
var inputBox = 0;
var claveGuardada="";


	
$(document).ready(function(e) {
	
	$("input[name=bloqueo-motivo]").removeAttr("checked");
	
				
	$("#fechaRoboSeleccion").datepicker({ 
		showOn: 'button', 
		//buttonImage: 'http://testmientel.i2b.cl/personas/framework/skins/mientel/img/icons/icon_calendar_red.gif',
		buttonImage: '../framework/skins/mientel/img/icons/icon_calendar_red.gif',
		buttonText: 'Selecciona una fecha',
		dateFormat: 'dd-mm-yy',
		buttonImageOnly: true,
		showAnim: 'show',
		maxDate: '+0d'
	});
	$("#ui-datepicker-div").hide();
	
	$("#fechaDenuncioSeleccion").datepicker({ 
		showOn: 'button', 
		buttonImage: '../framework/skins/mientel/img/icons/icon_calendar_red.gif',
		buttonText: 'Selecciona una fecha',
		dateFormat: 'dd-mm-yy',
		buttonImageOnly: true,
		showAnim: 'show',
		maxDate: '+0d'
	});
	
	var tipoBloqueo="";
    // Despliega bloque por robo o por extravio para Suscripcion
	$("input[name=bloqueo-motivo]").click(function(){
		$(this).parents('.seleccion-motivo').children('.formulario_boton').children('.btnBloqueoPaso0').removeClass('btnAzulGrandeDesactivado btnAzulGrandeDesactivadoLargo');
		$(this).parents('.seleccion-motivo').children('.formulario_boton').children('.btnBloqueoPaso0').addClass('btnAzulGrande btnAzulGrandeLargo');
		tipoBloqueo = $(this).attr('id');
		var claveuno;
		// metodos para validar la clave en caso que sea necesario
		$.validator.addMethod("clave", function(value, element) {
			claveuno = value;
		  if( tipoBloqueo == "bloqueo_perdida" && value == '')
			  return false;        
		  return true;
		}, "Debe ingresar una clave");
		
		$.validator.addMethod("clave1", function(value, element) {
		  if(( tipoBloqueo == "bloqueo_perdida" && value == '' )|| value != claveuno)
			  return false;        
		  return true;
		}, "Debe reingresar la clave correctamente");		
		});
		
		$.validator.addMethod("claveDesbloqueo", function(value, element) {
		  //if(tipoBloqueo == "bloqueo_perdida" && value != claveGuardada)
			//  return false;        
		  return true;
		}, "Clave incorrecta");
		
		
	$('.btnBloqueoPaso0').click(function(e) {
        if($(this).hasClass('btnAzulGrande')){
        	
        	$(this).hide();
        	$('.formulario_boton .loading').show();
        	
			$.ajax({
				type: 'POST',
				url: urlBtnBloqueoPaso0,
				async: true, 
				dataType: 'json',
				success: function (resp){
				 if(resp.estado == 'Ok'){
					 	datosBloqueo.imei = resp.respuesta.imei;
					 	
						$('#bloqueo_0').hide();
						$('#emailBloqueo').val(resp.respuesta.emailUsuario + "@" + resp.respuesta.emailDominio );
						$('#telAdicionalNumBloqueo').val(resp.respuesta.areaFono);
						$('#telAdicionalBloqueo').val(resp.respuesta.numeroFono);
						
						if ( tipoBloqueo == "bloqueo_perdida" ){
							$('#bloqueo_1').addClass('perdida').removeClass('robo');
							$('#bloqueo_1 .caja-informacion').hide();
							$('#bloqueo_1 .ejemplo.FechaAprox').html('Fecha aproximada en la que ocurri&oacute; la perdida o el extrav&iacute;o');
							$('#bloqueo_1 .ejemplo.horaBloq').html('Hora de la denuncia');
							
							$('.bloqueo .clave').show();
							$('#bloqueo_1').find('h1').html('Bloqueo por P&eacute;rdida o Extrav&iacute;o<span>Paso 1 de 3</span>');
						}else{
							$('#bloqueo_1').addClass('robo').removeClass('perdida');
							$('.bloqueo .clave').hide();
							
							if ( tipoBloqueo == "bloqueo_hurto" ){
								$('#bloqueo_1 .ejemplo.FechaAprox').html('Fecha aproximada en la que ocurri&oacute; el hurto');	
								$('#bloqueo_1').find('h1').html('Bloqueo por Hurto<span>Paso 1 de 3</span>');
							}
							
							if ( tipoBloqueo == "bloqueo_robo" ){
								$('#bloqueo_1 .ejemplo.FechaAprox').html('Fecha aproximada en la que ocurri&oacute; el robo');	
								$('#bloqueo_1').find('h1').html('Bloqueo por Robo <span>Paso 1 de 3</span>');
							}
							
						}
						$('.btnBloqueoPaso0').show();
			        	$('.formulario_boton .loading').hide();
						$('#bloqueo_1').show();

						if(inputBox == 0){
							$('.inputBoxBloqueo').inputBox();
							$('.selectBoxBloqueo').selectBox();
							inputBox = 1;
						}
				 }
				}
			});
        	
		}
    });
	
	$('.btnCancelarPaso1').click(function(e) { 
		if ($(this).hasClass('desactivado')){
			return false
		}
		$('#globoErrorBloqueo').hide();
		$('#bloqueo_1').hide();		
		$('#bloqueo_1 .caja-informacion').show();
		$('#bloqueo_0').show();
    });	
	
	
	$('.btnBloqueoPaso1').click(function(e) {
		if ($(this).hasClass('desactivado')){
			return false
		}else{
			
			maxlongitud = maxlongitud = parseInt($(".ampliacionNumerica").attr('maxlength'));
			$(".bloqueoFormulario").find('input[name="telAdicionalBloqueo"]').rules('add', {
				required:true,
				digits: true,
				minlength:maxlongitud
			});
			
			if ($(".bloqueoFormulario").valid()){

				$('.btnBloqueoPaso1').hide();
	        	$('.campo .loading').show();
				
				$('#globoErrorBloqueoBloqueo').fadeOut();
				datosBloqueo.direccion = $('.dirBloqueo').val();
				datosBloqueo.email = $('.emailBloqueo').val();
				datosBloqueo.telefono = (parseInt($('.telAdicionalNumBloqueo').val(),10)+' - '+$('.telAdicionalBloqueo').val());
				datosBloqueo.areaFonoContacto = $('.telAdicionalNumBloqueo').val();
				datosBloqueo.fonoContacto = $('.telAdicionalBloqueo').val();
				datosBloqueo.fecha = $('.fechaRobo').val();
				
				datosBloqueo.hora = $('.horaRobo option:selected').text()+':'+$('.minutoRobo option:selected').text()+' '+$('.ampmRobo option:selected').text();
				datosBloqueo.campoAMPM = $('.campoAMPMRobo option:selected').text();
				datosBloqueo.denuncia = $('.denuncioBloqueo').val();
				datosBloqueo.fechadenuncia = $('.fechaDenuncioRobo').val();
				datosBloqueo.denuncia1 = $('.denuncioBloqueo1').val();
				if ( tipoBloqueo == "bloqueo_robo" ){
					datosBloqueo.clave = "";
				}
				if ( tipoBloqueo == "bloqueo_hurto" ){
					datosBloqueo.clave = "";
				}
				if ( tipoBloqueo == "bloqueo_perdida" ){
					datosBloqueo.clave = $('.claveBloqueo').val();
				}			
				
				$('#bloqueo_1').hide();
				$('.listado-equipos').hide();

				$('.btnBloqueoPaso1').show();
	        	$('.campo .loading').hide();
				
		        $('.caja-ultimo').show();
				
				$('#bloqueo_2').show();		
				if ( tipoBloqueo == "bloqueo_robo" ){
					$('#bloqueo_2').find('h1').html('Bloqueo (Robo)<span>Paso 2 de 3</span>');
				}
				if ( tipoBloqueo == "bloqueo_hurto" ){
					$('#bloqueo_2').find('h1').html('Bloqueo (Hurto)<span>Paso 2 de 3</span>');
				}
				if ( tipoBloqueo == "bloqueo_perdida" ){
					$('#bloqueo_2').find('h1').html('Bloqueo (P&eacute;rdida o Extrav&iacute;o)<span>Paso 2 de 3</span>');
				}
			}
		}
    });
	
	$('.modificarDirBloqueo').click(function(e) {
		e.preventDefault();
		if ($(this).hasClass('abierto')){
			$(this).removeClass('abierto');
			$(this).parent().removeClass('cancelar');
			$(this).parent().parent().removeClass('sinPadding');
			$('#modificarDirCaja').hide();
			$(this).html('Modificar');
			$('.btnGuardarDirbloqueo').show();
			$('.btnGuardarDirbloqueo').parents('.fila').next('.mensaje').hide();
			$('#globoErrorBloqueo').hide();
			$('.btnBloqueoPaso1, .btnCancelarPaso1').removeClass('desactivado');
			return false
		}
		$('.btnBloqueoPaso1, .btnCancelarPaso1').addClass('desactivado');
		$('#globoErrorBloqueo').hide();		
		$('#modificarDirCaja').show();
		$(this).addClass('abierto');
		$(this).parent().addClass('cancelar');
		$(this).parent().parent().addClass('sinPadding');
		$(this).html('Cancelar');
		
		//cerramos la caja de Mail si está abierta
		if ($('.modificarMailBloqueo').hasClass('abierto')){
			$('.modificarMailBloqueo').removeClass('abierto');
			$('.modificarMailBloqueo').parent().removeClass('cancelar');
			$('.modificarMailBloqueo').parent().parent().removeClass('sinPadding');
			$('#modificarMailCaja').hide();
			$('.modificarMailBloqueo').html('Modificar');
			$('.btnGuardarMailbloqueo').show();
			$('.btnGuardarMailbloqueo').parents('.fila').next('.mensaje').hide();
			return false
		}
		
		
		$(".regionSelect").show();
		$(".regionText").hide();
		$(".ciudadSelect").show();
		$(".ciudadText").hide();
		$(".comunaSelect").show();
		$(".comunaText").hide();
		$(".calleInput").show();
		$(".calleText").hide();
		$(".numeroInput").show();
		$(".numeroText").hide();
		$(".deptoInput").show();
		$(".dptoText").hide();
		
    });
	
	$('.btnGuardarDirbloqueo').click(function(e){
		
		if ($(".modificarDireccion").valid()){
			
			$(".regionSelect").hide();
			$(".regionText").html($('.region-promociones option:selected').text());
			$(".regionText").show();
			
			$(".ciudadSelect").hide();
			$(".ciudadText").html($('#ciudadpromociones option:selected').text());
			$(".ciudadText").show();
			
			$(".comunaSelect").hide();
			$(".comunaText").html($('#comunapromociones option:selected').text());
			$(".comunaText").show();
			
			$(".calleInput").hide();
			$(".calleText").html($('.calle').val());
			$(".calleText").show();
			
			$(".numeroInput").hide();
			$(".numeroText").html($('.numero').val());
			$(".numeroText").show();
			
			$(".deptoInput").hide();
			$(".dptoText").html($('.depto_casa_otro').val());
			$(".dptoText").show();
			
			
			
			$(this).hide();
        	$(this).parents('.fila').next('.mensaje').show();
		}				
    });
	
	$('.btnConfirmarDirbloqueo').click(function(e){
		$('.btnBloqueoPaso1, .btnCancelarPaso1').removeClass('desactivado');
		$('.modificarDirBloqueo').removeClass('abierto');
		$('.modificarDirBloqueo').parent().removeClass('cancelar');
		$('.modificarDirBloqueo').parent().parent().removeClass('sinPadding');
		$('#modificarDirCaja').hide();
		$('.modificarDirBloqueo').html('Modificar');
		$('.btnGuardarDirbloqueo').show();
		$('.btnGuardarDirbloqueo').parents('.fila').next('.mensaje').hide();
		
		var dptoExist = $('.depto_casa_otro').val();
		
		
		if(dptoExist == null || dptoExist == ""){
			
			$('.dirBloqueo').val($('.calle').val()+', '+$('.numero').val()+', '+$('#comunapromociones option:selected').text()+', '+$('#ciudadpromociones option:selected').text()+', '+$('.region-promociones option:selected').text());
		}else{
			
			$('.dirBloqueo').val($('.calle').val()+', '+$('.numero').val()+', Depto/Oficina '+$('.depto_casa_otro').val()+', '+$('#comunapromociones option:selected').text()+', '+$('#ciudadpromociones option:selected').text()+', '+$('.region-promociones option:selected').text());
		}
		datosBloqueo.direccionFormated = $('.calle').val();
		datosBloqueo.numeroDomicilio = $('.numero').val();
		datosBloqueo.deptoOff = $('.depto_casa_otro').val();
		datosBloqueo.region = $('.region-promociones option:selected').val();
		datosBloqueo.ciudad = $('#ciudadpromociones option:selected').text();
		datosBloqueo.comuna = $('#comunapromociones option:selected').text();
		
	
    });
	
	$('.modificarMailBloqueo').click(function(e) {
		e.preventDefault();
		$('.nuevoEmailBloqueo').removeAttr('readonly');
		if ($(this).hasClass('abierto')){
			$(this).removeClass('abierto');
			$(this).parent().removeClass('cancelar');
			$(this).parent().parent().removeClass('sinPadding');
			$('#modificarMailCaja').hide();
			$(this).html('Modificar');
			$('.btnGuardarMailbloqueo').show();
			$('.btnGuardarMailbloqueo').parents('.fila').next('.mensaje').hide();
			$('#globoErrorBloqueo').hide();
			$('.btnBloqueoPaso1, .btnCancelarPaso1').removeClass('desactivado');
			return false
		}
		$('.btnBloqueoPaso1, .btnCancelarPaso1').addClass('desactivado');
		$('#globoErrorBloqueo').hide();
		$('#modificarMailCaja').show();
		$('.nuevoEmailBloqueo').val('');
		$(this).addClass('abierto');
		$(this).parent().addClass('cancelar');
		$(this).parent().parent().addClass('sinPadding');
		$(this).html('Cancelar');
		
		//cerramos la caja de dir si está abierta
		if ($('.modificarDirBloqueo').hasClass('abierto')){
			$('.modificarDirBloqueo').removeClass('abierto');
			$('.modificarDirBloqueo').parent().removeClass('cancelar');
			$('.modificarDirBloqueo').parent().parent().removeClass('sinPadding');
			$('#modificarDirCaja').hide();
			$('.modificarDirBloqueo').html('Modificar');
			$('.btnGuardarDirbloqueo').show();
			$('.btnGuardarDirbloqueo').parents('.fila').next('.mensaje').hide();
			return false
		}
    });
	
	$('.btnGuardarMailbloqueo').click(function(e){
		if ($(".modificarMail").valid()){
			$(this).hide();
        	$(this).parents('.fila').next('.mensaje').show();
        	$('.nuevoEmailBloqueo').attr('readonly','readonly');
		}
    });
	
	$('.btnConfirmarMailbloqueo').click(function(e){
		$('.btnBloqueoPaso1, .btnCancelarPaso1').removeClass('desactivado');
		$('.modificarMailBloqueo').removeClass('abierto');
		$('.modificarMailBloqueo').parent().removeClass('cancelar');
		$('.modificarMailBloqueo').parent().parent().removeClass('sinPadding');
		$('.modificarMailBloqueo').html('Modificar');
		$('#modificarMailCaja').hide();
		$('.btnGuardarMailbloqueo').show();
		$('.btnGuardarMailbloqueo').parents('.fila').next('.mensaje').hide();
		$('.emailBloqueo').val($('.nuevoEmailBloqueo').val());
    });
	
	$('.btnBuscarEquipos').click(function(e) {
		e.preventDefault();
        $(this).parents('.caja-ultimo').hide();
        
        $('.cargandoBloqueoListadoEquipos').show();               
		
        $.ajax({
			type: 'POST',
			url: urlBtnBloqueoPaso2,
			dataType: 'html',
			success: function (resp){
        		$('.listaEquipos').html(resp);
        		$('.cargandoBloqueoListadoEquipos').hide();
        		$('.listado-equipos').show();
        		//$(this).parents('.caja-ultimo').next('.listado-equipos').show();
			}
		});

		
    });
	
	
	$("input[name=equipo_bloqueo]").live("click",function(){

		$(this).parents('.escoge-movil').children('.fieldset').find('.btnBloqueoPaso2').removeClass('btnDesactivado');
		$(this).parents('.escoge-movil').children('.fieldset').find('.btnBloqueoPaso2').addClass('btnAzul');
		//equipoBloqueo = $(this).attr('id');
		//datosBloqueo.equipo = equipoBloqueo;
		
        var cadenaTexto= $(this).attr('value'); 
        var arrayCadena = cadenaTexto.split('*');
        datosBloqueo.imsi = arrayCadena[0];
        datosBloqueo.imei = arrayCadena[1];
        equipoBloqueo = arrayCadena[2];
        datosBloqueo.equipo = equipoBloqueo;       
        datosBloqueo.iccid = arrayCadena[3];
       
        //datosBloqueo.iccid = arrayCadena[3];
        /*
        var marca = arrayCadena[3];
        var modelo = arrayCadena[4];
		*/
	});
	
	$('.btnCancelarPaso2').click(function(e) {	
		$("input[name=equipo_bloqueo]").removeAttr("checked");
		$("input[name=equipo_bloqueo]").parents('.escoge-movil').children('.fieldset').find('.btnBloqueoPaso2').removeClass('btnAzul');
		$("input[name=equipo_bloqueo]").parents('.escoge-movil').children('.fieldset').find('.btnBloqueoPaso2').addClass('btnDesactivado');
        $('#bloqueo_2').hide();
        $('#bloqueo_1').show();
    });
	
	$("input[name=condiciones-bloqueo]").change(function(){
		if ($(this).attr('checked')){
			$(this).parents('.tabla').next('.fieldset').find('.btnBloqueoPaso3').removeClass('btnDesactivado');
			$(this).parents('.tabla').next('.fieldset').find('.btnBloqueoPaso3').addClass('btnAzul');
		}else{			
			$(this).parents('.tabla').next('.fieldset').find('.btnBloqueoPaso3').removeClass('btnAzul');
			$(this).parents('.tabla').next('.fieldset').find('.btnBloqueoPaso3').addClass('btnDesactivado');
		}			
	});
	
	$('.btnBloqueoPaso2').click(function(e){

		if ($(this).hasClass('btnDesactivado')){
			return false
		}else{
			$('#bloqueo_2').hide();
							
			if ( tipoBloqueo == "bloqueo_robo" ){
				$('#bloqueo_3').find('h1').html('Bloqueo (Robo)<span>Paso 3 de 3</span>');
				$('.campoDatoClave').hide();
				$('.datoMotivo').html('Robo');	
			}
			if ( tipoBloqueo == "bloqueo_hurto" ){
				$('#bloqueo_3').find('h1').html('Bloqueo (Hurto)<span>Paso 3 de 3</span>');
				$('.campoDatoClave').hide();
				$('.datoMotivo').html('Hurto');	
			}
			if ( tipoBloqueo == "bloqueo_perdida" ){
				$('#bloqueo_3').find('h1').html('Bloqueo (P&eacute;rdida o Extrav&iacute;o)<span>Paso 3 de 3</span>');
				$('.campoDatoClave').show();
				$('.datoClave').html(datosBloqueo.clave);
				$('.datoMotivo').html('P&eacute;rdida o Extrav&iacute;o');					
			}
			
		
			
			//se coocan los datos			
			$('.datoSim').html(datosBloqueo.iccid);
			$('.datoEquipo').html(datosBloqueo.equipo);
			$('.datoImei').html(datosBloqueo.imei);
			$('.datoFecha').html(datosBloqueo.fecha+' a las '+datosBloqueo.hora);
			$('.datoCorreo').html(datosBloqueo.email);
			$('.datoTel').html(datosBloqueo.telefono);
			//mostramos el paso 3
			$('#bloqueo_3').show();
		}
    });
	
	$('.btnCancelarPaso3').click(function(e) {	
		$("input[name=condiciones-bloqueo]").removeAttr("checked");
		$("input[name=condiciones-bloqueo]").parents('.tabla').next('.fieldset').find('.btnBloqueoPaso3').removeClass('btnAzul');
		$("input[name=condiciones-bloqueo]").parents('.tabla').next('.fieldset').find('.btnBloqueoPaso3').addClass('btnDesactivado');
		$("input[name=equipo_bloqueo]").removeAttr("checked");
		$("input[name=equipo_bloqueo]").parents('.escoge-movil').children('.fieldset').find('.btnBloqueoPaso2').removeClass('btnAzul');
		$("input[name=equipo_bloqueo]").parents('.escoge-movil').children('.fieldset').find('.btnBloqueoPaso2').addClass('btnDesactivado');
        $('#bloqueo_3').hide();	
		$('#bloqueo_2').show();		
    });
	
	$('.btnBloqueoPaso3').click(function(e){
		if ($(this).hasClass('btnDesactivado')){
			return false
		}else{
			$('#bloqueo_3').hide();
			$('.cargandoBloqueoFinal').show();

            var fechaDenuncia = $('.fechaDenuncioRobo').val();
            var horaDenuncia = $('.horaRobo').val();

            if (datosBloqueo.campoAMPM=='PM'){
                horaDenuncia = parseInt(horaDenuncia) + 12;
            }
            
            horaDenuncia = horaDenuncia + $('.minutoRobo').val() + '00';
            

            var operacionComercial = "";
            var codigoOperacion ="1";
            var fechaOperacionComercial = "";
            
            var motivoBloqueo = "";
			if ( tipoBloqueo == "bloqueo_robo"){				
				motivoBloqueo="1";
			}
			if (tipoBloqueo == "bloqueo_hurto" ){				
				motivoBloqueo = "2";
			}	
			if ( tipoBloqueo == "bloqueo_perdida"){				
				motivoBloqueo = "3";
			}		

			var dataString = "";
			
			
			dataString += "&email="+datosBloqueo.email;
			dataString += "&emailDominio="+datosBloqueo.emailDominio;
			dataString += "&areaFonoContacto="+datosBloqueo.areaFonoContacto;
			dataString += "&fonoContacto="+datosBloqueo.fonoContacto;
			dataString += "&direccion="+datosBloqueo.direccionFormated;
			dataString += "&motivoBloqueo="+motivoBloqueo;
			dataString += "&claveBloqueo="+datosBloqueo.clave;
			dataString += "&fechaRoboExtravio="+datosBloqueo.fecha;
			dataString += "&imei="+$.trim(datosBloqueo.imei);
			dataString += "&marcaModelo="+$.trim(datosBloqueo.equipo);
			dataString += "&operacionComercial="+operacionComercial;
			dataString += "&fechaOperacion="+fechaOperacionComercial;
			dataString += "&fechaConstancia="+datosBloqueo.fechadenuncia;
			dataString += "&horaConstancia="+horaDenuncia;
			dataString += "&unidad="+datosBloqueo.denuncia;
			dataString += "&nroConstancia="+datosBloqueo.denuncia1;
			dataString += "&codigoOperacion="+codigoOperacion;
			dataString += "&imsi="+datosBloqueo.imsi;
			
			dataString += "&numeroDomicilio="+datosBloqueo.numeroDomicilio;
			dataString += "&deptoOff="+datosBloqueo.deptoOff;
			dataString += "&region="+datosBloqueo.region;
			dataString += "&ciudad="+datosBloqueo.ciudad;
			dataString += "&comuna="+datosBloqueo.comuna;
			
			$.ajax({
                type: "GET",
                url: urlBtnBloqueoPaso3,
                data: dataString,
                dataType: 'json',
                //async:false,
                success: function (resp) {
					$('.cargandoBloqueoFinal').hide();
					if(resp.estado == "Error"){
						$('.divErrorBloqueo').show();
					}else{
						if(resp.estado == 'OK'){
	                        $('#bloqueo_3').hide();
	                        $('.divErrorBloqueo').show();
	                    }else{
	                        $('#bloqueo_3').hide();
	                        $('#bloqueo_4').show();
	                        //$('.divErrorBloqueo').show();
	                    }
					}
                },
                error: function(data) {
                    $('#bloqueo_3').hide();
                    $('.divErrorBloqueo').show();
                }
            });		
		}
    });
	
	$('.btnDesbloqueoPaso1').click(function(e) {
        $('#desbloqueo_0').hide();
		$('#desbloqueo_1').show();
    });
	
	$('.btnCancelarDesbloqueoPaso1').click(function(e) {        
		$('#globoErrorBloqueo').hide();
		$('#desbloqueo_1').hide();
		$('#desbloqueo_0').show();

		$('.claveValid').html('').hide();
		$('.claveDesbloqueoPerdida').val('');
		$('.claveInput').show();
		$('.btnDesbloqueoPaso2').removeClass('btnAzul').addClass('btnDesactivado');
		

    });
	
	//validar clave para desbloqueo
	
	$('.btnDesbloqueoPaso2').click(function(e) {
		
		if(datosBloqueo.inputClave == "OK" ){
			$('#desbloqueo_1').hide();
			$('#desbloqueo_2').show();
		}
		
    });
	
	$('.claveDesbloqueoPerdida').keyup(function(e) {
	   $('.bloqueo .loading, .bloqueo .claveInvalida').hide();
	   if($(this).val().length == 4){
		   $('.bloqueo .loading').show();
		   
		   var dataString = "&claveBloqueo="+ $('.claveDesbloqueoPerdida').val()+"&random="+Math.random()*99999;
		   datosBloqueo.inputClave = "";
		   $.ajax({
				type: 'POST',
				url: urlBtnDesbloqueoPaso1,
				data: dataString,
				async: true, 
				dataType: 'json',
				cache : false,
				success: function (resp){

			   if(resp.estado == 'Ok'){
					   $('.bloqueo .claveInvalida').hide();
					   $('.bloqueo .loading').hide();
					   $('.btnDesbloqueoPaso2').removeClass('btnDesactivado').addClass('btnAzul');
					   $('.claveInput').hide();
					   $('.claveValid').html($('.claveDesbloqueoPerdida').val()).show();
					   datosBloqueo.inputClave = "OK";
				 }else{
					   $('.bloqueo .loading').hide();
					   $('.btnDesbloqueoPaso2').removeClass('btnAzul').addClass('btnDesactivado');
					   $('.bloqueo .claveInvalida').show();
				 }
				}
			});
	   }
    });
	
		
	
	$('.btnCancelarDesbloqueoPaso2').click(function(e) {    
		datosBloqueo.inputClave = "";
		$('#desbloqueo_2').hide();
		$('#desbloqueo_1').show();
    });
	
	$('.btnDesbloqueoPaso3').click(function(e) {
		
		var dataString = "&claveBloqueo="+ $('.claveDesbloqueoPerdida').val();
		
		$('#desbloqueo_2').hide();
		$('.cargandoDesbloqueoFinal').show();
		
		$.ajax({
			type: 'POST',
			url: urlBtnDesbloqueoPaso3,
			data: dataString,
			async: true, 
			dataType: 'json',
			success: function (resp){
			$('.cargandoDesbloqueoFinal').hide();
			 if(resp.estado == 'Ok'){
					$('#desbloqueo_2').hide();
					$('#desbloqueo_3').show();
			 }else{
				 $('.divError').show();
			 }
			}
		});		
		
    });
});
