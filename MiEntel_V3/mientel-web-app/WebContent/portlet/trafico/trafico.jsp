<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<cm:search id="mensaje_error_plan_mm_excedido" query="idContenido = 'mensaje_error_plan_mm_excedido'" useCache="true"  />

<f:view>    
	<script type="text/javascript">
	
    function loadTrafico(){        
    	var url='<%=request.getContextPath()%>/portlet/trafico/traficoJson.faces';
    	var url2 = $('#detalleTrafico').attr('href');
    	setTimeout(function(){
    		alertaReintento('trafico');    
    	      },TIEMPOREINTENTO);
	    $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'json',
	            success: function (resp){
                    //Evitar reintento
	    	        flagRespuestas['trafico'] = '1';
	    	        if(resp.estado == 'Ok'){

		    	        	$('#traficoFechaActualizacion').html(resp.respuesta.traficoFechaActualizacionFormated);
			                $('#traficoVoz').html(resp.respuesta.traficoVozFormated+ ' minutos');
			                $('#traficoVozFechaActualizacion').html(resp.respuesta.traficoVozFechaActualizacionFormated);
			                $('#traficoMensajes').html(resp.respuesta.traficoMensajes);
			                $('#traficoMensajesFechaActualizacion').html(resp.respuesta.traficoMensajesFechaActualizacionFormated);

			            		                
		    	       if(!resp.respuesta.excedido){

		    	    	   		$('#traficoTotal').html(resp.respuesta.sumaTraficoDatosFormated);      
			                	$('#traficoInternetMovilFechaActualizacion').html(resp.respuesta.traficoInternetMovilFechaActualizacionFormated);
			                	/*
					            $('#traficoInternetMovil').html(resp.respuesta.traficoInternetMovilFormated+' MB');      
				                $('#traficoInternetMovilFechaActualizacion').html(resp.respuesta.traficoInternetMovilFechaActualizacionFormated);
				                $('#traficoBandaAnchaMovil').html(resp.respuesta.traficoBandaAnchaMovilFormated+' MB');
				                $('#traficoBandaAnchaMovilFechaActualizacion').html(resp.respuesta.traficoBandaAnchaMovilFechaActualizacionFormated);
				                $('#traficoBlackberry').html(resp.respuesta.traficoBlackberryFormated + ' MB');
				                $('#traficoBlackberryFechaActualizacion').html(resp.respuesta.traficoBlackberryFechaActualizacionFormated);
			                    $('#tabla_plan_normal').fadeIn();
			                    */			                
		    	          }else{       
		    	        	  		    	        	    
				                if(resp.respuesta.planesMultimediaBean == null || (resp.respuesta.planesMultimediaBean===undefined || resp.respuesta.planesMultimediaBean.valorTotalTrafico===undefined)){
				                	  $('#mensaje_error_mm_excedido').fadeIn();
					            }else{
						              $("#datos_incluidos_plan").html(resp.respuesta.planesMultimediaBean.valorTotalTrafico+' MB');
						              $("#porcentaje").html(resp.respuesta.planesMultimediaBean.promConsumo+' %');
						              $("#datos_disponibles").html(resp.respuesta.planesMultimediaBean.cuotaTraficoUtil+' MB');
					            	  $('#tabla_plan_mm_excedido').fadeIn();
					            	  
					            	  var valor_imagen=resp.respuesta.planesMultimediaBean.promConsumoSinDec;
					            	  
					            	  $('#img_barra_consumo').attr('src','../framework/skins/mientel/img/trafico/barras/img_barra_naranja_'+valor_imagen+'.png');

					            	 if(resp.respuesta.planesMultimediaBean.tieneValorExcedido){   
                                    	  $("#texto_datos_valor_adicional").html('La tarifa de cada MB adicional es de <b>$'+resp.respuesta.planesMultimediaBean.valorMBExcedido+'</b>');                                 	  
                                    	   $("#datos_excedidos_valor").html(resp.respuesta.planesMultimediaBean.traficoExcedido+' MB');     						               
     						               $("#datos_excedidos_fecha_dos").html('al '+resp.respuesta.planesMultimediaBean.fechaDiaMesHoraFormat);
     						            //NUEVO ESTRUCTURA TARIFARIA
     						               if(resp.respuesta.mostrarSeccionExcedidoDash == '1'){
     						            	  $('#datos_excedidos').fadeIn(); 
                                       	   	  $('#datos_excedidos_precio').fadeIn();
         						           }
     						            //NUEVO ESTRUCTURA TARIFARIA                                   	   
                                        }else{
                                        	$("#texto_datos_valor_adicional_sin_datos").html('La tarifa de cada MB adicional es de <b>$'+resp.respuesta.planesMultimediaBean.valorMBExcedido+'</b>');
                                        	$("#datos_excedidos_fecha_uno").html('al '+resp.respuesta.planesMultimediaBean.fechaDiaMesHoraFormat);   
											//NUEVO ESTRUCTURA TARIFARIA
                                        	if(resp.respuesta.mostrarSeccionExcedidoDash == '1'){
                                        		$('#sin_datos_excedidos').fadeIn(); 
                                        		$('#datos_excedidos_fecha_uno').fadeIn();
           						           }
                                        	//NUEVO ESTRUCTURA TARIFARIA
                                        	                                     	
                                        	 
                                        }
					            	  
						        }  
		    	        	
			    	    }
			                if(!resp.respuesta.tieneTraficoActual){
			                	$('#detalleTrafico').attr('href',url2+'&periodo=1');
			                }			                
			    			$('#loading-tabla-trafico').hide();
			    			$('#alerta-tabla-reintento-trafico').hide();
			    			$('#contenido-tabla-trafico').fadeIn();
	    	           }else{
	    	        	showErrorMessages('trafico',resp.detalle+" "+$('div[id*=detalleT]').html());
		    	    }	

	    	        if (param == 'saldoRecargosSGO' && flagRespuestas['facturacion']==1) {      	  	      
	      	  		    parent.location="#marcaSGO2"; 
	      	  	    }      
	            }
	        });

        } 

    $(document).ready(function() {
		loadTrafico();
	  	});
	</script>

