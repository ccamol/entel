/**
 * 
 */
$(document).ready(function(){
	$('#solPin').click(function(){
    	var msisdn = $('#msisdn').val();
    	
		if (msisdn == "") {
			popupAlert("Ingresa la informaci\u00f3n requerida");
			return;
		} else {
			var largoNum = msisdn.length;
			if (largoNum < 8) {
				popupAlert("Ingresa un n\u00famero v\u00e1lido");
				return;
			}
		}
    	$('#pinForm').submit();
	});
});