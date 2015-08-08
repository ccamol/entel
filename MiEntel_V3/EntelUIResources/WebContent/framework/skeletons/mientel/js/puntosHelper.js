function actualizarPuntos(path){
	var url = path;	
	$.ajax({
        type: 'POST',
        url: url,
        dataType: 'json',
        success: function (resp){
	        if(resp.estado == 'Ok'){
	        	$('#puntosVencidos').html(resp.respuesta.puntosVencidos);
	        	$('#saldoContable').html(resp.respuesta.saldoPuntos);	
	        	
	        	$('#div_bolsas_canje .marcador_fila').each(function(i, fila){	  	        		
	        		var puntosNecesarios = $(fila).find('.info_producto').val().split('|')[1];	        			    		        	
	        		if(puntosNecesarios > resp.respuesta.saldoPuntos){	        			
	        			$(fila).find('.fila_estado1 .botones').replaceWith('<div class="puntos_insuficientes">Necesitas m&aacute;s puntos para canjear esta bolsa</div>');	        				        		
	        		}	    			
	        	});
	        	
	        	$('#div_recargas_canje .marcador_fila').each(function(i, fila){	        		
	        		var puntosNecesarios = $(fila).find('.info_producto').val().split('|')[1];	    			
	        		if(puntosNecesarios > parseInt(resp.respuesta.saldoPuntos)){
	        			$(fila).find('.fila_estado1 .botones').replaceWith('<div class="puntos_insuficientes">Necesitas m&aacute;s puntos para canjear esta bolsa</div>');	        			
	        		}	    			
	        	});
	        }
        }
	});	
		
}