<div class="db-tabla db-tabla-espacio">
	<div class="db-tabla-cabecera">
		<div class="db-cabecera-top"></div>
		<div class="db-cabecera-cuerpo clearfix">
			<div class="db-titulo db-titulo-trafico">Tr&aacute;fico</div>
			<div id="traficoFechaActualizacion" class="db-titulo-derecha">
			</div>
		</div>
	</div>
<div class="db-tabla-cuerpo">
	
	<div id="detalleT" style="display:none" align="center">
				<r:pageUrl pageLabel="${traficoController.pageLabelTrafico}" var="link">
				<r:param name="periodo" value="1"></r:param>
				</r:pageUrl>		
				<a href="<%=link%>" style="text-decoration: underline;">Ver detalle tr&aacute;fico</a>
	</div>
	
	<div class="alerta-tabla-reintento" id="alerta-tabla-reintento-trafico">
	</div>
	 <div id="loading-tabla-trafico">	 
	 </div>
     <div id="contenido-tabla-trafico">
		<ul>
			<li>
				<div class="clearfix">
					<span class="db-tabla-texto-izquierda">Voz</span>
					<span id="traficoVoz" class="db-tabla-texto-derecha"></span> 
				</div>
				<span id="traficoVozFechaActualizacion" class="db-tabla-texto-fecha"></span>
			</li>
			<li>
				<div class="clearfix">
					<span class="db-tabla-texto-izquierda">Mensajes</span>
					<span id="traficoMensajes" class="db-tabla-texto-derecha"></span>
				</div>
				<span id="traficoMensajesFechaActualizacion" class="db-tabla-texto-fecha"></span>
			</li>
			<li>
				<div class="clearfix">
					<span class="db-tabla-texto-izquierda">Datos</span>
					<span id="traficoTotal" class="db-tabla-texto-derecha"></span>
				</div>
				<span id="traficoInternetMovilFechaActualizacion" class="db-tabla-texto-fecha"></span>
			</li>
		</ul>
		<!-- 
		<div id="tabla_plan_normal" style="display: none;">
			<ul>	
				<li>			  
					<span class="db-tabla-texto">Datos</span>
						<div class="db-tabla-interna">
							<div class="db-tabla-interna-item db-tabla-interna-item-sombreado clearfix">
								<div class="clearfix">
									<span class="db-tabla-texto-izquierda">Internet m&oacute;vil</span>
									<span id="traficoInternetMovil" class="db-tabla-texto-derecha"></span>
								</div>
								<span id="traficoInternetMovilFechaActualizacion" class="db-tabla-texto-fecha"></span>
							</div>
							<div class="db-tabla-interna-item clearfix">
								<div class="clearfix">
									<span class="db-tabla-texto-izquierda">Banda ancha m&oacute;vil</span>
									<span id="traficoBandaAnchaMovil" class="db-tabla-texto-derecha"></span>
								</div>
								<span id="traficoBandaAnchaMovilFechaActualizacion" class="db-tabla-texto-fecha"></span>
							</div>
							<div class="db-tabla-interna-item db-tabla-interna-item-sombreado clearfix">
								<div class="clearfix">
									<span class="db-tabla-texto-izquierda">BlackBerry</span>
									<span id="traficoBlackberry" class="db-tabla-texto-derecha">
										
									</span>
								</div>
								<span id="traficoBlackberryFechaActualizacion" class="db-tabla-texto-fecha"></span>
							</div>
						</div>
				 </li>
			</ul>			
		</div>
		-->
		<div id="tabla_plan_mm_excedido" style="display:none;">
			<ul>
				  <li>			  
				    <div class="clearfix">
		                     <span class="db_texto flotar_izq">Datos incluidos en tu plan:</span>
		                     <span class="db_texto flotar_der"><strong class="flotar_der" id="datos_incluidos_plan"></strong></span>             
		               </div>
					<div class="db_tabla_interna">
						<div class="dbn_tabla_interna_barra clearfix">
							<div class="flotar_izq" style="width:105px;">
								<img id="img_barra_consumo" src=""  height="31">
		                               <p class="dbn_texto_porcent clearfix"><span id="porcentaje"></span> consumido</p>                                           
							</div>
		                     <div class="flotar_der" style="width:105px;">
		                         <span class="db_texto_fecha" id="datos_disponibles"></span>                                            
								   <span class="db_texto_fecha bdn_margen_top_min_2" id="datos_excedidos_fecha_uno" style="display:none;"></span>
		                      </div>
						</div>
					</div>
					<div class="cuadrogris-info-excedidos" id="datos_excedidos" style="display:none; border-bottom: 0 dotted #CCCCCC;">
						<div class="clearfix">
			                   <span class="db_texto flotar_izq">Datos adicionales:</span>
			                   <span class="db_texto flotar_der" id="datos_excedidos_valor"></span>             
			              </div>
			              <div class="clearfix" >			                  
			                   <span class="db_texto flotar_der" id="datos_excedidos_fecha_dos"></span>
			              </div> 
			              <div class="dbn_row_comp_datos_excedidos" id="datos_excedidos_precio" style="display:inline;">
		                    <span id="texto_datos_valor_adicional" ></span> 
		                  </div>  
					</div>	
					<div class="cuadrogris-info-excedidos" id="sin_datos_excedidos" style="display:none; border-bottom: 0 dotted #CCCCCC;">
						  <div class="dbn_row_comp_datos_excedidos" id="datos_excedidos_precio" style="display:inline;">
		                    <span id="texto_datos_valor_adicional_sin_datos" ></span> 
		                  </div>  
					</div>				
				</li>				
		    </ul>		     			 
		 </div>		 
		 <div id="mensaje_error_mm_excedido" class="mensaje-alerta-sistema-pequeno" style="display:none;margin-top: 10px;">		      
		  	   <div id="alerta-tabla-mensaje" style="display: block;"><cm:getProperty node="${mensaje_error_plan_mm_excedido[0]}" name="html" /></div>		  	  
         </div>	
         <a id="detalleTrafico" href='<r:pageUrl pageLabel="${traficoController.pageLabelTrafico}"></r:pageUrl>' style="padding: 15px 15px 0pt 68px; display: block; font-size: 12px;hover:">
				<u>Ver detalle Tr&aacute;fico</u>
	     </a>
	   </div>
	</div>
	<div class="db-tabla-pie">	
	</div>
</div>

</f:view>
