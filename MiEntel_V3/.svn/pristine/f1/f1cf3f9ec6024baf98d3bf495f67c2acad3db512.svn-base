<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences"	prefix="preferences"%>

<cm:search id="MisContratos_infoContratosFirmados" query="idContenido = 'MisContratos_infoContratosFirmados'" useCache="true"  />

<f:view beforePhase="#{contratosController.init}">
<script type="text/javascript">
var pagTotal = '1';

function loadListaContratos(numPagina){
	var url='<%=request.getContextPath()%>/portlet/contratos/ListaContratosFragment.faces';

	     $('#listaContratosFirmados').html("<tr><td colspan='4'><center><br><img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/></b><br><br></center></td></tr>");
	     
		 $.ajax({
	            type: 'POST',
	            url: url,
	            dataType: 'html',
	            data: {paginaActual:numPagina},
	            success: function (resp){
	            	$('#listaContratosFirmados').html(resp);
	           } 
        });
}

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

$(document).ready(function(){
	

	var numPagina = 1;
	pagTotal = '<h:outputText value="#{contratosController.totalPaginas}"></h:outputText>';

	$("#paginadorContratos").paginate({
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
		    loadListaContratos(currval);
			desactivarBotones(currval);
		}
	});

	$('.jPag-control-back, .jPag-control-front').css('opacity',0).css('cursor','default');

	if(pagTotal < 5){
		var ancho = (pagTotal * 30);	
		$('.ulwrapdiv').css('width',ancho+'px');
	}else{
		ancho=150;
	}

	$('.jPag-backk').click(function(){
		$('.jPag-current').parent().prev().children('a').trigger('click');
	});

	$('.jPag-nextt').click(function(){
		$('.jPag-current').parent().next().children('a').trigger('click');
	});
	var numPagina = 1;
	loadListaContratos(numPagina);
	desactivarBotones("1");
	ancho2 = ancho+180;
	setTimeout('anchopaginador(ancho2);',1000);
});



</script>
<style type="text/css">

    #listaContratosFirmados .impar{
        background-color: #EEF4FA;
    }
    
    #listaContratosFirmados .par .celda{
        border-bottom: 1px solid #E3E3E3;
    }
    
    #listaContratosFirmados .impar .celda{
        border-bottom: 1px solid #E3E3E3;
    }
    

    
</style>

<h1 class="bullet">Contratos</h1>
<div class="estructuraTrafico">
	<div class="clearfix">
        <p><cm:getProperty node="${MisContratos_infoContratosFirmados[0]}" name="html" /></p>		
	</div>

	<!-- Tabla Datos -->
	<div class="tabla">	
		<div class="header_tabla trafico clearfix">
			<div class="top" style="_margin-bottom: -6px !important;"><span style="_height: 8px !important;"></span></div>
			<div class="main" id="contratosTableHeader">
				<table>
					<tr>
						<th style="border-right: 1px solid #CDDAE7;" width="122">Fecha Creaci&oacute;n</th>
						<th style="border-right: 1px solid #CDDAE7;" width="115">Nr. de m&oacute;vil </th>
						<th style="border-right: 1px solid #CDDAE7;" width="214">Nombre Documento</th>
                        <th style="border-right: 1px solid #CDDAE7;" width="132">Folio</th>
                        <th style="border-right: 1px solid #CDDAE7;" width="50"></th>
					</tr>					
				</table>					
			</div>
			<div class="bottom" style="_margin-top: -6px !important;"><span style="_height: 8px !important;"></span></div>
		</div>
		<div class="contenido_tabla">
				    <h:panelGroup rendered="#{!contratosController.error}">
				        <table>
				            <tbody id="listaContratosFirmados"></tbody>
				            <h:panelGroup rendered="#{contratosController.numeroRegistros > 20}">
							    <tfoot>
							        <tr>
							            <td colspan="4">
							              <div><br/></div>
									      <div id="paginadorContratos"></div>
							            </td>
							        </tr>
							    </tfoot>
						    </h:panelGroup>
			            </table>
				    </h:panelGroup>
				    <h:panelGroup rendered="#{contratosController.error}">
				        <table>
							<tr>
								<td colspan="4">
									<div class="alerta-tabla-mensaje" style="font-size: 1.2em; width: 544px;">
									    <h:outputText value="#{contratosController.errorMessage}" escape="false"/>
									</div>
								</td>
							</tr>
						</table>
                    </h:panelGroup>                                    
		</div>
	</div>
    <!--/tabla-->	
						
</div>

</f:view>
