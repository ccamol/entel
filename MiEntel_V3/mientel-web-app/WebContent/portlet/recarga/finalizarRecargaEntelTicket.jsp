<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>

<f:view>
<cm:search id="marcaRecargas" query="idContenido = 'marcaRecargas'" useCache="true"  />
<script type="text/javascript">
$(document).ready(function() {
	crearMarcaTransaccionGTM();
});

function crearMarcaTransaccionGTM() {
	var idTransaccion = '<h:outputText value="#{recargaController.facibilidadEntelticketBean.codUnicoTransaccion}" />';
	var montoRecarga = '<h:outputText value="#{recargaController.facibilidadEntelticketBean.montoDispTicket}" />';
	var nombreProducto = 'Recarga_' + montoRecarga;
	var esVozBAM = "<h:outputText value="#{profile.flagBam == miEntelBean.siglaUsuarioBAM ? 'Banda Ancha Movil' : 'Telefonia'}"/>";

	dataLayer = dataLayer||[];
	dataLayer.push({
		'mx_content': 'Personas/Mi Entel/Mis Productos/' + esVozBAM + '/Recargas/Entelticket/Conversion',
		'event': 'pageview'
	});

	dataLayer.push({
		'transactionId': idTransaccion, 'sku': 'Entelticket', 'price': montoRecarga,
		'event': 'addTrans'
	});

	dataLayer.push({
		'transactionId': idTransaccion, 'sku': nombreProducto, 'name': nombreProducto,
		'category': 'Recargas', 'price': montoRecarga, 'quantity': '1',
		'event': 'addItem'
	});
	
	dataLayer.push({'event': 'tracktrans', 'tracktrans': true});
}	
</script>
<h2 style="padding-left: 0;">Entelticket</h2>

<p><strong>Paso 3 de 3</strong></p>

<!-- Mensaje Verde -->
<div class="mensaje_verde">
    <strong>La recarga se ha realizado</strong><br />
    Un mensaje de texto confirm&aacute;ndola llegar&aacute; a tu m&oacute;vil

</div>
<!-- /Mensaje Verde -->
<div id="marca-adwords"><cm:getProperty node='${marcaRecargas[0]}' name='html'/></div>
<div class="caja_datos">
    <div class="tabla_formulario_fila clearfix">
        <div class="tabla_formulario_label">N&uacute;mero Entel recargado:</div>
        <div class="tabla_formulario_label"><strong><h:outputText value="#{recargaController.facibilidadEntelticketBean.numeroPcs}" /></strong></div>
    </div>
    <div class="tabla_formulario_fila clearfix">

        <div class="tabla_formulario_label">Monto cargado:</div>
        <div class="tabla_formulario_label"><strong>
        
        $<h:outputText value="#{recargaController.facibilidadEntelticketBean.montoDispTicket}">        	
        	<f:convertNumber currencyCode="CLP" locale="es" />
        </h:outputText>
        
        </strong></div>
    </div>
    <div class="tabla_formulario_fila clearfix">
        <div class="tabla_formulario_label">Fecha y hora:</div>
        <div class="tabla_formulario_label"><strong><h:outputText value="#{recargaController.fechaActual} hrs" /></strong></div>
    </div>

    <div class="tabla_formulario_fila clearfix">
        <div class="tabla_formulario_label">V&aacute;lido hasta:</div>
        <div class="tabla_formulario_label"><strong><h:outputText value="#{recargaController.facibilidadEntelticketBean.validezRecarga} dias" /></strong></div>
    </div>
    <div class="tabla_formulario_fila clearfix">
        <div class="tabla_formulario_label">C&oacute;digo de autorizaci&oacute;n:</div>

        <div class="tabla_formulario_label"><strong><h:outputText value="#{recargaController.facibilidadEntelticketBean.codUnicoTransaccion}" /></strong></div>
    </div>
</div>
	<h:panelGroup rendered="#{profile.mercado eq miEntelBean.siglaPrepago or profile.mercado eq miEntelBean.siglaCuentaControlada or profile.flagBam eq 1}">
		<br />
		<div class="clearfix">    
			<p style="float: left; margin: 17px 10px 0px 150px;" class="alinearVerBolsaIE">¿Necesitas comprar una bolsa?</p>
			<a class="botonVerBolsaNaranja" href="<r:pageUrl pageLabel='${recargaController.pageLabelBolsas}'></r:pageUrl>"></a>
		</div>	
	</h:panelGroup>

</f:view>