/**
 * Base para todas las secciones
 */
var tempTooltip;

$(document).ready(function(){
	
	var random = Math.round(Math.random()*10000);
	
	/**
	 * Fix para botones en FF2
	 */
	if($.browser.mozilla && parseInt($.browser.version) <= 2) {
		$('.btnAzulGrandeDesactivado, .btnVerde, .btnVerdeDelgado, .btnVerdeGrande, .btnAzul, .btnAzulDelgado, .btnAzulGrande').css('display','-moz-inline-box');
	}
		
	if ($.browser.msie && parseInt($.browser.version) <= 6) {
		$(".sel-estas-consultando").css('width', '171px');
		$(".pngFix").pngFix(); 
	}
	//

	/**
	 * Transformacion de campos Select
	 */
	$(".txt-numero").inputBox();
	$(".txt-renumero").inputBox();
	$(".tipo-tarjeta").selectBox();
	$(".txt-numero,.txt-renumero").keypress(function (e){	
	  //if the letter is not digit then display error and don't type anything
	  if( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57))
	  {
	    
	    return false;
	  }
	});
//Valida que solo se ingresen caracteres numericos
	$(".input_numerico").keypress(function(evt){






		var key = evt.keyCode ? evt.keyCode : evt.which ;
		return (key < 32 || (key >= 48 && key <= 57)); 






	});
		
	$(".input_texto").keypress(function(evt){	 
		var key = evt.keyCode ? evt.keyCode : evt.which ; 
		return (key <= 31 || (key >= 65 && key <= 90) || (key >= 97 && key <= 122) || (key == 209 || key == 241) || (key == 225 || key == 193) || (key == 233 || key == 201) || (key == 237 || key ==205) || (key == 243 || key == 211) || (key == 250 || key == 218));  
	});
	
	eventualizaTooltips();
	eventualizaTextoTooltips();

	/*
	 * Configurar tooltips del futuro 
	 * (los que vengan como respuesta Ajax).
	 */
	$(".autoTooltip.titleTT").live('click', function() {
		var el = $(this);
		if (typeof el.attr('tooltip') == 'undefined') {
			eventualizaTooltips();
			eventualizaTextoTooltips();
			el.click();
		}
		return false;
	});
	
	
	$(".autoTooltip.titleTT").live('mouseover', function() {
		//console.log("FUTURE CLICK!!");
	}); /**/
});

