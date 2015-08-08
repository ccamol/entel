<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<f:view beforePhase="#{traficoEnLineaController.initDetalleHistoricoAlertaCuotaTrafico}">
	<it:iterator var="item" value="#{traficoEnLineaController.detalleAlertaCuotaTrafico}" rowIndexVar="row">
	 <c:set var="style" value="#{ (row % 2 == 0) and (row == fn:length(traficoEnLineaController.detalleAlertaCuotaTrafico)-1) ? 'final' : '' } #{row % 2 == 0 ? '': 'impar'}" scope="page" />					
		<tr class="<h:outputText value="#{style}"/> item-alerta-cuota">
			<td width="65%" class="detalle" ><h:outputText value="#{item.txtSMS}" /></td>
			<td width="35%" class="fecha"><h:outputText value="#{item.fechaEnvioSMSFormat}" /></td>
        </tr>   
	</it:iterator>	
</f:view>
