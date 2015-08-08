var datosBloqueo = new Array();
var equipoBloqueo;
var inputBox = 0;
var claveGuardada="";


function clickBloqueoBAM(){
	var dataString = "random="+Math.random()*99999;
	$.ajax({
		type: 'POST',
		url: urlInitBloqueoDesbloqueo,
		async: true, 
		data: dataString,
		dataType: 'json',
		cache : false,
		success: function (resp){
			$('.cargandoBloqueo').hide();
			if(resp.estado == "Error"){
				$('.divError').show();
			}else{
				if ( resp.respuesta.desbloqueado == "0000" || resp.respuesta.desbloqueado == "5003" || resp.respuesta.desbloqueado == "5004"){
					$(".caja-ultimo .ultimo .nombre").html(resp.respuesta.descripcionEquipo);
					$('.datoSim').html(resp.respuesta.iccid);
					$('#imsiSeleccionada').html(resp.respuesta.msisdn);
					$('#numeroSeleccionado').html(resp.respuesta.msisdn);
					
					$('#emailBloqueo').val(resp.respuesta.consultarDatosBuicBean.emailUsuario + "@" + resp.respuesta.consultarDatosBuicBean.emailDominio );
					$('#telAdicionalNumBloqueo').val(resp.respuesta.consultarDatosBuicBean.areaFono);
					$('#telAdicionalBloqueo').val(resp.respuesta.consultarDatosBuicBean.numeroFono);
					
					$('#equipo_bloqueo_0').val(resp.respuesta.imsi+'*'
												+resp.respuesta.imei+'*'
												+resp.respuesta.descripcionEquipo+'*'
												+resp.respuesta.consultarDatosBuicBean.numeroDomicilio+'*'
												+resp.respuesta.consultarDatosBuicBean.deptoOff+'*'
												+resp.respuesta.consultarDatosBuicBean.region+'*'
												+resp.respuesta.consultarDatosBuicBean.ciudad+'*'
												+resp.respuesta.consultarDatosBuicBean.comuna+'*'
												+resp.respuesta.iccid
												);
				
					$('.divBloqueoRoboPerdida').show();	
					
				}else if ( resp.respuesta.bloqueoRoboHurto == "5000" || resp.respuesta.desbloqueado == "5005"  || resp.respuesta.desbloqueado == "5008"){
					
					$('.divDesbloqueoRoboHurto').show();
					
				}else if ( resp.respuesta.bloqueoRoboHurto == "5002" || resp.respuesta.desbloqueado == "5006"  || resp.respuesta.desbloqueado == "5009"){
					
					$('.divDesbloqueoHurto').show();	
					
				}else if ( resp.respuesta.bloqueoPerdidaExtravio == "5001" || resp.respuesta.desbloqueado == "5007"  || resp.respuesta.desbloqueado == "5010"){
					
					$(".dato.desbloqueoSim").html(resp.respuesta.iccid);
					$(".dato.desbloqueoEquipo").html(resp.respuesta.equipo);
					$(".dato.desbloqueoImei").html(resp.respuesta.imei);
					$(".dato.desbloqueoFecha").html(resp.respuesta.fechaPerdidaFormated);					
					$('.divDesbloqueoPerdidaExtravio').show();	
					
				}else{
					
					if(resp.respuesta.error=="4999"){
				         $('.divErrorDatos').show();
				     }else if(resp.respuesta.error=="6000"){
				         $('.pendienteBloqueoDesboqueo').show();
				         $('#mensaje_sol_pendiente').html('Actualmente existe una solicitud de bloqueo en curso, la cual quedar&aacute; finalizada en los pr&oacute;ximos minutos.');
				     }else if(resp.respuesta.error=="6001"){
				         $('.pendienteBloqueoDesboqueo').show();
				         $('#mensaje_sol_pendiente').html('Actualmente existe una solicitud de desbloqueo en curso, la cual quedar&aacute; finalizada en los pr&oacute;ximos minutos.');
				     }else if(resp.respuesta.error=="0020"){
				    	 $('.divErrorDesBloqueo').show();
				     }else if(resp.respuesta.error=="0019"){
				    	 $('.divErrorBloqueo').show();
				     }else{
				    	 $('.divError').show();
				     }	
					
				}
			}
		}
	});		
}
	
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
		$(this).parents('.seleccion-motivo').children('.formulario_boton').children('.btnBloqueoBAMPaso0').removeClass('btnAzulGrandeDesactivado btnAzulGrandeDesactivadoLargo');
		$(this).parents('.seleccion-motivo').children('.formulario_boton').children('.btnBloqueoBAMPaso0').addClass('btnAzulGrande btnAzulGrandeLargo');
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
		
		
		
	$('.btnBloqueoBAMPaso0').click(function(e) {
        if($(this).hasClass('btnAzulGrande')){
        	
        	$('.btnBloqueoPaso0').hide();
        	
				$('#bloqueo_0').hide();
						
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
				$('#bloqueo_1').show();

				if(inputBox == 0){
					$('.inputBoxBloqueo').inputBox();
					$('.selectBoxBloqueo').selectBox();
					inputBox = 1;
				}
		}
    });
	
	$('.btnCancelarBAMPaso1').click(function(e) { 
		if ($(this).hasClass('desactivado')){
			return false
		}
		$('#globoErrorBloqueo').hide();
		$('#bloqueo_1').hide();		
		$('#bloqueo_1 .caja-informacion').show();
		$('#bloqueo_0').show();
    });	
	
	
	$('.btnBloqueoBAMPaso1').click(function(e) {

		if ($(this).hasClass('desactivado')){
			return false
		}else{
			if ($(".bloqueoFormulario").valid()){

				$('.btnBloqueoBAMPaso1').hide();
	        	$('.campo .loading').show();
				
				$('#globoErrorBloqueoBloqueo').fadeOut();
				datosBloqueo.direccion = $('.dirBloqueo').val();
				datosBloqueo.email = $('.emailBloqueo').val();
				datosBloqueo.telefono = ($('.telAdicionalNumBloqueo').val()+' - '+$('.telAdicionalBloqueo').val());
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

				$('.btnBloqueoBAMPaso1').show();
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
	
	$('.modificarDirBloqueoBAM').click(function(e) {
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
			$('.btnBloqueoBAMPaso1, .btnCancelarBAMPaso1').removeClass('desactivado');
			return false
		}
		$('.btnBloqueoBAMPaso1, .btnCancelarBAMPaso1').addClass('desactivado');
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
			$('.modificarMailBloqueoBAM').html('Modificar');
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
		$('.btnBloqueoBAMPaso1, .btnCancelarBAMPaso1').removeClass('desactivado');
		$('.modificarDirBloqueoBAM').removeClass('abierto');
		$('.modificarDirBloqueoBAM').parent().removeClass('cancelar');
		$('.modificarDirBloqueoBAM').parent().parent().removeClass('sinPadding');
		$('#modificarDirCaja').hide();
		$('.modificarDirBloqueoBAM').html('Modificar');
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
	
	$('.modificarMailBloqueoBAM').click(function(e) {
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
			$('.btnBloqueoBAMPaso1, .btnCancelarBAMPaso1').removeClass('desactivado');
			return false
		}
		$('.btnBloqueoBAMPaso1, .btnCancelarBAMPaso1').addClass('desactivado');
		$('#globoErrorBloqueo').hide();
		$('#modificarMailCaja').show();
		$('.nuevoEmailBloqueo').val('');
		$(this).addClass('abierto');
		$(this).parent().addClass('cancelar');
		$(this).parent().parent().addClass('sinPadding');
		$(this).html('Cancelar');
		
		//cerramos la caja de dir si está abierta
		if ($('.modificarDirBloqueoBAM').hasClass('abierto')){
			$('.modificarDirBloqueoBAM').removeClass('abierto');
			$('.modificarDirBloqueoBAM').parent().removeClass('cancelar');
			$('.modificarDirBloqueoBAM').parent().parent().removeClass('sinPadding');
			$('#modificarDirCaja').hide();
			$('.modificarDirBloqueoBAM').html('Modificar');
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
		$('.btnBloqueoBAMPaso1, .btnCancelarBAMPaso1').removeClass('desactivado');
		$('.modificarMailBloqueoBAM').removeClass('abierto');
		$('.modificarMailBloqueoBAM').parent().removeClass('cancelar');
		$('.modificarMailBloqueoBAM').parent().parent().removeClass('sinPadding');
		$('.modificarMailBloqueoBAM').html('Modificar');
		$('#modificarMailCaja').hide();
		$('.btnGuardarMailbloqueo').show();
		$('.btnGuardarMailbloqueo').parents('.fila').next('.mensaje').hide();
		$('.emailBloqueo').val($('.nuevoEmailBloqueo').val());
    });
	
	$('.btnBuscarEquiposBAM').click(function(e) {
		e.preventDefault();
        $(this).parents('.caja-ultimo').hide();
		$("input[name=equipo_bloqueo]").parents('.escoge-movil').children('.fieldset').find('.btnBloqueoBAMPaso2').removeClass('btnAzul');
		$("input[name=equipo_bloqueo]").parents('.escoge-movil').children('.fieldset').find('.btnBloqueoBAMPaso2').addClass('btnDesactivado');
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

		$(this).parents('.escoge-movil').children('.fieldset').find('.btnBloqueoBAMPaso2').removeClass('btnDesactivado');
		$(this).parents('.escoge-movil').children('.fieldset').find('.btnBloqueoBAMPaso2').addClass('btnAzul');
		
        var cadenaTexto= $(this).attr('value'); 
        var arrayCadena = cadenaTexto.split('*');
        
        datosBloqueo.imsi = arrayCadena[0];
        datosBloqueo.imei = arrayCadena[1];
        equipoBloqueo = arrayCadena[2];
        datosBloqueo.equipo = equipoBloqueo;
        
        datosBloqueo.numeroDomicilioBUIC = arrayCadena[3];
        datosBloqueo.deptoOffBUIC = arrayCadena[4];
        datosBloqueo.regionBUIC = arrayCadena[5];
        datosBloqueo.ciudadBUIC = arrayCadena[6];
        datosBloqueo.comunaBUIC = arrayCadena[7];
        datosBloqueo.iccid = arrayCadena[8];
        
	});
	
	$('.btnCancelarBAMPaso2').click(function(e) {	
		$("input[name=equipo_bloqueo]").removeAttr("checked");
		$("input[name=equipo_bloqueo]").parents('.escoge-movil').children('.fieldset').find('.btnBloqueoBAMPaso2').removeClass('btnAzul');
		$("input[name=equipo_bloqueo]").parents('.escoge-movil').children('.fieldset').find('.btnBloqueoBAMPaso2').addClass('btnDesactivado');
        $('#bloqueo_2').hide();
        $('#bloqueo_1').show();
    });
	
	$("input[name=condiciones-bloqueo]").change(function(){
		if ($(this).attr('checked')){
			$(this).parents('.tabla').next('.fieldset').find('.btnBloqueoBAMPaso3').removeClass('btnDesactivado');
			$(this).parents('.tabla').next('.fieldset').find('.btnBloqueoBAMPaso3').addClass('btnAzul');
		}else{			
			$(this).parents('.tabla').next('.fieldset').find('.btnBloqueoBAMPaso3').removeClass('btnAzul');
			$(this).parents('.tabla').next('.fieldset').find('.btnBloqueoBAMPaso3').addClass('btnDesactivado');
		}			
	});
	
	$('.btnBloqueoBAMPaso2').click(function(e){
		
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
	
	$('.btnCancelarBAMPaso3').click(function(e) {	
		$("input[name=condiciones-bloqueo]").removeAttr("checked");
		$("input[name=condiciones-bloqueo]").parents('.tabla').next('.fieldset').find('.btnBloqueoBAMPaso3').removeClass('btnAzul');
		$("input[name=condiciones-bloqueo]").parents('.tabla').next('.fieldset').find('.btnBloqueoBAMPaso3').addClass('btnDesactivado');
		$("input[name=equipo_bloqueo]").removeAttr("checked");
		$("input[name=equipo_bloqueo]").parents('.escoge-movil').children('.fieldset').find('.btnBloqueoBAMPaso2').removeClass('btnAzul');
		$("input[name=equipo_bloqueo]").parents('.escoge-movil').children('.fieldset').find('.btnBloqueoBAMPaso2').addClass('btnDesactivado');
        $('#bloqueo_3').hide();	
		$('#bloqueo_2').show();		
    });
	
	$('.btnBloqueoBAMPaso3').click(function(e){
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
            

            var operacionComercial = "1";
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
			
			dataString += "&numeroDomicilioBUIC="+datosBloqueo.numeroDomicilioBUIC;
			dataString += "&deptoOffBUIC="+datosBloqueo.deptoOffBUIC;
			dataString += "&regionBUIC="+datosBloqueo.regionBUIC;
			dataString += "&ciudadBUIC="+datosBloqueo.ciudadBUIC;

			dataString += "&comunaBUIC="+datosBloqueo.comunaBUIC;
			
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
	
	$('.btnDesbloqueoBAMPaso1').click(function(e) {
        $('#desbloqueo_0').hide();
		$('#desbloqueo_1').show();
    });
	
	$('.btnCancelarDesbloqueoBAMPaso1').click(function(e) {        
		$('#globoErrorBloqueo').hide();
		$('#desbloqueo_1').hide();
		$('#desbloqueo_0').show();

		$('.claveValid').html('').hide();
		$('.claveDesbloqueoBAMPerdida').val('');
		$('.claveInput').show();
		$('.btnDesbloqueoBAMPaso2').removeClass('btnAzul').addClass('btnDesactivado');
		

    });
	
	//validar clave para desbloqueo
	
	$('.btnDesbloqueoBAMPaso2').click(function(e) {
		
		if(datosBloqueo.inputClave == "OK" ){
			$('#desbloqueo_1').hide();
			$('#desbloqueo_2').show();
		}
		
    });
	
	$('.claveDesbloqueoBAMPerdida').keyup(function(e) {
	   $('.bloqueo .loading, .bloqueo .claveInvalida').hide();
	   if($(this).val().length == 4){
		   $('.bloqueo .loading').show();
		   
		   var dataString = "&claveBloqueo="+ $('.claveDesbloqueoBAMPerdida').val()+"&random="+Math.random()*99999;
		   datosBloqueo.inputClave = "";
		   $.ajax({
				type: 'POST',
				url: urlBtnDesbloqueoPaso1,
				data: dataString,
				async: true, 
				cache : false,
				dataType: 'json',
				success: function (resp){

			   if(resp.estado == 'Ok'){
					   $('.bloqueo .claveInvalida').hide();
					   $('.bloqueo .loading').hide();
					   $('.btnDesbloqueoBAMPaso2').removeClass('btnDesactivado').addClass('btnAzul');
					   $('.claveInput').hide();
					   $('.claveValid').html($('.claveDesbloqueoBAMPerdida').val()).show();
					   datosBloqueo.inputClave = "OK";
				 }else{
					   $('.bloqueo .loading').hide();
					   $('.btnDesbloqueoBAMPaso2').removeClass('btnAzul').addClass('btnDesactivado');
					   $('.bloqueo .claveInvalida').show();
				 }
				}
			});
	   }
    });
	
		
	
	$('.btnCancelarDesbloqueoBAMPaso2').click(function(e) {    
		datosBloqueo.inputClave = "";
		$('#desbloqueo_2').hide();
		$('#desbloqueo_1').show();
    });
	
	$('.btnDesbloqueoBAMPaso3').click(function(e) {
		
		var dataString = "&claveBloqueo="+ $('.claveDesbloqueoBAMPerdida').val();
		
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
