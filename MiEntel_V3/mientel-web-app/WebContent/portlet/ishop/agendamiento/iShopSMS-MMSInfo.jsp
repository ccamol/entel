<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>

<f:view beforePhase="#{iShopSMSMMSInfoController.init}">

	<cm:search id="iShopAgenSMS" query="idContenido = 'iShopAgenSMS'" useCache="true" />
	<cm:search id="iShopAgenSMSVerMas" query="idContenido = 'iShopAgenSMSVerMas'" useCache="true" />
	<cm:search id="iShopAgenMMS" query="idContenido = 'iShopAgenMMS'" useCache="true" />
	<cm:search id="iShopAgenMMSVerMas" query="idContenido = 'iShopAgenMMSVerMas'" useCache="true" />	

	<script type="text/javascript">
		function openPage(tipo, id, descripcion) {
			var url = '<h:outputText value="#{iShopSMSMMSInfoController.formAction}" />';

			if (tipo == 'SMS') {
				var pars = '&contenidoMMS=&contenidoSMS=' + id + '&descripcionContenidoSMS=' + descripcion;
			} else {
				var pars = '&contenidoMMS=' + descripcion + '&contenidoSMS=&descripcionContenidoSMS=';
			}
			
			window.open(url + pars, 'target=_blank');
		}
	</script>
	
	<div class="ishop">
		<h1>SMS-MMS info</h1>
		
		<div id="pasos" class="margen">SMS Info</div>
		<div class="linea-azul"></div>
		
		<cm:getProperty node="${iShopAgenSMS[0]}" name="titulo" />
		<cm:getProperty node="${iShopAgenSMS[0]}" name="html" />

		<div class="bloque-gris">
			<cm:getProperty node="${iShopAgenSMSVerMas[0]}" name="html" />
		</div>
		
		<div class="listado_sms_mms clearfix">		
			<ul>
				<it:iterator var="cont" value="#{iShopSMSMMSInfoController.contenidoSMS}">
					<li><a href="#" onclick="javascript:openPage('SMS','<h:outputText value="#{cont.id}" />','<h:outputText value="#{cont.descripcion}" />');"><h:outputText value="#{cont.descripcion}" /></a></li>
				</it:iterator>
			</ul>
		</div>
						
		<div id="pasos" class="margen">MMS Info</div>
		<div class="linea-azul"></div>
		
		<cm:getProperty node="${iShopAgenMMS[0]}" name="titulo" />
		<cm:getProperty node="${iShopAgenMMS[0]}" name="html" />

		<div class="bloque-gris">
			<cm:getProperty node="${iShopAgenMMSVerMas[0]}" name="html" />
		</div>
		
		<div class="listado_sms_mms clearfix">
			<ul>
				<it:iterator var="cont" value="#{iShopSMSMMSInfoController.contenidoMMS}">
					<li><a href="#" onclick="javascript:openPage('MMS','<h:outputText value="#{cont.id}" />','<h:outputText value="#{cont.descripcion}" />');"><h:outputText escape="false" value="#{cont.descripcion}" /></a></li>
				</it:iterator>
			</ul>
		</div>
	</div>
</f:view>