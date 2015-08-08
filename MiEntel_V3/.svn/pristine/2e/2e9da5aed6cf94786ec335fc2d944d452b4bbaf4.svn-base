var datosBloqueo = new Array();
var equipoBloqueo;
var datosEquipo = new Array();

$(document).ready(function(e) {
	
	$('body').append('<div class="fancybox-overlay-ie-serviciotecnico"></div>');		
	
	$(".inputNumerico").keypress(function(evt){
		var key = evt.keyCode ? evt.keyCode : evt.which;
		return (key <= 27 || (key >= 48 && key <= 57)); 
	});
	
	$('.btnPreingreso_0').click(function(e) {
		var ambiente = $('#preIngresoServicioTecnicoMercado').val();		
		//se llama servicio para listado de equipos
		$('.st_paso0').hide();		
		if(ambiente==1){ //CASO SS/CC
			$('.paso_ccss').show();
			var respuesta=$('#flagObtenerEquipoSoporte').val();//VALIDA SI HAY LISTADO DE EQUIPOS			
			if(respuesta=="true"){
				//listado lleno
				$('.paso1CasoA').show();
				$('.paso1CasoB').hide();				
			}else{
				//listado vacio
				$('.paso1CasoA').hide();
				$('.paso1CasoB').show();
			}
		}else{	//CASO PREPAGO
			$('.paso_prepago').show();
		}		
    });
	
	var equipoIngresar;
	var antiguedad;
	
	$("input[name=equipo-ingreso]").click(function(){
		//validar antiguedad de equipo
		$('.cajaInrgesoImei').hide();
		equipoIngresar = $(this).attr('id'); //id equipo
		antiguedad = parseInt($(this).attr('rel')); //meses de antiguedad
		
		//se guardan los datos del equipo
		datosEquipo['id']=$(this).attr('id');
		datosEquipo['antiguedad']=antiguedad;
		datosEquipo['fecha']=$(this).parents('.fila').children('.fecha').html();
		datosEquipo['imei']=$(this).parents('.fila').children('.imei').html();
		datosEquipo['marca']=$(this).parents('.fila').children('.marca').html();
		
		//se precargan los datos para el paso 2 (FALLAS)
		$('.equipoSeleccionado .fila .marca').html(datosEquipo['marca']);
		$('.equipoSeleccionado .fila .imei').html(datosEquipo['imei']);
		$('.equipoSeleccionado .fila .fecha').html(datosEquipo['fecha']);
		
		validarAntiguedad(equipoIngresar,antiguedad);
	});
	
	
	$('.btnCancelarSeleccionEquipo').click(function(e) {
		e.preventDefault();
		$("input[name=equipo-ingreso]").removeAttr('checked');
		$('.fancybox-overlay-ie-serviciotecnico, #antiguedadMayor14').hide();
		$('.cajaInrgesoImei').show();
    });
	
	$('.btnCancelar_1').click(function(e) {
		e.preventDefault();
		var ancho=$(window).width();
		var alto=$(window).height();
		ancho=(ancho-$('#cancelarProcesoIngreso').width())/2;
		ancho=parseInt(ancho);
		alto=(alto-$('#cancelarProcesoIngreso').height())/2;
		alto=parseInt(alto);
		$('#cancelarProcesoIngreso').css('left',ancho+'px').css('top',alto+'px');
		$('.fancybox-overlay-ie-serviciotecnico, #cancelarProcesoIngreso').show();
		$('html, body').animate({ scrollTop: 0 }, 0);
    });
	
	$('.btnContinuarPreIngreso').click(function(e) {
		e.preventDefault();
		$('.fancybox-overlay-ie-serviciotecnico, #cancelarProcesoIngreso').hide();
    });
	
	$('.imeiEquipoIngreso').keyup(function(e) {
		$('.cajaImei .globoError').hide();
		$('.btnPreingreso_1').removeClass('btnAzul').addClass('btnDesactivado');
		if($(this).val().length==15){
			$('.imeiEquipoIngreso').attr("readonly",true);
			validarImei($(this).val());
		}
    });
	
	$('.btnCancelarProceso').click(function(e) {
        location.href = $(".btnCancelarProceso").attr('rel');//se debe direccionar al home de Entel
    });
	
	$('.autoTooltip').click(function(e) {
		if($(this).hasClass('imeiEquipoIngresoToolTip')){
			$('.tooltip1 .texto').css('height','100px');
		}else{
			$('.tooltip1 .texto').css('height','auto');
		}        
    });
	
	
	$('.btnPreingreso_1').click(function(e) {
		if($(this).hasClass('btnAzul')){
			$('.st_paso1').hide();
			$('.st_paso2').show();			
		}		
    });
	
	$("input[name=accion-antiguedad]").click(function(){
		$('.btnContinuarAccionAntiguedad').removeClass('btnDesactivado').addClass('btnAzul');//se habilita el boton continuar
		
	});
	
	$('.btnContinuarAccionAntiguedad').click(function(e) {
		e.preventDefault();
		if($(this).hasClass('btnAzul')){
			var accionAntiguedad = $("input[name=accion-antiguedad]:checked").attr('id'); //id acción			
			if(accionAntiguedad=="accion_1"){//continuar proceso
				$('.fancybox-overlay-ie-serviciotecnico, #antiguedadMayor14').hide();
				$('.st_paso1').hide();
				$('.st_paso2').show();
			}else if(accionAntiguedad=="accion_2"){//cambio de equipo
				location.href=$(".btnContinuarAccionAntiguedad").attr('rel');//se direcciona a renovación
			}
		}
    });
	
	$("input[name=opcion-falla]").click(function(){
		//validar antiguedad de equipo
		$("input[name=opcion-falla]").attr('disabled','');
		
		//activacion botón continuar
		if($("input[name=opcion-falla]:checked").length>=1){
			$('.btnPreingreso_2').removeClass('btnDesactivado').addClass('btnAzul');	
		}else{
			$('.btnPreingreso_2').removeClass('btnAzul').addClass('btnDesactivado');	
		}
		
		//validación máximo 3
		/*
		if($("input[name=opcion-falla]:checked").length==3){
			$("input[name=opcion-falla]:not(:checked)").attr('disabled','disabled');
		}
		*/
	});
	
	$('.detalllesComentariosTexto').keyup(function() {
		$('.detallesComentarios .globoError').hide();
		limits_textarea($(this), 200);
		$('.caracteresDisponibles span').html(200-$(this).val().length);		
    });
	
	$('.emailIngreso').keyup(function(e) {
        $('.cajaEmail .globoError').hide();
    });
	
	var emailRegEx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
	
	$('.btnPreingreso_2').click(function(e) {		
		e.preventDefault();		
		$('.globoError').hide();
		if($(this).hasClass('btnAzul')){
			if($("input[id=falla-otras]").attr('checked')){// opcion OTRAS escogida
				if($('.detalllesComentariosTexto').val().length<1){// valdiacion Mensaje/Comentario
					$('.detallesComentarios .globoError').show();
					return false;
				}
			}			
			if (emailRegEx.test($('.emailIngreso').val())){	
				
				//Comentario
				$("#imprimir_comentarios").val($('.detalllesComentariosTexto').val());
				$('.resumenIngreso-comentarios').html($('.detalllesComentariosTexto').val());
				//Accesorios
				accesorios();				
				$("#imprimir_accingreso").val($(".accesoriosIngreso").html());
				//Fallas
				fallas();
				$("#imprimir_fallas").val($(".resumenIngreso-fallas").html());
				//Marca
				$("#imprimir_marca").val($(".equipoSeleccionado .fila").find('.marca').text());
				$(".resumenIngreso-marca").html($(".equipoSeleccionado .fila").find('.marca').text());
				//Imei
				$("#imprimir_imei").val($(".equipoSeleccionado .fila").find('.imei').text());
				$(".resumenIngreso-imei").html($(".equipoSeleccionado .fila").find('.imei').text());		
				
				$('.st_paso2').hide();
				$('.st_paso3').show();
			}else{
				$('.cajaEmail .globoError').show();
			}					
		}
    });
	
	$('.emailIngreso').focusout(function(e) {        
		if (!emailRegEx.test($(this).val())){
			$('.cajaEmail .globoError').show();
		}
    });
	
	$('.btnPreingreso_3').click(function(e) {		
		if($(this).hasClass('btnAzul')){
			registrarPreIngresoOT();			
		}
    });
	
	$('.btnVerSucursalesIngreso').click(function(e) {
		e.preventDefault();
		var htmlSucursales="";
        //función buscar sucursales	
		
		//carga html
		htmlSucursales += "<p><strong>Sucursal 4</strong></p>";
		htmlSucursales += "<p>Horario de atenci&oacute;n: Lunes a viernes 10:00 a 18:00</p>";
		
        htmlSucursales += "<p><strong>Sucursal 5</strong></p>";
        htmlSucursales += "<p>Horario de atenci&oacute;n: Lunes a viernes 10:00 a 18:00</p>";
		
        htmlSucursales += "<p><strong>Sucursal 6</strong></p>";
        htmlSucursales += "<p>Horario de atenci&oacute;n: Lunes a viernes 10:00 a 18:00</p>";
				
		$('.resultadoSucursalesIngreso').html(htmlSucursales);
    });
		
	
});

