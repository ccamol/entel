/*-------------------------------------------------------
	Funcion para cargar contenidos Mensaje para ti.
	
	PARAMETROS,
	urls -> array de urls.
	numMsjShow -> numero de mensajes a visualizar.
	
	VARIABLES,
	longitud -> Cantidad de urls.
	i -> Contador General.
	cont -> Contador de mensajes, se coloca en 1 al llegar al mismo valor de numMsjShow
			y con esto da paso a una nueva lista.
	contOK -> Contador de respuestas OK.
	indice -> Contador de identificadores existentes para los UL.
--------------------------------------------------------*/
function cargar_contenido(urls, numMsjShow){
	var i = 1; var indice = 1; var cont = 0; var contOK = 0;
	
	var longitud = urls.length;	
	
	if (longitud == 0){
		$('#loading-tabla-mensaje').hide();
		$('#alerta-tabla-mensaje').show();		
	} 
	else{
		crearLista(indice, false);
		$.each(urls, function(index, currentUrl){
			$.ajax({
				type: 'POST',
				url: currentUrl,
				dataType: 'json',
				success: function(respuesta){
					if (respuesta.estado == 'OK') {
						contOK++;
						if (cont < numMsjShow) {
							cont++;
						} else {
							cont = 1;
							indice++;													
							crearLista(indice, true);														
						}							
						agregarMensajeLista(indice, respuesta.value);									
					}
				},
				complete: function() {
					if(i == longitud){
						$('#loading-tabla-mensaje').hide();					
						if (contOK == 0) {			
							$('#alerta-tabla-mensaje').show();
						} 
						else {
							agregarPaginacion(indice);						
						}
					}
					i++;
				}
			});
		});
	}
	
}


/*-------------------------------------------------------
	Funcion que detiene la carga de mensajes
--------------------------------------------------------*/
function stop() {
	$('#loading-tabla-mensaje').hide();
	$('.tablePrueba').stopTime('controlled').end();
}

/*-------------------------------------------------------
	Funcion que crea una nueva lista
--------------------------------------------------------*/
function crearLista(indice, oculta) {
	var ul = '<ul id="lista_' + indice + '"></ul>';	
	
	if (oculta == true) {
		ul = '<ul id="lista_'+indice+'" class="lista_oculta"></ul>';
	}
	
	$('.tablePrueba').append(ul);
}

/*-------------------------------------------------------
	Funcion que agrega un mensaje a la lista
--------------------------------------------------------*/
function agregarMensajeLista(indice, mensaje) {
	$('#lista_' + indice).append('<li>' + mensaje + '</li>');
}

/*-------------------------------------------------------
	Funcion que crea el paginador
--------------------------------------------------------*/
function agregarPaginacion(indice) {
	if (indice > 1) {
		var paginador = '<div class="db_paginador clearfix">';	
		paginador += '<a class="db_paginador_item db_atras" onclick="anterior(this);"></a>';
		
		for (var i = 1; i <= indice; i++) {
			if (i == 1) {
				paginador += '<a id="' + i + '" class="db_paginador_item db_seccion db_seccion_seleccionado" onclick="enlaces(this);"></a>';
			} else {
				paginador += '<a id="' + i + '" class="db_paginador_item db_seccion" onclick="enlaces(this);"></a>';
			}
		}	
		
		paginador += '<a class="db_paginador_item db_siguiente" onclick="siguiente(this);"></a>';
		paginador += '</div>';
		
		$('.tablePrueba').append(paginador);
		
		if (indice == 2) {
			$('.db_paginador').css('padding','20px 20px 0 72px').show();
		} else if (indice == 3) {
			$('.db_paginador').css('padding','20px 20px 0 55px').show();
		}
	}
}

/*-------------------------------------------------------
	Funcion paginacion enlace centro.
--------------------------------------------------------*/
function enlaces(campo){
	var listas = $(campo).parents(".tablePrueba:first").find("ul");
	var href = $(campo).attr('id');
	var link = $(campo).parents(".db_paginador:first").find(".db_seccion");

	for (i=0;i<listas.length;i++){
		listas.eq(i).hide();
		link.eq(i).removeClass('db_seccion_seleccionado');
	}
	$('#lista_'+href+'').fadeIn(500);
	$(campo).addClass('db_seccion_seleccionado');
	
	return false;
}

/*-------------------------------------------------------
	Funcion paginacion enlace anterior.
--------------------------------------------------------*/	
function anterior(campo){	
	var lista = $(campo).parents(".tablePrueba:first").find("ul:visible");
	var link = $(campo).parents(".db_paginador:first").find(".db_seccion_seleccionado");
	var nroLista = parseInt(lista.attr('id').split('lista_')[1]);
	var nroLink = parseInt(link.attr('id'));
	nroLista = nroLista - 1;
	nroLink = nroLink - 1;
	if ( (nroLista>0) && (nroLink>0) ){
		lista.hide();
		lista.prev().fadeIn(500);
		link.removeClass('db_seccion_seleccionado');
		link.prev().addClass('db_seccion_seleccionado');
	}
	return false;
}

/*-------------------------------------------------------
	Funcion paginacion enlace siguiente.
--------------------------------------------------------*/
function siguiente(campo){
	var lista = $(campo).parents(".tablePrueba:first").find("ul:visible");
	var link = $(campo).parents(".db_paginador:first").find(".db_seccion_seleccionado");
	var ultimo = $(campo).parents(".db-tabla-cuerpo").find("ul:last");
	var nroUltimo = parseInt(ultimo.attr('id').split('lista_')[1]);
	var nroLista = parseInt(lista.attr('id').split('lista_')[1]);
	var nroLink = parseInt(link.attr('id'));
	nroLista = nroLista + 1;
	nroLink = nroLink + 1;
	if ( (nroLista<parseInt(nroUltimo+1)) && (nroLink<parseInt(nroUltimo+1)) ){
		lista.hide();
		lista.next().fadeIn(500);
		link.removeClass('db_seccion_seleccionado');
		link.next().addClass('db_seccion_seleccionado');
	}
	return false;
}