<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<f:view beforePhase="#{traficoEnLineaController.initDetalleTraficoEnLineaPPCC}">
	<it:iterator var="item" value="#{traficoEnLineaController.detalleTraficoEnLineaPPCC}" rowIndexVar="row">		
            <ul>
            	<li class="simplePagerPage">
            	<h:panelGroup layout="block" styleClass="item-trafico clearfix #{ (row % 2 == 0) and (row == fn:length(traficoEnLineaController.detalleTraficoEnLineaPPCC)-1) ? 'final' : '' } #{row % 2 == 0 ? '': 'impar'}">
                    	<div class="tipo"><h:outputText value="#{item.tipoServicio}" /></div>
                        <div class="destino"><h:outputText value="#{item.destino}" /></div>
                        <div class="fecha"><h:outputText value="#{item.fechaLlamada}"/> - <h:outputText value="#{item.horaLlamada}"/></div>
                        <div class="duracion"><h:outputText value="#{item.duracion}" /></div>
                        <div class="unidad"><h:outputText value="#{item.unidad}" /></div>
                        <div class="costo"><h:outputText value="#{item.costo}" /></div>
                        <div class="saldo"><h:outputText value="#{item.saldo}" /></div>
                    </h:panelGroup>
                </li>	
			</ul>
	</it:iterator>
	
</f:view>