function validarImei(valor){	
	cargarIMEI(valor);	
}

function validarAntiguedad(idequipoIngresar,antiguedad){
	if(antiguedad<14){//menor a 14 meses
		$('.btnPreingreso_1').removeClass('btnDesactivado').addClass('btnAzul');	
	}else if(antiguedad>=14){ // caso RENOVAR		
		//mostrar lightbox
		var ancho=$(window).width();
		var alto=$(window).height();
		ancho=(ancho-$('#antiguedadMayor14').width())/2;
		ancho=parseInt(ancho);
		alto=(alto-$('#antiguedadMayor14').height())/2;
		alto=parseInt(alto);
		$('#antiguedadMayor14').css('left',ancho+'px').css('top',alto+'px');
		$('.fancybox-overlay-ie-serviciotecnico, #antiguedadMayor14').show();
		$('html, body').animate({ scrollTop: 0 }, 0);	
	}
}

function accesorios(){
	$(".accesoriosIngreso").html("");
	$("input[name=opcion-falla]:checked").each(function(){
		var valor = $(this).val();
	    var id = valor.split(',');	    
	    for(i=2;i<=id.length-1;i++){	    	
	    	var cadena = $(".accesoriosIngreso").html();
	    	if(cadena.indexOf(id[i])<0){	    	
		    	if($(".accesoriosIngreso").html()==""){
		    		if(id[i]!="")
		    		$(".accesoriosIngreso").append(id[i]);
		    	}else{
		    		if(id[i]!="")
		    		$(".accesoriosIngreso").append("; "+id[i]);
		    	}
	    	}
	    }
	});
}

function fallas(){
	$(".resumenIngreso-fallas").html("");
	$("input[name=opcion-falla]:checked").each(function(){
		var valor = $(this).val();
	    var id = valor.split(',');	    
	    if(id[1]!=null){
	    	if($(".resumenIngreso-fallas").html()=="")
	    		$(".resumenIngreso-fallas").html(id[1]);
	    	else
	    		$(".resumenIngreso-fallas").append("; "+id[1]);
    	}
	});
}

function codigoFallas(){	
	var arrayFallas = new Array();
	$("input[name=opcion-falla]:checked").each(function(){
		var valor = $(this).val();
	    var id = valor.split(',');
	    arrayFallas.push(id[0]);
	});
	return arrayFallas;
}

function limits_textarea(obj, limit){
    var text = $(obj).val();
    var length = text.length;
    if(length > limit){
       $(obj).val(text.substr(0,limit));
    }
}







