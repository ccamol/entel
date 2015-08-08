<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>

<f:view beforePhase="#{contratosController.initPageData}">
    <h:panelGroup rendered="#{!contratosController.error}">
	<it:iterator var="item" value="#{contratosController.listaContratos}" rowIndexVar="row">
						<tr class="<h:outputText value="#{(row % 2 == 0)?'par':'impar'}" />">
							<td class="celda" width="122"><h:outputText value="#{item.fechaDocumento}" /></td>
							<td class="celda" width="115"><h:outputText value="#{item.movil}"/></td>
							<td class="celda" width="214"><h:outputText value="#{item.nombreDocumento}"/></td>
							<td class="celda" width="132"><h:outputText value="#{item.numeroDocumento}"/></td>
							<td class="celda" width="50">							
							    <h:panelGroup layout="block" rendered="#{!(item.url eq null) and !(item.url eq '')}">
							     <a href="<h:outputText value="#{item.url}"/>" target="_blank" class="btnAzulDelgado">
	                                <span> Ver </span>
	                            </a>
							    </h:panelGroup>

	                       </td>
						</tr>
	</it:iterator>
	</h:panelGroup>
    <h:panelGroup rendered="#{contratosController.error}">
		<tr>
			<td colspan="4">
				<div class="alerta-tabla-mensaje" style="font-size: 1.2em; width: 544px;">
				    <h:outputText value="#{contratosController.errorMessage}" escape="false"/>
				</div>
			</td>
		</tr>
   </h:panelGroup>
</f:view>