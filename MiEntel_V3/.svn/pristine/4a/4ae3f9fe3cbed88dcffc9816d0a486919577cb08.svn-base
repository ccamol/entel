<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>






<f:view beforePhase="#{historialBolsasController.initHistorialBolsas}">
	<it:iterator var="item" value="#{historialBolsasController.detalleRecargasPP}" rowIndexVar="row">		
			
	
			
            <ul>
            	<li class="simplePagerPage">
            	<h:panelGroup layout="block" styleClass="item-trafico clearfix #{ (row % 2 == 0) and (row == fn:length(historialBolsasController.detalleRecargasPP)-1) ? 'final' : '' } #{row % 2 == 0 ? '': 'impar'}">
                    	<div style="width:23%; height:12px; padding-top:0px; padding:12px 0px 12px 11px; vertical-align: top; font-size: 1.2em; margin:0px;"><h:outputText value="#{item.fechaRecarga}"><f:convertDateTime pattern="dd-MM-yyyy" locale="es" /></h:outputText></div>
                        <div style="width:13%; height:12px; padding-top:0px; padding:12px 0px 12px 11px; vertical-align: top; font-size: 1.2em; margin:0px;"><h:outputText value="#{item.fechaRecarga}"><f:convertDateTime pattern="HH:mm" locale="es" /></h:outputText></div>
                        <div style="width:33%; height:12px; padding-top:0px; padding:12px 0px 12px 11px; vertical-align: top; font-size: 1.2em; margin:0px;"><h:outputText value="#{item.plataformaRecarga}#{item.tipoRecarga}"></h:outputText></div>
                        <div style="width:23%; height:12px; padding-top:0px; padding:12px 0px 12px 11px; vertical-align: top; font-size: 1.2em; margin:0px;">$ <h:outputText value="#{item.montoRecarga}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></div>                        
                    </h:panelGroup>
                </li>	
			</ul>
			
	 </it:iterator>
	 <h:panelGroup rendered="#{!historialBolsasController.sesionPag}">     
			noSesion
      </h:panelGroup>
	 
	 
	
</f:view>