<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<cm:search id="infoAlertaCuotaTrafico" query="idContenido = 'infoAlertaCuotaTrafico'" useCache="true"  />
 

<f:view beforePhase="#{traficoEnLineaController.initHistoricoAlertaCuotaTrafico}">
<script type="text/javascript">


var pagTotal;
	$(document).ready(function(){
		var numPagina = 1;
		pagTotal = '<h:outputText value="#{traficoEnLineaController.paginasTotalACT}"></h:outputText>';	
		$("#paginadorTrafico").paginate({
			count 		: pagTotal,
			start 		: numPagina,
			display     : 5,
			border					: false,
			text_color  			: '#3a3a3a',
			background_color    	: 'none',	
			text_hover_color  		: '#2573AF',
			background_hover_color	: 'none', 
			rotate		: false,
			images		: false,
			mouse		: 'press',
			onChange: function(currval){				
				loadTableHistorialAlertaCuotaTrafico(currval);
				desactivarBotones(currval);
			}
		});

		$('.jPag-control-back, .jPag-control-front').css('opacity',0).css('cursor','default');

		
		if(pagTotal < 5){
			var ancho = (pagTotal * 30);	
			$('.ulwrapdiv').css('width',ancho+'px');
			
			
			//$('.jPag-control-front').attr('style','left:'+ancho+'px !important;');
		}else{
			ancho=150;
			//$('.jPag-control-front').css('left',248+'px !important');
		}
		
		
		$('.jPag-backk').click(function(){
			$('.jPag-current').parent().prev().children('a').trigger('click');
		});

		$('.jPag-nextt').click(function(){
			$('.jPag-current').parent().next().children('a').trigger('click');
		});

		
		loadTableHistorialAlertaCuotaTrafico(numPagina);
		desactivarBotones("1");
		ancho2 = ancho+180;
		setTimeout('anchopaginador(ancho2);',1000);
	});
	var ancho2;
	function anchopaginador(ancho){
		$('.jPaginate').css('width',ancho+'px').css('margin','0 auto');
	}
	
	var lastIndex;
	function desactivarBotones(actual){

		if(actual==pagTotal){
			$('.jPag-control-front').css('opacity',0).children('a').css('cursor','default');
			
		}else{
			$('.jPag-control-front').css('opacity',1).children('a').css('cursor','pointer');
		}
		if(actual==1){
			$('.jPag-control-back').css('opacity',0).children('a').css('cursor','default');
		}else{
			$('.jPag-control-back').css('opacity',1).children('a').css('cursor','pointer');
		}
	}
	function loadTableHistorialAlertaCuotaTrafico(numPagina){
    	
		var url='<%=request.getContextPath()%>/portlet/trafico/detalleAlertaCuotaTraficoJson.faces';

		     $('.tableAlertaCuotaTrafico').html("<center><br><img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/></b><br><br></center>");
		     
			 $.ajax({
		            type: 'POST',
		            url: url,
		            dataType: 'html',
		            data: {numPagina:numPagina},
		            success: function (resp){
		            	$('.tableAlertaCuotaTrafico').html(resp);
		           } 
	        });

	}

	function moverTableTraficoEnLinea(element){
		
		if($(element).hasClass('desactivado')){
			return false;
		}
			
		if($(element).hasClass('back')){
			loadTableHistorialAlertaCuotaTrafico(lastIndex-1);
		}else{
			loadTableHistorialAlertaCuotaTrafico(lastIndex+1);		
		}
	}

	

	
</script>

<!-- ESTRUCTURA DE TRAFICO -->
</br>
<div class="contenido_alerta_cuota_trafico" ><cm:getProperty node="${infoAlertaCuotaTrafico[0]}" name="html" /></div>

<!-- MENSAJES -->
<jsp:include page="../common/messages_table.jsp"></jsp:include>

<div class="estructuraTrafico">

	<h1>
		<strong><cm:getProperty node="${infoAlertaCuotaTrafico[0]}" name="titulo" /></strong>
	</h1>
					<div class="tabla tabla-trafico">
   					 <h:form> 
   					  	<jsp:include page="/token.jsp" flush="true"/>
						<div  class="icono-descarga">
						<h:panelGroup rendered="#{!traficoEnLineaController.errorAlertaCuotaTrafico}">
							<a class="icono-descarga_act" href="/personas/historialACTXLS">Descargar en formato excel</a>
						</h:panelGroup>
						</div>
							
							<h:panelGroup rendered="#{!traficoEnLineaController.errorAlertaCuotaTrafico}">
								<div class="header_tabla trafico clearfix">
									<div class="top"><span></span></div>
									<div class="main">
									
										<table>
											<tr class="titulos">
												<th width="65%"><strong>Alerta de cuota enviada por SMS</strong></th>
												<th width="35%" class="ultimo"><strong>Fecha y Hora env&iacute;o de SMS</strong></th>
											</tr>
										</table>	
													
									</div>
									<div class="bottom"><span></span></div>
								</div>
								</h:panelGroup>	
							
															
									<div class="contenido_tabla align_left">
											<h:panelGroup rendered="#{traficoEnLineaController.errorAlertaCuotaTrafico}">
													<div class="mensaje-alerta-sistema-pequeno"> 
										       			<div class="clearfix sub-contenedor"> 
										           			<div class="contenedor-imagen" style="width:5%;"> 
										           				<div class="imagen" ></div> 
										           			</div> 
										           			<div class="texto" style="width:90%;font-size: 12px;"><h:outputText value="#{traficoEnLineaController.errorMessage}" /></div> 
										       			</div> 
										   			</div>
										   			<jsp:include page="../common/messages_table.jsp"></jsp:include>
											</h:panelGroup>
											
											<h:panelGroup rendered="#{!traficoEnLineaController.errorAlertaCuotaTrafico}">
													
													<table class="tableAlertaCuotaTrafico">
											
										            </table>
											            
											            
													<div><br/></div>
													<div id="paginadorTrafico"></div>
													
											</h:panelGroup>
											
											
									</div>
					</h:form>			
					</div>
					<!--/tabla-->	
						
</div>
					<!-- /ESTRUCTURA DE TRAFICO -->	                                        						
				
</f:view>	