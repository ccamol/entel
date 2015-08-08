<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<f:view beforePhase="#{bloqueoDesbloqueoEquipoBAMController.consultarHistoricoEquipos}">
	
	<h:panelGroup rendered="#{bloqueoDesbloqueoEquipoBAMController.renderedListaTraficoEquipos}">
		<it:iterator var="item" value="#{bloqueoDesbloqueoEquipoBAMController.listaTraficoEquipos}" rowIndexVar="row">
	                	<div class="lista">
	                        <h:panelGroup layout="block" styleClass="equipo clearfix #{row % 2 == 0 ? '': 'impar'}">
	                            <div class="nombre"><h:outputText value="#{item.descripcionEquipo}"/></div>
	                            <div class="radio">
	                                <input type="radio" name="equipo_bloqueo" id="equipo_bloqueo_<h:outputText value="#{row+1}"/>"  
	                                value="<h:outputText value="#{item.imsi}"/>*
	                                <h:outputText value="#{item.imei}"/>*
	                                <h:outputText value="#{item.descripcionEquipo}"/>" />
	                            </div>
	                        </h:panelGroup>
	                    </div>
		</it:iterator>
	</h:panelGroup>
	
	<h:panelGroup rendered="#{!bloqueoDesbloqueoEquipoBAMController.renderedListaTraficoEquipos}">
       		<div class="listaVacia" style="display:block">
           		<div class="equipo clearfix">
                   <div class="nombre">
                       El n&uacute;mero m&oacute;vil no registra tr&aacute;fico en otros equipos durante los &uacute;ltimos 40 d&iacute;as.
                   </div>
               </div>
           </div>
	</h:panelGroup>

</f:view>