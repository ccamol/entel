$(document).ready(function(){
	
	$("#centro .db-paginador-seccion").click(function(){
		var tablaCuerpo = $(this).parents(".db-tabla-cuerpo:first");
		var listas = tablaCuerpo.find("ul");
		var href = $(this).attr('href');
		var link = $(this).parents(".db-paginador:first").find(".db-paginador-seccion");

		for (i=0;i<listas.length;i++){
			//listas.eq(i).addClass('lista-tabla-oculta');
			listas.eq(i).hide();
			link.eq(i).removeClass('db-paginador-seccion-selecionado');
		}
		tablaCuerpo.find(href).fadeIn(500);
		$(this).addClass('db-paginador-seccion-selecionado');
		
		return false;
	});
	
	$("#centro .db-paginador-back").click(function(){
		var el  = $(this).parent().find(".db-paginador-seccion-selecionado").prev();
		if (el.hasClass('db-paginador-seccion')) {
			el.click();
		} else {
			$(this).parent().find("a.db-paginador-seccion:last").click();
		}
		return false;
	});
	
	$("#centro .db-paginador-next").click(function(){
		var el  = $(this).parent().find(".db-paginador-seccion-selecionado").next();
		if (el.hasClass('db-paginador-seccion')) {
			el.click();
		} else {
			$(this).parent().find("a.db-paginador-seccion:first").click();
		}
		return false;
	});
});