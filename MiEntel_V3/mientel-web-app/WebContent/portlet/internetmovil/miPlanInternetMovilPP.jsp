<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it"  uri="/WEB-INF/tld/IteratorTag.tld"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<f:view beforePhase="#{internetMovilController.initPP}">
<cm:search id="marcaBolsasPPIMVoz" query="idContenido = 'marcaBolsasPPIMVoz'" useCache="true"  />

<script type="text/javascript">
function contratarBolsa(link,codBolsa,valor,nombre){
        var url='<%=request.getContextPath()%>/portlet/internetmovil/miPlanInternetMovilJson.faces';
        
        //Mostrar loading
        var tr = $('.link'+link).parents(".lista-bolsas:first");
        tr.find('.paso_2').html("<img src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;<b>Enviando...</b>");
        tr.find('.paso_2').show();      

	    $.ajax({
	            type: 'POST',
	            url: url,
	            async: false, 
	            dataType: 'json',
	            data: {accion:'comprar',cartaServicio:codBolsa,valorBolsa:valor,nombreBolsa:nombre},
	            success: function (resp){
	             if(resp.estado == 'Ok'){
	            	 tr.find('.paso_2').hide();
		             //tr.find('.paso_3').html("<h5>Haz cambiado a este plan.</h5><p>"+resp.respuesta+"</p>");
		             tr.find('.paso_3').show();
		             
		             crearMarcaTransaccionGTM(resp);
                 }else{
                	 $('.mensajeError'+link).html(resp.detalle);
                	 $('.mensajeErrorP'+link).show();
                     tr.find('.paso_2').hide();
                     }
	            }
	        });
}

function crearMarcaTransaccionGTM(resp) {
	var idTransaccion = resp.respuesta.idTransaccion;
	var codigoProducto = resp.respuesta.skuID;
	var nombreProducto = resp.respuesta.nombre;

	mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Internet movil/Compra tu bolsa/Conversion');

	mxTracker._addTrans(idTransaccion, '', resp.respuesta.valorTransaccion);
	mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Fee tarifa', resp.respuesta.nuevoValor, '1');
	mxTracker._addItem(idTransaccion, codigoProducto, nombreProducto, 'Costo cambio tarifa', resp.respuesta.costoOperacional, '1');

	dataLayer = dataLayer||[];
	dataLayer.push({'event': 'tracktrans', 'tracktrans': true});
}

</script>
<h1>Mi plan</h1>
<div id="marca-adwords"></div>
			<h:panelGroup rendered="#{! (empty internetMovilController.bolsasActualesPP)}">
			<!-- ESTRUCTURA TARIFARIA -->
			<div class="estructuraTarifaria">
				<h2 class="superchip">
					<strong>Mi Plan:</strong>
                    <it:iterator value="#{internetMovilController.bolsasActualesPP}" var="bolsaAct" rowIndexVar="bolsaIndex">
                    <h:outputText rendered="#{bolsaIndex == 0}" value="#{bolsaAct.nombreBolsa}"/>
                    </it:iterator>
				</h2>
				
				<!-- Tabla Tarificacion -->
				<div class="header_tabla clearfix">
					<div class="top"><span></span></div>
					<div class="main">
						<table>
							<tr>
								<th width="110">Plan</th>

								<th width="110">Precio plan</th>
								<th width="110" class="ultimo">MB Incluidos</th>
							</tr>
						</table>					
					</div>
					<div class="bottom"><span></span></div>
				</div>
				<div class="contenido_tabla">

					<table class="tablaFacturacion">
						<tbody>
						<it:iterator value="#{internetMovilController.bolsasActualesPP}" var="bolsaActual">
							<tr>
								<td width="110"><h:outputText value="#{bolsaActual.nombreBolsa}"/></td>
								<td width="110">$<h:outputText value="#{bolsaActual.valorBolsa}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></td>
								<td width="110"><h:outputText value="#{bolsaActual.cantidadBolsa}"/></td>
							</tr>
                        </it:iterator>
						</tbody>
					</table>
				</div>
			</div>
			<div class="disclaimer">
				Debes mantener un saldo superior a $7 para usar estos planes.
			</div>		
			<!-- /ESTRUCTURA TARIFARIA -->
			</h:panelGroup>
			
			<h:panelGroup rendered="#{(empty internetMovilController.bolsasActualesPP)}">
			<!-- ESTRUCTURA TARIFARIA -->
			<div class="estructuraTarifaria">
				<h2 class="superchip">
					Sin plan
				</h2>
				<cm:search id="nodoAvisoNoPlan" query="idContenido = 'condGenContratar'" useCache="true"  />
				<!-- Aviso no plan -->
				<div class="aviso_no_plan clearfix">
				    <cm:getProperty node="${nodoAvisoNoPlan[0]}" name="html" />
				</div>
			</div>
			<!-- /ESTRUCTURA TARIFARIA -->
			</h:panelGroup>
			
			<div class="separacion_planes"></div>

			<h2 class="superchipOK"><h:outputText value="#{(empty internetMovilController.bolsasActualesPP) ? 'Compra tu plan' : 'Cambio de plan'}"/></h2>
			
			<p>Selecciona de la siguiente lista de planes que tenemos para ti:</p>
			
						<h:panelGroup rendered="#{! (empty internetMovilController.bolsasDispRegalo)}">	
			<div id="menu-desplegable-planes"><!-- bolsa -->
			<it:iterator value="#{internetMovilController.bolsasDispRegalo}" var="bolsaDispo" rowIndexVar="bolsaIndex">
