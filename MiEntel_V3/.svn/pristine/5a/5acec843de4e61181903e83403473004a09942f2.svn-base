<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="preferences" %>

<f:view>
	<c:set var="isAdmin" value="${miEntelBean.AAATitular}" />
	<c:set var="isTotal" value="${miEntelBean.AAAControlTotal}" />
	<c:set var="isParcial" value="${miEntelBean.AAAControlParcial}" />
	<c:set var="isConsulta" value="${miEntelBean.AAAConsultar}" />
	<c:set var="aaa" value="${profile.aaa}" />
	<c:set var="mercadoUser" value="${profile.mercado}" />
	<preferences:getPreference name="pageLabel" var="pageLabel"/>
	
	<script type="text/javascript">

	var param = obtenerParametroURL("contexto");
	

	function abrirDetalleLlamados () {

   	 var parametros = {"folio":$('#pfolio').val(),"tipoDoc":$('#ptipoDoc').val()};
   	 var url='<%=request.getContextPath()%>/portlet/facturacion/TB_facturaDetalleJson.faces';	
   		$.ajax({
	            type: 'POST',
	            url: url,
	            data:parametros,
	            dataType: 'json',
	            success: function (resp){			                
     	    	if(resp.estado == 'Ok'){		      	    			      	    	
		      	  		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, width=1000, height=665, top=20, left=140";
		    		    window.open(resp.respuesta,"Entel",opciones);
	            }
  	          }
	      });		
     }

     
     $(document).ready(function() {
			validateSVG();
	 		loadFacturacion();
	 	});

      var svgSupport;
	   function validateSVG(){
	 		svgSupport = (window.SVGSVGElement) ? true : false;
	 		if(!svgSupport){
		 		$('#graficoFactura').hide();
		 		$('#graficoFacturaImage').find('.graficoImage').attr('src','<%=request.getContextPath()%>/ChartFacturacionImageServlet');
		 		$('#graficoFacturaImage').show();		    
		 	}
		}
	   
       function loadFacturacion(){    	    
         var aaaAdmin= 3;
    	 var url='<%=request.getContextPath()%>/portlet/facturacion/resumenFacturacionJson.faces';
    	 var urlPagoEnLinea='';

    	 $('.estado_cuenta_box').hide(); 
    	 
    	 setTimeout(function(){
    		 alertaReintento('facturacion');    
     	      },TIEMPOREINTENTO);
	      
   	      $.ajax({
 	            type: 'POST',
 	            url: url,
 	            dataType: 'json',
 	            success: function (resp){
	                //Evita hacer reintento
   	    	        flagRespuestas['facturacion'] = '1';

      	    	if(resp.estado == 'Ok'){
          	    	
 	                $('#totalPagar').html('Total: $ '+resp.respuesta.totalPagoFormated);
 	                $('#fechaPeriodo').html(resp.respuesta.fechaPeriodoFormated);
 	                $('#fechaVencimiento').html(resp.respuesta.fechaVencimientoFormated);
 	                $('#fechaEmision').html(resp.respuesta.fechaEmisionFormated);
 	                $('#ptipoDoc').val(resp.respuesta.tipoDocumento);
 	                $('#pfolio').val(resp.respuesta.folio);  	                      

 	                if(resp.respuesta.estadoDocumento=='PAGADA'){
 	                	$('.db-facturacion-boton').hide();
 	                	$('.estado_cuenta_box').show();
 	 	            }

 	                if(aaaAdmin=='${aaa}'){
						 if(svgSupport){
							configurarParametrosGrafico(resp);
						 }		
                    }
 	    			$('#loading-tabla-facturacion').hide();
 	    			$('#alerta-tabla-reintento-facturacion').hide();
 	    			$('#contenido-tabla-facturacion').fadeIn();

 	    			if (param == 'saldoRecargosSGO') { 	    			   
 	    				parent.location="#marcaSGO2"; 
 	    			}
 	            }else{
 	            	 showErrorMessages('facturacion',resp.detalle);
 	            	 }
      	    	
      	    	if (param == 'saldoRecargosSGO' && flagRespuestas['trafico']==1) {      	  	      
      	  		    parent.location="#marcaSGO2"; 
      	  	    }	
   	          }
 	        });
        } 

            var options;
            function configurarParametrosGrafico(resp){

            	 //Configurando parametros del grafico
	                options = {
					chart: {
					    renderTo: 'grafico',
					    defaultSeriesType: 'column',
					    marginLeft: 50
					},
					title: {
					    text: ''
					},
					credits : {
					    enabled : false
					},
					legend : {
					    enabled : false
					},
					colors: [ '#66A4ED' ],

            	    xAxis: {
               	        categories: []
             	    },
             	   tooltip: {
             	    	formatter: function() {
             	    	return '<p style="font-size:10px;">'+this.x+'</p> <br></br>  : <b> $ ' +formato_numero(this.y,1,",",".")+'</b>';
             	        }
					},
					yAxis: [{
					   title: {
					       text: null
					   },                                
					   minorGridLineWidth: 0,
					   gridLineWidth: 0,
					   alternateGridColor: 'rgb(242, 242, 242)',
					   
					   labels: {
					       align: 'left',
					       enabled : true,
					       x: -50,
					        
					       formatter: function() {
					          var numeroFormateado = formato_numero(this.value,1,",",".");
					           return numeroFormateado;					           
					       }
					   },
					   
					   minorTickInterval: 1000,
					   showFirstLabel: false,
					   showLastLabel: true
					}],

					plotOptions: {
					    series: {
					        stacking: 'normal'
					    },       
					    column: {
					       dataLabels: {
					           enabled: false
					       }
					    }
					},

					series: []
				};

	                //parametros de los datos 
	                var series = {
                	name: ' ',
	    	            data: []
	    	        };

	    			//Configurar datos
	    			var itemsData = resp.respuesta.meses;
	    			$.each(itemsData, function(itemNo, item) {
	                    options.xAxis.categories.push(item);
	                });
	    			
	    			var itemsData = resp.respuesta.facturacionMeses;
	    			var max = 0;
	    			$.each(itemsData, function(itemNo, item) {

 	    			var parsedItem = parseFloat(item);
		    			series.data.push(parsedItem);

		    			if(parsedItem > max) {
 		    			max = parsedItem;
		    			}  
	                });

			   /*
				* Calculo de intervalo del ejeY
				* Que sera al multiplo de 1000 siguiente mas
				* cercano a 'max' (maximo valor de la serie)
				*/
				var numIntervalos = resp.respuesta.numIntervalosGrafico;
				var tickInterval = ( parseInt(max/(numIntervalos*1000)) + 1) * 1000;
				options.yAxis[0].tickInterval = tickInterval;
	                
	    			if(series.data.length > 0){
 	    			options.series.push(series);		

					//Opcions globales del graficos
					Highcharts.setOptions({
					    chart: {
					        style: {
					            fontFamily: 'Arial,Helvetica,sans-serif',
					            fontSize: '12px',
					            fontStyle: 'normal'
					        }
					    }
					});
 	    			
 	    			var chart = new Highcharts.Chart(options);
 	    			
	    			}else{
	    				$('#graficoFactura').hide();
	 	    		}

            }


            function formato_numero(numero, decimales, separador_decimal, separador_miles){ // v2007-08-06
                numero=parseFloat(numero);
                if(isNaN(numero)){
                    return "";
                }

                if(decimales!==undefined){
                    // Redondeamos
                    numero=numero.toFixed(decimales);
                }

                // Convertimos el punto en separador_decimal
                numero=numero.toString().replace(".", separador_decimal!==undefined ? separador_decimal : ",");

                if(separador_miles){
                    // Añadimos los separadores de miles
                    var miles=new RegExp("(-?[0-9]+)([0-9]{3})");
                    while(miles.test(numero)) {
                        numero=numero.replace(miles, "$1" + separador_miles + "$2");
                    }
                }

                var parts = numero.split(",");
                if(parts.length >= 1){
                	numero=parts[0];
                }
                
                return numero;
            }

	</script>	
	
	<div class="db-tabla db-tabla-espacio" id="facturacion">
	<div class="db-tabla-cabecera">
	<div class="db-cabecera-top"></div>
	<div class="db-cabecera-cuerpo">
	<div class="db-titulo db-titulo-facturacion">Facturaci&oacute;n</div>	
	</div>	
	</div>
	<div class="db-tabla-cuerpo">
	           
		<c:choose>
			<c:when test="${isAdmin==aaa}">
				<div class="alerta-tabla-reintento"
					id="alerta-tabla-reintento-facturacion"></div>
				<div id="loading-tabla-facturacion"></div>
	
				<div id="contenido-tabla-facturacion">
	
					<ul class="db-facturacion">
						<li class="db-facturacion-total-pago clearfix">
							<span id="totalPagar" class="db-tabla-texto-izquierda db-texto-largo"></span>
						    <span class="db-tabla-texto-derecha">
						   	    <a href="javascript:abrirDetalleLlamados()" class="linkFacturaDetalle"><u><span>Ver factura y detalle</span></u></a>
							</span>
						</li>
						<li class="nobullet clearfix">
							<ul class="data_interior">
								<li class="clearfix"><span
									class="db-tabla-texto-izquierda db-texto-largo">Per&iacute;odo</span>
								<span id="fechaPeriodo" class="db-tabla-texto-derecha"></span></li>
								<li class="clearfix"><span
									class="db-tabla-texto-izquierda db-texto-largo">Emisi&oacute;n</span>
								<span id="fechaEmision" class="db-tabla-texto-derecha"></span></li>
								<li class="clearfix"><span
									class="db-tabla-texto-izquierda db-texto-largo">Vence</span> <span
									id="fechaVencimiento" class="db-tabla-texto-derecha"></span></li>
							</ul>
						</li>
					</ul>
					
					<div class="db-facturacion-boton clearfix">
						<div class="db-facturacion-boton-enlace">           
							<a class="btnVerdeGrande" title="Realiza tu Pago en L&iacute;nea" href="<r:pageUrl pageLabel='${pageLabel}'></r:pageUrl>" onclick="mxTracker._trackPromo('Caja Facturacion','Pagar');">
								<span>Pagar en l&iacute;nea </span>
							</a>	
						</div>
						
						<div class="db-facturacion-boton-textos">
							<a href="http://personas.entelpcs.cl/PortalPersonas/appmanager/entelpcs/personas?_nfpb=true&_pageLabel=P15200965121299677977380" target="_blank">
								<u>Otras formas de pago</u>
							</a>
						</div>
					</div>
					
					<cm:search id="estado_factura_box" query="idContenido = 'estado_factura_box'" useCache="true"  />		     
					<div  class="estado_cuenta_box">
						<cm:getProperty node="${estado_factura_box[0]}" name="html" />
					</div>
					
					<div id="graficoFactura" class="db_grafico">
						<span>&Uacute;ltimos 6 meses:</span>
						<div id="grafico" style="height: 140px; width: 225px"></div>
					</div>
					
					<div id="graficoFacturaImage" class="db_grafico" style="display:none">
						<span>&Uacute;ltimos 6 meses:</span>
						<div style="height: 140px; width: 225px">
							<h:graphicImage id="graficoImage" styleClass="graficoImage"></h:graphicImage>
						</div>
					</div>
				</div>
				
			</c:when>
			
			<c:when test="${isTotal==aaa || isParcial==aaa}">
				<div class="alerta-tabla-reintento"
					id="alerta-tabla-reintento-facturacion"></div>
				<div id="loading-tabla-facturacion"></div>
	
				<div id="contenido-tabla-facturacion">
		
					<ul class="db-facturacion">
						<li class="db-facturacion-total-pago clearfix">
							<span id="totalPagar" class="db-tabla-texto-izquierda db-texto-largo"></span>
							<span class="db-tabla-texto-derecha"></span>
						</li>
						<li class="nobullet clearfix">
							<ul class="data_interior">
								<li class="clearfix">
									<span class="db-tabla-texto-izquierda db-texto-largo">Per&iacute;odo</span>
									<span id="fechaPeriodo" class="db-tabla-texto-derecha"></span>
								</li>
								<li class="clearfix">
									<span class="db-tabla-texto-izquierda db-texto-largo">Emisi&oacute;n</span>
									<span id="fechaEmision" class="db-tabla-texto-derecha"></span>
								</li>
								<li class="clearfix">
									<span class="db-tabla-texto-izquierda db-texto-largo">Vence</span>
									<span id="fechaVencimiento" class="db-tabla-texto-derecha"></span>
								</li>
							</ul>
						</li>
					</ul>
		
					<div class="db-facturacion-boton clearfix">
						<div class="db-facturacion-boton-enlace">
							<a class="btnVerdeGrande" title="Realiza tu Pago en L&iacute;nea" href="<r:pageUrl pageLabel='${pageLabel}'></r:pageUrl>" onclick="mxTracker._trackPromo('Caja Facturacion','Pagar');">
								<span>Pagar en l&iacute;nea</span>
							</a>
						</div>
						<div class="db-facturacion-boton-textos">
							<a href="http://personas.entelpcs.cl/PortalPersonas/appmanager/entelpcs/personas?_nfpb=true&_pageLabel=P15200965121299677977380" target="_blank">
								<u>Otras formas de pago</u>
							</a>
						</div>
					</div>
	            
					<div class="db-facturacion-llamados">	
						<a target="_blank" href="javascript:abrirDetalleLlamados()" class="ver_detalle link_2 linkFacturaDetalle"><u>Ver detalle de llamados</u></a>				   
					</div>
				</div>
				
			</c:when>
	         
			<c:when test="${isConsulta==aaa}">
				<div class="contenedor-mensajes"> 
			        <ul class="mensajes-lista">			        			       
				        <h:outputText value="#{ miEntelBean.accessDeniedDefaultMessage }" styleClass="mensaje-error" ></h:outputText>				        
			        </ul> 
			    </div>
			</c:when>
			
			<c:otherwise>
			    <div class="contenedor-mensajes"> 
			        <ul class="mensajes-lista">			        			       
				        <h:outputText value="#{ miEntelBean.accessDeniedDefaultMessage }" styleClass="mensaje-error" ></h:outputText>				        
			        </ul> 
			    </div>
			</c:otherwise>
			
		</c:choose>
	</div>
		<div class="db-tabla-pie"></div>
	</div>
	<input type="hidden" value="" id="pfolio" />
	<input type="hidden" value="" id="ptipoDoc" />
</f:view>