function eventualizaTooltips() {
	/**
	 * Tooltips
	 */
	 
	TOOLTIPS = $(".autoTooltip:not(.TTready), .toolTip:not(.TTready)");
	if(TOOLTIPS.length > 0) {
		
		var html = '<div id="tooltip1" class="tooltip1" style="top: 142px; left: 459px; display: none"><div class="flecha"><div class="texto">tooltip</div></div></div>';
		$('body').append(html);
		if($.browser.msie) {
			$("#tooltip1").css("backgroundImage", $("#tooltip1").css("backgroundImage").replace(/\.png/i, '.gif'));
			var divInterior = $("#tooltip1 div.flecha:first");
			divInterior.css("backgroundImage", divInterior.css("backgroundImage").replace(/\.png/i, '.gif'));
		}
		
		var prepareFunction = function() {
			var el = $(this);
			
			//if (typeof el.attr('toolTip') !== 'undefined' && el.attr('toolTip') !== false) return true;
			
			el.removeClass('activo').addClass("TTready");
			if (typeof el.attr('title') !== 'undefined' && el.attr('title') !== false) {
				el.attr('tooltip', el.attr('title')).removeAttr('title');
			}
			var inlineTooltip = (el.hasClass("toolTip") && !el.hasClass("titleTT")); //si es este tipo de tooltip entonces el texto esta en el <a>(.*)</a>
			if (inlineTooltip) {
				el.data("tooltipText", el.html()).html("&nbsp &nbsp &nbsp").addClass("ico_interrogacionNuevo").addClass("visible").css("display", "inline-block");
			}
			
			if (el.hasClass("titleTT")) {
				el.addClass('toolTip');
				el.data("tooltipText", "<strong>" + el.attr('tooltip') + "</strong>");
			}/**/
		};

		TOOLTIPS.each(prepareFunction);
	 
		TOOLTIPS.bind('click', function(){
			
			var el = $(this);
			var tt = $('#tooltip1');
			var xy = el.offset();
			var text = el.attr('tooltip');
			var inlineTooltip = el.hasClass("toolTip"); //si es este tipo de tooltip entonces el texto esta en el <a>(.*)</a>
			var titleTooltip = el.hasClass("titleTT");
			
			var href = el.attr('href');
			//var activo = (tt.find('div').text() == text || (href == tempTooltip && href != 'javascript:;' && href != '#')) && tt.is(':visible'); //estoy haciendo click a un tooltip que esta activo ?
			var activo = (el.hasClass('activo')) && tt.is(':visible');
			
			if (activo) {
				
				var buttonBackg = el.css('backgroundImage').replace('tooltips/ico_x.gif', 'icons/ico_interrogacion.gif');
				el.css('backgroundImage', buttonBackg);
				
				var startTop = tt.offset().top;
				
				//if($.browser.msie && $.browser.version <= 6.0) {
				//	tt.hide();
					
				//} else {
					tt.animate({
						opacity: 0,
						top: startTop + 50 + 'px'
					}, 150);
				//}			
				
				tt.queue(function() {
					$(this).css('display','none').dequeue();
				});
				
				TOOLTIPS.removeClass('activo');
				tempTooltip = null;
			}
			else {
				
				if(el.hasClass("ext")) {
					tt.addClass("extendido");
				} else {
					tt.removeClass("extendido");
				}
				
				TOOLTIPS.each(function() {
					var buttonBackg = $(this).css('backgroundImage').replace('tooltips/ico_x.gif', 'icons/ico_interrogacion.gif');
					$(this).css('backgroundImage', buttonBackg);
				});
				
				var buttonBackg = el.css('backgroundImage').replace('icons/ico_interrogacion.gif', 'tooltips/ico_x.gif');
				el.css('backgroundImage', buttonBackg);
				
				if (inlineTooltip) {
					text = el.data("tooltipText");
				} else {
				var idTooltip = el.attr('href');
					if(/^[#]{1,}(\s|[a-zA-Z])*/i.test(idTooltip)) {
						text = $(idTooltip).html();
					}
				}
				
				TOOLTIPS.removeClass('activo');
				el.addClass('activo');
				tempTooltip = idTooltip;
				
				tt.find('div.texto:first').html(text)
					.end().css({
						top: xy.top - tt.height(),
						left: xy.left - 112,
						display: 'block'/*,
						zIndex: 149,
						opacity: 0 /**/
					});
					
				xy.top -= (parseInt(tt.height()) + 15);
				xy.left -= 112;
				
				if($.browser.msie && $.browser.version <= 5.0) {
					tt.css('opacity',1).css({
						top: xy.top,
						left: xy.left
					});
					
				} else {
					tt.css({ top: xy.top-50 });
					tt.animate({
						top: xy.top, 
						opacity: 1
					}, 100, function() {
						setTimeout(function() {
							if (el.hasClass('activo')) {
								el.click();
							}
						}, 10000);
					});
				}
			}
			
			return false;
		});
	 }
	/*------------------ FIN TOOLTIP -----------------*/	
}
function eventualizaTextoTooltips() {
	/**
	 * Tooltips
	 */
	if($(".autoTextoTooltip").length > 0) {
		
		var html = '';
		html += '<div id="tooltip1" class="tooltip1" style="top: 142px; left: 459px; display: none">';
		html += '	<a href="#" class="tooltipCerrar" onclick="return cerrarTooltip()">cerrar</a>';
		html += '	<div class="flecha">';
		html += '		<div class="texto">tooltip</div>';
		html += '	</div>';
		html += '</div>';
		$('body').append(html);

		$(".autoTextoTooltip").each(function() {
			var el = $(this);
			el.removeClass('activo');
			//el.attr('tooltip', el.attr('title')).removeAttr('title');
		});
	 
		$(".autoTextoTooltip").click(function(){
			
			var el = $(this);
			var tt = $('#tooltip1');
			var xy = el.offset();
			//var text = el.attr('tooltip');
			
			var href = el.attr('href');
			var activo = (el.hasClass('activo')) && tt.is(':visible');
			
			if (activo) {				
				var startTop = tt.offset().top;
				
				if($.browser.msie && $.browser.version <= 6.0) {
					tt.hide();
					
				} else {
					tt.animate({
						opacity: 0,
						top: startTop + 50 + 'px'
					}, 150);
				}			
				
				tt.queue(function() {
					$(this).css('display','none').dequeue();
				});
				
				$(".autoTextoTooltip").removeClass('activo');
				tempTooltip = null;
			}
			else {
				
				if(el.hasClass("ext")) {
					tt.addClass("extendido");
				} else {
					tt.removeClass("extendido");
				}
				
				var idTooltip = el.attr('href');
				if(/^[#]{1,}(\s|[a-zA-Z])*/i.test(idTooltip)) {
					text = $(idTooltip).html();
				}
				
				$(".autoTextoTooltip").removeClass('activo');
				el.addClass('activo');
				tempTooltip = idTooltip;
				
				//alert((parseInt(xy.left) - parseInt(tt.width())/2 + parseInt(el.width()/2)));
				
				tt.find('div.texto:first').html(text)
					.end().css({
						top: xy.top - tt.height(),
						left: (parseInt(xy.left) - parseInt(tt.width())/2 + parseInt(el.width()/2))+'px',
						display: 'block',
						zIndex: 149,
						opacity: 0
					});
					
				xy.top -= (parseInt(tt.height()) + 15);
				//xy.left -= (parseInt(xy.left) - parseInt(tt.width())/2 + parseInt(el.width()/2));
				
				if($.browser.msie && $.browser.version <= 6.0) {	
					tt.css('opacity',1).css({
						top: xy.top,
						left: (parseInt(xy.left) - parseInt(tt.width())/2 + parseInt(el.width()/2))+'px'
					});
					
				} else {
					tt.css({ top: xy.top-50 });
					tt.animate({
						top: xy.top, 
						opacity: 1
					}, 100);
				}
			}
			
			return false;
		});
	 }
	/*------------------ FIN TOOLTIP -----------------*/	
}

function cerrarTooltip() {
	$('.tooltip1').hide();
	$(".autoTextoTooltip").removeClass('activo');
	return false;
}


/*------------------------------------
            COOKIES             
-------------------------------------*/
function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	
	var data = document.cookie.split('cookie=').join('');
	if(!!data) {
		
		var flag = true;
		var output = [];
		var temp = data.split('@@');
		
		for (var i in temp) {
			var nombre = temp[i].split('##');
			if (nombre[0] == name) {
				nombre[1] = value;
				flag = false;
			}
			output.push(nombre[0]+'##'+nombre[1]);
		}
		
		if(flag) {
			output.push(name+'##'+value);
		}
		
		output = output.join('@@');
		
	} else {
		var output = name+'##'+value;
	}
	
	document.cookie = "cookie="+output+expires+"; path=/";
}

function readCookie(name) {
	
	var nameEQ = "cookie="
	var ca = document.cookie.split(';');
	var data = null;
	if(ca) {
		for(var i=0; i<ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1,c.length);
			if (c.indexOf(nameEQ) == 0) {
				data = c.substring(nameEQ.length, c.length);
			}
		}
		
		if(data) {
			var temp = data.split('@@');
			for(var i in temp) {
				var nombre = temp[i].split('##');
				if(nombre[0] == name) {
					return nombre[1];
				}
			}
		}
	}
	
	return null;
}

