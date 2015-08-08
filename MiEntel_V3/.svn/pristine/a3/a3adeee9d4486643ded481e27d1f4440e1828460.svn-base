<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>

<cm:search id="debesSaber" query="idContenido = 'debeSaberTraficoEnLinea'" useCache="true"  />

<f:view>

<script type="text/javascript">

   function loadTraficoEnLinea() {
    var url='<%=request.getContextPath()%>/portlet/trafico/traficoEnLineaJson.faces';
	
    setTimeout(function(){
  		 alertaReintento('traficoenlinea');    
   	      },TIEMPOREINTENTO);

    
    $('.rowsTable').html("<center><br><img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/></b><br><br></center>");
    
    $.ajax({
            type: 'POST',
            url: url,
            dataType: 'json',
            success: function (resp){
               //Evita reintento
	           flagRespuestas['traficoenlinea'] = '1'; 
               if(resp.estado == 'Ok'){ 
                var list = resp.respuesta;
				
                var htm = '';
                $.each(list, function(itemNo, item) {
                 var rowClass = (itemNo % 2 == 0) ? 'db-tabla-interna-item clearfix' : 'db-tabla-interna-item db_tabla_interna_color clearfix';  
                 
             	 htm += '<div class="'+rowClass+'">';
				 htm += '<div class="db-tabla-columna db_tabla_columna_1">'+item.destino+'</div>';
				 htm += '<div class="db-tabla-columna db_tabla_columna_2">&nbsp;'+item.tipoServicio+'</div>';
				 htm += '<div class="db-tabla-columna db_tabla_columna_3">'+item.fechaLlamada+'&nbsp;-&nbsp;'+item.horaLlamada+'</div>';
				 htm += '<div class="db-tabla-columna db_tabla_columna_4">'+item.duracion+'</div>';
				 htm += '<div class="db-tabla-columna db_tabla_columna_5">'+item.costo+'</div>';
				 htm += '<div class="db-tabla-columna db_tabla_columna_6">'+item.saldo+'</div>';
				 htm += '</div>';  
                });
				  
                
                $('.rowsTable').html(htm);
                $('#alerta-tabla-reintento-traficoenlinea').hide();
    			$('#loading-tabla-traficoenlinea').hide();
    			$('#contenido-tabla-traficoenlinea').fadeIn();
               }else{
           		$('#contenido-tabla-traficoenlinea').hide();
          	   	showErrorMessages('traficoenlinea',resp.detalle);
               }
            }
        });
}

   $(document).ready(function() {
	   loadTraficoEnLinea();
		$('#alerta-tabla-reintento-traficoenlinea').hide();
    	$('#loading-tabla-traficoenlinea').hide();
    	$('#contenido-tabla-traficoenlinea').fadeIn();	   
 	});
</script>

			
                    <!--TRAFICO EN LINEA-->
					<div class="db-tabla-grande db-tabla-espacio-vertical">
						<div class="db-tabla-grande-cabecera">
							<div class="db-cabecera-top"></div>
							<div class="db-cabecera-cuerpo clearfix">
								<div class="db-titulo db-titulo-trafico-pp">Tr&aacute;fico en l&iacute;nea</div>
								<div class="db-titulo-derecha">&Uacute;ltimos 5 movimientos realizados.</div>
								<div class="db-titulo-derecha">&nbsp;</div>
							</div>
						</div>
						<div class="db-tabla-grande-cuerpo">
							<div class="alerta-tabla-reintento" id="alerta-tabla-reintento-traficoenlinea"></div>
							<div id="loading-tabla-traficoenlinea"></div>
							<div id="contenido-tabla-traficoenlinea">
							<!-- <div class="mensajeServicio">
								<p>Este servicio no se encuentra disponible aún para esta versión <strong>BETA</strong> de MiEntel. Si necesitas acceder al servicio seleccionado puedes hacerlo en la <a href="<h:outputText value="#{traficoController.URLMiEntelV2}" />">versión actual</a>.</p>
								<p>Agradecemos puedas dejarnos un comentario que nos permita mejorar esta versión de MiEntel.</p>
								<p>Gracias por tu paciencia.</p>
							</div>    -->                      
                          
							<div class="db-tabla-grande-interna">
								<div class="db-tabla-interna-cabecera clearfix">
									<div class="db-tabla-columna db_tabla_columna_1">Destino o Emisión</div>
									<div class="db-tabla-columna db_tabla_columna_2">Tipo</div>
									<div class="db-tabla-columna db_tabla_columna_3">Fecha/Hora</div>
									<div class="db-tabla-columna db_tabla_columna_4">Duración</div>
									<div class="db-tabla-columna db_tabla_columna_5">Costo</div>
									<div class="db-tabla-columna db_tabla_columna_6">Saldo</div>
								</div>
								<div class="rowsTable"></div>
							</div>
							
							</div>
						</div>
						<div class="db-tabla-grande-pie"></div>

					<div>
						<br/>
					</div>		
							
				<!-- debes saber-->
					<div class="debes-saber trafico clearfix">
						<div class="contenido debeSaber"><cm:getProperty node="${debesSaber[0]}" name="html" /></div>
					</div>
				<!-- /debes saber-->								
					</div>
                    <!--/TRAFICO EN LINEA-->
	</f:view>