<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="pref" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<pref:getPreference name="tipoLink"  var="tipoLink"/>
<pref:getPreference name="mostrarBannerEcuenta"  var="mostrarBannerEcuenta" defaultValue="1" />
<pref:getPreference name="marcaEventoBotonEcuenta" var="marcaEcuenta"/>

<cm:search id="params" query="idContenido = '${marcaEcuenta}'" useCache="false"/>

<f:view beforePhase="#{facturacionFullController.init}">

<h:panelGroup rendered="#{facturacionFullController.urlFacturaContexto!=''}">
    <script type="text/javascript">
        function abrirDetalleEcuenta(){
            var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, width=1000, height=665, top=20, left=140";
            window.open('<h:outputText value="#{facturacionFullController.urlFacturaContexto}" />',"",opciones); 
        }

        $(document).ready(function(){
            abrirDetalleEcuenta();
        });
    </script>
</h:panelGroup>
<script type="text/javascript">

function abrirDetalleLlamados () {
	   var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, width=1000, height=665, top=20, left=140";
	   window.open('<h:outputText value="#{facturacionFullController.urlFactura}" />',"",opciones); 
}

function abrirDetalleLlamadosItem (folio,tipoDoc,theNewWin) {

 	 var parametros = {"folio":folio,"tipoDoc":tipoDoc};
 	 var url='<%=request.getContextPath()%>/portlet/facturacion/TB_facturaDetalleJson.faces';	
 		$.ajax({
	            type: 'POST',
	            url: url,
	            data:parametros,
	            dataType: 'json',
	            success: function (resp){			                
   	    	if(resp.estado == 'Ok'){		      	    			      	    	
   	    				//theNewWin.window.location = resp.respuesta;
   	    				theNewWin.location.href = resp.respuesta;
	/*	      	  		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, width=1000, height=665, top=20, left=140";
		    		    window.open(resp.respuesta,"Entel",opciones);*/ 
	            }
	          }
	      });		
}


			var svgSupport;
			function validateSVG(){
					svgSupport = (window.SVGSVGElement) ? true : false;
					if(!svgSupport){						
				 		$('#graficoFactura').hide();
				 		$('#graficoFacturaImage').find('.graficoImage').attr('src','<%=request.getContextPath()%>/ChartFacturacionImageServlet');
				 		$('#graficoFacturaImage').show();		    
				 	}
			}

			
			function hideErrorDiv(){
				$('div[id*=errorMSG]').hide();
			}

					
			$(document).ready(function() {
		    	  validateSVG();	
		    	  if(svgSupport){
						configurarParametrosGrafico();
				    }		    	  	    	  		    	  
				$('a.ver_detalle.link-fact').css({
					padding: "0 9.5px"
				});					
				if($('#folioFromContexto').val() != ''){
					var tipo = $('#tipoDocFromContexto').val();
					var folio = $('#folioFromContexto').val();
					var URL = "/personas/detalleLlamadosPDF?origen=FACT&tipo="+tipo+"&folio="+folio;
					window.location.href = URL;
				}				
			});


			var options;
            function configurarParametrosGrafico(){

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
             	    	return '<p style="font-size:10px;">'+this.x+'</p> <br></br>  : <b>$ ' +formato_numero(this.y,1,",",".")+'</b>';
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
	    			//var itemsData = resp.respuesta.meses;	    			
	    			 var itemsData = '<h:outputText value="#{facturacionFullController.mesesFacturas}" />';	    			   
	    			     itemsData = itemsData.split(',');  
	    			     itemsData= itemsData.reverse(); 
	    			    	
	    			$.each(itemsData, function(itemNo, item) {
	                    options.xAxis.categories.push(item);
	                });
	    			
	    			//var itemsData = resp.respuesta.facturacionMeses;
	    			
	    			var itemsData = '<h:outputText value="#{facturacionFullController.montosFacturas}" />';	    			  
	    			    itemsData = itemsData.split(',');
	    			    itemsData = itemsData.reverse(); 
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
				//var numIntervalos = resp.respuesta.numIntervalosGrafico;
				
				var numIntervalos = '<h:outputText value="#{facturacionFullController.numIntervalosGrafico}" />';
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




			

		   /*function validateSVG(){
		 		var svgSupport = (window.SVGSVGElement) ? true : false;
		 		if(svgSupport){
		 			loadGrafica();    
			 	}else{
			 		$('#grafico_imagen').hide();
			 		$('#grafico_imagen_jpg').show();	
			    }
			}*/


			/*var chart;
			function loadGrafica(){

				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'column',
						borderWidth: 1,
						borderRadius: 1
					},
					title: {
						text: ''
					},
					xAxis: {
						categories: [<h:outputText value="#{facturacionFullController.facturacionFullBean.dataMeses}" />],
						labels: {
							rotation: -45,
							align: 'right',
							style: {
								 font: 'bold 9px Verdana, sans-serif'
							}
						}
					},
					yAxis: {
						allowDecimals: true,
						min: 0,
						title: {
							text: ''
						},
						gridLineDashStyle: 'ShortDash',
						labels: {
				            formatter: function() {
							   var numeroFormateado = formato_numero(this.value,1,",",".");
					           return numeroFormateado;		
				            },
				            style: {
				            	color: '#000000',
				            	font: 'bold 7px Verdana, sans-serif'
				            }
				        }
				        
					},					
					colors: ["#D5FFFE", "#99D2FF", "#295DFF"],
					legend: {
						enabled: false
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.x +'</b><br/>'+
								 this.series.name +': '+ this.y +'<br/>'+
								 'Total: '+ this.point.stackTotal;
						}
					},
					plotOptions: {
						column: {
							stacking: 'normal',
							borderWidth: 0,
							shadow: false
						},
				        series: {
				            pointWidth: 14
				        }
					},
					credits : {
       	               enabled : false
  					},
				    series: [{
						name: 'Otros servicios',
						data: [<h:outputText value="#{facturacionFullController.facturacionFullBean.dataOtros}" />]
					}, {
						name: 'Servicios adicionales',
						data: [<h:outputText value="#{facturacionFullController.facturacionFullBean.dataServiciosAdicionales}" />]
					}, {
						name: 'Servicio telefonia movil',
						data: [<h:outputText value="#{facturacionFullController.facturacionFullBean.dataTelefoniaMovil}" />]
					}]
				});
			}*/


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
	
	<!-- MENSAJES -->
	<jsp:include page="../common/messages_table.jsp"></jsp:include>
	
	<entel:view name="facturacionFull">
		<h:panelGroup rendered="#{(facturacionFullController.AAA == '2' && facturacionFullController.subMercado == 'NA') || facturacionFullController.AAA == '0'}">
		<script type="text/javascript">hideErrorDiv();</script>
	       <cm:search id="infoRestriccionAAA0" query="idContenido = 'infoRestriccionAAA0'" useCache="false"/>
			<div class="contenedor-mensajes">
			       <ul>
			        <li class="mensaje-alerta">
				        <div align="center"> 
				        	<span><cm:getProperty node="${infoRestriccionAAA0[0]}" name="html"/></span>
			            </div> 
			       </li>
			    </ul>
			</div>
	    </h:panelGroup>
		
		<h:panelGroup rendered="#{facturacionFullController.AAA == '3' || facturacionFullController.AAA == '1' || (facturacionFullController.AAA == '2' && facturacionFullController.subMercado == 'FDT')}">
			
			<h:panelGroup rendered="#{facturacionFullController.facturacionFullBean!=null}">
			
			<!-- CONTENIDO CENTRAL -->
	    
				<h1>Estado de facturaci&oacute;n</h1>
				
				<h2 class="facturacion">Facturaci&oacute;n&nbsp;
				<h:outputText value="#{facturacionFullController.facturacionFullBean.detalleFacturaMes.fechaPeriodoMes}">
						<f:convertDateTime pattern="MMMM yyyy" locale="es" />
					</h:outputText>  
				</h2>
	
				<div class="tabla">
	
					<div class="header_tabla_blanco clearfix">
						<div class="top"><span></span></div>
						<div class="main_blanco">
							<table>
								<tr>
									<th width="80%" class="ultimo">Total mes actual</th>
									<th width="20%" class="ultimo derecha">
									$ <h:outputText value="#{facturacionFullController.facturacionFullBean.detalleFacturaMes.totalActualMes}">
										<f:convertNumber currencyCode="CLP" locale="es" />
									</h:outputText>
									</th>
								</tr>
	
							</table>					
						</div>
						<div class="bottom_blanco"><span></span></div>
					</div>
					<div class="contenido_tabla borde">
						<table class="tablaFacturacion">
							<tbody>
								<tr>
									<td width="80%">Saldo anterior<div class="texto_gris"></div></td>
	
									<td width="20%" class="derecha">
									$ <h:outputText value="#{facturacionFullController.facturacionFullBean.detalleFacturaMes.saldoAnteriorMes}">
									<f:convertNumber currencyCode="CLP" locale="es" />
									</h:outputText></td>
								</tr>
							</tbody>
						</table>						
					</div>
					<div class="header_tabla_blanco clearfix">
						<!--<div class="top_blanco"><span></span></div>-->
						<div class="main">
							<table>
	
								<tr>
									<th width="80%" class="ultimo"><strong>Total</strong></th>
									<th width="20%" class="ultimo derecha"><strong>
									$ <h:outputText value="#{facturacionFullController.facturacionFullBean.detalleFacturaMes.totalPagoMes}">
									<f:convertNumber currencyCode="CLP" locale="es" />
									</h:outputText>
	</strong></th>
								</tr>
							</table>					
						</div>
						<div class="bottom"><span></span></div>
					</div>
	
				</div>
				
				<div class="pago_cuenta clearfix">
					<p class="parrafo">Per&iacute;odo de facturaci&oacute;n:&nbsp;
					<h:outputText value="#{facturacionFullController.facturacionFullBean.detalleFacturaMes.fechaPeriodoMes}">
						<f:convertDateTime pattern="MMMM yyyy" locale="es" />
					</h:outputText> 
					<br />
					Fecha de emisi&oacute;n:&nbsp;<h:outputText value="#{facturacionFullController.facturacionFullBean.detalleFacturaMes.fechaEmisionMes}">
						<f:convertDateTime pattern="dd MMMM yyyy" locale="es" />
					</h:outputText><br />
					Pagar hasta: <h:outputText value="#{facturacionFullController.facturacionFullBean.detalleFacturaMes.fechaVencimientoMes}">
					<f:convertDateTime pattern="dd MMMM yyyy" locale="es" />
					</h:outputText><br />
					Estado: <h:outputText value="#{facturacionFullController.facturacionFullBean.detalleFacturaMes.estadoMes}"></h:outputText>
					<br />
	
					</p>
					
					
				<!-- Muestra el boton de ir a pago de cuenta solo si no esta pagado -->
				<h:panelGroup rendered="#{facturacionFullController.facturacionFullBean.detalleFacturaMes.codEstadoMes == '0'}">
					<a class="btnVerdeGrande btnVerdeGrandeLargo boton_parrafo" 
					title="Realiza tu Pago en L&iacute;nea" 
					href="<r:pageUrl pageLabel='${facturacionFullController.pageLabelPagoEnLinea}'></r:pageUrl>"
					onclick="mxTracker._trackPromo('Boton','Ir a pago de cuenta');">
					<span>Ir a pago de cuenta</span>
					</a>
				</h:panelGroup>									

				</div>
				
				<h:panelGroup rendered="#{facturacionFullController.AAA=='3'}">
				
				<div class="detalles clearfix">
					<a href="javascript:abrirDetalleLlamados()" class="linkFacturaDetalle ver_detalle link-fact link_1" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mi Cuenta/Mis Boletas/Ver Factura-0');">Ver factura y detalle</a>
				</div>
				
				</h:panelGroup>
				
				<h:panelGroup rendered="#{facturacionFullController.AAA=='3'}">
				
				<h2 class="historico">Hist&oacute;rico &uacute;ltimos 6 meses</h2>
	
				
				<div class="header_tabla clearfix">
					<div class="top"><span></span></div>
					<div class="main">
						<table>
							<tr>
							    <th width="60">Emisi&oacute;n</th>
								<th width="80">Vencimiento</th>	
								<th width="65">Monto</th>
								<th width="75">Estado</th>
								<th width="90" class="ultimo">Factura y detalle</th>
							</tr>
						</table>					
					</div>
	
					<div class="bottom"><span></span></div>
				</div>
				<div class="contenido_tabla">
					<table class="tablaFacturacion">
						<tbody>					
							
							<it:iterator var="itemc" value="#{facturacionFullController.facturacionFullBean.documentosFacturasFull}" rowIndexVar="row">	
								
								<c:set var="style" value="#{row % 2 == 0 ? '': 'impar'}" scope="page" />
							    <tr class="<h:outputText value="#{style}"/>" >
									<td width="60"><h:outputText value="#{itemc.fechaEmision}"><f:convertDateTime pattern="MMMM yyyy" locale="es" /></h:outputText></td>
									<td width="80"><h:outputText value="#{itemc.fechaVencimiento}"><f:convertDateTime pattern="dd MMMM yyyy" locale="es" /></h:outputText></td>
									<td width="65">$ <h:outputText value="#{itemc.montoTotal}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></td>
									<td width="75"><h:outputText value="#{itemc.estado}" /></td>
                                  
									<td width="90">	
                                          <a href="javascript:theNewWin = window.open('','Entel','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, width=1000, height=665, top=20, left=140');abrirDetalleLlamadosItem('<h:outputText value="#{itemc.folio}" />','<h:outputText value="#{itemc.tipo}" />',theNewWin)" onClick="mxTracker._trackPageview('Personas/Mi Entel/Mi Cuenta/Mis Boletas/Ver Factura-<h:outputText value="#{row}" />');" class="linkFacturaDetalle icon-factura">Ver</a>
									</td>																																									
								</tr>
							</it:iterator>
							
						</tbody>
					</table>
					
					<!-- Parametros del contexto que identifican el detalle de llamados de un cliente para un mes especifico-->
					<input type="hidden" id="folioFromContexto" value="<h:outputText value="#{facturacionFullController.folioFromContexto}" />" />
					<input type="hidden" id="tipoDocFromContexto" value="<h:outputText value="#{facturacionFullController.tipoDocFromContexto}" />" />
					
				</div>	
				<!-- grafico -->	
				
				<br/>
				<br/>
				<center>
				<div class="db-tabla-cabecera">
					<div class="db-cabecera-top"></div>
					
                   <div class="db-tabla-cuerpo" style="background: url() !important">	
							   <div id="graficoFactura" class="db_grafico" style="border-top:1px none #CCCCCC !important">
									<span style="text-align:left;">&Uacute;ltimos 6 meses:</span>
									<div id="grafico" style="height: 140px; width: 225px"></div>
								</div>
								
								<div id="graficoFacturaImage" class="db_grafico" style="display:none">
									<span style="text-align:left;">&Uacute;ltimos 6 meses:</span>
									<div style="height: 140px; width: 225px">
										<h:graphicImage id="graficoImage" styleClass="graficoImage"></h:graphicImage>
									</div>
								</div>
				    </div>	
				<div class="db-tabla-pie"></div>
				
               </div>
               </center>
					<br/>
					
					 <!-- 
					<div class="grafico_leyenda">
						<div class="leyenda_item clearfix">
							<div class="leyenda_color">
								<div class="leyenda_color_1"></div>
							</div>
	
							<div class="leyenda_descripcion">
								<ul>
									<li>Otros servicios. Cobros / Descuentos Entel PCS y/o proveedores.</li>
								</ul>
							</div>
						</div>
						<div class="leyenda_item clearfix">
							<div class="leyenda_color">
	
								<div class="leyenda_color_2"></div>
							</div>
							<div class="leyenda_descripcion">
								<ul>
									<li>Servicios adicionales.</li>
									<li>Comunicaciones Larga Distancia Internacional.</li>
									<li>Comunicaciones a otros servicios p&uacute;blicos.</li>
	
								</ul>
							</div>
						</div>
						<div class="leyenda_item clearfix">
							<div class="leyenda_color">
								<div class="leyenda_color_3"></div>
							</div>
							<div class="leyenda_descripcion">
								<ul>
	
									<li>Servicio telefon&iacute;a m&oacute;vil ( Incluye Servicio de Voz, Mensajes, Datos y bolsas en caso de haberlas contratado )</li>
								</ul>
							</div>
						</div>										
					</div>
					 -->
				
				<!-- /grafico -->			            
	            </h:panelGroup>
	            <!-- Debes saber -->
				<div id="debes-saber" class="clearfix">
					<div class="img">
						<img alt="Debes saber" src="../framework/skins/mientel/img/debessaber.gif">
					</div>
					<div class="contenido">
						<p>El gráfico de arriba considera todos los ítemes de gasto cargados a su factura, a diferencia del gráfico incluido en su boleta de papel, que por norma excluye los Servicios Adicionales. </p>
					</div>
				</div>	
				<!-- /Debes saber -->
	    <!-- /CONTENIDO CENTRAL -->
	    
	    	</h:panelGroup>
	    
	    </h:panelGroup>	
	</entel:view>
	
	<entel:view name="detalleLlamados">
	<!-- centro -->
		<div id="centro">
			<h1>Detalle de llamados</h1>
		
			<h2 class="facturacion">Facturaci&oacute;n&nbsp;
				<h:outputText value="#{facturacionFullController.facturacionFullBean.detalleFacturaMes.fechaPeriodoMes}">
					<f:convertDateTime pattern="MMMM yyyy" locale="es" />
				</h:outputText>  
			</h2>
		
			<div class="detalles clearfix">
			    <a href="javascript:abrirDetalleLlamados()" class="linkFacturaDetalle ver_detalle link-fact link_1">Ver detalle de llamados</a>
			</div>			
			
			<h2 class="historico">Hist&oacute;rico &uacute;ltimos 6 meses</h2>	
			<div class="header_tabla clearfix">
				<div class="top"><span></span></div>
				<div class="main">
					<table>
						<tr>
							<td width="15%"></td>					   
							<th width="40%">Emisi&oacute;n</th>
							<th width="40%" style="padding:0px;0px;0px;0px;">Detalle de llamados</th>
							<td width="5%"></td>							
						</tr>
					</table>					
				</div>
				<div class="bottom"><span></span></div>
			</div>
			
			<div class="contenido_tabla">
				<table class="tablaFacturacion">
					<tbody>
						<it:iterator var="itemc" value="#{facturacionFullController.facturacionFullBean.documentosFacturasFull}" rowIndexVar="row">		
							<c:set var="style" value="#{row % 2 == 0 ? '': 'impar'}" scope="page" />					
							<tr class="<h:outputText value="#{style}"/>" >
								<td width="15%"></td> 
								<td width="40%">
									<h:outputText value="#{itemc.fechaEmision}"><f:convertDateTime pattern="MMMM yyyy" locale="es" /></h:outputText></td>
								<td width="40%">
									<a href="javascript:theNewWin = window.open('','Entel','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, width=1000, height=665, top=20, left=140');abrirDetalleLlamadosItem('<h:outputText value="#{itemc.folio}" />','<h:outputText value="#{itemc.tipo}" />',theNewWin)" onClick="mxTracker._trackPageview('Personas/Mi Entel/Mi Cuenta/Mis Boletas/Ver Factura-<h:outputText value="#{row}" />');" class="linkFacturaDetalle icon-llamados">Ver</a>
								</td>	
								<td width="5%"></td>																												
							</tr>					
						</it:iterator>																					
					</tbody>
				</table>						
			</div>			
		</div>
		<br />
		<!-- /centro -->	
	</entel:view>							
	<entel:view name="sinAutorizacionBAM">
         <div class="contenedor-mensajes"> 
	        <ul class="mensajes-lista">
	        	<h:outputText value="#{miEntelBean.accessDeniedDefaultMessage}" styleClass="mensaje-error" ></h:outputText>
	        </ul> 
		 </div>
	</entel:view>
</f:view>