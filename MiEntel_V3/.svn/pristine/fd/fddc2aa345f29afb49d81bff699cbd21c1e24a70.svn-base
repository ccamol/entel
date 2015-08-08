$(document).ready(function() {

	//Para mostrar informacion exclusiva al titular de la cuenta
	var opcionSelect = readCookie("opcion");
	if (opcionSelect) {
		var tipo = opcionSelect.split('_')[0];
		var nivelAcceso = opcionSelect.split('_')[1];
		if (((tipo == "ss") || (tipo == "cc")) && (nivelAcceso == "titular")) {
			$(".email_factura").show();
			$(".direccion_factura").show();
		}
		else {
			$(".email-factura").hide();
			$(".direccion_factura").hide();
		}
	}

/*	$(".sel-estas-consultando").change(function(){
		var url = window.location.href;
		url = url.match(/([^_])+$/)[0];
		window.location.href =  $(this).val()+"_ok.html"; //$(this).val()+"_"+url;
	});
	
	var url = window.location.href;
	url = url.split('/')[url.split('/').length-1].split('_');
	if(url.length > 1) {
		url = url[0];
		url = (url=='pp'||url=='cc')?url:'ss';
		$(".sel-estas-consultando").get(0).setValue(url);
	}*/
						   
						   
	/*************************/
	/*   SECCION: FORM OK    */
	/*************************/
	$('.verTrafico').click(function() {
		$(this).hide();
		$('#tabla_trafico').show();
		return false;
	});
	/*************************/
	/* SECCION: MIS USUARIOS */
	/*************************/
	$('.tabla-permisos-modificar').find('a').click(function() {
		var td = $(this).parent().parent().parent().parent();
		var menu = td.find('.tabla-permisos-estado-menu');
		
		var trCss = $(this).parent().parent().parent().parent().next().css('paddingTop');
		
		if(!menu.is(':visible')) {
			menu.show();
			td.parent().addClass('top');
			
			$(this).html('Cancelar');
			$(this).parent().addClass('modificar');
			
			$(this).parent().parent().parent().parent().next().css('paddingTop','9px');
			
		} else {
			menu.hide();
			td.parent().removeClass('top');
			$(this).html('Modificar');
			$(this).parent().removeClass('modificar');
			
			$(this).parent().parent().parent().parent().next().css('paddingTop',trCss);
		}
		
		return false;
	});
});
/*************************/
/* SECCION: MIS USUARIOS */
/*************************/
function cambiarEstado(el, estado) {
	var ico, txt;
	switch(estado) {
		case 'total':
			ico = "ico_verde";
			bg = "ico_verde_b";
			txt = "Permiso Total";
			op0 = "menu_total";
			op1 = "menu_consulta";
			op2 = "menu_parcial";
		break;
		
		case 'parcial':
			ico = "ico_azul";
			bg = "ico_azul_b";
			txt = "Permiso Parcial";
			op0 = "menu_parcial";
			op1 = "menu_consulta";
			op2 = "menu_total";
		break;
		
		case 'consulta':
			ico = "ico_amarillo";
			bg = "ico_amarillo_b";
			txt = "Solo Consulta";
			op0 = "menu_consulta";
			op1 = "menu_total";
			op2 = "menu_parcial";
		break;
	}
	
	//Cambiar elemento del menu
	var el = $(el).parent().parent().parent();
	el.find('.tabla-permisos-estado-menu').hide();
	el.find('.tabla-permisos-estado').hide();
	el.find('.estado-confirmar').show();
	
	el.parent().css('padding',0);
		
		
	//Mostrar confirmacion
	var txtPrev = $(el).parent().parent().parent().find('.estado span').eq(1).html();
	el.find('.estado-confirmar').find('.prev').html(txtPrev);
	el.find('.estado-confirmar').find('strong').html(txt);
	el.find('.estado-confirmar').removeClass().addClass('estado-confirmar').addClass(bg);
	
	
	//Accion cancelar
	el.find('.estado-confirmar').find('a.btnCancelar').click(function() {
		el.find('.estado-confirmar').hide();
		el.find('.tabla-permisos-estado').show();
		el.parent().css('padding', '15px 0 0 10px');
		
		el.find('.tabla-permisos-modificar').removeClass('modificar');
		el.find('.tabla-permisos-modificar a').html('Modificar');
		el.parent().removeClass('top');
		
		return false;
	});
	
	//Accion confirmar
	el.find('.estado-confirmar').find('a.btnAzul').click(function() {
		el.find('.estado span').get(0).className = "icono "+ico;
		el.find('.estado span').eq(1).html(txt);
												 
		el.find('.estado-confirmar').hide();
		el.find('.tabla-permisos-estado').show();
		el.parent().css('padding', '15px 0 0 10px');
	
		el.find('.tabla-permisos-modificar').removeClass('modificar');
		el.find('.tabla-permisos-modificar a').html('Modificar');
		el.parent().removeClass('top');
		
		el.find('.'+op0).hide();
		el.find('.'+op1).show();
		el.find('.'+op2).show();
	
		return false;
	});
	
	return false;
}