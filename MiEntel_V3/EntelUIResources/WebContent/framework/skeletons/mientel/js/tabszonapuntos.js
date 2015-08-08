$(document).ready(function() {
	
	$('.tabsZona').click(function(){
		$('.tabsZona').removeClass('seleccionado');
		$(this).addClass('seleccionado');
		var cadena = $(this).attr('href');
		$('.tabs-container').hide();
		$(cadena).show();
		return false;
	});
	
});