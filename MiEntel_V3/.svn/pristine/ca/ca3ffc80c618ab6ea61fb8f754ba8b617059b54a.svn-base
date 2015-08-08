/*---------------------------------

			JS TRAFICO

----------------------------------*/
function initMenuDesplegableTrafico() { 	
	$("#menu-desplegable div.trafico-anterior-titulo a.btn-trafico-anterior").click(function(){
		if($(this).hasClass("abierto")){
			$(this).removeClass("abierto").addClass("cerrado");
			$(this).parents("div.trafico-anterior-titulo").removeClass("abierto");
			$(this).next('div').hide();	
		}else{
			$(this).removeClass("cerrado").addClass("abierto");
			$(this).parents("div.trafico-anterior-titulo").removeClass("cerrado").addClass("abierto");
			$(this).next('div').show();	
		}
	});
}


$(document).ready(function() {
	
	$('.paso3').hide();
	$('.paso4').hide();
	
	/*
	 * Manejo de pasos en tráfico
	 */
	$('.ir_paso3').click(function(){
		var parde = $(this).parents('.bolsa_bam:first');		
		
		parde.css({
			'background'	:'#fff1a8',
			'border-bottom'	:'1px solid #cfc489',
			'border-top'	:'1px solid #cfc489'
		});	
		
		$(this).parents('.bolsa_bam:first').find('.paso2').hide();
		$(this).parents('.bolsa_bam:first').find('.paso3').show();
		
		return false;
	});
	
	$('.ir_paso2').click(function(){
		var pardeCancelar = $(this).parents('.bolsa_bam:first');		
		
		if (pardeCancelar.hasClass('impar')){
			
			pardeCancelar.css({
				'background'	:'#f5f5f5',
				'border-bottom'	:'1px solid #ebebeb',
				'border-top'	:'1px solid #ebebeb'
			});
			
		} else {
			
			pardeCancelar.css({
				'background'	:'#ffffff',
				'border-bottom'	:'1px solid #ebebeb',
				'border-top'	:'1px solid #ebebeb'
			});
			
		}
		
		$(this).parents('.bolsa_bam:first').find('.paso3').hide();
		$(this).parents('.bolsa_bam:first').find('.paso2').show();
		
		return false;
	});
	
	$('.ir_paso4').click(function(){
		$(this).parents('.bolsa_bam:first').find('.paso3').hide();
		$(this).parents('.bolsa_bam:first').find('.paso4').show();		
		return false;
	});
	
});

