/**
 * Funcion para obtener info de deployment de la aplicacion
 */
function mientel_info(context) {

	var url = '/personas/MiEntelInfoServlet';
	if(context != null) {
		url = context;
	}

	$.ajax({
		type: 'GET',
		url: url,
		dataType: 'json',
		success: function (resp){
			console.log(resp);					
		},
		error: function() {
			console.log('error');
		}
	});

}
