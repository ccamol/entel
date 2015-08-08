$(document).ready(function() {	
	
	var zIndex = 500;
	var filaCampos = $('.fila-campo');
	for(i=0; i<filaCampos.length; i++) {
		filaCampos.eq(i).css({
			zIndex: zIndex--,
			position: 'relative'
		});	
	}
	
	$('input.inputBox').each(function() {
		var defaulValue = $(this).val();
		$(this).data('valor', defaulValue).focus(function() {
			var input = $(this);
			if(input.val() == input.data('valor')) {
				input.val('');
			}
			input.blur(function() {
				var el = $(this);
				if(el.val() == '') {
					el.val($(this).data('valor'));
				}
			});
		});
	});						   
});

function soloNumeros(evt){
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 31 || (key >= 48 && key <= 57)); 
}

function soloLetras(evt) {
	var key = evt.keyCode ? evt.keyCode : evt.which ;
	return (key <= 31 || (key >= 65 && key <= 90) || (key >= 97 && key <= 122) || (key == 209 || key == 241) || (key == 225 || key == 193) || (key == 233 || key == 201) || (key == 237 || key ==205) || (key == 243 || key == 211) || (key == 250 || key == 218)); 
}


/*****************************************/
/*               BORRAR                  */
/* SOLO PARA FUNCIONAMIENTO DE PROTOTIPO */
/*****************************************/
$(document).ready(function() {
	var url = window.location.href;	
	url = url.split('?');
	if(url.length > 1) {
		url = url[1].split('canal=');
		if(url.length > 1) {
			url = url[1];
		}
	}
	
});