function eraseCookie(name) {
	createCookie(name,"",-1);
}




//FUNCIONES REINTENTOS ACCESO A SERVICIO
var TIEMPOREINTENTO = 15000;
var flagRespuestas = {"facturacion":'0',
			          "trafico":'0',
			          "productos":'0',
			          "plan":'0',
			          "traficoenlinea":'0',
			          "mensaje":'0',
			          "traficoBam":'0',
			          "saldoRecargaBam":'0',
			          "saldoBolsaPPBAM":'0',
			          "traficoBAMCC":'0',
			          "tuEquipo":'0',
			          "tuEquipoBAM":'0',
					  "saldoFDT":'0',
					  "selectorCuentas":'0'};



function alertaReintento(portletName){
	
        if (flagRespuestas[portletName] == 0){
        	 reintentar(portletName);
            }else if(flagRespuestas[portletName] == 2){
                $('#loading-tabla-'+portletName).hide();
                $('#alerta-tabla-reintento-'+portletName).fadeIn();
                $('#alerta-tabla-reintento-'+portletName).html('<p>El servicio no est&aacute; disponible en este momento.</p><p>Por favor intente m&aacute;s tarde.</p>');
        }
    }

function reintentar(portletName){
	flagRespuestas[portletName] = 2;
	if(portletName == 'facturacion'){
        loadFacturacion();    
    }else if(portletName == 'trafico'){
        loadTrafico();	
    }else if(portletName == 'productos'){
    	loadProductosContratados();	
    }else if(portletName == 'plan'){
    	loadPlan();
    }else if(portletName == 'traficoenlinea'){
    	loadTraficoEnLinea();
    }else if(portletName == 'mensaje'){
    	loadMensajesParaTi();
    }else if(portletName == 'traficoBam'){
    	loadTraficoBam();
    }else if(portletName == 'saldoRecargaBam'){
    	loadSaldoRecargaBam();	
    }else if(portletName == 'saldoBolsaPPBAM'){
    	loadSaldoBolsaPPBAM();
    }else if(portletName == 'traficoBAMCC'){
    	loadTraficoBAMCC();
    }else if(portletName == 'tuEquipo'){
    	loadTuEquipo();
    }else if(portletName == 'tuEquipoBAM'){
    	loadTuEquipoBAM();
    }else if(portletName == 'saldoFDT'){
    	loadSaldo();
    }else if(portletName == 'selectorCuentas'){
    	loadSelector();
    }else if(portletName == 'tuEquipo4GLTE'){
    	load4GLTE();
    }
}

