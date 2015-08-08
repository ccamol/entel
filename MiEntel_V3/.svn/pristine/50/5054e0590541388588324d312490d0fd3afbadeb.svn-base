var indice = 2;
	var nextPrev = -1;
	var numData=0;
	var cont=0;
	var last="next";

	function mycarousel_initCallback(carousel) {
	    jQuery('.jcarousel-control a').bind('click', function() {
	        carousel.scroll(jQuery.jcarousel.intval(jQuery(this).text()));
	        return false;
	    });

	    jQuery('.jcarousel-scroll select').bind('change', function() {
	        carousel.options.scroll = jQuery.jcarousel.intval(this.options[this.selectedIndex].value);
	        return false;
	    });

	   
		jQuery('#mycarousel-next').bind('click', function() {
	        nextPrev=-1;
			carousel.next();
			return false;
	    });
		
		jQuery('#mycarousel-prev').bind('click', function() {
			nextPrev=-2;
			carousel.prev();
			return false;
	    });
	    
	};

	jQuery(document).ready(function() {
	    
		
		jQuery("#mycarousel").jcarousel({
	        scroll: 1,
			wrap: 'last',
			auto: 2,
			initCallback: mycarousel_initCallback,
			itemFirstOutCallback: {
				onBeforeAnimation: function(data) {

					if(nextPrev==-1){
						clickNext(indice);
					}
					if(nextPrev==-2){
						clickPrev(indice);
					}
					nextPrev=-1;
				
			  	}
			},
			buttonNextHTML: null,
			buttonPrevHTML: null
	    });
	}).css('visibility','visible');

		$("#mycarousel .enlace_destacado").each(function(i) {
			$(this).click(function(){
			
			indice = i+1;
				$("#mycarousel .enlace_destacado").removeClass("enlace_seleccionado");
				$(this).addClass("enlace_seleccionado");
			});
		});	
		
		function minimizar(){
			div = document.getElementById("mycarousel2");
			if(div.style.display=="none"){
				div.style.display = "";
			}else{
				div.style.display = "none";
			}
		}
		function clickHREF(data){
			if(numData!=data){
				div = document.getElementById("mycarousel2");
				if(div.style.display=="none"){
					div.style.display = "block";
				}
				indice=data-1;
				var total = $('#mycarousel .enlace_destacado').length;
		
				$('#mycarousel .enlace_destacado').removeClass('enlace_seleccionado');
				$('#mycarousel .enlace_destacado').eq(indice++).addClass('enlace_seleccionado');

				nextPrev=-1;
				
				limpiarLlenar(total,indice);
				
				if(indice == total) {
					indice = 0;
				}
				
				numData=data;
				nextPrev=0;
				last=0;
				indice++;
				last="next";
				cont=0;
			}
		}
		function clickPrev(data){
				
				indice=data-1;
				var total = $('#mycarousel .enlace_destacado').length;
				$('#mycarousel .enlace_destacado').removeClass('enlace_seleccionado');
				
				if(cont==0){
					if(indice>0)
					indice=indice-2;
				}else{
					if(indice>0)
					indice=indice-1;
				}
				cont++;
				$('#mycarousel .enlace_destacado').eq(indice).addClass('enlace_seleccionado');
				
				nextPrev=-1;
				limpiarLlenar(total,indice);
					
				if(indice == total) {
					indice = 0;
				}
				indice=indice+1;
				numData=data;
				nextPrev=0;
				last="prev";
										
		}
		
		function clickNext(data){
				
				if(last=="prev")
					data++;
				indice=data-1;
				var total = $('#mycarousel .enlace_destacado').length;
				$('#mycarousel .enlace_destacado').removeClass('enlace_seleccionado');
				
				$('#mycarousel .enlace_destacado').eq(indice++).addClass('enlace_seleccionado');
				
				nextPrev=-1;
				limpiarLlenar(total,indice);
				if(indice == total) {
					indice = 0;
				}
				indice=indice+1;
				numData=data;
				last="next";
				cont=0;
		}
		
		function limpiarLlenar(total,indice){
			if(total>8){
					
				for(i=2;i<(total);i++){
					div = document.getElementById("id_"+i);
					div.style.display = "none";
				}
				if(indice==total){
					div = document.getElementById("id_"+(indice-1));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-2));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-3));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-4));
					div.style.display = "";
					div = document.getElementById("id_"+(indice-5));
					div.style.display = "";
				}else{
					if(indice<(total-1)){
						div = document.getElementById("id_"+(indice));
						div.style.display = "";
						div = document.getElementById("id_"+(indice+1));
						div.style.display = "";
						div = document.getElementById("id_"+(indice+2));
						div.style.display = "";
						if(indice>1){
							div = document.getElementById("id_"+(indice-1));
							div.style.display = "";
						}
						if(indice>2){
							div = document.getElementById("id_"+(indice-2));
							div.style.display = "";
						}
					}
				}
				if(indice==0){
					div = document.getElementById("id_"+(indice+1));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+2));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+3));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+4));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+5));
					div.style.display = "";
					div = document.getElementById("id_"+(indice+6));
					div.style.display = "";
				}else{
					if(indice==1){
						div = document.getElementById("id_"+(indice+1));
						div.style.display = "";
						div = document.getElementById("id_"+(indice+2));
						div.style.display = "";
						div = document.getElementById("id_"+(indice+3));
						div.style.display = "";
						div = document.getElementById("id_"+(indice+4));
						div.style.display = "";
						div = document.getElementById("id_"+(indice+5));
						div.style.display = "";
					}else{
						if(indice==2){
							div = document.getElementById("id_"+(indice+1));
							div.style.display = "";
							div = document.getElementById("id_"+(indice+2));
							div.style.display = "";
							div = document.getElementById("id_"+(indice+3));
							div.style.display = "";
							div = document.getElementById("id_"+(indice+4));
							div.style.display = "";
						}else{
							if(indice==3){
								div = document.getElementById("id_"+(indice+1));
								div.style.display = "";
								div = document.getElementById("id_"+(indice+2));
								div.style.display = "";
								div = document.getElementById("id_"+(indice+3));
								div.style.display = "";
							}else{
								if(indice==(total-1)){
									div = document.getElementById("id_"+(indice));
									div.style.display = "";
									div = document.getElementById("id_"+(indice-1));
									div.style.display = "";
									div = document.getElementById("id_"+(indice-2));
									div.style.display = "";
									div = document.getElementById("id_"+(indice-3));
									div.style.display = "";
									div = document.getElementById("id_"+(indice-4));
									div.style.display = "";
								}else{
									if(indice==(total-2)){
										div = document.getElementById("id_"+(indice));
										div.style.display = "";
										div = document.getElementById("id_"+(indice-1));
										div.style.display = "";
										div = document.getElementById("id_"+(indice-2));
										div.style.display = "";
										div = document.getElementById("id_"+(indice-3));
										div.style.display = "";
									}
								}
							}
						}
					}
						
						
				}
			}	
		}	