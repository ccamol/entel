<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


			



<f:view beforePhase="#{historialBolsasController.initHistorialBolsas}">
	<it:iterator var="item" value="#{historialBolsasController.listaBolsasPP}" rowIndexVar="row">		
			
	
			
            <ul>
            	<li class="simplePagerPage">
            	<h:panelGroup layout="block" styleClass="item-trafico clearfix #{ (row % 2 == 0) and (row == fn:length(historialBolsasController.listaBolsasPP)-1) ? 'final' : '' } #{row % 2 == 0 ? '': 'impar'}">
                    	<div style="width:127px; text-align: center; height: 52px; padding-top: 10px;"><h:outputText value="#{item.descBolsacontratada}" /></div>
                        <div style="width:86px; text-align: center; height: 52px; padding-top: 10px;"><h:outputText value="#{item.precioOPuntosZonaEntel}" /></div>
                        <div style="width:104px; text-align: center; height: 52px; padding-top: 10px;"><h:outputText value="#{item.canalContratacion}" /></div>
                        <div style="width:84px; text-align: center; height: 52px; padding-top: 10px;"><h:outputText value="#{item.fechaContratacion}"><f:convertDateTime pattern="dd-MM-yyyy" locale="es" /></h:outputText></div>
                        <div style="width:86px; text-align: center; height: 52px; padding-top: 10px;"><h:outputText value="#{item.fechaEnvioSMS}" /></div>
                        <div style="width:64px; text-align: center; height: 52px; padding-top: 10px;">
                        
                        <div style="display:none" id="<h:outputText value="#{item.codigoRegistro}"/>">     
			                          <p><h:outputText value="#{item.textoSMSConfirmacion}" escape="true" /></p>
	                    </div>			            		
 						<a class="ico_interrogacionNuevo autoTooltip" href="#<h:outputText value="#{item.codigoRegistro}"/>" style="margin-left:20px; background:url(<%=request.getContextPath()%>/portlet/historico/sobre_c.gif); height: 18px" onclick="tooltipClick(this);"></a>
                        </div>
                    </h:panelGroup>
                </li>	
			</ul>
			
	 </it:iterator>
	 <h:panelGroup rendered="#{!historialBolsasController.sesionPag}">     
			noSesion
      </h:panelGroup>
	 
	 
	
</f:view>