function showErrorMessages(portletName,msg){
	 $('#loading-tabla-'+portletName).hide();
     $('#alerta-tabla-reintento-'+portletName).fadeIn();
     $('#alerta-tabla-reintento-'+portletName).html('<p>'+msg+'</p>');
}

function obtenerParametroURL(name) {		 
	var regexS = "[\\?&]"+name+"=([^&#]*)"; 
	var regex = new RegExp(regexS); 
	var tmpURL = window.location.href; 
	var results = regex.exec(tmpURL); 
 
	if (results == null) { 
		return "" ; 
	} else { 
		return results[1]; 
	} 
}

function I2BClassValidator(arg) {
	
	var el = $(this);
	var opt = {
		rules: {},
		messages: {}
	};
	if (typeof arg == 'object') {
		$.extend(opt, arg);
	}

	for (var key in opt.rules) {
		var newRules = {
   		messages: {}

   	};
		$.extend(newRules, opt.rules[key]);
		if (typeof opt.messages[key] != 'undefined') {
			$.extend(newRules.messages, opt.messages[key]);
		}
		$("input." + key + ", textarea." + key + ", select." + key).each(function() {
			if ($(this).length>0) {
				$(this).rules("add", newRules);
			}
		});
	}
}

function showGlobo2(el) {
	
	
	if ($('#globoError').length < 1) {
		var $globo = $('<div id="globoError" class="globoError mensaje"><table><tr><td class="body">msg</td></tr></table></div>')
			.css({top: "-999px", left: "-999px", position: 'absolute' })
			.appendTo('body');
			
		$('#globoError').hide();

	
	}
	
	
	//pintaCampoRojo(el);
	//var $input = $(el).parents('.fila_campo').find('div.campo:last');//.find('div:last');
	var $input = $(el).parents('div:first');
	var punto = $input.offset();
	var $globo = $('#globoError');
		
	if ($globo.is(':hidden')) {
		$globo.fadeIn(200, function() {
			$(el).focus();							
		});
	}
	
	$globo.css({
		'top': $input.offset().top + parseInt($input.height())/2 - parseInt($globo.css('height'))/2,
		'left': $input.offset().left + parseInt($input.width()) + 5
	});
	
	$(window).resize(function() {
		$globo.css({
			'top': $input.offset().top + parseInt($input.height())/2 - parseInt($globo.css('height'))/2,
			'left': parseInt($input.width()) + 5
		});					  
	});
	
	return false;
}
/**/

function formatearRut(casilla){
	function formatearMillones(nNmb){
		var sRes = "";
		for (var j, i = nNmb.length - 1, j = 0; i >= 0; i--, j++)
		 sRes = nNmb.charAt(i) + ((j > 0) && (j % 3 == 0)? ".": "") + sRes;
		return sRes;
	}
	
	casillaRut = $(casilla)[0];
	
	var rut=casillaRut.value;
	var ultimoDigito=rut.substr(rut.length-1,1);
	var terminaEnK = (ultimoDigito.toLowerCase()=="k");
	rutSinFormato=rut.replace(/\W/g,"");
	rut=rut.replace(/\D/g,"");
	var dv=rut.substr(rut.length-1,1);
	if(!terminaEnK){ rut=rut.substr(0,rut.length-1); }
	else{ dv="K"; }
	if(rut && dv) {
		casillaRut.value=$.Rut.formatear(rut)+"-"+dv;
	}
}

$.fn.inputNumerico = function() {
	return this.each(function() {
		$(this).keydown(function(e) {
			var key = e.charCode || e.keyCode || e.which;
			return (key <= 31 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105)); 
		}); 
	});
};

$.fn.inputRut = function() {
	return this.each(function() {
		$(this).keydown(function(e) {
			var key = e.charCode || e.keyCode || e.which;
			return (key <= 31 || (key >= 48 && key <= 57) || key == 75 || key == 107 || (key >= 96 && key <= 105));  
		}); 
	});
};