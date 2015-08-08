/**
 * 
 */
$(document).ready(function(){
	$('#registerTwo').click(function(){
    	var rut = $('#rut').val();
    	var clave = $('#clave').val();
    	
		if (!rut == "") {
			if (!Rut(rut)) {
				popupAlert("Ingresa la informaci\u00f3n requerida");
				return;
			}
		}else{
			popupAlert("Debes introducir un n\u00famero de Rut");
			return;
		}
		
		if (clave == "") {
			popupAlert("Ingresa la informaci\u00f3n requerida");
            return;
    	}else{
			var largoPin = clave.length;
			if (largoPin < 4) {
				popupAlert("La clave ingresada debe contener 4 d\u00edgitos");
				return;
			}
		}
    		$('#formRegisterTwo').submit();
	});
});