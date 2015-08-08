/**
 * 
 */
$(document).ready(function() {
	$("a").click(function() {
		var edoc = $('#edoc').val();		
	});
	
	
	$("#btn_confirmar").click(function(e) {
		if ($("#confirmaCompra").is(':checked')){
			$("#bolsasForm").submit();
		}else{
			e.preventDefault();
			popupAlert("Debes marcar S\u00ed en \"Comprar esta bolsa\" antes de confirmar");
		}
	});
	
	$("#confirmaCompra").click(function() {
		
		if ($("#confirmaCompra").is(':checked')){
			$("#btn_confirmar").removeClass("btn-secondary");
			$("#btn_confirmar").addClass("btn-primary");			
		}else{
			$("#btn_confirmar").removeClass("btn-primary");			  
			$("#btn_confirmar").addClass("btn-secondary");								
		}
	});	
});