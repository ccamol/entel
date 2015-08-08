<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>

<cm:search id="infoDebesSaber" query="idContenido = 'historico_recargas'" useCache="true"  />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

	<h:panelGroup rendered="#{recargaHistoricoController.recargaHistoricoBean!=null}">
	
		 <h:panelGroup rendered="#{recargaHistoricoController.existeHistoricoRecargas}">
               
			<h1>Hist&oacute;rico de recargas</h1>
			
                <div class="header_tabla_historico clearfix">
                    <div class="top"><span></span></div>
                    <div class="main">
                        <table>
                            <tbody>
	                            <tr>
	                                <th width="25%">Fecha</th>
	                                <th width="15%">Hora</th>
	                                <th width="35%">Medio</th>
	                                <th width="25%">Monto</th>
	                            </tr>
                        	</tbody>
                        </table>					
                    </div>
                    <div class="bottom"><span></span></div>
                </div>
              
                
                
                <div class="contenido_tabla">
				<table class="tablaFacturacion">
					<tbody>					
						
						<it:iterator var="item" value="#{recargaHistoricoController.recargaHistoricoBean.detalleRecargas}" rowIndexVar="row">	
							<c:set var="style" value="#{row % 2 == 0 ? '': 'impar'}" scope="page" />
							
							<tr class="<h:outputText value="#{style}"/>" >
								<td width="25%"><h:outputText value="#{item.fechaRecarga}"><f:convertDateTime pattern="dd-MM-yyyy" locale="es" /></h:outputText></td>
								<td width="15%"><h:outputText value="#{item.fechaRecarga}"><f:convertDateTime pattern="HH:mm" locale="es" /></h:outputText></td>
								<td width="35%"><h:outputText value="#{item.plataformaRecarga}#{item.tipoRecarga}"></h:outputText></td>
								<td width="25%">$ <h:outputText value="#{item.montoRecarga}"><f:convertNumber currencyCode="CLP" locale="es" /></h:outputText></td>																							
							</tr>
							
						</it:iterator>
																							
					</tbody>
				</table>						
			</div>	
                
			<cm:getProperty node="${infoDebesSaber[0]}" name="html" />
			
		</h:panelGroup>

        <h:panelGroup rendered="#{!recargaHistoricoController.existeHistoricoRecargas}">     
			<div class="mensaje-alerta-sistema-pequeno"> 
       			<div class="clearfix sub-contenedor"> 
           			<div class="contenedor-imagen"> 
           				<div class="imagen"></div> 
           			</div> 
           			<div class="texto">No se obtuvo informaci&oacute;n del hist&oacute;rico de recargas.</div> 
       			</div> 
   			</div>
        </h:panelGroup>
	</h:panelGroup>

	<!-- MENSAJES -->
	<jsp:include page="../common/messages_table.jsp"></jsp:include>

</f:view>