<div class="bolsa <h:outputText value="#{bolsaIndex % 2 == 0 ? 'par' : 'impar'}"/> clearfix">
	<div class="header">
		<a href="javascript:;" class="cerrado" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Internet movil/Compra tu bolsa/Desplegar');"><h:outputText value="#{bolsaDispo.nombreBolsa}"/></a>

	</div>
	<div style="display: none;" class="lista-bolsas" id="red">
	
		<div class="tabla_precios">
		
			<div class="planes_header_tabla clearfix">
				<div class="top"><span></span></div>
				<div class="main">
					<table>
						<tbody><tr>
							<th width="120" class="primera">Nombre del plan</th>
							<th width="70">Precio Plan</th>
							<th width="70">MB Incluidos</th>
							<th width="150" class="ultimo">&nbsp;</th>
						</tr>
					</tbody></table>					
				</div>
				<div class="bottom"><span></span></div>
			</div>
			
			<table class="planes_contabla">
				<tbody>
					<tr>
						<td width="120" class="primera"><h:outputText value="#{bolsaDispo.nombreBolsa}"/></td>
						<td width="70">$<h:outputText value="#{bolsaDispo.costo}">
						<f:convertNumber currencyCode="CLP" locale="es" />
	 					</h:outputText></td>
						<td width="70"><h:outputText value="#{bolsaDispo.cantidad}">
						                <f:convertNumber currencyCode="CLP" locale="es" />
						                </h:outputText></td>
						<td width="150"><a class="<h:outputText value="#{(empty internetMovilController.bolsasActualesPP) ? 'btnVerdeDelgado alargar' : 'btnCambiarmePlan'}"/> cambiarPlan" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Internet movil/Compra tu bolsa/Comprar');"><span>Comprar</span></a></td>
					</tr>
				</tbody>
			</table>
			<div class="disclaimer">
				 <it:iterator value="#{bolsaDispo.descBolsa}" var="strDescBolsa">
				 <h:outputText escape="false" value="#{strDescBolsa}"/>
                 </it:iterator>
			</div>
		</div><!--/tabla_precios-->
		
		<div class="paso_2">
			<div class="nombre_plan">
				<div class="nombre_plan_top"><span></span></div>
				<div class="nombre_plan_main">Nombre del plan</div>
				<div class="nombre_plan_bottom"><span></span></div>
			</div>
			<div class="paso_confirmar clearfix">
				<span class="plan"><h:outputText value="#{bolsaDispo.nombreBolsa}"/></span>
				<span>Vas a <h:outputText value="#{(empty internetMovilController.bolsasActualesPP) ? 'comprar' : 'cambiar a'}"/> este plan</span>
				<a class="cancelar" href="#">Cancelar</a>
				<a onclick="contratarBolsa('<h:outputText value="#{bolsaIndex}"/>','<h:outputText value="#{bolsaDispo.observacion}"/>','<h:outputText value="#{bolsaDispo.costo}"/>','<h:outputText value="#{bolsaDispo.nombreBolsa}"/>');" class="btnVerdeDelgado link<h:outputText value="#{bolsaIndex}"/>" href="javascript:void(0);"><span>Confirmar</span></a>
			</div>
			<div class="disclaimer">
				 <it:iterator value="#{bolsaDispo.descBolsa}" var="strDescBolsa">
				 <h:outputText escape="false" value="#{strDescBolsa}"/>
                </it:iterator>
			</div>
		</div><!--/paso_2-->
		
		<div class="paso_3">
			<div class="nombre_plan">
				<div class="nombre_plan_top"><span></span></div>
				<div class="nombre_plan_main">Nombre del plan</div>
				<div class="nombre_plan_bottom"><span></span></div>
			</div>
			<div class="paso_confirmar clearfix">
				<span class="plan"><h:outputText value="#{bolsaDispo.nombreBolsa}"/></span>
				<div>
					<h5>Has cambiado a este plan</h5>
					<div>
						- Tu solicitud ha sido recibida.
						<br>
						- El cambio se efectuará el día de mañana.
					</div>
				</div>
			</div>
			<div class="disclaimer">
				 <it:iterator value="#{bolsaDispo.descBolsa}" var="strDescBolsa">
				 <h:outputText escape="false" value="#{strDescBolsa}"/>
                 </it:iterator>
			</div>
		</div><!--/paso_3-->
	</div>
</div>
  <div class="mensaje-error-pequeno mensajeErrorP<h:outputText value="#{bolsaIndex}"/>" style="display:none;">
                <div class="clearfix sub-contenedor">
                  <div class="contenedor-imagen">
                     <div class="imagen"></div>
                  </div>
                 <div class="texto mensajeError<h:outputText value="#{bolsaIndex}"/>"></div>
                </div>
     </div>
<!-- /bolsa -->
</it:iterator>
<!-- /bolsa -->
</div>
			<script type="text/javascript">
				$(document).ready(function(){
					initMenuDesplegablePlanes();
				});
			</script>
</h:panelGroup>
<h:panelGroup rendered="#{empty (internetMovilController.bolsasDispRegalo)}">
<div class="mensaje-alerta-sistema-pequeno">
<div class="clearfix sub-contenedor">
<div class="contenedor-imagen">
<div class="imagen"></div>
</div>
<div class="texto">No hay Planes Disponibles.</div>
</div>
</div>
</h:panelGroup>	
</f:view>
