var valueTecNot;
var valueCobNot;
var valueRecNot;
var datosReclamo = new Array();
var reclamoGenerado='0';
$(document).ready(function() {
	
	
	// Agrego globo para mensajes de error en el body
	var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
	.css({top: "-999px", left: "-999px"})
	.appendTo('body');
	$('#globoError').hide(); 
	
	$('a').click(function(e) {
		$('.globoError').fadeOut();
	});	
	
	$(".input_numerico").keypress(function(evt){
		var key = evt.keyCode ? evt.keyCode : evt.which ;
		return (key <= 31 || (key >= 48 && key <= 57)); 
	});
	
	$('.contenido1').click(function(e) {
		$('.bloqueConfirmacionExito').hide();
		$('.mensajeConfirmacionResumen').show();
		$('.mensajeAlertaGrande').hide();
		
		
	});
	
	
	
	
	
	$('.reclamoCobrosAntecedentes').focus(function() {
		$('.globoError').fadeOut();
    });
		
	
	$('.btnReclamoCobros').click(function(e) {
		var formValidado = true;
		datosReclamo.mensaje="";
		$('.globoError').fadeOut();	
		
		if($('.nombreUsuario').val()=='' && $('.nombreUsuario').val() != undefined){	
			  $('#globoError').find('td:first').html("Ingresa tu nombre y apellido.");
				mostrarError($('.nombreUsuario'),'1');
			    formValidado = false;
		}
	
		if($('select[name="reclamoReclamo"]').val()=='' && formValidado==true ){	
			  //$('.erroMsjTipoReclamo').show();
			  $('#globoError').find('td:first').html("Selecciona el reclamo.");
				mostrarError($('select[name="reclamoReclamo"]'),'1');
			    formValidado = false;
		}
		
		if($('select[name="reclamoReclamo"]').val()==relamoComercial){
			 if($('select[name="reclamoTipoReclamo"]').val()==''){				
				 $('#globoError').find('td:first').html("Selecciona el tipo de reclamo.");
					mostrarError($('select[name="reclamoTipoReclamo"]'),'1');
				    formValidado = false;
			 }else {				 
				 if($('select[name="reclamoTipoReclamo"]').val()=='1'){					 
					 if($('#reclamoCobrosDocumentoReclamado').val()==''){						
						 $('#globoError').find('td:first').html("Ingresa el n&uacute;mero documento.");
							mostrarError($('#reclamoCobrosDocumentoReclamado'),'1');
						    formValidado = false;
					 }
					 if(formValidado==true){
						 if($('.reclamoMontoCobrosTotal').val()==''){							
							 $('#globoError').find('td:first').html("Ingresa el monto impugnado.");
								mostrarError($('.reclamoMontoCobrosTotal'),'2');
							   formValidado = false;
						 }
					 }
					 
					 if(formValidado==true){
							if(documentoBuscadoBackup==''){							
								 $('#globoError').find('td:first').html("Verifique el n&uacute;mero de documento");
									mostrarError($('#reclamoVerifNroNoc'),'1');
								   formValidado = false;
							 }
					  }
				
					 if(formValidado==true){
						if($('#reclamoCobrosDocumentoReclamado').val()!==documentoBuscadoBackup){							
							 $('#globoError').find('td:first').html("Ingresa el n&uacute;mero de documento original.");
								mostrarError($('#reclamoVerifNroNoc'),'1');
							   formValidado = false;
						 }
					 } 
				 }
			 }			 
		}
		else if($('select[name="reclamoReclamo"]').val()==relamoTecnico){
			if($('select[name="reclamoTipoReclamoTecnico"]').val()==''){
				$('#globoError').find('td:first').html("Selecciona el tipo de reclamo.");
				 mostrarError($('select[name="reclamoTipoReclamoTecnico"]'),'1');
				 formValidado = false;
			 }
			
			//INICIO VALIDAR LOCALIZACION PROBLEMA
			
				if($('.regionpromocionesLocalizacion').val() < 1){
					$('#globoError').find('td:first').html("Seleccione una regi&oacute;n.");
					mostrarError($('.regionpromocionesLocalizacion'),'1');
					formValidado = false;
				}else if ($('.ciudadLocalizacionProblema').val() < 1){
					$('#globoError').find('td:first').html("Seleccione una ciudad.");
					mostrarError($('.ciudadLocalizacionProblema'),'1');
					formValidado = false;
				}else if ($('.comunaLocalizacionProblema').val() < 1){
					$('#globoError').find('td:first').html("Seleccione una comuna");
					mostrarError($('.comunaLocalizacionProblema'),'1');
					formValidado = false;
				}else if ($('.calleLocalizacionProblema').val() == "" || $('.calleLocalizacionProblema').val()=="Direcci\u00F3n y n\u00FAmero"){
					$('#globoError').find('td:first').html("Escriba la calle.");
					mostrarError($('.calleLocalizacionProblema'),'1');
					formValidado = false;
				}				
				 
				var direccion = $('.reclamoTecnicoDireccion').val($('.calleLocalizacionProblema').val()+', '+$('.comunaLocalizacionProblema option:selected').text()+', '+$('.ciudadLocalizacionProblema option:selected').text()+', '+$('.regionpromocionesLocalizacion option:selected').text());
		    // FIN VALIDAR LOCALIZACION PROBLEMA
			
			
		}
				
		
		if($('.reclamoCobrosAntecedentes').val()=='' && formValidado==true ){
				$('#globoError').find('td:first').html("Ingresa los antecedentes.");
				mostrarError($('.reclamoCobrosAntecedentes'),'0');
			    formValidado = false;
		}
		
		if(!$('input[name="checkReclamoCobrosNotificacionEmail"]').is(':checked') && 
		   !$('input[name="checkreclamoCobrosNotificacionSMS"]').is(':checked') &&
		   !$('input[name="checkReclamoCobrosNotificacionCarta"]').is(':checked') && formValidado
		 ){
			$('#globoError').find('td:first').html("Ingrese un medio de respuesta.");
			mostrarError($('.reclamoCobrosNotificacionSMS'),'2');
			formValidado = false;
		}
		
		if($('input[name="checkReclamoCobrosNotificacionEmail"]').is(':checked') && formValidado){
			if($('.reclamoCobrosNotificacionEmail').val()==''){				
					$('#globoError').find('td:first').html("Ingresa el email.");
					mostrarError($('.reclamoCobrosNotificacionEmail'),'2');
					formValidado = false;
			}			     
		}
		
		if($('input[name="checkreclamoCobrosNotificacionSMS"]').is(':checked') && formValidado){
			if($('.reclamoCobrosNotificacionSMS').val()==''){
				$('#globoError').find('td:first').html("Ingresa el m&oacute;vil.");
				mostrarError($('.reclamoCobrosNotificacionSMS'),'0');
				formValidado = false;	
			}	
        }
		
		if($('input[name="checkReclamoCobrosNotificacionCarta"]').is(':checked') && formValidado){
			if($('.reclamoTecnicoDireccion').val()=='' && formValidado==true ){	
				$('#globoError').find('td:first').html("Debe ingresar una direcci&oacute;n");
				 mostrarError($('.reclamoTecnicoDireccion'),'2');
				  formValidado = false;
			}
         }
		
		
		
		
		if(formValidado==true){
			
			$('.resumen_numero_reclamo').html($('#numeroReclamoDefoult').val());		
			$('#imp_numero_reclamado').val($('#numeroReclamoDefoult').val());			  
			$("#id_imp_reclamo").val($('select[name="reclamoReclamo"]').val());			
			$('.resumen_reclamo').html($('select[name="reclamoReclamo"] option:selected').text());
			$('#imp_reclamo').val($('select[name="reclamoReclamo"] option:selected').text());
			
			
			datosReclamo.movil=$('#numeroReclamoDefoult').val();
			datosReclamo.suboperacion=$('select[name="reclamoReclamo"]').val();
			datosReclamo.suboperacionText=$('select[name="reclamoReclamo"] option:selected').text();
			datosReclamo.email =$('.reclamoCobrosNotificacionEmail').val();
			
			datosReclamo.mensaje="Antecedentes: "+$('.reclamoCobrosAntecedentes').val();	
			datosReclamo.antecedentes=$('.reclamoCobrosAntecedentes').val(); 
			datosReclamo.documento="";
			datosReclamo.montoCobrado="";
			datosReclamo.monto="";
			datosReclamo.localizacion="";
			datosReclamo.cuenta="";
			datosReclamo.tipoDocumento="";
			
			
			
			if($('select[name="reclamoReclamo"]').val()==relamoTecnico){					
				$('.resumen_tipo_reclamo').html($('select[name="reclamoTipoReclamoTecnico"] option:selected').text());
				$('#imp_tipo_reclamo').val($('select[name="reclamoTipoReclamoTecnico"] option:selected').text());
				$('#id_imp_tipo_reclamo').val($('select[name="reclamoTipoReclamoTecnico"] option:selected').val());
				$('#imp_localizacion').val($('.reclamoTecnicoDireccion').val());
				
				var localizacion_problema = $('.regionpromocionesLocalizacion option:selected').text()+', '+$('.ciudadLocalizacionProblema option:selected').text()+', '+$('.comunaLocalizacionProblema option:selected').text()+', '+$('.calleLocalizacionProblema').val();
				datosReclamo.localizacion = localizacion_problema;
				$('.resumen_localizacion_problema').html(localizacion_problema);
				$('.solo_tecnico').show();				
				
				datosReclamo.motivo=$('select[name="reclamoTipoReclamoTecnico"] option:selected').val();
				datosReclamo.motivoText=$('select[name="reclamoTipoReclamoTecnico"] option:selected').text();
			}
			else if($('select[name="reclamoReclamo"]').val()==relamoComercial){				
				$('.resumen_tipo_reclamo').html($('select[name="reclamoTipoReclamo"] option:selected').text());
				$('#imp_tipo_reclamo').val($('select[name="reclamoTipoReclamo"] option:selected').text());
				$('#id_imp_tipo_reclamo').val($('select[name="reclamoTipoReclamo"] option:selected').val());			
				
				
				if($('select[name="reclamoTipoReclamo"] option:selected').val()==relamoBoletaFactura){
					$('.solo_comercial').show();
					
					if(datosReclamo.mensaje==''){
						datosReclamo.mensaje= datosReclamo.mensaje+"Documento: "+$('#reclamoCobrosDocumentoReclamado').val()+' , Monto:'+$('.reclamoMontoCobrosTotal').val()+' ';
					}else{
						datosReclamo.mensaje= datosReclamo.mensaje+" / Documento: "+$('#reclamoCobrosDocumentoReclamado').val()+' , Monto:'+$('.reclamoMontoCobrosTotal').val()+' ';
					}
					
					datosReclamo.documento=$('#reclamoCobrosDocumentoReclamado').val();
					datosReclamo.monto=$('#reclamoMontoCobrosTotalHidden').val();
					datosReclamo.cuenta=$('#reclamoMontoCuenta').val();
					datosReclamo.tipoDocumento=$('#reclamoTipoDocumento').val();
					datosReclamo.montoCobrado=$('.reclamoMontoCobrosTotal').val();
					
				}
								
				$('.resumen_numero_docuemnto').html($('#reclamoCobrosDocumentoReclamado').val());
				$('#imp_nro_doc').val($('#reclamoCobrosDocumentoReclamado').val());
				$('.resumen_monto').html($('.reclamoMontoCobrosTotal').val());
				$('#imp_monto_doc').val($('.reclamoMontoCobrosTotal').val());				
				
				
				datosReclamo.motivo=$('select[name="reclamoTipoReclamo"] option:selected').val();
				datosReclamo.motivoText=$('select[name="reclamoTipoReclamo"] option:selected').text();
			}
			
			  $('.resumen_antecedentes').html($('.reclamoCobrosAntecedentes').val());			  
			  $('#imp_antecedentes').val($('.reclamoCobrosAntecedentes').val());
			  

			  var medioRespuesta='';
			  var medioRespuestaHTML='';
			  datosReclamo.checkEmail='1';
			
				if($('input[name="checkReclamoCobrosNotificacionEmail"]').is(':checked')){					
					medioRespuestaHTML='Email : '+$('.reclamoCobrosNotificacionEmail').val()+' <br>';
					/*
					if(datosReclamo.mensaje==''){
						datosReclamo.mensaje= datosReclamo.mensaje+'Email : '+$('.reclamoCobrosNotificacionEmail').val();	
					}else{
						datosReclamo.mensaje= datosReclamo.mensaje+' / Email : '+$('.reclamoCobrosNotificacionEmail').val();
					}*/
					datosReclamo.checkEmail='0';
				}				
				
				/*if($('input[name="checkreclamoCobrosNotificacionSMS"]').is(':checked')){					
					medioRespuesta=medioRespuesta+'SMS : '+$('.reclamoCobrosNotificacionSMS').val()+' <br>';
					if(datosReclamo.mensaje==''){
						datosReclamo.mensaje = datosReclamo.mensaje+'SMS : '+$('.reclamoCobrosNotificacionSMS').val();	
					}else{
						datosReclamo.mensaje= datosReclamo.mensaje+' / SMS : '+$('.reclamoCobrosNotificacionSMS').val();
					}						
				}*/
				
				datosReclamo.checkDireccion='1';
				if($('input[name="checkReclamoCobrosNotificacionCarta"]').is(':checked')){					
					var dptoExistReclamo = $('.depto_casa_otro').val();
					if(dptoExistReclamo == null || dptoExistReclamo == ""){						
						$('.reclamoTecnicoDireccion').val($('.calle').val()+', '+$('.numero').val()+', '+$('.reclamoTecnicoComuna option:selected').text()+', '+$('.reclamoTecnicoCiudad option:selected').text()+', '+$('.region-promociones option:selected').text());
					}else{						
						$('.reclamoTecnicoDireccion').val($('.calle').val()+', '+$('.numero').val()+', Depto/Oficina '+$('.depto_casa_otro').val()+', '+$('.reclamoTecnicoComuna option:selected').text()+', '+$('.reclamoTecnicoCiudad  option:selected').text()+', '+$('.region-promociones option:selected').text());
					}
					
					medioRespuestaHTML=medioRespuestaHTML+'Carta Certifica : '+$('.reclamoTecnicoDireccion').val();
					
					/*					 					 
					if(datosReclamo.mensaje==''){
						datosReclamo.mensaje= datosReclamo.mensaje+'Localizacion: '+$('.reclamoTecnicoDireccion').val();
					}else{
						datosReclamo.mensaje= datosReclamo.mensaje+' /Localizacion: '+$('.reclamoTecnicoDireccion').val();
					}*/
					
					datosReclamo.region=$('.region-promociones option:selected').text();
                    datosReclamo.ciudad=$('.reclamoTecnicoCiudad option:selected').text();
                    datosReclamo.comuna=$('.reclamoTecnicoComuna option:selected').text();
                    datosReclamo.calle=$('.calle').val();
                    datosReclamo.numero=$('.numero').val();
                    datosReclamo.dpto=$('.depto_casa_otro').val();
                    datosReclamo.checkDireccion='0';
					
				}	
				
				datosReclamo.medio=medioRespuestaHTML;
				
				if($(".nombreUsuario").val()!=undefined){
					$("#imp_nombre").val($(".nombreUsuario").val());
				}
				
				$(".resumen_nombre").html($("#imp_nombre").val());
				datosReclamo.nombre=$("#imp_nombre").val();
				
				datosReclamo.mensaje= replaceAll(datosReclamo.mensaje,'|','');

				$('.resumen_medio_respuesta').html(medioRespuestaHTML);
				$('#imp_medio_respuesta').val(medioRespuestaHTML);			
			
				$('.bloqueReclamoSuperior').hide();
				$('.bloqueTipoReclamoTecnico').hide();
				$('.bloqueTipoReclamoComercial').hide();				
				$('.bloqueTecnicoUbicacion').hide();
				$('.bloqueNumeroDocumento').hide();
				$('.bloqueInferior').hide();
				$('.bloqueReclamoSuperior').hide();
				$('.bloqueResumen').show();		
		}
		
		
	});
	
	 $('.btnResumenCobroCancelar').click(function(e) {
		 
		    $('.bloqueReclamoSuperior').show();
			$('.bloqueTipoReclamoTecnico').show();
			$('.bloqueTipoReclamoComercial').show();				
			$('.bloqueTecnicoUbicacion').show();
			$('.bloqueNumeroDocumento').show();
			$('.bloqueInferior').show();
			$('.bloqueReclamoSuperior').show();
			$('.bloqueResumen').hide();				
			
			/*$(".reclamoMontoCobrosTotal").val('');
            $(".reclamoMontoCobrosTotalHidden").val('');
            $(".reclamoMontoImpugnadoTotal").val('');
            $('.solo_comercial').hide();	
            $('.solo_tecnico').hide();	
            $("#reclamoCobrosDocumentoReclamado").val('');    
            $(".reclamoMontoCobrosTotal").val(''); 
            
            $("select[id*=regionpromocionesLocalizacion]")[0].setValue('');	 
            $(".ciudadLocalizacionProblema")[0].setValue('');	
            $(".comunaLocalizacionProblema")[0].setValue('');
            $(".calleLocalizacionProblema").val('Direcci\u00F3n y n\u00FAmero');*/			
		
	 });
	 
	 $('.volverAIntentar').click(function(e){
		 $('.mensajeAlertaGrande').hide();
		 $('.mensajeConfirmacionResumen').show();
	 });
	 
	 $('.btnResumenCobroCancelar').click(function(e){
		 if($('select[name="reclamoReclamo"]').val()==relamoComercial){	
			 if($('select[name="reclamoTipoReclamo"]').val()!=relamoBoletaFactura){
				 $('.bloqueNumeroDocumento').hide();
			 }
			 $('.bloqueTipoReclamoTecnico').hide(); 
		 }else if($('select[name="reclamoReclamo"]').val()==relamoTecnico){			 
			 $('.bloqueNumeroDocumento').hide();
			 $('.bloqueTipoReclamoComercial').hide();
		 }
	 });
	 
	 
	 $('.btnConfirmarResumen').click(function(event){
			event.preventDefault();
			$('.mensajeConfirmacionResumen').hide();
			var dataString = "";
			$('.cargandoReclamo').show();
			
                datosReclamo.nombre=$(".resumen_nombre").html();
				dataString += "&movil="+datosReclamo.movil;
				dataString += "&suboperacion="+datosReclamo.suboperacion;
				dataString += "&suboperacionText="+datosReclamo.suboperacionText;
				dataString += "&motivo="+datosReclamo.motivo;
				dataString += "&motivoText="+datosReclamo.motivoText;
				dataString += "&email="+datosReclamo.email;
				dataString += "&mensaje="+datosReclamo.mensaje;
				dataString += "&nombre="+datosReclamo.nombre;
				dataString += "&antecedentes="+datosReclamo.antecedentes;
				dataString += "&medio="+datosReclamo.medio;
				dataString += "&monto="+datosReclamo.monto;
				dataString += "&documento="+datosReclamo.documento;
				dataString += "&localizacion="+datosReclamo.localizacion;
				dataString += "&region="+datosReclamo.region;
                dataString += "&ciudad="+datosReclamo.ciudad;
                dataString += "&comuna="+datosReclamo.comuna;
                dataString += "&calle="+datosReclamo.calle;
                dataString += "&numero="+datosReclamo.numero;
                dataString += "&dpto="+datosReclamo.dpto; 
                dataString += "&montoCobrado="+replaceAll(datosReclamo.montoCobrado,'.','');
                dataString += "&checkEmail="+datosReclamo.checkEmail;
                dataString += "&checkDireccion="+datosReclamo.checkDireccion;
                dataString += "&cuenta="+datosReclamo.cuenta;
                dataString += "&reclamoTipoDocumento="+datosReclamo.tipoDocumento;
                dataString += "&random="+Math.random()*99999;
			
			//Ajax para ingresa reclamo
			   $.ajax({
					type: 'POST',
					url: urlIgresarReclamo,
					data: dataString,
					async: true, 
					dataType: 'json',
					cache : false,
					success: function (resp){
				       $('.cargandoReclamo').hide();
					   if(resp.estado == 'Ok'){
						   $('.bloqueConfirmacionExito').show();						   
						   //$('.mensajeConfirmacionExito').html(replaceAll($('.mensajeConfirmacionExito').html(),"#NUMERORECLAMO#",resp.respuesta));
						   $('#numeroReclamoOk').html(resp.respuesta);						   
						   $('#imp_cod_reclamo').val(resp.respuesta);
						   reclamoGenerado='1';						  
						 }else{
						   $('.mensajeAlertaGrande').show();
					   }
					}
				});			
		});
	

	$('select[name="reclamoReclamo"]').change(function(e) {
        $('.erroMsjTipoReclamo').hide();
        $('.bloqueNumeroDocumento').hide();
        $('.globoError').fadeOut();
    });	
	
	$('select[name="reclamoReclamo"]').keyup(function(e) {
        $('.erroMsjTipoReclamo').hide();
        $('.bloqueNumeroDocumento').hide();
        $('.globoError').fadeOut();
    });	
	
	var random = Math.round(Math.random()*10000);
	
	$('.reclamoCobrosAntecedentes').keypress(function() {
		$('.globoError').fadeOut();	
	});	
	
	$(".reclamoCobrosAntecedentes").blur(function(){
		$('.globoError').hide();
	});
	
	$('select, input:radio').change(function() {					
		$('.globoError').fadeOut();					 
	});
	
	$("input[name=reclamoTecnicoDireccion], input[name=reclamoCobrosNotificacionDireccion], input[name=reclamoRecargasNotificacionDireccion]").focus(function(){
		$(this).closest('.subFila').find('.blurDireccion:first').hide();
	});
	$("input[name=reclamoTecnicoDireccion], input[name=reclamoCobrosNotificacionDireccion], input[name=reclamoRecargasNotificacionDireccion]").blur(function(){
		if ($(this).val() == "" ){
			$(this).closest('.subFila').find('.blurDireccion:first').show();
		}
	});
	$(".blurDireccion").click(function(){
		$(this).hide();
		$(this).hide().prev().find('input:first').focus();
	});
		
	
	$('select[name="reclamoTipoReclamo"]').change(function() {
		$('.globoError').fadeOut();
		var element = $(this);
		var value = (element.val() != "") ? element.val() : false;
		reclamos.tipoReclamo();		
		if(value){
			if(value == relamoBoletaFactura){				
				$('.bloqueNumeroDocumento').show();				
			}else{				
				$('.bloqueNumeroDocumento').hide();				
		    }
			
			element.closest('.clearfix').next().next().hide();
			element.closest('.clearfix').next().show();			
			
		}
	});	
	
	$('select[name="reclamoTipoReclamoTecnico"]').change(function() {
		$('.erroMsjTipoReclamoTec').hide();	
		$('.globoError').fadeOut();
	});
	
	$('input[name="reclamoTecnicoNotificacion"]').change(function() {
		$('.globoError').fadeOut();
		var element = $(this);
		var value = parseInt(element.val());
		reclamos.tecnicoNotificacion();
		if(value == 1){
			valueTecNot = value;
			element.closest('.altoFijo').next().show();			
		}else{
			if(value == 2){
				valueTecNot = value;
				element.closest('.altoFijo').next().show();				
			}else{
				valueTecNot = "Carta Certificada";
			}
		}
	});

	
	$('.btnResumenTecnicoCancelar').click(function(event){
		event.preventDefault();
		reclamos.cancelarResumenTecnico();
	});
	
	/*$('.btnReclamoCobros').click(function(event){
		event.preventDefault();
		reclamos.resumenCobro();
	});*/
	
	$('.btnResumenCobroCancelar').click(function(event){
		event.preventDefault();
		reclamos.cancelarResumenCobro();
	});
	
	$('.btnResumenRecargaCancelar').click(function(event){
		event.preventDefault();
		reclamos.cancelarResumenRecarga();
	});
	
	

	$('.modificarDirReclamo').click(function(e) {
		e.preventDefault();
		if ($(this).hasClass('abierto')){
			$(this).removeClass('abierto');
			$(this).parent().removeClass('cancelar');
			$(this).parent().parent().removeClass('sinPadding');
			$('#modificarDirCaja').hide();
			$(this).html('Modificar');
			//$('.globoErrorBloqueo').hide();		
			return false;
		}	
		//$('#globoErrorBloqueo').hide();		
		$('#modificarDirCaja').show();
		$(this).addClass('abierto');
		$(this).parent().addClass('cancelar');
		$(this).parent().parent().addClass('sinPadding');
		$(this).html('Cancelar');
	});

	

	$('.btnGuardarDirReclamo').click(function(e){
		
		if ($(".modificarDireccion").valid()){
			
			$(".regionSelect").hide();
			$(".regionText").html($('.region-promociones option:selected').text());
			$(".regionText").show();
			
			$(".ciudadSelect").hide();
			$(".ciudadText").html($('.reclamoTecnicoCiudad option:selected').text());
			$(".ciudadText").show();
			
			$(".comunaSelect").hide();
			$(".comunaText").html($('.reclamoTecnicoComuna option:selected').text());
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
	    	//$(this).parents('.fila').next('.mensaje').show();
			
			$('.modificarDirBloqueo').removeClass('abierto');
			$('.modificarDirBloqueo').parent().removeClass('cancelar');
			$('.modificarDirBloqueo').parent().parent().removeClass('sinPadding');
			$('#modificarDirCaja').hide();
			$('.modificarDirBloqueo').html('Modificar');
			$('.btnGuardarDirbloqueo').show();
			$('.btnGuardarDirbloqueo').parents('.fila').next('.mensaje').hide();
			
			var dptoExist = $('.depto_casa_otro').val();
			
			
			if(dptoExist == null || dptoExist == ""){
				
				$('.reclamoTecnicoDireccion').val($('.calle').val()+', '+$('.numero').val()+', '+$('.reclamoTecnicoComuna option:selected').text()+', '+$('.reclamoTecnicoCiudad option:selected').text()+', '+$('.region-promociones option:selected').text());
			}else{
				
				$('.reclamoTecnicoDireccion').val($('.calle').val()+', '+$('.numero').val()+', Depto/Oficina '+$('.depto_casa_otro').val()+', '+$('.reclamoTecnicoComuna option:selected').text()+', '+$('.reclamoTecnicoCiudad  option:selected').text()+', '+$('.region-promociones option:selected').text());
			}
			
		}				
	});

	$('.btnConfirmarDirReclamo').click(function(e){
		
		$('.modificarDirBloqueo').removeClass('abierto');
		$('.modificarDirBloqueo').parent().removeClass('cancelar');
		$('.modificarDirBloqueo').parent().parent().removeClass('sinPadding');
		$('#modificarDirCaja').hide();
		$('.modificarDirBloqueo').html('Modificar');
		$('.btnGuardarDirbloqueo').show();
		$('.btnGuardarDirbloqueo').parents('.fila').next('.mensaje').hide();
		
		var dptoExist = $('.depto_casa_otro').val();
		
		
		if(dptoExist == null || dptoExist == ""){
			
			$('.reclamoTecnicoDireccion').val($('.calle').val()+', '+$('.numero').val()+', '+$('.reclamoTecnicoComuna option:selected').text()+', '+$('.reclamoTecnicoCiudad option:selected').text()+', '+$('.region-promociones option:selected').text());
		}else{
			
			$('.reclamoTecnicoDireccion').val($('.calle').val()+', '+$('.numero').val()+', Depto/Oficina '+$('.depto_casa_otro').val()+', '+$('.reclamoTecnicoComuna option:selected').text()+', '+$('.reclamoTecnicoCiudad  option:selected').text()+', '+$('.region-promociones option:selected').text());
		}

	});
	
	$('.modificarSMSBloqueo').click(function(e) {
		e.preventDefault();
		$('.nuevoSMSBloqueo').removeAttr('readonly');
		if ($(this).hasClass('abierto')){
			$(this).removeClass('abierto');
			$(this).parent().removeClass('cancelar');
			$(this).parent().parent().removeClass('sinPadding');
			$('#modificarSMSCaja').hide();
			$(this).html('Modificar');
			$('.btnGuardarSMSbloqueo').show();
			$('.btnGuardarSMSbloqueo').parents('.fila').next('.mensaje').hide();
			//$('#globoErrorBloqueo').hide();
			$('.btnBloqueoPaso1, .btnCancelarPaso1').removeClass('desactivado');
			return false
		}
		$('.btnBloqueoPaso1, .btnCancelarPaso1').addClass('desactivado');
		//$('#globoErrorBloqueo').hide();
		$('#modificarSMSCaja').show();
		$('.nuevoSMSBloqueo').val('');
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
	
	
	$('.btnGuardarSMSReclamo').click(function(e){
		//if ($(".modificarSMS").valid()){
		//$('.erroMsjSMS').hide();
		if ($(".nuevoSMSBloqueo").val()!="" && $(".nuevoSMSBloqueo").val().length == 8){
			$('.reclamoCobrosNotificacionSMS').val($('.nuevoSMSBloqueo').val()); 
			$(this).parents('.fila').next('.mensaje').show();
        	$('.nuevoSMSBloqueo').attr('readonly','readonly');
        	$('.modificarSMSBloqueo').removeClass('abierto');
			$('.modificarSMSBloqueo').parent().removeClass('cancelar');
			$('.modificarSMSBloqueo').parent().parent().removeClass('sinPadding');
			$('.modificarSMSBloqueo').html('Modificar');
			$('#modificarSMSCaja').hide();
			$('.btnGuardarSMSbloqueo').show();
			$('.btnGuardarSMSbloqueo').parents('.fila').next('.mensaje').hide();					
		}else{
			//$('#globoError').find('td:first').html('hola');			
			$('#globoError').find('td:first').html("Ingrese el m&oacute;vil");
			mostrarError($('.nuevoSMSBloqueo'),'0');
			
		}
    });	
	
 
	// BLOQUE DETALLE RECLAMO.
    
	   $('#reclamoVerifNroNoc').click(function(e) {	  
		   
		 if($('#reclamoCobrosDocumentoReclamado').val()!=''){
		   $('.cargandoBuscarDocumento').show();  
	        var dataString = "&documentoBuscado="+ $('#reclamoCobrosDocumentoReclamado').val()+"&random="+Math.random()*99999;
			$.ajax( {
				type : 'POST',
				url : urlBuscarMontoDocumento,	
				data: dataString,
				async: true, 
				dataType: 'json',
				cache : false,		
				success : function(data) {
					if(data.estado == 'Ok'){	
					   $('.reclamoMontoCobrosTotal').val(data.respuesta.montoTotal); 
					   $('#reclamoMontoCobrosTotalHidden').val(data.respuesta.montoTelefoniaMovil); 
					   $('.reclamoMontoCobrosTotal ').val(data.respuesta.montoTotal); 
					   
					   $('#reclamoMontoCuenta').val(data.respuesta.numeroCuenta);					   
					   $('#reclamoTipoDocumento').val(data.respuesta.tipo);					   
					   
                       documentoBuscadoBackup = $('#reclamoCobrosDocumentoReclamado').val();
					}else{
						 $('#globoError').find('td:first').html("El documento buscado no existe!");   						
					     mostrarError($('#reclamoVerifNroNoc'),'1');	
					     $('.reclamoMontoCobrosTotal').val('');
					     $('#reclamoMontoCobrosTotalHidden').val(''); 
						 $('.reclamoMontoCobrosTotal ').val(''); 
					     documentoBuscadoBackup='';
					}					
				    $('.cargandoBuscarDocumento').hide();
			  }
	       });
		 }else{
			 $('#globoError').find('td:first').html("Escriba el documento a verificar.!");   						
		     mostrarError($('#reclamoVerifNroNoc'),'1');	
		 }	
	  }); 
	   
      /*$('.reclamoReclamo').change(function(event){
         alert($(this).val());
	     event.preventDefault();
	     $.ajax({
			type: 'POST',
			url: urlObtenerSubMotivos,
			data: 'motivo='+$(this).val(),
			async: true, 
			dataType: 'json',
			cache : false,
			success: function (submotivos){
		   		$(".reclamoTipoReclamoDefault").empty();
		   		$(".reclamoTipoReclamoDefault").append('<option value="">Seleccionar</option>');
		   		for (var i = 0; i < submotivos.length; i++){
		   			$('.reclamoTipoReclamoDefault').append('<option value='+ submotivos[i].codigo +'>'+ submotivos[i].descripcion +'</option>');
		   		}
			}
		  });			
	   });*/	   
	   
	   $('.contenido1').click(function(e){
		   if(reclamoGenerado=='1'){
			   loadTableHistorialReclamos();
			   $('.btnResumenCobroCancelar').click();
			   $('.borrarFormulario').click();
		   }
	   });  

		
});


//BLOQUE DETALLE RECLAMO.	
function verDetalleReclamo(numeroReclamo){
	
	
	$('#detalle_fecha_ingreso').html("");
	$('#detalle_numero_ticket').html("");	
	$('#detalle_rut').html("");	
	$('#detalle_numero_reclamo').html("");	
	$('#detalle_reclamo').html("");	
	$('#detalle_tipo_reclamo').html("");	
	$('#detalle_documentos_adjuntos').html("");	
	$("#detalle_solo_numero_docuemnto").hide();
	$(".detalle_solo_respuesta").hide();
	$("#detalle_solo_direccion_reclamada").hide();	
	$('#detalle_antecedentes').html("");	
	$('#detalle_notificacion').html("");		
	$('#detalle_estado').html("");	
	$('#detalle_fecha_respuesta').html("");	
	$('#detalle_documentos_respuesta').html("");
 
     var parametros = {"codReclamo":numeroReclamo};
		$.ajax( {
			type : 'POST',
			url : urlDetalleReclamo,	
			data:parametros,
			dataType:'json',		
			success : function(data) {
				if(data.estado == 'Ok'){			

					//$('#detalle_fecha_ingreso').html(data.respuesta.fechaIngreso);
					$('#detalle_fecha_ingreso').html(data.respuesta.fechaIngresoText);
					$('#detalle_numero_ticket').html(numeroReclamo);	
					//$('#detalle_nombre').html(data.respuesta.nombre);	
					$('#detalle_rut').html(data.respuesta.rut);	
					$('#detalle_numero_reclamo').html(data.respuesta.numeroReclamado);
					$('#detalle_producto').html("Mi Entel M&oacute;vil");
                    //$('#detalle_compania').html("Entel PSC");
					$('#detalle_reclamo').html(data.respuesta.motivo);	
					$('#detalle_tipo_reclamo').html(data.respuesta.tipoDereclamo);	
					
					$('#detalle_documentos_adjuntos').html(data.respuesta.documentosAdjuntos);	
					
					if(data.respuesta.numeroDeDocumento!=' ' && data.respuesta.numeroDeDocumento!=''){
						$('#detalle_numero_documento').html(data.respuesta.numeroDeDocumento);
						$('#detalle_monto_documento').html(data.respuesta.monto);						
						$(".detalle_solo_numero_docuemnto").show();
					}
					
					if(data.respuesta.direccionReclamada!=' - ' && data.respuesta.direccionReclamada!='' && data.respuesta.direccionReclamada!=' '){
						$("#detalle_solo_direccion_reclamada").show();
						$("#detalle_direccion_reclamada").html(data.respuesta.direccionReclamada);
						
					}					
					
					
					$('#detalle_antecedentes').html(data.respuesta.antecedentes);
					
					$('#detalle_notificacion').html(data.respuesta.notificacion);		
					$('#detalle_estado').html(data.respuesta.estado);	
					$('#detalle_fecha_respuesta').html(data.respuesta.fechaRespuestaText);	
					$('#detalle_documentos_respuesta').html(data.respuesta.respuesta);
					//$('.btnDetalleReclamos').click();
					$('.thickbox').click();
					
					
				   /*if ($.browser.msie && parseInt($.browser.version) <= 6) {
				       										
							$('.enlaceDetalleReclamo').trigger('click');
							
						}else{
							//$('.thickbox').click();	
							
				   }*/
				}
		  }
    }); 
} 

//FIN BLOQUE DETALLE RECLAMO.	



var reclamos = {
	reclamo : function(){
		$('.bloqueTecnico').hide();
		$('.bloqueNumeroDocumento').hide();
		$('.bloqueTipoReclamo').hide();
	},
	tipoReclamo : function(){
		//$('.bloqueCobrosBoleta').show();
		$('.bloqueNumeroDocumento').hide();
		$('.bloqueRecargasSaldos').hide();
	},
	tecnicoNotificacion : function(){
		$('input[name="reclamoTecnicoNotificacionEmail"]').val("");
		$('input[name="reclamoTecnicoNotificacionSMS"]').val("");
		$('.notificacionEmail').hide();
		$('.notificacionSMS').hide();
		$('.bloqueRecargasNotificacionCarta').hide();
	},
	recargasNotificacion : function(){
		$('input[name="reclamoRecargasNotificacionEmail"]').val("");
		$('input[name="reclamoRecargasNotificacionSMS"]').val("");
		$('select[name="reclamoRecargasNotificacionRegion"]').get(0).setValue("");
		$('select[name="reclamoRecargasNotificacionCiudad"]').get(0).setValue("");
		$('select[name="reclamoRecargasNotificacionComuna"]').get(0).setValue("");
		$('input[name="reclamoRecargasNotificacionDireccion"]').val("");
		$('.notificacionEmail').hide();
		$('.notificacionSMS').hide();
		$('.bloqueRecargasNotificacionCarta').hide();
	},	
	resumenCobro : function(){
		//reclamoCobrosNotificacionDireccion,
		var documento = $('input[name="reclamoCobrosDocumento"]').val();
		var monto = $('input[name="reclamoMontoCobrosTotal"]').val();
		var antecedentes = $('textarea[name="reclamoCobrosAntecedentes"]').val();
		var region = $('select[name="reclamoCobrosNotificacionRegion"] option:selected').text()+', ';
		var ciudad = $('select[name="reclamoCobrosNotificacionCiudad"] option:selected').text()+', ';
		var comuna = $('select[name="reclamoCobrosNotificacionComuna"] option:selected').text();
		$('.bloqueReclamoSuperior').hide();
		$('.bloqueTipoReclamo').hide();
		//$('.bloqueCobrosBoleta').hide();
		$('.bloqueRecargasSaldos').hide();
		$('.bloqueTecnico').hide();
		$('.bloqueResumenReclamoRecarga').hide();
		$('.bloqueNumeroDocumento').hide();
		$('.bloqueResumenReclamoTecnico').hide();
		$('.textoCobroNumero').empty().text(documento);
		$('.textoCobroMonto').empty().text(monto);
		$('.textoCobroAntecedentes').empty().text(antecedentes);
		if(valueCobNot == 1){
			$('.textoCobroNotificacion').empty().text($('input[name="reclamoCobrosNotificacionEmail"]').val());
		}else{
			if(valueCobNot == 2){
				$('.textoCobroNotificacion').empty().text($('input[name="reclamoCobrosNotificacionSMS"]').val());
			}else{
				if(valueCobNot == 3){
					$('.textoCobroNotificacion').empty().text("Carta Certificada");
					$('.textoCobroLocalizacion').empty().text(region+ciudad+comuna);
					$('.filaCobroLocalizacion').show();
				}
			}
		}
		$('.bloqueResumenReclamoCobros').show();
	},
	cancelarResumenCobro : function(){
		$('.bloqueReclamoSuperior').show();
		$('.bloqueTipoReclamo').show();
		//$('.bloqueCobrosBoleta').show();
		$('.bloqueRecargasSaldos').hide();
		$('.bloqueTecnico').hide();
		$('.bloqueResumenReclamoCobros').hide();
		$('.bloqueResumenReclamoRecarga').hide();
		$('.bloqueResumenReclamoTecnico').hide();
		//$('.filaCobroLocalizacion').empty().hide();
	},
	resumenRecarga : function(){
		//reclamoRecargasNotificacionDireccion,
		var antecedentes = $('textarea[name="reclamoRecargasAntecedentes"]').val();
		var region = $('select[name="reclamoRecargasNotificacionRegion"] option:selected').text()+', ';
		var ciudad = $('select[name="reclamoRecargasNotificacionCiudad"] option:selected').text()+', ';
		var comuna = $('select[name="reclamoRecargasNotificacionComuna"] option:selected').text();
		$('.bloqueReclamoSuperior').hide();
		$('.bloqueTipoReclamo').hide();
		//$('.bloqueCobrosBoleta').hide();
		$('.bloqueRecargasSaldos').hide();
		$('.bloqueTecnico').hide();
		$('.bloqueResumenReclamoCobros').hide();
		$('.bloqueNumeroDocumento').hide();
		$('.bloqueResumenReclamoTecnico').hide();
		$('.textoRecargaAntecedentes').empty().text(antecedentes);
		if(valueRecNot == 1){
			$('.textoRecargaNotificacion').empty().text($('input[name="reclamoRecargasNotificacionEmail"]').val());
		}else{
			if(valueRecNot == 2){
				$('.textoRecargaNotificacion').empty().text($('input[name="reclamoRecargasNotificacionSMS"]').val());
			}else{
				if(valueRecNot == 3){
					$('.textoRecargaNotificacion').empty().text("Carta Certificada");
					$('.textoRecargaLocalizacion').empty().text(region+ciudad+comuna);
					$('.filaRecargaLocalizacion').show();
				}
			}
		}
		$('.bloqueResumenReclamoRecarga').show();
	},
	cancelarResumenRecarga : function(){
		$('.bloqueReclamoSuperior').show();
		$('.bloqueTipoReclamo').show();
		//$('.bloqueCobrosBoleta').hide();
		$('.bloqueRecargasSaldos').show();
		$('.bloqueTecnico').hide();
		$('.bloqueResumenReclamoCobros').hide();
		$('.bloqueResumenReclamoRecarga').hide();
		$('.bloqueResumenReclamoTecnico').hide();
		$('.filaRecargaLocalizacion').empty().hide();
	},
	resumenTecnico : function(){
		//reclamoTecnicoDireccion, 
		var region = $('select[name="reclamoTecnicoRegion"] option:selected').text()+', ';
		var ciudad = $('select[name="reclamoTecnicoCiudad"] option:selected').text()+', ';
		var comuna = $('select[name="reclamoTecnicoComuna"] option:selected').text();
		var antecedentes = $('textarea[name="reclamoTecnicoAntecedentes"]').val();
		$('.bloqueReclamoSuperior').hide();
		$('.bloqueTipoReclamo').hide();
		//$('.bloqueCobrosBoleta').hide();
		$('.bloqueRecargasSaldos').hide();
		$('.bloqueTecnico').hide();
		$('.bloqueNumeroDocumento').hide();
		$('.bloqueResumenReclamoCobros').hide();
		$('.bloqueResumenReclamoRecarga').hide();		
		$('.textoLocalizacionTecnico').empty().text(region+ciudad+comuna);
		$('.textoAntecedentesTecnico').empty().text(antecedentes);
		if(valueTecNot == 1){
			valueTecNot = $('input[name="reclamoTecnicoNotificacionEmail"]').val();
		}else{
			if(valueTecNot == 2){
				valueTecNot = $('input[name="reclamoTecnicoNotificacionSMS"]').val();
			}
		}
		$('.textoNotificacionTecnico').empty().text(valueTecNot);
		$('.bloqueResumenReclamoTecnico').show();
	},
	cancelarResumenTecnico : function(){
		$('.bloqueReclamoSuperior').show();
		$('.bloqueTipoReclamo').hide();
		//$('.bloqueCobrosBoleta').hide();
		//$('.bloqueRecargasSaldos').hide();
		$('.bloqueTecnico').show();
		$('.bloqueResumenReclamoCobros').hide();
		$('.bloqueResumenReclamoRecarga').hide();
		$('.bloqueResumenReclamoTecnico').hide();
	}
}

function replaceAll( text, busca, reemplaza ){
	if(typeof text != 'undefined'){
	  while (text.toString().indexOf(busca) != -1)
	      text = text.toString().replace(busca,reemplaza);
	  return text;
	}
}

function limits_textarea(obj, limit){
    var text = $(obj).val();
    var length = text.length;
    if(length > limit){
       $(obj).val(text.substr(0,limit));
    }
}


function mostrarError(el,tipo) {		
	var $globo = $('#globoError');
	var input = $(el).parents('div:first');
		
	if ($globo.is(':hidden')) {
		$globo.fadeIn(200, function() {
			//$(el).focus();							
		});
	}
	if(tipo=='0'){
			$globo.css({
				'top': input.offset().top + parseInt(input.height())/2 - parseInt($globo.css('height'))/2,
				'left': input.offset().left +300
				//'left': input.offset().left + parseInt(input.width()) + 5 
			});
			
			$(window).resize(function() {
				$globo.css({
					'top': input.offset().top + parseInt(input.height())/2 - parseInt($globo.css('height'))/2,
					'left': input.offset().left +300
					//'left': input.offset().left + parseInt(input.width()) + 5 
				});
			});	
	    }else if(tipo=='2'){
			$globo.css({
				'top': input.offset().top + parseInt(input.height())/2 - parseInt($globo.css('height'))/2,
				'left': input.offset().left+200
				//'left': input.offset().left + parseInt(input.width()) + 5 
			});
			
			$(window).resize(function() {
				$globo.css({
					'top': input.offset().top + parseInt(input.height())/2 - parseInt($globo.css('height'))/2,
					'left': input.offset().left+200
					//'left': input.offset().left + parseInt(input.width()) + 5 
				});
			});	
	    }	
	else{
	    	$globo.css({
				'top': input.offset().top + parseInt(input.height())/2 - parseInt($globo.css('height'))/2,
				 'left': input.offset().left +300
				//'left': input.offset().left + parseInt(input.width()) + 5 
			});
			
			$(window).resize(function() {
				$globo.css({
					'top': input.offset().top + parseInt(input.height())/2 - parseInt($globo.css('height'))/2,
					'left': input.offset().left +300
					//'left': input.offset().left + parseInt(input.width()) + 5 
				});
			});	
	}
	
	return false;
}

