/**
 * 
 */
$(document).ready(function(){
	$('#registerThree').click(function(){
    	var email = $('#email').val();
    	var condTerm = $('#condTerm').is(':checked');
    	
    	if(email==""){
    		popupAlert("Debes ingresar un email");
            return;
    	}if(!condTerm){
    		popupAlert("Debes aceptar las condiciones de uso de Mi Entel para registrarte");
   			return;
    	}else{
    		$('#formRegisterThree').submit();
    	}
	});
});