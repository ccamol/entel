/**
 * 
 */
$(document).ready(function() {
	$('#entrar').click(function() {
		var msisdn = $('#msisdn').val();
		var rut = $('#rut').val();
		var pin = $('#pin').val();

		if (msisdn == "") {
			popupAlert("Debes introducir un n\u00famero de tel\u00e9fono");
			return;
		} else {
			var largoNum = msisdn.length;
			if (largoNum < 8) {
				popupAlert("El n\u00famero de tel\u00e9fono debe contener 8 d\u00edgitos");
				return;
			}
		}

		if (!rut == "") {
			if (!Rut(rut)) {
				popupAlert("Rut inv\u00e1lido");
				return;
			}
		}else{
			popupAlert("Debes introducir un n\u00famero de Rut");
			return;
		}

		if (pin == "") {
			popupAlert("Debes ingresar tu clave");
			return;
		} else {
			var largoPin = pin.length;
			if (largoPin < 4) {
				popupAlert("La clave ingresada debe contener 4 d\u00edgitos");
				return;
			}
		}
		$('#loginForm').submit();
	});
});