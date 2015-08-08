$(document).ready(function(){
	var actions = $.url().param("action");
	if(actions!=""){
		if(actions=="renovar"){
			limpiarTabs();
			$('.linea_tabs > .tab').eq(0).addClass('seleccionado');
			$('.contenido_tabs > .contenido_tab').eq(0).css({'display':'block'});
		}else{
			if(actions=="bloquear"){
				limpiarTabs();
				$('.linea_tabs > .tab').eq(1).addClass('seleccionado');
				$('.contenido_tabs > .contenido_tab').eq(1).css({'display':'block'});
			}else{
				if(actions=="servicio"){
					limpiarTabs();
					$('.linea_tabs > .tab').eq(2).addClass('seleccionado');
					$('.contenido_tabs > .contenido_tab').eq(2).css({'display':'block'});
				}else{
					if(actions=="config"){
						limpiarTabs();
						$('.linea_tabs > .tab').eq(3).addClass('seleccionado');
						$('.contenido_tabs > .contenido_tab').eq(3).css({'display':'block'});
					}else{
						if(actions=="iphone"){
							limpiarTabs();
							$('.linea_tabs > .tab').eq(4).addClass('seleccionado');
							$('.contenido_tabs > .contenido_tab').eq(4).css({'display':'block'});
						}						
					}
				}
			}
		}
	}
});

function limpiarTabs(){
	$('.tab').removeClass('seleccionado');
	$('.contenido_tab').css({'display':'none'});
}